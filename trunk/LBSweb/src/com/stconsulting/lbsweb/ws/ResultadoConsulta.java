/*
 * ResultadoConsultaService.java
 *
 * Created on 20 de junio de 2005, 09:25 AM
 */

package com.stconsulting.lbsweb.ws;

/**
 *
 * @author STCosulting
 */
public class ResultadoConsulta {
    
    private String codResultado;
    
    private String mensaje;
    
    private ResultadoDetalleConsulta[] listaResultadoDetalle;
    
    /** Creates a new instance of ResultadoConsultaService */
    public ResultadoConsulta() {
    }
    
    /**
     * Getter for property codResultado.
     * @return Value of property codResultado.
     */
    public java.lang.String getCodResultado() {
        return codResultado;
    }
    
    /**
     * Setter for property codResultado.
     * @param codResultado New value of property codResultado.
     */
    public void setCodResultado(java.lang.String codResultado) {
        this.codResultado = codResultado;
    }
    
    /**
     * Getter for property mensaje.
     * @return Value of property mensaje.
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }
    
    /**
     * Setter for property mensaje.
     * @param mensaje New value of property mensaje.
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * Getter for property listaResultadoDetalle.
     * @return Value of property listaResultadoDetalle.
     */
    public com.stconsulting.lbsweb.ws.ResultadoDetalleConsulta[] getListaResultadoDetalle() {
        return this.listaResultadoDetalle;
    }
    
    /**
     * Setter for property listaResultadoDetalle.
     * @param listaResultadoDetalle New value of property listaResultadoDetalle.
     */
    public void setListaResultadoDetalle(com.stconsulting.lbsweb.ws.ResultadoDetalleConsulta[] listaResultadoDetalle) {
        this.listaResultadoDetalle = listaResultadoDetalle;
    }
    
}
