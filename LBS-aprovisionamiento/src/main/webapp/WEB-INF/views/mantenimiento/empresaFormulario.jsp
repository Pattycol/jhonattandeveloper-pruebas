<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="<c:url value="/empresa/guardar" />" method="post" id="formEmpresa">
	<fieldset>
		<legend>Empresa</legend>
		<c:if test="${not empty e}">
		<input type="hidden" id="idEmpresa" name="id" value="${e.id}" />
		</c:if>
		<p>
			<label for="ruc">RUC</label>
			<input name="ruc" id="ruc" value="${e.ruc}" />
		</p>
		<p>
			<label for="razonSocial">Razón Social</label>
			<input name="razonSocial" id="razonSocial" value="${e.razonSocial}" />
		</p>
		<p>
			<label for="codigoCliente">Código de Cliente</label>
			<input name="codigoCliente" id="codigoCliente" value="${e.codigoCliente}" />
		</p>		
		<p>
			<label for="estado">Activo</label>
			<input type="checkbox" name="estado" id="estado" value="A" <c:if test="${e.estado=='A'}">checked="checked"</c:if> />
		</p>
			
		<c:if test="${sessionScope.usuarioSesion.rol.codigo=='super'}">
		<p>
			<label for="vista">Ocultar</label>
			<input type="checkbox" name="vista" id="vista"  <c:if test="${e.vista=='true'}">checked="checked"</c:if>  />
		</p>
		</c:if>
		<c:if test="${usuarioSesion.rol.codigo!='atencion'}">
		<input type="submit" value="Guardar" style="float:right;"/>
		<c:if test="${not empty e}">
		<input type="button" id="nuevaArea" style="float:right;" value="Crear Área" />
		</c:if>
		</c:if>
	</fieldset>
</form>