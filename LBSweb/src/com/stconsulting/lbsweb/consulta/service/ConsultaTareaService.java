/*
 * ConsultaService.java
 *
 * Created on 27 de mayo de 2005, 01:14 PM
 */

package com.stconsulting.lbsweb.consulta.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.stconsulting.common.bean.Resultado;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.ConstantsMQT2;
import com.stconsulting.common.util.Converter;
import com.stconsulting.common.util.Helper;
import com.stconsulting.lbsweb.consulta.bean.Mobile;
import com.stconsulting.lbsweb.consulta.bean.Tarea;
import com.stconsulting.lbsweb.consulta.persistence.ConsultaDAO;
import com.stconsulting.lbsweb.consulta.persistence.ConsultaTareaDAO;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

/**
 * 
 * @author STconsulting
 */
public class ConsultaTareaService extends GenericService{
	protected static Logger log=null;

	/** Creates a new instance of ConsultaService */
	public ConsultaTareaService(){
		log=Logger.getLogger(this.getClass());
	}

	/**
	 * Obtiene la Lista de Tareas de acuerdo al filtro seleccionado
	 * 
	 * @param filtroTarea
	 * @throws ServiceException
	 * @return
	 */
	public Resultado getListaTareas(Tarea filtroTarea) throws ServiceException{
		Resultado result=new Resultado();
		List<Tarea> listaTareas=null;
		TransactionContext tx=null;

		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO consultaTareaDAO=new ConsultaTareaDAO(tx);
			listaTareas=consultaTareaDAO.listTareas2(filtroTarea);
			result.setCodigo(Constants.OK);
			result.setResult(listaTareas);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			log.error(e.toString());
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}
	
	public Resultado getListaTareasBatch(Tarea filtroTarea) throws ServiceException{
		Resultado result=new Resultado();
		List<Tarea> listaTareas=null;
		TransactionContext tx=null;

		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO consultaTareaDAO=new ConsultaTareaDAO(tx);
			listaTareas=consultaTareaDAO.listTareas(filtroTarea);
			result.setCodigo(Constants.OK);
			result.setResult(listaTareas);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			log.error(e.toString());
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}

	public Resultado getListaTareasCancelar(Tarea filtroTarea) throws ServiceException{
		Resultado result=new Resultado();
		List<Tarea> listaTareas=null;
		TransactionContext tx=null;

		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO consultaTareaDAO=new ConsultaTareaDAO(tx);
			listaTareas=consultaTareaDAO.listTareasCancelar(filtroTarea);
			result.setCodigo(Constants.OK);
			result.setResult(listaTareas);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			log.error(e.toString());
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}

	public Resultado isValidTask(Tarea tarea) throws ServiceException{
		boolean isValid=false;
		Resultado result=new Resultado();
		TransactionContext tx=null;
		String listaMobilesNoHabiles="";
		List<Mobile> listaMobiles=tarea.getListaMobiles();
		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO consultaTareaDAO=new ConsultaTareaDAO(tx);
			for(int i=0;i < listaMobiles.size();i++){
				Mobile mobile=listaMobiles.get(i);
				isValid=consultaTareaDAO.isValidTask(mobile.getNumero(),tarea.getFechaInicio(),tarea.getFechaFin(),tarea.getHoraInicio(),tarea.getHoraFin());
				if(!isValid){
					listaMobilesNoHabiles+=mobile.getNumero() + "-";
				}
			}

			if(listaMobilesNoHabiles.length() > 0 && listaMobilesNoHabiles.substring(listaMobilesNoHabiles.length() - 1).equals("-")){
				listaMobilesNoHabiles=listaMobilesNoHabiles.substring(0,listaMobilesNoHabiles.length() - 1);
			}
			if(listaMobilesNoHabiles.equals("")){
				result.setCodigo(Constants.COD_TAREA_VALIDA);
			}
			else{
				result.setCodigo(Constants.COD_TAREA_INVALIDA);
				result.setMensaje("Numero(s): " + listaMobilesNoHabiles + " no validos porque ya se encuentra en otra tarea");
			}
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error(e.toString());
			result.setMensaje(Constants.NO_OK);
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}

