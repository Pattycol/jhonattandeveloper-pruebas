package com.btg.claro.LBS.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.AreaDAO;
import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Blacklist;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;

@Repository("AreaDAO")
public class AreaDAOHibernate extends DAO<Area> implements AreaDAO{

	@SuppressWarnings("unchecked")
	public List<Area> getAreasPorUsuario(Usuario usuario){
		String sql="SELECT a FROM Area a,Usuario u WHERE u.id=:usuario AND a IN elements(u.areas)";
		Query q=em.createQuery(sql);
		q.setParameter("usuario",usuario.getId());
		return q.getResultList();
	}
	
	public Empresa getEmpresaAreaUsuario(String telefono){
		
		StringBuffer sql=new StringBuffer("SELECT a.empresa ");
		sql.append("FROM Usuario u,Area a ");
		sql.append("WHERE u.numero=:numero ");
		sql.append("AND a IN elements(u.areas) and rownum=1");
		
		Query q=em.createQuery(sql.toString());
		q.setParameter("numero",telefono);
		try{
			return (Empresa) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	
		
	}
	
	

}
