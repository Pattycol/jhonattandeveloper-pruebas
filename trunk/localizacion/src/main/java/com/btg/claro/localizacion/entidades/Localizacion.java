package com.btg.claro.localizacion.entidades;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the localizacion database table.
 * 
 */
@Entity
@Table(name="localizacion")
public class Localizacion implements IEntidad{
	private static final long serialVersionUID=1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_consulta")
	private Date fechaConsulta;

	private String telefono;

	private String resultado;

	// uni-directional many-to-one association to Celda
	@ManyToOne
	@JoinColumn(name="id_celda")
	private Celda celda;

	public Celda getCelda() {
		return celda;
	}

	public void setCelda(Celda celda) {
		this.celda = celda;
	}

	// uni-directional many-to-one association to Sesion
	@ManyToOne
	@JoinColumn(name="id_sesion")
	private Sesion sesion;

	public Localizacion(){
	}

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public Date getFechaConsulta(){
		return this.fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta){
		this.fechaConsulta=fechaConsulta;
	}

	public String getTelefono(){
		return this.telefono;
	}

	public void setTelefono(String telefono){
		this.telefono=telefono;
	}

	public Sesion getSesion(){
		return this.sesion;
	}

	public void setSesion(Sesion sesion){
		this.sesion=sesion;
	}

	public String getResultado(){
		return resultado;
	}

	public void setResultado(String resultado){
		this.resultado=resultado;
	}

}