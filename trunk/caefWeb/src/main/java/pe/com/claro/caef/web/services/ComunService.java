package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.Comun;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaServicioClienteTotal;


public interface ComunService {
	
	public List<ConsultaNumeroTelefonico> getConsultarNumerosTelefonicos(Usuario usuario, ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter);
	public List<ConsultaDatosMaestro> getConsultarDatosMaestros(Usuario usuario, ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter);
	public List<ConsultaServicioClienteTotal> ConsultarServiciosClienteTotal(Usuario usuario);

}
