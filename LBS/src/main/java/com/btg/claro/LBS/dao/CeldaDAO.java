package com.btg.claro.LBS.dao;

import com.btg.claro.LBS.domain.Celda;
import com.btg.dao.dao.IDAO;

public interface CeldaDAO extends IDAO<Celda>{

	Celda buscarCeldaPorIdentificador(String identificador);
}
