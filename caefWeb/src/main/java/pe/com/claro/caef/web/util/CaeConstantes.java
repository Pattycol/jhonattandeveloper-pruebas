package pe.com.claro.caef.web.util;

import java.math.BigInteger;
import java.util.ResourceBundle;



public class CaeConstantes {

	private static final String BUNDLE_NAME_CAECONDIG = "caeConfig";
	private static final ResourceBundle RESOURCE_BUNDLE_CAECONDIG = ResourceBundle.getBundle(BUNDLE_NAME_CAECONDIG);

	// private static ResourceBundle rb = ResourceBundle.getBundle("messages_es");

	/*
	 * public static final String TRANSACCION_CAMBIOSIM_MOTIVO1 = rb.getString("transaccion.cambioSim.motivo1"); public static final String TRANSACCION_CAMBIOSIM_MOTIVO2 = rb.getString("transaccion.cambioSim.motivo2"); public static final String TRANSACCION_CAMBIOSIM_MOTIVO3 = rb.getString("transaccion.cambioSim.motivo3"); public static final String TRANSACCION_CAMBIOSIM_MOTIVO4 = rb.getString("transaccion.cambioSim.motivo4"); public static final String TRANSACCION_CAMBIOSIM_MOTIVO5 = rb.getString("transaccion.cambioSim.motivo5"); public static final String TRANSACCION_CAMBIOSIM_MOTIVO6 = rb.getString("transaccion.cambioSim.motivo6");
	 */

	// Constantes de Nombres de Origenes para los mensajes.
	public final static String origenBSCS = "BSCS708";
	public final static String origenTodel1 = "TODEL1";
	public final static String origenFacturas = "Facturas";
	public final static String origenDBTO_TEST = "DBTO_TEST";
	public final static String origenTIMPRB = "TIMPRB";
	public final static String origenCAEBD = "CAEBD";
	public final static String origenSIXBELL = "SIXBELL";
	public final static String origenTIMTVIR = "TIMTVIR";
	public final static String origenTIMDEV = "TIMDEV";
	// AVM
	public final static String origenGWP = "GWP";
	public final static String origenEAI = "EAI";
	// Constantes para enmascaramiento de numero de telefono
	public final static int digitosTelefonoNormal = 4;
	public final static int digitosTelefonoReducido = 3;
	public final static String caracterEnmascarar = "X";
	public final static int longitudTelefonoReducido = 6;

	// Constante de Detalle de Llamadas
	public final static String flagLlamadaOculto = "0";
	/* 05-06 */
	public final static String flagLlamadaOcultoCoporativo = "1";
	public final static String llamadaLibre = "Libre";
	public final static String servicioCero = "0";

	// Constantes para el Valor de los Combos
	public final static String valueTodos = "Todas";
	public final static String valueSeleccionar = "-- Seleccione --";
	public final static String valueSelecciona = "-- Selecciona --";
	public final static String valueTodos1 = "Todos";

	// ESTADO DE CUENTA
	public final static int valueMaxEstadoCuenta = 6; // Toma los 6 últimos meses de facturación
	// Constantes para la Actualizacion de Datos
	// public final static int valueFechasFacturacion = 2; // Toma los 2 últimos meses de facturación
	public final static int valueFechasFacturacion = 3; // Toma los 3 últimos meses de facturación
	public final static int valueFlagActDatos = 1; // Variable que indica que cuando el usuario se vuelva a loguear no le debe aparecer la interface de actualizacion de datos
	public final static int valueFlagDesDatos = 0; // Variable que indica que cuando el usuario se vuelva a loguear le debe aparecer la interface de actualizacion de datos
	public final static int valueContActDatos = 0; // Variable por defecto 0, viene de la BD TIMPRB
	public final static String valueError = "OK"; // Variable por defecto OK, viene de la BD TIMPRB

	// Constantes para Administracion de Permisos
	public final static int valueCodPerfilAdm = 1; // Número perfil Administrador
	public final static int valueCodPerfilSubAdm = 2; // Número perfil Subadministrador
	public final static int valueCodPerfilEmpleadoPlus = 11; // Número perfil Empleado Plus
	public final static int valueCodPerfilUsu = 3; // Número perfil usuario
	public final static String valueTipoCuenta = "H"; // Número tipo usuario

