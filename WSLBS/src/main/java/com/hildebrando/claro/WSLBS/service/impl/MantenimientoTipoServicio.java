package com.hildebrando.claro.WSLBS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.domain.TipoServicio;
import com.hildebrando.claro.WSLBS.dao.TipoServicioDAO;
import com.hildebrando.claro.WSLBS.service.MantenimientoTipoServicioService;


@Service("MantenimientoTipoServicioService")
public class MantenimientoTipoServicio implements MantenimientoTipoServicioService {

	
	@Autowired
	private TipoServicioDAO tipoServicioDAO;
	
	public TipoServicio validaTipoServicio(String nombre) {
		// TODO Auto-generated method stub
		return tipoServicioDAO.obtenerTipoServicioPorNombre(nombre);
	}

}
