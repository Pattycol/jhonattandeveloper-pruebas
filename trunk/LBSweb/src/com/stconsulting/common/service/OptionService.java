/*
 * Service.java
 *
 * Created on June 4, 2004, 6:37 PM
 */

package com.stconsulting.common.service;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.common.service.ServiceException;

import com.stconsulting.common.persistence.*;
import com.stconsulting.common.list.*;

/**
 * 
 * @author Administrator
 */
public class OptionService extends GenericService{

	/** Creates a new instance of Service */
	public OptionService(){
		super();
	}

	public OptionList select(String cadena) throws ServiceException{
		TransactionContext tx=null;
		OptionDAO optionDAO=null;
		OptionList list=new OptionList();
		try{
			tx=new TransactionContext();
			optionDAO=new OptionDAO(tx);
			log.debug("OptionService cadena: " + cadena);
			list=optionDAO.select(cadena);
			tx.close();
			tx=null;
		}
		catch(Exception e){
			e.printStackTrace();
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
		return list;
	}
}
