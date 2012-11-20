package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


import pe.com.claro.caef.web.action.filter.ConsultarListRecargasFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasEntrantesFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasFacturadasFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasNoFacturadasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.beans.LlamadaEntrante;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.ConsultarLlamadasWS;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultaLlamadas;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultaLlamadas_Service;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ListaLlamadasEntrantes;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ListaLlamadasFacturadas;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ListaLlamadasNoFacturadas;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ListaRecargas;
import pe.com.claro.eai.crmservices.fija.consultallamadas.ObjectFactory;

@Component
public class ConsultarLlamadasWSImpl implements ConsultarLlamadasWS {
	
	private ConsultaLlamadas port;
	private ConsultaLlamadas_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(ConsultarLlamadasWSImpl.class);
	
	public ConsultarLlamadasWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_CONSULTA_LLAMADAS);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new ConsultaLlamadas_Service(url);
		port = cs.getConsultaLlamadasSOAP();
	}
	
	public List<LlamadaNoFacturada> consultarListaLlamadasNofacturadas(Usuario usuario, ConsultarListaLlamadasNoFacturadasFilter consultarListaLlamadasNoFacturadasFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasNofacturadasRequest _consultarListaLlamadasNofacturadas_parameters = new pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasNofacturadasRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersCodigoCliente = of.createConsultarListaLlamadasNofacturadasRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarListaLlamadasNofacturadas_parameters.setCodigoCliente(_consultarListaLlamadasNofacturadas_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersCodigoProducto = of.createConsultarListaLlamadasNofacturadasRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarListaLlamadasNofacturadas_parameters.setCodigoProducto(_consultarListaLlamadasNofacturadas_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersCodigoServicio = of.createConsultarListaLlamadasNofacturadasRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarListaLlamadasNofacturadas_parameters.setCodigoServicio(_consultarListaLlamadasNofacturadas_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersNumeroOrigen = of.createConsultarListaLlamadasNofacturadasRequestNumeroOrigen(consultarListaLlamadasNoFacturadasFilter.getNumOrigen());
        _consultarListaLlamadasNofacturadas_parameters.setNumeroOrigen(_consultarListaLlamadasNofacturadas_parametersNumeroOrigen);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersFechaInicio = of.createConsultarListaLlamadasNofacturadasRequestFechaInicio(consultarListaLlamadasNoFacturadasFilter.getFecInicio());
        _consultarListaLlamadasNofacturadas_parameters.setFechaInicio(_consultarListaLlamadasNofacturadas_parametersFechaInicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersFechaFin = of.createConsultarListaLlamadasNofacturadasRequestFechaFin(consultarListaLlamadasNoFacturadasFilter.getFecFin());
        _consultarListaLlamadasNofacturadas_parameters.setFechaFin(_consultarListaLlamadasNofacturadas_parametersFechaFin);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasNofacturadas_parametersNumeroPagina = of.createConsultarListaLlamadasNofacturadasRequestNumeroPagina(consultarListaLlamadasNoFacturadasFilter.getNumeroPagina());
        _consultarListaLlamadasNofacturadas_parameters.setNumeroPagina(_consultarListaLlamadasNofacturadas_parametersNumeroPagina);
        _consultarListaLlamadasNofacturadas_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarListaLlamadasNofacturadas_parameters.setIpApp(idaplicacion);
        _consultarListaLlamadasNofacturadas_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasNofacturadasResponse _consultarListaLlamadasNofacturadas__return = port.consultarListaLlamadasNofacturadas(_consultarListaLlamadasNofacturadas_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTAR LLAMADAS : consultarListaLlamadasNofacturadas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("numero origen : ").append(consultarListaLlamadasNoFacturadasFilter.getNumOrigen()).append(",\n");
        sf.append("fecha inicio : ").append(consultarListaLlamadasNoFacturadasFilter.getFecInicio()).append(",\n");
        sf.append("fecha fin : ").append(consultarListaLlamadasNoFacturadasFilter.getFecFin()).append(",\n");
        sf.append("numero pagina : ").append(consultarListaLlamadasNoFacturadasFilter.getNumeroPagina()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_consultarListaLlamadasNofacturadas__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarListaLlamadasNofacturadas__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarListaLlamadasNofacturadas__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarListaLlamadasNofacturadas__return.getListaLlamadasNoFacturadas().size() > 0)
        {
        	List<LlamadaNoFacturada> lstLlamadaNoFacturada = new ArrayList<LlamadaNoFacturada>();
        	
        	for(ListaLlamadasNoFacturadas lc : _consultarListaLlamadasNofacturadas__return.getListaLlamadasNoFacturadas())
        	{
        		LlamadaNoFacturada lnf = new LlamadaNoFacturada();
        		lnf.setHoraFin(lc.getHoraFin()!=null?lc.getHoraFin().toString():"");
        		lnf.setHoraInicio(lc.getHoraInicio()!=null?lc.getHoraInicio().toString():"");
        		lnf.setNumDestino(lc.getNumeroDestino()!=null?lc.getNumeroDestino().toString():"");
        		lnf.setNumOrigen(lc.getNumeroOrigen()!=null?lc.getNumeroOrigen().toString():"");
        		lnf.setTipServicio(lc.getTipoServicioLlamada()!=null?lc.getTipoServicioLlamada().toString():"");
        		lnf.setValDuracion(lc.getTiempoDuracionLlamada()!=null?lc.getTiempoDuracionLlamada().toString():"");
        		lstLlamadaNoFacturada.add(lnf);
        	}
        	
        	return lstLlamadaNoFacturada;
        }
        else
        {
        	return new ArrayList<LlamadaNoFacturada>();
        }
	}
	
	public List<LlamadaFacturada> consultarListaLlamadasFacturadas(Usuario usuario, ConsultarListaLlamadasFacturadasFilter consultarListaLlamadasFacturadasFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasFacturadasRequest _consultarListaLlamadasFacturadas_parameters = new pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasFacturadasRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersCodigoCliente = of.createConsultarListaLlamadasFacturadasRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarListaLlamadasFacturadas_parameters.setCodigoCliente(_consultarListaLlamadasFacturadas_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersCodigoProducto = of.createConsultarListaLlamadasFacturadasRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarListaLlamadasFacturadas_parameters.setCodigoProducto(_consultarListaLlamadasFacturadas_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersCodigoServicio = of.createConsultarListaLlamadasFacturadasRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarListaLlamadasFacturadas_parameters.setCodigoServicio(_consultarListaLlamadasFacturadas_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersNumeroOrigen =of.createConsultarListaLlamadasFacturadasRequestNumeroOrigen(consultarListaLlamadasFacturadasFilter.getNumOrigen());
        _consultarListaLlamadasFacturadas_parameters.setNumeroOrigen(_consultarListaLlamadasFacturadas_parametersNumeroOrigen);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersFechaInicio = of.createConsultarListaLlamadasFacturadasRequestFechaInicio(consultarListaLlamadasFacturadasFilter.getFecInicio());
        _consultarListaLlamadasFacturadas_parameters.setFechaInicio(_consultarListaLlamadasFacturadas_parametersFechaInicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersFechaFin = of.createConsultarListaLlamadasFacturadasRequestFechaFin(consultarListaLlamadasFacturadasFilter.getFecFin());
        _consultarListaLlamadasFacturadas_parameters.setFechaFin(_consultarListaLlamadasFacturadas_parametersFechaFin);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasFacturadas_parametersNumeroPagina = of.createConsultarListaLlamadasFacturadasRequestNumeroPagina("-1"/*consultarListaLlamadasFacturadasFilter.getNumeroPagina()*/);
        _consultarListaLlamadasFacturadas_parameters.setNumeroPagina(_consultarListaLlamadasFacturadas_parametersNumeroPagina);
        _consultarListaLlamadasFacturadas_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarListaLlamadasFacturadas_parameters.setIpApp(idaplicacion);
        _consultarListaLlamadasFacturadas_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasFacturadasResponse _consultarListaLlamadasFacturadas__return = port.consultarListaLlamadasFacturadas(_consultarListaLlamadasFacturadas_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTAR LLAMADAS : consultarListaLlamadasFacturadas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("numero origen : ").append(consultarListaLlamadasFacturadasFilter.getNumOrigen()).append(",\n");
        sf.append("fecha inicio : ").append(consultarListaLlamadasFacturadasFilter.getFecInicio()).append(",\n");
        sf.append("fecha fin : ").append(consultarListaLlamadasFacturadasFilter.getFecFin()).append(",\n");
        sf.append("numero pagina : ").append(consultarListaLlamadasFacturadasFilter.getNumeroPagina()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_consultarListaLlamadasFacturadas__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarListaLlamadasFacturadas__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarListaLlamadasFacturadas__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarListaLlamadasFacturadas__return.getListaLlamadasFacturadas().size() > 0)
        {
        	List<LlamadaFacturada> lstLlamadaFacturada = new ArrayList<LlamadaFacturada>();
        	
        	for(ListaLlamadasFacturadas lc : _consultarListaLlamadasFacturadas__return.getListaLlamadasFacturadas())
        	{
        		LlamadaFacturada lnf = new LlamadaFacturada();
        		lnf.setHoraFin(Constantes.convertirFecha(lc.getHoraFin()!=null?lc.getHoraFin().toString():""));
        		lnf.setHoraInicio(Constantes.convertirFecha(lc.getHoraInicio()!=null?lc.getHoraInicio().toString():""));
        		lnf.setNumDestino(lc.getNumeroDestino()!=null?lc.getNumeroDestino().toString():"");
        		lnf.setNumOrigen(lc.getNumeroOrigen()!=null?lc.getNumeroOrigen().toString():"");
        		lnf.setTipServicio(lc.getTipoServicioLlamada()!=null?lc.getTipoServicioLlamada().toString():"");
        		lnf.setValDuracion(lc.getTiempoDuracionLlamada()!=null?lc.getTiempoDuracionLlamada().toString():"");
        		lnf.setValTarifa(lc.getValorTarifaLlamada()!=null?lc.getValorTarifaLlamada().toString():"");
        		lnf.setNumRecibo(lc.getNumeroRecibo()!=null?lc.getNumeroRecibo().toString():"");
        		lnf.setValCosto(lc.getValorCostoLlamada()!=null?lc.getValorCostoLlamada().toString():"");
        		lstLlamadaFacturada.add(lnf);
        	}
        	
        	return lstLlamadaFacturada;
        }
        else
        {
        	return new ArrayList<LlamadaFacturada>();
        }
	}

	public List<ConsultaRecarga> consultarListRecargas(Usuario usuario, ConsultarListRecargasFilter consultarListRecargasFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListRecargasRequest _consultarListRecargas_parameters = new pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListRecargasRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarListRecargas_parametersCodigoCliente = of.createConsultarListRecargasRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarListRecargas_parameters.setCodigoCliente(_consultarListRecargas_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListRecargas_parametersCodigoProducto = of.createConsultarListRecargasRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarListRecargas_parameters.setCodigoProducto(_consultarListRecargas_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListRecargas_parametersCodigoServicio = of.createConsultarListRecargasRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarListRecargas_parameters.setCodigoServicio(_consultarListRecargas_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListRecargas_parametersFechaInicio = of.createConsultarListRecargasRequestFechaInicio(consultarListRecargasFilter.getFecInicio());
        _consultarListRecargas_parameters.setFechaInicio(_consultarListRecargas_parametersFechaInicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListRecargas_parametersFechaFin = of.createConsultarListRecargasRequestFechaFin(consultarListRecargasFilter.getFecFin());
        _consultarListRecargas_parameters.setFechaFin(_consultarListRecargas_parametersFechaFin);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListRecargas_parametersNumeroPagina = of.createConsultarListRecargasRequestNumeroPagina(consultarListRecargasFilter.getNumeroPagina());
        _consultarListRecargas_parameters.setNumeroPagina(_consultarListRecargas_parametersNumeroPagina);
        _consultarListRecargas_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarListRecargas_parameters.setIpApp(idaplicacion);
        _consultarListRecargas_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListRecargasResponse _consultarListRecargas__return = port.consultarListRecargas(_consultarListRecargas_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTAR LLAMADAS : consultarListRecargas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("fecha inicio : ").append(consultarListRecargasFilter.getFecInicio()).append(",\n");
        sf.append("fecha fin : ").append(consultarListRecargasFilter.getFecFin()).append(",\n");
        sf.append("numero pagina : ").append(consultarListRecargasFilter.getNumeroPagina()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_consultarListRecargas__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarListRecargas__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarListRecargas__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarListRecargas__return.getListaRecargas().size() > 0)
        {
        	List<ConsultaRecarga> lstConsultaRecarga = new ArrayList<ConsultaRecarga>();
        	
        	for(ListaRecargas lc : _consultarListRecargas__return.getListaRecargas())
        	{
        		ConsultaRecarga cr = new ConsultaRecarga();
        		cr.setFecRecarga(Constantes.convertirFecha(lc.getFechaRecarga()!=null?lc.getFechaRecarga().toString():""));
        		cr.setValorMonto(lc.getMontoRecarga()!=null?lc.getMontoRecarga().toString():"");
        		cr.setFecInicio(Constantes.convertirFecha(lc.getFechaInicioRecarga()!=null?lc.getFechaInicioRecarga().toString():""));
        		cr.setFecFin(Constantes.convertirFecha(lc.getFechaFinRecarga()!=null?lc.getFechaFinRecarga().toString():""));
        		cr.setAgenteRecarga(lc.getAgenteRecarga()!=null?lc.getAgenteRecarga().toString():"");
        		lstConsultaRecarga.add(cr);
        	}
        	
        	return lstConsultaRecarga;
        }
        else
        {
        	return new ArrayList<ConsultaRecarga>();
        }
	}
	
	public List<LlamadaEntrante> consultarListaLlamadasEntrantes(Usuario usuario, ConsultarListaLlamadasEntrantesFilter consultarListaLlamadasEntrantesFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasEntrantesRequest _consultarListaLlamadasEntrantes_parameters = new pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasEntrantesRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersCodigoCliente = of.createConsultarListaLlamadasEntrantesRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarListaLlamadasEntrantes_parameters.setCodigoCliente(_consultarListaLlamadasEntrantes_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersCodigoProducto = of.createConsultarListaLlamadasEntrantesRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarListaLlamadasEntrantes_parameters.setCodigoProducto(_consultarListaLlamadasEntrantes_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersCodigoServicio = of.createConsultarListaLlamadasEntrantesRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarListaLlamadasEntrantes_parameters.setCodigoServicio(_consultarListaLlamadasEntrantes_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersNumeroDestino = of.createConsultarListaLlamadasEntrantesRequestNumeroDestino(consultarListaLlamadasEntrantesFilter.getNumDestino());
        _consultarListaLlamadasEntrantes_parameters.setNumeroDestino(_consultarListaLlamadasEntrantes_parametersNumeroDestino);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersFechaInicio = of.createConsultarListaLlamadasEntrantesRequestFechaInicio(consultarListaLlamadasEntrantesFilter.getFecInicio());
        _consultarListaLlamadasEntrantes_parameters.setFechaInicio(_consultarListaLlamadasEntrantes_parametersFechaInicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersFechaFin = of.createConsultarListaLlamadasEntrantesRequestFechaFin(consultarListaLlamadasEntrantesFilter.getFecFin());
        _consultarListaLlamadasEntrantes_parameters.setFechaFin(_consultarListaLlamadasEntrantes_parametersFechaFin);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarListaLlamadasEntrantes_parametersNumeroPagina = of.createConsultarListaLlamadasEntrantesRequestNumeroPagina(consultarListaLlamadasEntrantesFilter.getNumeroPagina());
        _consultarListaLlamadasEntrantes_parameters.setNumeroPagina(_consultarListaLlamadasEntrantes_parametersNumeroPagina);
        _consultarListaLlamadasEntrantes_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarListaLlamadasEntrantes_parameters.setIpApp(idaplicacion);
        _consultarListaLlamadasEntrantes_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultallamadas.ConsultarListaLlamadasEntrantesResponse _consultarListaLlamadasEntrantes__return = port.consultarListaLlamadasEntrantes(_consultarListaLlamadasEntrantes_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA LLAMADAS : consultarListaLlamadasEntrantes con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("numero destino : ").append(consultarListaLlamadasEntrantesFilter.getNumDestino()).append(",\n");
        sf.append("fecha inicio : ").append(consultarListaLlamadasEntrantesFilter.getFecInicio()).append(",\n");
        sf.append("fecha fin : ").append(consultarListaLlamadasEntrantesFilter.getFecFin()).append(",\n");
        sf.append("numero pagina : ").append(consultarListaLlamadasEntrantesFilter.getNumeroPagina()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_consultarListaLlamadasEntrantes__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarListaLlamadasEntrantes__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarListaLlamadasEntrantes__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarListaLlamadasEntrantes__return.getListaLlamadasEntrantes().size() > 0)
        {
        	List<LlamadaEntrante> lstLlamadaEntrante = new ArrayList<LlamadaEntrante>();
        	
        	for(ListaLlamadasEntrantes lc : _consultarListaLlamadasEntrantes__return.getListaLlamadasEntrantes())
        	{
        		LlamadaEntrante le = new LlamadaEntrante();
        		le.setNumOrigen(lc.getNumeroTelefonoOrigen()!=null?lc.getNumeroTelefonoOrigen().toString():"");
        		le.setFecInicio(Constantes.convertirFecha(lc.getFechaInicio()!=null?lc.getFechaInicio().toString():""));
        		le.setFecFin(Constantes.convertirFecha(lc.getFechaFin()!=null?lc.getFechaFin().toString():""));
        		le.setValDuracion(lc.getTiempoDuracionLlamada()!=null?lc.getTiempoDuracionLlamada().toString():"");
        		lstLlamadaEntrante.add(le);
        	}
        	
        	return lstLlamadaEntrante;
        }
        else
        {
        	return new ArrayList<LlamadaEntrante>();
        }
	}
}
