package com.stconsulting.common.listener;

import java.util.MissingResourceException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class BatchTareaListener implements ServletContextListener{
	
	private Logger log=Logger.getLogger(this.getClass());

	public void contextInitialized(ServletContextEvent sce){
		try{
			//ServletContext sc=sce.getServletContext();
			BatchTareaAutomathicExecuter bckAuto=new BatchTareaAutomathicExecuter();
			bckAuto.startAutomathicExecuter();
			log.debug("Bachero listener inicializado!!!");
		}
		catch(MissingResourceException mre){
			System.err.println("Exception:" + mre.toString());
		}
	}

	public void contextDestroyed(ServletContextEvent event){
	}
}
