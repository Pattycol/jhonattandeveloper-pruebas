package unmsm.fisi.tesis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import unmsm.fisi.tesis.coneccion.ConexionWithSQL;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Poblacion;
import unmsm.fisi.tesis.servicio.ConocimientoFitness;

public class CromosomaDAO {

	public static void guardarCromosoma( Cromosoma crom) {
		  
		Connection con = ConexionWithSQL.obtenerConexion();
		try {
			   
		      CallableStatement cstmt = con.prepareCall("{call dbo.sp_asignarCromosoma( ?, ?, ?, ?, ?, ?, ?, ?)}");
		      cstmt.setInt("NUMEROCROMOSOMA", crom.getNumeroCromosoma());
		      cstmt.setString("PARENTESCO", crom.getParentesco());
		      cstmt.setInt("GENERACIONACIMIENTO", crom.getGeneracionNacimiento());
		      cstmt.setInt("GENERACIONFALLECIMIENTO", crom.getGeneracionFallecimiento());
		      cstmt.setString("PADRE", crom.getPadre());
		      cstmt.setString("MADRE", crom.getMadre());
		      cstmt.setInt("FLAGSELECCION", 1);
		      cstmt.setString("ESTADO", crom.getEstado());
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
	
	public static void guardarDetalleCromosoma( ConocimientoFitness objFitnes,Cromosoma crom) {
		  
		Connection con = ConexionWithSQL.obtenerConexion();
		try {
			   
		      CallableStatement cstmt = con.prepareCall("{call dbo.sp_registrarDetalleCromosoma( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		      cstmt.setInt("NUMEROCROMOSOMA", crom.getNumeroCromosoma());
		      cstmt.setString("PARENTESCO", crom.getParentesco());
		      cstmt.setInt("GENERACIONACIMIENTO", crom.getGeneracionNacimiento());
		      cstmt.setInt("GENERACIONFALLECIMIENTO", crom.getGeneracionFallecimiento());
		      cstmt.setString("PADRE", crom.getPadre());
		      cstmt.setString("MADRE", crom.getMadre());
		      cstmt.setInt("FLAGSELECCION", 1);
		      cstmt.setString("ESTADO", crom.getEstado());
		      cstmt.setInt("NUMEROGENERACION", objFitnes.getNumeroGeneracion());
		      cstmt.setInt("NUMEROPOSICION", objFitnes.getPosicionCromosoma());
		      cstmt.setDouble("VALORADAPTACION", objFitnes.getValorFitness());
		      
		      //cstmt.registerOutParameter("managerID", java.sql.Types.INTEGER);
		      cstmt.execute();
		      
		      //System.out.println("MANAGER ID: " + cstmt.getInt("managerID"));
		      cstmt.close();
		   }
		   catch (Exception e) {
		      e.printStackTrace();
		      System.out.print(e.getMessage());
				
		   }
		   finally {
				 if (con != null) try { con.close(); } catch(Exception e) {e.printStackTrace();}
	        }
	}
	
	
}
