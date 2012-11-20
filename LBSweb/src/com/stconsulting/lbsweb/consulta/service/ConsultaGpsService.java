/*
 * ConsultaGpsService.java
 *
 * Created on 12 de agosto de 2005, 12:15 PM
 */

package com.stconsulting.lbsweb.consulta.service;


import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.common.util.*;

import com.stconsulting.lbsweb.connector.GpsService;
import com.stconsulting.lbsweb.consulta.persistence.*;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

import com.stconsulting.lbsweb.util.EnviaSMS;
import com.stconsulting.lbsweb.connector.GpsData;
import java.util.*;

import org.apache.log4j.Logger;
/**
 *
 * @author STCosulting
 */
public class ConsultaGpsService {
    protected static Logger log = null;

    private static String gpsDestiny;
    private static String gpsSource;
    private static String gpsCommand;
    private static int gpsDelay;
    static{
    	gpsDestiny=Helper.getProperty(Constants.GPS_BUNDLE,Constants.GPS_SMSC_DESTINY);
    	gpsSource=Helper.getProperty(Constants.GPS_BUNDLE,Constants.GPS_SMSC_SOURCE);
    	gpsCommand=Helper.getProperty(Constants.GPS_BUNDLE,Constants.GPS_SMSC_COMMAND);
    	gpsDelay=(int)Helper.getPropertyDouble(Constants.GPS_BUNDLE,Constants.GPS_SMSC_DELAY);
    }    

    /** Creates a new instance of ConsultaGpsService */
    public ConsultaGpsService() {
        log = Logger.getLogger(this.getClass());
    }
    
    public ResultadoConsultaWeb consultaGPS(Usuario usuario,ParametroConsultaWeb parametro) throws ServiceException {
        ResultadoConsultaWeb resultado=null;        
        ConsultaService consultaService=null;
        TransactionContext tx = null;
        try{
            log.debug("Entrando a consultaGPS : "+new java.util.Date());
            String mobileOrigen=usuario.getTelefono();
            
            //instanciamos todos los objetos
            consultaService=new ConsultaService();
            resultado = new ResultadoConsultaWeb();
            resultado.setMobileOrigen(mobileOrigen);
            resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
            //se crea la transacci�n para validar numeros
            tx = new TransactionContext();
            tx.begin();
            LocalizacionGpsDAO gpsDAO=new LocalizacionGpsDAO(tx);
            ConsultaDAO consultaDAO=new ConsultaDAO(tx);
            //CobroDAO  cobroDAO=new CobroDAO(tx);
            
            String resultadoValidacionNumeros=consultaService.validaNumeros(usuario,parametro.getListaMobiles(),consultaDAO);
            log.debug("luego de validar numeros asociados : "+resultadoValidacionNumeros);
            if(resultadoValidacionNumeros.equals(Constants.COD_NUMERO_VALIDO)){
                    //Enviar SMS para generar el evento.
            	    EnviaSMS smsSender= new EnviaSMS();
            	    smsSender.send(gpsDestiny,gpsSource,gpsCommand);
            	    log.debug("Esperando "+ gpsDelay + " segundos");
            	    Thread.currentThread();
					Thread.sleep(gpsDelay*1000);
            	    //se actualiza la data generada por el gps
                    this.actualizaDataGps(gpsDAO);
                    List<ResultadoDetalleWeb> listaDetalle=consultaDetalleGPS(usuario,parametro,gpsDAO);
                    resultado.setListaResultadoDetalle(listaDetalle);
                    resultado.setCodResultado(Constants.COD_RESULTADO_CONSULTA_OK);
                
            }else{
                resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO);
                int ind=Integer.parseInt(resultadoValidacionNumeros);
                resultado.setMensaje("El sgte. n�mero no es v�lido : "+parametro.getListaMobiles().get(ind).getNumero());
            }
            
            //se cierra la transaccion
            tx.commit();
            tx.close();
            tx = null;
                
        }catch(Exception e){
            log.error(e);
            resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);                    
            resultado.setMensaje(Constants.MENSAJE_ERROR_INTERNO);        
             if(tx != null) {
                try {
                    tx.rollback();
                    tx.close();
                    tx = null;
                }
                catch(Exception ignore) {
                }
            }            
            throw new ServiceException(e);
        }
        return resultado;
    }
    
    public void actualizaDataGps(LocalizacionGpsDAO gpsDAO) throws ServiceException {
        GpsService gpsService=null;
        //TransactionContext tx = null;
        try{
            gpsService=new GpsService();
            List<GpsData> listaData=gpsService.obtenerDataArchivoGPS();
            gpsDAO.insertGpsData(listaData);
            
        }catch(Exception e){
            log.error(e);
            throw new ServiceException(e);
        }
    }

    private List<ResultadoDetalleWeb> consultaDetalleGPS(Usuario usuario,ParametroConsultaWeb parametro,LocalizacionGpsDAO localizacionGpsDAO) throws ServiceException{
        log.debug("Ejecutando metodo : consultaDetalleGPS");
        List<ResultadoDetalleWeb> listaDetalle;
        ResultadoDetalleWeb resultadoDetalle=null;
        
        listaDetalle=new ArrayList<ResultadoDetalleWeb>(); 
        try{
        Localizacion localizacion;
        List<Localizacion> lista=localizacionGpsDAO.listLocalizacion(parametro);
        if(lista.size()>0){
        	resultadoDetalle=new ResultadoDetalleWeb();
        	localizacion=lista.get(lista.size()-1);
        	resultadoDetalle.setMobileDestino(localizacion.getMobileDestino());
        	resultadoDetalle.setFechaConsulta(localizacion.getFecha());
        	resultadoDetalle.setResultado("LAT. "+localizacion.getLatitud()+"-LON. "+localizacion.getLongitud());
        	listaDetalle.add(resultadoDetalle);
        }
        //el proceso no tuvo problemas
        }catch(Exception e)  {
            log.error(e);
            throw new ServiceException(e);
        }
        return listaDetalle;
    }
    
    public String generaSqlMapaConsultaGps(ParametroConsultaWeb parametro) throws ServiceException{
        String resultSql=Constants.ERROR;
        try{
            StringBuffer sql=new StringBuffer("");
            String strBD_LBS = Helper.getSchema(Constants.BD_LBS);
            sql=sql.append(" SELECT L.LGPSI_CODIGO as codLocalizacion,");            
            sql=sql.append(" L.LGPSV_TELEFONO_ORIGEN as mobileDestino,");
            sql=sql.append(" L.LGPSD_FECHA_REGISTRO as fechaRegistro,");
            sql=sql.append(" L.LGPSV_LATITUD as latitud,");	   
            sql=sql.append(" L.LGPSV_LONGITUD as longitud");            
            sql=sql.append(" FROM "+strBD_LBS+".LBST_LOCALIZACION_GPS L");
            sql=sql.append(" WHERE 1=1 AND");
            sql=sql.append(" LGPSV_TELEFONO_ORIGEN IN(");
            if(parametro.getListaMobiles().size()>0){
            	sql=sql.append(parametro.getListaMobiles().get(parametro.getListaMobiles().size()-1).getNumero());
            }
            sql=sql.append(")"); 
            sql=sql.append(" ORDER BY 3 DESC Limit 1");
            log.debug("SQl Mapa Gps : "+sql.toString());
            resultSql=sql.toString();
        }catch(Exception e){
            log.error(e);
            throw new ServiceException(e);
        }
        log.debug("sql generaSqlMapaConsultaGps"+resultSql);
        return resultSql;
    }
}
