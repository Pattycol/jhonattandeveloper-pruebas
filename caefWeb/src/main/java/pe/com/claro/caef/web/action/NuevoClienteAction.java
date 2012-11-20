package pe.com.claro.caef.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.NuevoClienteDatosFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.NuevoUsuario;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.NuevoClienteService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class NuevoClienteAction extends GeneralAction implements Preparable{

	private static final long serialVersionUID = 1L;
	
	private NuevoClienteDatosFilter nuevoClienteDatosFilter;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroNuevoUsuario;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipDocumento;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	@Autowired
	private NuevoClienteService nuevoClienteService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;

	public MensajesSeguridad getMensajesSeguridad() {
		return mensajesSeguridad;
	}

	public void setMensajesSeguridad(MensajesSeguridad mensajesSeguridad) {
		this.mensajesSeguridad = mensajesSeguridad;
	}

	public NuevoClienteAction() {
		
		nuevoClienteDatosFilter = new NuevoClienteDatosFilter();
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		lstConsultaDatosMaestroNuevoUsuario = new ArrayList<ConsultaDatosMaestro>();
		lstConsultaDatosMaestroTipDocumento = new ArrayList<ConsultaDatosMaestro>();
	}

	public void prepare() throws Exception {
		
		Usuario user = new Usuario();
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("error_registra_cliente");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);	
		
		consultarDatosMaestrosFilter.setCodigoCaso("TIPDIDE");
		consultarDatosMaestrosFilter.setCodigoEstado("");
		consultarDatosMaestrosFilter.setParametro1("");//tipDoc
		consultarDatosMaestrosFilter.setParametro2("");//numDoc
		consultarDatosMaestrosFilter.setParametro3("");//codCLI
		consultarDatosMaestrosFilter.setParametro4("");//Correo
		lstConsultaDatosMaestroTipDocumento= comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				
	}
	
	public void validate(){
		
		
		
	}
	
	public String redireccion(){
		
		return Action.SUCCESS;
	}
	
	public String cancelar()
	{			
		return "cancelar";
	}
	public String salida(){
		
		return "salida";
	}
	
	public String registrarCliente(){
		
		
		LOG.info("ENTRE AL METODO REGISTRAR USUARIO");
		
		/**OBTENER VALORES DE SESION**************/
		/*Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());*/
		Usuario user = new Usuario();
		/*****************************************/
		int code=0;
		String paso="exito"; 
		AuditTypes au = new AuditTypes();
		NuevoUsuario objNuevoUsuario = new NuevoUsuario();
		ObtenerDatosUsuarioFilter objObtenerUsuarioFilter = new ObtenerDatosUsuarioFilter();
		ObtenerDatosUsuario objObtenerUsuario = new ObtenerDatosUsuario();
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("error_registra_cliente");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);		
		
		try {
			
			if(nuevoClienteDatosFilter.getNumDocumento().trim().equals("")){
				addActionError(MENSAJES_CONFIG.NUEVO_CLIENTE_ERROR1);
			}
			else if(nuevoClienteDatosFilter.getCodCliente().trim().equals("")){
				addActionError(MENSAJES_CONFIG.NUEVO_CLIENTE_ERROR2);
			}
			else if(nuevoClienteDatosFilter.getCorreoCliente().trim().equals("")){
				addActionError(MENSAJES_CONFIG.NUEVO_CLIENTE_ERROR3);
			}
			else{
			
				consultarDatosMaestrosFilter.setCodigoCaso("VALCLINVOUSU");
				consultarDatosMaestrosFilter.setCodigoEstado("1");
				consultarDatosMaestrosFilter.setParametro1(nuevoClienteDatosFilter.getTipDocumento());
				consultarDatosMaestrosFilter.setParametro2(nuevoClienteDatosFilter.getNumDocumento().trim());
				consultarDatosMaestrosFilter.setParametro3(nuevoClienteDatosFilter.getCodCliente().trim());
				consultarDatosMaestrosFilter.setParametro4(nuevoClienteDatosFilter.getCorreoCliente().trim().toLowerCase());
				lstConsultaDatosMaestroNuevoUsuario= comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				for (ConsultaDatosMaestro cf : lstConsultaDatosMaestroNuevoUsuario) {
					objObtenerUsuarioFilter.setTxId("");
					objObtenerUsuarioFilter.setIpApp("");
					objObtenerUsuarioFilter.setTelefono(cf.getValorDatoMaestro1());
					objObtenerUsuarioFilter.setUsrApp("");	
				}
			
				objObtenerUsuario = nuevoClienteService.obtenerDatosUsuario(user, objObtenerUsuarioFilter);
				
				if(objObtenerUsuario.getLstListaRespuestasType() != null){
					paso = "exito";//OLVIDO CLAVE

						addActionMessage(MENSAJES_CONFIG.ERROR_REGISTRO_CLIENTE2);
				
					
					
				}
				
				else{
					
					objNuevoUsuario.setTelefono(objObtenerUsuarioFilter.getTelefono());
					objNuevoUsuario.setTxId(ma.IdTransaccion());
					au = nuevoClienteService.nuevoUsuario(user, objNuevoUsuario);
				
					paso = "exito";
	
					code=Integer.parseInt(au.getCodError());
					if(code==1){
						addActionMessage(MENSAJES_CONFIG.ERROR_REGISTRO_CLIENTE2);
					}else{
						addActionMessage(au.getMsgError());
					}
					
					
					
				
				}
			}
			
		} catch (Exception e) {
			LOG.info(e.toString());
		}
		
		return paso;
		
		
	}

	public NuevoClienteDatosFilter getNuevoClienteDatosFilter() {
		return nuevoClienteDatosFilter;
	}

	public void setNuevoClienteDatosFilter(
			NuevoClienteDatosFilter nuevoClienteDatosFilter) {
		this.nuevoClienteDatosFilter = nuevoClienteDatosFilter;
	}

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroNuevoUsuario() {
		return lstConsultaDatosMaestroNuevoUsuario;
	}

	public void setLstConsultaDatosMaestroNuevoUsuario(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroNuevoUsuario) {
		this.lstConsultaDatosMaestroNuevoUsuario = lstConsultaDatosMaestroNuevoUsuario;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroTipDocumento() {
		return lstConsultaDatosMaestroTipDocumento;
	}

	public void setLstConsultaDatosMaestroTipDocumento(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipDocumento) {
		this.lstConsultaDatosMaestroTipDocumento = lstConsultaDatosMaestroTipDocumento;
	}

}
