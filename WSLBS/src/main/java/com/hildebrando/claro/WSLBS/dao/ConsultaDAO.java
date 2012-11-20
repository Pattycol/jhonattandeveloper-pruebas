package com.hildebrando.claro.WSLBS.dao;

import java.util.Date;
import java.util.List;

import com.btg.claro.LBS.domain.Consulta;
import com.btg.dao.dao.IDAO;

public interface ConsultaDAO extends IDAO<Consulta>{

	List<Consulta> getReportePorUsuarioConsultante(int usuario,Date fechaInicio,Date fechaFin);
	
	List<Consulta> getReportePorUsuarioConsultado(int usuario,Date fechaInicio,Date fechaFin);
	
	List<Consulta> getReportePorFechaConsultada(Date fechaInicio,Date fechaFin);
	
	List<Consulta> getReportePorUsuarioConsultanteYUsuarioConsultado(int usuarioConsultante,
												int usuarioConsultado, Date fechaInicio,Date fechaFin);

}
