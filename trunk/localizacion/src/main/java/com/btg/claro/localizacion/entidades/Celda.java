package com.btg.claro.localizacion.entidades;

import javax.persistence.*;


/**
 * The persistent class for the celda database table.
 * 
 */
@Entity(name="com.btg.claro.localizacion.entidades.Celda")
@Table(name="celda")
public class Celda implements IEntidad {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private double x;

	private double y;

    public Celda() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

}