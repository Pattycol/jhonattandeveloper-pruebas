package com.btg.claro.lbs.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for obtenerUbicacion complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerUbicacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="obtenerUbicacion",propOrder={"telefono"})
public class ObtenerUbicacion{

	protected String telefono;

	/**
	 * Gets the value of the telefono property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTelefono(){
		return telefono;
	}

	/**
	 * Sets the value of the telefono property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTelefono(String value){
		this.telefono=value;
	}

}
