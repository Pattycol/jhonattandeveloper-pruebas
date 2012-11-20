package pe.com.claro.caef.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarRespuestas;
import pe.com.claro.caef.web.beans.UsuarioPreguntas;
import pe.com.claro.caef.web.services.EliminarPreguntasSecretasService;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;
import pe.com.claro.caef.web.ws.TransaccionSeguridadWS;

@Service("eliminarPreguntasSecretasService")
public class EliminarPreguntasSecretasServiceImpl implements
		EliminarPreguntasSecretasService {

	@Autowired
	private TransaccionSeguridadWS transaccionSeguridadWS;
	
	@Autowired
	private ConsultaSeguridadWS consultaSeguridadWS;
	
	public AuditTypes grabarRespuestas(Usuario usuario,
			GrabarRespuestas grabarRespuestas) {
		
		AuditTypes at = new AuditTypes();
		at = transaccionSeguridadWS.grabarRespuestas(usuario, grabarRespuestas);
		
		return at;
	}

	public AuditTypes usuarioPreguntas(Usuario usuario,
			UsuarioPreguntas usuarioPreguntas) {
		
		AuditTypes at = new AuditTypes();
		at = transaccionSeguridadWS.usuarioPreguntas(usuario, usuarioPreguntas);
		
		return at;
	}

}
