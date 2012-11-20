package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarListTrafficViewFilter;
import pe.com.claro.caef.web.action.filter.ObtenerUrlTrafficViewFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.TrafficView;
import pe.com.claro.caef.web.beans.UrlTrafficView;
import pe.com.claro.caef.web.services.TrafficViewService;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;

@Service("trafficViewService")
public class TrafficViewServiceImpl implements TrafficViewService {

	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	static final Logger log = Logger.getLogger(TrafficViewServiceImpl.class);
	
	public List<TrafficView> getConsultarListTrafficView(Usuario usuario,
			ConsultarListTrafficViewFilter consultarListTrafficViewFilter) {
		// TODO Auto-generated method stub
		List<TrafficView> lstTv = new ArrayList<TrafficView>();
		
		lstTv = consultaCliente.consultarListTrafficView(usuario);
		
		/*TrafficView tv = new TrafficView();
		tv.setCodCID("2349887");
		tv.setDescripcionEstadoCid("Habilitado");
		
		tv.setFecInst("20/01/2012");
		tv.setNomDirecSucursal("");
		tv.setNomProducto("Internet");
		tv.setNomSucursal("");
		lstTv.add(tv);*/
		
		return lstTv;
	}

	public UrlTrafficView getObtenerUrlTrafficView(Usuario usuario,
			ObtenerUrlTrafficViewFilter obtenerUrlTrafficViewFilter) {
		
		UrlTrafficView utv = new UrlTrafficView();
		
		utv.setUrlTrafficView(consultaCliente.obtenerUrlTrafficView(usuario, obtenerUrlTrafficViewFilter).getUrlTrafficView());
		
		return utv;
	}

}
