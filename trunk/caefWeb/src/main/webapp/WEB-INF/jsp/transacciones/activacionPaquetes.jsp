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
				$("#tablaPaquetes").paginar({
					 items : 10,
					 div_paginacion : '#paginador'
				});
			});
			
			function visibleDiv(){
				var visible = document.getElementById("visible").value;	
				document.getElementById("contenido_a_mostrar").style.display = visible;
			}
			
			function changeCboNumFijo(){
				var cboNumFijo = document.getElementById("cboNumFijo").selectedIndex;
				
				if(cboNumFijo == 0){
					document.getElementById("txtNumFijoOpcional").disabled = false;
					document.getElementById("txtNumFijoOpcional").style.background = "#FFFFFF";
				}
				else{
					document.getElementById("txtNumFijoOpcional").disabled = true;
					document.getElementById("txtNumFijoOpcional").value = "";
					document.getElementById("txtNumFijoOpcional").style.background = "#E6E6E6";
				}
			}
			
			function changeCboNumMovil(){
				var cboNumMovil = document.getElementById("cboNumMovil").selectedIndex;
				
				if(cboNumMovil == 0){
					document.getElementById("txtNumMovilOpcional").disabled = false;
					document.getElementById("txtNumMovilOpcional").style.background = "#FFFFFF";
				}
				else{
					document.getElementById("txtNumMovilOpcional").disabled = true;
					document.getElementById("txtNumMovilOpcional").value = "";
					document.getElementById("txtNumMovilOpcional").style.background = "#E6E6E6";
				}
			}
			
			window.onload = function(){
				visibleDiv();
			}
			
			function asignaTarifa(combo){

			     var valor = combo.options[combo.selectedIndex].text;
			     var value = combo.options[combo.selectedIndex].value;
			     
			     document.getElementById("txtOcultoCboAdicionar").value = valor;
			     
			     var precio = parseFloat(value.substr(3,4));
			     var tarifa = parseFloat(document.getElementById("txtNuevaTarifa").value);
			     
			     //document.getElementById("txtNuevaTarifa").value = (precio + tarifa);
			     document.getElementById("txtNuevaTarifa").value = precio;
				
			}
			
			
			
			
</script>
<br>

<div class="tablaPlomo">


<div class="detalleTitulo" align="center">
Activaci&oacute;n de Paquetes 
</div>

<table width="100%">
<tr>
			 	 <td width="100%" colspan="6" align="center">
			 	 <s:if
							test="hasActionErrors()">
							<div class="errors" style="margin:auto 0;">
							
								<s:actionerror cssClass="centrar"/>
							</div>
						</s:if> <s:if test="hasActionMessages()">
							<div class="msg-ok" style="margin:auto 0;">
							
								<s:actionmessage cssClass="centrar"/>
							
							</div>
						</s:if></td>
			</tr>
</table>

<div align="left"><br>

<s:hidden id="visible" name="visible"/>

<div style="border:1px solid #ccc; width:98%; height:auto; display:block; margin:5px;">
<p style="display:block; padding-left:3px; background-color:#fff; margin-top:-10px; width:100px; height:20px; margin-left:7px;">Datos del Servicio</p>

<div>
<s:if test="lstConsultarInstanciaServicio.size() > 0">
<table id="tablaPaquetes" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
<thead>
<tr>
<th><font>Paquete</font></th>
<th><font>Direcci&oacute;n</font></th>
<th></th>
</tr>
</thead>
<tbody>
	<s:iterator value="lstConsultarInstanciaServicio" status="rowstatus">
	<s:form action="">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<!-- codigoInstanciaServicio -->
			<td align="left"><s:property value="numero"/></td>
			<td align="center"><s:property value="direccion"/>
			<s:hidden id="direccionOculto" name="direccion" cssStyle="overflow:hidden;font-size: 0px; line-height: 0;"/>
			<s:hidden id="sLetraInstancia" name="sLetraInstancia" cssStyle="overflow:hidden;font-size: 0px; line-height: 0;"/>
			<s:hidden id="codigoInstanciaServicio" name="codigoInstanciaServicio" cssStyle="overflow:hidden;font-size: 0px; line-height: 0;"/>
			</td>
		

			
			<td align="center"><s:submit action="executeActivaPaquete" type="image" src="../img/Solicitud.jpg"/></td>
		  </tr>
		  </s:form>
		  </s:iterator>	
