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
				$("#tablaTrafficView").paginar({
					 items : 10,
					 div_paginacion : '#paginador'
				});
			});
			
			function verUrl(codigo)
			{
				window.open(codigo,"trafficView","width=500,height=500");
			}
</script>
<br>
	<div class="tablaPlomo">
	    <div align="center">
						  
						  
						  <div class="detalleTitulo">
						  	Traffic View
						  </div>
						 
						  
			              <table class="textofuerapanel">
						  
		  				  <tr>
		  				  <td aling="left"><br>
			                              <b> <span>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></span><br><br>
                            </b> </td></tr>
		  </table>
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
	    </div>
	    
	    <s:if test="lstTrafficView.size() > 0" > 
                <table id="tablaTrafficView" border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th>CID</th>
			<th>Sucursal</th>
			<th>Direcci&oacute;n</th>
			<th>Producto</th>
			<th>Fec. Instalaci&oacute;n</th>
		    <th>Ver</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator value="lstTrafficView" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="center"><s:property value="codCID"/></td>
			<td align="left"><s:property value="nomSucursal"/></td>
			<td align="left"><s:property value="nomDirecSucursal"/></td>
			<td align="left"><s:property value="nomProducto"/></td>
			<td align="center"><s:property value="fecInst"/></td>
		    <td align="center"><input type="button" onclick="verUrl('<s:property value="urlTraffic"/>')" style="background-image:url('../img/trafficView.jpg');no-repeat; border:none; width:24px; height:24px;"></td>
		    <%--  <s: submit type="image" src="../img/consulta_reporte.jpg"/> --%>
		  </tr>
		 </s:iterator>	
	  </tbody>
		</table>
		<div id="paginador" class='page_navigation'></div> 
</s:if>
	
	
	</div>