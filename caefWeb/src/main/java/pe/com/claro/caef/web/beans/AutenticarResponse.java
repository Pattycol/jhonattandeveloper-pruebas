package pe.com.claro.caef.web.beans;

import java.util.List;

public class AutenticarResponse extends AuditTypes {
	
	private Boolean isValidar;
	private UsuarioWebType usuarioWebType;
	private List<ListaMenu> lstListaMenu;
	private List<ListaCuenta> lstListaCuenta;
	
	public Boolean getIsValidar() {
		return isValidar;
	}
	public void setIsValidar(Boolean isValidar) {
		this.isValidar = isValidar;
	}
	public UsuarioWebType getUsuarioWebType() {
		return usuarioWebType;
	}
	public void setUsuarioWebType(UsuarioWebType usuarioWebType) {
		this.usuarioWebType = usuarioWebType;
	}
	public List<ListaMenu> getLstListaMenu() {
		return lstListaMenu;
	}
	public void setLstListaMenu(List<ListaMenu> lstListaMenu) {
		this.lstListaMenu = lstListaMenu;
	}
	public List<ListaCuenta> getLstListaCuenta() {
		return lstListaCuenta;
	}
	public void setLstListaCuenta(List<ListaCuenta> lstListaCuenta) {
		this.lstListaCuenta = lstListaCuenta;
	}
	
}
