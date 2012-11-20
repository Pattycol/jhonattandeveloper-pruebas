// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 20/09/2006 12:55:52 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ParametroConsulta.java

package com.btg.claro.LBS.ws;

public class ParametroConsulta{

	public ParametroConsulta(){
	}

	public String getMobileOrigen(){
		return mobileOrigen;
	}

	public void setMobileOrigen(String mobileOrigen){
		this.mobileOrigen=mobileOrigen;
	}

	public String[] getListaMobileDestino(){
		return listaMobileDestino;
	}

	public void setListaMobileDestino(String listaMobileDestino[]){
		this.listaMobileDestino=listaMobileDestino;
	}

	public String getCodTipoConsulta(){
		return codTipoConsulta;
	}

	public void setCodTipoConsulta(String codTipoConsulta){
		this.codTipoConsulta=codTipoConsulta;
	}

	public String getCodTipoRespuesta(){
		return codTipoRespuesta;
	}

	public void setCodTipoRespuesta(String codTipoRespuesta){
		this.codTipoRespuesta=codTipoRespuesta;
	}

	private String mobileOrigen;
	private String listaMobileDestino[];
	private String codTipoConsulta;
	private String codTipoRespuesta;
}