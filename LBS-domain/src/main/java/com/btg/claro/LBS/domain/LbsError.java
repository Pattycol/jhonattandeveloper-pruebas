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
@Table(name="LBS_ERROR")
public class LbsError implements Entidad {
	
	@Id
	@SequenceGenerator(name="secuenciaLbsError",sequenceName="LBSERROR_SEQ",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="secuenciaLbsError")
	@Column(name="id_error")
	private Integer id;
	
	@Column
	private String mensaje;
	

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LbsError() {
		super();
	}

	public LbsError(Integer id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
	}

	
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setId(Integer id) {
		// TODO Auto-generated method stub
		this.id= id;
	}

}
