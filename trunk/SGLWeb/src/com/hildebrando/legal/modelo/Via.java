package com.hildebrando.legal.modelo;

// Generated 10-ago-2012 17:25:04 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Via generated by hbm2java
 */
public class Via implements java.io.Serializable {

	private int idVia;
	private Proceso proceso;
	private String nombre;
	private Integer prioridad;
	private Character estado;
	private Set avisos = new HashSet(0);
	private Set instancias = new HashSet(0);
	private Set expedientes = new HashSet(0);
	
	public Via() {
	}

	public Via(int idVia) {
		this.idVia = idVia;
	}

	public Via(int idVia, Proceso proceso, String nombre, Integer prioridad,
			Set avisos, Set instancias, Set expedientes) {
		this.idVia = idVia;
		this.proceso = proceso;
		this.nombre = nombre;
		this.prioridad = prioridad;
		this.avisos = avisos;
		this.instancias = instancias;
		this.expedientes = expedientes;
	}

	public int getIdVia() {
		return this.idVia;
	}

	public void setIdVia(int idVia) {
		this.idVia = idVia;
	}

	public Proceso getProceso() {
		return this.proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getInstancias() {
		return this.instancias;
	}

	public void setInstancias(Set instancias) {
		this.instancias = instancias;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Set getAvisos() {
		return avisos;
	}

	public void setAvisos(Set avisos) {
		this.avisos = avisos;
	}

	public Set getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(Set expedientes) {
		this.expedientes = expedientes;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

}
