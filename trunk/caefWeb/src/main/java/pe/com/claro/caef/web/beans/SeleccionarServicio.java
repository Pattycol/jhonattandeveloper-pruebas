package pe.com.claro.caef.web.beans;

import java.util.List;

public class SeleccionarServicio extends AuditTypes {
	
	private List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal;

	public List<ConsultaServicioClienteTotal> getLstConsultaServicioClienteTotal() {
		return lstConsultaServicioClienteTotal;
	}

	public void setLstConsultaServicioClienteTotal(
			List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal) {
		this.lstConsultaServicioClienteTotal = lstConsultaServicioClienteTotal;
	}

}
