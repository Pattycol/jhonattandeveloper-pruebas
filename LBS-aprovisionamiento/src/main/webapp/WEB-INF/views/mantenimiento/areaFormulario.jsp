<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="<c:url value="/area/guardar" />" method="post" id="formArea">
	<fieldset>
		<legend>Área</legend>
		<c:if test="${not empty a}">
		<input type="hidden" id="idArea" name="id" value="${a.id}" />
		</c:if>
		<p>
			<label for="nombre">Nombre</label>
			<input name="nombre" id="nombre" value="${a.nombre}" />
		</p>
		<p>
			<label>Empresa</label>
			<c:choose>
			<c:when test="${empty empresa}">
			<select name="empresa.id" id="idEmpresa">
				<option value="-1">Seleccione Empresa</option>
				<c:forEach items="${empresas}" var="e">
				<option value="${e.id}" <c:if test="${e.id==a.empresa.id}">selected="selected"</c:if>>${e.razonSocial}</option>
				</c:forEach>
			</select>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="empresa.id" value="${empresa.id}" />
			<span>${empresa.razonSocial}</span>
			</c:otherwise>
			</c:choose>
		</p>
		<p>
			<label>Área padre</label>
			<select name="idPadre" id="padre">
				<option value="-1">Ninguna</option>
				<c:forEach items="${padres}" var="p">
				<option value="${p.id}"<c:if test="${p.id==a.padre.id}">selected="selected"</c:if>>${p.nombre}</option>
				</c:forEach>
			</select>
		</p>
		<!--p>
			<label for="cpm" style="font-size:0.57em">Máx. Consultas mensuales</label>
			<input name="consultasPorMes" id="cpm" value="${a.consultasPorMes}" size="2" />
		</p-->
		<p>
			<label for="estado">Activo</label>
			<input type="checkbox" name="estado" id="estado" value="A" <c:if test="${a.estado=='A'}">checked="checked"</c:if> />
		</p>
		<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
		<input type="submit" value="Guardar" style="float:right;"/>
		<c:if test="${not empty empresa and not empty a}">
		<input type="button" id="nuevoUsuario" style="float:right;" value="Registrar Usuario" />
		</c:if>
		</c:if>
	</fieldset>
</form>