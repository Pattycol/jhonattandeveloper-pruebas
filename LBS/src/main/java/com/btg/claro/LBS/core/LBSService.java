package com.btg.claro.LBS.core;

import java.util.Date;

import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Consulta;

public interface LBSService{
	
	/**
	 * Método que realiza la consulta de un número. Realiza todas la verificaciones necesarias
	 * para determinar si es que el número puede ser consultado. Una vez que todas las validaciones
	 * son correctas, se procede a determinar la ubicación del móvil y se realiza el cobro.
	 * Finalmente se envía la respuesta al solicitante.
	 * 
	 * @param numeroOrigen
	 * @param numeroDestino
	 * @param fecha
	 * @param tipoConsulta el tipo de consulta (SMS o WEB)
	 */
	Consulta realizarConsulta(String numeroOrigen,String numeroDestino,Date fecha,String tipoConsulta);
	
	/**
	 * Método que realiza la consulta de un número. Realiza todas la verificaciones necesarias
	 * para determinar si es que el número puede ser consultado. Una vez que todas las validaciones
	 * son correctas, se procede a determinar la ubicación del móvil y se realiza el cobro opcionalmente.
	 * Finalmente se envía la respuesta al solicitante.
	 * 
	 * @param numeroOrigen
	 * @param numeroDestino
	 * @param fecha
	 * @param cobro indica si se cobra o no
	 * @param tipoConsulta el tipo de consulta (SMS o WEB)
	 */
	Consulta realizarConsulta(String numeroOrigen,String numeroDestino,Date fecha,boolean cobro,String tipoConsulta);
	
	Integer guardarConsulta(Consulta consulta);
	
	
	Celda guardarCelda(String idCelda);

}
