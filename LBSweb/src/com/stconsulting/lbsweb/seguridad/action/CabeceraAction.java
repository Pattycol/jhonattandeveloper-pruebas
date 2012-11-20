/*
 * SeniorityAction.java
 *
 * Created on July 21, 2004, 11:38 AM
 */

package com.stconsulting.lbsweb.seguridad.action;

import javax.servlet.http.*;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * 
 * @author STConsulting
 */
public class CabeceraAction extends DispatchAction{

	public ActionForward cargar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("Ingrese al cargar de CabeceraAction");
		// PerfilService service=new PerfilService();
		// LoginForm form = (LoginForm)actionForm;
		// inicializar(form,service);
		log.debug("Salir del cargar de Cabecera Action");
		return mapping.findForward("nuevo");

	}
	/*
	 * public ActionForward guardar(ActionMapping mapping, ActionForm
	 * actionForm, HttpServletRequest request, HttpServletResponse response)
	 * throws Exception {
	 * log.debug("Ingrese al metodo guardar del GPAction");
	 * GrupoParametrosService service=new GrupoParametrosService();
	 * GrupoParametrosForm form = (GrupoParametrosForm)actionForm;
	 * GrupoParametros grupoParametros=new GrupoParametros();
	 * grupoParametros.setNombreGrupoParametros
	 * (form.getNombreGrupoParametros());
	 * grupoParametros.setNumParametrosTotales(0); //debido a que arranca vacio,
	 * sin parametros //grupoParametros.setEstado(form.getEstado()); String
	 * flag=request.getParameter("boton"); try{ if(flag.equals("Agregar"))
	 * service.insertar(grupoParametros); /*if(flag.equals("Modificar")){ // lo
	 * estoy dejando solo como ejemplo, ya que tod. no lo uso
	 * seniority.setCodSeniority(form.getCodSeniority());
	 * service.actualizar(seniority); }
	 */
	/*
	 * }catch (Exception e){ } inicializar(form,service);
	 * log.debug("Saliendo del action de GP"); return
	 * mapping.findForward("nuevo");
	 * 
	 * } public ActionForward eliminar(ActionMapping mapping, ActionForm
	 * actionForm, HttpServletRequest request, HttpServletResponse response)
	 * throws Exception {
	 * 
	 * GrupoParametrosService service=new GrupoParametrosService();
	 * GrupoParametrosForm form = (GrupoParametrosForm)actionForm;
	 * GrupoParametros seniority=new GrupoParametros();
	 * 
	 * String selectedList[]=request.getParameterValues("selected");
	 * if(selectedList!=null){ log.debug("selectedList :"+selectedList.length);
	 * }else{ log.debug("selectedList nulo"); } if(selectedList!=null){
	 * service.eliminar((List)form.getListaGrupoParametros(),selectedList); }
	 * inicializar(form,service);
	 * 
	 * return mapping.findForward("nuevo"); }
	 */

	/*
	 * public void inicializar(PerfilForm form, PerfilService service) throws
	 * Exception { ArrayList lista=new ArrayList();
	 * lista=(ArrayList)service.listar(); //form.setCodSeniority("");
	 * form.setListaPerfiles(lista); //form.setEstado('A');
	 * //form.setListaGrupoParametros(lista); }
	 */

}
