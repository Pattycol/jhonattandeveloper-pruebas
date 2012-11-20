package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.EstadoCuentaService;

public class EstadoCuentaServiceTest {
	
	private EstadoCuentaService estadoCuentaService;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		estadoCuentaService = (EstadoCuentaService) context.getBean("estadoCuentaService");
	}

	@Test
	public void getConsultarCabeceraEstadoCuentaTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getConsultarEstadoCuentaTest() {
		fail("Not yet implemented");
	}

}
