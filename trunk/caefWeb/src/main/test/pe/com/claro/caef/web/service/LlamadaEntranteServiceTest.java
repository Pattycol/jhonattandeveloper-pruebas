package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.LlamadaEntranteService;

public class LlamadaEntranteServiceTest {

	private LlamadaEntranteService llamadaEntranteService; 
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		llamadaEntranteService = (LlamadaEntranteService) context.getBean("llamadaEntranteService");
	}
	
	@Test
	public void getConsultarListaLlamadasEntrantesTest() {
		fail("Not yet implemented");
	}

}
