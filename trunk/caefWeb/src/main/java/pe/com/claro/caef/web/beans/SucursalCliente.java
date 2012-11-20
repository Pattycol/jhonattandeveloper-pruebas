package pe.com.claro.caef.web.beans;

public class SucursalCliente {
	//Bean que se utiliza para cargar las sucursales para el req. Consulta de Cliente
	
	private String nombreSucursal;
	private String dirSucursal;
	private String ubiSucursal;
	private String nombreDistrito;
	private String nombreProvincia;
	private String flgFacturacion;
	
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	public String getDirSucursal() {
		return dirSucursal;
	}
	public void setDirSucursal(String dirSucursal) {
		this.dirSucursal = dirSucursal;
	}
	public String getUbiSucursal() {
		return ubiSucursal;
	}
	public void setUbiSucursal(String ubiSucursal) {
		this.ubiSucursal = ubiSucursal;
	}
	public String getNombreDistrito() {
		return nombreDistrito;
	}
	public void setNombreDistrito(String nombreDistrito) {
		this.nombreDistrito = nombreDistrito;
	}
	public String getNombreProvincia() {
		return nombreProvincia;
	}
	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}
	public String getFlgFacturacion() {
		return flgFacturacion.equals("1")?"true":"false";
	}
	public void setFlgFacturacion(String flgFacturacion) {
		this.flgFacturacion = flgFacturacion;
	}
	
}
