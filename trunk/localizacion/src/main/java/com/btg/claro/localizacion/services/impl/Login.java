package com.btg.claro.localizacion.services.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.localizacion.daos.AplicacionDAO;
import com.btg.claro.localizacion.daos.SesionDAO;
import com.btg.claro.localizacion.entidades.Aplicacion;
import com.btg.claro.localizacion.entidades.Sesion;
import com.btg.claro.localizacion.services.LoginService;
import com.btg.claro.localizacion.util.Constantes;
import com.btg.claro.localizacion.util.Util;

@Service("LoginService")
public class Login implements LoginService{

	@Autowired
	private AplicacionDAO aplicacionDAO;

	@Autowired
	private SesionDAO sesionDAO;

	@Transactional
	public String login(String nombre,String password,String ip){
		Aplicacion aplicacion=aplicacionDAO.buscarPorNombre(nombre);
		if(aplicacion != null){
			if(aplicacion.getPassword().equals(password)){
				//if(aplicacion.getIp().equals(ip)){
					String token=generarToken(nombre);
					Sesion sesion=new Sesion();
					sesion.setToken(token);
					sesion.setAplicacion(aplicacion);
					Calendar hoy=Calendar.getInstance();
					sesion.setFechaIngreso(hoy.getTime());
					hoy.add(Calendar.HOUR_OF_DAY,1);
					sesion.setFechaVencimiento(hoy.getTime());
					sesionDAO.guardar(sesion);
					return token;
				/*}
				return Constantes.IP_INCORRECTA;*/
			}
		}
		return Constantes.LOGIN_INCORRECTO;
	}

	private String generarToken(String nombre){
		StringBuffer token=new StringBuffer();
		token.append(Util.toMD5(nombre + System.currentTimeMillis())).append(Util.toMD5("" + Math.random()));
		return token.toString();
	}

}
