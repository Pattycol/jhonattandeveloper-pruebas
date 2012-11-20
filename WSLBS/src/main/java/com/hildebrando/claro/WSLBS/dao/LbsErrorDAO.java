package com.hildebrando.claro.WSLBS.dao;

import com.btg.claro.LBS.domain.LbsError;
import com.btg.dao.dao.IDAO;

public interface LbsErrorDAO extends IDAO<LbsError> {
	
	LbsError obtenerMensaje(int id);

}
