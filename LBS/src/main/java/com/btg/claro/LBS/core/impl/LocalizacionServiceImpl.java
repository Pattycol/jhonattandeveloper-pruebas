package com.btg.claro.LBS.core.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.core.LocalizacionService;
import com.btg.claro.LBS.sms.EnvioSMS;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.vlr.ClienteVLR;
import com.btg.claro.LBS.vlr.DatosMovil;

@Service("LocalizacionService")
public class LocalizacionServiceImpl implements LocalizacionService{
	
	private static final Logger log=LoggerFactory.getLogger(LocalizacionServiceImpl.class);

	@Autowired
	private EnvioSMS envioSMS;

	@Autowired
	private ClienteVLR clienteVLR;

	public DatosMovil localizarMovil(String numero){
		DatosMovil datos=null;
		/*datos=new DatosMovil();
		datos.setError(Constantes.OK);
		datos.setFechaInterna(new Date());
		datos.setHLR("HLR4");
		datos.setVLR("VLR1");
		datos.setIdCelda("08601");
		datos.setImsid(true);
		datos.setMnr(true);*/
		if(envioSMS.enviarSMSOculto("51997990000",numero)){
			datos=clienteVLR.ubicarMovil(numero);
			if(datos != null){
				log.info("Datos recibidos: idCelda="+datos.getIdCelda()+";mnr="+datos.isMnr()+";imsid="+datos.isImsid()+";fechaInterna="+datos.getFecha()+";error="+datos.getError());
				if(datos.getError()==Constantes.OK){
					if(!datos.isMnr() && !datos.isImsid()){
						datos.setError(Constantes.ERROR_MOVIL_APAGADO);
					}
				}
			}
			else{
				log.warn("Error al procesar VLR");
				datos=new DatosMovil();
				datos.setError(Constantes.ERROR_VLR);
			}
		}
		else{
			log.warn("Error al mandar SMS oculto");
			datos=new DatosMovil();
			datos.setError(Constantes.ERROR_SMS);
		}
		return datos;
	}
	
	public boolean enviarPassword(String numero, String password){
		
		boolean  valor = envioSMS.enviarSMS("125",numero, "Bienvenido a LBS Claro Empresas, su nueva clave para acceder es: "+ password + ".Favor de cambiarla.");
		
		if(valor){
			log.info("Se envio el Password");
		}else{
			log.warn("Error al mandar Password");
		}
		
		return valor;
	}


}
