/*
 * ReporteDAO.java
 *
 * Created on 9 de junio de 2005, 12:51 PM
 */

package com.stconsulting.lbsweb.consulta.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
public class ReporteDAO extends GenericDAO{

	/** Creates a new instance of ReporteDAO */
	public ReporteDAO(TransactionContext context){
		super(context);
		this.context=context;
		log=Logger.getLogger(this.getClass());
	}

	/**REN Probado ----------------------------------------------------------------------*/
	public List<ResultadoDetalleReporteWeb> reporteLocalizacion(String mobileOrigen,ParametroConsultaWeb parametro) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ResultadoDetalleReporteWeb> listaResultado=null;
		StringBuffer sql=null;
		//SimpleDateFormat fechita = new SimpleDateFormat("dd/MM/yyyy");
		try{
			log.debug("DAO antes del sql reporteLocalizacion, mobileOrigen :" + mobileOrigen);

			listaResultado=new ArrayList<ResultadoDetalleReporteWeb>();
			sql=new StringBuffer("");
			sql.append("SELECT c.id_consulta as codLocalizacion, consultante.numero as mobileOrigen, ");
			sql.append("consultado.numero as mobileDestino, c.resultado as mensaje, ");
			sql.append("c.fecha_consulta as fecha, c.tipo as codTipoConsulta, ");
			sql.append("substr(c.resultado, 57) as direccion ");
			sql.append("FROM Consulta c ");
			sql.append("INNER JOIN usuario consultante ON c.consultante=consultante.id_usuario ");
			sql.append("INNER JOIN usuario consultado ON c.consultado=consultado.id_usuario ");
			sql.append("WHERE consultante.numero = ? ");
			
			/*sql=sql.append(" SELECT L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino,");
			sql=sql.append(" L.LOCAV_DIRECCION as mensaje,");
			sql=sql.append(" L.LOCAD_FECHA_REGISTRO as fecha,");
			sql=sql.append(" L.TIPO_CONSULTA as codTipoConsulta,");
			sql=sql.append(" SUBSTRING(L.LOCAV_DIRECCION,56) as direccion");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_LOCALIZACION L");*/
			
			// sql=sql.append(" LEFT JOIN "+strBD_LBS+".LBST_CELDA C ON L.LOCAC_CELL_ID = C.CELDC_CODIGO");
			
			/*sql=sql.append(" WHERE L.LOCAV_TELEFONO_ORIGEN = ?");*/
			
			
			if(parametro.getListaMobiles().size() > 0){
				sql.append("AND consultado.numero IN (");
				
				/*sql=sql.append(" AND L.LOCAV_TELEFONO_DESTINO IN (");*/
				
				
				for(Mobile mobile : parametro.getListaMobiles()){
					sql=sql.append("'"+mobile.getNumero() + "',");
				}
				sql=sql.deleteCharAt(sql.length() - 1);
				sql=sql.append(") ");
			}
			if(parametro.getFechaFin() != null && parametro.getFechaInicio() != null){
				sql=sql.append(" AND c.fecha_consulta BETWEEN ? AND ?");
				//sql=sql.append(" AND CAST(substr(c.fecha_consulta,0,8) AS DATE) >= '" + fechita.format(parametro.getFechaInicio()) + "' ");
				
				/*sql=sql.append(" AND CAST(SUBSTRING(L.LOCAD_FECHA_REGISTRO,1,10) AS DATE) <= '" + parametro.getFechaFin() + "'");
				sql=sql.append(" AND CAST(SUBSTRING(L.LOCAD_FECHA_REGISTRO,1,10) AS DATE) >= '" + parametro.getFechaInicio() + "'");*/
			}
			sql=sql.append(" ORDER BY fecha ASC,mobileDestino ASC");
			log.info("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,mobileOrigen);
			log.debug("Fecha Inicio: "+parametro.getFechaInicio().getTime());
			log.debug("Fecha Fin: "+parametro.getFechaFin().getTime());
			pstmt.setTimestamp(2,new Timestamp(parametro.getFechaInicio().getTime()));
			pstmt.setTimestamp(3,new Timestamp(parametro.getFechaFin().getTime()));
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			while(rs.next()){
				ResultadoDetalleReporteWeb resultado=new ResultadoDetalleReporteWeb();
				resultado.setCodOperacion(rs.getString("codLocalizacion"));
				resultado.setMobileDestino(rs.getString("mobileDestino"));
				resultado.setResultado((rs.getString("direccion") == null || rs.getString("direccion").equals("")) ? rs.getString("mensaje") : rs.getString("direccion"));
				resultado.setFechaRegistro(Helper.formateaFecha(new java.util.Date(rs.getDate("fecha").getTime()),Constants.FORMATO_FECHA_MOSTRAR));
				resultado.setHoraRegistro(Helper.formateaFecha(new java.util.Date(rs.getTimestamp("fecha").getTime()),Constants.FORMATO_HORA_MOSTRAR));
				resultado.setCodTipoConsulta(rs.getString("codTipoConsulta"));
				if(resultado.getCodTipoConsulta() != null && resultado.getCodTipoConsulta().equals(Constants.COD_TIPO_CONSULTA_WEB))
					resultado.setDescTipoConsulta(Constants.DESC_TIPO_CONSULTA_WEB);
				else if(resultado.getCodTipoConsulta() != null && resultado.getCodTipoConsulta().equals(Constants.COD_TIPO_CONSULTA_TAREA))
					resultado.setDescTipoConsulta(Constants.DESC_TIPO_CONSULTA_TAREA);
				resultado.setDireccion(rs.getString("direccion"));

				listaResultado.add(resultado);

				// listaResultado.size();

			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){
			log.error(e.getMessage(),e);
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
}
