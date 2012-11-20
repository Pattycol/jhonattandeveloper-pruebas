<%@page import="pe.com.claro.caef.web.util.MENSAJES_CONFIG"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />
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
	
function salir(e){
	if (e.preventDefault) {
        e.preventDefault();
    }else{
        e.returnValue = false;
    }    
	document.location.href="<%=request.getContextPath()%>/j_spring_security_logout";

}
	
	function olvidoClave(){
		document.forms[0].action="<%=request.getContextPath()%>/jsp/submitOlvidoClave.action";
		document.forms[0].submit();
	}
	
function validarEmail2(){
	var correo = document.getElementById("txt_antiguaClave").value;
	var filtro = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
	if (!filtro.test(correo)) {
		document.getElementById("txt_antiguaClave").value = "";
		if(correo!="")
	   		alert("<%=MENSAJES_CONFIG.VALIDA_CORREO_ECTRONICO_JSP%>");
	}
	
}
</script>
<div style="margin:0 0 20px 0;">
<table style="width:890px; margin-bottom:10px;" align="center" cellspacing="0" cellpadding="0" border="0">
							<tbody><tr>
								<td height="21" style="padding-left: 12px; background-color:#CE0000; text-align:left;" class="txtblanco">
									<strong>Mi Claro</strong>&nbsp; 
								</td>
							</tr>			
						</tbody></table>
<div class="tablaPlomo" style="width:890px; margin:0 auto;">
	<div style="width:765px; border:solid 1px #B2B2B2;">  
	    <div align="left">
		<div class="detalleTitulo">
						  	Olvid&eacute; mi Clave
						  </div>
						  						  <table width="100%" border="0">
						    <tr>
			 	 <td width="100%" colspan="6" align="center"><s:if
							test="hasActionErrors()">
							<div class=" msg-error">
							
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
						  <table class="textofuerapanel">
		  <tr align="left"><td>
						  <br>
	          <b>&nbsp; <strong><s:property value="mensajesSeguridad.mensaje1"/></strong> <br>
        </b>          
        </td></tr>
		  </table>
        </div>
        			
        <s:form action="/jsp/submitOlvidoClave">			
		<table width="100%"  align="left" cellpadding="4" cellspacing="0" class="tablaPloma"  >

			<tbody>
			
			<tr>
			 	 <td  width="10%"></td>
				 <td  width="16%" height="2px"></td>
				 <td width="27%"  ></td>
				 <td width="22%"  ></td>
				 <td width="19%"  ></td> 
				 <td width="10%"  ></td>
			</tr>

				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td><span style="padding-left: 5px">
					  <label for="txt_antiguaClave" class="textoDentroPanel">Ingrese su Correo Electr&oacute;nico:</label>
					</span></td>
					<td><input class="campotexto" id="txt_antiguaClave" name="olvidoClaveActionFilter.correoCliente" type="text" value="" onblur="validarEmail2();"></td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
		</tbody></table>
		<div style="clear:both;"></div>
		<table width="100%">
				<tr>
				  	<td>
					<s:submit cssClass="btWide" onclick="javascript:olvidoClave();" value="Continuar" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<s:submit cssClass="btWide" onClick="salir(window.event);" value="Cancelar"/>
					</td>
				</tr>		
		</table>	
		</s:form>
		<div style="clear:both;"></div>
		<table class="textofuerapanel">
		  <tr align="left" class="verdana10"><td>&nbsp;<b><s:property value="mensajesSeguridad.mensaje2"/></b><br>
			  &nbsp;<s:property value="mensajesSeguridad.mensaje3"/>
        </td></tr>
		  </table> 
		<div style="clear:both;"></div>
		<div>
	                   </div> </div>
        </div>