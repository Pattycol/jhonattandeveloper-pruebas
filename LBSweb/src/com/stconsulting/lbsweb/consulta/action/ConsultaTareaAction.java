/*
 * ConsultaAction.java
 *
 * Created on 26 de mayo de 2005, 03:00 PM
 */

package com.stconsulting.lbsweb.consulta.action;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.stconsulting.common.bean.Resultado;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.ConstantsMQT2;
import com.stconsulting.common.util.Helper;
import com.stconsulting.lbsweb.consulta.bean.Mobile;
import com.stconsulting.lbsweb.consulta.bean.Tarea;
import com.stconsulting.lbsweb.consulta.form.ConsultaTareaForm;
import com.stconsulting.lbsweb.consulta.service.ConsultaService;
import com.stconsulting.lbsweb.consulta.service.ConsultaTareaService;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;
import com.stconsulting.lbsweb.util.Util;

/**
 * 
 * @author STconsulting
 */
public class ConsultaTareaAction extends DispatchAction{

	private static Logger log=Logger.getLogger(ConsultaTareaAction.class);

	public ConsultaTareaAction(){
	}

	@SuppressWarnings("unchecked")
	public void inicializar(ConsultaTareaForm form,ConsultaTareaService service,Usuario usuario) throws Exception{
		Tarea filtroTarea=new Tarea();
		filtroTarea.setMobileOrigen(usuario.getTelefono());
		filtroTarea.setCodEstado(Constants.COD_ESTADO_ACTIVO);
		filtroTarea.setFechaInicio(form.getFechaInicio());
		filtroTarea.setFechaFin(form.getFechaFin());
		Resultado result=service.getListaTareas(filtroTarea);
		form.setMobile("");
		form.setListaEstados(Util.getListaEstadosTarea(Constants.COD_TODOS));
		form.setCodEstado(Constants.COD_ESTADO_ACTIVO);

		 form.setListaTareas(new ArrayList());
		if(result != null && result.getCodigo().equals(Constants.OK)){
		form.setListaTareas((List<Tarea>) result.getResult());
		}

	}

	public ActionForward inicio(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		Helper.removeSessions(request);
		Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
		log.debug("Usuario :" + usuario.getTelefono());
		ActionForward frw=mapping.getInputForward();

		ConsultaTareaService service=new ConsultaTareaService();
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		form.setFechaInicio(new Date());
		form.setFechaFin(new Date());
		
		this.inicializar(form,service,usuario);
		
		return mapping.findForward("nuevaConsulta");
		//return frw;
	}

	@SuppressWarnings("unchecked")
	public ActionForward consultaTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{

			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
			ConsultaTareaService service=new ConsultaTareaService();
			Tarea filtroTarea=new Tarea();
			filtroTarea.setMobileOrigen(usuario.getTelefono());
			filtroTarea.setMobile(form.getMobile().trim());
			
			Date fechaInicio=new Date();
			Date fechaFin=new Date();
			Format f=new SimpleDateFormat("dd/MM/yy");
			try{
				fechaInicio=(Date) f.parseObject(form.getFechaInicioString());
				fechaFin=(Date) f.parseObject(form.getFechaFinString());
				
			}
			catch(ParseException e){
				return null;
			}
			form.setFechaInicio(fechaInicio);
			form.setFechaFin(fechaFin);
			
			filtroTarea.setFechaInicio(form.getFechaInicio());
			filtroTarea.setFechaFin(form.getFechaFin());
			filtroTarea.setCodEstado(form.getCodEstado());
		
			log.debug("Iniciando metodo en ConsultaTareaAction: consultaTarea");
			Resultado result=service.getListaTareas(filtroTarea);
			if(result != null && result.getCodigo().equals(Constants.OK)){
				form.setListaTareas((List<Tarea>) result.getResult());

			}
			else{
				if(result != null){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
					saveErrors(request,errors);
				}
			}
		

		}
		catch(Exception e){
			log.error(e);
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}
		
