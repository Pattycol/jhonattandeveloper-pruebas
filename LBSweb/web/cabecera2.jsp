<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.lbsweb.seguridad.bean.Usuario" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.stconsulting.common.util.Converter" %>
<%
Usuario usuario=(Usuario)session.getAttribute(Constants.USUARIO_LOGUEADO);
String consultas_web_principal=(String)session.getAttribute(Constants.CONSULTAS_WEB_FALTANTES);
int consultas_web = Integer.parseInt(consultas_web_principal);
%>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<style type="text/css">
a{
display: block;
text-decoration: none;
color: red;
padding: 5px;
}
 
ul{
width: 500px;
height: 28px;
}
 
ul,li{
list-style: none;
margin:0;
padding:0;
}
 
#nav{
font-family: 'Century Gothic', sans-serif;
}
 
#nav li{
float:left;
position: relative;
width: 125px;
font-size: 14px;
font-variant: small-caps;
}
 
.submenu{
display: none;
position: absolute;
left: -1px;
border:none;
height: auto;
background: none;
}
 
#nav .submenu li{
float: none;
position: static;
margin: 0;
font-size: 11px;
font-variant: normal;
border: 1px solid red;
border-top: none;
width: 124px;
}
 
#nav .submenu li a{
color: #FFFFFF;
background: white url('image/menu.png') repeat-x 0 0;
width: 114px;
height: 16px;
}
</style>
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script src="include/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="include/jquery-backgroundPosition.js" type="text/javascript"></script>
</head>
<body topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">
<html:form  action='Login' method='post' >
<table width="100%" border="0" cellpadding="0" cellspacing="0" >

 <tr>
  	  <td>
  		<table width="100%" border="0" cellspacing="0" cellpadding="0">      
          <tr>
          
            <td width="40%" align="center">
           		 <div id="recargado">
           		  <% if(consultas_web > 0 ){ %>
                    <font color="#FF0000">Consultas Restantes: <%=consultas_web_principal %></font><br>
                 <% }else{ %>
                    <font color="#FF0000">Uds. está generando consultas adicionales.Cada consulta adicional a su paquete tiene un costo de S/.0.25 inc. IGV..</font><br>
                 <% } %>
           		 </div>
           	</td>
          </tr>
        </table></td>
      </tr>
  <!--DWLayoutTable-->      
</table>
</html:form>
</body>
</html:html>
