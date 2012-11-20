package com.btg.claro.LBS.core;

import com.btg.claro.LBS.vlr.DatosMovil;

public interface LocalizacionService{
	
	/**
	 * Trata de localizar a un movil a partir del número
	 * telefónico. Envía un SMS oculto para actualizar la 
	 * ubicación del movil y luego consulta al VLR que pertenece
	 * para obtener su celda
	 * 
	 * @param numero
	 * @return el identificador de la celda donde se encuentra el movil o
	 * un código de error
	 */
	DatosMovil localizarMovil(String numero);
	
	boolean enviarPassword(String numero, String password);


}
