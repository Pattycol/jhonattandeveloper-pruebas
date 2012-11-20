package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.ConsultaClienteService;
import pe.com.claro.caef.web.services.EnviarCorreoService;
import pe.com.claro.caef.web.util.CorreoDatos;

public class EnviarCorreoServiceTest {

	private EnviarCorreoService enviarCorreoService;
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"classpath:caefWeb-ws.xml","classpath:caefWeb-service.xml"});
		enviarCorreoService = (EnviarCorreoService) context.getBean("enviarCorreoService");
	
	}

	@Test
	public void enviarCorreoTest() {
		CorreoDatos cd = new CorreoDatos();
		
		StringBuffer sb = new StringBuffer();
		sb.append("MAS TEST");
		cd.setAsunto("TEST TEST");
		cd.setContenido(enviarCorreoService.contenidoCorreo("MENSAJE BIENVENIDA", sb));
		cd.setDestinatario("lucarf@hildebrando.com");
	
		try{
			enviarCorreoService.enviarCorreo(cd);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
