package com.btg.claro.LBS.aprovisionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoBlacklistService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Blacklist;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/blacklist")
public class MantenimientoBlacklistController{
	
	@Autowired
	private MantenimientoBlacklistService mantenimientoBlacklistService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String mantenimientoBlacklist(Model model){
		model.addAttribute("blacklists",mantenimientoBlacklistService.getBlacklists());
		return "mantenimiento/blacklist";
	}
	
	@RequestMapping(value="nueva",method=RequestMethod.GET)
	public String nuevaBlacklist(){
		return "mantenimiento/blacklistFormulario";
	}
	
	@RequestMapping(value="guardar",method=RequestMethod.POST)
	public String guardar(Blacklist blacklist,@RequestParam String numero,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		mantenimientoBlacklistService.guardarBlacklist(blacklist,numero,usuario);
		return "redirect:/blacklist";
	}
	
	@RequestMapping(value="modificar",method=RequestMethod.GET)
	public String modificar(@RequestParam int id,Model model){
		Blacklist blacklist=mantenimientoBlacklistService.buscarBlacklist(id);
		if(blacklist!=null){
			model.addAttribute("b",blacklist);
		}
		return "mantenimiento/blacklistFormulario";
	}
	
	@RequestMapping(value="existeUsuario",method=RequestMethod.GET)
	public @ResponseBody boolean existeUsuario(@RequestParam String numero){
		return mantenimientoBlacklistService.existeUsuario(numero);
	}

}
