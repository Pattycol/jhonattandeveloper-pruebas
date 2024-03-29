package com.hildebrando.legal.modelo;

// Generated 10-ago-2012 17:25:04 by Hibernate Tools 3.4.0.CR1

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Expediente generated by hbm2java
 */
public class Expediente implements java.io.Serializable, Cloneable {

	private long idExpediente;
	private TipoExpediente tipoExpediente;
	private ContraCautela contraCautela;
	private EstadoCautelar estadoCautelar;
	private Expediente expediente;
	private FormaConclusion formaConclusion;
	private EstadoExpediente estadoExpediente;
	private Proceso proceso;
	private Usuario usuario;
	private TipoCautelar tipoCautelar;
	private Recurrencia recurrencia;
	private Oficina oficina;
	private Instancia instancia;
	private Via via;
	private Moneda moneda;
	private Organo organo;
	private Calificacion calificacion;
	private Riesgo riesgo;
	private String numeroExpediente;
	private Date fechaInicioProceso;
	private Date fechaFinProceso;
	private Double montoCautelar;
	private String descripcionCautelar;
	private Double importeCautelar;
	private String secretario;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuarioCreacion;
	private String usuarioModificacion;
	private Character flagRevertir;
	private List<Involucrado> involucrados;
	private List<Honorario> honorarios;
	private List<Cuantia> cuantias;
	private List<Inculpado> inculpados;
	private List<Anexo> anexos;
	private List<Provision> provisions;
	private List<ActividadProcesal> actividadProcesals;
	private List<Resumen> resumens;
	private Set expedientes = new HashSet(0);

	public Expediente() {
	}

	public Expediente(long idExpediente) {
		this.idExpediente = idExpediente;
	}

	public Expediente(long idExpediente, TipoExpediente tipoExpediente,
			ContraCautela contraCautela, Expediente expediente,
			EstadoCautelar estadoCautelar, Usuario usuario,
			EstadoExpediente estadoExpediente, TipoCautelar tipoCautelar,
			Recurrencia recurrencia, Via via, Proceso proceso, FormaConclusion formaConclusion,
			Oficina oficina, Instancia instancia, Moneda moneda, Organo organo,
			Calificacion calificacion, Riesgo riesgo, String numeroExpediente,
			Date fechaInicioProceso, Date fechaFinProceso, Double montoCautelar,
			String descripcionCautelar, Double importeCautelar,
			String secretario, Date fechaCreacion, Date fechaModificacion,
			String usuarioCreacion, String usuarioModificacion,
			Character flagRevertir, List involucrados, List honorarios,
			List cuantias, List actividadProcesals, List inculpados, List anexos,
			Set expedientes, List provisions, List resumens) {
		this.idExpediente = idExpediente;
		this.tipoExpediente = tipoExpediente;
		this.contraCautela = contraCautela;
		this.expediente = expediente;
		this.estadoCautelar = estadoCautelar;
		this.usuario = usuario;
		this.proceso = proceso;
		this.via = via;
		this.estadoExpediente = estadoExpediente;
		this.tipoCautelar = tipoCautelar;
		this.recurrencia = recurrencia;
		this.formaConclusion = formaConclusion;
		this.oficina = oficina;
		this.instancia = instancia;
		this.moneda = moneda;
		this.organo = organo;
		this.calificacion = calificacion;
		this.riesgo = riesgo;
		this.numeroExpediente = numeroExpediente;
		this.fechaInicioProceso = fechaInicioProceso;
		this.fechaFinProceso = fechaFinProceso;
		this.montoCautelar = montoCautelar;
		this.descripcionCautelar = descripcionCautelar;
		this.importeCautelar = importeCautelar;
		this.secretario = secretario;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
		this.flagRevertir = flagRevertir;
		this.involucrados = involucrados;
		this.honorarios = honorarios;
		this.cuantias = cuantias;
		this.expedientes= expedientes;
		this.actividadProcesals = actividadProcesals;
		this.inculpados = inculpados;
		this.anexos = anexos;
		this.provisions = provisions;
		this.resumens = resumens;
	}

	public long getIdExpediente() {
		return this.idExpediente;
	}

	public void setIdExpediente(long idExpediente) {
		this.idExpediente = idExpediente;
	}

