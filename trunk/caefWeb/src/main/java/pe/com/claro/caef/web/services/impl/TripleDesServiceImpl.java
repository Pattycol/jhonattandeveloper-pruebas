package pe.com.claro.caef.web.services.impl;

import javax.crypto.spec.DESedeKeySpec;

import org.springframework.stereotype.Service;


import pe.com.claro.caef.web.beans.EncryptBean;
import pe.com.claro.caef.web.services.EncryptService;
import pe.com.claro.caef.web.util.TripleDESEncryption;

@Service("encryptService")
public class TripleDesServiceImpl implements EncryptService{
	




	// Genera un nuevo password encriptado
	public EncryptBean generatePassword(String password) 
			throws Exception {
		try {
			DESedeKeySpec keySpec = TripleDESEncryption.generateKey();
			return new EncryptBean(TripleDESEncryption.encrypt(password, keySpec),keySpec.getKey()) ;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	// Compara un password encriptado con un password sin encriptar
	public boolean validatePassword(EncryptBean encryptBean, String password) 
			throws Exception {
		
		try{
			
			DESedeKeySpec keySpec = TripleDESEncryption.loadKey(encryptBean.getTdes_key()); // Convierte la llave de un arreglo de bytes a una objeto de la clase DESedeKeySpec
			
			String decript_pass = TripleDESEncryption.decrypt(encryptBean.getEncrypy_pass(), keySpec);
			// Desencripta el password encriptado
			if (decript_pass.equals(password)) // Compara passwords
				return true;
			else 
				return false;
			
		} catch (Exception e) {
			throw new Exception(e);
		}

	}
	
	// Desencripta password
	public String getDecryptPassword(EncryptBean encryptBean)
			throws Exception {
		try{
			return TripleDESEncryption.decrypt(encryptBean.getEncrypy_pass(), TripleDESEncryption.loadKey(encryptBean.getTdes_key()));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
