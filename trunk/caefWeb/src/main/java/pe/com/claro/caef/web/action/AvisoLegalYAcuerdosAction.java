package pe.com.claro.caef.web.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.SeguridadBean;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarPreguntas;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.AvisoLegalYAcuerdosService;
import pe.com.claro.caef.web.services.CargarMensajesService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class AvisoLegalYAcuerdosAction extends GeneralAction implements Preparable {
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	@Autowired
	private AvisoLegalYAcuerdosService avisoLegalYAcuerdosService;
	
	private GrabarPreguntas grabarPreguntas;
	private AuditTypes auditTypes;
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	public AvisoLegalYAcuerdosAction()
	{
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridad = new MensajesSeguridad();
	}
	
	public AuditTypes getAuditTypes() {
		return auditTypes;
	}

	public void setAuditTypes(AuditTypes auditTypes) {
		this.auditTypes = auditTypes;
	}

	public GrabarPreguntas getGrabarPreguntas() {
		return grabarPreguntas;
	}

	public void setGrabarPreguntas(GrabarPreguntas grabarPreguntas) {
		this.grabarPreguntas = grabarPreguntas;
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
		mensajesSeguridadFilter.setTipoPantalla("avisoLegalYAcuerdos");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
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
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("avisoLegalYAcuerdos");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		//user.setCodigoServicio(userCodSer.getCodigoServicio());	
		//user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		grabarPreguntas = new GrabarPreguntas();
		SeguridadBean sb = new SeguridadBean();
		Map sessionSeguridadBean = ActionContext.getContext().getSession();
		sb = (SeguridadBean)sessionSeguridadBean.get("seguridadBean");
		
		if(sb == null)
		{
			addActionError(mensajesSeguridad.getMensaje7());
			return Action.INPUT;
		}
		else
		{
			grabarPreguntas.setEmail(sb.getEmail().toString());
			grabarPreguntas.setFlagEmail("5");
			grabarPreguntas.setFlagTipoLinea("FIJA");
			grabarPreguntas.setIpApp("");
			grabarPreguntas.setLinea(user.getTelefonoMiClaroFija());
			grabarPreguntas.setLstPreguntasType(sb.getLstPreguntasType());
			grabarPreguntas.setNroDocumento(sb.getNroDocumento().toString());
			grabarPreguntas.setNroPregReg(sb.getNroPregReg());
			grabarPreguntas.setNroPregResp(sb.getNroPregResp());
			grabarPreguntas.setOperacionE("2");
			grabarPreguntas.setOperacionF("3");
			grabarPreguntas.setPerfil("");
			grabarPreguntas.setTipoDocumento(sb.getTipoDocumento().toString());
			grabarPreguntas.setTxId("");
			grabarPreguntas.setUsrApp("");

			auditTypes = avisoLegalYAcuerdosService.grabarPreguntas(user, grabarPreguntas);
		
			if(auditTypes.getCodError().equals("0"))
			{
				addActionMessage(mensajesSeguridad.getMensaje8());
				return Action.SUCCESS;
			}
			else
			{
				addActionError(mensajesSeguridad.getMensaje9());
				return Action.INPUT;
			}
		}
	}
	
	public String cancelar()
	{	
		Map sessionSeguridadBean = ActionContext.getContext().getSession();
		sessionSeguridadBean.remove("seguridadBean");
		
		return "cancelar";
	}	

}
