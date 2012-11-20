package pe.com.claro.caef.dao.jdbc.implement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;




import pe.com.claro.caef.dao.UsuarioDao;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.util.CaeWebDSUtil;

public class UsuarioDaoImpl implements UsuarioDao {
	private String sqlInsertarUsuario;
	private String sqlActualizaClave;
	private String sqlActualizaFlagCondicionUso;
	private String sqlActualizaFlagManualDelUsuario;
	private String sqlActualizaFlagDatos;
	

	public int actualizaClave(final Usuario usuario) throws Exception {
		
		int estado = -1;
		Connection cn=CaeWebDSUtil.obtenerConexion();
		CallableStatement proc = null;

		   try
		   {
		      
		      proc = cn.prepareCall("{ call PKG_PORTALES_CLARO.SP_ACTUALIZA_CLAVE(?,?,?,?) }");
		      proc.registerOutParameter(1, Types.INTEGER);
		      proc.setString(2,usuario.getTelefonoMiClaroFija());
		      proc.setString(3,usuario.getEncPass());
		      proc.setBytes(4,usuario.getTdes_key());
		      proc.execute();
		      estado=proc.getInt(1);
		   }
		   finally
		   {
		      try
		      {
		         proc.close();
		      }
		      catch (SQLException e) {
		    	  e.printStackTrace();
		      }
		      cn.close();
		   }
		
		
		return estado;
	}

	public int actualizaFlagDatos(final String telefono, final int flag) throws Exception {

		int estado = -1;
		Connection cn=CaeWebDSUtil.obtenerConexion();
		CallableStatement proc = null;
		   try
		   {
		      
		      proc = cn.prepareCall("{ call PKG_PORTALES_CLARO.SP_ACTUALIZO_DATOS(?,?,?) }");
		      proc.registerOutParameter(1, Types.INTEGER);
		      proc.setString(2,String.valueOf(flag));
		      proc.setString(3,telefono);
		      proc.execute();
		      estado=proc.getInt(1);
		   }
		   finally
		   {
		      try
		      {
		         proc.close();
		      }
		      catch (SQLException e) {
		    	  e.printStackTrace();
		      }
		      cn.close();
		   }
		
		return estado;
	}

	public String getSqlInsertarUsuario() {
		return sqlInsertarUsuario;
	}

	public void setSqlInsertarUsuario(String sqlInsertarUsuario) {
		this.sqlInsertarUsuario = sqlInsertarUsuario;
	}

	public String getSqlActualizaClave() {
		return sqlActualizaClave;
	}

	public void setSqlActualizaClave(String sqlActualizaClave) {
		this.sqlActualizaClave = sqlActualizaClave;
	}

	public String getSqlActualizaFlagCondicionUso() {
		return sqlActualizaFlagCondicionUso;
	}

	public void setSqlActualizaFlagCondicionUso(String sqlActualizaFlagCondicionUso) {
		this.sqlActualizaFlagCondicionUso = sqlActualizaFlagCondicionUso;
	}

	public String getSqlActualizaFlagManualDelUsuario() {
		return sqlActualizaFlagManualDelUsuario;
	}

	public void setSqlActualizaFlagManualDelUsuario(
			String sqlActualizaFlagManualDelUsuario) {
		this.sqlActualizaFlagManualDelUsuario = sqlActualizaFlagManualDelUsuario;
	}

	public String getSqlActualizaFlagDatos() {
		return sqlActualizaFlagDatos;
	}

	public void setSqlActualizaFlagDatos(String sqlActualizaFlagDatos) {
		this.sqlActualizaFlagDatos = sqlActualizaFlagDatos;
	}
	
	

}
