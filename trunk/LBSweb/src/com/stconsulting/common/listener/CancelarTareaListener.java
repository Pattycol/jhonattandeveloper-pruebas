package com.stconsulting.common.listener;

import java.util.MissingResourceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class CancelarTareaListener implements ServletContextListener{
	
	private Logger log=Logger.getLogger(this.getClass());

	public void contextInitialized(ServletContextEvent sce){
		try{
			//ServletContext sc=sce.getServletContext();
			CancelarTareaAutomathicExecuter bckAuto=new CancelarTareaAutomathicExecuter();
			bckAuto.startAutomathicExecuter();
			log.debug("Cancelar Tarea listener inicializado!!!");
		}
		catch(MissingResourceException mre){
			System.err.println("Exception:" + mre.toString());
		}
	}

	public void contextDestroyed(ServletContextEvent event){
	}
}
