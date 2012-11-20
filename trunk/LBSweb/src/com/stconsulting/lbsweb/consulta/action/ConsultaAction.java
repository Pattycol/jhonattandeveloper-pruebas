/*
 * ConsultaAction.java
 *
 * Created on 26 de mayo de 2005, 03:00 PM
 */

package com.stconsulting.lbsweb.consulta.action;

import java.util.*;

import javax.servlet.http.*;
import org.apache.log4j.Logger;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;

import com.stconsulting.common.util.Constants;
import com.stconsulting.lbsweb.consulta.form.ConsultaForm;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.consulta.service.ConsultaService;
import com.stconsulting.lbsweb.util.Util;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

/**
 * 
 * @author STconsulting
 */
public class ConsultaAction extends DispatchAction{

	protected Logger log=null;

	public ConsultaAction(){
		log=Logger.getLogger(this.getClass());
	}

	public ActionForward inicio(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//HttpSession session=request.getSession(false);
		HttpSession session=request.getSession(false);
		//session.getId();
    	Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
		log.debug("Usuario :" + usuario.getTelefono());
		ActionForward frw=mapping.getInputForward();
      
		ConsultaForm form=(ConsultaForm) actionForm;
		form.inicializa();
	//	return frw;
		return mapping.findForward("RedirectConsulta");
	}

	@SuppressWarnings("unchecked")
	public ActionForward agregaMobiles(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{
			ConsultaForm form=(ConsultaForm) actionForm;
			List<String> listaMobiles=(List<String>) session.getAttribute(Constants.MOBILES_SELECCIONADOS);
			if(listaMobiles != null){
				for(String mobile : listaMobiles){
					// verifica si no existe el nro en la lista
					if(!Util.verificaExistenciaNumero(mobile,form.getListaMobiles())){
						Util.agregaMobile(mobile,form.getListaMobiles());
					}
				}
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}

		return mapping.findForward("nuevaConsulta");
	}

	public ActionForward consultaSimpleLBS(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{

			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			ConsultaForm form=(ConsultaForm) actionForm;
			ConsultaService service=new ConsultaService();
			log.debug("Iniciando metodo en ConsultaAction: consultaSimpleLBS");

			List<Mobile> listaMobiles=Util.obtieneListaMobilesValidos(form.getListaMobiles());
			// if(validaFormatoMobiles(listaMobiles)){
			if(!Util.validaMobiles(listaMobiles)){
				request.setAttribute(Constants.MENSAJE_USUARIO,Constants.MENSAJE_MOBILES_REPETIDOS);
				return mapping.findForward("nuevaConsulta");
			}

			ParametroConsultaWeb parametro=new ParametroConsultaWeb();
			parametro.setCodTipoRespuesta(form.getTipoRespuesta());
			parametro.setListaMobiles(listaMobiles);
			parametro.setCodTipoConsulta(Constants.COD_TIPO_CONSULTA_WEB);
			ResultadoConsultaWeb resultado=service.consultaSimpleLBS(usuario,parametro);
			if(resultado != null && resultado.getCodResultado().equals(Constants.COD_RESULTADO_CONSULTA_OK)){
				form.setResultadoConsulta(resultado);
				String sql=service.generaSqlMapa(resultado);
				// session.removeAttribute(Constants.QUERY_SYSTEM_ARQ);
				// session.setAttribute(Constants.QUERY_SYSTEM_ARQ,sql);
				form.setQueryMapa(sql);
				request.setAttribute(Constants.REQUEST_ACTIVA_GRAFICO,Constants.REQUEST_ACTIVA_GRAFICO);
				log.debug("sql ConsultaAction: " + sql);
				log.debug("resultado : " + resultado.getListaResultadoDetalle().size());
				//this.mapa(mapping, actionForm, request, response);
				return mapping.findForward("successConsulta");
				
			}
			if(resultado != null)
				request.setAttribute(Constants.MENSAJE_USUARIO,resultado.getMensaje());

		}
		catch(Exception e){
			log.error(e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}
		
		
		log.debug("Ya llamo a Mapa");
		//return mapping.findForward("mapaConsulta");
		return mapping.findForward("nuevaConsulta");
	}

	public ActionForward siguiente(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{

		return mapping.findForward("successConsulta");
	}

	public ActionForward regresar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return mapping.findForward("RedirectConsulta");
	}

	public ActionForward mapa(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//consultaSimpleLBS(mapping, actionForm, request, response);
		log.debug("paso lo del mapa");
		ConsultaForm form=(ConsultaForm) actionForm;
		log.debug("Mapa en el form : " + form.getQueryMapa());
		
		
		//return null;
		return mapping.findForward("mapagooglemap");
		
		//return mapping.findForward("mapaConsulta");
	}

}
