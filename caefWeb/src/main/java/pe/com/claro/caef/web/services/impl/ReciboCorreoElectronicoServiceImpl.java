package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarGrupoFactRecibosFilter;
import pe.com.claro.caef.web.action.filter.RegistrarActivacionReciboCorreoElectronicoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.ReciboCorreoElectronico;
import pe.com.claro.caef.web.services.ReciboCorreoElectronicoService;
import pe.com.claro.caef.web.ws.ConsultarRecibosWS;
import pe.com.claro.caef.web.ws.TransaccionClientesWS;

@Service("reciboCorreoElectronicoService")
public class ReciboCorreoElectronicoServiceImpl implements
		ReciboCorreoElectronicoService {

	@Autowired
	private ConsultarRecibosWS consultarRecibosWS;
	
	@Autowired
	private TransaccionClientesWS transaccionClientesWS;
	
	static final Logger log = Logger.getLogger(ReciboCorreoElectronicoServiceImpl.class);
	
	public List<ReciboCorreoElectronico> getConsultarGrupoFactRecibos(Usuario usuario,
			ConsultarGrupoFactRecibosFilter consultarGrupoFactRecibosFilter) {
		// TODO Auto-generated method stub
		
		List<ReciboCorreoElectronico> lstReciboCorreoElectronico = new ArrayList<ReciboCorreoElectronico>();
		
		/*ReciboCorreoElectronico rce = new ReciboCorreoElectronico();
		rce.setCodGrupo("23");
		rce.setFlgActivo("false");
		rce.setNomServicio("aaaa");
		rce.setValCorreoElectronico("ronald_k7121@hotmail.com");*/
		
		lstReciboCorreoElectronico = consultarRecibosWS.consultarGrupoFactRecibos(usuario);
		//lstReciboCorreoElectronico.add(rce);
		
		return lstReciboCorreoElectronico;
	}

	public void getRegistrarActivacionReciboCorreoElectronico(Usuario usuario,
			RegistrarActivacionReciboCorreoElectronicoFilter registrarActivacionReciboCorreoElectronico) {
		
		transaccionClientesWS.registrarActivacionReciboCorreoElectronico(usuario, registrarActivacionReciboCorreoElectronico);

	}

}
