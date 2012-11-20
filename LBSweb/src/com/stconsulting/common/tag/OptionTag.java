/*
 * Tag.java
 *
 * Created on June 4, 2004, 5:36 PM
 */ 

package com.stconsulting.common.tag;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import com.stconsulting.common.bean.*; 
import com.stconsulting.common.list.*; 
import com.stconsulting.common.service.*;

/**
 *
 * @author  Administrator
 */
public class OptionTag extends TagSupport{
    
    /**
	 * 
	 */
	private static final long serialVersionUID=-4245366507137763058L;

	/**
     * Holds value of property id.
     */
    private String id;
    
    /**
     * Holds value of property name.
     */
    private String name;
    
    /**
     * Holds value of property value.
     */
    private String value="-1";
    
    /**
     * Holds value of property disable.
     */
    private String disable;
    
    /**
     * Holds value of property onChange.
     */
    private String onChange;
    
    /**
     * Holds value of property className.
     */
    private String className;
    
    /** Creates a new instance of Tag */
    public int doStartTag() throws JspException  {
        try{
            JspWriter out = pageContext.getOut();
            OptionService service = new OptionService();
            OptionList list = service.select(id);
            out.print("<select name='"+name+"' ");
            if(onChange!=null)
                out.print("onChange='"+onChange+"'" );
            out.print("class='"+className+"' "); 
            out.println(">");
            out.println("\t<option value=''>--- Seleccione ---</option>");  
            String ch = "";
            for(int i=0;i<list.size();i++){
                Option op = list.getOption(i);
                ch = (value.equals(op.getCodigo()))?"selected":"";
                out.print("\t<option "+ch+" value='" + op.getCodigo() + "'>");
                out.println(op.getDescripcion()+"</option>");
            }
            out.println("</select>");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    public int doEndTag() {
        return EVAL_PAGE;
    }
    
    /**
     * Getter for property id.
     * @return Value of property id.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Setter for property id.
     * @param id New value of property id.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Setter for property name.
     * @param name New value of property name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Getter for property value.
     * @return Value of property value.
     */
    public String getValue() {
        return this.value;
    }
    
    /**
     * Setter for property value.
     * @param value New value of property value.
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * Getter for property disable.
     * @return Value of property disable.
     */
    public String getDisable() {
        return this.disable;
    }
    
    /**
     * Setter for property disable.
     * @param disable New value of property disable.
     */
    public void setDisable(String disable) {
        this.disable = disable;
    }
    
    /**
     * Getter for property onChange.
     * @return Value of property onChange.
     */
    public String getOnChange() {
        return this.onChange;
    }
    
    /**
     * Setter for property onChange.
     * @param onChange New value of property onChange.
     */
    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }
    
    /**
     * Getter for property className.
     * @return Value of property className.
     */
    public String getClassName() { 
        return this.className;
    }
    
    /**
     * Setter for property className.
     * @param className New value of property className.
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
}
