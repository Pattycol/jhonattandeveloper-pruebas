package pe.com.claro.caef.web.ws;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ActualizarDatosClienteFilter;
import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.action.filter.RegistrarIncidenciaFilter;
import pe.com.claro.caef.web.action.filter.RegistrarPublicacionDirectorioAbonadoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.RegistrarIncidencia;


public interface TransaccionClientesWS {
	
	/*OK*/public void registrarActivacionReciboCorreoElectronico(Usuario usuario, RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronico);
	/*OK*/public AuditTypes actualizarDatosCliente(Usuario usuario, ActualizarDatosClienteFilter actualizarDatosClienteFilter);
	/*OK*/public AuditTypes registrarPublicacionDirectorioAbonado(Usuario usuario, RegistrarPublicacionDirectorioAbonadoFilter registrarPublicacionDirectorioAbonadoFilter);
	/*OK*/public RegistrarIncidencia registrarIncidencia(Usuario usuario, RegistrarIncidenciaFilter registrarIncidenciaFilter);

}
