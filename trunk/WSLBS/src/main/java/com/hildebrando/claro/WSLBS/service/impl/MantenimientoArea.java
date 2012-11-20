package com.hildebrando.claro.WSLBS.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;
import com.hildebrando.claro.WSLBS.dao.AreaDAO;
import com.hildebrando.claro.WSLBS.dao.EmpresaDAO;
import com.hildebrando.claro.WSLBS.service.MantenimientoAreaService;
import com.hildebrando.claro.WSLBS.service.ProcesoAuditoriaService;
import com.hildebrando.claro.WSLBS.util.Constantes;

@Service("MantenimientoAreaService")
public class MantenimientoArea implements MantenimientoAreaService{
	
	@Autowired
	private AreaDAO areaDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private ProcesoAuditoriaService auditoriaService;

	@Transactional
	public List<Area> getAreasPorEmpresa(int idEmpresa){
		return areaDAO.getAreasPorEmpresa(idEmpresa);
	}
	
	public List<Area> getAreasPorEmpresaRuc(String ruc){
		return areaDAO.getAreasPorEmpresaRuc(ruc);
	}

	public List<Empresa> getEmpresas(){
		return empresaDAO.getEmpresasActivas();
	}

	@Transactional
	public void guardarArea(Area area,int padre,Usuario usuario){
		
		if(padre!=-1){
			Area areaPadre=areaDAO.get(padre);
			if(areaPadre!=null)
				area.setPadre(areaPadre);
		}
		if(area.getId()==null){
			area.setFechaCreacion(new Date());
			area.setEstado(Constantes.ESTADO_ACTIVO);
			areaDAO.guardar(area);
			auditoriaService.registrarNuevoObjeto(area,usuario);
		}
		else{
			Area existente=areaDAO.get(area.getId());
			auditoriaService.registrarModificacionObjeto(existente,area,usuario);
			existente.setConsultasPorMes(area.getConsultasPorMes());
			existente.setEmpresa(area.getEmpresa());
			existente.setPadre(area.getPadre());
			existente.setEstado(area.getEstado());
			existente.setNombre(area.getNombre());
			areaDAO.guardar(existente);
		}
		
	}

	public Area buscarArea(int id){
		return areaDAO.get(id);
	}

	public boolean consultasPermitidas(int id,int consultasPorMes,int idEmpresa){
		Empresa empresa=empresaDAO.get(idEmpresa);
		if(empresa!=null){
			int maxConsultas=empresa.getConsultasPorMes();
			if(consultasPorMes>maxConsultas)
				return false;
			int consultas=0;
			Area area=areaDAO.get(id);
			List<Area> areas=areaDAO.getAreasPorEmpresa(idEmpresa);
			for(Area a: areas){
				if(!a.equals(area)){
					Integer cpm=a.getConsultasPorMes();
					if(cpm==null)
						cpm=0;
					consultas+=cpm;
				}
			}
			return (consultasPorMes+consultas)<=maxConsultas;
		}
		return true;
	}

	public List<Area> getAreasPadre(int idEmpresa,Area area){
		return areaDAO.getAreasPadre(idEmpresa,area);
	}

	public Empresa getEmpresa(int idEmpresa){
		return empresaDAO.get(idEmpresa);
	}

}
