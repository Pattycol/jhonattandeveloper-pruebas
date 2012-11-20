package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarContactosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarServiciosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarSucursalClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ContactoCliente;
import pe.com.claro.caef.web.beans.ServicioCliente;
import pe.com.claro.caef.web.beans.SucursalCliente;
import pe.com.claro.caef.web.services.ConsultaClienteService;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.caef.web.ws.impl.ConsultarClienteWSImpl;
@Service("consultaClienteService")
public class ConsultaClienteServiceImpl implements ConsultaClienteService {

	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	public ConsultaCliente getConsultarDatosGenCliente(Usuario usuario,
			ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter) {
		// TODO Auto-generated method stub
		
		consultarDatosGenClienteFilter = new ConsultarDatosGenClienteFilter();
		consultarDatosGenClienteFilter.setCodTipoDocumento(usuario.getCodTipoDocumento());
		consultarDatosGenClienteFilter.setNumDocumento(usuario.getNumDocumento());
		
		ConsultaCliente cc = new ConsultaCliente();
		ConsultaCliente ccWS = new ConsultaCliente();
		ccWS = consultaCliente.consultarDatosGenCliente(usuario, consultarDatosGenClienteFilter);
		/*DATOS GENRALES DEL CLIENTE*/
		cc.setCodCliente(ccWS.getCodCliente());
		cc.setNomCliente(ccWS.getNomCliente());
		cc.setNomClientePila(ccWS.getNomClientePila());
		cc.setNomApePaterno(ccWS.getNomApePaterno());
		cc.setNomApeMaterno(ccWS.getNomApeMaterno());
		cc.setCodTipoDocumento(ccWS.getCodTipoDocumento());
		cc.setNumDocumento(ccWS.getNumDocumento());
		cc.setFecNacimiento(ccWS.getFecNacimiento());
		cc.setValCorreoElectronico(ccWS.getValCorreoElectronico());
		cc.setCodEstadoCivil(ccWS.getCodEstadoCivil());
		cc.setNomCargoEsp(ccWS.getNomCargoEsp());
		cc.setCodGenero(ccWS.getCodGenero());
		
		/*DATOS DE DIRECCION DEL CLIENTE*/
		cc.setCodTipoVia(ccWS.getCodTipoVia());
		cc.setNomVia(ccWS.getNomVia());
		cc.setNumVia(ccWS.getNumVia());
		cc.setCodTipoDomicilio(ccWS.getCodTipoDomicilio());
		cc.setDesDomicilio(ccWS.getDesDomicilio());
		cc.setValPiso(ccWS.getValPiso());
		cc.setValManzana(ccWS.getValManzana());
		cc.setValSector(ccWS.getValSector());
		cc.setNomUrbanizacion(ccWS.getNomUrbanizacion());
		cc.setValReferencia(ccWS.getValReferencia());
		cc.setValLote(ccWS.getValLote());
		
		/*DATOS DE UBIGEO DEL CLIENTE*/
		cc.setCodPais(ccWS.getCodPais());
		cc.setNomPais(ccWS.getNomPais());
		cc.setCodDepartamento(ccWS.getCodDepartamento());
		cc.setNomDepartamento(ccWS.getNomDepartamento());
		cc.setCodProvincia(ccWS.getCodProvincia());
		cc.setNomProvincia(ccWS.getNomProvincia());
		cc.setCodDisrito(ccWS.getCodDisrito());
		cc.setNomDistrito(ccWS.getNomDistrito());
		
		cc.setLstContactoCliente(consultaCliente.consultarContactosCliente(usuario));
		cc.setLstSucursalCliente(consultaCliente.consultarSucursalCliente(usuario));
		cc.setLstServicioCliente(consultaCliente.consultarServicioCliente(usuario));

		return cc;
	}
	
	public List<ConsultaNumeroTelefonico> consultarNumerosTelefonicos(Usuario usuario, ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter)
	{
		List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico = new ArrayList<ConsultaNumeroTelefonico>();
		
		lstConsultaNumeroTelefonico = consultaCliente.consultarNumerosTelefonicos(usuario, consultarNumerosTelefonicosFilter);
		
		return lstConsultaNumeroTelefonico;
	}
	
	

}
