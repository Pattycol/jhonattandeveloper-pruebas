/*
 * LocalizacionGpsDAO.java
 *
 * Created on 12 de agosto de 2005, 12:44 PM
 */

package com.stconsulting.lbsweb.consulta.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stconsulting.lbsweb.connector.GpsData;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.util.*;
/**
 *
 * @author STCosulting
 */
public class LocalizacionGpsDAO extends GenericDAO{
    private static String strBD_LBS;
    /** Creates a new instance of LocalizacionGpsDAO */
    public LocalizacionGpsDAO(TransactionContext context) {
        super(context);
        this.context = context;
        strBD_LBS = Helper.getSchema(Constants.BD_LBS);
        log = Logger.getLogger(this.getClass());       
    }
    
     public void insertGpsData(List<GpsData> listaData) throws DAOException{

	 if(listaData.size()>0){
        log.debug("Ingrese al insertGpsData");
        StringBuffer sql=new StringBuffer("");
        sql.append("INSERT INTO " +strBD_LBS+".LBST_LOCALIZACION_GPS (");
        sql.append("LGPSV_TELEFONO_ORIGEN,LGPSV_COMANDO,LGPSV_PARTE, ");
        sql.append("LGPSV_MODO,LGPSV_TIPO_POSICION,LGPSV_FORMATO_POSICION, ");
        sql.append("LGPSV_LATITUD,LGPSV_LONGITUD,LGPSV_VELOCIDAD, ");
        sql.append("LGPSV_DIRECCION,LGPSD_FECHA_REGISTRO,LGPSD_FECHA_REGISTRO_GPS) ");
        sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
        PreparedStatement pstmt = null;
        try{
            log.debug("Sql :"+sql.toString());
            pstmt = context.getConnection().prepareStatement(sql.toString());
            for(int i=0;i<listaData.size();i++){
                try{
	                GpsData data=listaData.get(i);
	                
	                pstmt.setString(1,data.getMobileOrigen());
	                pstmt.setString(2,data.getCmd());
	                pstmt.setString(3,data.getPart());
	                pstmt.setString(4,data.getMode());
	                pstmt.setString(5,data.getPositionSource());
	                pstmt.setString(6,data.getPositionFormat());
	                pstmt.setString(7,data.getPositionLat());
	                pstmt.setString(8,data.getPositionLon());
	                pstmt.setString(9,data.getSpeed());
	                pstmt.setString(10,data.getDirection());
	                java.sql.Timestamp fechaReg=null;
	                java.sql.Timestamp fechaGps=null;
	                long retrasoHoras=5*60*60*1000; //5horas
	                log.debug("fechaReg "+data.getDateRegistro().replace('.','/')+" "+data.getTimeRegistro());
	                log.debug("fechaGps "+data.getDate().replace('.','/')+" "+data.getTime());
                    fechaReg=new java.sql.Timestamp(Converter.stringToDate(data.getDateRegistro().replace('.','/')+" "+data.getTimeRegistro(),Constants.FORMATO_FECHA_HORA_24).getTime() - retrasoHoras);
                    fechaGps=new java.sql.Timestamp(Converter.stringToDate(data.getDate().replace('.','/')+" "+data.getTime(),Constants.FORMATO_FECHA_HORA_24).getTime() - retrasoHoras);
	                pstmt.setTimestamp(11,fechaReg);
	                pstmt.setTimestamp(12,fechaGps);
	                pstmt.executeUpdate();
                }catch(Exception e){
                	log.error(e);
                	continue;
                }
            }
            pstmt.close();
            pstmt = null;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e);
            if(pstmt != null){
                try {
                    pstmt.close();
                }
                catch(Exception ignore){
                }
            }
            throw new DAOException(e);
        }
	 }
        log.debug("Sali del LocalizacionGpsDAO - insertGpsData");
   }
     
     public List<Localizacion> listLocalizacionHistorica(ParametroConsultaWeb parametro)throws DAOException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Localizacion> listaResultado = null;
        StringBuffer sql=null;
        try{
            listaResultado=new ArrayList<Localizacion>();
            sql=new StringBuffer("");
            sql=sql.append(" SELECT L.LGPSI_CODIGO as codLocalizacion,");            
            sql=sql.append(" L.LGPSV_TELEFONO_ORIGEN as mobileDestino,");
            sql=sql.append(" L.LGPSD_FECHA_REGISTRO as fecha,");
            sql=sql.append(" L.LGPSV_LATITUD as latitud,");	   
            sql=sql.append(" L.LGPSV_LONGITUD as longitud");	   
            sql=sql.append(" FROM "+strBD_LBS+".LBST_LOCALIZACION_GPS L");            
            sql=sql.append(" WHERE L.LGPSV_TELEFONO_ORIGEN IN (");
            for(int i=0;i<parametro.getListaMobiles().size();i++){                
                sql=sql.append(parametro.getListaMobiles().get(i).getNumero()+",");
            }
            sql=sql.deleteCharAt(sql.length()-1);
            sql=sql.append(")"); 
            if(parametro.getFechaFin()!=null && parametro.getFechaInicio()!=null){
                sql=sql.append(" AND CAST(SUBSTRING(L.LGPSD_FECHA_REGISTRO,1,10) AS DATE) <= '"+ parametro.getFechaFin()+ "'");
                sql=sql.append(" AND CAST(SUBSTRING(L.LGPSD_FECHA_REGISTRO,1,10) AS DATE) >= '"+ parametro.getFechaInicio()+ "'");
            }
            sql=sql.append(" ORDER BY fecha ASC,mobileDestino ASC");
            log.debug("sql "+sql.toString());            
            pstmt = context.getConnection().prepareStatement(sql.toString());
           
            rs = pstmt.executeQuery();            
            log.debug("despues del execute");
            while(rs.next()){
                Localizacion localizacion=new Localizacion();
                localizacion.setCodLocalizacion(rs.getInt("codLocalizacion"));                
                localizacion.setMobileDestino(rs.getString("mobileDestino"));                
                localizacion.setFecha(rs.getTimestamp("fecha"));
                localizacion.setLatitud(rs.getString("latitud"));
                localizacion.setLongitud(rs.getString("longitud"));
                
                listaResultado.add(localizacion);
            }
            pstmt.close();
            pstmt = null;
            rs.close();
            rs = null;
            
            }catch(Exception e){                
                log.error(e.getMessage());
                if(pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch(Exception ignore) {
                    }
                }
                if(rs != null) {
                    try {
                        rs.close();
                    }
                    catch(Exception ignore) {
                    }
                }
            throw new DAOException(e);
        }
        return listaResultado;
     }
     
     public List<Localizacion> listLocalizacion(ParametroConsultaWeb parametro)throws DAOException{
         PreparedStatement pstmt = null;
         ResultSet rs = null;
         List<Localizacion> listaResultado = null;
         StringBuffer sql=null;
         try{
             listaResultado=new ArrayList<Localizacion>();
             sql=new StringBuffer("");
             sql=sql.append(" SELECT L.LGPSI_CODIGO as codLocalizacion,");            
             sql=sql.append(" L.LGPSV_TELEFONO_ORIGEN as mobileDestino,");
             sql=sql.append(" L.LGPSD_FECHA_REGISTRO as fecha,");
             sql=sql.append(" L.LGPSV_LATITUD as latitud,");	   
             sql=sql.append(" L.LGPSV_LONGITUD as longitud");	   
             sql=sql.append(" FROM "+strBD_LBS+".LBST_LOCALIZACION_GPS L");            
             sql=sql.append(" WHERE L.LGPSV_TELEFONO_ORIGEN IN (" );
             for(int i=0;i<parametro.getListaMobiles().size();i++){                
                 sql=sql.append(parametro.getListaMobiles().get(i).getNumero()+",");
             }
             sql=sql.deleteCharAt(sql.length()-1);
             sql=sql.append(")"); 
             if(parametro.getFechaFin()!=null && parametro.getFechaInicio()!=null){
                 sql=sql.append(" AND CAST(SUBSTRING(L.LGPSD_FECHA_REGISTRO,1,10) AS DATE) <= '"+parametro.getFechaFin()+ "'");
                 sql=sql.append(" AND CAST(SUBSTRING(L.LGPSD_FECHA_REGISTRO,1,10) AS DATE) >= '"+ parametro.getFechaInicio()+ "'");
             }
             sql=sql.append(" ORDER BY fecha DESC,mobileDestino DESC");
             /*Solo para efectos de la demo*/
             sql=sql.append(" limit 1 ");
             log.debug("Ejecutando sql "+sql.toString());            
             pstmt = context.getConnection().prepareStatement(sql.toString());
            
             rs = pstmt.executeQuery();            
             log.debug("despues del execute");
             while(rs.next()){
                 Localizacion localizacion=new Localizacion();
                 localizacion.setCodLocalizacion(rs.getInt("codLocalizacion"));                
                 localizacion.setMobileDestino(rs.getString("mobileDestino"));                
                 localizacion.setFecha(rs.getTimestamp("fecha"));
                 localizacion.setLatitud(rs.getString("latitud"));
                 localizacion.setLongitud(rs.getString("longitud"));
                 
                 listaResultado.add(localizacion);
             }
             pstmt.close();
             pstmt = null;
             rs.close();
             rs = null;
             
             }catch(Exception e){                
                 log.error(e.getMessage());
                 if(pstmt != null) {
                     try {
                         pstmt.close();
                     }
                     catch(Exception ignore) {
                     }
                 }
                 if(rs != null) {
                     try {
                         rs.close();
                     }
                     catch(Exception ignore) {
                     }
                 }
             throw new DAOException(e);
         }
         return listaResultado;
      }
}
