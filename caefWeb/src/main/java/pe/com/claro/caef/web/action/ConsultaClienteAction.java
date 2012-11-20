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
import pe.com.claro.caef.web.action.filter.ConsultarDatosGenClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaCliente;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.services.ComunService; 
import pe.com.claro.caef.web.services.ConsultaClienteService;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class ConsultaClienteAction  extends GeneralAction  implements Preparable{
	
	@Autowired
	private ConsultaClienteService consultaClienteService;
	
	@Autowired
	private ComunService comunService;
	
	private ConsultaCliente consultaCliente;
	private ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipVia;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroTipDomicilio;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroDistrito;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroCiudad;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	

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

	public ConsultarDatosGenClienteFilter getConsultarDatosGenClienteFilter() {
		return consultarDatosGenClienteFilter;
	}

	public void setConsultarDatosGenClienteFilter(
			ConsultarDatosGenClienteFilter consultarDatosGenClienteFilter) {
		this.consultarDatosGenClienteFilter = consultarDatosGenClienteFilter;
	}

	public ConsultaCliente getConsultaCliente() {
		return consultaCliente;
	}

	public void setConsultaCliente(ConsultaCliente consultaCliente) {
		this.consultaCliente = consultaCliente;
	}

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
		
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_DATOS_CLIENTE),user.getCodigoUsuario(),
																PropertiesCAEFAudit.DES_DATOS_CLIENTE,user.getTelefonoMiClaroFija(),
																"","","","","","Se ha consultado datos del cliente","","",ma.getIP());
		
		/*try {
		sender.send(message);
		} catch (JMSException e) {
		LOG.info(e.toString());
		}*/
		
		consultaCliente = consultaClienteService.getConsultarDatosGenCliente(user, consultarDatosGenClienteFilter);
		return Action.SUCCESS;
	}
	//FIN: MetodosAction

	
	
}
