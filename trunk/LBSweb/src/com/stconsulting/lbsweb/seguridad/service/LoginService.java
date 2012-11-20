package com.stconsulting.lbsweb.seguridad.service;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.stconsulting.common.persistence.TransactionContext;
import com.stconsulting.common.service.GenericService;
import com.stconsulting.common.service.ServiceException;
import com.stconsulting.common.util.Constants;
import com.stconsulting.common.util.Helper;
import com.stconsulting.lbsweb.seguridad.bean.Usuario;
import com.stconsulting.lbsweb.seguridad.persistence.LoginDAO;
import com.stconsulting.lbsweb.util.Util;

public class LoginService extends GenericService{

	public LoginService(){
		super();
	}
	
	public String recuperarPassword(Usuario usuario) throws ServiceException{
		log.debug("Ingrese al verifica de LoginService");
		TransactionContext tx=null;
		String password=null;
		Usuario usuarioAux=null;

		try{
			tx=new TransactionContext();
			tx.begin();
			LoginDAO loginDAO=new LoginDAO(tx);
			usuarioAux=loginDAO.getUsuario(usuario.getUser().trim(),true);//probar
			
			if(usuarioAux == null){
				password=Constants.COD_USUARIO_INVALIDO;
			}
			else{
				
				password = usuarioAux.getPassword();
			}
		}catch(Exception e){
				log.error(e);
				if(tx != null){
					try{
						tx.close();
						tx=null;
					}
					catch(Exception ignore){
					}
				}
				e.printStackTrace();
				log.info("ERROR: LoginService: verifica: " + e.getMessage() + e.toString());
				throw new ServiceException(e);
			}
			log.debug("Sali del verifica() de LoginService");
			return password;
		}


	public void cambiarPassword(Usuario usuario,String password, boolean tipo) throws ServiceException{
		log.debug("Ingrese al verifica de LoginService");
		TransactionContext tx=null;

		try{
			tx=new TransactionContext();
			tx.begin();
			LoginDAO loginDAO=new LoginDAO(tx);
			loginDAO.cambiarPassword(usuario,password,tipo);

			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			if(tx != null){
				try{
					tx.rollback();
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			throw new ServiceException(e);
		}
	}

	public String verifica(Usuario usuario, boolean tipo) throws ServiceException{
		log.debug("Ingrese al verifica de LoginService");
		TransactionContext tx=null;
		String codValido=null;
		Usuario usuarioAux=null;
	    log.info("password:" + Util.toMD5(usuario.getPassword()));
		try{
			tx=new TransactionContext();
			tx.begin();
			LoginDAO loginDAO=new LoginDAO(tx);
			usuarioAux=loginDAO.getUsuario(usuario.getUser().trim(), tipo);

			if(usuarioAux == null){
				codValido=Constants.COD_USUARIO_INVALIDO;
			}
			else{
				/*Date fechaInicio=Converter.stringToDate(usuarioAux.getFechaActualizacion());
				Date fechaHoy=Converter.stringToDate(Helper.formateaFecha(new Date(),ConstantsMQT2.FORMATO_FECHA_MOSTRAR));*/
				int diasVenPwd=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_DIAS_VENCIMIENTO_PASSWORD));
				int difDias=Helper.getDiferenciaDias(usuarioAux.getFechaActualizacion(),new Date());
				log.debug("Diferencia de Dias en la Validacion de Password " + difDias);
				boolean flagchangePassword=difDias >= diasVenPwd;

				if(usuarioAux.getPassword()==null){
					codValido=Constants.COD_USUARIO_INVALIDO;
				}
 else if (usuario.getPassword().equals("12345")
                        && usuarioAux.getPassword().equals(Util.toMD5(usuario.getPassword())) && usuarioAux
.getClaveRecuperada().equalsIgnoreCase("A")
                        && flagchangePassword == false) {
					codValido=Constants.COD_USUARIO_PASSWORD_VENCIDO;
				}
 else if (flagchangePassword
                        && usuarioAux.getPassword().equals(Util.toMD5(usuario.getPassword()))
                        && usuarioAux.getClaveRecuperada().equalsIgnoreCase("I")) {
                    codValido = Constants.COD_USUARIO_PASSWORD_VENCIDO;
                }
				else if(!usuarioAux.getPassword().equals(Util.toMD5(Helper.checkValue(usuario.getPassword())))){
					codValido=Constants.COD_USUARIO_INVALIDO;
				}
				else if(!usuarioAux.getEstado().equals(Constants.COD_ESTADO_USUARIO_ACTIVO)){
					codValido=Constants.COD_USUARIO_INACTIVO;
				}
				else if(usuarioAux.getClaveRecuperada().equals(Constants.COD_USUARIO_CLAVE_RECUPERADA)){
					codValido = Constants.COD_USUARIO_CLAVE_RECUPERADA;
				}
				else if(!usuarioAux.getCodPerfil().equals(Constants.COD_PERFIL_ADMIN)){
					codValido=Constants.COD_USUARIO_NO_ADMIN;
					/*
					 * }else if(usuarioAux.getCodServicio()==null ||
					 * usuarioAux.getCodServicio().equals("")){ codValido =
					 * Constants.COD_USUARIO_NO_APROVISIONADO;
					 */}
				else{
					codValido=Constants.COD_USUARIO_VALIDO;
					BeanUtils.copyProperties(usuario,usuarioAux);
				}
			}

			log.debug("Se realizo la consulta con exito " + codValido);
			tx.commit();
			tx.close();
			tx=null;
		}
		catch(Exception e){
			log.error(e);
			if(tx != null){
				try{
					tx.close();
					tx=null;
				}
				catch(Exception ignore){
				}
			}
			e.printStackTrace();
			log.info("ERROR: LoginService: verifica: " + e.getMessage() + e.toString());
			throw new ServiceException(e);
		}
		log.debug("Sali del verifica() de LoginService");
		return codValido;
	}

}
