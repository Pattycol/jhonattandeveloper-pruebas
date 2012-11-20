/*
 * BatchCobroListener.java
 *
 * Created on 10 de junio de 2005, 03:02 PM
 */

package com.stconsulting.common.listener;

import java.util.MissingResourceException;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 
 * @author STCosulting
 */
public class BatchCobroListener implements ServletContextListener{
	
	private Logger log=Logger.getLogger(this.getClass());

	/** Creates a new instance of BatchCobroListener */
	public BatchCobroListener(){
	}

	public void contextDestroyed(javax.servlet.ServletContextEvent servletContextEvent){
	}

	public void contextInitialized(javax.servlet.ServletContextEvent sce){
		try{
			//ServletContext sc=sce.getServletContext();
			BatchCobroAutomathicExecuter bckAuto=new BatchCobroAutomathicExecuter();
			bckAuto.startAutomathicExecuter();
			log.debug("Bachero COBRO listener inicializado!!!");
		}
		catch(MissingResourceException mre){
			System.err.println("Exception:" + mre.toString());
		}
	}

}
