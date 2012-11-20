package pe.com.claro.caef.web.util;

import java.util.List;

import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.EstadoCuenta;

public class ReporteConsultaEstadoCuenta {
	
	private String plantilla;
	private String razonSocial;
	private String cuenta;
	private String fecha;
	private String hora;
	private String numero;
	
	List<DetalleEstadoCuenta> lstEstadoCuenta;
	

	public List<DetalleEstadoCuenta> getLstEstadoCuenta() {
		return lstEstadoCuenta;
	}
	public void setLstEstadoCuenta(List<DetalleEstadoCuenta> lstEstadoCuenta) {
		this.lstEstadoCuenta = lstEstadoCuenta;
	}
	public String getPlantilla() {
		return plantilla;
	}
	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
