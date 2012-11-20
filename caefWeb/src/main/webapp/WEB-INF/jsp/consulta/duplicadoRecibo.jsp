<%@ taglib prefix="s" uri="/struts-tags"%>
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
</script>
	  <br>
	  <div class="tablaPlomo">
	    <div align="center">
						  
						  
						  <div class="detalleTitulo">
						  	Duplicado de Recibos
						  </div>
						  
			              <div align="left">
			                              <table class="textofuerapanel">
						  
		  				  <tr>
		  				  <td aling="left">
			                              
			                              <b> <span>&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></span><br>
                                      </b> <s:property value="mensajeError"/> 
                          </td></tr>
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
	    </div></td>
	    
	    <s:if test="lstDuplicadoRecibos.size() > 0" > 
                <table border="0" cellpadding="2" class="cuerpo" cellspacing="2">
		<thead>
		<tr>
			<th><font>Fecha de Emisi&oacute;n</font> </th>
			<th><font>Fecha de Facturaci&oacute;n</font> </th>
			<th><font>Importe</font></th>
			<th><font>Recibo</font></th>
		  </tr>
		</thead>
		<tbody>
		
		<s:iterator value="lstDuplicadoRecibos" status="rowstatus">
		
			<s:form>
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>

			<td align="center"><s:property value="fecEmision"/></td>
			<td align="center"><s:property value="fecFacturacion"/><s:hidden name="codCicloFac"/></td>
			<td align="center"><s:property value="valTotal"/><s:hidden name="codFactura"/></td>
			<td align="center"><s:submit type="image" src="../img/PDF.jpg" action="exportarAction"/></td>
			</tr>
			</s:form>
		  
		</s:iterator>
		
	  </tbody>
		</table>
</s:if>
	
	
	</div>

