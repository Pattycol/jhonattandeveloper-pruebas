package pe.com.claro.caef.web.beans;

public class SuscribirDirectorioAbonado extends AuditTypes {
	//Bean que se utiliza para la subscripcion al directorio de abonados del cliente req. Suscripción/Desuscripción al Directorio de Abonados
	
	private String desPlan;
	private String numTelefonico;
	private String flgActivo;
	
	public String getDesPlan() {
		return desPlan;
	}
	public void setDesPlan(String desPlan) {
		this.desPlan = desPlan;
	}
	public String getNumTelefonico() {
		return numTelefonico;
	}
	public void setNumTelefonico(String numTelefonico) {
		this.numTelefonico = numTelefonico;
	}
	public String getFlgActivo() {
		return flgActivo;
	}
	public void setFlgActivo(String flgActivo) {
		this.flgActivo = flgActivo;
	}
}
