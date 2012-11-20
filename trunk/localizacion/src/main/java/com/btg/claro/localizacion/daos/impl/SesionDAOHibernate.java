package com.btg.claro.localizacion.daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.localizacion.daos.SesionDAO;
import com.btg.claro.localizacion.entidades.Sesion;

@Repository("SesionDAO")
public class SesionDAOHibernate extends DAO<Sesion> implements SesionDAO{

	public Sesion getPorToken(String token){
		String sql="SELECT s FROM Sesion s WHERE s.token=:token";
		Query q=em.createQuery(sql);
		q.setParameter("token",token);
		try{
			return (Sesion) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}
