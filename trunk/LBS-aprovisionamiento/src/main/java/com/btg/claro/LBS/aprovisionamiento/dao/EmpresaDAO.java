package com.btg.claro.LBS.aprovisionamiento.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface EmpresaDAO extends IDAO<Empresa>{

	List<Empresa> getEmpresasActivas();

	Empresa buscarPorRUC(String ruc);

	List<Empresa> buscarEmpresas(String query, Usuario usuario);
	

}
