/*
 * FileCompresser.java
 *
 * Created on 16 de agosto de 2004, 12:06 PM
 */

package com.stconsulting.common.util;

import java.io.*;
import java.util.zip.*;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * 
 * @author Administrador
 */
public class FileCompressor{
	
	private static Logger log=Logger.getLogger(FileCompressor.class);

	public static int BUFFER_SIZE=10240;

	/** Creates a new instance of FileCompresser */
	public FileCompressor(){
	}

	public static java.io.File compressFile(String entryName,java.io.InputStream is,String fileName,String fileDirectory) throws Exception{
		return compressFile(entryName,is,fileName,fileDirectory,null);
	}

	public static java.io.File compressFile(String entryName,java.io.InputStream is,String fileName,String fileDirectory,String comment) throws Exception{
		// Create a new File
		File f=new File(fileDirectory,fileName);
		if(f.exists())
			return null;
		if(f.createNewFile()){
			FileOutputStream fos=null;
			try{
				fos=new FileOutputStream(f);
				// Make it a Zip
				ZipOutputStream zos=new ZipOutputStream(fos);
				ZipEntry zEntry=new ZipEntry(entryName);
				zEntry.setMethod(ZipEntry.DEFLATED);
				log.debug("Comentario de file " + fileName + ":" + comment);
				if(comment != null)
					zEntry.setComment(comment);
				// zEntry.
				zos.putNextEntry(zEntry);
				byte[] buffer=new byte[BUFFER_SIZE];
				int nBytes;
				do{
					nBytes=is.read(buffer);
					if(nBytes == -1)
						break;
					zos.write(buffer,0,nBytes);
				}while(nBytes >= 0);
				zos.closeEntry();
				zos.close();
				return f;
			}
			catch(java.io.IOException ioe){
				try{
					if(f.exists()){
						if(fos != null)
							fos.close();
						f.delete();
					}
					return null;
				}
				catch(Exception ignore){
				}
			}
		}
		return null;
	}

	public static DecompressedFile decompressFile(java.io.InputStream is,String fileName,String fileDirectory) throws Exception{
		// Create a new File
		DecompressedFile f=new DecompressedFile(fileDirectory,fileName);
		if(f.exists())
			return null;
		if(f.createNewFile()){
			FileOutputStream fos=null;
			try{
				fos=new FileOutputStream(f);
				// Make it a Zip
				ZipInputStream zis=new ZipInputStream(is);
				ZipEntry sEntry=zis.getNextEntry();
				f.setComment(sEntry.getComment());
				int nBytes,totalBytes=0;
				byte[] buffer=new byte[BUFFER_SIZE];
				do{
					nBytes=zis.read(buffer);
					if(nBytes == -1)
						break;
					fos.write(buffer,0,nBytes);
					totalBytes+=nBytes;
				}while(nBytes >= 0);
				zis.closeEntry();
				zis.close();
				fos.flush();
				fos.close();
				log.debug("total bytes written=" + totalBytes);
				return f;
			}
			catch(java.io.IOException ioe){
				try{
					if(f.exists()){
						if(fos != null)
							fos.close();
						f.delete();
					}
					return null;
				}
				catch(Exception ignore){
				}
			}
		}
		return null;
	}

	public static java.io.File createTempDir(String uniqueName) throws Exception{
		java.io.File f=null;
		if(uniqueName != null)
			f=new java.io.File(ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE).getString("temp.path"),uniqueName);
		else
			f=new File(ResourceBundle.getBundle(Constants.APPLICATION_BUNDLE).getString("temp.path"));
		if(!f.exists()){
			if(!f.mkdirs())
				throw new Exception("No se pudo crear directorio temporal");
			// STCAMBIAR If you shutdown the application, the temp directories
			// are deleted
			if(uniqueName != null)
				f.deleteOnExit();
		}

		return f;
	}

}
