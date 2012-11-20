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

import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoEmpresaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/empresa")
public class MantenimientoEmpresaController{
	
	@Autowired
	private MantenimientoEmpresaService mantenimientoEmpresaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String mantenimientoEmpresa(/*Model model*/){
		//model.addAttribute("empresas",mantenimientoEmpresaService.getEmpresas());
		return "mantenimiento/empresa";
	}
	
	@RequestMapping(value="nueva",method=RequestMethod.GET)
	public String nuevaEmpresa(){
		return "mantenimiento/empresaFormulario";
	}
	
	@RequestMapping(value="guardar",method=RequestMethod.POST)
	public @ResponseBody Empresa guardar(Empresa empresa,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		mantenimientoEmpresaService.guardarEmpresa(empresa,usuario);
		return empresa;
	}
	
	@RequestMapping(value="modificar",method=RequestMethod.GET)
	public String modificar(@RequestParam int id,Model model){
		Empresa empresa=mantenimientoEmpresaService.buscarEmpresa(id);
		if(empresa!=null){
			model.addAttribute("e",empresa);
		}
		return "mantenimiento/empresaFormulario";
	}
	
	@RequestMapping(value="existente",method=RequestMethod.GET)
	public @ResponseBody boolean existeEmpresa(@RequestParam String ruc,@RequestParam int id){
		if(id<=0)
			return !mantenimientoEmpresaService.existeEmpresa(ruc);
		return true;
	}
	
	@RequestMapping(value="buscar",method=RequestMethod.GET)
	public @ResponseBody List<Empresa> bucarEmpresa(@RequestParam String query,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);		
		return mantenimientoEmpresaService.buscarEmpresas(query,usuario);
	}

}
