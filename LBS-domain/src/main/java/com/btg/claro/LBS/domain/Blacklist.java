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
public class Blacklist implements Entidad{
	
	@Id
	@SequenceGenerator(name="secuenciaBlacklist",sequenceName="BLACKLIST_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaBlacklist")
	@Column(name="id_blacklist")
	private Integer id;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Auditable
	@Column(name="fecha_fin")
	private Date fechaFin;
	
	private Character estado;
	
	@Auditable
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuario;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Date getFechaCreacion(){
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion){
		this.fechaCreacion=fechaCreacion;
	}

	public Date getFechaFin(){
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin){
		this.fechaFin=fechaFin;
	}

	public Character getEstado(){
		return estado;
	}

	public void setEstado(Character estado){
		this.estado=estado;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public void setUsuario(Usuario usuario){
		this.usuario=usuario;
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
		Blacklist other=(Blacklist) obj;
		if(id == null){
			if(other.id != null)
				return false;
		}
		else if(!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
