package pe.com.claro.caef.web.beans;

public class ConsultarInstanciaServicio extends AuditTypes {
	//Bean que se utiliza para consultar la instancia de un servicio req. Registro de Incidencias
	
	private String codigoInstanciaServicio;
	private String numero;
	private String direccion;
	private String sLetraInstancia;
	private String sCodigoInstancia;
	
	
	
	public String getCodigoInstanciaServicio() {
		return codigoInstanciaServicio;
	}
	public void setCodigoInstanciaServicio(String codigoInstanciaServicio) {
		this.codigoInstanciaServicio = codigoInstanciaServicio;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getsLetraInstancia() {
		return sLetraInstancia;
	}
	public void setsLetraInstancia(String sLetraInstancia) {
		this.sLetraInstancia = sLetraInstancia;
	}
	public String getsCodigoInstancia() {
		return sCodigoInstancia;
	}
	public void setsCodigoInstancia(String sCodigoInstancia) {
		this.sCodigoInstancia = sCodigoInstancia;
	}
}
