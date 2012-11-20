<%@page import="pe.com.claro.caef.web.util.MENSAJES_CONFIG"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri = "http://java.sun.com/jstl/core" prefix ="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- saved from url=(0052)http://www.miclaro.com.pe/caeWeb/loginPivot.htm?null -->
<html><head>
	<!-- lista de css -->
	<link rel="stylesheet" type="text/css" href="<s:url value="/login_files/geral.css"/>" id="geral">
	<link rel="stylesheet" type="text/css" href="<s:url value="/login_files/main.css"/>" id="main">
	<link rel="stylesheet" type="text/css" href="<s:url value="/login_files/pestana.css"/>" id="pestana">
	<link rel="stylesheet" type="text/css" href="<s:url value="/login_files/general.css"/>" id="general">
	<link href="<s:url value="/login_files/login.css"/>" rel="stylesheet" type="text/css" id="login">
	<link href="<s:url value="/login_files/class.css"/>" rel="stylesheet" type="text/css" id="class">
	<link href="<s:url value="/login_files/estilos.css"/>" rel="stylesheet" type="text/css"  id="estilos">
	<!-- lista de js -->
	<!--<script language="JavaScript" src="<s:url value="/login_files/estilos.js"/>"></script>-->
	<script src="<s:url value="/login_files/login_nav.js"/>" type="text/javascript"></script>
	<script src="<s:url value="/login_files/code.js"/>" type="text/javascript"></script>
	<script type="text/javascript" src="<s:url value="/login_files/Dispatcher.js"/>"></script>
	<script language="VBScript" src="<s:url value="/login_files/Dispatcher.vbs"/>"></script>
	<script language="JavaScript" src="<s:url value="/login_files/util.js"/>"> </script>
    	        
	<!-- Titulo -->
	<title>Sistema Mi Claro</title>

<style type="text/css">
<!--
body {
	overflow:hidden;
}
.Estilo1 {color: #FF0000}

-->
</style>
<script language="javascript" type="text/javascript">
/*if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("geral").href= "/css/ie.css";
}
if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("main").href= "/css/ie.css";
} 
if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("pestana").href= "/css/ie.css";
} 
if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("general").href= "/css/ie.css";
}
if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("login").href= "/css/ie.css";
} 
if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("class").href= "/css/ie.css";
} 
if (navigator.appName.indexOf("Microsoft Internet Explorer") !=-1) {
	document.getElementById("estilos").href= "/css/ie.css";
}*/ 
</script>

<script language="javascript" type="text/javascript" src="<s:url value="/login_files/script/mootools-1.2.3-core.js"/>"></script>
<script language="javascript" type="text/javascript" src="<s:url value="/login_files/script/mootools-1.2.3.1-more.js"/>"></script>
<script language="javascript" type="text/javascript" src="<s:url value="/login_files/script/FancyTabs.js"/>"></script>
<script language="javascript" type="text/javascript">
	/* SCRIPT USAGE */
	/* use load as window event instead of domready to ensure that correct dimensions are gathered */
	window.addEvent('domready', function(){
		// first example
		var firstExample = new FancyTabs({
			tabs: '#FC_tabs .tab',
			contentContainer:'FC_content',
			contentElement:'.content',
			tabFx:{
				classSelected : '.FC_selected',
				classDefault: '.FC_default'
			},
			fxStyle: 0, 
			slideEnter: 'right'
		});

	})
</script>

<script language="JavaScript" type="text/JavaScript">

function validarEnteroLogin() {
	if (event.keyCode < 48 || event.keyCode > 57 ) 
		event.returnValue = false;
}

function doConsultar(controller,idL,idP){
	validarLogin('<%=MENSAJES_CONFIG.LOGIN_VALIDA_CORREO_ECTRONICO%>','<%=MENSAJES_CONFIG.LOGIN_VALIDA_NUMERO%>',
				 'Ingrese su clave.','La clave debe tener 6 dígitos.','El número debe tener 9 dígitos.','Formato de número incorrecto.CAE',controller,idL,idP);
}

function doConsultarFijo(controller,idL,idP){
	validarLoginFijo('<%=MENSAJES_CONFIG.LOGIN_VALIDA_CORREO_ECTRONICO%>','<%=MENSAJES_CONFIG.LOGIN_VALIDA_NUMERO%>',
				 '<%=MENSAJES_CONFIG.LOGIN_VALIDA_INGRESO_CLAVE%>','<%=MENSAJES_CONFIG.LOGIN_VALIDA_DIGITOS_CLAVE%>','<%=MENSAJES_CONFIG.LOGIN_VALIDA_DIGITOS_NUMERO%>','<%=MENSAJES_CONFIG.VALIDA_CORREO_ECTRONICO_JSP%>',controller,idL,idP);
}


function validarLogin(mensaje1,mensaje2,mensaje3,mensaje4,mensaje5,mensajeFormat,controller,idL,idP) {
var login = document.getElementById(idL);
var password = document.getElementById(idP);

	if(login.value != '')
	{	
		var patron=/[^0-9]/;
		/*if(patron.exec(login.value)){
			showAlertMessage(mensajeFormat);
			login.focus();
			return false;
		}*/
	
		/*if(login.value.length != 9){
				showAlertMessage(mensaje5);
				login.focus();
				return false;
		}*/
			
		
		if(password.value != ''){
			if(password.value.length >= 6){
					document.form1.j_password.value = password.value;
					document.form1.j_username.value = login.value;	
					document.form1.submit();
				}
			else
				showAlertMessage(mensaje4);
		}else{
			showAlertMessage(mensaje3);
		}
	}else{
		showAlertMessage(mensaje1);
	}	
	
}

function validarLoginFijo(mensaje1,mensaje2,mensaje3,mensaje4,mensaje5,mensajeFormat,controller,idL,idP) {
	var login = document.getElementById(idL);
	login.value=login.value.toLowerCase();
	var password = document.getElementById(idP);

		if(login.value != '')
		{	

			var patronCorreo = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,3})$/;
			if(!patronCorreo.exec(login.value)){
				showAlertMessage(mensajeFormat);
				login.focus();
				return false;
			}
		
			/*if(login.value.length != 9){
					showAlertMessage(mensaje5);
					login.focus();
					return false;
			}*/
				
			
			if(password.value != ''){
				if(password.value.length >= 6){
						document.form1.j_password.value = password.value;
						document.form1.j_username.value = login.value;						
						document.form1.submit();
					}
				else
					showAlertMessage(mensaje4);
			}else{
				showAlertMessage(mensaje3);
			}
		}else{
			showAlertMessage(mensaje1);
		}	
		
	}

