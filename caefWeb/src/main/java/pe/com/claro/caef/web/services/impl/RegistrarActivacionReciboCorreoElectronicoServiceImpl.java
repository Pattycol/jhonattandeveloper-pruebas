package pe.com.claro.caef.web.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.services.RegistrarActivacionReciboCorreoElectronicoService;
import pe.com.claro.caef.web.ws.TransaccionClientesWS;

public class RegistrarActivacionReciboCorreoElectronicoServiceImpl implements
		RegistrarActivacionReciboCorreoElectronicoService {

	@Autowired
	private TransaccionClientesWS transaccionClientesWS;
	
	public void registrarActivacionReciboCorreoElectronico(
			Usuario usuario,
			RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronico) {
		
		transaccionClientesWS.registrarActivacionReciboCorreoElectronico(usuario, registrarActivacionReciboCorreoElectronico);
	}

}
