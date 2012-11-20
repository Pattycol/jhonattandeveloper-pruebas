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
import pe.com.claro.caef.web.action.filter.ActivacionPaqueteActionFilter;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarInstanciaServicioFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.RegistrarIncidenciaFilter;
import pe.com.claro.caef.web.auth.SeguridadBean;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultarInstanciaServicio;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.RegistrarIncidencia;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.RegistrarIncidenciaService;
import pe.com.claro.caef.web.util.MetodosAuditoria;
import pe.com.claro.caef.web.util.PropertiesCAEFAudit;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

@Component
@Scope(value = "prototype")
public class ActivacionPaqueteAction extends GeneralAction implements
		Preparable {

	@Autowired
	private RegistrarIncidenciaService registrarIncidenciaService;

	@Autowired
	private ComunService comunService;

	/*
	 * @Autowired private QueueSender sender;
	 * 
	 * @Autowired private QueueListener listener;
	 */
	@Autowired
	private CargarMensajesService cargarMensajesService;

	private MetodosAuditoria ma = new MetodosAuditoria();

	private ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter;
	private List<ConsultaDatosMaestro> lstNumFijo;
	private List<ConsultaDatosMaestro> lstNumMovil;
	private List<ConsultarInstanciaServicio> lstConsultarInstanciaServicio;
	private List<ConsultaDatosMaestro> lstPlanesAdicionar;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private ActivacionPaqueteActionFilter activacionPaqueteActionFilter;

	// Variables de la grilla / PAQUETE
	private String sLetraInstancia;
	private String codigoInstanciaServicio;
	private String direccion;

	private String visible;
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private MensajesSeguridad mensajesSeguridad;

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

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public ActivacionPaqueteAction() {

		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		lstPlanesAdicionar = new ArrayList<ConsultaDatosMaestro>();
		activacionPaqueteActionFilter = new ActivacionPaqueteActionFilter();
	}

	public void prepare() throws Exception {
		/** OBTENER VALORES DE SESION **************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario) codServicio.get("codServicio");

		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/

		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		// SET VALOR COMBOBOX: NUMERO TELEFONICO FIJO
		consultarDatosMaestrosFilter.setCodigoCaso("TELFIJ");
		consultarDatosMaestrosFilter.setCodigoEstado(""); // codEstado
		consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario()); // empty
		consultarDatosMaestrosFilter.setParametro2(""); // empty
		consultarDatosMaestrosFilter.setParametro3(""); // empty
		consultarDatosMaestrosFilter.setParametro4(""); // empty
		lstNumFijo = comunService.getConsultarDatosMaestros(user,
				consultarDatosMaestrosFilter);
		// SET VALOR COMBOBOX: NUMERO TELEFONICO MOVIL
		consultarDatosMaestrosFilter.setCodigoCaso("TELCEL");
		consultarDatosMaestrosFilter.setCodigoEstado(""); // codEstado
		consultarDatosMaestrosFilter.setParametro1(user.getCodigoUsuario()); // empty
		consultarDatosMaestrosFilter.setParametro2(""); // empty
		consultarDatosMaestrosFilter.setParametro3(""); // empty
		consultarDatosMaestrosFilter.setParametro4(""); // empty
		lstNumMovil = comunService.getConsultarDatosMaestros(user,
				consultarDatosMaestrosFilter);

		lstConsultarInstanciaServicio = registrarIncidenciaService
				.consultarInstanciaServicio(user,
						consultarInstanciaServicioFilter);

		for (ConsultarInstanciaServicio cs : lstConsultarInstanciaServicio) {

			for (int i = 0; i < cs.getNumero().length(); i++) {
				if (cs.getNumero().substring(i, i + 1).equals("-")) {
					cs.setsLetraInstancia(cs.getNumero().substring(0, i));
					cs.setsCodigoInstancia(cs.getNumero().substring(i + 1,
							cs.getNumero().length()));
				}
			}

		}

	}

	public String input() {
		setVisible("none");

		/** OBTENER VALORES DE SESION **************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario) codServicio.get("codServicio");

		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/

		DetalleAuditoriaBean message = new DetalleAuditoriaBean(
				Long.parseLong(PropertiesCAEFAudit.COD_ACTIVACION_PAQUETES),
				user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ACTIVACION_PAQUETES,
				user.getTelefonoMiClaroFija(), "", "", "", "", "",
				"Se ha consultado los servicios con los que cuenta el cliente",
				"", "", ma.getIP());
		/*
		 * try { sender.send(message); } catch (JMSException e) {
		 * LOG.info(e.toString()); }
		 */

		return Action.INPUT;
	}

	public String execute() {
		/** OBTENER VALORES DE SESION **************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario) codServicio.get("codServicio");

		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/

		DetalleAuditoriaBean message = new DetalleAuditoriaBean(
				Long.parseLong(PropertiesCAEFAudit.COD_ACTIVACION_PAQUETES),
				user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ACTIVACION_PAQUETES,
				user.getTelefonoMiClaroFija(), "", "", "", "", "",
				"Se han consultado los planes que se pueden adicionar", "", "",
				ma.getIP());
		/*
		 * try { sender.send(message); } catch (JMSException e) {
		 * LOG.info(e.toString()); }
		 */

		try {

			consultarDatosMaestrosFilter.setCodigoCaso("PLANADIC");
			consultarDatosMaestrosFilter.setCodigoEstado("");
			consultarDatosMaestrosFilter.setParametro1(this.sLetraInstancia);
			consultarDatosMaestrosFilter.setParametro2(user.getCodigoUsuario());
			consultarDatosMaestrosFilter
					.setParametro3(this.codigoInstanciaServicio);
			consultarDatosMaestrosFilter.setParametro4("");
			lstPlanesAdicionar = comunService.getConsultarDatosMaestros(user,
					consultarDatosMaestrosFilter);

			mensajesSeguridadFilter = new MensajesSeguridadFilter();
			mensajesSeguridadFilter.setTipoPantalla("activacionPaquetes");
			mensajesSeguridad = cargarMensajesService
					.obtenerMensajes(mensajesSeguridadFilter);

			if (lstPlanesAdicionar.size() > 0) {
				for (ConsultaDatosMaestro cm : lstPlanesAdicionar) {

				}

				activacionPaqueteActionFilter.setsPlanActual(lstPlanesAdicionar
						.get(0).getValorDatoMaestro1());
				activacionPaqueteActionFilter
						.setsNuevaTarifa(lstPlanesAdicionar.get(0)
								.getValorDatoMaestro2());
				activacionPaqueteActionFilter.setsDireccion(this.direccion);
				activacionPaqueteActionFilter
						.setsCodInstanciaServicio(this.codigoInstanciaServicio);

				setVisible("block");
			} else {
				addActionError(getText(mensajesSeguridad.getMensaje1()));

				setVisible("none");
			}

		} catch (Exception e) {
			LOG.info(e.toString());
		}

		return Action.SUCCESS;
	}

	public String aceptar() {

		/** OBTENER VALORES DE SESION **************/
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario) codServicio.get("codServicio");

		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("activacionPaquetes");
		mensajesSeguridad = cargarMensajesService
				.obtenerMensajes(mensajesSeguridadFilter);
		/*****************************************/

		DetalleAuditoriaBean message = new DetalleAuditoriaBean(
				Long.parseLong(PropertiesCAEFAudit.COD_ACTIVACION_PAQUETES),
				user.getCodigoUsuario(),
				PropertiesCAEFAudit.DES_ACTIVACION_PAQUETES,
				user.getTelefonoMiClaroFija(),
				"",
				"",
				"",
				"",
				"",
				"Se ha registrado la solicitud de activación de paquetes adicionales",
				"", "", ma.getIP());
		/*
		 * try { sender.send(message); } catch (JMSException e) {
		 * LOG.info(e.toString()); }
		 */

		RegistrarIncidencia objRegIncidencia = new RegistrarIncidencia();

		try {

			/* PARA OBTENER EL CODIGO DE INSTANCIA DE SERVICIO */
			consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
			consultarDatosMaestrosFilter.setCodigoCaso("CIS");
			consultarDatosMaestrosFilter.setParametro1(getUsuario()
					.getCodigoUsuario());
			consultarDatosMaestrosFilter.setParametro2(getUsuario()
					.getCodigoServicio());
			lstPlanesAdicionar = comunService.getConsultarDatosMaestros(user,
					consultarDatosMaestrosFilter);

			RegistrarIncidenciaFilter objRegIncidenciaFilter = new RegistrarIncidenciaFilter();

			objRegIncidenciaFilter.setCodigoTransaccion("7");
			objRegIncidenciaFilter.setCodigoCanal("8");
			objRegIncidenciaFilter.setCodigoEstado("1");
			objRegIncidenciaFilter.setCodigoEstadoSecuencia("0");
			objRegIncidenciaFilter
					.setCodigoInstanciaServicio(lstPlanesAdicionar.get(0)
							.getValorDatoMaestro1());

			String opcMovil = "";
			String opcFija = "";

			if (activacionPaqueteActionFilter.getsOpcionalFijo() != null)
				opcFija = "/511-"
						+ activacionPaqueteActionFilter.getsOpcionalFijo();
			if (activacionPaqueteActionFilter.getsOpcionalMovil() != null)
				opcMovil = "/511-"
						+ activacionPaqueteActionFilter.getsOpcionalMovil();

			objRegIncidenciaFilter.setObservacion("El plan a adicionar es: "
					+ activacionPaqueteActionFilter.getsPlanAdicionar()
					+ ". Los números de contacto fijos son: "
					+ activacionPaqueteActionFilter.getsTelFijo() + opcFija
					+ ". Los números de contacto móviles son: "
					+ activacionPaqueteActionFilter.getsTelMovil() + opcMovil);

			objRegIncidencia = registrarIncidenciaService.registrarIncidencia(
					user, objRegIncidenciaFilter);

			if (objRegIncidencia.getCodError().equals("0")) {
				setVisible("none");
				addActionMessage(mensajesSeguridad.getMensaje2());
			} else {
				addActionError(getText(mensajesSeguridad.getMensaje3()));
			}

		} catch (Exception e) {
			LOG.info(e.toString());
			addActionError(getText(mensajesSeguridad.getMensaje4()));
		}

		return "exito";
	}

	public List<ConsultaDatosMaestro> getLstNumMovil() {
		return lstNumMovil;
	}

	public void setLstNumMovil(List<ConsultaDatosMaestro> lstNumMovil) {
		this.lstNumMovil = lstNumMovil;
	}

	public List<ConsultaDatosMaestro> getLstNumFijo() {
		return lstNumFijo;
	}

	public void setLstNumFijo(List<ConsultaDatosMaestro> lstNumFijo) {
		this.lstNumFijo = lstNumFijo;
	}

	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}

	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	public List<ConsultarInstanciaServicio> getLstConsultarInstanciaServicio() {
		return lstConsultarInstanciaServicio;
	}

	public void setLstConsultarInstanciaServicio(
			List<ConsultarInstanciaServicio> lstConsultarInstanciaServicio) {
		this.lstConsultarInstanciaServicio = lstConsultarInstanciaServicio;
	}

	public ConsultarInstanciaServicioFilter getConsultarInstanciaServicioFilter() {
		return consultarInstanciaServicioFilter;
	}

	public void setConsultarInstanciaServicioFilter(
			ConsultarInstanciaServicioFilter consultarInstanciaServicioFilter) {
		this.consultarInstanciaServicioFilter = consultarInstanciaServicioFilter;
	}

	public String getsLetraInstancia() {
		return sLetraInstancia;
	}

	public void setsLetraInstancia(String sLetraInstancia) {
		this.sLetraInstancia = sLetraInstancia;
	}

	public List<ConsultaDatosMaestro> getLstPlanesAdicionar() {
		return lstPlanesAdicionar;
	}

	public void setLstPlanesAdicionar(
			List<ConsultaDatosMaestro> lstPlanesAdicionar) {
		this.lstPlanesAdicionar = lstPlanesAdicionar;
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

	public ActivacionPaqueteActionFilter getActivacionPaqueteActionFilter() {
		return activacionPaqueteActionFilter;
	}

	public void setActivacionPaqueteActionFilter(
			ActivacionPaqueteActionFilter activacionPaqueteActionFilter) {
		this.activacionPaqueteActionFilter = activacionPaqueteActionFilter;
	}

}
