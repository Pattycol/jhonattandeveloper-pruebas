<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/example-taglib" prefix="st"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%
int numMaxMobiles=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MOBILES));
%>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script language="javascript" src="include/valida.js" type="text/javascript"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function validaNumerosConsulta(){
 var frm=document.forms[0];
 var auxVacio=-1;
 var auxLleno=-1;
 //ubica el primer nro vacio
 for (var i = 0; i < <%=numMaxMobiles%>; i++) { 
    if(esVacio(frm.all('listaMobiles[' + i + '].numero').value)){
        auxVacio=i;
        break;
    }
  }
  //ubica el ultimo nro lleno
  for (var j = 0; j < <%=numMaxMobiles%>; j++) { 
    if(!esVacio(frm.all('listaMobiles[' + j + '].numero').value)){
        auxLleno=j;        
    }
  }
  if(auxLleno==-1){
    alert('Debe ingresar un número');
   frm.all('listaMobiles[0].numero').focus();
   return false;
  }  
  if(auxVacio!=-1 && auxVacio < auxLleno){
   alert('Debe ingresar los números de manera consecutiva');
   frm.all('listaMobiles[' + auxVacio + '].numero').focus();
   return false;
  }
  for (var k = 0; k < <%=numMaxMobiles%>; k++) { 
    if(!esVacio(frm.all('listaMobiles[' + k + '].numero').value)){
      
        if(frm.all('listaMobiles[' + k + '].numero').value.length<9){
           alert('Los números deben tener un mínimo de 9 dígitos');
           frm.all('listaMobiles[' + k + '].numero').focus();
            return false;
           }
    }
  }
   return true; 
}

function consulta(){
 var frm=document.forms[0];
if(frm.tipoRespuesta.value==<%=Constants.COD_SELECCIONE%>){
    alert('Debe seleccionar un Formato de Respuesta');
    frm.tipoRespuesta.focus();
    return;
 }
 if(validaNumerosConsulta()){
        frm.button1.disabled=true;
        goMethod('consultaGPS');
 }else{
    return;
 }
}
</script>

</head>

<body >
<html:form action='ConsultaGps' method='post'>
<input type="hidden" name="method" >

<table border="0" >
  <tr>
    <td colspan="2" class="tituloPrincipal"> Consulta LBS - GPS</td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  <tr>
    <td colspan="2" class="menu_cabecera">Datos de la Consulta</td>
    </tr>
</table>
<html:errors/>
<logic:notEmpty name="<%=Constants.MENSAJE_USUARIO%>" scope="request">
<div class='textoMensajeError'> 
<bean:write  name="<%=Constants.MENSAJE_USUARIO%>" scope="request" />
</div>
</logic:notEmpty>
 <table  >
    <tr>
    <td width="177" class="labelPrim">Formato de Respuesta:</td>
    <td width="189">
    <html:select property="tipoRespuesta" styleClass="comboSelector"> 
      <html:option value='<%= Constants.COD_SELECCIONE%>' ><%=Constants.DESC_SELECCIONE%></html:option> 
      <html:optionsCollection property="listaTipoRespuesta" value="codigo" label="descripcion"/> 
      </html:select>
    </td>
    </tr>      
    <%int ind=1;%>    
    <nested:iterate property="listaMobiles">
	<tr>
	<td class="labelPrim">Mobile <%=ind++%>:</td>	
	<td>
            <nested:text size="15" maxlength="11" styleClass="labelRespuesta" property="numero" onkeypress="return esTeclaNumeroSinComa(event)"/>
	<%if(ind==2){%>
	  <a href="javascript:abreBusqueda()"><img src="image/busqueda.gif" alt="Buscar N&uacute;meros" border="0" width="17" height="17"  ></a>
	  <a href="javascript:abrirAyudaFormato()" class="vinculomenu" title='Ayuda acerca del formato de los números'>Ayuda</a>
	<%}%>
	</td>
	</tr>
    </nested:iterate>  
   <tr>
     <td colspan="2" align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Consultar" onclick="javascript:consulta();">
     </td>
    </tr>
  </table>
</html:form>
</body>
</html:html>
