package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarConsumoClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.services.ConsultaConsumosService;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;

@Service("consultaConsumosService")
public class ConsultaConsumosServiceImpl implements ConsultaConsumosService {

	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	static final Logger log = Logger.getLogger(ConsultaConsumosServiceImpl.class);
	
	public List<ConsultaConsumo> getConsultarConsumoCliente(Usuario usuario,
			ConsultarConsumoClienteFilter consultarConsumoClienteFilter) {

		List<ConsultaConsumo> lstConsultaConsumo = new ArrayList<ConsultaConsumo>();
		
		/*consultarConsumoClienteFilter = new ConsultarConsumoClienteFilter();
		consultarConsumoClienteFilter.setNumeroTelefonico("16290161");
		consultarConsumoClienteFilter.setFecInicio("01/09/2011");
		consultarConsumoClienteFilter.setFecFin("30/09/2011");*/

		lstConsultaConsumo = consultaCliente.consultarConsumoCliente(usuario, consultarConsumoClienteFilter);
		
		return lstConsultaConsumo;
	}

}
