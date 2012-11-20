package pe.com.claro.caef.web.beans;

import java.util.ArrayList;
import java.util.List;

public class ObtenerDatosUsuario extends AuditTypes {
	
	private String nroPreguntas;
	private List<ListaRespuestasType> lstListaRespuestasType;

	
	public List<ListaRespuestasType> getLstListaRespuestasType() {
		return lstListaRespuestasType;
	}

	public void setLstListaRespuestasType(
			List<ListaRespuestasType> lstListaRespuestasType) {
		this.lstListaRespuestasType = lstListaRespuestasType;
	}

	public String getNroPreguntas() {
		return nroPreguntas;
	}

	public void setNroPreguntas(String nroPreguntas) {
		this.nroPreguntas = nroPreguntas;
	}

}
