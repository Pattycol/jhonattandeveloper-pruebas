package com.btg.claro.LBS.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smpp.Data;
import org.smpp.NotSynchronousException;
import org.smpp.ServerPDUEventListener;
import org.smpp.Session;
import org.smpp.TCPIPConnection;
import org.smpp.TimeoutException;
import org.smpp.WrongSessionStateException;
import org.smpp.pdu.BindReceiver;
import org.smpp.pdu.BindRequest;
import org.smpp.pdu.BindResponse;
import org.smpp.pdu.BindTransciever;
import org.smpp.pdu.BindTransmitter;
import org.smpp.pdu.EnquireLink;
import org.smpp.pdu.PDU;
import org.smpp.pdu.PDUException;
import org.smpp.pdu.Response;
import org.smpp.pdu.SubmitSM;
import org.smpp.pdu.SubmitSMResp;
import org.smpp.pdu.UnknownCommandIdException;
import org.smpp.pdu.ValueNotSetException;
import org.smpp.pdu.WrongLengthOfStringException;

public class SesionSMSC{
	
	private static Logger log=LoggerFactory.getLogger(SesionSMSC.class);
	
	private static Session sesion;
	
	private static ServerPDUEventListener listener;
	
	public static void respond(Response response) throws ValueNotSetException, WrongSessionStateException, IOException{
		if(!isConected()){
			log.warn("Nos hemos desconectado del SMSC, reconectando...");
			if(!conectar(listener)){
				return;
			}
		}
		sesion.respond(response);
	}
	
	public static PDU receive() throws UnknownCommandIdException, TimeoutException, NotSynchronousException, PDUException, IOException{
		if(!isConected()){
			log.warn("Nos hemos desconectado del SMSC, reconectando...");
			if(!conectar(listener)){
				return null;
			}
		}
		return sesion.receive();
	}
	
	public static SubmitSMResp submit(SubmitSM request) throws ValueNotSetException, TimeoutException, PDUException, WrongSessionStateException, IOException{
		if(!isConected()){
			log.warn("Nos hemos desconectado del SMSC, reconectando...");
			if(!conectar(listener)){
				return null;
			}
		}
		return sesion.submit(request);
	}

	public static void enquireLink(EnquireLink request) throws ValueNotSetException, TimeoutException, PDUException, WrongSessionStateException, IOException{
		if(!isConected()){
			log.warn("Nos hemos desconectado del SMSC, reconectando...");
			if(!conectar(listener)){
				return;
			}
		}
		sesion.enquireLink(request);
	}
	
	private static boolean isConected(){
		return sesion!=null && sesion.isOpened() && sesion.isBound();
	}

	public static boolean conectar(ServerPDUEventListener eventListener){
		listener=eventListener;
		BindRequest request=null;
		BindResponse response=null;
		String bindOption=Config.getPropiedad("smsc.bindOption");

		if(bindOption.equalsIgnoreCase("t")){
			request=new BindTransmitter();
		}
		else if(bindOption.equalsIgnoreCase("r")){
			request=new BindReceiver();
		}
		else if(bindOption.equalsIgnoreCase("tr")){
			request=new BindTransciever();
		}
		else{
			log.error("Invalid bind mode, expected t, r or tr, got " + bindOption + ". Operation canceled.");
			return false;
		}

		String servidor=Config.getPropiedad("smsc.servidor");
		int puerto=Config.getPropiedadInt("smsc.puerto");

		TCPIPConnection connection=new TCPIPConnection(servidor,puerto);
		connection.setReceiveTimeout(20 * 1000);

		sesion=new Session(connection);

		try{
			request.setSystemId(Config.getPropiedad("smsc.usuario"));
			request.setPassword(Config.getPropiedad("smsc.clave"));
			request.setSystemType("SMPP");
			request.setInterfaceVersion((byte) 0x34);

			// send the request
			log.debug("Bind request " + request.debugString());
			if(eventListener!=null){
				response=sesion.bind(request,listener);
			}
			else{
				response=sesion.bind(request);
			}
			log.debug("Bind response " + response.debugString());
			if(response.getCommandStatus() == Data.ESME_ROK){
				log.info("LBS conectado al SMSC("+servidor+":"+puerto+")");
				return true;
			}
			log.error("No se pudo conectar al SMSC("+servidor+":"+puerto+")");
			return false;
		}
		catch(WrongLengthOfStringException e){
			log.error("Error conectando al SMSC",e);
			return false;
		}
		catch(ValueNotSetException e){
			log.error("Error conectando al SMSC",e);
			return false;
		}
		catch(TimeoutException e){
			log.error("Error conectando al SMSC",e);
			return false;
		}
		catch(PDUException e){
			log.error("Error conectando al SMSC",e);
			return false;
		}
		catch(WrongSessionStateException e){
			log.error("Error conectando al SMSC",e);
			return false;
		}
		catch(IOException e){
			log.error("Error conectando al SMSC",e);
			return false;
		}
	}

	public static boolean conectar(){
		return conectar(null);
	}

	public static ServerPDUEventListener getListener(){
		return listener;
	}

}
