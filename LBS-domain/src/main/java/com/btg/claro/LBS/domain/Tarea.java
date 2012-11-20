package com.btg.claro.LBS.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.btg.dao.entidad.Entidad;

@Entity
public class Tarea implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaTarea",sequenceName="TAREA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaTarea")
	@Column(name="id_tarea")
	private Integer id;
	
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Column(name="fecha_fin")
	private Date fechaFin;
	
	@Column(name="intervalo_minutos")
	private Integer intervaloMinutos;
	
	@Column(name="intervalo_horas")
	private Integer intervaloHoras;
	
	@Column(name="intervalo_dias")
	private String intervaloDias;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="hora_inicio")
	private Date horaInicio;
	
	@Column(name="hora_fin")
	private Date horaFin;
	
	private Character estado;
	
	@ManyToOne
	@JoinColumn(name="consultante")
	private Usuario consultante;
	
	@ManyToOne
	@JoinColumn(name="consultado")
	private Usuario consultado;
	
	@ManyToMany
	@JoinTable(name="consulta_por_tarea",joinColumns={@JoinColumn(name="id_tarea")},inverseJoinColumns={@JoinColumn(name="id_consulta")})
	private List<Consulta> consultas;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
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

	public Integer getIntervaloMinutos(){
		return intervaloMinutos;
	}

	public void setIntervaloMinutos(Integer intervaloMinutos){
		this.intervaloMinutos=intervaloMinutos;
	}

	public Integer getIntervaloHoras(){
		return intervaloHoras;
	}

	public void setIntervaloHoras(Integer intervaloHoras){
		this.intervaloHoras=intervaloHoras;
	}

	public String getIntervaloDias(){
		return intervaloDias;
	}

	public void setIntervaloDias(String intervaloDias){
		this.intervaloDias=intervaloDias;
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion=fechaCreacion;
	}

	public Date getHoraInicio(){
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio){
		this.horaInicio=horaInicio;
	}

	public Date getHoraFin(){
		return horaFin;
	}

	public void setHoraFin(Date horaFin){
		this.horaFin=horaFin;
	}

	public Character getEstado(){
		return estado;
	}

	public void setEstado(Character estado){
		this.estado=estado;
	}

	public Usuario getConsultante(){
		return consultante;
	}

	public void setConsultante(Usuario consultante){
		this.consultante=consultante;
	}

	public Usuario getConsultado(){
		return consultado;
	}

	public void setConsultado(Usuario consultado){
		this.consultado=consultado;
	}

	public List<Consulta> getConsultas(){
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas){
		this.consultas=consultas;
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
