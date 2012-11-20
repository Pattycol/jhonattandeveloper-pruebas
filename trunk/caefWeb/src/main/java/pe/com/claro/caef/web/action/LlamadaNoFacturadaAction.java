package pe.com.claro.caef.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasNoFacturadasFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.LlamadaNoFacturadaService;
import pe.com.claro.caef.web.util.CorreoDatos;
import pe.com.claro.caef.web.util.ExcelUtil;
import pe.com.claro.caef.web.util.GeneradorExcel;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value = "prototype")
public class LlamadaNoFacturadaAction extends GeneralAction implements
		Preparable {
	
	private int flag;
	private static String PATH=PropertiesCAEF.RUTA_PLANTILLA;
	@Autowired
	private LlamadaNoFacturadaService llamadaNoFacturadaService;

	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private Boolean flagBoton;
	
	public Boolean getFlagBoton() {
		return flagBoton;
	}

	public void setFlagBoton(Boolean flagBoton) {
		this.flagBoton = flagBoton;
	}

	private LlamadaNoFacturada llamadaNoFacturada;
	private List<LlamadaNoFacturada> lstLlamadaNoFacturada;
	private ConsultarListaLlamadasNoFacturadasFilter consultarListaLlamadasNoFacturadasFilter;

	@Autowired
	private ComunService comunService;

	private List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico;
	private ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter;

	@Autowired
	private CargarMensajesService cargarMensajesService;

	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;

	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroFecha;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	private boolean bFLagBtnEnviarCorreo;
	
	
	
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroFecha() {
		return lstConsultaDatosMaestroFecha;
	}

	public void setLstConsultaDatosMaestroFecha(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroFecha) {
		this.lstConsultaDatosMaestroFecha = lstConsultaDatosMaestroFecha;
	}

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	public LlamadaNoFacturadaAction() {
		consultarListaLlamadasNoFacturadasFilter = new ConsultarListaLlamadasNoFacturadasFilter();
		lstLlamadaNoFacturada = new ArrayList<LlamadaNoFacturada>();
		this.flagBoton = false;
	}

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

	public List<ConsultaNumeroTelefonico> getLstConsultaNumeroTelefonico() {
		return lstConsultaNumeroTelefonico;
	}

	public void setLstConsultaNumeroTelefonico(
			List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico) {
		this.lstConsultaNumeroTelefonico = lstConsultaNumeroTelefonico;
	}

	public ConsultarNumerosTelefonicosFilter getConsultarNumerosTelefonicosFilter() {
		return consultarNumerosTelefonicosFilter;
	}

	public void setConsultarNumerosTelefonicosFilter(
			ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter) {
		this.consultarNumerosTelefonicosFilter = consultarNumerosTelefonicosFilter;
	}

	public LlamadaNoFacturada getLlamadaNoFacturada() {
		return llamadaNoFacturada;
	}

	public void setLlamadaNoFacturada(LlamadaNoFacturada llamadaNoFacturada) {
		this.llamadaNoFacturada = llamadaNoFacturada;
	}

	public List<LlamadaNoFacturada> getLstLlamadaNoFacturada() {
		return lstLlamadaNoFacturada;
	}

	public void setLstLlamadaNoFacturada(
			List<LlamadaNoFacturada> lstLlamadaNoFacturada) {
		this.lstLlamadaNoFacturada = lstLlamadaNoFacturada;
	}

	public ConsultarListaLlamadasNoFacturadasFilter getConsultarListaLlamadasNoFacturadasFilter() {
		return consultarListaLlamadasNoFacturadasFilter;
	}

	public void setConsultarListaLlamadasNoFacturadasFilter(
			ConsultarListaLlamadasNoFacturadasFilter consultarListaLlamadasNoFacturadasFilter) {
		this.consultarListaLlamadasNoFacturadasFilter = consultarListaLlamadasNoFacturadasFilter;
	}

	public String getsNumOrigen() {
		return sNumOrigen;
	}

	public void setsNumOrigen(String sNumOrigen) {
		this.sNumOrigen = sNumOrigen;
	}

	public String getsFechaInicio() {
		return sFechaInicio;
	}

	public void setsFechaInicio(String sFechaInicio) {
		this.sFechaInicio = sFechaInicio;
	}

	public String getsFechaFin() {
		return sFechaFin;
	}

	public void setsFechaFin(String sFechaFin) {
		this.sFechaFin = sFechaFin;
	}

	public Date getDtFechaInicioRango() {
		return dtFechaInicioRango;
	}

	public void setDtFechaInicioRango(Date dtFechaInicioRango) {
		this.dtFechaInicioRango = dtFechaInicioRango;
	}

	public Date getDtFechaFinRango() {
		return dtFechaFinRango;
	}

	public void setDtFechaFinRango(Date dtFechaFinRango) {
		this.dtFechaFinRango = dtFechaFinRango;
	}
	// FIN: Set&Get
	// INICIO: MetodosAction
	public void prepare() throws Exception {
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		lstConsultaNumeroTelefonico = comunService
				.getConsultarNumerosTelefonicos(user,
						consultarNumerosTelefonicosFilter);
		/*lstConsultaNumeroTelefonico.set(0, new ConsultaNumeroTelefonico(
				"Seleccione", "Seleccione"));
		lstConsultaNumeroTelefonico.add(new ConsultaNumeroTelefonico(
				"999999999", "999999999"));*/
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("llamadaNoFacturada");
		mensajesSeguridad = cargarMensajesService
				.obtenerMensajes(mensajesSeguridadFilter);
		
		this.bFLagBtnEnviarCorreo = true;
		this.flagBoton = false;
		
		
		sNumOrigen = consultarListaLlamadasNoFacturadasFilter.getNumOrigen();
		
		GregorianCalendar objGregorianCalendar = new GregorianCalendar();
		
		try{
			consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
			consultarDatosMaestrosFilter.setCodigoCaso("CIS");
			//consultarDatosMaestrosFilter.setParametro1(user.getCodigoInstanciaServicio());
			consultarDatosMaestrosFilter.setParametro1(getUsuario().getCodigoUsuario());
			consultarDatosMaestrosFilter.setParametro2(getUsuario().getCodigoServicio());
			lstConsultaDatosMaestroFecha = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			
			consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
			consultarDatosMaestrosFilter.setCodigoCaso("FECFAC");
			consultarDatosMaestrosFilter.setParametro1(lstConsultaDatosMaestroFecha.get(0).getValorDatoMaestro1());
			consultarDatosMaestrosFilter.setParametro2("3");
			lstConsultaDatosMaestroFecha = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			
			String [] RangoFecIni = lstConsultaDatosMaestroFecha.get(0).getValorDatoMaestro1().split("/");
			String [] RangoFecFin = lstConsultaDatosMaestroFecha.get(0).getValorDatoMaestro2().split("/");
			
			String anio = RangoFecIni[2];
			String mes = RangoFecIni[1];
			String dia = RangoFecIni[0];
			
			objGregorianCalendar.set(Integer.valueOf(anio), Integer.valueOf(mes), Integer.valueOf(dia));
			this.dtFechaInicioRango = objGregorianCalendar.getTime();
			
			
			//objGregorianCalendar.set(2012, 2, 30);
			anio = RangoFecFin[2];
			mes = RangoFecFin[1];
			dia = RangoFecFin[0];
			objGregorianCalendar.set(Integer.valueOf(anio), Integer.valueOf(mes) + 2, Integer.valueOf(dia));
			this.dtFechaFinRango = objGregorianCalendar.getTime();			
			
		}catch(Exception e){
			LOG.info(e.toString());
			addActionError(MENSAJES_CONFIG.LLAMADA_NOFACTURADA6);
		}

		
	}

	public String input() {
		
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_LLAMADA__NO_FACTURADA),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_LLAMADA__NO_FACTURADA,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","se ingreso a Llamadas no Facturadas","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/

		return Action.INPUT;
	}

	String sNumOrigen = "";
	Date dtFechaInicioRango = null;
	Date dtFechaFinRango = null;
	
	String sFechaInicio = null;
	String sFechaFin = null;
	
	public String getList() {
	
			
			/**OBTENER VALORES DE SESION**************/
			Usuario userCodSer = new Usuario();
			Map codServicio = ActionContext.getContext().getSession();
			userCodSer = (Usuario)codServicio.get("codServicio");
			
			Usuario user = new Usuario();
			user = getUsuario();
			user.setCodigoServicio(userCodSer.getCodigoServicio());	
			user.setCodigoProducto(userCodSer.getCodigoProducto());
			/*****************************************/
			
			
			lstLlamadaNoFacturada = llamadaNoFacturadaService
					.getConsultarListaLlamadasNofacturadas(user,
							consultarListaLlamadasNoFacturadasFilter);

			

			if(lstLlamadaNoFacturada.size()<=0){
				if(flag==1){
					addActionError( MENSAJES_CONFIG.LLAMADA_NOFACTURADA2);
					this.bFLagBtnEnviarCorreo = true;
					this.flagBoton = false;
				}

			}
			else{
				this.bFLagBtnEnviarCorreo = false;
				this.flagBoton = true;
				
				Map lstNoFacturada = ActionContext.getContext().getSession();
				List<LlamadaNoFacturada> lstLlamadaNoFacturadaConsulta = new ArrayList<LlamadaNoFacturada>();
				lstLlamadaNoFacturadaConsulta = lstLlamadaNoFacturada;
				lstNoFacturada.put("lstNoFacturada", lstLlamadaNoFacturadaConsulta);
			}

			consultarListaLlamadasNoFacturadasFilter.setNumOrigen(this.sNumOrigen);
			consultarListaLlamadasNoFacturadasFilter.setFecInicio(this.sFechaInicio);
			consultarListaLlamadasNoFacturadasFilter.setFecFin(this.sFechaFin);
			
			DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_LLAMADA__NO_FACTURADA),getUsuario().getCodigoUsuario(),
					PropertiesCAEFAudit.DES_LLAMADA__NO_FACTURADA,getUsuario().getTelefonoMiClaroFija(),
					"","","","","","consultar","","",ma.getIP());
			/*try {
				sender.send(message);
			} catch (JMSException e) {
				LOG.info(e.toString());
			}*/
			
			//return Action.SUCCESS;

		return Action.SUCCESS;
		
	}

	

	public String obtenerFechas() {

		return Action.INPUT;
	}

	public void validate() {
		
		
	
			/**OBTENER VALORES DE SESION**************/
			Usuario userCodSer = new Usuario();
			Map codServicio = ActionContext.getContext().getSession();
			userCodSer = (Usuario)codServicio.get("codServicio");
			
			Usuario user = new Usuario();
			user = getUsuario();
			user.setCodigoServicio(userCodSer.getCodigoServicio());	
			user.setCodigoProducto(userCodSer.getCodigoProducto());
			/*****************************************/
			
			try{
			
			
			
			
			
			
			
			
			if (consultarListaLlamadasNoFacturadasFilter.getNumOrigen().equalsIgnoreCase("Seleccione")) {
				if(flag==1){
					this.sFechaInicio = "";
					this.sFechaFin = "";
					consultarListaLlamadasNoFacturadasFilter.setFecInicio(sFechaInicio);
					consultarListaLlamadasNoFacturadasFilter.setFecFin(sFechaFin);
					addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_NUMERO);
				}

			}
			else {
				sNumOrigen = consultarListaLlamadasNoFacturadasFilter.getNumOrigen();
				
				GregorianCalendar objGregorianCalendar = new GregorianCalendar();
				
				
				consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
				consultarDatosMaestrosFilter.setCodigoCaso("CIS");
				//consultarDatosMaestrosFilter.setParametro1(user.getCodigoInstanciaServicio());
				consultarDatosMaestrosFilter.setParametro1(getUsuario().getCodigoUsuario());
				consultarDatosMaestrosFilter.setParametro2(getUsuario().getCodigoServicio());
				lstConsultaDatosMaestroFecha = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				
				consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
				consultarDatosMaestrosFilter.setCodigoCaso("FECFAC");
				consultarDatosMaestrosFilter.setParametro1(lstConsultaDatosMaestroFecha.get(0).getValorDatoMaestro1());
				consultarDatosMaestrosFilter.setParametro2("3");
				lstConsultaDatosMaestroFecha = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				
				String [] RangoFecIni = lstConsultaDatosMaestroFecha.get(0).getValorDatoMaestro1().split("/");
				String [] RangoFecFin = lstConsultaDatosMaestroFecha.get(0).getValorDatoMaestro2().split("/");
				
				String anio = RangoFecIni[2];
				String mes = RangoFecIni[1];
				String dia = RangoFecIni[0];
				
				objGregorianCalendar.set(Integer.valueOf(anio), Integer.valueOf(mes), Integer.valueOf(dia));
				this.dtFechaInicioRango = objGregorianCalendar.getTime();
				
				
				//objGregorianCalendar.set(2012, 2, 30);
				anio = RangoFecFin[2];
				mes = RangoFecFin[1];
				dia = RangoFecFin[0];
				objGregorianCalendar.set(Integer.valueOf(anio), Integer.valueOf(mes) + 2, Integer.valueOf(dia));
				this.dtFechaFinRango = objGregorianCalendar.getTime();
				
				
				int diaIni = 0;
				int diafin = 0;
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Calendar fecIni = Calendar.getInstance();
					Calendar fecFin = Calendar.getInstance();
					fecIni.setTime(sdf.parse(consultarListaLlamadasNoFacturadasFilter.getFecInicio()));
					fecFin.setTime(sdf.parse(consultarListaLlamadasNoFacturadasFilter.getFecFin()));
					diaIni = fecIni.get(Calendar.DAY_OF_YEAR);
					diafin = fecFin.get(Calendar.DAY_OF_YEAR);
				} catch (Exception e) {
						LOG.info(e.toString());
				}
				
				if (consultarListaLlamadasNoFacturadasFilter.getNumOrigen().equalsIgnoreCase("Seleccione")) {
					if(flag==1){
						this.sFechaInicio = "";
						this.sFechaFin = "";
						consultarListaLlamadasNoFacturadasFilter.setFecInicio(sFechaInicio);
						consultarListaLlamadasNoFacturadasFilter.setFecFin(sFechaFin);
						addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_NUMERO);
					}

				}
				else if((diafin - diaIni) > 30 )
				{
					if(flag==1){
						addActionError( MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHARANGO);
					}
					
				}
				else
				{
				if (consultarListaLlamadasNoFacturadasFilter.getFecInicio().length() == 0) {
					if(flag==1){
						addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHAINICIO);
					}	

					
				} else if (consultarListaLlamadasNoFacturadasFilter.getFecFin().length() == 0) {
					if(flag==1){
						addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHAFIN);
					}
					
				}
				else {

					/*sNumOrigen = consultarListaLlamadasNoFacturadasFilter.getNumOrigen();
					
					GregorianCalendar objGregorianCalendar1 = new GregorianCalendar();
					objGregorianCalendar1.set(2012, 2, 26);
					this.dtFechaInicioRango = objGregorianCalendar1.getTime();
					objGregorianCalendar1.set(2012, 2, 30);
					this.dtFechaFinRango = objGregorianCalendar1.getTime();*/
		
					if (consultarListaLlamadasNoFacturadasFilter.getFecInicio().length() == 0) {
						if(flag==1){
							addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHAINICIO);
						}	
						
					} else if (consultarListaLlamadasNoFacturadasFilter.getFecFin().length() == 0) {
						if(flag==1){
							addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHAFIN);
						}
						
					} else {
						try {
							/*sFechaInicio = consultarListaLlamadasNoFacturadasFilter.getFecInicio();
							sFechaFin = consultarListaLlamadasNoFacturadasFilter.getFecFin();*/
							
							SimpleDateFormat formatoFecha = new SimpleDateFormat(
									"dd/MM/yyyy");
							Date fechaInicio = formatoFecha
									.parse(consultarListaLlamadasNoFacturadasFilter
											.getFecInicio());
							Date fechaFin = formatoFecha
									.parse(consultarListaLlamadasNoFacturadasFilter
											.getFecFin());
							if (fechaInicio.getTime() > fechaFin.getTime()) {
								if(flag==1){
									addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHARANGO);
								}
								
							}
						} catch (Exception e) {
							LOG.info(e.toString());
							addActionError(MENSAJES_CONFIG.VALIDA_LLAMADA_NO_FACTURADA_FECHA);
						}
		
					}
				}
				}
			  }
			
			}
			catch (Exception e) {
				LOG.info(e.toString());
			}
			
		
		
		
			
		

	}

	// LLAMADA AJAX PARA ENVIAR CORREO
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public String enviarCorreo() throws Exception {
		
		/**OBTENER VALORES DE SESION**************/
		/*Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		/*
		lstLlamadaNoFacturada = llamadaNoFacturadaService.getConsultarListaLlamadasNofacturadas(user, consultarListaLlamadasNoFacturadasFilter);*/

		List<LlamadaNoFacturada> lstLlamadaNoFacturadaConsulta = new ArrayList<LlamadaNoFacturada>();
		Map lstNoFacturada = ActionContext.getContext().getSession();
		lstLlamadaNoFacturadaConsulta = (List<LlamadaNoFacturada>)lstNoFacturada.get("lstNoFacturada");
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String path = servletContext.getRealPath(PATH+"llamadasNoFacturadas.xml");
		
		String nuevopath = ExcelUtil.convertirPath(path);
		
		File f = GeneradorExcel.generarReporteLlamadasNoFacturadas(lstLlamadaNoFacturadaConsulta,nuevopath);

		CorreoDatos datos = new CorreoDatos();
		datos.setArchivo(f);
		datos.setDestinatario(getUsuario().getCorreoCliente());
		datos.setAsunto("Correo Electronico");
		datos.setContenido(PropertiesCAEF.cuerpoMensaje(getUsuario().getNombreUsuario()));
		
		try {

			enviarCorreoService.enviarCorreo(datos);
			inputStream = new StringBufferInputStream(MENSAJES_CONFIG.LLAMADA_NOFACTURADA4);

		} catch (Exception e) {
			inputStream = new StringBufferInputStream(
					MENSAJES_CONFIG.LLAMADA_NOFACTURADA5);
			LOG.info(e.toString());
		}

		return "mail";

	}

	private InputStream fileInputStream;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String download() throws Exception {
		
		/**OBTENER VALORES DE SESION**************/
		/*Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		/*
		lstLlamadaNoFacturada = llamadaNoFacturadaService.getConsultarListaLlamadasNofacturadas(user,consultarListaLlamadasNoFacturadasFilter);*/
		
		List<LlamadaNoFacturada> lstLlamadaNoFacturadaConsulta = new ArrayList<LlamadaNoFacturada>();
		Map lstNoFacturada = ActionContext.getContext().getSession();
		lstLlamadaNoFacturadaConsulta = (List<LlamadaNoFacturada>)lstNoFacturada.get("lstNoFacturada");
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String path = servletContext.getRealPath(PATH+"llamadasNoFacturadas.xml");
		
		String nuevopath = ExcelUtil.convertirPath(path);
		
		File f = GeneradorExcel.generarReporteLlamadasNoFacturadas(lstLlamadaNoFacturadaConsulta,nuevopath);

		fileInputStream = new FileInputStream(f);
		
		return "download";

	}

	public boolean isbFLagBtnEnviarCorreo() {
		return bFLagBtnEnviarCorreo;
	}

	public void setbFLagBtnEnviarCorreo(boolean bFLagBtnEnviarCorreo) {
		this.bFLagBtnEnviarCorreo = bFLagBtnEnviarCorreo;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	

}
