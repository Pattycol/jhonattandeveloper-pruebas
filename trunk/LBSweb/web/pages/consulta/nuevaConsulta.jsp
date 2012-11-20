<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%
int numMaxMobiles=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MOBILES));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script type="text/javascript" src="include/jquery.js"></script>
<script type="text/javascript" src="include/general.js"></script>
<script type="text/javascript" src="include/valida.js"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function validaNumerosConsulta(){
	var frm=document.ConsultaForm;
	var auxVacio=-1;
	var auxLleno=-1;
	$(".labelRespuesta").each(function(i,item){
		if(auxVacio==-1 && esVacio(item.value)){
	        auxVacio=i;
	    }
		else if(!esVacio(item.value)){
			auxLleno=i;
		}
	});
 //ubica el primer nro vacio
 //for (var i = 0; i < <%=numMaxMobiles%>; i++) { 
    
  //}
  //ubica el ultimo nro lleno
  //for (var j = 0; j < <%=numMaxMobiles%>; j++) { 
    //if(!esVacio(frm.all('listaMobiles[' + j + '].numero').value)){
      //  auxLleno=j;        
    //}
  //}
	if(auxLleno==-1){
		alert('Debe ingresar un número');
   //frm.all('listaMobiles[0].numero').focus();
		$(".labelRespuesta").get(0).focus();
		return false;
	}  
 	if(auxVacio!=-1 && auxVacio < auxLleno){
		alert('Debe ingresar los números de manera consecutiva');
		//frm.all('listaMobiles[' + auxVacio + '].numero').focus();
		$(".labelRespuesta").get(auxVacio).focus();
		return false;
	}
	$(".labelRespuesta").each(function(i,item){
		var valor=$(item).value;
		if(!esVacio(valor) && valor.length<8){
			alert('Los números deben tener un mínimo de 8 dígitos');
			$(".labelRespuesta").get(i).focus();
			return false;
		}
	});
  /*for (var k = 0; k < <%=numMaxMobiles%>; k++) { 
    if(!esVacio(frm.all('listaMobiles[' + k + '].numero').value)){
      
        if(frm.all('listaMobiles[' + k + '].numero').value.length<8){
           alert('Los números deben tener un mínimo de 8 dígitos');
           frm.all('listaMobiles[' + k + '].numero').focus();
            return false;
           }
           
    }
	}*/
	return true; 
}

function consulta(){
//  var frm=document.forms[1];
 var tipo = document.ConsultaForm.tipoRespuesta.value;

 if(tipo == <%=Constants.COD_SELECCIONE%>){
    alert('Debe seleccionar un Formato de Respuesta');
    document.ConsultaForm.tipoRespuesta.focus();
    return;
 }
 if(validaNumerosConsulta()){
	document.ConsultaForm.button1.disabled=true;
   	document.ConsultaForm.action = "Consulta.do?method=consultaSimpleLBS";
	
   	document.ConsultaForm.submit();
	
 }else{
    return;
 }
}


</script>

</head>

<body topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">
<html:form action='Consulta' method='post' >
<input type="hidden" name="method" />
<table border="0">
  <tr>
    <td colspan="2" class="tituloPrincipal"> Consulta LBS </td>    
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
	<td height="18">
            <nested:text size="15" maxlength="9" style="width: 101px" styleClass="labelRespuesta" property="numero" onkeypress="return esTeclaNumeroSinComa(event);"/>
	<%if(ind==2){%>
	  
<!-- 	  <a href="javascript:abreBusqueda()" style="height: 8px; width: 9px"><img src="image/busqueda.gif" alt="Buscar N&uacute;meros" border="0" width="17" height="17"  /></a> -->
<!-- 	  <a href="javascript:abrirAyudaFormato()" style="height: 2px; width: 8px" class="vinculomenu" title='Ayuda acerca del formato de los números'>Ayuda</a> -->
	  
	<%}%>
	</td>
	</tr>
    </nested:iterate>    
   <tr>   
     <td colspan="2" align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Consultar" onclick="javascript:consulta();" />
     </td>
    </tr>
  </table>
</html:form>
</body>
</html:html>
