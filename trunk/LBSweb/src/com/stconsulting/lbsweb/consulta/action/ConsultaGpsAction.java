/*
 * ConsultaGpsAction.java
 *
 * Created on 12 de agosto de 2005, 12:15 PM
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
import com.stconsulting.lbsweb.consulta.service.ConsultaGpsService;
import com.stconsulting.lbsweb.util.Util;

import com.stconsulting.lbsweb.seguridad.bean.Usuario;

/**
 * 
 * @author STCosulting
 */
public class ConsultaGpsAction extends DispatchAction{

	protected Logger log=null;

	public ConsultaGpsAction(){
		log=Logger.getLogger(this.getClass());
	}

	public ActionForward inicio(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);

		Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
		log.debug("Usuario :" + usuario.getTelefono());
		ActionForward frw=mapping.getInputForward();

		ConsultaForm form=(ConsultaForm) actionForm;
		form.inicializa();
		Date fechaHoy=new Date();
		form.setFechaInicio(fechaHoy);
		form.setFechaFin(fechaHoy);
		return frw;
	}

	public ActionForward consultaGPS(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{

			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			ConsultaForm form=(ConsultaForm) actionForm;
			ConsultaGpsService service=new ConsultaGpsService();

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
			// parametro.setFechaInicio(form.getFechaInicio());
			// parametro.setFechaFin(form.getFechaFin());
			ResultadoConsultaWeb resultado=service.consultaGPS(usuario,parametro);
			if(resultado != null && resultado.getCodResultado().equals(Constants.COD_RESULTADO_CONSULTA_OK)){
				form.setResultadoConsulta(resultado);
				log.debug("Entrando a generar la consulta del mapa");
				String sql=service.generaSqlMapaConsultaGps(parametro);
				log.debug("Entrando a dibujar el mapa");
				form.setQueryMapa(sql);
				request.setAttribute(Constants.REQUEST_ACTIVA_GRAFICO,Constants.REQUEST_ACTIVA_GRAFICO);
				return mapping.findForward("success");
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

	public ActionForward siguiente(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return mapping.findForward("success");
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

	public ActionForward regresar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return mapping.findForward("nuevaConsulta");
	}

	public ActionForward mapa(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//ConsultaForm form=(ConsultaForm) actionForm;
		return mapping.findForward("mapa");
	}
}
