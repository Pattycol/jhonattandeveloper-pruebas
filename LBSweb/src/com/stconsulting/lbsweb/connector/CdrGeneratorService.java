/*
 * CdrGenerator.java
 *
 * Created on 3 de junio de 2005, 15:31
 */

package com.stconsulting.lbsweb.connector;

import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.common.bean.Resultado;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Helper;
import com.stconsulting.common.util.Converter;

import com.stconsulting.common.service.ServiceException;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.*;
import org.apache.commons.net.ftp.*;
import noNamespace.*;

import java.util.*;

/**
 * 
 * @author Administrador
 */
public class CdrGeneratorService{
	protected Logger log=null;
	private static String servidorFTP=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_HOST_FTP);
	private static String usuarioFTP=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_USUARIO_FTP);
	private static String passwordFTP=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_PSW_FTP);
	private static int puertoFTP=(int) Helper.getPropertyDouble(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_PUERTO_FTP);
	private static String subdirectorioFTP=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_SUBDIR_FTP);
	//private static String subdirectorioBackupFTP=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_SUBDIRBACKUP_FTP);
	private static String extensionCdrEliminar=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_EXTENSION_ELIMINAR);

	private static int dCallDuration=Integer.parseInt(Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_CALL_DURATION));
	// private static String
	// strCallReference=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_CALL_REFERENCE);
	private static String strCalledIMSI=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_CALLED_IMSI);
	private static String strCallingIMEI=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_CALLING_IMEI);
	private static String strCallingIMSI=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_CALLING_IMSI);
	private static int dDataVolumeDown=Integer.parseInt(Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_DATA_VOLUME_DOWN));
	private static int dDataVolumeUp=Integer.parseInt(Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_DATA_VOLUME_UP));
	private static int dEventVolume=Integer.parseInt(Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_DATA_VOLUME_UP));
	private static String strExchangeID=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_EXCHANGE_ID);
	private static String strFiller1=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_FILLER_1);
	private static String strFiller2=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_FILLER_2);
	private static String strFiller3=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_FILLER_3);
	private static String strFiller4=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_FILLER_4);
	private static String strFiller5=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_FILLER_5);
	private static String strGSMPI=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_GSMPI);
	private static String strHPLMNind=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_HPLMN);
	private static String strLAC=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_LAC);
	private static int dMessageVolume=Integer.parseInt(Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_MESSAGE_VOLUME));
	private static String strQualityofService=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_QUALITY_SERVICE);
	private static String strRoutingCategory=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_ROUTING_CATEGORY);
	private static String strSequence=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_SEQUENCE);
	private static String strService=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_SERVICE);
	private static String strType=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_TYPE);

	/** Creates a new instance of CdrGenerator */
	public CdrGeneratorService(){
		log=Logger.getLogger(this.getClass());
	}

	/*
	 * public static void main(String[] args){ try{
	 * System.out.print(Converter.dateToString(new
	 * java.util.Date(),"yyyyMMddhhmmss")); }catch(Exception e){
	 * e.printStackTrace(); } }
	 */
	public Resultado generaCDR(List<Cobro> listaCobro,int[] numConsecutivo) throws ServiceException{
		Resultado resultado=new Resultado();
		try{

			resultado.setCodigo(Constants.ERROR);
			String nombreArchivo=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_NOMBRE_ARCHIVO);
			String parametroFormato=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_NOMBRE_ARCHIVO_FORMATO);
			String extension=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_EXTENSION_ARCHIVO);
			String formatoInternacional=Helper.getProperty(Constants.CDR_BUNDLE,Constants.PROPERTY_CDR_FORMATO_INTERNACIONAL);
			nombreArchivo=nombreArchivo + Helper.getFormatoConsecutivo(numConsecutivo[0],5) + parametroFormato + (Converter.dateToString(new java.util.Date(),Constants.FORMATO_FECHA_HORA_CDR)) + "." + extension;
			resultado.setDescripcion(nombreArchivo);

			XmlOptions xmlOptions;
			xmlOptions=new XmlOptions();
			// Requests use of whitespace for easire reading
			xmlOptions.setSavePrettyPrint();
			// Requests that nested levels of the xml document to be indented by
			// multiple of 4 whitespace characters
			xmlOptions.setSavePrettyPrintIndent(4);
			xmlOptions.setCompileDownloadUrls();
			xmlOptions.setLoadMessageDigest();

			CDRVariosDocument myDoc;
			myDoc=CDRVariosDocument.Factory.newInstance();
			CDRVariosDocument.CDRVarios cdrVarios=myDoc.addNewCDRVarios();

			String strTimestamp;
			String strCallReference;
			int numConsecutivoCdr=numConsecutivo[1];
			for(int i=0;i < listaCobro.size();i++){
				CDRDocument.CDR cdr=cdrVarios.addNewCDR();

				Cobro cobro=listaCobro.get(i);
				Localizacion localizacion=cobro.getLocalizacion();
				// log.debug("Fecha de registro del cobro : "+cobro.getFechaRegistro());
				strTimestamp=Converter.dateToString(new java.util.Date(),Constants.PATH_TIMESTAMP_CDR);

				// strTimestamp=Converter.dateToString(new
				// java.util.Date(Converter.stringToSqlDate(cobro.getFechaRegistro(),Constants.FORMATO_FECHA_HORA_24).getTime())
				// ,Constants.PATH_TIMESTAMP_CDR);
				strTimestamp=Converter.dateToString(new java.util.Date(Converter.stringToSqlDate(cobro.getFechaRegistro(),Constants.FORMATO_FECHA_HORA_24).getTime()),Constants.PATH_TIMESTAMP_CDR);
				strCallReference=Converter.dateToString(new java.util.Date(),Constants.FORMATO_FECHA_HORA_CDR) + Helper.getFormatoConsecutivo(numConsecutivoCdr,5);

				cdr.setCallingNumber(formatoInternacional + localizacion.getMobileOrigen());
				cdr.setCallDuration(dCallDuration);
				cdr.setCallReference(strCallReference);
				cdr.setCalledIMSI(strCalledIMSI);
				cdr.setCallingIMEI(strCallingIMEI);
				cdr.setCallingIMSI(strCallingIMSI);
				cdr.setDataVolumeDown(dDataVolumeDown);
				cdr.setDataVolumeUp(dDataVolumeUp);
				cdr.setDestination(formatoInternacional + localizacion.getMobileDestino());
				cdr.setEventVolume(dEventVolume);
				cdr.setExchangeID(strExchangeID);
				cdr.setFiller1(strFiller1);
				cdr.setFiller2(strFiller2);
				cdr.setFiller3(strFiller3);
				cdr.setFiller4(strFiller4);
				cdr.setFiller5(strFiller5);
				cdr.setGSMPI(strGSMPI);
				cdr.setHPLMNind(strHPLMNind);
				cdr.setLAC(strLAC);
				cdr.setMessageVolume(dMessageVolume);
				cdr.setQualityofService(strQualityofService);
				cdr.setRoutingCategory(strRoutingCategory);
				cdr.setSequence(strSequence);
				cdr.setService(strService);
				cdr.setTimestamp(strTimestamp);
				cdr.setType(strType);

				numConsecutivoCdr++;
			}

			// log.debug("XML formado : "+myDoc.toString());
			// se guarda en ftp
			byte[] strCdrBytes=myDoc.toString().getBytes();
			log.debug("bytes : " + strCdrBytes.length);
			FtpCliente ftpCliente=new FtpCliente();
			if(ftpCliente.connectAndLogin(servidorFTP,usuarioFTP,passwordFTP,puertoFTP)){
				try{
					ftpCliente.enviaArchivosPorFTP(subdirectorioFTP,strCdrBytes,nombreArchivo);
					resultado.setCodigo(Constants.OK);

				}
				catch(Exception e){
					log.error(e);
				}
				finally{
					ftpCliente.logout();
					ftpCliente.disconnect();
				}
			}

		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e.getMessage());
		}
		return resultado;
	}

	public void eliminaArchivo(String archivoCdr) throws ServiceException{
		FtpCliente ftpCliente=new FtpCliente();
		try{
			log.debug("ENTRO A ELIMINAR EL ARCHIVO : " + archivoCdr);
			if(ftpCliente.connectAndLogin(servidorFTP,usuarioFTP,passwordFTP,puertoFTP)){
				try{
					if(ftpCliente.changeWorkingDirectory(subdirectorioFTP))
						ftpCliente.deleteFile(archivoCdr);
				}
				catch(Exception e){
					log.error(e);
					throw new ServiceException(e.getMessage());
				}
				finally{
					ftpCliente.logout();
					ftpCliente.disconnect();
				}
			}
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	// Eliminar Archivos OLD
	public void removeFilesCdrOld(){
		FtpCliente ftpCliente=new FtpCliente();
		int tamExtensionEliminar=extensionCdrEliminar.length();
		int posicionCopiar=0;
		try{
			if(ftpCliente.connectAndLogin(servidorFTP,usuarioFTP,passwordFTP,puertoFTP)){
				try{
					log.info("Entrando a Eliminar Archivos Cdr Old");
					if(ftpCliente.changeWorkingDirectory(subdirectorioFTP)){
						ftpCliente.enterLocalPassiveMode();
						FTPFile[] files=ftpCliente.listFiles();
						if(files != null && files.length > 0){
							for(int i=0;i < files.length;i++){
								String nameFile=files[i].getName().trim();
								posicionCopiar=nameFile.length() - tamExtensionEliminar;
								if(extensionCdrEliminar.toUpperCase().equals(nameFile.substring(posicionCopiar,posicionCopiar + tamExtensionEliminar).toUpperCase())){
									ftpCliente.deleteFile(nameFile);
								}
							}
							log.info("Fin de Eliminar Archivos CdrOld");
						}
					}
				}
				catch(Exception e){
					log.error(e);
					throw new ServiceException(e.getMessage());
				}
				finally{
					ftpCliente.logout();
					ftpCliente.disconnect();
				}
			}
		}
		catch(Exception e){
			log.error(e);
		}
	}

	/*public ArrayList getFilesOld(){
		FtpCliente ftpCliente=new FtpCliente();
		ArrayList listaFilesEliminar=new ArrayList();
		int tamExtensionEliminar=extensionCdrEliminar.length();
		int posicionCopiar=0;
		try{
			log.debug("Entrando a Mover Archivos OLD");
			if(ftpCliente.connectAndLogin(servidorFTP,usuarioFTP,passwordFTP,puertoFTP)){
				try{
					log.debug("Entrando a Mover Archivos OLD 111");

					if(ftpCliente.changeWorkingDirectory(subdirectorioFTP)){
						log.debug("Entrando a Mover Archivos OLD 222");
						ftpCliente.enterLocalPassiveMode();
						FTPFile[] files=ftpCliente.listFiles();
						log.debug("Entrando a Mover Archivos OLD 333 files " + files);
						log.debug("Numero de archivos en directorio cdr: " + files.length);
						if(files != null && files.length > 0){
							for(int i=0;i < files.length;i++){
								String nameFile=files[i].getName().trim();
								log.debug("Nombre del Archivo " + nameFile);
								posicionCopiar=nameFile.length() - tamExtensionEliminar;
								if(extensionCdrEliminar.equals(nameFile.substring(posicionCopiar,posicionCopiar + tamExtensionEliminar)) || extensionCdrEliminar.toUpperCase().equals(nameFile.substring(posicionCopiar,posicionCopiar + tamExtensionEliminar))){
									log.debug("Nombre del Archivo a Mover " + nameFile);
									ftpCliente.sendCommand("cp " + nameFile + " /usr/lbsweb/cdrBackup");
								}
							}
						}
					}
				}
				catch(Exception e){
					log.error(e);
					throw new ServiceException(e.getMessage());
				}
				finally{
					ftpCliente.logout();
					ftpCliente.disconnect();
				}
			}
		}
		catch(Exception e){
			log.error(e);
		}
		return listaFilesEliminar;
	}*/

	/*
	 * public void MoveFiletoBackup() throws ServiceException { FtpCliente
	 * ftpCliente=new FtpCliente(); try{
	 * log.debug("ENTRO A MoveFiletoBackup"); ArrayList
	 * listFilestoMove=this.getFilesOld();
	 * log.debug("Lista Archivos a Eliminar "+listFilestoMove.size());
	 * if
	 * (ftpCliente.connectAndLogin(servidorFTP,usuarioFTP,passwordFTP,puertoFTP
	 * )){ try{ log.debug("ENTRO A MoveFiletoBackup");
	 * if(ftpCliente.changeWorkingDirectory(subdirectorioBackupFTP)) {
	 * ftpCliente.enterLocalPassiveMode(); if (listFilestoMove!=null &&
	 * listFilestoMove.size()>0) { for (int i=0;i<listFilestoMove.size();i++) {
	 * log.debug("Entrando a Mover Archivos"); FTPFile
	 * fileEliminar=(FTPFile)listFilestoMove.get(i);
	 * log.debug("Entrando a Mover Archivos11111"); FileInputStream
	 * in=new FileInputStream(fileEliminar.getName());
	 * log.debug("Entrando a Mover Archivos222");
	 * ftpCliente.storeFile(fileEliminar.getName(),in);
	 * 
	 * ftpCliente.storeUniqueFile((InputStream)listFilestoMove.get(i));
	 * log.debug("Ya lo Movio");
	 * log.debug("Se movio satisfactoriamente el archivo "); } } }
	 * }catch(Exception e){ log.error(e); throw new
	 * ServiceException(e.getMessage()); }finally{ ftpCliente.logout();
	 * ftpCliente.disconnect(); } } }catch(Exception e){ log.error(e); throw new
	 * ServiceException(e.getMessage()); }
	 * 
	 * 
	 * }
	 */
}
