package pe.com.claro.caef.web.beans;

public class DetalleEstadoCuenta {
	//Bean que se utiliza para consultar el detalle del estado de cuenta del cliente req. Estado de Cuenta
	
	private String numFactura;
	private String codDocumento;
	private String desPago;
	private String fecRegistro;
	private String fecEmision;
	private String fecVencimiento;
	private String flgCargoCuenta;
	private String valDocumento;
	private String valAbono;
	private String valSaldo;
	private String fecDocumento;
	private String fecCancelada;
	
	public String getNumFactura() {
		return numFactura;
	}
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}
	public String getCodDocumento() {
		return codDocumento;
	}
	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}
	public String getDesPago() {
		return desPago;
	}
	public void setDesPago(String desPago) {
		this.desPago = desPago;
	}
	public String getFecRegistro() {
		return fecRegistro;
	}
	public void setFecRegistro(String fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	public String getFecEmision() {
		return fecEmision;
	}
	public void setFecEmision(String fecEmision) {
		this.fecEmision = fecEmision;
	}
	public String getFecVencimiento() {
		return fecVencimiento;
	}
	public void setFecVencimiento(String fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}
	public String getFlgCargoCuenta() {
		return flgCargoCuenta.equals("1")?"true":"false";
	}
	public void setFlgCargoCuenta(String flgCargoCuenta) {
		this.flgCargoCuenta = flgCargoCuenta;
	}
	public String getValDocumento() {
		return valDocumento;
	}
	public void setValDocumento(String valDocumento) {
		this.valDocumento = valDocumento;
	}
	public String getValAbono() {
		return valAbono;
	}
	public void setValAbono(String valAbono) {
		this.valAbono = valAbono;
	}
	public String getValSaldo() {
		return valSaldo;
	}
	public void setValSaldo(String valSaldo) {
		this.valSaldo = valSaldo;
	}
	public String getFecDocumento() {
		return fecDocumento;
	}
	public void setFecDocumento(String fecDocumento) {
		this.fecDocumento = fecDocumento;
	}
	public String getFecCancelada() {
		return fecCancelada;
	}
	public void setFecCancelada(String fecCancelada) {
		this.fecCancelada = fecCancelada;
	}
}
