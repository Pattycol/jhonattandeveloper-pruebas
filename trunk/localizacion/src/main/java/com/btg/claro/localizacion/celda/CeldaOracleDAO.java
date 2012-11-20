package com.btg.claro.localizacion.celda;



public interface CeldaOracleDAO extends IDAO<Celda>{

    Celda getPorIdentificador(String identificador);
}
