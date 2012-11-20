package pe.com.claro.caef.web.util;

import java.io.Serializable;

public class PropertiesCAEF  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Se manejara con el properties externo
	   private static final String PATH = System.getProperty("claro.properties");
	   private static final String NOMBRE_PROPERTIES = "caef.properties";

	    public static PropertiesConfig rb = null;

	    /**
	     * @return the rb
	     */
	    public static PropertiesConfig getRb() {
	    	
	    	
		if (rb == null) {
			
		    rb = PropertiesConfig.getSingleton(PATH + NOMBRE_PROPERTIES);
		    
		    
		}
		return rb;
	    }
	
		public final static String BASE_URL 		=   getRb().getValor("base.url");
		

		public final static String CORREO_EMISOR 		=   getRb().getValor("correo.emisor");
		public final static String CORREO_CLAVE 		=   getRb().getValor("correo.clave");
		public final static String CORREO_SERVIDOR_IP 		=   getRb().getValor("correo.servidor.ip");
		public final static String CORREO_SERVIDOR_PUERTO 		=   getRb().getValor("correo.servidor.puerto");
		
		public final static String WS_CONSULTA_CLIENTE 		= getRb().getValor("ws.consultaCliente");
		public final static String WS_CONSULTA_RECIBOS 		= getRb().getValor("ws.consultaRecibos");
		public final static String WS_CONSULTA_LLAMADAS 		= getRb().getValor("ws.consultaLlamadas");
		public final static String WS_TRANSACCION_CLIENTES 		= getRb().getValor("ws.transaccionClientes");
		public final static String WS_TRANSACCION_AUTENTICACION = getRb().getValor("ws.transaccionAutenticacion");
		public final static String WS_CONSULTA_AUTENTICACION = getRb().getValor("ws.consultaAutenticacion");
		public final static String WS_CONSULTA_SEGURIDAD = getRb().getValor("ws.consultaSeguridad");
		public final static String WS_TRANSACCION_SEGURIDAD = getRb().getValor("ws.transaccionSeguridad");
		
		public final static String PREGUNTAS_SECRETAS_INGRESO = getRb().getValor("preguntassecretas.ingreso");
		
		public final static String URL_DEFAULT		=   getRb().getValor("base.url.default");
		public final static String URL_INGRESAR		=   getRb().getValor("base.url.ingreso");
		public final static String URL_ADICIONAR		=   getRb().getValor("base.url.adicionar");
		public final static String URL_ELIMINAR		=   getRb().getValor("base.url.eliminar");
		public final static String RUTA_PLANTILLA		=   getRb().getValor("ruta.plantilla");
	
	public static String cuerpoMensaje(String user){
		
		String mensaje = "Estimado(a) Sr.(Srta.) "+user+"<br><br>"+
						 "Adjuntamos la información solicitada desde <b>Mi Claro</b>.<br><br>"+
						 "Sin otro particular, nos despedimos y le recordamos que nuestro Servicio de Atención al Cliente se encuentra disponible las 24 horas<br>"+
						 " marcando gratuitamente al 123 ó 135 para Corporativos desde su celular Claro, o al 0-801-123-23 desde un teléfono fijo a costo de llamada local.<br><br>"+
						 "Cordialmente,<br>"+
						 "<b><font color='red'>Atención al cliente</font></b><br>"+
						 "<b><font color='red'>América Móvil Perú SAC</font></b><br>"+
						 "CLARO, LA RED donde todo es posible. Ingresa a <a href='http://www.claro.com.pe'>www.claro.com.pe</a>";
		
		return mensaje;
	}
}
