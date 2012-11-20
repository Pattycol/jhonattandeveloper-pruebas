package com.hildebrando.claro.WSLBS.pojos;

public class Resultado {

    private ArbolEmpresa Empresa;

    private String mensaje;

    private String error;

    private String cantidad;

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArbolEmpresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(ArbolEmpresa empresa) {
        Empresa = empresa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
