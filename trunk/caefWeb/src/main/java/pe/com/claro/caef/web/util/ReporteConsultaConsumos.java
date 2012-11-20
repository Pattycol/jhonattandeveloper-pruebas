package pe.com.claro.caef.web.util;

import java.util.List;

import pe.com.claro.caef.web.beans.ConsultaConsumo;
import pe.com.claro.caef.web.beans.LlamadaFacturada;

public class ReporteConsultaConsumos {

	private String plantilla;
	private String razonSocial;
	private String cuenta;
	private String fecha;
	private String hora;
	private String numero;
	private String valConsumo;
	
	public void setValConsumo(String valConsumo) {
		this.valConsumo = valConsumo;
	}

	public String getValConsumo() {
		
		Float f = 0f;
		for(ConsultaConsumo ll : lstConsultaConsumo)
		{
			
			f += new Float(ll.getValConsumo());
		}
		
		return f.toString();
	}

	

	private List<ConsultaConsumo> lstConsultaConsumo;

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

	public List<ConsultaConsumo> getLstConsultaConsumo() {
		return lstConsultaConsumo;
	}

	public void setLstConsultaConsumo(List<ConsultaConsumo> lstConsultaConsumo) {
		this.lstConsultaConsumo = lstConsultaConsumo;
	}

	
}
