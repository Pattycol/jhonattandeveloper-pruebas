<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>

<html:html>

<head>
<title>Cambio de Password</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script language="Javascript" src="include/general.js"></script>
<script language="javascript" src="include/valida.js" type="text/javascript"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">

</head>

<body >
<table border="0" width="100%" >
  <tr>
    <td colspan="2" class="tituloPrincipal">Recuperaci&oacute;n de Clave</td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>  
</table>
    <table width='100%'>
        <tr>
            <td><div class='textoMensajeError'><bean:message bundle="application" key="mensaje.exito.recuperar.password"/></div></td>
        </tr>
    </table>
    <br>
   <table width='100%' align='center'>
        <tr>
            <td align='center'><input name="button1" type="button" class="botonDialogo" value="Cerrar" onclick="javascript:window.close();"></td>
        </tr>
    </table> 
</body>
</html:html>