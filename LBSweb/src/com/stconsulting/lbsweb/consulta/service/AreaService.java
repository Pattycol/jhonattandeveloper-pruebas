package com.stconsulting.lbsweb.consulta.service;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.lbsweb.consulta.persistence.AreaDAO;

public class AreaService extends GenericService {
	protected static Logger log=null;

	/** Creates a new instance of ConsultaService */
	public AreaService(){
		log=Logger.getLogger(this.getClass());
	}
	
	public String getRucArea(String telefono){
		
		 
		TransactionContext tx=null;
		String ruc=null;
		try{
			
			tx=new TransactionContext();
			tx.begin();
			
			AreaDAO areaDAO= new AreaDAO(tx);
			
			ruc = areaDAO.getEmpresaAreaUsuario(telefono);
			
			tx.commit();
			tx.close();
			tx=null;
			
		}catch(Exception e){
			log.error("ERROR :" + e.getMessage());
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			// throw new ServiceException(e);
		}
		
		return ruc;
		
	}

}
