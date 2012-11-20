package com.btg.claro.LBS.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.Normalizer.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util{
	
	private static final Logger log=LoggerFactory.getLogger(Util.class);
	
	public static String toMD5(String entrada){
		try{
			MessageDigest d=MessageDigest.getInstance("MD5");
			d.update(entrada.getBytes());
			byte[] md5=d.digest();
			StringBuffer result=new StringBuffer();
	        for(byte b : md5){
	            int low=b & 0x0F;
	            int high=b & 0xF0;
	            result.append(Integer.toHexString(high).substring(0, 1));
	            result.append(Integer.toHexString(low));
	        }
			return result.toString();
		}
		catch(NoSuchAlgorithmException e){
		}
		return null;
	}
	
	public static boolean vacio(String cadena){
		return cadena==null || cadena.equals("");
	}
	
	public static String quitarAcentos(String cadena){
		return Normalizer.normalize(cadena,Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+","");
	}
	public static String getTextoMail(String archivo/*,Object... parametros*/){
		StringBuffer sb=new StringBuffer();
		try{
			FileReader fr=new FileReader(new File(Config.getPropiedad("lbs.carpetaCorreo")+File.separator+archivo));
			BufferedReader br=new BufferedReader(fr);			
			String linea;
			while((linea=br.readLine())!=null){
				sb.append(linea).append("<br/>");
			}
			br.close();
			fr.close();
		}
		catch(FileNotFoundException e){
			log.error("No se entontro el archivo "+archivo);
			return null;
		}
		catch(IOException e){
			log.error("Error leyendo archivo "+archivo,e);
			return null;
		}
		return sb.toString();
	}

}
