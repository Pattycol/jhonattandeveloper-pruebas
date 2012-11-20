package com.btg.claro.LBS.aprovisionamiento.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;

public interface MantenimientoEmpresaService{

	void guardarEmpresa(Empresa empresa,Usuario usuario);

	Empresa buscarEmpresa(int id);

	boolean existeEmpresa(String ruc);

	List<Empresa> buscarEmpresas(String query, Usuario usuario);

}
