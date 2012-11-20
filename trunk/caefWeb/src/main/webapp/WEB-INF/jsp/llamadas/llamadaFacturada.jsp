<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %> 
<%@page import="pe.com.claro.caef.web.auth.Usuario"%>
 <%
  
  	Usuario objUser =(Usuario)session.getAttribute("usuario");
  
  %>
<link rel="stylesheet" href="../css/login.css" />
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

				$("#tablaLlamadasFacturadas").paginar({
					 items : 10,
					 div_paginacion : '#paginador'
				});
			});
				
			
			 
			function execute(abc){
				alert("entro");
				
				var combo = abc.text;
				var combo1 = abc.value;
				
				alert(combo + "  "+combo1);
				
			}
</script>

<sx:head parseContent="true"/>
<br>
<div  class="tablaPlomo">
	  
        				  <div align="left">
						  
						  
						  <div class="detalleTitulo">
						
						  	Detalle de Llamadas Facturadas
						  </div>
						 <table class="textofuerapanel">
		  <tr align="left"><td>
						  
						  						        <b> <span><s:property value="mensajesSeguridad.mensaje1"/></span><br>
					                </b>
					                </td></tr>
		  </table>
                </div>
        <s:url id="detailUrl" action="completeCombo"/>        
                
        <s:form action="/jsp/executeLlamadasFacturadas" id="selectForm">
		<table width="100%"  align="left" cellpadding="4" cellspacing="0" class="tablaPloma"  >

			<tbody>
			
			
			
			<tr>
			 	
			 	 <td  width="100%" colspan="5" align="center">
			 	 
			 	 <s:if test="hasActionErrors()">
						   <div class="errors" style="color:#000;font-weight:normal;">
						      <s:actionerror  cssStyle="color:#000;font-weight:normal;"/>
						   </div>
				</s:if>
			 	
			 	 </td>
			</tr>
			<tr>
				<td >
				<table class="tablaPlomo" width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tbody class="fondoPlomo">
			<tr><td colspan="4" height="5"></td></tr>
			<tr class="servicos-texto3">
			  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td style="padding-left: 8px" class="textoDentroPanel">N&uacute;mero Claro:</td>
					<td width="36%" style="padding-left: 5px">
					<!--<s:select
						cssStyle="width:125px;"
						list="lstConsultaNumeroTelefonico" 
						listKey = "codigoInstanciaServicio"
						listValue = "numero"
						name="consultarListaLlamadasFacturadasFilter.numOrigen" 
						value="consultarListaLlamadasFacturadasFilter.numOrigen"/> -->
						<sx:autocompleter name="consultarListaLlamadasFacturadasFilter.numOrigen" list="lstConsultaNumeroTelefonico" 
						listKey="codigoInstanciaServicio" listValue="numero" cssStyle="font-weight:normal;" 
						notifyTopics="changed" forceValidOption="true" id="sel" headerKey="Seleccione"
						headerValue="--Seleccione--" cssClass="dropdowns" iconPath="/img/combo_box_arrow.png"  />
					</td>
			        <td width="13%" style="padding-left: 5px">&nbsp;</td>
			</tr>

				
			<tr><td colspan="4" height="6"></td></tr>
				
				<tr class="servicos-texto3">
				  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<input id="fechaEmision" name="fechaEmision" type="hidden" value="">
					<td style="padding-left: 8px" class="textoDentroPanel">Fecha Emisi&oacute;n:</td>
					<td style="padding-left: 5px">

						<sx:autocompleter href="%{detailUrl}" formId="selectForm" listenTopics="changed" id="cboFechas" 
						name="consultarListaLlamadasFacturadasFilter.fecInicio" cssClass="dropdowns" iconPath="/img/combo_box_arrow.png" />
						
										</td>
				    <td style="padding-left: 5px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px" align="right"><input class="btWide" name="buttonConsultar" value="Consultar" type="submit"></td>
					<s:url id="fileDownload" namespace="/jsp" action="downloadLlamadasFacturadas" ></s:url>
					
					<!-- <td height="8px" align="left"><s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td>&nbsp;</td>  -->
					
					<s:if test="flagBoton == true" >
				    <td height="8px" align="left"><s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td>&nbsp;</td>
   					</s:if>
    				<s:else>
   					<td height="8px" align="left"><label class="btWideLinkEnabled">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</label> </></td><td height="8px">&nbsp;</td>
					</s:else>
					
				</tr>
				<tr><td colspan="4" height="5"></td></tr>
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
										<s:url id="enviarMail" namespace="/jsp" action="enviarCorreoLlamadasFacturadas" ></s:url>
										
									<!--  		<sj:a id="enviarMailLlamadasFacturadas" 
													href="%{enviarMail}"   effect="highlight" 
													indicator="indicator" 
													button="true"   targets="result" disabled="%{bFLagBtnEnviarCorre}"
												>
												  	Enviar Detalle de Llamadas Facturadas
												</sj:a>-->
												
										
					
				  <s:a href="%{enviarMail}" cssClass="enlacerojo Estilo5 Estilo6 Estilo8">Enviar Detalle de Llamadas Facturadas a : <%=objUser.getCorreoCliente() %></s:a>
   					
									<!-- <img id="indicator" src="../img/load.gif" alt="Enviando..." style="display:none"/> -->	 
										<div id="result"></div>   
									<!-- 	
									<s:a href="%{fileDownload}">Enviar Detalle de Llamadas</s:a>
									<a href="javascript:enviar();"> <u>Enviar Detalle de Llamadas</u> </a> a:  -->
										<br>
										<br>
									
									
								</td>
								</s:if>
							</tr>
						</tbody></table>
		
		<br>
		
	</s:form>

	<s:if test="lstLlamadaFacturada.size() > 0" >
	<table width="98%" align="center">
	<tbody>
	<tr>
	<td colspan="3" class="tablaPlomo">
	<table id="tablaLlamadasFacturadas" border="0" cellpadding="2" class="cuerpo" cellspacing="2" >
		<thead>
		<tr>
			<th><font color=red>L&iacute;nea Origen </font></th>
			<th><font color=red>L&iacute;nea Destino </font></th>
			<th><font color=red>Fecha Hora Inicio </font></th>
			<th><font color=red>Fecha Hora Fin </font></th>
			<th><font color=red>Duraci&oacute;n </font></th>
			<!-- <th>Tarifa</th>  -->
			<!-- <th>Tipo de Servicio</th>  -->
			<th><font color=red>Recibo</font></th>
			<th><font color=red>Monto</font></th>
		  </tr>
		</thead>
		<tbody>
		  <s:iterator value="lstLlamadaFacturada" status="rowstatus">
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
			<td align="center"><s:property value="valDuracion"/></td>
			<!-- <td align="center"><s:property value="valTarifa"/></td> -->
			<!-- <td align="center"><s:property value="tipServicio"/></td> -->
			<td align="center"><s:property value="numRecibo"/></td>
			<td align="center"><s:property value="valCosto"/></td>
		   </tr>
		  </s:iterator>	
	  </tbody>
		</table>
		</td>
	  </tr>
	  </tbody>
		</table>
		<div id="paginador" class='page_navigation'></div> 
	</s:if>
	</div>
