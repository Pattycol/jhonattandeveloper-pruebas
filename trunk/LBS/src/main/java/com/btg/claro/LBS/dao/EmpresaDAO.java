package com.btg.claro.LBS.dao;

import com.btg.claro.LBS.domain.Empresa;
import com.btg.dao.dao.IDAO;

public interface EmpresaDAO extends IDAO<Empresa> {

		void incrementarConsultasWeb(String ruc);
		
		void incrementarConsultasSMS(String ruc);
		
		void incrementarConsultasAdicionales(String ruc);
}
