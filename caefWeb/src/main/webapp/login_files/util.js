document.onkeydown = function(){   
    if(window.event && window.event.keyCode == 116){  
     window.event.keyCode = 505;         
    }  
    if(window.event && window.event.keyCode == 505){   
     return false;      
    }  
} 

function nrcIE(){
	if (document.all){
		return false;
	}
}

function nrcNS(e){
	if(document.layers||(document.getElementById&&!document.all)){ 
		if (e.which==2||e.which==3){
			return false;
		}
			
	}
} 

function goToControllerOpera(controller,operacion) {
	var frm = document.forms[0];
	frm.action = controller;
	frm.operacion.value = operacion;
	frm.submit();
}

if (document.layers){
	document.captureEvents(Event.MOUSEDOWN);
	document.onmousedown=nrcNS;
}else{
	document.onmouseup=nrcNS;document.oncontextmenu=nrcIE;
}
	document.oncontextmenu=new Function("return false");
		
//Funcion que cambia la action del formulario y la redirecciona hacia otro
//Controller
function goToController(controller) {
	var frm = document.forms[0];
	frm.action = controller;
	frm.submit();
}

function redirection(controller){
	
	document.location.href = controller;
}

// Funcion que cambia el parametro de la operacion a realizar
// en un SimpleFormController, debe existir el hidden
// 'operacion' dentro del documento hmtl

function doOperacion(operacion) {
	var frm = document.forms[0];
	frm.operacion.value = operacion;
	//alert('operacion : ' + operacion)
	frm.submit();
}

// Funcion que muestra un mensaje de tipo alert
function showAlertMessage(mensaje) {
	alert (mensaje);
}

function validarNumero1(mensaje1,mensaje2,mensaje3,mensaje4,mensajeCombo,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
			if(frm.numeros.options.length==1)
				showAlertMessage(mensajeCombo);
			else{
				frm.operacion.value = operacion;
				frm.submit();
			}
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();
		}
	}
}

function validarNumero2(mensaje1,mensaje2,mensaje3,mensaje4,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
			frm.operacion.value = operacion;
			frm.submit();
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();	
		}
	}
}

function validarNick(){
	if(event.keyCode>=48 && event.keyCode<=57){
		event.returnValue= true;
	}else if(event.keyCode>=97&& event.keyCode<=122){
		event.returnValue=true;
	}else if(event.keyCode==46 ||event.keyCode==45 || event.keyCode==95){
		event.returnValue=true;
	}else  event.returnValue=false;
} 

function validarEntero() {
	if (event.keyCode < 48 || event.keyCode > 57 ) 
		event.returnValue = false;
}

/*CPP validar campos de letra y alfanumericos*/
	function esAlfanumerico(evt){
	    if((event.keyCode < 65 || event.keyCode > 90) && (event.keyCode < 97 || event.keyCode > 122) && (event.keyCode < 48 || event.keyCode > 57) && event.keyCode!=32 && event.keyCode!=39 && event.keyCode!=45 && event.keyCode!=95 && event.keyCode!=241 && event.keyCode!=209 && event.keyCode!=225 && event.keyCode!=233 && event.keyCode!=237 && event.keyCode!=243 && event.keyCode!=250 && event.keyCode!=193 && event.keyCode!=201 && event.keyCode!=205 && event.keyCode!=211 && event.keyCode!=218){
	       event.returnValue = false;
	    }
	}
/*fin*/

/*CPP validar campos de letra y alfanumericos para correoElectronico*/
	function esEmail(evt){
	    if((event.keyCode < 65 || event.keyCode > 90) && 
	       (event.keyCode < 97 || event.keyCode > 122) && 
	       (event.keyCode < 48 || event.keyCode > 57) && 
	    	event.keyCode!=45 && 
	    	event.keyCode!=46 && 
	    	event.keyCode!=64 &&
	    	event.keyCode!=95 )
	     {
	       event.returnValue = false;
	    }
	}
/*fin*/

// Funcion que sirve para refrescar el formulario en los ordenamientos
// y paginamientos del displayTag
function tableTagRefresh(ruta) {
	var frm = document.forms[0];
	frm.operacion.value = 'refrescar';
	frm.action=ruta;
	frm.submit();
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 3
function habilitarGrupo() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.subcuenta.disabled = false;
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.subcuenta.disabled = true;
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 4
function habilitarGrupo4() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.presubcuenta.disabled = false;
		frm.subcuenta.disabled = false;
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.presubcuenta.disabled = true;
		frm.subcuenta.disabled = true;
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 2
function habilitarGrupo2() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		/*Al alterar consulta consumo */
		frm.subcuenta.disabled = false;
		
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		/*Para limpiarlo*/
		//frm.numero.value = "";
		//alert('Limpio');
		
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		/*Al alterar consulta consumo */
		frm.subcuenta.disabled = true;
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}
//Funcion para habilitar y desabilitar campos de la cabecera Nivel 1
function habilitarGrupo1() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		//Ahorita lo creo CHVE
		frm.cuenta.disabled = false;
			
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		//Ahorita lo creo CHVE
		frm.cuenta.disabled = true;
			
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

function habilitarExportar(){
		var frm = document.forms[0];
		frm.buttonExportar.disabled = false;	
		//document.getElementById('buttonExportar').disabled=false;
	}
	
	
function validarNumero3(mensaje1,mensaje2,operacion) {
	
	var frm = document.forms[0];

	if(frm.telefono.value != ''){
		
		if(frm.telefono.value.length != 9){
				showAlertMessage(mensaje2);
				frm.telefono.focus();
				return false;
				}
	
		frm.operacion.value = operacion;
		frm.submit();
	}else{
		showAlertMessage(mensaje1);
		frm.telefono.focus();
	}
}	
	
	
//function validarNumero3(mensaje1,mensaje2,mensaje3,operacion) {
//	var frm = document.forms[0];
	//if(frm.telefono.value != ''){
	//	if(frm.localidad.value == 1){
	//		if(frm.telefono.value.length != 9){
		//		showAlertMessage(mensaje2);
		//		frm.telefono.focus();
		//		return false;
		//	}
	//	}else{
		//	if(frm.telefono.value.length != 9){
		//		showAlertMessage(mensaje3);
		//		frm.telefono.focus();
		//		return false;
		//	}
		//}
		//frm.operacion.value = operacion;
		//frm.submit();
	//}else{
	///	showAlertMessage(mensaje1);
	//	frm.telefono.focus();
	//}
//}

function validarNumero4(mensaje1,mensajeFormat,operacion) {
	var frm = document.forms[0];
	
	if(frm.numero.value == '')
		showAlertMessage(mensaje1);
	else{
		var patron=/[^0-9]/;
		if(patron.exec(frm.numero.value)){
			showAlertMessage(mensajeFormat);
			frm.numero.focus();
			return false;
		}
		frm.operacion.value = operacion;
		frm.submit();
	}
}


function validarFormatoFecha(f,mensaje1,mensaje2){ 
		
		var fecha=/^[0-9][0-9]\/[0-9][0-9]\/[0-9][0-9][0-9][0-9]$/;
		if(f.value.length!=0){
			if(!fecha.exec(f.value)){
				alert(mensaje1);
				f.focus();
				return false;
			}
			 
			/* comprobamos que la fecha es v?lida */
			var d = new Date();
			/* la funci?n tiene como entrada: a?o, mes, d?a */
			d.setFullYear(f.value.substring(6,10), f.value.substring(3,5)-1, f.value.substring(0,2))
			 
			/* getMonth() devuelve el n?mero de mes del 0 al 11
			   getDate() devuelve el d?a del mes */
			if(d.getMonth() != f.value.substring(3,5)-1 || d.getDate() != f.value.substring(0,2)){
				alert(mensaje2);
				f.focus();
				return false;
			}
			else
				return true;
		}
		else
			return true;
	}
	
function validarFormatoMail(m,mensaje) { 
		var mail = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/; 
		
		if(m.value.length!=0){
			if (!m.value.match(mail)) { 
				alert(mensaje);
				m.focus();
				return false; 
			}
			else
				return true;
		}
		else
			return true
	} 

function validarFormatoMail2(m,mensaje) { 
		var mail = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/; 
		
		if (!m.value.match(mail)) { 
			alert(mensaje);
			m.focus();
			return false; 
		}
		else 
			return true;
	} 

	
