package pe.com.claro.caef.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
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
import pe.com.claro.caef.web.action.filter.ConsultarConsumoClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.beans.LlamadaNoFacturada;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.ConsultaConsumosService;
import pe.com.claro.caef.web.util.CorreoDatos;
import pe.com.claro.caef.web.util.ExcelUtil;
import pe.com.claro.caef.web.util.GeneradorExcel;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class ConsultaConsumosAction extends GeneralAction implements Preparable{
	
	@Autowired
	private ConsultaConsumosService consultaConsumosService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
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

	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	private ConsultaConsumo consultaConsumo;
	private List<ConsultaConsumo> lstConsultaConsumo;
	private ConsultarConsumoClienteFilter consultarConsumoClienteFilter;
	private ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter;
	private List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico;
	
	private boolean bFLagBtnEnviarCorreo;
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	//INICIO: Set&Get
	
	public boolean isbFLagBtnEnviarCorreo() {
		return bFLagBtnEnviarCorreo;
	}

	public void setbFLagBtnEnviarCorreo(boolean bFLagBtnEnviarCorreo) {
		this.bFLagBtnEnviarCorreo = bFLagBtnEnviarCorreo;
	}

	public ConsultaConsumosAction(){
		consultarConsumoClienteFilter = new ConsultarConsumoClienteFilter();
		flagBoton = false;
	}
	
	public ConsultaConsumo getConsultaConsumo() {
		return consultaConsumo;
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
	public ConsultarNumerosTelefonicosFilter getConsultarNumerosTelefonicosFilter() {
		return consultarNumerosTelefonicosFilter;
	}
	public void setConsultarNumerosTelefonicosFilter(
			ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter) {
		this.consultarNumerosTelefonicosFilter = consultarNumerosTelefonicosFilter;
	}
	public List<ConsultaNumeroTelefonico> getLstConsultaNumeroTelefonico() {
		return lstConsultaNumeroTelefonico;
	}
	public void setLstConsultaNumeroTelefonico(
			List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico) {
		this.lstConsultaNumeroTelefonico = lstConsultaNumeroTelefonico;
	}
	public void setConsultaConsumo(ConsultaConsumo consultaConsumo) {
		this.consultaConsumo = consultaConsumo;
	}
	public List<ConsultaConsumo> getLstConsultaConsumo() {
		return lstConsultaConsumo;
	}
	public void setLstConsultaConsumo(List<ConsultaConsumo> lstConsultaConsumo) {
		this.lstConsultaConsumo = lstConsultaConsumo;
	}
	public ConsultarConsumoClienteFilter getConsultarConsumoClienteFilter() {
		return consultarConsumoClienteFilter;
	}
	public void setConsultarConsumoClienteFilter(
			ConsultarConsumoClienteFilter consultarConsumoClienteFilter) {
		this.consultarConsumoClienteFilter = consultarConsumoClienteFilter;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
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
		
		lstConsultaNumeroTelefonico = comunService.getConsultarNumerosTelefonicos(user, consultarNumerosTelefonicosFilter);
		//lstConsultaNumeroTelefonico= new ArrayList<ConsultaNumeroTelefonico>();
		if(lstConsultaNumeroTelefonico.size() <= 0)
			addActionError( MENSAJES_CONFIG.ERROR_CONSUMO_MENSAJE1);
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("estadoConsumo");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		this.bFLagBtnEnviarCorreo = true;
		this.flagBoton = false;
	}
	
	public String input()
	{	
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_CONSULTA_CONSUMOS),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_CONSULTA_CONSUMOS,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","se ingreso a consulta de consumos","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
		
		return Action.INPUT;
	}	
	
	public String getList()
	{
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("estadoConsumo");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		lstConsultaConsumo = consultaConsumosService.getConsultarConsumoCliente(user, consultarConsumoClienteFilter);
		
		if(lstConsultaConsumo.size() <= 0)
		{
			addActionError( getText(mensajesSeguridad.getMensaje2()));
		}
		else{
			this.flagBoton = true;
			
			Map lstConsumos = ActionContext.getContext().getSession();
			List<ConsultaConsumo> lstConsultaConsumoConsulta = new ArrayList<ConsultaConsumo>();
			lstConsultaConsumoConsulta = lstConsultaConsumo;
			lstConsumos.put("lstConsumos", lstConsultaConsumoConsulta);
		}

		return Action.SUCCESS;
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
			/*lstConsultaConsumo = consultaConsumosService.getConsultarConsumoCliente(user, consultarConsumoClienteFilter);*/
			
			List<ConsultaConsumo> lstConsultaConsumoConsulta = new ArrayList<ConsultaConsumo>();
			Map lstConsumos = ActionContext.getContext().getSession();
			lstConsultaConsumoConsulta = (List<ConsultaConsumo>)lstConsumos.get("lstConsumos");
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = servletContext.getRealPath(PATH+"consultaConsumos.xml");
			
			String nuevopath = ExcelUtil.convertirPath(path);
			
			
			File f = GeneradorExcel.generarReporteConsultaConsumos(lstConsultaConsumoConsulta,nuevopath);
			
			CorreoDatos datos = new CorreoDatos();
			datos.setArchivo(f);
			datos.setDestinatario(getUsuario().getCorreoCliente());
			datos.setAsunto("Consulta de consumos");
			datos.setContenido(PropertiesCAEF.cuerpoMensaje(getUsuario().getNombreUsuario()));
			
			try{
				
				enviarCorreoService.enviarCorreo(datos);
				inputStream = new StringBufferInputStream(mensajesSeguridad.getMensaje3());
				
			}catch(Exception e)
			{
				inputStream = new StringBufferInputStream(mensajesSeguridad.getMensaje4());
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
			/*lstConsultaConsumo = consultaConsumosService.getConsultarConsumoCliente(user, consultarConsumoClienteFilter);*/
			
			List<ConsultaConsumo> lstConsultaConsumoConsulta = new ArrayList<ConsultaConsumo>();
			Map lstConsumos = ActionContext.getContext().getSession();
			lstConsultaConsumoConsulta = (List<ConsultaConsumo>)lstConsumos.get("lstConsumos");
			
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String path = servletContext.getRealPath(PATH+"consultaConsumos.xml");
			
			String nuevopath = ExcelUtil.convertirPath(path);
			
			
			File f = GeneradorExcel.generarReporteConsultaConsumos(lstConsultaConsumoConsulta,nuevopath);
			
			 fileInputStream = new FileInputStream(f);
			 return "download";
			
		}

	//FIN: MetodosAction
}