	public final static String ORACLE_SQL_NAME_ACTUALIZACION_USUARIOS = RESOURCE_BUNDLE_CAECONDIG.getString("oracle.sql.name.object.listaTelefonos");
	public final static String ORACLE_SQL_NAME_ARRAY_ACTUALIZACION_USUARIOS = RESOURCE_BUNDLE_CAECONDIG.getString("oracle.sql.name.array.listaTelefonos");

	/* Inicio Constantes para Sixbell */
	public final static String valuePortal = "PORTALCAE";
	public final static String valueConsulta = "TELEFONOCONSULTA";
	public final static int valueBolsa = 0;
	public final static int valueExitCode = 1;
	public final static String valueCodAplic = "8000";
	public final static String valueBolsaInfinita = "SALDO SIN LIMITE";

	/* VALIDANDO LAS BOLSAS CON EL DOCUMENTO BOLSAS VIGENTES - DESCRIPCION A UTILIZAR */
	public final static String valueBolsa1 = "Bolsa de Minutos CDI (Cualquier Destino Internacional)"; // "BOLSA MINUTOS TODOS LOS DESTINOS";
	public final static String valueBolsa2 = "Bolsa de SMS CDI (Cualquier Destino Internacional)"; // "BOLSA SMS TODOS LOS DESTINOS";
	public final static String valueBolsa3 = "Bolsa de Soles CDI (Cualquier Destino Internacional)"; // "BOLSA DINERO TODOS LOS DESTINOS";
	public final static String valueBolsa4 = "Saldo Disponible Prepago"; // "BOLSA CUENTA PERSONAL PREPAGO";
	public final static String valueBolsa5 = "Bolsa de Datos (KB)"; // "BOLSA GPRS";
	public final static String valueBolsa6 = "Bolsa de Soles para SMS CDI (Cualquier Destino Internacional)"; // "BOLSA DINERO SMS CDI";
	public final static String valueBolsa7 = "Bolsa de Minutos a Claro Locales"; // "BOLSA MINUTOS ON-NET LOCAL";
	public final static String valueBolsa8 = "Bolsa de MMS"; // "BOLSA MMS";
	public final static String valueBolsa9 = "Bolsa de Minutos ClaroData 121"; // "BOLSA MINUTOS CLARODATA 121";
	public final static String valueBolsa10 = "Bolsa de Soles RPMe (Red Privada Móvil Empresarial)"; // "BOLSA DINERO RPMe";
	public final static String valueBolsa11 = "Bolsa de Soles RPM (Red Privada Móvil)"; // "BOLSA DINERO RPM";
	public final static String valueBolsa12 = "Bolsa de Soles a Claro Nacionales"; // "BOLSA DINERO ON-NET";
	public final static String valueBolsa13 = "Bolsa de Soles a Fijos Nacionales"; // "BOLSA DINERO OFF FIJO";
	public final static String valueBolsa14 = "Bolsa de Soles a Otros Operadores Nacionales"; // "BOLSA DINERO OFF MOVIL";
	public final static String valueBolsa15 = "Bolsa de Soles LDN (Larga Distancia Nacional)"; // "BOLSA DINERO LDN";
	public final static String valueBolsa16 = "Bolsa de Soles LDI (Larga Distancia Internacional)"; // "BOLSA DINERO LDI" ;
	public final static String valueBolsa17 = "Bolsa de Minutos RPMe (Red Privada Móvil Empresarial)";// "BOLSA MINUTOS RPMe";
	public final static String valueBolsa18 = "Bolsa de Minutos RPM (Red Privada Móvil)";// "BOLSA MINUTOS RPM";
	public final static String valueBolsa19 = "Bolsa de Minutos a Claro Nacionales";// "BOLSA MINUTOS ON-NET";
	public final static String valueBolsa20 = "Bolsa de Minutos a Fijos Nacionales";// "BOLSA MINUTOS OFF FIJO";
	public final static String valueBolsa21 = "Bolsa de Minutos a Otros Operadores Nacionales";// "BOLSA MINUTOS OFF MOVIL";
	public final static String valueBolsa22 = "Bolsa de Minutos LDN (Larga Distancia Nacional)";// "BOLSA MINUTOS LDN";
	public final static String valueBolsa23 = "Bolsa de Minutos LDI (Larga Distancia Internacional)";// "BOLSA MINUTOS LDI";
	public final static String valueBolsa24 = "Saldo Disponible Prepago para Datos (KB)"; // "BOLSA PREPAGO GPRS";
	public final static String valueBolsa25 = "Bolsa de Minutos ClaroData 134";// "BOLSA MINUTOS CLARODATA 134";
	public final static String valueBolsa26 = "Bolsa de Soles Fuera de la Red a Destinos Locales"; // "BOLSA DINERO OFF RPM";
	public final static String valueBolsa27 = "Bolsa de Minutos Fuera de la Red a Destinos Locales";// "BOLSA MINUTOS OFF RPM";
	public final static String valueBolsa28 = "Bolsa de Soles Fijos Locales"; // "BOLSA DINERO FIJO LOCAL";
	public final static String valueBolsa29 = "Bolsa de Minutos a Fijos Locales";// "BOLSA MINUTOS FIJO LOCAL";
	public final static String valueBolsa30 = "Bolsa de Minutos a Fijos y Otros Operadores Locales";// "BOLSA MINUTOS OFF-NET LOCAL";
	public final static String valueBolsa31 = "Bolsa de Minutos ClaroData 121/ 134";// "BOLSA DE MINUTOS CLARODATA  121 /134";
	public final static String valueBolsa32 = "Bolsa de Minutos Plan Exacto";// "BOLSA MINUTOS PLAN EXACTO";
	public final static String valueBolsa33 = "Bolsa de Soles Adicionales";// "BOLSA DINERO ADICIONAL";
	public final static String valueBolsa34 = "Bolsa de SMS a Claro Nacionales";// "BOLSA SMS ON-NET";
	public final static String valueBolsa35 = "Bolsa de Minutos CDN (Cualquier Destino Nacional)";// "BOLSA MINUTOS CD";
	public final static String valueBolsa36 = "Bolsa de Minutos a Fijos y Otros Operadores Nacionales";// "BOLSA MINUTOS OFF-NET";
	public final static String valueBolsa37 = "Bolsa de SMS RPMe (Red Privada Móvil Empresarial)"; // "BOLSA SMS RPMe";
	public final static String valueBolsa38 = "Bolsa de Minutos a Fijos, Otros Celulares Nacionales y Destinos Fuera de la Red";// "BOLSA MINUTOS OFF-NET + OFF RPM";
	public final static String valueBolsa39 = "Bolsa de Soles o Minutos asignados al Plan"; // "BOLSA MIN/DIN . ADICIONAL"; //Para los TOPES
	public final static String valueBolsa40 = "Bolsa de Soles RPM, Claro Nacionales y Fijos Locales";// "BOLSA DINERO RPM+ON-NET NAC.+OFF FIJO LO";
	// public final static String valueBolsa41 = "CONTADOR MIN/DIN. PARA IVR"; //DOC NO VA
	public final static String valueBolsa42 = "Bolsa de Soles y SMS CDN (Cualquier Destino Nacional)";// "BOLSA DINERO VOZ + SMS TODOS DESTINOS";
	public final static String valueBolsa43 = "Bolsa de SMS CDN (Cualquier Destino Nacional)";// "BOLSA SMS NACIONAL";
	public final static String valueBolsa44 = "Bolsa de Soles Adicionales para Llamadas"; // "BOLSA DINERO ADICIONAL VOZ";
	public final static String valueBolsa45 = "Bolsa de Soles Adicionales para SMS CDI (Cualquier Destino Internacional)"; // "BOLSA DINERO ADICIONAL SMS";
	public final static String valueBolsa46 = "Bolsa de Soles Adicionales para Datos (KB)"; // "BOLSA DINERO ADICIONAL GPRS";
	public final static String valueBolsa47 = "Bolsa de Soles Adicionales para MMS"; // "BOLSA DINERO ADICIONAL MMS";
	// public final static String valueBolsa48 = "Bolsa de Minutos Libres";//"MINUTOS LIBRES";
	public final static String valueBolsa49 = "Bolsa de Soles Adicionales para LBS";// "BOLSA DINERO ADICIONAL LBS";
	public final static String valueBolsa50 = "Bolsa de Soles Adicionales para Ideas";// "BOLSA DINERO ADICIONAL IDEAS";
	public final static String valueBolsa51 = "Bolsa de Soles Adicionales para Video Llamada"; // "BOLSA DINERO ADICIONAL VIDEO LLAMADA";
	public final static String valueBolsa52 = "Bolsa de Soles para Video Llamada";// "BOLSA DINERO VIDEO LLAMADA";
	public final static String valueBolsa53 = "Bolsa de Minutos Video Llamada";// "BOLSA MINUTOS VIDEO LLAMADA";
	// public final static String valueBolsa54 = "BOLSA CUENTA PERSONAL POSTPAGO"; //DOC NO VA
	public final static String valueBolsa55 = "Bolsa de Soles Control de Consumo para SMS CDI  (Cualquier Destino Internacional)";// "BOLSA DINERO CONTROL CONSUMO SMS";
	public final static String valueBolsa56 = "Bolsa de Soles Control de Consumo para MMS";// "BOLSA DINERO CONTROL CONSUMO MMS";
	public final static String valueBolsa57 = "Bolsa de Soles Control de Consumo para Datos (KB)";// "BOLSA DINERO CONTROL CONSUMO GPRS";
	public final static String valueBolsa58 = "Bolsa de Soles Control de Consumo para LBS";// "BOLSA DINERO CONTROL CONSUMO LBS";
	public final static String valueBolsa59 = "Bolsa de Soles Control de Consumo para Ideas";// "BOLSA DINERO CONTROL CONSUMO IDEAS";
	public final static String valueBolsa60 = "Bolsa de Minutos a Claro y Fijos Nacionales ";// "BOLSA MIN ON-NET NAC / OFF-NET FIJO NAC";
	public final static String valueBolsa61 = "Bolsa de Minutos CDI (Cualquier Destino Internacional) para Empleados";// "BOLSA MINUTOS EMPLEADOS";
	public final static String valueBolsa62 = "Bolsa de Banda Ancha Móvil";// "BOLSA BANDA ANCHA";
	// Adicionales
	public final static String valueBolsa63 = "Bolsa de Minutos BlackBerry";// "BOLSA MINUTOS BLACKBERRY";
	// public final static String valueBolsa64 = "Bolsa de Minutos Fuera de la Red CDI (Cualquier Destino Internacional)";//"BOLSA MINUTOS TODOS LOS DESTINOS OFF RPM";
	public final static String valueBolsa67 = "Bolsa de Minutos Fuera de la Red CDI (Cualquier Destino Internacional)";// "BOLSA MINUTOS TODOS LOS DESTINOS OFF RPM";