function validarTelefono() 
	{ 
		if (event.keyCode < 48 || event.keyCode > 57 ) 
		event.returnValue = false;
	} 
	
function validarFecha() 
	{ 
		if (event.keyCode < 47 || event.keyCode > 57 ) 
		event.returnValue = false;
	}
	
function deshabilitarActualizacion()
	{
		var frm = document.forms[0];
		frm.buttonActualizar.disabled = true;
		frm.nombre.disabled=true;
		frm.apePat.disabled=true;
		frm.tipoDoc.disabled=true;
		frm.numeDoc.disabled=true;
		frm.teleRef.disabled=true;
		frm.cargo.disabled=true;
		frm.funcion.disabled=true;
		frm.sexo[0].disabled=true;
		frm.sexo[1].disabled=true;
		frm.fechaNaci.disabled=true;
		frm.emailUsuario.disabled=true;
	}
	
function openPoliticas() {
	window.open("http://www.claro.com.pe/wps/portal/pe/pc/hogar/legal-y-regulatorio/aviso-legal-de-uso-del-sitio-web",'ventana','scrollbars=yes, width=700, height=500, toolbar=yes, status=no,resizable=yes');
}

function openTerminos() {
	window.open("http://contenidos.claro.com.pe/portal/includes/claromail/terminos_y_condiciones_de_uso_iclaro.pdf",'ventana','scrollbars=yes, width=700, height=500, toolbar=yes, status=no,resizable=yes');
}

function openDemo(ruta) {
	window.open(ruta+'/jsp/cuentas/demo_recibo.jsp','ventana','scrollbars=yes, width=700, height=500, toolbar=yes, status=no,resizable=yes');
}

function openTarifas() {
	window.open("http://www.claro.com.pe/portalTimWebApp/application?origin=mensajesms.jsp&event=bea.portal.framework.internal.refresh&pageid=TIM&evento=politicasTim.link&sms=707",'ventana','scrollbars=yes, width=700, height=500, toolbar=yes, status=no,resizable=yes');
}

function openMacromedia() {
	window.open("http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash");
}

function openPortalClaro() {
	window.open("http://www.claro.com.pe/");
}

function openPortalIdeas() {
	window.open("http://www.ideasclaro.com.pe/");
}

function validarNumero5(mensaje1,mensaje2,mensaje3,mensaje4,mensajesSixbell,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
		  var cont=0;
		  for (var i=frm.numeros.options.length-1;i>-1;i--) 
		  { 
            if (frm.numeros.options[i].selected) 
               cont++;                                          
          }
            if(cont>5){
				showAlertMessage(mensajesSixbell);
				return false;
			}
			else{
				frm.operacion.value = operacion;
				frm.submit();
			}
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
					if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
					if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();		
		}
	}
}

/*Validar hasta 20 numeros obtenidos especialmente para consulta consumo*/
function validarNumero20(mensaje1,mensaje2,mensaje3,mensaje4,mensajesSixbell,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
		  var cont=0;
		  for (var i=frm.numeros.options.length-1;i>-1;i--) 
		  { 
            if (frm.numeros.options[i].selected) 
               cont++;                                          
          }
            if(cont>20){
				showAlertMessage(mensajesSixbell);
				return false;
			}
			else{
				frm.operacion.value = operacion;
				frm.submit();
			}
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
					if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
					if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();		
		}
	}
}


/*Validar hasta 10 numeros obtenidos especialmente para consulta consumo*/
function validarNumero10(mensaje1,mensaje2,mensaje3,mensaje4,mensajesSixbell,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
		  var cont=0;
		  for (var i=frm.numeros.options.length-1;i>-1;i--) 
		  { 
            if (frm.numeros.options[i].selected) 
               cont++;                                          
          }
            if(cont>10){
				showAlertMessage(mensajesSixbell);
				return false;
			}
			else{
				frm.operacion.value = operacion;
				frm.submit();
			}
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
					if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
					if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();		
		}
	}
}


//Cuando hay hasta 5 números   
function validarNumero6(mensaje1,mensaje2,mensaje3,mensaje4,mensajesOST,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
		  var cont=0;
		  for (var i=frm.numeros.options.length-1;i>-1;i--)
		  	{ 
            if (frm.numeros.options[i].selected) 
               cont++;                                          
            }
            if(cont>5){
				showAlertMessage(mensajesOST);
				return false;
			}
			else{
			frm.operacion.value = operacion;
			frm.submit();
			}
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();
		}
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 3
function habilitarGrupoPerfil() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.subcuenta.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.subcuenta.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 4
function habilitarGrupoPerfil4() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.presubcuenta.disabled = false;
		frm.subcuenta.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.presubcuenta.disabled = true;
		frm.subcuenta.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 2
function habilitarGrupoPerfil2() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}
//Funcion para habilitar y desabilitar campos de la cabecera Nivel 1
function habilitarGrupoPerfil1() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 3
function habilitarGrupoLlamadas() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.subcuenta.disabled = false;
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.subcuenta.disabled = true;
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 4
function habilitarGrupoLlamadas4() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		frm.presubcuenta.disabled = false;
		frm.subcuenta.disabled = false;
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		frm.presubcuenta.disabled = true;
		frm.subcuenta.disabled = true;
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

//Funcion para habilitar y desabilitar campos de la cabecera Nivel 2
function habilitarGrupoLlamadas2() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		frm.cuenta.disabled = false;
		/*Para habilitar en ctas madres*/
		frm.subcuenta.disabled = false;
		/* - */			
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		/*Para habilitar en ctas madres*/
		frm.subcuenta.disabled = true;
		/* - */			
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}
//Funcion para habilitar y desabilitar campos de la cabecera Nivel 1
function habilitarGrupoLlamadas1() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		/*Para habilitar en ctas madres*/
		frm.cuenta.disabled = false;
		/* - */	
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		frm.localidad.disabled = true;
	}else{
		/*Para Deshabilitar en ctas madres*/
		frm.cuenta.disabled = true;		
		/* - */	
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}

function openQueOpinas(ruta) {
	window.open(ruta,'ventana','scrollbars=no, width=400, height=210, toolbar=no, status=no,resizable=no');
}

function changeStyle(s){
		s.style.backgroundColor='#FF0000';
		s.style.color='#FFFFFF';
}

function returnStyle(s){
	s.style.backgroundColor='#FFFFFF';
	s.style.color='#FF0000';
}

function validarNumeroDetalleLlamadaFacturado1(mensajeFecha,mensaje1,mensaje2,mensaje3,mensaje4,mensajeFormat,operacion) {
	var frm = document.forms[0];
	
	if(frm.fechaFacturacion.selectedIndex == 0){
		showAlertMessage(mensajeFecha);
		return false;
	}
	if(frm.grupo.checked){
		if(frm.numeros.selectedIndex == 0){
			showAlertMessage(mensaje2);
			return false;
		}
	}else{
		if(frm.numero.value == ''){
			showAlertMessage(mensaje1);
			return false;
		}else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}					
		}
	}	
	frm.operacion.value = operacion;
	frm.fechaEmision.value=frm.fechaFacturacion.options[frm.fechaFacturacion.selectedIndex].text;
	frm.submit();
}

function validarNumeroDetalleLlamadaFacturado2(mensajeFecha,mensaje1,mensajeFormat,operacion) {
	var frm = document.forms[0];
	
	if(frm.fechaFacturacion.selectedIndex == 0){
		showAlertMessage(mensajeFecha);
		return false;
	}
	if(frm.numero.value == '')
		showAlertMessage(mensaje1);
	else{
		var patron=/[^0-9]/;
		if(patron.exec(frm.numero.value)){
			showAlertMessage(mensajeFormat);
			frm.numero.focus();
			return false;
		}
		frm.operacion.value = operacion;
		frm.fechaEmision.value=frm.fechaFacturacion.options[frm.fechaFacturacion.selectedIndex].text;
		frm.submit();	
	}
}

function enviarDetalleLlamadaFacturado(mensajeFecha,operacion) {
	var frm = document.forms[0];				 
	if(frm.fechaFacturacion.selectedIndex == 0)
		showAlertMessage(mensajeFecha);		
	else{
		frm.operacion.value = operacion;
		frm.submit();
	}
}

