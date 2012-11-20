package com.btg.claro.LBS.aprovisionamiento.dao;

import java.util.List;

import com.btg.claro.LBS.domain.Rol;
import com.btg.dao.dao.IDAO;

public interface RolDAO extends IDAO<Rol>{

	List<Rol> getRolesActivos();

	List<Rol> getRolesNoAdmin();

}
