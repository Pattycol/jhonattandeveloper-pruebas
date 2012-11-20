package com.btg.claro.LBS.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.HLRDAO;

@Repository("HLRDAO")
public class HLRDAOImpl implements HLRDAO{
	
	@PersistenceContext(unitName="hlrPU")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public int getHLRPorNumero(int numero){
		
		
		String sql="SELECT IDNUMERACIONHLR FROM RN_RANGONUMERACIONRED WHERE :numero>INICIORANGO AND :numero<FINRANGO";		
		Query q=em.createNativeQuery(sql);
		q.setParameter("numero",numero);
		//FIXME la consulta debería devolver sólo un valor		
		List<BigDecimal> lista=q.getResultList();
		int hlr=0;
		if(lista.size()>0){
			hlr=lista.get(lista.size()-1).intValue();
		}
		
		
		return hlr;
	}

}
