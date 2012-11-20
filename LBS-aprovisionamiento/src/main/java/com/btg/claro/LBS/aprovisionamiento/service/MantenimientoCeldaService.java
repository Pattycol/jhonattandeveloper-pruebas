package com.btg.claro.LBS.aprovisionamiento.service;

import java.util.List;

import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Usuario;

public interface MantenimientoCeldaService{

	List<Celda> getCeldas();

	void guardarCelda(Celda celda,Usuario usuario);

	Celda buscarCelda(int id);

	boolean existeCelda(String identificador);

	List<Celda> buscarCeldasPorIdentificador(String query);

}
