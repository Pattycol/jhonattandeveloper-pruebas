package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoPeru;
import entidades.Departamento;

@Controller("peruController")
public class PeruController {
	
	private DaoPeru daoPeru;
	
	public PeruController(DaoPeru daoPeru){
		this.daoPeru = daoPeru;
	}
	
	@RequestMapping(params="accion=getData")
	public ModelAndView getData(){
		
		List<Object[]> list= daoPeru.query();
		return new ModelAndView("vista", "lista", list);
	}
	
	@RequestMapping(params="accion=mntDpto")
	public ModelAndView mntDpto(){
		
		Departamento departamento= new Departamento();
		return new ModelAndView("departamentos", "departamento", departamento);
	}
	
	
	@RequestMapping(params="accion=addDepart")
	public ModelAndView addDepart(@ModelAttribute Departamento departamento){
		String msg="Correcto";
		
		try {
			daoPeru.addDepartamento(departamento);
		} catch (Exception e) {
			msg="Incorrecto";
		}
	
		return new ModelAndView("resultado", "msg", msg);
	}

}
