package com.stconsulting.common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * User: tcdata Date: 26/09/2003 Time: 12:05:39 PM
 */
public class SessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent event){
	}

	public void sessionDestroyed(HttpSessionEvent event){
		// deleteTemporalFolder(event);
	}

	/*
	 * private void deleteTemporalFolder(HttpSessionEvent event) { Logger log =
	 * Logger.getLogger(this.getClass()); HttpSession session =
	 * event.getSession();
	 * 
	 * log.info("Session with id " + session.getId() + " destroyed.");
	 * log.info("Deleting temporal folder.");
	 * 
	 * String tempPath = session.getServletContext().getRealPath("/temp") +
	 * File.separator + session.getId();
	 * 
	 * try { File tempFolder = new File(tempPath); } catch(Exception e) {
	 * log.error(e.getMessage()); } }
	 */
}
