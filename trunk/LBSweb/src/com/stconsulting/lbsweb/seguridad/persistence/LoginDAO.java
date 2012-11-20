/*
 * SeniorityDAO.java
 *
 * Created on July 21, 2004, 11:08 AM
 */

package com.stconsulting.lbsweb.seguridad.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.lbsweb.seguridad.bean.*;

import com.stconsulting.common.util.*;

/**
 * 
 * @author STConsulting
 */
public class LoginDAO extends GenericDAO{

	private static String strBD_LBS;

	/** Creates a new instance of SeniorityDAO */
	public LoginDAO(TransactionContext context){
		super(context);
		this.context=context;
		//strBD_LBS=Helper.getSchema(Constants.BD_LBS);
		log=Logger.getLogger(this.getClass());
	}

	public Usuario getUsuario(String codigo, boolean tipo) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Usuario usuario=null;
		log.debug("DAO antes del sql ,codigo : " + codigo);
		StringBuffer sql=new StringBuffer("SELECT u.id_usuario as codUsuario,");
		sql.append("u.clave as password,");
		sql.append("u.numero as telefono,");
		sql.append("u.clave_recuperada as recuperada,");
		sql.append("r.codigo as codPerfil,");
		sql.append("u.nombres||' '||u.apellidos as nombres,");
		sql.append("u.estado as codestado,");
		sql.append("u.fecha_actualizacion as fechaActualizacion,");
		sql.append("e.id_empresa as codEmpresa,");
		sql.append("e.razon_social as empresa ");
		sql.append("FROM area a,empresa e,usuario u ");
		sql.append("INNER JOIN rol r ON r.id_rol=u.rol ");
		sql.append("WHERE u.numero=? AND ");
		sql.append("a.id_area=(SELECT id_area FROM usuario_por_area WHERE id_usuario=u.id_usuario AND rownum=1) AND a.empresa=e.id_empresa");
		try{
			// String codEmpresa =
			// Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_COD_EMPRESA);
			// String codEmpresa =
			// Parameters.getParameter(Constants.KEY_COD_EMPRESA);
			// String codAreaAdmin =
			// Parameters.getParameter(Constants.KEY_COD_AREA_ADMIN);

			/*sql=sql.append("SELECT U.HABII_TELEFONO as codUsuario, ");
			sql=sql.append(" U.HABIV_PASSWORD as password, ");
			sql=sql.append(" A.HAARC_TIPO as codPerfil, ");
			sql=sql.append(" U.HABIV_NOMBRES as nombres, ");
			sql=sql.append(" A.EMPRI_CODIGO as codEmpresa, ");
			sql=sql.append(" U.HABIV_EMPRESA as empresa, ");
			sql=sql.append(" U.HABIC_ESTADO as codEstado, ");
			sql=sql.append(" S.SERVICIOI_ID as codServicio, ");
			sql=sql.append(" CAST(SUBSTRING(U.HABID_FECHA_ACTUALIZACION,1,10) AS DATE) as fechaActualizacion ");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_HABILITADOXAREA A, " + strBD_LBS + ".LBST_HABILITADO U ");
			sql=sql.append(" LEFT JOIN " + strBD_LBS + ".LBST_SERVICIOSXHABILITADO S ON U.HABII_TELEFONO=S.HABII_TELEFONO ");
			sql=sql.append(" AND S.SERVICIOI_ID=?");
			sql=sql.append(" WHERE U.HABII_TELEFONO=? ");
			sql=sql.append(" AND U.HABII_TELEFONO=A.HABII_CODIGO ");*/
			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			//pstmt.setString(1,Constants.COD_SERVICIO_LBS);
			pstmt.setString(1,codigo);

			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			if(rs.next()){
				usuario=new Usuario();
				usuario.setCodUsuario(rs.getInt("codUsuario"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setPassword(rs.getString("password"));
				usuario.setCodPerfil(rs.getString("codPerfil"));
				usuario.setNombreCompleto(rs.getString("nombres"));
				usuario.setCodEmpresa(rs.getInt("codEmpresa"));
				usuario.setDescEmpresa(rs.getString("empresa"));
				usuario.setEstado(rs.getString("codEstado"));
				if(tipo == true){
					usuario.setClaveRecuperada("");
				}else{
					usuario.setClaveRecuperada(rs.getString("recuperada"));
				}
				usuario.setCodServicio(Constants.COD_SERVICIO_LBS);
				usuario.setFechaActualizacion(rs.getDate("fechaActualizacion"));
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
			e.printStackTrace();
			log.info("ERROR: LoginDAO: getUsuario: " + e.getMessage() + e.toString());
			throw new DAOException(e);
		}
		return usuario;
	}

	public void cambiarPassword(Usuario usuario,String password, boolean tipo) throws DAOException{
		PreparedStatement pstmt=null;
		StringBuffer sql=null;
		try{
			log.debug("DAO antes del sql cambiarPassword " + usuario);

			sql=new StringBuffer("");
			
			if(tipo == true){
				sql.append("UPDATE Usuario ");
				sql.append("SET clave = ?, fecha_actualizacion = CURRENT_DATE, clave_recuperada = 'I' ");
				sql.append("WHERE numero = ?");
			}else{
				sql.append("UPDATE Usuario ");
				sql.append("SET clave = ?, fecha_actualizacion = CURRENT_DATE, clave_recuperada = 'A' ");
				sql.append("WHERE numero = ?");
			}
			/*sql=sql.append(" UPDATE " + strBD_LBS + ".LBST_HABILITADO");
			sql=sql.append(" SET HABIV_PASSWORD = ?,");
			sql=sql.append(" HABID_FECHA_ACTUALIZACION = now()");
			sql=sql.append(" WHERE HABII_TELEFONO = ?");*/
						
			log.debug("sql :" + sql.toString() + " psw:" + password + ",nro:" + usuario.getTelefono());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,password);
			pstmt.setString(2,usuario.getTelefono());

			int i=pstmt.executeUpdate();
			log.debug("despues del update," + i + " row(s) updated");

			pstmt.close();
			pstmt=null;

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
			throw new DAOException(e);
		}
	}
	
}
