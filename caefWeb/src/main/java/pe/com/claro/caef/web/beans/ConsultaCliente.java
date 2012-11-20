package pe.com.claro.caef.web.beans;

import java.util.ArrayList;
import java.util.List;

public class ConsultaCliente extends AuditTypes {
	//Bean que se utiliza para cargar los datos generales del cliente para el req. Consulta de Cliente
	
	private String codCliente;			//codigoCliente
	private String nomCliente;			//nombre
	private String nomClientePila;		//nombrePila
	private String nomApePaterno;		//apellidoPaterno
	private String nomApeMaterno;		//apellidoMaterno
	private String codTipoDocumento;	//codigoTipoDocumento
	private String numDocumento;		//numeroDocumento
	private String valCorreoElectronico;//correoElectronico
	private String fecNacimiento;		//fechaNacimiento
	private String codEstadoCivil;		//codigoEstadoCivil
	private String nomCargoEsp;			//cargoEspecifico
	private String codGenero;			//codigoGenero
	private String codNacionalidad;
	private String codTipoVia;			//codigoTipoVia
	private String nomVia;				//nombreVia
	private String numVia;				//numeroVia
	private String codTipoDomicilio;	//codigoTipoDomicilio
	private String desDomicilio;		//descripcionDomicilio
	private String valPiso;				//piso
	private String valManzana;			//manzana
	private String valLote;				//lote
	private String valSector;			//sector
	private String nomUrbanizacion;		//nombreUrbanizacion
	private String valReferencia;		//referencia
	private String codPais;				//codigoPais
	private String nomPais;				//nombrePais
	private String codDepartamento;		//codigoDepartamento
	private String nomDepartamento;		//nombreDepartamento
	private String codProvincia;		//codigoProvincia
	private String nomProvincia;		//nombreProvincia
	private String codDisrito;			//codigoDistrito
	private String nomDistrito;			//nombreDistrito
	private List<ContactoCliente> lstContactoCliente = new ArrayList<ContactoCliente>();
	private List<SucursalCliente> lstSucursalCliente = new ArrayList<SucursalCliente>();
	private List<ServicioCliente> lstServicioCliente = new ArrayList<ServicioCliente>();
	
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getNomCliente() {
		return nomCliente;
	}
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}
	public String getNomClientePila() {
		return nomClientePila;
	}
	public void setNomClientePila(String nomClientePila) {
		this.nomClientePila = nomClientePila;
	}
	public String getNomApePaterno() {
		return nomApePaterno;
	}
	public void setNomApePaterno(String nomApePaterno) {
		this.nomApePaterno = nomApePaterno;
	}
	public String getNomApeMaterno() {
		return nomApeMaterno;
	}
	public void setNomApeMaterno(String nomApeMaterno) {
		this.nomApeMaterno = nomApeMaterno;
	}
	public String getCodTipoDocumento() {
		return codTipoDocumento;
	}
	public void setCodTipoDocumento(String codTipoDocumento) {
		this.codTipoDocumento = codTipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
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
	public String getCodTipoVia() {
		return codTipoVia;
	}
	public void setCodTipoVia(String codTipoVia) {
		this.codTipoVia = codTipoVia;
	}
	public String getNomVia() {
		return nomVia;
	}
	public void setNomVia(String nomVia) {
		this.nomVia = nomVia;
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
	public String getNomUrbanizacion() {
		return nomUrbanizacion;
	}
	public void setNomUrbanizacion(String nomUrbanizacion) {
		this.nomUrbanizacion = nomUrbanizacion;
	}
	public String getValReferencia() {
		return valReferencia;
	}
	public void setValReferencia(String valReferencia) {
		this.valReferencia = valReferencia;
	}
	public String getCodPais() {
		return codPais;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getNomPais() {
		return nomPais;
	}
	public void setNomPais(String nomPais) {
		this.nomPais = nomPais;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getNomDepartamento() {
		return nomDepartamento;
	}
	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}
	public String getCodProvincia() {
		return codProvincia;
	}
	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}
	public String getNomProvincia() {
		return nomProvincia;
	}
	public void setNomProvincia(String nomProvincia) {
		this.nomProvincia = nomProvincia;
	}
	public String getCodDisrito() {
		return codDisrito;
	}
	public void setCodDisrito(String codDisrito) {
		this.codDisrito = codDisrito;
	}
	public String getNomDistrito() {
		return nomDistrito;
	}
	public void setNomDistrito(String nomDistrito) {
		this.nomDistrito = nomDistrito;
	}
	public List<ContactoCliente> getLstContactoCliente() {
		return lstContactoCliente;
	}
	public void setLstContactoCliente(List<ContactoCliente> lstContactoCliente) {
		this.lstContactoCliente = lstContactoCliente;
	}
	public List<SucursalCliente> getLstSucursalCliente() {
		return lstSucursalCliente;
	}
	public void setLstSucursalCliente(List<SucursalCliente> lstSucursalCliente) {
		this.lstSucursalCliente = lstSucursalCliente;
	}
	public List<ServicioCliente> getLstServicioCliente() {
		return lstServicioCliente;
	}
	public void setLstServicioCliente(List<ServicioCliente> lstServicioCliente) {
		this.lstServicioCliente = lstServicioCliente;
	}
}
