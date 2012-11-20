package com.btg.claro.LBS.aprovisionamiento.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoUsuarioService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Usuario;

@Controller
@RequestMapping("/usuario")
public class MantenimientoUsuarioController{
	
	@Autowired
	private MantenimientoUsuarioService mantenimientoUsuarioService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String mantenimientoUsuario(/*Model model*/){
		//model.addAttribute("usuarios",mantenimientoUsuarioService.getUsuarios());
		return "mantenimiento/usuario";
	}
	
	@RequestMapping(value="nuevo",method=RequestMethod.GET)
	public String nuevoUsuario(@RequestParam int area,Model model,HttpSession sesion){
		if(area==-1){
			Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
			model.addAttribute("empresas",mantenimientoUsuarioService.getEmpresas());
			if(usuario.getRol().getCodigo().equals(Constantes.ROL_SUPER_USUARIO)){
				model.addAttribute("roles",mantenimientoUsuarioService.getRoles());
			}
			else{
				model.addAttribute("roles",mantenimientoUsuarioService.getRolesNoAdmin());
			}
		}
		else{
			model.addAttribute("area",mantenimientoUsuarioService.getArea(area));
			model.addAttribute("roles",mantenimientoUsuarioService.getRolesNoAdmin());
		}
		
		return "mantenimiento/usuarioFormulario";
	}
	
	@RequestMapping(value="guardar",method=RequestMethod.POST)
	public @ResponseBody Usuario guardar(Usuario usuario,@RequestParam String claveOld,@RequestParam int[] idsAreas,HttpSession sesion){
		Usuario usuarioSesion=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		mantenimientoUsuarioService.guardarUsuario(usuario,claveOld,idsAreas,usuarioSesion);
		return usuario;
	}
	
	@RequestMapping(value="modificar",method=RequestMethod.GET)
	public String modificar(@RequestParam int area,@RequestParam int id,Model model){
		Usuario usuario=mantenimientoUsuarioService.buscarUsuario(id);
		if(usuario!=null){
			if(area==-1){
				model.addAttribute("empresas",mantenimientoUsuarioService.getEmpresas());
				model.addAttribute("detalleArea",mantenimientoUsuarioService.getDetalleAreas(usuario));
				model.addAttribute("roles",mantenimientoUsuarioService.getRoles());
			}
			else{
				model.addAttribute("area",mantenimientoUsuarioService.getArea(area));
				model.addAttribute("roles",mantenimientoUsuarioService.getRolesNoAdmin());
			}
			model.addAttribute("u",usuario);
			
		}
		return "mantenimiento/usuarioFormulario";
	}
	
	@RequestMapping(value="existe",method=RequestMethod.GET)
	public @ResponseBody boolean existeUsuario(@RequestParam String numero,@RequestParam int id){
		if(id<=0)
			return !mantenimientoUsuarioService.existeUsuario(numero);
		return true;
	}
	
	@RequestMapping(value="maxConsultas",method=RequestMethod.GET)
	public @ResponseBody boolean maxConsultas(@RequestParam int id,@RequestParam int consultasPorMes,String areas){
		List<Integer> idsAreas=new ArrayList<Integer>();
		StringTokenizer st=new StringTokenizer(areas,",");
		while(st.hasMoreElements()){
			idsAreas.add(Integer.parseInt(st.nextToken()));
		}
		return mantenimientoUsuarioService.consultasPermitidas(id,consultasPorMes,idsAreas);
	}
	
	@RequestMapping(value="buscar",method=RequestMethod.GET)
	public @ResponseBody List<Usuario> bucarUsuario(@RequestParam String query,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		String rol=usuario.getRol().getCodigo();
		if(rol.equals(Constantes.ROL_SUPER_USUARIO)){
			return mantenimientoUsuarioService.buscarUsuarios(query);
		}
		else if(rol.equals(Constantes.ROL_ADMIN)){
			return mantenimientoUsuarioService.buscarUsuariosNoSuper(query);
		}
		else{
			return mantenimientoUsuarioService.buscarUsuariosNoAdmin(query);
		}
	}
	
	@RequestMapping(value="porArea",method=RequestMethod.GET)
	public @ResponseBody List<Usuario> obtenerUsuariosPorArea(@RequestParam int idArea){
		return mantenimientoUsuarioService.getUsuariosPorArea(idArea);
	}
	
	@RequestMapping(value="eliminar",method=RequestMethod.POST)
	public @ResponseBody boolean eliminarUsuario(@RequestParam int id,HttpSession sesion){
		Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
		return mantenimientoUsuarioService.eliminarUsuario(id,usuario);
	}
	
	@RequestMapping(value="buscarUsuarioEliminado",method=RequestMethod.GET)
	public @ResponseBody Usuario buscarUsuarioEliminado(@RequestParam String numero){
		return mantenimientoUsuarioService.buscarUsuarioEliminado(numero);
	}
	

}
