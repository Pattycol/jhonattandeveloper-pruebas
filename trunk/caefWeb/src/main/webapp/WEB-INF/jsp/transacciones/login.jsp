<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tbody><tr valign="top">
		<td>





<script type="text/javascript">	
 function openpopup(ruta) {
	window.open(ruta,'ventana','scrollbars=yes, width=902, height=529, toolbar=yno, status=yes, resizable=yes ');
}

</script>



<form id="loginForm" method="post" action="./img/login.htm">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	
		<tbody><tr>
			<td height="178" align="center" valign="bottom">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody><tr>
					<td align="center">
					<table width="100%" cellspacing="0" cellpadding="0">
						<tbody><tr>
							<td><object id="FlashID2" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="184">
								<param name="movie" value="/caeWeb/swf/cabecera_enLinea.swf">
								<param name="quality" value="high">
								<param name="wmode" value="transparent">
								<param name="swfversion" value="9.0.45.0">
								<!-- Esta etiqueta param indica a los usuarios de Flash Player 6.0 r65 o posterior que descarguen la versión más reciente de Flash Player. Elimínela si no desea que los usuarios vean el mensaje. -->
								<param name="expressinstall" value="../Scripts/expressInstall.swf">
								<!-- La siguiente etiqueta object es para navegadores distintos de IE. Ocúltela a IE mediante IECC. --> <!--[if !IE]>-->
								<object type="application/x-shockwave-flash" align="middle" data="http://www.miclaro.com.pe/caeWeb/swf/cabecera_enLinea.swf" width="100%" height="184"> <!--<![endif]-->
									<param name="quality" value="high">
									<param name="wmode" value="transparent">
									<param name="swfversion" value="9.0.45.0">
									<param name="expressinstall" value="../Scripts/expressInstall.swf">
									<!-- El navegador muestra el siguiente contenido alternativo para usuarios con Flash Player 6.0 o versiones anteriores. -->
									<div>
									<h4>El contenido de esta p&aacute;gina requiere una versi&oacute;n m&aacute;s reciente de Adobe Flash Player.</h4>
									<p><a href="http://www.adobe.com/go/getflashplayer"><img src="./img/get_flash_player.gif" alt="Obtener Adobe Flash Player" width="112" height="33"></a></p>
									</div>
									<!--[if !IE]>--> </object><!--<![endif]--> </object></td>
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
					<td width="483" height="252" valign="top" bgcolor="#ffffff" background="./img/bg_loginred.jpg">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" height="252">
						<tbody><tr>
							<td width="134" height="252">
							<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="153" height="252" id="FlashID">
								<param name="movie" value="/caeWeb/swf/swfparalogin.swf">
								<param name="quality" value="high">
								<param name="wmode" value="transparent">
								<param name="swfversion" value="9.0.45.0">
								<param name="expressinstall" value="../Scripts/expressInstall.swf">
								
									<object data="http://www.miclaro.com.pe/caeWeb/swf/swfparalogin.swf" type="application/x-shockwave-flash" width="153" height="252">
										<param name="quality" value="high">
										<param name="wmode" value="transparent">
										<param name="swfversion" value="9.0.45.0">
										<param name="expressinstall" value="../Scripts/expressInstall.swf">
									</object>
									</object></td>
							<td width="182" valign="top" align="center">
							<table>
								<tbody><tr>
									<td height="45px"></td>
								</tr>
								<tr>
									<td>
									<table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
										
								<!--  		
										<tr>
											<td height="20" class="txtblanco" align="left">Seleccione su Localidad:</td>
										</tr>
										<tr>
											<td align="left"><select id="localidadC" name="localidad" class="selectedWidth">
												
											</select></td>
										</tr>
									-->	
										
										<tbody><tr>
											<td height="20" class="txtblanco" align="left" style="padding-top: 7px">Ingrese su Número Claro:</td>
										</tr>
										<tr>
											<td align="left"><input id="loginP" name="login" class="boxlogin" onKeyPress="javascript:validarEntero();" type="text" value="" maxlength="9"></td>
										</tr>
										<tr>
											<td height="20" class="txtblanco" align="left" style="padding-top: 7px">Ingrese su Clave:</td>
										</tr>
										<tr>
											<td align="left"><input id="personas_p" name="password" class="boxlogin" readonly="readonly" type="password" value=""> <input id="passwordVisible" name="passwordVisible" type="hidden" value=""></td>
										</tr>
									</tbody></table>
									<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding-top: 15px">
										<tbody><tr>
											<td align="left" style="padding-right: 6px"><a href="javascript:goToController('/caeWeb/security/forget_password.htm?idMenu=910')" class="enlaceblanco"><u>Olvidó Clave</u></a></td>
											<td align="right"><a href="javascript:goToController('/caeWeb/security/new_user.htm?idMenu=911')" class="enlaceblanco"><u>Nuevo Usuario</u></a></td>
										</tr>
									</tbody></table>
									</td>
								</tr>
							</tbody></table>
							</td>
							<td align="center" valign="top">
							<table cellspacing="0" cellpadding="0" border="0" width="80" style="margin-top: 50px">
								<tbody>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae0" id="btcae0" value="4" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae1" id="btcae1" value="5" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae2" id="btcae2" value="0" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
									</tr>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae03" id="btcae3" value="3" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae04" id="btcae4" value="1" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae05" id="btcae5" value="6" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
									</tr>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae06" id="btcae6" value="2" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae07" id="btcae7" value="9" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle"><input class="bt" type="button" name="btcae08" id="btcae8" value="7" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
									</tr>
									<tr>
										<td align="middle"><input class="bt" type="button" name="btcae09" id="btcae9" value="8" onClick="setPassword(this.value,&#39;personas_p&#39;,&#39;La clave debe tener 6 dígitos.&#39;);"></td>
										<td align="middle" colspan="2"><input class="btLimpiar" type="button" name="limpiar" value="limpiar" onClick="document.getElementById(&#39;personas_p&#39;).value=&#39;&#39;;"></td>
									</tr>
									<tr>
										<td colspan="3" align="middle" style="padding-top: 3px"><input name="ingresar" class="btIngresar" type="button" onClick="javascript:doConsultar(&#39;&#39;,&#39;loginP&#39;,&#39;personas_p&#39;)" value="Ingresar"></td>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>
					</tbody></table>
					</td>
					<td width="293" height="252" align="center" background="./img/bg_loginblue.jpg" valign="top">
					<table width="250" height="183" border="0" cellspacing="0" cellpadding="0">
						<tbody><tr>
							<td height="40px"></td>
						</tr>
						
							<tr>
								<td style="padding-left: 20px;" height="142" valign="top">
									<table cellpadding="2" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./img/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Que_es/pregunta_0000.html');"> ¿Qué es Mi Claro? </a></td>
										</tr>
									</tbody></table>
								
									<table cellpadding="2" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./img/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Consultas_y_Operaciones/pregunta_0000.html');"> ¿Qué consultas y operaciones puedo hacer en Mi Claro? </a></td>
										</tr>
									</tbody></table>
								
									<table cellpadding="2" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./img/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Porque_Registrarme/pregunta_0000.html');"> ¿Por qué registrarme en Mi Claro? </a></td>
										</tr>
									</tbody></table>
								
									<table cellpadding="2" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./img/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Nuevo_Usuario/pregunta_0000.html');"> Soy Nuevo Usuario, ¿cómo ingreso? </a></td>
										</tr>
									</tbody></table>
								
									<table cellpadding="2" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./img/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/Olvide_mi_Clave/pregunta_0000.html');"> Olvidé mi Clave, ¿qué hago? </a></td>
										</tr>
									</tbody></table>
								
									<table cellpadding="2" cellspacing="0" style="border-collapse: collapse">
										<tbody><tr>
											<td align="left" valign="top"><img src="./img/bull_01.gif" width="5">
											</td>
											<td style="padding-left: 10px"><a class="enlaceblancoLogin" href="javascript: openpopup('http://www.miclaro.com.pe/opencms/opencms/claroenlinea/general/No_puedo_Ingresar/pregunta_0000.html');"> No puedo ingresar, ¿qué hago? </a></td>
										</tr>
									</tbody></table>
								</td>
							</tr>
						
					</tbody></table>
					<table height="69px" border="0">
						<tbody><tr>
							<td><a href="javascript: openpopup('/caeWeb/jsp/banners/democlaroenlinea.jsp');" style="border: none"> <img style="width: 260" src="./img/transparencia.gif" border="0" height="60"> </a></td>
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
</form>
<script language="javascript">
poblarBoton();
</script>





