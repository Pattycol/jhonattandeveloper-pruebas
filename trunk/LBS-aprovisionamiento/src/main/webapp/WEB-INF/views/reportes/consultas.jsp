<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="reportes">
	<ul>
		<li><a href="#consultante">Consultas realizadas por este número</a></li>
		<li><a href="#consultado">Consultas realizadas a este número</a></li>
	</ul>
	<div id="consultante">
		<c:choose>
		<c:when test="${reporteConsultante.consultasTotales<=0}">
		<h3>No se encontraron consultas para los parámetros ingresados</h3>
		<span>Intente modificar los parámetros</span>
		</c:when>
		<c:otherwise>
		<strong>Total de Consultas realizadas: ${reporteConsultante.consultasTotales}</strong>
		<c:if test="${not empty reporteConsultante.consultasSMS}">
		<div class="SMS">
			<span>Consultas SMS: ${reporteConsultante.totalSMS} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultante.porcentajeSMS}" />%)</span><br />
			<span>Consultas exitosas: ${reporteConsultante.exitoSMS} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultante.porcentajeExitoSMS}" />%)</span>
			<table class="data">
				<thead>
					<tr>
						<th colspan="5">Consultas SMS</th>
					</tr>
					<tr>
						<th>Fecha</th>
						<th>Número Consultante</th>
						<th>Número Consultado</th>
						<th>Celda</th>
						<th>Resultado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reporteConsultante.consultasSMS}" var="c">
					<tr <c:if test="${c.codigoError==0}">class="exito"</c:if>>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${c.fechaConsulta}" /></td>
						<td>${c.consultante.numero}</td>
						<td>${c.consultado.numero}</td>
						<td>${c.celda.identificador}</td>
						<td>${c.resultado}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${not empty reporteConsultante.consultasWEB}">
		<div class="WEB">
			<span>Consultas WEB: ${reporteConsultante.totalWEB} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultante.porcentajeWEB}" />%)</span><br />
			<span>Consultas exitosas: ${reporteConsultante.exitoWEB} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultante.porcentajeExitoWEB}" />%)</span>
			<table class="data">
				<thead>
					<tr>
						<th colspan="5">Consultas WEB</th>
					</tr>
					<tr>
						<th>Fecha</th>
						<th>Número Consultante</th>
						<th>Número Consultado</th>
						<th>Celda</th>
						<th>Resultado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reporteConsultante.consultasWEB}" var="c">
					<tr <c:if test="${c.codigoError==0}">class="exito"</c:if>>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${c.fechaConsulta}" /></td>
						<td>${c.consultante.numero}</td>
						<td>${c.consultado.numero}</td>
						<td>${c.celda.identificador}</td>
						<td>${c.resultado}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		</c:otherwise>
		</c:choose>
	</div>
	<div id="consultado">
		<c:choose>
		<c:when test="${reporteConsultado.consultasTotales<=0}">
		<h3>No se encontraron consultas para los parámetros ingresados</h3>
		<span>Intente modificar los parámetros</span>
		</c:when>
		<c:otherwise>
		<strong>Total de Consultas realizadas: ${reporteConsultado.consultasTotales}</strong>
		<c:if test="${not empty reporteConsultado.consultasSMS}">
		<div class="SMS">
			<span>Consultas SMS: ${reporteConsultado.totalSMS} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultado.porcentajeSMS}" />%)</span><br />
			<span>Consultas exitosas: ${reporteConsultado.exitoSMS} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultado.porcentajeExitoSMS}" />%)</span>
			<table class="data">
				<thead>
					<tr>
						<th colspan="5">Consultas SMS</th>
					</tr>
					<tr>
						<th>Fecha</th>
						<th>Número Consultante</th>
						<th>Número Consultado</th>
						<th>Celda</th>
						<th>Resultado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reporteConsultado.consultasSMS}" var="c">
					<tr <c:if test="${c.codigoError==0}">class="exito"</c:if>>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${c.fechaConsulta}" /></td>
						<td>${c.consultante.numero}</td>
						<td>${c.consultado.numero}</td>
						<td>${c.celda.identificador}</td>
						<td>${c.resultado}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<c:if test="${not empty reporteConsultado.consultasWEB}">
		<div class="WEB">
			<span>Consultas WEB: ${reporteConsultado.totalWEB} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultado.porcentajeWEB}" />%)</span><br />
			<span>Consultas exitosas: ${reporteConsultado.exitoWEB} (<fmt:formatNumber maxFractionDigits="2" value="${reporteConsultado.porcentajeExitoWEB}" />%)</span>
			<table class="data">
				<thead>
					<tr>
						<th colspan="5">Consultas WEB</th>
					</tr>
					<tr>
						<th>Fecha</th>
						<th>Número Consultante</th>
						<th>Número Consultado</th>
						<th>Celda</th>
						<th>Resultado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${reporteConsultado.consultasWEB}" var="c">
					<tr <c:if test="${c.codigoError==0}">class="exito"</c:if>>
						<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${c.fechaConsulta}" /></td>
						<td>${c.consultante.numero}</td>
						<td>${c.consultado.numero}</td>
						<td>${c.celda.identificador}</td>
						<td>${c.resultado}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		</c:otherwise>
		</c:choose>
	</div>
</div>