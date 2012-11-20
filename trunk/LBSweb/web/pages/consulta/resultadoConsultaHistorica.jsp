<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@taglib uri="http://displaytag.sf.net" prefix="displayTag" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<bean:define id="listaResultado" name="ConsultaForm" property="resultadoConsulta.listaResultadoDetalle" type="java.util.ArrayList"/>
<%
if(listaResultado==null) listaResultado=new java.util.ArrayList();
 int size=listaResultado.size();
String inicio=(String)request.getAttribute(Constants.REQUEST_ACTIVA_GRAFICO);
 %>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script language="javascript" src="include/valida.js" type="text/javascript"></script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript">
function cargaGrafico(){
    goFrameDer('ConsultaHistorica.do?method=mapa');
}
<%if(inicio!=null){%>
cargaGrafico();
<%}%>
</script>
</head>

<body >
<html:form action='ConsultaHistorica' method='post'>
<input type="hidden" name="method" >
<table border="0" width="100%">
  <tr>
    <td colspan="2" class="tituloPrincipal"> Consulta LBS Histórica</td>    
  </tr>
  <tr>
    <td width="18%">&nbsp;</td>
    <td width="21%">&nbsp;</td>    
  </tr>
  <tr>
    <td colspan="2" class="menu_cabecera">Resultado de la Consulta</td>
    </tr>
</table>

<br>
<table width="100%" id="wb">
<tr><td>   
       <displayTag:table messageEmpty="No existen registros" name="listaResultado" width="100%" scope="page" requestURI="ConsultaHistorica.do?method=siguiente" pagesize="5" id="res" >
            <displayTag:column width="15%" title="Fecha" property="fechaConsulta" sortable="false" />
            <displayTag:column width="15%" title="Mobile Destino" property="mobileDestino" sortable="true" />
            <displayTag:column width="60%" title="Resultado" property="resultado" sortable="false" />
            <displayTag:column width="10%" title="Tipo" property="tipoConsulta" sortable="false" />
            <displayTag:caption>
                    <displayTag:headerdisplay title="" />
            </displayTag:caption>
            <displayTag:footer>
                    <displayTag:footerdisplay />
            </displayTag:footer>
      </displayTag:table>
</td>
</tr>
<logic:notEmpty name="ConsultaForm" property="resultadoConsulta.listaResultadoDetalle">
<tr class='texto1'> 
  <td>Total registros: <%=size %> </td>
</tr>
</logic:notEmpty>
</table>
<table align="center">
 <tr>   
     <td align="center" >
            <input name="button1" type="button" class="botonDialogo" value="Regresar" onclick="javascript:goMethod('regresar')">
     </td>
 </tr>
</table>
</html:form>
</body>
</html:html>