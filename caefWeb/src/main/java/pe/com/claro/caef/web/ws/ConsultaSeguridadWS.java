package pe.com.claro.caef.web.ws;

import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ListarPreguntasAleatorias;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasAleatorias;
import pe.com.claro.caef.web.beans.ValidarPreguntasSecretas;

public interface ConsultaSeguridadWS {
	
	public PreguntasAleatorias listarPreguntasAleatorias(Usuario usuario, ListarPreguntasAleatorias listarPreguntasAleatorias);
	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario, ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter);
	public ObtenerDatosUsuario obtenerDatosUsuario(Usuario usuario, ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter);
	public AuditTypes validarPreguntasSecretas(Usuario usuario, ValidarPreguntasSecretas validarPreguntasSecretas);

}
