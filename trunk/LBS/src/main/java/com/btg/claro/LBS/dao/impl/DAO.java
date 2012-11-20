package com.btg.claro.LBS.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.btg.dao.dao.IDAO;
import com.btg.dao.entidad.Entidad;

public class DAO<T extends Entidad> implements IDAO<T>{
	
private Class<Entidad> clazz;
	
	@PersistenceContext(unitName="lbsPU")
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	public DAO(){
		clazz=(Class<Entidad>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	public T get(Integer id){
		return (T) em.find(clazz,id);
	}
	
	@SuppressWarnings({"unchecked","cast"})
	public List<T> getTodos(){
		Entity e=(Entity)clazz.getAnnotation(Entity.class);
		String nombre=null;
		if(e==null || e.name()==null || e.name().length()==0)
			nombre=clazz.getSimpleName();
		else
			nombre=e.name();
		String sql="SELECT e FROM "+nombre+" e";
		return em.createQuery(sql).getResultList();
	}

	public void guardar(T objeto){
		if(objeto.getId()!=null)
			em.merge(objeto);
		else
			em.persist(objeto);
		//em.flush();
	}

}
