package com.btg.claro.LBS.aprovisionamiento.dao;

import com.btg.claro.LBS.domain.Blacklist;
import com.btg.dao.dao.IDAO;

public interface BlacklistDAO extends IDAO<Blacklist>{

	Blacklist buscarBlacklistPorNumero(String numero);

}
