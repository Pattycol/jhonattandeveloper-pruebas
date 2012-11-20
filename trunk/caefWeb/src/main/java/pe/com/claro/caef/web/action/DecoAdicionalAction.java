package pe.com.claro.caef.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import org.apache.struts2.components.ActionMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.com.claro.caef.bean.DetalleAuditoriaBean;
import pe.com.claro.caef.jms.QueueListener;
import pe.com.claro.caef.jms.QueueSender;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarInstanciaServicioFilter;
import pe.com.claro.caef.web.action.filter.DecoAdicionalActionFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.RegistrarIncidenciaFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultarInstanciaServicio;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.RegistrarIncidencia;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.RegistrarIncidenciaService;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value="prototype")
public class DecoAdicionalAction extends GeneralAction implements Preparable{
	
	@Autowired
	private RegistrarIncidenciaService registrarIncidenciaService;
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;
	private List<ConsultarInstanciaServicio> lstConsultarInstanciaServicio;
	private ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter;
	private int flag;
	
	
	private MetodosAuditoria ma = new MetodosAuditoria();
	
	/*@Autowired
	private QueueSender sender;
	@Autowired
	private QueueListener listener;*/
	
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstNumFijo;
	private List<ConsultaDatosMaestro> lstNumMovil;
	private List<ConsultaDatosMaestro> lstTipoDeco;
	private List<ConsultaDatosMaestro> lstDecoActual;
	private List<ConsultaDatosMaestro> lstCIS;
	private List<ConsultaDatosMaestro> lstDecoMaximo;
	private List<ConsultaDatosMaestro> lstDecoNumero;
	private List<ConsultaDatosMaestro> lstMontoServicio;
	private DecoAdicionalActionFilter decoAdicionalActionFilter;
	private List<ConsultaDatosMaestro> lstMontoContrata;

	//Variables de la grilla / PAQUETE
	private String sLetraInstancia;
	private String codigoInstanciaServicio;
	private String direccion;
	
	private String visible;
	
	//Lista del combo DECO
	private List<String> lstCanDecoAdicional;
	
