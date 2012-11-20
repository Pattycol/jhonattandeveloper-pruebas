package com.stconsulting.lbsws.ws;

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
 *         &lt;element name="arg0" type="{http://endpoints.LBSws.btg.com/}parametroConsulta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultaLBS", propOrder = { "parametro", "arg0" })
public class ConsultaLBS {

	protected ParametroConsulta arg0;

	protected ParametroConsulta parametro;
	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link ParametroConsulta }
	 * 
	 */
	public ParametroConsulta getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link ParametroConsulta }
	 * 
	 */
	public void setArg0(ParametroConsulta value) {
		this.arg0 = value;
	}

	public ParametroConsulta getParametro(){
		return parametro;
	}

	public void setParametro(ParametroConsulta parametro){
		this.parametro=parametro;
	}

}
