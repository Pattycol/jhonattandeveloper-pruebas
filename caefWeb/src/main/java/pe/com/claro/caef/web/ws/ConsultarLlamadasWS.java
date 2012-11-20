package pe.com.claro.caef.web.ws;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarListRecargasFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasEntrantesFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasFacturadasFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasNoFacturadasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.beans.LlamadaEntrante;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;


public interface ConsultarLlamadasWS {

	/*OK*/public List<LlamadaNoFacturada> consultarListaLlamadasNofacturadas(Usuario usuario, ConsultarListaLlamadasNoFacturadasFilter consultarListaLlamadasNoFacturadasFilter);
	/*OK*/public List<LlamadaFacturada> consultarListaLlamadasFacturadas(Usuario usuario, ConsultarListaLlamadasFacturadasFilter consultarListaLlamadasFacturadasFilter);
	/*OK*/public List<ConsultaRecarga> consultarListRecargas(Usuario usuario, ConsultarListRecargasFilter consultarListRecargasFilter);
	/*OK*/public List<LlamadaEntrante> consultarListaLlamadasEntrantes(Usuario usuario, ConsultarListaLlamadasEntrantesFilter consultarListaLlamadasEntrantesFilter);
}
