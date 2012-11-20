package com.btg.claro.LBS.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.btg.dao.entidad.Entidad;

@Entity
public class Rol implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaRol",sequenceName="ROL_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaRol")
	@Column(name="id_rol")
	private Integer id;
	
	private String nombre;
	
	private String codigo;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	private Character estado;
	
	public Rol(){}

	public Rol(Integer id,String nombre,String codigo){
		super();
		this.id=id;
		this.nombre=nombre;
		this.codigo=codigo;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getNombre(){
		return nombre;
	}

	public void setNombre(String nombre){
		this.nombre=nombre;
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

	public String getCodigo(){
		return codigo;
	}

	public void setCodigo(String codigo){
		this.codigo=codigo;
	}

	@Override
	public int hashCode(){
		final int prime=31;
		int result=1;
		result=prime * result + ((id == null) ? 0 : id.hashCode());
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
		Rol other=(Rol) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString(){
		return "Rol [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + "]";
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
