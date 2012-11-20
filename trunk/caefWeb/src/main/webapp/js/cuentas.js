/*
	Administra las Cuentas Madre
	----------------------------*/
	
/*
	validar cuentas genérico
	------------------------------------*/
function validaCuentasMadre()
{
	var frm = document.forms[0];
	var operacion = 'consultar';
	
	//si existe grupo
	if( frm.grupo )
	{ 	//si esta chekado
		if( frm.grupo.checked )
		{
			if( validaCuentas() )
			{
				//colocar todas las validaciones adicionales
				if( validacionesAdicionales() )
				{
					doOperacion(operacion);
				}
			}
		//si no esta chekado
		}else
		{
			if( validaNumero() )
			{
				//colocar todas las validaciones adicionales
				if( validacionesAdicionales() ){
					doOperacion(operacion);
				}
			}
		}
	}else //si no existe grupo
	{
		//y si existe cuentas y subcuentas //caso recibos
		if( validaNumero() && validaCuentas())
		{
			if( validacionesAdicionales() )
			{	
				//para detalle llamadas facturado
				/*if(frm.localidad){
					frm.localidad.value="";
				}*/
				doOperacion(operacion);	
			}
		}
	}
}

/*
	Función para habilitar y desabilitar campos para todos los niveles
*/

function habilitarGrupo()
{
	var frm = document.forms[0];
	
	if( frm.grupo){
		if( frm.grupo.checked )
		{	
			if( frm.cuenta ){
				frm.cuenta.disabled = false;
			}
			if( frm.subcuenta ){
				frm.subcuenta.disabled = false;
			}
			if( frm.numeros ){
				frm.numeros.disabled = false;
			}
			if( frm.numero ){
				frm.numero.disabled = true;
			}
			if( frm.localidad ){
				frm.localidad.disabled = true;
			}	
		}else
		{
			if( frm.cuenta ){
				frm.cuenta.disabled = true;
			}
			if( frm.subcuenta ){
				frm.subcuenta.disabled = true;
			}
			if( frm.numeros ){
				frm.numeros.disabled = true;
			}
			if( frm.numero ){
				frm.numero.disabled = false;
			}
			if( frm.localidad ){
				frm.localidad.disabled = false;
			}
		}
	}
}


/* Valida la selección de alguna cuenta o ingreso de número Claro */
function validaCuentas()
{
	var frm 	= document.forms[0];
	var mensajeCuenta		= 'Seleccione el n\xfamero de Cuenta.';
	var mensajeSubCuenta	= 'Seleccione el n\xfamero de Subcuenta.';
	var mensajeCombo		= 'Formato de n\xfamero incorrecto.';
	var mensajeCuentaVacia = 'El campo Cuenta est\xe1 vac\xedo.';
	
	//existe cuenta
	if(frm.cuenta)
	{
		if(frm.cuenta.type=='text'){
			if(frm.cuenta.value==''){
				showAlertMessage(mensajeCuentaVacia);
				frm.cuenta.focus();
				return false;
			}
		}
		else{
			//si no se ha seleccionado ninguna cuenta
			if(frm.cuenta.options.selectedIndex ==0){
				showAlertMessage(mensajeCuenta);
				frm.cuenta.focus();
				return false
			}else  //se ha seleccionado alguna cuenta
			{
				//preguntar si existe subcuenta o si existen numeros
				if(frm.subcuenta){
					if(frm.subcuenta.options.selectedIndex == 0){
						showAlertMessage(mensajeSubCuenta);
						frm.subcuenta.focus();
						return false;
					}else{
						if(frm.numeros){
							return validaNumerosClaro();
						}
					}
					return true;
				}
				if(frm.numeros)
				{
					return validaNumerosClaro();
				}
			}
		}
	}
	return true;
}

function validaNumerosClaro(){
	var frm 	= document.forms[0];
	var mensajeNumeros		= 'Seleccione un n\xfamero.';
	
	if(frm.numeros.multiple){
		if(frm.numeros.options.selectedIndex ==-1){
			showAlertMessage(mensajeNumeros);
			frm.numeros.focus();
			return false;
		}
		return validaNumeroLineasSeleccionadas();
	}else{
		if(frm.numeros.options.selectedIndex ==0){
			showAlertMessage(mensajeNumeros);
			frm.numeros.focus();
			return false;
		}
	}
	return true;
}

