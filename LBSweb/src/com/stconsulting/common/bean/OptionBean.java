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
public class OptionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=-8993890509102144062L;
	
	private String label;
	private String value;

	/** Creates a new instance of OptionBean */
	public OptionBean(){
	}

	/**
	 * Getter for property label.
	 * 
	 * @return Value of property label.
	 */
	public java.lang.String getLabel(){
		return label;
	}

	/**
	 * Setter for property label.
	 * 
	 * @param label
	 *            New value of property label.
	 */
	public void setLabel(java.lang.String label){
		this.label=label;
	}

	/**
	 * Getter for property value.
	 * 
	 * @return Value of property value.
	 */
	public java.lang.String getValue(){
		return value;
	}

	/**
	 * Setter for property value.
	 * 
	 * @param value
	 *            New value of property value.
	 */
	public void setValue(java.lang.String value){
		this.value=value;
	}

}
