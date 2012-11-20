package com.btg.claro.LBS.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.BlackListDAO;
import com.btg.claro.LBS.domain.Blacklist;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.util.Constantes;

@Repository("BlackListDAO")
public class BlackListDAOHibernate extends DAO<Blacklist> implements BlackListDAO{

	public Blacklist buscarBlackListActivoPorNumeroDeUsuario(Usuario usuario){
		Query query=em.createQuery("from Blacklist b where b.usuario.numero=:numero and b.estado=:estado");
		query.setParameter("numero",usuario.getNumero());
		query.setParameter("estado",Constantes.ESTADO_ACTIVO);
		try{
			return (Blacklist) query.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}
