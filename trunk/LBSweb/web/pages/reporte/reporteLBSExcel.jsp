<% 
response.setContentType("application/vnd.ms-excel");
response.setHeader("Cache-Control", "max-age=0");
response.setHeader("Content-Disposition","inline; filename=\"ReporteLBSHistorico.xls\"");
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://displaytag.sf.net" prefix="displayTag" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<bean:define id="listaResultado" name="ConsultaForm" property="resultadoReporte.listaResultadoDetalle" type="java.util.ArrayList"/>
<%
if(listaResultado==null) listaResultado=new java.util.ArrayList();
 int size=listaResultado.size();
 pageContext.setAttribute("size", Integer.toString(size));
 %>
<html:html>
<head>
<title>Reporte Consulta Historico LBS</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script language="javascript" src="include/general.js" type="text/javascript"></script>
</head>

<body>
<html:form action="Consulta" method="post" >
    <input type="hidden" name="method" >
    <table  align="center" width="100%">
        <tr>
             <td align='center' >Reporte de Consultas LBS</td>
        </tr>
        <tr>
             <td align='center' >Del : <bean:write name="ConsultaForm" property="resultadoReporte.fechaInicio" /> - Al : <bean:write name="ConsultaForm" property="resultadoReporte.fechaFin" /> </td>
        </tr>
    </table>
     <br>
    <table border="1" align="center" cellspacing='0'>
        <tr >
           <th width="12" >Mobile Destino</th>
           <th width="10%" >Fecha</th>
           <th width="10%" >Hora</th>
           <th width="50%" >Resultado</th>
           <th width="18%" >Tipo Consulta</th>
        </tr>
         <c:if test="${ !empty listaResultado }">
	     <c:forEach  items="${listaResultado}" var="reporte" varStatus="status" begin="0" end="${size-1}">
               <tr>
                   <td width="12" ><c:out value="${reporte.mobileDestino}"/></td>
                   <td width="10%" ><c:out value="${reporte.fechaRegistro}"/></td>
                   <td width="10%" ><c:out value="${reporte.horaRegistro}"/></td>
                   <td width="50%" ><c:out value="${reporte.resultado}"/></td>
                   <td width="18%" ><c:out value="${reporte.codTipoConsulta}"/></td>
                </tr>
	     </c:forEach>  
         </c:if>
    </table>
    <br>
   </html:form>
</body>
</html:html>






