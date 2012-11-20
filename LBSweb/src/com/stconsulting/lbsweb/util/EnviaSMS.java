package com.stconsulting.lbsweb.util;

import java.net.*; // for Socket
import java.io.*; // for IOException and Input/OutputStream
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Helper;
import org.apache.log4j.Logger;

public class EnviaSMS{

	protected Logger log=null;

	private static String gpsIp;
	private static int gpsPort;

	public EnviaSMS(){
		log=Logger.getLogger(this.getClass());
	}

	static{
		gpsIp=Helper.getProperty(Constants.GPS_BUNDLE,Constants.GPS_SMSC_IP);
		gpsPort=(int) Helper.getPropertyDouble(Constants.GPS_BUNDLE,Constants.GPS_SMSC_PORT);
	}

	public void send(String number,String largeAccount,String message){
		try{

			String Parte1="00/";
			// Parte 2 es la longitud
			String Parte3="/O/51/";
			// Parte 4 es destino (arg 0)
			String Parte5="/";
			// Parte 6 es Origen(arg 1)
			String Parte7="/////////////////3//";
			// Parte 8 es el mensaje en hexadecimal
			String Parte9="/////////////";
			// Parte 10 es el checksum
			String Parte11="";

			// Esta parte convierte el texto ha hexadecimal

			String x="";
			int suma=0;
			char[] myChars=message.toCharArray();
			for(int i=0;i < myChars.length;++i){
				char c=myChars[i];
				//byte hi=(byte) (c >>> 8);
				byte lo=(byte) (c & 0xff);
				x=x + UnicodeFormatter.byteToHex(lo);
			}
			// Fin de Conversion

			// Formacion de SMS sin checksum

			int longitud=Parte1.length() - 1 + 5 + Parte3.length() + number.length() + Parte5.length() + largeAccount.length() + Parte7.length() + x.length() + Parte9.length() + 2;
			log.debug("longitud=" + longitud);
			String LongSMS="";
			if(longitud < 100){
				LongSMS="000" + String.valueOf(longitud);
			}
			else{
				LongSMS="00" + String.valueOf(longitud);
			}
			String TempSMS="00/" + LongSMS + (Parte3 + number + Parte5 + largeAccount + Parte7 + x + Parte9).toUpperCase();
			// Fin de Formacion de SMS sin checksum

			// Calculo del checksum

			char[] SMSchar=TempSMS.toCharArray();
			for(int j=0;j < SMSchar.length;j++){
				char d=SMSchar[j];
				suma=suma + d;
			}

			int res=0,p=0;
			char hex[]=new char[8];
			while(suma > 0){
				res=suma % 16;
				suma=suma / 16;
				if(res < 10){
					hex[p]=(char) (res + 48);
				}
				else{
					hex[p]=(char) (res + 55);
				}
				p++;
			}
			// Fin de Calculo del checksum hex[1]+hex[0]
			// log.debug("checksum="+hex[1]+hex[0]);

			// Formacion de SMS Total
			String SMS="" + (TempSMS + hex[1] + hex[0]).toUpperCase() + Parte11;
			log.debug("SMS: " + SMS);
			// Fin de Formacion de SMS Total

			// Conexion al SMSC para envio

			log.debug("Enviador de SMS iniciado");
			Socket socket=new Socket(gpsIp,gpsPort);
			socket.setSoTimeout(5 * 1000);
			// Socket socket=new Socket("172.26.1.1",5102);
			log.debug("Connectado al SMSC...enviando SMS");
			InputStream in=socket.getInputStream();
			OutputStream out=socket.getOutputStream();
			byte[] byteBuffer=SMS.getBytes();
			out.write(byteBuffer);
			String Blank="                                               ";
			byte[] ack=Blank.getBytes();

			//int recvMsgSize; // Bytes received in last read

			// recvMsgSize = in.read(byteBuffer);
			log.debug("Antes de leer el mensaje");
			/*recvMsgSize=*/in.read(ack);

			log.debug("ACK: " + new String(ack));

			socket.close();
			// Cierre de Conexion al SMSC
		}
		catch(Exception e){
			log.error(e);
		}

	}// Fin de Main

	static public class UnicodeFormatter{

		static public String byteToHex(byte b){
			// Returns hex String representation of byte b
			char hexDigit[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			char[] array={hexDigit[(b >> 4) & 0x0f],hexDigit[b & 0x0f]};
			return new String(array);
		}

		static public String charToHex(char c){
			// Returns hex String representation of char c
			byte hi=(byte) (c >>> 8);
			byte lo=(byte) (c & 0xff);
			return byteToHex(hi) + byteToHex(lo);
		}

	} // class

}
