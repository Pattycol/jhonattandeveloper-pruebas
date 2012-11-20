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
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:useBean id="fechaHoy" scope="session" class="java.util.Date"/>
<%
int numMaxMobiles=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_NUM_MOBILES));
//int tiempoConsulta=Integer.parseInt(Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_CONSULTA));
String nuevaTarea=(String)session.getAttribute(Constants.SESSION_NUEVA_TAREA);
String strcantmobilsel= (String)session.getAttribute(Constants.SESSION_CANT_MOBILES_SEL);
int cantmobilsel=0;
if (strcantmobilsel!=null) {
  cantmobilsel=Integer.parseInt(strcantmobilsel);    
}

Date fechaInicio=(Date)session.getAttribute(Constants.SESSION_FECHA_INICIO);

java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
String fechaIni = sdf.format(fechaInicio);

Date fechaFin=(Date)session.getAttribute(Constants.SESSION_FECHA_FIN);
String fechaFi = sdf.format(fechaFin);

String activaGrafico=(String)request.getAttribute(Constants.REQUEST_ACTIVA_GRAFICO);
int horaInicio = (Integer)session.getAttribute("horaInicio");
int horaFin = (Integer)session.getAttribute("horaFin");

%>



<html:html>
	<head>
		<title>Documento sin t&iacute;tulo</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		
		
    <script src="include/valida.js" type="text/javascript"></script>
    
    <!-- jQuery DateTimePicker-->
	
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
		

   	 
		<script type="text/javascript">

			function periodo(obj){ 			
				var f = document.ConsultaTareaForm;
				if (f.codPeriodo.value=='<%=Constants.COD_PERIODO_SEMANAL %>'){
					document.getElementById('divSemanal').style.display = "";
					document.getElementById('divSemanal_2').style.display = "";
				}
				else{
					document.getElementById('divSemanal').style.display = "none";
					document.getElementById('divSemanal_2').style.display = "none";
				}		
			}

			function doAction(action) {
				var f = document.ConsultaTareaForm; 
				if (action=="") {
					action=f.button1.value;
				}
				if (action=="cancelar") {
					alert("Cancelar");
				<% if (nuevaTarea.equals(Constants.NUEVA_TAREA) || nuevaTarea.equals(Constants.MODIFICAR_TAREA)) { %>
					f.method.value="pasar";
					  //goMethodDemo(1,'Consulta','consultaSimpleLBS');
				<% } else if (nuevaTarea.equals(Constants.CONFIRMAR_TAREA)) { %>
					f.method.value="regresarNuevaTarea";
				<%} %>
					f.submit();
				}
				else if (action=="Registrar") {
         
					if (validaIngresoTarea()) {
						f.button1.disabled=true;
						f.method.value="registrarTarea";
						f.submit();
					}
				}
				else if (action=="Modificar") {
					f.button1.disabled=true;
					f.method.value="grabarModificarTarea";
					f.submit();
				}
				else if (action=="Confirmar") {
					f.button1.disabled=true;
					f.method.value="confirmarTarea";
					f.submit();
				} 
			}
    
    function cargar() {
    	//alert("Entro en el Cargar Nueva TAREA");
      f = document.ConsultaTareaForm;   
      if (f.codPeriodo.value=='<%=Constants.COD_PERIODO_SEMANAL %>'){
             document.getElementById('divSemanal').style.display = "";
             document.getElementById('divSemanal_2').style.display = "";
      } else {
             document.getElementById('divSemanal').style.display = "none";
             document.getElementById('divSemanal_2').style.display = "none";
      }
      <% if (nuevaTarea.equals(Constants.NUEVA_TAREA)) { %>
         f.button1.value="Registrar";
         f.codFormato.disabled=false;
         f.codPeriodo.disabled=false;
         f.intervalo.disabled=false;
         
      <% } else if (nuevaTarea.equals(Constants.MODIFICAR_TAREA) || nuevaTarea.equals(Constants.CONFIRMAR_TAREA)){
              if (nuevaTarea.equals(Constants.CONFIRMAR_TAREA)) {
         %>
               f.button1.value="Confirmar";
               f.codHorario.disabled=true;
          <% } else if (nuevaTarea.equals(Constants.MODIFICAR_TAREA)) {%>
               f.button1.value="Modificar";
          <% } %>
         f.codFormato.disabled=true;
         f.codPeriodo.disabled=true;
         f.intervalo.disabled=true;
         for(i=0; i<<%=cantmobilsel%> ;i++) { 
            document.getElementsByName('listaMobiles[' + i + '].numero')[0].disabled = true;
	 }
	 if (f.codPeriodo.value=='<%=Constants.COD_PERIODO_SEMANAL %>'){
             for (i=0;i<(f.selectedItems.length);i++) {
                f.selectedItems[i].disabled=true;
             }
         }
     <% } %>
    }
    
    //Validaciones Numeros Validos
    /*
    function validaNumerosConsulta(){
       var frm=document.ConsultaTareaForm;
       var auxVacio=-1;
       var auxLleno=-1;
       //ubica el primer nro vacio
       for (var i = 0; i < <%=numMaxMobiles%>; i++) { 
        if(esVacio(document.getElementsByName('listaMobiles[' + i + '].numero')[0].value)){
            auxVacio=i;
            break;
          }
        }
      //ubica el ultimo nro lleno
      for (var j = 0; j < <%=numMaxMobiles%>; j++) { 
       if(!esVacio(document.getElementsByName('listaMobiles[' + j + '].numero')[0].value)){
          auxLleno=j;        
        }
      }
     if(auxLleno==-1){
        alert('Debe ingresar un número');
        document.getElementsByName('listaMobiles[0].numero')[0].focus();
        return false;
     }  
     if(auxVacio!=-1 && auxVacio < auxLleno){
        alert('Debe ingresar los números de manera consecutiva');
        document.getElementsByName('listaMobiles[' + auxVacio + '].numero')[0].focus();
        return false;
     }
     
     for (var k = 0; k < <%=numMaxMobiles%>; k++) { 
       if(!esVacio(document.getElementsByName('listaMobiles[' + k + '].numero')[0].value)){
          if(document.getElementsByName('listaMobiles[' + k + '].numero')[0].value.length<9){
             alert('Los números deben tener un mínimo de 9 dígitos');
             document.getElementsByName('listaMobiles[' + k + '].numero')[].focus();
             return false;
          }   
       }
     }
      return true; 
    }*/

    function validaNumerosConsulta(){
    	var frm=document.ConsultaTareaForm;
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
        if(!esVacio(document.getElementsByName('listaMobiles[' + k + '].numero')[0].value)){
          
            if(document.getElementsByName('listaMobiles[' + k + '].numero')[0].value.length<8){
               alert('Los números deben tener un mínimo de 8 dígitos');
               document.getElementsByName('listaMobiles[' + k + '].numero')[0].focus();
                return false;
               }
               
        }
    	}*/
    	return true; 
    }
    
     //Validaciones
     function validarDiasSeleccionados(){
           check=false;
           var f=document.ConsultaTareaForm;
           if (f.codPeriodo.value=='<%=Constants.COD_PERIODO_SEMANAL%>') {
             for (i=0;i<(f.selectedItems.length);i++) {
                 if (f.selectedItems[i].checked==true) {
                    check = true;
                    break;
                 }
                 
              }
           } else { check=true; }
           return check;
      }
      
			function validaIngresoTarea() {
				var f=document.ConsultaTareaForm;

    	  
    	//agregado 14/08/08
				var _fecha='<%=fechaHoy %>';
				var _auxfecha = _fecha.split("/");	

				var _fechaHoy=parseFloat(_auxfecha[2])*12*30 +  parseFloat(_auxfecha[1])*30 + parseFloat(_auxfecha[0]);



				_fecha=f.fechaFinString.value;
				_auxfecha = _fecha.split("/");	

				var _fechaFin= parseFloat(_auxfecha[2])*12*30 + parseFloat(_auxfecha[1])*30 + parseFloat(_auxfecha[0]);
          
        
				if (f.codFormato.value==<%=Constants.COD_SELECCIONE%>) {
					alert('Debe seleccionar un Formato de Respuesta');
					f.codFormato.focus();
					return false;
				}
				else if(!validaNumerosConsulta()) {
					return false;
				}
				else if (f.fechaInicioString.value=="") {
					alert('Debe ingresar una fecha de Inicio');
					return false;
				}
				else if (f.fechaFinString.value=="") {
					alert('Debe ingresar una fecha Fin');
					return false;
				}
				else if (!isDate(f.fechaInicioString.value)) {
					return false;
				}
				else if (!isDate(f.fechaFinString.value)) {
					return false;
				}
				else if (!compararFechaMayor(f.fechaInicioString.value,f.fechaFinString.value)) { 
					alert("La fecha final debe ser mayor que la fecha inicial"); 
					return false;    
				}
				else if (!compararFechaMayor('<%=fechaHoy %>',f.fechaInicioString.value)) { 
					alert("La fecha de inicio debe ser mayor o igual al "+ '<%=fechaHoy %>'); 
					return false;   
				}
				else if (f.codPeriodo.value==<%=Constants.COD_SELECCIONE%>) {
					alert('Debe seleccionar un periodo');
					f.codPeriodo.focus();
					return false;  
				}
				else if (!validarDiasSeleccionados()) {
					alert('Debe seleccionar al menos un dia de la semana ');
					return false;  
				}
<%--				else if (f.codHorario.value==<%=Constants.COD_SELECCIONE%>) {
					alert('Debe seleccionar un horario');
					f.codHorario.focus();
					return false;  
				}--%>
				else if (f.intervalo.value==<%=Constants.COD_SELECCIONE%>) {
					alert('Debe seleccionar el Intervalo');
					f.intervalo.focus();
					return false;  
				}
				else if ( _fechaFin - _fechaHoy >8 ) {
					alert("La fecha final no debe sobrepasar de una semana"); 
					return false;  
				}
				return true;
			}
      