function consultarEstadoCuenta(mensaje1,mensajeFormatCuenta,operacion) {
	var frm = document.forms[0];				 
	if(frm.cuenta.value == -1)
		showAlertMessage(mensaje1);
	else{
		var patron=/[^0-9.]/;
		if(frm.cuenta.selectedIndex==0){
			showAlertMessage(mensaje1);
			return false;
		}else
		if(patron.exec(frm.cuenta.value)){
			showAlertMessage(mensajeFormatCuenta);
			return false;
		}
		frm.operacion.value = operacion;
		frm.submit();	
	}
}

//abre popUp sms
function openSms(ruta) {
	window.open(ruta,'ventana','scrollbars=no, width=560, height=760, toolbar=no, status=no,resizable=no');
}

/*script Rnld */
function openBanners(ruta,ancho,alto,popup) {

	if( popup == 1 ) //link
	{
		window.location=ruta;
	}else 
		if (popup == 2) //pop up Externo
		{
			var ind ='http';
			var i = ruta.indexOf(ind);
			var r = ruta.substring(i);
			if(ancho!=0 && alto!=0)
			{	
				ventanaHijo=window.open(r,'ventana','scrollbars=yes, width=' + ancho + ', height=' + alto + ', toolbar=no, status=no, resizable=yes');
			}
			else
			{
				ventanaHijo=window.open(r,'ventana','scrollbars=yes, width=500, height=680, toolbar=no, status=no, resizable=yes');
			}
		}
		else 
			if(popup == 0) //banner
			{
				if(ancho!=0 && alto!=0)
				{	
					ventanaHijo=window.open(ruta,'ventana','scrollbars=no, width=' + ancho + ', height=' + alto + ', toolbar=no, status=no,resizable=no');
				}
				else
				{
					ventanaHijo=window.open(ruta,'ventana','scrollbars=no, width=500, height=680, toolbar=no, status=no,resizable=no');
				}
			}else
				if(popup == 3)
				{
				  openContactenos('/caeWeb/principal/contactenos.htm');
				}		
}

function openContactenos(ruta) {
	window.open(ruta,'ventana','scrollbars=no, width=420, height=430, toolbar=no, status=no,resizable=no');
}
/*
function armarPack(){
	//document.getElementById('packdetalle').style.display='inline';
	var frm = document.forms[0];
	if(frm.cantPack.value==''){
		alert('Seleccione la cantidad de Pack(s) a solicitar.');
		frm.cantPack.focus();
		return false;
	}
	doOperacion('armar');
}

function consultarHLR(){
	var frm = document.forms[0];
	if(frm.teleConsulta.value==''){
		alert('Ingrese el n\xFAmero telef\xF3nico a consultar.');
		frm.teleConsulta.focus();
		return false;
	}
	doOperacion('consultarHLR');
}
function ocultarDetalle(){
	//document.getElementById('packdetalle').style.display='none';
}

function doSolicitarPack(numPacksEscogido){

	var frm = document.forms[0];
	if(frm.cantPack.value==''){
		alert('Seleccione la cantidad de Pack(s) a solicitar.');
		frm.cantPack.focus();
		return false;
	}
	if(frm.dirDespacho.value==''){
		alert('Seleccione la direcci\xF3n de despacho.');
		frm.dirDespacho.focus();
		return false;
	}
	var hlr1;
	var hlr2;
	var hlr3;
	var i;
	var numeroTotal;
	if(parseInt(numPacksEscogido)<=1){
		if(frm.listPack_HLR1.value!='')
			hlr1=parseInt(frm.listPack_HLR1.value);
		else
			hlr1=0;
		if(frm.listPack_HLR2.value!='')	
			hlr2=parseInt(frm.listPack_HLR2.value);
		else
			hlr2=0;
		if(frm.listPack_HLR3.value!='')	
			hlr3=parseInt(frm.listPack_HLR3.value);
		else
			hlr3=0
		numeroTotal = hlr1+hlr2+hlr3;
		if(numeroTotal!=5){
			alert('El Pack esta compuesto de 5 chips, por favor corrija la composici\xF3n de su Pack');
			return false;
		}
	}else{
		for(i=0; i<numPascksEscogido; i++){
			if(frm.listPack_HLR1[i].value!='')
				hlr1=parseInt(frm.listPack_HLR1[i].value);
			else
				hlr1=0;
			if(frm.listPack_HLR2[i].value!='')
				hlr2=parseInt(frm.listPack_HLR2[i].value);
			else
				hlr2=0;
			if(frm.listPack_HLR3[i].value!='')
				hlr3=parseInt(frm.listPack_HLR3[i].value);
			else
				hlr3=0;
			numeroTotal = hlr1+hlr2+hlr3;
			if(numeroTotal!=5){
				alert('El Pack est\xE1 compuesto de 5 chips, por favor corrija la composici\xF3n de su Pack');
				return false;
			}
		}
	}
	doOperacion('solicitar');
	//alert('Registro correcto');
}

function maximoPacks(packs){
	var frm = document.forms[0];
	var packsescrito;
	packescrito = frm.cantPack.value;
	if(packescrito <=packs)
		armarPack();
	else{
		alert('El n\xFAmero de Packs ingresado excede el m\xE1ximo permitido ');
		frm.cantPack.focus();
		frm.cantPack.value='';
		return false;
	}
}

function chgButtons(hlr,index){
	var frm = document.forms[0];

	if(index>=1){	
			if(hlr=='1'){
				frm.listPack_HLR1[index].value='5';
				frm.listPack_HLR2[index].value='';
				frm.listPack_HLR3[index].value='';
				frm.listPack_HLR1[index].readOnly=true;
				frm.listPack_HLR2[index].readOnly=true;
				frm.listPack_HLR3[index].readOnly=true;
			}
			if(hlr=='2'){
				frm.listPack_HLR1[index].value='';
				frm.listPack_HLR2[index].value='5';
				frm.listPack_HLR3[index].value='';
				frm.listPack_HLR1[index].readOnly=true;
				frm.listPack_HLR2[index].readOnly=true;
				frm.listPack_HLR3[index].readOnly=true;
			}
			if(hlr=='3'){
				frm.listPack_HLR1[index].value='';
				frm.listPack_HLR2[index].value='';
				frm.listPack_HLR3[index].value='5';
				frm.listPack_HLR1[index].readOnly=true;
				frm.listPack_HLR2[index].readOnly=true;
				frm.listPack_HLR3[index].readOnly=true;
			}
			if(hlr=='4'){
				frm.listPack_HLR1[index].value='';
				frm.listPack_HLR2[index].value='';
				frm.listPack_HLR3[index].value='';
				frm.listPack_HLR1[index].readOnly=false;
				frm.listPack_HLR2[index].readOnly=false;
				frm.listPack_HLR3[index].readOnly=false;
			}
		}else{
			if(hlr=='1'){
				frm.listPack_HLR1.value='5';
				frm.listPack_HLR2.value='';
				frm.listPack_HLR3.value='';
				frm.listPack_HLR1.readOnly=true;
				frm.listPack_HLR2.readOnly=true;
				frm.listPack_HLR3.readOnly=true;
			}
			if(hlr=='2'){
				frm.listPack_HLR1.value='';
				frm.listPack_HLR2.value='5';
				frm.listPack_HLR3.value='';
				frm.listPack_HLR1.readOnly=true;
				frm.listPack_HLR2.readOnly=true;
				frm.listPack_HLR3.readOnly=true;
			}
			if(hlr=='3'){
				frm.listPack_HLR1.value='';
				frm.listPack_HLR2.value='';
				frm.listPack_HLR3.value='5';
				frm.listPack_HLR1.readOnly=true;
				frm.listPack_HLR2.readOnly=true;
				frm.listPack_HLR3.readOnly=true;
			}
			if(hlr=='4'){
				frm.listPack_HLR1.value='';
				frm.listPack_HLR2.value='';
				frm.listPack_HLR3.value='';
				frm.listPack_HLR1.readOnly=false;
				frm.listPack_HLR2.readOnly=false;
				frm.listPack_HLR3.readOnly=false;
			}
		}
	
}

function ocultarCuerpo(){
	var frm = document.forms[0];
	document.getElementById('cuerpoPack').style.display = 'none';
	frm.botonSolicitarPack.disabled = true;
	frm.buscarHLR.disabled = true;
}

function deshabilitarBotonPack(){

	var frm = document.forms[0];
	frm.botonSolicitarPack.disabled = true;
	frm.botonSeleccionar.disabled= true;


}
*/
/************************************************
	scripts para datosActualizaciones.jsp
	autor: RLV
************************************************/

