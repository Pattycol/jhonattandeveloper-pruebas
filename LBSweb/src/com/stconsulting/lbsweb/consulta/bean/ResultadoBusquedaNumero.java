/*
 * ResultadoBusquedaNumero.java
 *
 * Created on 2 de junio de 2005, 10:57 AM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;

/**
 *
 * @author STCosulting
 */
public class ResultadoBusquedaNumero implements Serializable {
    
    private String mobile;
    private String nombreUsuario;
    
    /** Creates a new instance of ResultadoBusquedaNumero */
    public ResultadoBusquedaNumero() {
    }
    
    /**
     * Getter for property mobile.
     * @return Value of property mobile.
     */
    public java.lang.String getMobile() {
        return mobile;
    }
    
    /**
     * Setter for property mobile.
     * @param mobile New value of property mobile.
     */
    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }
    
    /**
     * Getter for property nombreUsuario.
     * @return Value of property nombreUsuario.
     */
    public java.lang.String getNombreUsuario() {
        return nombreUsuario;
    }
    
    /**
     * Setter for property nombreUsuario.
     * @param nombreUsuario New value of property nombreUsuario.
     */
    public void setNombreUsuario(java.lang.String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
}
