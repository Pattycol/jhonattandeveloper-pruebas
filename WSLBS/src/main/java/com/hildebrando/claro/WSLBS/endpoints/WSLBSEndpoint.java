package com.hildebrando.claro.WSLBS.endpoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Rol;
import com.btg.claro.LBS.domain.TipoServicio;
import com.btg.claro.LBS.domain.Usuario;
import com.hildebrando.claro.WSLBS.pojos.ArbolArea;
import com.hildebrando.claro.WSLBS.pojos.ArbolEmpresa;
import com.hildebrando.claro.WSLBS.pojos.ArbolUsuario;
import com.hildebrando.claro.WSLBS.pojos.Areas;
import com.hildebrando.claro.WSLBS.pojos.Resultado;
import com.hildebrando.claro.WSLBS.service.MantenimientoAreaService;
import com.hildebrando.claro.WSLBS.service.MantenimientoConsultasService;
import com.hildebrando.claro.WSLBS.service.MantenimientoEmpresaService;
import com.hildebrando.claro.WSLBS.service.MantenimientoTipoServicioService;
import com.hildebrando.claro.WSLBS.service.MantenimientoUsuarioService;
import com.hildebrando.claro.WSLBS.util.Constantes;
import com.hildebrando.claro.WSLBS.util.Util;
import com.hildebrando.claro.WSLBS.ws.ResultadoConsulta;