function ocultarPantalla(){	    
	ocultarDatosPrepago();
	ocultarDatosCuenta();
}
//funcion para activar o desactivar controles en registro/actualizacion de datos prepago	

function actualizarPrepago(){	
	//alert('actualizar prepago');
	var frm = document.forms[0];
	frm.buttonActualizar.disabled = false;
	frm.buttonActualizarCuenta.disabled=true;
	frm.buttonActualizar.value='Actualizar';
	document.getElementById('botoncuenta').style.display='none';
	frm.tipo.value='actualizar';
	frm.nombre.readOnly=true; //disabled - readOnly
	frm.apePat.readOnly=true;
	frm.tipoDoc.disabled=true;
	frm.numeDoc.readOnly=true;
	frm.teleRef.disabled=false;
	frm.sexo[0].disabled=false;
	frm.sexo[1].disabled=false;
	frm.fechaNaci.disabled=false;
	frm.lugarNaci.disabled = false;
	frm.emailUsuario.disabled=false;

}

function actualizarPrepagoTodo(){	
	//alert('actualizar prepago todo');
	var frm = document.forms[0];
	frm.buttonActualizar.disabled = false;
	frm.buttonActualizarCuenta.disabled=true;
	frm.buttonActualizar.value='Actualizar';
	document.getElementById('botoncuenta').style.display='none';
	frm.tipo.value='actualizar';
	frm.nombre.disabled=false; //antes true; disabled
	frm.apePat.disabled=false; //antes true;
	frm.tipoDoc.disabled=false; //antes true;
	frm.numeDoc.disabled=false; //antes true;
	frm.teleRef.disabled=false;
	frm.sexo[0].disabled=false;
	frm.sexo[1].disabled=false;
	frm.fechaNaci.disabled=false;
	frm.lugarNaci.disabled = false;
	frm.emailUsuario.disabled=false;
}

function habilitarPrepago(){
	var frm = document.forms[0];
	frm.nombre.disabled=false;
	frm.apePat.disabled=false;
	frm.tipoDoc.disabled=false;
	frm.numeDoc.disabled=false;
	frm.teleRef.disabled=false;
	frm.sexo[0].disabled=false;
	frm.sexo[1].disabled=false;
	frm.fechaNaci.disabled=false;
	frm.lugarNaci.disable = false;
	frm.emailUsuario.disabled=false;
}
	
//funcion para activar o desactivar controles en registro/actualizacion de datos prepago
function registrarPrepago(){
	//alert('registrar datos prepago');
	var frm = document.forms[0];
	frm.buttonActualizar.disabled = false;
	frm.buttonActualizar.value="Registrar";
	frm.tipo.value="registrar";
	frm.buttonActualizarCuenta.disabled = true;
	document.getElementById('botoncuenta').style.display='none';
	frm.nombre.disabled=false;
	frm.apePat.disabled=false;
	frm.tipoDoc.disabled=false;
	frm.numeDoc.disabled=false;
	frm.teleRef.disabled=false;
	frm.sexo[0].disabled=false;
	frm.sexo[1].disabled=false;
	frm.fechaNaci.disabled=false;
	frm.emailUsuario.disabled=false;
	frm.lugarNaci.disabled=false;
	
    for (i = 0; i < frm.lugarNaci.length; i++) {
      if (frm.lugarNaci[i].value == 'Peru') {
         frm.lugarNaci[i].selected = true;
      }   
   }
}

//no debe mostrarse la seccion de datos del usuario
//y debe permitir modificar datos de la cuenta
function actualizarCuenta(){		
	//alert('actualizar datos cuenta postpago');
	var frm = document.forms[0];
	frm.buttonActualizar.disabled=true;
	frm.buttonActualizarCuenta.disabled=false;
	frm.buttonActualizarCuenta.value="Actualizar";
	frm.tipo.value="actualizarCuenta";

	ocultarDatosPrepago();
	//datos cuenta
	frm.teleRefCon.disabled=false;
	frm.persCon.disabled=false;
	frm.emailCon.disabled=false;
	//direccion facturacion
	frm.dirCalle.disabled=false;
	frm.nomCalle.disabled=false;
	frm.dirNumero.disabled=false;
	frm.dirSn.disabled=false;
	frm.dirMz.disabled=false;
	frm.dirNumeromz.disabled=false;
	frm.dirTipodep.disabled=false;
	frm.dirLote.disabled=false;
	frm.dirNumerodep.disabled=false;
	frm.maxCaracteres1.disabled=false;
	
	//direccion de referencia
	frm.dirUrbanizacion.disabled=false;
	frm.dirRefUrb.disabled=false;
	frm.dirRefZona.disabled=false;
	frm.dirRefNombrezona.disabled=false;
	frm.dirReferencias.disabled=false;
	frm.maxCaracteres2.disabled=false;
	
	//lista de ubigeos
	//frm.dirPais.disabled=false;
	frm.dirDepartamento.disabled=false;
	frm.dirProvincia.disabled=false;
	frm.dirDistrito.disabled=false;
	//frm.dirCodigoPostal.disabled=false;
	
	if(frm.dirMz.selectedIndex==0){
		frm.dirNumeromz.value="";
		frm.dirLote.value="";
		frm.dirNumeromz.readOnly=true;
		frm.dirLote.readOnly=true;
	}
}

function doCancelar(){

}

function ocultarFormularioCuenta(){
	document.getElementById('formularioCuentas').style.display = 'none';
}

function ocultarDatosCuenta(){
	document.getElementById('datosCuenta').style.display = 'none';
	document.getElementById('botoncuenta').style.display='none';
	document.getElementById('link').style.display='none';
}

function ocultarDatosPrepago(){
	document.getElementById('botondatos').style.display='none';
	document.getElementById('datosPrepago').style.display='none';
	document.getElementById('link').style.display='none';
}

function ocultarBotones(){
	document.getElementById('botoncuenta').style.display='none';
	document.getElementById('botondatos').style.display='none';
	document.getElementById('link').style.display='';
}

function ContadorD1(){
	var objTipoVia=document.forms[0].dirCalle;
	var objNombreCalle=document.forms[0].nomCalle;
	var objNroCalle=document.forms[0].dirNumero;
	var objCheck = document.forms[0].dirSn;
	var objManzana=document.forms[0].dirNumeromz;;
	var objLote=document.forms[0].dirLote;
	var objTipoInterior=document.forms[0].dirTipodep;
	var objNroInterior=document.forms[0].dirNumerodep;
	var objTipoManzana=document.forms[0].dirMz;
		
	var strCad='';
	if(objTipoVia.selectedIndex>0 && objTipoVia.value!='')
	{
		strCad = strCad + objTipoVia.options[objTipoVia.selectedIndex].value;				
	}
	if(objNombreCalle.value!='')
	{				
		strCad = strCad + ' ' + objNombreCalle.value;
	}
	if(objNroCalle.value!='' && objNroCalle.disabled!=true)
	{				
		if (objNroCalle.value!='S/N')
		{strCad = strCad + ' ' + objNroCalle.value;}
	}
	if(objCheck.checked)
	{				
		strCad = strCad + ' ' + 'S/N';
	}
	if(objTipoManzana.selectedIndex > 0 && objTipoManzana.value != '')
	{			
		strCad = strCad + ' ' + objTipoManzana.options[objTipoManzana.selectedIndex].value;				
	}																	
	if(objManzana.value!='' && objManzana.disabled!=true)
	{			
		strCad = strCad + ' ' + objManzana.value;
	}
	if(objLote.value!='' && objLote.disabled!=true)
	{
		strCad = strCad + ' LT ' + objLote.value;
	}
	if(objTipoInterior.selectedIndex>0 && objTipoInterior.value!='')
	{
		strCad = strCad + ' ' + objTipoInterior.options[objTipoInterior.selectedIndex].value;					
	}				
	if(objNroInterior.value!='' && objNroInterior.disabled!=true)
	{
		strCad = strCad + ' ' + objNroInterior.value;
	}				
	var strDireccion=new String(strCad);															
	intLon=strDireccion.length;	
	document.forms[0].maxCaracteres1.value = intLon;				
}
		
