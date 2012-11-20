package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasNoFacturadasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;
import pe.com.claro.caef.web.services.LlamadaNoFacturadaService;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.ws.ConsultarLlamadasWS;

@Service("llamadaNoFacturadaService")
public class LlamadaNoFacturadaServiceImpl implements LlamadaNoFacturadaService {

	@Autowired
	private ConsultarLlamadasWS consultarLlamadasWS;
	
	static final Logger log = Logger.getLogger(LlamadaNoFacturadaServiceImpl.class);
	
	public List<LlamadaNoFacturada> getConsultarListaLlamadasNofacturadas(Usuario usuario,
			ConsultarListaLlamadasNoFacturadasFilter consultarListaLlamadasNoFacturadasFilter) {
		// TODO Auto-generated method stub
		
		List<LlamadaNoFacturada> lstlnf = new ArrayList<LlamadaNoFacturada>();
		/*DATA DE PRUEBA PARA LA CARGA DE LLAMADAS NO FACTURADAS*/
		/*consultarListaLlamadasNoFacturadasFilter.setFecFin("30/09/2011");
		consultarListaLlamadasNoFacturadasFilter.setFecInicio("01/09/2011");*/
		consultarListaLlamadasNoFacturadasFilter.setNumeroPagina("1");
		//consultarListaLlamadasNoFacturadasFilter.setNumOrigen("16290161");
		
		lstlnf = consultarLlamadasWS.consultarListaLlamadasNofacturadas(usuario, consultarListaLlamadasNoFacturadasFilter);
		
		//Da formato a la fecha
		for (LlamadaNoFacturada lf : lstlnf) {
			lf.setHoraInicio(Constantes.convertirFecha(lf.getHoraInicio()));
			lf.setHoraFin(Constantes.convertirFecha(lf.getHoraFin()));
		}
		
		
		return lstlnf;
	}

}
