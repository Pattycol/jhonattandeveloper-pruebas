package pe.com.claro.caef.web.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ActualizarDatosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ActualizaCliente;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ContactoCliente;
import pe.com.claro.caef.web.beans.ServicioCliente;
import pe.com.claro.caef.web.beans.SucursalCliente;
import pe.com.claro.caef.web.services.ActualizaClienteService;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.caef.web.ws.TransaccionClientesWS;

@Service("actualizaClienteService")
public class ActualizaClienteServiceImpl implements ActualizaClienteService {

	@Autowired
	private TransaccionClientesWS transaccionClientesWS;
	
	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	static final Logger log = Logger.getLogger(ActualizaClienteServiceImpl.class);
	
	public ConsultaCliente getActualizarDatosCliente(Usuario usuario,
			ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter) {
		
		consultarDatosGenClienteFilter = new ConsultarDatosGenClienteFilter();
		consultarDatosGenClienteFilter.setCodTipoDocumento(usuario.getCodTipoDocumento());
		consultarDatosGenClienteFilter.setNumDocumento(usuario.getNumDocumento());
		
		ConsultaCliente ccWS = new ConsultaCliente();
		ccWS = consultaCliente.consultarDatosGenCliente(usuario, consultarDatosGenClienteFilter);
		
		return ccWS;
	}
	
	public AuditTypes actualizarDatosCliente(Usuario usuario, ConsultaCliente consultaCliente)
	{
		ActualizarDatosClienteFilter actualizarDatosClienteFilter = new ActualizarDatosClienteFilter();
		
		actualizarDatosClienteFilter.setCodCliente(consultaCliente.getCodCliente());
		actualizarDatosClienteFilter.setCodEstadoCivil(consultaCliente.getCodEstadoCivil());
		actualizarDatosClienteFilter.setCodGenero(consultaCliente.getCodGenero());
		actualizarDatosClienteFilter.setCodNacionalidad(consultaCliente.getCodNacionalidad());
		actualizarDatosClienteFilter.setCodProducto(usuario.getCodigoProducto());
		actualizarDatosClienteFilter.setCodServicio(usuario.getCodigoServicio());
		actualizarDatosClienteFilter.setCodTipoDocumento(usuario.getCodTipoDocumento());
		actualizarDatosClienteFilter.setCodTipoDomicilio(consultaCliente.getCodTipoDomicilio());
		actualizarDatosClienteFilter.setDesDomicilio(consultaCliente.getDesDomicilio());
		actualizarDatosClienteFilter.setFecNacimiento(consultaCliente.getFecNacimiento());
		actualizarDatosClienteFilter.setNomCargoEsp(consultaCliente.getNomCargoEsp());
		actualizarDatosClienteFilter.setNumDocumento(usuario.getNumDocumento());
		//faltan mandar los campos nomvia
		//faltan mandar los campos codtipovia
		actualizarDatosClienteFilter.setNumVia(consultaCliente.getNumVia());
		actualizarDatosClienteFilter.setValCorreoElectronico(consultaCliente.getValCorreoElectronico());
		actualizarDatosClienteFilter.setValLote(consultaCliente.getValLote());
		actualizarDatosClienteFilter.setValManzana(consultaCliente.getValManzana());
		actualizarDatosClienteFilter.setValPiso(consultaCliente.getValPiso());
		actualizarDatosClienteFilter.setValReferencia(consultaCliente.getValReferencia());
		actualizarDatosClienteFilter.setValSector(consultaCliente.getValSector());

		AuditTypes at = new AuditTypes();
		
		at = transaccionClientesWS.actualizarDatosCliente(usuario, actualizarDatosClienteFilter);
		
		return at;
		

	}

}
