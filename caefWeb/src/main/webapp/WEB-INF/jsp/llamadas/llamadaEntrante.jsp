<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="pe.com.claro.caef.web.auth.Usuario"%>
 <%
  
  	Usuario objUser =(Usuario)session.getAttribute("usuario");
  
  %>
<script type="text/javascript" src="../js/jquery.paginarTabla-1.0.js"></script>
<script type="text/javascript">
$(function() {
	 $(document).keydown(function(e){
	  var code = (e.keyCode ? e.keyCode : e.which);
	  if(code == 116) {
	   e.preventDefault();
	   location="<%=request.getContextPath()%>/j_spring_security_logout";
	  }
	  
	 });
	});


	$(document).ready(function() {

		$("#tablaLlamadasEntrantes").paginar({
			items : 10,
			div_paginacion : '#paginador'
		});

	});
</script>

<script type="text/javascript">
	function cambiarPorNumeroCelular() {
		//document.frmLlamadasNoFacturadas.action = 'obtenerFechasLlamadasNoFacturadas';
		document.frmLlamadasEntrantes.flag.value='0';
		document.frmLlamadasEntrantes.submit();

	}
	function seteaHidden(){
		document.frmLlamadasEntrantes.flag.value='1';
	}	
</script>
<br>
<div align="center" class="tablaPlomo">
	<div align="left">
		<div class="detalleTitulo">Detalle de Llamadas Entrantes</div>
		<table class="textofuerapanel">

			<tr>
				<td>&nbsp;&nbsp;</td>
				<td align="left"><br> <b> <span class="strong"><s:property
								value="mensajesSeguridad.mensaje1" /></span><br>
				</b></td>
			</tr>
		</table>
	</div>
	<s:form id="frmLlamadasEntrantes" name="frmLlamadasEntrantes"
		action="/jsp/getListLlamadaEntrante">
		<table width="100%" align="left" cellpadding="4" cellspacing="0"
			class="tablaPloma">

			<tbody>

				<tr>
					<td width="100%" colspan="4" align="center"><s:if
							test="hasActionErrors()">
							<div class="errors">
								<s:actionerror />
							</div>
						</s:if></td>
				</tr>
				<tr>
					<!-- -->
					<td>
						<table class="tablaPlomo" width="98%" border="0" align="center"
							cellpadding="0" cellspacing="0">
							<tbody class="fondoPlomo">
								<tr>
									<td colspan="6" height="5"></td>
								</tr>
								<tr class="servicos-texto3">
									<td style="padding-left: 5px" class="textoDentroPanel"
										width="17%">N&uacute;mero Telef&oacute;nico:</td>
									<td style="padding-left: 5px"><s:select
											list="lstConsultaNumeroTelefonico" listKey="numero"
											listValue="numero"
											value="consultarListaLlamadasEntrantesFilter.numDestino"
											name="consultarListaLlamadasEntrantesFilter.numDestino"
											cssClass="dropdowns" onchange="cambiarPorNumeroCelular()"
											headerKey="Seleccione" headerValue="--Seleccione--" />
											<s:hidden id="flag" name="flag"/>
									</td>
									<td style="padding-left: 5px" width="10%">&nbsp;</td>
									<td style="padding-left: 5px">&nbsp;</td>
									<td style="padding-left: 5px">&nbsp;</td>
									<td style="padding-left: 5px">&nbsp;</td>
								</tr>
								<tr class="servicos-texto3">
									<td style="padding-left: 5px" class="textoDentroPanel"
										width="17%">Fecha Inicio:</td>
									<td style="padding-left: 3px"><sj:datepicker
											cssClass="campotextoFecha"
											name="consultarListaLlamadasEntrantesFilter.fecInicio"
											label="Fecha Inicio" displayFormat="dd/mm/yy" firstDay="1"
											minDate="-3m" maxDate="+0d"
											readonly="true" value="%{dtFechaIni}" /><!--minDate="%{dtFechaInicioRango}" maxDate="%{dtFechaFinRango}" --></td>
									<td style="padding-left: 5px" class="textoDentroPanel"
										width="10%">Fecha Fin:</td>
									<td style="padding-left: 5px"><sj:datepicker
											cssClass="campotextoFecha"
											name="consultarListaLlamadasEntrantesFilter.fecFin"
											label="Fecha Fin" displayFormat="dd/mm/yy" firstDay="1"
											minDate="-3m" maxDate="+0d"
											readonly="true" value="%{dtFechaFin}" /><!--minDate="%{dtFechaInicioRango}" maxDate="%{dtFechaFinRango}" --></td>
									<td style="padding-left: 5px">&nbsp;</td>
									<td style="padding-left: 5px">&nbsp;</td>
								</tr>
								<tr>
									<td height="8px" colspan="6">
									<div style="margin:0 auto; width:35%; text-align:center;">
									<div style="float:left;">
									<s:url id="fileDownload" namespace="/jsp"
										action="downloadLlamadaEntrante">
										<s:param
											name="consultarListaLlamadasEntrantesFilter.fecInicio">
											<s:property
												value="consultarListaLlamadasEntrantesFilter.fecInicio" />
										</s:param>
										<s:param name="consultarListaLlamadasEntrantesFilter.fecFin">
											<s:property
												value="consultarListaLlamadasEntrantesFilter.fecFin" />
										</s:param>
									</s:url>
									<input class="btWide" name="buttonConsultar" value="Consultar" onclick="seteaHidden()" type="submit">
									</div>
									<!-- <td height="8px" align="left"><s:a href="%{fileDownload}" disabled="%{bFLagBtnEnviarCorreo}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td height="8px">&nbsp;</td> -->
									<div style="float:left;">
									<s:if test="flagBoton == true">
										<s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a>
									</s:if>
									<s:else>
										<label
											class="btWideLinkEnabled">&nbsp;&nbsp;&nbsp;&nbsp;Exportar
												a Excel</label> 
									</s:else>
									</div>
									<div style="clear:both;"></div>
									</div>
									</td>
								</tr>
								<tr>
									<td colspan="6" height="5"></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<br>
		<table align="center" width="100%" class="tablaPloma">
			<tbody>
				<tr class="servicos-texto3" align="center">
					<s:if test="flagBoton == true">
						<td colspan="6"><br> <s:url id="enviarMail"
								namespace="/jsp" action="enviarCorreoLlamadaEntrante"></s:url>

							<!--<sj:a id="enviarMailLlamadaEntrante" href="%{enviarMail}"
								effect="highlight" indicator="indicator" button="true"
								targets="result" disabled="%{bFLagBtnEnviarCorreo}">
												  	Enviar Consulta de Llamadas Entrantes
												</sj:a>-->
							<s:a href="%{enviarMail}" cssClass="enlacerojo Estilo5 Estilo6 Estilo8">Enviar Detalle de Llamadas Entrantes a : <%=objUser.getCorreoCliente() %></s:a>					
							<!--<img id="indicator" src="../img/load.gif" alt="Enviando..."
							style="display: none" /> -->
							<div id="result"></div> <!-- 	
									<s:a href="%{fileDownload}">Enviar Detalle de Llamadas</s:a>
									<a href="javascript:enviar();"> <u>Enviar Detalle de Llamadas</u> </a> a:  -->
							<br> <br></td>
					</s:if>
				</tr>
			</tbody>
		</table>

		<br>
	</s:form>

	<s:if test="lstLlamadaEntrante.size() > 0">
		<table id="tablaLlamadasEntrantes" border="0" cellpadding="2"
			class="cuerpo" cellspacing="2">
			<thead>
				<tr>
					<th>L&iacute;nea Origen</th>
					<th>L&iacute;nea Destino</th>
					<th>Fecha Hora Inicio</th>
					<th>Fecha Hora Fin</th>
					<th>Duraci&oacute;n (est&aacute; en segundos)</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="lstLlamadaEntrante" status="rowstatus">
					<s:if test="#rowstatus.odd == true">
						<tr class="odd">
					</s:if>
					<s:else>
						<tr>
					</s:else>
					<td align="center"><s:property value="numOrigen" /></td>
					<td align="center"><s:property value="numDestino" /></td>
					<td align="center"><s:property value="fecInicio" /></td>
					<td align="center"><s:property value="fecFin" /></td>
					<td align="center"><s:property value="valDuracion" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div id="paginador" class='page_navigation'></div>
	</s:if>
</div>