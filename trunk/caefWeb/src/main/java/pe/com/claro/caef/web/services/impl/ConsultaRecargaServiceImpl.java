package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarListRecargasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.services.ConsultaRecargaService;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.ws.ConsultarLlamadasWS;

@Service("consultaRecargaService")
public class ConsultaRecargaServiceImpl implements ConsultaRecargaService {

	@Autowired
	private ConsultarLlamadasWS consultarLlamadasWS;
	
	public List<ConsultaRecarga> getConsultarListRecargas(Usuario usuario ,ConsultarListRecargasFilter consultarListRecargasFilter) {
		
		List<ConsultaRecarga> lstConsultaRecarga  =new ArrayList<ConsultaRecarga>();
		
		/*consultarListRecargasFilter = new ConsultarListRecargasFilter();
		
		consultarListRecargasFilter.setNumeroPagina("-1");*/
		/*consultarListRecargasFilter.setFecInicio("01/09/2011");
		consultarListRecargasFilter.setFecFin("30/09/2011");*/
		lstConsultaRecarga = consultarLlamadasWS.consultarListRecargas(usuario, consultarListRecargasFilter);
		
		return lstConsultaRecarga;
	}

}
