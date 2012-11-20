package com.btg.claro.LBS.vlr.impl;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.dao.HLRDAO;
import com.btg.claro.LBS.dao.HLRUsuarioClaroPortadoDAO;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.util.Ejecucion;
import com.btg.claro.LBS.vlr.ClienteVLR;
import com.btg.claro.LBS.vlr.DatosMovil;
import com.jscape.inet.telnet.TelnetException;
import com.jscape.inet.telnet.TelnetSession;

@Service("ClienteVLR")
public class ClienteVLRImpl implements ClienteVLR{
	
	private static final Logger log=LoggerFactory.getLogger(ClienteVLRImpl.class);
	
	@Autowired
	private HLRDAO hlrDAO;
	
	@Autowired
	private HLRUsuarioClaroPortadoDAO hlrUsuarioClaroPortadoDAO;

	/**
	 * Método que obtiene el número de VLR al que está asignado un
	 * número telefónico. Se consulta al HLR seleccionado con la
	 * finalidad de encontrar el VLR donde se encuentra el número
	 * ingresado.
	 * 
	 * @param hlr el HLR a consultar
	 * @param numero el número a buscar
	 * @return el número de VLR donde se encuentra el número
	 */
	private int obtenerVLR(int hlr,String numero){
		String ipHLR=Config.getPropiedad("hlr"+hlr+".servidor");
		int puertoHLR=Config.getPropiedadInt("hlr"+hlr+".puerto");
		String usuarioHLR=Config.getPropiedad("hlr"+hlr+".usuario");
		String claveHLR=Config.getPropiedad("hlr"+hlr+".clave");
		
		int maxConsultas=Config.getPropiedadInt("hlr"+hlr+".maxConsultas");
		log.debug("Consultas simultaneas al HLR"+hlr+": "+Ejecucion.getConsultasHLR(hlr-1));
		while(Ejecucion.getConsultasHLR(hlr-1)>=maxConsultas){
			log.debug("Durmiendo la conexion al HLR"+hlr+" para el numero "+numero);
			try{
				Thread.sleep(400);
			}
			catch(InterruptedException e){
				log.error(e.getMessage(),e);
			}
		}
		
		log.debug("HLR"+hlr+" (ip: "+ipHLR+",puerto: "+puertoHLR+",usuario: "+usuarioHLR+",clave: "+claveHLR+")");
		TelnetSession sesion=new TelnetSession(ipHLR,puertoHLR);
		sesion.setShellPrompt("<");
		sesion.setLoginPrompt("ENTER USERNAME < ");
		sesion.setPasswordPrompt("ENTER PASSWORD < ");
		String vlrId=null;
		int intentos=Config.getPropiedadInt("hlr"+hlr+".intentos");
		int r=0;
		boolean fallo;
		Ejecucion.agregarConsultasHLR(hlr-1);
		do{
			fallo=false;
			try{				
				//iniciamos sesion
				sesion.connect(usuarioHLR+"\r",claveHLR+"\r",2000);
				//ejecutamos la consulta
				vlrId=procesarRespuestaHLR(sesion.send("ZMIO:MSISDN=51"+numero+";\r"));		
				log.debug("Identificador del VLR "+vlrId+" para el número "+numero);
				//forzamos el cierre de la sesión
				sesion.send("Z;\r");
				try{
					sesion.send("Z;\r",10);
				}
				catch(TelnetException e){
					//ignoramos, en realidad lo que queremos es que salte la excepción
				}
				sesion.disconnect();
			}
			catch(TelnetException e){
				log.warn("Error al acceder al HLR"+hlr,e);
				fallo=true;
			}
			r++;
		}while(fallo && r<intentos);
		Ejecucion.quitarConsultasHLR(hlr-1);
		int numeroVlrs=Config.getPropiedadInt("numero.vlrs");
		for(int i=1;i<=numeroVlrs;i++){
			if(Config.getPropiedad("vlr"+i+".identificador").equals(vlrId)){
				return i;
			}
		}
		return 0;
	}