</script></head>





<body class="body">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr valign="top">
		<td>





<script type="text/javascript">	



 function openpopup(ruta) {
	window.open(ruta,'ventana','scrollbars=yes, width=902, height=529, toolbar=yno, status=yes, resizable=yes ');
}
 
 function nuevoUsuario(){
	 document.form1.action = "nuevoUsuario";
	 document.form1.submit();
 }
 
 function nuevoUsuario(){
	document.form2.action="<%=request.getContextPath()%>/jsp/nuevoUsuario.action";
	document.form2.submit();
 }

 function olvidoClave(){
		document.form2.action="<%=request.getContextPath()%>/jsp/inputOlvidoClave.action";
		document.form2.submit();
	 }
 

</script>




	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	
		<tbody><tr>
			<td height="178" align="center" valign="bottom">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td align="center">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tbody><tr>
							<td><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
   codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0"
   width="100%" height="184">
 <param name="movie" value="http://www.miclaro.com.pe/caeWeb/swf/cabecera_enLinea.swf">
 <param name="quality" value="high">
 <param name="bgcolor" value="#FFFFFF">
 <param name="swfversion" value="9.0.45.0">
 <param name="expressinstall" value="../Scripts/expressInstall.swf">
<!--[if !IE]>-->
                              <object type="application/x-shockwave-flash" align="middle" 

data="http://www.miclaro.com.pe/caeWeb/swf/cabecera_enLinea.swf" width="100%" height="184">
                                <!--<![endif]-->
                                <param name="quality" value="high">
                                <param name="wmode" value="transparent">
                                <param name="swfversion" value="9.0.45.0">
                                <param name="expressinstall" value="../Scripts/expressInstall.swf">
                                <!-- El navegador muestra el siguiente contenido alternativo para usuarios con 

Flash Player 6.0 o versiones anteriores. -->
                                <div>
                                  <h4>El contenido de esta página requiere una versión más reciente de Adobe 

Flash Player.</h4>
                                  <p><a href="http://www.adobe.com/go/getflashplayer"><img 

src="./login_files/get_flash_player.gif" alt="Obtener Adobe Flash Player" width="112" height="33"></a></p>
                                </div>
                                <!--[if !IE]>-->
                              </object>
							  <!--<![endif]-->