	public final static String valueTipoBolsa1 = "SOLES";
	public final static String valueTipoBolsa2 = "SEGUNDOS";
	public final static String valueTipoBolsa3 = "MINUTOS";
	public final static String valueTipoBolsa4 = "EVENTOS";
	public final static String valueTipoBolsa5 = "KILOBYTES";
	public final static String valueTipoBolsa6 = "BYTES";
	public final static String valueStatus1 = "ACTIVO"; // Se aplica a Linea
	public final static String valueStatus2 = "SUSPENDIDO"; // Se aplica a Linea
	public final static String valueStatus3 = "CANCELADO"; // Se aplica a Linea
	public final static String valueStatus4 = "ACTIVO"; // Se aplica a Cuenta
	public final static String valueStatus5 = "SUSPENDIDO"; // Se aplica a Cuenta
	public final static String valueStatus6 = "CANCELADO"; // Se aplica a Cuenta
	public final static String valueStatus13 = "BLOQUEO POR ROBO"; // Se aplica a Linea
	public final static String valueStatus14 = "BLOQUEO POR PERDIDA"; // Se aplica a Linea
	public final static String valueStatus15 = "BLOQUEO A PEDIDO"; // Se aplica a Linea
	public final static String valueExitCode1 = "TRANSACCION SATISFACTORIA";
	public final static String valueExitCode2 = "CUENTA INVALIDA";
	public final static String valueExitCode3 = "ACCESO RESTRINGIDO";
	public final static String valueExitCode4 = "SUPERO EL LIMITE DE ACCESOS";
	public final static String valueExitCode5 = "DATOS NO DISPONIBLES";
	public final static String valueExitCode6 = "USER ID INVALIDO";
	public final static String valueExitCode7 = "ERROR INTERNO";
	public final static String valueExitCode8 = "TRANSACCION INVALIDA";
	public final static String valueExitCode9 = "TRANSACCION EN EJECUCION";
	public final static int[] codigosNoMostrarSixbell = { 41 }; // Son los codigos de las bolsas que no se mostraran

