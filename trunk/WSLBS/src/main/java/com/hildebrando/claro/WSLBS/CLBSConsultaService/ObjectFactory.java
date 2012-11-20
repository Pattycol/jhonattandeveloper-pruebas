package com.hildebrando.claro.WSLBS.CLBSConsultaService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.stconsulting.lbsws.ws package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _EnviarPasswordSMSResponse_QNAME = new QName("http://endpoint.ws.LBS.claro.btg.com/", "enviarPasswordSMSResponse");
	private final static QName _EnviarPasswordSMS_QNAME = new QName("http://endpoint.ws.LBS.claro.btg.com/", "enviarPasswordSMS");
	  	   
	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.stconsulting.lbsws.ws
	 * 
	 */
	public ObjectFactory() {
	}

	

	
	  /**
     * Create an instance of {@link EnviarPasswordSMSResponse }
     * 
     */
    public EnviarPasswordSMSResponse createEnviarPasswordSMSResponse() {
        return new EnviarPasswordSMSResponse();
    }
    
    /**
     * Create an instance of {@link EnviarPasswordSMS }
     * 
     */
    public EnviarPasswordSMS createEnviarPasswordSMS() {
        return new EnviarPasswordSMS();
    }

	

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarPasswordSMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.ws.LBS.claro.btg.com/", name = "enviarPasswordSMSResponse")
    public JAXBElement<EnviarPasswordSMSResponse> createEnviarPasswordSMSResponse(EnviarPasswordSMSResponse value) {
        return new JAXBElement<EnviarPasswordSMSResponse>(_EnviarPasswordSMSResponse_QNAME, EnviarPasswordSMSResponse.class, null, value);
    }
    

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarPasswordSMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.ws.LBS.claro.btg.com/", name = "enviarPasswordSMS")
    public JAXBElement<EnviarPasswordSMS> createEnviarPasswordSMS(EnviarPasswordSMS value) {
        return new JAXBElement<EnviarPasswordSMS>(_EnviarPasswordSMS_QNAME, EnviarPasswordSMS.class, null, value);
    }

}