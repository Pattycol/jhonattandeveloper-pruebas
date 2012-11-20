package pe.com.claro.caef.web.beans;

import java.util.List;

public class PreguntasAleatorias extends AuditTypes {
	
	private List<PreguntasType> lstPreguntas;

	public List<PreguntasType> getLstPreguntas() {
		return lstPreguntas;
	}

	public void setLstPreguntas(List<PreguntasType> lstPreguntas) {
		this.lstPreguntas = lstPreguntas;
	}

}
