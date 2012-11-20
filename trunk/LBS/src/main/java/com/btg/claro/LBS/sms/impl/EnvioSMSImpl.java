package com.btg.claro.LBS.sms.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smpp.TimeoutException;
import org.smpp.WrongSessionStateException;
import org.smpp.pdu.Address;
import org.smpp.pdu.PDUException;
import org.smpp.pdu.SubmitSM;
import org.smpp.pdu.ValueNotSetException;
import org.smpp.pdu.WrongDateFormatException;
import org.smpp.pdu.WrongLengthOfStringException;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.sms.EnvioSMS;
import com.btg.claro.LBS.util.SesionSMSC;

@Service("EnvioSMS")
public class EnvioSMSImpl implements EnvioSMS{

	private static final Logger log=LoggerFactory.getLogger(EnvioSMSImpl.class);

	public boolean enviarSMS(String numeroOrigen,String numeroDestino,String mensaje){
		return enviarMensaje(numeroOrigen,numeroDestino,mensaje,false);
	}

	public boolean enviarSMSOculto(String numeroOrigen,String numeroDestino){
		return enviarMensaje(numeroOrigen,numeroDestino,"",true);
	}

	private boolean enviarMensaje(String numeroOrigen,String numeroDestino,String mensaje,boolean oculto){
		//if(!numeroDestino.startsWith("51"))
			numeroDestino="51" + numeroDestino;
		SubmitSM request=new SubmitSM();

		try{
			request.setServiceType("");
			Address sourceAddress=new Address((byte) 1,(byte) 1,numeroOrigen);
			Address destinationAddress=new Address((byte) 1,(byte) 1,numeroDestino);
			request.setSourceAddr(sourceAddress);
			request.setDestAddr(destinationAddress);
			request.setReplaceIfPresentFlag((byte) 0);
			request.setShortMessage(mensaje);
			request.setScheduleDeliveryTime("");
			request.setValidityPeriod("");
			request.setEsmClass((byte) 0);
			if(oculto)
				request.setProtocolId((byte) 64);
			else
				request.setProtocolId((byte) 0);
			request.setPriorityFlag((byte) 0);
			request.setRegisteredDelivery((byte) 0);
			request.setDataCoding((byte) 0);
			request.setSmDefaultMsgId((byte) 0);
			request.assignSequenceNumber(true);
			log.info("Submit request: " + request.debugString());
		}
		catch(WrongLengthOfStringException e){
			log.error("Error creando el mensaje",e);
			return false;
		}
		catch(WrongDateFormatException e){
			log.error("Error creando el mensaje",e);
			return false;
		}
		try{
			/* SubmitSMResp response= */SesionSMSC.submit(request);
			// if(response!=null)
			return true;
			// return false;
		}
		catch(ValueNotSetException e){
			log.error("Error enviando el mensaje",e);
			return false;
		}
		catch(TimeoutException e){
			log.error("Error enviando el mensaje",e);
			return false;
		}
		catch(PDUException e){
			log.error("Error enviando el mensaje",e);
			return false;
		}
		catch(WrongSessionStateException e){
			log.error("Error enviando el mensaje",e);
			return false;
		}
		catch(IOException e){
			log.error("Error enviando el mensaje",e);
			return false;
		}
	}

}
