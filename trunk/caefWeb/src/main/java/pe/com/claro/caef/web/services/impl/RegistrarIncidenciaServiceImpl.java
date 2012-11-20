package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarInstanciaServicioFilter;
import pe.com.claro.caef.web.action.filter.RegistrarIncidenciaFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultarInstanciaServicio;
import pe.com.claro.caef.web.beans.RegistrarIncidencia;
import pe.com.claro.caef.web.services.RegistrarIncidenciaService;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.caef.web.ws.TransaccionClientesWS;
@Service("registrarIncidenciaService")
public class RegistrarIncidenciaServiceImpl implements
		RegistrarIncidenciaService {
	
	@Autowired
	private ConsultarClienteWS consultaCliente;

	@Autowired
	private TransaccionClientesWS transaccionClientesWS;
	
	public List<ConsultarInstanciaServicio> consultarInstanciaServicio(
			Usuario usuario,
			ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter) {
		
		List<ConsultarInstanciaServicio> lstConsultarInstanciaServicio = new ArrayList<ConsultarInstanciaServicio>();
		//consultarInstanciaServicioFilter = new ConsultarInstanciaServicioFilter();
		lstConsultarInstanciaServicio = consultaCliente.consultarInstanciaServicio(usuario, consultarInstanciaServicioFilter);
		
		return lstConsultarInstanciaServicio;
	}
	
	public RegistrarIncidencia registrarIncidencia(Usuario usuario, RegistrarIncidenciaFilter registrarIncidenciaFilter)
	{
		RegistrarIncidencia re = new RegistrarIncidencia();
		
		re = transaccionClientesWS.registrarIncidencia(usuario, registrarIncidenciaFilter);
		
		return re;
	}

}
