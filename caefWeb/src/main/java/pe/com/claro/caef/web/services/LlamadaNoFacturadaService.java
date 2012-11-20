package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasNoFacturadasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;

public interface LlamadaNoFacturadaService {
	
	public List<LlamadaNoFacturada> getConsultarListaLlamadasNofacturadas(Usuario usuario, ConsultarListaLlamadasNoFacturadasFilter consultarListaLlamadasNoFacturadasFilter);

}
