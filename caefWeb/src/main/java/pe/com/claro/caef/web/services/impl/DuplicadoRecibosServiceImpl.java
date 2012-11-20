package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarDetalleReciboClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarReciboClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DuplicadoRecibo;
import pe.com.claro.caef.web.beans.DuplicadoReciboPDF;
import pe.com.claro.caef.web.services.DuplicadoRecibosService;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.ws.ConsultarRecibosWS;

@Service("duplicadoRecibosService")
public class DuplicadoRecibosServiceImpl implements DuplicadoRecibosService {

	@Autowired
	private ConsultarRecibosWS consultarRecibosWS;
	
	static final Logger log = Logger.getLogger(DuplicadoRecibosServiceImpl.class);
	
	public List<DuplicadoRecibo> getConsultarReciboCliente(Usuario usuario,
			ConsultarReciboClienteFilter consultarReciboClienteFilter) {
		
		List<DuplicadoRecibo> lstDuplicadoRecibo = new ArrayList<DuplicadoRecibo>();
		lstDuplicadoRecibo = consultarRecibosWS.consultarReciboCliente(usuario, consultarReciboClienteFilter);
		
		for (DuplicadoRecibo dr : lstDuplicadoRecibo) {
			dr.setFecEmision(Constantes.convertirFecha(dr.getFecEmision()));
			dr.setFecFacturacion(Constantes.convertirFecha(dr.getFecFacturacion()));
		}
		
		return lstDuplicadoRecibo;
	}
	
	public List<DuplicadoReciboPDF> consultarDetalleReciboCliente(Usuario usuario, 
			ConsultarDetalleReciboClienteFilter consultarDetalleReciboClienteFilter) {
		
		List<DuplicadoReciboPDF> lstDuplicadoReciboPDF = new ArrayList<DuplicadoReciboPDF>();
		lstDuplicadoReciboPDF = consultarRecibosWS.consultarDetalleReciboCliente(usuario, consultarDetalleReciboClienteFilter);
		
		return lstDuplicadoReciboPDF;
	}

}
