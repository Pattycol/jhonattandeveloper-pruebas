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
public class ColumnTag extends BodyTagSupport {

    /**
     * property declaration for tag attribute: property.
     */    
    private java.lang.String property;    

    /**
     * property declaration for tag attribute: title.
     */    
    private java.lang.String title;
    
    /**
     * property declaration for tag attribute: checkBox.
     */    
    private java.lang.String checkBox;
    
    /**
     * property declaration for tag attribute: checkBoxName.
     */    
    private java.lang.String checkBoxName;
    
    /**
     * property declaration for tag attribute: nulls.
     */    
    private boolean nulls;
    
    /**
     * property declaration for tag attribute: sort.
     */    
    private java.lang.String sort;
    
    /**
     * property declaration for tag attribute: sortable.
     */    
    private boolean sortable;
    
    /**
     * property declaration for tag attribute: autolink.
     */    
    private boolean autolink;
    
    /**
     * property declaration for tag attribute: media.
     */    
    private java.lang.String media;
    
    /**
     * property declaration for tag attribute: href.
     */    
    private java.lang.String href;
    
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
     * property declaration for tag attribute: maxLength.
     */    
    private java.lang.String maxLength;
    
    /**
     * property declaration for tag attribute: maxWords.
     */    
    private java.lang.String maxWords;
    
    /**
     * property declaration for tag attribute: width.
     */    
    private java.lang.String width;
    
    /**
     * property declaration for tag attribute: class.
     */    
    private java.lang.String class;
    
    /**
     * property declaration for tag attribute: styleClass.
     */    
    private java.lang.String styleClass;
    
    /**
     * property declaration for tag attribute: headerClass.
     */    
    private java.lang.String headerClass;
    
    /**
     * property declaration for tag attribute: headerStyleClass.
     */    
    private java.lang.String headerStyleClass;
    
    /**
     * property declaration for tag attribute: style.
     */    
    private java.lang.String style;
    
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
     * property declaration for tag attribute: height.
     */    
    private java.lang.String height;
    
    /**
     * property declaration for tag attribute: nowrap.
     */    
    private java.lang.String nowrap;
    
    /**
     * property declaration for tag attribute: valign.
     */    
    private java.lang.String valign;
    
    /**
     * property declaration for tag attribute: group.
     */    
    private java.lang.String group;
    
    /**
     * property declaration for tag attribute: decorator.
     */    
    private java.lang.String decorator;
    
    public ColumnTag() {
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
    
    public java.lang.String getProperty() {
        return property;
    }
    
    public void setProperty(java.lang.String value) {
        property = value;
    }
    
    public java.lang.String getTitle() {
        return title;
    }
    
    public void setTitle(java.lang.String value) {
        title = value;
    }
    
    public java.lang.String getCheckBox() {
        return checkBox;
    }
    
    public void setCheckBox(java.lang.String value) {
        checkBox = value;
    }
    
    public java.lang.String getCheckBoxName() {
        return checkBoxName;
    }
    
    public void setCheckBoxName(java.lang.String value) {
        checkBoxName = value;
    }
    
    public boolean getNulls() {
        return nulls;
    }
    
    public void setNulls(boolean value) {
        nulls = value;
    }
    
    public java.lang.String getSort() {
        return sort;
    }
    
    public void setSort(java.lang.String value) {
        sort = value;
    }
    
    public boolean getSortable() {
        return sortable;
    }
    
    public void setSortable(boolean value) {
        sortable = value;
    }
    
    public boolean getAutolink() {
        return autolink;
    }
    
    public void setAutolink(boolean value) {
        autolink = value;
    }
    
    public java.lang.String getMedia() {
        return media;
    }
    
    public void setMedia(java.lang.String value) {
        media = value;
    }
    
    public java.lang.String getHref() {
        return href;
    }
    
    public void setHref(java.lang.String value) {
        href = value;
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
    
    public java.lang.String getMaxLength() {
        return maxLength;
    }
    
    public void setMaxLength(java.lang.String value) {
        maxLength = value;
    }
    
    public java.lang.String getMaxWords() {
        return maxWords;
    }
    
    public void setMaxWords(java.lang.String value) {
        maxWords = value;
    }
    
    public java.lang.String getWidth() {
        return width;
    }
    
    public void setWidth(java.lang.String value) {
        width = value;
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
    
    public java.lang.String getHeaderClass() {
        return headerClass;
    }
    
    public void setHeaderClass(java.lang.String value) {
        headerClass = value;
    }
    
    public java.lang.String getHeaderStyleClass() {
        return headerStyleClass;
    }
    
    public void setHeaderStyleClass(java.lang.String value) {
        headerStyleClass = value;
    }
    
    public java.lang.String getStyle() {
        return style;
    }
    
    public void setStyle(java.lang.String value) {
        style = value;
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
    
    public java.lang.String getHeight() {
        return height;
    }
    
    public void setHeight(java.lang.String value) {
        height = value;
    }
    
    public java.lang.String getNowrap() {
        return nowrap;
    }
    
    public void setNowrap(java.lang.String value) {
        nowrap = value;
    }
    
    public java.lang.String getValign() {
        return valign;
    }
    
    public void setValign(java.lang.String value) {
        valign = value;
    }
    
    public java.lang.String getGroup() {
        return group;
    }
    
    public void setGroup(java.lang.String value) {
        group = value;
    }
    
    public java.lang.String getDecorator() {
        return decorator;
    }
    
    public void setDecorator(java.lang.String value) {
        decorator = value;
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
        throw new JspException("error in ColumnTag: " + ex);
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
