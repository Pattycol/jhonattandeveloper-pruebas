<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@taglib uri="http://displaytag.sf.net" prefix="displayTag" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>

<bean:define id="listaResultado" name="ConsultaForm" property="resultadoConsulta.listaResultadoDetalle" type="java.util.ArrayList"/>
<%
if(listaResultado==null) listaResultado=new java.util.ArrayList();
 int size=listaResultado.size();
 %>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script src="include/general.js" type="text/javascript"></script>
<script src="include/valida.js" type="text/javascript"></script>
<script type="text/javascript" src="include/jquery.js"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function cargaGrafico(){
    //goFrameDer('Consulta.do?method=mapa');
  //  alert("cargaGrafico")
	goMethodDemo(1,'Consulta','mapa')
}
</script>
</head>

<body onload="cargaGrafico()" topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">  
<!--  <body >  -->
<html:form action='Consulta' method='post'>
<input type="hidden" name="method" >

<table border="0">
  <tr>
    <td colspan="2" class="tituloPrincipal"> Consulta LBS </td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  <tr>
    <td colspan="2" class="menu_cabecera">Resultado de la Consulta</td>
    </tr>
</table>

<br>
<table id="wb">
<tr><td>   
<logic:notEmpty name="ConsultaForm" property="resultadoConsulta.listaResultadoDetalle"> 
       <displayTag:table messageEmpty="No existen registros" name="listaResultado" width="100%" scope="page" requestURI="Consulta.do?method=siguiente" pagesize="5" id="res" >            
            <displayTag:column width="25%" title="Mobile Destino" property="mobileDestino" sortable="false" headerClass="cabecera"/>
            <displayTag:column width="75%" title="Resultado" property="resultado" sortable="false" headerClass="cabecera"/>            
            <displayTag:caption>
                    <displayTag:headerdisplay title="" />
            </displayTag:caption>
            <displayTag:footer>
                    <displayTag:footerdisplay />
            </displayTag:footer>
      </displayTag:table>
   </logic:notEmpty>
</td>
</tr>
<logic:notEmpty name="ConsultaForm" property="resultadoConsulta.listaResultadoDetalle">
<tr class='texto1'> 
  <td>Total registros: <%=size %> </td>
</tr>
</logic:notEmpty>
</table>
<table align="center">
 <tr>   
     <td align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Regresar" onclick="javascript:goMethodDemo(1,'Consulta','regresar')">
     </td>
 </tr>
</table>

 <c:set var="i" value="0" />
			<c:forEach items="${ConsultaForm.resultadoConsulta.listaResultadoDetalle}" var="detalle">
			<c:if test="${detalle.localizacion != null and detalle.localizacion != null}">
			<%System.out.println("Dentro del ifff con RESULTADO "); %>
			<p>
				<input type="hidden" name="x[${i}]" value="${detalle.localizacion.ejeX}" />
		<!-- 	<c:out value="${i}"/>	<c:out value="${detalle.localizacion.ejeX}"/>  -->
				<input type="hidden" name="y[${i}]" value="${detalle.localizacion.ejeY}" />
				<input type="hidden" name="telefonos[${i}]" value="${detalle.mobileDestino}" />
				<input type="hidden" name="fecha[${i}]" value="${detalle.fechaConsulta}" />
			</p>
			<c:set var="i" value="${i+1}" />
			</c:if>
			</c:forEach> 

</html:form>
</body>
</html:html>