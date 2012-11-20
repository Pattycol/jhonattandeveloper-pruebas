package pe.com.claro.caef.web.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import pe.com.claro.caef.web.action.filter.ConsultarDatosMaestrosFilter;
import pe.com.claro.caef.web.beans.Autenticar;
import pe.com.claro.caef.web.beans.AutenticarResponse;
import pe.com.claro.caef.web.beans.ConsultaDatosMaestro;
import pe.com.claro.caef.web.services.ComunService;
import pe.com.claro.caef.web.services.LoginService;
import pe.com.claro.caef.web.util.PropertiesCAEF;

@Component("caefWebSuccessHandler")
public class CaefWebSuccessHandler implements AuthenticationSuccessHandler  {
	
	@Autowired
	private ComunService comunService;
	
	private ConsultarDatosMaestrosFilter consultarDatosMaestrosFilter;
	private List<ConsultaDatosMaestro> lstConsultaDatosMaestroLogin;
	
	@Autowired
	private LoginService loginService;
	
	private Autenticar autenticar;
	
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

	private static final Logger log = Logger.getLogger(CaefWebSuccessHandler.class);
	
	private AuthenticationSuccessHandler target = new SimpleUrlAuthenticationSuccessHandler(PropertiesCAEF.URL_DEFAULT);
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException,ServletException {
	        
			log.info("CARGANDO EL USUARIO " + auth.getPrincipal());
			
			Usuario usuario = new Usuario();
			
			usuario.setAuthorities(new ArrayList<GrantedAuthority>(1)
			{
				{
					add(new GrantedAuthority(){
						public String getAuthority()
						{
							// TODO Auto-generated method stub
							return "ROLE_USER";
						}
					}
		        	   );
				}
			} );
			
				String [] usuarioSeguridad  = auth.getPrincipal().toString().split("/");
				
				consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
				consultarDatosMaestrosFilter.setCodigoCaso("VALCLICAESGA");
				consultarDatosMaestrosFilter.setCodigoEstado("1");
				//consultarDatosMaestrosFilter.setParametro1("epomazon@claro.com.pe");
				consultarDatosMaestrosFilter.setParametro1(usuarioSeguridad[0].toString());
				//consultarDatosMaestrosFilter.setParametro1("epomazon@claro.com.pe");//epomazon@claro.com.pe
				//consultarDatosMaestrosFilter.setParametro1(usuarioSeguridad[0].toString());
				lstConsultaDatosMaestroLogin = comunService.getConsultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
				
				/**SET DE VALORES DE SESION**/
				usuario.setCodigoUsuario(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro2());
				usuario.setCodTipoDocumento(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro3());
				usuario.setNumDocumento(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro4());
				usuario.setCorreoCliente(usuarioSeguridad[0]);
				usuario.setTelefonoMiClaroFija(usuarioSeguridad[2]);
				usuario.setPassword(auth.getCredentials().toString());
				usuario.setDominio("www.miclaro.com.pe");
				/****************************/
				
				
				/*******TRAER KEY PASSWORD****/
				AutenticarResponse ar = new AutenticarResponse(); 
				autenticar = new Autenticar();
				autenticar.setTxId("");
				autenticar.setTelefono(usuarioSeguridad[2]);
				autenticar.setIdApp("");
				autenticar.setClave(auth.getCredentials().toString());
				ar = loginService.autenticar(usuario, autenticar);
				usuario.setTdes_key(ar.getUsuarioWebType().getTdesKey());
				usuario.setEncPass(ar.getUsuarioWebType().getPassword());
				
				
				
				/****************************/
				
				consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
				consultarDatosMaestrosFilter.setCodigoCaso("CLIENTE");
				consultarDatosMaestrosFilter.setCodigoEstado("1");
				consultarDatosMaestrosFilter.setParametro1(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro3());
				consultarDatosMaestrosFilter.setParametro2(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro4());
				//consultarDatosMaestrosFilter.setParametro1(usuarioSeguridad[0].toString());
				lstConsultaDatosMaestroLogin = comunService.getConsultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
				
				/**SET DE VALORES DE SESION**/
				usuario.setNombreUsuario(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro2());
				/****************************/
				consultarDatosMaestrosFilter = new ConsultarDatosMaestrosFilter();
				consultarDatosMaestrosFilter.setCodigoCaso("TPER");
				consultarDatosMaestrosFilter.setParametro1(usuario.getCodigoUsuario());
				consultarDatosMaestrosFilter.setParametro2(usuario.getCodTipoDocumento());
				consultarDatosMaestrosFilter.setParametro3(usuario.getNumDocumento());

				//consultarDatosMaestrosFilter.setParametro1(usuarioSeguridad[0].toString());
				lstConsultaDatosMaestroLogin = comunService.getConsultarDatosMaestros(usuario, consultarDatosMaestrosFilter);
				
				if(lstConsultaDatosMaestroLogin.get(0).getValorDatoMaestro1().equalsIgnoreCase("J")){
					usuario.setTipUsuario("Corporativo");
					usuario.setsFlagUsuario("C");
				}
				else{
					usuario.setTipUsuario("Residencial");
					usuario.setsFlagUsuario("R");
				
				}
				
				if(usuarioSeguridad[1].equals("ingresar"))
				{
					/*if(usuarioSeguridad[0].equals("123456789"))
					{
						/*CLIENTE QUE CONSULTA LAS OPCIONES DE:
						 * CONSULTAR CLIENTE
						 * ACTUALIZAR CLIENTE
						 * 
						 * */
					/*	usuario.setCodigoUsuario("00750508");
						usuario.setCodTipoDocumento("002");
						usuario.setNumDocumento("40938292");
						
						usuario.setNombreUsuario("USUARIO QA 1");
						usuario.setCodigoServicio("7488");//se va a obtener de la pantalla de servicios
						usuario.setCodigoInstanciaServicio("1304021");//ya no se va a enviar por sesion
						//usuario.setCodigoInstanciaServicio("0068");
						usuario.setTipUsuario("recidencial");//falta procedure que traiga dicha informacion*/
						//usuario.setTipUsuario("recidencial");//falta procedure que traiga dicha informacion
						request.getSession().setAttribute("usuario", usuario);
					/*}
					else if(usuarioSeguridad[0].equals("987654321"))
					{
						usuario.setCodigoUsuario("00012895");

						usuario.setCodTipoDocumento("001");
						usuario.setNumDocumento("20337564373");
						usuario.setNombreUsuario("USUARIO QA 2");
						usuario.setCodigoServicio("0079");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}
					else if(usuarioSeguridad[0].equals("123459876"))
					{
						//CONSULTA DE RECARGAS
						usuario.setCodigoUsuario("01203317");

						usuario.setCodTipoDocumento("");
						usuario.setNumDocumento("");
						usuario.setNombreUsuario("USUARIO QA 3");
						usuario.setCodigoServicio("1");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}*/
					target = new SimpleUrlAuthenticationSuccessHandler(PropertiesCAEF.URL_INGRESAR);
				}
				else if(usuarioSeguridad[1].equals("adicionar"))
				{
					/*if(usuarioSeguridad[0].equals("123456789"))
					{
						/*CLIENTE QUE CONSULTA LAS OPCIONES DE:
						 * CONSULTAR CLIENTE
						 * ACTUALIZAR CLIENTE
						 * 
						 * */
						/*usuario.setCodigoUsuario("00750508");
						
						usuario.setCodTipoDocumento("002");
						usuario.setNumDocumento("40938292");
						usuario.setNombreUsuario("USUARIO QA 1");
						usuario.setCodigoServicio("7488");
						usuario.setCodigoInstanciaServicio("1304021");
						//usuario.setCodigoInstanciaServicio("0068");
						usuario.setTipUsuario("recidencial");*/
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					/*}
					else if(usuarioSeguridad[0].equals("987654321"))
					{
						usuario.setCodigoUsuario("00012895");
						
						usuario.setCodTipoDocumento("001");
						usuario.setNumDocumento("20337564373");
						usuario.setNombreUsuario("USUARIO QA 2");
						usuario.setCodigoServicio("0079");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}
					else if(usuarioSeguridad[0].equals("123459876"))
					{
						//CONSULTA DE RECARGAS
						usuario.setCodigoUsuario("01203317");

						usuario.setCodTipoDocumento("");
						usuario.setNumDocumento("");
						usuario.setNombreUsuario("USUARIO QA 3");
						usuario.setCodigoServicio("1");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}*/
					target = new SimpleUrlAuthenticationSuccessHandler(PropertiesCAEF.URL_ADICIONAR);
				}
				else if(usuarioSeguridad[1].equals("eliminar"))
				{
					/*if(usuarioSeguridad[0].equals("123456789"))
					{
						/*CLIENTE QUE CONSULTA LAS OPCIONES DE:
						 * CONSULTAR CLIENTE
						 * ACTUALIZAR CLIENTE
						 * 
						 * */
						/*usuario.setCodigoUsuario("00750508");

						usuario.setCodTipoDocumento("002");
						usuario.setNumDocumento("40938292");
						usuario.setNombreUsuario("USUARIO QA 1");
						usuario.setCodigoServicio("7488");
						usuario.setCodigoInstanciaServicio("1304021");
						//usuario.setCodigoInstanciaServicio("0068");
						usuario.setTipUsuario("recidencial");*/
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					/*}
					else if(usuarioSeguridad[0].equals("987654321"))
					{
						usuario.setCodigoUsuario("00012895");

						usuario.setCodTipoDocumento("001");
						usuario.setNumDocumento("20337564373");
						usuario.setNombreUsuario("USUARIO QA 2");
						usuario.setCodigoServicio("0079");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}
					else if(usuarioSeguridad[0].equals("123459876"))
					{
						//CONSULTA DE RECARGAS
						usuario.setCodigoUsuario("01203317");
					
						usuario.setCodTipoDocumento("");
						usuario.setNumDocumento("");
						usuario.setNombreUsuario("USUARIO QA 3");
						usuario.setCodigoServicio("1");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}*/
					target = new SimpleUrlAuthenticationSuccessHandler(PropertiesCAEF.URL_ELIMINAR);
				}
				else if(usuarioSeguridad[1].equals("miclaro"))
				{
					/*if(usuarioSeguridad[0].equals("123456789"))
					{
						/*CLIENTE QUE CONSULTA LAS OPCIONES DE:
						 * CONSULTAR CLIENTE
						 * ACTUALIZAR CLIENTE
						 * 
						 * */
						/*usuario.setCodigoUsuario("00750508");
						usuario.setCorreoCliente(usuarioSeguridad[0]);
						usuario.setCodTipoDocumento("002");
						usuario.setNumDocumento("40938292");
						usuario.setNombreUsuario("USUARIO QA 1");
						usuario.setCodigoServicio("7488");
						usuario.setCodigoInstanciaServicio("1304021");*/
						//usuario.setCodigoInstanciaServicio("0068");
						//usuario.setTipUsuario("recidencial");

						request.getSession().setAttribute("usuario", usuario);
					/*}
					else if(usuarioSeguridad[0].equals("987654321"))
					{
						usuario.setCodigoUsuario("00012895");

						usuario.setCodTipoDocumento("001");
						usuario.setNumDocumento("20337564373");
						usuario.setNombreUsuario("USUARIO QA 2");
						usuario.setCodigoServicio("0079");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}
					else if(usuarioSeguridad[0].equals("123459876"))
					{
						//CONSULTA DE RECARGAS
						usuario.setCodigoUsuario("01203317");

						usuario.setCodTipoDocumento("");
						usuario.setNumDocumento("");
						usuario.setNombreUsuario("USUARIO QA 3");
						usuario.setCodigoServicio("1");
						
						//usuario.setTipUsuario("recidencial");
						request.getSession().setAttribute("usuario", usuario);
					}
					else if(usuarioSeguridad[0].equals("lucarf@hildebrando.com"))
					{
						/*CLIENTE QUE CONSULTA LAS OPCIONES DE:
						 * CONSULTAR CLIENTE
						 * ACTUALIZAR CLIENTE
						 * 
						 * */
						/*usuario.setCodigoUsuario("00750508");
						usuario.setCodigoUsuario("00009207");
						usuario.setCorreoCliente(usuarioSeguridad[0]);
						usuario.setCodTipoDocumento("002");
						usuario.setNumDocumento("08208754");
						usuario.setNombreUsuario("Ana Maria");
						usuario.setCodigoServicio("8479");
						usuario.setCodigoInstanciaServicio("1304021");
						//usuario.setCodigoInstanciaServicio("0068");
						//usuario.setTipUsuario("Recidencial");
						
						request.getSession().setAttribute("usuario", usuario);
					}
					*/
					target = new SimpleUrlAuthenticationSuccessHandler(PropertiesCAEF.URL_DEFAULT);
				}
				
	            target.onAuthenticationSuccess(request, response, auth);
	    }

	    public void proceed(HttpServletRequest request, 
	        HttpServletResponse response, Authentication auth) throws IOException,ServletException {
	        target.onAuthenticationSuccess(request, response, auth);
	    }
	
	
}
