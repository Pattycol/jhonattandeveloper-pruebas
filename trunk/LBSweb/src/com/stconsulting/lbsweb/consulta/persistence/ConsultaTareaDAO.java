/*
 * ConsultaDAO.java
 *
 * Created on 27 de mayo de 2005, 02:46 PM
 */

package com.stconsulting.lbsweb.consulta.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.persistence.TransactionSysException;
import com.stconsulting.lbsweb.util.*;
import com.stconsulting.common.util.*;

import com.stconsulting.lbsweb.consulta.bean.*;

/**
 * 
 * @author STconsulting
 */
public class ConsultaTareaDAO extends GenericDAO{

	private static String strBD_LBS;

	/**
	 * Creates a new instance of ConsultaTareaDAO
	 * 
	 * @param context
	 * @throws TransactionSysException
	 */
	public ConsultaTareaDAO(TransactionContext context) throws TransactionSysException{
		super(context);
		this.context=context;
		strBD_LBS="LBS";
		log=Logger.getLogger(this.getClass());
	}

	public Integer getNextPK() throws DAOException{
		// long resultaux=1;
		Integer Codigo=new Integer(0);
		String sql="SELECT TAREA_SEQ.nextval FROM dual";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			pstmt=context.getConnection().prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				Codigo=new Integer(rs.getInt(1));
				/*
				 * if(Codigo == null) Codigo=new Integer(0); else Codigo=new
				 * Integer(Codigo.intValue() + 1);
				 */
			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;
			log.debug("PK generated: " + Codigo);
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
		return Codigo.intValue();
	}

	/**
	 * Inserta una nueva Tarea
	 * 
	 * @param tarea
	 * @throws DAOException
	 */
	public void insert(Tarea tarea,String mobile) throws DAOException{
		log.debug("Ingrese al insert del DAO TareaDAO - insert");
		int idTarea=getNextPK();
		tarea.setIdTarea(idTarea);
		tarea.setDescripcion(Integer.toString(idTarea));

		StringBuffer sql=new StringBuffer("");
		sql.append("INSERT INTO tarea (");
		sql.append("id_tarea, consultante, consultado,fecha_inicio, fecha_fin, intervalo_dias, intervalo_minutos, estado, fecha_creacion, hora_inicio, hora_fin) VALUES ");
		sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		/*
		 * sql.append("INSERT INTO " + strBD_LBS + ".LBST_TAREA ("); sql.append(
		 * "TAREAI_ID,TAREAI_MOBILE_ORIGEN,TAREAI_EMPRI_CODIGO,TAREAV_DESCRIPCION,TAREAC_COD_FORMATO,TAREV_NOM_FORMATO,TAREAD_FECHA_INICIO,TAREAD_FECHA_FIN,"
		 * ); sql.append(
		 * "TAREAC_PERIODO,TAREAV_NOM_PERIODO,TAREAV_DIA_PERIODO,TAREAI_CANTIDAD_PERIODO,TAREAC_COD_HORARIO,"
		 * ); sql.append(
		 * "TAREAV_NOM_HORARIO,TAREI_HORA_INICIO,TAREI_HORA_FIN,TAREAI_INTERVALO,TAREAC_ESTADO,TAREI_CREATED_DATE) "
		 * ); sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 */

		PreparedStatement pstmt=null;
		try{
			Date fecha=null;
			log.debug("Sql :" + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());

			pstmt.setString(1,String.valueOf(idTarea));
			pstmt.setInt(2,buscarClienteXMobil(tarea.getMobileOrigen()));
			pstmt.setInt(3,buscarClienteXMobil(mobile));

			fecha=tarea.getFechaInicio();
			fecha.setHours(tarea.getHoraInicio());
			pstmt.setDate(4,new java.sql.Date(fecha.getTime()));
			fecha=tarea.getFechaFin();
			fecha.setHours(tarea.getHoraFin());
			pstmt.setDate(5,new java.sql.Date(fecha.getTime()));
			pstmt.setString(6,tarea.getIntervaloDias());
			pstmt.setInt(7,tarea.getIntervalo());
			pstmt.setString(8,tarea.getCodEstado());
			pstmt.setTimestamp(9,new java.sql.Timestamp(new Date().getTime()));
			fecha=new Date();
			fecha.setHours(tarea.getHoraInicio());
			fecha.setMinutes(0);
			fecha.setSeconds(0);
			pstmt.setTimestamp(10,new java.sql.Timestamp(fecha.getTime()));
			fecha=new Date();
			fecha.setHours(tarea.getHoraFin());
			fecha.setMinutes(0);
			fecha.setSeconds(0);
			pstmt.setTimestamp(11,new java.sql.Timestamp(fecha.getTime()));

			/*
			 * pstmt.setString(1,String.valueOf(idTarea));
			 * pstmt.setString(2,tarea.getMobileOrigen());
			 * pstmt.setInt(3,tarea.getCodEmpresa());
			 * pstmt.setString(4,tarea.getDescripcion());
			 * pstmt.setString(5,tarea.getCodFormato());
			 * pstmt.setString(6,tarea.getDescFormato()); pstmt.setDate(7,new
			 * java
			 * .sql.Date(Converter.stringToSqlDate(tarea.getFechaInicio()).getTime
			 * ())); pstmt.setDate(8,new
			 * java.sql.Date(Converter.stringToSqlDate(
			 * tarea.getFechaFin()).getTime()));
			 * pstmt.setString(9,tarea.getCodPeriodo());
			 * pstmt.setString(10,tarea.getDescPeriodo());
			 * pstmt.setString(11,tarea.getDiaPeriodo());
			 * pstmt.setInt(12,tarea.getCantPeriodo());
			 * pstmt.setString(13,tarea.getCodHorario());
			 * pstmt.setString(14,tarea.getDescHorario());
			 * pstmt.setInt(15,tarea.getHoraInicio());
			 * pstmt.setInt(16,tarea.getHoraFin());
			 * pstmt.setInt(17,tarea.getIntervalo());
			 * pstmt.setString(18,tarea.getCodEstado());
			 * pstmt.setTimestamp(19,new java.sql.Timestamp(new
			 * Date().getTime()));
			 */
			int result=pstmt.executeUpdate();
			log.debug(result + " row(s) inserted");
			pstmt.close();
			pstmt=null;
		}
		catch(Exception e){
			e.printStackTrace();
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
		log.debug("Sali del Tarea DAO - insert");
	}

	private Integer buscarClienteXMobil(String mobil) throws SQLException,TransactionSysException{
		String sql="SELECT id_usuario FROM usuario WHERE usuario.numero = ?";
		PreparedStatement pstmt=context.getConnection().prepareStatement(sql);
		pstmt.setString(1,mobil);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return new Integer(rs.getInt(1));
		}
		return null;
	}

