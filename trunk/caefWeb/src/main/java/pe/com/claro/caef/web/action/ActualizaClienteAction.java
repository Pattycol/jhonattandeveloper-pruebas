package pe.com.claro.caef.web.action;

import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ActualizarDatosClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ActualizaCliente;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.ActualizaClienteService;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.ConsultaClienteService;
import pe.com.claro.caef.web.util.Constantes;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class ActualizaClienteAction extends GeneralAction  implements Preparable{

	@Autowired
	private ActualizaClienteService actualizaClienteService;
	
	private ActualizaCliente actualizaCliente;
	private ActualizarDatosClienteFilter actualizarDatosClienteFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipVia;

	private Boolean flagBoton;
	@Autowired
	private CargarMensajesService cargarMensajesService;
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/

	public MensajesSeguridadFilter getMensajesSeguridadFilter() {
		return mensajesSeguridadFilter;
	}

	public void setMensajesSeguridadFilter(
			MensajesSeguridadFilter mensajesSeguridadFilter) {
		this.mensajesSeguridadFilter = mensajesSeguridadFilter;
	}

	public MensajesSeguridad getMensajesSeguridad() {
		return mensajesSeguridad;
	}

	public void setMensajesSeguridad(MensajesSeguridad mensajesSeguridad) {
		this.mensajesSeguridad = mensajesSeguridad;
	}

	public Boolean getFlagBoton() {
		return flagBoton;
	}

	public void setFlagBoton(Boolean flagBoton) {
		this.flagBoton = flagBoton;
	}

	@Autowired
	private ComunService comunService;
	
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipDomicilio;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroDistrito;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroCiudad;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroNacionalidad;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroSexo;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroEstadoCivil;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroEstadoCivil() {
		return lstConsultaDatosMaestroEstadoCivil;
	}

	public void setLstConsultaDatosMaestroEstadoCivil(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroEstadoCivil) {
		this.lstConsultaDatosMaestroEstadoCivil = lstConsultaDatosMaestroEstadoCivil;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroSexo() {
		return lstConsultaDatosMaestroSexo;
	}

	public void setLstConsultaDatosMaestroSexo(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroSexo) {
		this.lstConsultaDatosMaestroSexo = lstConsultaDatosMaestroSexo;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroNacionalidad() {
		return lstConsultaDatosMaestroNacionalidad;
	}

	public void setLstConsultaDatosMaestroNacionalidad(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroNacionalidad) {
		this.lstConsultaDatosMaestroNacionalidad = lstConsultaDatosMaestroNacionalidad;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroTipVia() {
		return lstConsultaDatosMaestroTipVia;
	}

	public void setLstConsultaDatosMaestroTipVia(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipVia) {
		this.lstConsultaDatosMaestroTipVia = lstConsultaDatosMaestroTipVia;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroTipDomicilio() {
		return lstConsultaDatosMaestroTipDomicilio;
	}

	public void setLstConsultaDatosMaestroTipDomicilio(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipDomicilio) {
		this.lstConsultaDatosMaestroTipDomicilio = lstConsultaDatosMaestroTipDomicilio;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroDistrito() {
		return lstConsultaDatosMaestroDistrito;
	}

	public void setLstConsultaDatosMaestroDistrito(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroDistrito) {
		this.lstConsultaDatosMaestroDistrito = lstConsultaDatosMaestroDistrito;
	}

	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroCiudad() {
		return lstConsultaDatosMaestroCiudad;
	}

	public void setLstConsultaDatosMaestroCiudad(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroCiudad) {
		this.lstConsultaDatosMaestroCiudad = lstConsultaDatosMaestroCiudad;
	}

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	private ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter;
	
	public ConsultarDatosGenClienteFilter getConsultarDatosGenClienteFilter() {
		return consultarDatosGenClienteFilter;
	}

	public void setConsultarDatosGenClienteFilter(
			ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter) {
		this.consultarDatosGenClienteFilter = consultarDatosGenClienteFilter;
	}
	
	private ConsultaCliente consultaCliente;
	
	public ConsultaCliente getConsultaCliente() {
		return consultaCliente;
	}

	public void setConsultaCliente(ConsultaCliente consultaCliente) {
		this.consultaCliente = consultaCliente;
	}
	
	//INICIO: Set&Get
	public ActualizaCliente getActualizaCliente() {
		return actualizaCliente;
	}
	public void setActualizaCliente(ActualizaCliente actualizaCliente) {
		this.actualizaCliente = actualizaCliente;
	}
	public ActualizarDatosClienteFilter getActualizarDatosClienteFilter() {
		return actualizarDatosClienteFilter;
	}
	public void setActualizarDatosClienteFilter(
			ActualizarDatosClienteFilter actualizarDatosClienteFilter) {
		this.actualizarDatosClienteFilter = actualizarDatosClienteFilter;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
	
	public void prepare() throws Exception
	{		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		//SET VALOR COMBOBOX: TIPO VIA
		consultarDatosMaestrosFilter.setCodigoCaso("TIPVIA");
		lstConsultaDatosMaestroTipVia = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: TIPO DOMICILIO
		consultarDatosMaestrosFilter.setCodigoCaso("TIPDOM");
		consultarDatosMaestrosFilter.setCodigoEstado("1");	//codEstado
		consultarDatosMaestrosFilter.setParametro1("");		//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstConsultaDatosMaestroTipDomicilio = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: DISTRITO
		consultarDatosMaestrosFilter.setCodigoCaso("CODDST");
		consultarDatosMaestrosFilter.setCodigoEstado("");
		consultarDatosMaestrosFilter.setParametro1("51");	//codPais
		consultarDatosMaestrosFilter.setParametro2("1");	//codEstado
		consultarDatosMaestrosFilter.setParametro3("1");	//codPvc
		consultarDatosMaestrosFilter.setParametro4("1");	//codDst
		lstConsultaDatosMaestroDistrito = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: CIUDAD
		consultarDatosMaestrosFilter.setCodigoCaso("CODPVC");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//empty
		consultarDatosMaestrosFilter.setParametro1("51");	//codPaís
		consultarDatosMaestrosFilter.setParametro2("1");	//codEstado
		consultarDatosMaestrosFilter.setParametro3("1");	//codEstado
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstConsultaDatosMaestroCiudad = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: NACIONALIDAD
		consultarDatosMaestrosFilter.setCodigoCaso("CODNAC");
		consultarDatosMaestrosFilter.setCodigoEstado("");
		consultarDatosMaestrosFilter.setParametro1(null);
		consultarDatosMaestrosFilter.setParametro2(null);
		consultarDatosMaestrosFilter.setParametro3("");
		consultarDatosMaestrosFilter.setParametro4("");
		lstConsultaDatosMaestroNacionalidad = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: SEXO
		consultarDatosMaestrosFilter.setCodigoCaso("CODGEN");
		consultarDatosMaestrosFilter.setCodigoEstado("");
		consultarDatosMaestrosFilter.setParametro1("");
		consultarDatosMaestrosFilter.setParametro2("");
		consultarDatosMaestrosFilter.setParametro3("");
		consultarDatosMaestrosFilter.setParametro4("");
		lstConsultaDatosMaestroSexo = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: ESTADO CIVIL
		consultarDatosMaestrosFilter.setCodigoCaso("ESTCIV");
		consultarDatosMaestrosFilter.setCodigoEstado("1");
		consultarDatosMaestrosFilter.setParametro1("");
		consultarDatosMaestrosFilter.setParametro2("");
		consultarDatosMaestrosFilter.setParametro3("");
		consultarDatosMaestrosFilter.setParametro4("");
		lstConsultaDatosMaestroEstadoCivil	 = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
	}
	public String input()
	{
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		consultaCliente = actualizaClienteService.getActualizarDatosCliente(user, consultarDatosGenClienteFilter);
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("objConsultaCliente", consultaCliente);
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_ACT_CLIENTE),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ACT_CLIENTE,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado actualizacion del cliente","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
		return Action.INPUT;
	}	
	
	public String execute()
	{
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		ConsultaCliente objConsultaCliente = new ConsultaCliente();
		if(getUsuario().getsFlagUsuario().equals(Constantes.CORPORATIVO)){
			objConsultaCliente = (ConsultaCliente)ActionContext.getContext().getSession().get("objConsultaCliente");
			objConsultaCliente.setValReferencia(consultaCliente.getValReferencia());
			//objConsultaCliente.setNomCargoEsp(consultaCliente.getNomCargoEsp());
			objConsultaCliente.setFecNacimiento(consultaCliente.getFecNacimiento());
			objConsultaCliente.setCodNacionalidad(consultaCliente.getCodNacionalidad());
			objConsultaCliente.setCodGenero(consultaCliente.getCodGenero());
			objConsultaCliente.setCodEstadoCivil(consultaCliente.getCodEstadoCivil());
		}
		else if(getUsuario().getsFlagUsuario().equals(Constantes.RESIDENCIAL)){
			objConsultaCliente = (ConsultaCliente)ActionContext.getContext().getSession().get("objConsultaCliente");
			objConsultaCliente.setValCorreoElectronico(consultaCliente.getValCorreoElectronico());
			objConsultaCliente.setNumVia(consultaCliente.getNumVia());
			objConsultaCliente.setCodTipoDomicilio(consultaCliente.getCodTipoDomicilio());
			objConsultaCliente.setDesDomicilio(consultaCliente.getDesDomicilio());
			objConsultaCliente.setValPiso(consultaCliente.getValPiso());
			objConsultaCliente.setValManzana(consultaCliente.getValManzana());
			objConsultaCliente.setValLote(consultaCliente.getValLote());
			objConsultaCliente.setValSector(consultaCliente.getValSector());
			objConsultaCliente.setValReferencia(consultaCliente.getValReferencia());
			objConsultaCliente.setNomCargoEsp(consultaCliente.getNomCargoEsp());
			objConsultaCliente.setFecNacimiento(consultaCliente.getFecNacimiento());
			objConsultaCliente.setCodNacionalidad(consultaCliente.getCodNacionalidad());
			objConsultaCliente.setCodGenero(consultaCliente.getCodGenero());
			objConsultaCliente.setCodEstadoCivil(consultaCliente.getCodEstadoCivil());
			mensajesSeguridadFilter = new MensajesSeguridadFilter();
			mensajesSeguridadFilter.setTipoPantalla("actualizaCliente");
			mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		}
		
		//Se valida que el numero de Vía se sólo númerico
		if(consultaCliente.getNumVia().matches(".*\\D+.*")){
			addActionError(MENSAJES_CONFIG.ACTUALIZA_CLIENTE_SOLO_NUMERO);
			consultaCliente = actualizaClienteService.getActualizarDatosCliente(user, consultarDatosGenClienteFilter);
			return Action.INPUT;
			
		}
		
		AuditTypes at = new AuditTypes();
		at = actualizaClienteService.actualizarDatosCliente(user, objConsultaCliente);	
		
		if(!at.getCodError().equals("0"))
		{
			addActionError( MENSAJES_CONFIG.VALIDA_ACTUALIZA_ERROR);
			consultaCliente = actualizaClienteService.getActualizarDatosCliente(user, consultarDatosGenClienteFilter);
			//flagBoton = true;
			return Action.INPUT;
		}
		else{
			consultaCliente = actualizaClienteService.getActualizarDatosCliente(user, consultarDatosGenClienteFilter);
			if(consultaCliente!=null){
				addActionMessage(mensajesSeguridad.getMensaje1());
				flagBoton = true;
			}
			else{
				addActionError(MENSAJES_CONFIG.VALIDA_ACTUALIZA_ERROR);
			}
		}
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_ACT_CLIENTE),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ACT_CLIENTE,user.getTelefonoMiClaroFija(),
				"","","","","","Aceptar","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
		 
		//REMOVER LA SESSION
		Map session = ActionContext.getContext().getSession();
		session.remove("objConsultaCliente");
		
		return Action.SUCCESS;
	}
	
	public void validate(){
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		boolean val = false;
		 
		if(getUsuario().getsFlagUsuario().equals(Constantes.CORPORATIVO)){
			
			if(consultaCliente.getValReferencia().length()==0){val=true;
			addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_REFERENCIA);}
			//else if(consultaCliente.getNomCargoEsp().length()==0){val=true;
				//addActionError( getText("validar.consultaCliente.nomCargoEsp"));}
			else if(consultaCliente.getFecNacimiento().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_FECHA_NACIMIENTO);}
			else if(consultaCliente.getCodNacionalidad().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_NACIONALIDAD);}
			else if(consultaCliente.getCodGenero().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_CODIGO_GENERO);}
			else if(consultaCliente.getCodEstadoCivil().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_ESTADO_CIVIL);}
			
		}
		else if(getUsuario().getsFlagUsuario().equals(Constantes.RESIDENCIAL)){
			
			if(consultaCliente.getValCorreoElectronico().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_CORREO_ELECTRONICO);}
			else if(consultaCliente.getNumVia().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_NUMERO_VIA);}
			else if(consultaCliente.getCodTipoDomicilio().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_TIPO_DOMICILIO);}
			else if(consultaCliente.getDesDomicilio().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_DES_DOMICILIO);}
			else if(consultaCliente.getValPiso().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_VAL_PISO);}
			else if(consultaCliente.getValManzana().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_VAL_MANZANA);}
			else if(consultaCliente.getValLote().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_VAL_LOTE);}
			else if(consultaCliente.getValSector().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_VAL_SECTOR);}
			else if(consultaCliente.getValReferencia().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_REFERENCIA);}
			else if(consultaCliente.getNomCargoEsp().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_NOM_CARGO);}
			else if(consultaCliente.getFecNacimiento().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_FECHA_NACIMIENTO);}
			else if(consultaCliente.getCodNacionalidad().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_NACIONALIDAD);}
			else if(consultaCliente.getCodGenero().length()==0){val=true;
				addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_CODIGO_GENERO);}
			else if(consultaCliente.getCodEstadoCivil().length()==0){val=true;
				addActionError(MENSAJES_CONFIG.VALIDA_CONSULTA_CLIENTE_CONSULTA_ESTADO_CIVIL);}
			
		}
		
		
		if(val == true)
		{
			consultaCliente = actualizaClienteService.getActualizarDatosCliente(user, consultarDatosGenClienteFilter);
		}
	}
	//FIN: MetodosAction
}
