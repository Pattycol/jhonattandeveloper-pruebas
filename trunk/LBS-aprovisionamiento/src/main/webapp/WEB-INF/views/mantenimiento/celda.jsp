<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.chimera.org/chimera.tld" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p:html titulo="LBS - Mantenimiento de Celdas" estilo="mantenimiento" javascript="mantenimiento/celda">
	<c:if test="${usuarioSesion.rol.codigo=='super'}">
	<div id="nuevo">
		<button id="nueva">Nueva Celda</button>
	</div>
	</c:if>
	<div id="existentes">		
		<label for="filtro">Buscar:</label>
		<input id="filtro" size="16" />
		<button id="buscar">Buscar</button>
		<div id="lista" class="ui-widget ui-widget-content">
		</div>
	</div>
	<div id="formularios">
		<div id="formulario"></div>
	</div>
</p:html>