/*
 * BusquedaNumeroAsociadoDAO.java
 *
 * Created on 2 de junio de 2005, 11:07 AM
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
import com.stconsulting.lbsweb.seguridad.bean.*;

/**
 * 
 * @author STCosulting
 */
public class BusquedaNumeroAsociadoDAO extends GenericDAO{
	private static String strBD_LBS;

	/** Creates a new instance of BusquedaNumeroAsociadoDAO */
	public BusquedaNumeroAsociadoDAO(TransactionContext context){
		super(context);
		this.context=context;
		strBD_LBS=Helper.getSchema(Constants.BD_LBS);
		log=Logger.getLogger(this.getClass());
	}
	
	/**REN Probado -----------------------------------------------------------------------------------*/
	public List<ResultadoBusquedaNumero> busquedaNumeros(Usuario usuario,String numero) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ResultadoBusquedaNumero> listaResultado=null;
		StringBuffer sql=null;
		try{
			log.debug("DAO antes del sql");

			listaResultado=new ArrayList<ResultadoBusquedaNumero>();
			sql=new StringBuffer("");
			
			sql = sql.append("SELECT u.numero as mobileUsuario, ");
			sql = sql.append("(u.apellidos || ', ' || u.nombres) as nombres, ");
			sql = sql.append("a.empresa as codEmpresa ");
			sql = sql.append("FROM Usuario u, Area a ");
			sql = sql.append("WHERE a.id_area = (SELECT id_area FROM usuario_por_area WHERE id_usuario=u.id_usuario and rownum=1) ");
			sql = sql.append("AND a.empresa=? AND u.numero=?");			
			
			/*sql=sql.append(" SELECT U.HABII_TELEFONO as mobileUsuario, ");
			sql=sql.append(" U.HABIV_NOMBRES as nombres, ");
			sql=sql.append(" A.EMPRI_CODIGO as codEmpresa ");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_HABILITADO U, " + strBD_LBS + ".LBST_HABILITADOXAREA A ");
			sql=sql.append(" WHERE U.HABII_TELEFONO = A.HABII_CODIGO");
			sql=sql.append(" AND A.EMPRI_CODIGO = ?");
			sql=sql.append(" AND A.HABII_CODIGO LIKE '" + numero + "%'");
			sql=sql.append(" ORDER BY 1");*/
			
			
			log.debug("sql " + sql.toString());
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setInt(1,usuario.getCodEmpresa());
			pstmt.setString(2,numero);
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			while(rs.next()){
				ResultadoBusquedaNumero resultadoDetalle=new ResultadoBusquedaNumero();
				resultadoDetalle.setMobile(rs.getString("mobileUsuario"));
				resultadoDetalle.setNombreUsuario(rs.getString("nombres"));
				listaResultado.add(resultadoDetalle);
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
