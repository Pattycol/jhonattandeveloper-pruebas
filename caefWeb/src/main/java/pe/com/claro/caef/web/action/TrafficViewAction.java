package pe.com.claro.caef.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarListTrafficViewFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.ObtenerUrlTrafficViewFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.TrafficView;
import pe.com.claro.caef.web.beans.UrlTrafficView;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.TrafficViewService;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class TrafficViewAction extends GeneralAction implements Preparable{
	
	@Autowired
	private TrafficViewService trafficViewService;
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private UrlTrafficView urlTrafficView;
	private List<TrafficView> lstTrafficView;
	private ConsultarListTrafficViewFilter consultarListTrafficViewFilter;
	private ObtenerUrlTrafficViewFilter obtenerUrlTrafficViewFilter;

	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	//INICIO: Set&Get

	public List<TrafficView> getLstTrafficView() {
		return lstTrafficView;
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
	public TrafficViewService getTrafficViewService() {
		return trafficViewService;
	}
	public void setTrafficViewService(TrafficViewService trafficViewService) {
		this.trafficViewService = trafficViewService;
	}
	public UrlTrafficView getUrlTrafficView() {
		return urlTrafficView;
	}
	public void setUrlTrafficView(UrlTrafficView urlTrafficView) {
		this.urlTrafficView = urlTrafficView;
	}
	public void setLstTrafficView(List<TrafficView> lstTrafficView) {
		this.lstTrafficView = lstTrafficView;
	}
	public ConsultarListTrafficViewFilter getConsultarListTrafficViewFilter() {
		return consultarListTrafficViewFilter;
	}
	public void setConsultarListTrafficViewFilter(
			ConsultarListTrafficViewFilter consultarListTrafficViewFilter) {
		this.consultarListTrafficViewFilter = consultarListTrafficViewFilter;
	}
	public ObtenerUrlTrafficViewFilter getObtenerUrlTrafficViewFilter() {
		return obtenerUrlTrafficViewFilter;
	}
	public void setObtenerUrlTrafficViewFilter(
			ObtenerUrlTrafficViewFilter obtenerUrlTrafficViewFilter) {
		this.obtenerUrlTrafficViewFilter = obtenerUrlTrafficViewFilter;
	}
	//FIN: Set&Get

	//INICIO: MetodosAction
	public void prepare() throws Exception
	{
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("trafficView");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
	}
	
	public String getConsultarListTrafficView()
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
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_TRAFFIC_VIEW),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_TRAFFIC_VIEW,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado el traffic vew","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		lstTrafficView = trafficViewService.getConsultarListTrafficView(user, consultarListTrafficViewFilter);
		//lstTrafficView= new ArrayList<TrafficView>();
				
		if(lstTrafficView.size() <= 0)
		{
			addActionError( getText("No se tiene información de Traffic View."));
		}
		
		return Action.SUCCESS;
	}
	
	public String getObtenerUrlTrafficView()
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
		
		urlTrafficView = trafficViewService.getObtenerUrlTrafficView(user, obtenerUrlTrafficViewFilter);
		return Action.SUCCESS;
	}
	//FIN: MetodosAction
}
