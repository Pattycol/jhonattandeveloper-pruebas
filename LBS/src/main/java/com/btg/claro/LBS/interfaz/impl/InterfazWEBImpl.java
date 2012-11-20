package com.btg.claro.LBS.interfaz.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.core.LBSService;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Consulta;
import com.btg.claro.LBS.interfaz.InterfazWEB;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.util.Ejecucion;
import com.btg.claro.LBS.ws.ResultadoDetalleConsulta;

@Service("InterfazWEB")
public class InterfazWEBImpl implements InterfazWEB{
	
	private static final Logger log=LoggerFactory.getLogger(InterfazWEBImpl.class);
	
	@Autowired
	private LBSService lbsService;

	public ResultadoDetalleConsulta[] ejecutar(String numeroOrigen,String[] numerosDestino,Date fechaLlegada,Date fechaInicio){
		Ejecucion.agregarHiloWEB(this);
		ResultadoDetalleConsulta[] detalles=new ResultadoDetalleConsulta[numerosDestino.length];
		for(int i=0;i<numerosDestino.length;i++){
			detalles[i]=new ResultadoDetalleConsulta();
			detalles[i].setMobileDestino(numerosDestino[i]);
			try{
				int idConsulta=0;
				boolean cobro=Config.getPropiedadBoolean("dev.cobro");
				Consulta consulta=lbsService.realizarConsulta(numeroOrigen,numerosDestino[i],fechaLlegada,cobro,Constantes.CONSULTA_WEB);
				if(consulta!=null){
					consulta.setFechaInicio(fechaInicio);
					if(consulta.getCodigoError()==Constantes.ERROR_CELDA_INEXISTENTE){
						Celda celda=consulta.getCelda();
						if(celda==null)
							consulta.setCelda(lbsService.guardarCelda(consulta.getIdCelda()));
					}
					idConsulta=lbsService.guardarConsulta(consulta);
				}
				if(idConsulta>0){
					detalles[i].setCodOperacion(idConsulta);
				}
			}
			catch(Exception e){
				log.error("Error raro",e);
			}
			
		}
		Ejecucion.removerHiloWEB(this);
		return detalles;
	}
	
}
