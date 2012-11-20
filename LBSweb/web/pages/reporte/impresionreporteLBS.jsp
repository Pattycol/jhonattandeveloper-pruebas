<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

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
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
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
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
     function cargar() {
        if (confirm("Esta seguro que desea imprimir el Reporte ")) {
                window.print();
        }
    }
    </script>
</head>

<body onload="javascript:cargar()">
<html:form action="Consulta" method="post" >
    <input type="hidden" name="method" >
    <table  align="center" width="100%">
        <tr>
             <td align='center' class="tituloPrincipal">Reporte de Consultas LBS</td>
        </tr>
        <tr>
             <td align='center' class="tituloSecundario">Numeros Consultados : <logic:iterate name="ConsultaForm" id="lstMob" property="listaMobiles" >
                                                                                    <bean:write name="lstMob" property="numero"/>
                                                                               </logic:iterate> 
             </td>
        </tr>
        <tr>
             <td align='center' class="tituloSecundario">Del : <bean:write name="ConsultaForm" property="resultadoReporte.fechaInicio" /> - Al : <bean:write name="ConsultaForm" property="resultadoReporte.fechaFin" /> </td>
        </tr>
    </table>
     <br>
    <table border="1" align="center" class='tablaReporte' cellspacing='0'>
        <tr >
           <th width="12" class='trReporteCab'>Mobile Destino</th>
           <th width="10%" class='trReporteCab'>Fecha</th>
           <th width="10%" class='trReporteCab'>Hora</th>
           <th width="50%" class='trReporteCab'>Resultado</th>
           <th width="18%" class='trReporteCab'>Tipo Consulta</th>
        </tr>
         <c:if test="${ !empty listaResultado }">
	     <c:forEach  items="${listaResultado}" var="reporte" varStatus="status" begin="0" end="${size-1}">
               <tr>
                   <td width="12" class='trReporte'><c:out value="${reporte.mobileDestino}"/></td>
                   <td width="10%" class='trReporte'><c:out value="${reporte.fechaRegistro}"/></td>
                   <td width="10%" class='trReporte'><c:out value="${reporte.horaRegistro}"/></td>
                   <td width="50%" class='trReporte'><c:out value="${reporte.resultado}"/></td>
                   <td width="18%" class='trReporte'><c:out value="${reporte.codTipoConsulta}"/></td>
                </tr>
	     </c:forEach>  
         </c:if>
    </table>
    <br>
   </html:form>
</body>
</html:html>
