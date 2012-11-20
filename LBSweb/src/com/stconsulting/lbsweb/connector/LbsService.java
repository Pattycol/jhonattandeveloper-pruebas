/*
 * LbsService.java
 *
 * Created on 27 de mayo de 2005, 02:37 PM
 */

package com.stconsulting.lbsweb.connector;

import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Helper;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsws.ws.LBSConsultaService;
import com.stconsulting.lbsws.ws.LBSConsultaServiceService;
import com.stconsulting.lbsws.ws.ParametroConsulta;
import com.stconsulting.lbsws.ws.ResultadoConsulta;
import com.stconsulting.lbsws.ws.ResultadoDetalleConsulta;

import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author STconsulting
 */
public class LbsService{
	protected Logger log=null;

	/** Creates a new instance of Billing */
	public LbsService(){
		log=Logger.getLogger(this.getClass());
	}

	public ResultadoConsultaWeb consultaLBS(Usuario usuario,ParametroConsultaWeb parametro) throws ServiceException{
		ResultadoConsultaWeb resultadoWeb=new ResultadoConsultaWeb();
		try{
			String rutaWSDL=Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_URL_WS_LBS);// "http://localhost:8080/LBSws/services/LBSConsultaService";
			URL url=new URL(rutaWSDL);
			String wsName=Helper.getProperty(Constants.WEBSERVICE_BUNDLE,Constants.PROPERTY_NAME_WS_LBS);// "LBSConsultaService";

			LBSConsultaServiceService serviceLocator=new LBSConsultaServiceService(url,wsName);
			/*serviceLocator.setLBSConsultaServiceEndpointAddress(url);
			serviceLocator.setLBSConsultaServiceWSDDServiceName(wsName);*/
			LBSConsultaService service=serviceLocator.getLBSConsultaServicePort();

			// parametros de la consulta al servicio web
			ParametroConsulta parametroWS=new ParametroConsulta();
			ResultadoConsulta resultadoWS=null;

			String telefono=usuario.getTelefono();
			parametroWS.setMobileOrigen(telefono);
			parametroWS.setCodTipoConsulta(parametro.getCodTipoConsulta());
			log.debug("Antes de invocar al servicio web del LBS, mobiles :" + parametro.getListaMobiles().size());
			for(Mobile mobile : parametro.getListaMobiles()){
				parametroWS.getListaMobileDestino().add(mobile.getNumero());
			}
			// invocamos al servicio web
			log.debug("Invocando al servicio web del LBS,lista :" + parametroWS.getListaMobileDestino().size());
			resultadoWS=service.consultaLBS(parametroWS);
			log.debug("Invoco Satisfactoriamente al Servicio Web LBS");
			// retorno la informacion en la clase ResultadoConsultaWeb
			resultadoWeb.setCodResultado(resultadoWS.getCodResultado());
			resultadoWeb.setMensaje(resultadoWS.getMensaje());
			resultadoWeb.setMobileOrigen(usuario.getTelefono());
			List<ResultadoDetalleWeb> listaResultadoDetalle=new ArrayList<ResultadoDetalleWeb>();
			for(ResultadoDetalleConsulta resultadoDetalleWS : resultadoWS.getListaResultadoDetalle()){
				ResultadoDetalleWeb resultadoDetalleWeb=new ResultadoDetalleWeb();
				resultadoDetalleWeb.setCodOperacion(resultadoDetalleWS.getCodOperacion());
				resultadoDetalleWeb.setMobileDestino(resultadoDetalleWS.getMobileDestino());
				listaResultadoDetalle.add(resultadoDetalleWeb);
			}
			resultadoWeb.setListaResultadoDetalle(listaResultadoDetalle);
		}
		catch(Exception e){
			log.error(e.getMessage(),e);
			throw new ServiceException(e.getMessage());
		}
		return resultadoWeb;
	}
}
