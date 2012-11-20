package com.btg.claro.LBS.aprovisionamiento.dao;

import java.util.List;

import com.btg.claro.LBS.domain.Celda;
import com.btg.dao.dao.IDAO;

public interface CeldaDAO extends IDAO<Celda>{

	List<Celda> getCeldas();

	Celda getPorIdentificador(String identificador);

	List<Celda> buscarPorIdentificador(String query);

}
