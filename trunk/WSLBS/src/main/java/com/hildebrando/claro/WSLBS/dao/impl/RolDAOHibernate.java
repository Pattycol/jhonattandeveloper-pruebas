package com.hildebrando.claro.WSLBS.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.domain.Rol;
import com.btg.dao.dao.DAO;
import com.hildebrando.claro.WSLBS.dao.RolDAO;
import com.hildebrando.claro.WSLBS.util.Constantes;

@Repository("RolDAO")
public class RolDAOHibernate extends DAO<Rol> implements RolDAO{

	@SuppressWarnings("unchecked")
	public List<Rol> getRolesActivos(){
		String sql="SELECT new Rol(r.id,r.nombre,r.codigo) FROM Rol r WHERE r.codigo<>:super AND r.estado=:estado ORDER BY r.nombre";
		Query q=em.createQuery(sql);
		q.setParameter("super",Constantes.ROL_SUPER_USUARIO);
		q.setParameter("estado",Constantes.ESTADO_ACTIVO);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Rol> getRolesNoAdmin(){
		String sql="SELECT new Rol(r.id,r.nombre,r.codigo) FROM Rol r WHERE r.codigo NOT IN (:super,:admin,:atencion) AND r.estado=:estado ORDER BY r.nombre";
		Query q=em.createQuery(sql);
		q.setParameter("super",Constantes.ROL_SUPER_USUARIO);
		q.setParameter("admin",Constantes.ROL_ADMIN);
		q.setParameter("atencion",Constantes.ROL_ATENCION_CLIENTE);
		q.setParameter("estado",Constantes.ESTADO_ACTIVO);
		return q.getResultList();
	}

}
