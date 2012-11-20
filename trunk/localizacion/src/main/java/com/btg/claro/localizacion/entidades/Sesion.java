package com.btg.claro.localizacion.entidades;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sesion database table.
 * 
 */
@Entity
@Table(name="sesion")
public class Sesion implements IEntidad {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private String token;

	//uni-directional many-to-one association to Aplicacion
    @ManyToOne
	@JoinColumn(name="id_aplicacion")
	private Aplicacion aplicacion;

    public Sesion() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Aplicacion getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
}