package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarCabeceraEstadoCuentaFilter;
import pe.com.claro.caef.web.action.filter.ConsultarEstadoCuentaFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.EstadoCuenta;
import pe.com.claro.caef.web.services.EstadoCuentaService;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.caef.web.ws.ConsultarRecibosWS;

@Service("estadoCuentaService")
public class EstadoCuentaServiceImpl implements EstadoCuentaService {

	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	@Autowired
	private ConsultarRecibosWS consultarRecibosWS;
	
	static final Logger log = Logger.getLogger(EstadoCuentaServiceImpl.class);
	
	public EstadoCuenta getConsultarCabeceraEstadoCuenta(Usuario usuario) {
		// TODO Auto-generated method stub
		EstadoCuenta ec = new EstadoCuenta();
		
		String [] deudaActual = consultaCliente.consultarCabeceraEstadoCuenta(usuario).getValDeudaActual().split("/");
		String [] deudaVencida = consultaCliente.consultarCabeceraEstadoCuenta(usuario).getValDeudaVencida().split("/");
		
		ec.setValDeudaActualSol(deudaActual[0]);
		ec.setValDeudaActualDol(deudaActual[1]);
		ec.setValDeudaVencidaSol(deudaVencida[0]);
		ec.setValDeudaVencidaDol(deudaVencida[1]);
		
		ec.setFecEmision(consultaCliente.consultarCabeceraEstadoCuenta(usuario).getFecEmision());
		ec.setFecVencimiento(consultaCliente.consultarCabeceraEstadoCuenta(usuario).getFecVencimiento());
		//ec.setValDeudaActual(consultaCliente.consultarCabeceraEstadoCuenta(usuario).getValDeudaActual());
		//ec.setValDeudaVencida(consultaCliente.consultarCabeceraEstadoCuenta(usuario).getValDeudaVencida());
		ec.setValMontoDisputa(consultaCliente.consultarCabeceraEstadoCuenta(usuario).getValMontoDisputa());
		
		
		ConsultarEstadoCuentaFilter consultarEstadoCuentaFilter = new ConsultarEstadoCuentaFilter();
		consultarEstadoCuentaFilter.setNumeroPagina("-1");
		consultarEstadoCuentaFilter.setFecEmision("");//no se usa en el procedure
		List<DetalleEstadoCuenta> lstDetalleEstadoCuenta = new ArrayList<DetalleEstadoCuenta>();
		lstDetalleEstadoCuenta = consultarRecibosWS.consultarEstadoCuentaDetalle(usuario, consultarEstadoCuentaFilter);
		
		//Para el formato de la fechas
		for (DetalleEstadoCuenta de : lstDetalleEstadoCuenta) {
			de.setFecRegistro(Constantes.convertirFecha(de.getFecRegistro()));
			de.setFecEmision(Constantes.convertirFecha(de.getFecEmision()));
			de.setFecVencimiento(Constantes.convertirFecha(de.getFecVencimiento()));
		}
		
		ec.setDetalleEstadoCuenta(lstDetalleEstadoCuenta);
		
		return ec;
	}

}
