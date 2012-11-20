package com.btg.claro.localizacion.endpoints.respuestas;

import com.btg.claro.localizacion.util.Ubicacion;

public class LocalizacionRespuesta {
	
	private String error;
	
	private Ubicacion ubicacion;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

}
