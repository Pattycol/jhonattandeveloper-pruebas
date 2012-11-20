package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarInstanciaServicioFilter;
import pe.com.claro.caef.web.action.filter.RegistrarIncidenciaFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultarInstanciaServicio;
import pe.com.claro.caef.web.beans.RegistrarIncidencia;

public interface RegistrarIncidenciaService {
	
	public List<ConsultarInstanciaServicio> consultarInstanciaServicio(Usuario usuario, ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter);
	public RegistrarIncidencia registrarIncidencia(Usuario usuario, RegistrarIncidenciaFilter registrarIncidenciaFilter);

}
