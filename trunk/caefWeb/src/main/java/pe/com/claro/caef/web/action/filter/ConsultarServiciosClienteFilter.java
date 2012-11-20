package pe.com.claro.caef.web.action.filter;

public class ConsultarServiciosClienteFilter {

	private String codCliente;
	private String codServicio;
	private String codProducto;
	
	//INICIO: Sets&Gets
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
	//FIN: Sets&Gets
}
