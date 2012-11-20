package pe.com.claro.caef.web.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
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
import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.action.filter.OlvidoClaveActionFilter;
import pe.com.claro.caef.web.auth.SeguridadBean;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.NuevoUsuario;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.NuevoClienteService;
import pe.com.claro.caef.web.services.PaqueteBusinessDelegate;
import pe.com.claro.caef.web.util.CorreoDatos;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.PropertiesCAEF;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class OlvidoClaveAction extends GeneralAction implements Preparable {
	

	
	private OlvidoClaveActionFilter olvidoClaveActionFilter;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroOlvidoClave;
	
	@Autowired
	private NuevoClienteService nuevoClienteService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired					  
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	private InputStream inputStream;
	
	
	public OlvidoClaveAction(){
		olvidoClaveActionFilter = new OlvidoClaveActionFilter();
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		lstConsultaDatosMaestroOlvidoClave = new ArrayList<ConsultaDatosMaestro>();
		cargarMensajesService=PaqueteBusinessDelegate.getCargarMensajesServiceImpl();
		nuevoClienteService=PaqueteBusinessDelegate.getNuevoClienteServiceImpl();
		comunService=PaqueteBusinessDelegate.getComunServiceImpl();
	}
	
    public InputStream getInputStream() {
        return inputStream;
    }
	
	public MensajesSeguridadFilter getMensajesSeguridadFilter() {
		return mensajesSeguridadFilter;
	}

	public void setMensajesSeguridadFilter(
			MensajesSeguridadFilter mensajesSeguridadFilter) {
		this.mensajesSeguridadFilter = mensajesSeguridadFilter;
	}

	public MensajesSeguridad getMensajesSeguridad() {
		return mensajesSeguridad;
	}

	public void setMensajesSeguridad(MensajesSeguridad mensajesSeguridad) {
		this.mensajesSeguridad = mensajesSeguridad;
	}

	public void prepare() throws Exception
	{
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("olvidoClave");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
	}
	
	public String input()
	{
		return Action.INPUT;
	}	
	
	public String execute()
	{
		return Action.SUCCESS;
	}
	
	public String cancelar()
	{			
		return "cancelar";
	}
	
	@SuppressWarnings("deprecation")
	public String submit()
	{
		//OBTENER VALORES DE SESION
		//Usuario userCodSer = new Usuario();
		//Map codServicio = ActionContext.getContext().getSession();
		//userCodSer = (Usuario)codServicio.get("codServicio");
		
		//Usuario user = new Usuario();
		//user = getUsuario();
		//user.setCodigoServicio(userCodSer.getCodigoServicio());	
		//user.setCodigoProducto(userCodSer.getCodigoProducto());
		String redirect=null;
		Usuario user = new Usuario();
		String correo=olvidoClaveActionFilter.getCorreoCliente();
		if(correo.equals("")){
			redirect="olvidoClave";
			addActionError(MENSAJES_CONFIG.OLVIDA_CALVE_LOG_MENSAJE3);
		}else{
			//String redirect="";
			AuditTypes au = new AuditTypes();
			NuevoUsuario objNuevoUsuario = new NuevoUsuario();
			ObtenerDatosUsuarioFilter objObtenerUsuarioFilter = new ObtenerDatosUsuarioFilter();
			ObtenerDatosUsuario objObtenerUsuario = new ObtenerDatosUsuario();
			ObtenerDatosPreguntas objObtenerPreguntas = new ObtenerDatosPreguntas();
			ObtenerDatosPreguntasFilter objObtenerPreguntasFilter = new ObtenerDatosPreguntasFilter();
			
			try {
				
				consultarDatosMaestrosFilter.setCodigoCaso("VALCLICAESGA");
				consultarDatosMaestrosFilter.setCodigoEstado("1");
				consultarDatosMaestrosFilter.setParametro1(olvidoClaveActionFilter.getCorreoCliente());
				consultarDatosMaestrosFilter.setParametro2("");
				consultarDatosMaestrosFilter.setParametro3("");
				consultarDatosMaestrosFilter.setParametro4("");
				lstConsultaDatosMaestroOlvidoClave= comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				
				if(lstConsultaDatosMaestroOlvidoClave.size()>0){
					
					
					for (ConsultaDatosMaestro cf : lstConsultaDatosMaestroOlvidoClave) {
						objObtenerUsuarioFilter.setTxId("");
						objObtenerUsuarioFilter.setIpApp("");
						objObtenerUsuarioFilter.setTelefono(cf.getValorDatoMaestro1());
						objObtenerUsuarioFilter.setUsrApp("");	
						
						
						
						
						
						
					}
				
					objObtenerUsuario = nuevoClienteService.obtenerDatosUsuario(user, objObtenerUsuarioFilter);
					
					if(!objObtenerUsuario.getCodError().equals("1")){
						
						
						
						redirect = "olvidoClave";//+mensajeError
					}
					else if(lstConsultaDatosMaestroOlvidoClave.get(0).getValorDatoMaestro4().equals("")){//codCliente es ?
						redirect = "nuevoUsuario";
					}
					else{
						
						if(objObtenerUsuario.getNroPreguntas().equals("0")){
							
							CorreoDatos datos = new CorreoDatos();
							datos.setDestinatario(olvidoClaveActionFilter.getCorreoCliente());
							datos.setAsunto("Olvido Clave");
							datos.setContenido(PropertiesCAEF.cuerpoMensaje(user.getNombreUsuario()));
							
							try{
								
								enviarCorreoService.enviarCorreo(datos);
								inputStream = new StringBufferInputStream(MENSAJES_CONFIG.OLVIDA_CLAVE_LOG_MENSAJE1);
								redirect = "olvidoClave";
								
							}catch(Exception e)
							{
								inputStream = new StringBufferInputStream(MENSAJES_CONFIG.OLVIDA_CALVE_LOG_MENSAJE2);
								LOG.info(e.toString());
								redirect = "olvidoClave";
							}
							
						}
						else{
							
							objObtenerPreguntas = nuevoClienteService.obtenerDatosPreguntas(user, objObtenerPreguntasFilter);
							
							if(objObtenerPreguntas.getNroPregRes().equals("0")){
								redirect = "olvidoClave";
							}
							else{
								
								Map<String, Object> session = ActionContext.getContext().getSession();
								
								SeguridadBean objSeguridad = new SeguridadBean();
								objSeguridad.setTelefono(lstConsultaDatosMaestroOlvidoClave.get(0).getValorDatoMaestro1());
								objSeguridad.setNumPregRes(objObtenerPreguntas.getNroPregRes());		
								session.put("seguridadOlvidoClave", objSeguridad);
								
								redirect = "preguntasAleatorias";
								
								
							}
								
							
						}
						
					}
				
				}
				else{
					
					redirect = "olvidoClave";
				}
					
				
			} catch (Exception e) {
				LOG.info(e.toString());
			}
			
		}

		
		
		
		return redirect;
	}
	
	

	public OlvidoClaveActionFilter getOlvidoClaveActionFilter() {
		return olvidoClaveActionFilter;
	}

	public void setOlvidoClaveActionFilter(
			OlvidoClaveActionFilter olvidoClaveActionFilter) {
		this.olvidoClaveActionFilter = olvidoClaveActionFilter;
	}

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroOlvidoClave() {
		return lstConsultaDatosMaestroOlvidoClave;
	}

	public void setLstConsultaDatosMaestroOlvidoClave(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroOlvidoClave) {
		this.lstConsultaDatosMaestroOlvidoClave = lstConsultaDatosMaestroOlvidoClave;
	}
	

}
