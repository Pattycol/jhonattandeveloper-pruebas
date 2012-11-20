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
<%@ page import="java.text.SimpleDateFormat" %>
<bean:define id="fecha1" name="ConsultaForm" property="fechaInicioString" type="java.lang.String"/>
<bean:define id="fecha2" name="ConsultaForm" property="fechaFinString" type="java.lang.String"/>

<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%
String fechaHoy=Helper.formateaFecha(new java.util.Date(),Constants.FORMATO_FECHA_MOSTRAR);
String reporte=(String)request.getAttribute(Constants.REQUEST_ACTIVA_REPORTE);
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
<script type="text/javascript" src="include/jquery.js"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" type="text/css" href="include/timePickerDemo/jquery-ui-1.7.3.custom.css">
<link rel="stylesheet" type="text/css" href="include/dtp4Demo/css/dtp4/jquery.ui.core.css">
<link rel="stylesheet" type="text/css" href="include/dtp4Demo/css/dtp4/jquery.ui.datepicker.css">
<link rel="stylesheet" href="include/dtp4Demo/css/dtp4/demos.css">
 
<script src="include/dtp4Demo/js/dtp4/jquery-1.6.2.js"></script>
<script src="include/dtp4Demo/js/dtp4/jquery.ui.core.js"></script>
<script src="include/dtp4Demo/js/dtp4/jquery.ui.widget.js"></script>
<script src="include/dtp4Demo/js/dtp4/jquery.ui.datepicker.js"></script>
<script src="include/dtp4Demo/js/dtp4/jquery.ui.datepicker-es.js"></script>

<script type="text/javascript">
	$(document)
	.ready( 
		 function() {
		$("#txtfecInicio").datepicker({
			showOtherMonths: true,
			selectOtherMonths: true,
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "image/calendar.gif",
			buttonImageOnly: true
		});
		
		$( "#txtfecInicio" ).datepicker( $.datepicker.regional[ "es" ] );	
		
		$( "#txtfecFin" ).datepicker({
			showOtherMonths: true,
			selectOtherMonths: true,
			changeMonth: true,
			changeYear: true,
			showOn: "button",
			buttonImage: "image/calendar.gif",
			buttonImageOnly: true
		});
		
	});
	
	</script>

<script language="javascript" type="text/javascript">

function validaNumerosConsulta(){
//	alert('Entro en validaNumerosConsulta');
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
	//alert('SAMIRA ');
	return true; 
}

/*function validaNumerosConsulta(flag){
	
 var frm=document.ConsultaForm;
 var auxVacio=-1;
 var auxLleno=-1;
 //ubica el primer nro vacio
 //alert("valida numeros");
 
 for (var i = 0; i < <%=numMaxMobiles%>; i++) { 
	 alert(frm.all("listaMobiles[" + i + "].numero").value);
    if(esVacio(frm.all('listaMobiles[' + i + '].numero').value)){
        auxVacio=i;
        break;
    }
  }
  alert("primer vacio");
  //ubica el primer nro lleno
  for (var j = 0; j < <%=numMaxMobiles%>; j++) { 
    if(!esVacio(frm.all('listaMobiles[' + j + '].numero').value)){
        auxLleno=j;
        break;
    }
  }
  alert("segundo vacio");
  if(flag=='true'){
      if(auxLleno==-1){
        alert('Debe ingresar un número');
       frm.all('listaMobiles[0].numero').focus();
       return false;
      } 
 }
  if(auxVacio < auxLleno){
   alert('Debe ingresar los números de manera consecutiva');
   frm.all('listaMobiles[' + auxVacio + '].numero').focus();
   return false;
  }
  alert("antes for");
  for (var k = 0; k < <%=numMaxMobiles%>; k++) { 
    if(!esVacio(frm.all('listaMobiles[' + k + '].numero').value)){
      
        if(frm.all('listaMobiles[' + k + '].numero').value.length<9){
           alert('Los números deben tener un mínimo de 9 dígitos');
           frm.all('listaMobiles[' + k + '].numero').focus();
            return false;
           }
           
    }
  }
  alert("luego for");
   return true; 
}*/

