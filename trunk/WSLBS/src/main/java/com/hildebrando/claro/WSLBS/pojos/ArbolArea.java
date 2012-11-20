package com.hildebrando.claro.WSLBS.pojos;

import java.util.ArrayList;
import java.util.List;

public class ArbolArea {

    private List<ArbolUsuario> usuario;

    private String nombre;

    public ArbolArea() {
        this.usuario = new ArrayList<ArbolUsuario>();
        this.nombre = "";
    }


    public List<ArbolUsuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<ArbolUsuario> usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
