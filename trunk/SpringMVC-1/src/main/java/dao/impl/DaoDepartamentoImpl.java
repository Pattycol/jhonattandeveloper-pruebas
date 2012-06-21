package dao.impl;


import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import dao.DaoDepartamento;
import entidades.Departamento;

public class DaoDepartamentoImpl extends SimpleJdbcDaoSupport implements DaoDepartamento {

	public Departamento getDepartamento(Integer idDepartamento) {
		// TODO Auto-generated method stub
		return null;
	}

	public void agregarDepartamento(Departamento departamento) {
		String sql="insert into departamentos values(?,?)";
		
		getJdbcTemplate().update(sql, departamento.getId(),departamento.getDepartamento());
		
	}

	public void eliminarDepartamento(Integer idDepartamento) {
		// TODO Auto-generated method stub
		
	}

	public void modificarDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		
	}

}
