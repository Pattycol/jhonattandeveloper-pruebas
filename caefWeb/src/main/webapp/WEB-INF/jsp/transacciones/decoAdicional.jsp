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
				$("#tablaDecoAdicional").paginar({
					 items : 10,
					 div_paginacion : '#paginador'
				});
			});
			
			
</script>
<script type="text/javascript">
	
	function visibleDiv(){
		var visible = document.getElementById("visible").value;	
			if(document.getElementById("contenido_a_mostrar"))
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
	
	function addComboDecoAdicional(){
		
		var decoMaximo = document.getElementById("txtDecoMaximo").value;
		var decoActual = document.getElementById("txtDecoActual").value;
		var can = decoMaximo - decoActual;
		var pest = document.getElementById("canAdicional");
		textoHtml = '<select id="txt_cantDecoAdi" name="decoAdicionalActionFilter.sCboDecoAdicional"> ';
		
		for(var i=0;i<can+1;i++){
			textoHtml += '<option>'+i+'</option>';
		}
		
		textoHtml +='</select>';
		pest.innerHTML = textoHtml;
		
	}
	
	function addComboDecoAdicional2(){
		 var comDist = document.getElementById("txt_cantDecoAdi").value;
		 limpiarLista(comDist);
		 var decoMaximo = document.getElementById("txtDecoMaximo").value;
		 var decoActual = document.getElementById("txtDecoActual").value;
		 var can = decoMaximo - decoActual;
		 
		 for(var i=0;i<can+1;i++){
			  comDist.appendChild(new Option(i,i));
		 }
		 
	}
	
	function limpiarLista(obj){
	        var lista = document.getElementById(obj.id);
	        var listTam = lista.options.length;
	        for(k=listTam;k>-1;k--){
	            lista.options[k] = null;
	        }
	}	
	
	window.onload = function(){
		visibleDiv();
	}
	function ocultaFormulario(){
		var objindicador=<%=request.getAttribute("flag")%>;
		var num=parseInt(objindicador);
		
		if(num==1){
			alert("en el 1");
			document.getElementById('btnGenerar').disabled=true;
		}	
	}
	


</script>

	  <br>
	    <div class="tablaPlomo">
	    
	    <s:hidden id="visible" name="visible"/>
				  
						  
						  <div class="detalleTitulo">
						  	Solicitud de Deco Adicional
						  </div>
						  <table width="100%" border="0">
						    <tr>
			 	 <td width="100%" colspan="6" align="center">
			 	 <s:if
							test="hasActionErrors()">
							<div class=" errors">
							
								<s:actionerror cssClass="centrar"/>
							</div>
				</s:if> 
				
				<s:if test="hasActionMessages()">
							<div class=" msg-ok">
							
								<s:actionmessage cssClass="centrar"/>
							
							</div>
						</s:if></td>
			</tr>
						  </table>
						  
<div style="border:1px solid #ccc; width:98%; height:auto; display:block; margin:20px 5px 5px 5px;">
<p style="display:block; padding-left:3px; background-color:#fff; margin-top:-10px; width:100px; height:20px; margin-left:7px;">Datos del Servicio</p>

						  <div>
		<s:if test="lstConsultarInstanciaServicio.size() > 0">
							<table id="tablaDecoAdicional" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th><font color="red">Paquete</font></th>
			<th><font color="red">Direcci&oacute;n</font></th>
			<th></th>
		  </tr>
		</thead>
		<tbody>
		  <s:iterator value="lstConsultarInstanciaServicio" status="rowstatus">
		  	<s:form>
	
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="left"><s:property value="numero"/>
			<td align="center"><s:property value="direccion"/>
			<s:hidden id="direccionOculto" name="direccion"/>
			<s:hidden id="sLetraInstancia" name="sLetraInstancia"/>
			<s:hidden id="codigoInstanciaServicio" name="codigoInstanciaServicio"/>			
			</td>
			<!-- <td align="left"><s:property value="numero"/> -->
			

			
			</td>
			<td align="center"><s:submit action="executeDecoAdicional" type="image" src="../img/Solicitud.jpg"/></td>
			</tr>
			</s:form>
		  </s:iterator>	
	  </tbody>
		</table></div></div>
		<div id="paginador" class='page_navigation'></div> 
						  </s:if>
						  
			                              
                                      </b> 
		



<s:if test="lstTipoDeco.size() > 0" >
	<s:if test="lstDecoMaximo.size() > 0" >
		<s:if test="lstDecoNumero != null">
			<s:if test="lstMontoServicio != null">
			<s:form name="form1" method="post" action="aceptarDecoAdicional">
			<div id="contenido_a_mostrar" style="border:1px solid #ccc; width:98%; height:auto; display:block; margin:20px 5px 5px 5px;">
 <p style="display:block; padding-left:3px; background-color:#fff; margin-top:-10px; width:150px; height:20px; margin-left:7px;">Solicitud de DECO</p>

						  <div>
				
					<table width="100%" border="0">

					<s:hidden id="hdCodServicio" name="decoAdicionalActionFilter.sCodInstanciaServicio"></s:hidden>
						  
			  	<tr>
				<td width="25%"><label for="lbl_cantDecoMax">Cantidad DECO m&aacute;ximo :</label></td>
			    <td width="25%">
				<s:textfield id="txtDecoMaximo" name="decoAdicionalActionFilter.iDecoMaximo" cssClass="campotexto"  readonly="true" /></td>
			    <td width="25%"><p><label for="lbl_cantDecoAct">Cantidad DECO actual : </label></p>      </td>
			    <td width="25%">	<s:textfield id="txtDecoActual" name="decoAdicionalActionFilter.iDecoActual" cssClass="campotexto"  readonly="true" /></td>
			  </tr>
			  <tr>
			    <td><label style="color:#FF0000;">(*) </label><label for="lbl_cantDecoAdi">Cantidad DECO adicional :</label></td>
			    <td>
			    	 <s:select 
						list="lstCanDecoAdicional" 
						name="decoAdicionalActionFilter.sCboTipDeco"
						cssStyle="width:50px;"
						cssClass="dropdowns"/> 
			    </td>
				
			    <td><label style="color:#FF0000;">(*) </label><label for="lbl_tipoDeco">Tipo DECO :</label></td>
			    <td>
			    <s:select 
					list="lstTipoDeco" 
					listKey = "valorDatoMaestro1"
					listValue = "valorDatoMaestro1"
					name="decoAdicionalActionFilter.sCboTipDeco"
					cssStyle="width:155px;"
					cssClass="dropdowns"/> 

			    
			    </td>
			  </tr>
			  <tr>
			    <td><label for="txtDireccion">Direcci&oacute;n :</label></td>
			    <td colspan="3"><s:textfield id="txtDireccion" name="decoAdicionalActionFilter.sDireccion" readonly="true" cssClass="campotexto"/></td>
			    </tr>
			  <tr>
			    <td><label style="color:#FF0000;">(*) </label><label for="lbl_referencia">Referencia : </label></td>
			    <td colspan="3"><s:textfield id="txt_referencia" name="decoAdicionalActionFilter.sReferencia"  cssClass="campotexto"/></td>
			    </tr>
			  <tr>
			    <td><label style="color:#FF0000;">(*) </label><label for="lbl_telefonoFijo">Tel&eacute;fono Fijo :</label> </td>
			    <td>
			    <s:select id="cboNumFijo"
					list="lstNumFijo" 
					listKey = "valorDatoMaestro1"
					listValue = "valorDatoMaestro2"
					headerKey="0"
					headerValue="-- Otro --"
					onchange="changeCboNumFijo();"
					name="decoAdicionalActionFilter.sTelFijo"
					cssStyle="width:155px"
					cssClass="dropdowns"/>
			    
			    </td>
			    <td><s:textfield id="txtNumFijoOpcional" name="decoAdicionalActionFilter.sOpcionalFijo" cssClass="campoTextoHabilitado" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxlength="9"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr> 
			    <td><label style="color:#FF0000;">(*) </label><label for="lbl_telefonoMovil">Tel&eacute;fono M&oacute;vil :</label> </td>
			    <td>
			    <s:select id="cboNumMovil"
					list="lstNumMovil" 
					listKey = "valorDatoMaestro1"
					listValue = "valorDatoMaestro2"
					headerKey="0"
					headerValue="-- Otro --"
					onchange="changeCboNumMovil();"
					name="decoAdicionalActionFilter.sTelMovil"
					cssStyle="width:155px; "
					cssClass="dropdowns"/> 
			    </td>
			    <td><s:textfield id="txtNumMovilOpcional" name="decoAdicionalActionFilter.sOpcionalMovil" cssClass="campoTextoHabilitado" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxlength="9"/></td>
			    <td>&nbsp;</td>
			  </tr>
			  <tr>
			    <td>
				<label for="lbl_MontoServicioTecnico">Monto por Servicio T&eacute;cnico: </label></td>
			    <td><s:textfield id="" name="decoAdicionalActionFilter.sMontoServicioTecnico" cssClass="campotexto" readonly="true" /></td>
			    <td><label for="lbl_nuevaTarifaMensual">Nueva Tarifa Mensual :</label> </td>
			    <td><s:textfield id="" name="decoAdicionalActionFilter.sNuevaTarifa" cssClass="campotexto" readonly="true" value="120"/></td>
			  </tr>
			  <tr>
			    <td colspan="4" align="center"><input id="btnGenerar" class="btWide" type="submit" name="Submit" value="Generar Solicitud" ></td>
			  </tr>
	</table></div></div>
	</s:form>			
			</s:if>
			<s:else>
			</s:else>
		</s:if>
		<s:else>
		</s:else>
	</s:if>
	<s:else>
	</s:else>
</s:if>
			
<s:else>

</s:else>	

		
		</div>
		  <!-- </div>									  
									  
	    </div> -->
