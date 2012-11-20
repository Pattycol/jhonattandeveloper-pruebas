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
import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.GrabarRespuestas;
import pe.com.claro.caef.web.beans.ListaRespuestasType;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasType;
import pe.com.claro.caef.web.beans.UsuarioPreguntas;
import pe.com.claro.caef.web.services.ActualizarPreguntasSecretasService;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.EliminarPreguntasSecretasService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class EliminarPreguntasSecretasAction extends GeneralAction implements Preparable {
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	@Autowired
	private ActualizarPreguntasSecretasService actualizarPreguntasSecretasService;
	
	@Autowired
	private EliminarPreguntasSecretasService eliminarPreguntasSecretasService;
	
	private ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter;
	private ObtenerDatosPreguntas obtenerDatosPreguntas;
	private ObtenerDatosUsuario obtenerDatosUsuario;
	private ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter;
	private GrabarRespuestas grabarRespuestas;
	private List<PreguntasType> lstPreguntasType;
	private List<PreguntasType> lstPreguntasTypeCod;
	private List<PreguntasType> lstPreguntasTypeRes;
	private List<PreguntasType> lstPreguntasTypeFlagElim;
	private Map<Integer, Boolean> checkboxes;
	private UsuarioPreguntas usuarioPreguntas;	
	private List<ListaRespuestasType> lstListaRespuestasTypeCopy;
	private int cantidadPregunta;
	
	public List<ListaRespuestasType> getLstListaRespuestasTypeCopy() {
		return lstListaRespuestasTypeCopy;
	}

	public void setLstListaRespuestasTypeCopy(
			List<ListaRespuestasType> lstListaRespuestasTypeCopy) {
		this.lstListaRespuestasTypeCopy = lstListaRespuestasTypeCopy;
	}

	public UsuarioPreguntas getUsuarioPreguntas() {
		return usuarioPreguntas;
	}

	public void setUsuarioPreguntas(UsuarioPreguntas usuarioPreguntas) {
		this.usuarioPreguntas = usuarioPreguntas;
	}

	public Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}

	public List<PreguntasType> getLstPreguntasTypeCod() {
		return lstPreguntasTypeCod;
	}

	public void setLstPreguntasTypeCod(List<PreguntasType> lstPreguntasTypeCod) {
		this.lstPreguntasTypeCod = lstPreguntasTypeCod;
	}

	public List<PreguntasType> getLstPreguntasType() {
		return lstPreguntasType;
	}

	public void setLstPreguntasType(List<PreguntasType> lstPreguntasType) {
		this.lstPreguntasType = lstPreguntasType;
	}

	public List<PreguntasType> getLstPreguntasTypeRes() {
		return lstPreguntasTypeRes;
	}

	public void setLstPreguntasTypeRes(List<PreguntasType> lstPreguntasTypeRes) {
		this.lstPreguntasTypeRes = lstPreguntasTypeRes;
	}

	public List<PreguntasType> getLstPreguntasTypeFlagElim() {
		return lstPreguntasTypeFlagElim;
	}

	public void setLstPreguntasTypeFlagElim(
			List<PreguntasType> lstPreguntasTypeFlagElim) {
		this.lstPreguntasTypeFlagElim = lstPreguntasTypeFlagElim;
	}

	public GrabarRespuestas getGrabarRespuestas() {
		return grabarRespuestas;
	}

	public void setGrabarRespuestas(GrabarRespuestas grabarRespuestas) {
		this.grabarRespuestas = grabarRespuestas;
	}

	public ObtenerDatosUsuarioFilter getObtenerDatosUsuarioFilter() {
		return obtenerDatosUsuarioFilter;
	}

	public void setObtenerDatosUsuarioFilter(
			ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter) {
		this.obtenerDatosUsuarioFilter = obtenerDatosUsuarioFilter;
	}

	public ObtenerDatosUsuario getObtenerDatosUsuario() {
		return obtenerDatosUsuario;
	}

	public void setObtenerDatosUsuario(ObtenerDatosUsuario obtenerDatosUsuario) {
		this.obtenerDatosUsuario = obtenerDatosUsuario;
	}

	public ObtenerDatosPreguntasFilter getObtenerDatosPreguntasFilter() {
		return obtenerDatosPreguntasFilter;
	}

	public void setObtenerDatosPreguntasFilter(
			ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter) {
		this.obtenerDatosPreguntasFilter = obtenerDatosPreguntasFilter;
	}

	public ObtenerDatosPreguntas getObtenerDatosPreguntas() {
		return obtenerDatosPreguntas;
	}

	public void setObtenerDatosPreguntas(ObtenerDatosPreguntas obtenerDatosPreguntas) {
		this.obtenerDatosPreguntas = obtenerDatosPreguntas;
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
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		cantidadPregunta=3;
		Usuario user = new Usuario();
		user = getUsuario();
		//user.setCodigoServicio(userCodSer.getCodigoServicio());	
		//user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("eliminarPreguntasSecretas");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		/*UTILIZAMOS EL SERVICIO DE ACTUALIZACION DE PREGUNTAS PARA CARGAR LAS PREGUNTAS QUE YA HAN SIDO REGISTRADAS*/
		obtenerDatosPreguntas = actualizarPreguntasSecretasService.obtenerDatosPreguntas(user, obtenerDatosPreguntasFilter);
		obtenerDatosUsuario = actualizarPreguntasSecretasService.obtenerDatosUsuario(user, obtenerDatosUsuarioFilter);
		
		Map listaRes = ActionContext.getContext().getSession();
		List<ListaRespuestasType> lstListaResp = new ArrayList<ListaRespuestasType>();
		lstListaResp = obtenerDatosUsuario.getLstListaRespuestasType();
		listaRes.put("eliminarPreguntas", lstListaResp);
	}
	
	public String input()
	{
		return Action.INPUT;
	}	
	
	public String execute()
	{
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		//user.setCodigoServicio(userCodSer.getCodigoServicio());	
		//user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		Map listaRes = ActionContext.getContext().getSession();
		lstListaRespuestasTypeCopy = (List<ListaRespuestasType>)listaRes.get("eliminarPreguntas");
				
		
		
		
		grabarRespuestas = new GrabarRespuestas();
		grabarRespuestas.setIpApp("");
		grabarRespuestas.setOperacion("2");//LOGICA DE CLARO PARA ESTE CAMPO, SIEMPRE ENVIAR 2.
		grabarRespuestas.setTelefono(user.getTelefonoMiClaroFija());//"966319620");
		grabarRespuestas.setTxId("");
		grabarRespuestas.setUsrApp("");//user.getTelefonoMiClaroFija());//"966319620");
		
		List<PreguntasType> lstPreguntasTotal = new ArrayList<PreguntasType>();
		
		//agregar validacion de no eliminar mas de lo que deben
		
		for(int i = 0; i < lstListaRespuestasTypeCopy.size(); i++)
		{
			PreguntasType pt = new PreguntasType();
			if(checkboxes.get(i).booleanValue() == true)
			{
				pt.setCodPregunta(lstListaRespuestasTypeCopy.get(i).getCodigo().toString());
				//pt.setRespuesta(lstPreguntasTypeRes.get(i).getRespuesta().toString());
				lstPreguntasTotal.add(pt);
			}
		}
		grabarRespuestas.setLstPreguntasType(lstPreguntasTotal);
		
		AuditTypes adGrabarRes = new AuditTypes();
		
		adGrabarRes = eliminarPreguntasSecretasService.grabarRespuestas(user, grabarRespuestas); 
		if(adGrabarRes.getCodError().equals("0"))
		{
			AuditTypes adUsuarioPreg = new AuditTypes();
			
			usuarioPreguntas = new UsuarioPreguntas();
			usuarioPreguntas.setIpApp("");
			usuarioPreguntas.setIpUsuario("");
			usuarioPreguntas.setNroDoc(user.getNumDocumento());
			usuarioPreguntas.setTipoDoc(user.getCodTipoDocumento());
			usuarioPreguntas.setNroPregRes(String.valueOf(grabarRespuestas.getLstPreguntasType().size()));//REVISAR SI ESTE ES EL VALOR A ENVIAR
			usuarioPreguntas.setOperacion("2");
			usuarioPreguntas.setTelefono(user.getTelefonoMiClaroFija());//);"966319620");
			usuarioPreguntas.setTotalPreg(String.valueOf(grabarRespuestas.getLstPreguntasType().size()));
			usuarioPreguntas.setTxId("");
			usuarioPreguntas.setUsrApp("");
			
			adUsuarioPreg = eliminarPreguntasSecretasService.usuarioPreguntas(user, usuarioPreguntas);
			if(adUsuarioPreg.getCodError().equals("1"))
				return Action.SUCCESS;
			else
			{
				addActionError(MENSAJES_CONFIG.ERROR_PREGUNTA_SECRETA_1);
				return Action.INPUT;
			}
		}
		else
		{
			addActionError(MENSAJES_CONFIG.ERROR_PREGUNTA_SECRETA_1);
			return Action.INPUT;
		}
	}

	public int getCantidadPregunta() {
		return cantidadPregunta;
	}

	public void setCantidadPregunta(int cantidadPregunta) {
		this.cantidadPregunta = cantidadPregunta;
	}
	
	
	

}