		return mapping.findForward("nuevaConsulta");
	}

	// Nueva Tarea
	public ActionForward nuevaTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("Entrado a NuevaTareaAction");
		Helper.removeSessions(request);
		HttpSession session=request.getSession(false);
		//ConsultaTareaService service=new ConsultaTareaService();
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		String intervalo=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_CONSULTA);

		form.setListaMobiles(Util.inicializaMobiles());
		form.setListaHorarios(Util.getListaHorarios());
		form.setCodFormato(Constants.COD_SELECCIONE);
		form.setCodHorario(Constants.COD_SELECCIONE);
		form.setCodPeriodo(Constants.COD_SELECCIONE);
		form.setIntervalo(intervalo);
		form.setFechaInicio(null);
		form.setFechaFin(null);
		form.setSelectedItems(new String[]{});
		Date fechaHoy=new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaHoy);

        int horaInicio = calendar.get(Calendar.HOUR_OF_DAY);
        int horaFin = calendar.get(Calendar.HOUR_OF_DAY);

        session.setAttribute("horaInicio", horaInicio);
        session.setAttribute("horaFin", horaFin);

        session.setAttribute(Constants.SESSION_NUEVA_TAREA, Constants.NUEVA_TAREA);
        session.setAttribute(Constants.SESSION_FECHA_INICIO, fechaHoy);
        session.setAttribute(Constants.SESSION_FECHA_FIN, fechaHoy);
        session.setAttribute("fechaHoy", fechaHoy);
        return mapping.findForward("nuevaTarea");
	}

	public ActionForward modificarTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("Entrado a ModificarTareaAction");
		HttpSession session=request.getSession(false);
		String indice=request.getParameter("indice");
		ConsultaService serviceConsulta=new ConsultaService();
		// ConsultaTareaService service=new ConsultaTareaService();
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		List<Tarea> listaTareas=form.getListaTareas();
		Tarea tarea=listaTareas.get(Integer.parseInt(indice) - 1);
		form.setIdTarea(tarea.getIdTarea());
		form.setListaMobiles(tarea.getListaMobiles());
		
		//form.setCodFormato(tarea.getCodFormato());
		form.setCodFormato("1");
		
		//form.setCodHorario(tarea.getCodHorario());
		//RangoHora horario = Helper.getRangoHora(tarea.getHoraInicio(), tarea.getHoraFin());
		form.setCodHorario("1");
		//form.setDescHorario(horario.getDescHorario());
		//form.setCodPeriodo(tarea.getCodPeriodo());
		if(tarea.getIntervaloDias().equals(Constants.COD_PERIODO_DIARIO)){
			form.setCodPeriodo(Constants.COD_PERIODO_DIARIO);
		}else{
			form.setCodPeriodo(Constants.COD_PERIODO_SEMANAL);
		}
		form.setHoraInicio(tarea.getHoraInicio());
		form.setHoraFin(tarea.getHoraFin());
		
		form.setFechaInicio(tarea.getFechaInicio());
		form.setFechaFin(tarea.getFechaFin());
		//form.setSelectedItems(Util.getSelectedIndex(tarea.getDiaPeriodo()));
		form.setIntervalo(Integer.toString(tarea.getIntervalo()));
		//form.setDescIntervalo(Integer.toString(tarea.getIntervalo()) + " minutos");
		form.setCodEstado(tarea.getCodEstado());
		form.setListaEstados(Util.getListaEstadosTarea(tarea.getCodEstado()));
		//form.setDescHorario(tarea.getDescHorario());
		session.setAttribute(Constants.SESSION_NUEVA_TAREA,Constants.MODIFICAR_TAREA);
		session.setAttribute(Constants.SESSION_CANT_MOBILES_SEL,Integer.toString(form.getListaMobiles().size()));
		session.setAttribute(Constants.SESSION_FECHA_INICIO,form.getFechaInicio());
		session.setAttribute(Constants.SESSION_FECHA_FIN,form.getFechaFin());
		// para generar el grafico
		form.setLocalizaciones(serviceConsulta.obtenerLocalizaciones(tarea));
		log.debug("Tama�o de la s Localizaciones :  "+form.getLocalizaciones().size());
		// session.removeAttribute(Constants.QUERY_SYSTEM_ARQ);
		// session.setAttribute(Constants.QUERY_SYSTEM_ARQ,sql);
		//form.setQueryMapa(sql);
		//log.debug("sql ConsultaTareaAction: " + sql);
		request.setAttribute(Constants.REQUEST_ACTIVA_GRAFICO,Constants.REQUEST_ACTIVA_GRAFICO);
		session.setAttribute(Constants.REQUEST_GRAFICO_AUTOMATICO,Constants.REQUEST_GRAFICO_AUTOMATICO);
		return mapping.findForward("nuevaTareaModifica");
	}

	public ActionForward pasar(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		form.setListaEstados(Util.getListaEstadosTarea(Constants.COD_TODOS));
		return mapping.findForward("nuevaConsulta");
	}

	public ActionForward regresarNuevaTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{

		HttpSession session=request.getSession(false);
		session.setAttribute(Constants.SESSION_NUEVA_TAREA,Constants.NUEVA_TAREA);
		return mapping.findForward("nuevaTarea");
	}

	@SuppressWarnings("unchecked")
	public ActionForward agregaMobiles(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{
			ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
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

		return mapping.findForward("nuevaTarea");
	}

	public ActionForward registrarTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("Entrado a RegistrarTareaAction");
		HttpSession session=request.getSession(false);
		ActionErrors errors=new ActionErrors();
		try{
			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			ConsultaTareaService service=new ConsultaTareaService();
			ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
			// int
			// tiempoConsulta=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_CONSULTA));
			List<Mobile> listaMobiles=Util.obtieneListaMobilesValidos(form.getListaMobiles());
			if(!Util.validaMobiles(listaMobiles)){
				request.setAttribute(Constants.MENSAJE_USUARIO,Constants.MENSAJE_MOBILES_REPETIDOS);
				return mapping.findForward("nuevaTarea");
			}
			Tarea tarea=new Tarea();
			tarea.setCantPeriodo(Constants.RANGO_HORARIO);
			tarea.setCodEstado(Constants.COD_ESTADO_ACTIVO);
			tarea.setCodFormato(form.getCodFormato());

			tarea.setCodPeriodo(form.getCodPeriodo());
			if(form.getCodPeriodo().equals(Constants.COD_PERIODO_DIARIO)){
				tarea.setIntervaloDias(Constants.COD_PERIODO_DIARIO);
			}else{
				tarea.setIntervaloDias(Helper.getListaItemsSelected(form.getSelectedItems()));
			}
			
			Format f=new SimpleDateFormat("dd/MM/yy");
            Date fechaInicio = new Date();
            Date fechaFin = new Date();
            try{
                fechaInicio = (Date) f.parseObject(form.getFechaInicioString());
                fechaFin = (Date) f.parseObject(form.getFechaFinString());
            } catch (Exception e) {

            }

            form.setFechaInicio(fechaInicio);
            form.setFechaFin(fechaFin);
			
			tarea.setFechaInicio(form.getFechaInicio());
			tarea.setFechaFin(form.getFechaFin());
			tarea.setListaMobiles(Util.obtieneListaMobilesValidos(form.getListaMobiles()));
			tarea.setDiaPeriodo(Helper.getListaItemsSelected(form.getSelectedItems()));
			tarea.setIntervalo(Integer.parseInt(form.getIntervalo()));
			// tarea.setIntervalo(tiempoConsulta);
			// se modifica la asignacion de intervalo, ahora puede seleccionar
			// tarea.setIntervalo(tiempoConsulta);
			// Rango Horario
//			RangoHora rangoHora=Helper.getRangoHora(form.getCodHorario(),form.getListaHorarios());
//			tarea.setCodHorario(rangoHora.getCodHorario());
//			tarea.setDescHorario(rangoHora.getDescHorario());
//			tarea.setHoraInicio(rangoHora.getHoraInicio());
//			tarea.setHoraFin(rangoHora.getHoraFin());
			
			tarea.setCodHorario("1");
			tarea.setHoraInicio(form.getHoraInicio());
			tarea.setHoraFin(form.getHoraFin());
			

			session.setAttribute(Constants.SESSION_FECHA_INICIO,form.getFechaInicio());
			session.setAttribute(Constants.SESSION_FECHA_FIN,form.getFechaFin());
			Resultado result=null;
			// Validar si el numero existe
			int dias=Helper.getDiferenciaDias(tarea.getFechaInicio(),tarea.getFechaFin());
			int nummaxDias=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MAX_DIAS_TAREA));

			result=service.validarNumero(usuario,tarea.getListaMobiles());
			log.debug("VALIDAR NUMERO VALIDO :" + result.getCodigo());
			if(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO.equals(result.getCodigo())){
				request.setAttribute(Constants.MENSAJE_USUARIO,result.getMensaje());
				return mapping.findForward("nuevaTarea");
			}
			else if(Constants.NO_OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
				saveErrors(request,errors);
				return mapping.findForward("nuevaTarea");
			}

			if(dias > nummaxDias){
				request.setAttribute(Constants.MENSAJE_USUARIO,Constants.MENSAJE_DIFERENCIA_DIAS_TAREA);
				return mapping.findForward("nuevaTarea");
			}

			// Validar si selecciono correctamente los dias dentro del rango de
			// fechas ingresado
			if(Constants.COD_PERIODO_SEMANAL.equals(tarea.getCodPeriodo())){
				result=service.isDiaPeriodoCorrecto(tarea);
				if(Constants.COD_DIAS_INCORRECTO.equals(result.getCodigo())){
					request.setAttribute(Constants.MENSAJE_USUARIO,result.getMensaje());
					return mapping.findForward("nuevaTarea");
				}
				else if(Constants.NO_OK.equals(result.getCodigo())){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
					saveErrors(request,errors);
					return mapping.findForward("nuevaTarea");
				}
			}

			// Validar la Hora

			String fechaHoy=Helper.formateaFecha(new Date(),ConstantsMQT2.FORMATO_FECHA_MOSTRAR);
			if(tarea.getFechaInicio().equals(fechaHoy) && tarea.getFechaFin().equals(fechaHoy)){
				result=service.isValidHour(tarea);
				if(Constants.COD_HORA_INVALIDA.equals(result.getCodigo())){
					request.setAttribute(Constants.MENSAJE_USUARIO,result.getMensaje());
					return mapping.findForward("nuevaTarea");
				}
				else if(Constants.NO_OK.equals(result.getCodigo())){
					errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
					saveErrors(request,errors);
					return mapping.findForward("nuevaTarea");
				}
			}
			// Validar si es una tarea valida y no se encuentra en estado activa
			// o inactiva en otra tarea
			result=service.isValidTask(tarea);
			log.debug("IS VALID TASK :" + result.getCodigo());
			if(Constants.COD_TAREA_INVALIDA.equals(result.getCodigo())){
				request.setAttribute(Constants.MENSAJE_USUARIO,result.getMensaje());
				return mapping.findForward("nuevaTarea");
			}
			else if(Constants.NO_OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
				saveErrors(request,errors);
				return mapping.findForward("nuevaTarea");
			}
			// Calcular la cantidad de Consultas a realizar
			result=service.calucarCantidadConsultas(tarea);
			log.debug("CANT CONSULTA :" + result.getCodigo());
			if(Constants.OK.equals(result.getCodigo())){
				request.setAttribute(Constants.MENSAJE_CANTIDAD_CONSULTA,result.getMensaje());
                session.setAttribute("horaInicio", tarea.getHoraInicio());
                session.setAttribute("horaFin", tarea.getHoraFin());
				session.setAttribute(Constants.SESSION_NUEVA_TAREA,Constants.CONFIRMAR_TAREA);
				session.setAttribute(Constants.SESSION_CANT_MOBILES_SEL,Integer.toString(form.getListaMobiles().size()));
				return mapping.findForward("nuevaTarea");
			}
			else if(Constants.NO_OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
				saveErrors(request,errors);
				return mapping.findForward("nuevaTarea");
			}

		}
		catch(Exception e){
			log.error(e);
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
		}
		return mapping.findForward("nuevaTarea");
	}

	public ActionForward grabarModificarTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("Entrado a grabarModificarTarea");
		ActionErrors errors=new ActionErrors();
		//HttpSession session=request.getSession(false);
		ConsultaTareaService service=new ConsultaTareaService();
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		Resultado result=new Resultado();
		Tarea tarea=new Tarea();
		try{
			tarea.setIdTarea(form.getIdTarea());
			tarea.setCodEstado(form.getCodEstado());
			result=service.actualizar(tarea);
			if(Constants.NO_OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.grabar.modificar"));
				saveErrors(request,errors);
				return mapping.findForward("nuevaTarea");
			}
			else if(Constants.OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("mensaje.grabar.modificar"));
				saveErrors(request,errors);
			}

		}
		catch(Exception e){
			log.error(e);
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
			return mapping.findForward("nuevaTarea");

		}
		log.debug("Antes del FORWARD");
		return mapping.findForward("successConsultaTarea");
	}

	public ActionForward confirmarTarea(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		log.debug("Entrado a grabarModificarTarea");
		ActionErrors errors=new ActionErrors();
		HttpSession session=request.getSession(false);
		ConsultaTareaService service=new ConsultaTareaService();
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		Resultado result=new Resultado();
		Tarea tarea=new Tarea();
		try{
			Usuario usuario=(Usuario) session.getAttribute(Constants.USUARIO_LOGUEADO);
			// int
			// tiempoConsulta=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_CONSULTA));
			tarea.setCantPeriodo(Constants.RANGO_HORARIO);
			tarea.setCodEstado(Constants.COD_ESTADO_ACTIVO);
			tarea.setCodFormato(form.getCodFormato());

			tarea.setCodPeriodo(form.getCodPeriodo());
			if(form.getCodPeriodo().equals(Constants.COD_PERIODO_DIARIO)){
				tarea.setIntervaloDias(Constants.COD_PERIODO_DIARIO);
			}else{
				tarea.setIntervaloDias(Helper.getListaItemsSelected(form.getSelectedItems()));
			}
			
			tarea.setListaMobiles(Util.obtieneListaMobilesValidos(form.getListaMobiles()));
			tarea.setDiaPeriodo(Helper.getListaItemsSelected(form.getSelectedItems()));
			tarea.setFechaInicio(form.getFechaInicio());
			tarea.setFechaFin(form.getFechaFin());
			tarea.setDescFormato(Helper.getDescripcion(tarea.getCodFormato(),form.getListaFormatos()));

			tarea.setDescPeriodo(Helper.getDescripcion(tarea.getCodPeriodo(),form.getListaPeriodos()));
			tarea.setIntervalo(Integer.parseInt(form.getIntervalo()));
			tarea.setMobileOrigen(usuario.getTelefono());
			tarea.setCodEmpresa(usuario.getCodEmpresa());
			// Rango Horario
//			RangoHora rangoHora=Helper.getRangoHora(form.getCodHorario(),form.getListaHorarios());
//			tarea.setCodHorario(rangoHora.getCodHorario());
//			tarea.setDescHorario(rangoHora.getDescHorario());
//			tarea.setHoraInicio(rangoHora.getHoraInicio());
//			tarea.setHoraFin(rangoHora.getHoraFin());
			
		tarea.setCodHorario("1");
		tarea.setHoraInicio(form.getHoraInicio());
		tarea.setHoraFin(form.getHoraFin());
			
			

			result=service.insertar(tarea);
			if(Constants.NO_OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.grabar.nuevo"));
				saveErrors(request,errors);
				return mapping.findForward("nuevaTarea");
			}
			else if(Constants.OK.equals(result.getCodigo())){
				errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("mensaje.exito.grabar"));
				saveErrors(request,errors);
			}

		}
		catch(Exception e){
			log.error(e);
			errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("errors.general"));
			saveErrors(request,errors);
			return mapping.findForward("nuevaTarea");

		}
		return mapping.findForward("successConsultaTarea");
	}

	public ActionForward mapa(ActionMapping mapping,ActionForm actionForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ConsultaTareaForm form=(ConsultaTareaForm) actionForm;
		
		log.debug("Mapa en el form : " + form.getQueryMapa());
		return mapping.findForward("mapaConsultaTareaGoogleMaps");
	}
}
