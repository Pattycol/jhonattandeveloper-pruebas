package pe.com.claro.caef.web.action;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class ManualUsuarioAction extends GeneralAction implements Preparable{

	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	
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
		mensajesSeguridadFilter.setTipoPantalla("manualUsuario");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
	}
	public String getActionManualCliente()
	{
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_MANUAL_USUARIO),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_MANUAL_USUARIO,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","se ingreso a Manual de usuario","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
		
		return Action.SUCCESS;
	}
}
