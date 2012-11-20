package com.stconsulting.common.listener;

import java.util.ResourceBundle;
import java.util.Properties;
import java.util.Enumeration;
import java.util.MissingResourceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.PropertyConfigurator;

public class ContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event){
		// Configuring log4j
		initLog4j();
	}

	public void contextDestroyed(ServletContextEvent event){
	}

	private void initLog4j(){
		ResourceBundle bundle=null;
		try{
			bundle=ResourceBundle.getBundle("com.stconsulting.resource.log4j");
			Properties props=new Properties();
			Enumeration<String> keys=bundle.getKeys();
			while(keys.hasMoreElements()){
				String key=keys.nextElement().toString();
				props.setProperty(key,bundle.getString(key));
			}
			PropertyConfigurator.configure(props);
		}
		catch(MissingResourceException mre){
			System.err.println("log4j.properties file not found !!");
		}
	}
}
