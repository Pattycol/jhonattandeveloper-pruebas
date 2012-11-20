package pe.com.claro.caef.web.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.Comun;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaServicioClienteTotal;
import pe.com.claro.caef.web.services.ComunService;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope(value="prototype")
public class ComunAction  extends GeneralAction{
	
	@Autowired
	private ComunService comunService;
	
	private Comun comun;
	private List<Comun> lstComun;
	private List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestro;
	private List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal;
	private ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	
	
	public List<ConsultaNumeroTelefonico> getLstConsultaNumeroTelefonico() {
		return lstConsultaNumeroTelefonico;
	}
	public void setLstConsultaNumeroTelefonico(
			List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico) {
		this.lstConsultaNumeroTelefonico = lstConsultaNumeroTelefonico;
	}
	public List<ConsultaServicioClienteTotal> getLstConsultaServicioClienteTotal() {
		return lstConsultaServicioClienteTotal;
	}
	public void setLstConsultaServicioClienteTotal(
			List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal) {
		this.lstConsultaServicioClienteTotal = lstConsultaServicioClienteTotal;
	}
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestro() {
		return lstConsultaDatosMaestro;
	}
	public void setLstConsultaDatosMaestro(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestro) {
		this.lstConsultaDatosMaestro = lstConsultaDatosMaestro;
	}
	//INICIO: Sets&Gets
	public Comun getComun() {
		return comun;
	}
	public void setComun(Comun comun) {
		this.comun = comun;
	}
	public List<Comun> getLstComun() {
		return lstComun;
	}
	public void setLstComun(List<Comun> lstComun) {
		this.lstComun = lstComun;
	}
	public ConsultarNumerosTelefonicosFilter getConsultarNumerosTelefonicosFilter() {
		return consultarNumerosTelefonicosFilter;
	}
	public void setConsultarNumerosTelefonicosFilter(
			ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter) {
		this.consultarNumerosTelefonicosFilter = consultarNumerosTelefonicosFilter;
	}
	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}
	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}
	//FIN: Sets&Gets
	
	//INICIO: MetodosAction
	public String getConsultarNumerosTelefonicos()
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
		return Action.SUCCESS;
	}
	public String getConsultarDatosMaestros()
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
		
		lstConsultaDatosMaestro = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		return Action.SUCCESS;
	}
	public String getConsultarServiciosClienteTotal()
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
		
		lstConsultaServicioClienteTotal = comunService.ConsultarServiciosClienteTotal(user);
		return Action.SUCCESS;
	}
	//FIN: MetodosAction

}
