package pe.com.claro.caef.web.beans;

public class ContactoCliente {
	//Bean que se utiliza para cargar los contactos para el req. Consulta de Cliente
	
	private String codigoContacto;
	private String nombreContacto;
	private String tipDocumentoIden;
	private String desDocumentoIden;
	private String numDocumentoIden;
	
	
	public String getCodigoContacto() {
		return codigoContacto;
	}
	public void setCodigoContacto(String codigoContacto) {
		this.codigoContacto = codigoContacto;
	}
	
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getTipDocumentoIden() {
		return tipDocumentoIden;
	}
	public void setTipDocumentoIden(String tipDocumentoIden) {
		this.tipDocumentoIden = tipDocumentoIden;
	}
	public String getDesDocumentoIden() {
		return desDocumentoIden;
	}
	public void setDesDocumentoIden(String desDocumentoIden) {
		this.desDocumentoIden = desDocumentoIden;
	}
	public String getNumDocumentoIden() {
		return numDocumentoIden;
	}
	public void setNumDocumentoIden(String numDocumentoIden) {
		this.numDocumentoIden = numDocumentoIden;
	}

}
