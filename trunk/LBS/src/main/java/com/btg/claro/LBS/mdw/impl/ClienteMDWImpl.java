package com.btg.claro.LBS.mdw.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.mdw.ClienteMDW;
import com.btg.claro.LBS.mdw.RespuestaMDW;
import com.btg.claro.LBS.util.Config;

@Service("ClienteMDW")
public class ClienteMDWImpl implements ClienteMDW{
	
	private static final Logger log=LoggerFactory.getLogger(ClienteMDWImpl.class);

	public RespuestaMDW reserve(String application,String msisdn,String productId){
		if(!msisdn.startsWith("51"))
			msisdn="51"+msisdn;
		String request="RequestType=reserve&Application="+application+"&ProductId="+productId+"&Msisdn="+msisdn;
		String response=getRespuesta(request);
		if(response!=null){
			return procesarRespuesta(response);
		}
		return null;
	}

	public RespuestaMDW acceptReserve(String application,String msisdn,String productId,String platform,String transactionId){
		if(!msisdn.startsWith("51"))
			msisdn="51"+msisdn;
		String request="RequestType=acceptReserve&Application="+application+"&ProductId="+productId+"&Msisdn="+msisdn+"&TransactionId="+transactionId+"&Platform="+platform;
		String response=getRespuesta(request);
		if(response!=null){
			return procesarRespuesta(response);
		}
		return null;
	}

	public RespuestaMDW cancelReserve(String application,String msisdn,String productId,String platform,String transactionId){
		if(!msisdn.startsWith("51"))
			msisdn="51"+msisdn;
		String request="RequestType=cancelReserve&Application="+application+"&ProductId="+productId+"&Msisdn="+msisdn+"&TransactionId="+transactionId+"&Platform="+platform;
		String response=getRespuesta(request);
		if(response!=null){
			return procesarRespuesta(response);
		}
		return null;
	}

	private String getRespuesta(String request){
		Socket socket=new Socket();
		String servidor=Config.getPropiedad("mdw.servidor");
		int puerto=Config.getPropiedadInt("mdw.puerto");
		SocketAddress socketAddress=new InetSocketAddress(servidor,puerto);
		try{
			log.debug("Tratando de conectarse al Middleware ("+servidor+":"+puerto+")");
			socket.connect(socketAddress);
			log.debug("Conectado al servidor "+servidor+":"+puerto);
			BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			log.debug("Enviando request: ("+request+")");
			bufferedWriter.write(request+"\r");
			bufferedWriter.flush();
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String response=bufferedReader.readLine();
			bufferedReader.close();
			socket.close();
			log.debug("Response recibido: ("+response+")");
			return response;
		}
		catch(IOException e){
			log.error("Error de conexion del MDW",e);
			return null;
		}
	}

	private RespuestaMDW procesarRespuesta(String response){
		RespuestaMDW respuesta=new RespuestaMDW();
		int p=response.indexOf("ReturnCode");
		if(p>-1){			
			p=response.indexOf("=",p)+1;
			int a=response.indexOf("&",p);
			if(a<0)
				a=response.length();
			respuesta.setCodigo(Integer.parseInt(response.substring(p,a)));
			log.debug("ReturnCode="+respuesta.getCodigo());
		}
		p=response.indexOf("Platform");
		if(p>-1){
			p=response.indexOf("=",p)+1;
			int a=response.indexOf("&",p);
			if(a<0)
				a=response.length();
			respuesta.setPlatform(response.substring(p,a));
			log.debug("Platform="+respuesta.getPlatform());
		}
		p=response.indexOf("TransactionId");
		if(p>-1){
			p=response.indexOf("=",p)+1;
			int a=response.indexOf("&",p);
			if(a<0)
				a=response.length();
			respuesta.setTransactionId(response.substring(p,a));
			log.debug("TransactionId="+respuesta.getTransactionId());
		}
		return respuesta;
	}

}
