package com.hildebrando.claro.WSLBS.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.domain.LbsError;
import com.btg.dao.dao.DAO;
import com.hildebrando.claro.WSLBS.dao.LbsErrorDAO;

@Repository("LbsErrorDAO")
public class LbsErrorDAOHibernate extends DAO<LbsError> implements LbsErrorDAO {

	public LbsError obtenerMensaje(int id) {
		String sql="SELECT l FROM LbsError l WHERE l.id=:id";
		Query q=em.createQuery(sql);
		q.setParameter("id",id);
		try{
			return (LbsError) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}
