package com.btg.claro.LBS.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.btg.dao.entidad.Entidad;


@Entity
@Table(name="TIPO_SERVICIO")
public class TipoServicio implements Entidad {

	@Id
	@SequenceGenerator(name="secuenciaTipoServicio", sequenceName="TIPOSERV_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="secuenciaTipoServicio")
	@Column(name="id_tipo_servicio")
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private Integer consultas_web;
	
	@Column
	private Integer maximo_lineas;
	
	public TipoServicio() {}
	
	public TipoServicio(Integer id, String nombre, Integer consultas_web,
			Integer maximo_lineas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.consultas_web = consultas_web;
		this.maximo_lineas = maximo_lineas;
	}

	
	public String getNombre() {
		return nombre;
	}

	public Integer getConsultas_web() {
		return consultas_web;
	}

	public Integer getMaximo_lineas() {
		return maximo_lineas;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setConsultas_web(Integer consultas_web) {
		this.consultas_web = consultas_web;
	}

	public void setMaximo_lineas(Integer maximo_lineas) {
		this.maximo_lineas = maximo_lineas;
	}

	

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}


	public void setId(Integer id) {
		this.id=id;
	}

	

}
