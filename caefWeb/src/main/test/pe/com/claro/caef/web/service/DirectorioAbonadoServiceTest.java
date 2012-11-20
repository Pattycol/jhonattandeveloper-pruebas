package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.DirectorioAbonadoService;

public class DirectorioAbonadoServiceTest {

	private DirectorioAbonadoService directorioAbonadoService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		directorioAbonadoService = (DirectorioAbonadoService) context.getBean("directorioAbonadoService");
	}

	@Test
	public void getConsultarNumTelefonicoAbonadoTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getRegistrarPublicacionDirectorioAbonadoTest() {
		fail("Not yet implemented");
	}

}
