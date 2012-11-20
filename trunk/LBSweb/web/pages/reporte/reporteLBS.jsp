<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@taglib uri="http://displaytag.sf.net" prefix="displayTag" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<bean:define id="listaResultado" name="ConsultaForm" property="resultadoReporte.listaResultadoDetalle" type="java.util.ArrayList"/>
<%
if(listaResultado==null) listaResultado=new java.util.ArrayList();
 int size=listaResultado.size();
 %>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
    function doAction(action) {
      
        if (action=="imprimir") {
            window.open('<%=request.getContextPath()%>/ConsultaHistorica.do?method=imprimir', '', 'width=700, height=520, toolbar=0, scrollbars=1');     
        }
        
        if (action=="excel") {
        	testwindow = window.open( '<%=request.getContextPath()%>/pages/reporte/reporteLBSExcel.jsp','ReporteExcel', 'width=500, height=150, location=no, menubar=no, titlebar=no, toolbar=no');
        	testwindow.moveTo(400, 400);
        }
        
    }

    function nueva(){
        
    	  f = document.ConsultaForm;
          f.method.value="inicio";
          f.submit();
    }
    
   </script>
</head>
<body topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">
<html:form action="ConsultaHistorica" method="post" >
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
             <td align='center' class="tituloSecundario">Del : <fmt:formatDate value="${ConsultaForm.resultadoReporte.fechaInicio}" pattern="dd/MM/yyy HH:mm:ss" /> - Al : <fmt:formatDate value="${ConsultaForm.resultadoReporte.fechaFin}" pattern="dd/MM/yyy HH:mm:ss" /> </td>
        </tr>
    </table>
    <table width="100%" id="wb">
    <tr><td>       
           <displayTag:table messageEmpty="No existen registros" name="listaResultado" width="100%" scope="page" requestURI="ConsultaHistorica.do?method=siguienteReporte" pagesize="10" id="res" >            
                <displayTag:column width="20%" title="Mobile Destino" property="mobileDestino" sortable="false" />
                <displayTag:column width="10%" title="Fecha" property="fechaRegistro" sortable="false" />
                <displayTag:column width="10%" title="Hora" property="horaRegistro" sortable="false" />                
                <displayTag:column width="40%" title="Resultado" property="resultado" sortable="false" />
               <displayTag:column width="20%" title="Tipo Consulta" property="codTipoConsulta" sortable="false" />
                <displayTag:caption>
                        <displayTag:headerdisplay title="" />
                </displayTag:caption>
                <displayTag:footer>
                        <displayTag:footerdisplay />
                </displayTag:footer>
          </displayTag:table>       
    </td>
    </tr>
    <logic:notEmpty name="ConsultaForm" property="resultadoReporte.listaResultadoDetalle">
    <tr class='texto1'> 
      <td>Total registros: <%=size %> </td>
    </tr>
    </logic:notEmpty>
    </table>   
    <c:if test="${ !empty listaResultado }">
    <table align="center" width="85%">
        <tr align="center">
            <td  align='center'><input name="button1" type="button" class="botonDialogo"  value="Imprimir" onClick="doAction('imprimir')">&nbsp;&nbsp;&nbsp;
            </td>
             <td  align='center'><input name="button2" type="button" class="botonDialogo"  value="Excel" onClick="doAction('excel')">&nbsp;&nbsp;&nbsp;
            </td>
            <td  align='center'><input name="button3" type="button" class="botonDialogo" value="Consultar" onclick="nueva()"></td>
         </tr>
   </table>
    </c:if>
   </html:form>
</body>
</html:html>
