<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.chimera.org/chimera.tld" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p:html titulo="LBS - Mantenimiento de Empresas" estilo="mantenimiento" javascript="mantenimiento/empresa">
	<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
	<div id="nuevo">
		<button id="nueva">Nueva Empresa</button>
	</div>
	</c:if>
	<div id="existentes">		
		<label for="filtro">Buscar:</label>
		<input id="filtro" size="16" />
		<button id="buscar">Buscar</button>
		<div id="lista" class="ui-widget ui-widget-content empresa"></div>
		<strong style="color:#004276;">Áreas:</strong>
		<div id="areas" class="ui-widget ui-widget-content"></div>
		<strong style="color:#004276;">Usuarios:</strong>
		<div id="usuarios" class="ui-widget ui-widget-content"></div>
	</div>
	<div id="formularios">
		<div id="formulario"></div>
		<div id="formularioArea"></div>
		<div id="formularioUsuario"></div>
	</div>
</p:html>