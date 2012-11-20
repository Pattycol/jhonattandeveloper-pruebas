<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%
  String cerrar=(String)request.getAttribute(Constants.ACCION_CERRAR);
%>
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
<script LANGUAGE="JavaScript">
function aceptar(){
    frm=document.forms[0];
    if(frm.mobile.value==''){
      alert('Debe ingresar su numero');
      frm.mobile.focus();
      return;
    }
    goMethod('recuperarPassword');        
    
}

function cerrar(){	  
  window.close();
}

<% if(cerrar!=null){ %>
   cerrar();
<%}%>
</script>
</head>

<body >
<html:form action='Login' method='post'>
<input type="hidden" name="method" >
<table border="0" width="100%" >
  <tr>
    <td colspan="2" class="tituloPrincipal"  >Recuperaci&oacute;n de Clave </td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>  
</table>

<table width="100%" >
     <tr>
     <td width="30%" class="labelPrim">Número:</td>
     <!--  <td width="70%" class="labelRespuesta"><bean:write name="LoginForm" property="mobile" /></td>-->
     <td width="70%" class="labelRespuesta"><html:text property="mobile" maxlength='11' onkeypress="return esTeclaNumeroSinComa(event)"/>     
    </tr>
    <tr>
    <td colspan="2" align="center"><html:errors/></td>
    </tr>
</table>
<table align="center">
 <tr>   
     <td align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Enviar" onclick="javascript:aceptar()">
            &nbsp;&nbsp;&nbsp;
            <input name="button1" type="button" class="botonDialogo" value="Cancelar" onclick="javascript:window.close()">
     </td>
 </tr>
</table>
</html:form>
</body>
</html:html>
