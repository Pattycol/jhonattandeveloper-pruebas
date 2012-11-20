package com.btg.claro.localizacion.daos;

import com.btg.claro.localizacion.entidades.Sesion;

public interface SesionDAO extends IDAO<Sesion> {

	Sesion getPorToken(String token);

}
