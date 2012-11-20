package pe.com.claro.caef.web.beans;

public class DuplicadoRecibo extends AuditTypes {
	//Bean que se utiliza para cargar la grilla de recibos req. Duplicado Recibos
	
	private String codFactura;
	private String codCicloFac;
	private String codCliente;
	private String fecEmision;
	private String valTotal;
	private String fecFacturacion;
	
	public String getCodFactura() {
		return codFactura;
	}
	public void setCodFactura(String codFactura) {
		this.codFactura = codFactura;
	}
	public String getCodCicloFac() {
		return codCicloFac;
	}
	public void setCodCicloFac(String codCicloFac) {
		this.codCicloFac = codCicloFac;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getFecEmision() {
		return fecEmision;
	}
	public void setFecEmision(String fecEmision) {
		this.fecEmision = fecEmision;
	}
	public String getValTotal() {
		return valTotal;
	}
	public void setValTotal(String valTotal) {
		this.valTotal = valTotal;
	}
	public String getFecFacturacion() {
		return fecFacturacion;
	}
	public void setFecFacturacion(String fecFacturacion) {
		this.fecFacturacion = fecFacturacion;
	}	

}
