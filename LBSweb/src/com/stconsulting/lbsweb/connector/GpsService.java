/*
 * GPSConnector.java
 *
 * Created on 11 de agosto de 2005, 03:21 PM
 */

package com.stconsulting.lbsweb.connector;

import java.io.*;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import com.stconsulting.common.util.*;

/**
 * 
 * @author STCosulting
 */
public class GpsService{

	protected Logger log=null;

	/*private static String servidorFTP;
	private static String usuarioFTP;
	private static String passwordFTP;
	private static int puertoFTP;
	private static String nombreArchivo;*/
	private static String nombreArchivoLocal;
	static{
		/*servidorFTP=Helper.getProperty(Constants.GPS_BUNDLE,Constants.PROPERTY_GPS_HOST_FTP);
		usuarioFTP=Helper.getProperty(Constants.GPS_BUNDLE,Constants.PROPERTY_GPS_USUARIO_FTP);
		passwordFTP=Helper.getProperty(Constants.GPS_BUNDLE,Constants.PROPERTY_GPS_PSW_FTP);
		puertoFTP=(int) Helper.getPropertyDouble(Constants.GPS_BUNDLE,Constants.PROPERTY_GPS_PUERTO_FTP);
		nombreArchivo=Helper.getProperty(Constants.GPS_BUNDLE,Constants.PROPERTY_GPS_ARCHIVO_FTP);*/
		nombreArchivoLocal=Helper.getProperty(Constants.GPS_BUNDLE,Constants.PROPERTY_GPS_ARCHIVO_LOCAL_FTP);
	}

	/** Creates a new instance of GPSConnector */
	public GpsService(){
		log=Logger.getLogger(this.getClass());
	}

	public List<GpsData> obtenerDataArchivoGPS(){

		List<GpsData> listaData=new ArrayList<GpsData>();

		log.debug("Nombre del archivo para abrir :" + nombreArchivoLocal);
		File reader=new File(nombreArchivoLocal);

		try{
			/*
			 * FileOutputStream out=new FileOutputStream(reader);
			 * BufferedOutputStream outBuffer; InputStream is;
			 */
			/*
			 * FtpCliente ftpCliente=new FtpCliente();
			 * if(ftpCliente.connectAndLogin
			 * (servidorFTP,usuarioFTP,passwordFTP,puertoFTP)){
			 * log.debug("Conexion FTP GPS establecida");
			 * ftpCliente.enterLocalPassiveMode();
			 * 
			 * ftpCliente.retrieveFile(nombreArchivo,out);
			 * log.debug("Se trae el archivo "+nombreArchivo);
			 * ftpCliente.deleteFile(nombreArchivo);
			 * ftpCliente.enviaArchivosPorFTP("",new byte[0],nombreArchivo); }
			 * out.close();
			 * 
			 * ftpCliente.logout(); ftpCliente.disconnect();
			 */

			InputStream stream=new FileInputStream(reader);
			InputStreamReader isr=new InputStreamReader(stream);

			BufferedReader br=new BufferedReader(isr);
			String line;
			//int cont=0;
			char chars[]=new char[]{'\u0011'};
			while((line=br.readLine()) != null){
				GpsData gpsdata=new GpsData();
				StringTokenizer tokens=new StringTokenizer(line,new String(chars));
				log.debug("Numero de token : " + tokens.countTokens());
				if(tokens.countTokens() != 12){
					continue;
				}
				String header=tokens.nextToken();
				String cmd=header.substring(1);
				header=header.substring(0,1);
				if(!cmd.equals("NMR")){
					gpsdata.setHeader(header);
					gpsdata.setCmd(cmd);
					gpsdata.setPart(tokens.nextToken());
					log.debug("" + gpsdata.getPart());
					gpsdata.setMode(tokens.nextToken());
					log.debug("" + gpsdata.getMode());
					gpsdata.setBatteryStatus(tokens.nextToken());
					log.debug("" + gpsdata.getBatteryStatus());
					gpsdata.setPositionSource(tokens.nextToken());
					log.debug("" + gpsdata.getPositionSource());
					gpsdata.setPositionFormat(tokens.nextToken());
					log.debug("" + gpsdata.getPositionFormat());
					gpsdata.setPositionLat(tokens.nextToken());
					log.debug("" + gpsdata.getPositionLat());
					gpsdata.setPositionLon(tokens.nextToken());
					log.debug("" + gpsdata.getPositionLon());
					gpsdata.setDate(tokens.nextToken());
					log.debug("" + gpsdata.getDate());
					gpsdata.setTime(tokens.nextToken());
					log.debug("" + gpsdata.getTime());
					gpsdata.setSpeed(tokens.nextToken());
					log.debug("" + gpsdata.getSpeed());
					gpsdata.setDirection(tokens.nextToken());
					log.debug("" + gpsdata.getDirection());
					// temporal
					gpsdata.setMobileOrigen("197103133");
					gpsdata.setDateRegistro(gpsdata.getDate());
					gpsdata.setTimeRegistro(gpsdata.getTime());
					// **********
					listaData.add(gpsdata);
				}
			}
		}
		catch(Exception e){
			log.error(e);
		}
		finally{
			reader.renameTo(new File(nombreArchivoLocal + ".old"));
		}
		return listaData;
	}

}
