/*
 * ConstantsMQT2.java
 *
 * Created on January 10, 2005, 2:23 PM
 */

package com.stconsulting.common.util;

/**
 * 
 * @author STConsulting
 */

public class ConstantsMQT2{
	// BD SOURCE
	public static final String BD_MQT2="BD_MQT2";
	public static final String BD_COMMON="BD_COMMON";

	public static final String COD_OPERADOR_TIM="1";
	// FORMATOS
	public static final String FORMATO_FECHA_MOSTRAR="dd/MM/yyyy";
	public static final String FORMATO_ENTERO_MOSTRAR="###,###";
	public static final String FORMATO_DECIMAL_MOSTRAR="###,###.##";

	public static final String MESSAGE_FORMAT="MESSAGE_FORMAT";
	public static final String MESSAGE_FORMAT2="MESSAGE_FORMAT2";

	public static final String FORMATO_RANGO_HORAS=":00";

	public static final String COD_SI="S";
	public static final String COD_NO="N";
	public static final int MAX_NUM_INT_FALLIDOS=5;

	public static final String COD_EST_ACT="A";
	public static final String COD_EST_INA="I";
	public static final String COD_EST_BLO="B";
	public static final String DESC_EST_ACT="Activo";
	public static final String DESC_EST_INA="Inactivo";

	public static final String EST_NUEVO="N";
	public static final String EST_MODIFICAR="M";

	// Carga de Combo Tipo WBList

	public static final String COD_WHITE="01";
	public static final String COD_BLACK="02";
	public static final String DESC_WHITE="White";
	public static final String DESC_BLACK="Black";

	// Carga del Combo de Tipo Ingreso

	public static final String COD_TIPO_ORI="01";
	public static final String COD_TIPO_ORDES="02";
	public static final String DESC_TIPO_ORI="Solo Rango Inicio";
	public static final String DESC_TIPO_ORIDES="Rango Inicial y Final";

	// Carga del Combo de Tipo Rellamada
	public static final String COD_TIPO_TIM_TIM="TT";
	public static final String COD_TIPO_ALL_TIM="AT";
	public static final String DESC_TIPO_TIM_TIM="Tim-Tim";
	public static final String DESC_TIPO_ALL_TIM="All-Tim";
	public static final String ALIAS_TIPO_TIM_TIM="Tim_Tim";
	public static final String ALIAS_TIPO_ALL_TIM="All_Tim";

	public static final String COD_SELECCIONE="-1";
	public static final String DESC_SELECCIONE="Seleccione";

	public static final String COD_TODOS="0";
	public static final String DESC_TODOS="Todos";

	// Mensajes Request
	public static final String MSG_ES_CONTENIDO="MSG_ES_CONTENIDO";

	// Valores de los Mensajes
	public static final String COD_CONTENIDO="0";
	public static final String COD_NO_CONTENIDO="1";

	public static final String COD_USER_PWD_INVALIDO="0";
	public static final String COD_USER_BLOQUEADO="1";

	public static final String WHITE_LIST="WHITE";
	public static final String BLACK_LIST="BLACK";

	// TITULOS
	public static final String TITULO_WBLIST="mantenimiento.title.wblits";
	public static final String TITULO_RANGO_OPERADORES="mantenimiento.title.rangooperadores";
	public static final String TITULO_PRIVLEGIO="seguridad.title.privilegio";
	public static final String TITULO_PERFILXPRIVILEGIO="seguridad.title.perfil.privilegio";
	public static final String TITULO_PERFIL_ELIMINAR="perfil.title.perfil.eliminar";
	// public static final String
	// TITULO_PRIVILEGIO_ELIMINAR="privilegio.title.perfil.eliminar";

	// REPORTES
	public static final String COD_SPAMMER="S";
	public static final String DESC_SPAMMER="Spammer";
	public static final String COD_PENALIZADOS="P";
	public static final String DESC_PENALIDOS="Penalizado";
	public static final String TIPO_SPAMMER="SPAM";
	public static final String TIPO_PENALIZADOS="PENA";
	public static final String TIPO_SMS="SMS";

	public static final String DESC_TIM_TIM="TIM-TIM";
	public static final String DESC_ALL_TIM="ALL-TIM";

