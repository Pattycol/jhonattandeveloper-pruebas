/*
 * SeniorityAction.java
 *
 * Created on July 21, 2004, 11:38 AM
 */

package com.stconsulting.lbsweb.seguridad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.stconsulting.common.util.Constants;
import com.stconsulting.lbsweb.consulta.service.AreaService;
import com.stconsulting.lbsweb.consulta.service.EmpresaService;
import com.stconsulting.lbsweb.consulta.service.TipoServicioService;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;
import com.stconsulting.lbsweb.seguridad.form.LoginForm;
import com.stconsulting.lbsweb.seguridad.service.LoginService;
import com.stconsulting.lbsweb.util.Util;
import com.stconsulting.lbsws.ws.LBSConsultaService;
import com.stconsulting.lbsws.ws.LBSConsultaServiceService;

/**
 * 
 * @author STConsulting
 */
public class LoginAction extends DispatchAction{

	protected Logger log=null;

	public LoginAction(){
		log=Logger.getLogger(this.getClass());
		//Parameters parameters=new Parameters();
	}

	public ActionForward cabecera(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
		log.debug("Usuario en session : " + usuario.getTelefono());
		return mapping.findForward("cabecera");
	}

	public ActionForward finalizarSesion(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);

		/*LoginForm form=(LoginForm) actionForm;
		form=null;
		form=new LoginForm();*/

