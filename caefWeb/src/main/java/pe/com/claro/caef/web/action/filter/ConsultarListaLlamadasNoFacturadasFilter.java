package pe.com.claro.caef.web.action.filter;

public class ConsultarListaLlamadasNoFacturadasFilter {
	
	private String codCliente;
	private String codServicio;
	private String codProducto;
	private String numOrigen;
	private String fecInicio;
	private String fecFin;
	private String numeroPagina;
	private String descripcion;
	
	//INICIO: Set&Get
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNumOrigen() {
		return numOrigen;
	}
	public void setNumOrigen(String numOrigen) {
		this.numOrigen = numOrigen;
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
	public String getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(String numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	//FIN: Set&Get
}
