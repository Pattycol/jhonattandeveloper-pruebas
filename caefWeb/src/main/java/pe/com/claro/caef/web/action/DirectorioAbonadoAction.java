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
import pe.com.claro.caef.web.action.filter.ConsultarNumTelefonicoAbonadoFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.RegistrarPublicacionDirectorioAbonadoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultarNumTelefonicoAbonado;
import pe.com.claro.caef.web.beans.DirectorioAbonado;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.SuscribirDirectorioAbonado;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.DirectorioAbonadoService;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class DirectorioAbonadoAction extends GeneralAction implements Preparable{
	
	@Autowired
	private DirectorioAbonadoService directorioAbonadoService;
	
	private SuscribirDirectorioAbonado directorioAbonado;
	private List<ConsultarNumTelefonicoAbonado> lstDirectorioAbonado;
	private int totalRegs;
	private String numero;
	private ConsultarNumTelefonicoAbonadoFilter consultarNumTelefonicoAbonadoFilter;
	private RegistrarPublicacionDirectorioAbonadoFilter registrarPublicacionDirectorioAbonadoFilter;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	@Autowired
	private ComunService comunService;
	
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTel;
	
	private Map<Integer, Boolean> checkboxes;
	
	//INICIO: Set&Get
	public SuscribirDirectorioAbonado getDirectorioAbonado() {
		return directorioAbonado;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public int getTotalRegs() {
		return totalRegs;
	}
	public void setTotalRegs(int totalRegs) {
		this.totalRegs = totalRegs;
	}
	public Map<Integer, Boolean> getCheckboxes() {
		return checkboxes;
	}
	public void setCheckboxes(Map<Integer, Boolean> checkboxes) {
		this.checkboxes = checkboxes;
	}
	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}
	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroTel() {
		return lstConsultaDatosMaestroTel;
	}
	public void setLstConsultaDatosMaestroTel(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroTel) {
		this.lstConsultaDatosMaestroTel = lstConsultaDatosMaestroTel;
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
	public List<ConsultarNumTelefonicoAbonado> getLstDirectorioAbonado() {
		return lstDirectorioAbonado;
	}
	public void setLstDirectorioAbonado(
			List<ConsultarNumTelefonicoAbonado> lstDirectorioAbonado) {
		this.lstDirectorioAbonado = lstDirectorioAbonado;
	}
	public void setDirectorioAbonado(SuscribirDirectorioAbonado directorioAbonado) {
		this.directorioAbonado = directorioAbonado;
	}
	
	public ConsultarNumTelefonicoAbonadoFilter getConsultarNumTelefonicoAbonadoFilter() {
		return consultarNumTelefonicoAbonadoFilter;
	}
	public void setConsultarNumTelefonicoAbonadoFilter(
			ConsultarNumTelefonicoAbonadoFilter consultarNumTelefonicoAbonadoFilter) {
		this.consultarNumTelefonicoAbonadoFilter = consultarNumTelefonicoAbonadoFilter;
	}
	public RegistrarPublicacionDirectorioAbonadoFilter getRegistrarPublicacionDirectorioAbonadoFilter() {
		return registrarPublicacionDirectorioAbonadoFilter;
	}
	public void setRegistrarPublicacionDirectorioAbonadoFilter(
			RegistrarPublicacionDirectorioAbonadoFilter registrarPublicacionDirectorioAbonadoFilter) {
		this.registrarPublicacionDirectorioAbonadoFilter = registrarPublicacionDirectorioAbonadoFilter;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
	public void prepare() throws Exception
	{
		/*consultarDatosMaestrosFilter.setCodigoCaso("TELFIJ");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
		consultarDatosMaestrosFilter.setParametro1(getUsuario().getCodigoUsuario());		//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstConsultaDatosMaestroTel = comunService.getConsultarDatosMaestros(getUsuario(), consultarDatosMaestrosFilter);*/
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("directorioAbonado");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
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
		
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("directorioAbonado");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		/*****************************************/
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_DIRECTORIO_ABONADOS),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_DIRECTORIO_ABONADOS,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado la información de directorio de abonados","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		lstDirectorioAbonado = directorioAbonadoService.getConsultarNumTelefonicoAbonado(user, consultarNumTelefonicoAbonadoFilter);
		totalRegs = lstDirectorioAbonado.size();
		
		if(totalRegs == 0)
			addActionError( getText(mensajesSeguridad.getMensaje2()));

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
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("directorioAbonado");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		/*****************************************/
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_DIRECTORIO_ABONADOS),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_DIRECTORIO_ABONADOS,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha registrado la publicación de directorio de abonados","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		if(totalRegs == 0)
		{
			addActionError( getText(mensajesSeguridad.getMensaje3()));
			return Action.SUCCESS;
		}
		else
		{
		registrarPublicacionDirectorioAbonadoFilter = new RegistrarPublicacionDirectorioAbonadoFilter();
		List<DirectorioAbonado> lstDirectorioAbonadoRes = new ArrayList<DirectorioAbonado>();
		String [] numTelefonico = numero.split(", ");
		
		for(int i = 0; i < totalRegs; i++)
		{
			DirectorioAbonado da = new DirectorioAbonado();
			if(checkboxes.get(i).booleanValue() == true)
			{
				da.setFlag(checkboxes.get(i).booleanValue() == true?"1":"0");
				da.setNumero(numTelefonico[i].toString());
			}
			else
			{
				da.setFlag(checkboxes.get(i).booleanValue() == true?"1":"0");
				da.setNumero(numTelefonico[i].toString());
			}
			lstDirectorioAbonadoRes.add(da);
		}

		
		registrarPublicacionDirectorioAbonadoFilter.setLstDirectorioAbonado(lstDirectorioAbonadoRes);
		AuditTypes at = new AuditTypes();
		at = directorioAbonadoService.getRegistrarPublicacionDirectorioAbonado(user, registrarPublicacionDirectorioAbonadoFilter);
		
		if(at.getCodError().equals("0"))
		{
			addActionMessage(mensajesSeguridad.getMensaje4());
			lstDirectorioAbonado = directorioAbonadoService.getConsultarNumTelefonicoAbonado(user, consultarNumTelefonicoAbonadoFilter);
			return Action.SUCCESS;
		}
		else
		{
			addActionError( getText(mensajesSeguridad.getMensaje3()));
			lstDirectorioAbonado = directorioAbonadoService.getConsultarNumTelefonicoAbonado(user, consultarNumTelefonicoAbonadoFilter);
			return Action.SUCCESS;
		}
		}
	}
	//FIN: MetodosAction

}
