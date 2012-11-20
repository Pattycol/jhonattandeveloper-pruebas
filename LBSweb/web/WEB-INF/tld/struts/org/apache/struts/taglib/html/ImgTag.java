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
public class ImgTag extends TagSupport {

    /**
     * property declaration for tag attribute: align.
     */    
    private java.lang.String align;    

    /**
     * property declaration for tag attribute: alt.
     */    
    private java.lang.String alt;
    
    /**
     * property declaration for tag attribute: altKey.
     */    
    private java.lang.String altKey;
    
    /**
     * property declaration for tag attribute: border.
     */    
    private java.lang.String border;
    
    /**
     * property declaration for tag attribute: bundle.
     */    
    private java.lang.String bundle;
    
    /**
     * property declaration for tag attribute: height.
     */    
    private java.lang.String height;
    
    /**
     * property declaration for tag attribute: hspace.
     */    
    private java.lang.String hspace;
    
    /**
     * property declaration for tag attribute: imageName.
     */    
    private java.lang.String imageName;
    
    /**
     * property declaration for tag attribute: ismap.
     */    
    private java.lang.String ismap;
    
    /**
     * property declaration for tag attribute: locale.
     */    
    private java.lang.String locale;
    
    /**
     * property declaration for tag attribute: lowsrc.
     */    
    private java.lang.String lowsrc;
    
    /**
     * property declaration for tag attribute: name.
     */    
    private java.lang.String name;
    
    /**
     * property declaration for tag attribute: onclick.
     */    
    private java.lang.String onclick;
    
    /**
     * property declaration for tag attribute: ondblclick.
     */    
    private java.lang.String ondblclick;
    
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
     * property declaration for tag attribute: paramId.
     */    
    private java.lang.String paramId;
    
    /**
     * property declaration for tag attribute: page.
     */    
    private java.lang.String page;
    
    /**
     * property declaration for tag attribute: pageKey.
     */    
    private java.lang.String pageKey;
    
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
     * property declaration for tag attribute: src.
     */    
    private java.lang.String src;
    
    /**
     * property declaration for tag attribute: srcKey.
     */    
    private java.lang.String srcKey;
    
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
     * property declaration for tag attribute: usemap.
     */    
    private java.lang.String usemap;
    
    /**
     * property declaration for tag attribute: vspace.
     */    
    private java.lang.String vspace;
    
    /**
     * property declaration for tag attribute: width.
     */    
    private java.lang.String width;
    
    public ImgTag() {
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
    
    public java.lang.String getAlign() {
        return align;
    }
    
    public void setAlign(java.lang.String value) {
        align = value;
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
    
    public java.lang.String getBorder() {
        return border;
    }
    
    public void setBorder(java.lang.String value) {
        border = value;
    }
    
    public java.lang.String getBundle() {
        return bundle;
    }
    
    public void setBundle(java.lang.String value) {
        bundle = value;
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
    
    public java.lang.String getImageName() {
        return imageName;
    }
    
    public void setImageName(java.lang.String value) {
        imageName = value;
    }
    
    public java.lang.String getIsmap() {
        return ismap;
    }
    
    public void setIsmap(java.lang.String value) {
        ismap = value;
    }
    
    public java.lang.String getLocale() {
        return locale;
    }
    
    public void setLocale(java.lang.String value) {
        locale = value;
    }
    
    public java.lang.String getLowsrc() {
        return lowsrc;
    }
    
    public void setLowsrc(java.lang.String value) {
        lowsrc = value;
    }
    
    public java.lang.String getName() {
        return name;
    }
    
    public void setName(java.lang.String value) {
        name = value;
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
    
    public java.lang.String getParamId() {
        return paramId;
    }
    
    public void setParamId(java.lang.String value) {
        paramId = value;
    }
    
    public java.lang.String getPage() {
        return page;
    }
    
    public void setPage(java.lang.String value) {
        page = value;
    }
    
    public java.lang.String getPageKey() {
        return pageKey;
    }
    
    public void setPageKey(java.lang.String value) {
        pageKey = value;
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
    
    public java.lang.String getSrc() {
        return src;
    }
    
    public void setSrc(java.lang.String value) {
        src = value;
    }
    
    public java.lang.String getSrcKey() {
        return srcKey;
    }
    
    public void setSrcKey(java.lang.String value) {
        srcKey = value;
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
    
    public java.lang.String getUsemap() {
        return usemap;
    }
    
    public void setUsemap(java.lang.String value) {
        usemap = value;
    }
    
    public java.lang.String getVspace() {
        return vspace;
    }
    
    public void setVspace(java.lang.String value) {
        vspace = value;
    }
    
    public java.lang.String getWidth() {
        return width;
    }
    
    public void setWidth(java.lang.String value) {
        width = value;
    }
    
}
