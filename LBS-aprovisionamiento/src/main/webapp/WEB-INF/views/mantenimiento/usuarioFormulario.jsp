<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="<c:url value="/usuario/guardar" />" method="post" id="formUsuario">
	<fieldset>
		<legend>Usuario</legend>
		<c:if test="${not empty u}">
		<input type="hidden" id="idUsuario" name="id" value="${u.id}" />
		</c:if>
		<p>
			<label>Rol</label>
			<select name="rol.id" id="idRol"<c:if test="${usuarioSesion.rol.codigo=='atencion'}"> disabled="disabled"</c:if>>
				<option value="-1">Seleccione Rol</option>
				<c:forEach items="${roles}" var="r">
				<option label="${r.codigo}" value="${r.id}" <c:if test="${r.id==u.rol.id}">selected="selected"</c:if>>${r.nombre}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label for="nombres">Nombres</label>
			<input name="nombres" id="nombres" value="${u.nombres}"<c:if test="${usuarioSesion.rol.codigo=='atencion'}"> readonly="readonly"</c:if> />
		</p>
		<p>
			<label for="apellidos">Apellidos</label>
			<input name="apellidos" id="apellidos" value="${u.apellidos}"<c:if test="${usuarioSesion.rol.codigo=='atencion'}"> readonly="readonly"</c:if> />
		</p>
		<p>
			<c:choose>
			<c:when test="${u.rol.codigo=='admin' or u.rol.codigo=='atencion'}">
			<label for="numero">Usuario</label>
			</c:when>
			<c:otherwise>
			<label for="numero">Número</label>
			</c:otherwise>
			</c:choose>
			<input name="numero" id="numero" value="${u.numero}"<c:if test="${usuarioSesion.rol.codigo=='atencion'}"> readonly="readonly"</c:if> />
		</p>
		<p>
			<label for="lbsweb">LBSweb</label>
			<input type="checkbox" id="lbsweb"<c:if test="${not empty u.clave and u.rol.codigo=='jefe'}"> checked="checked"</c:if><c:if test="${u.rol.codigo!='jefe'}"> disabled="disabled"</c:if> />
		</p>
		<p>
			<label for="clave">Clave</label>
			<input id="clave" name="clave" type="password"<c:if test="${u.rol.codigo=='empleado'}"> disabled="disabled"</c:if> />
			<input id="claveOld" name="claveOld" type="hidden" value="${u.clave}" />
		</p>
		<c:choose>
		<c:when test="${empty area}">
		<p>
			<label>Empresa</label>
			<select id="idEmpresa"<c:if test="${usuarioSesion.rol.codigo=='atencion'}"> disabled="disabled"</c:if>>
				<option value="-1">Seleccione Empresa</option>
				<c:forEach items="${empresas}" var="e">
				<option value="${e.id}" <c:if test="${e.id==detalleArea.idEmpresa}">selected="selected"</c:if>>${e.razonSocial}</option>
				</c:forEach>
			</select>
		</p>
		<div style="margin:5px 0;height: 220px;">
			<label>Áreas</label>
			<div id="areasUsuario">
				<div style="float:left;width:45%;height:200px;">
					<span style="font-size:0.8em">Disponibles</span>
					<div id="disponibles" class="ui-widget ui-widget-content" style="overflow:auto;height:180px;">
						<c:forEach items="${detalleArea.disponibles}" var="a">
						<div class="disponible" style="cursor:pointer;">
							<input type="hidden" class="idArea" value="${a.id}" />
							<span>${a.nombre}</span>
						</div>
						</c:forEach>
					</div>
				</div>
				<div style="float:left;width:10%;height:200px;text-align:center;">
					<strong>&gt;<br />A<br />R<br />R<br />A<br />S<br />T<br />R<br />A<br />R<br />&gt;</strong>
				</div>
				<div style="float:left;width:45%;height:200px;">
					<span style="font-size:0.8em">Asignadas</span>
					<div id="asignadas" class="ui-widget ui-widget-content" style="overflow:auto;height:180px;">
						<c:forEach items="${detalleArea.asignadas}" var="a">
						<div class="asignada" style="cursor:pointer;">
							<input type="hidden" class="idArea" value="${a.id}" />
							<span>${a.nombre}</span>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
		<p>
			<label>Empresa</label>
			<span>${area.empresa.razonSocial}</span>
		</p>
		<p>
			<label>Área</label>
			<input type="hidden" name="idsAreas" value="${area.id}" />
			<span>${area.nombre}</span>
		</p>
		</c:otherwise>
		</c:choose>
		<!--p>
			<label for="cpm" style="font-size:0.57em">Máx. Consultas mensuales</label>
			<input name="consultasPorMes" id="cpm" value="${u.consultasPorMes}" size="2" />
		</p-->
		<p>
			<label for="estado">Activo</label>
			<input type="checkbox" name="estado" id="estado" value="A" <c:if test="${u.estado=='A'}">checked="checked"</c:if><c:if test="${usuarioSesion.rol.codigo=='atencion'}"> readonly="readonly"</c:if> />
		</p>
		<input type="submit" value="Guardar" style="float:right;"/>
		<c:if test="${usuarioSesion.rol.codigo!='atencion' and not empty u}">
		<input id="eliminarUsuario" type="button" value="Eliminar" style="float:right;"/>
		</c:if>
	</fieldset>
</form>