package com.btg.claro.lbs.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consultaLBS complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="consultaLBS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parametro" type="{http://endpoint.ws.LBS.claro.btg.com/}parametroConsulta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="consultaLBS",propOrder={"parametro"})
public class ConsultaLBS{

	protected ParametroConsulta parametro;

	/**
	 * Gets the value of the parametro property.
	 * 
	 * @return possible object is {@link ParametroConsulta }
	 * 
	 */
	public ParametroConsulta getParametro(){
		return parametro;
	}

	/**
	 * Sets the value of the parametro property.
	 * 
	 * @param value
	 *            allowed object is {@link ParametroConsulta }
	 * 
	 */
	public void setParametro(ParametroConsulta value){
		this.parametro=value;
	}

}