		// form.setMobile("");
		// form.setPassword("");
		if(session != null){
			session.removeAttribute(Constants.USUARIO_LOGUEADO);
			session.invalidate();
			session=null;
		}
		return mapping.getInputForward();
	}

	public ActionForward obtenerConsultasWebRestantes(ActionMapping mapping, ActionForm actionForm,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        
        EmpresaService empresaService = new EmpresaService();
        AreaService areaService = new AreaService();

        TipoServicioService tipoServicioService = new TipoServicioService();

        	
       try {
		
    	   Usuario usuario = (Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
           log.debug("Usuario en session : " + usuario.getTelefono());
           
           String idEmpresa = areaService.getRucArea(usuario.getTelefono());

           String consultasWebTotales = tipoServicioService.getConsultasWebxTipo(idEmpresa);
           String consultasWebRealizadas = empresaService.consultasWebRealizadas(idEmpresa);

           int valor = Integer.parseInt(consultasWebTotales)
                   - Integer.parseInt(consultasWebRealizadas);

           session.setAttribute(Constants.CONSULTAS_WEB_FALTANTES, "" + valor);
           
	} catch (Exception e) {
		// TODO: handle exception
		
	}
       
        
        

        return mapping.findForward("cabecera2");
    }
	
	public ActionForward cargar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{

		//HttpSession session=request.getSession(true);
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{
			log.debug("Se elimina usuario de session");
			session.removeAttribute(Constants.USUARIO_LOGUEADO);

			String strCodigoValidacion=null;
			//String flag=null;

			log.debug(" ***************************************");
			log.debug("Ingrese al cargar de LoginAction");

			LoginService service=new LoginService();
			
			LoginForm form=(LoginForm) actionForm;
			form.setUser(form.getMobile());
            if(form.getUser()!=null && form.getPassword()!=null){
            	
    			Usuario usuario=new Usuario();
    			usuario.setUser(form.getUser());
    			usuario.setPassword(form.getPassword());
    			strCodigoValidacion=verificaDatos(form,service,usuario,false);
    			
    			if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_VALIDO)){
    		        // Subo en sesion los datos del usuario que se loguea.
    		        EmpresaService empresaService= new EmpresaService();
    		        AreaService areaService= new AreaService();
    		        
    		        TipoServicioService tipoServicioService= new TipoServicioService();
    		       
    		        String idEmpresa =areaService.getRucArea(form.getUser());
    		        String consultasWebTotales = tipoServicioService.getConsultasWebxTipo(idEmpresa);
    		        String consultasWebRealizadas= empresaService.consultasWebRealizadas(idEmpresa);
    		        int valor = Integer.parseInt(consultasWebTotales) - Integer.parseInt(consultasWebRealizadas);
    		       
    		        form.setConsultas_web_faltantes(""+valor);
    		        session.setAttribute(Constants.CONSULTAS_WEB_FALTANTES,""+valor);
    		        
    		        session.setAttribute(Constants.USUARIO_LOGUEADO,usuario);
    		   
    		        log.info("User, password correctos, saliendo del cargar de LoginAction");
    		        return mapping.findForward("success");
    		       }
    			if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_PASSWORD_VENCIDO)){
    				request.setAttribute(Constants.ACCION_CAMBIAR_PASSWORD,Constants.ACCION_CAMBIAR_PASSWORD);
                    errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
                            "errors.login.user.new.password"));
    				saveErrors(request,errors);
    			}
    			else if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_NO_ADMIN)){
    				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.perfil.invalid"));
    				saveErrors(request,errors);
    			}
    			else if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_CLAVE_RECUPERADA)){
    				request.setAttribute(Constants.ACCION_CAMBIAR_PASSWORD,Constants.ACCION_CAMBIAR_PASSWORD);
    				//errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.new.password"));
    				saveErrors(request,errors);
    			}
    			else if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_NO_APROVISIONADO)){
    				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.no.aprovisionado"));
    				saveErrors(request,errors);
    			}
    			else{
    				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.invalid"));
    				saveErrors(request,errors);
    			}
    			return mapping.findForward("error");
            }
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.invalid"));
			saveErrors(request,errors);
			return mapping.findForward("error");
		}
		catch(Exception e){
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
			return mapping.findForward("error");
		}
	}

	public String verificaDatos(LoginForm form,LoginService service,Usuario usuario,boolean tipo) throws Exception{
		String resultado;
		resultado=service.verifica(usuario,tipo);
		if(resultado.equals(Constants.COD_USUARIO_VALIDO)){
			form.setNombreUsuario(usuario.getNombreCompleto());
			form.setAreaUsuario(usuario.getDescArea());
			form.setMobile(usuario.getTelefono());
		}
		else{
			if(!resultado.equals(Constants.COD_USUARIO_PASSWORD_VENCIDO)){
				form.setUser("");
				//form.setMobile("");
			}
			form.setPassword("");
		}
		return resultado;
	}

	public ActionForward inicioCambiarPassword(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//HttpSession session=request.getSession(false);
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.login.user.new.password"));
        saveErrors(request, errors);
		return mapping.findForward("password");
	}

	public ActionForward cambiarPassword(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		String strCodigoValidacion = null;
		try{
			LoginForm form=(LoginForm) actionForm;
			LoginService loginService=new LoginService();
			String password = request.getParameter("password");
			String confirma=request.getParameter("confirmaPassword");
			if(confirma != null /*&& confirma.equals(form.getPassword())*/){
				Usuario usuario=new Usuario();
				usuario.setUser(form.getMobile());
				usuario.setPassword(password);
				
				strCodigoValidacion=verificaDatos(form,loginService,usuario,true);

    			if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_VALIDO)){

					loginService.cambiarPassword(usuario,Util.toMD5(confirma),true);
                    saveErrors(request, errors);
					return mapping.findForward("passwordSuccess");

    			}
    			if(strCodigoValidacion != null && strCodigoValidacion.equals(Constants.COD_USUARIO_PASSWORD_VENCIDO)){
    				//request.setAttribute(Constants.ACCION_CAMBIAR_PASSWORD,Constants.ACCION_CAMBIAR_PASSWORD);
    				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.new.password"));
    				saveErrors(request,errors);
    				return mapping.findForward("password");
    			}else{
    				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.invalid"));
    				saveErrors(request,errors);
    				return mapping.findForward("password");
    			}
				
			}
			form.setPassword("");
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.login.user.new.password.invalid"));
			saveErrors(request,errors);
		}
		catch(Exception e){
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);

		}

		return mapping.findForward("password");
	}
	
	public ActionForward inicioRecuperarPassword(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return mapping.findForward("recuperar");
	}
	
	public ActionForward recuperarPassword(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ActionErrors errors=new ActionErrors();
		String strCodigoValidacion = null;
        String pass = Util.generaAleatorio();
		
		try{
			
			LoginForm form=(LoginForm) actionForm;
			LoginService loginService=new LoginService();
			
			Usuario usuario=new Usuario();
			usuario.setUser(form.getMobile());
			usuario.setTelefono(form.getMobile());
			
			strCodigoValidacion = loginService.recuperarPassword(usuario);
            log.debug("strCodigoValidacion " + strCodigoValidacion);


			if(strCodigoValidacion != null && strCodigoValidacion != Constants.COD_USUARIO_INVALIDO){

                log.debug("antes cambio ");
                loginService.cambiarPassword(usuario, Util.toMD5(pass), false);
                log.debug("despues cambio");

				LBSConsultaServiceService endpointService= new LBSConsultaServiceService();
						
				LBSConsultaService lbsConsultaService  =endpointService.getLBSConsultaServicePort();
			
                log.debug("antes envio ");
                lbsConsultaService.enviarPasswordSMS(form.getMobile(), pass);
                log.debug("despues envio ");

				return mapping.findForward("recuperarPasswordSuccess");
						

			}else{
                log.debug("Invalido usuario " + usuario.getTelefono());

                errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.numero.invalido"));
                saveErrors(request, errors);
				
			}
			
		}catch(Exception e){

            log.debug("excepcion " + e.toString());

            e.printStackTrace();

            // errors.add(ActionErrors.GLOBAL_ERROR, new
            // ActionError("errors.general"));
            // saveErrors(request, errors);


		}

		return mapping.findForward("recuperar");
}
	
}
