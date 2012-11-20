package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarRespuestas;
import pe.com.claro.caef.web.beans.UsuarioPreguntas;

public interface EliminarPreguntasSecretasService {
	
	public AuditTypes grabarRespuestas(Usuario usuario, GrabarRespuestas grabarRespuestas);
	public AuditTypes usuarioPreguntas(Usuario usuario, UsuarioPreguntas usuarioPreguntas);

}
