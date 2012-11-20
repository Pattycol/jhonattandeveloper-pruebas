package com.btg.claro.LBS.aprovisionamiento.service;

import javax.servlet.http.HttpSession;

public interface LoginService{

	boolean iniciarSesion(String numero,String clave,HttpSession sesion);

}
