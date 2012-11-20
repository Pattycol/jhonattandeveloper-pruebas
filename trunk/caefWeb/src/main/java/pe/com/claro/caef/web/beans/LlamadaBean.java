package pe.com.claro.caef.web.beans;





public class LlamadaBean{

	
	private ClienteBean cliente;
	private CategoriaBean categoria;
	private TotalBean total;
	
	private String concepto;
	private String precioUnitario;
	private String numeroLlamadas;
	private String servicioMinutos;
	private String importe;
	
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getNumeroLlamadas() {
		return numeroLlamadas;
	}
	public void setNumeroLlamadas(String numeroLlamadas) {
		this.numeroLlamadas = numeroLlamadas;
	}
	public String getServicioMinutos() {
		return servicioMinutos;
	}
	public void setServicioMinutos(String servicioMinutos) {
		this.servicioMinutos = servicioMinutos;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	public TotalBean getTotal() {
		return total;
	}
	public void setTotal(TotalBean total) {
		this.total = total;
	}
	public CategoriaBean getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaBean categoria) {
		this.categoria = categoria;
	}
	public ClienteBean getCliente() {
		return cliente;
	}
	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
	
}
