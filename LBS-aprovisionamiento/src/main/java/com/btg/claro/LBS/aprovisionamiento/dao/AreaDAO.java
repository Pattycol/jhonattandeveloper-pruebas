package com.btg.claro.LBS.aprovisionamiento.dao;

import java.util.List;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface AreaDAO extends IDAO<Area>{

	List<Area> getAreasPorEmpresa(int idEmpresa);

	List<Area> getAreasPadre(int idEmpresa,Area area);

	List<Area> getAreasPorUsuario(Usuario usuario);

}
