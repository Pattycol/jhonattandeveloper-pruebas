package pe.com.claro.caef.dao;


import pe.com.claro.caef.web.auth.Usuario;


public interface UsuarioDao {
	public int actualizaClave(Usuario usuario) throws Exception;
	
	public int actualizaFlagDatos(String telefono,int flag) throws Exception;
}
