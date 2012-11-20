<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />

<div class="tablaPlomo">
	  
	    <div align="center">
						  						  <div class="detalleTitulo">
						  						  						  <s:form action="/jsp/executeAvisoLegalYAcuerdos">
						  	Aviso Legal y Acuerdos
						  </div><br>
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

						  <table width="96%" border="1" cellpadding="0" cellspacing="0" class="estilotabla" style="border-collapse:collapse">         
                <TR align="center">
                    <td align="center" bgcolor="#f4f9fa" class="fondoPlomo">
                        <table width="100%"  align="center" cellpadding="4" cellspacing="0">
                            <tr><td height="7"></td></tr>
                            <tr>
                                <td height="8" class="textoDentroPanel">
                                    <P align=left><s:property value="mensajesSeguridad.mensaje1"/><s:property value="mensajesSeguridad.mensaje2"/></P>
                                    <br>
                                    <P align=left><s:property value="mensajesSeguridad.mensaje3"/></P>
                                    <br>
                                    <P align=left><s:property value="mensajesSeguridad.mensaje4"/></P>
                                    <br>
                                    <P align=left><s:property value="mensajesSeguridad.mensaje5"/></P>
                                    <br>
                                    <P align=left><s:property value="mensajesSeguridad.mensaje6"/></P>
                                    <br>
                                    <P align=left><A href="javascript:openPDF();"><s:property value="mensajesSeguridad.mensaje10"/></a></P>
                                    <br>
                                    <P align=left><A href="javascript:openPDF();"><s:property value="mensajesSeguridad.mensaje11"/></a></P>
                                    <br>
                                    <P align=left><A href="javascript:openPDF2();"><s:property value="mensajesSeguridad.mensaje12"/></a></P>
                                    <br>
                                    <P align=left><A href="javascript:openPDF2();"><s:property value="mensajesSeguridad.mensaje13"/></a></P>
                                    <br>
                                    <P align=right class="verdana10"><img style="cursor: hand;" onClick="javascript:print();" name="imageField"
                                        src='/caefWeb/img/b_print.png' /> <a href="javascript:print();">Imprimir</a>
                                    </P>
                                    <P align=right class="verdana10">
                                    <input name="buttonConfirmar" value="Confirmar" type="submit" class="btWide">
                                    </P>
                                    <P align=right class="verdana10">
                                    <s:submit action="cancelarAvisoLegalYAcuerdos" value="Cancelar" cssClass="btWide"/>
                                    </P>
                                </td>
                            </tr>
                        
                            
                            <tr><td height="10"></td></tr>
                        </table><!-- 98 -->
                    </td>
                </TR>  
            </table><!-- 96 -->
			   </s:form>             
        </div>
	</div>