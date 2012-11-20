/*
 * Constants.java
 *
 * Created on July 15, 2003, 2:23 PM
 */ 

package com.stconsulting.common.util;

/**
 *
 * @author  STConsulting
 */
import java.util.ResourceBundle;

public class Constants {

    //Consultas realizadas
	public static final String CONSULTAS_WEB_FALTANTES = "ConsultasWebFaltantes";
	
	
	//BD SOURCE
    public static final String BD_LBS="bdLBS";
    
    public static final String LOGIN_KEY = "LOGIN_KEY";
    public static final String USER_KEY = "USER_KEY";
    public static final String APPLICATION_BUNDLE = "com.stconsulting.resource.application";
    public static final String CONNECTION_BUNDLE = "com.stconsulting.resource.connection";
    public static final String WEBSERVICE_BUNDLE = "com.stconsulting.resource.webservice";
    public static final String CDR_BUNDLE = "com.stconsulting.resource.cdr";
    public static final String GPS_BUNDLE = "com.stconsulting.resource.gps";
    //Para envio de parametros a la sesion
    public static final String USUARIO_LOGUEADO = "UsuarioLogueado";
    public static final String QUERY_SYSTEM_ARQ = "QuerySystemArq";
    public static final String MENSAJE_USUARIO = "MensajeUsuario";
    public static final String ACCION_CERRAR = "cerrar";
    public static final String ACCION_CAMBIAR_PASSWORD = "CambiarPassword";
    public static final String MOBILES_SELECCIONADOS = "MobilesSelecionados";
    
    public static final String SI = "SI";
    public static final String NO = "NO";
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    
    public static final byte NUM_DIGITOS_MOBILE = 9; 
    public static final long NUM_MILIS_DIA = 24*60*60*1000;
    public static final long AJUSTE_HORARIO = 5*60*60*1000;
    public static final long DELAY = 40000L;
    public static final String PATH_TIMESTAMP_CDR = "yyyyMMddHHmmss";
    //
    //Para consulta al Billing
    public static final int COD_BILLING_PREPAGO_TIENE_SALDO = 0;
    public static final int COD_BILLING_PREPAGO_NO_TIENE_SALDO = 1;
    public static final int COD_BILLING_POSTPAGO_TIENE_PERMISO = 2;
    public static final int COD_BILLING_POSTPAGO_NO_TIENE_PERMISO = 3;
    public static final int COD_BILLING_POSTPAGO_NO_TIENE_PERMISO_2 = 4;
    
    public static final String COD_RESULTADO_BILLING_TIENE_SALDO = "0";
    public static final String COD_RESULTADO_BILLING_NO_TIENE_SALDO = "1";
    //Para jsp
    public static final String COD_SELECCIONE="-1";
    public static final String DESC_SELECCIONE="Seleccione";
    
    public static final String COD_TIPO_RPTA_PTOREF = "1";
    public static final String DESC_TIPO_RPTA_PTOREF = "Punto de Referencia";
    public static final String COD_TIPO_RPTA_LATLON = "2";
    public static final String DESC_TIPO_RPTA_LATLON = "Latitud-Longitud";
    public static final String COD_TIPO_RPTA_UTM = "3";
    public static final String DESC_TIPO_RPTA_UTM = "UTM";
    
    //Para validaci�n de Usuario en el logueo
    public static final String COD_USUARIO_VALIDO = "0";
    public static final String COD_USUARIO_INVALIDO = "1";
    public static final String COD_USUARIO_PASSWORD_VENCIDO = "2";
    public static final String COD_USUARIO_NO_ADMIN = "3";
    public static final String COD_USUARIO_INACTIVO = "4";
    public static final String COD_USUARIO_NO_APROVISIONADO = "5";
    public static final String COD_USUARIO_CLAVE_RECUPERADA = "A";
    public static final String COD_PERFIL_ADMIN = "jefe";
    public static final String COD_ESTADO_USUARIO_ACTIVO = "A";
    