function ContadorD2()
{
	var objTipoUrbanizacion = document.forms[0].dirUrbanizacion;
	var objNombreUrbanizacion = document.forms[0].dirRefUrb;
	var objTipoZona = document.forms[0].dirRefZona;
	var objNombreEtapa = document.forms[0].dirRefNombrezona;
	var objReferencias = document.forms[0].dirReferencias;
	
	strCad = '';				
	if(objTipoUrbanizacion.selectedIndex>0 && objTipoUrbanizacion.value!='')
	{
		strCad = strCad + objTipoUrbanizacion.options[objTipoUrbanizacion.selectedIndex].value;
	}
	if(objNombreUrbanizacion.value!='' && objNombreUrbanizacion.disabled!=true)
	{						
		strCad = strCad + ' ' + objNombreUrbanizacion.value;
	}				
	if(objTipoZona.selectedIndex>0 && objTipoUrbanizacion.selectedIndex>0 && objTipoZona.value!='')
	{
		strCad = strCad + ' ' + objTipoZona.options[objTipoZona.selectedIndex].value;					
	}	
	if(objTipoZona.selectedIndex>0 && objTipoUrbanizacion.selectedIndex==0)
	{
		strCad = strCad + objTipoZona.options[objTipoZona.selectedIndex].value;					
	}
	if(objNombreEtapa.value!='' && objNombreEtapa.disabled!=true )
	{	
		strCad = strCad + ' ' + objNombreEtapa.value;
	}
	
	if(objReferencias.value!='' && (objTipoUrbanizacion.selectedIndex>0 || objTipoZona.selectedIndex>0))
	{					
		strCad = strCad + ' ' + objReferencias.value;
	}
	else
	{
		strCad = strCad + objReferencias.value;
	}
	var strNotas=new String(strCad);											
	intLon=strNotas.length;
	document.forms[0].maxCaracteres2.value = intLon;				
}

function valida_notas_direccion_vacio(){
	var objTipoUrbanizacion = document.forms[0].dirUrbanizacion;
	var objNombreUrbanizacion = document.forms[0].dirRefUrb;
	var objTipoZona = document.forms[0].dirRefZona;
	var objNombreEtapa = document.forms[0].dirRefNombrezona;
	var objReferencias = document.forms[0].dirReferencias;
	
	if(objTipoUrbanizacion.selectedIndex==0 && objNombreUrbanizacion.value=='' && objTipoZona.selectedIndex==0 && objNombreEtapa.value=='' && objReferencias.value==''){
		return true;
	}
	return false;
}

function valida_notas_direccion(tipo){
	var objTipoUrbanizacion = document.forms[0].dirUrbanizacion;
	var objNombreUrbanizacion = document.forms[0].dirRefUrb;
	var objTipoZona = document.forms[0].dirRefZona;
	var objNombreEtapa = document.forms[0].dirRefNombrezona;
	var objReferencias = document.forms[0].dirReferencias;
	var strCad;
	
	if(valida_notas_direccion_vacio()){
		document.forms[0].nuevaReferenciaFacturacion.value = "";
		return true;
	}
	if(objTipoUrbanizacion.selectedIndex == 0 && objNombreUrbanizacion.value!=''){
		if(tipo=="0"){
			alert('Debes especificar el Tipo de Urbanizaci\xF3n/Residencial/Pueblo Joven');
		}else{
			alert('Debe especificar el Tipo de Urbanizaci\xF3n/Residencial/Pueblo Joven');
		}
		objTipoUrbanizacion.focus();
		return false;
	}
	if(objTipoUrbanizacion.selectedIndex > 0 && objNombreUrbanizacion.value==''){
		if(tipo=="0"){
			alert('Debes especificar el Nombre de la Urbanizaci\xF3n/Residencial/Pueblo Joven');
		}else{
			alert('Debe especificar el Nombre de la Urbanizaci\xF3n/Residencial/Pueblo Joven');
		}
		objNombreUrbanizacion.focus();
		return false;
	}
	if(objTipoZona.selectedIndex == 0 && objNombreEtapa.value!=''){
		if(tipo=="0"){
			alert('Debes especificar el Tipo de Zona/Etapa');
		}else{
			alert('Debe especificar el Tipo de Zona/Etapa');
		}
		objTipoZona.focus();
		return false;
	}	
	if(objTipoZona.selectedIndex > 0 && objNombreEtapa.value==''){
		if(tipo=="0"){
			alert('Debes especificar el Nombre de la Zona/Etapa');
		}else{
			alert('Debe especificar el Nombre de la Zona/Etapa');
		}
		objNombreEtapa.focus();
		return false;
	}
	
	strCad = '';				
	if(objTipoUrbanizacion.selectedIndex > 0 && objNombreUrbanizacion.value != ''){
		strCad = strCad + objTipoUrbanizacion.options[objTipoUrbanizacion.selectedIndex].value;
		strCad = strCad + ' ' + objNombreUrbanizacion.value;
	}
	if(objTipoZona.selectedIndex>0 && objNombreEtapa.value!=''){
		strCad = strCad + ' ' + objTipoZona.options[objTipoZona.selectedIndex].value;
		strCad = strCad + ' ' + objNombreEtapa.value;
	}
	
	if(objReferencias.value!=''){
		if(strCad.length == 0){
			strCad = objReferencias.value;
		}else{
			strCad = strCad + ' ' + objReferencias.value;
		}					
	}
	
	var strNotas = new String(strCad);					
	var intLon = strNotas.length;
	
	if(intLon > 40){
		if(tipo=="0"){
			alert('La longitud de la Referencia supera el m\xe1ximo permitido de 40 caracteres, por favor corr\xEDgelo.');
		}else{
			alert('La longitud de la Referencia supera el m\xe1ximo permitido de 40 caracteres, por favor corr\xEDjalo.');
		}		
		return false;
	}
	document.forms[0].nuevaReferenciaFacturacion.value = strNotas;
	return true;
}

function valida_campo_direccion_vacio(){
	var objTipoVia=document.forms[0].dirCalle;
	var objNombreCalle=document.forms[0].nomCalle;
	var objNroCalle=document.forms[0].dirNumero;
	var objCheck = document.forms[0].dirSn;
	var objManzana=document.forms[0].dirNumeromz;
	var objLote=document.forms[0].dirLote;
	var objTipoInterior=document.forms[0].dirTipodep;
	var objNroInterior=document.forms[0].dirNumerodep;
	var objTipoManzana=document.forms[0].dirMz;
	
	if(objTipoVia.selectedIndex<=0 && objNombreCalle.value=='' && objNroCalle.value=='' && !objCheck.checked && 
		objTipoManzana.selectedIndex<=0 && objManzana.value=='' && objLote.value=='' && objTipoInterior.selectedIndex==0 && objNroInterior.value==''){
		return true;
	}
	return false;
}

