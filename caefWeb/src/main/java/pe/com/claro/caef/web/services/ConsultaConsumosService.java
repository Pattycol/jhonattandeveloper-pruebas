package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarConsumoClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaConsumo;

public interface ConsultaConsumosService {
	
	public List<ConsultaConsumo> getConsultarConsumoCliente(Usuario usuario, ConsultarConsumoClienteFilter consultarConsumoClienteFilter);

}