    public static final String KEY_COD_AREA_ADMIN = "COD_AREA_ADMIN";    
    public static final String KEY_MONTO_CONSULTA = "MONTO_CONSULTA"; 
    public static final String KEY_COD_EMPRESA = "COD_EMPRESA";
    
    //Para validacion de numero
    public static final String COD_NUMERO_VALIDO = "V";
    public static final String COD_NUMERO_INVALIDO = "I";
    
    //Para consulta al LBS
    public static final String COD_TIPO_CONSULTA_WEB = "3";
    public static final String COD_TIPO_CONSULTA_TAREA = "4";
    public static final String DESC_TIPO_CONSULTA_WEB = "WEB";
    public static final String DESC_TIPO_CONSULTA_TAREA = "TAREA";
    public static final String DESC_TIPO_CONSULTA_WEB_HIST = "W";
    public static final String DESC_TIPO_CONSULTA_TAREA_HIST = "WT";
    
    //Para resultado de consulta al LBS
    public static final String COD_RESULTADO_CONSULTA_OK = "0";
    public static final String COD_ERROR_CONSULTA_INTERNO = "1";
    public static final String COD_ERROR_CONSULTA_NUMERO_INVALIDO = "2";
    public static final String COD_ERROR_CONSULTA_NO_TIENE_SALDO = "3";
    public static final String COD_ERROR_CONSULTA_COBRO_INVALIDO = "4";
    public static final String COD_CELDA_LOCALIZACION_INVALIDO_1 = "00000";
    //Para cobro de consulta
    public static final String COD_ESTADO_COBRO_PENDIENTE = "P";
    public static final String COD_ESTADO_COBRO_CERRADO = "C";
    
    //properties
    public static final String PROPERTY_COD_EMPRESA = "COD_EMPRESA";
    public static final String PROPERTY_NUM_MOBILES = "NUM_MOBILES";
    public static final String PROPERTY_COD_AREA_ADMIN = "COD_AREA_ADMIN";
    public static final String PROPERTY_MONTO_CONSULTA = "MONTO_CONSULTA";
    public static final String PROPERTY_URL_WS_BILLING = "url.ws.billing";
    public static final String PROPERTY_NAME_WS_BILLING = "name.ws.billing";
    public static final String PROPERTY_CANAL_WS_BILLING = "canal.ws.billing";
    public static final String PROPERTY_USER_WS_BILLING = "user.ws.billing";
    public static final String PROPERTY_PSW_WS_BILLING = "psw.ws.billing";
    public static final String PROPERTY_URL_WS_LBS = "url.ws.lbs";
    public static final String PROPERTY_NAME_WS_LBS = "name.ws.lbs";
    public static final String PROPERTY_NUM_MAX_DIAS_HISTORICO="NUM_MAX_DIAS_HISTORICO";
    public static final String PROPERTY_RUTA_HOST_MAPA = "RUTA_HOST_MAPA";
    public static final String PROPERTY_TIEMPO_RECARGA_GRAFICO="TIEMPO_RECARGA_GRAFICO";
    
