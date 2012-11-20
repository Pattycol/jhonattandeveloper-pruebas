package pe.com.claro.caef.web.beans;

import java.util.List;

public class ValidarPreguntasSecretas {
	
	private String txId;
	private String ipApp;
	private String usrApp;
	private String telefono;
	private String flagTipoLinea;
	private String tipoDocumento;
	private String nroDocumento;
	private String email;
	private String clave;
	private String operacionT;
	private List<PreguntasType> lstPreguntasType;
	
	public List<PreguntasType> getLstPreguntasType() {
		return lstPreguntasType;
	}
	public void setLstPreguntasType(List<PreguntasType> lstPreguntasType) {
		this.lstPreguntasType = lstPreguntasType;
	}
	public String getTxId() {
		return txId;
	}
	public void setTxId(String txId) {
		this.txId = txId;
	}
	public String getIpApp() {
		return ipApp;
	}
	public void setIpApp(String ipApp) {
		this.ipApp = ipApp;
	}
	public String getUsrApp() {
		return usrApp;
	}
	public void setUsrApp(String usrApp) {
		this.usrApp = usrApp;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFlagTipoLinea() {
		return flagTipoLinea;
	}
	public void setFlagTipoLinea(String flagTipoLinea) {
		this.flagTipoLinea = flagTipoLinea;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getOperacionT() {
		return operacionT;
	}
	public void setOperacionT(String operacionT) {
		this.operacionT = operacionT;
	}

}
