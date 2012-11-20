<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	
function Limpiar() {
	document.getElementById("antiguaClave").value = "";
	document.getElementById("nuevaClave").value = "";
	document.getElementById("confirmaClave").value = "";
	}
	
function alerta(){
	var objindicador=<%=request.getAttribute("flag")%>;
	var num=parseInt(objindicador);
	var mensaje="<%=request.getAttribute("mensajeResultado")%>";
	if(num==1){
		alert(mensaje);
	}

//probando deshabilitar el boton regresar
	

		
		
		//location.replace(history.forward(1))
	
	
}
if(history.forward(1))
	alert("retro");
alerta();
</script>

<link rel="stylesheet" href="../css/login.css" />
<br>
<div class="tablaPlomo">
	  
	    <div align="center">
		<div class="detalleTitulo">
						  	Cambio de Clave
						  </div>
						  <table class="textofuerapanel">
		  <tr align="left"><td>
						  <br>
	          <b> <strong><s:property value="mensajesSeguridad.mensaje1"/></strong> <br>
        </b>    
        </td></tr>
		  </table>
		        </div>
        				 
  
        <s:form >			
          
		<table width="100%"  align="left" cellpadding="4" cellspacing="0" class="tablaPloma"  >

			<tbody onload="Javascript:history.go(1);" onunload="Javascript:history.go(1);">
			
			<tr>
			 	 <td  width="10%"></td>
				 <td  width="16%" height="2px"></td>
				 <td width="23%"  ></td>
				 <td width="22%"  ></td>
				 <td width="19%"  ></td> 
				 <td width="10%"  ></td>
			</tr>

				<tr>
				  <td></td>
					<td colspan="2"><span class="textoDentroPanel">
					  <label for="lbl_antiguaClave">Ingresar ANTIGUA CLAVE:</label>
					</span></td>
					<td>
					<s:password cssClass="campoTextoHabilitado" size="20" name="passwordAntiguo" maxlength="6" id="antiguaClave"></s:password>
					</td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td colspan="2"><span class="textoDentroPanel">
					  <label for="lbl_nuevaClave">Ingresar NUEVA CLAVE:</label>
					</span></td>
					<td>
					<s:password cssClass="campoTextoHabilitado" size="20" name="passwordNuevo" maxlength="6" id="nuevaClave"></s:password>
					</td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td colspan="2"><span class="textoDentroPanel">
					  <label for="lbl_confirmarClave">Confirmar NUEVA CLAVE:</label>
					</span></td>
					<td>
					<s:password cssClass="campoTextoHabilitado" size="20" name="passwordConfirma" maxlength="6" id="confirmaClave"></s:password>
					</td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td><span style="padding-left: 5px">
					  <input class="btWide" name="buttonLimpiar" value="Limpiar" type="button" onclick="Limpiar();">
					</span></td>
					<!-- 
					<td><input class="btWide" name="buttonActualizar" value="Actualizar" type="button"></td>
						 -->
					<td><s:submit cssClass="btWide" name="buttonActualizar" value="Actualizar" action="cambiarClave"/></td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
		</tbody></table>	
		</s:form>
		<div style="clear:both;"></div>
		<div style="margin:25px 0 15px 5px; ">
		
	         <br> <strong><span class="textoFueraPanel"><s:property value="mensajesSeguridad.mensaje2"/></span></strong> <br/>
	         <span class="textoDentroPanel"><s:property value="mensajesSeguridad.mensaje2"/></span>
	         
            </div>
        </div>