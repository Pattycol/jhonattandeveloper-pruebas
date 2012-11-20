package com.btg.claro.LBS.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.UsuarioDAO;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.util.Constantes;

@Repository("UsuarioDAO")
public class UsuarioDAOHibernate extends DAO<Usuario> implements UsuarioDAO{

	public Usuario buscarPorNumero(String numero){
		String sql="SELECT new Usuario(u.id,u.numero,u.nombres,u.apellidos,u.consultasPorMes,u.rol) FROM Usuario u WHERE u.numero=:numero AND u.estado=:estado";
		Query q=em.createQuery(sql);
		q.setParameter("numero",numero);
		q.setParameter("estado",Constantes.ESTADO_ACTIVO);
		try{
			Usuario usuario=(Usuario) q.getSingleResult();
			
			//em.refresh(usuario);
			//if(usuario.getAreas().size()>0)
				return usuario;
			//return null;
				
		}
		catch(NoResultException e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getAdministradores(){
		String sql="SELECT u FROM Usuario u WHERE u.rol.codigo=:admin";
		Query q=em.createQuery(sql);
		q.setParameter("admin",Constantes.CODIGO_ROL_ADMIN);
		return q.getResultList();
	}

}
