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
				$("#tablaCorreoElectronico").paginar({
					 items : 10,
					 div_paginacion : '#paginador'
				});
				
				
			});
			
			
			function habilitaBoton(){
				
				var ck = document.getElementById("cbkAcuerdo").checked;
				
				if(ck == true){
					document.getElementById("btnAceptar").disabled = false;
					document.getElementById("btnAceptar").className = "btWide";
				}
				else{
					document.getElementById("btnAceptar").disabled = true;
					document.getElementById("btnAceptar").className = document.getElementById("btnAceptar").className.replace( /(?:^|\s)btWide(?!\S)/ , '' );
					document.getElementById("btnAceptar").className = "btWideEnabled";
					}
			}
			
			
			
</script>
<br>
	<div class="tablaPlomo">	
	    <div align="center">
						  
						  
						  <div class="detalleTitulo">
						  	Solicitud de Recibos por Correo Electr&oacute;nico </div>
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
                          <table class="textofuerapanel">
						  
		  				  <tr>
		  				  <td aling="left">
                                              <b> <span class="strong">&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></span><br>
                                              
                                                                </b> </td></tr>
		  </table>
		  <div style="clear:both;"></div>
	    <s:form>
	    <s:if test="lstReciboCorreoElectronico.size() > 0" > 
                <table id="tablaCorreoElectronico" width="95%" border="0" cellpadding="2" cellspacing="2" class="cuerpo">
		<thead>
		<tr>
			<th width="24%"><font>Servicio</font></th>
			<th width="70%"><font>Correo Electr&oacute;nico</font> </th>
			<th width="6%"><font>Activar</font></th>
		  </tr>
		</thead>
		<tbody>
		 <s:iterator value="lstReciboCorreoElectronico" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="center"><s:text name="nomServicio"/></td>
			<td align="center">
			
				<s:select id="cboCorreos"
						name="lstCorreoString.descripcion" 
						list="lstEmailString"
						cssStyle="width:250px;"
						/>
			
			</td>
			<td align="center"><s:checkbox name="checkboxes[%{#rowstatus.index}]" id="ckActivo" value="flgActivo"/></td>
			<td><s:hidden name="codGrupo">
				
			</s:hidden></td>
		  </tr>
		 </s:iterator>	
	  </tbody>
		</table>

		<div id="paginador" class='page_navigation'></div> 
</s:if>
	
	<div align="center">
			              
	    </div>
	
	<table width="100%">
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="46%">&nbsp;</td>
    <td width="15%">&nbsp;</td>
    <td width="15%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3"><div align="left">
     <s:checkbox id="cbkAcuerdo" name="cbkAcuerdo" fieldValue="false" onclick="habilitaBoton();"/>
			                              Aceptar T&eacute;rminos y Condiciones del Servicio. </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" align="center"><s:submit  cssClass="btWideEnabled" action="guardarAceptacion" name="btnAceptar" value="Aceptar" id="btnAceptar" disabled="true"/></td>
  </tr>
</table>
</s:form>
		  
		  
	    </div>


<script type="text/javascript">

		var ck = document.getElementById("ckActivo").checked;
				
		if(ck == true){
			document.getElementById("ckActivo").disabled = true;
			document.getElementById("cboCorreos").disabled = true;
		}
		else{
			document.getElementById("ckActivo").disabled = false;
			document.getElementById("cboCorreos").disabled = false;
		}
			

</script>


	</div>