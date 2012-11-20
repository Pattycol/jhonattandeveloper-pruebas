package com.btg.claro.localizacion.util;

import java.util.HashSet;

public class ListEjecuciones2 {

    private static HashSet<Ejecucion> hashSet;

    public static synchronized boolean isEjecucion(Ejecucion id) {

        return hashSet.contains(id);

    }

    public static synchronized boolean obtenerEjecucion(Integer id) {

        return true;

    }

    public static synchronized void agregarEjecucion(Ejecucion ejecucion) {
        if (hashSet == null) {
            hashSet = new HashSet<Ejecucion>();
        }

        hashSet.add(ejecucion);

    }

}
