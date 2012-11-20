package pe.com.claro.caef.web.services;


import pe.com.claro.caef.web.auth.Usuario;


public interface UsuarioService {
	
	// Cambia la clave del usuario
	public int actualizaClave(Usuario usuario) throws Exception;
	
	/* Actualiza el flag de condiciones de uso a 1 en la tabla USUARIO campo FLAGACEPTOCOND. Este campo sólo es 0 (cero) cuando se crea un usuario,
	 * una vez que se loguea le aparece la pantalla de Condiciones de Uso, cuando el usuario presione el botón Aceptar se ejecuta esta función. Si el campo
	 * es uno no le aparece la pantalla.
	 */ 
	
	/* Actualiza el flag de datos a 0 (cero) o 1 (uno). Si es cero, cuando se loguee le parecerá la pantalla de Actualización de Datos  
	 * si es uno no le aparecerá la pantalla. El flag se vuelve cero cuando se crea un "Nuevo Usuario" y cuando se "Cambia de Clave".
	 */
	public int actualizaFlagDatos(String telefono, int flag) throws Exception;

}
