package com.btg.claro.LBS.aprovisionamiento.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoCeldaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/celda")
public class MantenimientoCeldaController{
	
	@Autowired
	private MantenimientoCeldaService mantenimientoCeldaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String mantenimientoCelda(){
		return "mantenimiento/celda";
	}
	
	@RequestMapping(value="nueva",method=RequestMethod.GET)
	public String nuevaCelda(){
		return "mantenimiento/celdaFormulario";
	}
	
	@RequestMapping(value="guardar",method=RequestMethod.POST)
	public String guardar(Celda celda,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		mantenimientoCeldaService.guardarCelda(celda,usuario);
		return "redirect:/celda";
	}
	
	@RequestMapping(value="modificar",method=RequestMethod.GET)
	public String modificar(@RequestParam int id,Model model){
		Celda celda=mantenimientoCeldaService.buscarCelda(id);
		if(celda!=null){
			model.addAttribute("c",celda);
		}
		return "mantenimiento/celdaFormulario";
	}
	
	@RequestMapping(value="existe",method=RequestMethod.GET)
	public @ResponseBody boolean existeCelda(@RequestParam String identificador,@RequestParam int id){
		if(id<=0)
			return !mantenimientoCeldaService.existeCelda(identificador);
		return true;
	}
	
	@RequestMapping(value="buscar",method=RequestMethod.GET)
	public @ResponseBody List<Celda> bucarCelda(@RequestParam String query){
		return mantenimientoCeldaService.buscarCeldasPorIdentificador(query);
	}

}
