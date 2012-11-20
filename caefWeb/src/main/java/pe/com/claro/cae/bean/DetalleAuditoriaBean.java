package pe.com.claro.cae.bean; 

import java.io.Serializable;

public class DetalleAuditoriaBean implements Serializable
{ 
	private static final long serialVersionUID = 1L;
	
	private Long codigoAuditoria;
	private String codigoUsuarioAudit;
	private String codigoUsuario;
	private String eventoOpcion;
	private String numeroClaro;
	private String cuenta;
	private String preSubcuenta;
	private String subCuenta;
	private String email;
	private String fechaEmision;
	private String datosEvento;
	private String servicios;
	
	private String ip;
	private String imei;
	private String marca;
	private String modelo;
	private String numeroacceso;
	
	public DetalleAuditoriaBean(){
		
	}
	
    public DetalleAuditoriaBean(Long codigoAuditoria, String codigoUsuario , String eventoOpcion,
							String numeroClaro, String cuenta, String preSubcuenta, String subCuenta, String email,
    					String fechaEmision, String datosEvento, String servicios,String codigoUsuarioAudit, String ip){
       
    	this.codigoAuditoria = codigoAuditoria;
    	this.codigoUsuario = codigoUsuario;
    	this.codigoUsuarioAudit = codigoUsuarioAudit ;
    	this.eventoOpcion = eventoOpcion;
    	this.numeroClaro = numeroClaro;
    	this.cuenta = cuenta;
    	this.preSubcuenta = preSubcuenta;
    	this.subCuenta = subCuenta;
    	this.email = email;
    	this.fechaEmision = fechaEmision;
    	this.datosEvento = datosEvento;
    	this.servicios = servicios;
    	this.ip = ip;
    }

	public Long getCodigoAuditoria() {
		return codigoAuditoria;
	}

	public void setCodigoAuditoria(Long codigoAuditoria) {
		this.codigoAuditoria = codigoAuditoria;
	}

	public String getCodigoUsuarioAudit() {
		return codigoUsuarioAudit;
	}

	public void setCodigoUsuarioAudit(String codigoUsuarioAudit) {
		this.codigoUsuarioAudit = codigoUsuarioAudit;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDatosEvento() {
		return datosEvento;
	}

	public void setDatosEvento(String datosEvento) {
		this.datosEvento = datosEvento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEventoOpcion() {
		return eventoOpcion;
	}

	public void setEventoOpcion(String eventoOpcion) {
		this.eventoOpcion = eventoOpcion;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getNumeroClaro() {
		return numeroClaro;
	}

	public void setNumeroClaro(String numeroClaro) {
		this.numeroClaro = numeroClaro;
	}

	public String getPreSubcuenta() {
		return preSubcuenta;
	}

	public void setPreSubcuenta(String preSubcuenta) {
		this.preSubcuenta = preSubcuenta;
	}

	public String getSubCuenta() {
		return subCuenta;
	}

	public void setSubCuenta(String subCuenta) {
		this.subCuenta = subCuenta;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append("codigoAuditoria=").append(this.codigoAuditoria).append(" , ");
		buffer.append("codigoUsuarioAudit=").append(this.codigoUsuarioAudit).append(" , ");
		buffer.append("codigoUsuario=").append(this.codigoUsuario).append(" , ");
		buffer.append("eventoOpcion=").append(this.eventoOpcion).append(" , ");
		buffer.append("numeroClaro=").append(this.numeroClaro).append(" , ");
		buffer.append("cuenta=").append(this.cuenta).append(" , ");
		buffer.append("preSubcuenta=").append(this.preSubcuenta).append(" , ");
		buffer.append("subCuenta=").append(this.subCuenta).append(" , ");
		buffer.append("email=").append(this.email).append(" , ");
		buffer.append("fechaEmision=").append(this.fechaEmision).append(" , ");
		buffer.append("datosEvento=").append(this.datosEvento).append(" , ");
		buffer.append("servicios=").append(this.servicios).append(" , ");
		buffer.append("ip=").append(this.ip).append(" , ");
		buffer.append("imei=").append(this.imei).append(" , ");
		buffer.append("marca=").append(this.marca).append(" , ");
		buffer.append("modelo=").append(this.modelo).append(" , ");
		buffer.append("numeroacceso=").append(this.numeroacceso).append(" , ");
		buffer.append("]");
		return buffer.toString();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNumeroacceso() {
		return numeroacceso;
	}

	public void setNumeroacceso(String numeroacceso) {
		this.numeroacceso = numeroacceso;
	}
	
	
	
	
} 
