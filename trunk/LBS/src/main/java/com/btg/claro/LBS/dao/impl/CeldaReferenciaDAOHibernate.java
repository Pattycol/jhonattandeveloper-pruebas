package com.btg.claro.LBS.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.celda.CeldaReferencia;
import com.btg.claro.LBS.dao.CeldaReferenciaDAO;
import com.btg.claro.LBS.domain.Celda;

@Repository("CeldaReferenciaDAO")
public class CeldaReferenciaDAOHibernate implements CeldaReferenciaDAO{
	
	//@PersistenceContext(unitName="celdaPU",type=PersistenceContextType.EXTENDED)
	protected EntityManager em;

	public CeldaReferencia get(Integer id){
		return em.find(CeldaReferencia.class,id);
	}

	@SuppressWarnings("unchecked")
	public List<CeldaReferencia> getTodos(){
		String sql="SELECT e FROM CeldaReferencia e";
		return em.createQuery(sql).getResultList();
	}

	public void guardar(CeldaReferencia objeto){
		if(objeto.getId()!=null)
			em.merge(objeto);
		else
			em.persist(objeto);
	}

	@SuppressWarnings("unchecked")
	public List<CeldaReferencia> buscarNuevas(List<Celda> celdas){
		String sql="SELECT c FROM CeldaReferencia c WHERE c.identificador NOT IN(";
		boolean primero=true;
		for(Celda c : celdas){
			if(!primero)
				sql+=",'"+c.getIdentificador()+"'";
			else{
				sql+="'"+c.getIdentificador()+"'";
				primero=false;
			}
		}
		sql+=")";
		return em.createQuery(sql).getResultList();
	}

}
