package pe.com.claro.caef.web.beans;

public class ConsultarNumTelefonicoAbonado {

	private String nombre;
	private String numero;
	private String publicar;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPublicar() {
		
		return publicar.equals("1")?"true":"false";
	}
	public void setPublicar(String publicar) {
		this.publicar = publicar;
	}
	
}
