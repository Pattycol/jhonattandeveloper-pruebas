/*
 * CobroService.java
 *
 * Created on 10 de junio de 2005, 03:12 PM
 */

package com.stconsulting.lbsweb.consulta.service;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.common.util.Constants;

import com.stconsulting.lbsweb.consulta.persistence.*;
import com.stconsulting.lbsweb.consulta.bean.*;

import java.util.*;
import org.apache.log4j.Logger;

/**
 * 
 * @author STCosulting
 */
public class CobroService extends GenericService{

	protected static Logger log=null;

	/** Creates a new instance of CobroService */
	public CobroService(){
		log=Logger.getLogger(this.getClass());
	}

	public List<Cobro> cargaListaCobroPorEstado(String codEstado) throws ServiceException{
		List<Cobro> listaCobro=null;
		TransactionContext tx=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			CobroDAO cobroDAO=new CobroDAO(tx);
			listaCobro=cobroDAO.cargaListaCobroPorEstado(codEstado);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error(e);

			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return listaCobro;
	}

	public int[] getNextCdrPK() throws ServiceException{
		int[] numeroConsecutivo=new int[2];
		TransactionContext tx=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			CobroDAO cobroDAO=new CobroDAO(tx);
			numeroConsecutivo=cobroDAO.getNextCdrPK();
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error(e);

			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
		return numeroConsecutivo;
	}

	public String actualizaEstadoCobro(List<Cobro> listaCobro,String codEstado){
		String codResultado=Constants.ERROR;
		TransactionContext tx=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			CobroDAO cobroDAO=new CobroDAO(tx);
			cobroDAO.actualizaEstadoCobro(listaCobro,codEstado);
			codResultado=Constants.OK;
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			codResultado=Constants.ERROR;
			log.error(e);
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
		}
		return codResultado;
	}

	public void actualizaConsecutivoCdr(int idXml,int idCobro){
		//String codResultado=Constants.ERROR;
		TransactionContext tx=null;
		try{
			tx=new TransactionContext();
			tx.begin();
			CobroDAO cobroDAO=new CobroDAO(tx);
			cobroDAO.actualizaConsecutivoCdr(idXml,idCobro);
			//codResultado=Constants.OK;
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			//codResultado=Constants.ERROR;
			log.error(e);
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
		}
	}

}
