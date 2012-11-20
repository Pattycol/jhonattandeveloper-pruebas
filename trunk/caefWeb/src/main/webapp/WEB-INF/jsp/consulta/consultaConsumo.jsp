<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
 <%@page import="pe.com.claro.caef.web.auth.Usuario"%>
 <%
  
  	Usuario objUser =(Usuario)session.getAttribute("usuario");
  
  %>
<link rel="stylesheet" href="../css/login.css" />
<head>
<title>Consulta de consumos</title>
</head>
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
				/*setTimeout(function(){*/
				$("#tablaConsumo").paginar({
					 items : 20,
					 div_paginacion : '#paginador'
				});
				/*},800);*/
			});
</script>
<br>
  <div class="tablaPlomo">
	    <div align="left">
		<div class="detalleTitulo">
		
		<s:form action="/jsp/getListConsumo">
								  	Estado de Consumo
		  </div>
		  <table class="textofuerapanel" cellpadding="0" cellspacing="0" align="left">
		  <tr ><td style="padding-left: 8px">
		  <br>
		  <b>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/><br>
        </b>  </td></tr>
		  </table>
	      </div>
        				  </td>
		<table width="100%"  align="left" cellpadding="4" cellspacing="0" class="tablaPloma"  >

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
				<table class="tablaPlomo" style="background-color:#EDEDED;" width="98%" border="0" align="center" cellpadding="8" cellspacing="8" >
				<tbody class="fondoPlomo">
				<tr class="servicos-texto3">
				  <!-- <td style="padding-left: 8px" width="20%" class="textoDentroPanel">&nbsp;</td> -->
					
					<td style="padding-left: 0px" colspan="2" width="8%" ><span class="textoDentroPanel" style="padding-left: 8px">N&uacute;mero Telef&oacute;nico:</span></td>
					
					<td style="padding-left: 5px" width="5%">
					<s:select
						name="lstNumeroTelefonico" 
						list="lstConsultaNumeroTelefonico" 
						listKey = "numero"
						listValue = "numero"
						headerKey="Seleccione"
						headerValue="-- Seleccione --"
						value="consultarConsumoClienteFilter.numeroTelefonico"
						name="consultarConsumoClienteFilter.numeroTelefonico" 
						cssClass="dropdowns"/>
						<!-- cssStyle="width:156px; background:#ffffff; font-size:10px;" -->
					</td>
					<td style="padding-left: 8px" width="10%" class="textoDentroPanel">&nbsp;</td>
					<td style="padding-left: 8px" width="10%" class="textoDentroPanel">&nbsp;</td>
				</tr>
				<tr>
				  <!-- <td height="8px">&nbsp;</td> -->
				  
					<td align="center" colspan="5">
					<div style="margin:0 auto; width:35%; text-align:center;">
					<div style="float:left;">
					  <input name="buttonConsultar" value="Consultar" type="submit" class="btWide">
					</div>
					<s:url id="fileDownload" namespace="/jsp" action="downloadConsumo" >
					</s:url>
					
					<!-- <td height="8px" align="left"><s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> </td><td height="8px">&nbsp;</td> -->
					<div style="float:left;">
					<s:if test="flagBoton == true" >
				    <!-- <td height="8px" align="left"> -->
				    <s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a> <!--</td><td height="8px">&nbsp;</td>  -->
   					</s:if>
    				<s:else>
   					<!--<td height="8px" align="left">--><label class="btWideLinkEnabled">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</label> </><!-- </td><td height="8px">&nbsp;</td> -->
					</s:else> 
					</div>
					<div style="clear:both;"></div>
					</div>
					</td>
					
					<!-- <td height="8px">&nbsp;</td> -->
				</tr>
				<!-- <tr><td colspan="5" height="5"></td></tr> -->
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
										<s:url id="enviarMail" namespace="/jsp" action="enviarCorreoConsumo" ></s:url>
										
										<!-- <sj:a id="enviarMailConsumo" 
													href="%{enviarMail}"   effect="highlight" 
													indicator="indicator" 
													button="true"   targets="result" disabled="%{bFLagBtnEnviarCorreo}" 
												>
												  	Enviar Detalle de Consumo
												</sj:a> -->
												<s:a href="%{enviarMail}" cssClass="enlacerojo Estilo5 Estilo6 Estilo8">Enviar Consulta de Consumos a : <%=objUser.getCorreoCliente() %></s:a>
										<!-- <img id="indicator" src="../img/load.gif" alt="Enviando..." style="display:none"/>  --> 
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
		
	<s:if test="lstConsultaConsumo.size() > 0" >
	<table id="tablaConsumo" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th><font color=red>Tipo Servicio</font></th>
			<th><font color=red>Producto</font></th>
			<th><font color=red>Tipo de Llamada</font></th>
			<th><font color=red>Descripci&oacute;n</font></th>
		    <th><font color=red>Consumo</font></th>
		</tr>
		</thead>
		<tbody>
		 <s:iterator value="lstConsultaConsumo" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="left"><s:property value="desTipServicio"/></td>
			<td align="left"><s:property value="desProducto"/></td>
			<td align="center"><s:property value="codTipLlamada"/></td>
			<td align="left"><s:property value="desTipLlamada"/></td>
		    <td align="center"><s:property value="valConsumo"/></td>
		  </tr>
		 </s:iterator>	
	  </tbody>
		</table>
		<div id="paginador" class='page_navigation'></div> 
		</s:if>
	</div>