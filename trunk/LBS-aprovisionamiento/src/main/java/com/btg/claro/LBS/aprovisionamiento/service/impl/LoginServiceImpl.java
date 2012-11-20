package com.btg.claro.LBS.aprovisionamiento.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.LBS.aprovisionamiento.dao.UsuarioDAO;
import com.btg.claro.LBS.aprovisionamiento.service.LoginService;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.aprovisionamiento.util.Util;
import com.btg.claro.LBS.domain.Usuario;

@Service("LoginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	public boolean iniciarSesion(String numero,String clave,HttpSession sesion){
		if(numero!=null && clave!=null){
			Usuario usuario=usuarioDAO.buscarPorNumeroClave(numero,Util.toMD5(clave));
			if(usuario!=null){
				sesion.setAttribute(Constantes.SESION_USUARIO,usuario);
				return true;
			}
		}
		return false;
	}

}
