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
			fxStyle: 2, 
			slideEnter: 'left'
		});

	})
	
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
 
 function xxlogin(user,pass)
 {	var frm = document.forms[0];
 	frm.login.value=user;
 	frm.password.value=pass;
 	doConsultar('','loginP','personas_p','localidadC');
 }
 try {
	 var pageTracker = _gat._getTracker("UA-9464957-1");
	 pageTracker._trackPageview();
	 } catch(err) {}