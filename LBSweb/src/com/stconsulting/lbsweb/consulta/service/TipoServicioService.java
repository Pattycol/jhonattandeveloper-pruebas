package com.stconsulting.lbsweb.consulta.service;

import org.apache.log4j.Logger;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.lbsweb.consulta.persistence.EmpresaDAO;
import com.stconsulting.lbsweb.consulta.persistence.TipoServicioDAO;

public class TipoServicioService extends GenericService {
	
	
	protected static Logger log=null;

	/** Creates a new instance of ConsultaService */
	public TipoServicioService(){
		log=Logger.getLogger(this.getClass());
	}
	
	public String getConsultasWebxTipo(String idEmpresa){
		
		 
		TransactionContext tx=null;
		String consultas=null;
		try{
			
			tx=new TransactionContext();
			tx.begin();
			
			TipoServicioDAO tipoServicioDAO=  new TipoServicioDAO(tx);
			
			consultas = tipoServicioDAO.getConsultasWebxTipo(idEmpresa);
			
			
			
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
		
		return consultas;
		
	}

}