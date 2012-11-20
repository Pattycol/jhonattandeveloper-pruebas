package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ListarPreguntasAleatorias;
import pe.com.claro.caef.web.beans.OlvideClave;
import pe.com.claro.caef.web.beans.PreguntasAleatorias;
import pe.com.claro.caef.web.beans.ValidarPreguntasSecretas;

public interface RegistrarOlvidoClaveService {
	
	public PreguntasAleatorias listaPreguntasAleatorias(Usuario usuario, ListarPreguntasAleatorias listarPreguntasAleatorias);
	public AuditTypes validarPreguntasSecretas(Usuario usuario, ValidarPreguntasSecretas validarPreguntasSecretas);
	public AuditTypes olvideClave(Usuario usuario, OlvideClave olvideClave);

}
