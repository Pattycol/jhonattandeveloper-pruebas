package dao;

import java.util.List;

import entidades.Departamento;


public interface DaoPeru {

	public List<Object[]> query();
	
	public void addDepartamento(Departamento departamento);
}
