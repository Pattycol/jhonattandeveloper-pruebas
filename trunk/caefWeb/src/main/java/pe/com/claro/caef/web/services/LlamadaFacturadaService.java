package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasFacturadasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.LlamadaFacturada;

public interface LlamadaFacturadaService {
	
	public List<LlamadaFacturada> getConsultarListaLlamadasFacturadas(Usuario usuario, ConsultarListaLlamadasFacturadasFilter consultarListaLlamadasFacturadasFilter);
	
}
