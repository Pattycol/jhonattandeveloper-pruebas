package org.apache.struts.taglib.html;

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
public class RewriteTag extends TagSupport {

    /**
     * property declaration for tag attribute: anchor.
     */    
    private java.lang.String anchor;    

    /**
     * property declaration for tag attribute: forward.
     */    
    private java.lang.String forward;
    
    /**
     * property declaration for tag attribute: href.
     */    
    private java.lang.String href;
    
    /**
     * property declaration for tag attribute: name.
     */    
    private java.lang.String name;
    
    /**
     * property declaration for tag attribute: page.
     */    
    private java.lang.String page;
    
    /**
     * property declaration for tag attribute: paramId.
     */    
    private java.lang.String paramId;
    
    /**
     * property declaration for tag attribute: paramName.
     */    
    private java.lang.String paramName;
    
    /**
     * property declaration for tag attribute: paramProperty.
     */    
    private java.lang.String paramProperty;
    
    /**
     * property declaration for tag attribute: paramScope.
     */    
    private java.lang.String paramScope;
    
    /**
     * property declaration for tag attribute: property.
     */    
    private java.lang.String property;
    
    /**
     * property declaration for tag attribute: scope.
     */    
    private java.lang.String scope;
    
    /**
     * property declaration for tag attribute: transaction.
     */    
    private java.lang.String transaction;
    
    public RewriteTag() {
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
    
    public java.lang.String getAnchor() {
        return anchor;
    }
    
    public void setAnchor(java.lang.String value) {
        anchor = value;
    }
    
    public java.lang.String getForward() {
        return forward;
    }
    
    public void setForward(java.lang.String value) {
        forward = value;
    }
    
    public java.lang.String getHref() {
        return href;
    }
    
    public void setHref(java.lang.String value) {
        href = value;
    }
    
    public java.lang.String getName() {
        return name;
    }
    
    public void setName(java.lang.String value) {
        name = value;
    }
    
    public java.lang.String getPage() {
        return page;
    }
    
    public void setPage(java.lang.String value) {
        page = value;
    }
    
    public java.lang.String getParamId() {
        return paramId;
    }
    
    public void setParamId(java.lang.String value) {
        paramId = value;
    }
    
    public java.lang.String getParamName() {
        return paramName;
    }
    
    public void setParamName(java.lang.String value) {
        paramName = value;
    }
    
    public java.lang.String getParamProperty() {
        return paramProperty;
    }
    
    public void setParamProperty(java.lang.String value) {
        paramProperty = value;
    }
    
    public java.lang.String getParamScope() {
        return paramScope;
    }
    
    public void setParamScope(java.lang.String value) {
        paramScope = value;
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
    
    public java.lang.String getTransaction() {
        return transaction;
    }
    
    public void setTransaction(java.lang.String value) {
        transaction = value;
    }
    
}
