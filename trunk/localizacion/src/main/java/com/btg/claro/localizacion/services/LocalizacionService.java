package com.btg.claro.localizacion.services;

import com.btg.claro.localizacion.entidades.Sesion;
import com.btg.claro.localizacion.util.Ejecucion;
import com.btg.claro.localizacion.util.Ubicacion;

public interface LocalizacionService{

	Sesion validarSesion(String token);

    Ubicacion localizarMovil(String telefono, Sesion sesion, boolean resultado, Ejecucion ejecucion);

}