	/* Fin Constantes para Sixbell */

	// Constantes para transformas las fechas de la base de datos
	public static enum mes {
		BLANCO, ENE, FEB, MAR, ABR, MAY, JUN, JUL, AGO, SET, OCT, NOV, DIC
	};

	public static enum month {
		BLANK, JAN, FEB, MAR, APR, MAY, JUN, JUL, AGO, SEP, OCT, NOV, DEC
	};

	public final static String pattern_dd_mm_yyyy = "dd/mm/yyyy";
	public final static String local_ES = "ES"; // Idioma español
	public final static String local_EN = "EN"; // Idioma ingles

	// Constantes para la administracion de usuario
	public final static String user_activo = "a"; // Números activos en BSCS
	public final static String user_desactivo = "d"; // Números desactivados en BSCS
	public final static String user_noActivo = "o"; // Números no activados en BSCS
	public final static String user_suspendido = "s"; // Números suspendidos en BSCS

	public final static String user_business = "1"; // Variable para el tipo de Usuario
	public final static String user_consumer = "2"; // Variable para el tipo de Usuario

	public final static String user_postpago = "1"; // Variable para el tipo de Cliente
	public final static String user_control1 = "73"; // Variable para el tipo de Cliente
	public final static String user_control2 = "C"; // Variable para el tipo de Cliente
	public final static String user_B2E = "3"; // Variable para el tipo de Cliente
	public final static String user_prepago = "P"; // Variable para el tipo de Cliente

