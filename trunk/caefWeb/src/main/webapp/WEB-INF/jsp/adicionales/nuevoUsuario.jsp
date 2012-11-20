<%@page import="pe.com.claro.caef.web.util.MENSAJES_CONFIG"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />
<script type="text/javascript">
var valor='';
var letras=false;
var patron =/[0-9]/; 
//Esta línea llama a la funcion InicializarEventos
addEvent(window,'load',inicializarEventos,false);


function inicializarEventos()
{
// Aquie obtienes mediante DOM el control a traves de su ID 
  var ob1=document.getElementById('target');

// Se le agrega al objeto el evento que deseamo (en este caso keypress), y la funcion ('presionar') que se va a ejecutar al generarse el evento...
  addEvent(ob1,'click',salir,false);
}

function addEvent(elemento,nomevento,funcion,captura)
{
  if (elemento.attachEvent)
  {
    elemento.attachEvent('on'+nomevento,funcion);
    return true;
  }
  else  
    if (elemento.addEventListener)
    {
      elemento.addEventListener(nomevento,funcion,captura);
      return true;
    }
    else
      return false;
}

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
	
	
	function regUsuario(){
		document.forms[0].action="<%=request.getContextPath()%>/jsp/registrarUsuario.action";
		document.forms[0].submit();
	}
	
function validarEmail2(){
	var correo = document.getElementById("txt_correoElectronico").value;
	if(correo!=""){
		var filtro = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
		if (!filtro.test(correo)) {
			document.getElementById("txt_correoElectronico").value = "";
		   alert("<%=MENSAJES_CONFIG.VALIDA_CORREO_ECTRONICO_JSP%>");
		}			
	}

}

