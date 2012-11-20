<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />
 <script type="text/javascript" src="../js/jquery.paginarTabla-1.0.js"></script>
	  <div class="tablaPlomo">
	    <div align="center">
						  						  <div class="detalleTitulo">
						  					 <s:form action="executeActualizarPreguntasSecretas">
						  	Actualizar de Preguntas Secretas
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
	<div><table class="textofuerapanel">
		  <tr align="left"><td>
           <p><b><s:property value="mensajesSeguridad.mensaje1"/></b><s:property value="mensajesSeguridad.mensaje2"/><s:property value="mensajesSeguridad.mensaje3"/><s:property value="mensajesSeguridad.mensaje4"/></p>
           </td></tr>
		  </table>
           </div>
	</fieldset>
	</td>
  </tr>
</table>
<br />
<table width="100%">
  <tr>
    <td class="menuSuperior"><s:property value="mensajesSeguridad.mensaje8"/></td>
  </tr>
</table>
<div class="tablaPlomo">
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
						name="codigo" 
						value="codigo"
						cssClass="dropdowns"
						cssStyle="width:455px;background:#E6E6E6; font-size:10px;"
						disabled="true"/>
    		</td>
  		</tr>
  		<tr>
    		<td width="9%" align="center" class="textoDentroPanel">Respuesta :</td>
    		<td disabled="disabled" style="font-size: 8pt;" class="campotexto"><s:property value="descripcion"/></td>
  		</tr>
  		</s:iterator>
	</table>
	</td>
  </tr>
</table>
</div>
<div class="tablaPlomo">
<table width="100%">
  <tr>
    <td>
	<table width="100%" border=1 bordercolor="#999999">
  		<s:iterator value="obtenerDatosPreguntasNuevas.lstNumPreguntas" status="rowstatus">
  		<tr>
    		<td rowspan="2" align="center">
    		<s:property value="codigo"/>
    		</td>
    		<td width="9%" align="center" class="textoDentroPanel">Pregunta :</td>
    		<td>
    			<s:select
						list="obtenerDatosPreguntasNuevas.lstListaPreguntas" 
						listKey = "codigo"
						listValue = "descripcion"
						name="lstPreguntasType.codPregunta" 
						value=""
						cssClass="dropdowns"
						cssStyle="width:455px;"/>
    		</td>
  		</tr>
  		<tr>
    		<td width="9%" align="center" class="textoDentroPanel">Respuesta :</td>
    		<td><s:textfield size="70" cssStyle="background:#FFFFFF;" name="lstPreguntasTypeRes.respuesta" value=""></s:textfield></td>
  		</tr>
  		</s:iterator>
	</table>
	</td>
  </tr>
</table>
</div>
<br />
<div class="tablaPlomo">
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
</div>
  </s:form>            
	
	</div>