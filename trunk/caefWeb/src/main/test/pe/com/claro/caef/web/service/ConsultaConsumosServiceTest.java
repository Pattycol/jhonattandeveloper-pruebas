package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.ConsultaConsumosService;

public class ConsultaConsumosServiceTest {

	private ConsultaConsumosService consultaConsumosService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		consultaConsumosService = (ConsultaConsumosService) context.getBean("consultaConsumosService");
	}

	@Test
	public void getConsultarConsumoClienteTest() {
		fail("Not yet implemented");
	}

}
