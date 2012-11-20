/*
 * GenericDAO.java
 *
 * Created on 14 de enero de 2004, 11:28 AM
 */

package com.stconsulting.common.persistence;

import java.util.*;

import org.apache.log4j.Logger;

/**
 * 
 * @author tcdata
 */
public class GenericDAO implements DataAccess{

	protected TransactionContext context;
	protected Logger log;
	public static String BOOLEAN_FIELD_TRUE_FLAG="1";
	public static String BOOLEAN_FIELD_FALSE_FLAG="0";

	/** Creates a new instance of GenericDAO */
	public GenericDAO(TransactionContext context){
		this.context=context;
		log=Logger.getLogger(this.getClass());
	}

	public void delete(Object obj){
	}

	public Object findByName(Object obj){
		return null;
	}

	public Object findByPrimaryKey(Object obj){
		return null;
	}

	public void insert(Object obj){
	}

	public void update(Object obj){
	}

	@SuppressWarnings("unused")
	public Integer getNextPK() throws DAOException{
		return null;
	}

	public List<Object> list(){
		return null;
	}

}
