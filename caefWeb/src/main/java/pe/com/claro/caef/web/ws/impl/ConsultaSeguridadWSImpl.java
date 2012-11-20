package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ListaPreguntas;
import pe.com.claro.caef.web.beans.ListaRespuestasType;
import pe.com.claro.caef.web.beans.ListarPreguntasAleatorias;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasAleatorias;
import pe.com.claro.caef.web.beans.PreguntasType;
import pe.com.claro.caef.web.beans.ValidarPreguntasSecretas;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;
import pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ConsultaSeguridad;
import pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ConsultaSeguridad_Service;

@Component
public class ConsultaSeguridadWSImpl implements ConsultaSeguridadWS {

	private ConsultaSeguridad port;
	private ConsultaSeguridad_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(ConsultaSeguridadWSImpl.class);
	
	public ConsultaSeguridadWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_CONSULTA_SEGURIDAD);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new ConsultaSeguridad_Service(url);
		port = cs.getConsultaSeguridadSOAP();
	}
	
	public PreguntasAleatorias listarPreguntasAleatorias(Usuario usuario,
			ListarPreguntasAleatorias listarPreguntasAleatorias) {
		
		java.lang.String _listarPreguntasAleatorias_txId = ma.IdTransaccion();
        java.lang.String _listarPreguntasAleatorias_ipApp = idaplicacion;
        java.lang.String _listarPreguntasAleatorias_usrApp = ma.getIP();
        java.lang.String _listarPreguntasAleatorias_telefono = listarPreguntasAleatorias.getTelefono();
        java.lang.String _listarPreguntasAleatorias_operacion = listarPreguntasAleatorias.getOperacion();
        java.lang.String _listarPreguntasAleatorias_nroPreguntas = listarPreguntasAleatorias.getNroPreguntas();
        
        javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType> _listarPreguntasAleatorias_audit = new javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType>();
        javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ListaPreguntasType> _listarPreguntasAleatorias_listaPreguntas = new javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ListaPreguntasType>();
        
        port.listarPreguntasAleatorias(_listarPreguntasAleatorias_txId, _listarPreguntasAleatorias_ipApp, _listarPreguntasAleatorias_usrApp, _listarPreguntasAleatorias_telefono, _listarPreguntasAleatorias_operacion, _listarPreguntasAleatorias_nroPreguntas, _listarPreguntasAleatorias_audit, _listarPreguntasAleatorias_listaPreguntas);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA SEGURIDAD : listarPreguntasAleatorias con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        
        sf.append("telefono : ").append(_listarPreguntasAleatorias_telefono).append(",\n");
        sf.append("operación : ").append(_listarPreguntasAleatorias_operacion).append(",\n");
        sf.append("nro. preguntas : ").append(_listarPreguntasAleatorias_nroPreguntas).append(",\n");        
        
        sf.append("AuditCode : ").append(_listarPreguntasAleatorias_audit.value.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_listarPreguntasAleatorias_audit.value.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_listarPreguntasAleatorias_audit.value.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        PreguntasAleatorias pa = new  PreguntasAleatorias();
        pa.setCodError(_listarPreguntasAleatorias_audit.value.getErrorCode());
        pa.setMsgError(_listarPreguntasAleatorias_audit.value.getErrorMsg());
        
        List<PreguntasType> lstPreguntasAleatorias = new ArrayList<PreguntasType>();
        if(_listarPreguntasAleatorias_listaPreguntas.value.getPreguntas().size() > 0)
        {
        	for(int i = 0; i  < _listarPreguntasAleatorias_listaPreguntas.value.getPreguntas().size(); i++)
        	{
        		PreguntasType pt = new PreguntasType();
        		pt.setCodPregunta(_listarPreguntasAleatorias_listaPreguntas.value.getPreguntas().get(i).getCodPregunta().toString());
        		pt.setRespuesta(_listarPreguntasAleatorias_listaPreguntas.value.getPreguntas().get(i).getDescripcion().toString());
        		pt.setNumPregunta(String.valueOf(i+1));
        		lstPreguntasAleatorias.add(pt);
        	}
        	pa.setLstPreguntas(lstPreguntasAleatorias);
        }
        
        return pa;
	}

	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario,
			ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter) {
		
		StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA SEGURIDAD : obtenerDatosPreguntas con los Parametros : \n [" );
        
		java.lang.String _obtenerDatosPreguntas_txId = ma.IdTransaccion();
        java.lang.String _obtenerDatosPreguntas_ipApp = idaplicacion;
        java.lang.String _obtenerDatosPreguntas_usrApp = ma.getIP();
        
        javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType> _obtenerDatosPreguntas_audit = new javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType>();
        javax.xml.ws.Holder<java.lang.String> _obtenerDatosPreguntas_nroPregReg = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<java.lang.String> _obtenerDatosPreguntas_nroPregRes = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ListaPreguntasInfoType> _obtenerDatosPreguntas_listaPreguntas = new javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ListaPreguntasInfoType>();
        
        port.obtenerDatosPreguntas(_obtenerDatosPreguntas_txId, _obtenerDatosPreguntas_ipApp, _obtenerDatosPreguntas_usrApp, _obtenerDatosPreguntas_audit, _obtenerDatosPreguntas_nroPregReg, _obtenerDatosPreguntas_nroPregRes, _obtenerDatosPreguntas_listaPreguntas);
        
      //LOG WEB SERVICE
        
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("txId : ").append(_obtenerDatosPreguntas_txId).append(",\n");
        sf.append("ipApp : ").append(_obtenerDatosPreguntas_ipApp).append(",\n");
        sf.append("usrApp : ").append(_obtenerDatosPreguntas_usrApp).append(",\n");
        
        sf.append("AuditCode : ").append(_obtenerDatosPreguntas_audit.value.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_obtenerDatosPreguntas_audit.value.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_obtenerDatosPreguntas_audit.value.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        ObtenerDatosPreguntas ovp = new ObtenerDatosPreguntas();
        ovp.setCodError(_obtenerDatosPreguntas_audit.value.getErrorCode());
        ovp.setMsgError(_obtenerDatosPreguntas_audit.value.getErrorMsg());
        ovp.setNroPregReg(_obtenerDatosPreguntas_nroPregReg!=null?_obtenerDatosPreguntas_nroPregReg.value.toString():"");
        ovp.setNroPregRes(_obtenerDatosPreguntas_nroPregRes!=null?_obtenerDatosPreguntas_nroPregRes.value.toString():"");
        
        if(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().size() > 0)
        {
        	List<ListaPreguntas> lstListaPreguntas = new ArrayList<ListaPreguntas>();
        	
        	for(int i=0; i < _obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().size(); i++)
        	{
        		ListaPreguntas lp = new ListaPreguntas();
        		
        		lp.setCodigo(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getCodigo());
        		lp.setDescripcion(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getDescripcion());
        		lp.setEstado(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getEstado());
        		lp.setFechaCrea(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getFechaCrea());
        		lp.setFechaMod(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getFechaMod());
        		lp.setUsuarioCrea(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getUsuarioCrea());
        		lp.setUsuarioMod(_obtenerDatosPreguntas_listaPreguntas.value.getPreguntas().get(i).getUsuarioMod());
        		lstListaPreguntas.add(lp);
        	}
        	
        	ovp.setLstListaPreguntas(lstListaPreguntas);
        }
        else
        	ovp.setLstListaPreguntas(null);
        
        return ovp;

	}
	public ObtenerDatosUsuario obtenerDatosUsuario(Usuario usuario, ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter)
	{
		StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA SEGURIDAD : obtenerDatosUsuario con los Parametros : \n [" );
        
		java.lang.String _obtenerDatosUsuario_txId = ma.IdTransaccion();
        java.lang.String _obtenerDatosUsuario_ipApp = idaplicacion;
        java.lang.String _obtenerDatosUsuario_usrApp = ma.getIP();
        java.lang.String _obtenerDatosUsuario_telefono = obtenerDatosUsuarioFilter.getTelefono();
        
        javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType> _obtenerDatosUsuario_audit = new javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType>();
        javax.xml.ws.Holder<java.lang.String> _obtenerDatosUsuario_nroPreguntas = new javax.xml.ws.Holder<java.lang.String>();
        javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ListaRespuestasType> _obtenerDatosUsuario_listaRespuestas = new javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.ListaRespuestasType>();
        
        port.obtenerDatosUsuario(_obtenerDatosUsuario_txId, _obtenerDatosUsuario_ipApp, _obtenerDatosUsuario_usrApp, _obtenerDatosUsuario_telefono, _obtenerDatosUsuario_audit, _obtenerDatosUsuario_nroPreguntas, _obtenerDatosUsuario_listaRespuestas);
        
      //LOG WEB SERVICE
        
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("txId : ").append(_obtenerDatosUsuario_txId).append(",\n");
        sf.append("ipApp : ").append(_obtenerDatosUsuario_ipApp).append(",\n");
        sf.append("usrApp : ").append(_obtenerDatosUsuario_usrApp).append(",\n");
        sf.append("telefono : ").append(_obtenerDatosUsuario_telefono).append(",\n");
        
        sf.append("AuditCode : ").append(_obtenerDatosUsuario_audit.value.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_obtenerDatosUsuario_audit.value.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_obtenerDatosUsuario_audit.value.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        ObtenerDatosUsuario odu = new ObtenerDatosUsuario();
        odu.setCodError(_obtenerDatosUsuario_audit.value.getErrorCode());
        odu.setMsgError(_obtenerDatosUsuario_audit.value.getErrorMsg());
        odu.setNroPreguntas(_obtenerDatosUsuario_nroPreguntas!=null?_obtenerDatosUsuario_nroPreguntas.value.toString():"");
        
        if(_obtenerDatosUsuario_listaRespuestas.value.getRespuestas().size() > 0)
        {
        	List<ListaRespuestasType> lstListaRespuestasType = new ArrayList<ListaRespuestasType>();
        	
        	for(int i=0; i < _obtenerDatosUsuario_listaRespuestas.value.getRespuestas().size(); i++)
        	{
        		ListaRespuestasType lrt = new ListaRespuestasType();
        		lrt.setCodigo(_obtenerDatosUsuario_listaRespuestas.value.getRespuestas().get(i).getCodigo());
        		lrt.setDescripcion(_obtenerDatosUsuario_listaRespuestas.value.getRespuestas().get(i).getDescripcion());
        		lrt.setKey(_obtenerDatosUsuario_listaRespuestas.value.getRespuestas().get(i).getKey().toString());
        		lrt.setContador(i+1);
        		lstListaRespuestasType.add(lrt);
        	}
        	odu.setLstListaRespuestasType(lstListaRespuestasType);
        }
        else
        	odu.setLstListaRespuestasType(null);
        
        return odu; 
	}
	
	public AuditTypes validarPreguntasSecretas(Usuario usuario, ValidarPreguntasSecretas validarPreguntasSecretas)
	{
		java.lang.String _validarPreguntasSecretas_txId = ma.IdTransaccion();
        java.lang.String _validarPreguntasSecretas_ipApp = idaplicacion;
        java.lang.String _validarPreguntasSecretas_usrApp = ma.getIP();
        java.lang.String _validarPreguntasSecretas_telefono = validarPreguntasSecretas.getTelefono();
        java.lang.String _validarPreguntasSecretas_flagTipoLinea = validarPreguntasSecretas.getFlagTipoLinea();
        java.lang.String _validarPreguntasSecretas_tipoDocumento = validarPreguntasSecretas.getTipoDocumento();
        java.lang.String _validarPreguntasSecretas_nroDocumento = validarPreguntasSecretas.getNroDocumento();
        java.lang.String _validarPreguntasSecretas_email = validarPreguntasSecretas.getEmail();
        java.lang.String _validarPreguntasSecretas_clave = validarPreguntasSecretas.getClave();
        java.lang.String _validarPreguntasSecretas_operacionT = validarPreguntasSecretas.getOperacionT();
        
        pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.PreguntasType _validarPreguntasSecretas_preguntas = new pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.PreguntasType();
        java.util.List<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.PreguntasType.Preguntas> _validarPreguntasSecretas_preguntasPreguntas = new java.util.ArrayList<pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.PreguntasType.Preguntas>();
        pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.PreguntasType.Preguntas _validarPreguntasSecretas_preguntasPreguntasVal1 = new pe.com.claro.eai.virtualchannelservices.cel.consultaseguridad.PreguntasType.Preguntas();
        
        for(int i=0; i < validarPreguntasSecretas.getLstPreguntasType().size(); i++)
    	{
        	_validarPreguntasSecretas_preguntasPreguntasVal1.setCodPregunta(validarPreguntasSecretas.getLstPreguntasType().get(i).getCodPregunta());
        	_validarPreguntasSecretas_preguntasPreguntasVal1.setRespuesta(validarPreguntasSecretas.getLstPreguntasType().get(i).getRespuesta());
        	_validarPreguntasSecretas_preguntasPreguntas.add(_validarPreguntasSecretas_preguntasPreguntasVal1);
        }
        
        _validarPreguntasSecretas_preguntas.getPreguntas().addAll(_validarPreguntasSecretas_preguntasPreguntas);
        
        pe.com.claro.eai.servicecommons.AuditType _validarPreguntasSecretas__return = port.validarPreguntasSecretas(_validarPreguntasSecretas_txId, _validarPreguntasSecretas_ipApp, _validarPreguntasSecretas_usrApp, _validarPreguntasSecretas_telefono, _validarPreguntasSecretas_flagTipoLinea, _validarPreguntasSecretas_tipoDocumento, _validarPreguntasSecretas_nroDocumento, _validarPreguntasSecretas_email, _validarPreguntasSecretas_clave, _validarPreguntasSecretas_operacionT, _validarPreguntasSecretas_preguntas);
        
        //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA SEGURIDAD : validarPreguntasSecretas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("txId : ").append(_validarPreguntasSecretas_txId).append(",\n");
        sf.append("ipApp : ").append(_validarPreguntasSecretas_ipApp).append(",\n");
        sf.append("usrApp : ").append(_validarPreguntasSecretas_usrApp).append(",\n");
        sf.append("telefono : ").append(_validarPreguntasSecretas_telefono).append(",\n");
        sf.append("flagTipoLinea : ").append(_validarPreguntasSecretas_flagTipoLinea).append(",\n");
        sf.append("tipoDocumento : ").append(_validarPreguntasSecretas_tipoDocumento).append(",\n");
        sf.append("nroDocumento : ").append(_validarPreguntasSecretas_nroDocumento).append(",\n");
        sf.append("email : ").append(_validarPreguntasSecretas_email).append(",\n");
        sf.append("clave : ").append(_validarPreguntasSecretas_clave).append(",\n");
        sf.append("operacionT : ").append(_validarPreguntasSecretas_operacionT).append(",\n");
        
        sf.append("AuditCode : ").append(_validarPreguntasSecretas__return.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_validarPreguntasSecretas__return.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_validarPreguntasSecretas__return.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes auditTypes = new AuditTypes();
        
        auditTypes.setCodError(_validarPreguntasSecretas__return.getErrorCode().toString());
        auditTypes.setMsgError(_validarPreguntasSecretas__return.getErrorMsg().toString());
        
        return auditTypes;
        
	}

}
