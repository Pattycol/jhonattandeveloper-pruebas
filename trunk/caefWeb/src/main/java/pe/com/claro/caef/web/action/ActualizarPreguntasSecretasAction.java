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
import pe.com.claro.caef.web.beans.ListaPreguntas;
import pe.com.claro.caef.web.beans.ListaPreguntasInfoType;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasType;
import pe.com.claro.caef.web.beans.UsuarioPreguntas;
import pe.com.claro.caef.web.services.ActualizarPreguntasSecretasService;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class ActualizarPreguntasSecretasAction extends GeneralAction implements Preparable {
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	@Autowired
	private ActualizarPreguntasSecretasService actualizarPreguntasSecretasService;
	
	private GrabarRespuestas grabarRespuestas;
	private UsuarioPreguntas usuarioPreguntas;
	private ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter;
	private ObtenerDatosPreguntas obtenerDatosPreguntas;
	private ObtenerDatosUsuario obtenerDatosUsuario;
	private ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter;
	private ObtenerDatosPreguntas obtenerDatosPreguntasNuevas;
	private List<PreguntasType> lstPreguntasType;
	private List<PreguntasType> lstPreguntasTypeRes;
	private String resul;
	
	
	public String getResul() {
		return resul;
	}

	public void setResul(String resul) {
		this.resul = resul;
	}

	public List<PreguntasType> getLstPreguntasTypeRes() {
		return lstPreguntasTypeRes;
	}

	public void setLstPreguntasTypeRes(List<PreguntasType> lstPreguntasTypeRes) {
		this.lstPreguntasTypeRes = lstPreguntasTypeRes;
	}

	public List<PreguntasType> getLstPreguntasType() {
		return lstPreguntasType;
	}

	public void setLstPreguntasType(List<PreguntasType> lstPreguntasType) {
		this.lstPreguntasType = lstPreguntasType;
	}

	public ObtenerDatosPreguntas getObtenerDatosPreguntasNuevas() {
		return obtenerDatosPreguntasNuevas;
	}

	public void setObtenerDatosPreguntasNuevas(
			ObtenerDatosPreguntas obtenerDatosPreguntasNuevas) {
		this.obtenerDatosPreguntasNuevas = obtenerDatosPreguntasNuevas;
	}

	public ObtenerDatosUsuario getObtenerDatosUsuario() {
		return obtenerDatosUsuario;
	}

	public void setObtenerDatosUsuario(ObtenerDatosUsuario obtenerDatosUsuario) {
		this.obtenerDatosUsuario = obtenerDatosUsuario;
	}

	public ObtenerDatosUsuarioFilter getObtenerDatosUsuarioFilter() {
		return obtenerDatosUsuarioFilter;
	}

	public void setObtenerDatosUsuarioFilter(
			ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter) {
		this.obtenerDatosUsuarioFilter = obtenerDatosUsuarioFilter;
	}

	public GrabarRespuestas getGrabarRespuestas() {
		return grabarRespuestas;
	}

	public void setGrabarRespuestas(GrabarRespuestas grabarRespuestas) {
		this.grabarRespuestas = grabarRespuestas;
	}

	public UsuarioPreguntas getUsuarioPreguntas() {
		return usuarioPreguntas;
	}

	public void setUsuarioPreguntas(UsuarioPreguntas usuarioPreguntas) {
		this.usuarioPreguntas = usuarioPreguntas;
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
		
		Usuario user = new Usuario();
		user = getUsuario();
		//user.setCodigoServicio(userCodSer.getCodigoServicio());	
		//user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("actualizaPreguntaSecreta");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		obtenerDatosPreguntas = actualizarPreguntasSecretasService.obtenerDatosPreguntas(user, obtenerDatosPreguntasFilter);
		obtenerDatosUsuario = actualizarPreguntasSecretasService.obtenerDatosUsuario(user, obtenerDatosUsuarioFilter);
		
		if(Integer.valueOf(obtenerDatosUsuario.getNroPreguntas()) == Integer.valueOf(obtenerDatosPreguntas.getNroPregReg()))
		{
			//obtenerDatosPreguntas = null;
			/*
			List<ListaPreguntasInfoType> lista = new ArrayList<ListaPreguntasInfoType>();
			ListaPreguntasInfoType pt = new ListaPreguntasInfoType();
			pt.setCodigo("");
			lista.add(pt);
			obtenerDatosPreguntasNuevas = obtenerDatosPreguntas;
			obtenerDatosPreguntasNuevas.setLstNumPreguntas(lista);
			*/
			//SI EL NUMERO DE PREGUNTAS ES EQUIVALENTE NO HAY PORQUE ADICIONAR Y NOS DIRECCIONAMOS A PRINCIPAL
			resul = "Principal";
			
		}
		else if(Integer.valueOf(obtenerDatosUsuario.getNroPreguntas()) > Integer.valueOf(obtenerDatosPreguntas.getNroPregReg()))
		{
			//obtenerDatosPreguntas = null;
			/*List<ListaPreguntasInfoType> lista = new ArrayList<ListaPreguntasInfoType>();
			
			for(int i = 0; i < Integer.valueOf(obtenerDatosUsuario.getNroPreguntas()); i++)
			{
				ListaPreguntasInfoType pt = new ListaPreguntasInfoType();
				pt.setCodigo(String.valueOf(obtenerDatosUsuario.getLstListaRespuestasType().size() + i + 1));
				lista.add(pt);
			}				
			obtenerDatosPreguntasNuevas = obtenerDatosPreguntas;
			obtenerDatosPreguntasNuevas.setLstNumPreguntas(lista);*/
			//SI EL NUMERO DE PREGUNTAS RESPONDIDAS ES MAYOR NOS DIRECCIONAMOS A PRINCIPAL, SIN EMBARGO ESTE ESCENARIO
			//NUNCA SUCEDERIA PORQUE DESDE LA PANTALLA DE LOGIN NOS DERIVARÍA A ELIMINAR PREGUNTAS.
			resul = "Principal";
			
		}
		else //número de preguntas contestadas es menor al registradas
		{
			obtenerDatosPreguntasNuevas = new ObtenerDatosPreguntas();
			int numPregAd = Integer.valueOf(obtenerDatosPreguntas.getNroPregReg()) - Integer.valueOf(obtenerDatosUsuario.getNroPreguntas());				
			
			List<ListaPreguntasInfoType> lista = new ArrayList<ListaPreguntasInfoType>();
			
			for(int i = 0; i < numPregAd; i++)
			{
				ListaPreguntasInfoType pt = new ListaPreguntasInfoType();
				pt.setCodigo(String.valueOf(obtenerDatosUsuario.getLstListaRespuestasType().size() + i + 1));
				lista.add(pt);
			}				
			obtenerDatosPreguntasNuevas = obtenerDatosPreguntas;
			obtenerDatosPreguntasNuevas.setLstNumPreguntas(lista);
			//EN CASO EL NÚMERO DE PREGUNTAS A RESPONDER SEA MAYOR AL NUMERO DE PREGUNTAS RESPONDIDAS EL CLIENTE DEBERA DE 
			//ADICIONAR PREGUNTAS
			resul="";
		}
	}
	
	public String input()
	{
		return Action.INPUT;
	}	
	
	public String principal()
	{
		if(resul.equals("Principal"))
			return "principal";
		else
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
		
		grabarRespuestas = new GrabarRespuestas();
		grabarRespuestas.setIpApp("");
		grabarRespuestas.setOperacion("1");//LOGICA DE CLARO PARA ESTE CAMPO, SIEMPRE ENVIAR 1.
		grabarRespuestas.setTelefono(user.getTelefonoMiClaroFija());//"966319620");//966319620
		grabarRespuestas.setTxId("");
		grabarRespuestas.setUsrApp("");
		
		List<PreguntasType> lstPreguntasTotal = new ArrayList<PreguntasType>();
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("actualizaPreguntaSecreta");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		for(int i = 0; i< obtenerDatosPreguntasNuevas.getLstNumPreguntas().size(); i++)
		{
			PreguntasType pt = new PreguntasType();
			pt.setCodPregunta(lstPreguntasType.get(i).getCodPregunta().toString());
			pt.setRespuesta(lstPreguntasTypeRes.get(i).getRespuesta().toString());
			lstPreguntasTotal.add(pt);
		}
		grabarRespuestas.setLstPreguntasType(lstPreguntasTotal);
		
		AuditTypes adGrabarRes = new AuditTypes();
		
		adGrabarRes = actualizarPreguntasSecretasService.grabarRespuestas(user, grabarRespuestas);
		
		if(adGrabarRes.getCodError().equals("0"))
		{
			AuditTypes adUsuarioPreg = new AuditTypes();
			usuarioPreguntas = new UsuarioPreguntas();
			usuarioPreguntas.setIpApp("");
			usuarioPreguntas.setIpUsuario("");
			usuarioPreguntas.setNroDoc(user.getNumDocumento());//dato que viene del login?
			usuarioPreguntas.setTipoDoc(user.getCodTipoDocumento());//dato que viene de login?
			usuarioPreguntas.setNroPregRes(String.valueOf(obtenerDatosPreguntasNuevas.getLstNumPreguntas().size()));//lo que le falta al usuario por responder
			usuarioPreguntas.setOperacion("2");
			usuarioPreguntas.setTelefono("966319620");
			usuarioPreguntas.setTotalPreg(String.valueOf(grabarRespuestas.getLstPreguntasType().size()));
			usuarioPreguntas.setTxId("");
			usuarioPreguntas.setUsrApp("");
			
			adUsuarioPreg = actualizarPreguntasSecretasService.usuarioPreguntas(user, usuarioPreguntas);
			if(adUsuarioPreg.getCodError().equals("1"))
			{
				addActionMessage(mensajesSeguridad.getMensaje5());
				return Action.SUCCESS;
			}
			else
			{
				addActionError( getText(mensajesSeguridad.getMensaje6()));
				return Action.INPUT;
			}
		}
		else
		{
			addActionError( getText(mensajesSeguridad.getMensaje7()));
			return Action.INPUT;
		}	
	}
}