	public Resultado insertar(Tarea tarea) throws ServiceException{
		Resultado result=new Resultado();
		TransactionContext tx=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO consultaTareaDAO=new ConsultaTareaDAO(tx);
			//consultaTareaDAO.insert(tarea);
			List<Mobile> listaMobiles=tarea.getListaMobiles();
			if(listaMobiles != null && listaMobiles.size() > 0){
				for(Mobile mobile : listaMobiles){
					consultaTareaDAO.insert(tarea, mobile.getNumero());
				}
			}
			result.setCodigo(Constants.OK);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			result.setMensaje(e.toString());
			log.error(e.toString());
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}

	public Resultado actualizar(Tarea tarea) throws ServiceException{
		Resultado result=new Resultado();
		TransactionContext tx=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO consultaTareaDAO=new ConsultaTareaDAO(tx);
			consultaTareaDAO.update(tarea);
			result.setCodigo(Constants.OK);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			result.setMensaje(e.toString());
			log.error(e.toString());
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}

	// validacion de imput
	public Resultado isDiaPeriodoCorrecto(Tarea tarea){
		Resultado result=new Resultado();
		try{
			//String listaDias=tarea.getDiaPeriodo();
			String listaDias=tarea.getIntervaloDias();
			Hashtable<String,String> hashDias=new Hashtable<String,String>();
			Date fechaInicial=tarea.getFechaInicio();
			Date fechaFinal=tarea.getFechaFin();
			int dias=Helper.getDiferenciaDias(fechaInicial,fechaFinal);
			Calendar fechaInicio=new GregorianCalendar();
			Calendar fechaCurrent=new GregorianCalendar();
			String strdia="";
			log.debug("isvalid1");
			for(int i=0;i < dias;i++){
				fechaInicio.setTime(fechaInicial);
				fechaCurrent=fechaInicio;
				fechaCurrent.add(Calendar.DATE,i);
				strdia=Integer.toString(fechaCurrent.get(Calendar.DAY_OF_WEEK));
				hashDias.put(strdia,strdia);
			}
			String listaDiasNoPermitidos="";
			String diaComparar="";
			for(int j=0;j < listaDias.length();j++){
				diaComparar=listaDias.substring(j,j + 1);
				if(!hashDias.containsKey(diaComparar)){
					listaDiasNoPermitidos+=Helper.getDescDias(Integer.parseInt(diaComparar)) + " ";
				}

			}
			log.debug("Dias No Permitidos: " + listaDiasNoPermitidos);
			if(listaDiasNoPermitidos.equals("")){
				result.setCodigo(Constants.COD_DIAS_CORRECTO);
			}
			else{
				result.setCodigo(Constants.COD_DIAS_INCORRECTO);
			}
			result.setMensaje("Dias no validos en el rango de fechas ingresado :" + listaDiasNoPermitidos);

		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			log.error(e.toString());

		}
		return result;

	}

	// Valida Numeros
	public Resultado validarNumero(Usuario usuario,List<Mobile> listaNumeros) throws ServiceException{
		Resultado result=new Resultado();
		TransactionContext tx=null;
		String codRes="";
		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaService consultaService=new ConsultaService();
			ConsultaDAO consultaDAO=new ConsultaDAO(tx);
			codRes=consultaService.validaNumeros(usuario,listaNumeros,consultaDAO);
			log.debug("Service VALIDAR NUMERO:" + codRes);
			if(codRes.equals(Constants.COD_NUMERO_VALIDO)){
				result.setCodigo(Constants.OK);
				log.debug("Entrando cuando es Valido");
			}
			else{
				log.debug("Entrando cuando no es Numero Valido");
				result.setCodigo(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO);
				int ind=Integer.parseInt(codRes);
				result.setMensaje("El sgte. n�mero no es v�lido : " + listaNumeros.get(ind).getNumero());
			}
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			result.setMensaje(e.toString());
			log.error(e.toString());
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return result;
	}

