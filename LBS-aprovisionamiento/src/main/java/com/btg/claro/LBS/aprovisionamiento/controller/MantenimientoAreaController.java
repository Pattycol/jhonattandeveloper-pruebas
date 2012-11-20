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

import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoAreaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/area")
public class MantenimientoAreaController{
	
	@Autowired
	private MantenimientoAreaService mantenimientoAreaService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String get(Model model){
		model.addAttribute("empresas",mantenimientoAreaService.getEmpresas());
		return "mantenimiento/area";
	}
	
	@RequestMapping(value="porEmpresa",method=RequestMethod.GET)
	public @ResponseBody List<Area> getAreasPorEmpresa(@RequestParam int idEmpresa){
		return mantenimientoAreaService.getAreasPorEmpresa(idEmpresa);
	}
	
	@RequestMapping(value="nueva",method=RequestMethod.GET)
	public String nuevaArea(@RequestParam int empresa,Model model){
		model.addAttribute("empresas",mantenimientoAreaService.getEmpresas());
		model.addAttribute("empresa",mantenimientoAreaService.getEmpresa(empresa));
		return "mantenimiento/areaFormulario";
	}
	
	@RequestMapping(value="guardar",method=RequestMethod.POST)
	public @ResponseBody Area guardar(Area area,@RequestParam int idPadre,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		mantenimientoAreaService.guardarArea(area,idPadre,usuario);
		return area;
	}
	
	@RequestMapping(value="modificar",method=RequestMethod.GET)
	public String modificar(@RequestParam int id,@RequestParam int empresa,Model model){
		Area area=mantenimientoAreaService.buscarArea(id);
		if(area!=null){
			model.addAttribute("a",area);
			model.addAttribute("padres",mantenimientoAreaService.getAreasPadre(area.getEmpresa().getId(),area));
		}
		model.addAttribute("empresas",mantenimientoAreaService.getEmpresas());
		model.addAttribute("empresa",mantenimientoAreaService.getEmpresa(empresa));
		return "mantenimiento/areaFormulario";
	}
	
	@RequestMapping(value="maxConsultas",method=RequestMethod.GET)
	public @ResponseBody boolean maxConsultas(@RequestParam int consultasPorMes,@RequestParam int idEmpresa,@RequestParam int id){
		if(idEmpresa>0)
			return mantenimientoAreaService.consultasPermitidas(id,consultasPorMes,idEmpresa);
		return true;
	}

}
