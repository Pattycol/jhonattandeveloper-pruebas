package pe.com.claro.caef.web.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {
	
	private static  Properties properties = null;
	private static  InputStream is = null;
	private static  PropertiesConfig pp = null;

	public static PropertiesConfig getSingleton(String prop) {
		pp=null;
		if (pp == null) {
			try {
				pp = new PropertiesConfig();
				properties = new Properties();
				is = new FileInputStream(prop);
				properties.load(is);
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pp;
	}

	public String getValor(String variable) {
		String resultado = null;
		if (variable != null) {
			if(properties.get(variable)!=null){
				resultado = properties.get(variable).toString();
			}
				
			else{
				resultado="Mensaje no cargado";
			}
		}
		return resultado;
	}
}
