package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarListRecargasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaRecarga;

public interface ConsultaRecargaService {

	public List<ConsultaRecarga> getConsultarListRecargas(Usuario usuario ,ConsultarListRecargasFilter consultarRecargaFilter);
}
