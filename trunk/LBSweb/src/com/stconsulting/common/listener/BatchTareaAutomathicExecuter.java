package com.stconsulting.common.listener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.stconsulting.common.bean.Resultado;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.common.util.Constants;
import com.stconsulting.lbsweb.consulta.bean.ParametroConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.ResultadoConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.Tarea;
import com.stconsulting.lbsweb.consulta.service.ConsultaService;
import com.stconsulting.lbsweb.consulta.service.ConsultaTareaService;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

public class BatchTareaAutomathicExecuter implements Runnable{

	/**
	 * Este metodo es el que se encarga de realizar el batchero de consultas de
	 * las tareas configuradas
	 */
	protected Logger log;
	// private boolean ejecuto=false;

	private List<Tarea> tareas1;
	private List<Tarea> tareas2;
	private List<Tarea> tareas3;
	private List<Tarea> tareas4;
	private List<Tarea> tareas5;

	Thread proceso1;
	Thread proceso2;
	Thread proceso3;
	Thread proceso4;
	Thread proceso5;

	@Override
    public void run(){


		ConsultaTareaService service=new ConsultaTareaService();
		final ConsultaService serviceConsulta=new ConsultaService();
		Thread actual=Thread.currentThread();

		log.debug("hilo actual:" + actual.getName() + ":::" + actual);
		if(actual.equals(proceso1)){
			log.debug("Ejecutando hilo1 -- " + tareas1.size() + " tareas");
			verificarTareas(service,serviceConsulta,tareas1);
		}
		if(actual.equals(proceso2)){
			log.debug("Ejecutando hilo2 -- " + tareas2.size() + " tareas");
			verificarTareas(service,serviceConsulta,tareas2);
		}
		if(actual.equals(proceso3)){
			log.debug("Ejecutando hilo3 -- " + tareas3.size() + " tareas");
			verificarTareas(service,serviceConsulta,tareas3);
		}
		if(actual.equals(proceso4)){
			log.debug("Ejecutando hilo4 -- " + tareas4.size() + " tareas");
			verificarTareas(service,serviceConsulta,tareas4);
		}

		if(actual.equals(proceso5)){
			log.debug("Ejecutando hilo5 -- " + tareas5.size() + " tareas");
			verificarTareas(service,serviceConsulta,tareas5);
		}
	}

