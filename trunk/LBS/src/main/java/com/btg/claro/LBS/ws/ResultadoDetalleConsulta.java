package com.btg.claro.LBS.ws;

public class ResultadoDetalleConsulta{
	
	private String mobileDestino;
	
	private String resultado;
	
	private Integer codOperacion;
	
	private Integer codCeldaUbicacion;

	public ResultadoDetalleConsulta(){
	}

	public Integer getCodCeldaUbicacion(){
		return codCeldaUbicacion;
	}

	public void setCodCeldaUbicacion(Integer codCeldaUbicacion){
		this.codCeldaUbicacion=codCeldaUbicacion;
	}

	public Integer getCodOperacion(){
		return codOperacion;
	}

	public void setCodOperacion(Integer codOperacion){
		this.codOperacion=codOperacion;
	}

	public String getMobileDestino(){
		return mobileDestino;
	}

	public void setMobileDestino(String mobileDestino){
		this.mobileDestino=mobileDestino;
	}

	public String getResultado(){
		return resultado;
	}

	public void setResultado(String resultado){
		this.resultado=resultado;
	}
	
}