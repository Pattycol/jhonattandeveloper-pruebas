package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.ActualizaClienteService;

public class ActualizaClienteServiceTest {
	
	private ActualizaClienteService actualizaClienteService;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		actualizaClienteService = (ActualizaClienteService) context.getBean("actualizaClienteService");
	}

	@Test
	public void getActualizarDatosClienteTest() {
		fail("Not yet implemented");
	}

}
