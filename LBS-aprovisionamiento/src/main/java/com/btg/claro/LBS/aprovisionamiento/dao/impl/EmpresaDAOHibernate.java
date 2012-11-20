package com.btg.claro.LBS.aprovisionamiento.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.btg.claro.LBS.aprovisionamiento.dao.EmpresaDAO;
import com.btg.claro.LBS.aprovisionamiento.util.Constantes;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.dao.dao.DAO;

@Repository("EmpresaDAO")
public class EmpresaDAOHibernate extends DAO<Empresa> implements EmpresaDAO {

	@SuppressWarnings("unchecked")
	public List<Empresa> getEmpresasActivas() {
		String sql = "SELECT new Empresa(e.id,e.ruc,e.razonSocial,e.vista) FROM Empresa e WHERE e.estado=:estado ORDER BY e.razonSocial";
		Query q = em.createQuery(sql);
		q.setParameter("estado", Constantes.ESTADO_ACTIVO);
		return q.getResultList();
	}

	public Empresa buscarPorRUC(String ruc) {
		String sql = "SELECT e FROM Empresa e WHERE e.ruc=:ruc AND e.estado<>:estado";
		Query q = em.createQuery(sql);
		q.setParameter("ruc", ruc);
		q.setParameter("estado", Constantes.ESTADO_ELIMINADO);
		try {
			return (Empresa) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> buscarEmpresas(String query, Usuario u) {

		String sql = " SELECT new Empresa(e.id,e.ruc,e.razonSocial,e.estado) FROM Empresa e "
				+ " WHERE (e.ruc LIKE :query OR  lower(e.razonSocial) LIKE :query) AND e.estado<>:estado ";

		if (u.getRol().getCodigo().trim().toLowerCase()
				.equals(Constantes.ROL_SUPER_USUARIO.toLowerCase())) {
			sql += " ";
		} else {
			sql += " AND e.vista = false ";
		}
		sql += " ORDER BY e.ruc";
		Query q = em.createQuery(sql);
		q.setParameter("query", query);
		q.setParameter("estado", Constantes.ESTADO_ELIMINADO);

		return q.getResultList();
	}

}