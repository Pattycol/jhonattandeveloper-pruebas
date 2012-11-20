/*
 * BusquedaNumeroAsociadoService.java
 *
 * Created on 2 de junio de 2005, 11:08 AM
 */

package com.stconsulting.lbsweb.consulta.service;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.common.service.ServiceException;

import com.stconsulting.lbsweb.consulta.persistence.BusquedaNumeroAsociadoDAO;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

import java.util.*;

/**
 * 
 * @author STCosulting
 */
public class BusquedaNumeroAsociadoService extends GenericService{

	/** Creates a new instance of BusquedaNumeroAsociadoService */
	public BusquedaNumeroAsociadoService(){
		super();
	}

	public List<ResultadoBusquedaNumero> busquedaNumeros(Usuario usuario,String numero) throws ServiceException{
		log.debug("Ingrese al busquedaNumeros() de BusquedaNumeroAsociadoService");
		TransactionContext tx=null;

		List<ResultadoBusquedaNumero> listaRsultado=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			BusquedaNumeroAsociadoDAO busquedaNumeroDAO=new BusquedaNumeroAsociadoDAO(tx);
			listaRsultado=busquedaNumeroDAO.busquedaNumeros(usuario,numero);

			log.debug("Se realizo la consulta con exito");
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error(e);
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		log.debug("Sali del busquedaNumeros() de BusquedaNumeroAsociadoService");
		return listaRsultado;
	}
}
