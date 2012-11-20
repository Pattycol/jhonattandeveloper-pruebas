package com.hildebrando.claro.WSLBS.ws;

import java.util.Date;


public class ResultadoConsulta{
	
	private String mensaje;
	
	private Date fecha;
	
	

	
	public ResultadoConsulta(String mensaje, Date fecha) {
		super();
		this.mensaje = mensaje;
		this.fecha = fecha;
	}



	public String getMensaje() {
		return mensaje;
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	
}