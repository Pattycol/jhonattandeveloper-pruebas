package com.btg.claro.LBS.aprovisionamiento.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.aprovisionamiento.dao.CeldaDAO;
import com.btg.claro.LBS.aprovisionamiento.service.MantenimientoCeldaService;
import com.btg.claro.LBS.aprovisionamiento.service.ProcesoAuditoriaService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Usuario;

@Service("MantenimientoCeldaService")
public class MantenimientoCelda implements MantenimientoCeldaService{

	@Autowired
	private CeldaDAO celdaDAO;
	
	@Autowired
	private ProcesoAuditoriaService auditoriaService;
	
	public List<Celda> getCeldas(){
		return celdaDAO.getCeldas();
	}

	@Transactional
	public void guardarCelda(Celda celda,Usuario usuario){
		if(celda.getEstado()==null){
			celda.setEstado(Constantes.ESTADO_INACTIVO);
		}
		if(celda.getId()==null){
			celda.setFechaCreacion(new Date());
			celdaDAO.guardar(celda);
			auditoriaService.registrarNuevoObjeto(celda,usuario);
		}
		else{
			Celda existente=celdaDAO.get(celda.getId());
			auditoriaService.registrarModificacionObjeto(existente,celda,usuario);
			existente.setDireccion(celda.getDireccion());
			existente.setEstado(celda.getEstado());
			existente.setIdentificador(celda.getIdentificador());
			existente.setFechaModificacion(new Date());
			existente.setX(celda.getX());
			existente.setY(celda.getY());
			celdaDAO.guardar(existente);
		}
	}

	public Celda buscarCelda(int id){
		return celdaDAO.get(id);
	}

	public boolean existeCelda(String identificador){
		return celdaDAO.getPorIdentificador(identificador)!=null;
	}

	public List<Celda> buscarCeldasPorIdentificador(String query){
		if(query==null){
			query="";
		}
		query+="%";
		return celdaDAO.buscarPorIdentificador(query);
	}

}
