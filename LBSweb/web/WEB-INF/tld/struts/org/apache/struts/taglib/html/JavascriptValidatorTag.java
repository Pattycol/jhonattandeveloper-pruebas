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
public class JavascriptValidatorTag extends TagSupport {

    /**
     * property declaration for tag attribute: cdata.
     */    
    private java.lang.String cdata;    

    /**
     * property declaration for tag attribute: dynamicJavascript.
     */    
    private java.lang.String dynamicJavascript;
    
    /**
     * property declaration for tag attribute: formName.
     */    
    private java.lang.String formName;
    
    /**
     * property declaration for tag attribute: method.
     */    
    private java.lang.String method;
    
    /**
     * property declaration for tag attribute: page.
     */    
    private java.lang.String page;
    
    /**
     * property declaration for tag attribute: src.
     */    
    private java.lang.String src;
    
    /**
     * property declaration for tag attribute: staticJavascript.
     */    
    private java.lang.String staticJavascript;
    
    /**
     * property declaration for tag attribute: htmlComment.
     */    
    private java.lang.String htmlComment;
    
    public JavascriptValidatorTag() {
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
    
    public java.lang.String getCdata() {
        return cdata;
    }
    
    public void setCdata(java.lang.String value) {
        cdata = value;
    }
    
    public java.lang.String getDynamicJavascript() {
        return dynamicJavascript;
    }
    
    public void setDynamicJavascript(java.lang.String value) {
        dynamicJavascript = value;
    }
    
    public java.lang.String getFormName() {
        return formName;
    }
    
    public void setFormName(java.lang.String value) {
        formName = value;
    }
    
    public java.lang.String getMethod() {
        return method;
    }
    
    public void setMethod(java.lang.String value) {
        method = value;
    }
    
    public java.lang.String getPage() {
        return page;
    }
    
    public void setPage(java.lang.String value) {
        page = value;
    }
    
    public java.lang.String getSrc() {
        return src;
    }
    
    public void setSrc(java.lang.String value) {
        src = value;
    }
    
    public java.lang.String getStaticJavascript() {
        return staticJavascript;
    }
    
    public void setStaticJavascript(java.lang.String value) {
        staticJavascript = value;
    }
    
    public java.lang.String getHtmlComment() {
        return htmlComment;
    }
    
    public void setHtmlComment(java.lang.String value) {
        htmlComment = value;
    }
    
}
