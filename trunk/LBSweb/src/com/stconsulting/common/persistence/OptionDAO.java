/*
 * DAO.java
 *
 * Created on June 4, 2004, 6:23 PM
 */

package com.stconsulting.common.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.GenericDAO;
import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.bean.*;
import com.stconsulting.common.list.*;

/**
 * 
 * @author Administrator
 */
public class OptionDAO extends GenericDAO{

	/** Creates a new instance of DAO */
	public OptionDAO(TransactionContext context){
		super(context);
		this.context=context;
		log=Logger.getLogger(this.getClass());
	}

	public OptionList select(String cadena){
		OptionList list=new OptionList();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			log.debug("OptionDAO planillas");
			log.debug("OptionDAO cadena: " + cadena);
			ResourceBundle rb=ResourceBundle.getBundle("planillas");
			String sql=rb.getString(cadena);
			log.debug("OptionDAO sql: " + sql);
			pstmt=context.getConnection().prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Option option=new Option();
				option.setCodigo(rs.getString(1));
				option.setDescripcion(rs.getString(2));
				list.add(option);
			}
			pstmt.close();
			pstmt=null;
			rs.close();
			rs=null;
		}
		catch(Exception e){
			e.printStackTrace();

		}
		return list;
	}
}
