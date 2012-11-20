package pe.com.claro.caef.web.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.ConsultarNumerosTelefonicosFilter;
import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.beans.Comun;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.ConsultaNumeroTelefonico;
import pe.com.claro.caef.web.beans.ConsultaServicioClienteTotal;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.PaqueteBusinessDelegate;
import pe.com.claro.caef.web.ws.ConsultarClienteWS;
import pe.com.claro.eai.crmservices.fija.consultaclientes.ListaConsultarNumerosTelefonicos;

@Service("comunService")
public class ComunServiceImpl implements ComunService {

	@Autowired
	private ConsultarClienteWS consultaCliente;
	
	static final Logger log = Logger.getLogger(ComunServiceImpl.class);
	
	public List<ConsultaNumeroTelefonico> getConsultarNumerosTelefonicos(Usuario usuario,
			ConsultarNumerosTelefonicosFilter consultarNumerosTelefonicosFilter) {
		consultaCliente=PaqueteBusinessDelegate.getConsultarClienteWSImpl();
		List<ConsultaNumeroTelefonico> lstConsultaNumeroTelefonico = new ArrayList<ConsultaNumeroTelefonico>();
		
		consultarNumerosTelefonicosFilter = new ConsultarNumerosTelefonicosFilter();
		consultarNumerosTelefonicosFilter.setCodEstado("1");
		
		lstConsultaNumeroTelefonico = consultaCliente.consultarNumerosTelefonicos(usuario, consultarNumerosTelefonicosFilter);
		
		return lstConsultaNumeroTelefonico;
	}

	public List<ConsultaDatosMaestro> getConsultarDatosMaestros(Usuario usuario,
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		consultaCliente=PaqueteBusinessDelegate.getConsultarClienteWSImpl();
		
		List<ConsultaDatosMaestro> lstConsultaDatosMaestro = new ArrayList<ConsultaDatosMaestro>();
		
		if(consultarDatosMaestrosFilter.getCodigoCaso().equals("TIPDIDE"))
		{//TIPO DOCUMENTO DE IDENTIDAD
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("ESTCIV"))
		{//ESTADO CIVIL
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CODGEN"))
		{//SEXO
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("TIPDOM"))
		{//TIPO DOMICILIO
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CODPAI"))
		{//PAIS
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CODEST"))
		{//REGION/DEPARTAMENTO
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CODPVC"))
		{//PROVINCIA
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CODDST"))
		{//DISTRITO-COMUNA
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CODNAC"))
		{//NACIONALIDAD
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("TIPVIA"))
		{//TIPO DE VIA
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("FECFAC"))
		{//FECHA FACTURACION			
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("TELFIJ"))
		{//TELEFONO FIJO
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("TELCEL"))
		{//TELEFONO CELULAR
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CLIENTE"))
		{//CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("EMAIL"))
		{//EMAIL
			consultarDatosMaestrosFilter.setParametro1(usuario.getCodigoUsuario());
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("VALCLICAESGA"))
		{//PRIMER WS CONSULTADO PARA EL LOGIN
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("VALCLINVOUSU"))
		{//PRIMER WS CONSULTADO PARA EL NUEVO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("PLANADIC"))
		{//PLANES CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("DECOADIC"))
		{//TIPO DECO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("TPER"))
		{//TIPO DECO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CIS"))
		{//TIPO DECO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("CAD"))
		{//TIPO DECO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("OND"))
		{//TIPO DECO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else if(consultarDatosMaestrosFilter.getCodigoCaso().equals("OMS"))
		{//TIPO DECO CLIENTE
			lstConsultaDatosMaestro = consultaCliente.consultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		}
		else
		{
			lstConsultaDatosMaestro = null;			
		}
		
		
		return lstConsultaDatosMaestro;
	}
	
	public List<ConsultaServicioClienteTotal> ConsultarServiciosClienteTotal(Usuario usuario)
	{
		consultaCliente=PaqueteBusinessDelegate.getConsultarClienteWSImpl();
		
		List<ConsultaServicioClienteTotal> lstConsultaServicioClienteTotal = new ArrayList<ConsultaServicioClienteTotal>();
		
		lstConsultaServicioClienteTotal = consultaCliente.consultarServiciosClienteTotal(usuario);
		
		return lstConsultaServicioClienteTotal;
	}

}
