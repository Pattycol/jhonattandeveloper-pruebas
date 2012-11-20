package com.btg.claro.LBS.sms;

public interface EnvioSMS{
	
	boolean enviarSMS(String numeroOrigen,String numeroDestino,String mensaje);
	
	boolean enviarSMSOculto(String numeroOrigen,String numeroDestino);

}
