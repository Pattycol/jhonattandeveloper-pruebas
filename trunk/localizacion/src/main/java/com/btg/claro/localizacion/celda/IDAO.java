package com.btg.claro.localizacion.celda;


import java.util.List;

public interface IDAO<T extends Entidad> {

	 public T get(Integer integer);

	    public  List<T> getTodos();

	    
    public T getPorIdentificador2(String identificador);
}
