package com.btg.claro.localizacion.daos;

import java.util.List;

import com.btg.claro.localizacion.entidades.IEntidad;

public interface IDAO<T extends IEntidad>{
	
	public T get(Integer id);
	
	public List<T> getTodos();
	
	public void guardar(T objeto);

}
