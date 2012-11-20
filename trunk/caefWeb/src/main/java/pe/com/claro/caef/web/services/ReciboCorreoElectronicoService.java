package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarGrupoFactRecibosFilter;
import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ReciboCorreoElectronico;

public interface ReciboCorreoElectronicoService {

	public List<ReciboCorreoElectronico> getConsultarGrupoFactRecibos(Usuario usuario, ConsultarGrupoFactRecibosFilter consultarGrupoFactRecibosFilter);
	public void getRegistrarActivacionReciboCorreoElectronico(Usuario usuario, RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronico);
}
