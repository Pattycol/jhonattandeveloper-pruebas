<%@ taglib prefix="s" uri="/struts-tags"%>
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
				$("#tablaEstadoCuenta").paginar({
					 items : 20,
					 div_paginacion : '#paginador'
				});
			});
</script>
<br>
	  <div class="tablaPlomo">
	    <div align="center">
						  						  <div class="detalleTitulo">
						  						  <s:form action="/jsp/getListEstadoCuenta">
						  	Estado de Cuenta
						  </div>
						  <table width="100%" border="0">
						    <tr>
			 	 <td width="100%" colspan="6" align="center"><s:if
							test="hasActionErrors()">
							<div class=" msg-error">
							
								<s:actionerror cssClass="centrar"/>
							</div>
						</s:if> <s:if test="hasActionMessages()">
							<div class=" msg-ok">
							
								<s:actionmessage cssClass="centrar"/>
							
							</div>
						</s:if></td>
			</tr>
						  </table>
						  <br>
			                
                </div>
                 </s:form>
		<table width="100%"  align="left" cellpadding="4" cellspacing="0" class="tablaPloma"  >

			<tbody>
			
			<tr>
			 	 <td  width="11%"></td>
				 <td  width="18%" height="2px"></td>
				 <td  ></td>
				 <td  ></td>
				 <td  ></td> <td  ></td>
			</tr>
			  <tr>
				<td colspan="7">
				<table class="tablaPlomo" width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
				<tbody class="fondoPlomo" style="background-color:#fff;">
			<tr class="servicos-texto3">
			  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
				

				
				
					<td style="padding-left: 0px"  class="textoDentroPanel">Deuda a la Fecha S/.:</td>
					<td  style="padding-left: 5px" ><s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.valDeudaActualSol" ></s:textfield></td>
					<td  style="padding-left: 5px"><span class="textoDentroPanel">Fec. Ult. Fact. :</span></td>
					<td style="padding-left: 5px" ><s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.fecEmision" ></s:textfield></td>
			        <td  style="padding-left: 5px" >&nbsp;</td>
			</tr>
			<tr class="servicos-texto3">
			 <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
			 <td style="padding-left: 0px" ><span class="textoDentroPanel">Deuda a la Fecha $.:</span></td>
			 <td style="padding-left: 5px"><s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.valDeudaActualDol" ></s:textfield></td>
			 <td style="padding-left: 5px"><span class="textoDentroPanel">Fec. Ult. Pago :</span></td>
			 <td style="padding-left: 5px"><s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.fecVencimiento" ></s:textfield></td>
			 <td height="8px">&nbsp;</td>
			</tr>
				
				<tr>
				  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td style="padding-left: 0px" ><span class="textoDentroPanel">Deuda Vencida S/.:</span></td>
					<td style="padding-left: 5px" ><span>
					  <s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.valDeudaVencidaSol" ></s:textfield>
					</span></td>
					<td style="padding-left: 5px" ><span class="textoDentroPanel">Deuda Vencida $.:</span></td>
					<td style="padding-left: 5px" ><span>
					  <s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.valDeudaVencidaDol" ></s:textfield>
					</span></td>
					<td height="8px">&nbsp;</td>
				</tr>
				
				<tr class="servicos-texto3">
				    <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td style="padding-left: 0px" class="textoDentroPanel" >Monto en Disputa :</td>
					<td style="padding-left: 5px" ><s:textfield size="10" disabled="true" cssStyle="background:#fff; border:none;color: #666666;font-family: Verdana,Arial,Helvetica,sans-serif;font-size: 10px;" name="estadoCuenta.valMontoDisputa" ></s:textfield></td>
					<s:url id="fileDownload" namespace="/jsp" action="downloadEstadoCuenta" ></s:url>
				    <td style="padding-left: 5px"><s:a href="%{fileDownload}" cssClass="btWideLink">&nbsp;&nbsp;&nbsp;&nbsp;Exportar a Excel</s:a></td>
					<td height="8px">&nbsp;</td>
					<td height="8px">&nbsp;</td>
				</tr>
				 <tr><td colspan="6" height="5"></td></tr>
				</tbody>
				</table>
				</td>
				</tr>
		</tbody></table>
		
		<div style="clear:both;"></div>
		<s:if test="estadoCuenta.detalleEstadoCuenta.size() > 0" > 
		
				<table  width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="tablaPlomo">
				<tbody >
				<tr>
				<td>
	<table id="tablaEstadoCuenta" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th><font>Detalle</font></th>
			<th><font >Documento</font></th>
			<th><font >Descripci&oacute;n Pago</font></th>
			<!-- <th><font color="red">Fec. Doc.</font> </th>
			<th><font >Fec. Can. </font></th>
			<th><font >Monto </font></th> -->
		    <th><font >Fec. Registro</font></th>
		    <th><font >Fec. Emisi&oacute;n</font></th>
		    <th><font >Fec. Vencimiento</font></th>
		    <th><font >Cargo</font></th>
		    <th><font >Abono</font></th>
		    <th><font >Saldo</font></th>
		</tr>
		</thead>
		<tbody>
		  <s:iterator value="estadoCuenta.detalleEstadoCuenta" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="left"><s:property value="numFactura"/></td>
			<td align="left"><s:property value="codDocumento"/></td>
			<td align="left"><s:property value="desPago"/></td>
			<!--<td><s:property value="fecDocumento"/></td>
			<td><s:property value="fecCancelada"/></td>
			<td><s:property value="valDocumento"/></td> -->
		    <td align="center"><s:property value="fecRegistro"/></td>
		    <td align="center"><s:property value="fecEmision"/></td>
		    <td align="center"><s:property value="fecVencimiento"/></td>
		    <td align="center"><s:checkbox name="flgCargoCuenta" disabled="true"/></td><!-- onClick="this.checked= !this.checked" -->
		    <td><s:property value="valAbono"/></td>
		    <td><s:property value="valSaldo"/></td>
		  </tr>
		 </s:iterator>
	  </tbody>
	  </table>
				</td>
				</tr>
		</tbody></table>
		
		<div id="paginador" class='page_navigation'></div>
</s:if>
	
	
	</div>