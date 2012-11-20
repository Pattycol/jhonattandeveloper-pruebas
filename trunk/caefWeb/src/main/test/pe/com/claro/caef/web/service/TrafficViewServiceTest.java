package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.services.TrafficViewService;

public class TrafficViewServiceTest {

	private TrafficViewService trafficViewService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		trafficViewService = (TrafficViewService) context.getBean("trafficViewService");
	}

	@Test
	public void getConsultarListTrafficViewTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getObtenerUrlTrafficViewTest() {
		fail("Not yet implemented");
	}

}