	public final static String user_default_tipoCuenta = "H"; // Número tipo usuario
	public final static String user_default_Nivel = "1";
	public final static int user_default_perfil = 3; // Número perfil usuario
	public final static int user_default_perfil_b2e = 16;// Número perfil usuario para B2Es
	public final static int user_admin_perfil_postpago = 4; // Número postpago perfil administrador
	public final static int user_subAdmin_perfil_postpago = 5; // Número postpago perfil subadministrador
	public final static int user_default_perfil_postpago = 6; // Número postpago perfil usuario
	public final static int user_admin_perfil_control = 7; // Número control perfil administrador
	public final static int user_subAdmin_perfil_control = 8; // Número control perfil subadministrador
	public final static int user_default_perfil_control = 9; // Número control perfil usuario
	public final static int user_default_perfil_prepago = 10; // Número Prepago
	public final static int user_plus_perfil_corporativo = 11;
	public final static int user_default_perfil_usuario_plus_2 = 12;

	public final static int user_admin_perfil_b2e = 14; // Número perfil usuario para B2Es

	public final static String user_Tipo_Bus = "CORPORATIVO"; // Para usuarios empresas (CAE)
	public final static String user_Tipo_Con = "CONSUMER"; // Para usuarios clientes (CAC)
	public final static String cliente_Tipo_PostPago = "POSTPAGO"; // Número tipo Postpago
	public final static String cliente_Tipo_Corporativo = "CORPORATIVO"; // Número tipo Corporativo
	public final static String cliente_Tipo_Control = "CONTROL"; // Número tipo Control
	public final static String cliente_Tipo_PrePago = "PREPAGO"; // Número tipo Prepago
	public final static String cliente_Tipo_B2E = "B2E";
	public final static String cliente_Tipo_DEMO = "Demo";
	public final static String[] valuesTipoPerfil = { cliente_Tipo_PostPago }; // Array de Tipos de Número

