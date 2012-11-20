package com.btg.claro.lbs.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.btg.claro.lbs.ws.endpoint package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory{

	private final static QName _ConsultaLBSResponse_QNAME=new QName("http://endpoint.ws.LBS.claro.btg.com/","consultaLBSResponse");
	private final static QName _ConsultaLBS_QNAME=new QName("http://endpoint.ws.LBS.claro.btg.com/","consultaLBS");
	private final static QName _ObtenerUbicacionResponse_QNAME=new QName("http://endpoint.ws.LBS.claro.btg.com/","obtenerUbicacionResponse");
	private final static QName _ObtenerUbicacion_QNAME=new QName("http://endpoint.ws.LBS.claro.btg.com/","obtenerUbicacion");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.btg.claro.lbs.ws.endpoint
	 * 
	 */
	public ObjectFactory(){
	}

	/**
	 * Create an instance of {@link ResultadoDetalleConsulta }
	 * 
	 */
	public ResultadoDetalleConsulta createResultadoDetalleConsulta(){
		return new ResultadoDetalleConsulta();
	}

	/**
	 * Create an instance of {@link ConsultaLBS }
	 * 
	 */
	public ConsultaLBS createConsultaLBS(){
		return new ConsultaLBS();
	}

	/**
	 * Create an instance of {@link ConsultaLBSResponse }
	 * 
	 */
	public ConsultaLBSResponse createConsultaLBSResponse(){
		return new ConsultaLBSResponse();
	}

	/**
	 * Create an instance of {@link ResultadoConsulta }
	 * 
	 */
	public ResultadoConsulta createResultadoConsulta(){
		return new ResultadoConsulta();
	}

	/**
	 * Create an instance of {@link ObtenerUbicacion }
	 * 
	 */
	public ObtenerUbicacion createObtenerUbicacion(){
		return new ObtenerUbicacion();
	}

	/**
	 * Create an instance of {@link ParametroConsulta }
	 * 
	 */
	public ParametroConsulta createParametroConsulta(){
		return new ParametroConsulta();
	}

	/**
	 * Create an instance of {@link ObtenerUbicacionResponse }
	 * 
	 */
	public ObtenerUbicacionResponse createObtenerUbicacionResponse(){
		return new ObtenerUbicacionResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ConsultaLBSResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace="http://endpoint.ws.LBS.claro.btg.com/",name="consultaLBSResponse")
	public JAXBElement<ConsultaLBSResponse> createConsultaLBSResponse(ConsultaLBSResponse value){
		return new JAXBElement<ConsultaLBSResponse>(_ConsultaLBSResponse_QNAME,ConsultaLBSResponse.class,null,value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ConsultaLBS }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace="http://endpoint.ws.LBS.claro.btg.com/",name="consultaLBS")
	public JAXBElement<ConsultaLBS> createConsultaLBS(ConsultaLBS value){
		return new JAXBElement<ConsultaLBS>(_ConsultaLBS_QNAME,ConsultaLBS.class,null,value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ObtenerUbicacionResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace="http://endpoint.ws.LBS.claro.btg.com/",name="obtenerUbicacionResponse")
	public JAXBElement<ObtenerUbicacionResponse> createObtenerUbicacionResponse(ObtenerUbicacionResponse value){
		return new JAXBElement<ObtenerUbicacionResponse>(_ObtenerUbicacionResponse_QNAME,ObtenerUbicacionResponse.class,null,value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ObtenerUbicacion }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace="http://endpoint.ws.LBS.claro.btg.com/",name="obtenerUbicacion")
	public JAXBElement<ObtenerUbicacion> createObtenerUbicacion(ObtenerUbicacion value){
		return new JAXBElement<ObtenerUbicacion>(_ObtenerUbicacion_QNAME,ObtenerUbicacion.class,null,value);
	}

}
