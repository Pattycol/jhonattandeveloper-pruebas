package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.action.filter.ConsultarConsumoClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarInstanciaServicioFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumTelefonicoAbonadoFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.ObtenerUrlTrafficViewFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaServicioClienteTotal;
import pe.com.claro.caef.web.beans.ConsultarInstanciaServicio;
import pe.com.claro.caef.web.beans.ConsultarNumTelefonicoAbonado;
import pe.com.claro.caef.web.beans.ContactoCliente;
import pe.com.claro.caef.web.beans.EstadoCuenta;
import pe.com.claro.caef.web.beans.ServicioCliente;
import pe.com.claro.caef.web.beans.SucursalCliente;
import pe.com.claro.caef.web.beans.TrafficView;
import pe.com.claro.caef.web.beans.UrlTrafficView;
import pe.com.claro.caef.web.services.impl.ConsultaClienteServiceImpl;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultaClientes;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultaClientes_Service;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarConsumoCliente;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarContactosCliente;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarDatosMaestros;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarInstanciaServicio;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarListTrafficView;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarNumTelefonicoAbonado;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarNumerosTelefonicos;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarServiciosCliente;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarServiciosClienteTotal;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarSucursalCliente;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ObjectFactory;

@Component
public class ConsultarClienteWSImpl implements ConsultarClienteWS {

	private ConsultaClientes port;
	private ConsultaClientes_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(ConsultarClienteWSImpl.class);
	
