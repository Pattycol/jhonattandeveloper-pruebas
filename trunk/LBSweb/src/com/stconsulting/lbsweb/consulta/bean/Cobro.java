/*
 * Cobro.java
 *
 * Created on 6 de junio de 2005, 03:02 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;

import com.stconsulting.lbsweb.consulta.bean.Localizacion;
/**
 *
 * @author STCosulting
 */
public class Cobro implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID=6469578933112987696L;
	
	int codCobro;
    String fechaRegistro;
    String fechaCobro;
    String codEstado;
    String descEstado;
    Localizacion localizacion;
    /** Creates a new instance of Cobro */
    public Cobro() {
    }
    
    /**
     * Getter for property codCobro.
     * @return Value of property codCobro.
     */
    public int getCodCobro() {
        return codCobro;
    }
    
    /**
     * Setter for property codCobro.
     * @param codCobro New value of property codCobro.
     */
    public void setCodCobro(int codCobro) {
        this.codCobro = codCobro;
    }
    
    /**
     * Getter for property fechaRegistro.
     * @return Value of property fechaRegistro.
     */
    public java.lang.String getFechaRegistro() {
        return fechaRegistro;
    }
    
    /**
     * Setter for property fechaRegistro.
     * @param fechaRegistro New value of property fechaRegistro.
     */
    public void setFechaRegistro(java.lang.String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    /**
     * Getter for property fechaCobro.
     * @return Value of property fechaCobro.
     */
    public java.lang.String getFechaCobro() {
        return fechaCobro;
    }
    
    /**
     * Setter for property fechaCobro.
     * @param fechaCobro New value of property fechaCobro.
     */
    public void setFechaCobro(java.lang.String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }
    
    /**
     * Getter for property codEstado.
     * @return Value of property codEstado.
     */
    public java.lang.String getCodEstado() {
        return codEstado;
    }
    
    /**
     * Setter for property codEstado.
     * @param codEstado New value of property codEstado.
     */
    public void setCodEstado(java.lang.String codEstado) {
        this.codEstado = codEstado;
    }
    
    /**
     * Getter for property descEstado.
     * @return Value of property descEstado.
     */
    public java.lang.String getDescEstado() {
        return descEstado;
    }
    
    /**
     * Setter for property descEstado.
     * @param descEstado New value of property descEstado.
     */
    public void setDescEstado(java.lang.String descEstado) {
        this.descEstado = descEstado;
    }
    
    /**
     * Getter for property localizacion.
     * @return Value of property localizacion.
     */
    public com.stconsulting.lbsweb.consulta.bean.Localizacion getLocalizacion() {
        return localizacion;
    }
    
    /**
     * Setter for property localizacion.
     * @param localizacion New value of property localizacion.
     */
    public void setLocalizacion(com.stconsulting.lbsweb.consulta.bean.Localizacion localizacion) {
        this.localizacion = localizacion;
    }
    
}
