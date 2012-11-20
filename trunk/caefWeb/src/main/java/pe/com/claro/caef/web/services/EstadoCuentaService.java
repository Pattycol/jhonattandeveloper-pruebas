package pe.com.claro.caef.web.services;

import java.util.List;
import pe.com.claro.caef.web.action.filter.ConsultarCabeceraEstadoCuentaFilter;
import pe.com.claro.caef.web.action.filter.ConsultarEstadoCuentaFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.DetalleEstadoCuenta;
import pe.com.claro.caef.web.beans.EstadoCuenta;

public interface EstadoCuentaService {
	
	public EstadoCuenta getConsultarCabeceraEstadoCuenta(Usuario usuario);

}
