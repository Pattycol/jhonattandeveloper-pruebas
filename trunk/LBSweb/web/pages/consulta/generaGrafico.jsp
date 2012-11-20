<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%@ page import="javax.servlet.http.*" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html:html>

<%
String rutaMapa=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RUTA_HOST_MAPA);
String tiempo=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_RECARGA_GRAFICO);
String reloadGrafico=(String)session.getAttribute(Constants.REQUEST_GRAFICO_AUTOMATICO);
if(reloadGrafico==null)
    reloadGrafico="";
%> 

<%-- Para la integración con google maps
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="include/jquery.js"></script>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
		<script type="text/javascript" src="include/mapa.js"></script>		
		<title>Mapa</title>
	</head>
	<body>
		<div id="coordenadas" style="display:none;">
			<c:forEach items="${ConsultaForm.resultadoConsulta.listaResultadoDetalle}" var="detalle" varStatus="status">
			<p>
				<input id="latitud${status.index}" value="${detalle.localizacion.ejeY}" />
				<input id="longitud${status.index}" value="${detalle.localizacion.ejeX}" />
				<input id="numero${status.index}" value="${detalle.mobileDestino}" />
			</p>
			</c:forEach>
		</div>
		<div id="mapa" style="width: 500px;height: 500px;"></div>
	</body>
	--%>
	<%-- name="<%=Constants.QUERY_SYSTEM_ARQ%>" --%>
	<bean:define id="strSql" name="ConsultaForm"  property="queryMapa"/>

	<head>
		<title>Documento sin t&iacute;tulo</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="include/estilos.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="include/jquery.js"></script>
		<script type="text/javascript">
			$(function(){
				var form=$("#frmGrafico");
				if(form.find("p").length>0){
					form.submit();
				}
			});
		</script>
	</head>
	
	<body>
		<form action="<%=rutaMapa%>" id="frmGrafico" method="post">
			<c:set var="i" value="0" />
			<c:forEach items="${ConsultaForm.resultadoConsulta.listaResultadoDetalle}" var="detalle">
			<c:if test="${not empty detalle.localizacion.ejeX and not empty detalle.localizacion.ejeY}">
			<p>
				<input type="hidden" name="x[${i}]" value="${detalle.localizacion.ejeX}" />
				<input type="hidden" name="y[${i}]" value="${detalle.localizacion.ejeY}" />
				<input type="hidden" name="telefonos[${i}]" value="${detalle.mobileDestino}" />
				<input type="hidden" name="fecha[${i}]" value="${detalle.fechaConsulta}" />
			</p>
			<c:set var="i" value="${i+1}" />
			</c:if>
			</c:forEach>
		</form>
	</body>	
</html:html>