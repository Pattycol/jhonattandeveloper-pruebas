package com.btg.claro.LBS.aprovisionamiento.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.btg.claro.LBS.domain.Usuario;

public interface ConsultasEmpresaService{

	List<Map<String,Object>> buscarUsuario(String query);
	
	List<Map<String,Object>> buscarEmpresas(String query, Usuario usuario);

	Map<String,Object> reporteUsuarioConsultado(int usuario,Date fechaInicio,Date fechaFin);
	
	Map<String,Object> reporteUsuarioConsultante(int usuario,Date fechaInicio,Date fechaFin);
	
	Map<String,Object> reporteFechaConsultada(Date fechaInicio,Date fechaFin);

}
