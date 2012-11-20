/*
 * Mobile.java
 *
 * Created on 27 de mayo de 2005, 12:33 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;

/**
 * 
 * @author STconsulting
 */
public class RangoHora implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=-5792440461700633243L;
	
	private int horaInicio;
	private int horaFin;
	private String codHorario;
	private String descHorario;

	/** Creates a new instance of RangoHora */
	public RangoHora(){
	}

	/**
	 * Getter for property horaInicio.
	 * 
	 * @return Value of property horaInicio.
	 */
	public int getHoraInicio(){
		return horaInicio;
	}

	/**
	 * Setter for property horaInicio.
	 * 
	 * @param horaInicio
	 *            New value of property horaInicio.
	 */
	public void setHoraInicio(int horaInicio){
		this.horaInicio=horaInicio;
	}

	/**
	 * Getter for property horaFin.
	 * 
	 * @return Value of property horaFin.
	 */
	public int getHoraFin(){
		return horaFin;
	}

	/**
	 * Setter for property horaFin.
	 * 
	 * @param horaFin
	 *            New value of property horaFin.
	 */
	public void setHoraFin(int horaFin){
		this.horaFin=horaFin;
	}

	/**
	 * Getter for property codHorario.
	 * 
	 * @return Value of property codHorario.
	 */
	public java.lang.String getCodHorario(){
		return codHorario;
	}

	/**
	 * Setter for property codHorario.
	 * 
	 * @param codHorario
	 *            New value of property codHorario.
	 */
	public void setCodHorario(java.lang.String codHorario){
		this.codHorario=codHorario;
	}

	/**
	 * Getter for property descHorario.
	 * 
	 * @return Value of property descHorario.
	 */
	public java.lang.String getDescHorario(){
		return descHorario;
	}

	/**
	 * Setter for property descHorario.
	 * 
	 * @param descHorario
	 *            New value of property descHorario.
	 */
	public void setDescHorario(java.lang.String descHorario){
		this.descHorario=descHorario;
	}

}
