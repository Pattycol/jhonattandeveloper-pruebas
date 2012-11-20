package pe.com.claro.caef.web.ws.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.Autenticar;
import pe.com.claro.caef.web.beans.AutenticarResponse;
import pe.com.claro.caef.web.beans.ListaCuenta;
import pe.com.claro.caef.web.beans.ListaMenu;
import pe.com.claro.caef.web.beans.UsuarioWebType;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.ws.ConsultaAutenticacionWS;
import pe.com.claro.eai.virtualchannelservices.cel.consultaautenticacion.ConsultaAutenticacion;
import pe.com.claro.eai.virtualchannelservices.cel.consultaautenticacion.ConsultaAutenticacionSOAPQSService;

@Component
public class ConsultaAutenticacionWSImpl implements ConsultaAutenticacionWS {

	private ConsultaAutenticacion port;
	private ConsultaAutenticacionSOAPQSService cs;
	private String idaplicacion;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	static final Logger log = Logger.getLogger(ConsultaAutenticacionWSImpl.class);
	
	private ConsultaAutenticacionWSImpl() throws MalformedURLException
	{
		URL url = new URL(PropertiesCAEF.WS_CONSULTA_AUTENTICACION);
		idaplicacion = new String(MetodosAuditoria.IDAPLICACION);
		cs = new ConsultaAutenticacionSOAPQSService(url);
		port = cs.getConsultaAutenticacionSOAPQSPort();
	}
	
