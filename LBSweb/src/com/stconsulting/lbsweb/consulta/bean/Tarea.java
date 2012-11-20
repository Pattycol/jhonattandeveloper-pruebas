/*
 * Tarea.java
 *
 * Created on 27 de mayo de 2005, 12:33 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author STconsulting
 */
public class Tarea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=5844995167204618929L;
	
	private Integer idTarea;
	
	private String mobile;
	
	private String descripcion;
	
	private String codFormato;
	
	private String descFormato;
	
	private String codPeriodo;
	
	private String descPeriodo;
	
	private String codHorario;
	
	private String descHorario;
	
	private String diaPeriodo;
	
	private int intervalo;
	
	private int cantPeriodo;
	
	private String codEstado;
	
	private String descEstado;
	
	private List<Mobile> listaMobiles;
	
	private String ind;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private String mobileOrigen;
	
	private Integer codEmpresa;
	
	private int horaInicio;
	
	private int horaFin;
	
	private Date createdDate;

	private Integer intervaloHoras;
	
	private String intervaloDias;
	
	public Integer getIdTarea(){
		return idTarea;
	}

	public void setIdTarea(Integer idTarea){
		this.idTarea=idTarea;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion=descripcion;
	}

	public String getCodFormato(){
		return codFormato;
	}

	public void setCodFormato(String codFormato){
		this.codFormato=codFormato;
	}

	public String getDescFormato(){
		return descFormato;
	}

	public void setDescFormato(String descFormato){
		this.descFormato=descFormato;
	}

	public String getCodPeriodo(){
		return codPeriodo;
	}

	public void setCodPeriodo(String codPeriodo){
		this.codPeriodo=codPeriodo;
	}

	public String getDescPeriodo(){
		return descPeriodo;
	}

	public void setDescPeriodo(String descPeriodo){
		this.descPeriodo=descPeriodo;
	}

	public String getCodHorario(){
		return codHorario;
	}

	public void setCodHorario(String codHorario){
		this.codHorario=codHorario;
	}

	public String getDescHorario(){
		return descHorario;
	}

	public void setDescHorario(String descHorario){
		this.descHorario=descHorario;
	}

	public String getDiaPeriodo(){
		return diaPeriodo;
	}

	public void setDiaPeriodo(String diaPeriodo){
		this.diaPeriodo=diaPeriodo;
	}

	public int getIntervalo(){
		return intervalo;
	}

	public void setIntervalo(int intervalo){
		this.intervalo=intervalo;
	}

	public int getCantPeriodo(){
		return cantPeriodo;
	}

	public void setCantPeriodo(int cantPeriodo){
		this.cantPeriodo=cantPeriodo;
	}

	public String getCodEstado(){
		return codEstado;
	}

	public void setCodEstado(String codEstado){
		this.codEstado=codEstado;
	}

	public String getDescEstado(){
		return descEstado;
	}

	public void setDescEstado(String descEstado){
		this.descEstado=descEstado;
	}

	public List<Mobile> getListaMobiles(){
		return listaMobiles;
	}

	public void setListaMobiles(List<Mobile> listaMobiles){
		this.listaMobiles=listaMobiles;
	}

	public String getInd(){
		return ind;
	}

	public void setInd(String ind){
		this.ind=ind;
	}

	public Date getFechaInicio(){
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio=fechaInicio;
	}

	public Date getFechaFin(){
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin){
		this.fechaFin=fechaFin;
	}

	public String getMobileOrigen(){
		return mobileOrigen;
	}

	public void setMobileOrigen(String mobileOrigen){
		this.mobileOrigen=mobileOrigen;
	}

	public Integer getCodEmpresa(){
		return codEmpresa;
	}

	public void setCodEmpresa(Integer codEmpresa){
		this.codEmpresa=codEmpresa;
	}

	public int getHoraInicio(){
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio){
		this.horaInicio=horaInicio;
	}

	public int getHoraFin(){
		return horaFin;
	}

	public void setHoraFin(int horaFin){
		this.horaFin=horaFin;
	}

	public Date getCreatedDate(){
		return createdDate;
	}

	public void setCreatedDate(Date createdDate){
		this.createdDate=createdDate;
	}

	public Integer getIntervaloHoras() {
		return intervaloHoras;
	}

	public void setIntervaloHoras(Integer intervaloHoras) {
		this.intervaloHoras = intervaloHoras;
	}

	public String getIntervaloDias() {
		return intervaloDias;
	}

	public void setIntervaloDias(String intervaloDias) {
		this.intervaloDias = intervaloDias;
	}

	public String getFechaInicioString(){
		Format f=new SimpleDateFormat("dd/MM/yy");
		return f.format(fechaInicio);
	}

	public String getFechaFinString(){
		Format f=new SimpleDateFormat("dd/MM/yy");
		return f.format(fechaFin);
	}

}
