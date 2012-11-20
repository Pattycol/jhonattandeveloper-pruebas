package pe.com.claro.caef.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;

public class LoginAction extends GeneralAction implements Preparable{
	@Autowired
	private ComunService comunService;

	/*
	 * @Autowired private QueueSender sender;
	 * 
	 * @Autowired private QueueListener listener;
	 */
	@Autowired
	private CargarMensajesService cargarMensajesService;
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	public String input(){
		System.out.println("EN IN");
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("pantallalogueo");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		return Action.INPUT;
		
	}

	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
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
	

}
