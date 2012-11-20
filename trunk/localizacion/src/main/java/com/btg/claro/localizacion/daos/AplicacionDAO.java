package com.btg.claro.localizacion.daos;

import com.btg.claro.localizacion.entidades.Aplicacion;

public interface AplicacionDAO extends IDAO<Aplicacion> {

	Aplicacion buscarPorNombre(String nombre);

}
