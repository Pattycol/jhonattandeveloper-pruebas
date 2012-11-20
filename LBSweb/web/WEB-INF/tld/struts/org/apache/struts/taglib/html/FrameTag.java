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
public class FrameTag extends BodyTagSupport {

    /**
     * property declaration for tag attribute: action.
     */    
    private java.lang.String action;    

    /**
     * property declaration for tag attribute: anchor.
     */    
    private java.lang.String anchor;
    
    /**
     * property declaration for tag attribute: forward.
     */    
    private java.lang.String forward;
    
    /**
     * property declaration for tag attribute: frameborder.
     */    
    private java.lang.String frameborder;
    
    /**
     * property declaration for tag attribute: frameName.
     */    
    private java.lang.String frameName;
    
    /**
     * property declaration for tag attribute: href.
     */    
    private java.lang.String href;
    
    /**
     * property declaration for tag attribute: longdesc.
     */    
    private java.lang.String longdesc;
    
    /**
     * property declaration for tag attribute: marginheight.
     */    
    private java.lang.String marginheight;
    
    /**
     * property declaration for tag attribute: marginwidth.
     */    
    private java.lang.String marginwidth;
    
    /**
     * property declaration for tag attribute: name.
     */    
    private java.lang.String name;
    
    /**
     * property declaration for tag attribute: noresize.
     */    
    private java.lang.String noresize;
    
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
     * property declaration for tag attribute: scrolling.
     */    
    private java.lang.String scrolling;
    
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
     * property declaration for tag attribute: title.
     */    
    private java.lang.String title;
    
    /**
     * property declaration for tag attribute: titleKey.
     */    
    private java.lang.String titleKey;
    
    /**
     * property declaration for tag attribute: transaction.
     */    
    private java.lang.String transaction;
    
    public FrameTag() {
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
    
    public java.lang.String getAction() {
        return action;
    }
    
    public void setAction(java.lang.String value) {
        action = value;
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
    
    public java.lang.String getFrameborder() {
        return frameborder;
    }
    
    public void setFrameborder(java.lang.String value) {
        frameborder = value;
    }
    
    public java.lang.String getFrameName() {
        return frameName;
    }
    
    public void setFrameName(java.lang.String value) {
        frameName = value;
    }
    
    public java.lang.String getHref() {
        return href;
    }
    
    public void setHref(java.lang.String value) {
        href = value;
    }
    
    public java.lang.String getLongdesc() {
        return longdesc;
    }
    
    public void setLongdesc(java.lang.String value) {
        longdesc = value;
    }
    
    public java.lang.String getMarginheight() {
        return marginheight;
    }
    
    public void setMarginheight(java.lang.String value) {
        marginheight = value;
    }
    
    public java.lang.String getMarginwidth() {
        return marginwidth;
    }
    
    public void setMarginwidth(java.lang.String value) {
        marginwidth = value;
    }
    
    public java.lang.String getName() {
        return name;
    }
    
    public void setName(java.lang.String value) {
        name = value;
    }
    
    public java.lang.String getNoresize() {
        return noresize;
    }
    
    public void setNoresize(java.lang.String value) {
        noresize = value;
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
    
    public java.lang.String getScrolling() {
        return scrolling;
    }
    
    public void setScrolling(java.lang.String value) {
        scrolling = value;
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
    
    public java.lang.String getTransaction() {
        return transaction;
    }
    
    public void setTransaction(java.lang.String value) {
        transaction = value;
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
        throw new JspException("error in FrameTag: " + ex);
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
