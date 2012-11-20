package com.btg.claro.localizacion.daos;

import java.util.List;

import com.btg.claro.localizacion.entidades.Aplicacion;
import com.btg.claro.localizacion.entidades.ListaNegra;

public interface ListaNegraDAO extends IDAO<ListaNegra> {

	List<ListaNegra> getPorAplicacion(Aplicacion aplicacion);
	
	

}
