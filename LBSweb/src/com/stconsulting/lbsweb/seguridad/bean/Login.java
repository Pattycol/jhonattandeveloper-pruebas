/*
 * Seniority.java
 *
 * Created on July 21, 2004, 10:39 AM
 */

package com.stconsulting.lbsweb.seguridad.bean;

import java.io.Serializable;

public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=-2667241769586570595L;
	
	private String user;
	private String password;
	private String apePat;
	private String apeMat;
	private String nombres;
	private String areaUsuario;

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
	 * Getter for property apeMat.
	 * 
	 * @return Value of property apeMat.
	 */
	public java.lang.String getApeMat(){
		return apeMat;
	}

	/**
	 * Setter for property apeMat.
	 * 
	 * @param apeMat
	 *            New value of property apeMat.
	 */
	public void setApeMat(java.lang.String apeMat){
		this.apeMat=apeMat;
	}

	/**
	 * Getter for property apePat.
	 * 
	 * @return Value of property apePat.
	 */
	public java.lang.String getApePat(){
		return apePat;
	}

	/**
	 * Setter for property apePat.
	 * 
	 * @param apePat
	 *            New value of property apePat.
	 */
	public void setApePat(java.lang.String apePat){
		this.apePat=apePat;
	}

	/**
	 * Getter for property nombres.
	 * 
	 * @return Value of property nombres.
	 */
	public java.lang.String getNombres(){
		return nombres;
	}

	/**
	 * Setter for property nombres.
	 * 
	 * @param nombres
	 *            New value of property nombres.
	 */
	public void setNombres(java.lang.String nombres){
		this.nombres=nombres;
	}

}
