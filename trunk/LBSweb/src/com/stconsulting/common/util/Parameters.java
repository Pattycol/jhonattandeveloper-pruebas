/*
 * Parameter.java
 *
 * Created on 27 de mayo de 2005, 05:09 PM
 */

package com.stconsulting.common.util;

import java.util.HashMap;
import java.util.Map;

import com.stconsulting.common.util.Helper;
import com.stconsulting.common.util.Constants;

/**
 * 
 * @author STconsulting
 */
public class Parameters{

	private static Map<String,String> parametros=null;

	/** Creates a new instance of Parameter */
	public Parameters(){
		// metodo de carga temporal
		if(parametros == null)
			parametros=cargaParametros();
	}

	public static String getParameter(String key){
		if(parametros==null)
			parametros=cargaParametros();
		return parametros.get(key);
	}

	private static Map<String,String> cargaParametros(){
		Map<String,String> params=new HashMap<String,String>();
		params.put(Constants.KEY_COD_AREA_ADMIN,Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_COD_AREA_ADMIN));
		params.put(Constants.KEY_COD_EMPRESA,Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_COD_EMPRESA));
		params.put(Constants.KEY_MONTO_CONSULTA,Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_MONTO_CONSULTA));

		return params;
	}
}
