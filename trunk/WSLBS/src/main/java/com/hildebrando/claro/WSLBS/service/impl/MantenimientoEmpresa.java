package com.hildebrando.claro.WSLBS.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.TipoServicio;
import com.btg.claro.LBS.domain.Usuario;
import com.hildebrando.claro.WSLBS.dao.AreaDAO;
import com.hildebrando.claro.WSLBS.dao.EmpresaDAO;
import com.hildebrando.claro.WSLBS.dao.TipoServicioDAO;
import com.hildebrando.claro.WSLBS.dao.UsuarioDAO;
import com.hildebrando.claro.WSLBS.service.MantenimientoEmpresaService;
import com.hildebrando.claro.WSLBS.service.ProcesoAuditoriaService;
import com.hildebrando.claro.WSLBS.util.Constantes;

@Service("MantenimientoEmpresa")
public class MantenimientoEmpresa implements MantenimientoEmpresaService{
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private ProcesoAuditoriaService auditoriaService;
	
	@Autowired
	private TipoServicioDAO tipoServicioDAO;

	@Autowired
	private AreaDAO areaDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void guardarEmpresa(Empresa empresa,Usuario usuario){	
	//	empresa.setConsultasPorMes(Integer.MAX_VALUE);
//		if(empresa.getEstado()==null){
//			empresa.setEstado(Constantes.ESTADO_INACTIVO);
//		}
		if(empresa.getId()==null){
			empresa.setFechaCreacion(new Date());
			empresa.setVista(false);	
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
			existente.setFechaCreacion(empresa.getFechaCreacion());
			existente.setVista(empresa.getVista());		
			existente.setTipoServicio(empresa.getTipoServicio());
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

	public Empresa obtenerEmpresa(String ruc) {
		return empresaDAO.buscarPorRUC(ruc);
	}


	public int obtenerMaximoLineas(String ruc) {
		// TODO Auto-generated method stub
		
		Empresa empresa= empresaDAO.buscarPorRUC(ruc);
		if(empresa!=null && empresa.getTipoServicio()!=null){
			TipoServicio tipoServicio= tipoServicioDAO.get(empresa.getTipoServicio().getId());
			
			return tipoServicio.getMaximo_lineas();
		}else{ 
			return -1;
		
		
		}

	}


	public int obtenerCantidadLineas(String ruc) {
		// TODO Auto-generated method stub
		Empresa empresa= empresaDAO.buscarPorRUC(ruc);
		int contadorUsuarios=0;
		
		if (empresa != null) {

			// Para obtener areas de empresa
			List<Area> listAreas	 = areaDAO.getAreasPorEmpresa(empresa.getId());
			
			for (int i = 0; i < listAreas.size(); i++) {
				
				// Para obtener usuarios por area
				List<Usuario> listUsuariosxArea = usuarioDAO.getUsuariosPorArea(listAreas.get(i));

				for (int j = 0; j < listUsuariosxArea.size(); j++) {
					Usuario usuario = new Usuario();
					usuario = listUsuariosxArea.get(j);
					if(usuario!=null  && usuario.getEstado().equals(Constantes.ESTADO_ACTIVO)){
						
						contadorUsuarios++;
					}

				}

			}
			
			return contadorUsuarios;

		}else{
			
		 return -1;
		}
	}

}
