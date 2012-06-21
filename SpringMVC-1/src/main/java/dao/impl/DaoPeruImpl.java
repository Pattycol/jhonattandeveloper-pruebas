package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import dao.DaoPeru;
import entidades.Departamento;

public class DaoPeruImpl implements DaoPeru{
	
	private SimpleJdbcTemplate simpleJdbcTemplate;
	
	private JdbcTemplate jdbcTemplate;
	

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
		this.simpleJdbcTemplate = simpleJdbcTemplate;
	}

	public List<Object[]> query() {
		String sql =
            "SELECT "
            + "departamento,"
            + "provincia,"
            + "distrito "
            + "FROM departamentos "
            + "INNER JOIN provincias "
            + "ON departamentos.idDepartamento=provincias.idDepartamento "
            + "INNER JOIN distritos "
            + "ON provincias.idProvincia=distritos.idProvincia";
		
		
		RowMapper<Object[]> mapper=new RowMapper<Object[]>() {
	           public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException{
	               Object[] object=new Object[3];
	               object[0]=rs.getString("departamento");
	               object[1]=rs.getString("provincia");
	               object[2]=rs.getString("distrito");
	               return object;
	           }
	         
	        };
	        List<Object[]> lista= jdbcTemplate.query(sql, new Object[]{}, mapper);
		
		
		return lista;
	}


	public void addDepartamento(Departamento departamento) {
		
		String sql="insert into departamentos values(?,?)";
		
		jdbcTemplate.update(sql, departamento.getId(),departamento.getDepartamento());
		
		
	}
	
	

}
