package pe.com.claro.caef.web.util;

import java.io.Serializable;

public class PropertiesCAEFAudit  implements Serializable{

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String PATH = System.getProperty("claro.properties");
	   private static final String NOMBRE_PROPERTIES = "caef_auditoria.properties";

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
		public final static String COD_ESTADO_CUENTA 		=   getRb().getValor("auditoria.estado.cuenta");
		public final static String DES_ESTADO_CUENTA 		=   getRb().getValor("auditoria.estado.cuenta.descripcion");
		
		public final static String COD_DUPLICADO_RECIBOS 		=   getRb().getValor("auditoria.duplicado.recibos");
		public final static String DES_DUPLICADO_RECIBOS 		=   getRb().getValor("auditoria.duplicado.recibos.descripcion");
		
		public final static String COD_TRAFFIC_VIEW 		=   getRb().getValor("auditoria.traffic.view");
		public final static String DES_TRAFFIC_VIEW 		=   getRb().getValor("auditoria.traffic.view.descripcion");
		
		public final static String COD_RECIBO_CORREO_ELECTRONICO 		=   getRb().getValor("auditoria.recibo.correo.electronico");
		public final static String DES_RECIBO_CORREO_ELECTRONICO 		=   getRb().getValor("auditoria.recibo.correo.electronico.descripcion");
		
		public final static String COD_ACTIVACION_PAQUETES		=   getRb().getValor("auditoria.activacion.paquetes");
		public final static String DES_ACTIVACION_PAQUETES 		=   getRb().getValor("auditoria.activacion.paquetes.descripcion");
		
		public final static String COD_DATOS_CLIENTE  =   getRb().getValor("auditoria.datosClientes");
		public final static String DES_DATOS_CLIENTE 		=   getRb().getValor("auditoria.datosClientes.descripcion");
		
		public final static String COD_ACT_CLIENTE 		=   getRb().getValor("auditoria.actCliente");
		public final static String DES_ACT_CLIENTE 		=   getRb().getValor("auditoria.actCliente.descripcion");
		
		public final static String COD_MANUAL_USUARIO 		=   getRb().getValor("auditoria.manual");
		public final static String DES_MANUAL_USUARIO 		=   getRb().getValor("auditoria.manual.descripcion");
		
		public final static String COD_CONSULTA_CONSUMOS 		=   getRb().getValor("auditoria.consulta.consumos");
		public final static String DES_CONSULTA_CONSUMOS  		=   getRb().getValor("auditoria.consulta.consumos.descripcion");
		
		public final static String COD_LLAMADA_FACTURADA 		=   getRb().getValor("auditoria.detalle.llamada.facturada");
		public final static String DES_LLAMADA_FACTURADA 		=   getRb().getValor("auditoria.detalle.llamada.facturada.descripcion");
		
		public final static String COD_LLAMADA__NO_FACTURADA 		=   getRb().getValor("auditoria.detalle.llamada.no.facturada");
		public final static String DES_LLAMADA__NO_FACTURADA 		=   getRb().getValor("auditoria.detalle.llamada.no.facturada.descripcion");
		
		public final static String COD_LLAMADA__ENTRANTE		=   getRb().getValor("auditoria.detalle.llamada.entrantes");
		public final static String DES_LLAMADA__ENTRANTE		=   getRb().getValor("auditoria.detalle.llamada.entrantes.descripcion");
		
		public final static String COD_CONSULTA_RECARGAS		=   getRb().getValor("auditoria.detalle.recargas");
		public final static String DES_CONSULTA_RECARGAS		=   getRb().getValor("auditoria.detalle.recargas.descripcion");
		
		public final static String COD_INSTALACION_DECO		=   getRb().getValor("auditoria.instalacion.deco");
		public final static String DES_INSTALACION_DECO 		=   getRb().getValor("auditoria.instalacion.deco.descripcion");
		
		public final static String COD_DIRECTORIO_ABONADOS		=   getRb().getValor("auditoria.directorio.abonados");
		public final static String DES_DIRECTORIO_ABONADOS 		=   getRb().getValor("auditoria.directorio.abonados.descripcion");
		
		public final static String COD_ENVIO_SMS		=   getRb().getValor("auditoria.envio.sms");
		public final static String DES_ENVIO_SMS 		=   getRb().getValor("auditoria.envio.sms.descripcion");
}
