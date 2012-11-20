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
public class Auditoria implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaAuditoria",sequenceName="AUDITORIA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaAuditoria")
	@Column(name="id_auditoria")
	private Integer id;
	
	private String accion;
	
	private String entidad;
	
	private Integer identificador;
	
	private String columna;
	
	@Column(name="valor_antiguo")
	private String valorAntiguo;
	
	@Column(name="valor_nuevo")
	private String valorNuevo;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getAccion(){
		return accion;
	}

	public void setAccion(String accion){
		this.accion=accion;
	}

	public String getEntidad(){
		return entidad;
	}

	public void setEntidad(String entidad){
		this.entidad=entidad;
	}

	public Integer getIdentificador(){
		return identificador;
	}

	public void setIdentificador(Integer identificador){
		this.identificador=identificador;
	}

	public String getColumna(){
		return columna;
	}

	public void setColumna(String columna){
		this.columna=columna;
	}

	public String getValorAntiguo(){
		return valorAntiguo;
	}

	public void setValorAntiguo(String valorAntiguo){
		this.valorAntiguo=valorAntiguo;
	}

	public String getValorNuevo(){
		return valorNuevo;
	}

	public void setValorNuevo(String valorNuevo){
		this.valorNuevo=valorNuevo;
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion=fechaCreacion;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public void setUsuario(Usuario usuario){
		this.usuario=usuario;
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