	public final static String tipoCuenta_Administrador = "P"; // Tipo Cuenta Administrador
	public final static String tipoCuenta_SubAdministrador = "S"; // Tipo Cuenta Subadministrador
	public final static String tipoCuenta_Licitador = "L"; // Tipo Cuenta Licitador
	public final static String tipoCuenta_Usuario = "H"; // Número tipo usuario
	public final static String tipoCuenta_NoAdministrador = "H','L','S";
	public final static String tipoCuenta_NoSubadministrador = "H','L";

	public final static String TIPO_PROVIDER_PREPAGO = "PREPAGO";
	// Constastes seguridad
	public final static int longitud_password = 6; // Longitud del password, usado en el login
	public final static String formato_internacional = "51"; // Formato internacional de Perú

	public final static int FALLO = 0; // Usado por múltiples condiciones
	public final static int EXITO = 1; // Usado por múltiples condiciones
	public final static int DESACTIVADO = 0; // Usado por múltiples condiciones
	public final static int ACTIVADO = 1; // Usado por múltiples condiciones

	// Constantes Administracion de Permisos
	public final static String insertaSubAdm = "1";
	public final static String errorInsertaSubAdm = "0";
	public final static String actualizaSubAdm = "1";
	public final static String errorActualizaSubAdm = "0";
	public final static String eliminaSubAdm = "1";
	public final static String errorEliminaSubAdm = "0";

	// Constantes para las pantallas
	public final static String maxlength = "9";
	public final static String maxlengthPermisos = "9";
	public final static String maxlengthNick = "30";
	public final static String maxlengthPassword = "6";

	public final static String flagLanguage = "1"; // Condición para que ejecute ALTER SESSION NLS

	// Constantes para "Activacion de Servicios"
	public final static String ESTADO_ACTIVADO = "A"; // Estado Activo
	public final static String ESTADO_DESACTIVADO = "D"; // Estado Desactivo

	// Constantes para localidad
	public final static String codigoLima = "1";

	// Constantes para localidad
	public final static int datoPrueba = 0;

	// Constantes para el servicio de ideas TV
	// public final static String SNCODE_SERVICIO_IDEAS="sncodeideas";
	// Constante para el servicio iMusica
	// public final static String SNCODE_SERVICIO_IMUSICA ="sncodeimusica";

	// Providers
	public final static String TIPO_PROVIDER_CONTROL = "CONTROL";

	public final static String codMenuPaginaPrincipal = "0"; // los banners de la pagina de inicio

	// registro de datos Prepago
	public final static String motivo_registro = "Registro Manual";

	public final static String INTERACCION_METODO_CONTACTO = "Web";// RLV - ojo va a cambiar a WEB
	public final static String INTERACCION_TIPO_INTERACCION = "Entrante";
	public final static String INTERACCION_CODIGO_EMPLEADO = "USRCEL";// RLV - ojo va a cambiar a USRWEB
	public final static String INTERACCION_CODIGO_SISTEMA = "USRWEB";// RLV - ojo va a cambiar a CEL
	public final static String INTERACCION_RESULTADO_NINGUNO = "Ninguno";
	public final static String INTERACCION_HECHOENUNO_1 = "1";
	public final static String INTERACCION_HECHOENUNO_0 = "0";
	public final static String INTERACCION_FLAGCASO_1 = "1";
	public final static String INTERACCION_FLAGCASO_0 = "0";

	/* OST */
	// Aprueba o rechaza cotización de OST
	public final static String APRUEBA_COTIZACION_OST = "1";
	public final static String RECHAZA_COTIZACION_OST = "0";

	// Usuario definido para registro de aprobación y desaprobación de OST es CEL (actualizalo al final)
	public final static String USUARIO_REGISTRO_OST = "CEL";

	// Parametros definidos para obtener los OST
	public final static int PARAMETRO_IN_ESTADO_OST = -1;
	public final static int PARAMETRO_OUT_ESTADO_OST = 221;

	// Parametro para obtener repuestos para OST
	public final static int PARAMETRO_ESTADO_REPUESTO = 3;

	// Parametro para obtener defectos para OST
	public final static int PARAMETRO_ESTADO_DEFECTOS = 5;
	public static final String FIRST_CALL_DATE = "2007-11-09";

