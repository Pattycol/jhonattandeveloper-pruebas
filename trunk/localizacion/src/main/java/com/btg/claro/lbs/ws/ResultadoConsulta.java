package com.btg.claro.lbs.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for resultadoConsulta complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="resultadoConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codResultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaResultadoDetalle" type="{http://endpoint.ws.LBS.claro.btg.com/}resultadoDetalleConsulta" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="resultadoConsulta",propOrder={"codResultado","listaResultadoDetalle","mensaje"})
public class ResultadoConsulta{

	protected String codResultado;
	@XmlElement(nillable=true)
	protected List<ResultadoDetalleConsulta> listaResultadoDetalle;
	protected String mensaje;

	/**
	 * Gets the value of the codResultado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodResultado(){
		return codResultado;
	}

	/**
	 * Sets the value of the codResultado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodResultado(String value){
		this.codResultado=value;
	}

	/**
	 * Gets the value of the listaResultadoDetalle property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the listaResultadoDetalle property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getListaResultadoDetalle().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ResultadoDetalleConsulta }
	 * 
	 * 
	 */
	public List<ResultadoDetalleConsulta> getListaResultadoDetalle(){
		if(listaResultadoDetalle == null){
			listaResultadoDetalle=new ArrayList<ResultadoDetalleConsulta>();
		}
		return this.listaResultadoDetalle;
	}

	/**
	 * Gets the value of the mensaje property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMensaje(){
		return mensaje;
	}

	/**
	 * Sets the value of the mensaje property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMensaje(String value){
		this.mensaje=value;
	}

}
