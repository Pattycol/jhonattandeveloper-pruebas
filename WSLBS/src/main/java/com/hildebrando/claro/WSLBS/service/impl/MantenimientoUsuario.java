package com.hildebrando.claro.WSLBS.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Rol;
import com.btg.claro.LBS.domain.Usuario;
import com.hildebrando.claro.WSLBS.CLBSConsultaService.LBSConsultaService;
import com.hildebrando.claro.WSLBS.CLBSConsultaService.LBSConsultaServiceService;
import com.hildebrando.claro.WSLBS.dao.AreaDAO;
import com.hildebrando.claro.WSLBS.dao.EmpresaDAO;
import com.hildebrando.claro.WSLBS.dao.RolDAO;
import com.hildebrando.claro.WSLBS.dao.UsuarioDAO;
import com.hildebrando.claro.WSLBS.service.MantenimientoUsuarioService;
import com.hildebrando.claro.WSLBS.service.ProcesoAuditoriaService;
import com.hildebrando.claro.WSLBS.util.Constantes;
import com.hildebrando.claro.WSLBS.util.Util;

@Service("MantenimientoUsuarioService")
public class MantenimientoUsuario implements MantenimientoUsuarioService{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private AreaDAO areaDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private RolDAO rolDAO;
	
	@Autowired
	private ProcesoAuditoriaService auditoriaService;
	
    public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

    @Transactional
	public void guardarUsuario(Usuario usuario,String claveOld,int[] idsAreas,Usuario usuarioSesion){

		if(usuario.getEstado()==null){
			usuario.setEstado(Constantes.ESTADO_INACTIVO);
		}
		if(idsAreas.length>0){
			List<Area> areas=new ArrayList<Area>();
			for(int idArea : idsAreas){
				Area area=areaDAO.get(idArea);
				if(area!=null){
					areas.add(area);
				}
			}
			usuario.setAreas(areas);
		}
		if(!Util.vacio(claveOld)){
			usuario.setClave(claveOld);
		}
		else{
			if(!Util.vacio(usuario.getClave())){
				usuario.setClave(Util.toMD5(usuario.getClave()));
			}
		}
		Date hoy=new Date();
		if(usuario.getId()==null){
			usuario.setFechaCreacion(hoy);
			usuario.setFechaActualizacion(hoy);
			usuarioDAO.guardar(usuario);
			auditoriaService.registrarNuevoObjeto(usuario,usuarioSesion);
		}
		else{
			Usuario existente=usuarioDAO.get(usuario.getId());
			auditoriaService.registrarModificacionObjeto(existente,usuario,usuarioSesion);
			existente.setConsultasPorMes(usuario.getConsultasPorMes());
			existente.setEstado(usuario.getEstado());
			existente.setNombres(usuario.getNombres());
			existente.setApellidos(usuario.getApellidos());
			existente.setNumero(usuario.getNumero());
			existente.setClave(usuario.getClave());
			existente.setRol(usuario.getRol());
			existente.setAreas(usuario.getAreas());
			existente.setFechaActualizacion(hoy);
			usuarioDAO.guardar(existente);
		}
	}
	
	
    @Transactional
	public void guardarUsuario3(Usuario usuario,int[] idsAreas,Usuario usuarioSesion){
		
		if(idsAreas.length>0){
			List<Area> areas=new ArrayList<Area>();
			for(int idArea : idsAreas){
				Area area=areaDAO.get(idArea);
				if(area!=null){
					areas.add(area);
				}
			}
			usuario.setAreas(areas);
		}
		
		if(Util.vacio(usuario.getClave())){
		
			if(usuario.getRol().getId().equals(2) &&
					!usuario.getTipoServicio().getNombre().equals("LBS1") &&
							!usuario.getTipoServicio().getNombre().equals("LBS2")){//Usuario jefe
				
				String pass= Util.generaAleatorio() ;
				
				LBSConsultaServiceService endpointService= new LBSConsultaServiceService();
				
				LBSConsultaService lbsConsultaService  =endpointService.getLBSConsultaServicePort();
			
				lbsConsultaService.enviarPasswordSMS(usuario.getNumero(),pass);
				
				usuario.setClave(Util.toMD5(pass));
				
			}
		}
		
		Date hoy=new Date();
		if(usuario.getId()==null){
			usuario.setFechaCreacion(hoy);
			usuario.setFechaActualizacion(hoy);
            usuario.setClave_recuperada(Constantes.ESTADO_ACTIVO);
			usuarioDAO.guardar(usuario);
			auditoriaService.registrarNuevoObjeto(usuario,usuarioSesion);
		}
		else{
			Usuario existente=usuarioDAO.get(usuario.getId());
			auditoriaService.registrarModificacionObjeto(existente,usuario,usuarioSesion);
			existente.setConsultasPorMes(usuario.getConsultasPorMes());
			existente.setEstado(usuario.getEstado());
			existente.setNombres(usuario.getNombres());
			existente.setApellidos(usuario.getApellidos());
			existente.setNumero(usuario.getNumero());
			existente.setClave(usuario.getClave());
			existente.setRol(usuario.getRol());
			existente.setAreas(usuario.getAreas());
			existente.setFechaActualizacion(hoy);
			existente.setTipoServicio(usuario.getTipoServicio());
			usuarioDAO.guardar(existente);
		}
	}
	
