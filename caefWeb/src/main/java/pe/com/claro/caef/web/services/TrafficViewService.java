package pe.com.claro.caef.web.services;

import java.util.List;

import pe.com.claro.caef.web.action.filter.ConsultarListTrafficViewFilter;
import pe.com.claro.caef.web.action.filter.ObtenerUrlTrafficViewFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.TrafficView;
import pe.com.claro.caef.web.beans.UrlTrafficView;

public interface TrafficViewService {
	
	public List<TrafficView> getConsultarListTrafficView(Usuario usuario, ConsultarListTrafficViewFilter consultarListTrafficViewFilter);
	public UrlTrafficView getObtenerUrlTrafficView(Usuario usuario, ObtenerUrlTrafficViewFilter obtenerUrlTrafficViewFilter);

}
