<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.lbsweb.seguridad.bean.Usuario" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.stconsulting.common.util.Converter" %>
<%
Usuario usuario=(Usuario)session.getAttribute(Constants.USUARIO_LOGUEADO);
String consultas_web_principal=(String)session.getAttribute(Constants.CONSULTAS_WEB_FALTANTES);
int consultas_web = Integer.parseInt(consultas_web_principal);
%>
<html:html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
<style type="text/css">
a{
display: block;
text-decoration: none;
color: red;
padding: 5px;
}
 
ul{
width: 500px;
height: 28px;
}
 
ul,li{
list-style: none;
margin:0;
padding:0;
}
 
#nav{
font-family: 'Century Gothic', sans-serif;
}
 
#nav li{
float:left;
position: relative;
width: 125px;
font-size: 14px;
font-variant: small-caps;
}
 
.submenu{
display: none;
position: absolute;
left: -1px;
border:none;
height: auto;
background: none;
}
 
#nav .submenu li{
float: none;
position: static;
margin: 0;
font-size: 11px;
font-variant: normal;
border: 1px solid red;
border-top: none;
width: 124px;
}
 
#nav .submenu li a{
color: #FFFFFF;
background: white url('image/menu.png') repeat-x 0 0;
width: 114px;
height: 16px;
}
</style>
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script src="include/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="include/jquery-backgroundPosition.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
function abreVentanaPassword(){
	   //var atributos="toolbar=no,Location=no,directories=no,channelmode=no,menubar=no,status=yes,scrollbars=no,resizable=no,width=300,height=250,fullscreen=no,top=100,left=200";
	    var vURL = "Login.do?method=inicioCambiarPassword";
	    newWin = window.open(vURL, "LBS", "toolbar=no, left=250, top=100, width=300, height=250,directories=no,status=no,scrollbars=auto,resizable=0,menubar=no");
	        
}
function xxx(){

}
/*function obetenerConsultasWeb{

    new Ajax.Request('../declaracion?flgaction=39',
      {
    	method: 'POST',
    	parameters: {h_cod: codCat},
    	onSuccess: function(t) {	
    		    			
    	}},
    	onFailure: function(t){ 
    	alert("Error inesperado.");
    	}
    );
	
}*/

function salir(url){

/*
if (confirm("Esta seguro que desea salir del sistema?"))
    top.document.location.href = url;
    */
}
/*#menu*/
$(document).ready(function() {
	$('#nav>li').hover(
		function(){
		$('.submenu',this).stop(true,true).slideDown('fast');
		},
		function(){
		$('.submenu',this).slideUp('fast');
		}
	);
 
	$('.submenu li a').css( {backgroundPosition: "0px 0px"} ).hover(
		function(){
		$(this).stop().animate({backgroundPosition: "(0px -99px)"}, 250);
		},
		function(){
		$(this).stop().animate({backgroundPosition: "(0px 0px)"}, 250);
		}
	);
	
	$.ajaxSetup ({
	    // Disable caching of AJAX responses */
	    cache: false
	});
	
		var refreshId = setInterval(
							function() {
			
							    $('#recargado').load('Login.do?method=obtenerConsultasWebRestantes');
							     
							}, 5000);		
		
	

		
});

</script>
</head>
<body topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">
<html:form  action='Login' method='post' >
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td bgcolor="#88C0F0" background="image/fnd-cielo.gif" height="60" colspan="2">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="padding-left:16px"><img src="image/ico-clarologo.gif" width="145" height="60" border="0"></td>
        <td align="right"><img src="image/ele-fotheader.gif" width="215" height="60" border="0"></td>
        </tr>
    </table></td>
  </tr>
  <tr><td colspan="2" bgcolor="#88C0F0"><img border=0 width=1 height=1></td></tr>
  <tr><td colspan="2" bgcolor="#FFFFFF"><img border=0 width=1 height=8></td></tr>
  <tr><td colspan="2" bgcolor="#FF0000"><img border=0 width=1 height=1></td></tr>
  <tr>
  	  <td>
  		<table width="100%" border="0" cellspacing="0" cellpadding="0">      
          <tr>
          	<td width="40%">
        		<ul id="nav">      		
					<li><a href="Consulta.do?method=inicio">Consultar</a></li>
					<li><a href="#">Tarea</a>
						<ul class="submenu">
							<li><a href="ConsultaTarea.do?method=nuevaTarea">Nueva</a></li>
							<li><a href="ConsultaTarea.do?method=inicio">Listar</a></li>
						</ul>
					</li>
					<li><a href="ConsultaHistorica.do?method=inicio">Hist&oacute;rico</a></li>
				</ul> 
            </td>
            <td width="40%" align="center">
           		 <div id="recargado">
           		 <% if(consultas_web > 0 ){ %>
           		 	<font color="#FF0000">Consultas Restantes: <b><%=consultas_web_principal %></b></font><br>
           		 <% }else{ %>
           		 	<font color="#FF0000">Uds. está generando consultas adicionales.Cada consulta adicional a su paquete tiene un costo de S/.0.25 inc. IGV..</font><br>
           		 <% } %>
           		 </div>
           	</td>
            <td width="20%" align="center" class="titnombre">            
            Número: <bean:write scope="session" name="<%=Constants.USUARIO_LOGUEADO%>" property="telefono"/>&nbsp;&nbsp;&nbsp;&nbsp;
             <div><a href="javascript:abreVentanaPassword()" style='color:#FF0000;font-weight:bold' >Cambio de Clave</a></div>
            <a style='color:#FF0000' href="javascript:if (confirm('Está seguro que desea salir del sistema?')){ top.document.location.href ='Login.do?method=finalizarSesion';}">cerrar sesi&oacute;n</a>
            </td>  
          </tr>
        </table></td>
      </tr>
      <tr><td colspan="2" bgcolor="#FF0000"><img border=0 width=1 height=1></td></tr>
  <!--DWLayoutTable-->      
</table>
</html:form>
</body>
</html:html>
