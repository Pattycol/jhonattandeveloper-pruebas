package com.jhonattandeveloper.pe.domain;

import java.io.Serializable;

public class Persona implements Serializable {
	
	private static final long serialVersionUID = 4212098772894294618L;
	private Integer id;
	private String first_name;
	private String last_name;
	private Double money;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
	

}
