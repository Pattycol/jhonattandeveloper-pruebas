/*
 * Option.java
 *
 * Created on June 4, 2004, 6:13 PM
 */

package com.stconsulting.common.bean;

import java.io.Serializable;

/**
 * 
 * @author Administrator
 */
public class Option implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=6496031324362657836L;

	private String codigo;

	private String descripcion;

	private String alias;

	/** Creates a new instance of Option */
	public Option(){
	}

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
	 * Getter for property alias.
	 * 
	 * @return Value of property alias.
	 */
	public java.lang.String getAlias(){
		return alias;
	}

	/**
	 * Setter for property alias.
	 * 
	 * @param alias
	 *            New value of property alias.
	 */
	public void setAlias(java.lang.String alias){
		this.alias=alias;
	}

}
