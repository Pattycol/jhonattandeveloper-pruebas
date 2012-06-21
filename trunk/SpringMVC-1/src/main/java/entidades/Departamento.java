package entidades;

import java.io.Serializable;

public class Departamento implements Serializable{
	
	private Integer id;
	private String departamento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	

}
