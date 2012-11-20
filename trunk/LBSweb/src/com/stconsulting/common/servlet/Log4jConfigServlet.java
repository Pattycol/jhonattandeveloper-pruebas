/*
 * Log4jConfigServlet.java
 *
 * Created on 18 de enero de 2004, 10:07 PM
 */

package com.stconsulting.common.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author tcdata
 * @version
 */
public class Log4jConfigServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID=5012865167443925082L;

	/**
	 * Initializes the servlet.
	 */
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		initLog4j();
	}

	/**
	 * Destroys the servlet.
	 */
	public void destroy(){

	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void processRequest(HttpServletRequest request,HttpServletResponse response){
	}

	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request,response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request,response);
	}

	/**
	 * Returns a short description of the servlet.
	 */
	public String getServletInfo(){
		return "Short description";
	}

	private void initLog4j(){
		ResourceBundle bundle=null;
		try{
			bundle=ResourceBundle.getBundle(getInitParameter("log4j-resource-bundle"));
			Properties props=new Properties();
			Enumeration<String> keys=bundle.getKeys();
			while(keys.hasMoreElements()){
				String key=keys.nextElement().toString();
				props.setProperty(key,bundle.getString(key));
			}
			PropertyConfigurator.configure(props);
		}
		catch(MissingResourceException mre){
			System.err.println("Log4jConfigServlet - log4j.properties file not found !!");
		}
	}
}
