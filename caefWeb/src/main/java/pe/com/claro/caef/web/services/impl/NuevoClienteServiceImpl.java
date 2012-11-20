package pe.com.claro.caef.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.NuevoUsuario;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.services.NuevoClienteService;
import pe.com.claro.caef.web.services.PaqueteBusinessDelegate;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;
import pe.com.claro.caef.web.ws.TransaccionAutenticacionWS;

@Service("NuevoClienteService")
public class NuevoClienteServiceImpl implements NuevoClienteService{

	@Autowired
	private ConsultaSeguridadWS consultaSeguridadWS;
	
	@Autowired
	private TransaccionAutenticacionWS transaccionAutenticacionWS; 
	
	
	public ObtenerDatosUsuario obtenerDatosUsuario(Usuario usuario,
			ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter) {
		consultaSeguridadWS=PaqueteBusinessDelegate.getConsultaSeguridadWSImpl();
		ObtenerDatosUsuario odu = new ObtenerDatosUsuario();		
		odu = consultaSeguridadWS.obtenerDatosUsuario(usuario, obtenerDatosUsuarioFilter);
		
		return odu;
	}

	public AuditTypes nuevoUsuario(Usuario usuario, NuevoUsuario nuevoUsuario) {
	
		AuditTypes au = new AuditTypes();
		transaccionAutenticacionWS=PaqueteBusinessDelegate.getTransaccionAutenticacionWSImpl();
		au = transaccionAutenticacionWS.nuevoUsuario(usuario, nuevoUsuario);
		
		return au;
	}

	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario,
			ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter) {
		consultaSeguridadWS=PaqueteBusinessDelegate.getConsultaSeguridadWSImpl();
		ObtenerDatosPreguntas op = new ObtenerDatosPreguntas();
		op = consultaSeguridadWS.obtenerDatosPreguntas(usuario, obtenerDatosPreguntasFilter);
		
		return op;
	}


}
