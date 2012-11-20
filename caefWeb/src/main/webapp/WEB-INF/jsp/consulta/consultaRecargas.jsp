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
	   location="<%=request.getContextPath()%>/j_spring_security_logout";
	  }
	 });
	});
			
			
			$(document).ready(function(){

					$("#tablaRecargas").paginar({
						 items : 20,
						 div_paginacion : '#paginador'
					});

			});
</script>
<br>
	  <div class="tablaPlomo">
        				  <div align="left">
						  <div class="detalleTitulo">
						  
						  
						  	Detalle de Recargas
						  </div>
						  <table class="textofuerapanel">
						  
		  				  <tr>
		  				  <td aling="left">
						  <br>
						        <b> <span class="strong">&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></span><br>
			                </b>
			                </td></tr>
		  </table>
        </div>
         <s:form action="/jsp/executeRecargas">
		<table width="100%"  align="left" cellpadding="0" cellspacing="0" class="tablaPloma"  >

			<tbody>
			
			<tr>
			 	 <td  width="100%" colspan="5" align="center">
			 	   
			 	 <s:if test="hasActionErrors()">
						   <div class="errors">
						      <s:actionerror/>
						   </div>
				</s:if>
			 	 
			 	 </td>
			</tr>

				<tr>
				<td >
				<table class="tablaPlomo" width="98%" style="background-color:#EDEDED;" border="0" align="center" cellpadding="8" cellspacing="8" >
				<tbody class="fondoPlomo">			
				<tr class="servicos-texto3">
				  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td style="padding-left: 5px" class="textoDentroPanel">Fecha Inicio:</td>
					<td>
					
					 <sj:datepicker cssClass="campotexto" name="consultarRecargaFilter.fecInicio" displayFormat="dd/mm/yy" label="Today" firstDay="1" maxDate="+0d" minDate="-3m" readonly="true"/>
					
					<!--maxDate="+3m"-->
					</td>
					<td style="padding-left: 5px"><span class="textoDentroPanel" style="padding-left: 8px">Fecha Fin:</span></td>
					<td style="padding-left: 5px">
					
					
					 <sj:datepicker cssClass="campotexto"  name="consultarRecargaFilter.fecFin" displayFormat="dd/mm/yy" label="Today" firstDay="1" maxDate="+0d" minDate="-3m" readonly="true"/>					
					</td>
				    <td style="padding-left: 5px">&nbsp;</td>
				    <td style="padding-left: 5px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<s:url id="fileDownload" namespace="/jsp" action="downloadRecargas" >
						<s:param name="consultarRecargaFilter.fecInicio"><s:property value="consultarRecargaFilter.fecInicio" /></s:param>
						<s:param name="consultarRecargaFilter.fecFin"><s:property value="consultarRecargaFilter.fecFin" /></s:param>
					</s:url>
					<td align="right"><input class="btWide" name="buttonConsultar" value="Consultar" type="submit"></td>
					
					<!-- <td height="8px" align="left"><s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td height="8px">&nbsp;</td> -->
					
					<s:if test="flagBoton == true" >
				    <td height="8px" align="left"><s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td height="8px">&nbsp;</td>
   					</s:if>
    				<s:else>
   					<td height="8px" align="left"><label class="btWideLinkEnabled">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</label> </></td><td height="8px">&nbsp;</td>
					</s:else> 
					
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
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
										<s:url id="enviarMail" namespace="/jsp" action="enviarCorreoRecargas" ></s:url>
										
										<!--<sj:a id="enviarMailRecargas" 
													href="%{enviarMail}"   effect="highlight" 
													indicator="indicator" 
							 						button="true"   targets="result" disabled="%{bFLagBtnEnviarCorre}"
												>
												  	Enviar Consulta de Recargas
												</sj:a>-->
										<s:a href="%{enviarMail}" cssClass="enlacerojo Estilo5 Estilo6 Estilo8">Enviar Consulta de Recargas a : <%=objUser.getCorreoCliente() %></s:a>
										<!--<s:a href="%{enviarMail}"> Enviar Consulta de Recargas </s:a>		
										<img id="indicator" src="../img/load.gif" alt="Enviando..." style="display:none"/>--> 
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
		
		<s:if test="lstConsultaRecarga.size() > 0" > 
	<table id="tablaRecargas" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th>Fecha de Pago </th>
			<th>Monto</th>
			<th>Fecha Inicio Recarga </th>
			<th>Fecha Fin Recarga </th>
			<th>Agente Recarga </th>
		  </tr>
		</thead>
		<tbody>
		 <s:iterator value="lstConsultaRecarga" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="center"><s:property value="fecRecarga"/></td>
			<td align="center"><s:property value="valorMonto"/></td>
			<td align="center"><s:property value="fecInicio"/></td>
			<td align="center"><s:property value="fecFin"/></td>
			<td align="left"><s:property value="agenteRecarga"/></td>
		   </tr>
		 </s:iterator>	
	  </tbody>
		</table>
		<div id="paginador" class='page_navigation'></div> 
		</s:if>
	</div>