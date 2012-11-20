package com.btg.claro.LBS.ws;


public class ResultadoConsulta{
	
	private String codResultado;
	
	private String mensaje;
	
	private ResultadoDetalleConsulta listaResultadoDetalle[];

	public ResultadoConsulta(){
	}

	public String getCodResultado(){
		return codResultado;
	}

	public void setCodResultado(String codResultado){
		this.codResultado=codResultado;
	}

	public ResultadoDetalleConsulta[] getListaResultadoDetalle(){
		return listaResultadoDetalle;
	}

	public void setListaResultadoDetalle(ResultadoDetalleConsulta listaResultadoDetalle[]){
		this.listaResultadoDetalle=listaResultadoDetalle;
	}

	public String getMensaje(){
		return mensaje;
	}

	public void setMensaje(String mensaje){
		this.mensaje=mensaje;
	}

	
}