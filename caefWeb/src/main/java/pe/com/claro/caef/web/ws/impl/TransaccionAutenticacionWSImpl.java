package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.NuevoUsuario;
import pe.com.claro.caef.web.beans.OlvideClave;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.TransaccionAutenticacionWS;
import pe.com.claro.eai.virtualchannelservices.cel.transaccionautenticacion.ObjectFactory;
import pe.com.claro.eai.virtualchannelservices.cel.transaccionautenticacion.TransaccionAutenticacion;
import pe.com.claro.eai.virtualchannelservices.cel.transaccionautenticacion.TransaccionAutenticacion_Service;

@Component
public class TransaccionAutenticacionWSImpl implements
		TransaccionAutenticacionWS {
	
	private TransaccionAutenticacion port;
	private TransaccionAutenticacion_Service cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(TransaccionAutenticacionWSImpl.class);

	public TransaccionAutenticacionWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_TRANSACCION_AUTENTICACION);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new TransaccionAutenticacion_Service(url);
		port = cs.getTransaccionAutenticacionSOAP();
	}
	
	public AuditTypes nuevoUsuario(Usuario usuario, NuevoUsuario nuevoUsuario) {
		
		ObjectFactory of = new ObjectFactory();
		
		java.lang.String _nuevoUsuario_txId = ma.IdTransaccion();
        java.lang.String _nuevoUsuario_telefono = nuevoUsuario.getTelefono();
        pe.com.claro.eai.servicecommons.AuditType _nuevoUsuario__return = port.nuevoUsuario(_nuevoUsuario_txId, _nuevoUsuario_telefono);
		
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION AUTENTICACION : nuevoUsuario con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("txId : ").append(_nuevoUsuario_txId).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("telefono : ").append(_nuevoUsuario_telefono).append(",\n");
        
        sf.append("AuditCode : ").append(_nuevoUsuario__return.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_nuevoUsuario__return.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_nuevoUsuario__return.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes auditTypes = new AuditTypes();
        auditTypes.setCodError(_nuevoUsuario__return.getErrorCode());
        auditTypes.setMsgError(_nuevoUsuario__return.getErrorMsg());      
        
		return auditTypes;
	}

	public AuditTypes olvideClave(Usuario usuario, OlvideClave olvideClave) {
		
		java.lang.String _olvideClave_txId = ma.IdTransaccion();
        java.lang.String _olvideClave_telefono = olvideClave.getTelefono();
        pe.com.claro.eai.servicecommons.AuditType _olvideClave__return = port.olvideClave(_olvideClave_txId, _olvideClave_telefono);
        
      //LOG WEB SERVICE
        StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web TRANSACCION AUTENTICACION : olvideClave con los Parametros : \n [" );
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        
        sf.append("idTx : ").append(_olvideClave_txId).append(",\n");
        sf.append("idApp : ").append(idaplicacion).append(",\n");
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("telefono : ").append(_olvideClave_telefono).append(",\n");
        
        sf.append("AuditCode : ").append(_olvideClave__return.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_olvideClave__return.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_olvideClave__return.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AuditTypes auditTypes = new AuditTypes();
        auditTypes.setCodError(_olvideClave__return.getErrorCode());
        auditTypes.setMsgError(_olvideClave__return.getErrorMsg());
		
        return auditTypes;
	}

}
