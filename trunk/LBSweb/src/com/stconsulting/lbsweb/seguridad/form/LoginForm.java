/*
 * PruebaForm.java
 *
 * Created on September 11, 2004, 12:28 PM
 */

package com.stconsulting.lbsweb.seguridad.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * @author STConsulting
 */
public class LoginForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID=8332898379830425545L;
	
	private String mobile;
	private String user;
	private String password;
	private String nombreUsuario;
	private String areaUsuario;
	private String consultas_web_faltantes;

	/** Creates a new instance of PerfilForm */
	public void reset(ActionMapping actionMapping,HttpServletRequest httpServletRequest){
		super.reset(actionMapping,httpServletRequest);
	}

	/**
	 * Getter for property password.
	 * 
	 * @return Value of property password.
	 */
	public java.lang.String getPassword(){
		return password;
	}

	/**
	 * Setter for property password.
	 * 
	 * @param password
	 *            New value of property password.
	 */
	public void setPassword(java.lang.String password){
		this.password=password;
	}

	/**
	 * Getter for property user.
	 * 
	 * @return Value of property user.
	 */
	public java.lang.String getUser(){
		return user;
	}

	/**
	 * Setter for property user.
	 * 
	 * @param user
	 *            New value of property user.
	 */
	public void setUser(java.lang.String user){
		this.user=user;
	}

	/**
	 * Getter for property areaUsuario.
	 * 
	 * @return Value of property areaUsuario.
	 */
	public java.lang.String getAreaUsuario(){
		return areaUsuario;
	}

	/**
	 * Setter for property areaUsuario.
	 * 
	 * @param areaUsuario
	 *            New value of property areaUsuario.
	 */
	public void setAreaUsuario(java.lang.String areaUsuario){
		this.areaUsuario=areaUsuario;
	}

	/**
	 * Getter for property nombreUsuario.
	 * 
	 * @return Value of property nombreUsuario.
	 */
	public java.lang.String getNombreUsuario(){
		return nombreUsuario;
	}

	/**
	 * Setter for property nombreUsuario.
	 * 
	 * @param nombreUsuario
	 *            New value of property nombreUsuario.
	 */
	public void setNombreUsuario(java.lang.String nombreUsuario){
		this.nombreUsuario=nombreUsuario;
	}

	/**
	 * Getter for property mobile.
	 * 
	 * @return Value of property mobile.
	 */
	public java.lang.String getMobile(){
		return mobile;
	}

	/**
	 * Setter for property mobile.
	 * 
	 * @param mobile
	 *            New value of property mobile.
	 */
	public void setMobile(java.lang.String mobile){
		this.mobile=mobile;
	}

	public String getConsultas_web_faltantes() {
		return consultas_web_faltantes;
	}

	public void setConsultas_web_faltantes(String consultas_web_faltantes) {
		this.consultas_web_faltantes = consultas_web_faltantes;
	}
	
	

}