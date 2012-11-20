package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.Autenticar;
import pe.com.claro.caef.web.beans.AutenticarResponse;
import pe.com.claro.caef.web.beans.ListaPreguntasInfoType;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.services.LoginService;
import pe.com.claro.caef.web.ws.ConsultaAutenticacionWS;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;

@Service("LoginService")
public class LoginServiceimpl implements LoginService {

	@Autowired
	private ConsultaAutenticacionWS consultaAutenticacionWS;
	
	@Autowired
	private ConsultaSeguridadWS consultaSeguridadWS;
	
	public AutenticarResponse autenticar(Usuario usuario, Autenticar autenticar) {

		AutenticarResponse ar =  new AutenticarResponse();
		
		ar = consultaAutenticacionWS.autenticar(usuario, autenticar);
		
		return ar;
	}
	
	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario, ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter)
	{
		ObtenerDatosPreguntas odp = new ObtenerDatosPreguntas();		
			
		odp = consultaSeguridadWS.obtenerDatosPreguntas(usuario, obtenerDatosPreguntasFilter);
			
		List<ListaPreguntasInfoType> lista = new ArrayList<ListaPreguntasInfoType>();
		
		for(int i = 0; i <= Integer.parseInt(odp.getNroPregReg()); i++)
		{
			ListaPreguntasInfoType pt = new ListaPreguntasInfoType();
			pt.setCodigo(String.valueOf(i + 1));
			lista.add(pt);
		}
			
		odp.setLstNumPreguntas(lista);
		
		return odp;
	}
	
	public ObtenerDatosUsuario obtenerDatosUsuario(Usuario usuario, ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter)
	{
		ObtenerDatosUsuario odu = new ObtenerDatosUsuario();		
		odu = consultaSeguridadWS.obtenerDatosUsuario(usuario, obtenerDatosUsuarioFilter);
		
		return odu;
	}
	
}