	/**
	 * Obtiene el identificador del VLR a partir de la respuesta del HLR
	 * 
	 * @param respuesta
	 * @return el identificador del VLR
	 */
	private String procesarRespuestaHLR(String respuesta){
		log.debug("Respuesta del HLR "+respuesta);
		int inicio=respuesta.indexOf("VLR-ADDRESS");
		if(inicio>-1){
			inicio+=45;
			return respuesta.substring(inicio,inicio+11);
		}
		return null;
	}

	/**
	 * Método que consulta al VLR indicado por el número buscado, devolviendo
	 * la información de la ubicación del número consultado.
	 * 
	 * @param vlr el VLR a consultar
	 * @param numero el número a buscar
	 * @return información sobre la ubicación del número o <code>null</code> si no se puede 
	 * ubicar al número
	 */
	private DatosMovil obtenerCelda(int vlr,String numero){
		String ipVLR=Config.getPropiedad("vlr"+vlr+".servidor");
		int puertoVLR=Config.getPropiedadInt("vlr"+vlr+".puerto");
		String usuarioVLR=Config.getPropiedad("vlr"+vlr+".usuario");
		String claveVLR=Config.getPropiedad("vlr"+vlr+".clave");
		
		int maxConsultas=Config.getPropiedadInt("vlr"+vlr+".maxConsultas");
		log.debug("Consultas simultaneas al VLR"+vlr+": "+Ejecucion.getConsultasVLR(vlr-1));
		while(Ejecucion.getConsultasVLR(vlr-1)>=maxConsultas){
			log.debug("Durmiendo la conexion al VLR"+vlr+" para el numero "+numero);
			try{
				Thread.sleep(400);
			}
			catch(InterruptedException e){
				log.error(e.getMessage(),e);
			}
		}
		
		log.debug("VLR"+vlr+" (ip: "+ipVLR+",puerto: "+puertoVLR+",usuario: "+usuarioVLR+",clave: "+claveVLR+")");
		TelnetSession sesion=new TelnetSession(ipVLR,puertoVLR);
		sesion.setShellPrompt("<");
		sesion.setLoginPrompt("ENTER USERNAME < ");
		sesion.setPasswordPrompt("ENTER PASSWORD < ");
		DatosMovil datos=null;
		int intentos=Config.getPropiedadInt("vlr"+vlr+".intentos");
		int r=0;
		boolean fallo;
		Ejecucion.agregarConsultasVLR(vlr-1);
		do{
			fallo=false;
			try{
				//iniciamos sesion
				sesion.connect(usuarioVLR+"\r",claveVLR+"\r",2000);
				//ejecutamos la consulta
				datos=procesarRespuestaVLR(sesion.send("ZMVO:MSISDN=51"+numero+";\r"));
				//forzamos el cierre de la sesión
				sesion.send("Z;\r");
				try{
					sesion.send("Z;\r",10);
				}
				catch(TelnetException e){
					//ignoramos, en realidad lo que queremos es que salte la excepción
				}
				sesion.disconnect();
			}
			catch(TelnetException e){
				log.warn("Error al acceder al VLR"+vlr,e);
				fallo=true;
			}
			r++;
		}while(fallo && r<intentos);
		Ejecucion.quitarConsultasVLR(vlr-1);
		return datos;
	}

	/**
	 * Obtiene la celda, el mnr, imsid y la fecha resultante de la localización de
	 * un movil
	 * 
	 * @param respuesta
	 * @return
	 */
	private DatosMovil procesarRespuestaVLR(String respuesta){
		log.debug("Respuesta del VLR "+respuesta);		
		int inicio=respuesta.indexOf("LAST USED CELL ID");
		if(inicio>-1){
			DatosMovil datos=new DatosMovil();
			inicio+=54;
			String idCelda=respuesta.substring(inicio,inicio+5);
			log.debug("idCelda: "+idCelda);
			datos.setIdCelda(idCelda);
			inicio=respuesta.indexOf("IMSI DETACH FLAG")+48;
			String imsi=respuesta.substring(inicio,inicio+1);
			log.debug("imsi: "+imsi);
			datos.setImsid(imsi.equals("N"));
			inicio=respuesta.indexOf("MOBILE NOT REACHABLE FLAG")+48;
			String mnr=respuesta.substring(inicio,inicio+1);
			log.debug("mnr: "+mnr);
			datos.setMnr(mnr.equals("N"));
			inicio=respuesta.indexOf("LAST ACTIVATE DATE")+48;
			String fecha=Calendar.getInstance().get(Calendar.YEAR)+"-"+respuesta.substring(inicio,inicio+11);
			log.debug("fecha: "+fecha);
			Format f=new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try{
				datos.setFechaInterna((Date) f.parseObject(fecha));
			}
			catch(ParseException e){
				log.warn("No se pudo parsear la fecha "+fecha);
			}
			return datos;
		}
		return null;
	}

