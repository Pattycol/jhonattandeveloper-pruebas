/*
 * BusquedaNumeroAsociadoAction.java
 *
 * Created on 2 de junio de 2005, 10:47 AM
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
import com.stconsulting.common.util.Helper;
import com.stconsulting.lbsweb.consulta.bean.ResultadoBusquedaNumero;
import com.stconsulting.lbsweb.consulta.form.BusquedaNumeroAsociadoForm;
import com.stconsulting.lbsweb.consulta.service.BusquedaNumeroAsociadoService;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

/**
 * 
 * @author STCosulting
 */
public class BusquedaNumeroAsociadoAction extends DispatchAction{

	protected Logger log=null;

	public BusquedaNumeroAsociadoAction(){
		log=Logger.getLogger(this.getClass());
	}

	public ActionForward inicio(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionForward frw=mapping.getInputForward();
		log.debug("Ejecutando en action BusquedaNumeroAsociadoAction metodo : inicio() ");
		BusquedaNumeroAsociadoForm form=(BusquedaNumeroAsociadoForm) actionForm;
		form.inicializa();
		return frw;
	}

	public ActionForward buscar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionErrors errors=new ActionErrors();
		try{
			HttpSession session=request.getSession(false);
			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);

			BusquedaNumeroAsociadoForm form=(BusquedaNumeroAsociadoForm) actionForm;
			BusquedaNumeroAsociadoService service=new BusquedaNumeroAsociadoService();
			List<ResultadoBusquedaNumero> listaResultado=service.busquedaNumeros(usuario,form.getMobile());
			form.setListaResultadoBusqueda(listaResultado);

		}
		catch(Exception e){
			log.error(e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}

		return mapping.findForward("success");
	}

	public ActionForward seleccionar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ActionErrors errors=new ActionErrors();
		try{
			HttpSession session=request.getSession(false);
			BusquedaNumeroAsociadoForm form=(BusquedaNumeroAsociadoForm) actionForm;
			List<String> listaMobilesSeleccionados=new ArrayList<String>();
			if(form.getMobiles() != null){
				for(String mobile : form.getMobiles())
					listaMobilesSeleccionados.add(mobile);
			}
			session.setAttribute(Constants.MOBILES_SELECCIONADOS,listaMobilesSeleccionados);
			request.setAttribute(Constants.ACCION_CERRAR,Constants.ACCION_CERRAR);
		}
		catch(Exception e){
			log.error(e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}
		return mapping.findForward("success");
	}

	public ActionForward siguiente(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{

		// Actualiza los checks seleccionados
		Helper.updateArrayChecksForm(actionForm,request,new String[]{"param"},new String[]{"mobiles"});
		return mapping.findForward("success");
	}

}