function validaTipoPerfil()
{
	var frm = document.forms[0];
	var mensaje 	= 'Seleccione el Tipo de Perfil.';
	
	if(frm.tipoPerfil){
		if(frm.tipoPerfil.type!='hidden'){
			if(frm.tipoPerfil.options.selectedIndex ==0){
				showAlertMessage(mensaje);
				frm.tipoPerfil.focus();
				return false;
			}
		}
	}
	return true;
}

function validaCategoria()
{
	var frm = document.forms[0];
	var mensaje 	= 'Seleccione la Categor\xeda.';
	
	if(frm.categoria){
		if(frm.categoria.options.selectedIndex ==0){
			showAlertMessage(mensaje);
			frm.categoria.focus();
			return false;
		}
	}
	return true;
}


/* retorna true si no existe el campo numero*/
function validaNumero()
{
	var frm = document.forms[0];
	var mensaje1 		= 'Ingrese un n\xfamero Claro.';
	var mensaje2 		= 'Ingrese un n\xfamero Claro.';
	var mensajeFormat 	= 'Formato de n\xfamero incorrecto.';
	
	//si existe campo numero
	if(frm.numero){
		//validar campo numero
		if(frm.numero.value == ''){
			showAlertMessage(mensaje1);
			frm.numero.focus();
			return false;
		}
		else{
			var patron=/[^0-9]/;
			if(patron.exec(frm.numero.value)){
				showAlertMessage(mensajeFormat);
				frm.numero.focus();
				return false;
			}
	
			if(frm.numero.value.length != 9){
					showAlertMessage(mensajeFormat);
					frm.numero.focus();
					return false;
				}
			
		}
	}
	return true;
}

// valida fechas de facturación
// si existe la valida de no existir devuelve true
function validaFechaFacturacion()
{
	var frm = document.forms[0];
	if(frm.fechaFacturacion){
		var mensajeFecha = 'Seleccione Fecha de Emisi\xf3n o Cuenta.';
		if(frm.fechaFacturacion.selectedIndex == 0){
			showAlertMessage(mensajeFecha);
			frm.fechaFacturacion.focus();
			return false;
		}
	}
	return true;
}

//valida las fechas  de inicio y fin
function validaFechaInicFin()
{
	var frm = document.forms[0];
	if(frm.fechaInicial && frm.fechaFinal)
	{
		return DiferenciaFechas();
	}
	return true;
}

/*Agregado para diferencia de fechas*/
function DiferenciaFechas()
{
	var formulario = document.forms[0];
	
	var CadenaFechaFinal = formulario.fechaFinal.value;
	var CadenaFechaInicial = formulario.fechaInicial.value;	   
	
	//obtengo las fechas hidden que son las que no se deberian exceder
	var FechaFinalHidden = formulario.fechaFinalHidden.value;
	var FechaInicialHidden = formulario.fechaInicialHidden.value;
	
	var a = validaFormatoFecha(CadenaFechaInicial);
	var b = validaFormatoFecha(CadenaFechaFinal);
	
	 //validamos el formato de las fechas  vacias
	if( CadenaFechaFinal == '' || CadenaFechaInicial == '' )
	{
   		//alert ('Ud. debe ingresar las dos fechas.');
   		return false;
   	}else
   	{	//validamos formato de fechas
   		if(a && b)
   		{
		   //Obtiene dia, mes y año del formulario
		   var fechaFinal = new fecha( CadenaFechaFinal );   
		   var fechaInicial = new fecha( CadenaFechaInicial );
		   //Obtiene día, mes y año del hidden
		   var fechaFinH = new fecha( FechaFinalHidden );   
		   var fechaInicH = new fecha( FechaInicialHidden );
		   
		   var miFechaFinal = new Date( fechaFinal.anio, fechaFinal.mes, fechaFinal.dia );
		   var miFechaInicial = new Date( fechaInicial.anio, fechaInicial.mes, fechaInicial.dia );
		   
		   var miFechaFinH = new Date( fechaFinH.anio, fechaFinH.mes, fechaFinH.dia );
		   var miFechaIniH = new Date( fechaInicH.anio, fechaInicH.mes, fechaInicH.dia );	   
		   
		   if ( miFechaInicial < miFechaIniH )
		   {
			  //alert('Para visualizar el Detalle de ciclos anteriores, ingresar a "Detalle de Llamadas Facturado".');
			  formulario.fechaInicial.focus();
			  formulario.fechaInicial.select();
			  return false;
		   }else if( miFechaFinal < miFechaInicial )
		   {
		  	////alert('Fecha final debe ser menor a la fecha actual');
		  	//alert('La Fecha Inicial debe ser menor o igual a la Fecha Final.');
		  	//formulario.fechaFinal.focus();
		  	return false;
		   }
		   else if( miFechaFinal > miFechaFinH )
		   {
			  	////alert('Fecha final debe ser menor a la fecha actual');
			  	//alert('Sólo es posible consultar el Detalle de Llamadas del ciclo en curso.');
			  	formulario.fechaFinal.focus();
			  	return false;
		   }else
		  		return true;
	   	}
		else
	 	{
	 		//alert('Formato de fechas Incorrecto.');
	 		if(a == false){
	 			formulario.fechaFinal.select();
	 			return false;
	 		}
	 		if(b == false){
	 			formulario.fechaInicial.select();
	 			return false;
	 		}
	 	}
	}
}

