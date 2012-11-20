package pe.com.claro.caef.web.beans;

import java.util.List;

public class ReciboCorreoElectronico extends AuditTypes {
	//Bean que se utiliza para consultar el grupo de fac. de recibos del cliente req. Solicitar Recibo por Correo Electrónico
	
	private String codGrupo;
	private String nomServicio;
	private String valCorreoElectronico;
	private List<ListaCorreos> lstCorreos;//agregado para el llenado del combo
	private String flgActivo;

	public String getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(String codGrupo) {
		this.codGrupo = codGrupo;
	}
	public String getNomServicio() {
		return nomServicio;
	}
	public void setNomServicio(String nomServicio) {
		this.nomServicio = nomServicio;
	}
	public String getValCorreoElectronico() {
		return valCorreoElectronico;
	}
	public void setValCorreoElectronico(String valCorreoElectronico) {
		this.valCorreoElectronico = valCorreoElectronico;
	}
	public String getFlgActivo() {
		return flgActivo.equals("1")?"true":"false";
	}
	public void setFlgActivo(String flgActivo) {
		this.flgActivo = flgActivo;
	}
	public List<ListaCorreos> getLstCorreos() {
		return lstCorreos;
	}
	public void setLstCorreos(List<ListaCorreos> lstCorreos) {
		this.lstCorreos = lstCorreos;
	}
}
