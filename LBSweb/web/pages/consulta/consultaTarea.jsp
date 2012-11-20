<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
<bean:define id="listaTareas" name="ConsultaTareaForm" property="listaTareas"/>
<bean:define id="fecha1" name="ConsultaTareaForm" property="fechaInicio" type="java.util.Date"/>
<bean:define id="fecha2" name="ConsultaTareaForm" property="fechaFin" type="java.util.Date"/>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	List lista=(List)listaTareas;
	if(lista==null)
		lista=new ArrayList();
	int size=lista.size();
	String ruta = null; 
	java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yy");
	String fechaUno = sdf.format(fecha1);
	String fechaDos = sdf.format(fecha2);
%>
<html:html>
	<head>
		<title>Consulta de Tareas</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		
   	<script type="text/javascript" src="js/jquery.js"></script> 
   	
   	
   	
   			
<!-- jQuery DateTimePicker-->
	<!--  <link rel="stylesheet" type="text/css" href="include/dtp4Demo/css/dtp4/jquery.ui.theme.css">-->
	<link rel="stylesheet" type="text/css" href="include/timePickerDemo/jquery-ui-1.7.3.custom.css">
	<link rel="stylesheet" type="text/css" href="include/dtp4Demo/css/dtp4/jquery.ui.core.css">
	<link rel="stylesheet" type="text/css" href="include/dtp4Demo/css/dtp4/jquery.ui.datepicker.css">
	<link rel="stylesheet" href="include/dtp4Demo/css/dtp4/demos.css">

	<script src="include/dtp4Demo/js/dtp4/jquery-1.6.2.js"></script>
	<script src="include/dtp4Demo/js/dtp4/jquery.ui.core.js"></script>
	<script src="include/dtp4Demo/js/dtp4/jquery.ui.widget.js"></script>
	<script src="include/dtp4Demo/js/dtp4/jquery.ui.datepicker.js"></script>
	<script src="include/dtp4Demo/js/dtp4/jquery.ui.datepicker-es.js"></script>
	
	<!-- LO ANTERIOR  -->
		<script type="text/javascript" src="include/general.js"></script>
		<script type="text/javascript" src="include/valida.js"></script>
	
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
		
		//$( "#txtfecInicio" ).datepicker( $.datepicker.regional[ "es" ] );	
		
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
   	
		<script type="text/javascript">
		
			/*function doAction(action) {
				var f=document.forms[1];   
				if (action=="buscar") {
					if (f.fechaInicioString.value!="" && !isDate(f.fechaInicioString.value)) {
						return "";
					}
					else if (f.fechaFinString.value!="" && !isDate(f.fechaFinString.value)) {
						return "";
					}
					f.method.value="consultaTarea";
					f.submit();
				}
				else if (action=="nuevaTarea") {
					f.action="ConsultaTarea.do?method=nuevaTarea";
					f.method.value="nuevaTarea";
					f.submit();
				}
			}*/

			function doAction(action) {
			    var f=document.ConsultaTareaForm;   
			    if (action=="buscar") {
			     if (f.fechaInicioString.value=="") {
			      alert("Ingresar una fecha inicio!")
			      return false;
			     }
			     else if (f.fechaFinString.value=="") {
			      alert("Ingrese una fecha fin")
			      return false;
			     }
			     
			     f.method.value="consultaTarea";
			     f.submit();
			    }
			    else if (action=="nuevaTarea") {
			     f.action="ConsultaTarea.do?method=nuevaTarea";
			     f.method.value="nuevaTarea";
			     f.submit();
			    }
			   }
		
		</script>
		<link href="include/estilos.css" rel="stylesheet" type="text/css">
	</head>

	<body >
<html:form action='ConsultaTarea' method='post'>
<input type="hidden" name="method" >

<table border="0" width="500px">
  <tr>
    <td colspan="2" class="tituloPrincipal"> Lista de Tareas<br>para Consulta LBS </td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  <tr>
    <td colspan="2" class="menu_cabecera">Datos de la Consulta</td>
    </tr>
</table>
<table border="0" width="450px;">
     <tr>
         <td width="20%" align="right" class="labelPrim">N&uacute;mero:</td>
         <td width="30%"><html:text size="13" maxlength="9" styleClass="labelRespuesta" property="mobile" onkeypress="return esTeclaNumeroSinComa(event)" /></td>
         <td width="20%" class="labelPrim">Estado:</td>
         <td width="30%">
          <html:select property="codEstado" styleClass="comboSelector"> 
              <html:option value='<%= Constants.COD_TODOS%>' ><%=Constants.DESC_TODOS%></html:option> 
              <html:optionsCollection property="listaEstados" value="codigo" label="descripcion"/> 
          </html:select>
         </td>         
    </tr>
    <tr>
         <td class="labelPrim">Fecha Inicial:</td>
         <td colspan="3"><input styleClass="labelRespuesta" size="10" value="<%=fechaUno%>" name="fechaInicioString" id="txtfecInicio" maxlength="10" >
     
    </tr>
    <tr>
       <td class="labelPrim">Fecha Final:</td>
       <td colspan="3"><input styleClass="labelRespuesta" size="10" value="<%=fechaDos%>" name="fechaFinString"   id="txtfecFin" maxlength="10" />
     
     </tr>
     <tr>
       <td class="labelPrim">&nbsp;</td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td align="center"><input name="Submit" type="button" class="boton" value="Buscar" onclick="javascript:doAction('buscar')"></td>
     </tr>
</table>
<br>
<table width="450px" id="idTarea">
<tr style="width:300px;" >
<logic:empty name="ConsultaTareaForm" property="listaTareas" > 
    <table width="450px" border="0" align="left" cellpadding="0" cellspacing="0">
        <tr> 
         <td class="texto comentario">No existen registros</td>
       </tr>
     </table>
</logic:empty>    
<logic:notEmpty name="ConsultaTareaForm" property="listaTareas"> 
       <displayTag:table name="listaTareas" width="450px;" requestURI="ConsultaTarea.do?method=pasar" scope="page" pagesize="5" id="res" >
            <displayTag:column  title="F.Inicio" sortable="true">
            <%ruta = "ConsultaTarea.do?method=modificarTarea&indice=" + res_rowNum; %>
	    <html:link action="<%=ruta%>"><c:out value="${res.fechaInicioString}"/></html:link> 
             </displayTag:column>
            <displayTag:column  title="F.Fin" property="fechaFinString" sortable="true" />  
            <displayTag:column  title="Numero Consultado" property="listaMobiles[0].numero" sortable="true" />  
            <displayTag:column  title="Estado" property="descEstado" sortable="true" />  
            <displayTag:caption>
                    <displayTag:headerdisplay title="Lista de Tareas" />
            </displayTag:caption>
            <displayTag:footer>
                    <displayTag:footerdisplay />
            </displayTag:footer>
      </displayTag:table>
</logic:notEmpty> 
 <logic:notEmpty name="ConsultaTareaForm" property="listaTareas"> 
      <table width="52%" border="0" align="left" cellpadding="0" cellspacing="0"> 
          <tbody> 
          <tr class='texto1'> 
              <td>Total registros: <%=size %> </td>
          </tr>
          </tbody> 
      </table>
</logic:notEmpty> 
</tr>
</table>
<table align="left" width="450px">
 <tr>   
     <td align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Nueva Tarea" onclick="doAction('nuevaTarea');">
     </td>
 </tr>
</table>
</html:form>
</body>
</html:html>
