package com.btg.claro.LBS.dao;

import java.util.List;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface AreaDAO extends IDAO<Area>{

	List<Area> getAreasPorUsuario(Usuario usuario);
	
	Empresa getEmpresaAreaUsuario(String telefono);

}
