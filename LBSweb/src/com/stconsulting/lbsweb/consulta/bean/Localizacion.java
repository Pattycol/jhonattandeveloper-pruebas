/*
 * Celda.java
 *
 * Created on May 31, 2005, 4:14 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author STCosulting
 */
public class Localizacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=4579385629140876073L;
	
	private Integer codLocalizacion;
	
	private String mobileOrigen;
	
	private String mobileDestino;
	
	private String codCelda;
	
	private String codLog;
	
	private String mensaje;
	
	private String direccion;
	
	private String referencia;
	
	private Float ejeX;
	
	private Float ejeY;
	
	private String latitud;
	
	private String longitud;
	
	private String tipoConsulta;
	
	private Date fecha;

	public Integer getCodLocalizacion(){
		return codLocalizacion;
	}

	public void setCodLocalizacion(Integer codLocalizacion){
		this.codLocalizacion=codLocalizacion;
	}

	public String getMobileOrigen(){
		return mobileOrigen;
	}

	public void setMobileOrigen(String mobileOrigen){
		this.mobileOrigen=mobileOrigen;
	}

	public String getMobileDestino(){
		return mobileDestino;
	}

	public void setMobileDestino(String mobileDestino){
		this.mobileDestino=mobileDestino;
	}

	public String getCodCelda(){
		return codCelda;
	}

	public void setCodCelda(String codCelda){
		this.codCelda=codCelda;
	}

	public String getCodLog(){
		return codLog;
	}

	public void setCodLog(String codLog){
		this.codLog=codLog;
	}

	public String getMensaje(){
		return mensaje;
	}

	public void setMensaje(String mensaje){
		this.mensaje=mensaje;
	}

	public String getDireccion(){
		return direccion;
	}

	public void setDireccion(String direccion){
		this.direccion=direccion;
	}

	public String getReferencia(){
		return referencia;
	}

	public void setReferencia(String referencia){
		this.referencia=referencia;
	}

	public Float getEjeX(){
		return ejeX;
	}

	public void setEjeX(Float ejeX){
		this.ejeX=ejeX;
	}

	public Float getEjeY(){
		return ejeY;
	}

	public void setEjeY(Float ejeY){
		this.ejeY=ejeY;
	}

	public String getLatitud(){
		return latitud;
	}

	public void setLatitud(String latitud){
		this.latitud=latitud;
	}

	public String getLongitud(){
		return longitud;
	}

	public void setLongitud(String longitud){
		this.longitud=longitud;
	}

	public String getTipoConsulta(){
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta){
		this.tipoConsulta=tipoConsulta;
	}

	public Date getFecha(){
		return fecha;
	}

	public void setFecha(Date fecha){
		this.fecha=fecha;
	}

}
