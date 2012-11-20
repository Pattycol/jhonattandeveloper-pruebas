<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="<c:url value="/celda/guardar" />" method="post" id="celdaFormulario">
	<fieldset>
		<legend>Celda</legend>
		<c:if test="${not empty c}">
		<input type="hidden" id="id" name="id" value="${c.id}" />
		</c:if>
		<p>
			<label for="identificador">Identificador</label>
			<input name="identificador" id="identificador" value="${c.identificador}" />
		</p>
		<p>
			<label for="x">Coordenada X</label>
			<input name="x" id="x" value="${c.x}" />
		</p>
		<p>
			<label for="y">Coordenada Y</label>
			<input name="y" id="y" value="${c.y}" />
		</p>
		<p>
			<label for="direccion">Dirección</label>
			<textarea name="direccion" id="direccion">${c.direccion}</textarea>
		</p>
		<p>
			<label for="estado">Activo</label>
			<input type="checkbox" name="estado" id="estado" value="A" <c:if test="${c.estado=='A'}">checked="checked"</c:if> />
		</p>
		<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
		<input type="submit" value="Guardar" style="float:right;"/>
		</c:if>
	</fieldset>
</form>