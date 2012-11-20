package pe.com.claro.caef.web.ws;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarDetalleReciboClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarEstadoCuentaFilter;
import pe.com.claro.caef.web.action.filter.ConsultarReciboClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.DuplicadoRecibo;
import pe.com.claro.caef.web.beans.DuplicadoReciboPDF;
import pe.com.claro.caef.web.beans.ReciboCorreoElectronico;

public interface ConsultarRecibosWS {

	/*OK*/public List<ReciboCorreoElectronico> consultarGrupoFactRecibos(Usuario usuario);
	/*OK*/public List<DuplicadoRecibo> consultarReciboCliente (Usuario usuario, ConsultarReciboClienteFilter consultarReciboClienteFilter);
	/*OK*/public List<DuplicadoReciboPDF> consultarDetalleReciboCliente(Usuario usuario, ConsultarDetalleReciboClienteFilter consultarDetalleReciboClienteFilter);
	/*OK*/public List<DetalleEstadoCuenta> consultarEstadoCuentaDetalle(Usuario usuario, ConsultarEstadoCuentaFilter consultarEstadoCuentaFilter);
}
