<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="../js/jquery.paginarTabla-1.0.js"></script>
<link rel="stylesheet" href="../css/gridSeguridad.css?v=1" />
<script type="text/javascript">


var clickCount=0;
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

			});
			
			function habilitaBoton(){
				
				var names = document.getElementsByName("names");
				var valor = document.getElementById("txtOcultoValor").value;
				var tam   =names.length;
				clickCount++;
				//alert("Clicks " +clickCount);
				
				var con =0;
				var activos=0;
				for(var i=0;i<names.length;i++){
					if(names[i].checked==false){
						con++;			
					}

				}
				activos=tam-con;
				//alert("Activos "+activos);
				
				if(con==names.length){
					document.getElementById("btnAceptar").disabled = true;
					document.getElementById("btnAceptar").className = "btWideEnabled";
				}
				else{
					
					if(valor == -1){
						
						for(var i=0;i<names.length;i++){
							
							if(names[i].checked==true){
								//alert("true en la posicion: "+i);
								document.getElementById("txtOcultoValor").value = i;
							}
						}
					}
					else{
						if(names[valor].checked==true && clickCount%2!=0 && activos==1){
							
							document.getElementById("txtOcultoValor").value = valor;	
									
						}else if(names[valor].checked==true && clickCount%2==0 && activos==1){
							document.getElementById("txtOcultoValor").value = valor;	
						}else{	
							
							names[valor].checked = false;
							for(var i=0;i<names.length;i++){
								if(names[i].checked==true){
									//alert("true en la posicion: "+i);
									document.getElementById("txtOcultoValor").value = i;			
								}
	
							}
						}		
							
					}
					
					document.getElementById("btnAceptar").disabled = false;
					document.getElementById("btnAceptar").className = "btWide";
					
				}
				
			}
</script>
<br>
	  <div align="left" class="tablaPlomo">
	    <div align="center">
						  						  <div class="detalleTitulo" align="center">
						  	
						  	Seleccionar Servicio
						  </div><br>
			                
                </div>
                 
                 
<s:form action="/jsp/executeSeleccionarServicio">
<s:hidden id="txtOcultoValor" name="posicionCheck" value="-1"/>
<table width="100%" align="center">
  <tr>
    <td width="100%" colspan="6" align="center"><s:if
							test="hasActionErrors()">
							<div class=" msg-error">
							
								<s:actionerror cssClass="centrar"/>
							</div>
						</s:if>
		</td>
  </tr>
</table>
<table width="100%">
  <tr>
    <td>
	<table id="tablaServicios" width="100%" border="0" class="cuerpo">
		<thead>
		<tr>
			<th><font color="red">Id. Producto</font> </th>
			<th><font color="red">Descripci&oacute;n Producto</font> </th>
			<th><font color="red">C&oacute;digo Servicio</font> </th>
			<th><font color="red">Descripci&oacute;n Servicio</font> </th>
			<th></th>
		  </tr>
		</thead>
		<tbody>
		<s:iterator value="lstConsultaServicioClienteTotal" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
		  
				<td align="center"><s:label name="idProducto" id="idProd"/><s:hidden id="idProdHd" name="idProducto"></s:hidden> </td>
				<td align="center"><s:property value="descripcion"/></td>
				<td align="center"><s:property value="codigoServicio"/></td>
				<td align="center"><s:property value="descripcionServicio"/></td>
				<td><s:checkbox id="ckbServicio" name="names" fieldValue="true" onclick="javascript:habilitaBoton();" /></td>	
			  </tr>
		   </s:iterator>
	  </tbody>
	  </table>
	  <div id="servicio" class='page_navigation'></div> 
</td>
  </tr>
</table>
<br />
<table width="100%">
  <tr>
    <td width="16%">&nbsp;</td>
    <td width="16%">&nbsp;</td>
    <td width="16%"><input class="btWideEnabled" id="btnAceptar" name="btnAceptar" type="submit" value="Aceptar" disabled="disabled"/>
    </td>
    <td width="16%" align="center">
    <a class="btWideLink" href="<%=request.getContextPath()%>/j_spring_security_logout">&nbsp;Cancelar</a>
    <!-- <input class="btWide" name="cancelar" type="button" value="Cancelar"/> --></td>
    <td width="16%">&nbsp;</td>
	<td width="16%">&nbsp;</td>
  </tr>
</table>
</s:form>                    
	
	</div>