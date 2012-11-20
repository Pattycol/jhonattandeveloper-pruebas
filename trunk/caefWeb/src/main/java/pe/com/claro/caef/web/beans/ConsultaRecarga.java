package pe.com.claro.caef.web.beans;

public class ConsultaRecarga extends AuditTypes {
	//Bean que se utiliza para consultar las recargas del cliente req. Consultar recargas
	
	private String fecRecarga;
	private String valorMonto;
	private String fecInicio;
	private String fecFin;
	private String agenteRecarga;

	public String getFecRecarga() {
		return fecRecarga;
	}
	public void setFecRecarga(String fecRecarga) {
		this.fecRecarga = fecRecarga;
	}
	public String getValorMonto() {
		return valorMonto;
	}
	public void setValorMonto(String valorMonto) {
		this.valorMonto = valorMonto;
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
	public String getAgenteRecarga() {
		return agenteRecarga;
	}
	public void setAgenteRecarga(String agenteRecarga) {
		this.agenteRecarga = agenteRecarga;
	}
}