	// manejo de colas - Cola de Solicitud de Pack
	public final static String CASO_TIPO_POSTPAGO = "POSTPAGO";
	public final static String CASO_CLASE_VARIACION = "VARIACIÓN";
	public final static String CASO_SUBCLASE_PACK = "PACK EMERGENCIA";
	public final static String CASO_METODO_CONTACTO_WEB = "Web";
	public final static String CASO_PRIORIDAD = "NO PRECISADO";
	public final static String CASO_SEVERIDAD = "NO PRECISADO";
	// public final static String CASO_COLA_PACK="GCE1-DESPACHOS";
	public final static String CASO_COLA_PACK = "GCE1 - DESPACHO";
	public final static String CASO_FLAG_INTERACT = "1";
	public final static String CASO_USR_PROCESO = "USRWEB";
	public final static String CASO_USUARIO = "USRCEL"; // RLV - usuario debe ser USRWEB
	public final static String CASO_TIPO_INTERACCION = "Entrante";

	// Parametros para obtener Bolsas en la consulta Relacion de Planes
	public static final String RP_BOLSAS_TIPO_BUSQUEDA = "C";
	public static final String RP_BOLSAS_ORDEN = "N";
	public static final String RP_BOLSAS_ASC_DESC = "A";
	public static final String RP_BOLSAS_CICLO = "00";
	public static final String RP_BOLSAS_SHBOLSA = "0";

	public static final int TC_ERROR_SOLICITUD_EN_24H = 1;
	public static final int TC_ERROR_FACTURA_NULL = 2;
	public static final int TC_ERROR_EXCESO_NUMEROS = 3;

	public static final String TC_TELEFONOS = "T";
	public static final String TC_BOLSAS = "B";
	public static final String TC_GENERAL = "G";
	public static final String TC_OTROS = "O";

	/**
	 * Constantes usadas en el excel de factura detallada, para marcar si el campo es Titulo Total
	 */
	public static final String TC_LABEL_TOTALES = ",Cargos del Mes,Total Cargo del Mes,Total Recibo,";
	public static final String TC_LABEL_TOTALES_CARGOS_MES = "Cargos del Mes";
	public static final String TC_LABEL_TOTLAES_TOTAL_CARGOS_MES = "Total Cargo del Mes";

	public static final String RPM_ILIMITADO = "PRODUCTO SERVICIO RPM ILIMITADO";
	public static final String RPME_ILIMITADO = "PRODUCTO SERVICIO RPME ILIMITADO";

	public final static String idMenuActualizacionDatosCuenta = "110";

	public final static String FECHA_DEFAULT_CLARIFY = "01/01/1753";

	public final static String NIVEL_40_BSCS = "40";

	/** Parametro usado para pasarle una instancia de DatosCliente a BloqueoActivoFormController * */
	public final static String SS_CUENTAS_CLIENTES = "ss_CuentaClienteBean";

	public final static String MSG_ERROR_BLOQUEO_EQUIPO = "MSG_ERROR_BLOQUEO_EQUIPO";
	public final static String OTROS_MOTIVOS_BLOQUEO_LINEA = "otrosMotivosBloqueoLinea";
	public final static int NUMERO_MAXIMO_EQUIPOS_MOSTRAR = 5;
	public final static String IS_ACCION_BLOQUEO = "isFormAccion";
	/**
	 * Parametros usados para publicar los valores de bloqueo y desbloqueo e imprimirlos en la confirmacion
	 */
	public final static String TICKET_MOTIVO_BLOQUEO_LINEA = "ticketMotivoBloqueoLinea";
	public final static String TICKET_COD_BLOQ_LINEA = "ticketCodigoBloqueoLinea";
	public final static String TICKET_MARCA_MODELO_BLOQ_EQUIPO = "ticketMarcaModeloBloqueoEquipo";
	public final static String TICKET_IMEI_BLOQ_EQUIPO = "ticketImeiBloqueoEquipo";
	public final static String TICKET_CODIGO_BLOQUEO_EQUIPO = "ticketCodigoBloqueoEquipo";
	public final static String TICKET_MOT_BLOQ_EQUIPO = "ticketMotivoBloqueoEquipo";
	public final static String TICKET_COD_BLOQ_EQUIPO = "ticketCodigoBloqueoEquipo";

