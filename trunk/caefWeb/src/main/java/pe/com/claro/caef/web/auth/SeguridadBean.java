package pe.com.claro.caef.web.auth;

import java.util.ArrayList;
import java.util.List;

import pe.com.claro.caef.web.beans.PreguntasType;

public class SeguridadBean {
	
	private String txId;
	private String ipApp;
	private String usrApp;
	private String linea; 
	private String tipoDocumento;
	private String nroDocumento;
	private String email;
	private String flagTipoLinea;
	private String nroPregReg;
	private String nroPregResp;
	private String operacionE;
	private String operacionF;
	private String perfil;
	private String flagEmail;
	private List<PreguntasType> lstPreguntasType;
	
	private String telefono;
	private String numPregRes;
	
	public String getNumPregRes() {
		return numPregRes;
	}

	public void setNumPregRes(String numPregRes) {
		this.numPregRes = numPregRes;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
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

	public String getFlagTipoLinea() {
		return flagTipoLinea;
	}

	public void setFlagTipoLinea(String flagTipoLinea) {
		this.flagTipoLinea = flagTipoLinea;
	}

	public String getNroPregReg() {
		return nroPregReg;
	}

	public void setNroPregReg(String nroPregReg) {
		this.nroPregReg = nroPregReg;
	}

	public String getNroPregResp() {
		return nroPregResp;
	}

	public void setNroPregResp(String nroPregResp) {
		this.nroPregResp = nroPregResp;
	}

	public String getOperacionE() {
		return operacionE;
	}

	public void setOperacionE(String operacionE) {
		this.operacionE = operacionE;
	}

	public String getOperacionF() {
		return operacionF;
	}

	public void setOperacionF(String operacionF) {
		this.operacionF = operacionF;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getFlagEmail() {
		return flagEmail;
	}

	public void setFlagEmail(String flagEmail) {
		this.flagEmail = flagEmail;
	}

	public List<PreguntasType> getLstPreguntasType() {
		return lstPreguntasType;
	}

	public void setLstPreguntasType(List<PreguntasType> lstPreguntasType) {
		this.lstPreguntasType = lstPreguntasType;
	}

}
