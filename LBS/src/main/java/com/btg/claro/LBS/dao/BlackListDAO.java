package com.btg.claro.LBS.dao;

import com.btg.claro.LBS.domain.Blacklist;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface BlackListDAO extends IDAO<Blacklist>{

	Blacklist buscarBlackListActivoPorNumeroDeUsuario(Usuario usuario);
	
}
