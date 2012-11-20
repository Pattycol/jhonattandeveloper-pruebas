package com.btg.claro.localizacion.celda;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("CeldaOracleDAO")
public class CeldaOracleDAOHibernate  extends DAO<Celda> implements CeldaOracleDAO {

    public Celda getPorIdentificador(String identificador) {
        String sql = "SELECT c FROM Celda c WHERE c.identificador=:identificador";
        Query q = em.createQuery(sql);
        q.setParameter("identificador", identificador);
        try {
            return (Celda) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
	

}
