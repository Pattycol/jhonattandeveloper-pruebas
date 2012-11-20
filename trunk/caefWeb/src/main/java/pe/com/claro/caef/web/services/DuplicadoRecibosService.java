package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarDetalleReciboClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarReciboClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DuplicadoRecibo;
import pe.com.claro.caef.web.beans.DuplicadoReciboPDF;

public interface DuplicadoRecibosService {
	
	public List<DuplicadoRecibo> getConsultarReciboCliente(Usuario usuario, ConsultarReciboClienteFilter consultarReciboClienteFilter);
	
	public List<DuplicadoReciboPDF> consultarDetalleReciboCliente(Usuario usuario, ConsultarDetalleReciboClienteFilter consultarDetalleReciboClienteFilter);
}
