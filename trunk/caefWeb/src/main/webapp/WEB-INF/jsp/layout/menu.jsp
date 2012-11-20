
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 <script language="JavaScript" type="text/javascript">
  function abrirSMS(){
	  window.open("envioSMSForm","SMS","width:300,heigth:150");
  }
  </script>
 
 <ul class="menuul"> 
  <li ><a class="navtop" href="./principal">Inicio</a></li> 
  <li><a class="navtop" href="./principal">Perfiles</a> 
    <ul class="submenuul"> 
      <li><a class="subnavtop" href="./consultaCliente">Datos del Cliente y servicios</a></li> 
      <li><a class="subnavtop" href="./inputActualizacionCliente">Actualizaci&oacute;n de Datos del Cliente</a></li> 
     	<li><a class="subnavtop" href="cambioClave">Cambio de clave</a></li>
      <!-- <li><a class="subnavtop" href="./manualUsuario">Manual de Usuario</a></li> --> 
      <!--  <li><a class="subnavtop" href="./nuevoUsuario">Nuevo Usuario</a></li>-->
    </ul> 
  </li> 
  <li><a class="navtop" href="./principal">Consultas y Reportes</a> 
    <ul class="submenuul"> 
      <li><a class="subnavtop"  href="./inputConsumo">Consulta de Consumos</a></li> 
      <li><a class="subnavtop"  href="./inputLlamadasFacturadas">Detalle de Llamadas Facturado</a></li> 
      <li><a class="subnavtop"  href="./inputLlamadasNoFacturadas">Detalle de Llamadas No Facturado </a></li> 
      <li><a class="subnavtop"  href="./inputLlamadaEntrante">Detalle de Llamadas Entrantes </a></li> 
      <li><a class="subnavtop"  href="./inputRecargas">Detalle de Recargas</a></li>
      <li><a class="subnavtop"  href="./inputEstadoCuenta">Estado de Cuenta</a></li> 
	  <li><a class="subnavtop"  href="./duplicadoRecibo">Duplicado de Recibos </a></li> 
	  <li><a class="subnavtop"  href="./trafficView">Traffic View</a></li> 
    </ul> 
  </li>
  <li><a class="navtop"  href="./principal">Operaciones</a> 
    <ul class="submenuul"> 
      <li><a class="subnavtop"  href="./correoElectronico">Recibo por Correo electr&oacute;nico</a></li> 
      <li><a class="subnavtop"  href="./inputActivaPaquete">Activaci&oacute;n de Paquetes </a></li> 
      <li><a class="subnavtop" href="./inputDecoAdicional">Instalaci&oacute;n DECO adicional</a></li> 
    </ul> 
  </li> 
  <li><a class="navtop" href="./principal">Servicios</a> 
    <ul class="submenuul"> 
      <li><a class="subnavtop" href="./inputDirectorioAbonado">Directorio de Abonados</a></li> 
      <li><a class="subnavtop" href="javascript:abrirSMS();">Env&iacute;o de SMS</a></li> 
      <!--  <li><a class="subnavtop" href="./inputIngresoPreguntasSecretas">Ingreso Preguntas Secretas</a></li>-->
      <!-- <li><a class="subnavtop" href="./principalActualizarPreguntasSecretas">Actualizar Preguntas Secretas</a></li> -->
      <!-- <li><a class="subnavtop" href="./inputEliminarPreguntasSecretas">Eliminar Preguntas Secretas</a></li> -->
      <!-- <li><a class="subnavtop" href="./inputAvisoLegalYAcuerdos">Aviso Legal Y Acuerdos</a></li> -->
      <!--  <li><a class="subnavtop" href="./inputRegistrarOlvidoClave">Registrar Olvido Clave</a></li>-->
      <!-- <li><a class="subnavtop" href="./inputOlvidoClave">Olvid&oacute; Clave</a></li> --> 
      <!-- <li><a class="subnavtop"  href="./inputSeleccionarServicio">Seleccionar Servicio</a></li> --> 
    </ul> 
  </li>
  <li>
	<security:authorize ifAllGranted="ROLE_USER">
	
			<a class="navtop" href="<%=request.getContextPath()%>/j_spring_security_logout">Salir</a>
			
	</security:authorize>
  
  </li> 
    <li><a class="navtop"></a> </li> 
</ul> 