	public ConsultarClienteWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_CONSULTA_CLIENTE);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
        cs = new ConsultaClientes_Service(url);
        port = cs.getConsultaClientesSOAP();  
	}
	
	public List<ContactoCliente> consultarContactosCliente(Usuario usuario)
	{
		 pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarContactosClienteRequest _consultarContactosCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarContactosClienteRequest();
	        ObjectFactory of = new ObjectFactory();
	        
        javax.xml.bind.JAXBElement<java.lang.String> _consultarContactosCliente_parametersCodigoCliente = of.createConsultarContactosClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarContactosCliente_parameters.setCodigoCliente(_consultarContactosCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarContactosCliente_parametersCodigoProducto = of.createConsultarContactosClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarContactosCliente_parameters.setCodigoProducto(_consultarContactosCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarContactosCliente_parametersCodigoServicio = of.createConsultarContactosClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarContactosCliente_parameters.setCodigoServicio(_consultarContactosCliente_parametersCodigoServicio);
        _consultarContactosCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarContactosCliente_parameters.setIpApp(idaplicacion);
        _consultarContactosCliente_parameters.setUsrApp(ma.getIP());
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarContactosClienteResponse _consultarContactosCliente__return = port.consultarContactosCliente(_consultarContactosCliente_parameters);
       
        //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarContactosCliente con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarContactosCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarContactosCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarContactosCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
         
        if( _consultarContactosCliente__return.getListaConsultarContactosCliente().size() > 0)
        {
        	List<ContactoCliente> lstContacto = new ArrayList<ContactoCliente>();
        	for(ListaConsultarContactosCliente lc : _consultarContactosCliente__return.getListaConsultarContactosCliente())
        	{
        		ContactoCliente cc = new ContactoCliente();
        		cc.setCodigoContacto(lc.getCodigoContacto()!=null?lc.getCodigoContacto().toString():"");
        		cc.setNombreContacto(lc.getNombreContacto()!=null?lc.getNombreContacto().toString():"");
        		cc.setNumDocumentoIden(lc.getNumeroDocumentoIdentidad()!=null?lc.getNumeroDocumentoIdentidad().toString():"");
        		cc.setDesDocumentoIden(lc.getDescripcionDocumentoIdentidad()!=null?lc.getDescripcionDocumentoIdentidad():"");
        		cc.setTipDocumentoIden(lc.getTipoDocumentoIdentidad()!=null?lc.getTipoDocumentoIdentidad():"");
        		
        		lstContacto.add(cc);
        	}
        	
        	return lstContacto;
        	
        }else{
        	return new ArrayList<ContactoCliente>();
        }
	}
	
	public List<SucursalCliente> consultarSucursalCliente(Usuario usuario)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarSucursalClienteRequest _consultarSucursalCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarSucursalClienteRequest();
		
		ObjectFactory of = new ObjectFactory();
		
        javax.xml.bind.JAXBElement<java.lang.String> _consultarSucursalCliente_parametersCodigoCliente = of.createConsultarSucursalClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarSucursalCliente_parameters.setCodigoCliente(_consultarSucursalCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarSucursalCliente_parametersCodigoProducto = of.createConsultarSucursalClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarSucursalCliente_parameters.setCodigoProducto(_consultarSucursalCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarSucursalCliente_parametersCodigoServicio = of.createConsultarSucursalClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarSucursalCliente_parameters.setCodigoServicio(_consultarSucursalCliente_parametersCodigoServicio);
        _consultarSucursalCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarSucursalCliente_parameters.setIpApp(idaplicacion);
        _consultarSucursalCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarSucursalClienteResponse _consultarSucursalCliente__return = port.consultarSucursalCliente(_consultarSucursalCliente_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarSucursalCliente con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarSucursalCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarSucursalCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarSucursalCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarSucursalCliente__return.getListaConsultarSucursalCliente().size() > 0)
        {
        	List<SucursalCliente> lstSucursal = new ArrayList<SucursalCliente>();
        	
        	for(ListaConsultarSucursalCliente lc : _consultarSucursalCliente__return.getListaConsultarSucursalCliente())
        	{
        		SucursalCliente sc = new SucursalCliente();
        		sc.setDirSucursal(lc.getDireccionSucursal()!=null?lc.getDireccionSucursal().toString():"");
        		sc.setFlgFacturacion(lc.getFlagFacturacion()!=null?lc.getFlagFacturacion().toString():"");
        		sc.setNombreDistrito(lc.getNombreDistrito()!=null?lc.getNombreDistrito().toString():"");
        		sc.setNombreProvincia(lc.getNombreProvincia()!=null?lc.getNombreProvincia().toString():"");
        		sc.setNombreSucursal(lc.getNombreSucursal()!=null?lc.getNombreSucursal().toString():"");
        		sc.setUbiSucursal(lc.getUbicacionSucursal()!=null?lc.getUbicacionSucursal().toString():"");        		
        		lstSucursal.add(sc);
        	}
        	
        	return lstSucursal;
        }
        else{
        	return new ArrayList<SucursalCliente>();
        }
	}
	
	public List<ServicioCliente> consultarServicioCliente(Usuario usuario)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarServiciosClienteRequest _consultarServiciosCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarServiciosClienteRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarServiciosCliente_parametersCodigoCliente = of.createConsultarServiciosClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarServiciosCliente_parameters.setCodigoCliente(_consultarServiciosCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarServiciosCliente_parametersCodigoProducto = of.createConsultarServiciosClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarServiciosCliente_parameters.setCodigoProducto(_consultarServiciosCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarServiciosCliente_parametersCodigoServicio = of.createConsultarServiciosClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarServiciosCliente_parameters.setCodigoServicio(_consultarServiciosCliente_parametersCodigoServicio);
        _consultarServiciosCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarServiciosCliente_parameters.setIpApp(idaplicacion);
        _consultarServiciosCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarServiciosClienteResponse _consultarServiciosCliente__return = port.consultarServiciosCliente(_consultarServiciosCliente_parameters);
		
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarServicioCliente con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarServiciosCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarServiciosCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarServiciosCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarServiciosCliente__return.getListaConsultarServiciosCliente().size() > 0)
        {
        	List<ServicioCliente> lstServicio = new ArrayList<ServicioCliente>();
        	
        	for(ListaConsultarServiciosCliente lc : _consultarServiciosCliente__return.getListaConsultarServiciosCliente())
        	{
        		ServicioCliente sc = new ServicioCliente();
        		sc.setDesEstado(lc.getDescripcionEstado()!=null?lc.getDescripcionEstado().toString():"");
        		sc.setDesProducto(lc.getDescripcionProducto()!=null?lc.getDescripcionProducto().toString():"");
        		sc.setDesServicio(lc.getDescripcionServicio()!=null?lc.getDescripcionServicio().toString():"");
        		sc.setDesTipoServicio(lc.getTipoServicio()!=null?lc.getTipoServicio().toString():"");
        		sc.setFecActivacion(lc.getFechaActivacion()!=null?lc.getFechaActivacion().toString():"");
        		sc.setNumServicio(lc.getNumeroServicio()!=null?lc.getNumeroServicio().toString():"");
        		lstServicio.add(sc);
        	}
        	
        	return lstServicio;
        }
        else{
        	return new ArrayList<ServicioCliente>();
        }
	}
	
	public List<ConsultaNumeroTelefonico> consultarNumerosTelefonicos(Usuario usuario, ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarNumerosTelefonicosRequest _consultarNumerosTelefonicos_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarNumerosTelefonicosRequest();
		
		ObjectFactory of = new ObjectFactory();
		
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumerosTelefonicos_parametersCodigoCliente = of.createConsultarNumerosTelefonicosRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarNumerosTelefonicos_parameters.setCodigoCliente(_consultarNumerosTelefonicos_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumerosTelefonicos_parametersCodigoProducto = of.createConsultarNumerosTelefonicosRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarNumerosTelefonicos_parameters.setCodigoProducto(_consultarNumerosTelefonicos_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumerosTelefonicos_parametersCodigoServicio = of.createConsultarNumerosTelefonicosRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarNumerosTelefonicos_parameters.setCodigoServicio(_consultarNumerosTelefonicos_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumerosTelefonicos_parametersCodigoEstado = of.createConsultarNumerosTelefonicosRequestCodigoEstado(consultarNumerosTelefonicosFilter.getCodEstado());
        _consultarNumerosTelefonicos_parameters.setCodigoEstado(_consultarNumerosTelefonicos_parametersCodigoEstado);
        _consultarNumerosTelefonicos_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarNumerosTelefonicos_parameters.setIpApp(idaplicacion);
        _consultarNumerosTelefonicos_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarNumerosTelefonicosResponse _consultarNumerosTelefonicos__return = port.consultarNumerosTelefonicos(_consultarNumerosTelefonicos_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarNumerosTelefonicos con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("codEstado : ").append(consultarNumerosTelefonicosFilter.getCodEstado()).append(",\n");
        sf.append("AuditCode : ").append(_consultarNumerosTelefonicos__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarNumerosTelefonicos__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarNumerosTelefonicos__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarNumerosTelefonicos__return.getListaConsultarNumerosTelefonicos().size() > 0)
        {
        	List<ConsultaNumeroTelefonico> lstNumeroTelefonico = new ArrayList<ConsultaNumeroTelefonico>();
        	
        	for(ListaConsultarNumerosTelefonicos lc : _consultarNumerosTelefonicos__return.getListaConsultarNumerosTelefonicos())
        	{
        		ConsultaNumeroTelefonico cnt = new ConsultaNumeroTelefonico();
        		cnt.setNumero(lc.getNumero()!=null?lc.getNumero().toString():"");
        		cnt.setCodigoInstanciaServicio(lc.getCodigoInstanciaServicio()!=null?lc.getCodigoInstanciaServicio().toString():"");
        		lstNumeroTelefonico.add(cnt);
        	}
        	
        	return lstNumeroTelefonico;
        }
        else{
        	return new ArrayList<ConsultaNumeroTelefonico>();
        }
	}
	
	public List<ConsultarNumTelefonicoAbonado> consultarNumTelefonicoAbonado(Usuario usuario, ConsultarNumTelefonicoAbonadoFilter consultarNumTelefonicoAbonadoFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarNumTelefonicoAbonadoRequest _consultarNumTelefonicoAbonado_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarNumTelefonicoAbonadoRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarNumTelefonicoAbonado_parametersCodigoCliente = of.createConsultarNumTelefonicoAbonadoRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarNumTelefonicoAbonado_parameters.setCodigoCliente(_consultarNumTelefonicoAbonado_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumTelefonicoAbonado_parametersCodigoProducto = of.createConsultarNumTelefonicoAbonadoRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarNumTelefonicoAbonado_parameters.setCodigoProducto(_consultarNumTelefonicoAbonado_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumTelefonicoAbonado_parametersCodigoServicio = of.createConsultarNumTelefonicoAbonadoRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarNumTelefonicoAbonado_parameters.setCodigoServicio(_consultarNumTelefonicoAbonado_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarNumTelefonicoAbonado_parametersCodigoEstado = of.createConsultarNumTelefonicoAbonadoRequestCodigoEstado(consultarNumTelefonicoAbonadoFilter.getCodigoEstado());
        _consultarNumTelefonicoAbonado_parameters.setCodigoEstado(_consultarNumTelefonicoAbonado_parametersCodigoEstado);
        _consultarNumTelefonicoAbonado_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarNumTelefonicoAbonado_parameters.setIpApp(idaplicacion);
        _consultarNumTelefonicoAbonado_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarNumTelefonicoAbonadoResponse _consultarNumTelefonicoAbonado__return = port.consultarNumTelefonicoAbonado(_consultarNumTelefonicoAbonado_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarNumTelefonicoAbonado con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("codEstado : ").append(consultarNumTelefonicoAbonadoFilter.getCodigoEstado()).append(",\n");
        sf.append("AuditCode : ").append(_consultarNumTelefonicoAbonado__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarNumTelefonicoAbonado__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarNumTelefonicoAbonado__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarNumTelefonicoAbonado__return.getListaConsultarNumTelefonicoAbonado().size() > 0)
        {
        	List<ConsultarNumTelefonicoAbonado> lstNumTelefonicoAbonado = new ArrayList<ConsultarNumTelefonicoAbonado>();
        	
        	for(ListaConsultarNumTelefonicoAbonado lc : _consultarNumTelefonicoAbonado__return.getListaConsultarNumTelefonicoAbonado())
        	{
        		ConsultarNumTelefonicoAbonado cnta = new ConsultarNumTelefonicoAbonado();
        		cnta.setNombre(lc.getNombre()!=null?lc.getNombre().toString():"");
        		cnta.setNumero(lc.getNumero()!=null?lc.getNumero().toString():"");
        		cnta.setPublicar(lc.getPublicar()!=null?lc.getPublicar().toString():"");
        		lstNumTelefonicoAbonado.add(cnta);
        	}
        	
        	return lstNumTelefonicoAbonado;
        }
        else{
        	return new ArrayList<ConsultarNumTelefonicoAbonado>();
        }
	}
	
	public List<ConsultaServicioClienteTotal> consultarServiciosClienteTotal(Usuario usuario)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarServiciosClienteTotalRequest _consultarServiciosClienteTotal_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarServiciosClienteTotalRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarServiciosClienteTotal_parametersCodigoCliente = of.createConsultarServiciosClienteTotalRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarServiciosClienteTotal_parameters.setCodigoCliente(_consultarServiciosClienteTotal_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarServiciosClienteTotal_parametersCodigoProducto = of.createConsultarServiciosClienteTotalRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarServiciosClienteTotal_parameters.setCodigoProducto(_consultarServiciosClienteTotal_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarServiciosClienteTotal_parametersCodigoServicio = of.createConsultarServiciosClienteTotalRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarServiciosClienteTotal_parameters.setCodigoServicio(_consultarServiciosClienteTotal_parametersCodigoServicio);
        _consultarServiciosClienteTotal_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarServiciosClienteTotal_parameters.setIpApp(idaplicacion);
        _consultarServiciosClienteTotal_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarServiciosClienteTotalResponse _consultarServiciosClienteTotal__return = port.consultarServiciosClienteTotal(_consultarServiciosClienteTotal_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarServiciosClienteTotal con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarServiciosClienteTotal__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarServiciosClienteTotal__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarServiciosClienteTotal__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarServiciosClienteTotal__return.getListaConsultarServiciosClienteTotal().size() > 0)
        {
        	List<ConsultaServicioClienteTotal> lstServicioClienteTotal = new ArrayList<ConsultaServicioClienteTotal>();
        	
        	for(ListaConsultarServiciosClienteTotal lc : _consultarServiciosClienteTotal__return.getListaConsultarServiciosClienteTotal())
        	{
        		ConsultaServicioClienteTotal csct = new ConsultaServicioClienteTotal();
        		csct.setIdProducto(lc.getIdProducto()!=null?lc.getIdProducto().toString():"");
        		csct.setDescripcionServicio(lc.getDescripcionServicio()!=null?lc.getDescripcionServicio().toString():"");
        		csct.setDescripcion(lc.getDescripcion()!=null?lc.getDescripcion().toString():"");
        		csct.setCodigoServicio(lc.getCodigoServicio()!=null?lc.getCodigoServicio().toString():"");
        		lstServicioClienteTotal.add(csct);
        	}
        	
        	return lstServicioClienteTotal;
        }
        else{
        	return new ArrayList<ConsultaServicioClienteTotal>();
        }
	}
	
	public List<ConsultarInstanciaServicio> consultarInstanciaServicio(Usuario usuario, ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarInstanciaServicioRequest _consultarInstanciaServicio_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarInstanciaServicioRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarInstanciaServicio_parametersCodigoCliente = of.createConsultarInstanciaServicioRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarInstanciaServicio_parameters.setCodigoCliente(_consultarInstanciaServicio_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarInstanciaServicio_parametersCodigoProducto = of.createConsultarInstanciaServicioRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarInstanciaServicio_parameters.setCodigoProducto(_consultarInstanciaServicio_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarInstanciaServicio_parametersCodigoServicio = of.createConsultarInstanciaServicioRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarInstanciaServicio_parameters.setCodigoServicio(_consultarInstanciaServicio_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarInstanciaServicio_parametersCodigoInstanciaServicio = of.createConsultarInstanciaServicioRequestCodigoInstanciaServicio(usuario.getCodigoInstanciaServicio());
        _consultarInstanciaServicio_parameters.setCodigoInstanciaServicio(_consultarInstanciaServicio_parametersCodigoInstanciaServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarInstanciaServicio_parametersCodigoEstado = of.createConsultarInstanciaServicioRequestCodigoEstado("1");//consultarInstanciaServicioFilter.getCodigoEstado());
        _consultarInstanciaServicio_parameters.setCodigoEstado(_consultarInstanciaServicio_parametersCodigoEstado);
        _consultarInstanciaServicio_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarInstanciaServicio_parameters.setIpApp(idaplicacion);
        _consultarInstanciaServicio_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarInstanciaServicioResponse _consultarInstanciaServicio__return = port.consultarInstanciaServicio(_consultarInstanciaServicio_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarInstanciaServicio con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append("codigo instancia servicio : ").append(usuario.getCodigoInstanciaServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarInstanciaServicio__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarInstanciaServicio__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarInstanciaServicio__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarInstanciaServicio__return.getListaConsultarInstanciaServicio().size() > 0)
        {
        	List<ConsultarInstanciaServicio> lstInstanciaServicio = new ArrayList<ConsultarInstanciaServicio>();
        	
        	for(ListaConsultarInstanciaServicio lc : _consultarInstanciaServicio__return.getListaConsultarInstanciaServicio())
        	{
        		ConsultarInstanciaServicio cis = new ConsultarInstanciaServicio();
        		cis.setCodigoInstanciaServicio(lc.getCodigoInstanciaServicio()!=null?lc.getCodigoInstanciaServicio().toString():"");
        		cis.setDireccion(lc.getDireccion()!=null?lc.getDireccion().toString():"");
        		cis.setNumero(lc.getNumero()!=null?lc.getNumero().toString():"");
        		lstInstanciaServicio.add(cis);
        	}
        	
        	return lstInstanciaServicio;
        }
        else{
        	return new ArrayList<ConsultarInstanciaServicio>();
        }
	}

	public List<TrafficView> consultarListTrafficView(Usuario usuario)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarListTrafficViewRequest _consultarListTrafficView_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarListTrafficViewRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarListTrafficView_parametersCodigoCliente = of.createConsultarListTrafficViewRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarListTrafficView_parameters.setCodigoCliente(_consultarListTrafficView_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListTrafficView_parametersCodigoProducto = of.createConsultarListTrafficViewRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarListTrafficView_parameters.setCodigoProducto(_consultarListTrafficView_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListTrafficView_parametersCodigoServicio = of.createConsultarListTrafficViewRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarListTrafficView_parameters.setCodigoServicio(_consultarListTrafficView_parametersCodigoServicio);
        _consultarListTrafficView_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarListTrafficView_parameters.setIpApp(idaplicacion);
        _consultarListTrafficView_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarListTrafficViewResponse _consultarListTrafficView__return = port.consultarListTrafficView(_consultarListTrafficView_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarListTrafficView con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarListTrafficView__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarListTrafficView__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarListTrafficView__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarListTrafficView__return.getListaConsultarListTrafficView().size() > 0)
        {
        	List<TrafficView> lstTrafficView = new ArrayList<TrafficView>();
        	
        	for(ListaConsultarListTrafficView lc : _consultarListTrafficView__return.getListaConsultarListTrafficView())
        	{
        		TrafficView tv = new TrafficView();
        		tv.setCodCID(lc.getCodigoCid()!=null?lc.getCodigoCid().toString():"");
        		tv.setDescripcionEstadoCid(lc.getDescripcionEstadoCid()!=null?lc.getDescripcionEstadoCid().toString():"");
        		tv.setUrlTraffic(lc.getDescripcionEstadoCid()!=null?lc.getDescripcionEstadoCid().toString():"");//en el campo estado cargamos la url
        		tv.setFecInst(Constantes.convertirFecha(lc.getFechaInstalacion()!=null?lc.getFechaInstalacion().toString():""));
        		tv.setNomDirecSucursal(lc.getDireccionSucursal()!=null?lc.getDireccionSucursal().toString():"");
        		tv.setNomProducto(lc.getDescripcionProducto()!=null?lc.getDescripcionProducto().toString():"");
        		tv.setNomSucursal(lc.getDescripcionSucursal()!=null?lc.getDescripcionSucursal().toString():"");
        		lstTrafficView.add(tv);
        	}
        	
        	return lstTrafficView;
        }
        else{
        	return new ArrayList<TrafficView>();
        }
	}
	
	public List<ConsultaDatosMaestro> consultarDatosMaestros(Usuario usuario, ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarDatosMaestrosRequest _consultarDatosMaestros_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarDatosMaestrosRequest();
        
		ObjectFactory of = new ObjectFactory();
		StringBuffer sf = new StringBuffer();
	    sf.append("Invocando el servicio web CONSULTACLIENTE : consultarDatosMaestros con los Parametros : \n [" );
	        
		javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersCodigoCaso = of.createConsultarDatosMaestrosRequestCodigoCaso(consultarDatosMaestrosFilter.getCodigoCaso());
        _consultarDatosMaestros_parameters.setCodigoCaso(_consultarDatosMaestros_parametersCodigoCaso);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersCodigoEstado = of.createConsultarDatosMaestrosRequestCodigoEstado(consultarDatosMaestrosFilter.getCodigoEstado());
        _consultarDatosMaestros_parameters.setCodigoEstado(_consultarDatosMaestros_parametersCodigoEstado);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersParametro1 = of.createConsultarDatosMaestrosRequestParametro1(consultarDatosMaestrosFilter.getParametro1());
        _consultarDatosMaestros_parameters.setParametro1(_consultarDatosMaestros_parametersParametro1);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersParametro2 = of.createConsultarDatosMaestrosRequestParametro2(consultarDatosMaestrosFilter.getParametro2());
        _consultarDatosMaestros_parameters.setParametro2(_consultarDatosMaestros_parametersParametro2);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersParametro3 = of.createConsultarDatosMaestrosRequestParametro3(consultarDatosMaestrosFilter.getParametro3());;
        _consultarDatosMaestros_parameters.setParametro3(_consultarDatosMaestros_parametersParametro3);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersParametro4 = of.createConsultarDatosMaestrosRequestParametro4(consultarDatosMaestrosFilter.getParametro4());;
        _consultarDatosMaestros_parameters.setParametro4(_consultarDatosMaestros_parametersParametro4);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosMaestros_parametersParametro5 = of.createConsultarDatosMaestrosRequestParametro5(consultarDatosMaestrosFilter.getParametro5());;
        _consultarDatosMaestros_parameters.setParametro5(_consultarDatosMaestros_parametersParametro5);
        _consultarDatosMaestros_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarDatosMaestros_parameters.setIpApp(idaplicacion);
        _consultarDatosMaestros_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarDatosMaestrosResponse _consultarDatosMaestros__return = port.consultarDatosMaestros(_consultarDatosMaestros_parameters);
        
      //LOG WEB SERVICE
       
        sf.append("Codigo caso : ").append(consultarDatosMaestrosFilter.getCodigoCaso()).append(",\n");
        sf.append("Codigo estado : ").append(consultarDatosMaestrosFilter.getCodigoEstado()).append(",\n");
        sf.append("Parametro1 : ").append(consultarDatosMaestrosFilter.getParametro1()).append(",\n");
        sf.append("Parametro2 : ").append(consultarDatosMaestrosFilter.getParametro2()).append(",\n");
        sf.append("Parametro3 : ").append(consultarDatosMaestrosFilter.getParametro3()).append(",\n");
        sf.append("Parametro4 : ").append(consultarDatosMaestrosFilter.getParametro4()).append(",\n");
        sf.append("Parametro5 : ").append(consultarDatosMaestrosFilter.getParametro5()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarDatosMaestros__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarDatosMaestros__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarDatosMaestros__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarDatosMaestros__return.getListaConsultarDatosMaestros().size() > 0)
        {
        	ArrayList<ConsultaDatosMaestro> lstDatosMestros = new ArrayList<ConsultaDatosMaestro>();
        	
        	for(int i=0; i < _consultarDatosMaestros__return.getListaConsultarDatosMaestros().size(); i++)
        	{
        		ConsultaDatosMaestro cdm = new ConsultaDatosMaestro();
        		cdm.setValorDatoMaestro1(_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro1()!=null?_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro1().toString().toUpperCase():"");
        		cdm.setValorDatoMaestro2(_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro2()!=null?_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro2().toString().toUpperCase():"");
        		cdm.setValorDatoMaestro3(_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro3()!=null?_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro3().toString().toUpperCase():"");
        		cdm.setValorDatoMaestro4(_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro4()!=null?_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro4().toString().toUpperCase():"");
        		cdm.setValorDatoMaestro5(_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro5()!=null?_consultarDatosMaestros__return.getListaConsultarDatosMaestros().get(i).getValorDatoMaestro5().toString().toUpperCase():"");
        		lstDatosMestros.add(cdm);

        	}
        	
        	
        	
        	/*for(ListaConsultarDatosMaestros lc : _consultarDatosMaestros__return.getListaConsultarDatosMaestros())
        	{
        		ConsultaDatosMaestro cdm = new ConsultaDatosMaestro();
        		cdm.setValorDatoMaestro1(lc.getValorDatoMaestro1());
        		cdm.setValorDatoMaestro2(lc.getValorDatoMaestro2());
        		cdm.setValorDatoMaestro3(lc.getValorDatoMaestro3());
        		cdm.setValorDatoMaestro4(lc.getValorDatoMaestro4());
        		cdm.setValorDatoMaestro5(lc.getValorDatoMaestro5());
        		lstDatosMestros.add(cdm);
        	}*/
        	
        	return lstDatosMestros;
        }
        else{
        	return new ArrayList<ConsultaDatosMaestro>();
        }
	}
	
	public UrlTrafficView obtenerUrlTrafficView(Usuario usuario, ObtenerUrlTrafficViewFilter obtenerUrlTrafficViewFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ObtenerUrlTrafficViewRequest _obtenerUrlTrafficView_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ObtenerUrlTrafficViewRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _obtenerUrlTrafficView_parametersCodigoCid = of.createObtenerUrlTrafficViewRequestCodigoCid(obtenerUrlTrafficViewFilter.getCodCID());
        _obtenerUrlTrafficView_parameters.setCodigoCid(_obtenerUrlTrafficView_parametersCodigoCid);
        _obtenerUrlTrafficView_parameters.setIdTransaccion(ma.IdTransaccion());
        _obtenerUrlTrafficView_parameters.setIpApp(idaplicacion);
        _obtenerUrlTrafficView_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ObtenerUrlTrafficViewResponse _obtenerUrlTrafficView__return = port.obtenerUrlTrafficView(_obtenerUrlTrafficView_parameters);
		
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarListTrafficView con los Parametros : \n [" );
        sf.append("codigo cid : ").append(obtenerUrlTrafficViewFilter.getCodCID()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_obtenerUrlTrafficView__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_obtenerUrlTrafficView__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_obtenerUrlTrafficView__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
		UrlTrafficView utv = new UrlTrafficView();
		
		utv.setUrlTrafficView(_obtenerUrlTrafficView__return.getUrlAplicacionTrafficView().toString());
		
		return utv;		
	}
	
	public List<ConsultaConsumo> consultarConsumoCliente(Usuario usuario, ConsultarConsumoClienteFilter consultarConsumoClienteFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarConsumoClienteRequest _consultarConsumoCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarConsumoClienteRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarConsumoCliente_parametersCodigoCliente = of.createConsultarConsumoClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarConsumoCliente_parameters.setCodigoCliente(_consultarConsumoCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarConsumoCliente_parametersCodigoProducto = of.createConsultarConsumoClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarConsumoCliente_parameters.setCodigoProducto(_consultarConsumoCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarConsumoCliente_parametersCodigoServicio = of.createConsultarConsumoClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarConsumoCliente_parameters.setCodigoServicio(_consultarConsumoCliente_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarConsumoCliente_parametersNumeroTelefonico = of.createConsultarConsumoClienteRequestNumeroTelefonico(consultarConsumoClienteFilter.getNumeroTelefonico());
        _consultarConsumoCliente_parameters.setNumeroTelefonico(_consultarConsumoCliente_parametersNumeroTelefonico);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarConsumoCliente_parametersFechaInicio = of.createConsultarConsumoClienteRequestFechaInicio(null/*consultarConsumoClienteFilter.getFecInicio()*/);
        _consultarConsumoCliente_parameters.setFechaInicio(_consultarConsumoCliente_parametersFechaInicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarConsumoCliente_parametersFechaFin = of.createConsultarConsumoClienteRequestFechaFin(null/*consultarConsumoClienteFilter.getFecFin()*/);
        _consultarConsumoCliente_parameters.setFechaFin(_consultarConsumoCliente_parametersFechaFin);
        _consultarConsumoCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarConsumoCliente_parameters.setIpApp(idaplicacion);
        _consultarConsumoCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarConsumoClienteResponse _consultarConsumoCliente__return = port.consultarConsumoCliente(_consultarConsumoCliente_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarListTrafficView con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append("numero telefonico : ").append(consultarConsumoClienteFilter.getNumeroTelefonico()).append(",\n");
        sf.append("Fecha inicio : ").append(consultarConsumoClienteFilter.getFecInicio()).append(",\n");
        sf.append("Fecha fin : ").append(consultarConsumoClienteFilter.getFecFin()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarConsumoCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarConsumoCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarConsumoCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarConsumoCliente__return.getListaConsultarConsumoCliente().size() > 0)
        {
    		List<ConsultaConsumo> lstConsultaConsumo = new ArrayList<ConsultaConsumo>();
    		
        	for(ListaConsultarConsumoCliente lc : _consultarConsumoCliente__return.getListaConsultarConsumoCliente())
        	{
        		ConsultaConsumo cc = new ConsultaConsumo();
        		cc.setDesTipServicio(lc.getNombreTipoServicio()!=null?lc.getNombreTipoServicio().toString():"");
        		cc.setDesProducto(lc.getNombreProducto()!=null?lc.getNombreProducto().toString():"");
        		cc.setCodTipLlamada(lc.getCodigoTipoLlamada()!=null?lc.getCodigoTipoLlamada().toString():"");
        		cc.setDesTipLlamada(lc.getDescripcionTipoLlamada()!=null?lc.getDescripcionTipoLlamada().toString():"");
        		cc.setValConsumo(lc.getConsumoAcumulado()!=null?lc.getConsumoAcumulado().toString():"");
        		lstConsultaConsumo.add(cc);
        	}
    		
    		return lstConsultaConsumo;
        }
        else
        {
        	return new ArrayList<ConsultaConsumo>();
        }	

	}
	
	public EstadoCuenta consultarCabeceraEstadoCuenta(Usuario usuario)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarCabeceraEstadoCuentaRequest _consultarCabeceraEstadoCuenta_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarCabeceraEstadoCuentaRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarCabeceraEstadoCuenta_parametersCodigoCliente = of.createConsultarCabeceraEstadoCuentaRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarCabeceraEstadoCuenta_parameters.setCodigoCliente(_consultarCabeceraEstadoCuenta_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarCabeceraEstadoCuenta_parametersCodigoProducto = of.createConsultarCabeceraEstadoCuentaRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarCabeceraEstadoCuenta_parameters.setCodigoProducto(_consultarCabeceraEstadoCuenta_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarCabeceraEstadoCuenta_parametersCodigoServicio = of.createConsultarCabeceraEstadoCuentaRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarCabeceraEstadoCuenta_parameters.setCodigoServicio(_consultarCabeceraEstadoCuenta_parametersCodigoServicio);
        _consultarCabeceraEstadoCuenta_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarCabeceraEstadoCuenta_parameters.setIpApp(idaplicacion);
        _consultarCabeceraEstadoCuenta_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarCabeceraEstadoCuentaResponse _consultarCabeceraEstadoCuenta__return = port.consultarCabeceraEstadoCuenta(_consultarCabeceraEstadoCuenta_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarListTrafficView con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarCabeceraEstadoCuenta__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarCabeceraEstadoCuenta__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarCabeceraEstadoCuenta__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
		EstadoCuenta ec= new EstadoCuenta();
		
		ec.setValDeudaActual(_consultarCabeceraEstadoCuenta__return.getDeudaActualCliente()!=null?_consultarCabeceraEstadoCuenta__return.getDeudaActualCliente().getValue():"");
		ec.setValDeudaVencida(_consultarCabeceraEstadoCuenta__return.getDeudaVencidaCliente()!=null?_consultarCabeceraEstadoCuenta__return.getDeudaVencidaCliente().getValue():"");
		ec.setValMontoDisputa(_consultarCabeceraEstadoCuenta__return.getMontoReclamoCliente()!=null?_consultarCabeceraEstadoCuenta__return.getMontoReclamoCliente().getValue():"");
		ec.setFecEmision(_consultarCabeceraEstadoCuenta__return.getFechaEmisionFactura()!=null?_consultarCabeceraEstadoCuenta__return.getFechaEmisionFactura().getValue():"");
		ec.setFecVencimiento(_consultarCabeceraEstadoCuenta__return.getFechaVencimientoFactura()!=null?_consultarCabeceraEstadoCuenta__return.getFechaVencimientoFactura().getValue():"");
		
		return ec;
	}
	
	public ConsultaCliente consultarDatosGenCliente(Usuario usuario, ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarDatosGenClienteRequest _consultarDatosGenCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarDatosGenClienteRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosGenCliente_parametersCodigoCliente = of.createConsultarDatosGenClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarDatosGenCliente_parameters.setCodigoCliente(_consultarDatosGenCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosGenCliente_parametersCodigoProducto = of.createConsultarDatosGenClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarDatosGenCliente_parameters.setCodigoProducto(_consultarDatosGenCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosGenCliente_parametersCodigoServicio = of.createConsultarDatosGenClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarDatosGenCliente_parameters.setCodigoServicio(_consultarDatosGenCliente_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosGenCliente_parametersTipoDocumento = of.createConsultarDatosGenClienteRequestTipoDocumento(consultarDatosGenClienteFilter.getCodTipoDocumento());
        _consultarDatosGenCliente_parameters.setTipoDocumento(_consultarDatosGenCliente_parametersTipoDocumento);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDatosGenCliente_parametersNumeroDocumento = of.createConsultarDatosGenClienteRequestNumeroDocumento(consultarDatosGenClienteFilter.getNumDocumento());
        _consultarDatosGenCliente_parameters.setNumeroDocumento(_consultarDatosGenCliente_parametersNumeroDocumento);
        _consultarDatosGenCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarDatosGenCliente_parameters.setIpApp(idaplicacion);
        _consultarDatosGenCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultaclientes.ConsultarDatosGenClienteResponse _consultarDatosGenCliente__return = port.consultarDatosGenCliente(_consultarDatosGenCliente_parameters);        
		
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTACLIENTE : consultarDatosGenCliente con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append("tipo documento : ").append(consultarDatosGenClienteFilter.getCodTipoDocumento()).append(",\n");
        sf.append("numero documento : ").append(consultarDatosGenClienteFilter.getNumDocumento()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarDatosGenCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarDatosGenCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarDatosGenCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
		ConsultaCliente cc = new ConsultaCliente();
		
		/*DATOS GENRALES DEL CLIENTE*/
		cc.setCodCliente(_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoCliente()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoCliente().toString().toUpperCase():"");
		cc.setNomCliente(_consultarDatosGenCliente__return.getListaCliente().get(0).getNombre()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getNombre().toString().toUpperCase():"");
		cc.setNomClientePila(_consultarDatosGenCliente__return.getListaCliente().get(0).getNombrePila()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getNombrePila().toString().toUpperCase():"");
		cc.setNomApePaterno(_consultarDatosGenCliente__return.getListaCliente().get(0).getApellidoPaterno()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getApellidoPaterno().toString().toUpperCase():"");
		cc.setNomApeMaterno(_consultarDatosGenCliente__return.getListaCliente().get(0).getApellidoMaterno()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getApellidoMaterno().toString().toUpperCase():"");
		cc.setCodTipoDocumento(_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoTipoDocumento()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoTipoDocumento().toUpperCase().toString():"");
		cc.setNumDocumento(_consultarDatosGenCliente__return.getListaCliente().get(0).getNumeroDocumento()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getNumeroDocumento().toString().toUpperCase():"");
		cc.setFecNacimiento(_consultarDatosGenCliente__return.getListaCliente().get(0).getFechaNacimiento()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getFechaNacimiento().toString().toUpperCase():"");
		cc.setValCorreoElectronico(_consultarDatosGenCliente__return.getListaCliente().get(0).getCorreoElectronico()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getCorreoElectronico().toString().toUpperCase():"");
		cc.setCodEstadoCivil(_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoEstadoCivil()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoEstadoCivil().toString().trim().toUpperCase():"");
		cc.setNomCargoEsp(_consultarDatosGenCliente__return.getListaCliente().get(0).getCargoEspecifico()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getCargoEspecifico().toString().toUpperCase():"");
		cc.setCodGenero(_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoGenero()!=null?_consultarDatosGenCliente__return.getListaCliente().get(0).getCodigoGenero().toString().toUpperCase():"");
		
		/*DATOS DE DIRECCION DEL CLIENTE*/
		cc.setCodTipoVia(_consultarDatosGenCliente__return.getListaDireccion().get(0).getCodigoTipoVia()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getCodigoTipoVia().toString():"");
		cc.setNomVia(_consultarDatosGenCliente__return.getListaDireccion().get(0).getNombreVia()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getNombreVia().toString():"");
		cc.setNumVia(_consultarDatosGenCliente__return.getListaDireccion().get(0).getNumeroVia()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getNumeroVia().toString():"");
		cc.setCodTipoDomicilio(_consultarDatosGenCliente__return.getListaDireccion().get(0).getCodigoTipoDomicilio()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getCodigoTipoDomicilio().toString():"");
		cc.setDesDomicilio(_consultarDatosGenCliente__return.getListaDireccion().get(0).getDescripcionDomicilio()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getDescripcionDomicilio().toString():"");
		cc.setValPiso(_consultarDatosGenCliente__return.getListaDireccion().get(0).getPiso()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getPiso().toString():"");
		cc.setValManzana(_consultarDatosGenCliente__return.getListaDireccion().get(0).getManzana()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getManzana().toString():"");
		cc.setValSector(_consultarDatosGenCliente__return.getListaDireccion().get(0).getSector()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getSector().toString():"");
		cc.setNomUrbanizacion(_consultarDatosGenCliente__return.getListaDireccion().get(0).getNombreUrbanizacion()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getNombreUrbanizacion().toString():"");
		cc.setValReferencia(_consultarDatosGenCliente__return.getListaDireccion().get(0).getReferencia()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getReferencia().toString():"");
		cc.setValLote(_consultarDatosGenCliente__return.getListaDireccion().get(0).getLote()!=null?_consultarDatosGenCliente__return.getListaDireccion().get(0).getLote().toString():"");
		
		/*DATOS DE UBIGEO DEL CLIENTE*/
		cc.setCodPais(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoPais()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoPais().toString():"");
		cc.setNomPais(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombrePais()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombrePais().toString():"");
		cc.setCodDepartamento(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoDepartamento()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoDepartamento().toString():"");
		cc.setNomDepartamento(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombreDepartamento()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombreDepartamento().toString():"");
		cc.setCodProvincia(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoProvincia()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoProvincia().toString():"");
		cc.setNomProvincia(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombreProvincia()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombreProvincia().toString():"");
		cc.setCodDisrito(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoDistrito()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getCodigoDistrito().toString():"");
		cc.setNomDistrito(_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombreDistrito()!=null?_consultarDatosGenCliente__return.getListaUbigeo().get(0).getNombreDistrito().toString():"");
		
		return cc;
	}
}
