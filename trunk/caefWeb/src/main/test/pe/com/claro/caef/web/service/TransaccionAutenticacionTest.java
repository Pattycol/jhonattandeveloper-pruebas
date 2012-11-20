package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.beans.NuevoUsuario;
import pe.com.claro.caef.web.ws.TransaccionAutenticacionWS;

public class TransaccionAutenticacionTest {

	private TransaccionAutenticacionWS transaccionAutenticacionWS; 
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"caefWeb-service.xml","classpath:caefWeb-ws.xml"});
		transaccionAutenticacionWS = (TransaccionAutenticacionWS) context.getBean("transaccionAutenticacionWS");
	}

	@Test
	public void test() {
		NuevoUsuario nuevoUsuario = new NuevoUsuario();
		nuevoUsuario.setTxId("9999999999");
		nuevoUsuario.setTelefono("986625066");
		
		//transaccionAutenticacionWS.nuevoUsuario(nuevoUsuario);
		
	}

}
