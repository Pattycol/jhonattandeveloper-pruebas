/*
 * ResultadoReporteWeb.java
 *
 * Created on 9 de junio de 2005, 12:39 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author STCosulting
 */
public class ResultadoReporteWeb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=4623777933152694952L;
	
	private String codResultado;
	
	private String mensaje;
	
	private String mobileOrigen;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	/**
	 * lista de ResultadoDetalle
	 */
	private List<ResultadoDetalleReporteWeb> listaResultadoDetalle;

	public String getCodResultado(){
		return codResultado;
	}

	public void setCodResultado(String codResultado){
		this.codResultado=codResultado;
	}

	public String getMensaje(){
		return mensaje;
	}

	public void setMensaje(String mensaje){
		this.mensaje=mensaje;
	}

	public String getMobileOrigen(){
		return mobileOrigen;
	}

	public void setMobileOrigen(String mobileOrigen){
		this.mobileOrigen=mobileOrigen;
	}

	public Date getFechaInicio(){
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio=fechaInicio;
	}

	public Date getFechaFin(){
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin){
		this.fechaFin=fechaFin;
	}

	public List<ResultadoDetalleReporteWeb> getListaResultadoDetalle(){
		return listaResultadoDetalle;
	}

	public void setListaResultadoDetalle(List<ResultadoDetalleReporteWeb> listaResultadoDetalle){
		this.listaResultadoDetalle=listaResultadoDetalle;
	}

}
