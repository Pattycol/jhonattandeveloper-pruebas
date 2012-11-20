package com.btg.claro.LBS.core.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.btg.claro.LBS.core.LBSService;
import com.btg.claro.LBS.core.LocalizacionService;
import com.btg.claro.LBS.dao.AreaDAO;
import com.btg.claro.LBS.dao.BlackListDAO;
import com.btg.claro.LBS.dao.CeldaDAO;
import com.btg.claro.LBS.dao.ConsultaDAO;
import com.btg.claro.LBS.dao.EmpresaDAO;
import com.btg.claro.LBS.dao.UsuarioDAO;
import com.btg.claro.LBS.domain.Area;
import com.btg.claro.LBS.domain.Celda;
import com.btg.claro.LBS.domain.Consulta;
import com.btg.claro.LBS.domain.Empresa;
import com.btg.claro.LBS.domain.Rol;
import com.btg.claro.LBS.domain.Usuario;
import com.btg.claro.LBS.mdw.ClienteMDW;
import com.btg.claro.LBS.mdw.RespuestaMDW;
import com.btg.claro.LBS.util.Config;
import com.btg.claro.LBS.util.Constantes;
import com.btg.claro.LBS.vlr.DatosMovil;

@Service("LBSService")
public class LBSServiceImpl implements LBSService{

	private static final Logger log=LoggerFactory.getLogger(LBSServiceImpl.class);

	@Autowired
	private ClienteMDW clienteMDW;

	@Autowired
	private LocalizacionService localizacionService;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private BlackListDAO blacklistDAO;

	@Autowired
	private ConsultaDAO consultaDAO;

	@Autowired
	private CeldaDAO celdaDAO;
	
	@Autowired
	private AreaDAO areaDAO;
	
	@Autowired
	private EmpresaDAO empresaDAO;
	
	

	@Override
    public Consulta realizarConsulta(String numeroOrigen,String numeroDestino,Date fecha,String tipoConsulta){
		return realizarConsulta(numeroOrigen,numeroDestino,fecha,true,tipoConsulta);
	}

