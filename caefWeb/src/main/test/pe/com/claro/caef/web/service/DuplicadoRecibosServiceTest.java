package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.DuplicadoRecibosService;

public class DuplicadoRecibosServiceTest {

	private DuplicadoRecibosService duplicadoRecibosService;
	@Before
	public void setUp() throws Exception {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		
		duplicadoRecibosService = (DuplicadoRecibosService) context.getBean("duplicadoRecibosService");
	}

	@Test
	public void getConsultarReciboClienteTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getConsultarDetalleReciboClienteTest() {
		fail("Not yet implemented");
	}

}
