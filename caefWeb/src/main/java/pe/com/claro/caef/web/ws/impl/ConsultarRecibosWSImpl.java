package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.action.filter.ConsultarDetalleReciboClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarEstadoCuentaFilter;
import pe.com.claro.caef.web.action.filter.ConsultarReciboClienteFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.DuplicadoRecibo;
import pe.com.claro.caef.web.beans.DuplicadoReciboPDF;
import pe.com.claro.caef.web.beans.ReciboCorreoElectronico;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.ConsultarRecibosWS;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultaRecibos;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultaRecibos_Service;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ListaConsultarGrupoFactRecibos;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ListaDetalleReciboCliente;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ListaEstadoCuenta;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ListaReciboCliente;
import pe.com.claro.eai.crmservices.fija.consultarecibos.ObjectFactory;

@Component
public class ConsultarRecibosWSImpl implements ConsultarRecibosWS {
	
	private ConsultaRecibos port;
	private ConsultaRecibos_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(ConsultarRecibosWSImpl.class);
	
	public ConsultarRecibosWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_CONSULTA_RECIBOS);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new ConsultaRecibos_Service(url);
        port = cs.getConsultaRecibosSOAP();
	}
	
	public List<ReciboCorreoElectronico> consultarGrupoFactRecibos(Usuario usuario)
	{
		pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarGrupoFactRecibosRequest _consultarGrupoFactRecibos_parameters = new pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarGrupoFactRecibosRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarGrupoFactRecibos_parametersCodigoCliente = of.createConsultarGrupoFactRecibosRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarGrupoFactRecibos_parameters.setCodigoCliente(_consultarGrupoFactRecibos_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarGrupoFactRecibos_parametersCodigoProducto = of.createConsultarGrupoFactRecibosRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarGrupoFactRecibos_parameters.setCodigoProducto(_consultarGrupoFactRecibos_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarGrupoFactRecibos_parametersCodigoServicio = of.createConsultarGrupoFactRecibosRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarGrupoFactRecibos_parameters.setCodigoServicio(_consultarGrupoFactRecibos_parametersCodigoServicio);
        _consultarGrupoFactRecibos_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarGrupoFactRecibos_parameters.setIpApp(idaplicacion);
        _consultarGrupoFactRecibos_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarGrupoFactRecibosResponse _consultarGrupoFactRecibos__return = port.consultarGrupoFactRecibos(_consultarGrupoFactRecibos_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA RECIBOS : consultarGrupoFactRecibos con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarGrupoFactRecibos__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarGrupoFactRecibos__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarGrupoFactRecibos__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarGrupoFactRecibos__return.getListaConsultarGrupoFactRecibos().size() > 0)
        {
        	List<ReciboCorreoElectronico> lstReciboCorreoElectronico = new ArrayList<ReciboCorreoElectronico>();
        	
        	for(ListaConsultarGrupoFactRecibos lc : _consultarGrupoFactRecibos__return.getListaConsultarGrupoFactRecibos())
        	{
        		ReciboCorreoElectronico rce = new ReciboCorreoElectronico();
        		rce.setCodGrupo(lc.getCodigoGrupo()!=null?lc.getCodigoGrupo().toString():"");
        		rce.setFlgActivo(lc.getFlagActivo()!=null?lc.getFlagActivo().toString():"");
        		rce.setNomServicio(lc.getNombreServicio()!=null?lc.getNombreServicio().toString():"");
        		rce.setValCorreoElectronico(lc.getCorreoElectronico()!=null?lc.getCorreoElectronico().toString():"");
        		lstReciboCorreoElectronico.add(rce);
        	}
        	
        	return lstReciboCorreoElectronico;
        }
        else
        {
        	return new ArrayList<ReciboCorreoElectronico>();
        }
	}
	
	public List<DuplicadoRecibo> consultarReciboCliente (Usuario usuario, ConsultarReciboClienteFilter consultarReciboClienteFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarReciboClienteRequest _consultarReciboCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarReciboClienteRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		Calendar periodo = Calendar.getInstance();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarReciboCliente_parametersCodigoCliente = of.createConsultarReciboClienteRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarReciboCliente_parameters.setCodigoCliente(_consultarReciboCliente_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarReciboCliente_parametersCodigoProducto = of.createConsultarReciboClienteRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarReciboCliente_parameters.setCodigoProducto(_consultarReciboCliente_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarReciboCliente_parametersCodigoServicio = of.createConsultarReciboClienteRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarReciboCliente_parameters.setCodigoServicio(_consultarReciboCliente_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarReciboCliente_parametersNumeroPedidos = of.createConsultarReciboClienteRequestNumeroPedidos(null/*nullconsultarReciboClienteFilter.getNumPeriodos()*/);
        _consultarReciboCliente_parameters.setNumeroPedidos(_consultarReciboCliente_parametersNumeroPedidos);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarReciboCliente_parametersAnioPeriodoInicio = of.createConsultarReciboClienteRequestAnioPeriodoInicio(String.valueOf(periodo.get(Calendar.YEAR)));/*nullconsultarReciboClienteFilter.getAnoPeriodo()*/
        _consultarReciboCliente_parameters.setAnioPeriodoInicio(_consultarReciboCliente_parametersAnioPeriodoInicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarReciboCliente_parametersMesPeriodoInicio = of.createConsultarReciboClienteRequestMesPeriodoInicio(null/*consultarReciboClienteFilter.getMesPeriodo()*/);
        _consultarReciboCliente_parameters.setMesPeriodoInicio(_consultarReciboCliente_parametersMesPeriodoInicio);
        _consultarReciboCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarReciboCliente_parameters.setIpApp(idaplicacion);
        _consultarReciboCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarReciboClienteResponse _consultarReciboCliente__return = port.consultarReciboCliente(_consultarReciboCliente_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA RECIBOS : consultarReciboCliente con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        //sf.append("numero de periodos : ").append(consultarReciboClienteFilter.getNumPeriodos()).append(",\n");
        //sf.append("ano periodo : ").append(consultarReciboClienteFilter.getAnoPeriodo()).append(",\n");
        //sf.append("mes periodo : ").append(consultarReciboClienteFilter.getMesPeriodo()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_consultarReciboCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarReciboCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarReciboCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        if(_consultarReciboCliente__return.getListaReciboCliente().size() > 0)
        {
        	List<DuplicadoRecibo> lstDuplicadoRecibo = new ArrayList<DuplicadoRecibo>();
        	
        	for(ListaReciboCliente lc : _consultarReciboCliente__return.getListaReciboCliente())
        	{
        		DuplicadoRecibo dr = new DuplicadoRecibo();
        		dr.setCodFactura(lc.getCodigoFactura()!=null?lc.getCodigoFactura().toString():"");
        		dr.setCodCicloFac(lc.getCodigoCicloFacturacion()!=null?lc.getCodigoCicloFacturacion().toString():"");
        		dr.setCodCliente(lc.getCodigoCliente()!=null?lc.getCodigoCliente().toString():"");
        		dr.setFecEmision(lc.getFechaEmision()!=null?lc.getFechaEmision().toString():"");
        		dr.setValTotal(lc.getValorTotalFacturacion()!=null?lc.getValorTotalFacturacion().toString():"");
        		dr.setFecFacturacion(lc.getFechaFacturacion()!=null?lc.getFechaFacturacion().toString():"");
        		lstDuplicadoRecibo.add(dr);
        	}
        	
        	return lstDuplicadoRecibo;
        }
        else
        {
        	return new ArrayList<DuplicadoRecibo>();
        }
	}
	
	public List<DuplicadoReciboPDF> consultarDetalleReciboCliente(Usuario usuario, ConsultarDetalleReciboClienteFilter consultarDetalleReciboClienteFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarDetalleReciboClienteRequest _consultarDetalleReciboCliente_parameters = new pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarDetalleReciboClienteRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarDetalleReciboCliente_parametersCodigoFactura = of.createConsultarDetalleReciboClienteRequestCodigoFactura(consultarDetalleReciboClienteFilter.getCodFactura());
        _consultarDetalleReciboCliente_parameters.setCodigoFactura(_consultarDetalleReciboCliente_parametersCodigoFactura);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarDetalleReciboCliente_parametersCodigoCicloFacturacion = of.createConsultarDetalleReciboClienteRequestCodigoCicloFacturacion(consultarDetalleReciboClienteFilter.getCodCicloFac());
        _consultarDetalleReciboCliente_parameters.setCodigoCicloFacturacion(_consultarDetalleReciboCliente_parametersCodigoCicloFacturacion);
        _consultarDetalleReciboCliente_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarDetalleReciboCliente_parameters.setIpApp(idaplicacion);
        _consultarDetalleReciboCliente_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarDetalleReciboClienteResponse _consultarDetalleReciboCliente__return = port.consultarDetalleReciboCliente(_consultarDetalleReciboCliente_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA RECIBOS : consultarDetalleReciboCliente con los Parametros : \n [" );
        sf.append("codigo factura : ").append(consultarDetalleReciboClienteFilter.getCodFactura()).append(",\n");
        sf.append("codigo ciclo facturacion : ").append(consultarDetalleReciboClienteFilter.getCodCicloFac()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("AuditCode : ").append(_consultarDetalleReciboCliente__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarDetalleReciboCliente__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarDetalleReciboCliente__return.getAudit().getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        if(_consultarDetalleReciboCliente__return.getListaDetalleReciboCliente().size() > 0)
        {
        	List<DuplicadoReciboPDF> lstDuplicadoReciboPDF = new ArrayList<DuplicadoReciboPDF>();
        	
        	for(ListaDetalleReciboCliente lc : _consultarDetalleReciboCliente__return.getListaDetalleReciboCliente())
        	{
        		DuplicadoReciboPDF drp = new DuplicadoReciboPDF();
        		drp.setCodDirFacturacion(lc.getCodigoDireccionFactura()!=null?lc.getCodigoDireccionFactura().toString():"");
        		drp.setCodCliente(lc.getCodigoCliente()!=null?lc.getCodigoCliente().toString():"");
        		drp.setValSubTotal(lc.getValorSubTotalFactura()!=null?lc.getValorSubTotalFactura().toString():"");
        		drp.setValImpuesto(lc.getImpuesto()!=null?lc.getImpuesto().toString():"");
        		drp.setValTotal(lc.getValorTotalFactura()!=null?lc.getValorTotalFactura().toString():"");
        		drp.setValSaldoAnt(lc.getSaldoAnteriorCliente()!=null?lc.getSaldoAnteriorCliente().toString():"");
        		drp.setNumSerie(lc.getNumeroSerie()!=null?lc.getNumeroSerie().toString():"");
        		drp.setNumDocumento(lc.getNumeroFactura()!=null?lc.getNumeroFactura().toString():"");
        		drp.setCodInstServicioPrin(lc.getCodigoInstanciaServicioPrincipal()!=null?lc.getCodigoInstanciaServicioPrincipal().toString():"");
        		drp.setFecEmision(lc.getFechaEmision()!=null?lc.getFechaEmision().toString():"");
        		drp.setFecVencimiento(lc.getFechaVencimiento()!=null?lc.getFechaVencimiento().toString():"");
        		drp.setCodFactura(lc.getCodigoFactura()!=null?lc.getCodigoFactura().toString():"");
        		drp.setCodCicloFac(lc.getCodigoCicloFacturacion()!=null?lc.getCodigoCicloFacturacion().toString():"");
        		drp.setTipFacturacion(lc.getTipoFacturacion()!=null?lc.getTipoFacturacion().toString():"");
        		drp.setCodCargoCta(lc.getCodigoCargoCuenta()!=null?lc.getCodigoCargoCuenta().toString():"");
        		drp.setTipInstServicio(lc.getTipoInstanciaServicio()!=null?lc.getTipoInstanciaServicio().toString():"");
        		drp.setNomInstServicioPrin(lc.getNombreInstanciaServicioPrincipal()!=null?lc.getNombreInstanciaServicioPrincipal().toString():"");
        		drp.setValHistorico(lc.getHistorico()!=null?lc.getHistorico().toString():"");
        		drp.setCodProductoPrin(lc.getCodigoProductoPrincipal()!=null?lc.getCodigoProductoPrincipal().toString():"");
        		drp.setValMuestraSaldo(lc.getValorMuestraSaldo()!=null?lc.getValorMuestraSaldo().toString():"");
        		drp.setNomEmpresa(lc.getNombreEmpresa()!=null?lc.getNombreEmpresa().toString():"");
        		drp.setDocEmpresa(lc.getRucEmpresa()!=null?lc.getRucEmpresa().toString():"");
        		drp.setDirEmpresa(lc.getDireccionEmpresa()!=null?lc.getDireccionEmpresa().toString():"");
        		drp.setDirSitioWeb(lc.getDireccionWebEmpresa()!=null?lc.getDireccionWebEmpresa().toString():"");
        		drp.setNumTelEmpresa(lc.getTelefonoEmpresa()!=null?lc.getTelefonoEmpresa().toString():"");
        		drp.setNumFaxEmpresa(lc.getFaxEmpresa()!=null?lc.getFaxEmpresa().toString():"");
        		drp.setRucEmpresa(lc.getRucEmpresa()!=null?lc.getRucEmpresa().toString():"");
        		
        		lstDuplicadoReciboPDF.add(drp);
        	}
        	
        	return lstDuplicadoReciboPDF;
        }
        else
        {
        	return new ArrayList<DuplicadoReciboPDF>();
        }
	}
	
	public List<DetalleEstadoCuenta> consultarEstadoCuentaDetalle(Usuario usuario, ConsultarEstadoCuentaFilter consultarEstadoCuentaFilter)
	{
		pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarEstadoCuentaRequest _consultarEstadoCuenta_parameters = new pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarEstadoCuentaRequest();
        
		ObjectFactory of = new ObjectFactory();
		
		javax.xml.bind.JAXBElement<java.lang.String> _consultarEstadoCuenta_parametersCodigoCliente = of.createConsultarEstadoCuentaRequestCodigoCliente(usuario.getCodigoUsuario());
        _consultarEstadoCuenta_parameters.setCodigoCliente(_consultarEstadoCuenta_parametersCodigoCliente);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarEstadoCuenta_parametersCodigoProducto = of.createConsultarEstadoCuentaRequestCodigoProducto(usuario.getCodigoProducto());
        _consultarEstadoCuenta_parameters.setCodigoProducto(_consultarEstadoCuenta_parametersCodigoProducto);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarEstadoCuenta_parametersCodigoServicio = of.createConsultarEstadoCuentaRequestCodigoServicio(usuario.getCodigoServicio());
        _consultarEstadoCuenta_parameters.setCodigoServicio(_consultarEstadoCuenta_parametersCodigoServicio);
        javax.xml.bind.JAXBElement<java.lang.String> _consultarEstadoCuenta_parametersFechaEmision = of.createConsultarEstadoCuentaRequestFechaEmision(consultarEstadoCuentaFilter.getFecEmision());
        _consultarEstadoCuenta_parameters.setFechaEmision(_consultarEstadoCuenta_parametersFechaEmision);
        
        javax.xml.bind.JAXBElement<java.lang.String> _consultarEstadoCuenta_parametersNumeroPagina = of.createConsultarEstadoCuentaRequestNumeroPagina(consultarEstadoCuentaFilter.getNumeroPagina());
        _consultarEstadoCuenta_parameters.setNumeroPagina(_consultarEstadoCuenta_parametersNumeroPagina);
        
        _consultarEstadoCuenta_parameters.setIdTransaccion(ma.IdTransaccion());
        _consultarEstadoCuenta_parameters.setIpApp(idaplicacion);
        _consultarEstadoCuenta_parameters.setUsrApp(ma.getIP());
        
        pe.com.claro.eai.crmservices.fija.consultarecibos.ConsultarEstadoCuentaResponse _consultarEstadoCuenta__return = port.consultarEstadoCuenta(_consultarEstadoCuenta_parameters);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA RECIBOS : consultarEstadoCuentaDetalle con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("fecha de emision : ").append(consultarEstadoCuentaFilter.getFecEmision()).append(",\n");
        sf.append("numero de pagina : ").append(consultarEstadoCuentaFilter.getNumeroPagina()).append(",\n");
        
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("AuditCode : ").append(_consultarEstadoCuenta__return.getAudit().getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_consultarEstadoCuenta__return.getAudit().getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_consultarEstadoCuenta__return.getAudit().getTxId().toString()).append(",\n");
        
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        if(_consultarEstadoCuenta__return.getListaEstadoCuenta().size() > 0)
        {
        	List<DetalleEstadoCuenta> lstDetalleEstadoCuenta = new ArrayList<DetalleEstadoCuenta>();
        	
        	for(ListaEstadoCuenta lc : _consultarEstadoCuenta__return.getListaEstadoCuenta())
        	{
        		DetalleEstadoCuenta ec = new DetalleEstadoCuenta();
        		ec.setNumFactura(lc.getNumeroFactura()!=null?lc.getNumeroFactura().toString():"");
        		ec.setCodDocumento(lc.getCodigoDocumento()!=null?lc.getCodigoDocumento().toString():"");
        		ec.setDesPago(lc.getDescripcionPago()!=null?lc.getDescripcionPago().toString():"");
        		ec.setFecDocumento(lc.getFechaDocumento()!=null?lc.getFechaDocumento().toString():"");
        		ec.setFecCancelada(lc.getFechaCancelada()!=null?lc.getFechaCancelada().toString():"");
        		ec.setFecRegistro(lc.getFechaRegistro()!=null?lc.getFechaRegistro().toString():"");
        		ec.setFecEmision(lc.getFechaEmision()!=null?lc.getFechaEmision().toString():"");
        		ec.setFecVencimiento(lc.getFechaVencimiento()!=null?lc.getFechaVencimiento().toString():"");
        		ec.setFlgCargoCuenta(lc.getFlagCargoCuenta()!=null?lc.getFlagCargoCuenta().toString():"");
        		ec.setValDocumento(lc.getValorDocumento()!=null?lc.getValorDocumento().toString():"");
        		ec.setValAbono(lc.getValorAbono()!=null?lc.getValorAbono().toString():"");
        		ec.setValSaldo(lc.getValorSaldo()!=null?lc.getValorSaldo().toString():"");
        		lstDetalleEstadoCuenta.add(ec);
        	}
        	return lstDetalleEstadoCuenta;
        }
        else
        {
        	return new ArrayList<DetalleEstadoCuenta>();
        }
	}
}