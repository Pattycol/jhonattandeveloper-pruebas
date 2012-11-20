package pe.com.claro.caef.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class PrincipalAction extends GeneralAction implements Preparable  {
	
	public void prepare() throws Exception
	{
		
	}
	public String getActionPrincipal()
	{
		return Action.SUCCESS;
	}
	
	public String validaUser(){
		
		
		
		return "exito";
	}
	
	/*public String cerrarSesion(){
		
		Map session = ActionContext.getContext().getSession();
		
		try {
			
			if(session!=null){
				session.remove("usuario");
			}
    		//session.removeAttribute("usuario");
    		 
		
		} catch (Exception e) {
			session.remove("usuario");
			//session.removeAttribute("usuario");
			
		
		}	
		
		return "exito";
	}*/

}