	public static final String COD_INT_15="0";
	public static final String DESC_INT_15="15 minutos";
	public static final String ALIAS_INT_15="15";
	public static final String COD_INT_30="1";
	public static final String DESC_INT_30="30 minutos";
	public static final String ALIAS_INT_30="30";
	public static final String COD_INT_60="2";
	public static final String DESC_INT_60="60 minutos";
	public static final String ALIAS_INT_60="60";
	public static final String COD_INT_90="3";
	public static final String DESC_INT_90="90 minutos";
	public static final String ALIAS_INT_90="90";

	public static final String TIPO_HORA="H";
	public static final String TIPO_MINUTO="M";

	// Tipos de Mensajes
	public static final String TIPO_MENSAJE_OK="OK";
	public static final String TIPO_MENSAJE_ERROR="ERROR";

	// Auditoria
	// Administracion
	public static final String COD_OPCION_PERFIL="PER";
	public static final String DESC_INSERTAR_PERFIL="Creacion de Perfil";
	public static final String DESC_INSERTAR_PRIVILEGIOS_PERFIL="Creacion de Privilegios por Perfil";
	public static final String DESC_ELIMINAR_PRIVILEGIOS_PERFIL="Eliminacion de Privilegios por Perfil";
	public static final String DESC_MODIFICAR_PERFIL="Modificacion de Perfil";
	public static final String DESC_ELIMINAR_PERFIL="Eliminacion de Perfil";
	public static final String COD_OPCION_PRIVILEGIO="PRI";
	public static final String DESC_INSERTAR_PRIVILEGIO="Creacion de Privilegio";
	public static final String DESC_MODIFICAR_PRIVILEGIO="Modificacion de Privilegio";
	public static final String DESC_ELIMINAR_PRIVILEGIO="Eliminacion de Privilegio";
	public static final String DESC_BUSCAR_PRIVILEGIO="Consulta de Privilegio";
	public static final String COD_OPCION_USUARIO="USU";
	public static final String DESC_INSERTAR_USUARIO="Creacion de Usuario";
	public static final String DESC_MODIFICAR_USUARIO="Modificacion de Usuario";
	public static final String DESC_ELIMINAR_USUARIO="Eliminacion de Usuario";
	// Mantenimiento
	public static final String COD_OPCION_WBL="WBL";
	public static final String DESC_INSERTAR_WBL="Creacion de White-Black List";
	public static final String DESC_MODIFICAR_WBL="Modificacion de White-Black List";
	public static final String DESC_ELIMINAR_WBL="Eliminacion de White-Black List";
	public static final String COD_OPCION_OPERADOR="OPE";
	public static final String DESC_INSERTAR_OPERADOR="Creacion de Operador";
	public static final String DESC_MODIFICAR_OPERADOR="Modificacion de Operador";
	public static final String DESC_ELIMINAR_OPERADOR="Eliminacion de Operador";
	public static final String COD_OPCION_MSC="MSC";
	public static final String DESC_INSERTAR_MSC="Creacion de Msc";
	public static final String DESC_MODIFICAR_MSC="Modificacion de Msc";
	public static final String DESC_ELIMINAR_MSC="Eliminacion de Msc";
	public static final String COD_OPCION_RANGO_OPERADORES="RAN";
	public static final String DESC_INSERTAR_RANGO_OPERADOR="Creacion de Rango Operador";
	public static final String DESC_MODIFICAR_RANGO_OPERADOR="Modificacion de Rango Operador";
	public static final String DESC_ELIMINAR_RANGO_OPERADOR="Eliminacion de Rango Operador";
	// Configuracion
	public static final String COD_OPCION_PARAMETROS="PAR";
	public static final String DESC_INSERTAR_PARAMETROS="Creacion de Parametro";
	public static final String DESC_MODIFICAR_PARAMETROS="Modificacion de Parametro";
	// Reportes
	public static final String COD_OPCION_REPORTE_TIPO_EVENTO_DIA="TED";
	public static final String DESC_CONSULTAR_REPORTE_TIPO_EVENTO_DIA="Consulta Reporte de Tipo de Evento por Dia";
	public static final String DESC_IMPRIMIR_REPORTE_TIPO_EVENTO_DIA="Imprimir Reporte de Tipo de Evento por Dia";
	public static final String COD_OPCION_REPORTE_EVENTO_DIA="EPD";
	public static final String DESC_CONSULTAR_REPORTE_EVENTO_DIA="Consulta Reporte de Evento por Dia";
	public static final String DESC_IMPRIMIR_REPORTE_EVENTO_DIA="Imprimir Reporte de Evento por Dia";
	public static final String COD_OPCION_REPORTE_EVENTO_MSC_OPERADOR="EMO";
	public static final String DESC_CONSULTAR_REPORTE_EVENTO_MSC_OPERADOR="Consulta Reporte de Evento por Msc y Operador";
	public static final String DESC_IMPRIMIR_REPORTE_EVENTO_MSC_OPERADOR="Imprimir Reporte de Evento por Msc y Operador";
	public static final String COD_OPCION_REPORTE_EVENTO_REPETIDO_MSC="ERM";
	public static final String DESC_CONSULTAR_REPORTE_EVENTO_REPETIDO_MSC="Consulta Reporte de Evento Repetido por Msc";
	public static final String DESC_IMPRIMIR_REPORTE_EVENTO_REPETIDO_MSC="Imprimir Reporte de Evento Repetido por Msc";
	public static final String COD_OPCION_REPORTE_SMS_ENVIADO_DIA="SMS";
	public static final String DESC_CONSULTAR_REPORTE_SMS_ENVIADO_DIA="Consulta Reporte de Sms enviado por Dia";
	public static final String DESC_IMPRIMIR_REPORTE_SMS_ENVIADO_DIA="Imprimir Reporte de Sms enviado por Dia";
	public static final String COD_OPCION_REPORTE_EFECTO_RELLAMADA_DIA="ERR";
	public static final String DESC_CONSULTAR_REPORTE_EFECTO_RELLAMADA_DIA="Consulta Reporte de Efecto Rellamada";
	public static final String DESC_IMPRIMIR_REPORTE_EFECTO_RELLAMADA_DIA="Imprimir Reporte de Efecto Rellamada";
	public static final String COD_OPCION_REPORTE_COMPARATIVO_RELLAMADA_DIA="CRR";
	public static final String DESC_CONSULTAR_REPORTE_COMPARATIVO_RELLAMADA_DIA="Consulta Reporte Comparativo Rellamada";
	public static final String DESC_IMPRIMIR_REPORTE_COMPARATIVO_RELLAMADA_DIA="Imprimir Reporte Comparativo Rellamada";
	public static final String COD_OPCION_REPORTE_AUDITORIA="AUD";
	public static final String DESC_CONSULTAR_REPORTE_AUDITORIA="Consulta Reporte Auditoria";
	public static final String DESC_CONSULTAR_USUARIO="Consulta Usuario";
	public static final String DESC_IMPRIMIR_REPORTE_AUDITORIA="Imprimir Reporte Auditoria";

