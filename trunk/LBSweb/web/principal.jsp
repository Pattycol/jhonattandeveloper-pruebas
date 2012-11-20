<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<bean:define id="usr" scope="session" name="<%=Constants.USUARIO_LOGUEADO%>" type="com.stconsulting.lbsweb.seguridad.bean.Usuario"/>
<%System.out.println("UsuarioLogueado : "+usr.getTelefono());%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html:html>
<head><title><bean:message bundle="application" key="global.title"/></title>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
</head>
<html:form action="Login" method="post">
<frameset rows="100,*,16" cols="*" frameborder="yes" border="2" >
  <frame src="Login.do?method=cabecera" name="cabecera" marginwidth="0" marginheight="0" scrolling="no" noresize id="cabecera" >
   <frameset cols="318,*" frameborder="NO" border="0" framespacing="0">
  	<frame src="Consulta.do?method=inicio" name="frmIzq" id="frmIzq" scrolling="auto" noresize>
  	<frame src="blank.html" name="frmDer" id="frmDer" scrolling="auto" noresize marginwidth="1" marginheight="1">
  </frameset>
  <frame src="footer.jsp" name="footer" marginwidth="0" marginheight="0" scrolling="no" noresize id="footer">
</frameset>
<noframes><body bgcolor='red'>
</body></noframes>
</html:form>
</html:html>
