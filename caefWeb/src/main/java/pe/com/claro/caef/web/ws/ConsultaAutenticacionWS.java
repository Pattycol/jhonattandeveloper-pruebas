package pe.com.claro.caef.web.ws;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.Autenticar;
import pe.com.claro.caef.web.beans.AutenticarResponse;

public interface ConsultaAutenticacionWS {
	
	public AutenticarResponse autenticar(Usuario usuario, Autenticar autenticar);

}
