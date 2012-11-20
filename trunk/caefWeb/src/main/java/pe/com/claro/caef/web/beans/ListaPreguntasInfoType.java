package pe.com.claro.caef.web.beans;

public class ListaPreguntasInfoType {
	
	private String codigo;
	private String descripcion;
	private String estado;
	private String usuarioCrea;
	private String usuarioMod;
	private String fechaMod;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuarioCrea() {
		return usuarioCrea;
	}
	public void setUsuarioCrea(String usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	public String getUsuarioMod() {
		return usuarioMod;
	}
	public void setUsuarioMod(String usuarioMod) {
		this.usuarioMod = usuarioMod;
	}
	public String getFechaMod() {
		return fechaMod;
	}
	public void setFechaMod(String fechaMod) {
		this.fechaMod = fechaMod;
	}
	
}
