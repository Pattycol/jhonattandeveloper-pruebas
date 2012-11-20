package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.beans.MensajesSeguridad;

public interface CargarMensajesService {
	
	public MensajesSeguridad obtenerMensajes(MensajesSeguridadFilter mensajesSeguridadFilter);

}
