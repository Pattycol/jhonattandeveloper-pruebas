package com.btg.claro.localizacion.entidades;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lista_negra database table.
 * 
 */
@Entity
@Table(name="lista_negra")
public class ListaNegra implements IEntidad {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="fecha_bloqueo")
	private Date fechaBloqueo;

	private String telefono;

	//uni-directional many-to-one association to Aplicacion
    @ManyToOne
	@JoinColumn(name="id_aplicacion")
	private Aplicacion aplicacion;

    public ListaNegra() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaBloqueo() {
		return this.fechaBloqueo;
	}

	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo = fechaBloqueo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Aplicacion getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
}