function validaFormatoFecha(fecha)
{
    var regexp = /^((?:0?[1-9])|(?:[12]\d)|(?:3[01]))\/((?:0?[1-9])|(?:1[0-2]))\/((?:19|20)\d\d)$/;
 	var userPattern = new RegExp(regexp);
 	var m = userPattern.exec(fecha);
	if( m == null ){
	    return false;
	}
	return true;
}


function validaNumeroLineasSeleccionadas()
{
	var frm = document.forms[0];
	var numeroMaxLineas = parseInt(frm.numeroMaxLineas.value);
	var mensaje = 'Debe seleccionar como m\xe1ximo ' + numeroMaxLineas + ' n\xfameros.';
	var cont = 0;
	
	if(frm.numeroMaxLineas){
		if(frm.numeroMaxLineas.value != null && frm.numeroMaxLineas.value != '')
		{
			for (var i=frm.numeros.options.length-1;i>-1;i--)
			{ 
				if (frm.numeros.options[i].selected) 
					cont++;                                          
		   	}
		   	if(cont > numeroMaxLineas){
				showAlertMessage(mensaje);
				frm.numeros.focus();
				return false;
			}
		}
	}
	return true;
}

function validacionesAdicionales(){
	return validaFechaFacturacion() && validaFechaInicFin() && validaTipoPerfil() && validaCategoria();
}

/*
	habilita el boton Exportar a Excel
*/
function habilitarExportar(){
	var frm = document.forms[0];
	if(frm.buttonExportar){
		frm.buttonExportar.disabled = false;	
	}
}

function enviarDetalleLlamadaFacturado(mensajeFecha,operacion) {
	var frm = document.forms[0];
    //showAlertMessage('Ingresa a Enviar');
	if(frm.fechaFacturacion.selectedIndex == 0){
		showAlertMessage(mensajeFecha);
		frm.fechaFacturacion.focus();
	}
	else{
		frm.operacion.value = operacion;
		frm.submit();
	}
}


//  mensajeFecha 	-->Seleccione Fecha de Emisión o Cuenta.
//  operacion 	 	-->enviar
function enviarDetalleLlamadaNoFacturado(mensajeFecha,operacion) {
	var frm = document.forms[0];
    //showAlertMessage('Ingresa a Enviar');
	if(frm.fechaInicial.value == '' || frm.fechaFinal.value == ''){
		showAlertMessage(mensajeFecha);
		if(frm.fechaInicial.value == '')	
			frm.fechaInicial.focus();
		else
			frm.fechaFinal.focus();
	}
	else{
		frm.operacion.value = operacion;
		frm.submit();
	}
}
