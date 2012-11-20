package pe.com.claro.caef.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class CambioClaveAction extends GeneralAction implements Preparable {
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
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
		mensajesSeguridadFilter.setTipoPantalla("cambioClave");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
	}
	public String getActionPrueba()
	{
		return Action.SUCCESS;
	}

}
