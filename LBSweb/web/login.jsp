<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ page import="com.stconsulting.common.util.*" %>
<%
  String cambiar=(String)request.getAttribute(Constants.ACCION_CAMBIAR_PASSWORD);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html:html>
<head>
<title>Claro Empresas ::: Sistema de Consulta Web LBS</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<style>
.rayita { background-color:#C5C5C5 }
.titsistema { font-family:arial,verdana; font-size:18px; font-weight:bold; color:#FF0000; line-height:26px }
.text { font-family:arial,verdana; font-size: 12px; }
input { font-family:arial,verdana; font-size:11px; }
</style>
<script src="include/jquery-1.6.2.min.js" type="text/javascript"></script>
<script src="include/jquery.backgroundPosition.js"></script>
<script language="javascript" src="include/valida.js" type="text/javascript"></script>
<script language="javascript" src="include/general.js" type="text/javascript"></script>
<script language="Javascript">
function goto(){
f = document.forms[0];
if (f.mobile.value == '')
    {alert('Debe ingresar usuario'); f.mobile.focus();}  
else if (f.password.value == '')
    {alert('Debe ingresar su contraseña'); f.password.focus();}
else {f.method.value='cargar'; f.submit(); }
}

function inicializa(){
//maximize();
f = document.forms[0];
f.password.value = '';
f.mobile.focus();
}
function abreVentanaPassword(){
   //var atributos="toolbar=no,Location=no,directories=no,channelmode=no,menubar=no,status=yes,scrollbars=no,resizable=no,width=300,height=250,fullscreen=no,top=100,left=200";
    var vURL = "Login.do?method=inicioCambiarPassword";
    newWin = window.open(vURL, "LBS", "toolbar=no, left=250, top=100, width=300, height=250,directories=no,status=no,scrollbars=auto,resizable=0,menubar=no");
        
}

function abreVentanaRecuperarPassword(){
	   //var atributos="toolbar=no,Location=no,directories=no,channelmode=no,menubar=no,status=yes,scrollbars=no,resizable=no,width=300,height=250,fullscreen=no,top=100,left=200";
	    var vURL = "Login.do?method=inicioRecuperarPassword";
	    newWin = window.open(vURL, "LBS", "toolbar=no, left=250, top=100, width=300, height=250,directories=no,status=no,scrollbars=auto,resizable=0,menubar=no");
	        
	}

function abrirAyuda(){
    var vURL = "pages/ayudaInicio.html";
    newWin = window.open(vURL, "LBS", "toolbar=no, left=380, top=250, width=375, height=335,directories=no,status=no,scrollbars=auto,resizable=0,menubar=no");
    
}
</script>
<link href="include/estilos.css" rel="stylesheet" type="text/css">
</head>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<html:form action="Login" method='post' >

<input type="hidden" name="method">  
<table width="100%" height="99%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="30%" rowspan="9" valign="top">&nbsp;</td>
    <td rowspan="8" width=1 class=rayita><img border=0 height=1 width=1></td>
    <td height="70" colspan="3" bgcolor="#F2F2F2">&nbsp;</td>
    <td rowspan="5" width=1 class=rayita><img border=0 height=1 width=1></td>
    <td width="80%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" class=rayita><img border=0 height=1 width=1></td>
    <td class=rayita><img border=0 height=1 width=1></td>
  </tr>
  <tr>
    <td height="20" colspan="3"><img src="image/ele-hmzul01.gif" width="265" height="29" border="0"></td>
    <td bgcolor="#E9E9E9">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3" class="rayita" height=1><img border=0 width=1 height=1></td>
    <td class="rayita" height=1><img border=0 width=2 height=1></td>
  </tr>
  <tr>
    <td height="250" colspan="3"><img src="image/fnd-home.jpg" width="265" height="251" border="0"></td>
    <td style="padding:10px"><table width="100%" border="0" cellspacing="0" cellpadding="10">
      <tr>
        <td class=titsistema>Bienvenido <br>
          al Sistema de Localizaci&oacute;n</td>
        <td><img src="image/ele-hmlogo.gif" width="198" height="99" border="0"></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="5" width=1 class="rayita"><img border=0 width=1 height=1></td>
  </tr>
  <tr>
    <td valign="top" bgcolor="#E0E0E0"><img border=0 width=215 height=1></td>
    <td width=1 class="rayita"><img border=0 width=1 height=1></td>
    <td width="48" height="28"><img src="image/ele-hmzul02.gif" width="49" height="29"></td>
    <td width=1 class="rayita"><img border=0 width=1 height=1></td>
    <td bgcolor="#F5F5F5">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5" width=1 class="rayita"><img border=0 width=1 height=1></td>
  </tr>
  <tr>
    <td><img border=0 width=1 height=100></td>
    <td>&nbsp;</td>
    <td width=1 class="rayita"><img border=0 width=1 height=160></td>
    <td bgcolor="#F5F5F5">&nbsp;</td>
    <td width=1 class="rayita"><img border=0 width=1 height=1></td>
    <td valign="top" style="padding:20px"><table border="0" cellspacing="0" cellpadding="0" class=text>
      <tr>
        <td width="90" height="26">N&uacute;mero:</td>
        <td width="160"><html:text property="mobile" maxlength='11' onkeypress="return esTeclaNumeroSinComa(event)"/></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>Clave:</td>
        <td><html:password property="password" maxlength='8' onkeypress="if (event.keyCode == '13') { goto(); }"/></td>
        <td><input onClick="goto()" type="button" name="ingreso" value="Ingresar" style="background-color:#FF0000;color:#FFFFFF;font-weight:bold;cursor:hand; height:22px;width:60px;border: outset #D6D6D6 2px;">
        &nbsp;&nbsp;&nbsp;<a href="javascript:abrirAyuda()" class="vinculomenu" title='Si eres un Usuario nuevo, ingresa a esta opción'>Ayuda</a></td>
      </tr>
      <tr>
      <td>&nbsp;</td>
      </tr>
        <tr>
		
		<td  style="width: 137px;"><a href="javascript:abreVentanaRecuperarPassword()" class="vinculomenu">Recuperaci&oacute;n de Clave</a></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
</tr>
      <tr>
      <td colspan="3"><html:errors/></td>
      </tr>
    </table></td>
  </tr>  
</table>  

</html:form>
</body>
<script language="Javascript">
inicializa();
<%if(cambiar!=null){%>
    abreVentanaPassword();
<%}%>
</script>
</html:html>