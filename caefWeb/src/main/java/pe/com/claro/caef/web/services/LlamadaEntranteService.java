package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasEntrantesFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.LlamadaEntrante;

public interface LlamadaEntranteService {
	
	public List<LlamadaEntrante> getConsultarListaLlamadasEntrantes(Usuario usuario, ConsultarListaLlamadasEntrantesFilter consultarListaLlamadasEntrantesFilter);

}
