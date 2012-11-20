package pe.com.claro.caef.web.beans;

public class TrafficView extends AuditTypes {
	//Bean que se utiliza para consultar la lista de trafficview del cliente req. TrafficView
	
	private String codCID; 				//codigoCid
	private String nomSucursal;			//descripcionSucursal
	private String nomDirecSucursal;	//direccionSucursal
	private String nomProducto;			//descripcionProducto
	private String fecInst;				//fechaInstalacion
	private String descripcionEstadoCid;//descripcionEstadoCid
	private String urlTraffic;
	
	public String getUrlTraffic() {
		return urlTraffic;
	}
	public void setUrlTraffic(String urlTraffic) {
		this.urlTraffic = urlTraffic;
	}
	public String getDescripcionEstadoCid() {
		return descripcionEstadoCid;
	}
	public void setDescripcionEstadoCid(String descripcionEstadoCid) {
		this.descripcionEstadoCid = descripcionEstadoCid;
	}
	public String getCodCID() {
		return codCID;
	}
	public void setCodCID(String codCID) {
		this.codCID = codCID;
	}
	public String getNomSucursal() {
		return nomSucursal;
	}
	public void setNomSucursal(String nomSucursal) {
		this.nomSucursal = nomSucursal;
	}
	public String getNomDirecSucursal() {
		return nomDirecSucursal;
	}
	public void setNomDirecSucursal(String nomDirecSucursal) {
		this.nomDirecSucursal = nomDirecSucursal;
	}
	public String getNomProducto() {
		return nomProducto;
	}
	public void setNomProducto(String nomProducto) {
		this.nomProducto = nomProducto;
	}
	public String getFecInst() {
		return fecInst;
	}
	public void setFecInst(String fecInst) {
		this.fecInst = fecInst;
	}
}
