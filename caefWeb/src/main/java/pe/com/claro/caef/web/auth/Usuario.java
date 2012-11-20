package pe.com.claro.caef.web.auth;

import java.io.Serializable;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.ActionContext;

public class Usuario implements UserDetails,Serializable,HttpSessionBindingListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection<GrantedAuthority> authorities;
	private String username;
	private String codigoUsuario;
	private String nombreUsuario;
	
	private String codigoProducto;
	private String codigoServicio;
	private String codigoInstanciaServicio;
	private byte[] tdes_key;
	private String codTipoDocumento;
	private String numDocumento;
	private String tipUsuario;
	private String loginMap;
	private String correoCliente;
	private String telefonoMiClaroFija;
	private String password;
	private String dominio;
	private String encPass;//aloja el password encriptado

	private String sFlagUsuario;

	public String getTelefonoMiClaroFija() {
		return telefonoMiClaroFija;
	}

	public void setTelefonoMiClaroFija(String telefonoMiClaroFija) {
		this.telefonoMiClaroFija = telefonoMiClaroFija;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public String getLoginMap() {
		return loginMap;
	}

	public void setLoginMap(String loginMap) {
		this.loginMap = loginMap;
	}

	public String getTipUsuario() {
		return tipUsuario;
	}

	public void setTipUsuario(String tipUsuario) {
		this.tipUsuario = tipUsuario;
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

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public String getCodigoInstanciaServicio() {
		return codigoInstanciaServicio;
	}

	public void setCodigoInstanciaServicio(String codigoInstanciaServicio) {
		this.codigoInstanciaServicio = codigoInstanciaServicio;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getsFlagUsuario() {
		return sFlagUsuario;
	}

	public void setsFlagUsuario(String sFlagUsuario) {
		this.sFlagUsuario = sFlagUsuario;
	}
//	

	public byte[] getTdes_key() {
		return tdes_key;
	}

	public void setTdes_key(byte[] tdes_key) {
		this.tdes_key = tdes_key;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getEncPass() {
		return encPass;
	}

	public void setEncPass(String encPass) {
		this.encPass = encPass;
	}

	public void valueBound(HttpSessionBindingEvent arg0) {
//		System.out.println("SE AGREG");
////		
////		System.out.println(ActionContext.getContext());
////		// TODO Auto-generated method stub
//
//		ServletContext contexto=arg0.getSession().getServletContext();
//		contexto.setAttribute("log",1);
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
//		System.out.println("se quito el user");
//		System.out.println(arg0.getSession().getServletContext());
//		ServletContext contexto=arg0.getSession().getServletContext();
//		contexto.setAttribute("deslog",1);
		
	}
	
	
	
	
}
