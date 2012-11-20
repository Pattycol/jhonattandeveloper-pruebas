package com.btg.claro.lbs.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for resultadoDetalleConsulta complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="resultadoDetalleConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codCeldaUbicacion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codOperacion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mobileDestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoDetalleConsulta", propOrder = { "codCeldaUbicacion",
		"codOperacion", "mobileDestino", "resultado" })
public class ResultadoDetalleConsulta {

	protected Integer codCeldaUbicacion;
	protected Integer codOperacion;
	protected String mobileDestino;
	protected String resultado;

	/**
	 * Gets the value of the codCeldaUbicacion property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCodCeldaUbicacion() {
		return codCeldaUbicacion;
	}

	/**
	 * Sets the value of the codCeldaUbicacion property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCodCeldaUbicacion(Integer value) {
		this.codCeldaUbicacion = value;
	}

	/**
	 * Gets the value of the codOperacion property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getCodOperacion() {
		return codOperacion;
	}

	/**
	 * Sets the value of the codOperacion property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setCodOperacion(Integer value) {
		this.codOperacion = value;
	}

	/**
	 * Gets the value of the mobileDestino property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobileDestino() {
		return mobileDestino;
	}

	/**
	 * Sets the value of the mobileDestino property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobileDestino(String value) {
		this.mobileDestino = value;
	}

	/**
	 * Gets the value of the resultado property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the value of the resultado property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResultado(String value) {
		this.resultado = value;
	}

}
