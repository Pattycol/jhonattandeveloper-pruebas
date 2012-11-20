package pe.com.claro.caef.web.util;

import java.io.Serializable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class PropertiesCAEFMail  implements Serializable{

	   private static final String PATH = System.getProperty("claro.properties");
	   private static final String NOMBRE_PROPERTIES = "caef.properties";

	   public static PropertiesConfig rb = null;

	    /**
	     * @return the rb
	     */
	   public static PropertiesConfig getRb() {
	    	
	    	
		if (rb == null) {
			
		    rb = PropertiesConfig.getSingleton(PATH + NOMBRE_PROPERTIES);
		    
		    
		}
		return rb;
	    }	
		public final static String CABECERA_PRIMERA_PARTE 		= getRb().getValor("mail.plantilla.cabecera1");
		public final static String CABECERA_SEGUNDA_PARTE 		= getRb().getValor("mail.plantilla.cabecera2");
		public final static String PIE 		= getRb().getValor("mail.plantilla.pie");	   
}
