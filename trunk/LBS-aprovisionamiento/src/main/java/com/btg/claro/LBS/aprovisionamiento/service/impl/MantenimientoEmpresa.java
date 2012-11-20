package com.btg.claro.LBS.aprovisionamiento.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.aprovisionamiento.dao.EmpresaDAO;
import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoEmpresaService;
import com.btg.claro.LBS.aprovisionamiento.service.ProcesoAuditoriaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;

@Service("MantenimientoEmpresa")
public class MantenimientoEmpresa implements MantenimientoEmpresaService{
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private ProcesoAuditoriaService auditoriaService;

	@Transactional
	public void guardarEmpresa(Empresa empresa,Usuario usuario){	
		empresa.setConsultasPorMes(Integer.MAX_VALUE);
		if(empresa.getEstado()==null){
			empresa.setEstado(Constantes.ESTADO_INACTIVO);
		}
		if(empresa.getId()==null){
			empresa.setFechaCreacion(new Date());
			empresaDAO.guardar(empresa);
			auditoriaService.registrarNuevoObjeto(empresa,usuario);
		}
		else{
			Empresa existente=empresaDAO.get(empresa.getId());
			//System.out.println("existente:"+existente);			
			//System.out.println("empresa:"+empresa);			
			auditoriaService.registrarModificacionObjeto(existente,empresa,usuario);
			existente.setConsultasPorMes(empresa.getConsultasPorMes());
			existente.setEstado(empresa.getEstado());
			existente.setRazonSocial(empresa.getRazonSocial());
			existente.setRuc(empresa.getRuc());
			existente.setVista(empresa.getVista());		
			empresaDAO.guardar(existente);			
			//System.out.println("empresaDAO.get(empresa.getId()");			
			//existente=empresaDAO.get(empresa.getId());			
			//System.out.println("existente:"+existente);			
			//System.out.println("empresa:"+empresa);
		}
	}

	public Empresa buscarEmpresa(int id){
		return empresaDAO.get(id);
	}

	public boolean existeEmpresa(String ruc){
		return empresaDAO.buscarPorRUC(ruc)!=null;
	}

	public List<Empresa> buscarEmpresas(String query, Usuario usuario){
		if(query==null){
			query="";
		}
		query="%"+query+"%";
		return empresaDAO.buscarEmpresas(query,usuario);
	}

	

}
