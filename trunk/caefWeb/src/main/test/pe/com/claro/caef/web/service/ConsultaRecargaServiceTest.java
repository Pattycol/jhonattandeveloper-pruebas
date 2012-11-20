package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.ConsultaRecargaService;

public class ConsultaRecargaServiceTest {
	
	private ConsultaRecargaService consultaRecargaService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		consultaRecargaService = (ConsultaRecargaService) context.getBean("consultaRecargaService");
	}

	@Test
	public void getConsultarListRecargasTest() {
		fail("Not yet implemented");
	}

}
