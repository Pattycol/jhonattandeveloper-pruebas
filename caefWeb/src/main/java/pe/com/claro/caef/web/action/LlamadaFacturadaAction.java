package pe.com.claro.caef.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Date;
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
import pe.com.claro.caef.web.action.filter.ConsultarListaLlamadasFacturadasFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaRecarga;
import pe.com.claro.caef.web.beans.LlamadaFacturada;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.LlamadaFacturadaService;
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
public class LlamadaFacturadaAction extends GeneralAction implements Preparable{
	
	@Autowired
	private LlamadaFacturadaService llamadaFacturadaService;
	private static String PATH=PropertiesCAEF.RUTA_PLANTILLA;
	@Autowired
	private ComunService comunService;
	
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
	private LlamadaFacturada llamadaFacturada;
	private List<LlamadaFacturada> lstLlamadaFacturada;
	//private List<LlamadaFacturada> lstLlamadaFacturadadownload;
	private ConsultarListaLlamadasFacturadasFilter consultarListaLlamadasFacturadasFilter;
	private List<String> lstFechaEmision;
	private List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico;
	private ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroFecha;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private ConsultaDatosMaestro consultaDatosMaestro;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	private String codigoInstanciaServicio;
	private boolean bFLagBtnEnviarCorre;
	
	public LlamadaFacturadaAction(){
		consultarListaLlamadasFacturadasFilter = new ConsultarListaLlamadasFacturadasFilter();
		flagBoton = false;
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
	public ConsultaDatosMaestro getConsultaDatosMaestro() {
		return consultaDatosMaestro;
	}
	public void setConsultaDatosMaestro(ConsultaDatosMaestro consultaDatosMaestro) {
		this.consultaDatosMaestro = consultaDatosMaestro;
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
	//INICIO:Set&Get
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
	public List<String> getLstFechaEmision() {
		return lstFechaEmision;
	}
	public void setLstFechaEmision(List<String> lstFechaEmision) {
		this.lstFechaEmision = lstFechaEmision;
	}
	public LlamadaFacturada getLlamadaFacturada() {
		return llamadaFacturada;
	}
	public void setLlamadaFacturada(LlamadaFacturada llamadaFacturada) {
		this.llamadaFacturada = llamadaFacturada;
	}
	public List<LlamadaFacturada> getLstLlamadaFacturada() {
		return lstLlamadaFacturada;
	}
	public void setLstLlamadaFacturada(List<LlamadaFacturada> lstLlamadaFacturada) {
		this.lstLlamadaFacturada = lstLlamadaFacturada;
	}
	public ConsultarListaLlamadasFacturadasFilter getConsultarListaLlamadasFacturadasFilter() {
		return consultarListaLlamadasFacturadasFilter;
	}
	public void setConsultarListaLlamadasFacturadasFilter(
			ConsultarListaLlamadasFacturadasFilter consultarListaLlamadasFacturadasFilter) {
		this.consultarListaLlamadasFacturadasFilter = consultarListaLlamadasFacturadasFilter;
	}
	//FIN:Set&Get	
	
	//INICIO: MetodosAction
	
	public String input()
	{
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_LLAMADA_FACTURADA),getUsuario().getCodigoUsuario(),
				PropertiesCAEFAudit.DES_LLAMADA_FACTURADA,getUsuario().getTelefonoMiClaroFija(),
				"","","","","","se ingreso a Llamadas Facturadas","","",ma.getIP());
		/*try {
			sender.send(message);
		} catch (JMSException e) {
			LOG.info(e.toString());
		}*/
	
		return Action.INPUT;
	}	
	Date dtFechaInicio = null;
	Date dtFechaFin = null;
	
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
		
