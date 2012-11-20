/*
 * ResultadoDetalle.java
 *
 * Created on 27 de mayo de 2005, 12:02 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author STconsulting
 */
public class ResultadoDetalleWeb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=-7054199511927938807L;

	private String mobileDestino;
	
	private Date fechaConsulta;
	
	private String resultado;
	
	private Integer codOperacion;
	
	private String codCeldaUbicacion;

	private String tipoConsulta;
	
	/** Agregado por Germán Enríquez para la implementación de googlemaps **/
	private Localizacion localizacion;

	/** Creates a new instance of ResultadoDetalle */
	public ResultadoDetalleWeb(){
	}

	public String getMobileDestino(){
		return mobileDestino;
	}

	public void setMobileDestino(String mobileDestino){
		this.mobileDestino=mobileDestino;
	}

	public Date getFechaConsulta(){
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta){
		this.fechaConsulta=fechaConsulta;
	}

	public String getResultado(){
		return resultado;
	}

	public void setResultado(String resultado){
		this.resultado=resultado;
	}

	public Integer getCodOperacion(){
		return codOperacion;
	}

	public void setCodOperacion(Integer codOperacion){
		this.codOperacion=codOperacion;
	}

	public String getCodCeldaUbicacion(){
		return codCeldaUbicacion;
	}

	public void setCodCeldaUbicacion(String codCeldaUbicacion){
		this.codCeldaUbicacion=codCeldaUbicacion;
	}

	public String getTipoConsulta(){
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta){
		this.tipoConsulta=tipoConsulta;
	}

	public Localizacion getLocalizacion(){
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion){
		this.localizacion=localizacion;
	}

}
