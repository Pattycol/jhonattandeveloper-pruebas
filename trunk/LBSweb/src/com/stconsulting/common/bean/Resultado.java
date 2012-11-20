/*
 * OptionBean.java
 *
 * Created on 5 de agosto de 2004, 02:37 PM
 */

package com.stconsulting.common.bean;

import java.io.Serializable;

/**
 * 
 * @author Administrador
 */
public class Resultado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=6136268362680655363L;
	
	private String codigo;
	private String mensaje;
	private String descripcion;
	private Object result;

	/**
	 * Getter for property codigo.
	 * 
	 * @return Value of property codigo.
	 */
	public java.lang.String getCodigo(){
		return codigo;
	}

	/**
	 * Setter for property codigo.
	 * 
	 * @param codigo
	 *            New value of property codigo.
	 */
	public void setCodigo(java.lang.String codigo){
		this.codigo=codigo;
	}

	/**
	 * Getter for property mensaje.
	 * 
	 * @return Value of property mensaje.
	 */
	public java.lang.String getMensaje(){
		return mensaje;
	}

	/**
	 * Setter for property mensaje.
	 * 
	 * @param mensaje
	 *            New value of property mensaje.
	 */
	public void setMensaje(java.lang.String mensaje){
		this.mensaje=mensaje;
	}

	/**
	 * Getter for property descripcion.
	 * 
	 * @return Value of property descripcion.
	 */
	public java.lang.String getDescripcion(){
		return descripcion;
	}

	/**
	 * Setter for property descripcion.
	 * 
	 * @param descripcion
	 *            New value of property descripcion.
	 */
	public void setDescripcion(java.lang.String descripcion){
		this.descripcion=descripcion;
	}

	/**
	 * Getter for property result.
	 * 
	 * @return Value of property result.
	 */
	public java.lang.Object getResult(){
		return result;
	}

	/**
	 * Setter for property result.
	 * 
	 * @param result
	 *            New value of property result.
	 */
	public void setResult(java.lang.Object result){
		this.result=result;
	}

}
