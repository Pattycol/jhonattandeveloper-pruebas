<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="menu">
	<h3><a href="#">Mantenimientos</a></h3>
	<div>
		<ul>
			<li>
				<a href="<c:url value="/empresa" />">Empresas</a>
			</li>
			<li>
				<a href="<c:url value="/area" />">Áreas</a>
			</li>
			<li>
				<a href="<c:url value="/usuario" />">Usuarios</a>
			</li>
			<li>
				<a href="<c:url value="/celda" />">Celdas</a>
			</li>
			<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
			<li>
				<a href="<c:url value="/blacklist" />">Blacklists</a>
			</li>
			</c:if>
		</ul>
	</div>
	<c:if test="${usuarioSesion.rol.codigo=='super'}">
	<h3><a href="#">Reportes</a></h3>
	<div>
		<ul>
			<li>
				<a href="<c:url value="/consultasTrafico" />">Consulta Tr&aacute;fico </a>
			</li>
			<li>
				<a href="<c:url value="/consultasEmpresa" />">Consultas por Empresa</a>
			</li>
			<li>
				<a href="<c:url value="/consultasFecha" />">Consultas por Fecha</a>
			</li>
			<li>
				<a href="#">Auditoría</a>
			</li>
		</ul>
	</div>
	</c:if>
</div>

