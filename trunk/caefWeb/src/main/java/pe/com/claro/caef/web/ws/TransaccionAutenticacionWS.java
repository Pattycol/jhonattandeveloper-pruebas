package pe.com.claro.caef.web.ws;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.NuevoUsuario;
import pe.com.claro.caef.web.beans.OlvideClave;

public interface TransaccionAutenticacionWS {
	
	public AuditTypes nuevoUsuario(Usuario usuario, NuevoUsuario nuevoUsuario);
	public AuditTypes olvideClave(Usuario usuario, OlvideClave olvideClave);

}
