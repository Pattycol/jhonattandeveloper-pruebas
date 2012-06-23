package pe.com.jhonattandeveloper.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/personas")
public class PersonaController {
	
	protected static Logger logger= Logger.getLogger("service");
	
	@Autowired
	private PersonaService personaService;

	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String getPersonas(Model model){
		logger.debug("Request recibido para mostrar todas las personas");
		
		List<Persona> personas =  personaService.getAll();
		
		model.addAttribute("personas", personas);
		
		return "listpersonas";
	}
	
	@RequestMapping(value="/add", method= RequestMethod.GET)
	public String getAdd(Model model){
		logger.debug("Request recibido para mostrar pagina de adicion de una persona");
		
		model.addAttribute("persona", new Persona());
		
		return "addpersona";
		
	}
	
	@RequestMapping(value="/add", method= RequestMethod.POST )
	public String add(@ModelAttribute("persona") Persona persona){
		logger.debug("Request recibido para agregar una persona");
		
		personaService.add(persona);
		
		return "addedpersona";
		
	}
	
	@RequestMapping(value="/delete", method= RequestMethod.GET)
	public String delete(@RequestParam(value="id", required=true)Integer id,Model model){
		
		logger.debug("Request recibido para eliminar una persona existente");
		
		personaService.delete(id);
		model.addAttribute("id", id);
		
		return "deletedpersona";
		
	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam(value="id",required=true)Integer id, 
						  Model model){
		logger.debug("Request recibido para ver la pagina de edicion de una persona");
		
		model.addAttribute("persona", personaService.get(id));
		
		return "editpersona";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("persona")Persona persona,
							@RequestParam(value="id",required=true)Integer id,
							Model model){
		logger.debug("Request recibido para editar una persona");
		persona.setId(id);
		personaService.edit(persona);
		model.addAttribute("id", id);
		
		return "editedpersona";
	}
	
	
}
