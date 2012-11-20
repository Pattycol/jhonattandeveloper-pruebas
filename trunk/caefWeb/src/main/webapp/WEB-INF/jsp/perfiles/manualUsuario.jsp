<%@ taglib prefix="s" uri="/struts-tags"%>
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
</script>

<div >
	  
	    <div align="center">
						  						  <div class="detalleTitulo">
						  	Manual del Cliente y Condiciones de Uso de OSIPTEL
						  </div><br>
						  
						  <table width="96%" border="1" cellpadding="0" cellspacing="0" class="estilotabla" style="border-collapse:collapse">         
                <TR align="center">
                    <td align="center" bgcolor="#f4f9fa" class="fondoPlomo">
                        <table width="100%"  align="center" cellpadding="4" cellspacing="0">
                            <tr><td height="7"></td></tr>
                            <tr>
                                <td height="8" class="textoDentroPanel">
                                    <s:property value="mensajesSeguridad.mensaje1"/>
                                    <br>
                                    <br>
                                    <s:property value="mensajesSeguridad.mensaje2"/>
                                    <br>
                                    <br>
                                    <P align=left><A href="javascript:openPDF();">Manual del Cliente.</a></P>
                                    <br>
                                    <P align=left><A href="javascript:openPDF2();">Cartilla informativa de las Condiciones de Uso de OSIPTEL. </a></P>
                                    <br>
                                    
                                    <P align=right class="verdana10"><img style="cursor: hand;" onClick="javascript:print();" name="imageField"
                                        src='/caefWeb/img/b_print.png' /> <a href="javascript:print();">Imprimir</a>
                                    </P>
                                </td>
                            </tr>
                        
                            
                            <tr><td height="10"></td></tr>
                        </table><!-- 98 -->
                    </td>
                </TR>  
            </table><!-- 96 -->
			                
        </div>
	</div>