function validaFechas(){
//	alert('Entro en validaFechas');
var frm=document.ConsultaForm;
 if(esVacio(frm.fechaInicioString.value)){
    alert('Debe ingresar una Fecha Inicio');
    return false;
 }
 if(esVacio(frm.fechaFinString.value)){
    alert('Debe ingresar una Fecha Fin');
    return false;
 }
 if (!isDate(frm.fechaInicioString.value)) {
    //alert('Debe ingresar una Fecha Inicio válida');
    frm.fechaInicioString.focus();
    return false;
 }
 if (!isDate(frm.fechaFinString.value)) {
    //alert('Debe ingresar una Fecha Fin válida');
    frm.fechaFinString.focus();
    return false;
 }


    	var _fecha='<%=fechaHoy %>';
		var _auxfecha = _fecha.split("/");	
		var _fechaHoy=   parseFloat(_auxfecha[2])*12*30 +  parseFloat(_auxfecha[1])*30 + parseFloat(_auxfecha[0]);
		var _fechaHoy=_fechaHoy-31;
			
	    _fecha=frm.fechaInicioString.value;
		_auxfecha = _fecha.split("/");	
		var _fechaInicio= parseFloat(_auxfecha[2])*12*30 + parseFloat(_auxfecha[1])*30 + parseFloat(_auxfecha[0]);

		if(_fechaInicio<_fechaHoy){
		alert("Solo puede consultar un mes atrás al actual");
		frm.fechaInicioString.focus();
		return false;
				}
		
    
 
 return true;
}


function reporte(){
    //no es necesario q ingrese nros.
   // alert('Entro aqui');
    if(validaFechas()){
        if(validaNumerosConsulta()){
        	
        	f = document.ConsultaForm;
           f.method.value="generaReporteLBS";
            f.submit();
        	//goMethod('generaReporteLBS');
         }else{
        	 alert("Numeros de consulta no validos");
            return;
         }
    }else{
    	alert("Fechas no validas");
        return;
     }
}
<%
    if(reporte!=null){
%>
 // goFrameDer("ConsultaHistorica.do?method=siguienteReporte");
 	document.ConsultaForm.button1.disabled=true;
   	document.ConsultaForm.action = "ConsultaHistorica.do?method=siguienteReporte";
	document.ConsultaForm.submit();
	
<%}%>
</script>

</head>

<body >
<html:form action='ConsultaHistorica' method='post'  >
<input type="hidden" name="method" >

<table border="0" >
  <tr>
    <td colspan="2" class="tituloPrincipal"> Consulta LBS Histórica</td>    
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

 <table width='100%' >
     <tr>
     <td width="40%" class="labelPrim">Fecha Inicio:</td>
    <td width="60%">
    <input styleClass="labelRespuesta" size="10" value="<%=fecha1%>" name="fechaInicioString" id="txtfecFin" maxlength="10" >
     
    </td>
    </tr>
    <tr>
     <td class="labelPrim">Fecha Fin:</td>
    <td ><input styleClass="labelRespuesta" size="10" value="<%=fecha2%>" name="fechaFinString" id="txtfecInicio" maxlength="10" >
    
    </td>
    </tr>
    <%int ind=1;%>    
    <nested:iterate property="listaMobiles">
	<tr>
	<td class="labelPrim">Mobile <%=ind++%>:</td>	
	<td>
            <nested:text size="15" maxlength="9" styleClass="labelRespuesta" property="numero" onkeypress="return esTeclaNumeroSinComa(event)"/>
	<%if(ind==2){%>
<!-- 	  <a href="javascript:abreBusqueda()"><img src="image/busqueda.gif" alt="Buscar N&uacute;meros" border="0" width="17" height="17"  ></a> -->
<!-- 	  <a href="javascript:abrirAyudaFormato()" class="vinculomenu" title='Ayuda acerca del formato de los números'>Ayuda</a> -->
	<%}%>
	</td>
	</tr>
    </nested:iterate>    
   <tr>   
     <td colspan="2" align="center" >
         
			<input name="button1" type="button" class="botonDialogo" value="Consultar" onclick="javascript:reporte();">
            &nbsp;&nbsp;&nbsp;
     </td>
    </tr>
  </table>
</html:form>
</body>
</html:html>
