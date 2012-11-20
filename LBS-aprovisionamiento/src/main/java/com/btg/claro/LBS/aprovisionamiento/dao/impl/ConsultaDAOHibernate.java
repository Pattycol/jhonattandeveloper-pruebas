package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.ConsultaDAO;
import com.btg.claro.LBS.domain.Consulta;
import com.btg.dao.dao.DAO;

@Repository("ConsultaDAO")
public class ConsultaDAOHibernate extends DAO<Consulta> implements ConsultaDAO{

	@SuppressWarnings("unchecked")
	public List<Consulta> getReportePorUsuarioConsultante(int usuario,Date fechaInicio,Date fechaFin){
		String sql="SELECT c FROM Consulta c WHERE c.consultante.id=:usuario AND c.fechaConsulta BETWEEN :fechaInicio AND :fechaFin ORDER BY c.fechaConsulta DESC";
		Query q=em.createQuery(sql);
		q.setParameter("usuario",usuario);
		q.setParameter("fechaInicio",fechaInicio);
		q.setParameter("fechaFin",fechaFin);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Consulta> getReportePorUsuarioConsultado(int usuario,Date fechaInicio,Date fechaFin){
		String sql="SELECT c FROM Consulta c WHERE c.consultado.id=:usuario AND c.fechaConsulta BETWEEN :fechaInicio AND :fechaFin ORDER BY c.fechaConsulta DESC";
		Query q=em.createQuery(sql);
		q.setParameter("usuario",usuario);
		q.setParameter("fechaInicio",fechaInicio);
		q.setParameter("fechaFin",fechaFin);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Consulta> getReportePorFechaConsultada(Date fechaInicio,
			Date fechaFin) {
		String sql="SELECT c FROM Consulta c WHERE c.fechaConsulta BETWEEN :fechaInicio AND :fechaFin ORDER BY c.fechaConsulta DESC";
		Query q=em.createQuery(sql);
		q.setParameter("fechaInicio",fechaInicio);
		q.setParameter("fechaFin",fechaFin);
		return q.getResultList();
	}

}