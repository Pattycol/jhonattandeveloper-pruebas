package unmsm.fisi.tesis.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import unmsm.fisi.tesis.web.forms.LoginForm;



public class LoginAction extends Action {
	
	
	public LoginAction() {
        super();
    }

   
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response)
        throws Exception {


    	LoginForm objetoForm = (LoginForm) form;
    	
    	String usuario = objetoForm.getUsuario();
    	String clave = objetoForm.getClave();
    	
    	int flag=0;
    	if(usuario!= null && clave!= null){
    		
    		if(usuario.compareTo("sistemas")==0 && clave.compareTo("sindrome")==0){
    			
    			flag=2;
    			
    		}else{
    			
    			flag=1;
    		}
    		
    		
    		
    	}else{
    		flag=1;
    		
    	}
    	
        if(flag!=2){
        	
            return mapping.findForward("error");
        }else{
        	
            return mapping.findForward("success");
        	
        }
        
    }

}