</object></td>
						</tr>
					</tbody></table>
					</td>
				</tr>
			</tbody></table>
			</td>
		</tr>
		
		<tr>
			<td height="252" align="center">
			<table width="757" border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td width="483" height="252" valign="top" bgcolor="#ffffff" background="./login_files/bg_loginred.jpg">
					<table width="483" border="0" cellspacing="0" cellpadding="0" height="252">
						<tbody><tr>
							<td width="10" height="10" colspan="4">
							<div id="FC_tabs"> <a href="#" class="tab selected Estilo 1">Móvil</a> <a href="#" class="tab Estilo 1"> Otros Servicios</a> </div>
							</td>
							</tr>
							<tr>
							<td width="143">
                                      <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="143" height="187" id="FlashID">
										<param name="movie" value="http://www.miclaro.com.pe/caeWeb/swf/swfparalogin.swf">
										<param name="quality" value="high">
										<param name="wmode" value="transparent">
										<param name="swfversion" value="9.0.45.0">
										<param name="expressinstall" value="../Scripts/expressInstall.swf">
								
											<object data="http://www.miclaro.com.pe/caeWeb/swf/swfparalogin.swf" type="application/x-shockwave-flash" width="153" height="187">
												<param name="quality" value="high">
												<param name="wmode" value="transparent">
												<param name="swfversion" value="9.0.45.0">
												<param name="expressinstall" value="../Scripts/expressInstall.swf">
											</object>
								  </object>
                            </td>
								  
                            </td>
							<td align="center" valign="top"><div id="FC_container">
                              
							  <div id="FC_content">
                                <div class="content">
                                  <table width="100%">
                                    <tr>
                                      
                                      <td width="40%"><table width="78%" align="center" border="0" cellspacing="0" cellpadding="0">
                                          <tbody>
                                            <tr>
                                              <td height="20" class="txtblanco" align="left" style="padding-top: 7px">Ingrese su Número Claro:</td>
                                            </tr>
                                            <tr>
                                              <td align="left"><input id="login" name="login2" class="boxlogin" onKeyPress="javascript:validarEntero();" type="text" value="" maxlength="9"></td>
                                            </tr>
                                            <tr>
                                              <td height="20" class="txtblanco" align="left" style="padding-top: 7px">Ingrese su Clave:</td>
                                            </tr>
                                            <tr>
                                              <td align="left"><input id="personas_p" name="password2" class="boxlogin" readonly="readonly" type="password" value="">
                                                  <input id="passwordVisible2" name="passwordVisible2" type="hidden" value=""></td>
                                            </tr>
                                          </tbody>
                                      </table>
                                          <table width="81%" border="0" cellspacing="0" cellpadding="0" style="padding-top: 15px" align="center">
                                            <tbody>
                                              <tr>
                                                <td align="left" style="padding-right: 6px"><a href="javascript:goToController('/caeWeb/security/forget_password.htm?idMenu=910')" class="enlaceblanco"><u>Olvidó Clave</u></a>&nbsp;&nbsp;<a href="javascript:goToController('/caeWeb/security/new_user.htm?idMenu=911')" class="enlaceblanco"><u>Nuevo Usuario</u></a></td>
                                                <td align="right"></td>
                                              </tr>
                                            </tbody>
                                          </table></td>
                                      <td width="25%"><table cellspacing="0" cellpadding="0" border="0" width="61%" height="100%" style="margin-top: 10px">
                                          <tbody>
                                            <tr>
                                              <td align="middle"><input class="bt" type="button" name="btcae02" id="btcae02" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle"><input class="bt" type="button" name="btcae12" id="btcae12" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle"><input class="bt" type="button" name="btcae22" id="btcae22" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                            </tr>
                                            <tr>
                                              <td align="middle"><input class="bt" type="button" name="btcae032" id="btcae03" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle"><input class="bt" type="button" name="btcae042" id="btcae04" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle"><input class="bt" type="button" name="btcae052" id="btcae05" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                            </tr>
                                            <tr>
                                              <td align="middle"><input class="bt" type="button" name="btcae062" id="btcae06" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle"><input class="bt" type="button" name="btcae072" id="btcae07" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle"><input class="bt" type="button" name="btcae082" id="btcae08" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                            </tr>
                                            <tr>
                                              <td align="middle"><input class="bt" type="button" name="btcae092" id="btcae09" value="" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
                                              <td align="middle" colspan="2"><input class="btLimpiar" type="button" name="limpiar2" value="limpiar" onClick="document.getElementById(&#39;personas_p&#39;).value=&#39;&#39;;"></td>
                                            </tr>
                                            <tr>
                                              <td colspan="3" align="middle"><input width="100%" name="ingresar2" class="btIngresar" type="button" onClick="javascript:doConsultar(&#39;&#39;,&#39;loginP&#39;,&#39;personas_p&#39;)" value="Ingresar"></td>
                                            </tr>
                                          </tbody>
                                      </table></td>
                                    </tr>
                                  </table>
                                  <!-- <p>&nbsp;</p>  -->
                                </div>
                                  <!--<s:url id="detailUrl" action="jsp/nuevoUsuario"/>-->
                                  
                                  <s:url var="detailUrl" value="javascript:nuevoUsuario()"/>
                                  <s:url var="detailUrlOlvidoClave" value="javascript:olvidoClave()"/>
							    <div class="content"> 
							    <s:form id="form2">
							   
								<table width="100%" border="0">
  <tr>
    <td>
	<table width="78%" align="center" border="0" cellspacing="0" cellpadding="0">
										
										
										<tbody>
										
										<tr>
											<td height="20" class="txtblanco" align="left" style="padding-top: 7px"><%=MENSAJES_CONFIG.LOGUEO_INGRESA_MAIL %></td>
										</tr>
										
										<tr>
											<td align="left"><input id="loginP" name="login" class="boxlogin" type="text" maxlength="30" value=""> <!-- onKeyPress="javascript:validarEntero();" maxlength="9" --></td>
										</tr>
										
										<tr>
											<td height="20" class="txtblanco" align="left" style="padding-top: 7px"><%=MENSAJES_CONFIG.LOGUEO_INGRESA_CLAVE %></td>
										</tr>
										<tr>
											<td align="left"><input id="personas_p2" name="password" class="boxlogin" readonly="readonly" type="password" value=""> <input id="passwordVisible" name="passwordVisible" type="hidden" value=""></td>
										</tr>
									</tbody></table>
									<table width="81%" border="0" cellspacing="1" cellpadding="0" style="padding-top: 15px" align="center">
                                            <tbody>
                                              <tr>
                                                <td align="left"><s:a href="%{detailUrlOlvidoClave}" cssClass="enlaceblanco"><u><%=MENSAJES_CONFIG.LOGUEO_OLVIDOCLAVE %></u></s:a>&nbsp;&nbsp;<s:a href="%{detailUrl}" cssClass="enlaceblanco"><u><%=MENSAJES_CONFIG.LOGUEO_NUEVO_USUARIO %></u></s:a></td>
                                                <td align="right"></td>
                                              </tr> 
                                            </tbody>
                                   </table>
									</td>
    				 <td><table cellspacing="1" cellpadding="0" border="0" width="61%" height="100%" style="margin-top: 10px">
								<tbody>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae0" id="btcae0" value="4" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae1" id="btcae1" value="5" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae2" id="btcae2" value="0" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
									</tr>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae03" id="btcae3" value="3" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae04" id="btcae4" value="1" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae05" id="btcae5" value="6" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
									</tr>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae06" id="btcae6" value="2" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae07" id="btcae7" value="9" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae08" id="btcae8" value="7" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
									</tr>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae09" id="btcae9" value="8" onClick="setPassword(this.value,&#39;personas_p2&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle" colspan="2"><input class="btLimpiar" type="button" name="limpiar" value="limpiar" onClick="document.getElementById(&#39;personas_p2&#39;).value=&#39;&#39;;"></td>
									</tr>
									<tr>
										<td colspan="3" align="middle" style="padding-top: 3px"><input name="ingresar" class="btIngresar" type="button" onClick="javascript:doConsultarFijo(&#39;&#39;,&#39;loginP&#39;,&#39;personas_p2&#39;)" value="Ingresar"></td>
									</tr>
								</tbody>
							
							</table></td>
  </tr>
</table>

									</s:form> </div>
							    </div>
							  </div></td>
							</tr>
					</tbody></table>
					</td>
					<td width="293" height="252" align="center" style="background-image:url(login_files/bg_loginblue.jpg);background-repeat:repeat-x;" valign="top">
					<table width="250" height="183" border="0" cellspacing="0" cellpadding="0">
						<tbody><tr>
							<td height="40px"></td>
						</tr>
						
							<tr>
								<td style="padding-left: 20px;" height="142" valign="top">
									<table>
									<tbody>
									<tr>
									<td align="left">
									<table cellpadding="0" cellspacing="0">
									<tbody><tr>
											<td align="left" valign="middle"><img src="./login_files/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Que_es/pregunta_0000.html');"> ¿Qué es Mi Claro? </a></td>
										</tr>
									</tbody></table>
									</td>
									</tr>
									<tr>
									<td align="left">
									<table cellpadding="0" cellspacing="0" style="border-collapse: collapse;*border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./login_files/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Consultas_y_Operaciones/pregunta_0000.html');"> ¿Qué consultas y operaciones puedo hacer en Mi Claro? </a></td>
										</tr>
									</tbody></table>
									</td>
									</tr>
									<tr>
									<td align="left">
									<table cellpadding="0" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./login_files/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Porque_Registrarme/pregunta_0000.html');"> ¿Por qué registrarme en Mi Claro? </a></td>
										</tr>
									</tbody></table>
									</td>
									</tr>
									<tr>
									<td align="left">
									<table cellpadding="0" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./login_files/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Nuevo_Usuario/pregunta_0000.html');"> Soy Nuevo Usuario, ¿cómo ingreso? </a></td>
										</tr>
									</tbody></table>
									</td>
									</tr>
									<tr>
									<td align="left">
									<table cellpadding="0" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./login_files/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Olvide_mi_Clave/pregunta_0000.html');"> Olvidé mi Clave, ¿qué hago? </a></td>
										</tr>
									</tbody></table>
									</td>
									</tr>
									<tr>
									<td align="left">
									<table cellpadding="0" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./login_files/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/No_puedo_Ingresar/pregunta_0000.html');"> No puedo ingresar, ¿qué hago? </a></td>
										</tr>
									</tbody></table>
									</td>
									</tr>
									</tbody>
									</table>
								</td>
							</tr>
						
					</tbody></table>
					<table height="66px" border="0">
						<tbody><tr>
							<td><a href="javascript: openpopup('/caeWeb/jsp/banners/democlaroenlinea.jsp');" style="border: none"> <img style="width: 260" src="./login_files/transparencia.gif" border="0" height="50"> </a></td>
						</tr>
					</tbody></table>
					</td>
					<td width="8" align="right" style="background-image: url(images/img_02.gif); background-repeat: repeat-x; background-position: bottom;"></td>
				</tr>
				<tr height="20">
					<td colspan="4"> 
					
					</td>
				</tr>
			</tbody></table>
			</td>
		</tr>
		<tr>

			<td>
			</td>
		</tr>
	</tbody></table>

<script language="javascript">
//poblarBoton();
</script>





<div>
<form name="form1" method="post" action="j_spring_security_check">
<input type="hidden" name="j_username" value=""> <input type="hidden" name="j_password" value="">
<!--  <INPUT TYPE="hidden" NAME="localidad">-->
</form>
</div>





<script type="text/javascript">
<!--
function xxlogin(user,pass)
{	var frm = document.forms[0];
	frm.login.value=user;
	frm.password.value=pass;
	doConsultar('','loginP','personas_p','localidadC');
}
//-->
</script>
</td>
	</tr>
	<tr valign="top" height="235">
		<td>
		

<!-- modificado por rlv -->
<table width="890" height="70" valign="top" align="center" cellspacing="0">
<tbody>
<tr>
<td style="color:red; font-size:small;"><b>${SPRING_SECURITY_LAST_EXCEPTION.message}</b></td>
<td><c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" /></td>
</tr>
</tbody>
</table>
<table width="890" height="70" valign="top" align="center" cellspacing="0">
	<tbody>
	<tr>
		<td height="20" align="center"><a href="http://www.claro.com.pe/" target="_blank" class="enlacerojo"><strong>Portal Claro</strong></a> <span class="textorojo">|</span> <a href="http://www.ideasclaro.com.pe/" target="_blank" class="enlacerojo"><strong>Ideas</strong></a> <span class="textorojo">|</span> <a href="http://www.claro.com.pe/claroclub" class="enlacerojo" target="_blank"><strong>ClaroClub</strong></a></td>
	</tr>
	<tr>
		<td height="30" align="center" class="footercss">Derechos reservados. Por favor lea nuestro  <span class="textorojo2"><strong><a href="javascript:openPoliticas();" class="enlaceazul">convenio para el uso y privacidad del sitio.</a></strong></span><br>
		Sitio Desarrollado para IE 7+ Resolución 1024x768 : Necesitas <a href="javascript:openMacromedia();" class="servicos-texto3">
		<strong>Macromedia Flash 9+, Acrobat 7+</strong></a></td>
	</tr>
	<tr>
		<td align="center" class="footercss">Copyright ® Claro 2011<br><font color="white">2.0.2 - .1229</font></td>
	</tr>
</tbody></table>


<script src="<s:url value="/login_files/ga.js"/>" type="text/javascript"></script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-9464957-1");
pageTracker._trackPageview();
} catch(err) {}</script>

		</td>
	</tr>
</tbody></table>





</body></html>