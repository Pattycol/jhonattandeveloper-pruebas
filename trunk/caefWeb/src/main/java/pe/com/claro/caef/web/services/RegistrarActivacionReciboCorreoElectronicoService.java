package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.auth.Usuario;

public interface RegistrarActivacionReciboCorreoElectronicoService {

	public void registrarActivacionReciboCorreoElectronico(Usuario usuario, RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronico);
}
