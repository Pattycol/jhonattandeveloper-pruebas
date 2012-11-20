package com.btg.claro.LBS.interfaz.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.btg.claro.LBS.core.LBSService;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Consulta;
import com.btg.claro.LBS.sms.EnvioSMS;
import com.btg.claro.LBS.util.ApplicationContextUtil;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.util.Ejecucion;
import com.btg.claro.LBS.util.Util;

public class InterfazSMS extends Thread{
	
	private static final Logger log=LoggerFactory.getLogger(InterfazSMS.class);

	private String numeroOrigen;
	
	private String numeroDestino;
	
	private Date fecha;
	
	private LBSService lbsService;
	
	private EnvioSMS envioSMS;

	public InterfazSMS(String numeroOrigen,String numeroDestino,Date fecha){
		super();
		this.numeroOrigen=numeroOrigen;
		this.numeroDestino=numeroDestino;
		this.fecha=fecha;
		ApplicationContext ctx=ApplicationContextUtil.getContext();
		lbsService=ctx.getBean(LBSService.class);
		envioSMS=ctx.getBean(EnvioSMS.class);
	}

	/**
	 * Metodo que inicia la ejecucion del hilo. Verifica que se hayan ingresado correctamente
	 * los parámetros y comienza la ejecucion de la consulta.
	 */
	public void run(){
		Ejecucion.agregarHiloSMS(this);
		if(!Util.vacio(numeroOrigen)){
			if(!Util.vacio(numeroDestino)){
				if(fecha!=null){
					log.info("Se inicia la consulta del número "+numeroDestino);
					long inicio=System.currentTimeMillis();
					boolean cobro=Config.getPropiedadBoolean("dev.cobro");
					Consulta consulta=lbsService.realizarConsulta(numeroOrigen,numeroDestino,fecha,cobro,Constantes.CONSULTA_SMS);
					String mensaje="Usted no puede usar el servicio LBS.";
					if(consulta!=null){
						try{
							if(consulta.getCodigoError()==Constantes.ERROR_CELDA_INEXISTENTE){
								Celda celda=consulta.getCelda();
								if(celda==null)
									consulta.setCelda(lbsService.guardarCelda(consulta.getIdCelda()));
							}
							lbsService.guardarConsulta(consulta);
							mensaje=consulta.getResultado();
						}
						catch(Exception e){
							log.error("Error al procesar la consulta ("+e.getMessage()+")",e);
							mensaje="Por favor volver a intentar localizar en breves minutos.";
						}
					}
					envioSMS.enviarSMS(Config.getPropiedad("lbs.numero"),numeroOrigen,mensaje);
					long fin=System.currentTimeMillis();
					log.info("Finalizada la consulta del numero "+numeroDestino);
					log.debug("La operación demoró "+(fin-inicio)+" milisegundos");
				}
				else{
					log.error("Se debe ingresar una fecha");
				}
			}
			else{
				log.error("Se debe ingresar el numero a consultar");
			}
		}
		else{
			log.error("No se ha ingresado el numero de origen");
		}
		Ejecucion.removerHiloSMS(this);
		return;
	}

}
