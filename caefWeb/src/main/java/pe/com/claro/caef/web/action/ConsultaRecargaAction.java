package pe.com.claro.caef.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.ServletContext;


import org.apache.struts2.ServletActionContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarListRecargasFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ConsultaRecargaService;
import pe.com.claro.caef.web.services.impl.ConsultaRecargaServiceImpl;
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
public class ConsultaRecargaAction extends GeneralAction implements Preparable{
	
	@Autowired
	private ConsultaRecargaService consultaRecargaService;
	private static String PATH=PropertiesCAEF.RUTA_PLANTILLA;
	private List<ConsultaRecarga> lstConsultaRecarga;
	private ConsultarListRecargasFilter consultarRecargaFilter;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
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
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	private boolean bFLagBtnEnviarCorre;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	public boolean isbFLagBtnEnviarCorre() {
		return bFLagBtnEnviarCorre;
	}

	public void setbFLagBtnEnviarCorre(boolean bFLagBtnEnviarCorre) {
		this.bFLagBtnEnviarCorre = bFLagBtnEnviarCorre;
	}

	private boolean bHabilitaBtnEnviar;
	
	//INICIO: Set&Get
	public ConsultaRecargaAction(){
		consultarRecargaFilter = new ConsultarListRecargasFilter();
		this.flagBoton = false;
	}
	
	public List<ConsultaRecarga> getLstConsultaRecarga() {
		return lstConsultaRecarga;
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
	public void setLstConsultaRecarga(List<ConsultaRecarga> lstConsultaRecarga) {
		this.lstConsultaRecarga = lstConsultaRecarga;
	}
	public ConsultarListRecargasFilter getConsultarRecargaFilter() {
		return consultarRecargaFilter;
	}
	public void setConsultarRecargaFilter(
			ConsultarListRecargasFilter consultarRecargaFilter) {
		this.consultarRecargaFilter = consultarRecargaFilter;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
	public void prepare() throws Exception
	{
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("detalleRecargas");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		this.bFLagBtnEnviarCorre = true;
		this.flagBoton = false;
	}
	public String input()
	{
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_CONSULTA_RECARGAS),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_CONSULTA_RECARGAS,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","se ingreso a Consulta de Recargas","","",ma.getIP());
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
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("detalleRecargas");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		consultarRecargaFilter.setNumeroPagina("-1");
		lstConsultaRecarga = consultaRecargaService.getConsultarListRecargas(user,consultarRecargaFilter);

		
		
		if(lstConsultaRecarga.size() <= 0){
			addActionError( getText(mensajesSeguridad.getMensaje2()));
			this.bFLagBtnEnviarCorre = true;
			this.flagBoton = false;
		}
		else {
			this.bFLagBtnEnviarCorre = false;
			this.flagBoton = true;
			
			Map lstRecarga = ActionContext.getContext().getSession();
			List<ConsultaRecarga> lstConsultaRecargaConsulta = new ArrayList<ConsultaRecarga>();
			lstConsultaRecargaConsulta = lstConsultaRecarga;
			lstRecarga.put("lstRecarga", lstConsultaRecargaConsulta);
		}
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_CONSULTA_RECARGAS),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_CONSULTA_RECARGAS,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","Consultar","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
		
		return Action.SUCCESS;
	}	
	
	public void validate(){

		int diaIni = 0;
		int diafin = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar fecIni = Calendar.getInstance();
			Calendar fecFin = Calendar.getInstance();
			fecIni.setTime(sdf.parse(consultarRecargaFilter.getFecInicio()));
			fecFin.setTime(sdf.parse(consultarRecargaFilter.getFecFin()));
			diaIni = fecIni.get(Calendar.DAY_OF_YEAR);
			diafin = fecFin.get(Calendar.DAY_OF_YEAR);
			mensajesSeguridadFilter = new MensajesSeguridadFilter();
			mensajesSeguridadFilter.setTipoPantalla("detalleRecargas");
			mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		} catch (Exception e) {
			LOG.info(e.toString());
		}
		
		if(consultarRecargaFilter.getFecInicio().length()==0)
			addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_RECARGA_FEC_INICIO);
		else if(consultarRecargaFilter.getFecFin().length()==0)
			addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_RECARGA_FEC_FIN);
		else if((diafin - diaIni) > 30 )
		{
			addActionError( MENSAJES_CONFIG.VALIDA_CONSULTA_RECARGA_FEC_RANGO);
		}
		else
		{
			try {
				SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaInicio = formatoFecha.parse(consultarRecargaFilter.getFecInicio());
				Date fechaFin = formatoFecha.parse(consultarRecargaFilter.getFecFin());
				
				if (fechaInicio.getTime() > fechaFin.getTime()) {
					addActionError(MENSAJES_CONFIG.VALIDA_CONSULTA_RECARGA_FEC_MAYOR);
				}
			} catch (Exception e) {
				LOG.info(e.toString());
			}			
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
			/*consultarRecargaFilter.setNumeroPagina("-1");
			lstConsultaRecarga = consultaRecargaService.getConsultarListRecargas(user,consultarRecargaFilter);
			*/
			List<ConsultaRecarga> lstConsultaRecargaConsulta = new ArrayList<ConsultaRecarga>();
			Map lstRecarga = ActionContext.getContext().getSession();
			lstConsultaRecargaConsulta = (List<ConsultaRecarga>)lstRecarga.get("lstRecarga");
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = servletContext.getRealPath(PATH+"consultaRecargas.xml");
			
			String nuevopath = ExcelUtil.convertirPath(path);
			
			File f = GeneradorExcel.generarReporteConsultaRecargas(lstConsultaRecargaConsulta,nuevopath);
			
			CorreoDatos datos = new CorreoDatos();
			datos.setArchivo(f);
			datos.setDestinatario(getUsuario().getCorreoCliente());
			datos.setAsunto("Consulta de Recargas");
			datos.setContenido(PropertiesCAEF.cuerpoMensaje(getUsuario().getNombreUsuario()));
			mensajesSeguridadFilter = new MensajesSeguridadFilter();
			mensajesSeguridadFilter.setTipoPantalla("detalleRecargas");
			mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
			
			try{
				
				enviarCorreoService.enviarCorreo(datos);
				inputStream = new StringBufferInputStream(mensajesSeguridad.getMensaje4());
				
			}catch(Exception e)
			{
				inputStream = new StringBufferInputStream(mensajesSeguridad.getMensaje5());
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
			/*consultarRecargaFilter.setNumeroPagina("-1");
			lstConsultaRecarga = consultaRecargaService.getConsultarListRecargas(user,consultarRecargaFilter);*/
			
			List<ConsultaRecarga> lstConsultaRecargaConsulta = new ArrayList<ConsultaRecarga>();
			Map lstRecarga = ActionContext.getContext().getSession();
			lstConsultaRecargaConsulta = (List<ConsultaRecarga>)lstRecarga.get("lstRecarga");
			
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = servletContext.getRealPath(PATH+"consultaRecargas.xml");
			
			String nuevopath = ExcelUtil.convertirPath(path);
			
			
			File f = GeneradorExcel.generarReporteConsultaRecargas(lstConsultaRecargaConsulta,path);
			
			 fileInputStream = new FileInputStream(f);
			 return "download";
			
		}

		public boolean isbHabilitaBtnEnviar() {
			return bHabilitaBtnEnviar;
		}

		public void setbHabilitaBtnEnviar(boolean bHabilitaBtnEnviar) {
			this.bHabilitaBtnEnviar = bHabilitaBtnEnviar;
		}

	//FIN: MetodosAction
}