<div>
<form name="form1" method="post">
<input type="hidden" name="login" value=""> <input type="hidden" name="password" value="">
<!--  <INPUT TYPE="hidden" NAME="localidad">-->
</form>
</div>

<script type="text/javascript">
pivotear();

function pivotear(){
	
	var login = document.getElementById("loginP");	
	var password  = document.getElementById("passwordVisible");
	var estado = 'null';
	var aplicacion = 'null';	
	
	document.form1.login.value = login.value;
	document.form1.password.value = password.value;	
	
		
	if(aplicacion == 'cae') {		
		document.form1.action = '/caeWeb/login.htm';
		document.form1.submit();
			
	}
	if(aplicacion == 'cac') {		
	 	document.form1.action = '/cacWeb/login.htm';	 		 	
		document.form1.submit();		
	}
}
function  mostrarFormuarioSpring() {
	window.alert("spring:login:" + document.getElementById("loginP").value);
	window.alert("spring:password:" + document.getElementById("personas_p").value);
	window.alert("spring:passwordVisible:" + document.getElementById("passwordVisible").value);		
}
function  mostrarFormularioHTML() {
	window.alert("html:login:" + document.form1.login.value);
	window.alert("html:password:" + document.form1.password.value);		
}
</script>



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
<!-- <ronald>  solo para pruebas luego borrar para pasar a produccion

<table border="0" cellspacing="0" cellpadding="0" align="center">
	<tr valign="top" align="center">		
		<td>PREPAGO<br>
		<input type="button" onclick="javascript: xxlogin('989082012','123456');" value="989082012"></td>
		<td>POSTAGO ADMIN<br>
		<input type="button" onclick="javascript: xxlogin('992777956','123456');" value="992777956"></td>
		<td>CONTROL<br>
		<input type="button" onclick="javascript: xxlogin('993466891','123456');" value="993466891"></td>
		<td>CORPORATIVO<br>
		<input type="button" onclick="javascript: xxlogin('997517070','123456');" value="997517070"></td>
		<td>B2E<br>
		<input type="button" onclick="javascript: xxlogin('997108293','123456');" value="997108293"></td>
	</tr>
</table--><script type="text/javascript">	
 function openpopup(ruta) {
	window.open(ruta,'ventana','scrollbars=yes, width=902, height=529, toolbar=yno, status=yes, resizable=yes ');
}

</script></td>
	</tr>
	<tr valign="top" height="235">
		<td>
		

<!-- modificado por rlv -->
<table width="890" height="70" valign="top" align="center" cellspacing="0">
	<tbody><tr>
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

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script><script src="./img/ga.js" type="text/javascript"></script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-9464957-1");
pageTracker._trackPageview();
} catch(err) {}</script>

		</td>
	</tr>
</tbody></table>