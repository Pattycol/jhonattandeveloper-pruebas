<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.chimera.org/chimera.tld" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p:html titulo="LBS - Mantenimiento de Listas Negras" estilo="mantenimiento" javascript="mantenimiento/blacklist">
	<c:if test="${usuarioSesion.rol.codigo=='super'}">
	<div id="nuevo">
		<button id="nueva">Nuevo Blacklist</button>
	</div>
	</c:if>
	<div id="existentes">		
		<c:if test="${not empty blacklists}">
		<label for="filtro">Filtrar:</label>
		<input id="filtro" />
		<div id="lista" class="ui-widget ui-widget-content">
			<c:forEach items="${blacklists}" var="black">
			<div class="fila">
				<input type="hidden" class="idBlacklist" value="${black.id}" />
				<strong>${black.usuario.numero}</strong>
			</div>
			</c:forEach>
		</div>
		</c:if>
	</div>
	<div id="formularios">
		<div id="formulario"></div>
	</div>
</p:html>