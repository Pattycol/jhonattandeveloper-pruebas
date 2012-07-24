package unmsm.fisi.tesis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import unmsm.fisi.tesis.coneccion.ConexionWithSQL;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.servicio.ConocimientoFitness;

public class ConfiguracionDAO {

	
	public static void guardarValoresConfiguracion(Configuracion conf, String codigoOrganizaciones, String numReglasConsiderar) {
		  
		Connection con = ConexionWithSQL.obtenerConexion();
		try {
			   
		      CallableStatement cstmt = con.prepareCall("{call dbo.sp_asignarValoresConfiguracion(?, ?, ?, ?, ?, ?, ?, ?)}");
		      cstmt.setInt("NUMEROPACIENTESACONSIDERAR", conf.getNumeroPacientes());
		      cstmt.setInt("NUMEROPOBLACIONACONSIDERAR", conf.getNumeroPoblacion());
		      cstmt.setDouble("PROBABIBLIDADCROSSOVER", conf.getProbabilidadCrossover_x());
		      cstmt.setDouble("PROBABILIDADMUTACION", conf.getProbabilidadMutacion_y());
		      cstmt.setInt("NUMEROGENERACIONACONSIDERAR", conf.getNumGeneraciones());
		      cstmt.setInt("NUMEROHALLAZGOSACONSIDERAR", conf.getNumHallazgos());
		      cstmt.setString("CODIGOORGANIZACIONES", codigoOrganizaciones);
		      cstmt.setString("LISTANUMEROREGLASACONSIDERAR", numReglasConsiderar);
		      //cstmt.registerOutParameter("managerID", java.sql.Types.INTEGER);
		      cstmt.execute();
		      //System.out.println("MANAGER ID: " + cstmt.getInt("managerID"));
		      cstmt.close();
		   }
		   catch (Exception e) {
		      e.printStackTrace();
		   }
		   finally {
				 if (con != null) try { con.close(); } catch(Exception e) {e.printStackTrace();}
	        }
	}
}
