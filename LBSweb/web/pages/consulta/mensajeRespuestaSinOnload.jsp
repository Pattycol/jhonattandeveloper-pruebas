<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/example-taglib" prefix="st"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@taglib uri="http://displaytag.sf.net" prefix="displayTag" %>
<%@ page import="java.util.*" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script language="Javascript" src="include/valida.js"></script>
<script language="javascript" type="text/javascript">
function goto(url){
	alert("Here Mensaje Respuesta");
document.location.href = url;
}
</script>
<script language="Javascript">
    var prevObj=null;
	function go(url){
		document.location.href = url
	}

	function over1(obj){
	obj.style.cursor = "hand"
	}

     function aceptar() {
       f = document.forms[1];
       f.method.value="inicio";
       f.submit();
    }

</script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<link href="include/itdg_intranet.css" rel="stylesheet" type="text/css">

</head>

<body >
<html:form action='ConsultaTarea' method='post'>
<input type="hidden" name="method" >

<table border="0" >
  <tr>
    <td colspan="2" class="tituloPrincipal">Registro de Tarea para Consulta LBS </td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  </table>
<br>
<table width="85%"  border="0" align="center">
  <tr>
	<td align="center" style="color:#FF0000"><strong><div class='textoMensajeError'> <html:errors/></div></strong></td>
  </tr>
</table>
<br>
<table width="85%"  border="0" align="center">
    <tr>
    <td align="center"><input name="button1" type="button" class="botonDialogo" value="Aceptar" onclick="javascript:aceptar()"></td>
    </tr>
  </table>
</html:form>
</body>
</html:html>