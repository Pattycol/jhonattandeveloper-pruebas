package com.btg.claro.LBS.aprovisionamiento.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.btg.claro.LBS.aprovisionamiento.service.LoginService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController{
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(){
		//log.debug(Util.toMD5("password"));
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String post(@RequestParam String numero,@RequestParam String clave,HttpSession sesion,Model model){
		if(loginService.iniciarSesion(numero,clave,sesion)){
			return "redirect:/home";
		}
		model.addAttribute("error","Número o clave incorrectos");
		return "login";
	}
	
	@RequestMapping(value="/out",method=RequestMethod.GET)
	public String logout(HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		if(usuario!=null){
			usuario=null;
			sesion.removeAttribute(Constantes.SESION_USUARIO);
			sesion.setAttribute(Constantes.SESION_USUARIO,null);
		}
		return "redirect:/";
	}

}