    @Transactional
	public void guardarUsuario2(Usuario usuario){
		
		usuarioDAO.guardar(usuario);
		//auditoriaService.registrarNuevoObjeto(usuario,usuario);
	}
	
	
    @Transactional
	public void actualizarUsuarioExistente(Usuario usuario,Usuario usuarioSesion){
		
		Date hoy=new Date();
		if(usuario.getId()==null){
			usuario.setFechaCreacion(hoy);
			usuario.setFechaActualizacion(hoy);
			usuarioDAO.guardar(usuario);
			auditoriaService.registrarNuevoObjeto(usuario,usuarioSesion);
		}
		else{
			Usuario existente=usuarioDAO.get(usuario.getId());
			auditoriaService.registrarModificacionObjeto(existente,usuario,usuarioSesion);
			existente.setConsultasPorMes(usuario.getConsultasPorMes());
			existente.setEstado(usuario.getEstado());
			existente.setNombres(usuario.getNombres());
			existente.setApellidos(usuario.getApellidos());
			existente.setNumero(usuario.getNumero());
			existente.setClave(usuario.getClave());
			existente.setRol(usuario.getRol());
			existente.setAreas(usuario.getAreas());
			existente.setFechaActualizacion(hoy);
			usuarioDAO.guardar(existente);
		}
	}
	

    public Usuario buscarUsuario(int id){
		return usuarioDAO.get(id);
	}

    public Usuario buscarUsuarioPorNumero(String numero) {
		return usuarioDAO.buscarPorNumero(numero);
	}
	
	

    public List<Empresa> getEmpresas(){
		return empresaDAO.getEmpresasActivas();
	}
	

    public Rol getRol(int id){
		return rolDAO.get(id);
	}
	
    public Usuario obtenerUsuarioPorNumero(String numero) {
		return usuarioDAO.buscarPorNumero(numero);
	}
	
    public Rol obtenerRol(int id) {
		return rolDAO.get(id);
	}


    public List<Rol> getRoles(){
		return rolDAO.getRolesActivos();
	}


    public Map<String,Object> getDetalleAreas(Usuario usuario){
		Map<String,Object> datos=new HashMap<String,Object>();
		int idEmpresa=-1;
		List<Area> areas=areaDAO.getAreasPorUsuario(usuario);
		if(areas!=null && areas.size()>0){
			idEmpresa=areas.get(0).getEmpresa().getId();
			List<Area> activas=areaDAO.getAreasPorEmpresa(idEmpresa);
			if(activas!=null && activas.size()>0){
				List<Area> disponibles=new ArrayList<Area>();
				for(Area act : activas){
					boolean existe=false;
					for(Area a : areas){
						if(act.equals(a)){
							existe=true;
						}
					}
					if(!existe){
						disponibles.add(act);
					}
				}
				datos.put("disponibles",disponibles);
			}
			datos.put("asignadas",areas);
			datos.put("idEmpresa",idEmpresa);
		}
		return datos;
	}

    public boolean consultasPermitidas(int id,int consultasPorMes,List<Integer> idsAreas){
		int maxConsultas=0;
		List<Usuario> usuarios=new ArrayList<Usuario>();
		for(int idArea : idsAreas){
			Area area=areaDAO.get(idArea);
			if(area!=null){
				Integer cpm=area.getConsultasPorMes();
				if(cpm==null)
					cpm=0;
				maxConsultas+=cpm;
				List<Usuario> pertenecientes=usuarioDAO.getUsuariosPorArea(area);
				for(Usuario u : pertenecientes){
					if(u.getId()!=id && !usuarios.contains(u)){
						usuarios.add(u);
					}
				}
			}
		}
		if(consultasPorMes>maxConsultas)
			return false;
		int consultasAsignadas=0;
		for(Usuario usuario : usuarios){
			Integer cpm=usuario.getConsultasPorMes();
			if(cpm==null)
				cpm=0;
			consultasAsignadas+=cpm;
		}
		return (consultasAsignadas+consultasPorMes)<=maxConsultas;
	}


    public boolean existeUsuario(String numero){
		return usuarioDAO.buscarPorNumero(numero)!=null;
	}


    public List<Usuario> buscarUsuarios(String query){
		if(query==null){
			query="";
		}
		query="%"+query+"%";
		return usuarioDAO.buscarUsuarios(query);
	}

    @Transactional
	public List<Usuario> getUsuariosPorArea(Area area){
		return usuarioDAO.getUsuariosPorArea(area);
	}


    public Area getArea(int idArea){
		return areaDAO.get(idArea);
	}

    public List<Rol> getRolesNoAdmin() {
		return rolDAO.getRolesNoAdmin();
	}


    public List<Usuario> buscarUsuariosNoSuper(String query){
		if(query==null){
			query="";
		}
		query="%"+query+"%";
		return usuarioDAO.buscarUsuariosNoSuper(query);
	}


    public List<Usuario> buscarUsuariosNoAdmin(String query){
		if(query==null){
			query="";
		}
		query="%"+query+"%";
		return usuarioDAO.buscarUsuariosNoAdmin(query);
	}


    @Transactional
	public boolean eliminarUsuario(int idUsuario,Usuario usuarioSesion){
		Usuario usuario=usuarioDAO.get(idUsuario);
		if(usuario!=null){
			usuario.setEstado(Constantes.ESTADO_ELIMINADO);
			usuarioDAO.guardar(usuario);
			auditoriaService.registrarEliminacionObjeto(usuario,usuarioSesion);
			return true;
		}
		return false;
	}


    public Usuario buscarUsuarioEliminado(String numero) {
		Usuario usuario=usuarioDAO.buscarUsuarioEliminado(numero);
		if(usuario!=null){
			usuario.setAreas(areaDAO.getAreasPorUsuario(usuario));
		}
		return usuario;
	}

}
