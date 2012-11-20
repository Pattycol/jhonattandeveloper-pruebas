package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasEntrantesFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.LlamadaEntrante;
import pe.com.claro.caef.web.services.LlamadaEntranteService;
import pe.com.claro.caef.web.ws.ConsultarLlamadasWS;

@Service("llamadaEntranteService")
public class LlamadaEntranteServiceImpl implements LlamadaEntranteService {

	@Autowired
	private ConsultarLlamadasWS consultarLlamadasWS;
	
	static final Logger log = Logger.getLogger(LlamadaEntranteServiceImpl.class);
	
	public List<LlamadaEntrante> getConsultarListaLlamadasEntrantes(Usuario usuario,
			ConsultarListaLlamadasEntrantesFilter consultarListaLlamadasEntrantesFilter) {
		
		List<LlamadaEntrante> lstLlamadaEntrante = new ArrayList<LlamadaEntrante>();
		
		/*DATA DE PRUEBA*/
		//consultarListaLlamadasEntrantesFilter = new ConsultarListaLlamadasEntrantesFilter();
		//consultarListaLlamadasEntrantesFilter.setNumDestino("16172828");
		//consultarListaLlamadasEntrantesFilter.setNumeroPagina("-1");
		//consultarListaLlamadasEntrantesFilter.setFecInicio("15/11/2011");
		//consultarListaLlamadasEntrantesFilter.setFecFin("29/11/2011");
		lstLlamadaEntrante = consultarLlamadasWS.consultarListaLlamadasEntrantes(usuario, consultarListaLlamadasEntrantesFilter);
		
		/*LlamadaEntrante le = new LlamadaEntrante();
		
		le.setFecFin("20/11/2011");
		le.setFecInicio("20/11/2011");
		le.setNumDestino("4312743");
		le.setNumOrigen("986625066");
		le.setValDuracion("7");
		lstLlamadaEntrante.add(le);
		
		le = new LlamadaEntrante();
		
		le.setFecFin("20/11/2011");
		le.setFecInicio("20/11/2011");
		le.setNumDestino("4312743");
		le.setNumOrigen("986625066");
		le.setValDuracion("10");
		lstLlamadaEntrante.add(le);
		
		le = new LlamadaEntrante();
		
		le.setFecFin("20/11/2011");
		le.setFecInicio("20/11/2011");
		le.setNumDestino("4312743");
		le.setNumOrigen("986625066");
		le.setValDuracion("2");
		lstLlamadaEntrante.add(le);*/
		
		return lstLlamadaEntrante;
	}

}
