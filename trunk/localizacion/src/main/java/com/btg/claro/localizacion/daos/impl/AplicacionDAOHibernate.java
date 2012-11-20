package com.btg.claro.localizacion.daos.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.localizacion.daos.AplicacionDAO;
import com.btg.claro.localizacion.entidades.Aplicacion;

@Repository("AplicacionDAO")
public class AplicacionDAOHibernate extends DAO<Aplicacion> implements AplicacionDAO {

	public Aplicacion buscarPorNombre(String nombre) {
		String sql="SELECT a FROM Aplicacion a WHERE a.nombre=:nombre";
		Query q=em.createQuery(sql);
		q.setParameter("nombre",nombre);
		try{
			return (Aplicacion) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}
