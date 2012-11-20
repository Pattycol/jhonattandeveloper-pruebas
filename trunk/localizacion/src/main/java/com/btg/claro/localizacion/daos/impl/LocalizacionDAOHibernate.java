package com.btg.claro.localizacion.daos.impl;

import org.springframework.stereotype.Repository;

import com.btg.claro.localizacion.daos.LocalizacionDAO;
import com.btg.claro.localizacion.entidades.Localizacion;

@Repository("LocalizacionDAO")
public class LocalizacionDAOHibernate extends DAO<Localizacion> implements LocalizacionDAO{

}
