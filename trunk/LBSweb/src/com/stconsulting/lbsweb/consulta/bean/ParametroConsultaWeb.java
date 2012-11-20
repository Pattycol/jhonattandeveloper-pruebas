/*
 * ParametroConsulta.java
 *
 * Created on 27 de mayo de 2005, 02:14 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author STconsulting
 */
public class ParametroConsultaWeb implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=-8556294964234616795L;

	private String codTipoRespuesta;
	
	private String codTipoConsulta;
	
	private Integer codTarea;
	
	private List<Mobile> listaMobiles;

	// consulta historica
	private String mobileOrigen;
	
	private Date fechaInicio;
	
	private Date fechaFin;

	public String getCodTipoRespuesta(){
		return codTipoRespuesta;
	}

	public void setCodTipoRespuesta(String codTipoRespuesta){
		this.codTipoRespuesta=codTipoRespuesta;
	}

	public String getCodTipoConsulta(){
		return codTipoConsulta;
	}

	public void setCodTipoConsulta(String codTipoConsulta){
		this.codTipoConsulta=codTipoConsulta;
	}

	public Integer getCodTarea(){
		return codTarea;
	}

	public void setCodTarea(Integer codTarea){
		this.codTarea=codTarea;
	}

	public List<Mobile> getListaMobiles(){
		return listaMobiles;
	}

	public void setListaMobiles(List<Mobile> listaMobiles){
		this.listaMobiles=listaMobiles;
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

}
