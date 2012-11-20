package pe.com.claro.caef.web.beans;

public class PreguntasType {
	
	private String codPregunta;
	private String Respuesta;
	private String numPregunta;
	
	public String getNumPregunta() {
		return numPregunta;
	}
	public void setNumPregunta(String numPregunta) {
		this.numPregunta = numPregunta;
	}
	public String getCodPregunta() {
		return codPregunta;
	}
	public void setCodPregunta(String codPregunta) {
		this.codPregunta = codPregunta;
	}
	public String getRespuesta() {
		return Respuesta;
	}
	public void setRespuesta(String respuesta) {
		Respuesta = respuesta;
	}
}
