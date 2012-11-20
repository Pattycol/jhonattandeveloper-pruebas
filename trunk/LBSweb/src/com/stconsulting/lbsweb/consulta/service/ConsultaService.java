/*
 * ConsultaService.java
 *
 * Created on 27 de mayo de 2005, 01:14 PM
 */

package com.stconsulting.lbsweb.consulta.service;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.persistence.TransactionSysException;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.common.persistence.DAOException;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Parameters;

import com.stconsulting.lbsweb.consulta.persistence.*;
import com.stconsulting.lbsweb.consulta.bean.*;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;

import com.stconsulting.lbsweb.connector.LbsService;

import java.util.*;

import org.apache.log4j.Logger;

/**
 * 
 * @author STconsulting
 */
public class ConsultaService extends GenericService{
	protected static Logger log=null;

	/** Creates a new instance of ConsultaService */
	public ConsultaService(){
		log=Logger.getLogger(this.getClass());
	}

	public ResultadoConsultaWeb consultaSimpleLBS(Usuario usuario,ParametroConsultaWeb parametro){// throws
		// ServiceException
		// {
		ResultadoConsultaWeb resultado=null;
		// BillingService billingService=null;
		LbsService lbsService=null;
		TransactionContext tx=null;
		try{
			log.debug("Entrando a consultaSimpleLBS : " + new java.util.Date());
			String mobileOrigen=usuario.getTelefono();

			// instanciamos todos los objetos

			// billingService=new BillingService();
			lbsService=new LbsService();
			resultado=new ResultadoConsultaWeb();
			resultado.setMobileOrigen(mobileOrigen);
			resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
			// se crea la transacci�n para validar numeros
			tx=new TransactionContext();
			tx.begin();
			ConsultaDAO consultaDAO=new ConsultaDAO(tx);
			CobroDAO cobroDAO=new CobroDAO(tx);
			// Parameters par=new Parameters();

			String resultadoValidacionNumeros=validaNumeros(usuario,parametro.getListaMobiles(),consultaDAO);
			log.debug("luego de validar numeros asociados : " + resultadoValidacionNumeros);
			if(resultadoValidacionNumeros.equals(Constants.COD_NUMERO_VALIDO)){
				// String mobileConsultaBilling="0" + mobileOrigen;
				double montoSaldo=Double.parseDouble(Parameters.getParameter(Constants.KEY_MONTO_CONSULTA));
				// se multiplica por el total de numeros x consultar
				montoSaldo*=parametro.getListaMobiles().size();
				// Comentado Momentaneamente Validacion Saldo
				log.debug("VALIDANDO SALDO ******************");
				// String
				// resultadoBilling=billingService.consultaSaldo(mobileConsultaBilling,montoSaldo);

				// if
				// (resultadoBilling.equals(Constants.COD_RESULTADO_BILLING_TIENE_SALDO)){
				//if(true){
					log.debug("usuario tiene saldo suficiente");
					// ya puedo invocar al LBS
					resultado=lbsService.consultaLBS(usuario,parametro);
					// proceso la informacion que retorna el servicio web,
					// genero el cobro
					procesaResultadoLBS(parametro,resultado,consultaDAO,cobroDAO);

				/*}
				else{
					resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_NO_TIENE_SALDO);
					resultado.setMensaje(Constants.MENSAJE_NO_TIENE_SALDO);
				}*/

			}
			else{
				resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO);
				int ind=Integer.parseInt(resultadoValidacionNumeros);
				resultado.setMensaje("El sgte. número no es válido : " + parametro.getListaMobiles().get(ind).getNumero());
			}

			// se cierra la transaccion
			tx.commit();
			tx.close();
			tx=null;

		}
		catch(Exception e){
			log.error("ERROR :" + e.getMessage(),e);
			resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
			resultado.setMensaje(Constants.MENSAJE_ERROR_INTERNO);
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			// throw new ServiceException(e);
		}
		return resultado;
	}

	public ResultadoConsultaWeb consultaHistoricaLBS(Usuario usuario,ParametroConsultaWeb parametro){// throws
		// ServiceException
		// {
		ResultadoConsultaWeb resultado=null;
		// BillingService billingService=null;
		// ConsultaService consultaService=null;
		TransactionContext tx=null;
		try{
			String mobileOrigen=usuario.getTelefono();
			resultado=new ResultadoConsultaWeb();
			resultado.setMobileOrigen(mobileOrigen);
			resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
			// se crea la transacci�n para validar numeros
			tx=new TransactionContext();
			tx.begin();
			ConsultaDAO consultaDAO=new ConsultaDAO(tx);

			String resultadoValidacionNumeros=validaNumeros(usuario,parametro.getListaMobiles(),consultaDAO);
			log.debug("luego de validar numeros asociados : " + resultadoValidacionNumeros);
			if(resultadoValidacionNumeros.equals(Constants.COD_NUMERO_VALIDO)){
				List<Localizacion> listaLocalizacionHist=consultaDAO.cargaListaLocalizacionHistorica(usuario.getTelefono(),parametro);
				if(listaLocalizacionHist != null)
					log.debug("Lista historica :" + listaLocalizacionHist.size());
				List<ResultadoDetalleWeb> listaResultadodetalle=obtenerListaHistorica(listaLocalizacionHist);
				resultado.setListaResultadoDetalle(listaResultadodetalle);
				resultado.setCodResultado(Constants.COD_RESULTADO_CONSULTA_OK);
			}
			else{
				resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO);
				int ind=Integer.parseInt(resultadoValidacionNumeros);
				resultado.setMensaje("El sgte. n�mero no es v�lido : " + parametro.getListaMobiles().get(ind).getNumero());
			}
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error("ERROR :" + e.getMessage());
			resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
			resultado.setMensaje(Constants.MENSAJE_ERROR_INTERNO);
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			// throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * retorna el indice (desde cero) del nro invalido � codigo de exito
	 * Constants.COD_NUMERO_VALIDO
	 */
	public String validaNumeros(Usuario usuario,List<Mobile> listaNumeros,ConsultaDAO consultaDAO) throws ServiceException{
		String codResultado=Constants.COD_NUMERO_VALIDO;
		String resultValidacion=null;
		try{
			for(int i=0;i < listaNumeros.size();i++){
				Mobile mobile=listaNumeros.get(i);
				if(mobile.isValidNumber()){
					resultValidacion=consultaDAO.validaNumero(mobile.getNumero(),usuario);
					log.debug("validando nro :" + mobile.getNumero() + " : " + resultValidacion);
					if(!resultValidacion.equals(Constants.COD_NUMERO_VALIDO)){
						// se retorna el indice del nro invalido
						codResultado=i + "";
					}
				}
				else{
					// se retorna el indice del nro invalido
					codResultado=i + "";
				}
			}

		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}
		return codResultado;
	}

	private void procesaResultadoLBS(ParametroConsultaWeb parametro,ResultadoConsultaWeb resultado,ConsultaDAO consultaDAO,CobroDAO cobroDAO) throws ServiceException{
		log.debug("Ejecutando metodo : procesaResultadoLBS");
		List<Localizacion> listaLocalizacion=null;
		// ArrayList listaValidaDeCobro=null;
		try{
			List<Integer> listaAux=new ArrayList<Integer>(); // arreglo de String
			// con codigos de
			// localizacion
			for(ResultadoDetalleWeb resultadoDetalle : resultado.getListaResultadoDetalle()){
				listaAux.add(resultadoDetalle.getCodOperacion());
			}
			listaLocalizacion=consultaDAO.cargaListaLocalizacion(listaAux);
			log.debug("Luego de cargar lista de localizacion : " + (listaLocalizacion != null ? listaLocalizacion.size() + "" : "null"));
			
			
			/*String codResultadoCobro=registraCobroConsulta(listaLocalizacion,cobroDAO);
			log.debug("se proceso el cobro : " + codResultadoCobro);
			if(codResultadoCobro.equals(Constants.OK)){*/
				// el proceso no tuvo problemas
				resultado.setCodResultado(Constants.COD_RESULTADO_CONSULTA_OK);

				log.debug("Formando mensajes resultado");
				for(ResultadoDetalleWeb resultadoDetalle : resultado.getListaResultadoDetalle()){
					log.debug("Analizando detalle resultado : " + resultadoDetalle.getCodOperacion());
					Localizacion localizacion=null;
					log.debug("ListaLocalizacion.size() = " + listaLocalizacion.size());
					for(int i=0;i<listaLocalizacion.size();i++){
						Localizacion aux=listaLocalizacion.get(i);
						if(aux!=null){
							if(aux.getCodLocalizacion().equals(resultadoDetalle.getCodOperacion())){
								localizacion=listaLocalizacion.remove(i);
								break;
							}
						}
					}
					//listaLocalizacion.remove(aux); // elimina de la lista, por
					// performance
					String mensaje="";
					if(localizacion != null){
						if(validaLocalizacion(localizacion)){
							// agregado por Germán Enríquez
							resultadoDetalle.setLocalizacion(localizacion);
							if(parametro.getCodTipoRespuesta().equals(Constants.COD_TIPO_RPTA_PTOREF)){
								mensaje=localizacion.getDireccion();
								if(localizacion.getDireccion() == null || localizacion.getDireccion().trim().equals("")){
									mensaje=localizacion.getMensaje();
								}
							}
							else if(parametro.getCodTipoRespuesta().equals(Constants.COD_TIPO_RPTA_LATLON)){
								mensaje="LATITUD: " + localizacion.getLatitud() + " LONGITUD: " + localizacion.getLongitud();
							}
							else if(parametro.getCodTipoRespuesta().equals(Constants.COD_TIPO_RPTA_UTM)){
								mensaje="UTM,DEFINIR";
							}
						}
						else{
							mensaje=localizacion.getMensaje();
						}
					}
					resultadoDetalle.setResultado(mensaje);

					// si tipo consulta es TAREA (4), guarda en tabla
					// LOCALIZACIONXTAREA
					if(parametro.getCodTipoConsulta().equals(Constants.COD_TIPO_CONSULTA_TAREA)){
						consultaDAO.insertaLocalizacionXTarea(resultadoDetalle.getCodOperacion(),parametro.getCodTarea());
					}
				}
			/*}
			else{
				// error al generar el cobro
				resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
				resultado.setMensaje(Constants.MENSAJE_COBRO_INVALIDO);
			}*/

		}
		catch(Exception e){
			log.error(e.getMessage(),e);
			throw new ServiceException(e);
		}
	}

	private String registraCobroConsulta(List<Localizacion> listaLocalizacion,CobroDAO cobroDAO) throws ServiceException{
		String codResultado=Constants.OK;
		try{
			List<Localizacion> listaValidaDeCobro=obtenerListaValidaCobro(listaLocalizacion);
			for(Localizacion localizacion : listaValidaDeCobro){
				cobroDAO.insertaCobro(localizacion.getCodLocalizacion(),Constants.COD_ESTADO_COBRO_PENDIENTE);
			}
		}
		catch(DAOException e){
			codResultado=Constants.ERROR;
			log.error(e);
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}

		return codResultado;
	}

	private List<Localizacion> obtenerListaValidaCobro(List<Localizacion> listaLocalizacion){
		List<Localizacion> listaValida=new ArrayList<Localizacion>();
		for(Localizacion localizacion : listaLocalizacion){
			if(validaLocalizacion(localizacion))
				listaValida.add(localizacion);
		}
		return listaValida;
	}

	private boolean validaLocalizacion(Localizacion localizacion){
		boolean valido=false;

		if(localizacion.getCodCelda() != null && !localizacion.getCodCelda().equals(Constants.COD_CELDA_LOCALIZACION_INVALIDO_1))
			valido=true;

		return valido;
	}

	private List<ResultadoDetalleWeb> obtenerListaHistorica(List<Localizacion> listaLocalizacionHist){
		List<ResultadoDetalleWeb> listaResultado=new ArrayList<ResultadoDetalleWeb>();
		for(Localizacion localizacion : listaLocalizacionHist){
			ResultadoDetalleWeb resultado=new ResultadoDetalleWeb();
			resultado.setCodOperacion(localizacion.getCodLocalizacion());
			resultado.setResultado((localizacion.getDireccion() == null || localizacion.getDireccion().equals("") ? localizacion.getMensaje() : localizacion.getDireccion()));
			resultado.setFechaConsulta(localizacion.getFecha());
			resultado.setMobileDestino(localizacion.getMobileDestino());
			if(localizacion.getTipoConsulta().equals(Constants.COD_TIPO_CONSULTA_TAREA))
				resultado.setTipoConsulta(Constants.DESC_TIPO_CONSULTA_TAREA_HIST);
			else if(localizacion.getTipoConsulta().equals(Constants.COD_TIPO_CONSULTA_WEB))
				resultado.setTipoConsulta(Constants.DESC_TIPO_CONSULTA_WEB_HIST);
			listaResultado.add(resultado);
		}
		return listaResultado;
	}

	public String generaSqlMapa(Object obj) throws ServiceException{
		String resultSql=Constants.ERROR;
		try{
			if(obj instanceof ResultadoConsultaWeb){
				resultSql=generaSqlMapaConsulta((ResultadoConsultaWeb) obj);
			}
			else if(obj instanceof Tarea){
				resultSql=generaSqlMapaTarea((Tarea) obj);
			}
			else if(obj instanceof ParametroConsultaWeb){
				resultSql=generaSqlMapaHistorico((ParametroConsultaWeb) obj);
			}
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}
		return resultSql;
	}

	public ResultadoReporteWeb generaReporteLBS(Usuario usuario,ParametroConsultaWeb parametro){// throws
		// ServiceException
		// {
		ResultadoReporteWeb resultado=null;
		//BillingService billingService=null;
		//ConsultaService consultaService=null;
		TransactionContext tx=null;
		try{
			String mobileOrigen=usuario.getTelefono();
			resultado=new ResultadoReporteWeb();
			resultado.setMobileOrigen(mobileOrigen);
			resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
			resultado.setFechaInicio(parametro.getFechaInicio());
			resultado.setFechaFin(parametro.getFechaFin());
			// se crea la transacci�n para validar numeros
			tx=new TransactionContext();
			tx.begin();
			ReporteDAO reporteDAO=new ReporteDAO(tx);
			ConsultaDAO consultaDAO=new ConsultaDAO(tx);

			String resultadoValidacionNumeros=validaNumeros(usuario,parametro.getListaMobiles(),consultaDAO);
			log.debug("luego de validar numeros asociados : " + resultadoValidacionNumeros);
			if(resultadoValidacionNumeros.equals(Constants.COD_NUMERO_VALIDO)){

				List<ResultadoDetalleReporteWeb> listaDetalle=reporteDAO.reporteLocalizacion(usuario.getTelefono(),parametro);

				resultado.setListaResultadoDetalle(listaDetalle);
				resultado.setCodResultado(Constants.COD_RESULTADO_CONSULTA_OK);
			}
			else{
				resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_NUMERO_INVALIDO);
				int ind=Integer.parseInt(resultadoValidacionNumeros);
				resultado.setMensaje("El sgte. numero no es valido : " + parametro.getListaMobiles().get(ind).getNumero());
			}
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error(e);
			resultado.setCodResultado(Constants.COD_ERROR_CONSULTA_INTERNO);
			resultado.setMensaje(Constants.MENSAJE_ERROR_INTERNO);
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			// throw new ServiceException(e);
		}
		return resultado;
	}

	private String generaSqlMapaConsulta(ResultadoConsultaWeb resultado){
		String resultSql=Constants.ERROR;
		StringBuffer sql=new StringBuffer("SELECT l.id_consulta as codLocalizacion,");
		sql.append("consultante.numero as mobileOrigen,");
		sql.append("consultado.numero as mobileDestino,");
		sql.append("l.fecha_consulta as fechaRegistro,");
		sql.append("l.resultado as mensaje,");
		sql.append("c.id_celda as codCelda,");
		sql.append("c.direccion as direccion,");
		sql.append("c.x as ejeX,");
		sql.append("c.y as ejeY,");
		sql.append(" 0 AS codTarea ");
		sql.append("FROM consulta l ");
		sql.append("INNER JOIN usuario consultante ON l.consultante=consultante.id_usuario ");
		sql.append("INNER JOIN usuario consultado ON l.consultado=consultado.id_usuario ");
		sql.append("INNER JOIN celda c ON l.id_celda=c.id_celda ");
		//sql.append("INNER JOIN consulta_por_tarea cpt ON cpt.id_consulta=l.idconsulta ");
		sql.append("WHERE l.id_consulta IN(");
		boolean primero=true;
		for(ResultadoDetalleWeb res : resultado.getListaResultadoDetalle()){
			if(!primero)
				sql.append(","+res.getCodOperacion());
			else{
				sql.append(res.getCodOperacion());
				primero=false;
			}
		}
		sql.append(") ");
		sql.append("ORDER BY 2,3,7 ASC");
		resultSql=sql.toString();
		/*try{
			StringBuffer sql=new StringBuffer("");
			String strBD_LBS=Helper.getSchema(Constants.BD_LBS);
			sql=sql.append("SELECT L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino,");
			sql=sql.append(" L.LOCAD_FECHA_REGISTRO AS fechaRegistro,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" C.CELDV_X as ejeX,");
			sql=sql.append(" C.CELDV_Y as ejeY,");
			sql=sql.append(" IFNULL(T.TAREAI_ID,0) AS codTarea");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_LOCALIZACION L");
			sql=sql.append(" LEFT JOIN " + strBD_LBS + ".LBST_CELDA C ON L.LOCAC_CELL_ID = C.CELDC_CODIGO");
			sql=sql.append(" LEFT JOIN LBS.LBST_LOCALIZACIONXTAREA T ON L.LOCAI_CODIGO = T.LOCAI_CODIGO");
			sql=sql.append(" WHERE C.CELDV_X IS NOT NULL");
			// AND L.LOCAI_CODIGO IN (74175,75145,75146,73986)
			if(resultado.getListaResultadoDetalle().size() > 0){
				sql=sql.append(" AND L.LOCAI_CODIGO IN (");
				for(int i=0;i < resultado.getListaResultadoDetalle().size();i++){
					ResultadoDetalleWeb res=resultado.getListaResultadoDetalle().get(i);
					sql=sql.append(res.getCodOperacion() + ",");
				}

				sql=sql.deleteCharAt(sql.length() - 1);
				sql=sql.append(")");
			}

			sql=sql.append(" ORDER BY 2,3,7 ASC");

			resultSql=sql.toString();
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}*/
		return resultSql;
	}
	
	

	private String generaSqlMapaTarea(Tarea tarea){
		String resultSql=Constants.ERROR;
		StringBuffer sql=new StringBuffer("SELECT l.id_consulta as codLocalizacion,");
		sql.append("consultante.numero as mobileOrigen,");
		sql.append("consultado.numero as mobileDestino,");
		sql.append("l.fecha_consulta as fechaRegistro,");
		sql.append("l.resultado as mensaje,");
		sql.append("c.id_celda as codCelda,");
		sql.append("c.direccion as direccion,");
		sql.append("c.x as ejeX,");
		sql.append("c.y as ejeY, ");
		sql.append("cpt.id_tarea AS codTarea ");
		sql.append("FROM consulta l ");
		sql.append("INNER JOIN usuario consultante ON l.consultante=consultante.id_usuario ");
		sql.append("INNER JOIN usuario consultado ON l.consultado=consultado.id_usuario ");
		sql.append("INNER JOIN celda c ON l.id_celda=c.id_celda ");
		sql.append("INNER JOIN consulta_por_tarea cpt ON cpt.id_consulta=l.id_consulta ");
		sql.append("WHERE cpt.id_tarea="+tarea.getIdTarea());
		sql.append(" ORDER BY 2,3,7 ASC");
		resultSql=sql.toString();
		/*try{
			StringBuffer sql=new StringBuffer("");
			String strBD_LBS=Helper.getSchema(Constants.BD_LBS);
			sql=sql.append("SELECT L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino,");
			sql=sql.append(" L.LOCAD_FECHA_REGISTRO AS fechaRegistro,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" C.CELDV_X as ejeX,");
			sql=sql.append(" C.CELDV_Y as ejeY,");
			sql=sql.append(" IFNULL(T.TAREAI_ID,0) AS codTarea");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_LOCALIZACION L");
			sql=sql.append(" LEFT JOIN " + strBD_LBS + ".LBST_CELDA C ON L.LOCAC_CELL_ID = C.CELDC_CODIGO");
			sql=sql.append(" LEFT JOIN LBS.LBST_LOCALIZACIONXTAREA T ON L.LOCAI_CODIGO = T.LOCAI_CODIGO");
			sql=sql.append(" WHERE C.CELDV_X IS NOT NULL AND T.TAREAI_ID = " + tarea.getIdTarea());
			sql=sql.append(" ORDER BY 2,3,7 ASC");
			resultSql=sql.toString();
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}*/
		return resultSql;
	}

	private String generaSqlMapaHistorico(ParametroConsultaWeb parametro){
		String resultSql=Constants.ERROR;
		StringBuffer sql=new StringBuffer("SELECT l.id_consulta as codLocalizacion,");
		sql.append("consultante.numero as mobileOrigen,");
		sql.append("consultado.numero as mobileDestino,");
		sql.append("l.fecha_consulta as fechaRegistro,");
		sql.append("l.resultado as mensaje,");
		sql.append("c.id_celda as codCelda,");
		sql.append("c.direccion as direccion,");
		sql.append("c.x as ejeX,");
		sql.append("c.y as ejeY,");
		sql.append(" cpt.id_tarea AS codTarea ");
		sql.append("FROM consulta l ");
		sql.append("INNER JOIN usuario consultante ON l.consultante=consultante.id_usuario ");
		sql.append("INNER JOIN usuario consultado ON l.consultado=consultado.id_usuario ");
		sql.append("INNER JOIN celda c ON l.id_celda=c.id_celda ");
		sql.append("INNER JOIN consulta_por_tarea cpt ON cpt.id_consulta=l.id_consulta ");
		sql.append("WHERE consultate.numero="+parametro.getMobileOrigen()+" AND ");
		sql.append("(l.tipo="+Constants.DESC_TIPO_CONSULTA_TAREA+" OR l.tipo="+Constants.DESC_TIPO_CONSULTA_WEB+") AND ");
		sql.append("consultado.numero IN(");
		boolean primero=true;
		for(Mobile mob : parametro.getListaMobiles()){
			if(!primero)
				sql.append(","+mob.getNumero());
			else{
				sql.append(mob.getNumero());
				primero=false;
			}
		}
		sql.append(") AND ");
		sql.append("l.fecha_consulta BETWEEN "+parametro.getFechaInicio()+" AND "+parametro.getFechaFin());
		sql.append(" ORDER BY 2,3 ASC");
		resultSql=sql.toString();
		/*try{
			StringBuffer sql=new StringBuffer("");
			String strBD_LBS=Helper.getSchema(Constants.BD_LBS);
			sql=sql.append("SELECT L.LOCAI_CODIGO as codLocalizacion,");
			sql=sql.append(" L.LOCAV_TELEFONO_DESTINO as mobileDestino,");
			sql=sql.append(" L.LOCAD_FECHA_REGISTRO AS fechaRegistro,");
			sql=sql.append(" L.LOCAV_TELEFONO_ORIGEN as mobileOrigen,");
			sql=sql.append(" C.CELDV_X as ejeX,");
			sql=sql.append(" C.CELDV_Y as ejeY,");
			sql=sql.append(" IFNULL(T.TAREAI_ID,0) AS codTarea");
			sql=sql.append(" FROM " + strBD_LBS + ".LBST_LOCALIZACION L");
			sql=sql.append(" LEFT JOIN " + strBD_LBS + ".LBST_CELDA C ON L.LOCAC_CELL_ID = C.CELDC_CODIGO");
			sql=sql.append(" LEFT JOIN LBS.LBST_LOCALIZACIONXTAREA T ON L.LOCAI_CODIGO = T.LOCAI_CODIGO");
			sql=sql.append(" WHERE C.CELDV_X IS NOT NULL AND L.LOCAV_TELEFONO_ORIGEN = " + parametro.getMobileOrigen());
			sql=sql.append(" AND (L.TIPO_CONSULTA=" + Constants.COD_TIPO_CONSULTA_TAREA + " OR L.TIPO_CONSULTA=" + Constants.COD_TIPO_CONSULTA_WEB + ")");
			sql=sql.append(" AND L.LOCAV_TELEFONO_DESTINO IN (");
			for(int i=0;i < parametro.getListaMobiles().size();i++){
				sql=sql.append(parametro.getListaMobiles().get(i).getNumero() + ",");
			}
			sql=sql.deleteCharAt(sql.length() - 1);
			sql=sql.append(")");
			if(parametro.getFechaFin() != null && parametro.getFechaInicio() != null){
				sql=sql.append(" AND CAST(SUBSTRING(L.LOCAD_FECHA_REGISTRO,1,10) AS DATE) <= '" + parametro.getFechaFin() + "'");
				sql=sql.append(" AND CAST(SUBSTRING(L.LOCAD_FECHA_REGISTRO,1,10) AS DATE) >= '" + parametro.getFechaInicio() + "'");
			}
			sql=sql.append(" ORDER BY 2,3 ASC");
			resultSql=sql.toString();
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}*/
		return resultSql;
	}

	public List<Localizacion> obtenerLocalizaciones(Tarea tarea){
		List<Localizacion> resultado=null;
		TransactionContext tx;
		try{
			tx=new TransactionContext();
			tx.begin();
			ConsultaTareaDAO dao=new ConsultaTareaDAO(tx);
			resultado=dao.getLocalizacionesPorTarea(tarea);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(TransactionSysException e){
			log.error(e.getMessage(),e);
		}
		catch(DAOException e){
			log.error(e.getMessage(),e);
		}
		
		return resultado;
	}
}
