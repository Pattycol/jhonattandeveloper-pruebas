package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.UsuarioDAO;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.DAO;

@Repository("UsuarioDAO")
public class UsuarioDAOHibernate extends DAO<Usuario> implements UsuarioDAO{

	public Usuario buscarPorNumeroClave(String numero,String clave){
		em.clear();
		String sql="SELECT u FROM Usuario u WHERE u.numero=:numero AND u.clave=:clave AND u.rol.codigo IN (:admin,:super,:atencion) AND u.estado=:estado";
		Query q=em.createQuery(sql);
		q.setParameter("numero",numero);
		q.setParameter("clave",clave);
		q.setParameter("admin",Constantes.ROL_ADMIN);
		q.setParameter("super",Constantes.ROL_SUPER_USUARIO);
		q.setParameter("atencion",Constantes.ROL_ATENCION_CLIENTE);
		q.setParameter("estado",Constantes.ESTADO_ACTIVO);
		try{
			return (Usuario) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarios(){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos) FROM Usuario u ORDER BY u.numero";
		return em.createQuery(sql).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuariosPorArea(Area area){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos,u.consultasPorMes) FROM Usuario u WHERE u.estado<>:estado AND :area in elements(u.areas) ORDER BY u.numero";
		Query q=em.createQuery(sql);
		q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		q.setParameter("area",area);
		return q.getResultList();
	}

	public Usuario buscarPorNumero(String numero){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos) FROM Usuario u WHERE u.numero=:numero and u.estado!=:estado";
		Query q=em.createQuery(sql);
		q.setParameter("numero",numero);
		q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		try{
			return (Usuario) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuarios(String query){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos) FROM Usuario u WHERE u.estado<>:estado AND (u.numero LIKE :query OR lower(u.nombres) LIKE :query OR lower(u.apellidos) LIKE :query) ORDER BY u.numero";
		Query q=em.createQuery(sql);
		q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		q.setParameter("query",query);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosNoSuper(String query){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos) FROM Usuario u WHERE u.estado<>:estado AND (u.numero LIKE :query OR lower(u.nombres) LIKE :query OR lower(u.apellidos) LIKE :query) AND u.rol.codigo<>:super ORDER BY u.numero";
		Query q=em.createQuery(sql);
		q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		q.setParameter("query",query);
		q.setParameter("super",Constantes.ROL_SUPER_USUARIO);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosNoAdmin(String query){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos) FROM Usuario u WHERE u.estado<>:estado AND (u.numero LIKE :query OR lower(u.nombres) LIKE :query OR lower(u.apellidos) LIKE :query) AND u.rol.codigo NOT IN (:super,:admin) ORDER BY u.numero";
		Query q=em.createQuery(sql);
		q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		q.setParameter("query",query);
		q.setParameter("super",Constantes.ROL_SUPER_USUARIO);
		q.setParameter("admin",Constantes.ROL_ADMIN);
		return q.getResultList();
	}

	public Usuario buscarUsuarioEliminado(String numero){
		String sql="SELECT u FROM Usuario u WHERE u.numero=:numero and u.estado=:estado";
		Query q=em.createQuery(sql);
		q.setParameter("numero",numero);
		q.setParameter("estado",Constantes.ESTADO_ELIMINADO);
		try{
			return (Usuario) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}

	}

}
