package com.hildebrando.claro.WSLBS.dao.impl;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.domain.Auditoria;
import com.btg.dao.dao.DAO;
import com.hildebrando.claro.WSLBS.dao.AuditoriaDAO;

@Repository("AuditoriaDAO")
public class AuditoriaDAOHibernate extends DAO<Auditoria> implements AuditoriaDAO{

}
