package pe.com.claro.caef.web.ws.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import pe.com.claro.caef.web.action.filter.ActualizarDatosClienteFilter;
import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.action.filter.RegistrarIncidenciaFilter;
import pe.com.claro.caef.web.action.filter.RegistrarPublicacionDirectorioAbonadoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.RegistrarIncidencia;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.TransaccionClientesWS;
import pe.com.claro.eai.crmservices.fija.transaccionclientes.ObjectFactory;
import pe.com.claro.eai.crmservices.fija.transaccionclientes.TransaccionClientes;
import pe.com.claro.eai.crmservices.fija.transaccionclientes.TransaccionClientes_Service;

@Component
public class TransaccionClientesWSImpl implements TransaccionClientesWS {
	
	private TransaccionClientes port;
	private TransaccionClientes_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(TransaccionClientesWSImpl.class);
	
	public TransaccionClientesWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_TRANSACCION_CLIENTES);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new TransaccionClientes_Service(url);
		port = cs.getTransaccionClientesSOAP();
	}
	
	public void registrarActivacionReciboCorreoElectronico(Usuario usuario, RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronico)
	{
		pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarActivacionReciboCorreoElectronicoRequest _registrarActivacionReciboCorreoElectronico_parameters = new pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarActivacionReciboCorreoElectronicoRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _registrarActivacionReciboCorreoElectronico_parametersCodigoGrupo = of.createRegistrarActivacionReciboCorreoElectronicoRequestCodigoGrupo(registrarActivacionReciboCorreoElectronico.getCodGrupo());
        _registrarActivacionReciboCorreoElectronico_parameters.setCodigoGrupo(_registrarActivacionReciboCorreoElectronico_parametersCodigoGrupo);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarActivacionReciboCorreoElectronico_parametersCodigoCliente = of.createRegistrarActivacionReciboCorreoElectronicoRequestCodigoCliente(usuario.getCodigoUsuario());
        _registrarActivacionReciboCorreoElectronico_parameters.setCodigoCliente(_registrarActivacionReciboCorreoElectronico_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarActivacionReciboCorreoElectronico_parametersCorreoElectronico = of.createRegistrarActivacionReciboCorreoElectronicoRequestCorreoElectronico(registrarActivacionReciboCorreoElectronico.getValCorreoElectronico());
        _registrarActivacionReciboCorreoElectronico_parameters.setCorreoElectronico(_registrarActivacionReciboCorreoElectronico_parametersCorreoElectronico);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarActivacionReciboCorreoElectronico_parametersFlagActivo = of.createRegistrarActivacionReciboCorreoElectronicoRequestFlagActivo(registrarActivacionReciboCorreoElectronico.getFlgActivo());
        _registrarActivacionReciboCorreoElectronico_parameters.setFlagActivo(_registrarActivacionReciboCorreoElectronico_parametersFlagActivo);
        _registrarActivacionReciboCorreoElectronico_parameters.setIdTransaccion(ma.IdTransaccion());
        _registrarActivacionReciboCorreoElectronico_parameters.setIpApp(idaplicacion);
        _registrarActivacionReciboCorreoElectronico_parameters.setUsrApp(ma.getIP());
                
        pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarActivacionReciboCorreoElectronicoResponse _registrarActivacionReciboCorreoElectronico__return = port.registrarActivacionReciboCorreoElectronico(_registrarActivacionReciboCorreoElectronico_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION CLIENTES : registrarActivacionReciboCorreoElectronico con los Parametros : \n [" );
        sf.append("codigo grupo : ").append(_registrarActivacionReciboCorreoElectronico_parametersCodigoGrupo).append(",\n");
        sf.append("codigo usuario : ").append(_registrarActivacionReciboCorreoElectronico_parametersCodigoCliente).append(",\n");
        sf.append("correo electronico : ").append(_registrarActivacionReciboCorreoElectronico_parametersCorreoElectronico).append(",\n");
        sf.append("flag activo : ").append(_registrarActivacionReciboCorreoElectronico_parametersFlagActivo).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_registrarActivacionReciboCorreoElectronico__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_registrarActivacionReciboCorreoElectronico__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_registrarActivacionReciboCorreoElectronico__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
	}
	
	public AuditTypes actualizarDatosCliente(Usuario usuario, ActualizarDatosClienteFilter actualizarDatosClienteFilter)
	{
		pe.com.claro.eai.crmservices.fija.transaccionclientes.ActualizarDatosClienteRequest _actualizarDatosCliente_parameters = new pe.com.claro.eai.crmservices.fija.transaccionclientes.ActualizarDatosClienteRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoCliente = of.createActualizarDatosClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _actualizarDatosCliente_parameters.setCodigoCliente(_actualizarDatosCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoProducto = of.createActualizarDatosClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _actualizarDatosCliente_parameters.setCodigoProducto(_actualizarDatosCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoServicio = of.createActualizarDatosClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _actualizarDatosCliente_parameters.setCodigoServicio(_actualizarDatosCliente_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoTipoDocumento = of.createActualizarDatosClienteRequestCodigoTipoDocumento(actualizarDatosClienteFilter.getCodTipoDocumento());
        _actualizarDatosCliente_parameters.setCodigoTipoDocumento(_actualizarDatosCliente_parametersCodigoTipoDocumento);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersNumeroDocumento = of.createActualizarDatosClienteRequestNumeroDocumento(actualizarDatosClienteFilter.getNumDocumento());
        _actualizarDatosCliente_parameters.setNumeroDocumento(_actualizarDatosCliente_parametersNumeroDocumento);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCorreoElectronico = of.createActualizarDatosClienteRequestCorreoElectronico(actualizarDatosClienteFilter.getValCorreoElectronico());
        _actualizarDatosCliente_parameters.setCorreoElectronico(_actualizarDatosCliente_parametersCorreoElectronico);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersFechaNacimiento = of.createActualizarDatosClienteRequestFechaNacimiento(actualizarDatosClienteFilter.getFecNacimiento());
        _actualizarDatosCliente_parameters.setFechaNacimiento(_actualizarDatosCliente_parametersFechaNacimiento);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoEstadoCivil = of.createActualizarDatosClienteRequestCodigoEstadoCivil(actualizarDatosClienteFilter.getCodEstadoCivil());
        _actualizarDatosCliente_parameters.setCodigoEstadoCivil(_actualizarDatosCliente_parametersCodigoEstadoCivil);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCargoEspecifico = of.createActualizarDatosClienteRequestCargoEspecifico(actualizarDatosClienteFilter.getNomCargoEsp());
        _actualizarDatosCliente_parameters.setCargoEspecifico(_actualizarDatosCliente_parametersCargoEspecifico);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoGenero = of.createActualizarDatosClienteRequestCodigoGenero(actualizarDatosClienteFilter.getCodGenero());
        _actualizarDatosCliente_parameters.setCodigoGenero(_actualizarDatosCliente_parametersCodigoGenero);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoNacionalidad = of.createActualizarDatosClienteRequestCodigoNacionalidad(actualizarDatosClienteFilter.getCodNacionalidad());
        _actualizarDatosCliente_parameters.setCodigoNacionalidad(_actualizarDatosCliente_parametersCodigoNacionalidad);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersNumeroVia = of.createActualizarDatosClienteRequestNumeroVia(actualizarDatosClienteFilter.getNumVia());
        _actualizarDatosCliente_parameters.setNumeroVia(_actualizarDatosCliente_parametersNumeroVia);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersCodigoTipoDomicilio = of.createActualizarDatosClienteRequestCodigoTipoDomicilio(actualizarDatosClienteFilter.getCodTipoDomicilio());
        _actualizarDatosCliente_parameters.setCodigoTipoDomicilio(_actualizarDatosCliente_parametersCodigoTipoDomicilio);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersDescripcionDomicilio = of.createActualizarDatosClienteRequestDescripcionDomicilio(actualizarDatosClienteFilter.getDesDomicilio());
        _actualizarDatosCliente_parameters.setDescripcionDomicilio(_actualizarDatosCliente_parametersDescripcionDomicilio);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersPiso = of.createActualizarDatosClienteRequestPiso(actualizarDatosClienteFilter.getValPiso());
        _actualizarDatosCliente_parameters.setPiso(_actualizarDatosCliente_parametersPiso);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersManzana = of.createActualizarDatosClienteRequestManzana(actualizarDatosClienteFilter.getValManzana());
        _actualizarDatosCliente_parameters.setManzana(_actualizarDatosCliente_parametersManzana);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersLote = of.createActualizarDatosClienteRequestLote(actualizarDatosClienteFilter.getValLote());
        _actualizarDatosCliente_parameters.setLote(_actualizarDatosCliente_parametersLote);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersSector = of.createActualizarDatosClienteRequestSector(actualizarDatosClienteFilter.getValSector());
        _actualizarDatosCliente_parameters.setSector(_actualizarDatosCliente_parametersSector);
        javax.xml.bind.JAXBElement<java.lang.String> _actualizarDatosCliente_parametersReferencia = of.createActualizarDatosClienteRequestReferencia(actualizarDatosClienteFilter.getValReferencia());
        _actualizarDatosCliente_parameters.setReferencia(_actualizarDatosCliente_parametersReferencia);
        _actualizarDatosCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _actualizarDatosCliente_parameters.setIpApp(idaplicacion);
        _actualizarDatosCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.transaccionclientes.ActualizarDatosClienteResponse _actualizarDatosCliente__return = port.actualizarDatosCliente(_actualizarDatosCliente_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION CLIENTES : actualizarDatosCliente con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("codigo tipo de documento : ").append(actualizarDatosClienteFilter.getCodTipoDocumento()).append(",\n");
        sf.append("numero de documento : ").append(actualizarDatosClienteFilter.getNumDocumento()).append(",\n");
        sf.append("correo electronico : ").append(actualizarDatosClienteFilter.getValCorreoElectronico()).append(",\n");
        sf.append("fecha de nacimiento : ").append(actualizarDatosClienteFilter.getFecNacimiento()).append(",\n");
        sf.append("codigo estado civil : ").append(actualizarDatosClienteFilter.getCodEstadoCivil()).append(",\n");
        sf.append("cargo especifico : ").append(actualizarDatosClienteFilter.getNomCargoEsp()).append(",\n");
        sf.append("codigo genero : ").append(actualizarDatosClienteFilter.getCodGenero()).append(",\n");
        sf.append("codigo nacionalidad : ").append(actualizarDatosClienteFilter.getCodNacionalidad()).append(",\n");
        sf.append("numero de via : ").append(actualizarDatosClienteFilter.getNumVia()).append(",\n");
        sf.append("codigo tipo de domicilio : ").append(actualizarDatosClienteFilter.getCodTipoDomicilio()).append(",\n");
        sf.append("descripcion domicilio : ").append(actualizarDatosClienteFilter.getDesDomicilio()).append(",\n");
        sf.append("piso : ").append(actualizarDatosClienteFilter.getValPiso()).append(",\n");
        sf.append("manzana : ").append(actualizarDatosClienteFilter.getValManzana()).append(",\n");
        sf.append("lote : ").append(actualizarDatosClienteFilter.getValLote()).append(",\n");
        sf.append("sector : ").append(actualizarDatosClienteFilter.getValSector()).append(",\n");
        sf.append("referencia : ").append(actualizarDatosClienteFilter.getValReferencia()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
               
        sf.append("AuditCode : ").append(_actualizarDatosCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_actualizarDatosCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_actualizarDatosCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes at = new AuditTypes();
        at.setCodError(_actualizarDatosCliente__return.getAudit().getErrorCode().toString());
        at.setMsgError(_actualizarDatosCliente__return.getAudit().getErrorMsg().toString());
        
        return at;
	}
	
	public AuditTypes registrarPublicacionDirectorioAbonado(Usuario usuario, RegistrarPublicacionDirectorioAbonadoFilter registrarPublicacionDirectorioAbonadoFilter)
	{
		pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarPublicacionDirectorioAbonadoRequest _registrarPublicacionDirectorioAbonado_parameters = new pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarPublicacionDirectorioAbonadoRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _registrarPublicacionDirectorioAbonado_parametersNumeroTelefonico = of.createRegistrarPublicacionDirectorioAbonadoRequestNumeroTelefonico(registrarPublicacionDirectorioAbonadoFilter.getNumero());
        _registrarPublicacionDirectorioAbonado_parameters.setNumeroTelefonico(_registrarPublicacionDirectorioAbonado_parametersNumeroTelefonico);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarPublicacionDirectorioAbonado_parametersFlagActivo = of.createRegistrarPublicacionDirectorioAbonadoRequestFlagActivo(registrarPublicacionDirectorioAbonadoFilter.getFlag());
        _registrarPublicacionDirectorioAbonado_parameters.setFlagActivo(_registrarPublicacionDirectorioAbonado_parametersFlagActivo);
        _registrarPublicacionDirectorioAbonado_parameters.setIdTransaccion(ma.IdTransaccion());
        _registrarPublicacionDirectorioAbonado_parameters.setIpApp(idaplicacion);
        _registrarPublicacionDirectorioAbonado_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarPublicacionDirectorioAbonadoResponse _registrarPublicacionDirectorioAbonado__return = port.registrarPublicacionDirectorioAbonado(_registrarPublicacionDirectorioAbonado_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION CLIENTES : registrarPublicacionDirectorioAbonado con los Parametros : \n [" );
        sf.append("numero telefonico : ").append(registrarPublicacionDirectorioAbonadoFilter.getNumero()).append(",\n");
        sf.append("flag activo : ").append(registrarPublicacionDirectorioAbonadoFilter.getFlag()).append(",\n");
        sf.append("idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_registrarPublicacionDirectorioAbonado__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_registrarPublicacionDirectorioAbonado__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_registrarPublicacionDirectorioAbonado__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes at = new AuditTypes();
        at.setCodError(_registrarPublicacionDirectorioAbonado__return.getAudit().getErrorCode().toString());
        at.setMsgError(_registrarPublicacionDirectorioAbonado__return.getAudit().getErrorMsg().toString());
        return at;
	}
	
	public RegistrarIncidencia registrarIncidencia(Usuario usuario, RegistrarIncidenciaFilter registrarIncidenciaFilter)
	{
		pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarIncidenciaRequest _registrarIncidencia_parameters = new pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarIncidenciaRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoCliente = of.createRegistrarIncidenciaRequestCodigoCliente(usuario.getCodigoUsuario());
        _registrarIncidencia_parameters.setCodigoCliente(_registrarIncidencia_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoProducto = of.createRegistrarIncidenciaRequestCodigoProducto(usuario.getCodigoProducto());
        _registrarIncidencia_parameters.setCodigoProducto(_registrarIncidencia_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoServicio = of.createRegistrarIncidenciaRequestCodigoServicio(usuario.getCodigoServicio());
        _registrarIncidencia_parameters.setCodigoServicio(_registrarIncidencia_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoInstanciaServicio = of.createRegistrarIncidenciaRequestCodigoInstanciaServicio(registrarIncidenciaFilter.getCodigoInstanciaServicio());//
        _registrarIncidencia_parameters.setCodigoInstanciaServicio(_registrarIncidencia_parametersCodigoInstanciaServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoTransaccion = of.createRegistrarIncidenciaRequestCodigoTransaccion(registrarIncidenciaFilter.getCodigoTransaccion());
        _registrarIncidencia_parameters.setCodigoTransaccion(_registrarIncidencia_parametersCodigoTransaccion);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoCanal = of.createRegistrarIncidenciaRequestCodigoCanal(registrarIncidenciaFilter.getCodigoCanal());//8
        _registrarIncidencia_parameters.setCodigoCanal(_registrarIncidencia_parametersCodigoCanal);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoEstado = of.createRegistrarIncidenciaRequestCodigoEstado(registrarIncidenciaFilter.getCodigoEstado());
        _registrarIncidencia_parameters.setCodigoEstado(_registrarIncidencia_parametersCodigoEstado);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersObservacion = of.createRegistrarIncidenciaRequestObservacion(registrarIncidenciaFilter.getObservacion());
        _registrarIncidencia_parameters.setObservacion(_registrarIncidencia_parametersObservacion);
        javax.xml.bind.JAXBElement<java.lang.String> _registrarIncidencia_parametersCodigoEstadoSecuencia = of.createRegistrarIncidenciaRequestCodigoEstadoSecuencia(registrarIncidenciaFilter.getCodigoEstadoSecuencia());//0
        _registrarIncidencia_parameters.setCodigoEstadoSecuencia(_registrarIncidencia_parametersCodigoEstadoSecuencia);
        _registrarIncidencia_parameters.setIdTransaccion(ma.IdTransaccion());
        _registrarIncidencia_parameters.setIpApp(idaplicacion);
        _registrarIncidencia_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.transaccionclientes.RegistrarIncidenciaResponse _registrarIncidencia__return = port.registrarIncidencia(_registrarIncidencia_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION CLIENTES : registrarIncidencia con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("codigo instancia de servicio : ").append(registrarIncidenciaFilter.getCodigoInstanciaServicio()).append(",\n");
        sf.append("codigo transaccion : ").append(registrarIncidenciaFilter.getCodigoTransaccion()).append(",\n");
        sf.append("codigo canal : ").append(registrarIncidenciaFilter.getCodigoCanal()).append(",\n");
        sf.append("codigo estado : ").append(registrarIncidenciaFilter.getCodigoEstado()).append(",\n");
        sf.append("observacion : ").append(registrarIncidenciaFilter.getObservacion()).append(",\n");
        sf.append("codigo estado secuencia : ").append(registrarIncidenciaFilter.getCodigoEstadoSecuencia()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_registrarIncidencia__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_registrarIncidencia__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_registrarIncidencia__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        RegistrarIncidencia ri = new RegistrarIncidencia();
        
        ri.setCodError(_registrarIncidencia__return.getAudit().getErrorCode().toString());
        ri.setMsgError(_registrarIncidencia__return.getAudit().getErrorMsg().toString());
        ri.setCodigoIncidencia(_registrarIncidencia__return.getCodigoIncidencia()!=null?_registrarIncidencia__return.getCodigoIncidencia().getValue():"");
        ri.setNumeroTicket(_registrarIncidencia__return.getNumeroTicket()!=null?_registrarIncidencia__return.getNumeroTicket().getValue():"");
        
        return ri;
        
	}
}
