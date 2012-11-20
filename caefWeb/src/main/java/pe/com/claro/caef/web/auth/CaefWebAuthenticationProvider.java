package pe.com.claro.caef.web.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import pe.com.claro.caef.web.action.GeneralAction;
import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.action.filter.MensajesSeguridadFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosPreguntasFilter;
import pe.com.claro.caef.web.action.filter.ObtenerDatosUsuarioFilter;
import pe.com.claro.caef.web.beans.Autenticar;
import pe.com.claro.caef.web.beans.AutenticarResponse;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.beans.MensajesSeguridad;
import pe.com.claro.caef.web.beans.ObtenerDatosPreguntas;
import pe.com.claro.caef.web.beans.ObtenerDatosUsuario;
import pe.com.claro.caef.web.services.CargarMensajesService;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.LoginService;

@Service("caefWebAuthenticationProvider")
public class CaefWebAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private ComunService comunService;
	
	@Autowired
	private CargarMensajesService cargarMensajesService;
	
	private MensajesSeguridad mensajesSeguridad;
	private MensajesSeguridadFilter mensajesSeguridadFilter;
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroLogin;
	
	@Autowired
	private LoginService loginService;
	
	private Autenticar autenticar;
	ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter;
	ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter;
	private String usuarioSeguridad;
	
	public MensajesSeguridad getMensajesSeguridad() {
		return mensajesSeguridad;
	}
	public void setMensajesSeguridad(MensajesSeguridad mensajesSeguridad) {
		this.mensajesSeguridad = mensajesSeguridad;
	}
	public MensajesSeguridadFilter getMensajesSeguridadFilter() {
		return mensajesSeguridadFilter;
	}
	public void setMensajesSeguridadFilter(
			MensajesSeguridadFilter mensajesSeguridadFilter) {
		this.mensajesSeguridadFilter = mensajesSeguridadFilter;
	}
	public String getUsuarioSeguridad() {
		return usuarioSeguridad;
	}
	public void setUsuarioSeguridad(String usuarioSeguridad) {
		this.usuarioSeguridad = usuarioSeguridad;
	}
	public ObtenerDatosPreguntasFilter getObtenerDatosPreguntasFilter() {
		return obtenerDatosPreguntasFilter;
	}
	public void setObtenerDatosPreguntasFilter(
			ObtenerDatosPreguntasFilter obtenerDatosPreguntasFilter) {
		this.obtenerDatosPreguntasFilter = obtenerDatosPreguntasFilter;
	}
	public ObtenerDatosUsuarioFilter getObtenerDatosUsuarioFilter() {
		return obtenerDatosUsuarioFilter;
	}
	public void setObtenerDatosUsuarioFilter(
			ObtenerDatosUsuarioFilter obtenerDatosUsuarioFilter) {
		this.obtenerDatosUsuarioFilter = obtenerDatosUsuarioFilter;
	}
	public Autenticar getAutenticar() {
		return autenticar;
	}
	public void setAutenticar(Autenticar autenticar) {
		this.autenticar = autenticar;
	}
	public List<ConsultaDatosMaestro> getLstConsultaDatosMaestroLogin() {
		return lstConsultaDatosMaestroLogin;
	}
	public void setLstConsultaDatosMaestroLogin(
			List<ConsultaDatosMaestro> lstConsultaDatosMaestroLogin) {
		this.lstConsultaDatosMaestroLogin = lstConsultaDatosMaestroLogin;
	}
	public ConsultarDatosMaestrosFilter getConsultarDatosMaestrosFilter() {
		return consultarDatosMaestrosFilter;
	}
	public void setConsultarDatosMaestrosFilter(
			ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter) {
		this.consultarDatosMaestrosFilter = consultarDatosMaestrosFilter;
	}

	private static final Logger log = Logger.getLogger(CaefWebAuthenticationProvider.class);
    
    private static Collection<GrantedAuthority> authorities =  new ArrayList<GrantedAuthority>(1){{
        add(
        		new GrantedAuthority() {
					
						public String getAuthority() {
							// TODO Auto-generated method stub
							return "ROLE_USER";
						}
				}
        		
        	);
    }};
    
    private static Collection<GrantedAuthority> validate =  new ArrayList<GrantedAuthority>(1){{
        add(
        		new GrantedAuthority() {
					
						public String getAuthority() {
							// TODO Auto-generated method stub
							return "VALIDATE_USER";
						}
				}
        		
        	);
    }};
    		
    
    protected String getProviderName() {
        return "CaefWebUsers";
    }
 

