/*
 * Tag.java
 *
 * Created on June 4, 2004, 5:36 PM
 */

package com.stconsulting.common.tag;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import org.apache.log4j.Logger;

/**
 * 
 * @author Administrator
 */
public class FechaTag extends TagSupport{
	
	private Logger log=Logger.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID=8864838179236305364L;

	/** Holds value of property name. */
	private String name;

	/** Holds value of property pattern. */
	private String pattern="dd/MM/yyyy";

	/** Holds value of property value. */
	private Date value;

	/** Holds value of property styleClass. */
	private String styleClass="";

	/** Holds value of property disabled. */
	private String disabled="false";

	/** Holds value of property onChange. */
	private String onChange="";

	/** Holds value of property onBlur. */
	private String onBlur="";

	public FechaTag(){
		super();
	}

	/**
	 * Method called at start of tag.
	 * 
	 * @return SKIP_BODY
	 */
	public int doStartTag() throws JspException{
		try{
			JspWriter out=pageContext.getOut();
			Format f=new SimpleDateFormat(pattern);
			String salida="<input type='text' name='" + name + "' columns='12'  size='12' value='" + f.format(value) + "'" + " class='" + styleClass + "' ";
			if(disabled.equals("true")){
				salida=salida + " disabled ";
			}
			if(!onChange.trim().equals("")){
				salida=salida + " onchange='" + onChange + "' ";
			}
			if(!onBlur.trim().equals("")){
				salida=salida + " onblur='" + onBlur + "' ";
			}
			salida=salida + ">";
			if(!disabled.equals("true")){
				salida=salida + "<a href=\"javascript:calendario('" + name + "','" + pattern + "')\"> <img src='/LBSweb/image/calendario/calendario.gif'" + " border=\"0\" alt=\"Click para mostrar el calendario\"></a>";
			}
			out.print(salida);
		}

		catch(Exception e){
			log.debug(e.getMessage());
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY;
	}

	/**
	 * Method called at end of tag.
	 * 
	 * @return EVAL_PAGE
	 */
	public int doEndTag(){
		return EVAL_PAGE;
	}

	/**
	 * Getter for property name.
	 * 
	 * @return Value of property name.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Setter for property name.
	 * 
	 * @param name
	 *            New value of property name.
	 */
	public void setName(String name){
		this.name=name;
	}

	/**
	 * Getter for property pattern.
	 * 
	 * @return Value of property pattern.
	 */
	public String getPattern(){
		return this.pattern;
	}

	/**
	 * Setter for property pattern.
	 * 
	 * @param pattern
	 *            New value of property pattern.
	 */
	public void setPattern(String pattern){
		this.pattern=pattern;
	}

	/**
	 * Getter for property value.
	 * 
	 * @return Value of property value.
	 */
	public Date getValue(){
		return this.value;
	}

	/**
	 * Setter for property value.
	 * 
	 * @param value
	 *            New value of property value.
	 */
	public void setValue(Date value){
		this.value=value;
	}

	/**
	 * Getter for property styleClass.
	 * 
	 * @return Value of property styleClass.
	 */
	public String getStyleClass(){
		return this.styleClass;
	}

	/**
	 * Setter for property styleClass.
	 * 
	 * @param styleClass
	 *            New value of property styleClass.
	 */
	public void setStyleClass(String styleClass){
		this.styleClass=styleClass;
	}

	/**
	 * Getter for property disabled.
	 * 
	 * @return Value of property disabled.
	 */
	public String getDisabled(){
		return this.disabled;
	}

	/**
	 * Setter for property disabled.
	 * 
	 * @param disabled
	 *            New value of property disabled.
	 */
	public void setDisabled(String disabled){
		this.disabled=disabled;
	}

	/**
	 * Getter for property onChange.
	 * 
	 * @return Value of property onChange.
	 */
	public String getOnChange(){
		return this.onChange;
	}

	/**
	 * Setter for property onChange.
	 * 
	 * @param onChange
	 *            New value of property onChange.
	 */
	public void setOnChange(String onChange){
		this.onChange=onChange;
	}

	/**
	 * Getter for property onBlur.
	 * 
	 * @return Value of property onBlur.
	 * 
	 */
	public String getOnBlur(){
		return this.onBlur;
	}

	/**
	 * Setter for property onBlur.
	 * 
	 * @param onBlur
	 *            New value of property onBlur.
	 * 
	 */
	public void setOnBlur(String onBlur){
		this.onBlur=onBlur;
	}
}
