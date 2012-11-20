package com.hildebrando.claro.WSLBS.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.domain.TipoServicio;
import com.btg.dao.dao.DAO;
import com.hildebrando.claro.WSLBS.dao.TipoServicioDAO;


@Repository("TipoServicioDAO")
public class TipoServicioDAOHibernate extends DAO<TipoServicio> implements TipoServicioDAO {

	public TipoServicio obtenerTipoServicioPorNombre(String nombre) {
		String sql="SELECT t FROM TipoServicio t WHERE t.nombre =:nombre";
		Query q=em.createQuery(sql);
		q.setParameter("nombre",nombre);
		//q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		try{
			return (TipoServicio) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}
