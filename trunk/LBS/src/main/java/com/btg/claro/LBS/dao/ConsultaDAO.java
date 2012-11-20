package com.btg.claro.LBS.dao;

import com.btg.claro.LBS.domain.Consulta;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.IDAO;

public interface ConsultaDAO extends IDAO<Consulta>{

	public Integer conseguirNumeroDeConsultasPorMesDeUsuarioConsultante(Usuario usuario);
	
}