function valida_campo_direccion(tipo){
	var objTipoVia = document.forms[0].dirCalle;
	var objNombreCalle = document.forms[0].nomCalle;
	var objNroCalle = document.forms[0].dirNumero;
	var objCheck = document.forms[0].dirSn;
	var objManzana = document.forms[0].dirNumeromz;
	var objLote = document.forms[0].dirLote;
	var objTipoInterior = document.forms[0].dirTipodep;
	var objNroInterior = document.forms[0].dirNumerodep;
	var objTipoManzana = document.forms[0].dirMz;
	var strCad;
	var intLon;
	
	if(valida_campo_direccion_vacio()){
		document.forms[0].nuevaDireccionFacturacion.value="";
		return true;
	}
	
	if(objTipoVia.selectedIndex==0){
		if(tipo=="0"){
			alert('Debes especificar el Tipo de Calle/Avenida/Jir\xF3n');
		}else{
			alert('Debe especificar el Tipo de Calle/Avenida/Jir\xF3n');
		}
		objTipoVia.focus();
		return false;
	}

	if(objNombreCalle.value==''){
		if(tipo=="0"){
			alert('Debes especificar el Nombre de la Calle/Avenida/Jir\xF3n');
		}else{
			alert('Debe especificar el Nombre de la Calle/Avenida/Jir\xF3n');
		}
		objNombreCalle.focus();
		return false;
	}
	
	if(objNroCalle.value==''){
		if(tipo=="0"){
			alert('Debes especificar el N\xFAmero de la Calle/Avenida/Jir\xF3n o seleccionar S/N');
		}else{
			alert('Debe especificar el N\xFAmero de la Calle/Avenida/Jir\xF3n o seleccionar S/N');
		}
		objNroCalle.focus();
		return false;
	}
	
	if ((!objCheck.checked && objNroCalle.value=='') && (objTipoManzana.selectedIndex==0 && objManzana.value=='')){
		if(tipo=="0"){
			alert('Debes especificar el N\xFAmero de la Calle/Avenida/Jir\xF3n');
		}else{
			alert('Debe especificar el N\xFAmero de la Calle/Avenida/Jir\xF3n');
		}
		objNroCalle.focus();
		return false;
	}
	
	if(objTipoManzana.selectedIndex > 0){
		if(objManzana.value==''){
			if(tipo=="0"){
				alert('Debes especificar el N\xFAmero de Manzana/Bloque/Edificio');
			}else{
				alert('Debe especificar el N\xFAmero de Manzana/Bloque/Edificio');	
			}
			objManzana.focus();
			return false;
		}
		if(objTipoManzana.value=='MZ' && objLote.value==''){
			if(tipo=="0"){
				alert('Debes especificar el N\xFAmero de Lote');
			}else{
				alert('Debe especificar el N\xFAmero de Lote');
			}
			objLote.focus();
			return false;
		}
	}
	
	if(objTipoInterior.selectedIndex > 0 && objNroInterior.value==''){
		if(tipo=="0"){
			alert('Debes especificar el N\xFAmero de Departamento/Interior');
		}else{
			alert('Debe especificar el N\xFAmero de Departamento/Interior');
		}
		objNroInterior.focus();
		return false;
	}
	
	if(objTipoInterior.selectedIndex == 0 && objNroInterior.value!=''){
		if(tipo=="0"){
			alert('Debes especificar el Tipo de Departamento/Interior');
		}else{
			alert('Debe especificar el Tipo de Departamento/Interior');
		}
		objTipoInterior.focus();
		return false;
	}
	
	strCad = '';
	strCad = strCad + objTipoVia.options[objTipoVia.selectedIndex].value;
	strCad = strCad + ' ' + objNombreCalle.value;
	if(objNroCalle.value!=''){
		strCad = strCad + ' ' + objNroCalle.value;
	}
	if(objManzana.value!='' && !objManzana.readOnly){
		strCad = strCad + ' ' + objTipoManzana.options[objTipoManzana.selectedIndex].value;
		strCad = strCad + ' ' + objManzana.value;
	}
	if(objLote.value!='' && !objLote.readOnly){
		strCad = strCad + ' LT ' + objLote.value;
	}
	if(objTipoInterior.selectedIndex>0 && objNroInterior.value!=''){
		strCad = strCad + ' ' + objTipoInterior.options[objTipoInterior.selectedIndex].value;
		strCad = strCad + ' ' + objNroInterior.value;
	}
	
	var strDireccion = new String(strCad);
	intLon = strDireccion.length;
	if(intLon>40){
		if(tipo=="0"){
			alert('La longitud de la Direcci\xF3n supera el m\xe1ximo permitido de 40 caracteres, por favor corr\xEDgelo.');
		}else{
			alert('La longitud de la Direcci\xF3n supera el m\xe1ximo permitido de 40 caracteres, por favor corr\xEDjalo.');
		}		
		return false;
	}
	document.forms[0].nuevaDireccionFacturacion.value=strDireccion;
	return true;
}


function cambioUbigeo(tipo){

	var objDpto = document.forms[0].dirDepartamento;
	var objProv = document.forms[0].dirProvincia;
	var objDistrito = document.forms[0].dirDistrito;
	if(tipo=='D'){
		if(objDpto.selectedIndex != 0)
			doOperacion('ubigeoD');
	}
	if(tipo=='P'){
		if(objDpto.selectedIndex != 0)
			doOperacion('ubigeoP');
	}
	return false;
}

function validarCombosUbigeo(tipo)
{	
	var objDpto = document.forms[0].dirDepartamento;
	if(objDpto.selectedIndex == 0){
		if(tipo=="0"){
			alert('Selecciona un Departamento');
		}else{
			alert('Seleccione un Departamento');
		}
		objDpto.focus();
		return false;
	}
	
	var objProv = document.forms[0].dirProvincia;
	if(objProv.selectedIndex == 0){
		if(tipo=="0"){
			alert('Selecciona una Provincia');
		}else{
			alert('Seleccione una Provincia');
		}
		objProv.focus();
		return false;
	}
	
	var objDistrito = document.forms[0].dirDistrito;
	if(objDistrito.selectedIndex == 0){
		if(tipo=="0"){
			alert('Selecciona un Distrito');
		}else{
			alert('Seleccione un Distrito');
		}
		objDistrito.focus();
		return false;
	}
	return true;
}

function IsNumeric(e,elem) 
{				
	if(window.event)
		{
     				keynum = e.keyCode;
		}
	else if(e.which)
		{
				keynum = e.which;
		}										
	return (keynum >= 48 && keynum <= 57)  
			|| (keynum == 45 && currentText == '')  
			|| (keynum == 8) || (keynum == 127) || (keynum==46)
			|| (keynum == 13)	
}

function change_numero(obj){
	if (obj.value!=''){
		document.forms[0].dirNumeromz.disabled=true;
		document.forms[0].dirLote.disabled=true;				
	}
	else{
		document.forms[0].dirNumeromz.disabled=false;
		document.forms[0].dirLote.disabled=false;															
	}	
}	
function Quitar_MZ(){				
	var objTipoManzana = document.forms[0].dirMz;
	
	var i = 0;
	var n = objTipoManzana.options.length;
	var posMZ = "";
	for(i=0; i< n ; i++){
		if (objTipoManzana.options[i].value == "MZ"){
			posMZ = i;
		}
	}
	
	if(document.forms[0].dirNumero.value!="" && !document.forms[0].dirSn.checked){
		if(posMZ != ""){
			objTipoManzana.remove(posMZ);	
		}					
	}
	else{
		if(posMZ == ""){
			objTipoManzana.options[objTipoManzana.options.length] = new Option("MZ - Manzana","MZ");						
		}	
	}
	
	if(!document.forms[0].dirNumero.readOnly){ // && document.forms[0].dirNumero.value!=""
		objTipoManzana.selectedIndex = 0;
		document.forms[0].dirNumeromz.value = "";
		document.forms[0].dirLote.value = "";
		document.forms[0].dirNumeromz.readOnly=true;
		document.forms[0].dirLote.readOnly=true;
	}
}

function Selecciona_TipoUrbanizacion()
{
	var objTipoUrbanizacion = document.forms[0].dirUrbanizacion;
	var objNombreUrbanizacion = document.forms[0].dirRefUrb;
	if(objTipoUrbanizacion.selectedIndex>0)
	{					
		objNombreUrbanizacion.disabled=false;
		objNombreUrbanizacion.focus();
	}
	else
		objNombreUrbanizacion.disabled=true;						
}
			
