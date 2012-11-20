<%@page import="pe.com.claro.caef.web.util.MENSAJES_CONFIG"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
<!--
	
	function olvidoClave(){
		alert("entre al boton");
		document.forms[0].action="<%=request.getContextPath()%>/jsp/submitOlvidoClave.action";
		document.forms[0].submit();
	}
	
//-->
</script>
<div class="grid_10 bordeDiv" >
	  
	    <div align="center">
		<div class="detalleTitulo">
						  	<%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ1 %>
						  </div><br>
	          <b> <strong><%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ2 %></strong> <br>
        </b>          </div>
        				  </td>
        <s:form action="/jsp/submitOlvidoClave" method="post">						  
		<table width="100%"  align="left" cellpadding="4" cellspacing="0" class="tablaPloma"  >

			<tbody>
			
			<tr>
			 	 <td  width="11%"></td>
				 <td  width="18%" height="2px"></td>
				 <td width="22%"  ></td>
				 <td width="18%"  ></td>
				 <td width="20%"  ></td> <td width="11%"  ></td>
			</tr>
			<tr class="servicos-texto3">
				  <td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<input id="fechaEmision" name="fechaEmision" type="hidden" value="">
					<td style="padding-left: 8px" class="textoDentroPanel">&nbsp;</td>
					<td style="padding-left: 5px"><span class="textoDentroPanel" style="padding-left: 8px"><%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ3 %></span></td>
					<td style="padding-left: 5px"><select id="tipoDocumento" name="tipoDocumento" class="campotexto">
                      <option value="-- Seleccione --">-- Seleccione --</option>
                      <option value="1" selected="selected">DNI</option>
                      <option value="2">PASAPORTE</option>
                      <option value="3">CARNET DE EXTRANJERIA</option>
                    </select></td>
					<td style="padding-left: 5px">&nbsp;</td>
				    <td style="padding-left: 5px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td><span style="padding-left: 5px">
					  <label for="lbl_numDocumento"><%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ4 %></label>
					</span></td>
					<td><input name="txt_numDocumento" type="text" value=""></td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
				<tr>
				  <td></td>
					<td height="8px">&nbsp;</td>
					<td><span style="padding-left: 5px">
					  <s:submit cssClass="btWide" onclick="javascript:olvidoClave();" value="Enviar" />
					</span></td>
					<td><!-- <input name="buttonVolver" value="Cancelar" type="button">  -->
					<s:submit cssClass="btWide" action="olvidoClaveAction" value="Cancelar"/>
					</td>
					<td height="8px">&nbsp;</td><td height="8px">&nbsp;</td>
				</tr>
		</tbody></table>	
		</s:form>
		<div>
	          <b><%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ5 %></b> <%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ6 %> <br>
			  <%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ7 %>
			  <%=MENSAJES_CONFIG.JSP_OLVIDO_CLAVE_MSJ8 %>
        </b>          </div>
        </div>