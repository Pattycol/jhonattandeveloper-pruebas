package com.btg.claro.localizacion.celda;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


public class DAO<T extends Entidad> implements IDAO<T>{
	
	private Class<Entidad> clazz;
	
	@PersistenceContext(unitName="lbsPU")
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	public DAO(){
		clazz=(Class<Entidad>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
    @SuppressWarnings("unchecked")
	public T get(Integer id){
		return (T) em.find(clazz,id);
	}
	
	@Override
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

    @Override
    @SuppressWarnings({ "unchecked", "cast" })
    public T getPorIdentificador2(String identificador) {
        Entity e = (Entity) clazz.getAnnotation(Entity.class);
        String nombre = null;
        if (e == null || e.name() == null || e.name().length() == 0)
            nombre = clazz.getSimpleName();
        else
            nombre = e.name();
        String sql = "SELECT e FROM " + nombre + " e where e.identificador =:identificador";

        try {
            return (T) em.createQuery(sql).setParameter("identificador", identificador)
                    .getSingleResult();
        } catch (NoResultException e2) {
            return null;
        }
    }

	

}
