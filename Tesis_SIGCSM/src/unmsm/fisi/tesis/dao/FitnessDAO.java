package unmsm.fisi.tesis.dao;

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
		
		String sql = " SELECT			CGF.NUMEROPOSICION, "+
									"	CGF.VALORADAPTACION, "+
									"	CGF.NUMEROGENERACION "+
					" 	FROM			SM_CROMOSOMA C "+
					"	INNER JOIN		SM_CROMOSOMAGENERACIONVALORFITNNES CGF ON C.NUMEROCROMOSOMA = CGF.NUMEROCROMOSOMA "+
					" 	WHERE			CGF.NUMEROGENERACION = " +numeroGeneracion +
					"   ORDER BY VALORADAPTACION DESC "; 
			
			try
			{
				PreparedStatement ps = cnx.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					MyConocimiento = new ConocimientoFitness();
					MyConocimiento.setNumeroGeneracion(rs.getInt("NUMEROGENERACION"));
					MyConocimiento.setPosicionCromosoma(rs.getInt("NUMEROPOSICION"));
					MyConocimiento.setValorFitness(rs.getDouble("VALORADAPTACION"));
					
					listaConocimientos.add(MyConocimiento);
				}
			
				
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
				
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
