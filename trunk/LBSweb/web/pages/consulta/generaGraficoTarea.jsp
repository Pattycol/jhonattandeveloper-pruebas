<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%
  String rutaMapa=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RUTA_HOST_MAPA);
%>
<html>

	<head>
		<title>Documento sin t&iacute;tulo</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
			<c:forEach items="${ConsultaTareaForm.localizaciones}" var="l">
			<c:if test="${not empty l.ejeX and not empty l.ejeY}">
			<p>
				<input type="hidden" name="x[${i}]" value="${l.ejeX}" />
				<input type="hidden" name="y[${i}]" value="${l.ejeY}" />
				<input type="hidden" name="telefonos[${i}]" value="${l.mobileDestino}" />
				<input type="hidden" name="fecha[${i}]" value="${l.fecha}" />
			</p>
			<c:set var="i" value="${i+1}" />
			</c:if>
			</c:forEach>
		</form>
		</body>
</html>