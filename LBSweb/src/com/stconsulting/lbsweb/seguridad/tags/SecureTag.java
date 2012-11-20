package com.stconsulting.lbsweb.seguridad.tags;

import javax.servlet.jsp.tagext.TagSupport;

public class SecureTag extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID=-2194877545224287116L;
	
	private String destination;
	private String code;
	private String name;
	private String image;
	private String estilo;
	private String valor;
	private String permisos;

	private String modulo="";
	private String opcion="";
	private String privilegio="";

	public SecureTag(){
		destination="";
		code="";
		name="";
		image="";
		estilo="";
		valor="";
		permisos="";
	}

	/*
	 * public int doStartTag() throws JspException { try { JspWriter out =
	 * pageContext.getOut(); if(validate()) out.println(getFunction()); }
	 * catch(Exception e) { log.debug(e.getMessage()); throw new
	 * JspException(e.getMessage()); } return 0; }
	 */
	public int doEndTag(){
		return 6;
	}

	public String getDestination(){
		return destination;
	}

	public void setDestination(String destination){
		this.destination=destination;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getImage(){
		return image;
	}

	public void setImage(String image){
		this.image=image;
	}

	public String getPermisos(){
		return permisos;
	}

	public void setPermisos(String permisos){
		this.permisos=permisos;
	}

	public String getEstilo(){
		return estilo;
	}

	public void setEstilo(String estilo){
		this.estilo=estilo;
	}

	public String getValor(){
		return valor;
	}

	public void setValor(String valor){
		this.valor=valor;
	}

	/*
	 * public boolean validate() {
	 * 
	 * //ArrayList lista =
	 * (ArrayList)pageContext.getSession().getAttribute(permisos); Usuario
	 * usuario =
	 * (Usuario)pageContext.getSession().getAttribute(Constantes.USUARIO_LOGUEADO
	 * );
	 * 
	 * PerfilService service = new PerfilService(); ArrayList lista =
	 * service.getPrivilegios(modulo,opcion,usuario);
	 * 
	 * //ArrayList lista =
	 * (ArrayList)pageContext.getSession().getAttribute(Constantes.PRIVILEGIOS);
	 * 
	 * log.debug(" ********************************** ");
	 * log.debug(" SecureTag lista ==>"+lista);
	 * log.debug(" code es :"+code);
	 * 
	 * boolean encontro = false;
	 * 
	 * if(lista != null) { log.debug(" Lista size ..."+lista.size());
	 * 
	 * for(int i = 0; i < lista.size() && !encontro; i++) { Privilegio priv =
	 * (Privilegio)lista.get(i);
	 * 
	 * if (priv.getId().equals(privilegio)){ encontro = true; } } } return
	 * encontro; }
	 * 
	 * public String getFunction() { //String cad = "<img src='" + image +
	 * "' border='0' onClick='javascript:" + name +
	 * "' style='cursor:hand'> &nbsp;"; //<input name="button3" type="button"
	 * class="boton" onClick="javascript:eliminar()" value="Eliminar">
	 * 
	 * String cad
	 * ="<input type='button' class='"+estilo+"' onClick='javascript:"
	 * +name+"' value='"+valor+"' style='cursor:hand'>";
	 * 
	 * 
	 * return cad; }
	 * 
	 * /** Getter for property modulo.
	 * 
	 * @return Value of property modulo.
	 */
	public java.lang.String getModulo(){
		return modulo;
	}

	/**
	 * Setter for property modulo.
	 * 
	 * @param modulo
	 *            New value of property modulo.
	 */
	public void setModulo(java.lang.String modulo){
		this.modulo=modulo;
	}

	/**
	 * Getter for property opcion.
	 * 
	 * @return Value of property opcion.
	 */
	public java.lang.String getOpcion(){
		return opcion;
	}

	/**
	 * Setter for property opcion.
	 * 
	 * @param opcion
	 *            New value of property opcion.
	 */
	public void setOpcion(java.lang.String opcion){
		this.opcion=opcion;
	}

	/**
	 * Getter for property privilegio.
	 * 
	 * @return Value of property privilegio.
	 */
	public java.lang.String getPrivilegio(){
		return privilegio;
	}

	/**
	 * Setter for property privilegio.
	 * 
	 * @param privilegio
	 *            New value of property privilegio.
	 */
	public void setPrivilegio(java.lang.String privilegio){
		this.privilegio=privilegio;
	}

}
