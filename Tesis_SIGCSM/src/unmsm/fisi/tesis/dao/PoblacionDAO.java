package unmsm.fisi.tesis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import unmsm.fisi.tesis.coneccion.ConexionWithSQL;
import unmsm.fisi.tesis.entidad.Configuracion;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.entidad.Poblacion;
import unmsm.fisi.tesis.servicio.ConocimientoFitness;

public class PoblacionDAO {

	
	public void asignarGeneracion(int numGeneracion, String detalle) {
		
		Connection cnx = ConexionWithSQL.obtenerConexion();
		Statement st=null;
		try {
			// creamos el statement
			 st = cnx.createStatement();
			
			String sql = " INSERT INTO SM_GENERACION(NUMEROGENERACION,DETALLE) "+
					" VALUES("  + numGeneracion+","+ detalle +")";
			
			st.executeUpdate(sql);
			cnx.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			 if (st != null) try { st.close(); } catch(Exception e) {e.printStackTrace();}
			 if (cnx != null) try { cnx.close(); } catch(Exception e) {e.printStackTrace();}
            
        }
	}
	
	public static void asignarGeneracionYPoblacion(int numGeneracion,  String detalle, int iteraciones, int poblacionInicial, int poblacionFinal) {
		  
		Connection con = ConexionWithSQL.obtenerConexion();
		//con.setAutoCommit(true);
		try {
			   
		      CallableStatement cstmt = con.prepareCall("{call dbo.sp_asignarGeneracionYPoblacion( ?, ?, ?, ?, ?)}");
		      
		      cstmt.setInt("NUMEROGENERACION", numGeneracion);
		      cstmt.setString("DETALLE", detalle);
		      cstmt.setInt("ITERACIONES", iteraciones);
		      cstmt.setInt("NUMEROPOBLACIONINICIAL", poblacionInicial);
		      cstmt.setInt("NUMEROPOBLACIONFINAL", poblacionFinal);
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
