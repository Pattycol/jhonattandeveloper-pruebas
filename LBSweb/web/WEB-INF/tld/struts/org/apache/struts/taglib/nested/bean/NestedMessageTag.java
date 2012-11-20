package org.apache.struts.taglib.nested.bean;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.IterationTag;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *  Generated tag class.
 */
public class NestedMessageTag extends TagSupport {

    /**
     * property declaration for tag attribute: arg0.
     */    
    private java.lang.String arg0;    

    /**
     * property declaration for tag attribute: arg1.
     */    
    private java.lang.String arg1;
    
    /**
     * property declaration for tag attribute: arg2.
     */    
    private java.lang.String arg2;
    
    /**
     * property declaration for tag attribute: arg3.
     */    
    private java.lang.String arg3;
    
    /**
     * property declaration for tag attribute: arg4.
     */    
    private java.lang.String arg4;
    
    /**
     * property declaration for tag attribute: bundle.
     */    
    private java.lang.String bundle;
    
    /**
     * property declaration for tag attribute: key.
     */    
    private java.lang.String key;
    
    /**
     * property declaration for tag attribute: locale.
     */    
    private java.lang.String locale;
    
    /**
     * property declaration for tag attribute: name.
     */    
    private java.lang.String name;
    
    /**
     * property declaration for tag attribute: property.
     */    
    private java.lang.String property;
    
    /**
     * property declaration for tag attribute: scope.
     */    
    private java.lang.String scope;
    
    public NestedMessageTag() {
        super();
    }
    
    
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   User methods.                                          ///
    ///                                                          ///
    ///   Modify these methods to customize your tag handler.    ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    
    
    //
    // methods called from doStartTag()
    //
    /**
     *  
     * Fill in this method to perform other operations from doStartTag().
     * 
     */
    public void otherDoStartTagOperations()  {
    
        //
        // TODO: code that performs other operations in doStartTag
        //       should be placed here.
        //       It will be called after initializing variables, 
        //       finding the parent, setting IDREFs, etc, and 
        //       before calling theBodyShouldBeEvaluated(). 
        //
        //       For example, to print something out to the JSP, use the following:
        //
        //   try {
        //       JspWriter out = pageContext.getOut();
        //       out.println("something");
        //   } catch (java.io.IOException ex) {
        //       // do something
        //   }
        //
        //
        

    }
    
    /**
     *  
     * Fill in this method to determine if the tag body should be evaluated
     * Called from doStartTag().
     * 
     */
    public boolean theBodyShouldBeEvaluated()  {

        //
        // TODO: code that determines whether the body should be
        //       evaluated should be placed here.
        //       Called from the doStartTag() method.
        //
        return true;

    }
    
    
    //
    // methods called from doEndTag()
    //
    /**
     *  
     * Fill in this method to perform other operations from doEndTag().
     * 
     */
    public void otherDoEndTagOperations()  {
    
        //
        // TODO: code that performs other operations in doEndTag
        //       should be placed here.
        //       It will be called after initializing variables, 
        //       finding the parent, setting IDREFs, etc, and 
        //       before calling shouldEvaluateRestOfPageAfterEndTag(). 
        //
        

    }
    
    /**
     *  
     * Fill in this method to determine if the rest of the JSP page
     * should be generated after this tag is finished.
     * Called from doEndTag().
     * 
     */
    public boolean shouldEvaluateRestOfPageAfterEndTag()  {

        //
        // TODO: code that determines whether the rest of the page
        //       should be evaluated after the tag is processed
        //       should be placed here.
        //       Called from the doEndTag() method.
        //
        return true;

    }
    
    
    ////////////////////////////////////////////////////////////////
    ///                                                          ///
    ///   Tag Handler interface methods.                         ///
    ///                                                          ///
    ///   Do not modify these methods; instead, modify the       ///
    ///   methods that they call.                                ///
    ///                                                          ///
    ////////////////////////////////////////////////////////////////
    
    
    /**
     * .
     *
     * This method is called when the JSP engine encounters the start tag,
     * after the attributes are processed.
     * Scripting variables (if any) have their values set here.
     * @return EVAL_BODY_INCLUDE if the JSP engine should evaluate the tag body, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doStartTag() throws JspException, JspException {
        otherDoStartTagOperations();
        
        if (theBodyShouldBeEvaluated()) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
    
    /**
     * .
     *
     *
     * This method is called after the JSP engine finished processing the tag.
     * @return EVAL_PAGE if the JSP engine should continue evaluating the JSP page, otherwise return SKIP_PAGE.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */
    public int doEndTag() throws JspException, JspException {
        otherDoEndTagOperations();
        
        if (shouldEvaluateRestOfPageAfterEndTag()) {
            return EVAL_PAGE;
        } else {
            return SKIP_PAGE;
        }
    }
    
    public java.lang.String getArg0() {
        return arg0;
    }
    
    public void setArg0(java.lang.String value) {
        arg0 = value;
    }
    
    public java.lang.String getArg1() {
        return arg1;
    }
    
    public void setArg1(java.lang.String value) {
        arg1 = value;
    }
    
    public java.lang.String getArg2() {
        return arg2;
    }
    
    public void setArg2(java.lang.String value) {
        arg2 = value;
    }
    
    public java.lang.String getArg3() {
        return arg3;
    }
    
    public void setArg3(java.lang.String value) {
        arg3 = value;
    }
    
    public java.lang.String getArg4() {
        return arg4;
    }
    
    public void setArg4(java.lang.String value) {
        arg4 = value;
    }
    
    public java.lang.String getBundle() {
        return bundle;
    }
    
    public void setBundle(java.lang.String value) {
        bundle = value;
    }
    
    public java.lang.String getKey() {
        return key;
    }
    
    public void setKey(java.lang.String value) {
        key = value;
    }
    
    public java.lang.String getLocale() {
        return locale;
    }
    
    public void setLocale(java.lang.String value) {
        locale = value;
    }
    
    public java.lang.String getName() {
        return name;
    }
    
    public void setName(java.lang.String value) {
        name = value;
    }
    
    public java.lang.String getProperty() {
        return property;
    }
    
    public void setProperty(java.lang.String value) {
        property = value;
    }
    
    public java.lang.String getScope() {
        return scope;
    }
    
    public void setScope(java.lang.String value) {
        scope = value;
    }
    
}