	public AutenticarResponse autenticar(Usuario usuario, Autenticar autenticar) {
		// TODO Auto-generated method stub
		
		StringBuffer sf = new StringBuffer();
        sf.append("Invocando el servicio web CONSULTA AUTENTICACION : autenticar con los Parametros : \n [" );
        
		java.lang.String _autenticar_txId = ma.IdTransaccion();
        java.lang.String _autenticar_telefono = autenticar.getTelefono();
        java.lang.String _autenticar_clave = autenticar.getClave();
        java.lang.String _autenticar_idApp = idaplicacion;
        
        javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType> _autenticar_audit = new javax.xml.ws.Holder<pe.com.claro.eai.servicecommons.AuditType>();       
        javax.xml.ws.Holder<java.lang.Boolean> _autenticar_isValidar = new javax.xml.ws.Holder<java.lang.Boolean>();
        javax.xml.ws.Holder<pe.com.claro.eai.virtualchannel.cel.UsuarioWebType> _autenticar_usuarioWeb = new javax.xml.ws.Holder<pe.com.claro.eai.virtualchannel.cel.UsuarioWebType>();
        javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaautenticacion.ItemListaMenuType> _autenticar_listaMenu = new javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaautenticacion.ItemListaMenuType>();
        javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaautenticacion.AutenticarResponse.ListaCuentas> _autenticar_listaCuentas = new javax.xml.ws.Holder<pe.com.claro.eai.virtualchannelservices.cel.consultaautenticacion.AutenticarResponse.ListaCuentas>();
        
        port.autenticar(_autenticar_txId, _autenticar_telefono, _autenticar_clave, _autenticar_idApp, _autenticar_audit, _autenticar_isValidar, _autenticar_usuarioWeb, _autenticar_listaMenu, _autenticar_listaCuentas);
        
      //LOG WEB SERVICE
        
        sf.append("codigo usuario : ").append( usuario.getCodigoUsuario()).append(",\n");
        sf.append("codigo producto : ").append(usuario.getCodigoProducto()).append(",\n");
        sf.append("codigo servicio : ").append(usuario.getCodigoServicio()).append(",\n");
        sf.append(" idTransaccion : ").append(ma.IdTransaccion()).append(",\n");
        
        sf.append("usrApp : ").append(ma.getIP()).append(",\n");
        sf.append("TxId : ").append(_autenticar_txId).append(",\n");
        sf.append("Telefono : ").append(_autenticar_telefono).append(",\n");
        sf.append("Clave : ").append(_autenticar_clave).append(",\n");
        sf.append("IdApp : ").append(_autenticar_idApp).append(",\n");
        
        sf.append("AuditCode : ").append(_autenticar_audit.value.getErrorCode().toString()).append(",\n");
        sf.append("AuditMsg : ").append(_autenticar_audit.value.getErrorMsg().toString()).append(",\n");
        sf.append("AuditTxId : ").append(_autenticar_audit.value.getTxId().toString()).append(",\n");
        sf.append("]");
        
        log.info(sf.toString());
        //-----------------------
        
        AutenticarResponse ar = new AutenticarResponse();
        if(_autenticar_audit.value.getErrorCode().equals("0"))
        {
        	ar.setCodError(_autenticar_audit.value.getErrorCode());
        	ar.setMsgError(_autenticar_audit.value.getErrorMsg());
        	ar.setIsValidar(_autenticar_isValidar.value);//!=null?Boolean.valueOf(_autenticar_isValidar.toString()):false);
        	
        	UsuarioWebType ut = new UsuarioWebType();
        	ut.setCodAplicacion(_autenticar_usuarioWeb.value.getCodAplicacion()!=null?_autenticar_usuarioWeb.value.getCodAplicacion().toString():"");
        	ut.setCodPerfil(String.valueOf(_autenticar_usuarioWeb.value.getCodPerfil())!=null?String.valueOf(_autenticar_usuarioWeb.value.getCodPerfil()):"");
        	ut.setCuentaPadre(_autenticar_usuarioWeb.value.getCuentaPadre()!=null?_autenticar_usuarioWeb.value.getCuentaPadre().toString():"");
        	ut.setCustcode(_autenticar_usuarioWeb.value.getCustcode()!=null?_autenticar_usuarioWeb.value.getCustcode().toString():"");
        	ut.setCustomerId(_autenticar_usuarioWeb.value.getCustomerId()!=null?_autenticar_usuarioWeb.value.getCustomerId().toString():"");
        	ut.setEmail(_autenticar_usuarioWeb.value.getEmail()!=null?_autenticar_usuarioWeb.value.getEmail().toString():"");
        	ut.setFlagAceptaCondicion(_autenticar_usuarioWeb.value.getFlagAceptaCondicion()!=null?_autenticar_usuarioWeb.value.getFlagAceptaCondicion().toString():"");
        	ut.setFlagActualizaDatos(_autenticar_usuarioWeb.value.getFlagActualizaDatos()!=null?_autenticar_usuarioWeb.value.getFlagActualizaDatos().toString():"");
        	ut.setFlagLlamadas(_autenticar_usuarioWeb.value.getFlagLlamadas()!=null?_autenticar_usuarioWeb.value.getFlagLlamadas().toString():"");
        	ut.setFlagManualCliente(_autenticar_usuarioWeb.value.getFlagManualCliente()!=null?_autenticar_usuarioWeb.value.getFlagManualCliente().toString():"");
        	ut.setFlagACEPTOCB32(_autenticar_usuarioWeb.value.getFlagACEPTOCB32()!=null?_autenticar_usuarioWeb.value.getFlagACEPTOCB32().toString():"");
        	ut.setFlagPortOut(_autenticar_usuarioWeb.value.getFlagPortOut()!=null?_autenticar_usuarioWeb.value.getFlagPortOut().toString():"");
        	ut.setLocalidad(_autenticar_usuarioWeb.value.getLocalidad()!=null?_autenticar_usuarioWeb.value.getLocalidad().toString():"");
        	ut.setLogin(_autenticar_usuarioWeb.value.getLogin()!=null?_autenticar_usuarioWeb.value.getLogin().toString():"");
        	ut.setMensaje(_autenticar_usuarioWeb.value.getMensaje()!=null?_autenticar_usuarioWeb.value.getMensaje().toString():"");
        	ut.setNick(_autenticar_usuarioWeb.value.getNick()!=null?_autenticar_usuarioWeb.value.getNick().toString():"");
        	ut.setNivel(_autenticar_usuarioWeb.value.getNivel()!=null?_autenticar_usuarioWeb.value.getNivel().toString():"");
        	ut.setNombre(_autenticar_usuarioWeb.value.getNombre()!=null?_autenticar_usuarioWeb.value.getNombre().toString():"");
        	ut.setNombreCompleto(_autenticar_usuarioWeb.value.getNombreCompleto()!=null?_autenticar_usuarioWeb.value.getNombreCompleto().toString():"");
        	ut.setNroDocumento(_autenticar_usuarioWeb.value.getNroDocumento()!=null?_autenticar_usuarioWeb.value.getNroDocumento().toString():"");
        	ut.setPassword(_autenticar_usuarioWeb.value.getPassword()!=null?_autenticar_usuarioWeb.value.getPassword().toString():"");
        	ut.setPeriodo(_autenticar_usuarioWeb.value.getPeriodo()!=null?_autenticar_usuarioWeb.value.getPeriodo().toString():"");
        	ut.setRazonSocial(_autenticar_usuarioWeb.value.getRazonSocial()!=null?_autenticar_usuarioWeb.value.getRazonSocial():"");
        	ut.setTdesKey(_autenticar_usuarioWeb.value.getTdesKey());
        	ut.setTelefono(_autenticar_usuarioWeb.value.getTelefono()!=null?_autenticar_usuarioWeb.value.getTelefono().toString():"");
        	ut.setTelefonoAuxMain(_autenticar_usuarioWeb.value.getTelefonoAuxMain()!=null?_autenticar_usuarioWeb.value.getTelefonoAuxMain().toString():"");
        	ut.setTipoCliente(_autenticar_usuarioWeb.value.getTipoCliente()!=null?_autenticar_usuarioWeb.value.getTipoCliente().toString():"");
        	ut.setTipoCuenta(_autenticar_usuarioWeb.value.getTipoCuenta()!=null?_autenticar_usuarioWeb.value.getTipoCuenta().toString():"");
        	ut.setTipoNivel(String.valueOf(_autenticar_usuarioWeb.value.getTipoNivel())!=null?String.valueOf(_autenticar_usuarioWeb.value.getTipoNivel()):"");
        	ut.setTipoUsuario(_autenticar_usuarioWeb.value.getTipoUsuario()!=null?_autenticar_usuarioWeb.value.getTipoUsuario().toString():"");
        	ut.setUsuarioClaro(_autenticar_usuarioWeb.value.getUsuarioClaro()!=null?_autenticar_usuarioWeb.value.getUsuarioClaro().toString():"");
        	ar.setUsuarioWebType(ut);
        	
        	if(_autenticar_listaMenu.value != null)
        	{
        		List<ListaMenu> lstListaMenu = new ArrayList<ListaMenu>(); 
        		for(int i = 0; i < _autenticar_listaMenu.value.getMenu().size(); i++)
        		{
        			ListaMenu lm = new ListaMenu();
        			lm.setLevelCoord(_autenticar_listaMenu.value.getMenu().get(i).getLevelCoord().toString());
        			lm.setListChildMenus(String.valueOf(_autenticar_listaMenu.value.getMenu().get(i).getListChildMenus()));
        			lm.setMenuId(_autenticar_listaMenu.value.getMenu().get(i).getMenuId().toString());
        			lm.setMenuName(_autenticar_listaMenu.value.getMenu().get(i).getMenuName().toString());
        			lm.setUrl(_autenticar_listaMenu.value.getMenu().get(i).getUrl().toString());
        			lstListaMenu.add(lm);
        		}
        		ar.setLstListaMenu(lstListaMenu);
        	}
        	
        	if(_autenticar_listaCuentas.value != null)
        	{
        		List<ListaCuenta> lstListaCuenta = new ArrayList<ListaCuenta>();
        		for(int i = 0; i < _autenticar_listaCuentas.value.getCuenta().size(); i++)
        		{
        			ListaCuenta lc = new ListaCuenta();
        			lc.setCsLevel(_autenticar_listaCuentas.value.getCuenta().get(i).getCsLevel().toString());
        			lc.setCustomerId(_autenticar_listaCuentas.value.getCuenta().get(i).getCustomerId().toString());
        			lc.setCustomerIdHigh(_autenticar_listaCuentas.value.getCuenta().get(i).getCustomerIdHigh().toString());
        			lc.setNroCuenta(_autenticar_listaCuentas.value.getCuenta().get(i).getNroCuenta().toString());
        			lstListaCuenta.add(lc);
        		}
        		ar.setLstListaCuenta(lstListaCuenta);
        	}
        }
        else
        {
        	ar.setCodError(_autenticar_audit.value.getErrorCode());
        	ar.setMsgError(_autenticar_audit.value.getErrorMsg());
        	ar.setIsValidar(_autenticar_isValidar.value);
        }
        
        return ar;
	}

}
