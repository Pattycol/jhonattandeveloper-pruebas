package pe.com.claro.caef.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.auth.SeguridadBean;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.IngresoPreguntaSecreta;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasType;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.IngresoPreguntasSecretasService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class IngresoPreguntasSecretasAction extends GeneralAction implements Preparable {

	@Autowired
	private ComunService comunService;
	
	@Autowired
	private IngresoPreguntasSecretasService ingresoPreguntasSecretasService;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTIPDIDE;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	private List<IngresoPreguntaSecreta> lstIngresoPreguntaSecreta;
	
	private ObtenerDatosUsuario obtenerDatosUsuario;
	private ObtenerDatosPreguntas obtenerDatosPreguntas;
	private ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter;
	private List<PreguntasType> lstPreguntasType;
	private List<PreguntasType> lstPreguntasTypeRes;
	private int cantidadPregunta;
	
	public List<PreguntasType> getLstPreguntasTypeRes() {
		return lstPreguntasTypeRes;
	}

	public void setLstPreguntasTypeRes(List<PreguntasType> lstPreguntasTypeRes) {
		this.lstPreguntasTypeRes = lstPreguntasTypeRes;
	}

	private PreguntasType preguntasType;
	
	
	public PreguntasType getPreguntasType() {
		return preguntasType;
	}

	public void setPreguntasType(PreguntasType preguntasType) {
		this.preguntasType = preguntasType;
	}

	public List<PreguntasType> getLstPreguntasType() {
		return lstPreguntasType;
	}

	public void setLstPreguntasType(List<PreguntasType> lstPreguntasType) {
		this.lstPreguntasType = lstPreguntasType;
	}

	private SeguridadBean sb;
	
	public SeguridadBean getSb() {
		return sb;
	}

	public void setSb(SeguridadBean sb) {
		this.sb = sb;
	}

	public ObtenerDatosPreguntas getObtenerDatosPreguntas() {
		return obtenerDatosPreguntas;
	}

	public void setObtenerDatosPreguntas(ObtenerDatosPreguntas obtenerDatosPreguntas) {
		this.obtenerDatosPreguntas = obtenerDatosPreguntas;
	}

	public ObtenerDatosPreguntasFilter getObtenerDatosPreguntasFilter() {
		return obtenerDatosPreguntasFilter;
	}

	public void setObtenerDatosPreguntasFilter(
			ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter) {
		this.obtenerDatosPreguntasFilter = obtenerDatosPreguntasFilter;
	}

	public ObtenerDatosUsuario getObtenerDatosUsuario() {
		return obtenerDatosUsuario;
	}

	public void setObtenerDatosUsuario(ObtenerDatosUsuario obtenerDatosUsuario) {
		this.obtenerDatosUsuario = obtenerDatosUsuario;
	}

	public List<IngresoPreguntaSecreta> getLstIngresoPreguntaSecreta() {
		return lstIngresoPreguntaSecreta;
	}

	public void setLstIngresoPreguntaSecreta(
			List<IngresoPreguntaSecreta> lstIngresoPreguntaSecreta) {
		this.lstIngresoPreguntaSecreta = lstIngresoPreguntaSecreta;
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

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroTIPDIDE() {
		return lstConsultaDatosMaestroTIPDIDE;
	}

	public void setLstConsultaDatosMaestroTIPDIDE(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroTIPDIDE) {
		this.lstConsultaDatosMaestroTIPDIDE = lstConsultaDatosMaestroTIPDIDE;
	}

	public void prepare() throws Exception
	{
		/**OBTENER VALORES DE SESION**************/
		//Usuario userCodSer = new Usuario();
		//Map codServicio = ActionContext.getContext().getSession();
		//userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		//USAR LA SESION NO APLICA PORQUE AUN NO HA INGRESADO A LA PANTALLA DE SELECCIONAR SERVICIOS
		//user.setCodigoServicio(userCodSer.getCodigoServicio());	
		//user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		consultarDatosMaestrosFilter.setCodigoCaso("TIPDIDE");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
		consultarDatosMaestrosFilter.setParametro1("");		//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstConsultaDatosMaestroTIPDIDE = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("ingresoPreguntaSecreta");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		//lstIngresoPreguntaSecreta = ingresoPreguntasSecretasService.obtenerPreguntaSecreta(getUsuario(), obtenerDatosUsuario);
		
		obtenerDatosPreguntas = ingresoPreguntasSecretasService.obtenerDatosPreguntas(user, obtenerDatosPreguntasFilter);
		cantidadPregunta = Integer.valueOf(obtenerDatosPreguntas.getLstNumPreguntas().size());
	}
	
	public String input()
	{		
		return Action.INPUT;
	}	
	
	public String execute()
	{		
		boolean error = false;
		
		if(sb.getNroDocumento().length() == 0)
		{error = true;
			addActionError(MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_3);
			return Action.INPUT;}
		else if (sb.getTipoDocumento().length() == 0)
		{error = true;
			addActionError(MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_4);
			return Action.INPUT;}
		else if (sb.getEmail().length() == 0)
		{error = true;
			addActionError(MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_5);
			return Action.INPUT;}
		else
		{
			String current_value=null;
			for (int i = 0; i < obtenerDatosPreguntas.getLstNumPreguntas().size(); i++)
			{
				int repeticiones=0;
				for(int j=0; j <obtenerDatosPreguntas.getLstNumPreguntas().size();j++){
					if(current_value!= null && current_value.equals(lstPreguntasType.get(j).getCodPregunta().toString())){
						repeticiones++;
						if(repeticiones>1){
							error=true;
							addActionError(MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_6);
							return Action.INPUT;
						}

					}
				}
				/*if(i>1){
					if(current_value.equals(lstPreguntasType.get(i).getCodPregunta().toString())||current_value.equals(lstPreguntasType.get(i-1).getCodPregunta().toString())){
						error=true;
						addActionError(MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_6);
						return Action.INPUT;
					}
					
				}
				if(current_value!=null && current_value.equals(lstPreguntasType.get(i).getCodPregunta().toString())){
					error=true;
					addActionError(MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_6);
					return Action.INPUT;
				}*/
				
				PreguntasType pt = new PreguntasType();
				if(Integer.parseInt(obtenerDatosPreguntas.getLstNumPreguntas().get(i).getCodigo()) == (i + 1))
				{
					if(lstPreguntasType.get(i).getCodPregunta().toString().equals(""))
					{	error = true;
					addActionError( MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_1);
					return Action.INPUT;}
					else if (lstPreguntasTypeRes.get(i).getRespuesta().toString().equals(""))
					{	error = true;
						addActionError( MENSAJES_CONFIG.ERROR_INGRESO_PREGUNTA_SECRETA_2);
					return Action.INPUT;}
				}
				current_value=lstPreguntasType.get(i).getCodPregunta().toString();
			}
		}
		
		if(error == false)
		{
		Map sessionSeguridadBean = ActionContext.getContext().getSession();
		List<PreguntasType> lstPreguntasTypeSession = new ArrayList<PreguntasType>();
		int totalItems = lstPreguntasType.size();
		SeguridadBean sbSession = new SeguridadBean();
		
		for (int i = 0; i < obtenerDatosPreguntas.getLstNumPreguntas().size(); i++)
		{		
			PreguntasType pt = new PreguntasType();
			if(Integer.parseInt(obtenerDatosPreguntas.getLstNumPreguntas().get(i).getCodigo()) == (i + 1))
			{
				pt.setCodPregunta(lstPreguntasType.get(i).getCodPregunta().toString());
				pt.setRespuesta(lstPreguntasTypeRes.get(i).getRespuesta().toString());
			}
			lstPreguntasTypeSession.add(pt);
		}
		sbSession.setNroPregReg(String.valueOf(obtenerDatosPreguntas.getLstNumPreguntas().size()));
		sbSession.setNroPregResp(String.valueOf(lstPreguntasTypeSession.size()));
		sbSession.setEmail(sb.getEmail().toString());
		sbSession.setNroDocumento(sb.getNroDocumento().toString());
		sbSession.setTipoDocumento(sb.getTipoDocumento().toString());
		sbSession.setLstPreguntasType(lstPreguntasTypeSession);
		
		sessionSeguridadBean.put("seguridadBean", sbSession);
		return Action.SUCCESS;
		}
		else 
			return Action.INPUT;
	}
	
	public String cancelar()
	{	
		Map sessionSeguridadBean = ActionContext.getContext().getSession();
		sessionSeguridadBean.remove("seguridadBean");
		
		return "cancelar";
	}

	public int getCantidadPregunta() {
		return cantidadPregunta;
	}

	public void setCantidadPregunta(int cantidadPregunta) {
		this.cantidadPregunta = cantidadPregunta;
	}
	
	
}
