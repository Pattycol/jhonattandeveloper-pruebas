<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.chimera.org/chimera.tld" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p:html titulo="LBS - Mantenimiento de Áreas" estilo="mantenimiento" javascript="mantenimiento/area">
	<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
	<div id="nuevo">
		<button id="nueva">Nueva Área</button>
	</div>
	</c:if>
	<c:choose>
	<c:when test="${empty empresas}">
	<strong class="error">Aún no se ha registrado ninguna empresa.</strong>
	</c:when>
	<c:otherwise>
	<div id="existentes">		
		<label>Empresa:</label>
		<select id="empresas">
			<option>Seleccione empresa</option>
			<c:forEach items="${empresas}" var="e">
			<option value="${e.id}">${e.razonSocial}</option>
			</c:forEach>
		</select>
		<div id="lista" class="ui-widget ui-widget-content"></div>
	</div>
	<div id="formularios">
		<div id="formulario"></div>
	</div>
	</c:otherwise>
	</c:choose>
</p:html>