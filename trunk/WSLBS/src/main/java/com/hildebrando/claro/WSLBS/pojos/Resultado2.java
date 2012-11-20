package com.hildebrando.claro.WSLBS.pojos;

public class Resultado2 {

    private String cod;
    private String consultante;
    private String consultado;
    private String fecIni;
    private String fecFin;

    private ArbolConsulta LIST;

    public String getCod() {
        return cod;
    }

    public String getConsultante() {
        return consultante;
    }

    public String getConsultado() {
        return consultado;
    }

    public String getFecIni() {
        return fecIni;
    }

    public String getFecFin() {
        return fecFin;
    }

    public ArbolConsulta getLIST() {
        return LIST;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setConsultante(String consultante) {
        this.consultante = consultante;
    }

    public void setConsultado(String consultado) {
        this.consultado = consultado;
    }

    public void setFecIni(String fecIni) {
        this.fecIni = fecIni;
    }

    public void setFecFin(String fecFin) {
        this.fecFin = fecFin;
    }

    public void setLIST(ArbolConsulta lIST) {
        LIST = lIST;
    }

}
