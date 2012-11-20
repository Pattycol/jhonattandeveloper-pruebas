package pe.com.claro.caef.web.ws;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarConsumoClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarInstanciaServicioFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumTelefonicoAbonadoFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.ObtenerUrlTrafficViewFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaServicioClienteTotal;
import pe.com.claro.caef.web.beans.ConsultarInstanciaServicio;
import pe.com.claro.caef.web.beans.ConsultarNumTelefonicoAbonado;
import pe.com.claro.caef.web.beans.ContactoCliente;
import pe.com.claro.caef.web.beans.EstadoCuenta;
import pe.com.claro.caef.web.beans.SeleccionarServicio;
import pe.com.claro.caef.web.beans.ServicioCliente;
import pe.com.claro.caef.web.beans.SucursalCliente;
import pe.com.claro.caef.web.beans.TrafficView;
import pe.com.claro.caef.web.beans.UrlTrafficView;

public interface ConsultarClienteWS {

	/*OK*/public List<ContactoCliente> consultarContactosCliente(Usuario usuario); 
	/*OK*/public List<SucursalCliente> consultarSucursalCliente(Usuario usuario);
	/*OK*/public List<ServicioCliente> consultarServicioCliente(Usuario usuario);
	/*OK*/public List<ConsultaNumeroTelefonico> consultarNumerosTelefonicos(Usuario usuario, ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter);
	/*OK*/public List<ConsultarNumTelefonicoAbonado> consultarNumTelefonicoAbonado(Usuario usuario, ConsultarNumTelefonicoAbonadoFilter consultarNumTelefonicoAbonadoFilter);
	/*OK*/public List<ConsultaServicioClienteTotal> consultarServiciosClienteTotal(Usuario usuario);
	/*OK*/public List<ConsultarInstanciaServicio> consultarInstanciaServicio(Usuario usuario, ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter);
	/*OK*/public List<TrafficView> consultarListTrafficView(Usuario usuario);
	/*OK*/public List<ConsultaDatosMaestro> consultarDatosMaestros(Usuario usuario, ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter);
	/*OK*/public UrlTrafficView obtenerUrlTrafficView(Usuario usuario, ObtenerUrlTrafficViewFilter obtenerUrlTrafficViewFilter);
	/*OK*/public List<ConsultaConsumo> consultarConsumoCliente(Usuario usuario, ConsultarConsumoClienteFilter consultarConsumoClienteFilter);
	/*OK*/public EstadoCuenta consultarCabeceraEstadoCuenta(Usuario usuario);
	/*OK*/public ConsultaCliente consultarDatosGenCliente(Usuario usuario, ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter);
	
}
