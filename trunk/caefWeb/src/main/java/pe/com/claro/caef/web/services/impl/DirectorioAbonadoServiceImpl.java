package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarNumTelefonicoAbonadoFilter;
import pe.com.claro.caef.web.action.filter.RegistrarPublicacionDirectorioAbonadoFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.AuditTypes;
import pe.com.claro.caef.web.beans.ConsultarNumTelefonicoAbonado;
import pe.com.claro.caef.web.beans.SuscribirDirectorioAbonado;
import pe.com.claro.caef.web.services.DirectorioAbonadoService;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.caef.web.ws.TransaccionClientesWS;

@Service("directorioAbonadoService")
public class DirectorioAbonadoServiceImpl implements DirectorioAbonadoService {

	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	@Autowired
	private TransaccionClientesWS transaccionClientesWS;
	
	static final Logger log = Logger.getLogger(DirectorioAbonadoServiceImpl.class);
	
	public List<ConsultarNumTelefonicoAbonado> getConsultarNumTelefonicoAbonado(Usuario usuario,
			ConsultarNumTelefonicoAbonadoFilter consultarNumTelefonicoAbonadoFilter) {
		// TODO Auto-generated method stub
		
		List<ConsultarNumTelefonicoAbonado> lstSda = new ArrayList<ConsultarNumTelefonicoAbonado>();
		consultarNumTelefonicoAbonadoFilter = new ConsultarNumTelefonicoAbonadoFilter();
		consultarNumTelefonicoAbonadoFilter.setCodigoEstado("1");
		lstSda = consultaCliente.consultarNumTelefonicoAbonado(usuario, consultarNumTelefonicoAbonadoFilter);
		
		return lstSda;
	}

	public AuditTypes getRegistrarPublicacionDirectorioAbonado(Usuario usuario,
			RegistrarPublicacionDirectorioAbonadoFilter registrarPublicacionDirectorioAbonadoFilter) {
		
		AuditTypes at = new AuditTypes();
		for(int i = 0; i < registrarPublicacionDirectorioAbonadoFilter.getLstDirectorioAbonado().size(); i++)
		{
			RegistrarPublicacionDirectorioAbonadoFilter rdaf = new RegistrarPublicacionDirectorioAbonadoFilter();
			rdaf.setNumero(registrarPublicacionDirectorioAbonadoFilter.getLstDirectorioAbonado().get(i).getNumero());
			rdaf.setFlag(registrarPublicacionDirectorioAbonadoFilter.getLstDirectorioAbonado().get(i).getFlag());
			at = transaccionClientesWS.registrarPublicacionDirectorioAbonado(usuario, rdaf);
		}
		
		return at;
	}

}
