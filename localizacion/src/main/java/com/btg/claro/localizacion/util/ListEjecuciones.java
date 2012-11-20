package com.btg.claro.localizacion.util;

import java.util.Hashtable;


public class ListEjecuciones {


    private static Hashtable<Integer, Ejecucion> hashtable;

    public static synchronized Ejecucion getEjecucion(Integer id) {
        return hashtable.get(id);

    }

    public static synchronized boolean isEjecucion(Integer id) {
        if (hashtable == null) {
            hashtable = new Hashtable<Integer, Ejecucion>();
        }
        return hashtable.containsKey(id);

    }

    public static synchronized void agregarEjecucion(Integer id, Ejecucion ejecucion) {
        if (hashtable == null) {
            hashtable = new Hashtable<Integer, Ejecucion>();
        }

        hashtable.put(id, ejecucion);

    }



}
