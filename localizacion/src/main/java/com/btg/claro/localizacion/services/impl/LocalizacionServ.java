package com.btg.claro.localizacion.services.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.lbs.ws.LBSConsultaService;
import com.btg.claro.lbs.ws.LBSEndpointService;
import com.btg.claro.localizacion.celda.Celda;
import com.btg.claro.localizacion.celda.CeldaOracleDAO;
import com.btg.claro.localizacion.daos.ListaNegraDAO;
import com.btg.claro.localizacion.daos.LocalizacionDAO;
import com.btg.claro.localizacion.daos.SesionDAO;
import com.btg.claro.localizacion.entidades.ListaNegra;
import com.btg.claro.localizacion.entidades.Localizacion;
import com.btg.claro.localizacion.entidades.Sesion;
import com.btg.claro.localizacion.services.LocalizacionService;
import com.btg.claro.localizacion.util.Bool;
import com.btg.claro.localizacion.util.Config;
import com.btg.claro.localizacion.util.Constantes;
import com.btg.claro.localizacion.util.Ejecucion;
import com.btg.claro.localizacion.util.Ubicacion;

@Service("LocalizacionService")
public class LocalizacionServ implements LocalizacionService {

    private static Logger log = LoggerFactory.getLogger(LocalizacionServ.class);

    @Autowired
    private SesionDAO sesionDAO;

    @Autowired
    private ListaNegraDAO listaNegraDAO;

    @Autowired
    private LocalizacionDAO localizacionDAO;

    @Autowired
    private CeldaOracleDAO celdaOracleDAO;

    @Override
    @Transactional
    public Sesion validarSesion(String token) {
        Sesion sesion = sesionDAO.getPorToken(token);
        if (sesion != null) {
            Calendar ahora = Calendar.getInstance();
            Calendar deSesion = Calendar.getInstance();
            deSesion.setTime(sesion.getFechaVencimiento());
            if (ahora.before(deSesion)) {
                ahora.add(Calendar.HOUR_OF_DAY, 1);
                sesionDAO.guardar(sesion);
                return sesion;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Ubicacion localizarMovil(String telefono, Sesion sesion, boolean resultado,
            Ejecucion ejecucion) {

        ejecucion.agregarHilos(this);

        log.debug("Hilo Agregado para " + sesion.getAplicacion().getNombre() + ", total :["
                + ejecucion.getTotalHilos() + "]");
        Localizacion localizacion = new Localizacion();
        localizacion.setSesion(sesion);
        Ubicacion ubicacion = null;
        if (telefono.length() <= Constantes.LONGITUD_TELEFONO) {
            localizacion.setTelefono(telefono);
            try {
                Integer.parseInt(telefono);
                if (noEstaEnBlackList(telefono, sesion)) {
                    String idCelda = ubicarMovil(telefono);
                    if (idCelda != "") {

                        Celda celdaOracle = celdaOracleDAO.getPorIdentificador2(idCelda);

                        if (celdaOracle != null) {

                            if (celdaOracle.getEstado() == 'A') {
                                log.info("Consulta Via WS realizada: idCelda="
                                        + celdaOracle.getId() + "fechaInterna="
                                        + celdaOracle.getFechaCreacion() + "Telefono=" + telefono
                                        + "Aplicacion =" + sesion.getAplicacion().getNombre()
                                        + "Date=" + new Date());

                                com.btg.claro.localizacion.entidades.Celda celda = new com.btg.claro.localizacion.entidades.Celda();
                                celda.setId(celdaOracle.getId());
                                celda.setX(celdaOracle.getX());
                                celda.setY(celdaOracle.getY());

                                localizacion.setCelda(celda);
                                localizacion.setResultado("Consulta exitosa");
                                ubicacion = new Ubicacion();
                                ubicacion.setX(celdaOracle.getX());
                                ubicacion.setY(celdaOracle.getY());
                                ubicacion.setDireccion(celdaOracle.getDireccion());

                            } else {

                                log.warn("No se encontraron datos!");

                                localizacion.setResultado("Celda no encontrada (" + idCelda + ")");

                            }


                        } else {
                            log.warn("No se encontraron datos!");

                            localizacion.setResultado("Celda no encontrada (" + idCelda + ")");
                        }
                    } else {
                        log.warn("Celda no encontrada (" + idCelda + ")");
                        localizacion.setResultado("Movil no encontrado");
                    }
                } else {

                    log.warn("Telefono en blacklist");
                    resultado = true;
                    localizacion.setResultado("Telefono en blacklist");
                }
            } catch (NumberFormatException e) {
                localizacion.setResultado("Telefono invalido");
            }
        } else {
            localizacion.setResultado("Telefono invalido (" + telefono + ")");
        }
        localizacion.setFechaConsulta(new Date());
        localizacionDAO.guardar(localizacion);

        ejecucion.removerHilos(this);

        log.debug("Hilos removido para " + sesion.getAplicacion().getNombre() + ", total :["
                + ejecucion.getTotalHilos() + "]");
        return ubicacion;
    }

    private String ubicarMovil(String telefono) {
        try {
            LBSEndpointService serviceLocator = new LBSEndpointService();
            LBSConsultaService service = serviceLocator.getLBSConsultaServicePort();
            String celda = service.obtenerUbicacion(telefono);
            if (celda != null) {
                try {
                    return celda;
                } catch (NumberFormatException e) {
                }
            }
        } catch (WebServiceException e) {
        }
        return "";
        /*
         * if(actualizarVLR(telefono)){ if(telnetVLR(telefono)){ int
         * celda=leerCelda(telefono); if(celda > 0){ // TODO leer los otros dos
         * archivos return celda; } } } return 0;
         */
    }

    private int leerCelda(String telefono) {
        String archivo = Config.getPropiedad("idCelda") + telefono;
        InputStream is;
        try {
            is = new FileInputStream(archivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String linea = "";
            String id = "";
            // leemos a ultima linea
            while ((linea = br.readLine()) != null) {
                id = linea;
            }
            br.close();
            is.close();
            return Integer.parseInt(id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private boolean telnetVLR(String telefono) {
        String ejecutable = Config.getPropiedad("lbs.telnetVLR");
        String[] cmd = { ejecutable, telefono };
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean actualizarVLR(String telefono) {
        // FIXME esto se puede hacer desde java
        String ejecutable = Config.getPropiedad("lbs.actualizarVLR");
        String ip = Config.getPropiedad("lbs.server");
        String[] cmd = { ejecutable, ip, "0051" + telefono };
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean noEstaEnBlackList(String telefono, Sesion sesion) {
        List<ListaNegra> listaNegra = listaNegraDAO.getPorAplicacion(sesion.getAplicacion());
        for (ListaNegra numero : listaNegra) {
            if (numero.getTelefono().equals(telefono)) {
                return false;
            }
        }
        return true;
    }

    public Ubicacion localizarMovil(String telefono, Sesion sesion, Bool resultado) {
        // TODO Auto-generated method stub
        return null;
    }

}