@Service("WSLBSService")
@WebService(serviceName = "WSLBSService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class WSLBSEndpoint {

    @Autowired
    private MantenimientoUsuarioService mantenimientoUsuarioService;

    @Autowired
    private MantenimientoEmpresaService mantenimientoEmpresaService;

    @Autowired
    private MantenimientoAreaService mantenimientoAreaService;

    @Autowired
    private MantenimientoTipoServicioService mantenimientoTipoServicioService;

    @Autowired
    private MantenimientoConsultasService mantenimientoConsultasService;

    Usuario verificar(String idUsuario) {


        Usuario usuario = new Usuario();

        boolean existeUsuario = mantenimientoUsuarioService.existeUsuario(idUsuario);

        if (existeUsuario) {

            usuario = mantenimientoUsuarioService.obtenerUsuarioPorNumero(idUsuario);

        } else {
            Rol rol = new Rol();
            rol = mantenimientoUsuarioService.obtenerRol(Constantes.ROL_ADM);
            Date hoy = new Date();
            usuario.setRol(rol);
            usuario.setNumero(idUsuario);
            usuario.setFechaCreacion(hoy);
            usuario.setEstado(Constantes.ESTADO_ACTIVO);
            usuario.setFechaActualizacion(hoy);
            mantenimientoUsuarioService.guardarUsuario2(usuario);
            usuario = mantenimientoUsuarioService.obtenerUsuarioPorNumero(idUsuario);
        }

        return usuario;

    }

    @WebMethod
    public Resultado crearEmpresa(@WebParam(name = "rucEmpresa") String rucEmpresa,
            @WebParam(name = "razonSocial") String razonSocial,
            @WebParam(name = "idUsuario") String idUsuario) {

        Resultado resultado = new Resultado();

        Empresa nEmpresa = new Empresa();

        if (rucEmpresa.matches("[0-9]{11}") && razonSocial.matches("[a-zA-Z]+[ x0Bf ]*[a-zA-Z]+")
                && idUsuario.matches("[a-zA-Z0-9]+")) {

            boolean existe = mantenimientoEmpresaService.existeEmpresa(rucEmpresa);

            Usuario usuario = new Usuario();
            usuario = verificar(idUsuario);

            if (existe) {

                Empresa empresa = mantenimientoEmpresaService.obtenerEmpresa(rucEmpresa);

                if (empresa.getEstado().equals(Constantes.ESTADO_ACTIVO)) {
                    resultado.setMensaje("" + Constantes.EMPRESA_YA_EXISTE);
                }

                if (empresa.getEstado().equals(Constantes.ESTADO_INACTIVO)) {
                    resultado.setMensaje("" + Constantes.EMPRESA_YA_EXISTE);
                }

                if (empresa.getEstado().equals(Constantes.ESTADO_ELIMINADO)) {
                    nEmpresa.setId(empresa.getId());
                    nEmpresa.setConsultasPorMes(empresa.getConsultasPorMes());
                    nEmpresa.setVista(empresa.getVista());
                    nEmpresa.setRuc(empresa.getRuc());
                    nEmpresa.setRazonSocial(razonSocial);
                    nEmpresa.setFechaCreacion(empresa.getFechaCreacion());
                    nEmpresa.setEstado(Constantes.ESTADO_ACTIVO);

                    List<Area> list = mantenimientoAreaService.getAreasPorEmpresa(empresa.getId());

                    for (int i = 0; i < 4 && list.size() > 0; i++) {
                        Area area = new Area();

                        if (i < list.size()) {

                            area = list.get(i);
                            area.setNombre("A" + (i + 1));
                            area.setEmpresa(nEmpresa);
                            area.setEstado(Constantes.ESTADO_ACTIVO);
                        } else {

                            area.setNombre("A" + (i + 1));
                            area.setEmpresa(nEmpresa);
                            area.setEstado(Constantes.ESTADO_ACTIVO);
                        }

                        mantenimientoAreaService.guardarArea(area, -1, usuario);
                    }

                    if (list.size() == 0 || list == null) {

                        for (int i = 0; i < 4; i++) {
                            Area area = new Area();
                            area.setNombre("A" + (i + 1));
                            area.setEmpresa(nEmpresa);
                            area.setEstado(Constantes.ESTADO_ACTIVO);
                            mantenimientoAreaService.guardarArea(area, -1, usuario);
                        }

                    }

                    mantenimientoEmpresaService.guardarEmpresa(nEmpresa, usuario);

                    resultado.setMensaje("" + Constantes.EMPRESA_ACTUALIZADO);
                }
                if (!empresa.getEstado().equals(Constantes.ESTADO_ACTIVO)
                        && !empresa.getEstado().equals(Constantes.ESTADO_INACTIVO)
                        && !empresa.getEstado().equals(Constantes.ESTADO_ELIMINADO)) {

                    resultado.setMensaje("" + Constantes.ACCION_INVALIDA_2);

                }

            } else {

                nEmpresa.setRuc(rucEmpresa);
                nEmpresa.setRazonSocial(razonSocial);
                nEmpresa.setEstado(Constantes.ESTADO_ACTIVO);
                mantenimientoEmpresaService.guardarEmpresa(nEmpresa, usuario);

                for (int i = 0; i < 4; i++) {
                    Area area = new Area();
                    area.setNombre("A" + (i + 1));
                    area.setEmpresa(nEmpresa);
                    mantenimientoAreaService.guardarArea(area, -1, usuario);
                }

                mantenimientoEmpresaService.guardarEmpresa(nEmpresa, usuario);
                resultado.setMensaje("" + Constantes.OK);

            }

        } else {

            if (!rucEmpresa.matches("[0-9]{11}")) {
                resultado.setMensaje("" + Constantes.RUC_INVALIDO);
            } else {
                if (!razonSocial.matches("[a-zA-Z]+[ x0Bf ]*[a-zA-Z]+")) {
                    resultado.setMensaje("" + Constantes.RAZON_SOCIAL_INVALIDA);
                } else {

                    resultado.setMensaje("" + Constantes.ID_USUARIO_INVALIDO);

                }

            }

        }

        return resultado;
    }

    @WebMethod
    public Resultado modificarEmpresa(@WebParam(name = "rucEmpresa") String rucEmpresa,
            @WebParam(name = "razonSocial") String razonSocial,
            @WebParam(name = "accion") String accion, @WebParam(name = "idUsuario") String idUsuario) {

        Resultado resultado = new Resultado();

        if (rucEmpresa.matches("[0-9]{11}") && razonSocial.matches("[a-zA-Z]+[ x0Bf ]*[a-zA-Z]+")
                && accion.matches("[AIE]") && idUsuario.matches("[a-zA-Z0-9]+")) {

            Usuario usuario = new Usuario();
            usuario = verificar(idUsuario);

            Empresa empresa = mantenimientoEmpresaService.obtenerEmpresa(rucEmpresa);
            char valor = 'N';

            if (accion.toCharArray()[0] == Constantes.ESTADO_ACTIVO) {
                valor = Constantes.ESTADO_ACTIVO;
            }

            if (accion.toCharArray()[0] == Constantes.ESTADO_INACTIVO) {
                valor = Constantes.ESTADO_INACTIVO;
            }

            if (accion.toCharArray()[0] == Constantes.ESTADO_ELIMINADO) {
                valor = Constantes.ESTADO_ELIMINADO;
            }

            if (empresa != null && valor != 'N') {

                empresa.setRazonSocial(razonSocial);
                empresa.setEstado(valor);
                mantenimientoEmpresaService.guardarEmpresa(empresa, usuario);

                // Para guardar areas de empresas
                List<Area> list = mantenimientoAreaService.getAreasPorEmpresa(empresa.getId());
                for (int i = 0; i < list.size(); i++) {
                    Area area = new Area();
                    area = list.get(i);
                    area.setEstado(valor);
                    area.setEmpresa(empresa);
                    mantenimientoAreaService.guardarArea(area, -1, usuario);
                }

                // Para guardar areas de empresas
                List<Area> list3 = mantenimientoAreaService.getAreasPorEmpresa(empresa.getId());

                // Para guardar usuarios
                for (int i = 0; i < list3.size(); i++) {

                    List<Usuario> list2 = mantenimientoUsuarioService.getUsuariosPorArea(list3
                            .get(i));

                    for (int j = 0; j < list2.size(); j++) {
                        Usuario usuario2 = new Usuario();
                        usuario2 = list2.get(j);
                        usuario2.setEstado(valor);

                        mantenimientoUsuarioService.actualizarUsuarioExistente(usuario2, usuario);

                    }

                }

                resultado.setMensaje("" + Constantes.EMPRESA_ACTUALIZADO);

            } else {

                if (empresa == null) {
                    resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);

                } else {

                    resultado.setMensaje("" + Constantes.ACCION_INVALIDA_2);
                }

            }

            return resultado;

        } else {

            if (!rucEmpresa.matches("[0-9]{11}")) {

                resultado.setMensaje("" + Constantes.RUC_INVALIDO);
                return resultado;
            } else {

                if (!razonSocial.matches("[a-zA-Z]+[ x0Bf ]*[a-zA-Z]+")) {
                    resultado.setMensaje("" + Constantes.RAZON_SOCIAL_INVALIDA);
                    return resultado;
                } else {

                    if (!accion.matches("[AIE]")) {
                        resultado.setMensaje("" + Constantes.ACCION_INVALIDA_2);
                        return resultado;
                    } else {
                        resultado.setMensaje("" + Constantes.ID_USUARIO_INVALIDO);
                        return resultado;

                    }

                }
            }

        }

    }

    @WebMethod
    public Resultado crearUsuario(@WebParam(name = "rucEmpresa") String rucEmpresa,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "nombreArea") String nombreArea,
            @WebParam(name = "idRol") String idRol,
            @WebParam(name = "tipoServicio") String tipoServicio,
            @WebParam(name = "idUsuario") String idUsuario) {

        Resultado resultado = new Resultado();

        Empresa empresa;

        boolean lineasMaximas = false;

        if (rucEmpresa.matches("[0-9]{11}") && telefono.matches("[0-9]{9}")
                && nombreArea.matches("[A-Z]{1}[0-9]{1}") && idRol.matches("[0-9]{1}")
                && tipoServicio.matches("[A-Z]+[0-9]*") && idUsuario.matches("[a-zA-Z0-9]+")) {

            empresa = mantenimientoEmpresaService.obtenerEmpresa(rucEmpresa);

            if (empresa != null) {

                int numeroLineasMaximas = mantenimientoEmpresaService
                        .obtenerMaximoLineas(rucEmpresa);

                int cantidadLineasHoy = mantenimientoEmpresaService
                        .obtenerCantidadLineas(rucEmpresa);

                if (numeroLineasMaximas > cantidadLineasHoy) {
                    lineasMaximas = true;
                }

                if (numeroLineasMaximas == -1) {
                    lineasMaximas = true;

                }

                Usuario usuario = new Usuario();
                usuario = verificar(idUsuario);
                int idArea = -1;

                List<Area> list = mantenimientoAreaService.getAreasPorEmpresaRuc(rucEmpresa);

                for (int i = 0; i < list.size(); i++) {

                    if (list.get(i).getNombre().equals(nombreArea)) {
                        idArea = list.get(i).getId();
                        i = list.size();
                    }

                }

                TipoServicio tipoServicio2 = null;
                tipoServicio2 = mantenimientoTipoServicioService.validaTipoServicio(tipoServicio);

                Usuario usuarioBuscado = mantenimientoUsuarioService
                        .buscarUsuarioPorNumero(telefono);

                Rol rol = new Rol();
                rol = mantenimientoUsuarioService.getRol(Integer.parseInt(idRol));

                if (usuarioBuscado != null) {

                    if (usuarioBuscado.getEstado().equals(Constantes.ESTADO_ACTIVO)) {

                        resultado.setMensaje("" + Constantes.USUARIO_YA_EXISTE);

                        return resultado;

                    } else {

                        if (usuarioBuscado.getEstado().equals(Constantes.ESTADO_INACTIVO)) {
                            resultado.setMensaje("" + Constantes.USUARIO_YA_EXISTE);

                            return resultado;
                        } else {

                            if (usuarioBuscado.getEstado().equals(Constantes.ESTADO_ELIMINADO)) {

                                if (idArea != -1
                                        && tipoServicio2 != null
                                        && (rol.getId().intValue() == 2 || rol.getId().intValue() == 3)) {
                                    usuarioBuscado.setEstado(Constantes.ESTADO_ACTIVO);
                                    usuarioBuscado.setRol(rol);
                                    usuarioBuscado.setTipoServicio(tipoServicio2);

                                    int areas[] = new int[1];
                                    areas[0] = idArea;

                                    mantenimientoUsuarioService.guardarUsuario3(usuarioBuscado,
                                            areas, usuario);

                                    resultado.setMensaje("" + Constantes.USUARIO_ACTUALIZADO);

                                } else {

                                    if (idArea == -1) {

                                        resultado.setMensaje("" + Constantes.AREA_NO_EXISTE);

                                        return resultado;

                                    } else {

                                        if (tipoServicio2 == null) {

                                            resultado.setMensaje(""
                                                    + Constantes.TIPO_SERVICIO_INVALIDO);

                                            return resultado;

                                        } else {

                                            resultado.setMensaje("" + Constantes.ROL_INVALIDO);

                                            return resultado;

                                        }

                                    }

                                }

                            } else {

                                resultado.setMensaje("" + Constantes.ACCION_INVALIDA_2);

                                return resultado;

                            }

                        }

                    }

                } else {

                    if (idArea != -1 && tipoServicio2 != null && empresa != null
                            && lineasMaximas == true && telefono.matches("[0-9]*")
                            && (telefono.length() == Constantes.CANTIDAD_DIGITOS_NUMERO)
                            && (rol.getId().intValue() == 2 || rol.getId().intValue() == 3)) {

                        // Crear un nuevo usuario
                        Usuario newUsuario = new Usuario();
                        newUsuario.setNumero(telefono);
                        newUsuario.setEstado(Constantes.ESTADO_ACTIVO);
                        newUsuario.setRol(rol);
                        newUsuario.setTipoServicio(tipoServicio2);
                        int areas[] = new int[1];
                        areas[0] = idArea;
                        mantenimientoUsuarioService.guardarUsuario3(newUsuario, areas, usuario);
                        resultado.setMensaje("" + Constantes.OK);

                        return resultado;

                    } else {

                        if (empresa == null) {
                            resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);

                            return resultado;

                        } else {
                            if (!telefono.matches("[0-9]*")) {

                                resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);

                                return resultado;

                            } else {

                                if (!(telefono.length() == Constantes.CANTIDAD_DIGITOS_NUMERO)) {

                                    resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);

                                    return resultado;

                                } else {

                                    if (idArea == -1) {

                                        resultado.setMensaje("" + Constantes.AREA_NO_EXISTE);

                                        return resultado;

                                    } else {

                                        if (tipoServicio2 == null) {

                                            resultado.setMensaje(""
                                                    + Constantes.TIPO_SERVICIO_INVALIDO);

                                            return resultado;

                                        } else {

                                            if (lineasMaximas == false) {

                                                resultado
                                                        .setMensaje("" + Constantes.LINEAS_MAXIMAS);

                                                return resultado;

                                            } else {

                                                resultado.setMensaje("" + Constantes.ROL_INVALIDO);

                                                return resultado;

                                            }

                                        }
                                    }
                                }
                            }

                        }

                    }

                }

                if (empresa.getTipoServicio() == null && empresa != null) {

                    if (tipoServicio2.getNombre().equals(Constantes.TS_LBS1)
                            || tipoServicio2.getNombre().equals(Constantes.TS_LBS2)) {
                        tipoServicio2 = null;
                        tipoServicio2 = mantenimientoTipoServicioService
                                .validaTipoServicio(Constantes.TS_LBSSMS);

                    }
                    empresa.setTipoServicio(tipoServicio2);
                    mantenimientoEmpresaService.guardarEmpresa(empresa, usuario);

                }

                return resultado;

            } else {

                resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);
                return resultado;

            }
        } else {

            if (!rucEmpresa.matches("[0-9]{11}")) {

                resultado.setMensaje("" + Constantes.RUC_INVALIDO);
                return resultado;
            } else {

                if (!telefono.matches("[0-9]{9}")) {
                    resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);
                    return resultado;
                } else {
                    if (!nombreArea.matches("[A-Z]{1}[0-9]{1}")) {
                        resultado.setMensaje("" + Constantes.AREA_INVALIDA);
                        return resultado;
                    } else {

                        if (!idRol.matches("[0-9]{1}")) {
                            resultado.setMensaje("" + Constantes.ROL_INVALIDO);
                            return resultado;
                        } else {
                            if (!tipoServicio.matches("[A-Z]+[0-9]*")) {
                                resultado.setMensaje("" + Constantes.TIPO_SERVICIO_INVALIDO);
                                return resultado;
                            } else {
                                resultado.setMensaje("" + Constantes.ID_USUARIO_INVALIDO);
                                return resultado;

                            }

                        }

                    }

                }

            }

        }

    }

    @WebMethod
    public Resultado modificarUsuario(@WebParam(name = "rucEmpresa") String rucEmpresa,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "nombreArea") String nombreArea,
            @WebParam(name = "idRol") String idRol, @WebParam(name = "accion") String accion,
            @WebParam(name = "idUsuario") String idUsuario) {

        Resultado resultado = new Resultado();

        if (rucEmpresa.matches("[0-9]{11}") && telefono.matches("[0-9]{9}")
                && nombreArea.matches("[A-Z]{1}[0-9]{1}") && idRol.matches("[23]")
                && accion.matches("[AIE]") && idUsuario.matches("[a-zA-Z0-9]+")) {

            Usuario usuario = new Usuario();
            usuario = verificar(idUsuario);
            int idArea = -1;

            Rol rol = new Rol();
            rol = mantenimientoUsuarioService.getRol(Integer.parseInt(idRol));

            boolean existe = mantenimientoEmpresaService.existeEmpresa(rucEmpresa);

            if (!existe) {

                resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);
                return resultado;
            }

            List<Area> list = mantenimientoAreaService.getAreasPorEmpresaRuc(rucEmpresa);

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNombre().equals(nombreArea)) {
                    idArea = list.get(i).getId();
                    i = list.size();
                }

            }

            Usuario usuarioBuscado = mantenimientoUsuarioService.buscarUsuarioPorNumero(telefono);

            if (usuarioBuscado != null) {

                if (idArea != -1 && (rol.getId().intValue() == 2 || rol.getId().intValue() == 3)) {

                    usuarioBuscado.setRol(rol);
                    int areas[] = new int[1];
                    areas[0] = idArea;

                    char valor = 'N';

                    if (accion.toCharArray()[0] == Constantes.ESTADO_ACTIVO) {
                        valor = Constantes.ESTADO_ACTIVO;
                    }

                    if (accion.toCharArray()[0] == Constantes.ESTADO_INACTIVO) {
                        valor = Constantes.ESTADO_INACTIVO;
                    }

                    if (accion.toCharArray()[0] == Constantes.ESTADO_ELIMINADO) {
                        valor = Constantes.ESTADO_ELIMINADO;
                    }
                    if (valor != 'N') {

                        usuarioBuscado.setEstado(valor);
                    } else {
                        resultado.setMensaje("" + Constantes.ACCION_INVALIDA_2);
                        return resultado;
                    }

                    mantenimientoUsuarioService.guardarUsuario3(usuarioBuscado, areas, usuario);
                    resultado.setMensaje("" + Constantes.OK);

                } else {

                    if (idArea == -1) {

                        resultado.setMensaje("" + Constantes.AREA_NO_EXISTE);

                    } else {

                        resultado.setMensaje("" + Constantes.ROL_INVALIDO);

                        return resultado;
                    }

                }

            } else {

                resultado.setMensaje("" + Constantes.USUARIO_NO_EXISTE);

            }

        } else {

            if (!rucEmpresa.matches("[0-9]{11}")) {

                resultado.setMensaje("" + Constantes.RUC_INVALIDO);
                return resultado;
            } else {

                if (!telefono.matches("[0-9]{9}")) {
                    resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);
                    return resultado;
                } else {
                    if (!nombreArea.matches("[A-Z]{1}[0-9]{1}")) {
                        resultado.setMensaje("" + Constantes.AREA_INVALIDA);
                        return resultado;
                    } else {

                        if (!idRol.matches("[23]")) {
                            resultado.setMensaje("" + Constantes.ROL_INVALIDO);
                            return resultado;
                        } else {
                            if (!accion.matches("[AIE]")) {
                                resultado.setMensaje("" + Constantes.ACCION_INVALIDA_2);
                                return resultado;
                            } else {
                                resultado.setMensaje("" + Constantes.ID_USUARIO_INVALIDO);
                                return resultado;

                            }

                        }

                    }

                }

            }

        }

        return resultado;
    }

    @WebMethod
    public Resultado historicoConsultas(@WebParam(name = "fechaInicio") String fechaInicio,
            @WebParam(name = "fechaFin") String fechaFin,
            @WebParam(name = "numeroConsultante") String numeroConsultante,
            @WebParam(name = "numeroConsultado") String numeroConsultado) {

        String mensaje = "";
        Resultado resultado = new Resultado();

        List<ResultadoConsulta> resultadoConsultas = new ArrayList<ResultadoConsulta>();

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yy");
        Date fechaInicio2 = null;
        Date fechaFin2 = null;

        try {

            fechaInicio2 = formatoDelTexto.parse(fechaInicio);
            fechaFin2 = formatoDelTexto.parse(fechaFin);

        } catch (ParseException e) {
            e.printStackTrace();
            resultado.setMensaje("" + Constantes.FECHAS_INVALIDAS);
            return resultado;
        }

        if (numeroConsultante.matches("[0-9]{9}") && numeroConsultado.matches("[0-9]{9}")) {

            Usuario usuarioNumeroConsultante = mantenimientoUsuarioService
                    .buscarUsuarioPorNumero(numeroConsultante);
            Usuario usuarioNumeroConsultado = mantenimientoUsuarioService
                    .buscarUsuarioPorNumero(numeroConsultado);

            if (usuarioNumeroConsultante == null || usuarioNumeroConsultado == null) {

                resultado.setMensaje("" + Constantes.NUMEROS_NO_EXISTEN);
                return resultado;


            } else {

                resultadoConsultas = mantenimientoConsultasService
                        .reporteConsultadoConsultanteRangoFechas(usuarioNumeroConsultante.getId(),
                                usuarioNumeroConsultado.getId(), fechaInicio2, fechaFin2);

                if (resultadoConsultas == null) {

                    resultado.setMensaje("" + Constantes.NO_DATOS);
                    return resultado;

                }
                int i = 1;

                for (ResultadoConsulta c : resultadoConsultas) {

                    mensaje = mensaje + "Consulta (" + i + "): Fecha de Consulta => "
                            + c.getFecha() + ", Resultado de Consulta => " + c.getMensaje() + "\n";

                    i++;
                }

                resultado.setMensaje("" + mensaje);
                return resultado;

            }

        } else {

            resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);

            return resultado;

        }

    }

    @WebMethod
    public Resultado desactivacionBloqueoLBS(@WebParam(name = "telefono") String telefono,
            @WebParam(name = "accion") String accion,
            @WebParam(name = "idUsuario") String idUsuario,
            @WebParam(name = "rucEmpresa") String rucEmpresa) {

        Resultado resultado = new Resultado();
        Usuario usuario = new Usuario();

        if (!Util.vacio(telefono) && Util.vacio(rucEmpresa)) {

            if (telefono.matches("[0-9]{9}") && accion.matches("[AIE]")
                    && idUsuario.matches("[a-zA-Z0-9]+")) {


                
                usuario = verificar(idUsuario);

                Usuario usuarioBuscado = mantenimientoUsuarioService
                        .buscarUsuarioPorNumero(telefono);

                if (usuarioBuscado != null) {
                    usuarioBuscado.setEstado(accion.toCharArray()[0]);

                    mantenimientoUsuarioService.actualizarUsuarioExistente(usuarioBuscado, usuario);

                    if (accion.toCharArray()[0] == Constantes.ESTADO_INACTIVO) {

                        resultado.setMensaje("" + Constantes.OK);
                        return resultado;

                    } else {
                        resultado.setMensaje("" + Constantes.OK);
                        return resultado;
                    }

                } else {

                    resultado.setMensaje("" + Constantes.USUARIO_NO_EXISTE);
                    return resultado;
                }

            } else {

                if (!telefono.matches("[0-9]{9}")) {
                    resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);
                    return resultado;
                } else {

                    if (!accion.matches("[AIE]")) {
                        resultado.setMensaje("" + Constantes.ACCION_INVALIDA_1);
                        return resultado;
                    } else {
                        resultado.setMensaje("" + Constantes.ID_USUARIO_INVALIDO);
                        return resultado;

                    }

                }

            }

        } else {

            if (!Util.vacio(rucEmpresa) && Util.vacio(telefono)) {

                if (rucEmpresa.matches("[0-9]{11}") && accion.matches("[AIE]")
                        && idUsuario.matches("[a-zA-Z0-9]+")) {

                    usuario = verificar(idUsuario);

                    Empresa empresa = mantenimientoEmpresaService.obtenerEmpresa(rucEmpresa);

                    if (empresa != null) {

                        empresa.setEstado(accion.toCharArray()[0]);

                        mantenimientoEmpresaService.guardarEmpresa(empresa, usuario);

                        // Para guardar areas de empresas
                        List<Area> list = mantenimientoAreaService.getAreasPorEmpresa(empresa
                                .getId());
                        for (int i = 0; i < list.size(); i++) {
                            Area area = new Area();
                            area = list.get(i);
                            area.setEstado(accion.toCharArray()[0]);
                            area.setEmpresa(empresa);
                            mantenimientoAreaService.guardarArea(area, -1, usuario);
                        }

                        // Para guardar areas de empresas
                        List<Area> list3 = mantenimientoAreaService.getAreasPorEmpresa(empresa
                                .getId());

                        // Para guardar usuarios
                        for (int i = 0; i < list3.size(); i++) {

                            List<Usuario> list2 = mantenimientoUsuarioService
                                    .getUsuariosPorArea(list3.get(i));

                            for (int j = 0; j < list2.size(); j++) {
                                Usuario usuario2 = new Usuario();
                                usuario2 = list2.get(j);
                                usuario2.setEstado(accion.toCharArray()[0]);

                                mantenimientoUsuarioService.actualizarUsuarioExistente(usuario2,
                                        usuario);

                            }

                        }

                        if (accion.toCharArray()[0] == Constantes.ESTADO_INACTIVO) {

                            resultado.setMensaje("" + Constantes.OK);

                        } else {

                            resultado.setMensaje("" + Constantes.OK);
                        }

                    } else {
                        resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);

                    }

                    return resultado;

                } else {

                    if (!rucEmpresa.matches("[0-9]{11}")) {

                        resultado.setMensaje("" + Constantes.RUC_INVALIDO);
                        return resultado;
                    } else {

                        if (!accion.matches("[AIE]")) {
                            resultado.setMensaje("" + Constantes.ACCION_INVALIDA_1);
                            return resultado;
                        } else {
                            resultado.setMensaje("" + Constantes.ID_USUARIO_INVALIDO);
                            return resultado;

                        }

                    }

                }

            } else {

                resultado.setMensaje("" + Constantes.TELEFONO_O_RUC);
                return resultado;

            }

        }

    }

    @WebMethod
    public Resultado verificacionCantidadLineas(@WebParam(name = "rucEmpresa") String rucEmpresa) {

        Resultado resultado = new Resultado();

        int cantidad = 0;

        if (rucEmpresa.length() == Constantes.CANTIDAD_DIGITOS_RUC && rucEmpresa.matches("[0-9]*")) {

            cantidad = mantenimientoEmpresaService.obtenerCantidadLineas(rucEmpresa);

            if (cantidad >= 0) {
                resultado.setMensaje("" + Constantes.OK);
                resultado.setCantidad("" + cantidad);
            } else {
                resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);

            }

        } else {

            resultado.setMensaje("" + Constantes.RUC_INVALIDO);

        }

        return resultado;

    }

    @WebMethod
    public Resultado validaExistenciaLinea(@WebParam(name = "telefono") String telefono) {
        Resultado resultado = new Resultado();

        HashSet<String> nombreAreas = new HashSet<String>();
        HashSet<String> nombreEmpresas = new HashSet<String>();

        if (telefono.length() == Constantes.CANTIDAD_DIGITOS_NUMERO && telefono.matches("[0-9]*")) {

            Usuario usuarioBuscado = mantenimientoUsuarioService.buscarUsuarioPorNumero(telefono);

            if (usuarioBuscado != null) {
                List<Area> listaAreas = usuarioBuscado.getAreas();
                for (Area area : listaAreas) {
                    if (area != null) {

                        nombreAreas.add(area.getNombre());
                        Empresa empresa = area.getEmpresa();
                        if (empresa != null) {
                            nombreEmpresas.add(empresa.getRazonSocial());
                        }
                    }

                }
                Iterator<String> iAre = nombreAreas.iterator();
                Iterator<String> iEmpr = nombreEmpresas.iterator();

                ArbolEmpresa Empresa = new ArbolEmpresa();

                while (iEmpr.hasNext()) {
                    Empresa.setNombre(iEmpr.next());

                }

                List<ArbolArea> area = new ArrayList<ArbolArea>();
                Areas areas2 = new Areas();

                ArbolArea arbolArea = new ArbolArea();

                while (iAre.hasNext()) {
                    arbolArea.setNombre(iAre.next());

                }

                List<ArbolUsuario> usuario = new ArrayList<ArbolUsuario>();

                ArbolUsuario arbolUsuario = new ArbolUsuario();
                arbolUsuario.setId(1);
                arbolUsuario.setNumero(usuarioBuscado.getNumero());
                arbolUsuario.setRol(usuarioBuscado.getRol().getNombre());
                arbolUsuario.setEstado(usuarioBuscado.getEstado().toString());
                usuario.add(arbolUsuario);

                arbolArea.setUsuario(usuario);

                area.add(arbolArea);

                areas2.setArea(area);

                Empresa.setAreas(areas2);

                resultado.setEmpresa(Empresa);

            } else {

                resultado.setMensaje("" + Constantes.USUARIO_NO_EXISTE);

            }

        } else {

            resultado.setMensaje("" + Constantes.TELEFONO_INVALIDO);
        }

        return resultado;

    }

    @WebMethod
    public Resultado arbolLinea(@WebParam(name = "rucEmpresa") String rucEmpresa) {

        Resultado resultado = new Resultado();

        ArbolEmpresa Empresa = new ArbolEmpresa();

        if (rucEmpresa.length() == Constantes.CANTIDAD_DIGITOS_RUC && rucEmpresa.matches("[0-9]*")) {

            Empresa empresa = mantenimientoEmpresaService.obtenerEmpresa(rucEmpresa);

            if (empresa != null) {
                Empresa.setNombre(empresa.getRazonSocial().toUpperCase());
                List<Area> listAreas = mantenimientoAreaService.getAreasPorEmpresa(empresa.getId());

                List<ArbolArea> area = new ArrayList<ArbolArea>();
                Areas areas2 = new Areas();

                for (int i = 0; i < listAreas.size(); i++) {

                    ArbolArea arbolArea = new ArbolArea();
                    arbolArea.setNombre(listAreas.get(i).getNombre());

                    List<Usuario> listUsuariosxArea = mantenimientoUsuarioService
                            .getUsuariosPorArea(listAreas.get(i));

                    List<ArbolUsuario> usuario = new ArrayList<ArbolUsuario>();
                    int cont = 0;

                    for (int j = 0; j < listUsuariosxArea.size(); j++) {

                        ArbolUsuario arbolUsuario = new ArbolUsuario();

                        Usuario usuario2 = new Usuario();
                        usuario2 = listUsuariosxArea.get(j);

                        if (usuario2 != null) {
                            cont++;
                            arbolUsuario.setId(cont);
                            arbolUsuario.setNumero(usuario2.getNumero());
                            arbolUsuario.setRol(usuario2.getRol().getNombre());
                            arbolUsuario.setEstado(usuario2.getEstado().toString());
                            usuario.add(arbolUsuario);
                        }

                    }
                    arbolArea.setUsuario(usuario);

                    area.add(arbolArea);

                }

                areas2.setArea(area);
                Empresa.setAreas(areas2);
                resultado.setEmpresa(Empresa);

            } else {

                resultado.setMensaje("" + Constantes.EMPRESA_NO_EXISTE);

            }

        } else {

            resultado.setMensaje("" + Constantes.RUC_INVALIDO);

        }

        return resultado;

    }

}
