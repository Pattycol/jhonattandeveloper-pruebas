package com.btg.claro.LBS.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.CeldaDAO;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.util.Constantes;

@Repository("CeldaDAO")
public class CeldaDAOHibernate  extends DAO<Celda> implements CeldaDAO{

	public Celda buscarCeldaPorIdentificador(String identificador){
		Query query=em.createQuery("from Celda c where c.identificador=:id AND c.estado<>:estado");
		query.setParameter("id",identificador);
		query.setParameter("estado",Constantes.ESTADO_INACTIVO);
		try{
			return (Celda) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}
	
}
