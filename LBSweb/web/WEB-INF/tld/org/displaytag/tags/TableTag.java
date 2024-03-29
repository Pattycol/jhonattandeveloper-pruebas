package org.displaytag.tags;

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
public class TableTag extends BodyTagSupport {

    /**
     * property declaration for tag attribute: list.
     */    
    private java.lang.String list;    

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
    
    /**
     * property declaration for tag attribute: length.
     */    
    private java.lang.String length;
    
    /**
     * property declaration for tag attribute: offset.
     */    
    private java.lang.String offset;
    
    /**
     * property declaration for tag attribute: pagesize.
     */    
    private java.lang.String pagesize;
    
    /**
     * property declaration for tag attribute: messageEmpty.
     */    
    private java.lang.String messageEmpty;
    
    /**
     * property declaration for tag attribute: decorator.
     */    
    private java.lang.String decorator;
    
    /**
     * property declaration for tag attribute: requestURI.
     */    
    private java.lang.String requestURI;
    
    /**
     * property declaration for tag attribute: width.
     */    
    private java.lang.String width;
    
    /**
     * property declaration for tag attribute: style.
     */    
    private java.lang.String style;
    
    /**
     * property declaration for tag attribute: class.
     */    
    private java.lang.String class;
    
    /**
     * property declaration for tag attribute: styleClass.
     */    
    private java.lang.String styleClass;
    
    /**
     * property declaration for tag attribute: border.
     */    
    private java.lang.String border;
    
    /**
     * property declaration for tag attribute: cellspacing.
     */    
    private java.lang.String cellspacing;
    
    /**
     * property declaration for tag attribute: cellpadding.
     */    
    private java.lang.String cellpadding;
    
    /**
     * property declaration for tag attribute: align.
     */    
    private java.lang.String align;
    
    /**
     * property declaration for tag attribute: background.
     */    
    private java.lang.String background;
    
    /**
     * property declaration for tag attribute: bgcolor.
     */    
    private java.lang.String bgcolor;
    
    /**
     * property declaration for tag attribute: frame.
     */    
    private java.lang.String frame;
    
    /**
     * property declaration for tag attribute: height.
     */    
    private java.lang.String height;
    
    /**
     * property declaration for tag attribute: hspace.
     */    
    private java.lang.String hspace;
    
    /**
     * property declaration for tag attribute: rules.
     */    
    private java.lang.String rules;
    
    /**
     * property declaration for tag attribute: summary.
     */    
    private java.lang.String summary;
    
    /**
     * property declaration for tag attribute: vspace.
     */    
    private java.lang.String vspace;
    
    /**
     * property declaration for tag attribute: export.
     */    
    private boolean export;
    
    /**
     * property declaration for tag attribute: id.
     */    
    private java.lang.String id;
    
    /**
     * property declaration for tag attribute: sort.
     */    
    private java.lang.String sort;
    
    /**
     * property declaration for tag attribute: defaultsort.
     */    
    private int defaultsort;
    
    /**
     * property declaration for tag attribute: defaultorder.
     */    
    private java.lang.String defaultorder;
    
    public TableTag() {
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
    
    public java.lang.String getList() {
        return list;
    }
    
    public void setList(java.lang.String value) {
        list = value;
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
    
    public java.lang.String getLength() {
        return length;
    }
    
    public void setLength(java.lang.String value) {
        length = value;
    }
    
    public java.lang.String getOffset() {
        return offset;
    }
    
    public void setOffset(java.lang.String value) {
        offset = value;
    }
    
    public java.lang.String getPagesize() {
        return pagesize;
    }
    
    public void setPagesize(java.lang.String value) {
        pagesize = value;
    }
    
    public java.lang.String getMessageEmpty() {
        return messageEmpty;
    }
    
    public void setMessageEmpty(java.lang.String value) {
        messageEmpty = value;
    }
    
    public java.lang.String getDecorator() {
        return decorator;
    }
    
    public void setDecorator(java.lang.String value) {
        decorator = value;
    }
    
    public java.lang.String getRequestURI() {
        return requestURI;
    }
    
    public void setRequestURI(java.lang.String value) {
        requestURI = value;
    }
    
    public java.lang.String getWidth() {
        return width;
    }
    
    public void setWidth(java.lang.String value) {
        width = value;
    }
    
    public java.lang.String getStyle() {
        return style;
    }
    
    public void setStyle(java.lang.String value) {
        style = value;
    }
    
    public java.lang.String getClass() {
        return class;
    }
    
    public void setClass(java.lang.String value) {
        class = value;
    }
    
    public java.lang.String getStyleClass() {
        return styleClass;
    }
    
    public void setStyleClass(java.lang.String value) {
        styleClass = value;
    }
    
    public java.lang.String getBorder() {
        return border;
    }
    
    public void setBorder(java.lang.String value) {
        border = value;
    }
    
    public java.lang.String getCellspacing() {
        return cellspacing;
    }
    
    public void setCellspacing(java.lang.String value) {
        cellspacing = value;
    }
    
    public java.lang.String getCellpadding() {
        return cellpadding;
    }
    
    public void setCellpadding(java.lang.String value) {
        cellpadding = value;
    }
    
    public java.lang.String getAlign() {
        return align;
    }
    
    public void setAlign(java.lang.String value) {
        align = value;
    }
    
    public java.lang.String getBackground() {
        return background;
    }
    
    public void setBackground(java.lang.String value) {
        background = value;
    }
    
    public java.lang.String getBgcolor() {
        return bgcolor;
    }
    
    public void setBgcolor(java.lang.String value) {
        bgcolor = value;
    }
    
    public java.lang.String getFrame() {
        return frame;
    }
    
    public void setFrame(java.lang.String value) {
        frame = value;
    }
    
    public java.lang.String getHeight() {
        return height;
    }
    
    public void setHeight(java.lang.String value) {
        height = value;
    }
    
    public java.lang.String getHspace() {
        return hspace;
    }
    
    public void setHspace(java.lang.String value) {
        hspace = value;
    }
    
    public java.lang.String getRules() {
        return rules;
    }
    
    public void setRules(java.lang.String value) {
        rules = value;
    }
    
    public java.lang.String getSummary() {
        return summary;
    }
    
    public void setSummary(java.lang.String value) {
        summary = value;
    }
    
    public java.lang.String getVspace() {
        return vspace;
    }
    
    public void setVspace(java.lang.String value) {
        vspace = value;
    }
    
    public boolean getExport() {
        return export;
    }
    
    public void setExport(boolean value) {
        export = value;
    }
    
    public java.lang.String getId() {
        return id;
    }
    
    public void setId(java.lang.String value) {
        id = value;
    }
    
    public java.lang.String getSort() {
        return sort;
    }
    
    public void setSort(java.lang.String value) {
        sort = value;
    }
    
    public int getDefaultsort() {
        return defaultsort;
    }
    
    public void setDefaultsort(int value) {
        defaultsort = value;
    }
    
    public java.lang.String getDefaultorder() {
        return defaultorder;
    }
    
    public void setDefaultorder(java.lang.String value) {
        defaultorder = value;
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
        throw new JspException("error in TableTag: " + ex);
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
