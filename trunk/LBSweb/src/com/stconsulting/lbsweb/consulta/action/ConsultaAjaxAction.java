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
public class ConsultaAjaxAction extends DispatchAction{

	protected Logger log=null;

	public ConsultaAjaxAction(){
		log=Logger.getLogger(this.getClass());
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

		return mapping.findForward("nuevaConsulta");
	}

	
	
	public ActionForward mapa(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ConsultaForm form=(ConsultaForm) actionForm;
		log.debug("Mapa en el form Ajax: " + form.getQueryMapa());
		return null ;
		//mapping.findForward("mapagooglemap");
	}

}
