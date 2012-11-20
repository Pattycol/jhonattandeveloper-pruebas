package pe.com.claro.caef.web.util;

import java.io.File;

public class CorreoDatos {

	private String razonSocial;
	private String contenido;
	private String enunciado;
	private String asunto;
	private String destinatario;
	private String replyTo;
	
	private File archivo;
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String cuenta) {
		this.destinatario = cuenta;
	}
	public File getArchivo() {
		return archivo;
	}
	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}
	public String getReplyTo() {
		return replyTo;
	}
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	
}
