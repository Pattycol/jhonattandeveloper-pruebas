package pe.com.claro.caef.web.util;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.jfree.util.Log;

public class MetodosAuditoria implements Serializable{

	public static MetodosAuditoria rb = null;
	public PropertiesConfiguration pc  = null;
	private static String PATH=System.getProperty("claro.properties");
	private static String FILE="caef.properties";
	
	public MetodosAuditoria()
	{
		try{
			pc = new PropertiesConfiguration(PATH+FILE);
			pc.setReloadingStrategy(new FileChangedReloadingStrategy());
		}catch(ConfigurationException ce)
		{
			ce.printStackTrace();
		}
	}
	
	public static MetodosAuditoria getRb() {
		if(rb==null) {
			
			rb =  new MetodosAuditoria();
		}
		return rb;
	}
	
	public final static String IDAPLICACION	= getRb().pc.getString("iDAplicacion");
	
	public String IdTransaccion()
    {
        String strValue = "";
        Integer objAleatorio;
        try
        {
            for (int i = 0; i < 7; i++)
            {
                objAleatorio = (int)(Math.random()*10+1);
                strValue = strValue + objAleatorio.toString();
            }
        }
        catch (Exception ex)
        {
        	Log.info(ex.toString());
            return "";
        }
        finally
        {
            objAleatorio = null;
        }
        return IDAPLICACION + strValue;
    }
	
	public String getIP()
    {
		String ip = null; // IP del cliente
		try{
		ip = java.net.InetAddress.getLocalHost().getHostAddress().toString();}
		catch(IOException e)
		{
			Log.info(e.toString());
		}
		return ip;
    }
	
}