function Selecciona_TipoZona()
{
	var objTipoZona = document.forms[0].dirRefZona;
	var objNombreEtapa = document.forms[0].dirRefNombrezona;
	if(objTipoZona.selectedIndex>0)
	{
		objNombreEtapa.disabled=false;
		objNombreEtapa.focus();
	}
	else
		objNombreEtapa.disabled=true;						
}

	function CheckSiNo(){
		var objCheck = document.forms[0].dirSn;
		if(objCheck.checked==true){
			document.forms[0].dirNumero.readOnly = true;
			document.forms[0].dirNumero.value="S/N";
		}
		else{
			document.forms[0].dirNumero.readOnly = false;
			document.forms[0].dirNumero.value="";
		}
		
		document.forms[0].dirMz.selectedIndex=0;
		document.forms[0].dirNumeromz.value = "";
		document.forms[0].dirLote.value = "";
		document.forms[0].dirLote.readOnly = true;
		
		Quitar_MZ(document.forms[0].dirNumero);
	}

	function maxLongitudNumDoc(){
		var frm = document.forms[0];
		var control = frm.numeDoc;
		var tipo = frm.tipoDoc.value;
		var maxLength = 10;
		tipo = tipo.toUpperCase();
		if ( tipo == 'DNI' || tipo == 'Libreta Electoral'.toUpperCase()){
			maxLength = 8;  
		}
		if ( tipo == 'RUC' || tipo == 'R.U.C' ){
			maxLength = 11;  
		}
		if ( tipo == 'Pasaporte'.toUpperCase() ){
			maxLength = 10;
		}
		if ( tipo == 'No Precisado'.toUpperCase() ){
			control.value = "";
			maxLength = 0;
		}
		control.maxLength = maxLength ;
		var valor = control.value;
		if (valor != ''){					
			if (valor.length>maxLength)
				control.value = valor.substring(0,maxLength);
		}
	}
	
	function change_manzana(obj){
		if(document.forms[0].dirMz.selectedIndex==0){
			document.forms[0].dirNumeromz.value = "";
			document.forms[0].dirNumeromz.readOnly = true;
			document.forms[0].dirLote.value = "";
			document.forms[0].dirLote.readOnly = true;
			
			if(document.forms[0].dirSn.checked==false){
				document.forms[0].dirNumero.readOnly = false;
			}
			return;
		}
		
		if(document.forms[0].dirMz.value == "MZ"){
			document.forms[0].dirNumero.readOnly = true;
			document.forms[0].dirNumeromz.readOnly = false;
			document.forms[0].dirLote.readOnly = false;
		}else{
			document.forms[0].dirNumeromz.readOnly = false;
			document.forms[0].dirLote.value = "";
			document.forms[0].dirLote.readOnly = true;
			if(document.forms[0].dirSn.checked==false){
				document.forms[0].dirNumero.readOnly = false;
			} 
		}
	}
	
	/*PARA ACTUALIZAR DATOS DE CLIENTE PREPAGO*/
	function doConsultar(flagOperacion,flagActualizaDatos){ 
		var frm = document.forms[0];
		
		if(flagOperacion!="2"){
			if(frm.nombre.value=='') {
				alert('Ingresa tus Nombres.');
				frm.nombre.focus();
				return false; 
			}
		
			if(frm.apePat.value=='') {
				alert('Ingresa tus Apellidos.');
				frm.apePat.focus();
				return false; 
			}
			
			if(frm.tipoDoc.selectedIndex=='0') {
				alert('Selecciona un Tipo de Documento.');
				frm.tipoDoc.focus();
				return false; 
			}
		}
		
		/*VALIDAR LONGITUDES DEL TIPO DE DOCUMENTO, SI ES MENOR DE EDAD NO PIDE NUMDOC*/
		if(frm.tipoDoc.selectedIndex!='7' && frm.tipoDoc.selectedIndex!='1') {
			if(frm.numeDoc.value==''){
				alert('Ingresa el N\xFAmero de Documento.');
				frm.numeDoc.focus();
				return false; 
			}
		}
		/*FIN VALIDAR LONGITUDES*/
		
		if(frm.teleRef.value=='') 
		{
			alert('Ingresa tu Tel\xe9fono de Referencia.');
			frm.teleRef.focus();
			return false; 
		}

		if(flagActualizaDatos=="1"){
			/*ES DOL -> sexo e email no obligatorios*/
			if(!frm.sexo[0].checked && !frm.sexo[1].checked) 
			{	alert('Selecciona el Sexo.');
				return false; 
			}
			
			if(frm.emailUsuario.value =='') 
			{	alert('Ingresa tu E-mail.');
				frm.emailUsuario.focus();
				return false; 
			}
			/*FIN ES DOL*/
		}
		
		if(frm.emailUsuario.value !=''){
			if(!validarFormatoMail(document.forms[0].emailUsuario,'Formato de E-mail incorrecto.'))
				return false;
		}
		
		if(frm.lugarNaci.selectedIndex=='0') 
		{
			alert('Selecciona un Lugar de Nacimiento.');
			frm.lugarNaci.focus();
			return false; 
		}
		
		if(frm.fechaNaci.value=='') 
		{
			alert('Ingresa tu Fecha de Nacimiento.');
			frm.fechaNaci.focus();
			return false; 
		}
		
		if(!validarFormatoFecha(document.forms[0].fechaNaci,'Formato de fecha no v\xE1lida.','Fecha de Nacimiento no v\xE1lida.')){
			frm.fechaNaci.focus();
			return false;
		}
			
		if(document.forms[0].buttonActualizar.value=='Actualizar'){
			if(!confirm('\xBFEst\xE1s seguro de actualizar los datos?')){
				return false;
			}
			try{
				habilitarPrepago();
			}catch(err){}
			doOperacion('actualizar');
		}else{
			if(!confirm('\xBFEst\xE1s seguro de registrar los datos?')){
				return false;
			}
			doOperacion('registrar');
		}
	}

	/*PARA ACTUALIZAR DATOS DE CLIENTE POSTPAGO, CORPORATIVO, B2E*/
	function doActualizarCuenta(tipo){
		var frm = document.forms[0];
		
		if(frm.numeros){
			if(frm.numeros.selectedIndex == 0){
				if(tipo=="0")
					alert('Selecciona un n\xfamero.');
				else
					alert('Seleccione un n\xfamero.');
				frm.numeros.focus();
				return false; 
			}
		}
		
		if(frm.teleRefCon.value==''){
			if(tipo=="0")
				alert('Ingresa tu Tel\xE9fono de Contacto.');
			else
				alert('Ingrese su Tel\xE9fono de Contacto.');
			frm.teleRefCon.focus();
			return false; 
		}
	
		if(frm.persCon.value==''){
			if(tipo=="0")
				alert('Ingresa tu Contacto Cliente.');
			else
				alert('Ingrese su Contacto Cliente.');
			frm.persCon.focus();
			return false; 
		}
		
		if(frm.emailCon.value ==''){
			if(tipo=="0")
				alert('Ingresa tu E-mail.');
			else
				alert('Ingrese su E-mail.');
			frm.emailCon.focus();
			return false; 
		}
		
		if(!validarFormatoMail(document.forms[0].emailCon,'Formato de E-mail incorrecto.')){
			return false;
		}
		
		if(!valida_campo_direccion(tipo)){
			return false;
		}
		
		if(!valida_notas_direccion(tipo)){
			return false;
		}

		if (!validarCombosUbigeo(tipo)){
			return false;
		}

		if(tipo=="0"){		
			if(!confirm('\xBFEst\xE1s seguro de guardar los cambios?')){
				return false;
			}
		}else{
			if(!confirm('\xBFEst\xE1 seguro de guardar los cambios?')){
				return false;
			}
		}

		doOperacion('actualizarcuenta');
	}
/********************************************************
			fin js de datosActualizaciones.jsp
********************************************************/

/*Para popup consulta OST*/
function openDetalleOST(ruta) {
	//window.open(ruta,'ventana','scrollbars=yes, width=680, height=520, toolbar=no, status=no,resizable=no');
	window.open(ruta,'ventana','scrollbars=yes, width=680, height=600, toolbar=no, status=no,resizable=no');
}

//Cuando hay hasta 10 números en el OST  
function validarNumero7(mensaje1,mensaje2,mensaje3,mensaje4,mensajesOST,mensajeFormat,operacion) {
	var frm = document.forms[0];
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
		  var cont=0;
		  for (var i=frm.numeros.options.length-1;i>-1;i--)
		  	{ 
            if (frm.numeros.options[i].selected) 
               cont++;                                          
            }
            if(cont>10){
				showAlertMessage(mensajesOST);
				return false;
			}
			else{
			frm.operacion.value = operacion;
			frm.submit();
			}
		}
	}
	else
	{
		if(frm.numero.value == '')
			showAlertMessage(mensaje1);
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}
			frm.operacion.value = operacion;
			frm.submit();
		}
	}
}

/*Creado para el cambio de Detalle Llamadas Facturadas para obtener 10 líneas a la vez*/

