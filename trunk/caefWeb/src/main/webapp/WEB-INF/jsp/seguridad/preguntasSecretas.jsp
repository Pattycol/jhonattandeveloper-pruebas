<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />
<script type="text/javascript">
var valor='';
var letras=false;
var patron =/[0-9]/; 
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

function salir(e){
		if (e.preventDefault) {
            e.preventDefault();
        }else{
            e.returnValue = false;
        }    
		document.location.href="<%=request.getContextPath()%>/j_spring_security_logout";

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
function validarEmail2(){
	var correo = document.getElementById("txt_correoElectronico").value;
	if(correo!=""){
		var filtro = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;	
		if (!filtro.test(correo)) {
			document.getElementById("txt_correoElectronico").value = "";
		   alert("Formato de correo electrónico incorrecto");
		}			
	}

}
	
</script>
 <script type="text/javascript" src="../js/jquery.paginarTabla-1.0.js"></script>
 <table style="width:890px; margin-bottom:10px;" align="center" cellspacing="0" cellpadding="0" border="0">
							<tbody><tr>
								<td height="21" style="padding-left: 12px; background-color:#CE0000; text-align:left;" class="txtblanco">
									<strong>Mi Claro</strong>&nbsp; 
								</td>
							</tr>			
						</tbody></table>
	  <div class="tablaPlomo" style="width:890px; margin:0 auto; border:solid 1px #B2B2B2;">
	    <div align="center">
						  						  <div class="detalleTitulo">
						  					  <s:form action="/jsp/executeIngresoPreguntasSecretas"> 
						  	Ingreso de Preguntas Secretas
						  </div><br>
			          <table>
<tr>
			 	 <td width="100%" colspan="6" align="left"><s:if
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
<div style="clear:both;"></div>                 
<table width="100%" align="left">
  <tr>
    <td>
    <fieldset width="99%" style="border-color:#FF0000"><div>
          <table class="textofuerapanel">
		  <tr align="left"><td>
		  <b><s:property value="mensajesSeguridad.mensaje1"/></b>
		  <s:property value="mensajesSeguridad.mensaje2"/>
		  </td></tr>
		  </table>
    </div>
    </fieldset>
	</td>
  </tr>
</table>
<div style="clear:both;"></div> 
<table class="tablaPloma"  style="width:450px;" align="left">
  <tr>
    <td class="textoDentroPanel" align="left">Tipo de Documento :</td>
    <td align="left">
     <s:select
     					id="tipo_documento"
						list="lstConsultaDatosMaestroTIPDIDE" 
						listKey = "valorDatoMaestro1"
						listValue = "valorDatoMaestro2"
						name="sb.tipoDocumento"
						headerValue="  -- Selecciona --  " 
						value=""
						cssClass="dropdowns"
						cssStyle="width:216px;"
						onchange="cambioTipo();"/>	
    </td>
  </tr>
  <tr>
    <td class="textoDentroPanel" align="left">Nro de Documento :</td>
    <td align="left"><s:textfield id="txt_numDocumento" cssStyle="width:214px;" cssClass="campotexto" name="sb.nroDocumento" ></s:textfield></td>
  </tr>
  <tr>
    <td class="textoDentroPanel" align="left">Email :</td>
    <td align="left"><s:textfield id="txt_correoElectronico" cssStyle="width:214px;" cssClass="campotexto" name="sb.email" value="" onblur="validarEmail2();"></s:textfield></td>
  </tr>
</table>
<div style="clear:both;"></div> 
<table width="100%" align="left">
  <tr>
    <td class="menuSuperior" style="text-align:left; color:#7F6666;"><s:property value="mensajesSeguridad.mensaje3"/> <s:text name="cantidadPregunta"></s:text> <s:property value="mensajesSeguridad.mensaje4"/> </td>
  </tr>
</table>
<div style="clear:both;"></div> 
<table align="left">
  <tr>
    <td>
	
  		<s:iterator value="obtenerDatosPreguntas.lstNumPreguntas" status="rowstatus">
  		<table style="border:solid 1px #ccc; width:750px;margin:5px;"  >
  		<tr><td>
  		<table>
  		<tr>
  			<td style="width:15px; color:#f00; font-style:italic; font-weight:bold; font-size:14px;" align="center">
    		<s:property value="codigo"/>
    		</td>
    		<td>
    		<table style="background-color:#E8E8E8;width:720px; ">
    			<tr><td>
		    		<span class="textoDentroPanel">Pregunta :</span>
		    		</td>
	    			<td style="width:620px; text-align:left; "><s:select
							list="obtenerDatosPreguntas.lstListaPreguntas" 
							listKey = "codigo"
							listValue = "descripcion"
							name="lstPreguntasType.codPregunta" 
							value=""
							cssClass="dropdowns"
							cssStyle="width:455px;"/>    			
    			</td></tr>
    			<tr><td>
		    		<span class="textoDentroPanel" >Respuesta :</span></td>
		    		<td style="width:620px; text-align:left; "><s:textfield cssStyle="width:450px;" cssClass="campotexto" name="lstPreguntasTypeRes.respuesta" value=""></s:textfield>    			
    			</td></tr>

    		</table>
    		</td>
  		</tr>
  		</table>
  		</td></tr>
  		</table>
  		</s:iterator>
	
	</td>
  </tr>
</table>
<div style="clear:both;"></div> 
<table width="100%">
  <tr>
    <td width="16%">&nbsp;</td>
    <td width="16%">&nbsp;</td>
    <td width="16%"><input name="grabar" type="submit" value="Grabar" class="btWide"/></td>
    <td width="16%"><s:submit action="cancelarIngresoPreguntasSecretas" onClick="salir(window.event);" value="Cancelar" cssClass="btWide"/></td>
    <td width="16%">&nbsp;</td>
	<td width="16%">&nbsp;</td>
  </tr>
</table>
<br />
</s:form>               
	
	</div>