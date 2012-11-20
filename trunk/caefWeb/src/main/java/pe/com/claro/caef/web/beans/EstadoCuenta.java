package pe.com.claro.caef.web.beans;

import java.util.ArrayList;
import java.util.List;

public class EstadoCuenta extends AuditTypes {
	//Bean que se utiliza para consultar el estado de cuenta del cliente req. Estado de Cuenta
	
	private String valDeudaActual;	//deudaActualCliente
	private String valDeudaVencida;	//deudaVencidaCliente
	private String valMontoDisputa;	//montoReclamoCliente
	private String fecEmision;		//fechaEmisionFactura
	private String fecVencimiento;	//fechaVencimientoFactura
	private String valDeudaActualSol;	//deudaActualClienteSol
	private String valDeudaActualDol;	//deudaActualClienteDol
	private String valDeudaVencidaSol;	//deudaVencidaClienteSol
	private String valDeudaVencidaDol;	//deudaVencidaClienteDol
	private List<DetalleEstadoCuenta> detalleEstadoCuenta = new ArrayList<DetalleEstadoCuenta>();
	
	
	public String getValDeudaVencidaSol() {
		return valDeudaVencidaSol;
	}
	public void setValDeudaVencidaSol(String valDeudaVencidaSol) {
		this.valDeudaVencidaSol = valDeudaVencidaSol;
	}
	public String getValDeudaVencidaDol() {
		return valDeudaVencidaDol;
	}
	public void setValDeudaVencidaDol(String valDeudaVencidaDol) {
		this.valDeudaVencidaDol = valDeudaVencidaDol;
	}
	public String getValDeudaActualSol() {
		return valDeudaActualSol;
	}
	public void setValDeudaActualSol(String valDeudaActualSol) {
		this.valDeudaActualSol = valDeudaActualSol;
	}
	public String getValDeudaActualDol() {
		return valDeudaActualDol;
	}
	public void setValDeudaActualDol(String valDeudaActualDol) {
		this.valDeudaActualDol = valDeudaActualDol;
	}
	public String getValDeudaActual() {
		return valDeudaActual;
	}
	public void setValDeudaActual(String valDeudaActual) {
		this.valDeudaActual = valDeudaActual;
	}
	public String getValDeudaVencida() {
		return valDeudaVencida;
	}
	public void setValDeudaVencida(String valDeudaVencida) {
		this.valDeudaVencida = valDeudaVencida;
	}
	public String getValMontoDisputa() {
		return valMontoDisputa;
	}
	public void setValMontoDisputa(String valMontoDisputa) {
		this.valMontoDisputa = valMontoDisputa;
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
	public List<DetalleEstadoCuenta> getDetalleEstadoCuenta() {
		return detalleEstadoCuenta;
	}
	public void setDetalleEstadoCuenta(List<DetalleEstadoCuenta> detalleEstadoCuenta) {
		this.detalleEstadoCuenta = detalleEstadoCuenta;
	}
}
