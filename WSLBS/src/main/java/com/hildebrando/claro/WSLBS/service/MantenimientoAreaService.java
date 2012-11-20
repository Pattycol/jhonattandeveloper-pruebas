package com.hildebrando.claro.WSLBS.service;

import java.util.List;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;

public interface MantenimientoAreaService{

	List<Area> getAreasPorEmpresa(int idEmpresa);
	
	List<Area> getAreasPorEmpresaRuc(String ruc);

	List<Empresa> getEmpresas();

	void guardarArea(Area area,int padre,Usuario usuario);

	Area buscarArea(int id);

	boolean consultasPermitidas(int id,int consultasPorMes,int idEmpresa);

	Object getAreasPadre(int idEmpresa,Area area);

	Empresa getEmpresa(int idEmpresa);

}
