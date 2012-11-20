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
function salir(e){
		
			if (e.preventDefault) {
	            e.preventDefault();
	        }else{
	            e.returnValue = false;
	        }

		document.location.href="<%=request.getContextPath()%>/j_spring_security_logout";

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
 <script type="text/javascript" src="../js/jquery.paginarTabla-1.0.js"></script>
         <div class="tablaPlomo" style="width:890px; margin:0 auto; border:solid 1px #B2B2B2;">
           <div align="center">
                                                                                                 <div class="detalleTitulo">
                                                                                       <s:form action="/jsp/excecuteRegistrarOlvidoClave">
                                                       Registrar Olvido Clave
                                                 </div><br>

               </div>

                <table width="100%">
 <tr>
   <td>
       <fieldset width="99%" style="border-color:#FF0000">
       <div><table class="textofuerapanel">
                 <tr align="left"><td>
          <p><b><s:property value="mensajesSeguridad.mensaje1"/></b> <s:property value="mensajesSeguridad.mensaje2"/></p>
          </td></tr>
                 </table>
          </div>
       </fieldset>
       </td>
 </tr>
</table>
<table width="100%" border="0">
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
<table  align="left" cellpadding="4" cellspacing="0">
 <tr>
   <td class="textoDentroPanel">Tipo de Documento :</td>
   <td>
    <s:select
                                               list="lstConsultaDatosMaestroTIPDIDE"
                                               id="tipo_documento"
                                               listKey = "valorDatoMaestro1"
                                               listValue = "valorDatoMaestro2"
                                               name="validarPreguntasSecretas.tipoDocumento"
                                               value=""
                                               cssClass="dropdowns"
                                               cssStyle="width:216px;background:#ffffff; font-size:10px;"
                                               onchange="cambioTipo();"/>
   </td>
 </tr>
 <tr>
   <td  class="textoDentroPanel">Nro de Documento :</td>
   <td><s:textfield id="txt_numDocumento" size="30" cssClass="campotexto" name="validarPreguntasSecretas.nroDocumento"></s:textfield></td>
 </tr>
 <tr>
   <td  class="textoDentroPanel">Email :</td>
   <td><s:textfield id="txt_correoElectronico" size="30" cssClass="campotexto" name="validarPreguntasSecretas.email"></s:textfield></td>
 </tr>
</table>
<div style="clear:both;"></div>
<table width="100%" align="left">
 <tr>
   <td class="menuSuperior" style="text-align:left;color:#7F6666;font-weight:bold;">Responda la Pregunta.</td>
 </tr>
</table>
<div style="clear:both;"></div>
<table width="100%" align="left">
 <tr>
   <td style="text-align:left;">
<table width="100%" border=0 bordercolor="#999999">
 <s:iterator value="preguntasAleatorias.lstPreguntas" status="rowstatus">
               <table style="border:solid 1px #ccc; width:750px;margin:5px;"  >
               <tr><td>
               <table>
               <tr>
                       <td style="width:15px; color:#f00; font-style:italic; font-weight:bold; font-size:14px;" align="center">
               <s:property   value="numPregunta"/>
               </td>
               <td>
               <table style="background-color:#E8E8E8;width:720px; ">
                       <tr><td>
                               <span class="textoDentroPanel">Pregunta :</span>
                               </td>
                               <td style="width:620px; text-align:left; ">    <s:select
                                               list="obtenerDatosPreguntas.lstListaPreguntas"
                                               listKey = "codigo"
                                               listValue = "descripcion"
                                               name="lstPreguntas.codPregunta"
                                               value=""
                                               cssClass="dropdowns"
                                               cssStyle="width:455px;background:#ffffff; font-size:10px;"/>
                       </td></tr>
                       <tr><td>
                               <span class="textoDentroPanel" >Respuesta :</span></td>
                               <td style="width:620px; text-align:left; ">
                               <s:textfield size="70" cssClass="campotexto" name="lstPreguntas.respuesta" value=""></s:textfield>
                       </td></tr>

               </table>
               </td>
               </tr>
               </table>
               </td></tr>
               </table>

 </s:iterator>
</table>
</td>
 </tr>
</table>
<div style="clear:both;"></div>
<table width="100%">
 <tr>
   <td width="16%">&nbsp;</td>
   <td width="16%">&nbsp;</td>
   <td width="16%"><input name="grabar" type="submit" value="Grabar" class="btWide"/></td>
   <td width="16%"><input id="target" name="cancelar"  type="button" value="Cancelar" class="btWide"/></td>
   <td width="16%">&nbsp;</td>
       <td width="16%">&nbsp;</td>
 </tr>
</table>
</s:form>
</div>