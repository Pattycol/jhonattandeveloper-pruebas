package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarPreguntas;

public interface AvisoLegalYAcuerdosService {
	
	public AuditTypes grabarPreguntas(Usuario usuario, GrabarPreguntas grabarPreguntas);

}
