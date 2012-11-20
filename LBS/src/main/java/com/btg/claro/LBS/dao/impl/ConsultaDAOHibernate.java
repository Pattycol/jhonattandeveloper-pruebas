package com.btg.claro.LBS.dao.impl;

import java.util.Calendar;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.dao.ConsultaDAO;
import com.btg.claro.LBS.domain.Consulta;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.util.Constantes;

@Repository("ConsultaDAO")
public class ConsultaDAOHibernate extends DAO<Consulta> implements ConsultaDAO{

	public Integer conseguirNumeroDeConsultasPorMesDeUsuarioConsultante(Usuario usuario) {
		String sql="select count(c.consultante) from Consulta c where c.consultante.id=:idUsuario and c.codigoError=:error and extract(YEAR from c.fechaConsulta)=:Anio AND extract(MONTH from c.fechaConsulta)=:Mes";
		Query query=em.createQuery(sql);
		query.setParameter("idUsuario", usuario.getId());
		query.setParameter("error",Constantes.OK);
		Calendar hoy=Calendar.getInstance();
		query.setParameter("Anio",hoy.get(Calendar.YEAR));
		query.setParameter("Mes",hoy.get(Calendar.MONTH)+1);
		Long resultado=(Long) query.getSingleResult();
		return resultado.intValue();
	}
	
}