	@Override
    public Consulta realizarConsulta(String numeroOrigen,String numeroDestino,Date fecha,boolean cobro,String tipoConsulta){
		log.info("Consultando: "+numeroOrigen+" --> "+numeroDestino);
		String mensaje=null;
		Consulta consulta=null;
		Usuario origen=usuarioDAO.buscarPorNumero(numeroOrigen);
		int error=Constantes.OK;
		if(origen != null){
			log.debug("Usuario consultante: "+origen.getNombres()+" "+origen.getApellidos());
			consulta=new Consulta();
			consulta.setConsultante(origen);
			consulta.setFechaConsulta(fecha);
			consulta.setTipo(tipoConsulta);
			consulta.setCodigoError(Constantes.ERROR_GENERAL);
			if(origen.getRol().getCodigo().equals(Constantes.CODIGO_ROL_JEFE) || origen.getRol().getCodigo().equals(Constantes.CODIGO_ROL_EMPLEADO)){
				Usuario destino=usuarioDAO.buscarPorNumero(numeroDestino);
				if(destino != null){
					log.debug("Usuario consultado: "+destino.getNombres()+" "+destino.getApellidos());
					consulta.setConsultado(destino);
					error=validarConsulta(origen,destino);
                    log.info("Flag Indicativo1");
					if(error == Constantes.OK){
						
						String application="";
						String productId="";
						int flag=0;
						int flag2=0;

                        log.info("Flag Indicativo2");

						 Empresa empresa =areaDAO.getEmpresaAreaUsuario(consulta.getConsultante().getNumero());
                        log.info(consulta.getConsultante().getNumero() + " ----"
                                + empresa.getConsultas_web_realizadas() + " -----"
                                + empresa.getTipoServicio().getConsultas_web());
						 
						 if(empresa.getConsultas_web_realizadas() != null){
                            log.info("Flag Indicativo4");
                            if (empresa.getConsultas_web_realizadas().intValue() == empresa
                                    .getTipoServicio().getConsultas_web().intValue()) {
                                log.info("Consulta adicional 1");
								 flag=1;
							 }
						}
						
                        log.info("Flag Indicativo3");

						if((tipoConsulta.equals(Constantes.CONSULTA_WEB)) && (flag==1)){
							application=Config.getPropiedad("mdw.web.application");
							productId=Config.getPropiedad("mdw.web.productId");
                            log.info("Tipo de consulta WEB:  " + "application " + application
                                    + "productId" + productId);
							flag2=1;
						}
						
						
						if(tipoConsulta.equals(Constantes.CONSULTA_SMS)){
							application=Config.getPropiedad("mdw.sms.application");
							productId=Config.getPropiedad("mdw.sms.productId");
							log.info("Tipo de consulta SMS:  "+"application "+application +"productId" + productId);
							
							flag2=1;
						}
						
						if(cobro && flag2==1){
							
							log.info("eNTRO AL COBRO PARA " + tipoConsulta );
							RespuestaMDW reserva=clienteMDW.reserve(application,numeroOrigen,productId);
							if(reserva.getCodigo() == 1){
								DatosMovil datos=localizacionService.localizarMovil(numeroDestino);
								consulta.setHlr(datos.getHLR());
								consulta.setVlr(datos.getVLR());
								error=datos.getError();
								if(error == Constantes.OK){
									Celda celda=celdaDAO.buscarCeldaPorIdentificador(datos.getIdCelda());
									consulta.setCelda(celda);
									if(celda != null && celda.getEstado()!=Constantes.ESTADO_PENDIENTE){
										RespuestaMDW aceptar=clienteMDW.acceptReserve(application,numeroOrigen,productId,reserva.getPlatform(),reserva.getTransactionId());
										if(aceptar.getCodigo() == 1){
											log.info("Cobro realizado correctamente al numero "+numeroOrigen);
											mensaje="Ultima ubicacion de " + numeroDestino + " en fecha " + datos.getFecha() + ". " + celda.getDireccion();
											consulta.setFechaResultado(new Date());
										}
										else{
											clienteMDW.cancelReserve(application,numeroOrigen,productId,reserva.getPlatform(),reserva.getTransactionId());
											error=Constantes.ERROR_GENERAL;
                                            mensaje = "Por favor volver a intentar localizar en breves minutos.";
										}
									}
									else{
										log.warn("La celda "+datos.getIdCelda()+" no se encuentra registrada en el sistema");
										clienteMDW.cancelReserve(application,numeroOrigen,productId,reserva.getPlatform(),reserva.getTransactionId());
										error=Constantes.ERROR_CELDA_INEXISTENTE;
										mensaje="El movil se encuentra fuera de cobertura del sistema LBS.";
										consulta.setIdCelda(datos.getIdCelda());
									}
								}
								else{
									clienteMDW.cancelReserve(application,numeroOrigen,productId,reserva.getPlatform(),reserva.getTransactionId());
									if(error == Constantes.ERROR_SMS){
                                        mensaje = "Por favor volver a intentar localizar en breves minutos.";
									}
									else if(error == Constantes.ERROR_VLR){
                                        mensaje = "Por favor volver a intentar localizar en breves minutos.";
									}
									else if(error == Constantes.ERROR_MOVIL_APAGADO){
										mensaje="Movil apagado.";
									}
									else{
                                        mensaje = "Por favor volver a intentar localizar en breves minutos.";
									}
								}
							}
							else if(reserva.getCodigo() == 2){
								error=Constantes.ERROR_SIN_SALDO;
								mensaje="Usted no cuenta con saldo suficiente.";
							}
							else if(reserva.getCodigo() == 3 || reserva.getCodigo() == 4){
								error=Constantes.ERROR_SIN_ACCESO;
								mensaje="Usted no tiene acceso al servicio LBS.";
							}
							else{
								error=Constantes.ERROR_GENERAL;
                                mensaje = "Por favor volver a intentar localizar en breves minutos.";
							}
						}
						else{
							DatosMovil datos=localizacionService.localizarMovil(numeroDestino);
							consulta.setHlr(datos.getHLR());
							consulta.setVlr(datos.getVLR());
							error=datos.getError();
							if(error == Constantes.OK){
								Celda celda=celdaDAO.buscarCeldaPorIdentificador(datos.getIdCelda());
								consulta.setCelda(celda);
								if(celda != null && celda.getEstado()!=Constantes.ESTADO_PENDIENTE){
									mensaje="Ultima ubicacion de " + numeroDestino + " en fecha " + datos.getFecha() + ". " + celda.getDireccion();
									consulta.setFechaResultado(new Date());
								}
								else{
									log.warn("La celda "+datos.getIdCelda()+" no se encuentra registrada en el sistema");
									error=Constantes.ERROR_CELDA_INEXISTENTE;
									mensaje="El movil se encuentra fuera de cobertura del sistema LBS.";
									consulta.setIdCelda(datos.getIdCelda());
								}
							}
							else if(error == Constantes.ERROR_SMS){
                                mensaje = "Por favor volver a intentar localizar en breves minutos.";
                            } else if (error == Constantes.ERROR_VLR) {
                                mensaje = "Por favor volver a intentar localizar en breves minutos.";
                            }
							else if(error == Constantes.ERROR_MOVIL_APAGADO){
								mensaje="Movil apagado.";
							}
							else{
                                mensaje = "Por favor volver a intentar localizar en breves minutos.";
							}
						}//fin cobro
						
						
					}
					else if(error == Constantes.ERROR_BLACKLIST_CONSULTANTE){
						mensaje="Usted esta inhabilitado de usar el servicio. Por favor comunicarse con su asesor de servicio.";
					}
					else if(error == Constantes.ERROR_BLACKLIST_CONSULTADO){
						mensaje="El numero solicitado no puede ser consultado. Por favor comunicarse con su asesor de servicio.";
					}
                    // else if(error == Constantes.ERROR_CONSULTAS_AGOTADAS){
                    // mensaje="Se han superado el numero de consultas por mes.";
                    // }
					else if(error == Constantes.ERROR_JERARQUIA){
						mensaje="Usted no tiene permiso para consultar ese numero.";
					}
					else{
						error=Constantes.ERROR_GENERAL;
                        mensaje = "Por favor volver a intentar localizar en breves minutos.";
					}
				}
				else{
					error=Constantes.ERROR_CONSULTADO_NULL;
					mensaje="El numero consultado no se encuentra registrado.";
				}
			}
			else{
				error=Constantes.ERROR_CONSULTANTE_ADMIN;
				mensaje="Por favor coordinar con Operación y Mantenimiento.";
			}
		}
		else{
			error=Constantes.ERROR_CONSULTANTE_NULL;
			mensaje="Usted no puede usar el servicio LBS.";
		}

		if(consulta != null){
			consulta.setResultado(mensaje);
			consulta.setCodigoError(error);
            log.info("Consulta XXX:" + consulta.getResultado() + "-----"
                    + consulta.getCodigoError());
		}


		return consulta;
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public Integer guardarConsulta(Consulta consulta){
		
	
		consultaDAO.guardar(consulta);
		 
		 Empresa empresa =areaDAO.getEmpresaAreaUsuario(consulta.getConsultante().getNumero());
		 int flag=0;
		 
		 if((consulta.getCodigoError()==Constantes.OK) || (consulta.getCodigoError()==Constantes.ERROR_MOVIL_APAGADO) ){
			 flag=1;
		}
		 log.info("Empresa :"+ empresa.getId()  +" ConsultaId: "+consulta.getId()+"). Consultas Web Realizadas " + empresa.getConsultas_web_realizadas() + "Consultas Web Totales " + empresa.getTipoServicio().getConsultas_web()  );
			
		// empresa=listaAreas.get(0).getEmpresa();
		 
		 if(consulta.getTipo().equals(Constantes.CONSULTA_WEB) && flag==1){
			 
			 if(empresa.getConsultas_web_realizadas() != null){
				 if(empresa.getConsultas_web_realizadas() < empresa.getTipoServicio().getConsultas_web()){
					 
					 empresaDAO.incrementarConsultasWeb(empresa.getRuc());
					 log.info("Consulta "+consulta.getTipo()+" incrementada de la Empresa :"+ empresa.getRuc()  +" ConsultaId: "+consulta.getId()+"). Resultado: ["+consulta.getResultado()+"]");
					
				 }else{
					 empresaDAO.incrementarConsultasAdicionales(empresa.getRuc());
					 log.info("Consulta "+consulta.getTipo()+" adicional incrementada de la Empresa :"+ empresa.getRuc()  +" ConsultaId: "+consulta.getId()+"). Resultado: ["+consulta.getResultado()+"]");
					
					 
				 }
				 
			 }else{
				 
				 empresaDAO.incrementarConsultasWeb(empresa.getRuc());
				 log.info("Consulta "+consulta.getTipo()+" incrementada de la Empresa :"+ empresa.getRuc()  +" ConsultaId: "+consulta.getId()+"). Resultado: ["+consulta.getResultado()+"]");
				
				 
			 }
			 
			 
		 }
		 
		if(consulta.getTipo().equals(Constantes.CONSULTA_SMS)){
			empresaDAO.incrementarConsultasSMS(empresa.getRuc());
			log.info("Consulta "+consulta.getTipo()+" incrementada de la Empresa :"+ empresa.getRuc()  +" ConsultaId: "+consulta.getId()+"). Resultado: ["+consulta.getResultado()+"]");
		}
		
		log.info("Consulta "+consulta.getTipo()+" realizada (idConsulta:"+consulta.getId()+"). Resultado: ["+consulta.getResultado()+"]");
		return consulta.getId();
	}

	@Override
    @Transactional(propagation=Propagation.REQUIRED)
	public Celda guardarCelda(String idCelda){
		Celda celda=new Celda();
		celda.setIdentificador(idCelda);
		celda.setEstado(Constantes.ESTADO_PENDIENTE);
		celda.setFechaCreacion(new Date());
		celdaDAO.guardar(celda);
		return celda;
	}

	private int validarConsulta(Usuario origen,Usuario destino){
		log.debug("Verificando blacklist para consultante");
		if(blacklistDAO.buscarBlackListActivoPorNumeroDeUsuario(origen) != null)
			return Constantes.ERROR_BLACKLIST_CONSULTANTE;
		log.debug("Verificando blacklist para consultado");
		if(blacklistDAO.buscarBlackListActivoPorNumeroDeUsuario(destino) != null)
			return Constantes.ERROR_BLACKLIST_CONSULTADO;
		log.debug("Verificando jerarquia de numeros");
		if(!verificarJerarquia(origen,destino))
			return Constantes.ERROR_JERARQUIA;
        // log.debug("Verificando consultas disponibles");
        // if(!verificarConsultasDisponibles(origen))
        // return Constantes.ERROR_CONSULTAS_AGOTADAS;
		return Constantes.OK;
	}

	/**
	 * <ol>
	 * <li>Se verifica que ambos números pertenezcan a la misma empresa</li>
	 * <li>
	 * Si los usuarios pertenecen a la misma área
	 * <ol>
	 * <li>El consultante debe ser jefe</li>
	 * </ol>
	 * </li>
	 * <li>
	 * Si los usuarios pertenecen a áreas distintas, el área del consultante
	 * tiene que ser padre del área del consultado
	 * <ol>
	 * <li>Si el consultante es jefe, puede consultar por todos</li>
	 * <li>Si el consultante es empleado, sólo puede consultar por los empleados
	 * </li>
	 * </ol>
	 * </li>
	 * </ol>
	 * 
	 * @param origen
	 * @param destino
	 * @return
	 */
	private boolean verificarJerarquia(Usuario origen,Usuario destino){
		// Se verifica que al menos tengan un área en común que pertenezca a la
		// misma empresa
		List<Area> areasOrigen=areaDAO.getAreasPorUsuario(origen);
		List<Area> areasDestino=areaDAO.getAreasPorUsuario(destino);
		boolean mismaEmpresa=false;
		for(Area o : areasOrigen){
			for(Area d : areasDestino){
				if(o.getEmpresa().equals(d.getEmpresa())){
					mismaEmpresa=true;
				}
			}
		}
		if(mismaEmpresa){
			log.debug("Los numeros pertenecen a la misma empresa");
			Rol rolUsuarioConsultante=origen.getRol();
			Rol rolUsuarioConsultado=destino.getRol();
			log.debug("Rol consultante: " + rolUsuarioConsultante.getNombre());
			log.debug("Rol consultado: " + rolUsuarioConsultado.getNombre());
			for(Area o : areasOrigen){
				for(Area d : areasDestino){
					// misma área
					if(o.equals(d)){
						log.debug("Los numeros son de la misma area");
						if(rolUsuarioConsultante.getCodigo().equals(Constantes.CODIGO_ROL_JEFE)){
							return true;
						}
					}
					else{
						log.debug("Los numeros son de areas diferentes");
						Area padre=d.getPadre();
						boolean esPadre=false;
						while(padre != null){
							if(padre.equals(o)){
								esPadre=true;
								break;
							}
							padre=padre.getPadre();
						}
						if(esPadre){
							log.debug("El consultante se encuentra en un area padre a la del consultado.");
							if(rolUsuarioConsultante.getCodigo().equals(Constantes.CODIGO_ROL_JEFE)){
								return true;
							}
							if(rolUsuarioConsultado.getCodigo().equals(Constantes.CODIGO_ROL_EMPLEADO)){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean verificarConsultasDisponibles(Usuario usuarioConsultante){
		Integer totalDeConsultasEnMes=consultaDAO.conseguirNumeroDeConsultasPorMesDeUsuarioConsultante(usuarioConsultante);
		log.debug("Consultas realizadas en este mes por el numero "+usuarioConsultante.getNumero()+": "+totalDeConsultasEnMes);
		Integer numeroConsultasPorUsuario=usuarioConsultante.getConsultasPorMes();
		log.debug("Maximo de consultas por usuario: "+numeroConsultasPorUsuario);
		if(numeroConsultasPorUsuario != null){
			return totalDeConsultasEnMes < numeroConsultasPorUsuario;
		}
		List<Area> areasDeUsuario=areaDAO.getAreasPorUsuario(usuarioConsultante);
		Integer totalConsultasPorArea=0;
		if(areasDeUsuario.size() > 0){
			for(Area area : areasDeUsuario){
				Integer consultasPorArea=area.getConsultasPorMes();
				log.debug("Maximo de consultas para el area "+area.getNombre()+": "+consultasPorArea);
				if(consultasPorArea!=null){
					totalConsultasPorArea+=consultasPorArea;
				}
			}

			if(totalConsultasPorArea == 0){
				// FIXME será correcto esto?
				Area area=areasDeUsuario.get(0);
				Empresa empresa=area.getEmpresa();
				Integer consultasDeEmpresa=empresa.getConsultasPorMes();
				log.debug("Maximo de consultas para la empresa "+empresa.getRazonSocial()+": "+consultasDeEmpresa);
				if(consultasDeEmpresa != null){
					return totalDeConsultasEnMes < consultasDeEmpresa;
				}
				return false;
			}
		}
		return totalDeConsultasEnMes < totalConsultasPorArea;
	}

}