	// Privilegios
	public static final String COD_USUARIO_CREAR_MOD="USU01";
	public static final String COD_USUARIO_ELIMINAR="USU02";
	public static final String COD_USUARIO_BUSCAR="USU03";
	public static final String COD_PERFIL_CREAR_MOD="PER01";
	public static final String COD_PERFIL_ELIMINAR="PER02";
	public static final String COD_PERFIL_BUSCAR="PER03";
	public static final String COD_PERFIL_AGREGAR_PRIVILEGIO="PER04";
	public static final String COD_PERFIL_ELIMINAR_PRIVILEGIO="PER05";
	public static final String COD_PRIVILEGIO_CREAR_MOD="PRI01";
	public static final String COD_PRIVILEGIO_ELIMINAR="PRI02";
	public static final String COD_PRIVILEGIO_BUSCAR="PRI03";
	public static final String COD_WB_CREAR_MOD="WBL01";
	public static final String COD_WB_ELIMINAR="WBL02";
	public static final String COD_WB_BUSCAR="WBL03";
	public static final String COD_OPERADOR_CREAR_MOD="OPE01";
	public static final String COD_OPERADOR_ELIMINAR="OPE02";
	public static final String COD_OPERADOR_BUSCAR="OPE03";
	public static final String COD_RANGO_OPERADOR_CREAR_MOD="RAN01";
	public static final String COD_RANGO_OPERADOR_ELIMINAR="RAN02";
	public static final String COD_RANGO_OPERADOR_BUSCAR="RAN03";
	public static final String COD_MSC_CREAR_MOD="MSC01";
	public static final String COD_MSC_ELIMINAR="MSC02";
	public static final String COD_MSC_BUSCAR="MSC03";
	public static final String COD_PARAMETRO_CREAR_MOD="PAR01";
	public static final String COD_PARAMETRO_BUSCAR="PAR01";

}
