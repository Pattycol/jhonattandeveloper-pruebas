package com.hildebrando.legal.modelo;

// Generated 01-ago-2012 12:12:34 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * SituacionHonorario generated by hbm2java
 */
public class SituacionHonorario implements java.io.Serializable {

	private int idSituacionHonorario;
	private String descripcion;
	private Character estado;
	private Set honorarios = new HashSet(0);

	public SituacionHonorario() {
	}

	public SituacionHonorario(int idSituacionHonorario) {
		this.idSituacionHonorario = idSituacionHonorario;
	}

	public SituacionHonorario(int idSituacionHonorario, String descripcion,
			Set honorarios) {
		this.idSituacionHonorario = idSituacionHonorario;
		this.descripcion = descripcion;
		this.honorarios = honorarios;
	}

	public int getIdSituacionHonorario() {
		return this.idSituacionHonorario;
	}

	public void setIdSituacionHonorario(int idSituacionHonorario) {
		this.idSituacionHonorario = idSituacionHonorario;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getHonorarios() {
		return this.honorarios;
	}

	public void setHonorarios(Set honorarios) {
		this.honorarios = honorarios;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

}
