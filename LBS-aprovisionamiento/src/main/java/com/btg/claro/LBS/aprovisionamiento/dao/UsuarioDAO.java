package com.btg.claro.LBS.aprovisionamiento.dao;

import java.util.List;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface UsuarioDAO extends IDAO<Usuario>{

	Usuario buscarPorNumeroClave(String numero,String clave);

	List<Usuario> getUsuarios();

	List<Usuario> getUsuariosPorArea(Area area);

	Usuario buscarPorNumero(String numero);

	List<Usuario> buscarUsuarios(String query);

	List<Usuario> buscarUsuariosNoSuper(String query);

	List<Usuario> buscarUsuariosNoAdmin(String query);

	Usuario buscarUsuarioEliminado(String numero);

}
