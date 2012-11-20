package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.LlamadaNoFacturadaService;

public class LlamadaNoFacturadaServiceTest {

	private LlamadaNoFacturadaService llamadaNoFacturadaService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		llamadaNoFacturadaService = (LlamadaNoFacturadaService) context.getBean("llamadaNoFacturadaService");
	}

	@Test
	public void getConsultarListaLlamadasNofacturadasTest() {
		fail("Not yet implemented");
	}

}
