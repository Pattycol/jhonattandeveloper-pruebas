<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="pe.com.claro.caef.web.auth.Usuario"%>
<%
  	Usuario objUser =(Usuario)session.getAttribute("usuario");  
	String sFlagUsuario = objUser.getsFlagUsuario();
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
				$("#tablaServicios").paginar({
					 items : 10,
					 div_paginacion : '#servicio'
					 
				});
				$("#tablaContactos").paginar({
					 items : 10,
					 div_paginacion : '#contacto'
					 
				});
				$("#tablaSucursales").paginar({
					 items : 10,
					 div_paginacion : '#sucursal'
					 
				});
			});
</script>
<br>
<div class="tablaPlomo">
<div>
						  
					 
						  <div class="detalleTitulo">
						  	Consulta de Datos del Cliente y de los Servicios
						  </div>
		  
						  <div>
						  <table width="100%" border="0" >
						 
  	<tr>
	<td width="25%" class="textoDentroPanel"><label for="lbl_cantDecoMax">Cliente / Raz&oacute;n Social : </label></td>
    <td colspan="3" ><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.nomCliente"  ></s:textfield></td>
    </tr>
  <tr>
    <td class="textoDentroPanel">Codigo : </td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.codCliente"  ></s:textfield></td>
    </tr>
  <tr>
    <td class="textoDentroPanel"><label for="lbl_email">Email :</label></td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.valCorreoElectronico"  ></s:textfield></td>
    </tr>
    
  <tr>
    <td class="menuSuperior"><label>Direcci&oacute;n</label></td>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td class="textoDentroPanel">Tipo V&iacute;a : </td>
    <td>
    <s:select 
		list="lstConsultaDatosMaestroTipVia" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codTipoDocumento"
		name="consultaCliente.codTipoDocumento"
		disabled="true"
		cssStyle="width:175px; background:#E6E6E6; font-size:10px;"/> 
      </td>
    <td class="textoDentroPanel">Nombre de V&iacute;a : </td>
    <td><s:textfield disabled="true" cssStyle="width:172px;" cssClass="campotexto" name="consultaCliente.nomVia"  ></s:textfield></td>
  </tr>
  <tr>
    <td class="textoDentroPanel">N&uacute;mero de V&iacute;a : </td>
    <td colspan="3"><s:textfield cssStyle="width:172px;" disabled="true" cssClass="campotexto" name="consultaCliente.numVia"  ></s:textfield></td>
    </tr>

  <tr>
    <td class="textoDentroPanel">Tipo de Domicilio : </td>
    <td>
    <s:select 
		list="lstConsultaDatosMaestroTipDomicilio" 
		listKey = "valorDatoMaestro1"
		listValue = "valorDatoMaestro2"
		value="consultaCliente.codTipoDomicilio"
		name="consultaCliente.codTipoDomicilio"
		disabled="true"
		cssStyle="width:175px; background:#E6E6E6; font-size:10px;"/> 
    </td>
    <td class="textoDentroPanel">Det. Domicilio : </td>
    <td><s:textfield cssStyle="width:172px;" disabled="true" cssClass="campotexto" name="consultaCliente.desDomicilio"  ></s:textfield></td>
  </tr>
  <tr>
    <td class="textoDentroPanel">Piso : </td>
    <td><s:textfield cssStyle="width:172px;" disabled="true" cssClass="campotexto" name="consultaCliente.valPiso"  ></s:textfield></td>
    <td class="textoDentroPanel">Manzana : </td>
    <td><s:textfield cssStyle="width:172px;" disabled="true" cssClass="campotexto" name="consultaCliente.valManzana"  ></s:textfield></td>
  </tr>
  <tr>
    <td class="textoDentroPanel">Lote : </td>
    <td><s:textfield cssStyle="width:172px;" disabled="true" cssClass="campotexto" name="consultaCliente.valLote"  ></s:textfield></td>
    <td class="textoDentroPanel">Sector : </td>
    <td><s:textfield cssStyle="width:172px;" disabled="true" cssClass="campotexto" name="consultaCliente.valSector"  ></s:textfield></td>
  </tr>
  <tr>
    <td class="textoDentroPanel">Urbanizaci&oacute;n : </td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.nomUrbanizacion"  ></s:textfield></td>
    </tr>
  <tr>
    <td class="textoDentroPanel">Referencia : </td>
    <td colspan="3"><s:textfield cssStyle="width:500px;" disabled="true" cssClass="campotexto" name="consultaCliente.valReferencia"  ></s:textfield></td>
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
		cssStyle="width:170px; background:#E6E6E6; font-size:10px;"/> 
    </td>
  </tr>
  <%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
  <tr>
    <td><label style=" font-weight:bold; font-size: 10pt">Otros Datos </label></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  
  <tr>
    <td class="textoDentroPanel">Consultor : </td>
    <td colspan="3"><s:textfield size="30" disabled="true" cssClass="campotexto" name="consultaCliente.nomClientePila"  ></s:textfield></td>
    </tr>
  <tr>
    <td class="textoDentroPanel">Adm. de Proyectos : </td>
    <td colspan="3"><s:textfield size="30" disabled="true" cssClass="campotexto" name="consultaCliente.nomApePaterno"  ></s:textfield></td>
   </tr>
   <%} %>
  <tr>
    <td class="menuSuperior"><label>Contactos</label></td>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="4" class="tablaPlomo">
	<table id="tablaContactos" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th>  Nombre Contacto  </th>
			<th>  Documento  </th>
		  </tr>
		</thead>
		<tbody>
		 	<s:iterator value="consultaCliente.lstContactoCliente" status="rowstatus">
	 			<s:if test="#rowstatus.odd == true">
			    	  <tr class="odd">
			    </s:if>
			    <s:else>
				      <tr>
				</s:else>
				
				    <td align="left" class="campoTextoCelda"><s:property value="nombreContacto"/></td>
					<td align="left" class="campoTextoCelda"><s:property value="numDocumentoIden"/></td>
				  </tr>
		  		
		   </s:iterator>	 
		 
	  </tbody>
		</table>
		<div id="contacto" class='page_navigation'></div> 
	</td>
    </tr>
  <tr>
    <td class="menuSuperior"><label>Servicios</label></td>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="4" class="tablaPlomo">
	<table id="tablaServicios" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th> Tipo Servicio  </th>
			<th> Producto/Paquete  </th>
			<th> Servicio  </th>
			<th> Fec. Activaci&oacute;n  </th>
			<th> Estado  </th>
			<th> N&uacute;m. Servicio  </th>
		  </tr>
		</thead>
		<tbody>
		<s:iterator value="consultaCliente.lstServicioCliente" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
		  
				<td align="left" class="campoTextoCelda"><s:property value="desTipoServicio"/></td>
				<td align="left" class="campoTextoCelda"><s:property value="desProducto"/></td>
				<td align="left" class="campoTextoCelda"><s:property value="desServicio"/></td>
				<td align="center" class="campoTextoCelda"><s:property value="fecActivacion"/></td>
				<td align="left" class="campoTextoCelda"><s:property value="desEstado"/></td>
				<td align="left" class="campoTextoCelda"><s:property value="numServicio"/></td>
			  </tr>
		   </s:iterator>	 
	  </tbody>
		</table>
		<div id="servicio" class='page_navigation'></div> 
	</td>
    </tr>
    <%if(sFlagUsuario.equalsIgnoreCase("C")){ %>
	<tr>
    <td><label style=" font-weight:bold; font-size: 10pt">Sucursales</label></td>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td colspan="4">
	<table id="tablaSucursales" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th>Nombre </th>
			<th>Direcci&oacute;n </th>
			<th>Distrito </th>
			<th>Ciudad </th>
			<th>Facturaci&oacute;n </th>
		  </tr>
		</thead>
		<tbody>
		<s:iterator value="consultaCliente.lstSucursalCliente" status="rowstatus">
	 			<s:if test="#rowstatus.odd == true">
			    	  <tr class="odd">
			    </s:if>
			    <s:else>
				      <tr>
				</s:else>
							<td align="left" class="campoTextoCelda"><s:property value="nomSucursal"/> </td>
							<td align="left" class="campoTextoCelda"><s:property value="dirSucursal"/></td>
							<td align="left" class="campoTextoCelda"><s:property value="nombreDistrito"/></td>
							<td align="left" class="campoTextoCelda"><s:property value="ubiSucursal"/></td>
							<td align="center" class="campoTextoCelda"><s:checkbox name="flgFacturacion" disabled="true"/></td>
						  </tr>
					
		 </s:iterator>	 
		 
	  </tbody>
		</table>
		<div id="sucursal" class='page_navigation'></div>
	</td>
    </tr>
    <%} %>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">&nbsp;</td>
    </tr>
  <!--  <tr> 
    <td colspan="4" align="center"><input  type="submit" name="Submit" value="Aceptar" ></td>
   
  </tr>-->
</table>

						  </div>
		  						 
		  </div>									  
									  
	    </div>