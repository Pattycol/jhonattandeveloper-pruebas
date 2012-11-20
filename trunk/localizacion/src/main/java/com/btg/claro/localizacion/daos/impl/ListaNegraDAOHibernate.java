package com.btg.claro.localizacion.daos.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.localizacion.daos.ListaNegraDAO;
import com.btg.claro.localizacion.entidades.Aplicacion;
import com.btg.claro.localizacion.entidades.ListaNegra;

@Repository("ListaNegraDAO")
public class ListaNegraDAOHibernate extends DAO<ListaNegra> implements ListaNegraDAO{

	@SuppressWarnings("unchecked")
	public List<ListaNegra> getPorAplicacion(Aplicacion aplicacion){
		String sql="SELECT l FROM ListaNegra l WHERE l.aplicacion=:aplicacion";
		Query q=em.createQuery(sql);
		q.setParameter("aplicacion",aplicacion);
		return q.getResultList();
	}

}
