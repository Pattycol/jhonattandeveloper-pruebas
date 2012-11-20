package com.stconsulting.lbsweb.seguridad.bean;

public class Opcion implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID=4476256886509336614L;
	
	private String id="";
	private String idModulo="";
	private String nombre="";
	private String descripcion="";
	private String ruta="";
	private String codOpcion;

	// private ArrayList privilegiosList = new ArrayList();

	public Opcion(){
	}

	/**
	 * Getter for property id.
	 * 
	 * @return Value of property id.
	 */
	public java.lang.String getId(){
		return id;
	}

	/**
	 * Setter for property id.
	 * 
	 * @param id
	 *            New value of property id.
	 */
	public void setId(java.lang.String id){
		this.id=id;
	}

	/**
	 * Getter for property idModulo.
	 * 
	 * @return Value of property idModulo.
	 */
	public java.lang.String getIdModulo(){
		return idModulo;
	}

	/**
	 * Setter for property idModulo.
	 * 
	 * @param idModulo
	 *            New value of property idModulo.
	 */
	public void setIdModulo(java.lang.String idModulo){
		this.idModulo=idModulo;
	}

	/**
	 * Getter for property descripcion.
	 * 
	 * @return Value of property descripcion.
	 */
	public java.lang.String getDescripcion(){
		return descripcion;
	}

	/**
	 * Setter for property descripcion.
	 * 
	 * @param descripcion
	 *            New value of property descripcion.
	 */
	public void setDescripcion(java.lang.String descripcion){
		this.descripcion=descripcion;
	}

	/**
	 * Getter for property nombre.
	 * 
	 * @return Value of property nombre.
	 */
	public java.lang.String getNombre(){
		return nombre;
	}

	/**
	 * Setter for property nombre.
	 * 
	 * @param nombre
	 *            New value of property nombre.
	 */
	public void setNombre(java.lang.String nombre){
		this.nombre=nombre;
	}

	/**
	 * Getter for property ruta.
	 * 
	 * @return Value of property ruta.
	 */
	public java.lang.String getRuta(){
		return ruta;
	}

	/**
	 * Setter for property ruta.
	 * 
	 * @param ruta
	 *            New value of property ruta.
	 */
	public void setRuta(java.lang.String ruta){
		this.ruta=ruta;
	}

	/**
	 * Getter for property codOpcion.
	 * 
	 * @return Value of property codOpcion.
	 */
	public java.lang.String getCodOpcion(){
		return codOpcion;
	}

	/**
	 * Setter for property codOpcion.
	 * 
	 * @param codOpcion
	 *            New value of property codOpcion.
	 */
	public void setCodOpcion(java.lang.String codOpcion){
		this.codOpcion=codOpcion;
	}

}