function cargaGrafico(){
  // goFrameDer('ConsultaTarea.do?method=mapa');
    goMethodDemo(1,'ConsultaTarea','mapa');
}
<% if(activaGrafico!=null){
%>
      cargaGrafico();
<%}%>     
</script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">

</head>

<body onload="cargar()" topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">
<html:form action='ConsultaTarea' method='post'>
<input type="hidden" name="method" >

<table border="0" >
  <tr>
    <td colspan="2" class="tituloPrincipal">Registro de Tarea para Consulta LBS</td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  <tr>
    <td colspan="2" class="menu_cabecera">Datos de la Tarea</td>
    </tr>
</table>
<html:errors/>
<logic:notEmpty name="<%=Constants.MENSAJE_USUARIO%>" scope="request">
<div class='textoMensajeError'> 
<bean:write  name="<%=Constants.MENSAJE_USUARIO%>" scope="request" />
</div>
</logic:notEmpty>
 <table border="0" width="307"  >
     <tr>
     <td width="84" class="labelPrim">Formato de Respuesta:</td>
     <td width="213"> 
     <html:select property="codFormato" styleClass="comboSelector" > 
        <html:option value='<%= Constants.COD_SELECCIONE%>' ><%=Constants.DESC_SELECCIONE%></html:option> 
        <html:optionsCollection property="listaFormatos" value="codigo" label="descripcion"/> 
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
	<% if (nuevaTarea.equals(Constants.NUEVA_TAREA)) {%>
<!-- 	  <a href="javascript:abreBusqueda()"><img src="image/busqueda.gif" alt="Buscar N&uacute;meros" border="0" width="17" height="17"  ></a> -->
	<% } %>
