package unmsm.fisi.tesis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import unmsm.fisi.tesis.coneccion.ConexionWithSQL;
import unmsm.fisi.tesis.servicio.ConocimientoFitness;


public class FitnessDAO {

	public void guardarValoreFitness(ConocimientoFitness objFitnes ) {
	
		Connection cnx = ConexionWithSQL.obtenerConexion();
		Statement st=null;
		try {
			// creamos el statement
			 st = cnx.createStatement();
			
			String sql = " INSERT INTO SM_VALORFITNNES(NUMEROGENERACION,NUMEROCROMOSOMA, VALOR) "+
					" VALUES("  + objFitnes.getNumeroGeneracion()+
							 ","+ objFitnes.getPosicionCromosoma() + 
							 ","+ objFitnes.getValorFitness() +")";
			
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
	
	public List<ConocimientoFitness> obtenerMejoresConocimientosDeGeneracion(int numeroGeneracion) throws Exception {
		
		Connection cnx = ConexionWithSQL.obtenerConexion();
		
		ConocimientoFitness MyConocimiento;
		List<ConocimientoFitness> listaConocimientos = new ArrayList<ConocimientoFitness>();
		
		String sql = " SELECT NUMEROGENERACION, NUMEROCROMOSOMA, VALOR FROM dbo.SM_VALORFITNNES " + 
					 " WHERE NUMEROGENERACION = "+numeroGeneracion +
					 " ORDER BY VALOR DESC "; 
			
			try
			{
				PreparedStatement ps = cnx.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					MyConocimiento = new ConocimientoFitness();
					MyConocimiento.setNumeroGeneracion(rs.getInt("NUMEROGENERACION"));
					MyConocimiento.setPosicionCromosoma(rs.getInt("NUMEROCROMOSOMA"));
					MyConocimiento.setValorFitness(rs.getDouble("VALOR"));
					
					listaConocimientos.add(MyConocimiento);
				}
			
				
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
				throw new Exception(e.getMessage());
			}
			
		return listaConocimientos;
	}
	
	public void eliminarValoresFitness() {
		
		Connection cnx = ConexionWithSQL.obtenerConexion();
		Statement st=null;
		try {
			// creamos el statement
			 st = cnx.createStatement();
			
			String sql = " DELETE FROM dbo.SM_VALORFITNNES ";
			
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
	
	
}
