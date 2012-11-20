<%@ taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="../css/login.css" />
<script type="text/javascript">
$(function() {
	 $(document).keydown(function(e){
	  var code = (e.keyCode ? e.keyCode : e.which);
	  if(code == 116) {
		e.preventDefault();
	   	location="<%=request.getContextPath()%>/j_spring_security_logout";
	  }
	  if(code==8){
		  e.preventDefault();
		  location="<%=request.getContextPath()%>/j_spring_security_logout";		  
	  }
	  if(code==8){
		   e.preventDefault();
		   location="<%=request.getContextPath()%>/j_spring_security_logout";
	  }
	 });
	});

</script>
 <div class="grid_10">
  <br>
  <td width="100%" colspan="3"><label>Bienvenido(a) Sr.(Srta.)&nbsp;<s:property value="%{#session.usuario.nombreUsuario}"/><br></label>
			<br>
			</td>
  </div> 			
  <div>
      <div class="grid_10">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody><tr>
				<td><img src="/caefWeb/img/icono_consulta.gif" width="735" height="21"></td>
			</tr>
			<tr>
				<td height="44">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody><tr>
						
							<!-- Muestra las consultas frecuentes  -->
							
								<td height="40" width="33%">
								<table border="0" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF">
									<tbody><tr>
										<td bordercolor="#d1d7db" width="40px"><a href="http://www.miclaro.com.pe/cacWeb/menu/consultaSaldo.htm?idMenu=82">
											<img src="/caefWeb/img/btn_iconos_postpago_1.jpg" width="40" height="40" border="0"></a>
										</td>
										<td bordercolor="#d1d7db" width="100%" style="background-image: url(/cacWeb/images/bg_opc01.jpg); background-repeat: repeat-x; padding-left: 4px">
											<a href="http://www.miclaro.com.pe/cacWeb/menu/consultaSaldo.htm?idMenu=82" class="enlaceazulrojo"> Consulta de Consumos</a>
										</td>
									</tr>
								</tbody></table>
								</td>
							
								<td height="40" width="33%">
								<table border="0" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF">
									<tbody><tr>
										<td bordercolor="#d1d7db" width="40px"><a href="http://www.miclaro.com.pe/cacWeb/menu/detalleLlamadasNoFacturadas.htm?idMenu=67">
											<img src="/caefWeb/img/btn_iconos_postpago_2.jpg" width="40" height="40" border="0"></a>
										</td>
										<td bordercolor="#d1d7db" width="100%" style="background-image: url(/cacWeb/images/bg_opc01.jpg); background-repeat: repeat-x; padding-left: 4px; text-decoration: none">
											<a href="http://www.miclaro.com.pe/cacWeb/menu/detalleLlamadasNoFacturadas.htm?idMenu=67" class="enlaceazulrojo"> Detalle de Llamadas No Facturado</a>
										</td>
									</tr>
								</tbody></table>
								</td>
							
								<td height="40" width="33%">
								<table border="0" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF">
									<tbody><tr>
										<td bordercolor="#d1d7db" width="40px"><a href="http://www.miclaro.com.pe/cacWeb/menu/estadoOST.htm?idMenu=30">
											<img src="/caefWeb/img/btn_iconos_postpago_4.jpg" width="40" height="40" border="0"></a>
										</td>
										<td bordercolor="#d1d7db" width="100%" style="background-image: url(/cacWeb/images/bg_opc01.jpg); background-repeat: repeat-x; padding-left: 4px">
											<a href="http://www.miclaro.com.pe/cacWeb/menu/estadoOST.htm?idMenu=30" class="enlaceazulrojo"> Orden de Servicio T&eacute;cnico</a>
										</td>
									</tr>
								</tbody></table>
								</td>
							
						
					</tr>
				</tbody></table>
				</td>
			</tr>
			<tr>
			<td height="1" bgcolor="#b5bdc4"></td>
			</tr>
		</tbody></table>
	
	</div>
	
	<div class="grid_10">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tbody><tr>
		<td height="25">&nbsp;</td>
	</tr>
	<tr>
		<td>
		<table width="100%%" border="0" cellspacing="0" cellpadding="0">
			<tbody><tr>
				<td valign="top"><table width="357" border="0" cellspacing="0" cellpadding="0">
                  <tbody>
                    <tr>
                      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                          <tbody>
                            <tr>
                              <td><img src="/caefWeb/img/icono_OperacionesServicios.gif" width="357" height="20"></td>
                            </tr>
                          </tbody>
                      </table></td>
                    </tr>
                    <tr>
                      <td style="padding-top: 1px"><table width="100%%" border="1" cellpadding="0" cellspacing="0" bordercolor="#c9caca" style="border-collapse: collapse">
                          <tbody>
                            <tr>
                              <td width="84%" height="139" style="background-image: url(/caefWeb/img/bg_opc02.jpg); background-repeat: repeat-x;"><table width="100%" height="134" border="0" cellspacing="0" cellpadding="0" background="/caefWeb/img/imagen_consultayservicios.jpg">
                                  <tbody>
                                    <tr>
                                      <td width="28%"></td>
                                      <td width="72%"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                          <!-- Muestra las transacciones frecuentes  -->
                                          <tbody>
                                            <tr>
                                              <td height="15" style="font-color:green"><a href="http://www.miclaro.com.pe/cacWeb/menu/compraPaquetes.htm?idMenu=199" class="enlaceazulrojo" style="padding-left: 10px"><font color=red> - Compra de Paquete de Datos</font></a></td>
                                            </tr>
                                            <tr>
                                              <td height="15"><a href="http://www.miclaro.com.pe/cacWeb/menu/triaciones.htm?idMenu=34" class="enlaceazulrojo" style="padding-left: 10px">-
                                                N&uacute;meros Frecuentes</a></td>
                                            </tr>
                                            <tr>
                                              <td height="15"><a href="http://www.miclaro.com.pe/cacWeb/menu/bloqueoLineaEquipo.htm?idMenu=66" class="enlaceazulrojo" style="padding-left: 10px">-
                                                Bloqueo de L&iacute;nea y Equipo</a></td>
                                            </tr>
                                            <tr>
                                              <td height="15"><a href="http://www.miclaro.com.pe/cacWeb/menu/desbloqueoLineaEquipo.htm?idMenu=127" class="enlaceazulrojo" style="padding-left: 10px">-
                                                Desbloqueo de L&iacute;nea y Equipo</a></td>
                                            </tr>
                                            <tr>
                                              <td height="15"><a href="http://www.miclaro.com.pe/cacWeb/menu/claromail/crearcuenta.htm?idMenu=57" class="enlaceazulrojo" style="padding-left: 10px">-
                                                Crear Cuenta iClaro</a></td>
                                            </tr>
                                          </tbody>
                                      </table></td>
                                    </tr>
                                  </tbody>
                              </table></td>
                            </tr>
                          </tbody>
                      </table></td>
                    </tr>
                  </tbody>
				  </table></td>
				<td width="3">&nbsp;</td>
				<td valign="top">
				</td>
			</tr>
		</tbody></table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0" width="717" height="80">
				
				
				<param name="movie" value="http://www.miclaro.com.pe/cacWeb/swf/banner/area_virtual.swf">
				<param name="quality" value="high">
				<embed src="http://www.miclaro.com.pe/cacWeb/swf/banner/area_virtual.swf" quality="high" pluginspage="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" width="717" height="80"> </object>

		</td>
	</tr>
</tbody></table>
		
	</div>
	</div>