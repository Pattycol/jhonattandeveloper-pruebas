package pe.com.claro.caef.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarPreguntas;
import pe.com.claro.caef.web.services.AvisoLegalYAcuerdosService;
import pe.com.claro.caef.web.ws.TransaccionSeguridadWS;

@Service("avisoLegalYAcuerdosService")
public class AvisoLegalYAcuerdosServiceImpl implements
		AvisoLegalYAcuerdosService {

	@Autowired
	private TransaccionSeguridadWS transaccionSeguridadWS;
	
	public AuditTypes grabarPreguntas(Usuario usuario,
			GrabarPreguntas grabarPreguntas) {
		
		AuditTypes  at = new AuditTypes();
		
		at = transaccionSeguridadWS.grabarPreguntas(usuario, grabarPreguntas);
		
		return at;
	}

}
