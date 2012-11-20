package pe.com.claro.caef.web.beans;

public class ActualizaCliente extends AuditTypes {
	//Bean que se utiliza para actualizar los datos del cliente para el req. Actualización de Datos del Cliente
	
	private String valCorreoElectronico;
	private String fecNacimiento;
	private String codEstadoCivil;
	private String nomCargoEsp;
	private String codGenero;
	private String codNacionalidad;
	
	private String numVia;
	private String codTipoDomicilio;
	private String desDomicilio;
	private String valPiso;
	private String valManzana;
	private String valLote;
	private String valSector;
	private String valReferencia;
	
	public String getValCorreoElectronico() {
		return valCorreoElectronico;
	}
	public void setValCorreoElectronico(String valCorreoElectronico) {
		this.valCorreoElectronico = valCorreoElectronico;
	}
	public String getFecNacimiento() {
		return fecNacimiento;
	}
	public void setFecNacimiento(String fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}
	public String getCodEstadoCivil() {
		return codEstadoCivil;
	}
	public void setCodEstadoCivil(String codEstadoCivil) {
		this.codEstadoCivil = codEstadoCivil;
	}
	public String getNomCargoEsp() {
		return nomCargoEsp;
	}
	public void setNomCargoEsp(String nomCargoEsp) {
		this.nomCargoEsp = nomCargoEsp;
	}
	public String getCodGenero() {
		return codGenero;
	}
	public void setCodGenero(String codGenero) {
		this.codGenero = codGenero;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getNumVia() {
		return numVia;
	}
	public void setNumVia(String numVia) {
		this.numVia = numVia;
	}
	public String getCodTipoDomicilio() {
		return codTipoDomicilio;
	}
	public void setCodTipoDomicilio(String codTipoDomicilio) {
		this.codTipoDomicilio = codTipoDomicilio;
	}
	public String getDesDomicilio() {
		return desDomicilio;
	}
	public void setDesDomicilio(String desDomicilio) {
		this.desDomicilio = desDomicilio;
	}
	public String getValPiso() {
		return valPiso;
	}
	public void setValPiso(String valPiso) {
		this.valPiso = valPiso;
	}
	public String getValManzana() {
		return valManzana;
	}
	public void setValManzana(String valManzana) {
		this.valManzana = valManzana;
	}
	public String getValLote() {
		return valLote;
	}
	public void setValLote(String valLote) {
		this.valLote = valLote;
	}
	public String getValSector() {
		return valSector;
	}
	public void setValSector(String valSector) {
		this.valSector = valSector;
	}
	public String getValReferencia() {
		return valReferencia;
	}
	public void setValReferencia(String valReferencia) {
		this.valReferencia = valReferencia;
	}
}
