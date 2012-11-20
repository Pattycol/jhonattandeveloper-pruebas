<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />
<script type="text/javascript" src="../js/jquery.paginarTabla-1.0.js"></script>
	  <div class="tablaPlomo">
	    <div align="center">
						  						  <div class="detalleTitulo">
						  					<s:form action="/jsp/executeEliminarPreguntasSecretas">
						  	Eliminar de Preguntas Secretas
						  </div><br>
			                
                </div>
                 <table>
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
                 <table width="100%">
  <tr>
    <td>
	<fieldset width="99%" style="border-color:#FF0000">
	<div>
	<table class="textofuerapanel">
		  <tr align="left"><td>
           <p><b>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="mensajesSeguridad.mensaje1"/></b><s:property value="mensajesSeguridad.mensaje2"/><b><s:property value="mensajesSeguridad.mensaje3"/></b><s:property value="mensajesSeguridad.mensaje4"/></p>
           </td></tr>
		  </table>
           </div>
	</fieldset>
	</td>
  </tr>
</table>

<table width="100%">
  <tr>
    <td class="menuSuperior"><s:property value="mensajesSeguridad.mensaje5"/>  <s:text name="cantidadPregunta"/><s:property value="mensajesSeguridad.mensaje6"/></td>
  </tr>
</table>
<table width="100%">
  <tr>
    <td>
<table width="100%">
  <tr>
    <td>
	<table width="100%" border=1 bordercolor="#999999">

	<s:iterator value="obtenerDatosUsuario.lstListaRespuestasType" status="rowstatus">
  		<tr>
    		<td rowspan="2" align="center">
    		<s:property value="contador"/>
    		</td>
    		<td width="9%" align="center" class="textoDentroPanel">Pregunta :</td>
    		<td>
    			<s:select
						list="obtenerDatosPreguntas.lstListaPreguntas" 
						listKey = "codigo"
						listValue = "descripcion"
						name="lstPreguntasType.codPregunta" 
						value="codigo"
						disabled="true"
						cssClass="dropdowns"
						cssStyle="width:455px;background:#E6E6E6; font-size:10px;"
						/>
    		</td> 
    		<td rowspan="2"><s:checkbox name="checkboxes[%{#rowstatus.index}]" fieldValue="true"/></td>
  		</tr>
  		<tr>
    		<td width="9%" align="center" class="textoDentroPanel">Respuesta :</td>
    		<td disabled="disabled" style="font-size: 8pt;"><s:textfield size="70" disabled="true" cssClass="campotexto" value="*****" name="lstPreguntasTypeRes.respuesta"></s:textfield></td>
  		</tr>
  	</s:iterator>
	</table>
	</td>
  </tr>
</table>
</td>
  </tr>
</table>
<br />
<table width="100%">
  <tr>
    <td width="16%">&nbsp;</td>
    <td width="16%">&nbsp;</td>
    <td width="16%"><input name="grabar" type="submit" value="Grabar" class="btWide"/></td>
    <td width="16%"><input name="cancelar" type="button" value="Cancelar" class="btWide"/></td>
    <td width="16%">&nbsp;</td>
	<td width="16%">&nbsp;</td>
  </tr>
</table>
</s:form>                        
	
	</div>