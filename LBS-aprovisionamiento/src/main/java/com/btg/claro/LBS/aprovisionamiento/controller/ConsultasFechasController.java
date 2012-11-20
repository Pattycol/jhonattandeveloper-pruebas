package com.btg.claro.LBS.aprovisionamiento.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.btg.claro.LBS.aprovisionamiento.service.ConsultasEmpresaService;

@Controller
@RequestMapping("/consultasFecha")
public class ConsultasFechasController {
	
	@Autowired
	private ConsultasEmpresaService consultasEmpresaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(){
		return "reportes/consultasFechas";
	}
	
	@RequestMapping(value="fecha",method=RequestMethod.GET)
	public String reporteUsuario(Model model,@RequestParam Date fechaInicio,@RequestParam Date fechaFin){
		model.addAttribute("reporteConsultado",consultasEmpresaService.reporteFechaConsultada(fechaInicio, fechaFin));
		return "reportes/resultadoConsulta";
	}

}
