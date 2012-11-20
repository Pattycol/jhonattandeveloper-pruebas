package com.btg.claro.LBS.interfaz;

import java.util.Date;

import com.btg.claro.LBS.ws.ResultadoDetalleConsulta;

public interface InterfazWEB{
	
	ResultadoDetalleConsulta[] ejecutar(String numeroOrigen,String[] numerosDestino,Date fechaLlegada,Date fechaInicio);

}
