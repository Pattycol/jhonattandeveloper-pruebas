package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarContactosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarServiciosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarSucursalClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ContactoCliente;

public interface ConsultaClienteService {
	
	public ConsultaCliente getConsultarDatosGenCliente(Usuario usuario, ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter);
	
}
