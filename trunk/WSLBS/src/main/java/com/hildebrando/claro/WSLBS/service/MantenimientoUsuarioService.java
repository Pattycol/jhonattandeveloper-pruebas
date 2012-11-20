package com.hildebrando.claro.WSLBS.service;

import java.util.List;
import java.util.Map;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Rol;
import com.btg.claro.LBS.domain.Usuario;

public interface MantenimientoUsuarioService{

	List<Usuario> getUsuarios();

	void guardarUsuario(Usuario usuario,String claveOld,int[] idsAreas,Usuario usuarioSesion);

	Usuario buscarUsuario(int id);

	List<Empresa> getEmpresas();

	List<Rol> getRoles();

	Map<String,Object> getDetalleAreas(Usuario usuario);

	boolean consultasPermitidas(int id,int consultasPorMes,List<Integer> idsAreas);

	boolean existeUsuario(String numero);

	List<Usuario> buscarUsuarios(String query);

	List<Usuario> getUsuariosPorArea(Area area);

	Area getArea(int idArea);

	List<Rol> getRolesNoAdmin();

	List<Usuario> buscarUsuariosNoSuper(String query);

	List<Usuario> buscarUsuariosNoAdmin(String query);

	boolean eliminarUsuario(int idUsuario,Usuario usuarioSesion);

	Usuario buscarUsuarioEliminado(String numero);
	
	void actualizarUsuarioExistente(Usuario usuario, Usuario usuarioSesion);
	
	Usuario buscarUsuarioPorNumero(String numero);
	
	
	void guardarUsuario2(Usuario usuario);
	
	Rol getRol(int id);
	
	Usuario obtenerUsuarioPorNumero(String numero);
	
	Rol obtenerRol(int id);
	
	void guardarUsuario3(Usuario usuario,int[] idsAreas,Usuario usuarioSesion);
		
}
