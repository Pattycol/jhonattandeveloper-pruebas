/*
 * ResultadoConsulta.java
 *
 * Created on 27 de mayo de 2005, 11:54 AM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author STconsulting
 */
public class ResultadoConsultaWeb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=6689938197643091074L;
	/**
	 * codigo de resultado, exito o error, constantes mapeadas en Constants.java
	 */
	private String codResultado;
	private String mensaje;
	private String mobileOrigen;

	/**
	 * lista de ResultadoDetalle
	 */
	private List<ResultadoDetalleWeb> listaResultadoDetalle;

	/** Creates a new instance of ResultadoConsulta */
	public ResultadoConsultaWeb(){
	}

	/**
	 * Getter for property codResultado.
	 * 
	 * @return Value of property codResultado.
	 */
	public java.lang.String getCodResultado(){
		return codResultado;
	}

	/**
	 * Setter for property codResultado.
	 * 
	 * @param codResultado
	 *            New value of property codResultado.
	 */
	public void setCodResultado(java.lang.String codResultado){
		this.codResultado=codResultado;
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
	 * Getter for property mobileOrigen.
	 * 
	 * @return Value of property mobileOrigen.
	 */
	public java.lang.String getMobileOrigen(){
		return mobileOrigen;
	}

	/**
	 * Setter for property mobileOrigen.
	 * 
	 * @param mobileOrigen
	 *            New value of property mobileOrigen.
	 */
	public void setMobileOrigen(java.lang.String mobileOrigen){
		this.mobileOrigen=mobileOrigen;
	}

	public List<ResultadoDetalleWeb> getListaResultadoDetalle(){
		return listaResultadoDetalle;
	}

	public void setListaResultadoDetalle(List<ResultadoDetalleWeb> listaResultadoDetalle){
		this.listaResultadoDetalle=listaResultadoDetalle;
	}

}
