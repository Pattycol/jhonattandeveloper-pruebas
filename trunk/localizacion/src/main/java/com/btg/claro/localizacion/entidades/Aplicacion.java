package com.btg.claro.localizacion.entidades;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aplicacion database table.
 * 
 */
@Entity
@Table(name="aplicacion")
public class Aplicacion implements IEntidad {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	private String ip;

	private String nombre;

	private String password;
	
	private Integer conexiones;
	
	

    public Integer getConexiones() {
		return conexiones;
	}

	public void setConexiones(Integer conexiones) {
		this.conexiones = conexiones;
	}

	public Aplicacion() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}