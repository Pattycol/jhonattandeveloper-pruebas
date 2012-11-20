package com.btg.claro.LBS.aprovisionamiento.service;

import java.util.List;

import com.btg.claro.LBS.domain.Blacklist;
import com.btg.claro.LBS.domain.Usuario;

public interface MantenimientoBlacklistService{

	List<Blacklist> getBlacklists();

	void guardarBlacklist(Blacklist blacklist,String numero,Usuario usuario);

	Blacklist buscarBlacklist(int id);

	boolean existeBlacklist(String numero);

	boolean existeUsuario(String numero);

}
