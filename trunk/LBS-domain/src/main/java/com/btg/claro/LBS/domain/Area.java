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

import com.btg.claro.LBS.auditoria.Auditable;
import com.btg.dao.entidad.Entidad;

@Entity
public class Area implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaArea",sequenceName="AREA_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaArea")
	@Column(name="id_area")
	private Integer id;
	
	@Auditable
	private String nombre;
	
	@Auditable
	@Column(name="consultas_por_mes")
	private Integer consultasPorMes;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	
	private Character estado;
	
	@Auditable
	@ManyToOne
	@JoinColumn(name="padre")
	private Area padre;
	
	@Auditable
	@ManyToOne
	@JoinColumn(name="empresa")
	private Empresa empresa;
	
	public Area(){}

	public Area(Integer id,String nombre,Integer consultasPorMes){
		super();
		this.id=id;
		this.nombre=nombre;
		this.consultasPorMes=consultasPorMes;
	}
	
	public Area(Integer id,String nombre,Integer consultasPorMes,Empresa empresa){
		super();
		this.id=id;
		this.nombre=nombre;
		this.consultasPorMes=consultasPorMes;
		this.empresa=empresa;
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

	public Integer getConsultasPorMes(){
		return consultasPorMes;
	}

	public void setConsultasPorMes(Integer consultasPorMes){
		this.consultasPorMes=consultasPorMes;
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

	public Area getPadre(){
		return padre;
	}

	public void setPadre(Area padre){
		this.padre=padre;
	}

	public Empresa getEmpresa(){
		return empresa;
	}

	public void setEmpresa(Empresa empresa){
		this.empresa=empresa;
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
		Area other=(Area) obj;
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
		return "Area [id=" + id + ", nombre=" + nombre + ", empresa=" + empresa + "]";
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