	public Resultado calucarCantidadConsultas(Tarea tarea){
		int horas=0,diasHabiles=0,cantDias=0;
		long cantconsultas=0;
		Resultado result=new Resultado();
		String fechaHoy=Helper.formateaFecha(new Date(),ConstantsMQT2.FORMATO_FECHA_MOSTRAR);
		try{
			Hashtable<String,String> hashDias=new Hashtable<String,String>();
			//String fechaIncio=tarea.getFechaInicio();
			//String fechaFin=tarea.getFechaFin();
			String codHorario=tarea.getCodHorario();
			String codPeriodo=tarea.getCodPeriodo();
			String listaDias=tarea.getIntervaloDias();
			long minHoy=0;
			// Seria 8hoas*60min*diashabiles/10
			// diasHabiles=RecorrerDias y verificar en la listadedias se existe
			// y aumentar contador
			if(Constants.COD_HORARIO_DIA_COMPLETO.equals(codHorario)){
				horas=24;
			}
			else{
				horas=tarea.getHoraFin() - tarea.getHoraInicio();
			}
			Date fechaInicial=tarea.getFechaInicio();
			Date fechaFinal=tarea.getFechaFin();
			int dias=Helper.getDiferenciaDias(fechaInicial,fechaFinal);
			if(Constants.COD_PERIODO_SEMANAL.equals(codPeriodo)){
				Calendar fechaInicio=new GregorianCalendar();
				Calendar fechaCurrent=new GregorianCalendar();
				String strdia="";
				for(int i=0;i < dias;i++){
					fechaInicio.setTime(fechaInicial);
					fechaCurrent=fechaInicio;
					fechaCurrent.add(Calendar.DATE,i);
					strdia=Integer.toString(fechaCurrent.get(Calendar.DAY_OF_WEEK));
					hashDias.put(strdia,strdia);
				}

				String diaComparar="";
				for(int j=0;j < listaDias.length();j++){
					diaComparar=listaDias.substring(j,j + 1);
					if(hashDias.containsKey(diaComparar)){
						cantDias++;
					}
				}
				diasHabiles=cantDias;
			}
			else{
				diasHabiles=dias;
			}

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String fechaTareaInicio = sdf.format(tarea.getFechaInicio());

            if (fechaHoy.equals(fechaTareaInicio)) {
				minHoy=Helper.getRestMinutesCurrent(new java.util.Date(),tarea);
				cantconsultas=(minHoy + (horas * 60 * (diasHabiles - 1))) / tarea.getIntervalo();
			}
			else{
				cantconsultas=(horas * 60 * (diasHabiles)) / tarea.getIntervalo();
			}
			cantconsultas=cantconsultas * tarea.getListaMobiles().size();
			result.setCodigo(Constants.OK);
			result.setMensaje("Cantidad de Consultas :" + Long.toString(cantconsultas));

		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			log.error(e);

		}
		return result;
	}

