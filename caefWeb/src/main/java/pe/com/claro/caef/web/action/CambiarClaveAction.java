package pe.com.claro.caef.web.action;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import pe.com.claro.caef.web.beans.EncryptBean;


import pe.com.claro.caef.web.auth.Usuario;
import pe.com.claro.caef.web.services.EncryptService;
import pe.com.claro.caef.web.services.PaqueteBusinessDelegate;
import pe.com.claro.caef.web.services.UsuarioService;
import pe.com.claro.caef.web.services.impl.UsuarioServiceImpl;
import pe.com.claro.caef.web.util.CaeConstantes;
import pe.com.claro.caef.web.util.MENSAJES_CONFIG;
import pe.com.claro.caef.web.util.TripleDESEncryption;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class CambiarClaveAction extends GeneralAction {
	
	private String passwordNuevo;
	private String passwordConfirma;
	//private UsuarioService usuarioService;
	private String passwordAntiguo;
	private String mensajeResultado;
	private int flag;
	
	@Autowired
	private EncryptService encryptService;
	
	
	
	public String cambioClave(){
		UsuarioService usuarioService=
					PaqueteBusinessDelegate.getUsuarioServiceImpl();
		Usuario userCodSer = new Usuario();
		Map codServicio = ActionContext.getContext().getSession();
		userCodSer = (Usuario)codServicio.get("codServicio");
		
		Usuario user = new Usuario();
		user = getUsuario();
		user.setCodigoServicio(userCodSer.getCodigoServicio());	
		user.setCodigoProducto(userCodSer.getCodigoProducto());
		/*****************************************/
		String mensaje = "";
		int exito=0;
		try {			
			Usuario  usuario = new Usuario();
			String passwordNuevo=this.getPasswordNuevo();
			String passwordConfirma=this.getPasswordConfirma();
			if((!this.getPasswordConfirma().equals(""))|| (!this.getPasswordNuevo().equals(""))){
				if (passwordNuevo.equals(passwordConfirma)) {
					EncryptBean encryptPass=  this.encryptService.generatePassword(passwordNuevo);  // Genera un nuevo password encriptado
					usuario.setTelefonoMiClaroFija(user.getTelefonoMiClaroFija());
					//usuario.setPassword(encryptPass.getEncrypy_pass());
					usuario.setEncPass(encryptPass.getEncrypy_pass());
					usuario.setTdes_key(encryptPass.getTdes_key());
				} else {
					addActionError(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_1);
					this.setMensajeResultado(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_1);
					mensaje=MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_1;
					this.setFlag(1);
					return "ERROR";
					
				}	
				if(mensaje.compareTo("")==0){
					if (!this.encryptService.validatePassword(new EncryptBean(user.getEncPass(),user.getTdes_key()), this.getPasswordAntiguo()) ){
						 // Compara el password encriptado del usuario con el password ingresado (password del usuario) sin encriptar
						addActionError(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_2);
						this.setMensajeResultado(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_2);
						mensaje=MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_2;
						this.setFlag(1);
						return "ERROR";
					} 
					else{
						if (this.encryptService.validatePassword(new EncryptBean(user.getEncPass(),user.getTdes_key()), passwordNuevo) ){
							// Compara un password encriptado del usuario con el nuevo password sin encriptar
							//mensaje = messageUtil.getMessage("mensaje.equalsPassword",new String []{});	
							addActionError(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_3);//sera reemplazado por el envio de mensajes de error
							this.setMensajeResultado(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_3);
							mensaje=MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_3;
							this.setFlag(1);
							return "ERROR";
						}  

						else{
							int resultado = usuarioService.actualizaClave(usuario); // Cambia la clave del usuario
							switch (resultado) {
							case CaeConstantes.FALLO:  // No se realizó el cambio
								//mensaje = messageUtil.getMessage("mensaje.registroNoGrabado",new String []{});
								addActionError(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_4);
								mensaje=MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_4;
								break;
		
							case CaeConstantes.EXITO:   // Se realizó el cambio
							//mensaje = messageUtil.getMessage("mensaje.registroGrabado",new String []{});
								exito=1;
								//user.setPassword(usuario.getPassword());
								user.setTdes_key(usuario.getTdes_key());
								user.setEncPass(usuario.getEncPass());
								break;
						}
						String tele = usuario.getTelefonoMiClaroFija(); // Consultar Tipo de Telefono
						usuarioService.actualizaFlagDatos(tele, CaeConstantes.valueFlagDesDatos); 	/* Actualiza el flag de datos a 0 (cero) o 1 (uno). Si es cero, cuando se loguee le parecerá la pantalla de Actualización de Datos  
																									 * si es uno no le aparecerá la pantalla. El flag se vuelve cero cuando se crea un "Nuevo Usuario" y cuando se "Cambia de Clave".
																									 */
						}
					}
				}//fin del primer if
			}
			else{
				return Action.INPUT;
			}
			
			

		} catch (Exception e) {
			LOG.info(e.toString());
			this.setFlag(1);
			this.setMensajeResultado(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_5);
			return "ERROR";
		} 
		this.setFlag(1);
		this.setMensajeResultado(MENSAJES_CONFIG.ERROR_CAMBIO_CLAVE_6);
		return Action.SUCCESS;
	}

	public String getPasswordNuevo() {
		return passwordNuevo;
	}

	public void setPasswordNuevo(String passwordNuevo) {
		this.passwordNuevo = passwordNuevo;
	}

	public String getPasswordConfirma() {
		return passwordConfirma;
	}

	public void setPasswordConfirma(String passwordConfirma) {
		this.passwordConfirma = passwordConfirma;
	}

	public String getPasswordAntiguo() {
		return passwordAntiguo;
	}

	public void setPasswordAntiguo(String passwordAntiguo) {
		this.passwordAntiguo = passwordAntiguo;
	}

	public String getMensajeResultado() {
		return mensajeResultado;
	}

	public void setMensajeResultado(String mensajeResultado) {
		this.mensajeResultado = mensajeResultado;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
	
	

}
