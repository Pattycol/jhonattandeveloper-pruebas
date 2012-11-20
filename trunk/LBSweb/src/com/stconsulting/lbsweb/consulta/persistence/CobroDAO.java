/*
 * CobroDAO.java
 *
 * Created on 6 de junio de 2005, 11:47 AM
 */

package com.stconsulting.lbsweb.consulta.persistence;

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

/**
 * 
 * @author STCosulting
 */
public class CobroDAO extends GenericDAO{
	private static String strBD_LBS;

	/** Creates a new instance of CobroDAO */
	public CobroDAO(TransactionContext context){
		super(context);
		this.context=context;
		strBD_LBS="LBS";
		log=Logger.getLogger(this.getClass());
	}

	// Generando Consecutivos de los Cdrs
	public int[] getNextCdrPK() throws DAOException{
		int[] numConsecutivo=new int[2];
		//long resultaux=1;
		Integer CodigoCobro=new Integer(0);
		Integer CodigoCdr=new Integer(0);
		String sql="SELECT MAX(XMLI),MAX(COBROI_ID) FROM " + strBD_LBS + ".LBST_COBRO_CONSECUTIVO FOR UPDATE";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=context.getConnection().prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				CodigoCdr=new Integer(rs.getInt(1));
				CodigoCobro=new Integer(rs.getInt(2));
				// Cobro Generado
				if(CodigoCobro.intValue() == Constants.NUM_CONSECUTIVO_MAX_CDR){
					CodigoCobro=new Integer(1);
				}
				else{
					CodigoCobro=new Integer(CodigoCobro.intValue() + 1);
				}
				// Xml Generado
				if(CodigoCdr.intValue() == Constants.NUM_CONSECUTIVO_MAX_CDR){
					CodigoCdr=new Integer(1);
				}
				else{
					CodigoCdr=new Integer(CodigoCdr.intValue() + 1);
				}

			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;
			numConsecutivo[0]=CodigoCdr.intValue();
			numConsecutivo[1]=CodigoCobro.intValue();
			log.debug("PK generated Cdr: " + CodigoCobro);
			log.debug("PK generated Xml: " + CodigoCdr);
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
		return numConsecutivo;
	}

	public void insertaCobro(Integer codLocalizacion,String codEstado) throws DAOException{
		PreparedStatement pstmt=null;

		StringBuffer sql=null;
		try{
			log.debug("DAO antes del sql insertaCobros");

			sql=new StringBuffer("");
			sql=sql.append(" INSERT INTO " + strBD_LBS + ".LBST_COBRO");
			sql=sql.append(" (LOCAI_CODIGO,COBROD_FECHA_REGISTRO,COBROC_ESTADO)");
			sql=sql.append(" VALUES(?,?,?)");

			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setInt(1,codLocalizacion);
			pstmt.setTimestamp(2,new java.sql.Timestamp(System.currentTimeMillis()));
			pstmt.setString(3,codEstado);
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

	/**
	 * retorna la lista de cobros en estado Pendiente
	 */
	public List<Cobro> cargaListaCobroPorEstado(String codEstado) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Cobro> listaResultado=null;
		StringBuffer sql=null;
		try{
			log.debug("DAO antes del sql");

			listaResultado=new ArrayList<Cobro>();
			sql=new StringBuffer("");
			sql=sql.append(" SELECT C.COBROI_ID as codCobro,");
			sql=sql.append(" C.COBROC_ESTADO as codEstado,");
			sql=sql.append(" C.COBROD_FECHA_REGISTRO as fechaRegistro,");
			sql=sql.append(" L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_COBRO C," + strBD_LBS + ".LBST_LOCALIZACION L");
			sql=sql.append(" WHERE C.LOCAI_CODIGO = L.LOCAI_CODIGO");
			sql=sql.append(" AND C.COBROC_ESTADO = ?");
			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,codEstado);
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			while(rs.next()){
				Localizacion localizacion=new Localizacion();
				Cobro cobro=new Cobro();
				cobro.setCodCobro(rs.getInt("codCobro"));
				cobro.setCodEstado(rs.getString("codEstado"));
				// cobro.setFechaRegistro(rs.getString("fechaRegistro"));
				cobro.setFechaRegistro(Helper.formateaFecha(new java.util.Date(rs.getTimestamp("fechaRegistro").getTime()),Constants.FORMATO_FECHA_HORA_24));
				localizacion.setCodLocalizacion(rs.getInt("codLocalizacion"));
				localizacion.setMobileOrigen(rs.getString("mobileOrigen"));
				localizacion.setMobileDestino(rs.getString("mobileDestino"));
				cobro.setLocalizacion(localizacion);
				listaResultado.add(cobro);
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
		return listaResultado;
	}

	public void actualizaEstadoCobro(List<Cobro> listaCobro,String codEstado) throws DAOException{
		PreparedStatement pstmt=null;
		//ResultSet rs=null;

		StringBuffer sql=null;
		try{
			log.debug("DAO actualizaEstadoCobro antes del sql");

			sql=new StringBuffer("");
			sql=sql.append(" UPDATE " + strBD_LBS + ".LBST_COBRO");
			sql=sql.append(" SET COBROC_ESTADO = ?, COBROD_FECHA_COBRO = ?");
			sql=sql.append(" WHERE COBROI_ID IN(");
			for(Cobro cobro : listaCobro){
				sql=sql.append(cobro.getCodCobro() + ",");
			}
			sql=sql.deleteCharAt(sql.length() - 1);
			sql=sql.append(")");
			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,codEstado);
			pstmt.setTimestamp(2,new java.sql.Timestamp(System.currentTimeMillis()));

			int i=pstmt.executeUpdate();
			log.debug("despues del UPDATE," + i + " row(s) updated");

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

	public void actualizaConsecutivoCdr(int idXml,int idCobro) throws DAOException{
		PreparedStatement pstmt=null;
		//ResultSet rs=null;

		StringBuffer sql=null;
		try{
			log.debug("DAO actualizaConsecutivoCdr antes del sql");
			log.debug("Update : idXml " + idXml + ",idCobro " + idCobro);
			sql=new StringBuffer("");
			sql=sql.append(" UPDATE " + strBD_LBS + ".LBST_COBRO_CONSECUTIVO");
			sql=sql.append(" SET XMLI = ?, COBROI_ID = ?");
			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setInt(1,idXml);
			pstmt.setInt(2,idCobro);

			int i=pstmt.executeUpdate();
			log.debug("despues del UPDATE," + i + " row(s) updated");

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