	public DecoAdicionalAction(){
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		decoAdicionalActionFilter = new DecoAdicionalActionFilter();
		lstTipoDeco = new ArrayList<ConsultaDatosMaestro>();
		lstCanDecoAdicional = new ArrayList<String>();
		lstDecoActual = new ArrayList<ConsultaDatosMaestro>();
	}
	
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
		//SET VALOR CONTRATA
		/*consultarDatosMaestrosFilter.setCodigoCaso("PAGOCONTRAT");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
		consultarDatosMaestrosFilter.setParametro1("");		//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstMontoContrata = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		decoAdicionalActionFilter.setsMontoServicioTecnico(lstMontoContrata.get(0).getValorDatoMaestro1().toString());*/
		//SET VALOR COMBOBOX: NUMERO TELEFONICO FIJO
		consultarDatosMaestrosFilter.setCodigoCaso("TELFIJ");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
		consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());		//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstNumFijo = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		//SET VALOR COMBOBOX: NUMERO TELEFONICO MOVIL
		consultarDatosMaestrosFilter.setCodigoCaso("TELCEL");
		consultarDatosMaestrosFilter.setCodigoEstado("");	//codEstado
		consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());	//empty
		consultarDatosMaestrosFilter.setParametro2("");		//empty
		consultarDatosMaestrosFilter.setParametro3("");		//empty
		consultarDatosMaestrosFilter.setParametro4("");		//empty
		lstNumMovil = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
		
		lstConsultarInstanciaServicio = registrarIncidenciaService.consultarInstanciaServicio(user, consultarInstanciaServicioFilter); 
	
		for (ConsultarInstanciaServicio cs : lstConsultarInstanciaServicio) {
			
			for(int i=0;i<cs.getNumero().length();i++){
				if(cs.getNumero().substring(i, i+1).equals("-")){
					cs.setsLetraInstancia(cs.getNumero().substring(0,i));
					cs.setsCodigoInstancia(cs.getNumero().substring(i+1,cs.getNumero().length()));
				}
			}
			
		}
	
	}
	
	public String input()
	{
		setVisible("none");
		
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_INSTALACION_DECO),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_INSTALACION_DECO,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado los servicios con los que cuenta el cliente","","",ma.getIP());
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
		mensajesSeguridadFilter.setTipoPantalla("errorDecoAdicional");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_INSTALACION_DECO),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_INSTALACION_DECO,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha consultado los tipos de deco que se pueden solicitar","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		try {
			
			
			
			
			
			consultarDatosMaestrosFilter.setCodigoCaso("DECOADIC");
			consultarDatosMaestrosFilter.setCodigoEstado("");
			consultarDatosMaestrosFilter.setParametro1(this.sLetraInstancia);
			consultarDatosMaestrosFilter.setParametro2(this.codigoInstanciaServicio);
			consultarDatosMaestrosFilter.setParametro3("");	
			consultarDatosMaestrosFilter.setParametro4("");
			consultarDatosMaestrosFilter.setParametro5("");
			lstTipoDeco = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			if(lstTipoDeco.size()>0){
				consultarDatosMaestrosFilter.setCodigoCaso("CAD");
				consultarDatosMaestrosFilter.setCodigoEstado("");
				consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
				consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
				consultarDatosMaestrosFilter.setParametro3("");	
				consultarDatosMaestrosFilter.setParametro4("");
				consultarDatosMaestrosFilter.setParametro5("");
				//lstDecoActual = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				lstDecoMaximo = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
				if(lstDecoMaximo.size()>0  && lstDecoMaximo.get(0).getValorDatoMaestro1()!=""){
					/*for (ConsultaDatosMaestro cm : lstTipoDeco) {
						
						
						
						
						
					}*/
					
					decoAdicionalActionFilter.setsDireccion(this.direccion);
					decoAdicionalActionFilter.setiDecoMaximo(lstDecoMaximo.get(0).getValorDatoMaestro1()==""?0:Integer.parseInt(lstDecoMaximo.get(0).getValorDatoMaestro1()));
					decoAdicionalActionFilter.setsCodInstanciaServicio(this.getCodigoInstanciaServicio());
					consultarDatosMaestrosFilter.setCodigoCaso("OND");
					consultarDatosMaestrosFilter.setCodigoEstado("");
					consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
					consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
					consultarDatosMaestrosFilter.setParametro3("");	
					consultarDatosMaestrosFilter.setParametro4("");
					consultarDatosMaestrosFilter.setParametro5("");
					lstDecoNumero = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
					if(lstDecoNumero.size()>0  && lstDecoNumero.get(0).getValorDatoMaestro1()!=""  && decoAdicionalActionFilter.getiDecoMaximo() > 0){
						decoAdicionalActionFilter.setiDecoActual(lstDecoNumero.get(0).getValorDatoMaestro1()==""?0:Integer.parseInt(lstDecoNumero.get(0).getValorDatoMaestro1()));
						
						int can = decoAdicionalActionFilter.getiDecoMaximo() - decoAdicionalActionFilter.getiDecoActual();
						if(can<0){
							lstCanDecoAdicional.add(0+"");
						}else{
							for(int i=0;i<can+1;i++){
								lstCanDecoAdicional.add(i+"");
							}							
						}
						

						
						setVisible("block");
						
						consultarDatosMaestrosFilter.setCodigoCaso("OMS");
						consultarDatosMaestrosFilter.setCodigoEstado("");
						consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
						consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
						consultarDatosMaestrosFilter.setParametro3("");	
						consultarDatosMaestrosFilter.setParametro4("");
						consultarDatosMaestrosFilter.setParametro5("");
						lstMontoServicio = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
						if(lstMontoServicio.size()>0){
							decoAdicionalActionFilter.setsMontoServicioTecnico(lstMontoServicio.get(0).getValorDatoMaestro1());
							return Action.SUCCESS;
						}else{
							addActionError(mensajesSeguridad.getMensaje4());
							return Action.INPUT;
						}
						
					}else{
						addActionError(mensajesSeguridad.getMensaje3());
						return Action.INPUT;
					}
					
					
				}else{
					addActionError(mensajesSeguridad.getMensaje2());
					return Action.INPUT;
				}
			}else{
			
				addActionError(mensajesSeguridad.getMensaje1());
				return Action.INPUT;
			}
			
			
			/*consultarDatosMaestrosFilter.setCodigoCaso("CAD");
			consultarDatosMaestrosFilter.setCodigoEstado("");
			consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
			consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
			consultarDatosMaestrosFilter.setParametro3("");	
			consultarDatosMaestrosFilter.setParametro4("");
			//lstDecoActual = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			lstDecoMaximo = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			
			for (ConsultaDatosMaestro cm : lstTipoDeco) {
				
				
				
				
				
			}
			

			
			
			decoAdicionalActionFilter.setsDireccion(this.direccion);
			decoAdicionalActionFilter.setiDecoMaximo(Integer.parseInt(lstDecoMaximo.get(0).getValorDatoMaestro1()));
			
			consultarDatosMaestrosFilter.setCodigoCaso("OND");
			consultarDatosMaestrosFilter.setCodigoEstado("");
			consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
			consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
			consultarDatosMaestrosFilter.setParametro3("");	
			consultarDatosMaestrosFilter.setParametro4("");
			lstDecoActual = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			
			decoAdicionalActionFilter.setiDecoActual(Integer.parseInt(lstDecoActual.get(0).getValorDatoMaestro1()));
			
			int can = decoAdicionalActionFilter.getiDecoMaximo() - decoAdicionalActionFilter.getiDecoActual();
			
			for(int i=0;i<can+1;i++){
				lstCanDecoAdicional.add(i+"");
			}
			
			setVisible("block");
			
			consultarDatosMaestrosFilter.setCodigoCaso("OMS");
			consultarDatosMaestrosFilter.setCodigoEstado("");
			consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
			consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
			consultarDatosMaestrosFilter.setParametro3("");	
			consultarDatosMaestrosFilter.setParametro4("");
			lstDecoActual = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			decoAdicionalActionFilter.setsMontoServicioTecnico(lstDecoActual.get(0).getValorDatoMaestro1());*/
			
		} catch (Exception e) {
			LOG.info(e.toString());
			return Action.INPUT;
		}
		
		
	}
	
	public String aceptar(){
		
		/**OBTENER VALORES DE SESION**************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		this.setVisible("block");
		
		/*****************************************/
		
		DetalleAuditoriaBean message = new DetalleAuditoriaBean(Long.parseLong(PropertiesCAEFAudit.COD_INSTALACION_DECO),user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_INSTALACION_DECO,user.getTelefonoMiClaroFija(),
				"","","","","","Se ha registrado la solicitud de deco adicional","","",ma.getIP());
		/*try {
			sender.send(message);
			} catch (JMSException e) {
			LOG.info(e.toString());
			}*/
		
		RegistrarIncidencia objRegIncidencia = new RegistrarIncidencia();
		
		try {
			
			
			
			RegistrarIncidenciaFilter objRegIncidenciaFilter = new RegistrarIncidenciaFilter();
			
			objRegIncidenciaFilter.setCodigoTransaccion("8");
			objRegIncidenciaFilter.setCodigoCanal("8");
			objRegIncidenciaFilter.setCodigoEstado("1");
			objRegIncidenciaFilter.setCodigoEstadoSecuencia("0");
			
			/**/
			consultarDatosMaestrosFilter.setCodigoCaso("CIS");
			consultarDatosMaestrosFilter.setCodigoEstado("");
			consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario());
			consultarDatosMaestrosFilter.setParametro2(user.getCodigoServicio());
			consultarDatosMaestrosFilter.setParametro3("");	
			consultarDatosMaestrosFilter.setParametro4("");
			lstCIS = comunService.getConsultarDatosMaestros(user, consultarDatosMaestrosFilter);
			decoAdicionalActionFilter.setsCodInstanciaServicio(lstCIS.get(0).getValorDatoMaestro1());
			/**/			
			
			objRegIncidenciaFilter.setCodigoInstanciaServicio(decoAdicionalActionFilter.getsCodInstanciaServicio());
			String cadena=decoAdicionalActionFilter.getsCboTipDeco();
			String [] spCadena = cadena.split(",");
			String cantidad=spCadena[0].trim();
			String tipo=spCadena[1].trim();
			objRegIncidenciaFilter.setObservacion("Tipo Deco: "+tipo+" - Cantidad: "+cantidad);
			
			objRegIncidencia = registrarIncidenciaService.registrarIncidencia(user, objRegIncidenciaFilter);
			
			if(!objRegIncidencia.getCodError().equals("0"))
				addActionError(MENSAJES_CONFIG.VALIDA_CONSULTA_RECARGA_FEC_INICIO);
			else{
				setVisible("block");
				this.setFlag(1);
				addActionMessage(MENSAJES_CONFIG.DECO_ADICIONAL_MENSAJE1);
			}
			
			
			
			
		} catch (Exception e) {
			LOG.info(e.toString());
		}
		
		return "exito";
	}

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}
	
	public ConsultarInstanciaServicioFilter getConsultarInstanciaServicioFilter() {
		return consultarInstanciaServicioFilter;
	}

	public void setConsultarInstanciaServicioFilter(
			ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter) {
		this.consultarInstanciaServicioFilter = consultarInstanciaServicioFilter;
	}

	public List<ConsultarInstanciaServicio> getLstConsultarInstanciaServicio() {
		return lstConsultarInstanciaServicio;
	}

	public void setLstConsultarInstanciaServicio(
			List<ConsultarInstanciaServicio> lstConsultarInstanciaServicio) {
		this.lstConsultarInstanciaServicio = lstConsultarInstanciaServicio;
	}

	public List<ConsultaDatosMaestro> getLstNumFijo() {
		return lstNumFijo;
	}

	public void setLstNumFijo(List<ConsultaDatosMaestro> lstNumFijo) {
		this.lstNumFijo = lstNumFijo;
	}

	public List<ConsultaDatosMaestro> getLstNumMovil() {
		return lstNumMovil;
	}

	public void setLstNumMovil(List<ConsultaDatosMaestro> lstNumMovil) {
		this.lstNumMovil = lstNumMovil;
	}

	public String getsLetraInstancia() {
		return sLetraInstancia;
	}

	public void setsLetraInstancia(String sLetraInstancia) {
		this.sLetraInstancia = sLetraInstancia;
	}

	public String getCodigoInstanciaServicio() {
		return codigoInstanciaServicio;
	}

	public void setCodigoInstanciaServicio(String codigoInstanciaServicio) {
		this.codigoInstanciaServicio = codigoInstanciaServicio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public List<ConsultaDatosMaestro> getLstTipoDeco() {
		return lstTipoDeco;
	}

	public void setLstTipoDeco(List<ConsultaDatosMaestro> lstTipoDeco) {
		this.lstTipoDeco = lstTipoDeco;
	}

	public DecoAdicionalActionFilter getDecoAdicionalActionFilter() {
		return decoAdicionalActionFilter;
	}

	public void setDecoAdicionalActionFilter(
			DecoAdicionalActionFilter decoAdicionalActionFilter) {
		this.decoAdicionalActionFilter = decoAdicionalActionFilter;
	}

	public List<String> getLstCanDecoAdicional() {
		return lstCanDecoAdicional;
	}

	public void setLstCanDecoAdicional(List<String> lstCanDecoAdicional) {
		this.lstCanDecoAdicional = lstCanDecoAdicional;
	}

	public List<ConsultaDatosMaestro> getLstDecoActual() {
		return lstDecoActual;
	}

	public void setLstDecoActual(List<ConsultaDatosMaestro> lstDecoActual) {
		this.lstDecoActual = lstDecoActual;
	}

	public List<ConsultaDatosMaestro> getLstDecoMaximo() {
		return lstDecoMaximo;
	}

	public void setLstDecoMaximo(List<ConsultaDatosMaestro> lstDecoMaximo) {
		this.lstDecoMaximo = lstDecoMaximo;
	}

	public List<ConsultaDatosMaestro> getLstDecoNumero() {
		return lstDecoNumero;
	}

	public void setLstDecoNumero(List<ConsultaDatosMaestro> lstDecoNumero) {
		this.lstDecoNumero = lstDecoNumero;
	}

	public List<ConsultaDatosMaestro> getLstMontoServicio() {
		return lstMontoServicio;
	}

	public void setLstMontoServicio(List<ConsultaDatosMaestro> lstMontoServicio) {
		this.lstMontoServicio = lstMontoServicio;
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
	public List<ConsultaDatosMaestro> getLstMontoContrata() {
		return lstMontoContrata;
	}

	public void setLstMontoContrata(List<ConsultaDatosMaestro> lstMontoContrata) {
		this.lstMontoContrata = lstMontoContrata;
	}

	public List<ConsultaDatosMaestro> getLstCIS() {
		return lstCIS;
	}

	public void setLstCIS(List<ConsultaDatosMaestro> lstCIS) {
		this.lstCIS = lstCIS;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	


}
