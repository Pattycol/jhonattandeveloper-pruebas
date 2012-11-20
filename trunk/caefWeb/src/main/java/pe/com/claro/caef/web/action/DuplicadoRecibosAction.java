package pe.com.claro.caef.web.action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.web.action.filter.ConsultarDetalleReciboClienteFilter;
import pe.com.claro.caef.web.action.filter.ConsultarReciboClienteFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.CategoriaBean;
import pe.com.claro.caef.web.beans.ClienteBean;
import pe.com.claro.caef.web.beans.DuplicadoRecibo;
import pe.com.claro.caef.web.beans.DuplicadoReciboPDF;
import pe.com.claro.caef.web.beans.LlamadaBean;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.TotalBean;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.DuplicadoRecibosService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class DuplicadoRecibosAction extends GeneralAction implements Preparable{

	@Autowired
	private DuplicadoRecibosService duplicadoRecibosService;
	
	private DuplicadoRecibo duplicadoRecibos;
	private List<DuplicadoRecibo> lstDuplicadoRecibos;
	private List<DuplicadoReciboPDF> lstReciboPDF;
	private List<DuplicadoReciboPDF> lstReciboDetalle;


	private List<LlamadaBean> lstExport;
	
	private ConsultarReciboClienteFilter consultarReciboClienteFilter;
	private ConsultarDetalleReciboClienteFilter consultarDetalleReciboClienteFilter;
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	
	//VARIABLES RECIBIDAS
	private String codCicloFac;
	private String codFactura;
	private String mensajeError;
	private String ruta;
	
	//INICIO: Set&Get
	public List<DuplicadoReciboPDF> getLstReciboDetalle() {
		return lstReciboDetalle;
	}

	public void setLstReciboDetalle(List<DuplicadoReciboPDF> lstReciboDetalle) {
		this.lstReciboDetalle = lstReciboDetalle;
	}
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public DuplicadoRecibosAction(){
		consultarDetalleReciboClienteFilter = new ConsultarDetalleReciboClienteFilter();
		lstExport = new ArrayList<LlamadaBean>();
		ruta = "";
	}
	
	public DuplicadoRecibo getDuplicadoRecibos() {
		return duplicadoRecibos;
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
	public void setDuplicadoRecibos(DuplicadoRecibo duplicadoRecibos) {
		this.duplicadoRecibos = duplicadoRecibos;
	}
	public List<DuplicadoRecibo> getLstDuplicadoRecibos() {
		return lstDuplicadoRecibos;
	}
	public void setLstDuplicadoRecibos(List<DuplicadoRecibo> lstDuplicadoRecibos) {
		this.lstDuplicadoRecibos = lstDuplicadoRecibos;
	}
	public ConsultarReciboClienteFilter getConsultarReciboClienteFilter() {
		return consultarReciboClienteFilter;
	}
	public void setConsultarReciboClienteFilter(
			ConsultarReciboClienteFilter consultarReciboClienteFilter) {
		this.consultarReciboClienteFilter = consultarReciboClienteFilter;
	}
	public ConsultarDetalleReciboClienteFilter getConsultarDetalleReciboClienteFilter() {
		return consultarDetalleReciboClienteFilter;
	}
	public void setConsultarDetalleReciboClienteFilter(
			ConsultarDetalleReciboClienteFilter consultarDetalleReciboClienteFilter) {
		this.consultarDetalleReciboClienteFilter = consultarDetalleReciboClienteFilter;
	}
	//FIN: Set&Get
	
	//INICIO: MetodosAction
	public void prepare() throws Exception
	{
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("duplicadoRecibos");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		setMensajeError("");	
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
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_DUPLICADO_RECIBOS),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_DUPLICADO_RECIBOS,user.getTelefonoMiClaroFija(),
				"","","","","","Se han consultado los duplicados de recibo","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		lstDuplicadoRecibos = duplicadoRecibosService.getConsultarReciboCliente(user, consultarReciboClienteFilter);
		//lstDuplicadoRecibos=new ArrayList<DuplicadoRecibo>();
		if(lstDuplicadoRecibos.size() <= 0)
		{
			addActionError( MENSAJES_CONFIG.DUPLICADO_RECIBOS_ERROR1);
		}
		else
		{
			int cantidadRec = lstDuplicadoRecibos.size();
			for(int i = 0; i <= cantidadRec; i++)
			{
				if(lstDuplicadoRecibos.size() > 3)
					lstDuplicadoRecibos.remove(0);
			}			
		}
		
		return Action.SUCCESS;
	}
	
	
	public String exportar(){
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		String valida = "";
		
		try {
			
			
			
			consultarDetalleReciboClienteFilter.setCodCicloFac(this.codCicloFac);//2
			consultarDetalleReciboClienteFilter.setCodFactura(this.codFactura);//0000002985			
		 	lstReciboPDF = duplicadoRecibosService.consultarDetalleReciboCliente(user, consultarDetalleReciboClienteFilter);
		 	
		 	
		 	consultarDetalleReciboClienteFilter.setCodCicloFac("DETALLE");//2
			consultarDetalleReciboClienteFilter.setCodFactura(this.codFactura);//0000002985			
		 	lstReciboDetalle = duplicadoRecibosService.consultarDetalleReciboCliente(user, consultarDetalleReciboClienteFilter);
		 	
		 	DuplicadoReciboPDF auxduplicadoReciboPDF= new DuplicadoReciboPDF();
			
			auxduplicadoReciboPDF = lstReciboPDF.get(0);
			
			ClienteBean cb = new ClienteBean();
			TotalBean tb = new TotalBean();
			LlamadaBean llb = new LlamadaBean();
			CategoriaBean cab = new CategoriaBean();
				
	 		cb.setCodigoCliente(auxduplicadoReciboPDF.getCodCliente());
 			cb.setDireccionDomicilaria(auxduplicadoReciboPDF.getDirEmpresa());
 			cb.setDireccionFacturacion(auxduplicadoReciboPDF.getDirEmpresa());
			cb.setFechaEmision(auxduplicadoReciboPDF.getFecEmision());
			cb.setFechaVencimiento(auxduplicadoReciboPDF.getFecVencimiento());
			cb.setNombreCliente(auxduplicadoReciboPDF.getNomEmpresa());
			cb.setNumeroRecibo(auxduplicadoReciboPDF.getNumDocumento());
			cb.setRuc(auxduplicadoReciboPDF.getRucEmpresa());
			cb.setServicio(auxduplicadoReciboPDF.getCodInstServicioPrin());
	
			tb.setIgv(auxduplicadoReciboPDF.getValImpuesto());
			tb.setSubTotal(auxduplicadoReciboPDF.getValSubTotal());
			tb.setTotalAPagar(auxduplicadoReciboPDF.getValTotal());
			tb.setTotalSaldoAnterior(auxduplicadoReciboPDF.getValSaldoAnt());
			
			cab.setFechaInicio(lstReciboDetalle.get(0).getFecEmision());
			cab.setFechaFin(lstReciboDetalle.get(0).getFecVencimiento());
			cab.setNombre(lstReciboDetalle.get(0).getCodInstServicioPrin());
		
			tb.setInteresesMoratorios(lstReciboDetalle.get(0).getCodCliente());
			tb.setRedondeoMesActual(lstReciboDetalle.get(0).getValSubTotal());
			tb.setTipoCambio(lstReciboDetalle.get(0).getCodDirFacturacion());
			tb.setRedondeoMesAnterior(lstReciboDetalle.get(0).getValImpuesto());
			tb.setTotalPeriodo(lstReciboDetalle.get(0).getValTotal());
		
			llb.setCliente(cb);
			llb.setTotal(tb);
			llb.setCategoria(cab);
			
			TotalBean tbAux = new TotalBean();
			tbAux= tb;
				
			for (int j = 0;  j < lstReciboDetalle.size()  ; j++) {	
				
				if(llb != null){
					llb.setConcepto(lstReciboDetalle.get(j).getNomEmpresa());
					llb.setPrecioUnitario(lstReciboDetalle.get(j).getFecEmision());
					llb.setNumeroLlamadas(lstReciboDetalle.get(j).getTipFacturacion());
					llb.setServicioMinutos(lstReciboDetalle.get(j).getCodCicloFac());
					llb.setImporte(lstReciboDetalle.get(j).getCodProductoPrin());
			
					lstExport.add(llb);
			
					llb=null;
					
				}else{
					
					
					LlamadaBean llb2 = null;
					llb2 = new LlamadaBean();
					//llb2=llbAux;
					llb2.setTotal(tbAux);
					llb2.setConcepto(lstReciboDetalle.get(j).getNomEmpresa());
					llb2.setPrecioUnitario(lstReciboDetalle.get(j).getFecEmision());
					llb2.setNumeroLlamadas(lstReciboDetalle.get(j).getTipFacturacion());
					llb2.setServicioMinutos(lstReciboDetalle.get(j).getCodCicloFac());
					llb2.setImporte(lstReciboDetalle.get(j).getCodProductoPrin());
			
					lstExport.add(llb2);
					
				}
			
			}
		 		
	 		if(lstExport.size()>0){
	 			valida = "exito";
	 		}
	 		else{
	 			valida = "error";
	 			setMensajeError("No se pudo exportar...");
	 		}
	 		
		 		
		 		//lstDuplicadoRecibos = duplicadoRecibosService.getConsultarReciboCliente(user, consultarReciboClienteFilter);
		 
		 		
        } catch (Exception e) {
            LOG.info(e.toString());
        }
		
		return valida;
	}
	
	public String getCodCicloFac() {
		return codCicloFac;
	}
	public void setCodCicloFac(String codCicloFac) {
		this.codCicloFac = codCicloFac;
	}
	
	public List<DuplicadoReciboPDF> getLstReciboPDF() {
		return lstReciboPDF;
	}
	public void setLstReciboPDF(List<DuplicadoReciboPDF> lstReciboPDF) {
		this.lstReciboPDF = lstReciboPDF;
	}
	public String getCodFactura() {
		return codFactura;
	}
	public void setCodFactura(String codFactura) {
		this.codFactura = codFactura;
	}
	public List<LlamadaBean> getLstExport() {
		return lstExport;
	}

	public void setLstExport(List<LlamadaBean> lstExport) {
		this.lstExport = lstExport;
	}

	public String getRuta() {
		
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	//FIN: MetodosAction
}
