<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.*" %>
<jsp:useBean id="listaMensajes" scope="request" class="java.util.ArrayList"/>
<jsp:useBean id="errorTask" scope="request" class="java.lang.String"/>
<jsp:useBean id="errorAction" scope="request" class="java.lang.String"/>
<jsp:useBean id="titulo" scope="request" class="java.lang.String"/>
<jsp:useBean id="tipoMsg" scope="request" class="java.lang.String"/>
<html:html>
<HEAD>
	<META name="GENERATOR" content="IBM WebSphere Studio">
	<META http-equiv="Content-Style-Type" content="text/css">
	<link href="include/estilos.css" rel="stylesheet" type="text/css">
        <link href="include/itdg_intranet.css" rel="stylesheet" type="text/css">
	<TITLE>Mensaje de Error</TITLE>
        <% String size = "0";
           pageContext.setAttribute("listaMensajes",listaMensajes);
           if(listaMensajes != null) {
             size = Integer.toString(listaMensajes.size());
           }
            pageContext.setAttribute("size", size);
        %>
	<SCRIPT language="JavaScript">
	function procesar(num) {
		switch (num) {
			case 1:      f = document.forms[0];
                                     f.submit();
				    break;
			default: break;
		}
	}
	
	</SCRIPT>

</HEAD>

<BODY  topmargin="0" leftmargin="0" class="body1">
<BR>
<CENTER>
<table width="60%"  border="0" align="center">
        <tr> 
          <td colspan="2" class="tituloPrincipal"><bean:message bundle="application" key="<%=titulo%>"/></td>
          <td colspan="2">&nbsp;</td>
          <td width="12%">&nbsp;</td>
        </tr>
</table>
<TABLE border="0" width="80%">
	<TBODY>
		<TR>
			<TD>&nbsp;</TD>
		</TR>	
	</TBODY>
</TABLE>
</CENTER>
<P><BR>
     


		<CENTER>
		
		<TABLE class="detailTitle" cellpadding="0" cellspacing="0">
		  <TR>
		    <TD align=center><b>Mensaje</b></TD>
		  </TR>  
		</TABLE>
		<TABLE border="0" cellspacing="0" cellpadding="0" class="generalLayout">
		    <TR><TD>	
			    <TABLE class="innerGeneralLayout" cellspacing="0" cellpadding="0">
				  <TR>
					 <TD width="210"></TD>
					 <TD colspan="2" width="584"><center> 					 
					 <% if (ConstantsMQT2.TIPO_MENSAJE_ERROR.equals(tipoMsg)) { %>
                                          <IMG src="image/img_nook.gif" width="15" height="15">
					 <% } else { %>
                                          <IMG src="image/img_ok.gif" width="15" height="15">
					 <% } %>
					 </center>
					  </TD>
					<TD width="192"></TD>
				</TR>
		          <c:if test="${ !empty listaMensajes }">  
                               <c:forEach  items="${listaMensajes}" var="mensaje" varStatus="status" begin="0" end="${size-1}">
                                <TR>	  	
                                    <TD width="165"></TD>
                                    <TD align=center class="labelPrim" colspan="2" width="650"><center>
                                        
                                          <c:out value="${mensaje.descripcion}"/>
                                      </center> 
				    </TD>
				    
			         
				</tr>
				 </c:forEach> 
		          </c:if>
				 
			</table>
			 
			</TD>
		   </TR>
		  </table> 
		<BR>
		<TABLE  border="0" cellspacing="0" cellpadding="0">
			<TR>
				<TD align="right">
					<INPUT type="button" class="boton" value="Continuar"  onclick="procesar(1);"  ></TD>
			</TR>
		</TABLE>

		
		</CENTER>

<html:form action='<%=errorAction%>' method='post' > 
<input type="hidden" name="method" value="<%= errorTask %>">
</html:form>

</BODY>
</html:html>