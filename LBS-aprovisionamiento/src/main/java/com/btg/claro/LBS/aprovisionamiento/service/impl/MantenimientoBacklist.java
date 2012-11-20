package com.btg.claro.LBS.aprovisionamiento.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.aprovisionamiento.dao.BlacklistDAO;
import com.btg.claro.LBS.aprovisionamiento.dao.UsuarioDAO;
import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoBlacklistService;
import com.btg.claro.LBS.aprovisionamiento.service.ProcesoAuditoriaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Blacklist;
import com.btg.claro.LBS.domain.Usuario;

@Service("MantenimientoBlaklistService")
public class MantenimientoBacklist implements MantenimientoBlacklistService{
	
	@Autowired
	private BlacklistDAO blacklistDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ProcesoAuditoriaService auditoriaService;

	public List<Blacklist> getBlacklists(){
		return blacklistDAO.getTodos();
	}

	@Transactional
	public void guardarBlacklist(Blacklist blacklist,String numero,Usuario usuario){
		Usuario bloqueado=usuarioDAO.buscarPorNumero(numero);
		if(bloqueado!=null){
			blacklist.setUsuario(bloqueado);
		}
		if(blacklist.getEstado()==null){
			blacklist.setEstado(Constantes.ESTADO_INACTIVO);
		}
		if(blacklist.getId()==null){
			blacklist.setFechaCreacion(new Date());
			blacklistDAO.guardar(blacklist);
			auditoriaService.registrarNuevoObjeto(blacklist,usuario);
		}
		else{
			Blacklist existente=blacklistDAO.get(blacklist.getId());
			auditoriaService.registrarModificacionObjeto(existente,blacklist,usuario);
			existente.setFechaFin(blacklist.getFechaFin());
			existente.setUsuario(existente.getUsuario());
			existente.setEstado(blacklist.getEstado());
			blacklistDAO.guardar(existente);
		}
	}

	public Blacklist buscarBlacklist(int id){
		return blacklistDAO.get(id);
	}

	public boolean existeBlacklist(String numero){
		return blacklistDAO.buscarBlacklistPorNumero(numero)!=null;
	}

	public boolean existeUsuario(String numero){
		return usuarioDAO.buscarPorNumero(numero)!=null;
	}

}
