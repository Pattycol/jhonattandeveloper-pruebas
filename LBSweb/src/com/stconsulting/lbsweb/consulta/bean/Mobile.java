/*
 * Mobile.java
 *
 * Created on 27 de mayo de 2005, 12:33 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;

import com.stconsulting.common.util.Constants;

/**
 * 
 * @author STconsulting
 */
public class Mobile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=5508224745632352294L;
	
	private String numero;

	/** Creates a new instance of Mobile */
	public Mobile(){
	}

	public Mobile(String numero){
		this.numero=numero;
	}

	/**
	 * Getter for property numero.
	 * 
	 * @return Value of property numero.
	 */
	public java.lang.String getNumero(){
		return numero;
	}

	/**
	 * Setter for property numero.
	 * 
	 * @param numero
	 *            New value of property numero.
	 */
	public void setNumero(java.lang.String numero){
		this.numero=numero;
	}

	public boolean isValidNumber(){
		boolean res=true;
		if(numero != null && numero.trim().equals("")){
			if(numero.length() < Constants.NUM_DIGITOS_MOBILE || numero.length() > Constants.NUM_DIGITOS_MOBILE + 1)
				res=false;
		}
		return res;
	}
}
