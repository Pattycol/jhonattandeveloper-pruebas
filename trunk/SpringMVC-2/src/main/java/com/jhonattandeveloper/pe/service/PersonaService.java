package com.jhonattandeveloper.pe.service;

import java.util.List;
import com.jhonattandeveloper.pe.domain.*;

public interface PersonaService {

	public List<Persona> getAll();
	
	public Persona get(Integer id);
	
	public void add(Persona persona);
	
	public void delete(Integer id);
	
	public void edit(Persona persona);
	
}