	public static final String FORM_OPERACION_CONSULTA = "consultar";
	public static final String TOPES_CONSUMO_FORM_OPERACION_CAMBIO = "cambio";
	public static final String TOPES_CONSUMO_FORM_OPERACION_PLAN = "plan";
	public static final String TOPES_CONSUMO_FORM_OPERACION_BOLSA = "bolsa";

	public static final int TOPES_CONSUMO_APPLYADJUST_PARAMETERS_VALUETOLERANCE = 0;
	public static final int TOPES_CONSUMO_APPLYADJUST_PARAMETERS_CONSUMEPERIOD = 0;
	public static final int TOPES_CONSUMO_APPLYADJUST_PARAMETERS_TRANSACTIONWAYID = 1;
	public static final int TOPES_CONSUMO_APPLYADJUST_PARAMETERS_TRANSACTIOCAUSEID = 5;
	public static final int TOPES_CONSUMO_APPLYADJUST_PARAMETERS_USERID = 5000;
	public static final int TOPES_CONSUMO_APPLYADJUST_PARAMETERS_IDPRODUCT = 945;
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_TIPO = "POSTPAGO";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_CLASE = "VARIACIÓN - SERVICIOS";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_SUBCLASE = "CAMBIO CONTROL DE CONSUMO";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_METODOCONTACTO = "Web";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_TIPOINTERACCION = "Entrante";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_CODIGOEMPLEADO = "USRCEL";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_CODIGOSISTEMA = "USRWEB";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_HECHOENUNO = "0";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_FLAGCASO = "0";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_TEXTRESULTADO = "Ninguno";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_BLANCO = "";
	public static final String TOPES_CONSUMO_CLARIFY_PARAMETERS_NOTAS = "topes_consumo.clarify.notas";
	public static final String TOPES_CONSUMO_VARIACIONLIMITE_AUMENTAR = "topes_consumo.variacion.aumentar";
	public static final String TOPES_CONSUMO_VARIACIONLIMITE_DISMINUIR = "topes_consumo.variacion.disminuir";
	public static final String TOPES_CONSUMO_MASIVO_CLARIFY_PARAMETERS_NOTAS = "topes_consumo_masivo.clarify.notas";

	public static final String CODIGO_EXITO = "0";

	/* JANUS TOPES DE CONSUMO */
	public static final BigInteger MOV_ID = new BigInteger("0");
	public static final String ENTITY_TYPE_C = "C";
	public static final String CLARO_EN_LINEA = "Mi Claro";
	public static final Integer RELATION_SHIP_3 = 3;
	public static final String CREDIT_DESCRIPTION = "VOICE";

	public final static String NOMBRE_PLAN = "PROD. DEFAULT LINEA";
	public final static String LABEL_PLAN = "Bolsa";

	public final static String NOMBRE_BOLSA_ENMASCARAR = "VOICE";
	public final static String LABEL_BOLSA_ENMASCARAR = "Bolsa de Soles o Minutos Asignados al Plan";
	public static final String ENTITY_TYPE_E = "E";
	public static final int MENSAJE_ERROR = 2033;
	public static final String MENSAJE_SUCCESS = "SUCCESS";
	public static final String TIPO_USR_JANUS = "T";
	public static final Integer RELATION_SHIP_0 = 0;
	public static final String CREDIT_DESCRIPTION_MONEY = "MONEY";
	public final static String NOMBRE_BOLSA_ENMASCARAR_MONEY = "MONEY";
	public static final String TIPO_USR_RICE = "F";

	public static String ID_MENU_PERFILES_Y_PERMISOS = "78";
	public static String ID_MENU_PERFIL_ADMINISTRADOR = "915";

	public static String ID_MENU_OPERACIONES = "118";
	public static String ID_COMPRA_PAQUETE_DATOS_POSTPAGO = "197";
	public static final String PAGE_COMPRA_PAQUETE_DATOS_POSTPAGO = "/caeWeb/menu/compraPaquetes.htm?idMenu=197";

}