	/**
	 * REN Ya no es usado
	 * ---------------------------------------------------------
	 */
	public void insertMobilxTask(Integer idTarea,String mobile) throws DAOException{
		log.debug("Ingrese al insert insertMobilxTask del DAO TareaDAO");
		StringBuffer sql=new StringBuffer("");
		sql.append("INSERT INTO " + strBD_LBS + ".LBST_TAREAXMOBIL (");
		sql.append("TAREAI_ID,MOBILE) ");
		sql.append("VALUES (?,?)");
		PreparedStatement pstmt=null;
		try{
			log.debug("Sql :" + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setInt(1,idTarea);
			pstmt.setString(2,mobile);
			int result=pstmt.executeUpdate();
			log.debug(result + " row(s) inserted");
			pstmt.close();
			pstmt=null;
		}
		catch(Exception e){
			e.printStackTrace();
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
		log.debug("Sali del Tarea DAO - insertMobilxTask");
	}

	/**
	 * Actualizar el estado de la Tarea
	 * 
	 * @param tarea
	 * @throws DAOException
	 */
	public void update(Tarea tarea) throws DAOException{
		log.debug("Ingrese al update del DAO TareaDAO");
		StringBuffer sql=new StringBuffer("");

		sql.append("UPDATE Tarea ");
		sql.append("SET estado=? WHERE id_tarea=?");

		/*
		 * sql.append(" UPDATE " + strBD_LBS + ".LBST_TAREA ");
		 * sql.append(" SET TAREAC_ESTADO=? WHERE TAREAI_ID=?");
		 */

		PreparedStatement pstmt=null;
		try{
			log.debug("Sql :" + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,tarea.getCodEstado());
			pstmt.setInt(2,tarea.getIdTarea());
			int result=pstmt.executeUpdate();
			log.debug(result + " row(s) updated");
			pstmt.close();
			pstmt=null;
		}
		catch(Exception e){
			e.printStackTrace();
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
		log.debug("Sali del Tarea DAO - update");
	}

	/**
	 * 
	 * @param filtroTarea
	 * @throws DAOException
	 * @return
	 */
	public List<Tarea> listTareasCancelar(Tarea filtroTarea) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Tarea> listaTareas=new ArrayList<Tarea>();
		StringBuffer sql=null;
		try{
			log.debug("listTareasCancelar DAO antes del sql");
			sql=new StringBuffer("");
			sql=sql.append(" SELECT DISTINCT T.TAREAI_ID as idTarea,");
			sql=sql.append(" T.TAREAC_ESTADO as codEstado");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_TAREA T");
			sql=sql.append(" WHERE T.TAREAC_ESTADO IN ('" + Constants.COD_ESTADO_ACTIVO + "','" + Constants.COD_ESTADO_INACTIVO + "')");
			if(filtroTarea != null){
				if(!filtroTarea.getFechaInicio().equals("") && !filtroTarea.getFechaFin().equals("")){
					sql=sql.append(" AND CAST(SUBSTRING(T.TAREAD_FECHA_INICIO,1,10) AS DATE) < '" + filtroTarea.getFechaFin().getTime() + "'");
					sql=sql.append(" AND CAST(SUBSTRING(T.TAREAD_FECHA_FIN,1,10) AS DATE) < '" + filtroTarea.getFechaInicio().getTime() + "'");
				}
			}
			sql=sql.append(" ORDER BY T.TAREAI_ID,T.TAREAD_FECHA_INICIO,T.TAREAD_FECHA_FIN FOR UPDATE");
			pstmt=context.getConnection().prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			Tarea tarea=null;
			while(rs.next()){
				tarea=new Tarea();
				tarea.setIdTarea(rs.getInt("idTarea"));
				tarea.setCodEstado(rs.getString("codEstado"));
				listaTareas.add(tarea);
			}
			log.debug("Lista de Tareas Cancelar :" + listaTareas.size());
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){
			log.error(e);
			log.debug("Error dao tarea Cancelar " + e);
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
		return listaTareas;
	}

	public List<Tarea> listTareas2(Tarea filtroTarea) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Tarea> listaTareas=new ArrayList<Tarea>();
		StringBuffer sql=null;
		try{
			log.debug("listTareas DAO antes del sql");
			sql=new StringBuffer("");

			sql.append("SELECT DISTINCT t.id_tarea as idTarea, consultante.numero as mobileOrigen, ");
			sql.append("a.empresa as codEmpresa, t.fecha_inicio as fechaInicio, t.fecha_fin as fechaFin, ");
			sql.append("t.estado as codEstado, t.fecha_creacion as createdDate, consultado.numero as mobile, ");
			sql.append("t.hora_inicio as horaInicio, t.hora_fin as horaFin, intervalo_dias as intervaloDias, intervalo_minutos as intervalo ");
			sql.append("FROM Tarea t ");
			sql.append("INNER JOIN usuario consultante ON t.consultante=consultante.id_usuario ");
			sql.append("INNER JOIN usuario consultado ON t.consultado=consultado.id_usuario ");
			sql.append(", Area a ");
			sql.append("WHERE a.id_area = (SELECT id_area FROM usuario_por_area WHERE id_usuario = consultante.id_usuario and rownum =1) ");
			

			if(filtroTarea != null){
				if(filtroTarea.getMobileOrigen() != null && !filtroTarea.getMobileOrigen().equals("")){
					sql.append(" AND consultante.numero LIKE '" + filtroTarea.getMobileOrigen() + "'");
				}
				
				if(filtroTarea.getMobile() != null && !filtroTarea.getMobile().equals("")){
					sql.append(" AND consultado.numero LIKE '" + filtroTarea.getMobile() + "'");
				}
				
				if(filtroTarea.getFechaInicio() != null && filtroTarea.getFechaFin() != null){
					sql.append(" AND ( ( trunc(t.fecha_inicio) >= trunc(?) ");
					sql.append(" AND trunc(t.fecha_fin) <= trunc(?) )  ");

					
//					sql.append("OR ( AND trunc(t.fecha_inicio) >= trunc(?) ");
//					sql.append("     AND trunc(t.fecha_fin) >= trunc(?) ) ");
		
					sql.append(" OR ( trunc(?) BETWEEN trunc(t.fecha_inicio) AND trunc(t.fecha_fin)");
					sql.append("   OR trunc(?) BETWEEN trunc(t.fecha_inicio) AND trunc(t.fecha_fin) ) )");
//					
//					sql.append(" AND trunc(t.fecha_inicio) <= trunc(?) ");
//					sql.append(" AND trunc(t.fecha_fin) <= trunc(?) ");
					
//					sql.append(" ( trunc(t.fecha_fin) = trunc(?) ) ) ");
					
					
				}
				
				if(filtroTarea.getCodEstado() != null && !filtroTarea.getCodEstado().equals(Constants.COD_TODOS)){
					sql.append(" AND t.estado LIKE '" + filtroTarea.getCodEstado() + "'");
				}
			}
			
			sql.append(" ORDER BY t.fecha_inicio desc, t.fecha_fin desc");


			log.debug("sql " + sql.toString());
			System.out.println("sql :"+ sql.toString());
			
			pstmt=context.getConnection().prepareStatement(sql.toString());

			if(filtroTarea.getFechaInicio() != null && filtroTarea.getFechaFin() != null){
				pstmt.setTimestamp(1,new java.sql.Timestamp(filtroTarea.getFechaInicio().getTime()));
				pstmt.setTimestamp(2,new java.sql.Timestamp(filtroTarea.getFechaFin().getTime()));
				
				pstmt.setTimestamp(3,new java.sql.Timestamp(filtroTarea.getFechaInicio().getTime()));
				pstmt.setTimestamp(4,new java.sql.Timestamp(filtroTarea.getFechaFin().getTime()));
//				
//				pstmt.setTimestamp(5,new java.sql.Timestamp(filtroTarea.getFechaInicio().getTime()));
//				pstmt.setTimestamp(6,new java.sql.Timestamp(filtroTarea.getFechaFin().getTime()));
//				
//				pstmt.setTimestamp(7,new java.sql.Timestamp(filtroTarea.getFechaInicio().getTime()));
//				
			}

			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			int ind=0;
			List<Mobile> listaMobiles=null;
			String comparatortask="";
			String idTarea="";
			Tarea tarea=null;
			while(rs.next()){
				idTarea=rs.getString("idTarea");
				if(!comparatortask.equals(idTarea)){
					listaMobiles=new ArrayList<Mobile>();
					comparatortask=idTarea;
					tarea=new Tarea();
					tarea.setIdTarea(rs.getInt("idTarea"));
					tarea.setMobileOrigen(rs.getString("mobileOrigen"));
					tarea.setCodEmpresa(rs.getInt("codEmpresa"));
					/*
					 * tarea.setDescripcion(rs.getString("descTarea"));
					 * tarea.setCodFormato(rs.getString("codFormato"));
					 * tarea.setDescFormato(rs.getString("nomFormato"));
					 */
					tarea.setFechaInicio(new Date(rs.getDate("fechaInicio").getTime()));
					tarea.setFechaFin(new Date(rs.getDate("fechaFin").getTime()));
					/*
					 * tarea.setCodPeriodo(rs.getString("codPeriodo"));
					 * tarea.setDescPeriodo(rs.getString("descPeriodo"));
					 * tarea.setCodHorario(rs.getString("codHorario"));
					 * tarea.setDescHorario(rs.getString("nomHorario"));
					 */		
					
					tarea.setHoraInicio(rs.getTimestamp("horaInicio").getHours());
					tarea.setHoraFin(rs.getTimestamp("horaFin").getHours());
					/*
					 * tarea.setHoraInicio(rs.getInt("horaInicio"));
					 * tarea.setHoraFin(rs.getInt("horaFin"));
					 * /*tarea.setDiaPeriodo(rs.getString("diaPeriodo"));
					 * tarea.setCantPeriodo(rs.getInt("cantPeriodo"));
					 */
					tarea.setIntervalo(rs.getInt("intervalo"));
					tarea.setCodEstado(rs.getString("codEstado"));
					java.sql.Date sqld=rs.getDate("createdDate");
					java.util.Date utild=new java.util.Date();
					utild.setTime(sqld.getTime());
					tarea.setCreatedDate(utild);
					tarea.setInd(Integer.toString(ind));
					tarea.setDescEstado(Helper.getDescripcion(tarea.getCodEstado(),Util.getListaEstadosTarea(Constants.COD_TODOS)));
					tarea.setListaMobiles(listaMobiles);
					tarea.getListaMobiles().add(new Mobile(rs.getString("mobile")));
					log.debug("Cargando Mobil " + rs.getString("mobile") + " a la Tarea :" + tarea.getDescripcion());
					tarea.setIntervaloDias(rs.getString("intervaloDias"));
					listaTareas.add(tarea);
					ind++;
				}
				else{
					log.debug("Cargando Mobil " + rs.getString("mobile") + " a la Tarea :" + tarea.getDescripcion());
					listaTareas.get(Integer.parseInt(tarea.getInd())).getListaMobiles().add(new Mobile(rs.getString("mobile")));
				}
			}
			log.debug("Lista de Tareas :" + listaTareas.size());
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){
			log.error(e.getMessage(),e);
			log.debug("Error dao tarea " + e);
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
		return listaTareas;
	}
	
	/**
	 * Lista las tareas que se van a cancelar porque se encuentran fuera del
	 * rango de configuracion
	 * 
	 * @param filtroTarea
	 * @throws DAOException
	 * @return
	 */
	public List<Tarea> listTareas(Tarea filtroTarea) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Tarea> listaTareas=new ArrayList<Tarea>();
		StringBuffer sql=null;
		try{
			log.debug("listTareas DAO antes del sql");
			sql=new StringBuffer("");

			sql.append("SELECT DISTINCT t.id_tarea as idTarea, consultante.numero as mobileOrigen, ");
			sql.append("a.empresa as codEmpresa, t.fecha_inicio as fechaInicio, t.fecha_fin as fechaFin, ");
			sql.append("t.estado as codEstado, t.fecha_creacion as createdDate, consultado.numero as mobile, ");
			sql.append("t.hora_inicio as horaInicio, t.hora_fin as horaFin, intervalo_dias as intervaloDias, intervalo_minutos as intervalo ");
			sql.append("FROM Tarea t ");
			sql.append("INNER JOIN usuario consultante ON t.consultante=consultante.id_usuario ");
			sql.append("INNER JOIN usuario consultado ON t.consultado=consultado.id_usuario ");
			sql.append(", Area a ");
			sql.append("WHERE a.id_area = (SELECT id_area FROM usuario_por_area WHERE id_usuario = consultante.id_usuario and rownum =1) ");

			/*
			 * sql=sql.append(" SELECT DISTINCT T.TAREAI_ID as idTarea,");
			 * sql=sql.append(" T.TAREAI_MOBILE_ORIGEN as mobileOrigen,");
			 * sql=sql.append(" T.TAREAI_EMPRI_CODIGO as codEmpresa,");
			 * 
			 * /////EXCLUIDOS////////////////////////////////////////
			 * sql=sql.append(" T.TAREAV_DESCRIPCION as descTarea,");
			 * sql=sql.append(" T.TAREAC_COD_FORMATO as codFormato,");
			 * sql=sql.append(" T.TAREV_NOM_FORMATO as nomFormato,");
			 * //////////////////////////////////////////////////////
			 * 
			 * sql=sql.append(" T.TAREAD_FECHA_INICIO as fechaInicio,");
			 * sql=sql.append(" T.TAREAD_FECHA_FIN as fechaFin,");
			 * 
			 * /////EXCLUIDOS////////////////////////////////////////
			 * sql=sql.append(" T.TAREAC_PERIODO as codPeriodo,");
			 * sql=sql.append(" T.TAREAV_NOM_PERIODO as descPeriodo,");
			 * sql=sql.append(" T.TAREAV_DIA_PERIODO as diaPeriodo,");
			 * sql=sql.append(" T.TAREAI_CANTIDAD_PERIODO as cantPeriodo,");
			 * sql=sql.append(" T.TAREAC_COD_HORARIO as codHorario,");
			 * sql=sql.append(" T.TAREAV_NOM_HORARIO as nomHorario,");
			 * sql=sql.append(" T.TAREI_HORA_INICIO as horaInicio,");
			 * sql=sql.append(" T.TAREI_HORA_FIN as horaFin,");
			 * sql=sql.append(" T.TAREAI_INTERVALO as intervalo,");
			 * //////////////////////////////////////////////////////
			 * 
			 * sql=sql.append(" T.TAREAC_ESTADO as codEstado,");
			 * sql=sql.append(" T.TAREI_CREATED_DATE as createdDate,");
			 * sql=sql.append(" TM.MOBILE as mobile");
			 * 
			 * sql=sql.append(" FROM " + strBD_LBS + ".LBST_TAREA T");
			 * sql=sql.append(" LEFT JOIN " + strBD_LBS +
			 * ".LBST_TAREAXMOBIL TM ON T.TAREAI_ID = TM.TAREAI_ID");
			 * sql=sql.append(" WHERE T.TAREAC_ESTADO IS NOT NULL ");
			 */

			if(filtroTarea != null){
				if(filtroTarea.getMobileOrigen() != null && !filtroTarea.getMobileOrigen().equals("")){
					sql.append(" AND consultante.numero LIKE '" + filtroTarea.getMobileOrigen() + "'");

					/*
					 * sql=sql.append(" AND T.TAREAI_MOBILE_ORIGEN LIKE '" +
					 * filtroTarea.getMobileOrigen() + "'");
					 */
				}
				if(filtroTarea.getMobile() != null && !filtroTarea.getMobile().equals("")){
					sql.append(" AND consultado.numero LIKE '" + filtroTarea.getMobile() + "'");

					/*
					 * sql=sql.append(" AND TM.MOBILE LIKE '" +
					 * filtroTarea.getMobile() + "'");
					 */
				}
				if(filtroTarea.getFechaInicio() != null && filtroTarea.getFechaFin() != null){
					sql.append(" AND trunc(t.fecha_inicio) <= trunc(?)");
					sql.append(" AND trunc(t.fecha_fin) >= trunc(?)");

					/*
					 * sql=sql.append(
					 * " AND CAST(SUBSTRING(T.TAREAD_FECHA_INICIO,1,10) AS DATE) <= '"
					 * + new
					 * java.sql.Date(Converter.stringToSqlDate(filtroTarea.
					 * getFechaFin()).getTime()) + "'"); sql=sql.append(
					 * " AND CAST(SUBSTRING(T.TAREAD_FECHA_FIN,1,10) AS DATE) >= '"
					 * + new
					 * java.sql.Date(Converter.stringToSqlDate(filtroTarea.
					 * getFechaInicio()).getTime()) + "'");
					 */
				}
				if(filtroTarea.getCodEstado() != null && !filtroTarea.getCodEstado().equals(Constants.COD_TODOS)){
					sql.append(" AND t.estado LIKE '" + filtroTarea.getCodEstado() + "'");

					/*
					 * sql=sql.append(" AND T.TAREAC_ESTADO LIKE '" +
					 * filtroTarea.getCodEstado() + "'");
					 */
				}
			}
			sql.append(" AND trunc(systimestamp) BETWEEN trunc(t.fecha_inicio) AND trunc(t.fecha_fin)");
			sql.append(" ORDER BY t.fecha_inicio, t.id_tarea, t.fecha_fin ");

			/*
			 * sql=sql.append(
			 * " ORDER BY T.TAREAD_FECHA_INICIO,T.TAREAI_ID,T.TAREAD_FECHA_FIN "
			 * );
			 */

			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());

			if(filtroTarea.getFechaInicio() != null && filtroTarea.getFechaFin() != null){
				pstmt.setTimestamp(1,new java.sql.Timestamp(filtroTarea.getFechaFin().getTime()));
				pstmt.setTimestamp(2,new java.sql.Timestamp(filtroTarea.getFechaInicio().getTime()));
			}

			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			int ind=0;
			List<Mobile> listaMobiles=null;
			String comparatortask="";
			String idTarea="";
			Tarea tarea=null;
			while(rs.next()){
				idTarea=rs.getString("idTarea");
				if(!comparatortask.equals(idTarea)){
					listaMobiles=new ArrayList<Mobile>();
					comparatortask=idTarea;
					tarea=new Tarea();
					tarea.setIdTarea(rs.getInt("idTarea"));
					tarea.setMobileOrigen(rs.getString("mobileOrigen"));
					tarea.setCodEmpresa(rs.getInt("codEmpresa"));
					/*
					 * tarea.setDescripcion(rs.getString("descTarea"));
					 * tarea.setCodFormato(rs.getString("codFormato"));
					 * tarea.setDescFormato(rs.getString("nomFormato"));
					 */
					tarea.setFechaInicio(new Date(rs.getDate("fechaInicio").getTime()));
					tarea.setFechaFin(new Date(rs.getDate("fechaFin").getTime()));
					/*
					 * tarea.setCodPeriodo(rs.getString("codPeriodo"));
					 * tarea.setDescPeriodo(rs.getString("descPeriodo"));
					 * tarea.setCodHorario(rs.getString("codHorario"));
					 * tarea.setDescHorario(rs.getString("nomHorario"));
					 */		
					
					tarea.setHoraInicio(rs.getTimestamp("horaInicio").getHours());
					tarea.setHoraFin(rs.getTimestamp("horaFin").getHours());
					/*
					 * tarea.setHoraInicio(rs.getInt("horaInicio"));
					 * tarea.setHoraFin(rs.getInt("horaFin"));
					 * /*tarea.setDiaPeriodo(rs.getString("diaPeriodo"));
					 * tarea.setCantPeriodo(rs.getInt("cantPeriodo"));
					 */
					tarea.setIntervalo(rs.getInt("intervalo"));
					tarea.setCodEstado(rs.getString("codEstado"));
					java.sql.Date sqld=rs.getDate("createdDate");
					java.util.Date utild=new java.util.Date();
					utild.setTime(sqld.getTime());
					tarea.setCreatedDate(utild);
					tarea.setInd(Integer.toString(ind));
					tarea.setDescEstado(Helper.getDescripcion(tarea.getCodEstado(),Util.getListaEstadosTarea(Constants.COD_TODOS)));
					tarea.setListaMobiles(listaMobiles);
					tarea.getListaMobiles().add(new Mobile(rs.getString("mobile")));
					log.debug("Cargando Mobil " + rs.getString("mobile") + " a la Tarea :" + tarea.getDescripcion());
					tarea.setIntervaloDias(rs.getString("intervaloDias"));
					listaTareas.add(tarea);
					ind++;
				}
				else{
					log.debug("Cargando Mobil " + rs.getString("mobile") + " a la Tarea :" + tarea.getDescripcion());
					listaTareas.get(Integer.parseInt(tarea.getInd())).getListaMobiles().add(new Mobile(rs.getString("mobile")));
				}
			}
			log.debug("Lista de Tareas :" + listaTareas.size());
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;

		}
		catch(Exception e){
			log.error(e.getMessage(),e);
			log.debug("Error dao tarea " + e);
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
		return listaTareas;
	}

	// Validaciones

	/**
	 * Valida si una nuevo numero puede pertenecer a una nueva tarea,siempre y
	 * cuando no se encuentre en estado activo o inactivo en la configuracion de
	 * otra tarea.
	 * 
	 * @param mobile
	 * @throws DAOException
	 * @return
	 */
	public boolean isValidTask(String mobile,Date fechaInicio,Date fechaFin,int horaInicio,int horaFin) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean isValid=true;
		StringBuffer sql=null;
		try{
			log.debug("isValidTask DAO antes del sql");
			sql=new StringBuffer("");

			sql.append("SELECT DISTINCT t.id_tarea as idTarea, consultado.numero as mobile , ");
			sql.append("t.estado as codEstado ");
			sql.append("FROM Tarea t ");
			sql.append("INNER JOIN usuario consultado ON t.consultado=consultado.id_usuario ");
			sql.append("WHERE ((t.fecha_inicio <= ? AND t.fecha_fin >= ?) ");
			sql.append("OR (t.fecha_inicio <= ? AND t.fecha_fin >= ?)) ");
			sql.append("AND ((extract(HOUR from t.hora_inicio)<? AND extract(HOUR from t.hora_fin)>?) ");
			sql.append("OR (extract(HOUR from t.hora_inicio)<? AND extract(HOUR from t.hora_fin)>?)) ");

			log.debug(sql);

			/*
			 * sql=sql.append(" SELECT DISTINCT T.TAREAI_ID as idTarea,");
			 * sql=sql
			 * .append(" TM.MOBILE as mobile,T.TAREAC_ESTADO as codEstado");
			 * sql=sql.append(" FROM " + strBD_LBS + ".LBST_TAREA T");
			 * sql=sql.append(" LEFT JOIN " + strBD_LBS +
			 * ".LBST_TAREAXMOBIL TM ON T.TAREAI_ID = TM.TAREAI_ID");
			 * sql=sql.append
			 * (" WHERE ((CAST(SUBSTRING(T.TAREAD_FECHA_INICIO,1,10) AS DATE) <= '"
			 * + datefechaInicio + "'"); sql=sql.append(
			 * " AND CAST(SUBSTRING(T.TAREAD_FECHA_FIN,1,10) AS DATE) >= '" +
			 * datefechaInicio + "')"); sql=sql.append(
			 * " OR (CAST(SUBSTRING(T.TAREAD_FECHA_INICIO,1,10) AS DATE) <= '" +
			 * datefechaFin + "'"); sql=sql.append(
			 * " AND CAST(SUBSTRING(T.TAREAD_FECHA_FIN,1,10) AS DATE) >= '" +
			 * datefechaFin + "'))");
			 */

			if(horaInicio == 0 && horaFin == 0){
				// no deberia ejecutar nada, por el momento
			}
			else{

				/*
				 * sql=sql.append(" AND T.TAREI_HORA_INICIO <=" + horaInicio);
				 * sql=sql.append(" AND T.TAREI_HORA_FIN >=" + horaFin);
				 */
			}

			sql.append("AND consultado.numero = ? ");
			sql.append("AND t.estado IN ('" + Constants.COD_ESTADO_ACTIVO + "','" + Constants.COD_ESTADO_INACTIVO + "') ");
			sql.append("ORDER BY t.id_tarea");

			/*
			 * sql=sql.append(" AND TM.MOBILE =? ");
			 * sql=sql.append(" AND T.TAREAC_ESTADO IN ('" +
			 * Constants.COD_ESTADO_ACTIVO + "','" +
			 * Constants.COD_ESTADO_INACTIVO + "')");
			 * sql=sql.append(" ORDER BY T.TAREAI_ID ");
			 */

			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());

			pstmt.setDate(1,new java.sql.Date(fechaInicio.getTime()));
			pstmt.setDate(2,new java.sql.Date(fechaInicio.getTime()));
			pstmt.setDate(3,new java.sql.Date(fechaFin.getTime()));
			pstmt.setDate(4,new java.sql.Date(fechaFin.getTime()));
			pstmt.setInt(5,horaInicio);
			pstmt.setInt(6,horaInicio);
			pstmt.setInt(7,horaFin);
			pstmt.setInt(8,horaFin);

			pstmt.setString(9,mobile);
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			while(rs.next()){
				log.debug("Presente en tarea " + rs.getString("idTarea"));
				isValid=false;
				log.debug("No es validO el Numero " + mobile);
				break;
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
		return isValid;
	}

	public List<Localizacion> getLocalizacionesPorTarea(Tarea tarea) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Localizacion> listaResultado=new ArrayList<Localizacion>();
		log.debug("DAO antes del sql");
		StringBuffer sql=new StringBuffer("SELECT l.id_consulta as codLocalizacion,");
		sql.append("consultante.numero as mobileOrigen,");
		sql.append("consultado.numero as mobileDestino,");
		sql.append("l.fecha_consulta as fechaRegistro,");
		sql.append("l.resultado as mensaje,");
		sql.append("c.id_celda as codCelda,");
		sql.append("c.direccion as direccion,");
		sql.append("c.x as ejeX,");
		sql.append("c.y as ejeY, ");
		sql.append("cpt.id_tarea AS codTarea ");
		sql.append("FROM consulta l ");
		sql.append("INNER JOIN usuario consultante ON l.consultante=consultante.id_usuario ");
		sql.append("INNER JOIN usuario consultado ON l.consultado=consultado.id_usuario ");
		sql.append("INNER JOIN celda c ON l.id_celda=c.id_celda ");
		sql.append("INNER JOIN consulta_por_tarea cpt ON cpt.id_consulta=l.id_consulta ");
		sql.append("WHERE cpt.id_tarea=" + tarea.getIdTarea());
		sql.append(" ORDER BY 1 ASC");
		log.debug("sql " + sql.toString());
		try{
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
				localizacion.setDireccion(rs.getString("direccion"));
				localizacion.setEjeX(rs.getFloat("ejeX"));
				localizacion.setEjeY(rs.getFloat("ejeY"));
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

}
