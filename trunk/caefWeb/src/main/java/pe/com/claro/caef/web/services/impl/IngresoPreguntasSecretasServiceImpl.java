package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.IngresoPreguntaSecreta;
import pe.com.claro.caef.web.beans.ListaPreguntasInfoType;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.beans.PreguntasType;
import pe.com.claro.caef.web.services.IngresoPreguntasSecretasService;
import pe.com.claro.caef.web.ws.ConsultaSeguridadWS;

@Service("ingresoPreguntasSecretasService")
public class IngresoPreguntasSecretasServiceImpl implements
		IngresoPreguntasSecretasService {
	
	@Autowired
	private ConsultaSeguridadWS consultaSeguridadWS;
	
	public ObtenerDatosPreguntas obtenerDatosPreguntas(Usuario usuario, ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter)
	{
		ObtenerDatosPreguntas odp = new ObtenerDatosPreguntas();
		obtenerDatosPreguntasFilter = new ObtenerDatosPreguntasFilter();
		obtenerDatosPreguntasFilter.setIpApp("");
		obtenerDatosPreguntasFilter.setTxId("");
		obtenerDatosPreguntasFilter.setUsrApp("");
		
		odp = consultaSeguridadWS.obtenerDatosPreguntas(usuario, obtenerDatosPreguntasFilter);
		
		List<ListaPreguntasInfoType> lista = new ArrayList<ListaPreguntasInfoType>();
		
		for(int i = 0; i < Integer.parseInt(odp.getNroPregReg()); i++)
		{
			ListaPreguntasInfoType pt = new ListaPreguntasInfoType();
			pt.setCodigo(String.valueOf(i + 1));
			lista.add(pt);
		}
		
		odp.setLstNumPreguntas(lista);
		
		return odp;
	}

}
