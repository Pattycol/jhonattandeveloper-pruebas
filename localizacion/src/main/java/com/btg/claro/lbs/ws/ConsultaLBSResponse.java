package com.btg.claro.lbs.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consultaLBSResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="consultaLBSResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://endpoint.ws.LBS.claro.btg.com/}resultadoConsulta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="consultaLBSResponse",propOrder={"_return"})
public class ConsultaLBSResponse{

	@XmlElement(name="return")
	protected ResultadoConsulta _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link ResultadoConsulta }
	 * 
	 */
	public ResultadoConsulta getReturn(){
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link ResultadoConsulta }
	 * 
	 */
	public void setReturn(ResultadoConsulta value){
		this._return=value;
	}

}
