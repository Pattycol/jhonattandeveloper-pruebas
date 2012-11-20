package com.hildebrando.claro.WSLBS.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hildebrando.claro.WSLBS.ws.ResultadoConsulta;

public interface MantenimientoConsultasService{

	List<Map<String,Object>> buscarUsuario(String query);

	Map<String,Object> reporteUsuarioConsultado(int usuario,Date fechaInicio,Date fechaFin);
	
	Map<String,Object> reporteUsuarioConsultante(int usuario,Date fechaInicio,Date fechaFin);
	
	Map<String,Object> reporteFechaConsultada(Date fechaInicio,Date fechaFin);
	
	List<ResultadoConsulta> reporteConsultadoConsultanteRangoFechas(int usuarioConsultante,
			int usuarioConsultado,
			Date fechaInicio,
			Date fechaFin);

}
