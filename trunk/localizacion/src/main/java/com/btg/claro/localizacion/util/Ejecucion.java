package com.btg.claro.localizacion.util;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.btg.claro.localizacion.services.impl.LocalizacionServ;

public class Ejecucion {

    private static Logger log = LoggerFactory.getLogger(Ejecucion.class);

    private List<LocalizacionServ> list;
    private int maximoEjecucion;
    private String nombre;


    public Ejecucion(int maximoEjecucion, String nombre) {
        this.maximoEjecucion = maximoEjecucion;
        this.nombre = nombre;

    }
	
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaximoEjecucion() {
        return maximoEjecucion;
    }

    public void setMaximoEjecucion(int maximoEjecucion) {
        this.maximoEjecucion = maximoEjecucion;
    }

    public synchronized void agregarHilos(LocalizacionServ hilo) {
		if(list == null){
			list = new LinkedList<LocalizacionServ>();
		}

        if (this.getTotalHilos() >= this.maximoEjecucion) {

            log.debug("Esperando a que se libere para : [" + this.maximoEjecucion + "]");

            try {
                wait();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block

            }

            log.debug("Se libero");
        }

		list.add(hilo);
		

	}
	
    public synchronized void removerHilos(LocalizacionServ hilo) {
		if(list!=null){

            list.remove(hilo);
            log.debug("hilo removido");
            notify();
            log.debug("aqui avisa para que se desocupe");

		}
	}
	
    public synchronized int getTotalHilos() {
		if(list==null){
			list=new LinkedList<LocalizacionServ>();
		}
		return list.size();
	}
	

}
