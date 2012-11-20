	package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ActualizarDatosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ActualizaCliente;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaCliente;

public interface ActualizaClienteService {
	
	public ConsultaCliente getActualizarDatosCliente(Usuario usuario, ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter);
	public AuditTypes actualizarDatosCliente(Usuario usuario, ConsultaCliente consultaCliente);

}