    public static final String PROPERTY_CDR_CALL_DURATION = "double.call.duration";
    public static final String PROPERTY_CDR_CALL_REFERENCE = "string.call.reference";
    public static final String PROPERTY_CDR_CALLED_IMSI = "string.called.imsi";
    public static final String PROPERTY_CDR_CALLING_IMEI = "string.called.imei";
    public static final String PROPERTY_CDR_CALLING_IMSI = "string.calling.imsi";
    public static final String PROPERTY_CDR_DATA_VOLUME_DOWN = "double.data.volume.down";            
    public static final String PROPERTY_CDR_DATA_VOLUME_UP = "double.data.volume.up";
    public static final String PROPERTY_CDR_EVENT_VOLUME = "double.event.volume";
    public static final String PROPERTY_CDR_EXCHANGE_ID = "string.exchange.id";
    public static final String PROPERTY_CDR_FILLER_1 = "string.filler1";
    public static final String PROPERTY_CDR_FILLER_2 = "string.filler2";
    public static final String PROPERTY_CDR_FILLER_3 = "string.filler3";
    public static final String PROPERTY_CDR_FILLER_4 = "string.filler4";
    public static final String PROPERTY_CDR_FILLER_5 = "string.filler5";
    public static final String PROPERTY_CDR_GSMPI = "string.gsmpi";
    public static final String PROPERTY_CDR_HPLMN = "string.hplmn";
    public static final String PROPERTY_CDR_LAC = "string.lac";
    public static final String PROPERTY_CDR_MESSAGE_VOLUME = "double.message.volume";
    public static final String PROPERTY_CDR_QUALITY_SERVICE = "string.quality.service";
    public static final String PROPERTY_CDR_ROUTING_CATEGORY = "string.routing.category";
    public static final String PROPERTY_CDR_SEQUENCE = "string.sequence";
    public static final String PROPERTY_CDR_SERVICE = "string.service";
    public static final String PROPERTY_CDR_TYPE = "string.type";
    public static final String PROPERTY_CDR_NOMBRE_ARCHIVO = "archivo.cdr";
    public static final String PROPERTY_CDR_NOMBRE_ARCHIVO_FORMATO = "archivo.cdr.formato";
    public static final String PROPERTY_CDR_EXTENSION_ARCHIVO = "extension.cdr";
    public static final String PROPERTY_CDR_HOST_FTP = "host.ftp.cdr";
    public static final String PROPERTY_CDR_USUARIO_FTP = "usuario.ftp.cdr";
    public static final String PROPERTY_CDR_PSW_FTP = "password.ftp.cdr";
    public static final String PROPERTY_CDR_PUERTO_FTP = "puerto.ftp.cdr";
    public static final String PROPERTY_CDR_SUBDIR_FTP = "subdirectorio.ftp.cdr";
    public static final String PROPERTY_CDR_FORMATO_INTERNACIONAL = "formato.internacional";
    public static final String PROPERTY_CDR_SUBDIRBACKUP_FTP = "subdirectorio.ftp.backup";
    public static final String PROPERTY_CDR_EXTENSION_ELIMINAR = "extension.cdr.eliminar";
    
    public static final String PROPERTY_GPS_HOST_FTP = "host.ftp.gps";
    public static final String PROPERTY_GPS_USUARIO_FTP = "usuario.ftp.gps";
    public static final String PROPERTY_GPS_PSW_FTP = "password.ftp.gps";
    public static final String PROPERTY_GPS_PUERTO_FTP = "puerto.ftp.gps";
    public static final String PROPERTY_GPS_ARCHIVO_FTP = "archivo.ftp.gps";
    public static final String PROPERTY_GPS_ARCHIVO_LOCAL_FTP = "archivo.local.ftp.gps";
    
    //SMS GPS
    public static final String GPS_SMSC_IP = "smsc.ip";
    public static final String GPS_SMSC_SOURCE = "smsc.source";
    public static final String GPS_SMSC_PORT = "smsc.port";
    public static final String GPS_SMSC_DESTINY = "smsc.destiny";
    public static final String GPS_SMSC_COMMAND = "smsc.command";
    public static final String GPS_SMSC_DELAY = "smsc.delay";
    
    //MaxCdrs
    public static final int NUM_CONSECUTIVO_MAX_CDR = 99999;
    
