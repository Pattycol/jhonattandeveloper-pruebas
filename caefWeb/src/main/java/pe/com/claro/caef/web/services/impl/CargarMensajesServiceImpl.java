package pe.com.claro.caef.web.services.impl;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.PropertiesConfig;

@Service("cargarMensajesService")
public class CargarMensajesServiceImpl implements CargarMensajesService {

	/*public PropertiesConfiguration pc  = null;
	public static CargarMensajesServiceImpl rb = null;
	
	public CargarMensajesServiceImpl()
	{
		try{
			pc = new PropertiesConfiguration("mensajes.properties");
			pc.setReloadingStrategy(new FileChangedReloadingStrategy());
		}catch(ConfigurationException ce)
		{
			ce.printStackTrace();
		}
	}
	
	public static CargarMensajesServiceImpl getRb()
	{
		if(rb==null) {
			
			rb =  new CargarMensajesServiceImpl();
		}
		return rb;
	}*/
	  /* private static final String PATH = System.getProperty("claro.properties");
	   private static final String NOMBRE_PROPERTIES = "cel_configuracion.properties";

	    public static PropertiesConfig rb = null;

	
	    public static PropertiesConfig getRb() {
		if (rb == null) {
		    rb = PropertiesConfig.getSingleton(PATH + NOMBRE_PROPERTIES);
		}
		return rb;
	    }*/

	
	/********************SECCION DE MENSAJES DE LAS PANTALLAS*************************************************
		public final static String LOGIN_ERROR = getRb().pc.getValor("login.msj1");
		public final static String PREGUNTAS_SECRETAS_INGRESO1 = getRb().pc.getValor("ingresopreguntassecretas.msj1");
		public final static String PREGUNTAS_SECRETAS_INGRESO2 = getRb().pc.getValor("ingresopreguntassecretas.msj2");
		public final static String CAMBIO_CLAVE1 = getRb().pc.getValor("cambioclave.msj1");
		public final static String CAMBIO_CLAVE2 = getRb().pc.getValor("cambioclave.msj2");
		public final static String MANUAL_USUARIO1 = getRb().pc.getValor("manualusuario.msj1");
		public final static String MANUAL_USUARIO2 = getRb().pc.getValor("manualusuario.msj2");
		public final static String ESTADO_CONSUMO1 = getRb().pc.getValor("estadoconsumo.msj1");
		public final static String ESTADO_CONSUMO2 = getRb().pc.getValor("estadoconsumo.msj2");
		public final static String ESTADO_CONSUMO3 = getRb().pc.getValor("estadoconsumo.msj3");
		public final static String ESTADO_CONSUMO4 = getRb().pc.getValor("estadoconsumo.msj4");
		public final static String LLAMADA_FACTURADA1 = getRb().pc.getValor("llamadafacturada.msj1");
		public final static String LLAMADA_NOFACTURADA1 = getRb().pc.getValor("llamadanofacturada.msj1");
		public final static String LLAMADA_ENTRANTE1 = getRb().pc.getValor("llamadaentrante.msj1");
		public final static String DETALLE_RECARGAS1 = getRb().pc.getValor("detallerecargas.msj1");
		public final static String DUPLICADO_RECIBOS1 = getRb().pc.getValor("duplicadorecibos.msj1");
		public final static String TRAFFIC_VIEW1 = getRb().pc.getValor("trafficview.msj1");
		public final static String RECIBO_CORREO_ELECTRONICO1 = getRb().pc.getValor("recibocorreoelectronico.msj1");
		public final static String DIRECTORIO_ABONADO1 = getRb().pc.getValor("directorioabonado.msj1");
		public final static String DIRECTORIO_ABONADO2 = getRb().pc.getValor("directorioabonado.msj2");
		public final static String DIRECTORIO_ABONADO3 = getRb().pc.getValor("directorioabonado.msj3");
		public final static String DIRECTORIO_ABONADO4 = getRb().pc.getValor("directorioabonado.msj4");
		public final static String ACTUALIZA_PREGUNTA_SECRETA1 = getRb().pc.getValor("actualizarpreguntassecretas.msj1");
		public final static String ACTUALIZA_PREGUNTA_SECRETA2 = getRb().pc.getValor("actualizarpreguntassecretas.msj2");
		public final static String ACTUALIZA_PREGUNTA_SECRETA3 = getRb().pc.getValor("actualizarpreguntassecretas.msj3");
		public final static String ACTUALIZA_PREGUNTA_SECRETA4 = getRb().pc.getValor("actualizarpreguntassecretas.msj4");
		public final static String ACTUALIZA_PREGUNTA_SECRETA5= getRb().pc.getValor("actualizarpreguntassecretas.msj5");
		public final static String ACTUALIZA_PREGUNTA_SECRETA6= getRb().pc.getValor("actualizarpreguntassecretas.msj6");
		public final static String ACTUALIZA_PREGUNTA_SECRETA7= getRb().pc.getValor("actualizarpreguntassecretas.msj7");
		public final static String ELIMINAR_PREGUNTA_SECRETA1 = getRb().pc.getValor("eliminarpreguntassecretas.msj1");
		public final static String ELIMINAR_PREGUNTA_SECRETA2 = getRb().pc.getValor("eliminarpreguntassecretas.msj2");
		public final static String ELIMINAR_PREGUNTA_SECRETA3 = getRb().pc.getValor("eliminarpreguntassecretas.msj3");
		public final static String ELIMINAR_PREGUNTA_SECRETA4 = getRb().pc.getValor("eliminarpreguntassecretas.msj4");
		public final static String OLVIDO_CLAVE1 = getRb().pc.getValor("olvidoclave.msj1");
		public final static String OLVIDO_CLAVE2 = getRb().pc.getValor("olvidoclave.msj2");
		public final static String OLVIDO_CLAVE3 = getRb().pc.getValor("olvidoclave.msj3");
		public final static String AVISO_LEGAL_Y_ACUERDOS1 = getRb().pc.getValor("avisolegalyacuerdos.msj1");
		public final static String AVISO_LEGAL_Y_ACUERDOS2 = getRb().pc.getValor("avisolegalyacuerdos.msj2");
		public final static String AVISO_LEGAL_Y_ACUERDOS3 = getRb().pc.getValor("avisolegalyacuerdos.msj3");
		public final static String AVISO_LEGAL_Y_ACUERDOS4 = getRb().pc.getValor("avisolegalyacuerdos.msj4");
		public final static String AVISO_LEGAL_Y_ACUERDOS5 = getRb().pc.getValor("avisolegalyacuerdos.msj5");
		public final static String AVISO_LEGAL_Y_ACUERDOS6 = getRb().pc.getValor("avisolegalyacuerdos.msj6");
		public final static String AVISO_LEGAL_Y_ACUERDOS7 = getRb().pc.getValor("avisolegalyacuerdos.msj7");
		public final static String AVISO_LEGAL_Y_ACUERDOS8 = getRb().pc.getValor("avisolegalyacuerdos.msj8");
		public final static String REGISTRAR_OLVIDO_CLAVE1 = getRb().pc.getValor("registrarolvidoclave.msj1");
		public final static String REGISTRAR_OLVIDO_CLAVE2 = getRb().pc.getValor("registrarolvidoclave.msj2");
		public final static String DETALLE_RECARGAS2 = getRb().pc.getValor("detallerecargas.msj2");
		public final static String DETALLE_RECARGAS3 = getRb().pc.getValor("detallerecargas.msj3");
		public final static String DETALLE_RECARGAS4 = getRb().pc.getValor("detallerecargas.msj4");
		public final static String DETALLE_RECARGAS5 = getRb().pc.getValor("detallerecargas.msj5");
		public final static String ACTIVACION_PAQUETE1 = getRb().pc.getValor("activacion.msj1");
		public final static String ACTIVACION_PAQUETE2 = getRb().pc.getValor("activacion.msj2");
		public final static String ACTIVACION_PAQUETE3 = getRb().pc.getValor("activacion.msj3");
		public final static String ACTIVACION_PAQUETE4 = getRb().pc.getValor("activacion.msj4");
		public final static String ACTUALIZA_CLIENTE1 = getRb().pc.getValor("actualizacliente.msj1");
		public final static String ACTUALIZA_CLIENTE2 = getRb().pc.getValor("actualizacliente.msj2");
		public final static String ERROR_DECO_ADICIONAL1 = getRb().pc.getValor("errordecoadicional.msj1");
		public final static String ERROR_DECO_ADICIONAL2 = getRb().pc.getValor("errordecoadicional.msj2");
		public final static String ERROR_DECO_ADICIONAL3 = getRb().pc.getValor("errordecoadicional.msj3");
		public final static String ERROR_DECO_ADICIONAL4 = getRb().pc.getValor("errordecoadicional.msj4");*/
	
