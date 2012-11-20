<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.chimera.org/chimera.tld" prefix="p" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p:html titulo="LBS - Consultas por Empresa" estilo="reportesEmpresa" javascript="reportes/empresaFecha">
	<div id="tabs">
		<ul>
			<li><a href="#usuario">Consultas por Fecha</a></li>
		</ul>
		<div id="fecha">
			<div class="parametros ui-widget ui-widget-content">
				<p>
					<label for="fechaInicio"> Fecha Inicio:</label>
					<input id="fechaInicio" />
					<input type="hidden" id="fi" />
					<label for="fechaFin"> Fecha Fin:</label>
					<input id="fechaFin" />
					<input type="hidden" id="ff" />
				</p>
				<p>
					<strong class="error" style="float:left;"></strong>
					<button id="generar" style="float:right;">Generar Reporte</button>
				</p>
			</div>
			<div class="resultado"></div>
		</div>
	</div>
</p:html>