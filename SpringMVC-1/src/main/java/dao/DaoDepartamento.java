package dao;

import entidades.Departamento;

public interface DaoDepartamento {
	
	public Departamento getDepartamento(Integer idDepartamento);
	
	public void agregarDepartamento(Departamento departamento);
	
	public void eliminarDepartamento(Integer idDepartamento);
	
	public void modificarDepartamento(Departamento departamento);

}
