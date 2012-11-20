/*
 * DataAccess.java
 *
 * Created on 12 de diciembre de 2003, 04:03 PM
 */

package com.stconsulting.common.persistence;

import java.util.List;

/**
 * 
 * @author curso
 */
public interface DataAccess{

	public void insert(Object obj);

	public void update(Object obj);

	public void delete(Object obj);

	public Object findByPrimaryKey(Object obj);

	public Object findByName(Object obj);

	public List<Object> list();
}
