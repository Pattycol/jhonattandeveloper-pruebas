package com.btg.claro.LBS.celda;

import javax.persistence.Column;
import javax.persistence.Id;


import com.btg.dao.entidad.Entidad;

//@Entity
//@Table(name="")
public class CeldaReferencia implements Entidad{
	
	@Id
	@Column(name="id_celda")
	private Integer id;
	
	private String identificador;
	
	private Float x;
	
	private Float y;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id=id;
	}

	public String getIdentificador(){
		return identificador;
	}

	public void setIdentificador(String identificador){
		this.identificador=identificador;
	}

	public Float getX(){
		return x;
	}

	public void setX(Float x){
		this.x=x;
	}

	public Float getY(){
		return y;
	}

	public void setY(Float y){
		this.y=y;
	}

	public String getLabel(){
		// TODO Auto-generated method stub
		return null;
	}

}
