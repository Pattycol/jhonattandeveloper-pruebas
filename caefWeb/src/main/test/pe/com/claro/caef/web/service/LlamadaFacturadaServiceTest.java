package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.LlamadaFacturadaService;

public class LlamadaFacturadaServiceTest {

	private LlamadaFacturadaService llamadaFacturadaService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		llamadaFacturadaService = (LlamadaFacturadaService) context.getBean("llamadaFacturadaService");
	}

	@Test
	public void getConsultarListaLlamadasFacturadasTest() {
		fail("Not yet implemented");
	}

}
