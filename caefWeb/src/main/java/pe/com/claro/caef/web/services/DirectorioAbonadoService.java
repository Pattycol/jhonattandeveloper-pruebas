package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarNumTelefonicoAbonadoFilter;
import pe.com.claro.caef.web.action.filter.RegistrarPublicacionDirectorioAbonadoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultarNumTelefonicoAbonado;
import pe.com.claro.caef.web.beans.SuscribirDirectorioAbonado;

public interface DirectorioAbonadoService {
	
	public List<ConsultarNumTelefonicoAbonado> getConsultarNumTelefonicoAbonado(Usuario usuario, ConsultarNumTelefonicoAbonadoFilter consultarNumTelefonicoAbonadoFilter);
	public AuditTypes getRegistrarPublicacionDirectorioAbonado(Usuario usuario, RegistrarPublicacionDirectorioAbonadoFilter registrarPublicacionDirectorioAbonadoFilter);

}
