package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.beans.EncryptBean;


public interface EncryptService {
	
	public EncryptBean generatePassword(String password) throws Exception;
	
	// Compara un password encriptado con un password sin encriptar
	public boolean validatePassword(EncryptBean encryptBean, String password) throws Exception;
	
	// Desencripta password
	public String getDecryptPassword(EncryptBean encryptBean) throws Exception; 
}