	public TipoExpediente getTipoExpediente() {
		return this.tipoExpediente;
	}

	public void setTipoExpediente(TipoExpediente tipoExpediente) {
		this.tipoExpediente = tipoExpediente;
	}

	public ContraCautela getContraCautela() {
		return this.contraCautela;
	}

	public void setContraCautela(ContraCautela contraCautela) {
		this.contraCautela = contraCautela;
	}

	public EstadoCautelar getEstadoCautelar() {
		return this.estadoCautelar;
	}

	public void setEstadoCautelar(EstadoCautelar estadoCautelar) {
		this.estadoCautelar = estadoCautelar;
	}

	public EstadoExpediente getEstadoExpediente() {
		return this.estadoExpediente;
	}

	public void setEstadoExpediente(EstadoExpediente estadoExpediente) {
		this.estadoExpediente = estadoExpediente;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoCautelar getTipoCautelar() {
		return this.tipoCautelar;
	}

	public void setTipoCautelar(TipoCautelar tipoCautelar) {
		this.tipoCautelar = tipoCautelar;
	}

	public Recurrencia getRecurrencia() {
		return this.recurrencia;
	}

	public void setRecurrencia(Recurrencia recurrencia) {
		this.recurrencia = recurrencia;
	}

	public Oficina getOficina() {
		return this.oficina;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = oficina;
	}

	public Instancia getInstancia() {
		return this.instancia;
	}

	public void setInstancia(Instancia instancia) {
		this.instancia = instancia;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Organo getOrgano() {
		return this.organo;
	}

	public void setOrgano(Organo organo) {
		this.organo = organo;
	}

	public Calificacion getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}

	public Riesgo getRiesgo() {
		return this.riesgo;
	}

	public void setRiesgo(Riesgo riesgo) {
		this.riesgo = riesgo;
	}

	public String getNumeroExpediente() {
		return this.numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public Date getFechaInicioProceso() {
		return this.fechaInicioProceso;
	}

	public void setFechaInicioProceso(Date fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}

	public Date getFechaFinProceso() {
		return this.fechaFinProceso;
	}

	public void setFechaFinProceso(Date fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}

	

	public String getDescripcionCautelar() {
		return this.descripcionCautelar;
	}

	public void setDescripcionCautelar(String descripcionCautelar) {
		this.descripcionCautelar = descripcionCautelar;
	}

	public Double getImporteCautelar() {
		return this.importeCautelar;
	}

	public void setImporteCautelar(Double importeCautelar) {
		this.importeCautelar = importeCautelar;
	}

	public String getSecretario() {
		return this.secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public List<Involucrado> getInvolucrados() {
		if(involucrados == null)
			involucrados = new ArrayList<Involucrado>();
		return involucrados;
	}

	public void setInvolucrados(List<Involucrado> involucrados) {
		this.involucrados = involucrados;
	}

	public List<Honorario> getHonorarios() {
		if(honorarios == null)
			honorarios = new ArrayList<Honorario>();
		return honorarios;
	}

	public void setHonorarios(List<Honorario> honorarios) {
		this.honorarios = honorarios;
	}

	public List<Cuantia> getCuantias() {
		if(cuantias == null)
			cuantias = new ArrayList<Cuantia>();
		return cuantias;
	}

	public void setCuantias(List<Cuantia> cuantias) {
		this.cuantias = cuantias;
	}

	public List<Inculpado> getInculpados() {
		if(inculpados == null)
			inculpados = new ArrayList<Inculpado>();
		return inculpados;
	}

	public void setInculpados(List<Inculpado> inculpados) {
		this.inculpados = inculpados;
	}

	public List<Anexo> getAnexos() {
		if(anexos == null)
			anexos = new ArrayList<Anexo>();
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}

	public List<Provision> getProvisions() {
		if(provisions == null)
			provisions = new ArrayList<Provision>();
		return provisions;
	}

	public void setProvisions(List<Provision> provisions) {
		this.provisions = provisions;
	}

	public Double getMontoCautelar() {
		return montoCautelar;
	}

	public void setMontoCautelar(Double montoCautelar) {
		this.montoCautelar = montoCautelar;
	}
	
	public void addInvolucrado(Involucrado involucrado){
		involucrado.setExpediente(this);
		involucrados.add(involucrado);
	}
	
	public void addHonorario(Honorario honorario){
		honorario.setExpediente(this);
		honorarios.add(honorario);
	}
	
	public void addCuantia(Cuantia cuantia){
		cuantia.setExpediente(this);
		cuantias.add(cuantia);
	}

	public void addInculpado(Inculpado inculpado){
		inculpado.setExpediente(this);
		inculpados.add(inculpado);
	}
	
	public void addAnexo(Anexo anexo){
		anexo.setExpediente(this);
		anexos.add(anexo);
	}
	
	public void addProvision(Provision provision){
		provision.setExpediente(this);
		provisions.add(provision);
	}
	
	public void addResumen(Resumen resumen){
		resumen.setExpediente(this);
		resumens.add(resumen);
	}
	
	public void addActividadProcesal(ActividadProcesal actividadProcesal){
		actividadProcesal.setExpediente(this);
		actividadProcesals.add(actividadProcesal);
	}

	public List<ActividadProcesal> getActividadProcesals() {
		return actividadProcesals;
	}

	public void setActividadProcesals(List<ActividadProcesal> actividadProcesals) {
		this.actividadProcesals = actividadProcesals;
	}


	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public FormaConclusion getFormaConclusion() {
		return formaConclusion;
	}

	public void setFormaConclusion(FormaConclusion formaConclusion) {
		this.formaConclusion = formaConclusion;
	}

	public Character getFlagRevertir() {
		return flagRevertir;
	}

	public void setFlagRevertir(Character flagRevertir) {
		this.flagRevertir = flagRevertir;
	}
	public Set getExpedientes() {
		return this.expedientes;
	}

	public void setExpedientes(Set expedientes) {
		this.expedientes = expedientes;
	}
	
	public Object clone(){
		Expediente expediente= new Expediente();
		expediente.setHonorarios(this.honorarios);
		expediente.setInvolucrados(this.involucrados);
		expediente.setInculpados(this.inculpados);
		expediente.setCuantias(this.cuantias);
		expediente.setActividadProcesals(this.actividadProcesals);
		expediente.setAnexos(this.anexos);
		expediente.setProvisions(this.provisions);
		expediente.setResumens(this.resumens);
		
		expediente.setProceso(this.proceso);
		expediente.setVia(this.via);
		expediente.setCalificacion(this.calificacion);
		expediente.setContraCautela(this.contraCautela);
		expediente.setDescripcionCautelar(this.descripcionCautelar);
		expediente.setEstadoCautelar(this.estadoCautelar);
		expediente.setEstadoExpediente(this.estadoExpediente);
		expediente.setExpediente(this.expediente);
		expediente.setExpedientes(this.expedientes);
		expediente.setFechaCreacion(this.fechaCreacion);
		expediente.setFechaFinProceso(this.fechaFinProceso);
		expediente.setFechaInicioProceso(this.fechaInicioProceso);
		expediente.setFechaModificacion(this.fechaModificacion);
		expediente.setFlagRevertir(this.flagRevertir);
		expediente.setFormaConclusion(this.formaConclusion);
		expediente.setUsuarioModificacion(this.usuarioModificacion);
		expediente.setUsuarioCreacion(this.usuarioCreacion);
		expediente.setUsuario(this.usuario);
		expediente.setTipoExpediente(this.tipoExpediente);
		expediente.setTipoCautelar(this.tipoCautelar);
		expediente.setSecretario(this.secretario);
		expediente.setRiesgo(this.riesgo);
		expediente.setRecurrencia(this.recurrencia);
		expediente.setOrgano(this.organo);
		expediente.setOficina(this.oficina);
		expediente.setNumeroExpediente(this.numeroExpediente);
		expediente.setMontoCautelar(this.montoCautelar);
		expediente.setMoneda(this.moneda);
		expediente.setInstancia(this.instancia);
		expediente.setImporteCautelar(this.importeCautelar);
		
		return expediente;
	}

	public List<Resumen> getResumens() {
		return resumens;
	}

	public void setResumens(List<Resumen> resumens) {
		this.resumens = resumens;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Via getVia() {
		return via;
	}

	public void setVia(Via via) {
		this.via = via;
	}
	
	
}
