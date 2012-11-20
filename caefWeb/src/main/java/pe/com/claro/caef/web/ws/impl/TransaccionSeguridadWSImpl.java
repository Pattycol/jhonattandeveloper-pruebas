package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarPreguntas;
import pe.com.claro.caef.web.beans.GrabarRespuestas;
import pe.com.claro.caef.web.beans.UsuarioPreguntas;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.TransaccionSeguridadWS;
import pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.TransaccionSeguridad;
import pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.TransaccionSeguridad_Service;

@Component
public class TransaccionSeguridadWSImpl implements TransaccionSeguridadWS {

	private TransaccionSeguridad port;
	private TransaccionSeguridad_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(TransaccionSeguridadWSImpl.class);
	
	public TransaccionSeguridadWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_TRANSACCION_SEGURIDAD);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new TransaccionSeguridad_Service(url);
		port = cs.getTransaccionSeguridadSOAP();
	}
	
	public AuditTypes grabarPreguntas(Usuario usuario,
			GrabarPreguntas grabarPreguntas) {
		
		java.lang.String _grabarPreguntas_txId = ma.IdTransaccion();
        java.lang.String _grabarPreguntas_ipApp = idaplicacion;
        java.lang.String _grabarPreguntas_usrApp = ma.getIP();
        java.lang.String _grabarPreguntas_linea = grabarPreguntas.getLinea();
        java.lang.String _grabarPreguntas_tipoDocumento = grabarPreguntas.getTipoDocumento();
        java.lang.String _grabarPreguntas_nroDocumento = grabarPreguntas.getNroDocumento();
        java.lang.String _grabarPreguntas_email = grabarPreguntas.getEmail();
        java.lang.String _grabarPreguntas_flagTipoLinea = grabarPreguntas.getFlagTipoLinea();
        java.lang.String _grabarPreguntas_nroPregReg = grabarPreguntas.getNroPregReg();
        java.lang.String _grabarPreguntas_nroPregResp = grabarPreguntas.getNroPregResp();
        java.lang.String _grabarPreguntas_operacionE = grabarPreguntas.getOperacionE();
        java.lang.String _grabarPreguntas_operacionF = grabarPreguntas.getOperacionF();
        java.lang.String _grabarPreguntas_perfil = grabarPreguntas.getPerfil();
        java.lang.String _grabarPreguntas_flagEmail = grabarPreguntas.getFlagEmail();
        
        pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType _grabarPreguntas_preguntas = new pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType();
        java.util.List<pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas> _grabarPreguntas_preguntasPreguntas = new java.util.ArrayList<pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas>();

        
        for(int i=0; i < grabarPreguntas.getLstPreguntasType().size(); i++)
    	{
            pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas _grabarPreguntas_preguntasPreguntasVal1 = new pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas();
        	_grabarPreguntas_preguntasPreguntasVal1.setCodPregunta(grabarPreguntas.getLstPreguntasType().get(i).getCodPregunta());
        	_grabarPreguntas_preguntasPreguntasVal1.setRespuesta(grabarPreguntas.getLstPreguntasType().get(i).getRespuesta());
        	_grabarPreguntas_preguntasPreguntas.add(_grabarPreguntas_preguntasPreguntasVal1);    	
    	}
        
        _grabarPreguntas_preguntas.getPreguntas().addAll(_grabarPreguntas_preguntasPreguntas);
        
        pe.com.claro.eai.servicecommons.AuditType _grabarPreguntas__return = port.grabarPreguntas(_grabarPreguntas_txId, _grabarPreguntas_ipApp, _grabarPreguntas_usrApp, _grabarPreguntas_linea, _grabarPreguntas_tipoDocumento, _grabarPreguntas_nroDocumento, _grabarPreguntas_email, _grabarPreguntas_flagTipoLinea, _grabarPreguntas_nroPregReg, _grabarPreguntas_nroPregResp, _grabarPreguntas_operacionE, _grabarPreguntas_operacionF, _grabarPreguntas_perfil, _grabarPreguntas_flagEmail, _grabarPreguntas_preguntas);
        
        //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION SEGURIDAD : grabarPreguntas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append(" idTx : ").append(_grabarPreguntas_txId).append(",\n");
        sf.append("idApp : ").append(_grabarPreguntas_ipApp).append(",\n");
        sf.append("usrApp : ").append(_grabarPreguntas_usrApp).append(",\n");
        sf.append("linea : ").append(_grabarPreguntas_linea).append(",\n");
        sf.append("tipoDocumento : ").append(_grabarPreguntas_tipoDocumento).append(",\n");
        sf.append("nroDocumento : ").append(_grabarPreguntas_nroDocumento).append(",\n");
        sf.append("email : ").append(_grabarPreguntas_email).append(",\n");
        sf.append("flagTipoLinea : ").append(_grabarPreguntas_flagTipoLinea).append(",\n");
        sf.append("nroPregReg : ").append(_grabarPreguntas_nroPregReg).append(",\n");
        sf.append("nroPregResp : ").append(_grabarPreguntas_nroPregResp).append(",\n");
        sf.append("operacionE : ").append(_grabarPreguntas_operacionE).append(",\n");
        sf.append("operacionF : ").append(_grabarPreguntas_operacionF).append(",\n");
        sf.append("perfil : ").append(_grabarPreguntas_perfil).append(",\n");
        sf.append("flagEmail : ").append(_grabarPreguntas_flagEmail).append(",\n");  
        
        sf.append("AuditCode : ").append(_grabarPreguntas__return.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_grabarPreguntas__return.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_grabarPreguntas__return.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes auditTypes = new AuditTypes();
        auditTypes.setCodError(_grabarPreguntas__return.getErrorCode());
        auditTypes.setMsgError(_grabarPreguntas__return.getErrorMsg());
        
		return auditTypes;
	}

	public AuditTypes grabarRespuestas(Usuario usuario,
			GrabarRespuestas grabarRespuestas) {
		
		java.lang.String _grabarRespuestas_txId = ma.IdTransaccion();
        java.lang.String _grabarRespuestas_ipApp = idaplicacion;
        java.lang.String _grabarRespuestas_usrApp = ma.getIP();
        java.lang.String _grabarRespuestas_telefono = grabarRespuestas.getTelefono();
        java.lang.String _grabarRespuestas_operacion = grabarRespuestas.getOperacion();
        
        pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType _grabarRespuestas_respuestas = new pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType();
        java.util.List<pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas> _grabarRespuestas_respuestasPreguntas = new java.util.ArrayList<pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas>();
        pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas _grabarRespuestas_respuestasPreguntasVal1 = new pe.com.claro.eai.virtualchannelservices.cel.transaccionseguridad.PreguntasType.Preguntas();
        
        for(int i=0; i < grabarRespuestas.getLstPreguntasType().size(); i++)
    	{
        	_grabarRespuestas_respuestasPreguntasVal1.setCodPregunta(grabarRespuestas.getLstPreguntasType().get(i).getCodPregunta());
        	_grabarRespuestas_respuestasPreguntasVal1.setRespuesta(grabarRespuestas.getLstPreguntasType().get(i).getRespuesta());
        	_grabarRespuestas_respuestasPreguntas.add(_grabarRespuestas_respuestasPreguntasVal1);
        }
        
        _grabarRespuestas_respuestas.getPreguntas().addAll(_grabarRespuestas_respuestasPreguntas);
        
        
        pe.com.claro.eai.servicecommons.AuditType _grabarRespuestas__return = port.grabarRespuestas(_grabarRespuestas_txId, _grabarRespuestas_ipApp, _grabarRespuestas_usrApp, _grabarRespuestas_telefono, _grabarRespuestas_operacion, _grabarRespuestas_respuestas);
        
        //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION SEGURIDAD : grabarRespuestas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append(" idTx : ").append(_grabarRespuestas_txId).append(",\n");
        sf.append("idApp : ").append(_grabarRespuestas_ipApp).append(",\n");
        sf.append("usrApp : ").append(_grabarRespuestas_usrApp).append(",\n");
        sf.append("telefono : ").append(_grabarRespuestas_telefono).append(",\n");
        sf.append("operacion : ").append(_grabarRespuestas_operacion).append(",\n");
        
        sf.append("AuditCode : ").append(_grabarRespuestas__return.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_grabarRespuestas__return.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_grabarRespuestas__return.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes auditTypes = new AuditTypes();
        auditTypes.setCodError(_grabarRespuestas__return.getErrorCode());
        auditTypes.setMsgError(_grabarRespuestas__return.getErrorMsg());
        
		
		return auditTypes;
	}

	public AuditTypes usuarioPreguntas(Usuario usuario,
			UsuarioPreguntas usuarioPreguntas) {
		
		java.lang.String _usuarioPreguntas_txId = ma.IdTransaccion();
        java.lang.String _usuarioPreguntas_ipApp = idaplicacion;
        java.lang.String _usuarioPreguntas_usrApp = ma.getIP();
        java.lang.String _usuarioPreguntas_telefono = usuarioPreguntas.getTelefono();
        java.lang.String _usuarioPreguntas_operacion = usuarioPreguntas.getOperacion();
        java.lang.String _usuarioPreguntas_nroPregRes = usuarioPreguntas.getNroPregRes();
        java.lang.String _usuarioPreguntas_ipUsuario = ma.getIP();
        java.lang.String _usuarioPreguntas_totalPreg = usuarioPreguntas.getTotalPreg();
        java.lang.String _usuarioPreguntas_tipoDoc = usuarioPreguntas.getTipoDoc();
        java.lang.String _usuarioPreguntas_nroDoc = usuarioPreguntas.getNroDoc();
        
        pe.com.claro.eai.servicecommons.AuditType _usuarioPreguntas__return = port.usuarioPreguntas(_usuarioPreguntas_txId, _usuarioPreguntas_ipApp, _usuarioPreguntas_usrApp, _usuarioPreguntas_telefono, _usuarioPreguntas_operacion, _usuarioPreguntas_nroPregRes, _usuarioPreguntas_ipUsuario, _usuarioPreguntas_totalPreg, _usuarioPreguntas_tipoDoc, _usuarioPreguntas_nroDoc);
        
        //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION SEGURIDAD : usuarioPreguntas con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("txId : ").append(_usuarioPreguntas_txId).append(",\n");
        sf.append("idApp : ").append(_usuarioPreguntas_ipApp).append(",\n");
        sf.append("usrApp : ").append(_usuarioPreguntas_usrApp).append(",\n");
        sf.append("telefono : ").append(_usuarioPreguntas_telefono).append(",\n");
        sf.append("operacion : ").append(_usuarioPreguntas_operacion).append(",\n");
        sf.append("nroPregRes : ").append(_usuarioPreguntas_nroPregRes).append(",\n");
        sf.append("ipUsuario : ").append(_usuarioPreguntas_ipUsuario).append(",\n");
        sf.append("totalPreg : ").append(_usuarioPreguntas_totalPreg).append(",\n");
        sf.append("tipoDoc : ").append(_usuarioPreguntas_tipoDoc).append(",\n");
        sf.append("nroDoc : ").append(_usuarioPreguntas_nroDoc).append(",\n");
        
        sf.append("AuditCode : ").append(_usuarioPreguntas__return.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_usuarioPreguntas__return.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_usuarioPreguntas__return.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes auditTypes = new AuditTypes();
        auditTypes.setCodError(_usuarioPreguntas__return.getErrorCode());
        auditTypes.setMsgError(_usuarioPreguntas__return.getErrorMsg());
        
		return auditTypes;
	}

}
