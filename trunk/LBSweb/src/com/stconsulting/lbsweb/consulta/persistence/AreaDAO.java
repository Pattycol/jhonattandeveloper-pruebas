package com.stconsulting.lbsweb.consulta.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.util.Constants;

public class AreaDAO extends GenericDAO {

	public AreaDAO(TransactionContext context) {
		super(context);
		this.context=context;
		log=Logger.getLogger(this.getClass());
		// TODO Auto-generated constructor stub
	}

	public String getEmpresaAreaUsuario(String telefono) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String idEmpresa=null;
		log.debug("DAO antes del sql");
		StringBuffer sql=new StringBuffer("SELECT a.empresa as id ");
		sql.append("FROM usuario u,area a ");
		sql.append("WHERE u.numero=? AND ");
		sql.append("a.id_area=(SELECT id_area FROM usuario_por_area WHERE id_usuario=u.id_usuario and rownum=1)");
		log.debug("sql " + sql.toString());
		try{
			

			/*sql=sql.append("SELECT A.HABII_CODIGO as numero ");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_HABILITADOXAREA A ");
			sql=sql.append(" WHERE A.EMPRI_CODIGO=? ");
			sql=sql.append(" AND A.HABII_CODIGO=? ");*/

			
			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,telefono);
		
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			if(rs.next()){
				idEmpresa=rs.getString("id");
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
		return idEmpresa;
	}
}
