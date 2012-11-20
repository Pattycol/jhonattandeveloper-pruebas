package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.Autenticar;
import pe.com.claro.caef.web.beans.AutenticarResponse;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;

public interface LoginService {
	
	public AutenticarResponse autenticar(Usuario usuario, Autenticar autenticar);
	public ObtenerDatosUsuario obtenerDatosUsuario(Usuario usuario, ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter);
	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario, ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter);

}
