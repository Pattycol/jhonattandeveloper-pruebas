package com.btg.claro.LBS.aprovisionamiento.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Usuario;

public class LoginFilter implements Filter{

	public void destroy(){}

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException{
		request.setCharacterEncoding("UTF-8");
		if(request instanceof HttpServletRequest){
			if(response instanceof HttpServletResponse){
				HttpServletRequest peticion=(HttpServletRequest) request;
				HttpServletResponse respuesta=(HttpServletResponse) response;
				HttpSession sesion=peticion.getSession();
				Usuario usuario=(Usuario) sesion.getAttribute(Constantes.SESION_USUARIO);
				if(usuario==null){
					String uri=peticion.getRequestURI();
					if(!uri.contains("login") && !uri.contains("resources")){
						respuesta.sendRedirect("login");
					}
					else{
						chain.doFilter(peticion,response);
					}
				}
				else{
					chain.doFilter(peticion,response);
				}
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException{}

}
