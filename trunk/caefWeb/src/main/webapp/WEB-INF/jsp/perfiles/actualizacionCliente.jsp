<%@page import="pe.com.claro.caef.web.util.MENSAJES_CONFIG"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@page import="pe.com.claro.caef.web.auth.Usuario"%>
  
  <%
  	Usuario objUser =(Usuario)session.getAttribute("usuario");  
	String sFlagUsuario = objUser.getsFlagUsuario();
  %>

<script>
$(function() {
	 $(document).keydown(function(e){
	  var code = (e.keyCode ? e.keyCode : e.which);
	  if(code == 116) {
	   e.preventDefault();
	   location="<%=request.getContextPath()%>/j_spring_security_logout";
	  }
	  if(code==13){
	  	return false;
	  }
	  
	 });
	});



function validarEmail() {
	var correo = document.getElementById("correoElectronico").value;
	if (/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$/.test(correo)){
		document.getElementById("correoElectronico").value = "";
		alert("<%=MENSAJES_CONFIG.VALIDA_MAIL_JSP%>");
	return (false);
	} else {
		alert("La dirección de email " + correo + " es correcta."); 
	return (true);
	}
	
	
}

function validarEmail2(){
	var correo = document.getElementById("correoElectronico").value;
	var filtro = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
	if (!filtro.test(correo)) {
		document.getElementById("correoElectronico").value = "";
	   alert("<%=MENSAJES_CONFIG.VALIDA_INGRESO_CORREO%>");
	}
	
}
</script>
<br>
<div class="tablaPlomo" style="padding-bottom:20px;">
<div>
						  
						  
						  <div class="detalleTitulo">
						  
						  	Actualizaci&oacute;n de Datos del Cliente - <%=objUser.getTipUsuario() %>
						  </div>
		  <div >
						 <s:form action="/jsp/executeActualizacionCliente">
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
  	<tr>
	<td width="25%" class="textoDentroPanel"><label for="lbl_cantDecoMax">Cliente o Raz&oacute;n Social : </label></td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.nomCliente"></s:textfield></td>
    </tr>
  <tr>
    <td class="textoDentroPanel">C&oacute;digo : </td>
    <td colspan="3">
    	
    	<s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.codCliente"></s:textfield>
    	
    </td>
    </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label><label class="textoDentroPanel" for="lbl_direccion">Email :</label></td>
	    <td colspan="3">
	    	<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
	    	<s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.valCorreoElectronico" id="correoElectronico" onblur="validarEmail2();" maxlength="50"/>
			<%}else if(sFlagUsuario.equalsIgnoreCase("R")){ %>    
			<s:textfield cssStyle="width:500px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.valCorreoElectronico" id="correoElectronico" onblur="validarEmail2();" maxlength="50"/>
			<%} %>
		</td>	
    </tr>
  <tr>
    <td><label class="menuSuperior">Direcci&oacute;n</label></td>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td class="textoDentroPanel">Tipo V&iacute;a : </td>
    <td>
    <s:select  
		list="lstConsultaDatosMaestroTipVia" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codTipoVia"
		name="consultaCliente.codTipoVia"
		disabled="true"
		cssStyle="width:175px; background:#E6E6E6; font-size:10px;"/>
    </td>
    <td class="textoDentroPanel">Nombre de V&iacute;a : </td>
    <td><s:textfield disabled="true" cssStyle="width:173px;" cssClass="campotexto" name="consultaCliente.nomVia"></s:textfield></td>
  </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">N&uacute;mero de V&iacute;a : </label></td>
    <td colspan="3">
    <%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:173px;" disabled="true" cssClass="campotexto" name="consultaCliente.numVia" maxlength="50" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxLength="5" ></s:textfield>
    <%}else{ %>	
    	<s:textfield cssStyle="width:173px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.numVia" maxlength="50" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxLength="5" ></s:textfield>
    <%} %>
    </td>
  </tr>

  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel"> Tipo de Domicilio : </label> </td>
    <td>
     <%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
	    <s:select 
			list="lstConsultaDatosMaestroTipDomicilio" 
			listKey = "valorDatoMaestro1"
			listValue = "valorDatoMaestro2"
			disabled="true"
			value="consultaCliente.codTipoDomicilio"
			name="consultaCliente.codTipoDomicilio"
			cssStyle="width:175px; background:#E6E6E6; font-size:10px;"/> 
	   <%}else{ %>
	   		<s:select 
			list="lstConsultaDatosMaestroTipDomicilio" 
			listKey = "valorDatoMaestro1"
			listValue = "valorDatoMaestro2"
			disabled="false"
			value="consultaCliente.codTipoDomicilio"
			name="consultaCliente.codTipoDomicilio"
			cssStyle="width:175px; background:#FFFFFF; font-size:10px;"/> 
	   <%} %>
    </td>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Det. Domicilio : </label></td>
    <td>
    	<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:173px;" disabled="true" cssClass="campotexto" name="consultaCliente.desDomicilio" maxlength="20"/>
    	<%}else{ %>
    	<s:textfield cssStyle="width:173px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.desDomicilio" maxlength="20"/>
    	<%} %>
    </td>
  </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Piso : </label></td>
    <td>
   		<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:173px;" disabled="true" cssClass="campotexto" name="consultaCliente.valPiso" maxlength="5"/>
    	<%}else{ %>
    	<s:textfield cssStyle="width:173px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.valPiso" maxlength="5"/>
    	<%} %>
    	
    </td>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Manzana : </label></td>
    <td>
    	<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:173px;" disabled="true" cssClass="campotexto" name="consultaCliente.valManzana" maxlength="5"/>
    	<%}else{ %>
    	<s:textfield cssStyle="width:173px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.valManzana" maxlength="5"/>
    	<%} %>
    </td>
  </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Lote : </label></td>
    <td>
    	<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:173px;" disabled="true" cssClass="campotexto" name="consultaCliente.valLote" maxlength="5"/>
    	<%}else{ %>
    	<s:textfield cssStyle="width:173px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.valLote" maxlength="5"/>
    	<%} %>
    </td>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel"> Sector : </label></td>
    <td>
    	<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:173px;" disabled="true" cssClass="campotexto" name="consultaCliente.valSector" maxlength="5"/>
    	<%}else{ %>
    	<s:textfield cssStyle="width:173px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.valSector" maxlength="5"/>
    	<%} %>
    </td>
  </tr>
  <tr>
    <td class="textoDentroPanel">Urbanizaci&oacute;n : </td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.nomUrbanizacion"></s:textfield></td>
    </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel"> Referencia : </label></td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" cssClass="campoTextoHabilitado" name="consultaCliente.valReferencia" maxlength="200"></s:textfield></td>
    </tr>
  <tr>
    <td class="textoDentroPanel">Distrito : </td>
    <td>
     <s:select 
		list="lstConsultaDatosMaestroDistrito" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codDisrito"
		name="consultaCliente.codDisrito"
		disabled="true"
		cssStyle="width:175px; background:#E6E6E6; font-size:10px;"/>
    </td>
    <td class="textoDentroPanel">Ciudad : </td>
    <td>
    <s:select 
		list="lstConsultaDatosMaestroCiudad" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codProvincia"
		name="consultaCliente.codProvincia"
		disabled="true"
		cssStyle="width:175px; background:#E6E6E6; font-size:10px;"/> 
    </td>
  </tr>
  <tr>
    <td><label class="menuSuperior">Otros Datos </label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Consultor : </label></td>
    <td colspan="3"><s:textfield cssStyle="width:500px;"  disabled="true" cssClass="campotexto" name="consultaCliente.nomClientePila"></s:textfield></td>
  </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Adm. de Proyectos : </label></td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.nomApePaterno"></s:textfield></td>
  </tr>
  <%} %>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Cargo o Desempe&ntilde;o : </label></td>
    <td colspan="3">
   		<%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
    	<s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.nomCargoEsp" maxlength="30"/>
    	<%}else{ %>
    	<s:textfield cssStyle="width:500px;" disabled="false" cssClass="campoTextoHabilitado" name="consultaCliente.nomCargoEsp" maxlength="30"/>
    	 <%} %>
    </td>
    </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Fecha de Nacimiento : </label></td>
    <td colspan="3">
    <sj:datepicker cssClass="campotexto" changeMonth="true" changeYear="true" name="consultaCliente.fecNacimiento" label="Fecha Nacimiento" displayFormat="dd/mm/yy" label="Today" firstDay="1" maxDate="+0m" readonly="true"/>
    </td>
    </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Nacionalidad : </label></td>
    <td>
    <s:select 
		list="lstConsultaDatosMaestroNacionalidad" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.nomApeMaterno"
		name="consultaCliente.codNacionalidad"
		cssStyle="width:175px; background:#FFFFFF; font-size:10px;"/>
    </td>
    </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Sexo : </label></td>
    <td colspan="3">
    <s:select 
		list="lstConsultaDatosMaestroSexo" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codGenero"
		name="consultaCliente.codGenero"
		cssStyle="width:175px; background:#FFFFFF; font-size:10px;"/>
    </td>
    </tr>
  <tr>
    <td><label style="color:#FF0000;">(*) </label> <label class="textoDentroPanel">Estado Civil : </label></td>
    <td colspan="3">
    <s:select 
		list="lstConsultaDatosMaestroEstadoCivil" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codEstadoCivil"
		name="consultaCliente.codEstadoCivil"
		cssStyle="width:175px; background:#FFFFFF; font-size:10px;"/>
    </td>
    </tr>
  <tr>
	 <s:if test="flagBoton == true" >
    <!--<td colspan="4" align="center"><br><input class="btWideEnabled" type="submit" name="btnAceptar" value="Aceptar" id="btnAceptar" disabled="disabled"></td>--> 
    <!--  <td colspan="4" align="center"><br><font color="red">Se guardo correctamente</font></td> -->
    </s:if>
    <s:else>
    <td colspan="4" align="center"><br><input class="btWide" type="submit" name="btnAceptar" value="Aceptar" id="btnAceptar"></td>
	</s:else>
  </tr>
</table>
</s:form>
						  </div>
									  
	    </div>
    </div>
	    