package pe.com.claro.caef.web.action.filter;

public class ConsultarEstadoCuentaFilter {
	
	private String codCliente;
	private String fecEmision;
	private String codProducto;
	private String codServicio;
	private String numeroPagina;

	//INICIO: Set&Get
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
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
	public String getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	//FIN: Set&Get
	
}
