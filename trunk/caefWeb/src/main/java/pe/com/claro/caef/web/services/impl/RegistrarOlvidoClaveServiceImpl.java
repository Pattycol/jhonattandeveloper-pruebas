package pe.com.claro.caef.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ListarPreguntasAleatorias;
import pe.com.claro.caef.web.beans.OlvideClave;
import pe.com.claro.caef.web.beans.PreguntasAleatorias;
import pe.com.claro.caef.web.beans.ValidarPreguntasSecretas;
import pe.com.claro.caef.web.services.RegistrarOlvidoClaveService;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;
import pe.com.claro.caef.web.ws.TransaccionAutenticacionWS;

@Service("registrarOlvidoClaveService")
public class RegistrarOlvidoClaveServiceImpl implements
		RegistrarOlvidoClaveService {

	@Autowired
	private ConsultaSeguridadWS consultaSeguridadWS;
	
	@Autowired
	private TransaccionAutenticacionWS transaccionAutenticacionWS;
	
	public PreguntasAleatorias listaPreguntasAleatorias(Usuario usuario,
			ListarPreguntasAleatorias listarPreguntasAleatorias) {
		
		PreguntasAleatorias pa = new PreguntasAleatorias();
		pa = consultaSeguridadWS.listarPreguntasAleatorias(usuario, listarPreguntasAleatorias);
		return pa;
	}

	public AuditTypes validarPreguntasSecretas(Usuario usuario,
			ValidarPreguntasSecretas validarPreguntasSecretas) {

		AuditTypes at = new AuditTypes();
		at = consultaSeguridadWS.validarPreguntasSecretas(usuario, validarPreguntasSecretas);
		return at;
	}
	
	public AuditTypes olvideClave(Usuario usuario, OlvideClave olvideClave)
	{
		AuditTypes at = new AuditTypes();
		at = transaccionAutenticacionWS.olvideClave(usuario, olvideClave);
		return at;
	}

}
