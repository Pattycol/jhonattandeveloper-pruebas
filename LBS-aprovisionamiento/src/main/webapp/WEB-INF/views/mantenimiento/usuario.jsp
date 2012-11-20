<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.chimera.org/chimera.tld" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p:html titulo="LBS - Mantenimiento de Usuarios" estilo="mantenimiento" javascript="mantenimiento/usuario">
	<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
	<div id="nuevo">
		<button id="nueva">Nuevo Usuario</button>
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