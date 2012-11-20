package pe.com.claro.caef.web.beans;

public class ConsultaConsumo extends AuditTypes {
	//Bean que se utiliza para consultar los consumos del cliente req. Consulta de consumos
	
	private String desTipServicio;	//nombreTipoServicio
	private String desProducto;		//nombreProducto
	private String codTipLlamada;	//codigoTipoLlamada
	private String desTipLlamada;	//descripcionTipoLlamada
	private String valConsumo;		//consumoAcumulado

	public String getDesTipServicio() {
		return desTipServicio;
	}
	public void setDesTipServicio(String desTipServicio) {
		this.desTipServicio = desTipServicio;
	}
	public String getDesProducto() {
		return desProducto;
	}
	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}
	public String getCodTipLlamada() {
		return codTipLlamada;
	}
	public void setCodTipLlamada(String codTipLlamada) {
		this.codTipLlamada = codTipLlamada;
	}
	public String getDesTipLlamada() {
		return desTipLlamada;
	}
	public void setDesTipLlamada(String desTipLlamada) {
		this.desTipLlamada = desTipLlamada;
	}
	public String getValConsumo() {
		return valConsumo;
	}
	public void setValConsumo(String valConsumo) {
		this.valConsumo = valConsumo;
	}
}
