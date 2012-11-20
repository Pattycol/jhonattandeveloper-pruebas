package pe.com.claro.caef.web.beans;

public class LlamadaEntrante extends AuditTypes {
	//Bean que se utiliza para cargar las llamadas entrantes para el req. Llamadas Entrantes
	
	private String numOrigen;
	private String numDestino;
	private String fecInicio;
	private String fecFin;
	private String valDuracion;

	
	public String getNumDestino() {
		return numDestino;
	}
	public void setNumDestino(String numDestino) {
		this.numDestino = numDestino;
	}
	public String getNumOrigen() {
		return numOrigen;
	}
	public void setNumOrigen(String numOrigen) {
		this.numOrigen = numOrigen;
	}
	public String getFecInicio() {
		return fecInicio;
	}
	public void setFecInicio(String fecInicio) {
		this.fecInicio = fecInicio;
	}
	public String getFecFin() {
		return fecFin;
	}
	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}
	public String getValDuracion() {
		return valDuracion;
	}
	public void setValDuracion(String valDuracion) {
		this.valDuracion = valDuracion;
	}
}
