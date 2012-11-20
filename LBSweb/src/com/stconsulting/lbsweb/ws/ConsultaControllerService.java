/*
 * ConsultaControllerService.java
 *
 * Created on 20 de junio de 2005, 09:50 AM
 */

package com.stconsulting.lbsweb.ws;

import com.stconsulting.common.util.Constants;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;
import com.stconsulting.lbsweb.seguridad.bean.Login;
import com.stconsulting.lbsweb.seguridad.service.LoginService;
import com.stconsulting.lbsweb.consulta.bean.ParametroConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.ResultadoConsultaWeb;
import com.stconsulting.lbsweb.consulta.bean.ResultadoDetalleWeb;
import com.stconsulting.lbsweb.consulta.bean.Mobile;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author STCosulting
 */
public class ConsultaControllerService{

	protected static Logger log=null;

	/** Creates a new instance of ConsultaControllerService */
	public ConsultaControllerService(){
		log=Logger.getLogger(this.getClass());
	}

	public ResultadoConsulta consultaLBS(ParametroConsulta parametro){
		ResultadoConsulta resultado=null;
		com.stconsulting.lbsweb.consulta.service.ConsultaService serviceLBS=null;
		try{
			serviceLBS=new com.stconsulting.lbsweb.consulta.service.ConsultaService();
			resultado=new ResultadoConsulta();
			resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_INTERNO);
			resultado.setMensaje(Constants.MSJ_RES_WS_CONSULTA_ERROR_INTERNO);

			Usuario usuario=new Usuario();

			// valida parametros no nulos
			if(StringUtils.isNotEmpty(parametro.getCodFormatoRespuesta()) && StringUtils.isNotEmpty(parametro.getMobileUsuario()) && StringUtils.isNotEmpty(parametro.getPassword())){
				// valida lista de mobiles no nulo
				if(parametro.getMobiles() != null && parametro.getMobiles().length > 0){
					String resultadoUsuario=verificaUsuario(parametro.getMobileUsuario(),parametro.getPassword(),usuario);

					if(resultadoUsuario.equals(Constants.COD_USUARIO_VALIDO)){
						usuario.setUser(parametro.getMobileUsuario());
						usuario.setTelefono(parametro.getMobileUsuario());

						List<Mobile> listaMobiles=obtenerListaMobiles(parametro.getMobiles());
						ParametroConsultaWeb parametroWeb=new ParametroConsultaWeb();
						parametroWeb.setCodTipoRespuesta(parametro.getCodFormatoRespuesta());
						parametroWeb.setListaMobiles(listaMobiles);
						parametroWeb.setCodTipoConsulta(Constants.COD_TIPO_CONSULTA_WEB);
						ResultadoConsultaWeb resultadoWeb=serviceLBS.consultaSimpleLBS(usuario,parametroWeb);

						if(resultadoWeb.getCodResultado().equals(Constants.COD_RESULTADO_CONSULTA_OK)){

							ResultadoDetalleConsulta[] resultadoDetalle=obtenerDetalle(resultadoWeb.getListaResultadoDetalle());
							resultado.setListaResultadoDetalle(resultadoDetalle);
							resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_OK);
							resultado.setMensaje(Constants.MSJ_RES_WS_CONSULTA_OK);
						}
						else{
							resultado.setMensaje(resultadoWeb.getMensaje());
							if(resultadoWeb.getCodResultado().equals(Constants.COD_ERROR_CONSULTA_NO_TIENE_SALDO)){
								resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_SALDO);
							}
							else if(resultadoWeb.getCodResultado().equals(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO)){
								resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_MOBILE);
							}
							else{
								resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_INTERNO);
							}
						}
					}
					else{
						log.debug("Usuario no valido");
						resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_USUARIO);
						resultado.setMensaje(Constants.MSJ_RES_WS_CONSULTA_ERROR_USUARIO);
					}
				}
				else{
					log.debug("Lista de Mobiles vacia");
					resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_MOBILE);
					resultado.setMensaje(Constants.MSJ_RES_WS_CONSULTA_ERROR_MOBILE);
				}
			}
			else{
				log.debug("Parametros nulos");
				resultado.setCodResultado(Constants.COD_RES_WS_CONSULTA_ERROR_PARAMETRO);
				resultado.setMensaje(Constants.MSJ_RES_WS_CONSULTA_ERROR_PARAMETRO);
			}
		}
		catch(Exception e){
			log.error(e);
		}
		return resultado;
	}

	private String verificaUsuario(String mobile,String password,Usuario usuario){
		String resultadoUsuario="";
		try{

			Login login=new Login();
			LoginService loginService=new LoginService();
			login.setUser(mobile);
			login.setPassword(password);
			resultadoUsuario=loginService.verifica(usuario,true);
		}
		catch(Exception e){
		}

		return resultadoUsuario;
	}

	private List<Mobile> obtenerListaMobiles(String[] listaMobiles){
		List<Mobile> mobiles=new ArrayList<Mobile>();
		for(int i=0;i < listaMobiles.length;i++){
			if(StringUtils.isNotEmpty(listaMobiles[i])){
				Mobile mobile=new Mobile();
				mobile.setNumero(listaMobiles[i]);
				mobiles.add(mobile);
			}
		}
		return mobiles;
	}

	private ResultadoDetalleConsulta[] obtenerDetalle(List<ResultadoDetalleWeb> listaResultadoWeb){
		ResultadoDetalleConsulta[] resultado=new ResultadoDetalleConsulta[listaResultadoWeb.size()];
		for(int i=0;i < listaResultadoWeb.size();i++){
			ResultadoDetalleConsulta resultadoConsulta=new ResultadoDetalleConsulta();
			ResultadoDetalleWeb resultadoWeb=listaResultadoWeb.get(i);

			resultadoConsulta.setMobileDestino(resultadoWeb.getMobileDestino());
			resultadoConsulta.setResultado(resultadoWeb.getResultado());
			resultado[i]=resultadoConsulta;
		}
		return resultado;
	}
}
