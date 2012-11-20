package com.btg.claro.LBS.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import com.btg.dao.entidad.Entidad;

@Entity
public class Consulta implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaConsulta",sequenceName="CONSULTA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaConsulta")
	@Column(name="id_consulta")
	private Integer id;
	
	@Column(name="fecha_consulta")
	private Date fechaConsulta;
	
	@Column(name="fecha_inicio")
	private Date fechaInicio;
	
	@Column(name="fecha_resultado")
	private Date fechaResultado;
	
	private String resultado;
	
	private String tipo;
	
	@Column(name="codigo_error")
	private Integer codigoError;
	
	private String hlr;
	
	private String vlr;
	
	@ManyToOne
	@JoinColumn(name="consultante")
	private Usuario consultante;
	
	@ManyToOne
	@JoinColumn(name="consultado")
	private Usuario consultado;
	
	@ManyToOne
	@JoinColumn(name="id_celda")
	private Celda celda;
	
	private transient String idCelda;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Date getFechaConsulta(){
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta){
		this.fechaConsulta=fechaConsulta;
	}

	public Date getFechaInicio(){
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio){
		this.fechaInicio=fechaInicio;
	}

	public Date getFechaResultado(){
		return fechaResultado;
	}

	public void setFechaResultado(Date fechaResultado){
		this.fechaResultado=fechaResultado;
	}

	public String getResultado(){
		return resultado;
	}

	public void setResultado(String resultado){
		this.resultado=resultado;
	}

	public String getTipo(){
		return tipo;
	}

	public void setTipo(String tipo){
		this.tipo=tipo;
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

	public Celda getCelda(){
		return celda;
	}

	public void setCelda(Celda celda){
		this.celda=celda;
	}

	public Integer getCodigoError(){
		return codigoError;
	}

	public void setCodigoError(Integer codigoError){
		this.codigoError=codigoError;
	}

	public String getHlr(){
		return hlr;
	}

	public void setHlr(String hlr){
		this.hlr=hlr;
	}

	public String getVlr(){
		return vlr;
	}

	public void setVlr(String vlr){
		this.vlr=vlr;
	}

	public String getIdCelda(){
		return idCelda;
	}

	public void setIdCelda(String idCelda){
		this.idCelda=idCelda;
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
