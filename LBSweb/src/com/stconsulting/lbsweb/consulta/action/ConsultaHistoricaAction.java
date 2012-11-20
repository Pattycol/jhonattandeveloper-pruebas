/*
 * ConsultaHistoricaAction.java
 *
 * Created on 8 de junio de 2005, 06:59 PM
 */

package com.stconsulting.lbsweb.consulta.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.stconsulting.common.util.Helper;
import com.stconsulting.lbsweb.consulta.bean.Mobile;
import com.stconsulting.lbsweb.consulta.bean.ParametroConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.ResultadoConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.ResultadoReporteWeb;
import com.stconsulting.lbsweb.consulta.form.ConsultaForm;
import com.stconsulting.lbsweb.consulta.service.ConsultaService;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;
import com.stconsulting.lbsweb.util.Util;

/**
 * 
 * @author STCosulting
 */
public class ConsultaHistoricaAction extends DispatchAction{

	protected Logger log=null;

	public ConsultaHistoricaAction(){
		log=Logger.getLogger(this.getClass());
	}

	public ActionForward inicio(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		session.getId();
		Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
		log.debug("Usuario :" + usuario.getTelefono());
		ConsultaForm form=(ConsultaForm) actionForm;
		log.debug("actionForm : " +actionForm.toString());
		form.inicializa();
		Date fechaHoy=new Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
		form.setFechaInicioString(sdf.format(fechaHoy));
		form.setFechaFinString(sdf.format(fechaHoy));
		return mapping.findForward("redirectHistorico");
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
		return mapping.findForward("consultaHistorica");
	}

	public ActionForward consultaHistoricaLBS(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{

			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			ConsultaForm form=(ConsultaForm) actionForm;
			ConsultaService service=new ConsultaService();
			log.debug("Iniciando metodo en ConsultaAction: consultaHistoricaLBS");
			List<Mobile> listaMobiles=Util.obtieneListaMobilesValidos(form.getListaMobiles());
			if(!Util.validaMobiles(listaMobiles)){
				request.setAttribute(Constants.MENSAJE_USUARIO,Constants.MENSAJE_MOBILES_REPETIDOS);
				return mapping.findForward("consultaHistorica");
			}
			// Validar rango de fecha permitido
			int dias=Helper.getDiferenciaDias(form.getFechaInicio(),form.getFechaFin());
			int nummaxDias=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MAX_DIAS_HISTORICO));

			if(dias > nummaxDias){
				request.setAttribute(Constants.MENSAJE_USUARIO,Constants.MENSAJE_DIFERENCIA_DIAS_HISTORICO);
				// return mapping.findForward("consultaHistorica");
			}
			else{

				ParametroConsultaWeb parametro=new ParametroConsultaWeb();
				parametro.setMobileOrigen(usuario.getTelefono());
				parametro.setListaMobiles(listaMobiles);
				parametro.setFechaInicio(form.getFechaInicio());
				Calendar fin=Calendar.getInstance();
				fin.setTime(form.getFechaFin());
				fin.set(Calendar.HOUR_OF_DAY,23);
				fin.set(Calendar.MINUTE,59);
				fin.set(Calendar.SECOND,59);
				fin.set(Calendar.MILLISECOND,999);
				parametro.setFechaFin(fin.getTime());
				ResultadoConsultaWeb resultado=service.consultaHistoricaLBS(usuario,parametro);
				if(resultado != null && resultado.getCodResultado().equals(Constants.COD_RESULTADO_CONSULTA_OK)){
					form.setResultadoConsulta(resultado);
					// String sql=service.generaSqlMapa(resultado);
					String sql=service.generaSqlMapa(parametro);

					form.setQueryMapa(sql);

					request.setAttribute(Constants.REQUEST_ACTIVA_GRAFICO,Constants.REQUEST_ACTIVA_GRAFICO);
					log.debug("sql ConsultaHistoricaAction: " + sql);
					if(resultado.getListaResultadoDetalle() != null){
						log.debug("resultado : " + resultado.getListaResultadoDetalle().size());
					}
					return mapping.findForward("success");
				}
				if(resultado != null)
					request.setAttribute(Constants.MENSAJE_USUARIO,resultado.getMensaje());
			}
			return mapping.findForward("consultaHistorica");

		}
		catch(Exception e){
			log.error(e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}
		return mapping.findForward("consultaHistorica");
	}

	public ActionForward siguiente(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{

		return mapping.findForward("success");
	}

	public ActionForward siguienteReporte(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{

		return mapping.findForward("reporte");
	}

	public ActionForward regresar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return mapping.findForward("consultaHistorica");
	}
	/*Modificado en el return por Samira Benazar*/
	public ActionForward generaReporteLBS(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{
			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			ConsultaForm form=(ConsultaForm) actionForm;
			ConsultaService service=new ConsultaService();
			List<Mobile> listaMobiles=Util.obtieneListaMobilesValidos(form.getListaMobiles());
			ParametroConsultaWeb parametro=new ParametroConsultaWeb();
			parametro.setListaMobiles(listaMobiles);
			Calendar inicio=Calendar.getInstance();
			inicio.setTime(form.getFechaInicio());
			inicio.set(Calendar.HOUR_OF_DAY,0);
			inicio.set(Calendar.MINUTE,0);
			inicio.set(Calendar.SECOND,0);
			inicio.set(Calendar.MILLISECOND,0);
			parametro.setFechaInicio(inicio.getTime());
			Calendar fin=Calendar.getInstance();
			fin.setTime(form.getFechaFin());
			fin.set(Calendar.HOUR_OF_DAY,23);
			fin.set(Calendar.MINUTE,59);
			fin.set(Calendar.SECOND,59);
			fin.set(Calendar.MILLISECOND,999);
			parametro.setFechaFin(fin.getTime());
			ResultadoReporteWeb resultado=service.generaReporteLBS(usuario,parametro);
			if(resultado != null && resultado.getCodResultado().equals(Constants.COD_RESULTADO_CONSULTA_OK)){
				form.setResultadoReporte(resultado);

				log.debug("resultado : " + resultado.getListaResultadoDetalle().size());
				request.setAttribute(Constants.REQUEST_ACTIVA_REPORTE,Constants.REQUEST_ACTIVA_REPORTE);
			}
			else{
				if(resultado != null)
					request.setAttribute(Constants.MENSAJE_USUARIO,resultado.getMensaje());
			}
		}
		catch(Exception e){
			log.error(e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}
		return mapping.findForward("reporte");
	}

	public ActionForward imprimir(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return mapping.findForward("impresion");
	}

	public ActionForward excel(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		ConsultaForm form=(ConsultaForm) actionForm;
		ResultadoReporteWeb resultado= form.getResultadoReporte();
		
		
		return mapping.findForward("excel2");
	}

	public ActionForward mapa(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ConsultaForm form=(ConsultaForm) actionForm;
		log.debug("Mapa en el form : " + form.getQueryMapa());
		return mapping.findForward("mapa");
	}
}
