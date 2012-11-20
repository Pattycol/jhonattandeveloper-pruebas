package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.CeldaDAO;
import com.btg.claro.LBS.domain.Celda;
import com.btg.dao.dao.DAO;

@Repository("CeldaDAO")
public class CeldaDAOHibernate extends DAO<Celda> implements CeldaDAO{

	@SuppressWarnings("unchecked")
	public List<Celda> getCeldas(){
		String sql="SELECT new Celda(c.id,c.identificador,c.direccion) FROM Celda c ORDER BY c.identificador";
		return em.createQuery(sql).getResultList();
	}

	public Celda getPorIdentificador(String identificador){
		String sql="SELECT c FROM Celda c WHERE c.identificador=:identificador";
		Query q=em.createQuery(sql);
		q.setParameter("identificador",identificador);
		try{
			return (Celda) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Celda> buscarPorIdentificador(String query){
		String sql="SELECT c FROM Celda c WHERE c.identificador LIKE :identificador";
		Query q=em.createQuery(sql);
		q.setParameter("identificador",query);
		return q.getResultList();
	}

}