</tbody>
</table>
<div id="paginador" class='page_navigation'></div> 
</s:if>
</div>

</div>





</b> </div>


<div id="contenido_a_mostrar" style="border:1px solid #ccc; width:98%; height:auto; display:block; margin:20px 5px 5px 5px;">
<p style="display:block; padding-left:3px; background-color:#fff; margin-top:-10px; width:250px; height:20px; margin-left:7px;">Solicitud de Activaci&oacute;n de Paquetes Adicionales</p>


<div>
	
	<s:if test="lstPlanesAdicionar.size() > 0" >
	<form name="form1" method="post" action="aceptarActivaPaquete">
		<table width="100%" border="0">
			
			<s:hidden id="hdCodServicio" name="activacionPaqueteActionFilter.sCodInstanciaServicio"></s:hidden>
			<tr>
			<td ><label for="lbl_planActual">Plan Actual :</label></td>
			<td colspan="3">
			<s:textfield name="activacionPaqueteActionFilter.sPlanActual" size="50" readonly="true" cssClass="campotexto" /></td>
			</tr>
			<tr>
			<td><label for="lbl_paqAdicionar">Paquete a Adicionar :</label></td>
			<td colspan="3">
			
			<s:select id="cboPlanAdicionar"
					list="lstPlanesAdicionar" 
					listKey = "valorDatoMaestro3"
					listValue = "valorDatoMaestro4"
					name="sPlanAdicionar"
					onchange="asignaTarifa(this);"
					cssClass="dropdowns"/>
			<s:hidden id="txtOcultoCboAdicionar" name="activacionPaqueteActionFilter.sPlanAdicionar"/>
			</td>
			</tr>
			<tr>
			<td><label for="txtDireccion">Direcci&oacute;n :</label></td>
			<td colspan="3"><s:textfield id="txtDireccion" size="80" readonly="true" name="activacionPaqueteActionFilter.sDireccion" cssClass="campotexto"/></td>
			</tr>
			<tr>
			<td><label for="lbl_telefonoFijo">Tel&eacute;fono Fijo : </label></td>
			<td >
			<s:select id="cboNumFijo"
					list="lstNumFijo" 
					listKey = "valorDatoMaestro1"
					listValue = "valorDatoMaestro2"
					headerKey="0"
					cssStyle="width:155px; "
					headerValue="-- Otro --"
					onchange="changeCboNumFijo();"
					name="activacionPaqueteActionFilter.sTelFijo"
					cssClass="dropdowns"/> 
					<s:textfield id="txtNumFijoOpcional" name="activacionPaqueteActionFilter.sOpcionalFijo" cssClass="campoTextoHabilitado" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxlength="9"/>
			</td>
			<td colspan="2"></td>
			</tr>
			<tr>
			<td><label for="lbl_telefonoMovil">Tel&eacute;fono M&oacute;vil : </label></td>
			<td>
			<s:select id="cboNumMovil"
					list="lstNumMovil" 
					listKey = "valorDatoMaestro1"
					listValue = "valorDatoMaestro2"
					headerKey="0"
					cssStyle="width:155px; "
					headerValue="-- Otro --"
					onchange="changeCboNumMovil();"
					name="activacionPaqueteActionFilter.sTelMovil"
					cssClass="dropdowns"/> 
					<s:textfield id="txtNumMovilOpcional" name="activacionPaqueteActionFilter.sOpcionalMovil" cssClass="campoTextoHabilitado" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxlength="9"/>
			</td>
			<td colspan="2"></td>
			</tr>
			<tr>
			<td><label for="lbl_nuevaTatifaMensuak">Nueva Tarifa Mensual : </label></td>
			<td colspan="3"><s:textfield id="txtNuevaTarifa" name="activacionPaqueteActionFilter.sNuevaTarifa"  readonly="true" cssClass="campotexto"/></td>
			</tr>
			<tr>
			<td colspan="4" align="center"><input class="btWide" type="submit" name="Submit" value="Aceptar" ></td>
			</tr>
		</table>
	</form>
	</s:if>
	<s:else>

	</s:else>
</div>

</div>
</div> 

