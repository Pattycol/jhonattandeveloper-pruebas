/*
 * ParametroConsultaService.java
 *
 * Created on 20 de junio de 2005, 09:25 AM
 */

package com.stconsulting.lbsweb.ws;

/**
 *
 * @author STCosulting
 */





























public class ParametroConsulta {
    
    private String mobileUsuario;
    
    private String password;
    
    private String codFormatoRespuesta;
    
    private String[] mobiles;
    
    /** Creates a new instance of ParametroConsultaService */
    public ParametroConsulta() {
    }
    
    /**
     * Getter for property codFormatoRespuesta.
     * @return Value of property codFormatoRespuesta.
     */
    public java.lang.String getCodFormatoRespuesta() {
        return codFormatoRespuesta;
    }
    
    /**
     * Setter for property codFormatoRespuesta.
     * @param codFormatoRespuesta New value of property codFormatoRespuesta.
     */
    public void setCodFormatoRespuesta(java.lang.String codFormatoRespuesta) {
        this.codFormatoRespuesta = codFormatoRespuesta;
    }
    
    /**
     * Getter for property mobiles.
     * @return Value of property mobiles.
     */
    public java.lang.String[] getMobiles() {
        return this.mobiles;
    }
    
    /**
     * Setter for property mobiles.
     * @param mobiles New value of property mobiles.
     */
    public void setMobiles(java.lang.String[] mobiles) {
        this.mobiles = mobiles;
    }
    
    /**
     * Getter for property mobileUsuario.
     * @return Value of property mobileUsuario.
     */
    public java.lang.String getMobileUsuario() {
        return mobileUsuario;
    }
    
    /**
     * Setter for property mobileUsuario.
     * @param mobileUsuario New value of property mobileUsuario.
     */
    public void setMobileUsuario(java.lang.String mobileUsuario) {
        this.mobileUsuario = mobileUsuario;
    }
    
    /**
     * Getter for property password.
     * @return Value of property password.
     */
    public java.lang.String getPassword() {
        return password;
    }
    
    /**
     * Setter for property password.
     * @param password New value of property password.
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    
}
