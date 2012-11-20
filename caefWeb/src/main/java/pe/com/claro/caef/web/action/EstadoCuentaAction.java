package pe.com.claro.caef.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
import pe.com.claro.caef.web.action.filter.ConsultarCabeceraEstadoCuentaFilter;
import pe.com.claro.caef.web.action.filter.ConsultarEstadoCuentaFilter;

//import pe.com.claro.caef.web.action.xls.EstadoCuentaActionxls;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.EstadoCuenta;
import pe.com.claro.caef.web.services.EstadoCuentaService;
import pe.com.claro.caef.web.util.ExcelUtil;
import pe.com.claro.caef.web.util.GeneradorExcel;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEF;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope(value="prototype")
public class EstadoCuentaAction extends GeneralAction{
	
	@Autowired
	private EstadoCuentaService estadoCuentaService;
	
//	@Autowired
//	private QueueSender sender;
//	@Autowired
//	private QueueListener listener;
//	
	private static String PATH=PropertiesCAEF.RUTA_PLANTILLA;
	private EstadoCuenta estadoCuenta;
	private List<DetalleEstadoCuenta> lstEstadoCuenta;
	private ConsultarCabeceraEstadoCuentaFilter consultarCabeceraEstadoCuentaFilter;
	private ConsultarEstadoCuentaFilter consultarEstadoCuentaFilter;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	

	
	//INICIO: Set&Get
	public EstadoCuenta getEstadoCuenta() {
		return estadoCuenta;
	}
	public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public List<DetalleEstadoCuenta> getLstEstadoCuenta() {
		return lstEstadoCuenta;
	}
	public void setLstEstadoCuenta(List<DetalleEstadoCuenta> lstEstadoCuenta) {
		this.lstEstadoCuenta = lstEstadoCuenta;
	}
	public ConsultarCabeceraEstadoCuentaFilter getConsultarCabeceraEstadoCuentaFilter() {
		return consultarCabeceraEstadoCuentaFilter;
	}
	public void setConsultarCabeceraEstadoCuentaFilter(
			ConsultarCabeceraEstadoCuentaFilter consultarCabeceraEstadoCuentaFilter) {
		this.consultarCabeceraEstadoCuentaFilter = consultarCabeceraEstadoCuentaFilter;
	}
	public ConsultarEstadoCuentaFilter getConsultarEstadoCuentaFilter() {
		return consultarEstadoCuentaFilter;
	}
	public void setConsultarEstadoCuentaFilter(
			ConsultarEstadoCuentaFilter consultarEstadoCuentaFilter) {
		this.consultarEstadoCuentaFilter = consultarEstadoCuentaFilter;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
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
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_ESTADO_CUENTA),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ESTADO_CUENTA,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado estado de cuenta","","",ma.getIP());
//		try {
//			sender.send(message);
//			} catch (JMSException e) {
//			LOG.info(e.toString());
//			}
		
		estadoCuenta = estadoCuentaService.getConsultarCabeceraEstadoCuenta(user);
		
		if(estadoCuenta.getCodError() != null)
			addActionError( MENSAJES_CONFIG.ERROR_ESTADO_CUENTA_MENSAJE1);
		
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
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_ESTADO_CUENTA),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ESTADO_CUENTA,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado estado de cuenta","","",ma.getIP());
//		try {
//			sender.send(message);
//			} catch (JMSException e) {
//			LOG.info(e.toString());
//			}
		
		//Trae la cabecera y el detalle del estado de cuenta
		estadoCuenta = estadoCuentaService.getConsultarCabeceraEstadoCuenta(user);
		return Action.SUCCESS;
	}
	
	private InputStream fileInputStream;
	 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	    
	public String download() throws Exception
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
		
		estadoCuenta = estadoCuentaService.getConsultarCabeceraEstadoCuenta(user);
		lstEstadoCuenta = estadoCuenta.getDetalleEstadoCuenta();
		//lstEstadoCuenta= new ArrayList<DetalleEstadoCuenta>();
		ServletContext servletContext = ServletActionContext.getServletContext();
		String path = servletContext.getRealPath(PATH+"estadoCuenta.xml");
		

		String nuevopath = ExcelUtil.convertirPath(path);
		
		File f = GeneradorExcel.generarReporteEstadoCuenta(lstEstadoCuenta,nuevopath);
		
		 fileInputStream = new FileInputStream(f);
		 return "download";
		
	}
	//FIN: MetodosAction
}
