package com.stconsulting.lbsweb.consulta.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;

public class TipoServicioDAO extends GenericDAO {

	public TipoServicioDAO(TransactionContext context) {
		super(context);
		this.context=context;
		log=Logger.getLogger(this.getClass());
		// TODO Auto-generated constructor stub
	}
	
	public String getConsultasWebxTipo(String idEmpresa) throws DAOException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String consultas=null;
		log.debug("DAO antes del sql");
		StringBuffer sql=new StringBuffer("SELECT t.consultas_web as consultas_web ");
		sql.append("FROM Tipo_Servicio t ");
		sql.append("inner join Empresa e on e.id_tipo_servicio=t.id_tipo_servicio ");
		sql.append("WHERE e.id_empresa=? ");
		log.debug("sql " + sql.toString());
		try{
			/*sql=sql.append("SELECT A.HABII_CODIGO as numero ");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_HABILITADOXAREA A ");
			sql=sql.append(" WHERE A.EMPRI_CODIGO=? ");
			sql=sql.append(" AND A.HABII_CODIGO=? ");*/

			pstmt=context.getConnection().prepareStatement(sql.toString());
			pstmt.setString(1,idEmpresa);
			rs=pstmt.executeQuery();
			log.debug("despues del execute");
			if(rs.next()){
				consultas=""+rs.getInt("consultas_web");
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
		return consultas;
	}

}
