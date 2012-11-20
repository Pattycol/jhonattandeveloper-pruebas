package com.btg.claro.LBS.dao;

import java.util.List;

import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface UsuarioDAO extends IDAO<Usuario>{
	
	Usuario buscarPorNumero(String numero);

	List<Usuario> getAdministradores();

}
