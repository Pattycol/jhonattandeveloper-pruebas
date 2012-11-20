package com.hildebrando.claro.WSLBS.dao;

import com.btg.claro.LBS.domain.TipoServicio;
import com.btg.dao.dao.IDAO;

public interface TipoServicioDAO extends IDAO<TipoServicio> {

	TipoServicio obtenerTipoServicioPorNombre(String nombre);

}
