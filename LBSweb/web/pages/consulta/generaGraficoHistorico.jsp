<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%@ page import="javax.servlet.http.*" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%
  String rutaMapa=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RUTA_HOST_MAPA);
  String tiempo=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_RECARGA_GRAFICO);
  //HttpSession sessionPag=request.getSession(false);
  //String strSql=(String)sessionPag.getAttribute(Constants.QUERY_SYSTEM_ARQ);
  //if(strSql==null)
  //    strSql="";
  //System.out.println("Mapa : "+strSql);    
  String reloadGrafico=(String)session.getAttribute(Constants.REQUEST_GRAFICO_AUTOMATICO);
  if(reloadGrafico==null)
      reloadGrafico="";
%>
<html:html>
<bean:define id="strSql" name="<%=Constants.QUERY_SYSTEM_ARQ%>" name="ConsultaForm" property="queryMapa"/>

<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action='' name="frmGrafico" method='post'>
<input type="hidden" name="sql" value="">
<!--<iframe id='frmGrafico' name='frmGrafico' src='' scrolling="auto" width='100%' height='100%' marginheight='0' marginwidth='0'></iframe> -->
</form>
</body>
<script language="javascript" type="text/javascript">
function redirecciona(){   
    
    document.frmGrafico.action='<%=rutaMapa%>';
    document.frmGrafico.sql.value="<%=strSql%>";
    document.frmGrafico.submit();
    <%--
    <%if(reloadGrafico.equals(Constants.REQUEST_GRAFICO_AUTOMATICO)){%>     
        setTimeout("redirecciona()",<%=tiempo%>);
    <%}%>
    --%>
} 
redirecciona();
</script>
</html:html>