package pe.com.claro.caef.web.action;

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
import pe.com.claro.caef.web.auth.SeguridadBean;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ListarPreguntasAleatorias;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.OlvideClave;
import pe.com.claro.caef.web.beans.PreguntasAleatorias;
import pe.com.claro.caef.web.beans.ValidarPreguntasSecretas;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.IngresoPreguntasSecretasService;
import pe.com.claro.caef.web.services.RegistrarOlvidoClaveService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class RegistrarOlvidoClaveAction extends GeneralAction implements Preparable {
	
	@Autowired
	private ComunService comunService;
	
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTIPDIDE;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	@Autowired
	private RegistrarOlvidoClaveService registrarOlvidoClaveService;
	
	@Autowired
	private IngresoPreguntasSecretasService ingresoPreguntasSecretasService;
	
	private ListarPreguntasAleatorias listarPreguntasAleatorias;
	private ValidarPreguntasSecretas validarPreguntasSecretas;
	private PreguntasAleatorias preguntasAleatorias;
	private OlvideClave olvideClave;
	private ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter;
	private ObtenerDatosPreguntas obtenerDatosPreguntas;
	
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
	public OlvideClave getOlvideClave() {
		return olvideClave;
	}
	public void setOlvideClave(OlvideClave olvideClave) {
		this.olvideClave = olvideClave;
	}
	public PreguntasAleatorias getPreguntasAleatorias() {
		return preguntasAleatorias;
	}
	public void setPreguntasAleatorias(PreguntasAleatorias preguntasAleatorias) {
		this.preguntasAleatorias = preguntasAleatorias;
	}
	public ListarPreguntasAleatorias getListarPreguntasAleatorias() {
		return listarPreguntasAleatorias;
	}
	public void setListarPreguntasAleatorias(
			ListarPreguntasAleatorias listarPreguntasAleatorias) {
		this.listarPreguntasAleatorias = listarPreguntasAleatorias;
	}
	public ValidarPreguntasSecretas getValidarPreguntasSecretas() {
		return validarPreguntasSecretas;
	}
	public void setValidarPreguntasSecretas(
			ValidarPreguntasSecretas validarPreguntasSecretas) {
		this.validarPreguntasSecretas = validarPreguntasSecretas;
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
		/*Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());*/
		/*****************************************/
		Usuario user = new Usuario();
		
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		consultarDatosMaestrosFilter.setCodigoCaso("TIPDIDE");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
		consultarDatosMaestrosFilter.setParametro1("");		//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstConsultaDatosMaestroTIPDIDE = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("registrarOlvidoClave");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		obtenerDatosPreguntas = ingresoPreguntasSecretasService.obtenerDatosPreguntas(user, obtenerDatosPreguntasFilter);
		
		SeguridadBean sb = new SeguridadBean();
		Map sessionSeguridadBean = ActionContext.getContext().getSession();
		sb = (SeguridadBean)sessionSeguridadBean.get("seguridadOlvidoClave");
		
		listarPreguntasAleatorias = new ListarPreguntasAleatorias();
		listarPreguntasAleatorias.setOperacion("1");
		listarPreguntasAleatorias.setTelefono("966319620");//sb.getTelefono());		//PASAR TELEFONO
		listarPreguntasAleatorias.setNroPreguntas(sb.getNumPregRes());	//PASAR NUMERO DE PREGUNTAS
		preguntasAleatorias = registrarOlvidoClaveService.listaPreguntasAleatorias(user, listarPreguntasAleatorias);
		
		if(!preguntasAleatorias.getCodError().equals("1"))
		{
			addActionError( MENSAJES_CONFIG.REGISTRO_OLVIDO_CLAVE_MENSAJE1);
		}
	}
	
	public String input()
	{
		LOG.info("ENTRANDO A REGISTRAR OLVIDO CLAVE");
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
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		SeguridadBean sb = new SeguridadBean();
		Map sessionSeguridadBean = ActionContext.getContext().getSession();
		sb = (SeguridadBean)sessionSeguridadBean.get("seguridadOlvidoClave");
		
		validarPreguntasSecretas = new ValidarPreguntasSecretas();
		validarPreguntasSecretas.setTelefono(sb.getTelefono());//PASAR TELEFONO
		validarPreguntasSecretas.setFlagTipoLinea("FIJA");
		//validarPreguntasSecretas.setTipoDocumento(""); //OBTENIDO DE LA PANTALLA
		//validarPreguntasSecretas.setNroDocumento("");  //OBTENIDO DE LA PANTALLA
		//validarPreguntasSecretas.setEmail(null); 		 //OBTENIDO DE LA PANTALLA
		validarPreguntasSecretas.setClave(null);
		validarPreguntasSecretas.setOperacionT("2");
		validarPreguntasSecretas.setLstPreguntasType(preguntasAleatorias.getLstPreguntas());
		
		AuditTypes at = new AuditTypes();
		at = registrarOlvidoClaveService.validarPreguntasSecretas(user, validarPreguntasSecretas);
		
		if(at.getCodError().equals("0"))
		{
			AuditTypes ato  = new AuditTypes();
			olvideClave = new OlvideClave();
			olvideClave.setTelefono("966319620");//sb.getTelefono());
			ato = registrarOlvidoClaveService.olvideClave(user, olvideClave);
			
			if(ato.getCodError().equals("0"))
			{
				return Action.SUCCESS;
			}
			else
			{
				addActionError(MENSAJES_CONFIG.REGISTRO_OLVIDO_CLAVE_MENSAJE2);
				return Action.INPUT;
			}

		}
		else
		{
			addActionError( MENSAJES_CONFIG.REGISTRO_OLVIDO_CLAVE_MENSAJE3);
			return Action.INPUT;
		}
	}

}
