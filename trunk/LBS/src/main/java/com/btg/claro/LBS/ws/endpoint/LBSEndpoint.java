package com.btg.claro.LBS.ws.endpoint;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.core.LocalizacionService;
import com.btg.claro.LBS.interfaz.InterfazWEB;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.util.Ejecucion;
import com.btg.claro.LBS.vlr.DatosMovil;
import com.btg.claro.LBS.ws.ParametroConsulta;
import com.btg.claro.LBS.ws.ResultadoConsulta;
import com.btg.claro.LBS.ws.ResultadoDetalleConsulta;

@Service("LBSConsultaService")
@WebService(name="LBSConsultaService")
public class LBSEndpoint{
	
	private static Logger log=LoggerFactory.getLogger(LBSEndpoint.class);
	
	@Autowired
	private LocalizacionService localizacionService;
	
	@Autowired
	private InterfazWEB interfazWEB;

	@WebMethod
	public ResultadoConsulta consultaLBS(@WebParam(name="parametro") ParametroConsulta parametro){
		Date llegada=new Date();
		ResultadoConsulta resultado=new ResultadoConsulta();
		int maxConsultasWEB=Config.getPropiedadInt("lbs.maxConsultasWEB");
		int maxConsultasSMS=Config.getPropiedadInt("lbs.maxConsultasSMS");
		boolean espera=false;
		int tiempo=0;
		//esperamos mientras el numero de consultas en ejecucion supere los limites establecidos
		if(log.isDebugEnabled()){
			log.debug("Consultas SMS en ejecucion: ["+Ejecucion.getTotalSMS()+"] Consultas WEB en ejecucion: ["+Ejecucion.getTotalWEB()+"]");
			if(Ejecucion.getTotalSMS()>=maxConsultasSMS || Ejecucion.getTotalWEB()>=maxConsultasWEB){
				log.debug("Esperando a que se libere la cola de ejecucion para la consulta WEB("+parametro.getMobileOrigen()+")");
			}
		}
		while(Ejecucion.getTotalSMS()>=maxConsultasSMS || Ejecucion.getTotalWEB()>=maxConsultasWEB){
			espera=true;
			try{
				int random=(int) (Math.random()*40)+80;
				Thread.sleep(random);
				tiempo+=random;
			}
			catch(InterruptedException e){}
		}
		if(espera){
			log.debug("Se espero "+tiempo+" milisegundos a que se desocupe la cola de ejecucion WEB");
		}
		ResultadoDetalleConsulta[] detalles=interfazWEB.ejecutar(parametro.getMobileOrigen(),parametro.getListaMobileDestino(),llegada,new Date());
		resultado.setCodResultado("1");
		resultado.setListaResultadoDetalle(detalles);
		return resultado;
	}
	
	@WebMethod
	public String obtenerUbicacion(@WebParam(name="telefono") String telefono){
		DatosMovil datos=localizacionService.localizarMovil(telefono);
		if(datos!=null){
			if(datos.getError()==Constantes.OK){
				return datos.getIdCelda();
			}
		}
		return null;
	}
	
	@WebMethod
	public void enviarPasswordSMS(@WebParam(name="numeroDestino") String numeroDestino,@WebParam(name="password") String password){
		
		localizacionService.enviarPassword(numeroDestino, password);
		
	}


}
