package pe.com.claro.caef.web.beans;

public class Comun {
	//Bean que se utiliza para los métodos:
	//consultarNumerosTelefonicos
	//consultarDatosMaestros
	
	private String numTelefonico;
	
	private String codDato;
	private String valDato;
	private String abrDato;

	private String codError;
	private String msgError;
	
	//INICIO: Sets&Gets
	public String getNumTelefonico() {
		return numTelefonico;
	}
	public void setNumTelefonico(String numTelefonico) {
		this.numTelefonico = numTelefonico;
	}
	public String getCodDato() {
		return codDato;
	}
	public void setCodDato(String codDato) {
		this.codDato = codDato;
	}
	public String getValDato() {
		return valDato;
	}
	public void setValDato(String valDato) {
		this.valDato = valDato;
	}
	public String getAbrDato() {
		return abrDato;
	}
	public void setAbrDato(String abrDato) {
		this.abrDato = abrDato;
	}
	public String getCodError() {
		return codError;
	}
	public void setCodError(String codError) {
		this.codError = codError;
	}
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}
	//FIN: Sets&Gets
	
}
