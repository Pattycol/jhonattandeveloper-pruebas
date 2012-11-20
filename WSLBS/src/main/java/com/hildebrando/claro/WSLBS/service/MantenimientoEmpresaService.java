package com.hildebrando.claro.WSLBS.service;

import java.util.List;

import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;

public interface MantenimientoEmpresaService{

	void guardarEmpresa(Empresa empresa,Usuario usuario);

	Empresa buscarEmpresa(int id);

	boolean existeEmpresa(String ruc);
	
	Empresa obtenerEmpresa(String ruc);

	List<Empresa> buscarEmpresas(String query, Usuario usuario);
	
	int obtenerMaximoLineas(String ruc);
	
	int obtenerCantidadLineas(String ruc);

}
