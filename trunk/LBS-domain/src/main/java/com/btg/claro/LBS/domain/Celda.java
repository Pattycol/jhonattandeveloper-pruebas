package com.btg.claro.LBS.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.btg.claro.LBS.auditoria.Auditable;
import com.btg.dao.entidad.Entidad;

@Entity
public class Celda implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaDireccionCelda",sequenceName="CELDA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaDireccionCelda")
	@Column(name="id_celda")
	private Integer id;
	
	@Auditable
	private String identificador;
	
	@Auditable
	private String direccion;
	
	@Auditable
	private Float x;
	
	@Auditable
	private Float y;	

	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	private Character estado;
	
	@Column(name="ciudad")
	private String ciudad;
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Celda(){}
	
	public Celda(String identificador) {
		super();
		this.identificador=identificador;
	}

	public Celda(Integer id,String identificador,String direccion){
		super();
		this.id=id;
		this.identificador=identificador;
		this.direccion=direccion;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getDireccion(){
		return direccion;
	}

	public void setDireccion(String direccion){
		this.direccion=direccion;
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion=fechaCreacion;
	}

	public Character getEstado(){
		return estado;
	}

	public void setEstado(Character estado){
		this.estado=estado;
	}

	public Date getFechaModificacion(){
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion){
		this.fechaModificacion=fechaModificacion;
	}

	public Float getX(){
		return x;
	}

	public void setX(Float x){
		this.x=x;
	}

	public Float getY(){
		return y;
	}

	public void setY(Float y){
		this.y=y;
	}

	@Override
	public int hashCode(){
		final int prime=31;
		int result=1;
		result=prime * result + ((id == null) ? 0 : id.hashCode());
		result=prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Celda other=(Celda) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		if(identificador == null){
			if(other.identificador != null)
				return false;
		}
		else if(!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
