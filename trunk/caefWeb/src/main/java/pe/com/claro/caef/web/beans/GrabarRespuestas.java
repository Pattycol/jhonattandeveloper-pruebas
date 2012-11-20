package pe.com.claro.caef.web.beans;

import java.util.List;

public class GrabarRespuestas {
	
	private String txId;
	private String ipApp;
	private String usrApp;
	private String telefono;
	private String operacion;
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
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

}
