package com.btg.claro.LBS.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smpp.ServerPDUEvent;
import org.smpp.ServerPDUEventListener;
import org.smpp.SmppObject;
import org.smpp.pdu.PDU;
import org.smpp.util.Queue;

public class SMPPTestPDUEventListener extends SmppObject implements ServerPDUEventListener{
	
	private static final Logger log=LoggerFactory.getLogger(SMPPTestPDUEventListener.class);

	private Queue requestEvents=new Queue();

	public SMPPTestPDUEventListener(){
	}

	public void handleEvent(ServerPDUEvent event){
		PDU pdu=event.getPDU();
		if(pdu.isRequest()){
			log.debug("async request received, enqueuing " + pdu.debugString());
			synchronized (requestEvents){
				requestEvents.enqueue(event);
				requestEvents.notify();
				log.debug("Mensajes en cola: "+requestEvents.size());
			}
		}
		else if(pdu.isResponse()){
			log.debug("async response received " + pdu.debugString());
		}
		else{
			log.warn("pdu of unknown class (not request nor response) received, discarding " + pdu.debugString());
		}
	}

	/**
	 * Returns received pdu from the queue. If the queue is empty, the
	 * method blocks for the specified timeout.
	 */
	public ServerPDUEvent getRequestEvent(long timeout){
		ServerPDUEvent pduEvent=null;
		synchronized (requestEvents){
			if(requestEvents.isEmpty()){
				try{
					requestEvents.wait(timeout);
				}
				catch(InterruptedException e){
					// ignoring, actually this is what we're waiting for
				}
			}
			if(!requestEvents.isEmpty()){
				pduEvent=(ServerPDUEvent) requestEvents.dequeue();
			}
		}
		return pduEvent;
	}
}
