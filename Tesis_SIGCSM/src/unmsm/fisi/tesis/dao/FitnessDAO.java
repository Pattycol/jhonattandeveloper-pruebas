package unmsm.fisi.tesis.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import unmsm.fisi.tesis.coneccion.ConexionWithSQL;
import unmsm.fisi.tesis.entidad.Cromosoma;
import unmsm.fisi.tesis.servicio.ConocimientoFitness;


public class FitnessDAO {

	public void guardarValoreFitness(ConocimientoFitness objFitnes, Cromosoma cromosoma ) {
	
		Connection cnx = ConexionWithSQL.obtenerConexion();
		Statement st=null;
		try {
			// creamos el statement
			 st = cnx.createStatement();
			
			String sql = " INSERT INTO SM_VALORFITNNES(NUMEROGENERACION,NUMEROCORMOSOMA,NUMEROPOSICION, VALORADAPTACION) "+
					" VALUES("  + objFitnes.getNumeroGeneracion()+
							","+ cromosoma.getNumeroCromosoma()+ 
							 ","+ objFitnes.getPosicionCromosoma() + 
							 ","+ cromosoma.getValorAdaptacion() +")";
							//","+ objFitnes.getValorFitness() +")";
			
			//guardar los datos del cromosoma.
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
	
	public static List<ConocimientoFitness> obtenerMejoresConocimientosDeGeneracion(int numeroGeneracion) {
		
		Connection cnx = ConexionWithSQL.obtenerConexion();
		
		ConocimientoFitness MyConocimiento;
		List<ConocimientoFitness> listaConocimientos = new ArrayList<ConocimientoFitness>();
		
			try
			{
				
				CallableStatement cstmt = cnx.prepareCall("{call dbo.sp_obtnerMojoresCromosomasDeGeneracion( ? )}");
			    cstmt.setInt("NUMEROGENERACION", numeroGeneracion);
			     
			    ResultSet rs =  cstmt.executeQuery();
			     
			     
				while(rs.next())
				{
					MyConocimiento = new ConocimientoFitness();
					MyConocimiento.setNumeroGeneracion(rs.getInt("NUMEROGENERACION"));
					MyConocimiento.setPosicionCromosoma(rs.getInt("NUMEROPOSICION"));
					MyConocimiento.setValorFitness(rs.getDouble("VALORADAPTACION"));
					
					listaConocimientos.add(MyConocimiento);
				}
				rs.close();
				cstmt.close();
			
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.print(e.getMessage());
				
			}finally {
				 if (cnx != null) try { cnx.close(); } catch(Exception e) {e.printStackTrace();}
	        }
			
		return listaConocimientos;
	}
	
	public void eliminarGeneracionCromosomaPoblacionFitnes() {
		
		Connection cnx = ConexionWithSQL.obtenerConexion();
		try {
			CallableStatement cstmt = cnx.prepareCall("{call dbo.sp_eliminarGeneracionCromosomaPoblacionYFitnes( )}");
		      
		     //cstmt.setInt("NUMEROPOBLACIONFINAL", poblacionFinal);
		    cstmt.execute(); 
		    cstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			 if (cnx != null) try { cnx.close(); } catch(Exception e) {e.printStackTrace();}
            
        }
	}
	
	
}