	public DatosMovil ubicarMovil(String numero){
		
		
		
		int hlr=hlrDAO.getHLRPorNumero(Integer.parseInt(numero));		
		if(hlr!=0){
			DatosMovil datos=new DatosMovil();
			datos.setHLR("HLR"+hlr);
			log.info("Numero "+numero+" pertenece a la Tabla BD Rangos de Numeracion");
			log.info("Número "+numero+" pertenece al HLR"+hlr);
			long inicio=System.currentTimeMillis();
			int vlr=obtenerVLR(hlr,numero);
			long fin=System.currentTimeMillis();
			log.debug("Demoramos "+(fin-inicio)+" milisegundos consultando el HLR"+hlr+" para el número "+numero);
			if(vlr!=0){
				datos.setVLR("VLR"+vlr);
				inicio=System.currentTimeMillis();
				log.info("Número "+numero+" pertenece al VLR"+vlr);
				DatosMovil resultado=obtenerCelda(vlr,numero);
				fin=System.currentTimeMillis();
				log.debug("Demoramos "+(fin-inicio)+" milisegundos consultando el VLR"+vlr+" para el número "+numero);
				if(resultado!=null){
					datos.setIdCelda(resultado.getIdCelda());
					datos.setMnr(resultado.isMnr());
					datos.setImsid(resultado.isImsid());
					datos.setError(Constantes.OK);
					datos.setFechaInterna(resultado.getFechaInterna());
				}
				else{
					datos.setError(Constantes.ERROR_VLR);
				}
			}
			else{
				log.info("Error en el HRL");
				datos.setError(Constantes.ERROR_MOVIL_APAGADO);
			}
			return datos;
		}else{
			
			int hlr2=hlrUsuarioClaroPortadoDAO.getHLRPorNumero(Integer.parseInt(numero));
			if(hlr2!=0){
				
				DatosMovil datos=new DatosMovil();
				datos.setHLR("HLR"+hlr2);
				log.info("Numero "+numero+" pertenece a la Tabla Claro Usuario Portados");
				log.info("Número "+numero+" pertenece al HLR2"+hlr2);
				long inicio=System.currentTimeMillis();
				int vlr=obtenerVLR(hlr2,numero);
				long fin=System.currentTimeMillis();
				log.debug("Demoramos "+(fin-inicio)+" milisegundos consultando el HLR2"+hlr2+" para el número "+numero);
				if(vlr!=0){
					datos.setVLR("VLR"+vlr);
					inicio=System.currentTimeMillis();
					log.info("Número "+numero+" pertenece al VLR"+vlr);
					DatosMovil resultado=obtenerCelda(vlr,numero);
					fin=System.currentTimeMillis();
					log.debug("Demoramos "+(fin-inicio)+" milisegundos consultando el VLR2"+vlr+" para el número "+numero);
					if(resultado!=null){
						datos.setIdCelda(resultado.getIdCelda());
						datos.setMnr(resultado.isMnr());
						datos.setImsid(resultado.isImsid());
						datos.setError(Constantes.OK);
						datos.setFechaInterna(resultado.getFechaInterna());
					}
					else{
						datos.setError(Constantes.ERROR_VLR);
					}
				}
				else{
					log.info("Error en el HRL");
					datos.setError(Constantes.ERROR_MOVIL_APAGADO);
				}
				return datos;
				
				
			}else{
				log.warn("El número "+numero+" no pertenece a un HLR conocido");
				return null;	
			}
			
		}
	}

}
