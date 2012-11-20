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
	
			$(document).ready(function(){
				setTimeout(function(){
				$("#tablaLlamadasNoFacturadas").paginar({
					 items : 20,
					 div_paginacion : '#paginador'
				});
				},800);
			});
</script>

<script type="text/javascript">
	function enviar() {
		
		enviarDetalleLlamadaNoFacturado('Seleccione Fecha de Emisión o Cuenta.','enviar');
	}
	function cambiarPorNumeroCelular(){
		//document.frmLlamadasNoFacturadas.action = 'obtenerFechasLlamadasNoFacturadas';
		document.frmLlamadasNoFacturadas.flag.value='0';
		document.frmLlamadasNoFacturadas.submit();
		
	}
	function seteaHidden(){
		document.frmLlamadasNoFacturadas.flag.value='1';
	}
	
</script>


<br>
	  	<div class="tablaPlomo">
        				  <div align="center">
						  						  <div class="detalleTitulo">
						   
						  	Detalle de Llamadas No Facturadas
						  </div>
						  <table class="textofuerapanel">
		  <tr align="left"><td>
						  <br>
						        <b> <span class="strong">&nbsp;&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></span><br>
			                </b>
			                </td></tr>
		  </table>
                </div>
        <s:form id="frmLlamadasNoFacturadas" name="frmLlamadasNoFacturadas" action="/jsp/getListLlamadasNoFacturadas">
		<table width="100%"  align="left" cellpadding="0" cellspacing="0" class="tablaPloma"  >
			<tbody>
			
			<tr>
			 	  <td  width="100%" colspan="6" align="center">
			 	 
			 	 <s:if test="hasActionErrors()">
						   <div class="errors" style="color:#000;font-weight:normal;">
						      <s:actionerror cssStyle="color:#000;font-weight:normal;"/>
						   </div>
				</s:if>
			 	 
			 	 </td>
			</tr>
			<tr>
				<td >
				<table class="tablaPlomo" style="background-color:#EDEDED;" width="98%" border="0" align="center" cellpadding="8" cellspacing="8" >
				<tbody class="fondoPlomo">
			<tr class="servicos-texto3">
			  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td width="18%" style="padding-left: 5px" class="textoDentroPanel">N&uacute;mero Claro:</td>
					<td width="22%" style="padding-left: 5px">
					<s:select
						name="consultarListaLlamadasNoFacturadasFilter.numOrigen" 
						list="lstConsultaNumeroTelefonico" 
						listKey = "numero"
						listValue = "numero"
						value="consultarListaLlamadasNoFacturadasFilter.numOrigen" 
						cssClass="dropdowns"
						
						onchange="cambiarPorNumeroCelular()"
						headerKey="Seleccione"
						headerValue="--Seleccione--"
						/>
						<s:hidden id="flag" name="flag"/>
					</td>
					<td width="10%" style="padding-left: 5px">
					&nbsp;
					</td>
					<td width="20%" style="padding-left: 5px">&nbsp;</td>
			        <td width="11%" style="padding-left: 5px">&nbsp;</td>
			        <td width="11%" style="padding-left: 5px">&nbsp;</td>
			</tr>

				
			
				
				<tr class="servicos-texto3">
					<td style="padding-left: 8px" class="textoDentroPanel" >&nbsp;</td>
					<td style="padding-left: 5px" class="textoDentroPanel" width="18%">Fecha Inicio:</td>
				  	<td style="padding-left: 3px" width="35%">
				  
						<sj:datepicker cssClass="campotextoFecha" name="consultarListaLlamadasNoFacturadasFilter.fecInicio" label="Fecha Inicio" 
						displayFormat="dd/mm/yy" 
						firstDay="1" 
						minDate="%{dtFechaInicioRango}" maxDate="+0d" readonly="true" 
					/><!-- maxDate="%{dtFechaFinRango}" -->
					</td>
					<td style="padding-left: 5px" align="center" width="10%" class="textoDentroPanel">Fecha Fin:</td>
					<td style="padding-left: 5px" colspan="2">
						<sj:datepicker cssClass="campotextoFecha" label="Fecha Fin"
						 name="consultarListaLlamadasNoFacturadasFilter.fecFin" 
						 label="Fecha Fin" displayFormat="dd/mm/yy" 
						 firstDay="1" minDate="%{dtFechaInicioRango}" maxDate="+0d" readonly="true"
						 />
					</td><!--maxDate=" "%{dtFechaFinRango}" -->
				    <s:url id="fileDownload" namespace="/jsp" action="downloadLlamadasNoFacturadas" >
						<s:param name="consultarListaLlamadasNoFacturadasFilter.fecInicio"><s:property value="consultarListaLlamadasNoFacturadasFilter.fecInicio" /></s:param>
						<s:param name="consultarListaLlamadasNoFacturadasFilter.fecFin"><s:property value="consultarListaLlamadasNoFacturadasFilter.fecFin" /></s:param>
					</s:url>
					<td width="11%" style="padding-left: 5px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					
					<td align="right"><input class="btWide" name="buttonConsultar" value="Consultar" type="submit" onclick="seteaHidden()"></td>
					
					<!-- <td height="8px" align="left"><s:a href="%{fileDownload}"cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td height="8px">&nbsp;</td> -->
					
					<s:if test="flagBoton == true" >
				    <td height="8px" align="left"><s:a href="%{fileDownload}"cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td height="8px">&nbsp;</td>
   					</s:if>
    				<s:else>
   					<td height="8px" align="left"><label class="btWideLinkEnabled">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</label> </></td><td height="8px">&nbsp;</td>
					</s:else> 
					
					<td height="8px">&nbsp;</td>
					<td height="8px">&nbsp;</td>
				</tr>
				</tbody>
				</table>
				</td>
				</tr>
		</tbody></table>
		<br>
		<table align="center" width="100%"  class="tablaPloma">
							<tbody><tr class="servicos-texto3" align="center">
								<s:if test="flagBoton == true" >
								<td colspan="6">
									
										<br>
										<s:url id="enviarMail" namespace="/jsp" action="enviarCorreoLlamadasNoFacturadas" ></s:url>
										
										<!--<sj:a id="enviarMailLlamadasNoFacturadas" 
													href="%{enviarMail}"   effect="highlight" 
													indicator="indicator" 
													button="true"   targets="result" disabled="%{bFLagBtnEnviarCorreo}"
												>
												  	Enviar Consulta de Llamadas No Facturadas
												</sj:a>-->
												
												<s:a href="%{enviarMail}" cssClass="enlacerojo Estilo5 Estilo6 Estilo8">Enviar Detalle de Llamadas No Facturadas a : <%=objUser.getCorreoCliente() %></s:a>
										<!--<img id="indicator" src="../img/load.gif" alt="Enviando..." style="display:none"/> --> 
										<div id="result"></div>   
										<br>
										<br>
									
									
								</td>
								</s:if>
							</tr>
						</tbody></table>
		
		<br>
		</s:form>	
		
		<s:if test="lstLlamadaNoFacturada.size() > 0" > 
	<table id="tablaLlamadasNoFacturadas" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th>L&iacute;nea Origen </th>
			<th>L&iacute;nea Destino </th>
			<th>Fecha y Hora Inicio </th>
			<th>Fecha y Hora Fin </th>
			<th>Duraci&oacute;n </th>
			<!-- <th>Tipo Servicio</th>  -->
		  </tr>
		</thead>
		<tbody>
		  <s:iterator value="lstLlamadaNoFacturada" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="center"><s:property value="numOrigen"/></td>
			<td align="center"><s:property value="numDestino"/></td>
			<td align="center"><s:property value="horaInicio"/></td>
			<td align="center"><s:property value="horaFin"/></td>
			<td align="left"><s:property value="valDuracion"/></td>
			<!-- <td align="center"><s:property value="tipServicio"/></td>  -->
		   </tr>
		  </s:iterator>	
	  </tbody>
		</table>
		<div id="paginador" class='page_navigation'></div> 
</s:if>

	</div>