/*
 * ConsultaTareaForm.java
 *
 * Created on 26 de mayo de 2005, 04:49 PM
 */

package com.stconsulting.lbsweb.consulta.form;

import org.apache.struts.action.ActionForm;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.stconsulting.lbsweb.util.Util;
import com.stconsulting.common.bean.Option;
import com.stconsulting.lbsweb.consulta.bean.Localizacion;
import com.stconsulting.lbsweb.consulta.bean.Mobile;
import com.stconsulting.lbsweb.consulta.bean.RangoHora;
import com.stconsulting.lbsweb.consulta.bean.Tarea;

/**
 * 
 * @author STconsulting
 */
public class ConsultaTareaForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID=8219438443303966406L;
	
	// Filtro de Consulta Tarea
	private String mobile;
	
	private Date fechaInicio;
	
	private String fechaInicioString;
	
	private Date fechaFin;
	
	private String fechaFinString;
	
	private String codEstado;
	
	private static List<Option> listaEstados;
	
	private List<Tarea> listaTareas;

	// Nueva Tarea
	private Integer idTarea;
	
	private String codFormato;
	
	private static List<Option> listaFormatos;
	
	private int cantidadMobilesConsulta;
	
	private List<Mobile> listaMobiles;
	
	private List<Localizacion> localizaciones;
	
	private String codPeriodo;
	
	private static List<Option> listaPeriodos;
	
	private String codHorario;
	
	private List<RangoHora> listaHorarios;

	private int horaInicio;
	
	private int horaFin;
	
	private String descHorario;
	
	private String[] selectedItems;
	
	private String[] items={"1","2","3","4","5","6","7"};
	
	private String intervalo;
	//private String descIntervalo;
	private String queryMapa="";

	private static List<Option> listaIntervalos;

	//private String descIntervalo;
	
	public ConsultaTareaForm(){

	}

	/**
	 * Getter for property codEstado.
	 * 
	 * @return Value of property codEstado.
	 */
	public java.lang.String getCodEstado(){
		return codEstado;
	}

	/**
	 * Setter for property codEstado.
	 * 
	 * @param codEstado
	 *            New value of property codEstado.
	 */
	public void setCodEstado(java.lang.String codEstado){
		this.codEstado=codEstado;
	}

	/**
	 * Getter for property fechaFin.
	 * 
	 * @return Value of property fechaFin.
	 */
	public Date getFechaFin(){
		if(fechaFin==null){
			Format f=new SimpleDateFormat("dd/MM/yy");
			try{
				fechaFin=(Date) f.parseObject(fechaFinString);
			}
			catch(ParseException e){
				return null;
			}
		}
		return fechaFin;
	}

	/**
	 * Setter for property fechaFin.
	 * 
	 * @param fechaFin
	 *            New value of property fechaFin.
	 */
	public void setFechaFin(Date fechaFin){
		this.fechaFin=fechaFin;
	}

	/**
	 * Getter for property fechaInicio.
	 * 
	 * @return Value of property fechaInicio.
	 */
	public Date getFechaInicio(){
		if(fechaInicio==null){
			Format f=new SimpleDateFormat("dd/MM/yy");
			try{
				fechaInicio=(Date) f.parseObject(fechaInicioString);
			}
			catch(ParseException e){
				return null;
			}
		}
		return fechaInicio;
	}

	/**
	 * Setter for property fechaInicio.
	 * 
	 * @param fechaInicio
	 *            New value of property fechaInicio.
	 */
	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio=fechaInicio;
	}

	/**
	 * Getter for property listaTareas.
	 * 
	 * @return Value of property listaTareas.
	 */
	public List<Tarea> getListaTareas(){

		return listaTareas;
	}

	/**
	 * Setter for property listaTareas.
	 * 
	 * @param listaTareas
	 *            New value of property listaTareas.
	 */
	public void setListaTareas(List<Tarea> listaTareas){
		this.listaTareas=listaTareas;
	}

	/**
	 * Getter for property mobile.
	 * 
	 * @return Value of property mobile.
	 */
	public java.lang.String getMobile(){
		return mobile;
	}

	/**
	 * Setter for property mobile.
	 * 
	 * @param mobile
	 *            New value of property mobile.
	 */
	public void setMobile(java.lang.String mobile){
		this.mobile=mobile;
	}

	/**
	 * Getter for property listaEstados.
	 * 
	 * @return Value of property listaEstados.
	 */
	public List<Option> getListaEstados(){
		return listaEstados;
	}

	/**
	 * Setter for property listaEstados.
	 * 
	 * @param listaEstados
	 *            New value of property listaEstados.
	 */
	public void setListaEstados(List<Option> listaEstados){
		ConsultaTareaForm.listaEstados=listaEstados;
	}

	/**
	 * Getter for property selectedItems.
	 * 
	 * @return Value of property selectedItems.
	 */
	public java.lang.String[] getSelectedItems(){
		return this.selectedItems;
	}

	/**
	 * Setter for property selectedItems.
	 * 
	 * @param selectedItems
	 *            New value of property selectedItems.
	 */
	public void setSelectedItems(java.lang.String[] selectedItems){
		this.selectedItems=selectedItems;
	}

	/**
	 * Getter for property items.
	 * 
	 * @return Value of property items.
	 */
	public java.lang.String[] getItems(){
		return this.items;
	}

	/**
	 * Setter for property items.
	 * 
	 * @param items
	 *            New value of property items.
	 */
	public void setItems(java.lang.String[] items){
		this.items=items;
	}

	public Integer getIdTarea(){
		return idTarea;
	}

	public void setIdTarea(Integer idTarea){
		this.idTarea=idTarea;
	}

	/**
	 * Getter for property codFormato.
	 * 
	 * @return Value of property codFormato.
	 */
	public java.lang.String getCodFormato(){
		return codFormato;
	}

	/**
	 * Setter for property codFormato.
	 * 
	 * @param codFormato
	 *            New value of property codFormato.
	 */
	public void setCodFormato(java.lang.String codFormato){
		this.codFormato=codFormato;
	}

	/**
	 * Getter for property listaFormatos.
	 * 
	 * @return Value of property listaFormatos.
	 */
	public List<Option> getListaFormatos(){
		if(listaFormatos == null){
			listaFormatos=Util.getListaTipoRpta();
		}
		return listaFormatos;
	}

	/**
	 * Setter for property listaFormatos.
	 * 
	 * @param listaFormatos
	 *            New value of property listaFormatos.
	 */
	public void setListaFormatos(List<Option> listaFormatos){
		ConsultaTareaForm.listaFormatos=listaFormatos;
	}

	/**
	 * Getter for property cantidadMobilesConsulta.
	 * 
	 * @return Value of property cantidadMobilesConsulta.
	 */
	public int getCantidadMobilesConsulta(){
		return cantidadMobilesConsulta;
	}

	/**
	 * Setter for property cantidadMobilesConsulta.
	 * 
	 * @param cantidadMobilesConsulta
	 *            New value of property cantidadMobilesConsulta.
	 */
	public void setCantidadMobilesConsulta(int cantidadMobilesConsulta){
		this.cantidadMobilesConsulta=cantidadMobilesConsulta;
	}

	/**
	 * Getter for property listaMobiles.
	 * 
	 * @return Value of property listaMobiles.
	 */
	public List<Mobile> getListaMobiles(){
		return listaMobiles;
	}

	/**
	 * Setter for property listaMobiles.
	 * 
	 * @param listaMobiles
	 *            New value of property listaMobiles.
	 */
	public void setListaMobiles(List<Mobile> listaMobiles){
		this.listaMobiles=listaMobiles;
	}

	/**
	 * Getter for property codPeriodo.
	 * 
	 * @return Value of property codPeriodo.
	 */
	public java.lang.String getCodPeriodo(){
		return codPeriodo;
	}

	/**
	 * Setter for property codPeriodo.
	 * 
	 * @param codPeriodo
	 *            New value of property codPeriodo.
	 */
	public void setCodPeriodo(java.lang.String codPeriodo){
		this.codPeriodo=codPeriodo;
	}

	/**
	 * Getter for property listaPeriodos.
	 * 
	 * @return Value of property listaPeriodos.
	 */
	public List<Option> getListaPeriodos(){
		if(listaPeriodos == null){
			listaPeriodos=Util.getListaPeriodos();
		}
		return listaPeriodos;
	}

	/**
	 * Setter for property listaPeriodos.
	 * 
	 * @param listaPeriodos
	 *            New value of property listaPeriodos.
	 */
	public void setListaPeriodos(List<Option> listaPeriodos){
		ConsultaTareaForm.listaPeriodos=listaPeriodos;
	}

	/**
	 * Getter for property codHorario.
	 * 
	 * @return Value of property codHorario.
	 */
	public java.lang.String getCodHorario(){
		return codHorario;
	}

	/**
	 * Setter for property codHorario.
	 * 
	 * @param codHorario
	 *            New value of property codHorario.
	 */
	public void setCodHorario(java.lang.String codHorario){
		this.codHorario=codHorario;
	}

	/**
	 * Getter for property listaHorarios.
	 * 
	 * @return Value of property listaHorarios.
	 */
	public List<RangoHora> getListaHorarios(){
		return listaHorarios;
	}

	/**
	 * Setter for property listaHorarios.
	 * 
	 * @param list
	 *            New value of property listaHorarios.
	 */
	public void setListaHorarios(List<RangoHora> list){
		this.listaHorarios=list;
	}

	/**
	 * Getter for property intervalo.
	 * 
	 * @return Value of property intervalo.
	 */
	public java.lang.String getIntervalo(){
		return intervalo;
	}

	/**
	 * Setter for property intervalo.
	 * 
	 * @param intervalo
	 *            New value of property intervalo.
	 */
	public void setIntervalo(java.lang.String intervalo){
		this.intervalo=intervalo;
	}

	/**
	 * Getter for property descHorario.
	 * 
	 * @return Value of property descHorario.
	 */
	public java.lang.String getDescHorario(){
		return descHorario;
	}

	/**
	 * Setter for property descHorario.
	 * 
	 * @param descHorario
	 *            New value of property descHorario.
	 */
	public void setDescHorario(java.lang.String descHorario){
		this.descHorario=descHorario;
	}

	/**
	 * Getter for property queryMapa.
	 * 
	 * @return Value of property queryMapa.
	 */
	public java.lang.String getQueryMapa(){
		return queryMapa;
	}

	/**
	 * Setter for property queryMapa.
	 * 
	 * @param queryMapa
	 *            New value of property queryMapa.
	 */
	public void setQueryMapa(java.lang.String queryMapa){
		this.queryMapa=queryMapa;
	}

	/**
	 * Getter for property listaPeriodos.
	 * 
	 * @return Value of property listaPeriodos.
	 */
	public List<Option> getListaIntervalos(){
		if(listaIntervalos == null){
			listaIntervalos=Util.getListaIntervalos();
		}
		return listaIntervalos;
	}

	/**
	 * Setter for property listaPeriodos.
	 * 
	 * @param listaPeriodos
	 *            New value of property listaPeriodos.
	 */
	public void setListaIntervalos(List<Option> listaIntervalos){
		ConsultaTareaForm.listaIntervalos=listaIntervalos;
	}

	/**
	 * Getter for property descIntervalo.
	 * 
	 * @return Value of property descIntervalo.
	 */
	public java.lang.String getDescIntervalo(){
		String descripcion="";
		if(intervalo != null){
			if(intervalo.equals("60"))
				descripcion="1 hora";
			else
				descripcion=intervalo + " minutos";
		}
		return descripcion;
	}
	
	public List<Localizacion> getLocalizaciones(){
		return localizaciones;
	}

	public void setLocalizaciones(List<Localizacion> localizaciones){
		this.localizaciones=localizaciones;
	}

	public String getFechaInicioString(){
		return fechaInicioString;
	}

	public void setFechaInicioString(String fechaInicioString){
		this.fechaInicioString=fechaInicioString;
	}

	public String getFechaFinString(){
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString){
		this.fechaFinString=fechaFinString;
	}
	
	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}
	
	/*public void setDescIntervalo(String descIntervalo) {
		this.descIntervalo = descIntervalo;
	}

	/**
	 * Setter for property descIntervalo.
	 * 
	 * @param descIntervalo
	 *            New value of property descIntervalo.
	 */
	/*public void setDescIntervalo(java.lang.String descIntervalo){
		this.descIntervalo=descIntervalo;
	}*/

}
