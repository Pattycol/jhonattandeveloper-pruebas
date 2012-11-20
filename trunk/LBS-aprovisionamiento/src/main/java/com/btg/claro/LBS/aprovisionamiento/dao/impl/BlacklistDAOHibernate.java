package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.BlacklistDAO;
import com.btg.claro.LBS.domain.Blacklist;
import com.btg.dao.dao.DAO;

@Repository("BlacklistDAO")
public class BlacklistDAOHibernate extends DAO<Blacklist> implements BlacklistDAO{

	public Blacklist buscarBlacklistPorNumero(String numero){
		String sql="SELECT b FROM Blacklist WHERE b.usuario.numero=:numero";
		Query q=em.createQuery(sql);
		q.setParameter("numero",numero);
		try{
			return (Blacklist) q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}
