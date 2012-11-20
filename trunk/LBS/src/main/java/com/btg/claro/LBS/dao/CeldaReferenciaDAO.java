package com.btg.claro.LBS.dao;

import java.util.List;

import com.btg.claro.LBS.celda.CeldaReferencia;
import com.btg.claro.LBS.domain.Celda;
import com.btg.dao.dao.IDAO;

public interface CeldaReferenciaDAO extends IDAO<CeldaReferencia>{

	List<CeldaReferencia> buscarNuevas(List<Celda> celdas);

}
