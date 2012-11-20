package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.GrabarRespuestas;
import pe.com.claro.caef.web.beans.ListaPreguntasInfoType;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasType;
import pe.com.claro.caef.web.beans.UsuarioPreguntas;
import pe.com.claro.caef.web.services.ActualizarPreguntasSecretasService;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;
import pe.com.claro.caef.web.ws.TransaccionSeguridadWS;

@Service("actualizarPreguntasSecretasService")
public class ActualizarPreguntasSecretasServiceImpl implements
		ActualizarPreguntasSecretasService {

	@Autowired
	private TransaccionSeguridadWS transaccionSeguridadWS;
	
	@Autowired
	private ConsultaSeguridadWS consultaSeguridadWS;
	
	public AuditTypes grabarRespuestas(Usuario usuario,
			GrabarRespuestas grabarRespuestas) {
		
		AuditTypes at = new AuditTypes();
		at = transaccionSeguridadWS.grabarRespuestas(usuario, grabarRespuestas);
		
		return at;
	}
	
	public AuditTypes usuarioPreguntas(Usuario usuario, UsuarioPreguntas usuarioPreguntas)
	{
		AuditTypes at = new AuditTypes();
		at = transaccionSeguridadWS.usuarioPreguntas(usuario, usuarioPreguntas);
		
		return at;
	}
	
	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario, ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter)
	{
		ObtenerDatosPreguntas odp = new ObtenerDatosPreguntas();
		
		obtenerDatosPreguntasFilter = new ObtenerDatosPreguntasFilter();
		obtenerDatosPreguntasFilter.setIpApp("");
		obtenerDatosPreguntasFilter.setTxId("");
		obtenerDatosPreguntasFilter.setUsrApp("");
			
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
		obtenerDatosUsuarioFilter = new ObtenerDatosUsuarioFilter();
		
		obtenerDatosUsuarioFilter.setIpApp("");
		obtenerDatosUsuarioFilter.setTxId("");
		obtenerDatosUsuarioFilter.setUsrApp("");
		obtenerDatosUsuarioFilter.setTelefono(usuario.getTelefonoMiClaroFija());//"966319620");
		
		odu = consultaSeguridadWS.obtenerDatosUsuario(usuario, obtenerDatosUsuarioFilter);
		
		return odu;
	}

}
