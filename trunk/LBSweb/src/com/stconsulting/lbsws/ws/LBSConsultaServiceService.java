package com.stconsulting.lbsws.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * LBSConsultaServiceService service = new LBSConsultaServiceService();
 * LBSConsultaService portType = service.getLBSConsultaServicePort();
 * portType.consultaLBS(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "LBSConsultaServiceService", targetNamespace = "http://endpoints.LBSws.btg.com/")
public class LBSConsultaServiceService extends Service {

	private final static URL LBSCONSULTASERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.stconsulting.lbsws.ws.LBSConsultaServiceService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.stconsulting.lbsws.ws.LBSConsultaServiceService.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://172.16.102.51:9997/LBSConsultaService?wsdl");
		} catch (MalformedURLException e) {
			logger
					.warning("Failed to create URL for the wsdl Location: 'http://172.16.102.51:9997/LBSConsultaService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		LBSCONSULTASERVICESERVICE_WSDL_LOCATION = url;
	}

	public LBSConsultaServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}
	
	public LBSConsultaServiceService(URL wsdlLocation, String serviceName) {
		super(wsdlLocation,new QName("http://endpoint.ws.LBS.claro.btg.com/",serviceName));
	}

	public LBSConsultaServiceService() {
		super(LBSCONSULTASERVICESERVICE_WSDL_LOCATION, new QName(
				"http://endpoint.ws.LBS.claro.btg.com/", "LBSEndpointService"));
	}

	/**
	 * 
	 * @return returns LBSConsultaService
	 */
	@WebEndpoint(name = "LBSConsultaServicePort")
	public LBSConsultaService getLBSConsultaServicePort() {
		return super.getPort(new QName("http://endpoint.ws.LBS.claro.btg.com/",
				"LBSConsultaServicePort"), LBSConsultaService.class);
	}

}