package pe.com.claro.caef.web.beans;

public class RegistrarIncidencia extends AuditTypes {
	//Bean que se utiliza para registrar una incidencia generada por el cliente req. Registro de Incidencias
	
	private String codigoIncidencia;
	private String numeroTicket;
	
	public String getCodigoIncidencia() {
		return codigoIncidencia;
	}
	public void setCodigoIncidencia(String codigoIncidencia) {
		this.codigoIncidencia = codigoIncidencia;
	}
	public String getNumeroTicket() {
		return numeroTicket;
	}
	public void setNumeroTicket(String numeroTicket) {
		this.numeroTicket = numeroTicket;
	}
	
}
