package com.stconsulting.lbsweb.seguridad.bean;

import java.io.Serializable;

public class Modulo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=8602805051735717563L;
	
	private String id="";
	private String nombre="";
	private String descripcion="";

	// private ArrayList opcionList = new ArrayList();

	public Modulo(){
	}

	/**
	 * Getter for property id.
	 * 
	 * @return Value of property id.
	 */
	public java.lang.String getId(){
		return id;
	}

	/**
	 * Setter for property id.
	 * 
	 * @param id
	 *            New value of property id.
	 */
	public void setId(java.lang.String id){
		this.id=id;
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
	 * Getter for property nombre.
	 * 
	 * @return Value of property nombre.
	 */
	public java.lang.String getNombre(){
		return nombre;
	}

	/**
	 * Setter for property nombre.
	 * 
	 * @param nombre
	 *            New value of property nombre.
	 */
	public void setNombre(java.lang.String nombre){
		this.nombre=nombre;
	}

}
