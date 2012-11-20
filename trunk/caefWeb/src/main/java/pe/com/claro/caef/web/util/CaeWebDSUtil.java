package pe.com.claro.caef.web.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jfree.util.Log;

public class CaeWebDSUtil {
	// Utilizaremos mas bien un pool de conexiones
	
	
	// creamos el metodo que nos permite obtener una conexion
	public static Connection obtenerConexion(){
		

		
		Connection cn=null;

		try {
			
			
			Context ctx = new InitialContext();
			//String raizContexto ="java:comp/env/";
			

			DataSource ds=
				(DataSource)ctx.lookup("claro.com.pe.cae.jdbc.dataSource.caeDS");
						
			
			cn = ds.getConnection();
			

			
			
		} catch (NamingException e) {
			
		  Log.info(e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return cn;
		
	}	

}