<!-- 	<a href="javascript:abrirAyudaFormato()" class="vinculomenu" title='Ayuda acerca del formato de los números'>Ayuda</a> -->
        <%}%>
	</td>
	</tr>
    </nested:iterate>      
   <tr>
  	<td class="labelPrim">&nbsp;</td>
  	<td>&nbsp;</td>  	
  </tr>
    <tr>     
    <td class="labelPrim">Fecha Inicio:</td>
    <td colspan="2"  >
    <%if(nuevaTarea.equals(Constants.MODIFICAR_TAREA)  || nuevaTarea.equals(Constants.CONFIRMAR_TAREA)){%>
    	
    	<input styleClass="labelRespuesta"  name="fechaInicioString" value="<%=fechaIni%>" disabled="true"  size="10" id="txtfecInicio" readonly="readonly" maxlength="10" >
    <%}else{ %>
    	
    	<input styleClass="labelRespuesta"  name="fechaInicioString" value="<%=fechaIni%>"  id="txtfecInicio" size="10" readonly="readonly" maxlength="10"  >
    <%} %>
    </td>
  </tr>
  <tr>
	<td class="labelPrim">Fecha Fin:</td>
	<td colspan="2">
	<%if(nuevaTarea.equals(Constants.MODIFICAR_TAREA)  || nuevaTarea.equals(Constants.CONFIRMAR_TAREA)){%>
	
		<input styleClass="labelRespuesta"  name="fechaFinString" value="<%=fechaFi%>" id="txtfecFin" disabled="true" size="10"  readonly="readonly" maxlength="10" />
	<%}else{%>
	
		<input styleClass="labelRespuesta"  name="fechaFinString" value="<%=fechaFi%>"  id="txtfecFin"  readonly="readonly" size="10" maxlength="10" />
		
	<%} %>
	</td>
  </tr>
  <tr>
  	<td class="labelPrim">Periodo:</td>
  	<td>
        <html:select property="codPeriodo" styleClass="comboSelector" onchange="javascript:periodo(this)"> 
            <html:option value='<%= Constants.COD_SELECCIONE%>' ><%=Constants.DESC_SELECCIONE%></html:option> 
            <html:optionsCollection property="listaPeriodos" value="codigo" label="descripcion"/> 
        </html:select>
  	</td>
  </tr>	
	<tr id="divSemanal" >
	  <td></td>
	  <td >
	  <% int i=1; %>
	  <logic:iterate name="ConsultaTareaForm" id="item" property="items">	
              <html:multibox property="selectedItems" >
               <bean:write name="item" /> 
              </html:multibox> 
              <%=Helper.getDescDias(i)%>
              <% i++; %> 
  
         </logic:iterate>
        </td>
	</tr>
	<tr id="divSemanal_2" >
	  <td></td>
	</tr>
	
	 <tr>     
    <td class="labelPrim">Hora Inicio:</td>
    <td>
	    <%if(nuevaTarea.equals(Constants.MODIFICAR_TAREA)  || nuevaTarea.equals(Constants.CONFIRMAR_TAREA)){%>
	    	
	    	<input type="text" name="horaInicio" value="<%=horaInicio%>" disabled="true" size="2" id="txtHoraInicio" maxlength="2" >
	    	
	    <%}else{ %>
	    	
	    	<input type="text"   name="horaInicio" value="<%=horaInicio%>" size="2" id="txtHoraInicio"  maxlength="2"  >
	    <%} %>
	    Formato 24hrs.
    </td>
  </tr>
  <tr>
	<td class="labelPrim">Hora Fin:</td>
	<td colspan="2">
		<%if(nuevaTarea.equals(Constants.MODIFICAR_TAREA)  || nuevaTarea.equals(Constants.CONFIRMAR_TAREA)){%>
		
			<input type="text"  name="horaFin" value="<%=horaFin%>" size="2" id="txtHoraFin" disabled="true"  maxlength="2" />
		<%}else{%>
		
			<input type="text"  name="horaFin" value="<%=horaFin%>" size="2" id="txtHoraFin" maxlength="2" />
			
		<%} %>
		Formato 24hrs.
	</td>
  </tr>
  

        
	<tr>
	  <td height="24" class="labelPrim">Intervalo:</td>
	  <td class="comboSelector">
	  <% if (nuevaTarea.equals(Constants.MODIFICAR_TAREA)) { %>
             <html:text size="10" maxlength="9" property="intervalo" disabled="true" />
           <% } else { %>
              <html:select property="intervalo" styleClass="comboSelector" > 
                <html:option value='<%= Constants.COD_SELECCIONE%>' ><%=Constants.DESC_SELECCIONE%></html:option> 
                <html:optionsCollection property="listaIntervalos" value="codigo" label="descripcion"/> 
             </html:select>
           <% } %>
	  </td>
    </tr>
  </table>
  <table width="307">
    <tr> <td width="86"></td> 
    <td width="209">
    <logic:notEmpty name="<%=Constants.MENSAJE_CANTIDAD_CONSULTA%>" scope="request">
    <div class='textoMensajeError'> 
    <bean:write  name="<%=Constants.MENSAJE_CANTIDAD_CONSULTA%>" scope="request" />
    </div>
    </logic:notEmpty>
    </td>
    </tr>
  </table>
 
   <table width="85%"  border="0" align="center">
    <tr>
    <td width="34%" >&nbsp;</td>
     <td width="16%" >
            <input name="button1" type="button" class="botonDialogo" value="Registrar" onclick="javascript:doAction('')">
     </td>
     <td width="16%">
            <input name="button" type="button" class="botonDialogo" value="Cancelar" onclick="javascript:doAction('cancelar')">
     </td>
     <td width="34%" >&nbsp;</td>
     </tr>
  </table>
</html:form>
</body>
</html:html>
