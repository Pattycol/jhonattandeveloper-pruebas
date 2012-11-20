package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.AreaDAO;
import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.DAO;

@Repository("AreaDAO")
public class AreaDAOHibernate extends DAO<Area> implements AreaDAO{
	
	private static final Logger log=LoggerFactory.getLogger(AreaDAOHibernate.class);

	@SuppressWarnings("unchecked")
	public List<Area> getAreasPorEmpresa(int idEmpresa){
		String sql="SELECT new Area(a.id,a.nombre,a.consultasPorMes) FROM Area a WHERE a.empresa.id=:idEmpresa";
		Query q=em.createQuery(sql);
		q.setParameter("idEmpresa",idEmpresa);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Area> getAreasPadre(int idEmpresa,Area area){
		//TODO hacer que se valide que no liste las areas que son hijos de esta area
		String sql="SELECT new Area(a.id,a.nombre,a.consultasPorMes) FROM Area a WHERE a.empresa.id=:idEmpresa AND a.id<>:idArea";
		Query q=em.createQuery(sql);
		q.setParameter("idEmpresa",idEmpresa);
		q.setParameter("idArea",area.getId());
		try{
			return q.getResultList();
		}catch(Exception e){
			log.error("Ocurrio un error al obtener los padres",e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Area> getAreasPorUsuario(Usuario usuario){
		String sql="SELECT a FROM Area a,Usuario u WHERE u.id=:usuario AND a IN elements(u.areas)";
		Query q=em.createQuery(sql);
		q.setParameter("usuario",usuario.getId());
		return q.getResultList();
	}

}
