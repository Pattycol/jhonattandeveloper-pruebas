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
public class CancelTag extends BodyTagSupport {

    /**
     * property declaration for tag attribute: accesskey.
     */    
    private java.lang.String accesskey;    

    /**
     * property declaration for tag attribute: alt.
     */    
    private java.lang.String alt;
    
    /**
     * property declaration for tag attribute: altKey.
     */    
    private java.lang.String altKey;
    
    /**
     * property declaration for tag attribute: disabled.
     */    
    private java.lang.String disabled;
    
    /**
     * property declaration for tag attribute: onblur.
     */    
    private java.lang.String onblur;
    
    /**
     * property declaration for tag attribute: onchange.
     */    
    private java.lang.String onchange;
    
    /**
     * property declaration for tag attribute: onclick.
     */    
    private java.lang.String onclick;
    
    /**
     * property declaration for tag attribute: ondblclick.
     */    
    private java.lang.String ondblclick;
    
    /**
     * property declaration for tag attribute: onfocus.
     */    
    private java.lang.String onfocus;
    
    /**
     * property declaration for tag attribute: onkeydown.
     */    
    private java.lang.String onkeydown;
    
    /**
     * property declaration for tag attribute: onkeypress.
     */    
    private java.lang.String onkeypress;
    
    /**
     * property declaration for tag attribute: onkeyup.
     */    
    private java.lang.String onkeyup;
    
    /**
     * property declaration for tag attribute: onmousedown.
     */    
    private java.lang.String onmousedown;
    
    /**
     * property declaration for tag attribute: onmousemove.
     */    
    private java.lang.String onmousemove;
    
    /**
     * property declaration for tag attribute: onmouseout.
     */    
    private java.lang.String onmouseout;
    
    /**
     * property declaration for tag attribute: onmouseover.
     */    
    private java.lang.String onmouseover;
    
    /**
     * property declaration for tag attribute: onmouseup.
     */    
    private java.lang.String onmouseup;
    
    /**
     * property declaration for tag attribute: property.
     */    
    private java.lang.String property;
    
    /**
     * property declaration for tag attribute: style.
     */    
    private java.lang.String style;
    
    /**
     * property declaration for tag attribute: styleClass.
     */    
    private java.lang.String styleClass;
    
    /**
     * property declaration for tag attribute: styleId.
     */    
    private java.lang.String styleId;
    
    /**
     * property declaration for tag attribute: tabindex.
     */    
    private java.lang.String tabindex;
    
    /**
     * property declaration for tag attribute: title.
     */    
    private java.lang.String title;
    
    /**
     * property declaration for tag attribute: titleKey.
     */    
    private java.lang.String titleKey;
    
    /**
     * property declaration for tag attribute: value.
     */    
    private java.lang.String value;
    
