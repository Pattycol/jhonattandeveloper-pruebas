package com.btg.claro.localizacion.daos.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.btg.claro.localizacion.daos.IDAO;
import com.btg.claro.localizacion.entidades.IEntidad;

public class DAO<T extends IEntidad> implements IDAO<T>{
	
	private Class<IEntidad> clazz;
	
	@PersistenceContext(unitName="Localizacion")
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	public DAO(){
		clazz=(Class<IEntidad>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
	}	

}
