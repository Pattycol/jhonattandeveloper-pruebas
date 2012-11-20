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

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasEntrantesFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.LlamadaEntrante;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.LlamadaEntranteService;
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
@Scope(value="prototype")
public class LlamadaEntranteAction extends GeneralAction implements Preparable{
	
	@Autowired
	private LlamadaEntranteService llamadaEntranteService;
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	private static String PATH=PropertiesCAEF.RUTA_PLANTILLA;
	private Boolean flagBoton;
	
	public Boolean getFlagBoton() {
		return flagBoton;
	}

	public void setFlagBoton(Boolean flagBoton) {
		this.flagBoton = flagBoton;
	}
	
	private LlamadaEntrante llamadaEntrante;
	private List<LlamadaEntrante> lstLlamadaEntrante;
	private ConsultarListaLlamadasEntrantesFilter consultarListaLlamadasEntrantesFilter;
	
	@Autowired
	private ComunService comunService;
	private List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico;
	private ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroFecha;
	
	private boolean bFLagBtnEnviarCorreo;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	private int flag;

	String sNumOrigen = "";
	Date dtFechaInicioRango = null;
	Date dtFechaFinRango = null;
	
	String sFechaInicio = null;
	String sFechaFin = null;
	
	public LlamadaEntranteAction(){
		consultarListaLlamadasEntrantesFilter = new ConsultarListaLlamadasEntrantesFilter();
		this.flagBoton = false;
	}
	
	String dtFechaIni;
	String dtFechaFin;
	
