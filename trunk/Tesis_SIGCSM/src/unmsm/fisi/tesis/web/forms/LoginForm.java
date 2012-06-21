package unmsm.fisi.tesis.web.forms;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {
	private String usuario;
	private String clave;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	

}
