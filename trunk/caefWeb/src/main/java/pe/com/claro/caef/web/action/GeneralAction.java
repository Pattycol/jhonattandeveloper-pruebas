package pe.com.claro.caef.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.services.EnviarCorreoService;

import com.opensymphony.xwork2.ActionSupport;

public abstract class GeneralAction  extends ActionSupport implements SessionAware  {

	@Autowired
	public EnviarCorreoService enviarCorreoService;
	
	private Map<String, Object> session;
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	
	public Usuario getUsuario()
	{
		return (Usuario)getSession().get("usuario");
	}

}
