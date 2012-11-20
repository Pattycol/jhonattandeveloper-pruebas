package com.btg.claro.LBS.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.EmpresaDAO;
import com.btg.claro.LBS.domain.Empresa;

@Repository("EmpresaDAO")
public class EmpresaDAOHibernate extends DAO<Empresa> implements EmpresaDAO {

	@Override
	public void incrementarConsultasWeb(String ruc) {
		// TODO Auto-generated method stub
		
		String sql=" update Empresa e "+
					" set e.consultas_web_realizadas = (select nvl(p.consultas_web_realizadas,0) +1 from Empresa p WHERE p.ruc = :ruc)"+
					" where e.ruc = :ruc";
		Query q=em.createQuery(sql);
		q.setParameter("ruc",ruc);
		q.executeUpdate();
		
	}

	@Override
	public void incrementarConsultasSMS(String ruc) {
		// TODO Auto-generated method stub
		String sql=" update Empresa e "+
				" set e.consultas_sms_realizadas = (select nvl(p.consultas_sms_realizadas,0) +1 from Empresa p WHERE p.ruc = :ruc)"+
				" where e.ruc = :ruc";
		Query q=em.createQuery(sql);
		q.setParameter("ruc",ruc);
		q.executeUpdate();
	}

	@Override
	public void incrementarConsultasAdicionales(String ruc) {
		// TODO Auto-generated method stub
		String sql=" update Empresa e "+
				" set e.consultas_adicionales = (select nvl(p.consultas_adicionales,0) +1 from Empresa p WHERE p.ruc = :ruc)"+
				" where e.ruc = :ruc";
	Query q=em.createQuery(sql);
	q.setParameter("ruc",ruc);
	q.executeUpdate();
	}

	

}
