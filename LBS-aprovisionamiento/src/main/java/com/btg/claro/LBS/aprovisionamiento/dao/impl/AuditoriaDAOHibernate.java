package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.AuditoriaDAO;
import com.btg.claro.LBS.domain.Auditoria;
import com.btg.dao.dao.DAO;

@Repository("AuditoriaDAO")
public class AuditoriaDAOHibernate extends DAO<Auditoria> implements AuditoriaDAO{

}
