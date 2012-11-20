/*
 * FtpClient.java
 *
 * Created on 10 de junio de 2005, 05:37 PM
 */

package com.stconsulting.lbsweb.connector;

import java.io.*;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.log4j.Logger;

import java.net.UnknownHostException; //import sun.net.ftp.FtpClient;
//import sun.net.ftp.FtpLoginException;
//import sun.net.ftp.FtpProtocolException;
//import sun.net.TelnetOutputStream;

import com.stconsulting.common.util.*;

/**
 * 
 * @author STCosulting
 */
public class FtpCliente extends FTPClient{
	
	private Logger log=Logger.getLogger(this.getClass());

	/** Creates a new instance of FtpClient */
	public FtpCliente(){
	}

	public boolean connectAndLogin(String host,String userName,String password,int port) throws IOException,UnknownHostException,FTPConnectionClosedException{
		boolean success=false;
		log.debug("INTENTANDO CONECTARSE FTP :host :" + host + ",user :" + userName + ", psw :" + password + ",port :" + port);
		setDefaultPort(port);
		connect(host);

		int reply=getReplyCode();
		if(FTPReply.isPositiveCompletion(reply)){
			success=login(userName,password);
			log.debug("Se hizo login :" + success);
		}
		else{
			log.debug("No pudo conectarse");
		}
		if(!success)
			disconnect();

		return success;
	}

	public String enviaArchivosPorFTP(String subDirectory,byte[] datosArchivo,String nombreArchivo){
		String ret="";
		// FTPClient client = null;
		// TelnetOutputStream tos = null;
		// FileInputStream dis = null;
		ByteArrayInputStream in=null;

		try{
			// client = new FTPClient(hostName, port);
			// client.login(userFtp, passwordFtp);
			// client.cd(subDirectory);
			// client.binary();
			log.debug("FtpCliente : Antes de enviar a " + subDirectory);
			boolean flag=true;
			if(subDirectory != null && !subDirectory.equals("")){
				flag=this.changeWorkingDirectory(subDirectory);
			}

			if(flag){
				log.debug("Se pudo cambiar de dir");
				in=new ByteArrayInputStream(datosArchivo);
				this.enterLocalPassiveMode();
				this.storeFile(nombreArchivo,in);
				log.debug("Se hizo el store");
				in.close();
				log.debug("FtpCliente : Despues de enviar");
				ret=Constants.OK;
			}

		}
		catch(Exception e){
			e.printStackTrace();
			ret=Constants.ERROR;
			try{
				if(in != null)
					in.close();
			}
			catch(Exception ignore){
			}
		}

		return ret;
	}
}
