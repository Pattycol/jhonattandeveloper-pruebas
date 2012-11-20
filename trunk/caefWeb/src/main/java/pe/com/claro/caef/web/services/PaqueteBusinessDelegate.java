package pe.com.claro.caef.web.services;

import java.net.MalformedURLException;

import org.jfree.util.Log;

import pe.com.claro.caef.web.services.impl.CargarMensajesServiceImpl;
import pe.com.claro.caef.web.services.impl.ComunServiceImpl;
import pe.com.claro.caef.web.services.impl.NuevoClienteServiceImpl;
import pe.com.claro.caef.web.services.impl.UsuarioServiceImpl;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.caef.web.ws.TransaccionAutenticacionWS;
import pe.com.claro.caef.web.ws.impl.ConsultaSeguridadWSImpl;
import pe.com.claro.caef.web.ws.impl.ConsultarClienteWSImpl;
import pe.com.claro.caef.web.ws.impl.TransaccionAutenticacionWSImpl;

public class PaqueteBusinessDelegate {

	private PaqueteBusinessDelegate() {
		// TODO Auto-generated constructor stub
	}

	public static UsuarioService getUsuarioServiceImpl(){
		return new UsuarioServiceImpl();
	}
	public static CargarMensajesService getCargarMensajesServiceImpl(){
		return new CargarMensajesServiceImpl();
	}
	public static NuevoClienteService getNuevoClienteServiceImpl(){
		return new NuevoClienteServiceImpl();
	}
	public static ComunService getComunServiceImpl(){
		return new ComunServiceImpl();
	}
	public static ConsultarClienteWS getConsultarClienteWSImpl(){
		try{
			return new ConsultarClienteWSImpl();
		}catch (Exception e) {
			Log.info(e.toString());
			return null;
		}
		
	}
	public static ConsultaSeguridadWS getConsultaSeguridadWSImpl(){
		try{
			return new ConsultaSeguridadWSImpl();
		}catch (Exception e) {
			Log.info(e.toString());
			return null;
		}
		
	}
	public static TransaccionAutenticacionWS getTransaccionAutenticacionWSImpl(){
		try {
			return new TransaccionAutenticacionWSImpl();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.info(e.toString());
			return null;
		}
	}
	
	
}
