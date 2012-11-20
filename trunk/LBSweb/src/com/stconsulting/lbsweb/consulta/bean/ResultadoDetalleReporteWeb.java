/*
 * ResultadoDetalleReporteWeb.java
 *
 * Created on 9 de junio de 2005, 12:47 PM
 */

package com.stconsulting.lbsweb.consulta.bean;

import java.io.Serializable;

/**
 *
 * @author STCosulting
 */
public class ResultadoDetalleReporteWeb implements Serializable {
    
    private String codOperacion;
    private String fechaRegistro;
    private String horaRegistro;
    private String mobileDestino;
    private String resultado;
    private String codTipoConsulta;
    private String descTipoConsulta;
    private String codTarea;
    private String direccion;
    /** Creates a new instance of ResultadoDetalleReporteWeb */
    public ResultadoDetalleReporteWeb() {
    }
    
    /**
     * Getter for property codOperacion.
     * @return Value of property codOperacion.
     */
    public java.lang.String getCodOperacion() {
        return codOperacion;
    }
    
    /**
     * Setter for property codOperacion.
     * @param codOperacion New value of property codOperacion.
     */
    public void setCodOperacion(java.lang.String codOperacion) {
        this.codOperacion = codOperacion;
    }
    
    /**
     * Getter for property codTarea.
     * @return Value of property codTarea.
     */
    public java.lang.String getCodTarea() {
        return codTarea;
    }
    
    /**
     * Setter for property codTarea.
     * @param codTarea New value of property codTarea.
     */
    public void setCodTarea(java.lang.String codTarea) {
        this.codTarea = codTarea;
    }
    
    /**
     * Getter for property codTipoConsulta.
     * @return Value of property codTipoConsulta.
     */
    public java.lang.String getCodTipoConsulta() {
        return codTipoConsulta;
    }
    
    /**
     * Setter for property codTipoConsulta.
     * @param codTipoConsulta New value of property codTipoConsulta.
     */
    public void setCodTipoConsulta(java.lang.String codTipoConsulta) {
        this.codTipoConsulta = codTipoConsulta;
    }
    
    /**
     * Getter for property descTipoConsulta.
     * @return Value of property descTipoConsulta.
     */
    public java.lang.String getDescTipoConsulta() {
        return descTipoConsulta;
    }
    
    /**
     * Setter for property descTipoConsulta.
     * @param descTipoConsulta New value of property descTipoConsulta.
     */
    public void setDescTipoConsulta(java.lang.String descTipoConsulta) {
        this.descTipoConsulta = descTipoConsulta;
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
     * Getter for property mobileDestino.
     * @return Value of property mobileDestino.
     */
    public java.lang.String getMobileDestino() {
        return mobileDestino;
    }
    
    /**
     * Setter for property mobileDestino.
     * @param mobileDestino New value of property mobileDestino.
     */
    public void setMobileDestino(java.lang.String mobileDestino) {
        this.mobileDestino = mobileDestino;
    }
    
    /**
     * Getter for property resultado.
     * @return Value of property resultado.
     */
    public java.lang.String getResultado() {
        return resultado;
    }
    
    /**
     * Setter for property resultado.
     * @param resultado New value of property resultado.
     */
    public void setResultado(java.lang.String resultado) {
        this.resultado = resultado;
    }
    
    /**
     * Getter for property horaRegistro.
     * @return Value of property horaRegistro.
     */
    public java.lang.String getHoraRegistro() {
        return horaRegistro;
    }
    
    /**
     * Setter for property horaRegistro.
     * @param horaRegistro New value of property horaRegistro.
     */
    public void setHoraRegistro(java.lang.String horaRegistro) {
        this.horaRegistro = horaRegistro;
    }
    
    /**
     * Getter for property direccion.
     * @return Value of property direccion.
     */
    public java.lang.String getDireccion() {
        return direccion;
    }
    
    /**
     * Setter for property direccion.
     * @param direccion New value of property direccion.
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }
    
}