	public boolean isbFLagBtnEnviarCorreo() {
		return bFLagBtnEnviarCorreo;
	}
	public void setbFLagBtnEnviarCorreo(boolean bFLagBtnEnviarCorreo) {
		this.bFLagBtnEnviarCorreo = bFLagBtnEnviarCorreo;
	}
	public String getDtFechaFin() {
		return dtFechaFin;
	}
	public void setDtFechaFin(String dtFechaFin) {
		this.dtFechaFin = dtFechaFin;
	}
	public String getDtFechaIni() {
		return dtFechaIni;
	}
	public void setDtFechaIni(String dtFechaIni) {
		this.dtFechaIni = dtFechaIni;
	}
	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}
	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroFecha() {
		return lstConsultaDatosMaestroFecha;
	}
	public void setLstConsultaDatosMaestroFecha(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroFecha) {
		this.lstConsultaDatosMaestroFecha = lstConsultaDatosMaestroFecha;
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
	public LlamadaEntrante getLlamadaEntrante() {
		return llamadaEntrante;
	}
	public void setLlamadaEntrante(LlamadaEntrante llamadaEntrante) {
		this.llamadaEntrante = llamadaEntrante;
	}
	public List<LlamadaEntrante> getLstLlamadaEntrante() {
		return lstLlamadaEntrante;
	}
	public void setLstLlamadaEntrante(List<LlamadaEntrante> lstLlamadaEntrante) {
		this.lstLlamadaEntrante = lstLlamadaEntrante;
	}
	public ConsultarListaLlamadasEntrantesFilter getConsultarListaLlamadasEntrantesFilter() {
		return consultarListaLlamadasEntrantesFilter;
	}
	public void setConsultarListaLlamadasEntrantesFilter(
			ConsultarListaLlamadasEntrantesFilter consultarListaLlamadasEntrantesFilter) {
		this.consultarListaLlamadasEntrantesFilter = consultarListaLlamadasEntrantesFilter;
	}

	public String getsNumOrigen() {
		return sNumOrigen;
	}
	public void setsNumOrigen(String sNumOrigen) {
		this.sNumOrigen = sNumOrigen;
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
		lstConsultaNumeroTelefonico = comunService.getConsultarNumerosTelefonicos(user, consultarNumerosTelefonicosFilter);
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("llamadaEntrante");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		this.bFLagBtnEnviarCorreo = false;
		this.flagBoton = false;
	}
	public String input()
	{		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_LLAMADA__ENTRANTE),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_LLAMADA__ENTRANTE,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","se ingreso a Llamada Entrante","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
		
		return Action.INPUT;
	}	
	
	public String getList()
	{
		if(flag==1){
			/**OBTENER VALORES DE SESION**************/
			Usuario userCodSer = new Usuario();
			Map codServicio = ActionContext.getContext().getSession();
			userCodSer = (Usuario)codServicio.get("codServicio");
			
			Usuario user = new Usuario();
			user = getUsuario();
			user.setCodigoServicio(userCodSer.getCodigoServicio());	
			user.setCodigoProducto(userCodSer.getCodigoProducto());
			/*****************************************/
			consultarListaLlamadasEntrantesFilter.setNumeroPagina("-1");
			/*consultarListaLlamadasEntrantesFilter.setFecFin(sFechaFin);
			consultarListaLlamadasEntrantesFilter.setFecInicio(sFechaInicio);*/
			consultarListaLlamadasEntrantesFilter.setNumDestino(sNumOrigen);
			lstLlamadaEntrante = llamadaEntranteService.getConsultarListaLlamadasEntrantes(user, consultarListaLlamadasEntrantesFilter);
			//lstLlamadaEntrante=new ArrayList<LlamadaEntrante>();
			if(lstLlamadaEntrante.size()<=0){
				addActionError(MENSAJES_CONFIG.LLAMADA_ENTRANTE5);
				this.bFLagBtnEnviarCorreo = true;
				this.flagBoton = false;
			}
			else{
				this.bFLagBtnEnviarCorreo = false;
				this.flagBoton = true;
				
				Map lstllamadaEntrante = ActionContext.getContext().getSession();
				List<LlamadaEntrante> lstLlamadaEntranteConsulta = new ArrayList<LlamadaEntrante>();
				lstLlamadaEntranteConsulta = lstLlamadaEntrante;
				lstllamadaEntrante.put("lstLlamadaEntrante", lstLlamadaEntranteConsulta);
			}
			
			
			DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_LLAMADA__ENTRANTE),getUsuario().getCodigoUsuario(),
					PropertiesCAEFAudit.DES_LLAMADA__ENTRANTE,getUsuario().getTelefonoMiClaroFija(),
					"","","","","","consultar","","",ma.getIP());
			/*try {
				sender.send(message);
			} catch (JMSException e) {
				LOG.info(e.toString());
			}*/
		}else{
			LOG.info("no se ejecuta input");
		}
		
		return Action.SUCCESS;
	}
	
	public String obtenerFechas() {
		return Action.INPUT;
	}
	
	public void validate(){
		
		if(flag==1){
			//**OBTENER VALORES DE SESION**************//
			Usuario userCodSer = new Usuario();
			Map codServicio = ActionContext.getContext().getSession();
			userCodSer = (Usuario)codServicio.get("codServicio");
			
			Usuario user = new Usuario();
			user = getUsuario();
			user.setCodigoServicio(userCodSer.getCodigoServicio());	
			user.setCodigoProducto(userCodSer.getCodigoProducto());
			//*****************************************//
			
			try {
			if(consultarListaLlamadasEntrantesFilter.getNumDestino().equalsIgnoreCase("Seleccione"))
			{
				this.sFechaInicio = "";
				this.sFechaFin = "";
				consultarListaLlamadasEntrantesFilter.setFecInicio(sFechaInicio);
				consultarListaLlamadasEntrantesFilter.setFecFin(sFechaFin);
				addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_NUMERO);
			}
			else
			{
				sNumOrigen = consultarListaLlamadasEntrantesFilter.getNumDestino();
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
					fecIni.setTime(sdf.parse(consultarListaLlamadasEntrantesFilter.getFecInicio()));
					fecFin.setTime(sdf.parse(consultarListaLlamadasEntrantesFilter.getFecFin()));
					diaIni = fecIni.get(Calendar.DAY_OF_YEAR);
					diafin = fecFin.get(Calendar.DAY_OF_YEAR);
				} catch (Exception e) {
					LOG.info(e.toString());
				}
				
				if(consultarListaLlamadasEntrantesFilter.getNumDestino().equalsIgnoreCase("Seleccione"))
				{
					this.sFechaInicio = "";
					this.sFechaFin = "";
					consultarListaLlamadasEntrantesFilter.setFecInicio(sFechaInicio);
					consultarListaLlamadasEntrantesFilter.setFecFin(sFechaFin);
					addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_NUMERO);
				}
				else if((diafin - diaIni) > 30 )
				{
					addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_RANGO_MAYOR);
				}
				else
				{
					if(consultarListaLlamadasEntrantesFilter.getFecInicio().length() == 0)
					{
						addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_FECHA_INICIO);
					}
					else if(consultarListaLlamadasEntrantesFilter.getFecFin().length()==0)
					{
						addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_FECHA_FIN);
					}
					else{
					/*sNumOrigen = consultarListaLlamadasEntrantesFilter.getNumDestino();
					GregorianCalendar objGregorianCalendar1 = new GregorianCalendar();
					objGregorianCalendar1.set(2012, 2, 26);
					this.dtFechaInicioRango = objGregorianCalendar1.getTime();
					objGregorianCalendar1.set(2012, 2, 30);
					this.dtFechaFinRango = objGregorianCalendar1.getTime();*/
					
					if(consultarListaLlamadasEntrantesFilter.getFecInicio().length() == 0)
					{
						addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_FECHA_INICIO);
					}
					else if(consultarListaLlamadasEntrantesFilter.getFecFin().length()==0)
					{
						addActionError( MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_FECHA_FIN);
					}
					else
					{
						try
						{
							/*sFechaInicio = consultarListaLlamadasEntrantesFilter.getFecInicio();
							sFechaFin = consultarListaLlamadasEntrantesFilter.getFecFin();*/
							SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
							Date fechaInicio = formatoFecha.parse(consultarListaLlamadasEntrantesFilter.getFecInicio());
							Date fechaFin = formatoFecha.parse(consultarListaLlamadasEntrantesFilter.getFecFin());
							if (fechaInicio.getTime() > fechaFin.getTime()) {
								addActionError(MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_FECHA_RANGO);
							}
						}
						catch (Exception e)
						{
							LOG.info(e.toString());
							addActionError(MENSAJES_CONFIG.VALIDA_LISTA_LLAMADAS_ENTRANTES_FECHA);
						}
					}
					}
				
				}
				
			  }	
			} catch (Exception e) {
				LOG.info(e.toString());
			}
		}else{
			LOG.info("no se ejecuta validate");
		}	
	}
	
	// LLAMADA AJAX PARA ENVIAR CORREO
		 private InputStream inputStream;
		    public InputStream getInputStream() {
		        return inputStream;
		    }

		
		public String enviarCorreo() throws Exception
		{
			/**OBTENER VALORES DE SESION**************/
			/*Usuario userCodSer = new Usuario();
			Map codServicio = ActionContext.getContext().getSession();
			userCodSer = (Usuario)codServicio.get("codServicio");
			
			Usuario user = new Usuario();
			user = getUsuario();
			user.setCodigoServicio(userCodSer.getCodigoServicio());	
			user.setCodigoProducto(userCodSer.getCodigoProducto());
			/*****************************************/
			/*consultarListaLlamadasEntrantesFilter.setNumeroPagina("-1");
			consultarListaLlamadasEntrantesFilter.setFecFin(sFechaFin);
			consultarListaLlamadasEntrantesFilter.setFecInicio(sFechaInicio);
			consultarListaLlamadasEntrantesFilter.setNumDestino(sNumOrigen);
			lstLlamadaEntrante = llamadaEntranteService.getConsultarListaLlamadasEntrantes(user, consultarListaLlamadasEntrantesFilter);*/
			
			List<LlamadaEntrante> lstLlamadaEntranteConsulta = new ArrayList<LlamadaEntrante>();
			Map lstLlamadaEntrante = ActionContext.getContext().getSession();
			lstLlamadaEntranteConsulta = (List<LlamadaEntrante>)lstLlamadaEntrante.get("lstLlamadaEntrante");
			
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = servletContext.getRealPath(PATH+"llamadasEntrantes.xml");
			
			String nuevopath = ExcelUtil.convertirPath(path);
			
			File f = GeneradorExcel.generarReporteLlamadasEntrantes(lstLlamadaEntranteConsulta,nuevopath);
			
			CorreoDatos datos = new CorreoDatos();
			datos.setArchivo(f);
			datos.setDestinatario(getUsuario().getCorreoCliente());
			datos.setAsunto("Llamadas entrantes");
			datos.setContenido(PropertiesCAEF.cuerpoMensaje(getUsuario().getNombreUsuario()));
			
			try{
				
				enviarCorreoService.enviarCorreo(datos);
				inputStream = new StringBufferInputStream(MENSAJES_CONFIG.LLAMADA_ENTRANTE2);
				
			}catch(Exception e)
			{
				inputStream = new StringBufferInputStream(MENSAJES_CONFIG.LLAMADA_ENTRANTE3);
				LOG.info(e.toString());
			}
			
			
			return "mail";
			
		}
		
		
		private InputStream fileInputStream;
		 
		public InputStream getFileInputStream() {
			return fileInputStream;
		}

		    
		public String download() throws Exception
		{
			/**OBTENER VALORES DE SESION**************/
			/*Usuario userCodSer = new Usuario();
			Map codServicio = ActionContext.getContext().getSession();
			userCodSer = (Usuario)codServicio.get("codServicio");
			
			Usuario user = new Usuario();
			user = getUsuario();
			user.setCodigoServicio(userCodSer.getCodigoServicio());	
			user.setCodigoProducto(userCodSer.getCodigoProducto());
			/*****************************************/
			/*consultarListaLlamadasEntrantesFilter.setNumeroPagina("-1");
			consultarListaLlamadasEntrantesFilter.setFecFin(sFechaFin);
			consultarListaLlamadasEntrantesFilter.setFecInicio(sFechaInicio);
			consultarListaLlamadasEntrantesFilter.setNumDestino(sNumOrigen);
			
			
			
			
			lstLlamadaEntrante = llamadaEntranteService.getConsultarListaLlamadasEntrantes(user, consultarListaLlamadasEntrantesFilter);*/
			
			
			List<LlamadaEntrante> lstLlamadaEntranteConsulta = new ArrayList<LlamadaEntrante>();
			Map lstLlamadaEntrante = ActionContext.getContext().getSession();
			lstLlamadaEntranteConsulta = (List<LlamadaEntrante>)lstLlamadaEntrante.get("lstLlamadaEntrante");
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = servletContext.getRealPath(PATH+"llamadasEntrantes.xml");
			
			String nuevopath = ExcelUtil.convertirPath(path);
			
			
			File f = GeneradorExcel.generarReporteLlamadasEntrantes(lstLlamadaEntranteConsulta,nuevopath);
			
			fileInputStream = new FileInputStream(f);
			return "download";
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}
		
		
	//FIN: MetodosAction
}
