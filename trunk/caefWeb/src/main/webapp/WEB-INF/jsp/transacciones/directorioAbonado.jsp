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
				$("#tablaDirectorioAbonado").paginar({
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
						  	Directorio de Abonados
						  	<s:form action="/jsp/executeDirectorioAbonado">
						  </div>
						 <s:if
							test="hasActionErrors()">
							<table><tr><td width="100%" colspan="6" align="center">
							<div class=" msg-error">
							
								<s:actionerror cssClass="centrar"/>
							</div></td></tr></table>
						</s:if> <s:if test="hasActionMessages()">
							<table><tr><td width="100%" colspan="6" align="center">
							<div class=" msg-ok">
							
								<s:actionmessage cssClass="centrar"/>
							
							</div>
							</td></tr></table>
						</s:if>
						  
			              <table class="textofuerapanel">
						  
		  				  <tr>
		  				  <td aling="left">
			                              <b> <span class="strong">&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></span>
                                      </b></td></tr>
		  </table>
	    </div>
	    <s:hidden name="totalRegs"></s:hidden>
	    <s:if test="lstDirectorioAbonado.size() > 0" > 
                <table id="tablaDirectorioAbonado" width="93%" border="0" cellpadding="2" cellspacing="2" class="cuerpo">
		<thead>
		<tr>
			<th width="43%">Plan</th>
			<th width="47%">N&uacute;mero Telef&oacute;nico </th>
			<th width="10%">Publicar</th>
		  </tr>
		</thead>
		<tbody>
		  <s:iterator value="lstDirectorioAbonado" status="rowstatus">
 			<s:if test="#rowstatus.odd == true">
		    	  <tr class="odd">
		    </s:if>
		    <s:else>
			      <tr>
			</s:else>
			<td align="center"><s:property value="nombre"/></td>
			<td align="center"><s:label name="numero"/> <s:hidden name="numero"></s:hidden></td>
    		<td align="center"><s:checkbox name="checkboxes[%{#rowstatus.index}]" fieldValue="true" value="publicar"/></td>
		   </tr>
		  </s:iterator>
	  </tbody>
	</table>
	<div id="paginador" class='page_navigation'></div> 
	</s:if>
	<br>
	<table width="100%">
	<tr>
    <td colspan="3"><div align="left">
     <s:checkbox id="cbkAcuerdo" name="cbkAcuerdo" fieldValue="false" onclick="habilitaBoton();"/>
			                              Aceptar T&eacute;rminos y Condiciones del Servicio. </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="24%">&nbsp;</td>
    <td width="46%">&nbsp;</td>
    <td width="15%"><s:submit cssClass="btWideEnabled" action="executeDirectorioAbonado" name="btnAceptar" value="Aceptar" id="btnAceptar" disabled="true"/></td>
    <td width="15%">&nbsp;</td>
  </tr>
</table>
   </s:form>   
  </div>