function cambioTipo(){
	
	valor=document.getElementById("tipo_documento").value;
	
	if(valor=="001"){
		//Este es Tipo RUC
		letras=false;
		document.getElementById("txt_numDocumento").value='';
		document.getElementById("txt_numDocumento").maxLength=11;
		var navegador = navigator.appName; 
		if (navegador == "Microsoft Internet Explorer"){
			document.getElementById("txt_numDocumento").attachEvent("onkeypress", cambio);
		} 
		else{
			
			document.getElementById("txt_numDocumento").addEventListener("keypress", cambio, false);
		}

		
	}else if(valor=="002"){

		letras=false;
		document.getElementById("txt_numDocumento").value='';
		document.getElementById("txt_numDocumento").maxLength=8;
		var navegador = navigator.appName; 
		if (navegador == "Microsoft Internet Explorer"){
			document.getElementById("txt_numDocumento").attachEvent("onkeypress", cambio);
		} 
		else{
			
			document.getElementById("txt_numDocumento").addEventListener("keypress", cambio, false);
		}
	}else if(valor=="003"){
		
		letras=true;
		document.getElementById("txt_numDocumento").value='';
		document.getElementById("txt_numDocumento").maxLength=9;
		var navegador = navigator.appName; 
		if (navegador == "Microsoft Internet Explorer"){
			document.getElementById("txt_numDocumento").attachEvent("onkeypress", cambio);
		} 
		else{
			document.getElementById("txt_numDocumento").addEventListener("keypress", cambio, false);
		}
	}else if(valor=="004"){
		
		letras=true;
		document.getElementById("txt_numDocumento").value='';
		document.getElementById("txt_numDocumento").maxLength=10;
		var navegador = navigator.appName; 
		if (navegador == "Microsoft Internet Explorer"){
			document.getElementById("txt_numDocumento").attachEvent("onkeypress", cambio);
		} 
		else{
			document.getElementById("txt_numDocumento").addEventListener("keypress", cambio, false);
		}
	}else if(valor=="005"){
		
		letras=true;
		document.getElementById("txt_numDocumento").value='';
		document.getElementById("txt_numDocumento").maxLength=10;
		var navegador = navigator.appName; 
		if (navegador == "Microsoft Internet Explorer"){
			document.getElementById("txt_numDocumento").attachEvent("onkeypress", cambio);
		} 
		else{
			document.getElementById("txt_numDocumento").addEventListener("keypress", cambio, false);
		}
	}else{
		letras=true;
		document.getElementById("txt_numDocumento").value='';
		document.getElementById("txt_numDocumento").maxLength=10;
		var navegador = navigator.appName; 
		if (navegador == "Microsoft Internet Explorer"){
			document.getElementById("txt_numDocumento").attachEvent("onkeypress", cambio);
		} 
		else{
			document.getElementById("txt_numDocumento").addEventListener("keypress", cambio, false);
		}	
	}
}
function cambio(b){
	// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57 
	
	tecla = (document.all) ? b.keyCode : b.which;
	//if (tecla==8||tecla==0) return true;
	
	if(letras==false){
		patron =/[0-9]/; 
	}else{
		
		 patron=/[A-Za-z0-9]/;
		
	}
	
    te = String.fromCharCode(tecla); 
    
    
    if(patron.test(te)==false){
        if (b.preventDefault) {
            b.preventDefault();
        }else{
            b.returnValue = false;
        }    	
    	
    }else{
    	return true;
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
<div class="tablaPlomo" style="width:890px; margin:0 auto; ">
	<div style="width:765px; border:solid 1px #B2B2B2;">
	    <div align="left">
		<div class="detalleTitulo">
						  	Nuevo Usuario
						  </div>
						   <table class="textofuerapanel">
		  <tr align="left"><td>&nbsp;
						  <strong><s:property value="mensajesSeguridad.mensaje3" /></strong><br>
	          <b>  <br>
        </b>          
        </td></tr>
		  </table>
						  <!-- <br>
	          <b> <strong>Para poder registrarlo, solo necesitamos lo siguiente:</strong> <br> -->
                 </div>
        
        <s:form  method="post">		
<div style="clear:both;"></div>
		<table width="60%"  align="center" cellpadding="4" cellspacing="0"  >
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
			<tbody>
			

				<tr class="servicos-texto3">
				  	<td style="padding-left: 8px" class="textoDentroPanel">&nbsp;<input id="fechaEmision" name="fechaEmision" type="hidden" value=""></td>
					<td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td align="right" style="padding-left: 5px"><span class="textoDentroPanel" style="padding-left: 8px"><%=MENSAJES_CONFIG.JSP_NUEVO_USUARIO_MSJ1 %></span></td>
					<td style="padding-left: 5px">
					<s:select name="nuevoClienteDatosFilter.tipDocumento"
							  id="tipo_documento"
							  list="lstConsultaDatosMaestroTipDocumento" 
							  listKey="valorDatoMaestro1" 
							  listValue="valorDatoMaestro5"
							  cssStyle="width:125px;"
							  cssClass="dropdowns"
							  onchange="cambioTipo();"/></td>
					<td style="padding-left: 5px">&nbsp;</td>
				    <td style="padding-left: 5px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td align="right"><span style="padding-left: 5px">
					  <label for="txt_numDocumento"> <%=MENSAJES_CONFIG.JSP_NUEVO_USUARIO_MSJ2 %></label>
					</span></td>
					<td><!--  <input id="txt_numDocumento" name="nuevoClienteDatosFilter.numDocumento" type="text" value=""
								  maxlength="15">-->
								  <s:textfield id="txt_numDocumento" cssStyle="width:125px;" name="nuevoClienteDatosFilter.numDocumento" maxlength="15"/> </td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td align="right"><span style="padding-left: 5px">
					  <label for="txt_codigoCliente"><%=MENSAJES_CONFIG.JSP_NUEVO_USUARIO_MSJ3 %></label>
					</span>
					</td>
					<td><input id="txt_codigoCliente" style="width:125px;" onKeyPress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" name="nuevoClienteDatosFilter.codCliente" type="text" value="" maxlength="8"></td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td align="right"><span style="padding-left: 5px">
					  <label for="txt_correoElectronico"><%=MENSAJES_CONFIG.JSP_NUEVO_USUARIO_MSJ4 %></label>
					</span></td>
					<td><input id="txt_correoElectronico" style="width:125px;" name="nuevoClienteDatosFilter.correoCliente" type="text" value="" onblur="validarEmail2();"></td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>

			</tbody>
			
		</table>	
		<table width="100%"><tr><td>
		<s:submit cssClass="btWide" action="registrarUsuario"  value="Aceptar"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<s:submit  id="target" cssClass="btWide"  value="Cancelar"/>
		</td></tr></table>
		</s:form>
		<div style="clear:both;"></div>
		<div class="verdana10" > 
	          <b>Nota:</b> <s:property value="mensajesSeguridad.mensaje4" /><br>
                </div></div>
                </div></div>