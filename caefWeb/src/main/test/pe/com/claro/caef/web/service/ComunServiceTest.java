package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.ComunService;

public class ComunServiceTest {

	private ComunService comunService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		comunService = (ComunService) context.getBean("comunService");
	}

	@Test
	public void getConsultarNumerosTelefonicosTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getConsultarDatosMaestrosTest() {
		fail("Not yet implemented");
	}

}
