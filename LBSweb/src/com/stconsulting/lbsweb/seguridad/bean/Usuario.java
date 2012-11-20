/*
 * Usuario.java
 *
 * Created on 25 de mayo de 2005, 04:05 PM
 */

package com.stconsulting.lbsweb.seguridad.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author STconsulting
 */
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=-4354070752942223094L;

	private Integer codUsuario;
	
	private String codTipoUsuario;
	
	private String codPerfil;
	
	private String descPerfil;
	
	private Integer codArea;
	
	private String descArea;
	
	private Integer codEmpresa;
	
	private String descEmpresa;
	
	private String apePat;
	
	private String apeMat;
	
	private String nombres;
	
	private String nombreCompleto;
	
	private String email;
	
	private String telefono;
	
	private String user;
	
	private String password;
	
	private int numFallidos;
	
	private String descEstado;
	
	private String estado;
	
	private String claveRecuperada;

	private String flagBloqueado;

	private Date fechaActualizacion;
	
	private String codServicio;

	public Integer getCodUsuario(){
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario){
		this.codUsuario=codUsuario;
	}

	public String getCodTipoUsuario(){
		return codTipoUsuario;
	}

	public void setCodTipoUsuario(String codTipoUsuario){
		this.codTipoUsuario=codTipoUsuario;
	}

	public String getCodPerfil(){
		return codPerfil;
	}

	public void setCodPerfil(String codPerfil){
		this.codPerfil=codPerfil;
	}

	public String getDescPerfil(){
		return descPerfil;
	}

	public void setDescPerfil(String descPerfil){
		this.descPerfil=descPerfil;
	}

	public Integer getCodArea(){
		return codArea;
	}

	public void setCodArea(Integer codArea){
		this.codArea=codArea;
	}

	public String getDescArea(){
		return descArea;
	}

	public void setDescArea(String descArea){
		this.descArea=descArea;
	}

	public Integer getCodEmpresa(){
		return codEmpresa;
	}

	public void setCodEmpresa(Integer codEmpresa){
		this.codEmpresa=codEmpresa;
	}

	public String getDescEmpresa(){
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa){
		this.descEmpresa=descEmpresa;
	}

	public String getApePat(){
		return apePat;
	}

	public void setApePat(String apePat){
		this.apePat=apePat;
	}

	public String getApeMat(){
		return apeMat;
	}

	public void setApeMat(String apeMat){
		this.apeMat=apeMat;
	}

	public String getNombres(){
		return nombres;
	}

	public void setNombres(String nombres){
		this.nombres=nombres;
	}

	public String getNombreCompleto(){
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto){
		this.nombreCompleto=nombreCompleto;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getTelefono(){
		return telefono;
	}

	public void setTelefono(String telefono){
		this.telefono=telefono;
	}

	public String getUser(){
		return user;
	}

	public void setUser(String user){
		this.user=user;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public int getNumFallidos(){
		return numFallidos;
	}

	public void setNumFallidos(int numFallidos){
		this.numFallidos=numFallidos;
	}

	public String getDescEstado(){
		return descEstado;
	}

	public void setDescEstado(String descEstado){
		this.descEstado=descEstado;
	}

	public String getEstado(){
		return estado;
	}

	public void setEstado(String estado){
		this.estado=estado;
	}

	public String getFlagBloqueado(){
		return flagBloqueado;
	}

	public void setFlagBloqueado(String flagBloqueado){
		this.flagBloqueado=flagBloqueado;
	}

	public Date getFechaActualizacion(){
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion){
		this.fechaActualizacion=fechaActualizacion;
	}

	public String getCodServicio(){
		return codServicio;
	}

	public void setCodServicio(String codServicio){
		this.codServicio=codServicio;
	}
	
	public String getClaveRecuperada() {
		return claveRecuperada;
	}

	public void setClaveRecuperada(String claveRecuperada) {
		this.claveRecuperada = claveRecuperada;
	}

}
