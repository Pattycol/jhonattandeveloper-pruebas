package com.stconsulting.lbsws.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for parametroConsulta complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="parametroConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codTipoConsulta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codTipoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaMobileDestino" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mobileOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametroConsulta", propOrder = { "codTipoConsulta",
		"codTipoRespuesta", "listaMobileDestino", "mobileOrigen" })
public class ParametroConsulta {

	protected String codTipoConsulta;
	protected String codTipoRespuesta;
	@XmlElement(nillable = true)
	protected List<String> listaMobileDestino;
	protected String mobileOrigen;

	/**
	 * Gets the value of the codTipoConsulta property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodTipoConsulta() {
		return codTipoConsulta;
	}

	/**
	 * Sets the value of the codTipoConsulta property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodTipoConsulta(String value) {
		this.codTipoConsulta = value;
	}

	/**
	 * Gets the value of the codTipoRespuesta property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodTipoRespuesta() {
		return codTipoRespuesta;
	}

	/**
	 * Sets the value of the codTipoRespuesta property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodTipoRespuesta(String value) {
		this.codTipoRespuesta = value;
	}

	/**
	 * Gets the value of the listaMobileDestino property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the listaMobileDestino property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getListaMobileDestino().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getListaMobileDestino() {
		if (listaMobileDestino == null) {
			listaMobileDestino = new ArrayList<String>();
		}
		return this.listaMobileDestino;
	}

	/**
	 * Gets the value of the mobileOrigen property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobileOrigen() {
		return mobileOrigen;
	}

	/**
	 * Sets the value of the mobileOrigen property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobileOrigen(String value) {
		this.mobileOrigen = value;
	}

}
