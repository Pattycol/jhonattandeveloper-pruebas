package com.btg.claro.LBS.sms.impl;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smpp.NotSynchronousException;
import org.smpp.ServerPDUEvent;
import org.smpp.TimeoutException;
import org.smpp.WrongSessionStateException;
import org.smpp.pdu.EnquireLink;
import org.smpp.pdu.PDU;
import org.smpp.pdu.PDUException;
import org.smpp.pdu.Request;
import org.smpp.pdu.Response;
import org.smpp.pdu.UnknownCommandIdException;
import org.smpp.pdu.ValueNotSetException;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.interfaz.impl.InterfazSMS;
import com.btg.claro.LBS.sms.ClienteSMSC;
import com.btg.claro.LBS.sms.SMPPTestPDUEventListener;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.Contador;
import com.btg.claro.LBS.util.SesionSMSC;

@Service("ClienteSMSC")
public class SMSCQuery implements ClienteSMSC{

	private static Logger log=LoggerFactory.getLogger(SMSCQuery.class);

	private long receiveTimeout=1000;

	private boolean asynchronous;

	public SMSCQuery(){
		asynchronous=!Config.getPropiedadBoolean("smsc.synchronized");
	}

	private static Datos getDataFromMsg(String cadena){
		if(cadena.contains("deliver")){
			Datos datos=new Datos();
			String patronNumeroOrigenDestino="addr:\\s[0-9]{1,3}\\s[0-9]{1,3}\\s[[^0-9]0-9]{3,13}";
			String numero="[0-9]{1,13}";
			String patronMensaje="sm:\\smsg:\\s(.|\n){1,254}\\(opt:";
			Pattern p=Pattern.compile(patronNumeroOrigenDestino);
			Matcher m=p.matcher(cadena);
			m.find();
			String cadenaNumeroOrigen=m.group();
			Pattern patronNumero=Pattern.compile(numero);
			Matcher matchNumero=patronNumero.matcher(cadenaNumeroOrigen);
			matchNumero.find();
			matchNumero.find();
			matchNumero.find();
			String num=matchNumero.group();

			if(num.startsWith("51"))
				num=num.substring(2);

			datos.setNumeroOrigen(num);
			log.debug("Numero Origen: " + datos.getNumeroOrigen());
			m.find();
			String numeroConsultado=m.group();
			matchNumero=patronNumero.matcher(numeroConsultado);
			matchNumero.find();
			matchNumero.find();
			matchNumero.find();
			datos.setNumeroConsultado(matchNumero.group());
			log.debug("Numero Consultado: " + datos.getNumeroConsultado());
			Pattern p2=Pattern.compile(patronMensaje);
			Matcher m2=p2.matcher(cadena);
			try{
				m2.find();
				String totalCadenaMsg=m2.group();
				String destino=totalCadenaMsg.substring(9,totalCadenaMsg.length() - 8);

				if(destino.startsWith("+51")&&destino.length()==12){
					destino=destino.substring((destino.length() - 9),destino.length());
				}
				else if(destino.startsWith("51")&&destino.length()==11){
					destino=destino.substring((destino.length() - 9),destino.length());
				}
				else if(destino.startsWith("0051")&&destino.length()==13){
					destino=destino.substring((destino.length() - 9),destino.length());
				}
				else if(destino.length() == 9){
					destino=destino.substring((destino.length() - 9),destino.length());
				}
				
				datos.setNumeroDestino(destino);
				log.debug("Numero Destino: " + datos.getNumeroDestino());

			}
			catch(IllegalStateException e){
				datos.setNumeroDestino(null);
			}
			return datos;
		}
		return null;
	}

