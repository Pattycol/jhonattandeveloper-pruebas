
package unmsm.fisi.tesis.coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSetMetaData;





public class ConexionWithSQL {
	
	static{		
		try {
			// cargamos el driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						   
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection obtenerConexion(){
		Connection cnx = null;
		String driverClase = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbUrl = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SIGCSM;";
		//String dbUrl = "jdbc:sqlserver://localhost\\abc:1433;databaseName=SIGCSM;";
	        //configurar el inicio de sesión en el SQL MANAGEMENT STUDIO 
        String dbUser = "sa";
        String dbPass = "admin";

		try {
			Class.forName(driverClase);
			cnx = DriverManager.getConnection(dbUrl,dbUser,dbPass);
			/*"jdbc:sqlserver://192.168.16.19:1433;forwardReadOnlyMethod=direct" +
            "databaseName=MyDataBase;user=UserSQL;password=xxxxxxxx"*/
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cnx;
	}
	
	 public  void Prueba(){

	        // Create a variable for the connection string.
	        String connectionUrl = "jdbc:sqlserver://192.168.16.19:1433;forwardReadOnlyMethod=direct" +
	            "databaseName=MyDataBase;user=UserSQL;password=xxxxxxxx";

	        // Declare the JDBC objects.
	        Connection con = null;
	        Statement stmt = null;
	        ResultSet rs = null;

	            try {
	                // Establish the connection.
	                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	                    con = DriverManager.getConnection(connectionUrl);

	                    // Create and execute an SQL statement that returns some data.
	                    String SQL = "SELECT TOP 10 * FROM ApplicationUser;";
	                    stmt = con.createStatement();
	                    rs = stmt.executeQuery(SQL);

	                    // Iterate through the data in the result set and display it.
	                    while (rs.next()) {
	                        System.out.println(rs.getString(4) + " " + rs.getString(6));
	                    }
	            }

	        // Handle any errors that may have occurred.
	        catch (Exception e) {
	            e.printStackTrace();
	        }

	        finally {
	            if (rs != null) try { rs.close(); } catch(Exception e) {}
	                if (stmt != null) try { stmt.close(); } catch(Exception e) {}
	                if (con != null) try { con.close(); } catch(Exception e) {}
	        }
	    }
	
}