	/**********************************************************************************************************/
	
	public MensajesSeguridad obtenerMensajes(
			MensajesSeguridadFilter mensajesSeguridadFilter) {
		
		MensajesSeguridad ms = new MensajesSeguridad();
		
		if(mensajesSeguridadFilter.getTipoPantalla().equals("login"))
		{
			
			ms.setMensaje1(MENSAJES_CONFIG.LOGIN_ERROR);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("cambioClave"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.CAMBIO_CLAVE1);
			ms.setMensaje2(MENSAJES_CONFIG.CAMBIO_CLAVE2);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("ingresoPreguntaSecreta"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.PREGUNTAS_SECRETAS_INGRESO1);
			ms.setMensaje2(MENSAJES_CONFIG.PREGUNTAS_SECRETAS_INGRESO2);
			ms.setMensaje3(MENSAJES_CONFIG.PREGUNTAS_SECRETAS_INGRESO3);
			ms.setMensaje4(MENSAJES_CONFIG.PREGUNTAS_SECRETAS_INGRESO4);
			
			
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("manualUsuario"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.MANUAL_USUARIO1);
			ms.setMensaje2(MENSAJES_CONFIG.MANUAL_USUARIO2);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("estadoConsumo"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ESTADO_CONSUMO1);
			ms.setMensaje2(MENSAJES_CONFIG.ESTADO_CONSUMO2);
			ms.setMensaje3(MENSAJES_CONFIG.ESTADO_CONSUMO3);
			ms.setMensaje4(MENSAJES_CONFIG.ESTADO_CONSUMO4);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("llamadaFacturada"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.LLAMADA_FACTURADA1);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("llamadaNoFacturada"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.LLAMADA_NOFACTURADA1);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("llamadaEntrante"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.LLAMADA_ENTRANTE1);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("detalleRecargas"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.DETALLE_RECARGAS1);
			ms.setMensaje2(MENSAJES_CONFIG.DETALLE_RECARGAS2);
			ms.setMensaje3(MENSAJES_CONFIG.DETALLE_RECARGAS3);
			ms.setMensaje4(MENSAJES_CONFIG.DETALLE_RECARGAS4);
			ms.setMensaje5(MENSAJES_CONFIG.DETALLE_RECARGAS5);
			
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("duplicadoRecibos"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.DUPLICADO_RECIBOS1);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("trafficView"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.TRAFFIC_VIEW1);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("reciboCorreoElectronico"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.RECIBO_CORREO_ELECTRONICO1);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("directorioAbonado"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.DIRECTORIO_ABONADO1);
			ms.setMensaje2(MENSAJES_CONFIG.DIRECTORIO_ABONADO2);
			ms.setMensaje3(MENSAJES_CONFIG.DIRECTORIO_ABONADO3);
			ms.setMensaje4(MENSAJES_CONFIG.DIRECTORIO_ABONADO4);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("actualizaPreguntaSecreta"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA1);
			ms.setMensaje2(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA2);
			ms.setMensaje3(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA3);
			ms.setMensaje4(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA4);
			ms.setMensaje5(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA5);
			ms.setMensaje6(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA6);
			ms.setMensaje7(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA7);
			ms.setMensaje8(MENSAJES_CONFIG.ACTUALIZA_PREGUNTA_SECRETA8);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("eliminarPreguntasSecretas"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ELIMINAR_PREGUNTA_SECRETA1);
			ms.setMensaje2(MENSAJES_CONFIG.ELIMINAR_PREGUNTA_SECRETA2);
			ms.setMensaje3(MENSAJES_CONFIG.ELIMINAR_PREGUNTA_SECRETA3);
			ms.setMensaje4(MENSAJES_CONFIG.ELIMINAR_PREGUNTA_SECRETA4);
			ms.setMensaje5(MENSAJES_CONFIG.ELIMINAR_PREGUNTA_SECRETA5);
			ms.setMensaje6(MENSAJES_CONFIG.ELIMINAR_PREGUNTA_SECRETA6);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("olvidoClave"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.OLVIDO_CLAVE1);
			ms.setMensaje2(MENSAJES_CONFIG.OLVIDO_CLAVE2);
			ms.setMensaje3(MENSAJES_CONFIG.OLVIDO_CLAVE3);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("avisoLegalYAcuerdos"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS1);
			ms.setMensaje2(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS2);
			ms.setMensaje3(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS3);
			ms.setMensaje4(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS4);
			ms.setMensaje5(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS5);
			ms.setMensaje6(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS6);
			ms.setMensaje7(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS7);
			ms.setMensaje8(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS8);
			ms.setMensaje9(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS9);
			ms.setMensaje10(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS10);
			ms.setMensaje11(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS11);
			ms.setMensaje12(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS12);
			ms.setMensaje13(MENSAJES_CONFIG.AVISO_LEGAL_Y_ACUERDOS13);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("registrarOlvidoClave"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.REGISTRAR_OLVIDO_CLAVE1);
			ms.setMensaje2(MENSAJES_CONFIG.REGISTRAR_OLVIDO_CLAVE2);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("activacionPaquetes"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ACTIVACION_PAQUETE1);
			ms.setMensaje2(MENSAJES_CONFIG.ACTIVACION_PAQUETE2);
			ms.setMensaje3(MENSAJES_CONFIG.ACTIVACION_PAQUETE3);
			ms.setMensaje4(MENSAJES_CONFIG.ACTIVACION_PAQUETE4);
		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("actualizaCliente"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ACTUALIZA_CLIENTE1);
			ms.setMensaje2(MENSAJES_CONFIG.ACTUALIZA_CLIENTE2);

		}		
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("errorDecoAdicional"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ERROR_DECO_ADICIONAL1);
			ms.setMensaje2(MENSAJES_CONFIG.ERROR_DECO_ADICIONAL2);
			ms.setMensaje3(MENSAJES_CONFIG.ERROR_DECO_ADICIONAL3);
			ms.setMensaje4(MENSAJES_CONFIG.ERROR_DECO_ADICIONAL4);

		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("error_registra_cliente"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.ERROR_REGISTRO_CLIENTE1);
			ms.setMensaje2(MENSAJES_CONFIG.ERROR_REGISTRO_CLIENTE2);
			ms.setMensaje3(MENSAJES_CONFIG.ERROR_REGISTRO_CLIENTE3);
			ms.setMensaje4(MENSAJES_CONFIG.ERROR_REGISTRO_CLIENTE4);

		}
		else if(mensajesSeguridadFilter.getTipoPantalla().equals("pantallalogueo"))
		{
			ms.setMensaje1(MENSAJES_CONFIG.LOGUEO_INGRESA_MAIL);
			ms.setMensaje2(MENSAJES_CONFIG.LOGUEO_INGRESA_CLAVE);
			ms.setMensaje3(MENSAJES_CONFIG.LOGUEO_OLVIDOCLAVE);
			ms.setMensaje4(MENSAJES_CONFIG.LOGUEO_NUEVO_USUARIO);

		}
		else
		{
			ms.setMensaje1("");
			ms.setMensaje2("");
			ms.setMensaje3("");
			ms.setMensaje4("");
			ms.setMensaje5("");
			ms.setMensaje6("");
		}
		
		return ms;
	}

}
