package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasFacturadasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.services.LlamadaFacturadaService;
import pe.com.claro.caef.web.ws.ConsultarLlamadasWS;

@Service("llamadaFacturadaService")
public class LlamadaFacturadaServiceImpl implements LlamadaFacturadaService {
	
	@Autowired
	private ConsultarLlamadasWS consultarLlamadasWS;
	
	static final Logger log = Logger.getLogger(LlamadaFacturadaServiceImpl.class);

	public List<LlamadaFacturada> getConsultarListaLlamadasFacturadas(Usuario usuario,
			ConsultarListaLlamadasFacturadasFilter consultarListaLlamadasFacturadasFilter) {
		// TODO Auto-generated method stub
		List<LlamadaFacturada> llf = new ArrayList<LlamadaFacturada>();
		//consultarListaLlamadasFacturadasFilter = new ConsultarListaLlamadasFacturadasFilter();
		
		/*DATA DE PRUEBA*/
		/*consultarListaLlamadasFacturadasFilter.setFecFin("30/09/2011");
		consultarListaLlamadasFacturadasFilter.setFecInicio("01/09/2011");
		consultarListaLlamadasFacturadasFilter.setNumOrigen("16290161");*/
		consultarListaLlamadasFacturadasFilter.setNumeroPagina("-1");
		llf = consultarLlamadasWS.consultarListaLlamadasFacturadas(usuario, consultarListaLlamadasFacturadasFilter);
		
		
		
		return llf; 
	}

}
