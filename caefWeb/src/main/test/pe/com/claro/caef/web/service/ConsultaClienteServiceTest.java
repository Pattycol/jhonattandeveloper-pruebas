package pe.com.claro.caef.web.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.com.claro.caef.web.action.filter.ConsultarContactosClienteFilter;
import pe.com.claro.caef.web.beans.ContactoCliente;
/*import pe.com.claro.caef.web.beans.consultaclientes.ConsultaClientes_Service;
import pe.com.claro.caef.web.beans.consultaclientes.ConsultarContactosClienteRequest;
import pe.com.claro.caef.web.beans.consultaclientes.ConsultarContactosClienteResponse;
import pe.com.claro.caef.web.beans.consultaclientes.ObjectFactory;*/
import pe.com.claro.caef.web.services.ConsultaClienteService;

public class ConsultaClienteServiceTest {

	private ConsultaClienteService consultaClienteService;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String []{"classpath:caefWeb-ws.xml","classpath:caefWeb-service.xml"});
		consultaClienteService = (ConsultaClienteService) context.getBean("consultaClienteService");
	}

	//@Test
	public void getConsultarSucursalClienteTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getConsultarContactosClienteTest() throws Exception {
		
		
		/*ConsultarContactosClienteFilter cccf = new ConsultarContactosClienteFilter();
		cccf.setCodCliente("01109438");
		cccf.setCodProducto("1234");
		cccf.setCodServicio("1234");
		
		List<ContactoCliente> lst = consultaClienteService.getConsultarContactosCliente(cccf);
		
				 */
		
		/*ConsultaClientes_Service cs = new ConsultaClientes_Service();
		ConsultarContactosClienteRequest ccr = new ConsultarContactosClienteRequest();
		ObjectFactory of = new ObjectFactory();
		ccr.setCodigoCliente(of.createConsultarContactosClienteRequestCodigoCliente("01109438"));
		ccr.setCodigoProducto(of.createConsultarContactosClienteRequestCodigoProducto("1234"));
		ccr.setCodigoServicio(of.createConsultarContactosClienteRequestCodigoServicio("1234"));
		
		ConsultarContactosClienteResponse cccr =   cs.getConsultaClientesSOAP().consultarContactosCliente(ccr);
		*/
	}
	
	//@Test
	public void getConsultarDatosGenClienteTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	public void getConsultarServiciosClienteTest() {
		fail("Not yet implemented");
	}

}
