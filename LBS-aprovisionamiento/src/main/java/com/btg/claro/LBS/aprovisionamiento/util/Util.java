package com.btg.claro.LBS.aprovisionamiento.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class Util{
	
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

}