function validarNumeroDetalleLlamadaFacturado3(mensajeFecha,mensaje1,mensaje2,mensaje3,mensaje4,mensajeFormat,operacion) {
	var frm = document.forms[0];
	
	if(frm.grupo.checked)
	{
		if(frm.numeros.selectedIndex == -1)
			showAlertMessage(mensaje2);
		else{
		  var cont=0;
		  for (var i=frm.numeros.options.length-1;i>-1;i--)
		  	{ 
            if (frm.numeros.options[i].selected) 
               cont++;                                          
            }
            if(cont>10){
				showAlertMessage(mensaje3);
				return false;
			}
			else{
					//alert('Si valido numero');
					/*Si el numero es correcto ahora valida la fecha*/
					if(frm.fechaFacturacion.selectedIndex == 0){
						showAlertMessage(mensajeFecha);
					return false;
					}
						//alert('fechaFacturacion: ' + frm.fechaFacturacion.options[frm.fechaFacturacion.selectedIndex].text);
						frm.operacion.value = operacion;
						frm.fechaEmision.value=frm.fechaFacturacion.options[frm.fechaFacturacion.selectedIndex].text;
						//alert('frm.fechaEmision.value: ' + frm.fechaEmision.value);
						frm.submit();
					//frm.operacion.value = operacion;
					//frm.submit();
					
			}
		}
	}
	else{
		if(frm.numero.value == ''){
			showAlertMessage(mensaje1);
			return false;
		}else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
			if(frm.localidad.value == 1){
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje3);
					frm.numero.focus();
					return false;
				}
			}else{
				if(frm.numero.value.length != 9){
					showAlertMessage(mensaje4);
					frm.numero.focus();
					return false;
				}
			}

			if(frm.fechaFacturacion.selectedIndex == 0){
						showAlertMessage(mensajeFecha);
				return false;
			}
					
			frm.operacion.value = operacion;
			frm.fechaEmision.value=frm.fechaFacturacion.options[frm.fechaFacturacion.selectedIndex].text;
			//alert('Si valido fecha y viaja al controlador');
			frm.submit();
								
		}
	}
		

}

/********************************************************
			fin js de datosActualizaciones.jsp
********************************************************/

function trim(cadena)
{
	for(i=0; i<cadena.length; )
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(i+1, cadena.length);
		else
			break;
	}

	for(i=cadena.length-1; i>=0; i=cadena.length-1)
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(0,i);
		else
			break;
	}
	
	return cadena;
}


//Funcion para habilitar y desabilitar campos de la cabecera Nivel 2
function habilitarGrupo2DLlamadas() {
	var frm = document.forms[0];
	if(frm.grupo.checked){
		//alert('Limpio FEcha');
		//frm.fechaFacturacion.value =0;
		//frm.fechaEmision.length =0;
		frm.cuenta.disabled = false;
		/*Al alterar consulta consumo */
		frm.subcuenta.disabled = false;
		frm.numeros.disabled = false;
		frm.numero.disabled = true;
		/*Para limpiarlo*/
		//frm.numero.value = "";
		//alert('Limpio');
		
		frm.localidad.disabled = true;
	}else{
		frm.cuenta.disabled = true;
		/*Al alterar consulta consumo */
		frm.subcuenta.disabled = true;		
		
		frm.numeros.disabled = true;
		frm.numero.disabled = false;
		frm.localidad.disabled = false;
	}
}


/*<Ronald>*/
function validarEmail(email)
{
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
		return true;
	}else {
		return false;
	}
}

function trim(cadena)
{
	for(i=0; i<cadena.length; )
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(i+1, cadena.length);
		else
			break;
	}

	for(i=cadena.length-1; i>=0; i=cadena.length-1)
	{
		if(cadena.charAt(i)==" ")
			cadena=cadena.substring(0,i);
		else
			break;
	}
	
	return cadena;
}


	function deshabilitarPrepago(){
		var frm = document.forms[0];
		frm.nombre.disabled = true;
		frm.apePat.disabled = true;
		frm.tipoDoc.disabled = true;
		frm.numeDoc.disabled = true;
		frm.teleRef.disabled = true;
		frm.sexo[0].disabled = true;
		frm.sexo[1].disabled = true;
		frm.emailUsuario.disabled = true;
		frm.lugarNaci.disabled = true;
		frm.fechaNaci.disabled = true;
	}
	
	function deshabilitarPostpago(){
		var frm = document.forms[0];
		frm.teleRefCon.disabled = true;
		frm.persCon.disabled = true;
		frm.emailCon.disabled = true;
		
		frm.dirCalle.disabled = true;
		frm.nomCalle.disabled = true;
		frm.dirNumero.disabled = true;
		frm.dirSn.disabled = true;
		frm.dirNumeromz.disabled = true;
		frm.dirLote.disabled = true;
		frm.dirTipodep.disabled = true;
		frm.dirNumerodep.disabled = true;
		frm.dirMz.disabled = true;
		
		frm.dirUrbanizacion.disabled = true;
		frm.dirRefUrb.disabled = true;
		frm.dirRefZona.disabled = true;
		frm.dirRefNombrezona.disabled = true;
		frm.dirReferencias.disabled = true;
		
		frm.dirDepartamento.disabled=true;
		frm.dirProvincia.disabled=true;
		frm.dirDistrito.disabled=true;
	}
	
	function esNumero() 
	{ 
		if (event.keyCode < 48 || event.keyCode > 57 ) 
		event.returnValue = false;
	}
	
	/*CPP validar campos de letra y alfanumericos*/
	function esDireccion(evt){
	    if((event.keyCode < 65 || event.keyCode > 90) && (event.keyCode < 97 || event.keyCode > 122) && (event.keyCode < 48 || event.keyCode > 57) && event.keyCode!=32 && event.keyCode!=39 && event.keyCode!=45 && event.keyCode!=95 && event.keyCode!=241 && event.keyCode!=209 && event.keyCode!=225 && event.keyCode!=233 && event.keyCode!=237 && event.keyCode!=243 && event.keyCode!=250 && event.keyCode!=193 && event.keyCode!=201 && event.keyCode!=205 && event.keyCode!=211 && event.keyCode!=218){
	       event.returnValue = false;
	    }
	}
	
	function mostrarMensaje(element, idSpanMensaje, caracteresParaMostrar)
	{
		if(element.value=='')
		{
			var elemento = document.getElementById(idSpanMensaje);
			elemento.style.visibility='hidden';
		}else 
		if(parseInt(element.value.length) < parseInt(caracteresParaMostrar))
		{
			var elemento = document.getElementById(idSpanMensaje);
			elemento.style.visibility='hidden';
		}else {
			var elemento = document.getElementById(idSpanMensaje);
			elemento.style.visibility='visible';
		}
	}
	
	function doCerrar(){
		
		window.close();
	}
	//VALIDACIONES SI SON NUMERO
	function soloNumeros(evt){
        // NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57
        var key = evt.keyCode ? evt.keyCode : evt.which ;
        return (key <= 40 || (key >= 48 && key <= 57));
    } 
    
    function validarNumero(valor){
		if(/^[0-9]+$/.test(valor)) 
			return true;
		else
			return false;
	}
	
	
	function disableEnableForm(xForm,xHow){
	  objElems = xForm.elements;
	  for(i=0;i<objElems.length;i++){
	    objElems[i].disabled = xHow;
	  }
	}
	
/*fin*/

//abre popUp configura tu claro
function openConfiguraTuClaro(ruta) {
	window.open(ruta,'ventana','scrollbars=no, width=760, height=600, toolbar=no, status=no,resizable=no');
}

function noCopyMouse(e) {
        var isRight = (e.button) ? (e.button == 2) : (e.which == 3);
        
        if(isRight) {
            return false;
        }
        return true;
    }
    
    function noCopyKey(e) {
        var forbiddenKeys = new Array('c','x','v');
        var keyCode = (e.keyCode) ? e.keyCode : e.which;
        var isCtrl;

        if(window.event)
            isCtrl = e.ctrlKey
        else
            isCtrl = (window.Event) ? ((e.modifiers & Event.CTRL_MASK) == Event.CTRL_MASK) : false;
    
        if(isCtrl) {
            for(i = 0; i < forbiddenKeys.length; i++) {
                if(forbiddenKeys[i] == String.fromCharCode(keyCode).toLowerCase()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    function validarNumeroPortal(mensaje1,mensaje2,operacion) {
	
	var frm = document.forms[0];

	if(frm.telefono.value != ''){
		
		if(frm.telefono.value.length != 9){
				muestraMensaje(mensaje2);
				frm.telefono.focus();
				return false;
				}
	
		frm.operacion.value = operacion;
		frm.submit();
	}else{
		muestraMensaje(mensaje1);
		frm.telefono.focus();
	}
}