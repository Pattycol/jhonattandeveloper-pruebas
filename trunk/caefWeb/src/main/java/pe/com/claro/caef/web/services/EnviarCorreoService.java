package pe.com.claro.caef.web.services;

import pe.com.claro.caef.web.util.CorreoDatos;

public interface EnviarCorreoService {

	public void enviarCorreo(CorreoDatos datos)  throws Exception;
	
	public  String contenidoCorreo(String saludoCompleto,StringBuffer cuerpoCorreo);
}
