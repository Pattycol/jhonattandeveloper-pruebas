<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@taglib uri="http://displaytag.sf.net" prefix="displayTag" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<bean:define id="listaResultado" name="BusquedaNumeroForm" property="listaResultadoBusqueda" type="java.util.ArrayList"/>
<%
  String cerrar=(String)request.getAttribute(Constants.ACCION_CERRAR);
%>
<html:html>

<head>
<title>Búsqueda de Números</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-cache">
<script language="Javascript" src="include/general.js"></script>
<script language="javascript" src="include/valida.js" type="text/javascript"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<script LANGUAGE="JavaScript">
function cerrar(){
	  window.opener.document.forms[0].method.value="agregaMobiles";
	  window.opener.document.forms[0].submit();
	  window.close();
	}

<% if(cerrar!=null){ %>
   cerrar();
<%}%>
</script>
</head>

<body >
<html:form action='BusquedaNumero' method='post'>
<input type="hidden" name="method" >
<table border="0" width="100%" >
  <tr>
    <td colspan="2" class="tituloPrincipal">Consulta de N&uacute;meros Asociados </td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  <tr>
    <td colspan="2" class="menu_cabecera">Datos de la Consulta </td>
    </tr>
</table>
<table width="100%"  >
     <tr>
     <td width="20%" class="labelPrim">Número:</td>
     <td width="50%"><html:text size="15" maxlength="11" styleClass="labelRespuesta" property="mobile" onkeypress="return esTeclaNumeroSinComa(event)"/></td>
     <td width="30%" align="center"> <input name="button1" type="button" class="botonDialogo" value="Buscar" onclick="javascript:goMethod('buscar')"></td>
    </tr>
</table>
 <br>
<logic:empty name="BusquedaNumeroForm" property="listaResultadoBusqueda">
<br>
<table align="center">
 <tr>   
     <td align="center" >            
	<input name="button1" type="button" class="botonDialogo" value="Cerrar" onclick="javascript:window.close()">
     </td>
 </tr>
</table>

</logic:empty>

<logic:notEmpty name="BusquedaNumeroForm" property="listaResultadoBusqueda">
<table width="100%" id="wb">
<tr><td>
    <displayTag:table messageEmpty="No hay registros" name="listaResultado" width="100%" scope="page" requestURI="BusquedaNumero.do?method=siguiente" pagesize="5" id="param" >            
            <displayTag:column width="10%"  checkBox="true" checkBoxName="mobiles">					
                    <html:multibox styleId="chkMobile" styleClass="checkbox"
                            onclick="onClickCheckBox('mobiles','param')" property="mobiles">
                            <bean:write name="param" property="mobile" />
                    </html:multibox>
            </displayTag:column>
            <displayTag:column width="20%" title="Mobile" property="mobile" sortable="true" />
            <displayTag:column width="70%" title="Usuario" property="nombreUsuario" sortable="true" />            
            <displayTag:caption>
                    <displayTag:headerdisplay title="Lista de Números" />
            </displayTag:caption>
            <displayTag:footer>
                    <displayTag:footerdisplay />
            </displayTag:footer>
      </displayTag:table>      
</td>
</tr>
</table>    
<table align="center">
 <tr>   
     <td align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Seleccionar" onclick="javascript:goMethod('seleccionar')">
			&nbsp;&nbsp;&nbsp;
			<input name="button1" type="button" class="botonDialogo" value="Cerrar" onclick="javascript:window.close()">
     </td>
 </tr>
</table>
</logic:notEmpty>

</html:form>
</body>
</html:html>