//    @RequestMapping(value = "/accessDenied")
	public Authentication authenticate(Authentication authentication){
		
		
		mensajesSeguridadFilter = new MensajesSeguridadFilter();
		mensajesSeguridadFilter.setTipoPantalla("login");
		mensajesSeguridad = cargarMensajesService.obtenerMensajes(mensajesSeguridadFilter);
		
		StringBuffer sf = new StringBuffer();
		
		String principal = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		/*PRUEBAS NUEVO FLUJO DEL LOGIN*/
		/**PASO 1***********************/
		Usuario usuario = new Usuario();
		/*String correoPrueba = "epomazon@claro.com.pe";*/
		//String correoPrueba = "asdsadsadsa";
		consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
		consultarDatosMaestrosFilter.setCodigoCaso("VALCLICAESGA");
		consultarDatosMaestrosFilter.setCodigoEstado("1");
		consultarDatosMaestrosFilter.setParametro1(principal);//correoPrueba);//reemplazar por principal
		lstConsultaDatosMaestroLogin = comunService.getConsultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
		
		
		/*******************************/
		if(lstConsultaDatosMaestroLogin.size() > 0)
		{
			sf.append("Validación de cliente SGA es correcta").append(",\n");
			//**PASO 2***********************//
			AutenticarResponse ar = new AutenticarResponse(); 
			autenticar = new Autenticar();
			autenticar.setTxId("");
			autenticar.setTelefono(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().toString());
			autenticar.setIdApp("");
			autenticar.setClave(password);
			ar = loginService.autenticar(usuario, autenticar);
			//*******************************//	
			if(ar.getCodError().equals("0"))
			{
				if(ar.getIsValidar() == true)
				{
					sf.append("Autenticar usuario mi claro fue correcto").append(",\n");
					//**PASO 3***********************//
					ObtenerDatosUsuario odu = new ObtenerDatosUsuario(); 
					obtenerDatosUsuarioFilter = new ObtenerDatosUsuarioFilter();		
					obtenerDatosUsuarioFilter.setIpApp("");
					obtenerDatosUsuarioFilter.setTxId("");
					obtenerDatosUsuarioFilter.setUsrApp("");
					obtenerDatosUsuarioFilter.setTelefono(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().toString());//966319620
					odu	= loginService.obtenerDatosUsuario(usuario, obtenerDatosUsuarioFilter);
					//*******************************//
					if(odu.getCodError().equals("0"))
					{
						//EL USUARIO NO TIENE PREGUNTAS REGISTRADAS || VER CORREO LIZ 29/03/2011
						sf.append("Obtener nro de preguntas secretas a responder").append(",\n");
						//**PASO 4***********************//*
						ObtenerDatosPreguntas odp = new ObtenerDatosPreguntas();
						obtenerDatosPreguntasFilter = new ObtenerDatosPreguntasFilter();
						obtenerDatosPreguntasFilter.setIpApp("");
						obtenerDatosPreguntasFilter.setTxId("");
						obtenerDatosPreguntasFilter.setUsrApp("");
						odp = loginService.obtenerDatosPreguntas(usuario, obtenerDatosPreguntasFilter);
						//*******************************//
						if(odp.getCodError().equals("1"))
						{
							//LLAMAR A PANTALLA DE INGRESO DE PREGUNTAS SECRETAS
							usuarioSeguridad = "ingresar";
							principal = principal +"/"+usuarioSeguridad + "/" + lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().toString();
							sf.append("El usuario de mi claro fija debe de registrar sus preguntas secretas").append(",\n");
							sf.append("Direccionando a la pantalla de ingreso de preguntas secretas").append(",\n");
							sf.append("]");
							log.info(sf.toString());
							return new UsernamePasswordAuthenticationToken(principal, password, authorities);
						}
						else
						{
							//**FALLO  EN EL PASO 4 - Audit**********//
							//VOLVER A CARGAR LA PANTALLA DE LOGIN
							
							sf.append("Error en el servicio obtenerDatosPreguntas").append(",\n");
							sf.append("]");
					        log.info(sf.toString());
							throw new BadCredentialsException("");
							//*******************************//*
						}
					}
					else if(odu.getCodError().equals("1"))
					{						
						if(odu.getNroPreguntas().length() > 0)
						{
							sf.append("Obtener nro de preguntas secretas respondidas fue correcto").append(",\n");
							//**PASO 4***********************//
							ObtenerDatosPreguntas odp = new ObtenerDatosPreguntas();
							obtenerDatosPreguntasFilter = new ObtenerDatosPreguntasFilter();
							obtenerDatosPreguntasFilter.setIpApp("");
							obtenerDatosPreguntasFilter.setTxId("");
							obtenerDatosPreguntasFilter.setUsrApp("");
							odp = loginService.obtenerDatosPreguntas(usuario, obtenerDatosPreguntasFilter);
							//*******************************//
							if(odp.getCodError().equals("1"))
							{
//								REUNION CON LIZ 16/05/12, YA NO SE ADICIONA NI SE ELIMINAN PREGUNTAS
//								if(odu.getLstListaRespuestasType().size() < Integer.valueOf(odp.getNroPregReg()))
//								{
//									//LLAMAR A PANTALLA DE ADICIONAR PREGUNTAS
//									usuarioSeguridad = "adicionar";//adicionar
//									principal = principal +"/"+usuarioSeguridad + "/" + lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().toString();
//									sf.append("El usuario de mi claro fija debe de adicionar preguntas secretas").append(",\n");
//									sf.append("Direccionando a la pantalla de adición de preguntas secretas").append(",\n");
//									sf.append("]");
//								    log.info(sf.toString());
//									return new UsernamePasswordAuthenticationToken(principal, password, authorities);
//								}
//								else if(odu.getLstListaRespuestasType().size() > Integer.valueOf(odp.getNroPregReg()))
//								{
//									//LLAMAR A PANTALLA DE ELIMINAR PREGUNTAS
//									usuarioSeguridad = "adicionar";//eliminar
//									principal = principal +"/"+usuarioSeguridad + "/" + lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().toString();
//									sf.append("El usuario de mi claro fija debe de eliminar preguntas secretas").append(",\n");
//									sf.append("Direccionando a la pantalla de eliminación de preguntas secretas").append(",\n");
//									sf.append("]");
//								    log.info(sf.toString());
//									return new UsernamePasswordAuthenticationToken(principal, password, authorities);
//								}
//								else
//								{
									usuarioSeguridad = "miclaro";
									sf.append("Flujo de seguridad completo para el usuario de mi claro fija: ").append(principal).append(",\n");
									principal = principal +"/"+usuarioSeguridad + "/" + lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().toString();
									sf.append("Direccionando a la pantalla servicios del cliente").append(",\n");
									sf.append("]");
									log.info(sf.toString());
									return new UsernamePasswordAuthenticationToken(principal, password, authorities);
//								}
							}
							else
							{
								//**FALLO  EN EL PASO 4 - Nro. Preg. a Conf.**********//*
								//VOLVER A CARGAR LA PANTALLA DE LOGIN
								
								sf.append("Error en el servicio obtenerDatosPreguntas").append(",\n");
								sf.append("Error al obtener el nro. de preg. secretas configuradas").append(",\n");
								sf.append("]");
							    log.info(sf.toString());
								throw new BadCredentialsException(mensajesSeguridad.getMensaje1());
								//*******************************//*
							}
						}
						else
						{
							//**FALLO  EN EL PASO 4 - Audit**********//*
							//VOLVER A CARGAR LA PANTALLA DE LOGIN
							
							sf.append("Error en el servicio obtenerDatosPreguntas").append(",\n");
							sf.append("]");
						    log.info(sf.toString());
							throw new BadCredentialsException(mensajesSeguridad.getMensaje1());
							//*******************************//*
						}
					}
					else
					{
						//**FALLO  EN EL PASO 3 - Audit**********//*
						//VOLVER A CARGAR LA PANTALLA DE LOGIN
						
						sf.append("Error en el servicio obtenerDatosUsuario").append(",\n");
						sf.append("]");
				        log.info(sf.toString());
						throw new BadCredentialsException(mensajesSeguridad.getMensaje1());
						//*******************************//*
					}
				}
				else
				{
					//**FALLO  EN EL PASO 2 - IsValidar**********//*
					//VOLVER A CARGAR LA PANTALLA DE LOGIN
					
					sf.append("Error en el servicio de auenticar").append(",\n");
					sf.append("El valor de isValidar para este cliente es false").append(",\n");
					sf.append("]");
			        log.info(sf.toString());
					throw new BadCredentialsException(mensajesSeguridad.getMensaje1());
					//*******************************//*
				}
			}
			else
			{
				//**FALLO  EN EL PASO 2 - Audit**********//*
				//VOLVER A CARGAR LA PANTALLA DE LOGIN
				
				sf.append("Error en el servicio de auenticar").append(",\n");
				sf.append("]");
		        log.info(sf.toString());
				throw new BadCredentialsException(mensajesSeguridad.getMensaje1());
				//*******************************//*
			}
		}
		else
		{
			//**FALLO  EN EL PASO 1**********//*
			//VOLVER A CARGAR LA PANTALLA DE LOGIN
			
			sf.append("Validación de cliente SGA es incorrecta").append(",\n");
			sf.append("El correo ingresado no esta registrado en Mi Claro Fija").append(",\n");
			sf.append("]");
	        log.info(sf.toString());
			throw new BadCredentialsException(mensajesSeguridad.getMensaje1());
			//*******************************//*
		}
		}


	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}
    
    
}
