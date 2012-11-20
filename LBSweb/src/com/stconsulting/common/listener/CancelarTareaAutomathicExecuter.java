package com.stconsulting.common.listener;

import org.apache.log4j.Logger;
import java.util.*;
import com.stconsulting.common.util.Constants;

import com.stconsulting.common.util.*;
import com.stconsulting.common.bean.*;
import com.stconsulting.lbsweb.connector.*;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.consulta.service.*;


public class CancelarTareaAutomathicExecuter{

	/**
	 * Este metodo es el que se encarga de cancelar las tareas vencidas
	 */
	protected Logger log;
	//private boolean ejecuto=false;

	public void startAutomathicExecuter(){
		//Logger log=Logger.getLogger(this.getClass());
		try{
			long period=Constants.ONCE_PER_DAY;
			log.debug("Periodo :" + period);
			// Realizar inmediatamente la consulta de las tareas
			long delay=Long.parseLong(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RANGO_SEPARACION_HORAS)) * 1000 * 3600;// Constants.DELAY_TAREA;
			Timer timer=new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){
				public void run(){
					ExecuteTask();
				}
			},delay,period);

		}

		catch(Exception e){
			System.err.println("Exception ocurrred, while attempting start Notifyer Cancelar Tarea: " + e);
		}
	}

	@SuppressWarnings("unchecked")
	private void ExecuteTask(){
		try{

			log.debug("Entrando a ExecuteTask de Cancelar Tarea " + new Date());
			Resultado result=new Resultado();
			ConsultaTareaService service=new ConsultaTareaService();
			Tarea filtroTarea=new Tarea();
			filtroTarea.setFechaInicio(new Date());
			filtroTarea.setFechaFin(new Date());
			result=service.getListaTareasCancelar(filtroTarea);
			log.debug("Result Lista Tareas a Cancelar Listener : " + result.getCodigo());
			List<Tarea> listaTareasCancelar=null;
			if(Constants.OK.equals(result.getCodigo())){
				listaTareasCancelar=(List<Tarea>) result.getResult();
				if(listaTareasCancelar != null && listaTareasCancelar.size() > 0){
					for(int i=0;i < listaTareasCancelar.size();i++){
						Tarea task=listaTareasCancelar.get(i);
						task.setCodEstado(Constants.COD_ESTADO_CERRADO);
						try{
							result=service.actualizar(task);
							if(Constants.OK.equals(result.getCodigo())){
								log.debug("Actualizacion en la Tarea " + task.getIdTarea());
							}
						}
						catch(Exception e){
							log.debug("Error al actualizar Tarea " + e);

						}
					}
				}
			}

			// Ejecutando Eliminacion de Archivos Cdr OLD
			log.debug("Entrando a Eliminar Archivos OLD Cancelar Listener");
			CdrGeneratorService cdrGenera=new CdrGeneratorService();
			cdrGenera.removeFilesCdrOld();

		}
		catch(Exception e){
			log.debug("Error en Execute Task Cancelar Tarea :" + e);
		}
		log.debug("Se realizo satisfactoriamente la Actualizacion de las Tareas");
	}

}
