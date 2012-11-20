package pe.com.claro.caef.web.beans;

public class LlamadaFacturada extends AuditTypes {
	//Bean que se utiliza para cargar las llamadas facturadas para el req. Llamadas Facturadas

	private String numOrigen;
	private String numDestino;
	private String horaInicio;
	private String horaFin;
	private String valDuracion;
	private String valTarifa;
	private String tipServicio;
	private String valCosto;
	private String numRecibo;

	public String getNumOrigen() {
		return numOrigen;
	}
	public void setNumOrigen(String numOrigen) {
		this.numOrigen = numOrigen;
	}
	public String getNumDestino() {
		return numDestino;
	}
	public void setNumDestino(String numDestino) {
		this.numDestino = numDestino;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getValDuracion() {
		return valDuracion;
	}
	public void setValDuracion(String valDuracion) {
		this.valDuracion = valDuracion;
	}
	public String getValTarifa() {
		return valTarifa;
	}
	public void setValTarifa(String valTarifa) {
		this.valTarifa = valTarifa;
	}
	public String getTipServicio() {
		return tipServicio;
	}
	public void setTipServicio(String tipServicio) {
		this.tipServicio = tipServicio;
	}
	public String getValCosto() {
		return valCosto;
	}
	public void setValCosto(String valCosto) {
		this.valCosto = valCosto;
	}
	public String getNumRecibo() {
		return numRecibo;
	}
	public void setNumRecibo(String numRecibo) {
		this.numRecibo = numRecibo;
	}
}