    //Mensajes
    public static final String MENSAJE_NO_TIENE_SALDO="El n�mero no tiene saldo suficiente";
    public static final String MENSAJE_COBRO_INVALIDO="Hubo un error al generar el cobro";
    public static final String MENSAJE_ERROR_INTERNO="Hubo un error interno.Comuniquese con el Administrador";
    public static final String MENSAJE_DIFERENCIA_DIAS_HISTORICO="El numero m�ximo de dias para la consulta es "+Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MAX_DIAS_HISTORICO);
      
    public static final String SESSION_CONS_FECHA_INICIO="SESSION_CONS_FECHA_INICIO";
    public static final String SESSION_CONS_FECHA_FIN="SESSION_CONS_FECHA_FIN";
    public static final String REQUEST_ACTIVA_GRAFICO="REQUEST_ACTIVA_GRAFICO";
    public static final String REQUEST_ACTIVA_REPORTE="REQUEST_ACTIVA_REPORTE";
    public static final String REQUEST_GRAFICO_AUTOMATICO="REQUEST_GRAFICO_AUTOMATICO";
        
    public static final String FORMATO_FECHA_HORA_MOSTRAR = "dd/MM/yyyy hh:mm:ss a";
    public static final String FORMATO_FECHA_HORA_24 = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMATO_HORA_MOSTRAR = "hh:mm a";
    public static final String FORMATO_FECHA_HORA_CDR = "yyyyMMddHHmmss";
    public static final String PROPERTY_TIEMPO_COBRO="TIEMPO_COBRO";
    public static final long DELAY_COBRO=0;
    public static final long TIEMPO_REPETICION_COBRO=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_COBRO))*60*1000;
    //EMD
    //Estados de las Tareas
    public static final String COD_ESTADO_ACTIVO="A";
    public static final String DESC_ESTADO_ACTIVO="Activo";
    public static final String COD_ESTADO_INACTIVO="I";
    public static final String DESC_ESTADO_INACTIVO="Inactivo";
    public static final String COD_ESTADO_CERRADO="C";
    public static final String DESC_ESTADO_CERRADO="Cerrado";
   
    
    //Error General
    public static final String NO_OK="NO_OK";
    
    //Combos Periodo
    public static final String COD_PERIODO_SEMANAL="S";
    public static final String DESC_PERIODO_SEMANAL="Semanal";
    public static final String COD_PERIODO_DIARIO="D";
    public static final String DESC_PERIODO_DIARIO="Diario";
    
    //Dias de la Semana
    //Separacion de 8 horas del HORARIO a correr
    public static final int RANGO_HORARIO=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RANGO_SEPARACION_HORAS));
    public static final String COD_LUNES="2";
    public static final String COD_MARTES="3";
    public static final String COD_MIERCOLES="4";
    public static final String COD_JUEVES="5";
    public static final String COD_VIERNES="6";
    public static final String COD_SABADO="7";
    public static final String COD_DOMINGO="1";
    
    //Combo Horario
    //Combos Periodo
    public static final int NUM_HORAS_DIA=24;
    public static final String COD_HORARIO_DIA_COMPLETO="0";
    public static final String DESC_HORARIO_DIA_COMPLETO="Todo el dia";
    
     //FORMATOS
    public static final String FORMATO_FECHA_MOSTRAR = "dd/MM/yyyy";
    
    public static final String FORMATO_ENTERO_MOSTRAR = "###,###";
    public static final String FORMATO_DECIMAL_MOSTRAR = "###,###.##";
    
    public static final String COD_TODOS="-1";
    public static final String DESC_TODOS="Todos";
    
    //Properties
     public static final String PROPERTY_TIEMPO_CONSULTA="TIEMPO_CONSULTA";
     public static final String PROPERTY_INTERVALO_TIEMPO_CONSULTA="INTERVALO_TIEMPO_CONSULTA";
     public static final String PROPERTY_NUM_MAX_DIAS_TAREA="NUM_MAX_DIAS_TAREA";
     public static final String PROPERTY_RANGO_SEPARACION_HORAS="RANGO_SEPARACION_HORAS";
     public static final String PROPERTY_NUM_DIAS_VENCIMIENTO_PASSWORD="NUM_DIAS_VENCIMIENTO_PASSWORD";
     
    
     //Request and Session
     public static final String SESSION_NUEVA_TAREA="SESSION_NUEVA_TAREA";
     public static final String SESSION_CANT_MOBILES_SEL="SESSION_CANT_MOBILES_SEL";
     public static final String SESSION_FECHA_INICIO="SESSION_FECHA_INICIO";
     public static final String SESSION_FECHA_FIN="SESSION_FECHA_FIN";
    
     public static final String NUEVA_TAREA="NUEVA_TAREA";
     public static final String MODIFICAR_TAREA="MODIFICAR_TAREA";
     public static final String CONFIRMAR_TAREA="CONFIRMAR_TAREA";
      
    public static final String MENSAJE_CANTIDAD_CONSULTA="MENSAJE_CANTIDAD_CONSULTAS";
     public static final String MENSAJE_MOBILES_REPETIDOS="Existen mobiles repetidos";
     public static final String COD_DIAS_CORRECTO="-1";
     public static final String COD_DIAS_INCORRECTO="-2";
     public static final String COD_TAREA_VALIDA="0";
     public static final String COD_TAREA_INVALIDA="-1";
     public static final String COD_HORA_INVALIDA="-1";
     
     
     /*********BATCHERO TAREAS**********/
     //Se repetira la consulta cada cierto tiempo 
     public static final long TIEMPO_REPETICION_TAREA=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_CONSULTA))*60*1000;
     public static final long DELAY_TAREA=0;
     public static final long ONCE_PER_DAY = 1000*60*60*24;

     
     //Estados para la Tarea
     public static final String COD_ESTADO_EN_PROCESO="P";
     public static final String COD_ESTADO_ATENDIDA="T";
     public static final String COD_ESTADO_EN_ESPERA="T";
     
     //Descripcion de Mensajes
      public static final String MENSAJE_DIFERENCIA_DIAS_TAREA="El numero maximo de dias para la tarea es "+Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MAX_DIAS_TAREA);      
      public static final String MENSAJE_HORA_NUEVA_TAREA="El rango de horas seleccionados es incorrecto, menor a la hora actual";
     
      public static final String COD_SERVICIO_LBS = "1";
    //FIN EMD
      
      //Constantes para servicios web
      public static final String COD_RES_WS_CONSULTA_OK = "0";
      //error interno, comuniquese con el adminsitrador
      public static final String COD_RES_WS_CONSULTA_ERROR_INTERNO = "1";      
      //parametros no validos
      public static final String COD_RES_WS_CONSULTA_ERROR_PARAMETRO = "2";
      //usuario y/o contrase�a no validos
      public static final String COD_RES_WS_CONSULTA_ERROR_USUARIO = "3";
      //mobiles no validos para consulta
      public static final String COD_RES_WS_CONSULTA_ERROR_MOBILE = "4";      
      //
      public static final String COD_RES_WS_CONSULTA_ERROR_SALDO = "5";
      public static final String MSJ_RES_WS_CONSULTA_OK = "La consulta se realiz� con �xito";
      //mensaje error interno, comuniquese con el adminsitrador
      public static final String MSJ_RES_WS_CONSULTA_ERROR_INTERNO = "Error inetrno en la Aplicaci�n. Comuniquese con el Administrador"; 
      //mensaje error en los parametros
      public static final String MSJ_RES_WS_CONSULTA_ERROR_PARAMETRO = "Par�metros no v�lidos"; 
      //mensaje error en el usuario
      public static final String MSJ_RES_WS_CONSULTA_ERROR_USUARIO = "Usuario no v�lido y/o no tiene perfil Administrador"; 
      //mensaje error CON LOS MOBILES
      public static final String MSJ_RES_WS_CONSULTA_ERROR_MOBILE = "La lista de mobiles no es v�lida"; 
      
      
    public Constants() {
    }
    
    public static String getString(String cadena)
    {
        return ResourceBundle.getBundle(APPLICATION_BUNDLE).getString(
                cadena);
    }
    
    public static String getApplicationPath()
    {
        String arr[],fileSeparator,result;
        arr=Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/");
        fileSeparator=System.getProperty("file.separator");
        int i=0;
        while(arr[i].length()==0) i++;
        result="";
        for(;i<arr.length-2;i++){
            result+=arr[i]+fileSeparator;
        }
        return result;
    }

}
