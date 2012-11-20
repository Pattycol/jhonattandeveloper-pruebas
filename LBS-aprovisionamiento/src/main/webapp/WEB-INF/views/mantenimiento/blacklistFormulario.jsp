<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form action="<c:url value="/blacklist/guardar" />" method="post" id="blacklistFormulario">
	<fieldset>
		<legend>Blacklist</legend>
		<c:if test="${not empty b}">
		<input type="hidden" id="id" name="id" value="${b.id}" />
		</c:if>
		<p>
			<label for="numero">Número</label>
			<input name="numero" id="numero" value="${b.usuario.numero}" />
		</p>
		<p>
			<label for="fechaFin">Fecha de fin</label>
			<input id="fechaFin" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${b.fechaFin}" />" />
			<input type="hidden" name="fechaFin" id="fecha" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${b.fechaFin}" />" />
		</p>
		<p>
			<label for="estado">Activo</label>
			<input type="checkbox" name="estado" id="estado" value="A" <c:if test="${b.estado=='A'}">checked="checked"</c:if> />
		</p>
		<input type="submit" value="Guardar" style="float:right;"/>
	</fieldset>
</form>