package pe.com.claro.caef.web.services.impl;

import org.springframework.stereotype.Service;

import pe.com.claro.caef.dao.UsuarioDao;
import pe.com.claro.caef.dao.jdbc.implement.UsuarioDaoImpl;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	private UsuarioDao usuarioDao = null;
	
	public int actualizaClave(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			usuarioDao=new UsuarioDaoImpl();
			
			
			result = usuarioDao.actualizaClave(usuario); 
		} catch ( Exception e) {
			throw new Exception(e);
		}
		return result;
	}

	public int actualizaFlagDatos(String telefono, int flag) throws Exception {
		int result = 0;
		try {
			result = usuarioDao.actualizaFlagDatos(telefono,flag); 
		} catch ( Exception e) {
			throw new Exception(e);
		}
		return result;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	

}
