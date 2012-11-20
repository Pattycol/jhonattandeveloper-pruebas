package pe.com.claro.caef.web.action.filter;

public class RegistrarActivacionReciboCorreoElectronicoFilter {
	private String codGrupo;
	private String codCliente;
	private String valCorreoElectronico;
	private String flgActivo;
	
	//INICIO: Set&Get
	public String getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getValCorreoElectronico() {
		return valCorreoElectronico;
	}
	public void setValCorreoElectronico(String valCorreoElectronico) {
		this.valCorreoElectronico = valCorreoElectronico;
	}
	public String getFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(String flgActivo) {
		this.flgActivo = flgActivo;
	}
	//FIN: Set&Get
}