	// Batchero Task Manager 08/06/2005
	/**
	 * 
	 * @param tarea
	 * @return
	 */
	//XXX Modificar
	public boolean IsValidaTaskExecute(Tarea tarea,Calendar calHoy){
		boolean isValid=false;
		try{

			log.debug("Verificando si se puede ejecutar la Tarea " + tarea.getDescripcion());
			Date fechaInicio=tarea.getFechaInicio();
			Date fechaFin=tarea.getFechaFin();
			Date fechaHoy=Converter.stringToDate(Helper.formateaFecha(new Date(),ConstantsMQT2.FORMATO_FECHA_MOSTRAR));

			boolean isValidRangoFechas=(fechaHoy.compareTo(fechaInicio) > 0 && fechaHoy.compareTo(fechaFin) < 0) || (fechaHoy.compareTo(fechaInicio) == 0 || fechaHoy.compareTo(fechaFin) == 0) ? true : false;
			String listaDias=tarea.getIntervaloDias();

			int horaCurrent=calHoy.get(Calendar.HOUR_OF_DAY);
			int diaCurrent=calHoy.get(Calendar.DAY_OF_WEEK);

			int minCurrent=calHoy.get(Calendar.MINUTE);
			log.debug("Hora Actual " + horaCurrent);
			log.debug("Min Actual " + minCurrent);

			// Verificando si se encuentra dentro del rango de fechas ingresado
			if(tarea.getIntervaloDias().equals(Constants.COD_PERIODO_DIARIO)){
				log.debug(" IsValidRangoFechas: " + isValidRangoFechas);
				if(isValidRangoFechas){
					log.debug("Rango de Fechas Correctos para la Tarea :" + tarea.getIdTarea());
					isValid=true;
				}
				else{
					log.debug("Rango de Fechas INCorrectos para la Tarea :" + tarea.getIdTarea());
					isValid=false;
				}

			}
			//else if(Constants.COD_PERIODO_SEMANAL.equals(tarea.getCodPeriodo())){
			else {
				//TODO Renzo, consultar como se va a manejar el tema de periodos semanales con dias.
				if(fechaHoy.getTime() >= fechaInicio.getTime() && fechaHoy.getTime() <= fechaFin.getTime() && Helper.isContenidoDia(listaDias,diaCurrent)){
					isValid=true;
				}
				else{
					isValid=false;
				}
			}
			// Verificando si se encuentra dentro de las horas de fecha
			// Ingresadas
			if(isValid){
				// modificacion - se debe verificar si el intervalo es valido
				
				log.debug("tarea intervalo de dias: " + tarea.getIntervaloDias());
				log.debug("tarea cod horario: " + tarea.getCodHorario());
				log.debug("tarea hora inicio: " + tarea.getHoraInicio());
				log.debug("tarea hora fin: " + tarea.getHoraFin());
				
				//tarea.setCodHorario(Helper.getRangoHora(tarea.getHoraInicio(), tarea.getHoraFin()).getCodHorario());
				if(Constants.COD_HORARIO_DIA_COMPLETO.equals(tarea.getCodHorario())){
					log.debug("Tarea COD_HORARIO_DIA_COMPLETO");

					Calendar calInicioDia=new GregorianCalendar();
					calInicioDia.setTime(new Date());
					calInicioDia.set(Calendar.HOUR_OF_DAY,tarea.getHoraInicio());
					calInicioDia.set(Calendar.MINUTE,0);

					int diferenciaMinutos=Helper.getMinutesDifference(calInicioDia,calHoy);
					if(diferenciaMinutos == 0 || (diferenciaMinutos % tarea.getIntervalo() == 0)){
						log.debug("cumple condicion");
						isValid=true;
					}
					else{
						isValid=false;
					}
					log.debug("calInicioDia : " + calInicioDia.toString());
					log.debug("calHoy : " + calHoy);
					log.debug("diferenciaMinutos : " + diferenciaMinutos);
					log.debug("div : " + diferenciaMinutos % tarea.getIntervalo());
					log.debug("isValid : " + isValid);
				}
				else{
					log.debug("Tarea " + tarea.getHoraInicio() + "-" + tarea.getHoraFin());
					if(horaCurrent >= tarea.getHoraInicio() && horaCurrent <= tarea.getHoraFin()){
						Calendar calInicioProceso=new GregorianCalendar(calHoy.get(Calendar.YEAR),calHoy.get(Calendar.MONTH),calHoy.get(Calendar.DATE),tarea.getHoraInicio(),0);
						int diferenciaMinutos=Helper.getMinutesDifference(calInicioProceso,calHoy);
						if(diferenciaMinutos == 0 || (diferenciaMinutos % tarea.getIntervalo() == 0)){
							log.debug("cumple condicion");
							isValid=true;
						}
						else{
							isValid=false;
						}
						log.debug("calInicioProceso : " + calInicioProceso.toString());
						log.debug("calHoy : " + calHoy);
						log.debug("diferenciaMinutos : " + diferenciaMinutos);
						log.debug("div : " + diferenciaMinutos / tarea.getIntervalo());
						log.debug("isValid : " + isValid);
					}
					else
						isValid=false;
				}

			}

		}
		catch(Exception e){
			log.error(e);
			log.debug(e.toString());

		}
		log.debug("IsValid Tarea " + tarea.getDescripcion() + " Ejecutar: " + isValid + " ---ID TAREA:" + tarea.getIdTarea());
		return isValid;
	}

	public Resultado isValidHour(Tarea tarea){
		Resultado result=new Resultado();
		try{
			Calendar fechaHoy=new GregorianCalendar();
			fechaHoy.setTime(new java.util.Date());
			int horaCurrent=fechaHoy.get(Calendar.HOUR_OF_DAY);
			if(Constants.COD_HORARIO_DIA_COMPLETO.equals(tarea.getCodHorario())){
				result.setCodigo(Constants.OK);
			}
			else{
				if(horaCurrent <= tarea.getHoraInicio() || horaCurrent < tarea.getHoraFin()){
					result.setCodigo(Constants.OK);
				}
				else{
					result.setCodigo(Constants.COD_HORA_INVALIDA);
					result.setMensaje(Constants.MENSAJE_HORA_NUEVA_TAREA);
				}
			}

		}
		catch(Exception e){
			result.setCodigo(Constants.NO_OK);
			result.setMensaje(e.toString());
			log.error("Exception isValidHour :" + e);
		}
		return result;
	}

}