		dtFechaInicio = new Date();
		dtFechaFin = new Date();
		
		
		lstConsultaNumeroTelefonico = comunService.getConsultarNumerosTelefonicos(user, consultarNumerosTelefonicosFilter);
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("llamadaFacturada");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		this.bFLagBtnEnviarCorre = true;
		flagBoton = false;
	}
	
	public String completeCombo(){
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codSrv = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codSrv.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		try {
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			
			
			
			
			lstConsultaNumeroTelefonico = comunService.getConsultarNumerosTelefonicos(user, consultarNumerosTelefonicosFilter);
			String codServicio = "";
			 
			for (ConsultaNumeroTelefonico cn : lstConsultaNumeroTelefonico) {
				if(cn.getNumero().equals(consultarListaLlamadasFacturadasFilter.getNumOrigen()))
					codServicio = cn.getCodigoInstanciaServicio();
			} 
			
			consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
			consultarDatosMaestrosFilter.setCodigoCaso("FECFAC");
			consultarDatosMaestrosFilter.setParametro1(codServicio);
			consultarDatosMaestrosFilter.setParametro2("3");
			lstConsultaDatosMaestroFecha = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			
			session.put("lstComboFechas", lstConsultaDatosMaestroFecha);
			
			
			
		} catch (Exception e) {
			LOG.info(e.toString());
		}
		
		return SUCCESS;
	}


	@SuppressWarnings("unchecked")
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
		
		String fecFin = "";
		
		try {
			
			Map session = ActionContext.getContext().getSession();
			Map<String,Object> listaConsulta = ActionContext.getContext().getSession();
			lstConsultaDatosMaestroFecha = (List<ConsultaDatosMaestro>) session.get("lstComboFechas");
			
			for (ConsultaDatosMaestro cn : lstConsultaDatosMaestroFecha) {
				
				if(cn.getValorDatoMaestro1().equalsIgnoreCase(consultarListaLlamadasFacturadasFilter.getFecInicio()))
					fecFin = cn.getValorDatoMaestro2();
			}
			
			
			
			
			consultarListaLlamadasFacturadasFilter.setFecFin(fecFin);
			
			lstLlamadaFacturada = llamadaFacturadaService.getConsultarListaLlamadasFacturadas(user,consultarListaLlamadasFacturadasFilter);
			//lstLlamadaFacturada=new ArrayList<LlamadaFacturada>();
			
			if(lstLlamadaFacturada.size()<=0){
				addActionError(MENSAJES_CONFIG.LLAMADA_FACTURADA2);
				flagBoton = false;
			}
			else{
				flagBoton = true;
				Map lstFacturada = ActionContext.getContext().getSession();
				List<LlamadaFacturada> lstLlamadaFacturadaConsulta = new ArrayList<LlamadaFacturada>();
				lstLlamadaFacturadaConsulta = lstLlamadaFacturada;
				lstFacturada.put("lstFacturada", lstLlamadaFacturadaConsulta);
			}
			
			
			DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_LLAMADA_FACTURADA),user.getCodigoUsuario(),
					PropertiesCAEFAudit.DES_LLAMADA_FACTURADA,user.getTelefonoMiClaroFija(),
					"","","","","","Consultar","","",ma.getIP());
			/*try {
				sender.send(message);
			} catch (JMSException e) {
				LOG.info(e.toString());
			}*/
			
			
		} catch (Exception e) {
			LOG.info(e.toString());
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
		/*lstLlamadaFacturada = llamadaFacturadaService.getConsultarListaLlamadasFacturadas(user, consultarListaLlamadasFacturadasFilter);*/
		
		List<LlamadaFacturada> lstLlamadaFacturadaConsulta = new ArrayList<LlamadaFacturada>();
		Map lstFacturada = ActionContext.getContext().getSession();
		lstLlamadaFacturadaConsulta = (List<LlamadaFacturada>)lstFacturada.get("lstFacturada");
		
		ServletContext servletContext = ServletActionContext.getServletContext();
		String path = servletContext.getRealPath(PATH+"llamadasFacturadas.xml");
		
		String nuevopath = ExcelUtil.convertirPath(path);
		
		File f = GeneradorExcel.generarReporteLlamadasFacturadas(lstLlamadaFacturadaConsulta,nuevopath);
		
		CorreoDatos datos = new CorreoDatos();
		datos.setArchivo(f);
		datos.setDestinatario(getUsuario().getCorreoCliente());
		datos.setAsunto("Llamadas Facturadas");
		datos.setContenido(PropertiesCAEF.cuerpoMensaje(getUsuario().getNombreUsuario()));
		
		try{
			
			enviarCorreoService.enviarCorreo(datos);
			inputStream = new StringBufferInputStream(MENSAJES_CONFIG.LLAMADA_FACTURADA3);
			
		}catch(Exception e) 
		{
			inputStream = new StringBufferInputStream(MENSAJES_CONFIG.LLAMADA_FACTURADA4);
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
		/*lstLlamadaFacturada = llamadaFacturadaService.getConsultarListaLlamadasFacturadas(user, consultarListaLlamadasFacturadasFilter);*/

		/* PRUEBAS 
		NumOrigen: 16550840
		FecInicio: 01/09/2011
		FecFin: 30/09/2011
		 */
		/*consultarListaLlamadasFacturadasFilter.setFecFin("30/09/2011");
		consultarListaLlamadasFacturadasFilter.setFecInicio("01/09/2011");
		consultarListaLlamadasFacturadasFilter.setNumOrigen("16550840");*/
		 
		 List<LlamadaFacturada> lstLlamadaFacturadaConsulta = new ArrayList<LlamadaFacturada>();
		 Map lstFacturada = ActionContext.getContext().getSession();
		 lstLlamadaFacturadaConsulta = (List<LlamadaFacturada>)lstFacturada.get("lstFacturada");
		 
		 ServletContext servletContext = ServletActionContext.getServletContext();
		 String path = servletContext.getRealPath(PATH+"llamadasFacturadas.xml");
			
		 String nuevopath = ExcelUtil.convertirPath(path);
			
		 File f = GeneradorExcel.generarReporteLlamadasFacturadas(lstLlamadaFacturadaConsulta,nuevopath);
		
		 fileInputStream = new FileInputStream(f);
		 
		 
		 
		 return "download";
		
	}

	public String getCodigoInstanciaServicio() {
		return codigoInstanciaServicio;
	}

	public void setCodigoInstanciaServicio(String codigoInstanciaServicio) {
		this.codigoInstanciaServicio = codigoInstanciaServicio;
	}

	public boolean isbFLagBtnEnviarCorre() {
		return bFLagBtnEnviarCorre;
	}

	public void setbFLagBtnEnviarCorre(boolean bFLagBtnEnviarCorre) {
		this.bFLagBtnEnviarCorre = bFLagBtnEnviarCorre;
	}
	
	
	/*public void validate(){
		if(consultarListaLlamadasFacturadasFilter.getFecFin().length()==0)
			addActionError( getText("validar.consultarListaLlamadasFacturadasFilter.fecInicio"));
	}*/
	//FIN: MetodosAction
}
