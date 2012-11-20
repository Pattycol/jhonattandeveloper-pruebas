package pe.com.claro.caef.web.beans;

public class ServicioCliente {
	//Bean que se utiliza para cargar los servicios para el req. Consulta de Cliente
	
	private String desTipoServicio;
	private String desProducto;
	private String desServicio;
	private String desEstado;
	private String fecActivacion;
	private String numServicio;
	
	public String getDesTipoServicio() {
		return desTipoServicio;
	}
	public void setDesTipoServicio(String desTipoServicio) {
		this.desTipoServicio = desTipoServicio;
	}
	public String getDesProducto() {
		return desProducto;
	}
	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}
	public String getDesServicio() {
		return desServicio;
	}
	public void setDesServicio(String desServicio) {
		this.desServicio = desServicio;
	}
	public String getDesEstado() {
		return desEstado;
	}
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	public String getFecActivacion() {
		return fecActivacion;
	}
	public void setFecActivacion(String fecActivacion) {
		this.fecActivacion = fecActivacion;
	}
	public String getNumServicio() {
		return numServicio;
	}
	public void setNumServicio(String numServicio) {
		this.numServicio = numServicio;
	}
	
}
