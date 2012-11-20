package pe.com.claro.caef.web.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TripleDESEncryption {
	/*Metodo que genera la llave*/
	 public static DESedeKeySpec generateKey() throws NoSuchAlgorithmException,InvalidKeySpecException {         
		KeyGenerator keygen = KeyGenerator.getInstance("DESede");
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		//keygen.init(192);
		
	    return (DESedeKeySpec) keyfactory.getKeySpec(keygen.generateKey(),DESedeKeySpec.class);
	 }
	
	// Convierte la llave de un arreglo de bytes a una objeto de la clase DESedeKeySpec
	public static DESedeKeySpec loadKey(byte[] rawKey) throws InvalidKeyException {         
		return new DESedeKeySpec(rawKey);
	}
	
	// Encripta el password
	public static String encrypt (String clearText, DESedeKeySpec keySpec) 
		throws	NoSuchAlgorithmException,InvalidKeySpecException, 
				NoSuchPaddingException,InvalidKeyException,
				IllegalBlockSizeException,BadPaddingException {
		     
		String cipherTextB64 = null;
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
		SecretKey key = keyfactory.generateSecret(keySpec);
	
		// Necesitamos un cifrador
		Cipher cipher = Cipher.getInstance("DESede");
		
		// Ciframos el texto en claro
		cipher.init(Cipher.ENCRYPT_MODE, key);                
		byte cipherText[] = cipher.doFinal(clearText.getBytes());        
	
		// Codificamos el texto cifrado en base 64
		BASE64Encoder base64encoder = new BASE64Encoder();
		cipherTextB64 = base64encoder.encode(cipherText);

		// Retornamos el texto cifrado en BASE64
	    return cipherTextB64;
	}
	
	// Desencripta el password encriptado
   public static String decrypt (String cipherTextB64, DESedeKeySpec keySpec) 
	throws	NoSuchAlgorithmException,InvalidKeySpecException, 
			NoSuchPaddingException,InvalidKeyException,
			IllegalBlockSizeException,BadPaddingException,
			IOException{
       String clearText = null;
       SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
       SecretKey key = keyfactory.generateSecret(keySpec);
       // Necesitamos un cifrador
       Cipher cipher = Cipher.getInstance("DESede");
       
       // La clave está codificada en base 64
       BASE64Decoder base64decoder = new BASE64Decoder();
       //cipherTextB64=cipherTextB64+"12";
       byte cipherText[] = base64decoder.decodeBuffer(cipherTextB64);
       //base64decoder.de
       
       // Ciframos el texto en claro
       cipher.init(Cipher.DECRYPT_MODE, key);
       byte bclearText[] = cipher.doFinal(cipherText);        
       clearText = new String(bclearText);

       return clearText;
   }
}
