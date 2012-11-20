package pe.com.claro.caef.web.beans;

public class ConsultaNumeroTelefonico extends AuditTypes {
	// Bean que se utiliza para consultar los números telefónicos de un cliente
	// || consultarNumerosTelefonicos

	private String numero;
	private String codigoInstanciaServicio;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoInstanciaServicio() {
		return codigoInstanciaServicio;
	}

	public void setCodigoInstanciaServicio(String codigoInstanciaServicio) {
		this.codigoInstanciaServicio = codigoInstanciaServicio;
	}

	public ConsultaNumeroTelefonico(String numero,
			String codigoInstanciaServicio) {
		super();
		this.numero = numero;
		this.codigoInstanciaServicio = codigoInstanciaServicio;
	}

	public ConsultaNumeroTelefonico() {
		super();
	}

}