	public void iniciar(){
		int maximoIdle=Config.getPropiedadInt("lbs.maxIdle");
		Contador contador=new Contador();
		contador.start();
		log.info("Escuchando peticiones de SMS");
		while(true){
			PDU pdu=null;
			if(asynchronous){
				ServerPDUEvent pduEvent=((SMPPTestPDUEventListener) SesionSMSC.getListener()).getRequestEvent(receiveTimeout);
				if(pduEvent != null){
					pdu=pduEvent.getPDU();
				}
			}
			else{
				try{
					pdu=SesionSMSC.receive();
				}
				catch(UnknownCommandIdException e){
					log.error("Error al recibir el SMS",e);
				}
				catch(TimeoutException e){
					log.error("Error al recibir el SMS",e);
				}
				catch(NotSynchronousException e){
					log.error("Error al recibir el SMS",e);
				}
				catch(PDUException e){
					log.error("Error al recibir el SMS",e);
				}
				catch(IOException e){
					log.error("Error al recibir el SMS",e);
				}
			}
			if(pdu != null){
				contador.setTiempoTranscurrido(0);
				log.debug("Received PDU " + pdu.debugString());
				Datos datos=getDataFromMsg(pdu.debugString());
				if(datos != null && datos.getNumeroConsultado().equals(Config.getPropiedad("lbs.numero"))){
					InterfazSMS interfaz=new InterfazSMS(datos.getNumeroOrigen(),datos.getNumeroDestino(),new Date());
					interfaz.start();
				}
				if(pdu.isRequest()){
					Response response=((Request) pdu).getResponse();
					// respond with default response
					log.debug("Going to send default response to request " + response.debugString());
					try{
						SesionSMSC.respond(response);
					}
					catch(ValueNotSetException e){
						log.warn("",e);
					}
					catch(WrongSessionStateException e){
						log.warn("",e);
					}
					catch(IOException e){
						log.warn("",e);
					}
				}
			}
			// si el tiempo de espera supera el límite permitido, mandamos un
			// enquire link
			if(contador.getTiempoTranscurrido() >= maximoIdle){
				log.debug("Enviando enquire link");
				EnquireLink request=new EnquireLink();
				try{
					SesionSMSC.enquireLink(request);
				}
				catch(ValueNotSetException e){
					log.error("Error enviando Enquire Link",e);
				}
				catch(TimeoutException e){
					log.error("Error enviando Enquire Link",e);
				}
				catch(PDUException e){
					log.error("Error enviando Enquire Link",e);
				}
				catch(WrongSessionStateException e){
					log.error("Error enviando Enquire Link",e);
				}
				catch(IOException e){
					log.error("Error enviando Enquire Link",e);
				}
				contador.setTiempoTranscurrido(0);
			}
		}
	}
	/*
	 * public static void main(String args[]){ String cadena=
	 * "(deliver: (pdu: 105 5 0 1) (addr: 1 1 51958108562)  (addr: 4 9 455)  (sm: msg: 993500464)  (opt: ) (extraopt: (oct: (tlv: 14) 01)  (oct: (tlv: 6) 01)  (oct: (tlv: 1062) 01)  ) ) "
	 * ; Datos datos=getDataFromMsg(cadena);
	 * System.out.println("------------------------------"); cadena=
	 * "(deliver: (pdu: 105 5 0 1) (addr: 1 1 51958108562)  (addr: 4 9 455)  (sm: msg: +51993500464)  (opt: ) (extraopt: (oct: (tlv: 14) 01)  (oct: (tlv: 6) 01)  (oct: (tlv: 1062) 01)  ) ) "
	 * ; datos=getDataFromMsg(cadena); System.out.println("datos:"+datos);
	 * System.out.println("------------------------------"); cadena=
	 * "(deliver: (pdu: 105 5 0 1) (addr: 1 1 +51958108562)  (addr: 4 9 455)  (sm: msg: 0051993500464)  (opt: ) (extraopt: (oct: (tlv: 14) 01)  (oct: (tlv: 6) 01)  (oct: (tlv: 1062) 01)  ) ) "
	 * ; datos=getDataFromMsg(cadena); System.out.println("datos:"+datos);
	 * System.out.println("------------------------------"); cadena=
	 * "(deliver: (pdu: 105 5 0 1) (addr: 1 1 958108562)  (addr: 4 9 455)  (sm: msg: 993500464)  (opt: ) (extraopt: (oct: (tlv: 14) 01)  (oct: (tlv: 6) 01)  (oct: (tlv: 1062) 01)  ) ) "
	 * ; datos=getDataFromMsg(cadena); System.out.println("datos:"+datos);
	 * System.out.println("------------------------------"); }
	 */
}

class Datos{

	private String numeroOrigen;

	private String numeroDestino;

	private String numeroConsultado;

	public String getNumeroOrigen(){
		return numeroOrigen;
	}

	public void setNumeroOrigen(String numerOrigen){
		this.numeroOrigen=numerOrigen;
	}

	public String getNumeroDestino(){
		return numeroDestino;
	}

	public void setNumeroDestino(String numeroDestino){
		this.numeroDestino=numeroDestino;
	}

	public String getNumeroConsultado(){
		return numeroConsultado;
	}

	public void setNumeroConsultado(String numeroConsultado){
		this.numeroConsultado=numeroConsultado;
	}
}
