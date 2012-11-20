package pe.com.claro.caef.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarGrupoFactRecibosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.GenericBean;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ReciboCorreoElectronico;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.ReciboCorreoElectronicoService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class ReciboCorreoElectronicoAction extends GeneralAction implements Preparable{
	
	@Autowired
	private ReciboCorreoElectronicoService reciboCorreoElectronicoService;
	private ReciboCorreoElectronico reciboCorreoElectronico;
	private List<ReciboCorreoElectronico> lstReciboCorreoElectronico;
	private ConsultarGrupoFactRecibosFilter consultarGrupoFactRecibosFilter;
	private RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronicoFilter;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroEmail;
	private List<String> lstEmailString;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private List<GenericBean> lstCodigoString;
	private List<GenericBean> lstCorreoString;
	private Map<Integer, Boolean> checkboxes;
	
	public Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}

	private String valCorreoElectronico;
	private String codGrupo;
	
	public String getValCorreoElectronico() {
		return valCorreoElectronico;
	}

	public void setValCorreoElectronico(String valCorreoElectronico) {
		this.valCorreoElectronico = valCorreoElectronico;
	}

	private List<ReciboCorreoElectronico> lstReciboCorreo = new ArrayList<ReciboCorreoElectronico>();

	public List<ReciboCorreoElectronico> getLstReciboCorreo() {
		return lstReciboCorreo;
	}

	public void setLstReciboCorreo(List<ReciboCorreoElectronico> lstReciboCorreo) {
		this.lstReciboCorreo = lstReciboCorreo;
	}

	@Autowired
	private ComunService comunService;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	//INICIO: Set&Get
	public ReciboCorreoElectronicoAction(){
		lstConsultaDatosMaestroEmail = new ArrayList<ConsultaDatosMaestro>();
		lstEmailString = new ArrayList<String>();
		lstCodigoString = new ArrayList<GenericBean>();
		lstCorreoString = new ArrayList<GenericBean>();
	}
	
	public ReciboCorreoElectronico getReciboCorreoElectronico() {
		return reciboCorreoElectronico;
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
	public void setReciboCorreoElectronico(
			ReciboCorreoElectronico reciboCorreoElectronico) {
		this.reciboCorreoElectronico = reciboCorreoElectronico;
	}
	public List<ReciboCorreoElectronico> getLstReciboCorreoElectronico() {
		return lstReciboCorreoElectronico;
	}
	public void setLstReciboCorreoElectronico(
			List<ReciboCorreoElectronico> lstReciboCorreoElectronico) {
		this.lstReciboCorreoElectronico = lstReciboCorreoElectronico;
	}
	public ConsultarGrupoFactRecibosFilter getConsultarGrupoFactReciboFilter() {
		return consultarGrupoFactRecibosFilter;
	}
	public void setConsultarGrupoFactReciboFilter(
			ConsultarGrupoFactRecibosFilter consultarGrupoFactReciboFilter) {
		this.consultarGrupoFactRecibosFilter = consultarGrupoFactReciboFilter;
	}
	public RegistrarActivacionReciboCorreoElectronicoFilter getRegistrarActivacionReciboCorreoElectronicoFilter() {
		return registrarActivacionReciboCorreoElectronicoFilter;
	}
	public void setRegistrarActivacionReciboCorreoElectronicoFilter(
			RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronicoFilter) {
		this.registrarActivacionReciboCorreoElectronicoFilter = registrarActivacionReciboCorreoElectronicoFilter;
	}
	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}
	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroEmail() {
		return lstConsultaDatosMaestroEmail;
	}
	public void setLstConsultaDatosMaestroEmail(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroEmail) {
		this.lstConsultaDatosMaestroEmail = lstConsultaDatosMaestroEmail;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
	public void prepare() throws Exception
	{
		
		llenaCombo();
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("reciboCorreoElectronico");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
	}
	
	private void llenaCombo(){
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		try {
			
			
			
			consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
			consultarDatosMaestrosFilter.setCodigoCaso("EMAIL");
			consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
			consultarDatosMaestrosFilter.setParametro1("");		//empty
			consultarDatosMaestrosFilter.setParametro2("");		//empty
			consultarDatosMaestrosFilter.setParametro3("");		//empty
			consultarDatosMaestrosFilter.setParametro4("");		//empty
			lstConsultaDatosMaestroEmail = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			
			for (ConsultaDatosMaestro m : lstConsultaDatosMaestroEmail) {
				lstEmailString.add(m.getValorDatoMaestro3());
			}

			
		} catch (Exception e) {
			
			LOG.info(e.toString());
		}
		
		
		
	}
	
	
	public String getConsultarGrupoFactRecibos()
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
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_RECIBO_CORREO_ELECTRONICO),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_RECIBO_CORREO_ELECTRONICO,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado los grupos de facturación","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		try {
		
			List<ReciboCorreoElectronico> lstRecibo = new ArrayList<ReciboCorreoElectronico>();
			
			lstReciboCorreoElectronico = reciboCorreoElectronicoService.getConsultarGrupoFactRecibos(user, consultarGrupoFactRecibosFilter);
			
			if(lstReciboCorreoElectronico.size() <= 0)
			{
				addActionError( MENSAJES_CONFIG.RECIBO_CORREO_ERROR1);
			}
			
		} catch (Exception e) {
			LOG.info(e.toString());
		}
		
		
		
		return Action.SUCCESS;
	}
	
	public String registrarAceptacionRecibo(){
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_RECIBO_CORREO_ELECTRONICO),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_RECIBO_CORREO_ELECTRONICO,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha registrado la activación de envío por correo electrónico","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		try {
			
			StringTokenizer st = new StringTokenizer(codGrupo, ", ");
			int can=0;
			
			if(codGrupo.contains(",")){
			
				while(st.hasMoreTokens()){
					
					
					
					
					
					RegistrarActivacionReciboCorreoElectronicoFilter objActivacionRecibo = new RegistrarActivacionReciboCorreoElectronicoFilter();
					objActivacionRecibo.setCodGrupo(st.nextToken());
					objActivacionRecibo.setFlgActivo(checkboxes.get(can).booleanValue()==true?"1":"0");
					objActivacionRecibo.setValCorreoElectronico(lstCorreoString.get(can).getDescripcion());
					
					reciboCorreoElectronicoService.getRegistrarActivacionReciboCorreoElectronico(user, objActivacionRecibo);
					can++;
					
				}
			}
			else{
				
				
				for (GenericBean g : lstCorreoString) {
					
					RegistrarActivacionReciboCorreoElectronicoFilter objActivacionRecibo = new RegistrarActivacionReciboCorreoElectronicoFilter();
					objActivacionRecibo.setCodGrupo(codGrupo.trim());
					objActivacionRecibo.setFlgActivo(checkboxes.get(can).booleanValue()==true?"1":"0");
					objActivacionRecibo.setValCorreoElectronico(g.getDescripcion());
					
					reciboCorreoElectronicoService.getRegistrarActivacionReciboCorreoElectronico(user, objActivacionRecibo);
					can++;
					
					
				}
				
			}
			
			
			
		} catch (Exception e) {
			LOG.info(e.toString());
			
		}
		
		getConsultarGrupoFactRecibos();
		
		return SUCCESS;
		
	}
	public String getRegistrarActivacionReciboCorreoElectronico()
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
		

		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_RECIBO_CORREO_ELECTRONICO),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_RECIBO_CORREO_ELECTRONICO,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha registrado la activación de envío por correo electrónico","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		reciboCorreoElectronicoService.getRegistrarActivacionReciboCorreoElectronico(user, registrarActivacionReciboCorreoElectronicoFilter);
		return Action.SUCCESS;
	}
	//FIN: MetodosAction

	public List<String> getLstEmailString() {
		return lstEmailString;
	}

	public void setLstEmailString(List<String> lstEmailString) {
		this.lstEmailString = lstEmailString;
	}

	public List<GenericBean> getLstCodigoString() {
		return lstCodigoString;
	}

	public void setLstCodigoString(List<GenericBean> lstCodigoString) {
		this.lstCodigoString = lstCodigoString;
	}

	public List<GenericBean> getLstCorreoString() {
		return lstCorreoString;
	}

	public void setLstCorreoString(List<GenericBean> lstCorreoString) {
		this.lstCorreoString = lstCorreoString;
	}

	public String getCodGrupo() {
		return codGrupo;
	}

	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}
}