	public void startAutomathicExecuter(){
		log=Logger.getLogger(this.getClass());
		try{
			log.debug("Entrando al Listener " + new java.util.Date());
			long period=Constants.TIEMPO_REPETICION_TAREA;
			log.debug("Periodo :" + period);
			log.debug("startAutomathicExecuter() : Periodo = " + period);
			// Realizar inmediatamente la consulta de las tareas
			long delay=Constants.DELAY_TAREA;//demora en la tarea
			// Date delay=this.calcDelay();
			//period=10000;
			Timer timer=new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				@Override
                public void run(){
					ExecuteTask();
				}
			},delay,period);

		}

		catch(Exception e){
			System.err.println("Exception ocurrred, while attempting start Notifyer: " + e.getMessage());
			log.error("Exception ocurrred, while attempting start Notifyer: " + e.getMessage());
		}
	}

	/*
	 * private Date calcDelay(){ int min=0; Calendar calendar=new
	 * GregorianCalendar(); try{ min=calendar.get(Calendar.MINUTE);
	 * calendar.add(Calendar.MINUTE,Helper.getAddMinutes(min));
	 * calendar.set(Calendar.SECOND,0); } catch(Exception e){
	 * log.debug("Exception in CalcDelay " + e.getMessage());
	 * 
	 * } return calendar.getTime();
	 * 
	 * }
	 */

	@SuppressWarnings("unchecked")
	private void ExecuteTask(){
		log.debug("Entrando a ExecuteTask " + new java.util.Date());
		log.debug("Entrando a ExecuteTask " + new java.util.Date().toString());
		Resultado result=new Resultado();
		ConsultaTareaService service=new ConsultaTareaService();
		Tarea filtroTarea=new Tarea();
		log.debug("antes de formatear fecha");
		log.debug("despues de formatear fecha");
		filtroTarea.setFechaInicio(new Date());
		filtroTarea.setFechaFin(new Date());
		filtroTarea.setCodEstado(Constants.COD_ESTADO_ACTIVO);
		filtroTarea.setMobile("");
		log.debug("antes de lista tareas");
		try{
			result=service.getListaTareasBatch(filtroTarea);
		
			log.debug("despues de lista tareas");
			List<Tarea> listaTareas=null;
	
			log.debug("Result :" + result.getDescripcion());
			if(Constants.OK.equals(result.getCodigo())){
				listaTareas=(List<Tarea>) result.getResult();
				log.debug("listaTareas size:" + listaTareas.size());
				if(listaTareas.size() > 0){
					lanzarHilos(listaTareas);
	
				}
			}
		}
		catch(ServiceException e){
			log.error(e.getMessage(),e);
		}
	}

	private void lanzarHilos(List<Tarea> listaTareas){
		tareas1=new ArrayList<Tarea>();
		tareas2=new ArrayList<Tarea>();
		tareas3=new ArrayList<Tarea>();
		tareas4=new ArrayList<Tarea>();
		tareas5=new ArrayList<Tarea>();
		int div5_=(listaTareas.size() / 5);
		int mod5_=listaTareas.size() % 5;
		proceso1=new Thread(this);
		tareas1=listaTareas.subList(0,div5_);
		proceso2=new Thread(this);
		tareas2=listaTareas.subList(div5_,div5_ * 2);
		proceso3=new Thread(this);
		tareas3=listaTareas.subList(div5_ * 2,div5_ * 3);
		proceso4=new Thread(this);
		tareas4=listaTareas.subList(div5_ * 3,div5_ * 4);
		proceso5=new Thread(this);
		tareas5=listaTareas.subList(div5_ * 4,div5_ * 5 + mod5_);

		log.debug("Arrancando Hilos");
		proceso1.start();
		proceso2.start();
		proceso3.start();
		proceso4.start();
		proceso5.start();

	}

	private void verificarTareas(ConsultaTareaService service,final ConsultaService serviceConsulta,List<Tarea> listaTareas){
		boolean isValidExecute=false;
		Calendar calendarHoy=new GregorianCalendar();
		calendarHoy.setTime(new Date());
		for(int i=0;i < listaTareas.size();i++){
			final Tarea task=listaTareas.get(i);
			isValidExecute=service.IsValidaTaskExecute(task,calendarHoy);
			if(isValidExecute){
				// Ejecutar Tarea de acuerdo a la programacion que se tiene

				try{
					log.debug("Ejecutando Tarea= descripcion:" + task.getDescripcion() + "--id: " + task.getIdTarea());
					final ParametroConsultaWeb parametro=new ParametroConsultaWeb();
					parametro.setCodTipoRespuesta(Constants.COD_TIPO_RPTA_PTOREF);
					log.debug("Lista Mobiles plena ejecucion :" + task.getListaMobiles().size());
					parametro.setListaMobiles(task.getListaMobiles());
					parametro.setCodTipoConsulta(Constants.COD_TIPO_CONSULTA_TAREA);
					parametro.setCodTarea(task.getIdTarea());
					final Usuario usuario=new Usuario();
					usuario.setTelefono(task.getMobileOrigen());
					usuario.setCodEmpresa(task.getCodEmpresa());

					ResultadoConsultaWeb resultado=serviceConsulta.consultaSimpleLBS(usuario,parametro);

					if(resultado != null && resultado.getCodResultado().equals(Constants.COD_RESULTADO_CONSULTA_OK)){
						log.debug("Consulta Realizada Satisfactoriamente para la Tarea :" + task.getDescripcion() + "--id: " + task.getIdTarea());
					}
					else{
						if(resultado != null){
							log.debug("Consulta Realizada con PROBLEMAS COD_RES: " + resultado.getCodResultado() + " para la Tarea : " + task.getDescripcion());
						}
					}

				}
				catch(Exception e){
					// Guardar Errores en Log o Tablas
					log.debug("Error al realizar la consulta de la Tarea: " + task.getDescripcion());
					log.debug("Exception :" + e.getMessage());
					log.error("Error al realizar la consulta de la Tarea: " + task.getDescripcion());
					log.error("Exception : " + e.getMessage());
				}

			}
		}
	}

	public void ejecutaConsulta(ConsultaService serviceConsulta){

	}

}