    public CancelTag() {
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
            return EVAL_BODY_BUFFERED;
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
    
    public java.lang.String getAccesskey() {
        return accesskey;
    }
    
    public void setAccesskey(java.lang.String value) {
        accesskey = value;
    }
    
    public java.lang.String getAlt() {
        return alt;
    }
    
    public void setAlt(java.lang.String value) {
        alt = value;
    }
    
    public java.lang.String getAltKey() {
        return altKey;
    }
    
    public void setAltKey(java.lang.String value) {
        altKey = value;
    }
    
    public java.lang.String getDisabled() {
        return disabled;
    }
    
    public void setDisabled(java.lang.String value) {
        disabled = value;
    }
    
    public java.lang.String getOnblur() {
        return onblur;
    }
    
    public void setOnblur(java.lang.String value) {
        onblur = value;
    }
    
    public java.lang.String getOnchange() {
        return onchange;
    }
    
    public void setOnchange(java.lang.String value) {
        onchange = value;
    }
    
    public java.lang.String getOnclick() {
        return onclick;
    }
    
    public void setOnclick(java.lang.String value) {
        onclick = value;
    }
    
    public java.lang.String getOndblclick() {
        return ondblclick;
    }
    
    public void setOndblclick(java.lang.String value) {
        ondblclick = value;
    }
    
    public java.lang.String getOnfocus() {
        return onfocus;
    }
    
    public void setOnfocus(java.lang.String value) {
        onfocus = value;
    }
    
    public java.lang.String getOnkeydown() {
        return onkeydown;
    }
    
    public void setOnkeydown(java.lang.String value) {
        onkeydown = value;
    }
    
    public java.lang.String getOnkeypress() {
        return onkeypress;
    }
    
    public void setOnkeypress(java.lang.String value) {
        onkeypress = value;
    }
    
    public java.lang.String getOnkeyup() {
        return onkeyup;
    }
    
    public void setOnkeyup(java.lang.String value) {
        onkeyup = value;
    }
    
    public java.lang.String getOnmousedown() {
        return onmousedown;
    }
    
    public void setOnmousedown(java.lang.String value) {
        onmousedown = value;
    }
    
    public java.lang.String getOnmousemove() {
        return onmousemove;
    }
    
    public void setOnmousemove(java.lang.String value) {
        onmousemove = value;
    }
    
    public java.lang.String getOnmouseout() {
        return onmouseout;
    }
    
    public void setOnmouseout(java.lang.String value) {
        onmouseout = value;
    }
    
    public java.lang.String getOnmouseover() {
        return onmouseover;
    }
    
    public void setOnmouseover(java.lang.String value) {
        onmouseover = value;
    }
    
    public java.lang.String getOnmouseup() {
        return onmouseup;
    }
    
    public void setOnmouseup(java.lang.String value) {
        onmouseup = value;
    }
    
    public java.lang.String getProperty() {
        return property;
    }
    
    public void setProperty(java.lang.String value) {
        property = value;
    }
    
    public java.lang.String getStyle() {
        return style;
    }
    
    public void setStyle(java.lang.String value) {
        style = value;
    }
    
    public java.lang.String getStyleClass() {
        return styleClass;
    }
    
    public void setStyleClass(java.lang.String value) {
        styleClass = value;
    }
    
    public java.lang.String getStyleId() {
        return styleId;
    }
    
    public void setStyleId(java.lang.String value) {
        styleId = value;
    }
    
    public java.lang.String getTabindex() {
        return tabindex;
    }
    
    public void setTabindex(java.lang.String value) {
        tabindex = value;
    }
    
    public java.lang.String getTitle() {
        return title;
    }
    
    public void setTitle(java.lang.String value) {
        title = value;
    }
    
    public java.lang.String getTitleKey() {
        return titleKey;
    }
    
    public void setTitleKey(java.lang.String value) {
        titleKey = value;
    }
    
    public java.lang.String getValue() {
        return value;
    }
    
    public void setValue(java.lang.String value) {
        this.value = value;
    }
    
    /**
     * .
     * Fill in this method to process the body content of the tag.
     * You only need to do this if the tag's BodyContent property
     * is set to "JSP" or "tagdependent."
     * If the tag's bodyContent is set to "empty," then this method
     * will not be called.
     */    
    public void writeTagBodyContent(JspWriter out, BodyContent bodyContent) throws IOException {
        //
        // TODO: insert code to write html before writing the body content.
        //       e.g.  out.println("<B>" + getAttribute1() + "</B>");
        //             out.println("   <BLOCKQUOTE>");
        
        //
        // write the body content (after processing by the JSP engine) on the output Writer
        //
        bodyContent.writeOut(out);
        
        //
        // Or else get the body content as a string and process it, e.g.:
        //     String bodyStr = bodyContent.getString();
        //     String result = yourProcessingMethod(bodyStr);
        //     out.println(result);
        //
        
        // TODO: insert code to write html after writing the body content.
        //       e.g.  out.println("   <BLOCKQUOTE>");
        
        // clear the body content for the next time through.
        bodyContent.clearBody();
    }
    
    /**
     * .
     *
     * Handles exception from processing the body content.
     */    
    public void handleBodyContentException(Exception ex) throws JspException {
        // Since the doAfterBody method is guarded, place exception handing code here.
        throw new JspException("error in CancelTag: " + ex);
    }
    
    /**
     * .
     *
     *
     * This method is called after the JSP engine processes the body content of the tag.
     * @return EVAL_BODY_AGAIN if the JSP engine should evaluate the tag body again, otherwise return SKIP_BODY.
     * This method is automatically generated. Do not modify this method.
     * Instead, modify the methods that this method calls.
     */    
    public int doAfterBody() throws JspException {
        try {
            //
            // This code is generated for tags whose bodyContent is "JSP"
            //
            BodyContent bodyContent = getBodyContent();
            JspWriter out = bodyContent.getEnclosingWriter();
            
            writeTagBodyContent(out, bodyContent);
        } catch (Exception ex) {
            handleBodyContentException(ex);
        }
        
        if (theBodyShouldBeEvaluatedAgain()) {
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }
    
    /**
     *
     * Fill in this method to determine if the tag body should be evaluated
     * again after evaluating the body.
     * Use this method to create an iterating tag.
     * Called from doAfterBody().
     *
     */    
    public boolean theBodyShouldBeEvaluatedAgain() {
        //
        // TODO: code that determines whether the tag body should be
        //       evaluated again after processing the tag
        //       should be placed here.
        //       You can use this method to create iterating tags.
        //       Called from the doAfterBody() method.
        //
        return false;
    }
    
}
