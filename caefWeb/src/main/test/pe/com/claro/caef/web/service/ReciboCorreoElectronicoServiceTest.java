package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.ReciboCorreoElectronicoService;

public class ReciboCorreoElectronicoServiceTest {

	private ReciboCorreoElectronicoService reciboCorreoElectronicoService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		reciboCorreoElectronicoService = (ReciboCorreoElectronicoService) context.getBean("reciboCorreoElectronicoService");
	}

	@Test
	public void getConsultarGrupoFactRecibosTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getRegistrarActivacionReciboCorreoElectronicoTest() {
		fail("Not yet implemented");
	}

}
