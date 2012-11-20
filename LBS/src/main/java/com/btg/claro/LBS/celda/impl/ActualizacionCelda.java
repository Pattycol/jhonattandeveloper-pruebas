package com.btg.claro.LBS.celda.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.celda.ActualizacionCeldaService;
import com.btg.claro.LBS.celda.CeldaReferencia;
import com.btg.claro.LBS.dao.CeldaDAO;
import com.btg.claro.LBS.dao.CeldaReferenciaDAO;
import com.btg.claro.LBS.dao.UsuarioDAO;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.util.Mail;
import com.btg.claro.LBS.util.Util;

@Service("ActualizacionCeldaService")
public class ActualizacionCelda implements ActualizacionCeldaService{
	
	private static final Logger log=LoggerFactory.getLogger(ActualizacionCelda.class);
	
	@Autowired
	private CeldaDAO celdaDAO;
	
	@Autowired
	private CeldaReferenciaDAO celdaReferenciaDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	//@Scheduled(cron="0 * * * * ?")
	@Transactional
	public void crearCeldasNuevas(){
		log.info("Inciando proceso de verificacion de celdas nuevas");
		List<CeldaReferencia> existentes=celdaReferenciaDAO.buscarNuevas(celdaDAO.getTodos());
		log.debug("Se encontraron "+existentes.size()+" celdas nuevas");
		for(CeldaReferencia ref : existentes){
			String id=ref.getIdentificador();
			if(!Util.vacio(id)){
				Celda celda=new Celda();
				celda.setIdentificador(id);
				celda.setX(ref.getX());
				celda.setY(ref.getY());
				celda.setFechaCreacion(new Date());
				celda.setEstado(Constantes.ESTADO_PENDIENTE);
				celdaDAO.guardar(celda);
				//notificamos por correo a las personas pertinentes
				List<Usuario> administradores=usuarioDAO.getAdministradores();
				Mail mail=new Mail();
				mail.setAsunto("Creación de nueva Celda en el LBS");
				//String token=Util.toMD5(System.currentTimeMillis()+""+)
				mail.setContenido(Util.getTextoMail("nuevaCelda"/*,id,*/));
				for(Usuario admin : administradores){
					mail.agregarDestinatario(admin.getCorreo());
				}
				mail.enviarCorreo();
			}
		}
	}

}
