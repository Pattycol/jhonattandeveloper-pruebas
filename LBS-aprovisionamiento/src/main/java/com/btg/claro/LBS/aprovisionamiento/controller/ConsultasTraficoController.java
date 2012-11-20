package com.btg.claro.LBS.aprovisionamiento.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btg.claro.LBS.aprovisionamiento.service.ConsultasEmpresaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/consultasTrafico")
public class ConsultasTraficoController{
	
	@Autowired
	private ConsultasEmpresaService consultasEmpresaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(){
		return "reportes/consultasTrafico";
	}
	
	@RequestMapping(value="buscarEmpresas",method=RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> buscarEmpresas(@RequestParam String query, HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		return consultasEmpresaService.buscarEmpresas(query,usuario);
	}
	
	@RequestMapping(value="buscarUsuario",method=RequestMethod.GET)
	public @ResponseBody List<Map<String,Object>> buscarUsuario(@RequestParam String term){
		return consultasEmpresaService.buscarUsuario(term);
	}
	
	@RequestMapping(value="usuario",method=RequestMethod.GET)
	public String reporteUsuario(Model model,@RequestParam int usuario,@RequestParam Date fechaInicio,@RequestParam Date fechaFin){
		model.addAttribute("reporteConsultante",consultasEmpresaService.reporteUsuarioConsultante(usuario,fechaInicio,fechaFin));
		model.addAttribute("reporteConsultado",consultasEmpresaService.reporteUsuarioConsultado(usuario,fechaInicio,fechaFin));
		return "reportes/consultas";
	}

}
