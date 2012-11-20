/*
 * ConsultaDAO.java
 *
 * Created on 27 de mayo de 2005, 02:46 PM
 */

package com.stconsulting.lbsweb.consulta.persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.util.*;

import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.seguridad.bean.*;

/**
 * 
 * @author STconsulting
 */
public class ConsultaDAO extends GenericDAO{

	/** Creates a new instance of ConsultaDAO */
	public ConsultaDAO(TransactionContext context){
		super(context);
		this.context=context;
		log=Logger.getLogger(this.getClass());
	}

	/**
	 * 
	 * @param numero
	 *            numero para validar
	 */
	public String validaNumero(String numero,Usuario usuario) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String codResultado=Constants.COD_NUMERO_INVALIDO;
		log.debug("DAO antes del sql");
		StringBuffer sql=new StringBuffer("SELECT u.id_usuario ");
		sql.append("FROM usuario u,area a ");
		sql.append("WHERE u.numero=? AND ");
		sql.append("a.id_area=(SELECT id_area FROM usuario_por_area WHERE id_usuario=u.id_usuario and rownum=1) and a.empresa=?");
		log.debug("sql " + sql.toString());
		try{
			

			/*sql=sql.append("SELECT A.HABII_CODIGO as numero ");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_HABILITADOXAREA A ");
			sql=sql.append(" WHERE A.EMPRI_CODIGO=? ");
			sql=sql.append(" AND A.HABII_CODIGO=? ");*/

			
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,numero);
			pstmt.setInt(2,usuario.getCodEmpresa());			

			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			if(rs.next()){
				codResultado=Constants.COD_NUMERO_VALIDO;
			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){

			log.error(e);
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(Exception ignore){
				}
			}
			if(rs != null){
				try{
					rs.close();
				}
				catch(Exception ignore){
				}
			}
			throw new DAOException(e);
		}
		return codResultado;
	}

	/**
	 * 
	 * @param listaCodigosLocalizacion
	 */
	public List<Localizacion> cargaListaLocalizacion(List<Integer> listaCodigosLocalizacion) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Localizacion> listaResultado=new ArrayList<Localizacion>();
		log.debug("DAO antes del sql");
		StringBuffer sql=new StringBuffer("SELECT l.id_consulta as codLocalizacion,");
		sql.append("consultante.numero as mobileOrigen,");
		sql.append("consultado.numero as mobileDestino,");
		sql.append("l.resultado as mensaje,");
		sql.append("c.id_celda as codCelda,");
		sql.append("c.direccion as direccion,");
		sql.append("c.x as ejeX,");
		sql.append("c.y as ejeY ");
		sql.append("FROM consulta l ");
		sql.append("INNER JOIN usuario consultante ON l.consultante=consultante.id_usuario ");
		sql.append("INNER JOIN usuario consultado ON l.consultado=consultado.id_usuario ");
		sql.append("LEFT OUTER JOIN celda c ON l.id_celda=c.id_celda ");
		sql.append("WHERE l.id_consulta IN(");
		boolean primero=true;
		for(Integer s : listaCodigosLocalizacion){
			if(!primero)
				sql.append(","+s);
			else{
				sql.append(s);
				primero=false;
			}
		}
		sql.append(")");
		log.debug("sql " + sql.toString());
		try{
			
			/*sql=new StringBuffer("");
			sql=sql.append(" SELECT L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino,");
			sql=sql.append(" L.LOCAV_DIRECCION as mensaje,");
			sql=sql.append(" L.LOCAC_CELL_ID as codCelda,");
			sql=sql.append(" L.LLBSI_CODIGO as codLog,");
			sql=sql.append(" C.CELDV_DIRECCION as direccion,");
			sql=sql.append(" C.CELDV_REFERENCIA as referencia,");
			sql=sql.append(" C.CELDV_X as ejeX,");
			sql=sql.append(" C.CELDV_Y as ejeY,");
			sql=sql.append(" C.CELDV_LATITUD as latitud,");
			sql=sql.append(" C.CELDV_LONGITUD as longitud");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_LOCALIZACION L");
			sql=sql.append(" LEFT JOIN " + strBD_LBS + ".LBST_CELDA C ON L.LOCAC_CELL_ID = C.CELDC_CODIGO");
			sql=sql.append(" WHERE L.LOCAI_CODIGO IN (");
			for(String s: listaCodigosLocalizacion){
				sql=sql.append(s+",");
			}
			sql=sql.deleteCharAt(sql.length() - 1);
			sql=sql.append(")");
			// ADDED BY xtian.aguilar.b@gmail.com EL 30/12/2005
			// sql=sql.append(" AND C.CELDV_X is not null");
			// FIN
			*/
			pstmt=context.getConnection().prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			while(rs.next()){
				Localizacion localizacion=new Localizacion();
				localizacion.setCodLocalizacion(rs.getInt("codLocalizacion"));
				localizacion.setMobileOrigen(rs.getString("mobileOrigen"));
				localizacion.setMobileDestino(rs.getString("mobileDestino"));
				localizacion.setMensaje(rs.getString("mensaje"));
				localizacion.setCodCelda(rs.getString("codCelda"));
				//localizacion.setCodLog(rs.getString("codLog"));
				localizacion.setDireccion(rs.getString("direccion"));
				//localizacion.setReferencia(rs.getString("referencia"));
				localizacion.setEjeX(rs.getFloat("ejeX"));
				if(localizacion.getEjeX()==null)
					localizacion.setEjeX(0f);
				localizacion.setEjeY(rs.getFloat("ejeY"));
				if(localizacion.getEjeY()==null)
					localizacion.setEjeY(0f);
				//localizacion.setLatitud(rs.getString("latitud"));
				//localizacion.setLongitud(rs.getString("longitud"));
				listaResultado.add(localizacion);
			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){
			log.error(e.getMessage());
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(Exception ignore){
				}
			}
			if(rs != null){
				try{
					rs.close();
				}
				catch(Exception ignore){
				}
			}
			throw new DAOException(e);
		}
		return listaResultado;
	}

	public List<Localizacion> cargaListaLocalizacionHistorica(String mobileOrigen,ParametroConsultaWeb parametro) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Localizacion> listaResultado=new ArrayList<Localizacion>();		
		log.debug("DAO antes del sql cargaListaLocalizacionHistorica, mobileOrigen :" + mobileOrigen);
		StringBuffer sql=new StringBuffer("SELECT l.id_consulta as codLocalizacion,");
		sql.append("consultante.numero as mobileOrigen,");
		sql.append("consultado.numero as mobileDestino,");
		sql.append("l.resultado as mensaje,");
		sql.append("c.id_celda as codCelda,");
		sql.append("l.fecha_consulta as fecha,");
		sql.append("l.tipo as tipoConsulta,");
		sql.append("c.direccion as direccion,");
		sql.append("c.x as ejeX,");
		sql.append("c.y as ejeY ");
		sql.append("FROM consulta l ");
		sql.append("INNER JOIN usuario consultante ON l.consultante=consultante.id_usuario ");
		sql.append("INNER JOIN usuario consultado ON l.consultado=consultado.id_usuario ");
		sql.append("INNER JOIN celda c ON l.id_celda=c.id_celda ");
		sql.append("WHERE consultante.numero=? AND ");
		sql.append("(l.tipo=? OR l.tipo=?) AND ");
		sql.append("consultado.numero IN(");
		boolean primero=true;
		for(Mobile mobile : parametro.getListaMobiles()){
			if(!primero)
				sql.append(","+mobile.getNumero());
			else{
				sql.append(mobile.getNumero());
				primero=false;
			}
		}
		sql.append(") ");
		if(parametro.getFechaFin() != null && parametro.getFechaInicio() != null){
			sql.append("AND l.fecha_consulta BETWEEN ? AND ? ");
		}
		sql.append("ORDER BY fecha ASC,mobileDestino ASC");
		log.debug("sql " + sql.toString());
		try{
			

			/*sql=sql.append(" SELECT L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino,");
			sql=sql.append(" L.LOCAV_DIRECCION as mensaje,");
			sql=sql.append(" L.LOCAC_CELL_ID as codCelda,");
			sql=sql.append(" L.LLBSI_CODIGO as codLog,");
			sql=sql.append(" L.LOCAD_FECHA_REGISTRO as fecha,");
			sql=sql.append(" L.TIPO_CONSULTA as tipoConsulta,");
			sql=sql.append(" C.CELDV_DIRECCION as direccion,");
			sql=sql.append(" C.CELDV_REFERENCIA as referencia,");
			sql=sql.append(" C.CELDV_X as ejeX,");
			sql=sql.append(" C.CELDV_Y as ejeY,");
			sql=sql.append(" C.CELDV_LATITUD as latitud,");
			sql=sql.append(" C.CELDV_LONGITUD as longitud");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_LOCALIZACION L");
			sql=sql.append(" LEFT JOIN " + strBD_LBS + ".LBST_CELDA C ON L.LOCAC_CELL_ID = C.CELDC_CODIGO");
			sql=sql.append(" WHERE L.LOCAV_TELEFONO_ORIGEN = ?");
			sql=sql.append(" AND (L.TIPO_CONSULTA=? OR L.TIPO_CONSULTA=?)");
			sql=sql.append(" AND L.LOCAV_TELEFONO_DESTINO IN (");
			for(Mobile mobile : parametro.getListaMobiles()){
				sql=sql.append(mobile.getNumero() + ",");
			}
			sql=sql.deleteCharAt(sql.length() - 1);
			sql=sql.append(")");
			if(parametro.getFechaFin() != null && parametro.getFechaInicio() != null){
				sql=sql.append(" AND CAST(SUBSTRING(L.LOCAD_FECHA_REGISTRO,1,10) AS DATE) <= '" + new java.sql.Date(Converter.stringToSqlDate(parametro.getFechaFin()).getTime()) + "'");
				sql=sql.append(" AND CAST(SUBSTRING(L.LOCAD_FECHA_REGISTRO,1,10) AS DATE) >= '" + new java.sql.Date(Converter.stringToSqlDate(parametro.getFechaInicio()).getTime()) + "'");
			}
			sql=sql.append(" ORDER BY fecha ASC,mobileDestino ASC");*/
			
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,mobileOrigen);
			//FIXME soporte para el tipo TAREA
			pstmt.setString(2,Constants.DESC_TIPO_CONSULTA_TAREA);
			pstmt.setString(3,Constants.DESC_TIPO_CONSULTA_WEB);
			if(parametro.getFechaFin() != null && parametro.getFechaInicio() != null){
				pstmt.setDate(4,(Date) parametro.getFechaInicio());
				pstmt.setDate(5,(Date) parametro.getFechaFin());
			}
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			while(rs.next()){
				Localizacion localizacion=new Localizacion();
				localizacion.setCodLocalizacion(rs.getInt("codLocalizacion"));
				localizacion.setMobileOrigen(rs.getString("mobileOrigen"));
				localizacion.setMobileDestino(rs.getString("mobileDestino"));
				localizacion.setMensaje(rs.getString("mensaje"));
				localizacion.setCodCelda(rs.getString("codCelda"));
				//localizacion.setCodLog(rs.getString("codLog"));
				localizacion.setFecha(rs.getDate("fecha"));
				localizacion.setDireccion(rs.getString("direccion"));
				//localizacion.setReferencia(rs.getString("referencia"));
				localizacion.setEjeX(rs.getFloat("ejeX"));
				localizacion.setEjeY(rs.getFloat("ejeY"));
				//localizacion.setLatitud(rs.getString("latitud"));
				//localizacion.setLongitud(rs.getString("longitud"));
				localizacion.setTipoConsulta(rs.getString("tipoConsulta"));

				listaResultado.add(localizacion);
			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){
			log.error(e.getMessage());
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(Exception ignore){
				}
			}
			if(rs != null){
				try{
					rs.close();
				}
				catch(Exception ignore){
				}
			}
			throw new DAOException(e);
		}
		return listaResultado;
	}

	public void insertaLocalizacionXTarea(Integer codLocalizacion,Integer codTarea) throws DAOException{
		PreparedStatement pstmt=null;
		log.debug("DAO antes del sql insertaLocalizacionXTarea ---ID TAREA:" + codTarea);
		StringBuffer sql=new StringBuffer("INSERT INTO consulta_por_tarea (id_consulta,id_tarea) ");
		sql.append("VALUES(?,?)");
		try{
			

			/*sql=sql.append(" INSERT INTO " + strBD_LBS + ".LBST_LOCALIZACIONXTAREA");
			sql=sql.append(" (LOCAI_CODIGO,TAREAI_ID)");
			sql=sql.append(" VALUES(?,?)");*/

			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setInt(1,codLocalizacion);
			pstmt.setInt(2,codTarea);

			int i=pstmt.executeUpdate();
			log.debug("despues del insert," + i + " row(s) inserted");

			pstmt.close();
			pstmt=null;

		}
		catch(Exception e){
			log.error(e);
			if(pstmt != null){
				try{
					pstmt.close();
				}
				catch(Exception ignore){
				}
			}
			throw new DAOException(e);
		}
	}
}
