function vTextArea(form, motivo)
{
    var nCantidadCaracteres = 250;

    if (motivo.length >= nCantidadCaracteres)
    {
            var d=confirm("Su motivo excede de 250 caracteres y sera truncado.\nSi desea modificarlo, presione Cancelar.");
            if (d) 
            {
            motivo=motivo.substring(0, nCantidadCaracteres-1);
            return false;
            } 
            else
                return false;
    }
    return true;
}


/*********************************************************************************************************
* Funcin: ValidaFecha
* Descripcin:  Valida que la cadena contenga una fecha en formato dd/mm/aaaa.
* Par?etros:
*    control:  un control de formulario que tenga la propiedad value
* Resultado:
*    Si control.value representa una fecha v?ida, retorna true.
*    Caso contrario retorna false y el control quedar?seleccionado.
* Observaciones:
*    Esta funcin valida aos bisiestos, soporta d?s y meses de uno y dos d?itos, y aos de 4 d?itos.
*********************************************************************************************************/
function vFecha(form, dia, mes, ano) 
{
    var mensaje = "Revise la fecha ingresada";
    var bresult
	RegExpFecha = /^\s*(\d{1,2})\/(\d{1,2})\/(\d{4})\s*$/
	function esBisiesto(a) {
		return (((a%4==0)&&(a%100!=0))||(a%400==0))
	}
        var fecha = dia + '/' + mes + '/' + ano;

	if (RegExpFecha.test(fecha)) {
		var dia = parseInt(RegExp.$1,10)
		var mes = parseInt(RegExp.$2,10)
		var anno = parseInt(RegExp.$3,10)
		if ((dia>0) && (dia<=31) && (mes>0) && (mes<=12)) {
			switch (mes) {
				case  4 :
				case  6 :
				case  9 : 
				case 11 :
					bresult = (dia < 31)
					break
				case  2 :
					bresult = (dia < 29 || ((dia == 29) && esBisiesto(anno)))
					break
				default:
					bresult = true
			}   
		}
		else
			bresult = false
	}
	else
		bresult = false
    if (!bresult)   
        {
        alert(mensaje);
        form.dia.focus();
        }
    return bresult
}



function vCharsB(objEvent) 
{
    //browser detection
    var strUserAgent = navigator.userAgent.toLowerCase(); 
    var isIE = strUserAgent.indexOf("msie") > -1; 
    var isNS6 = strUserAgent.indexOf("netscape6") > -1; 
    var isNS4 = !isIE && !isNS6  && parseFloat(navigator.appVersion) < 5; 

    //regular expressions
    var reValidDigits = /\d/;

    //regular expressions for alphanumerics
    var reValidAlpha = /\w/i;

    var iKeyCode, strKey;  

    if (isIE) {
        iKeyCode = objEvent.keyCode;
    } else {
        iKeyCode = objEvent.which;
    }

    strKey = String.fromCharCode(iKeyCode);

    //aqui van todos los keycodes que quieres dejar pasar...
    if (iKeyCode == 8) return true; //el backspace...
    if (iKeyCode == 32) return true; //el espacio...

    //aqui van todos los keycodes que no quieres dejar pasar...
    if (iKeyCode == 95) return false; //el underscore...
    if (iKeyCode == 241) return false; //la n con tilde

    if (reValidAlpha.test(strKey) && !reValidDigits.test(strKey))
        {//si es alfanumerico y no es caracter -> es letra
        //alert("Valid Character Detected!\nKeyCode = " + iKeyCode + "\nCharacter =" + strKey);
        }
    else
        {
        //alert("Invalid Character Detected!\nKeyCode = " + iKeyCode + "\nCharacter =" + strKey);
        return false;
        }
}

//funcion que filtra que solo ingreser letras mayusculas o minusculas 
function vChars(objEvent) 
{
    //browser detection
    var strUserAgent = navigator.userAgent.toLowerCase(); 
    var isIE = strUserAgent.indexOf("msie") > -1; 
    var isNS6 = strUserAgent.indexOf("netscape6") > -1; 
    var isNS4 = !isIE && !isNS6  && parseFloat(navigator.appVersion) < 5; 

    //regular expressions
    var reValidDigits = /\d/;

    //regular expressions for alphanumerics
    var reValidAlpha = /\w/i;

    var iKeyCode, strKey;  

    if (isIE) {
        iKeyCode = objEvent.keyCode;
    } else {
        iKeyCode = objEvent.which;
    }

    strKey = String.fromCharCode(iKeyCode);

    //aqui van todos los keycodes que te quieres saltear...
    if (iKeyCode == 8) return true; //el backspace...

    //aqui van todos los keycodes que no quieres dejar pasar...
    if (iKeyCode == 95) return false; //el underscore...
    if (iKeyCode == 241) return false; //la n con tilde

    if (reValidAlpha.test(strKey) && !reValidDigits.test(strKey))
        {//si es alfanumerico y no es caracter -> es letra
        //alert("Valid Character Detected!\nKeyCode = " + iKeyCode + "\nCharacter =" + strKey);
        }
    else
        {
        //alert("Invalid Character Detected!\nKeyCode = " + iKeyCode + "\nCharacter =" + strKey);
        return false;
        }
}

//funcion que filtra que solo ingreser numeros
function vNums(objEvent) 
{
    //browser detection
    var strUserAgent = navigator.userAgent.toLowerCase(); 
    var isIE = strUserAgent.indexOf("msie") > -1; 
    var isNS6 = strUserAgent.indexOf("netscape6") > -1; 
    var isNS4 = !isIE && !isNS6  && parseFloat(navigator.appVersion) < 5; 

    //regular expressions
    var reValidDigits = /\d/;

    var iKeyCode, strKey;  

    if (isIE) {
        iKeyCode = objEvent.keyCode;
    } else {
        iKeyCode = objEvent.which;
    }

    strKey = String.fromCharCode(iKeyCode);

    //aqui van todos los keycodes que quieres dejar pasar...
    if (iKeyCode == 8) return true; //el backspace...
    //if (iKeyCode == ??) return true; //me falta dejar pasar el tab...

    if (!reValidDigits.test(strKey))
        {//si es alfanumerico y no es caracter -> es letra
        //alert("Valid Character Detected!\nKeyCode = " + iKeyCode + "\nCharacter =" + strKey);
        return false;
        }
}




/* Funcion para validar el codigo de contribuyente */
       function validarIdContribuyente(idcontribuyente) 
	   {
        var cadena = "Debe ingresar correctamente el código de contribuyente";
         if( idcontribuyente=="" || idcontribuyente.indexOf('@',0) != -1  || idcontribuyente.indexOf(';',0) != -1
         || idcontribuyente.indexOf('/',0) != -1  || idcontribuyente.indexOf(' ',0) == 0
         || idcontribuyente.indexOf(';',0) != -1 || idcontribuyente.indexOf('<',0) != -1
         || idcontribuyente.indexOf('>',0) != -1 || idcontribuyente.indexOf('*',0) != -1
         || idcontribuyente.indexOf('|',0) != -1 || idcontribuyente.indexOf('¨',0) != -1
         || idcontribuyente.indexOf('&',0) != -1 || idcontribuyente.indexOf('$',0) != -1
         || idcontribuyente.indexOf('!',0) != -1 || idcontribuyente.indexOf('"',0) != -1
         || idcontribuyente.indexOf(':',0) != -1 )
         { alert(cadena); return false; }
         else return true;
      }

/* Funcion para validar el codigo del predio */
       function validarIdPredio(idpredio) 
	   {
        var cadena = "Debe ingresar correctamente el código del predio";
         if( idpredio=="" || idpredio.indexOf('@',0) != -1  || idpredio.indexOf(';',0) != -1
         || idpredio.indexOf('/',0) != -1  || idpredio.indexOf(' ',0) == 0
         || idpredio.indexOf(';',0) != -1 || idpredio.indexOf('<',0) != -1
         || idpredio.indexOf('>',0) != -1 || idpredio.indexOf('*',0) != -1
         || idpredio.indexOf('|',0) != -1 || idpredio.indexOf('¨',0) != -1
         || idpredio.indexOf('&',0) != -1 || idpredio.indexOf('$',0) != -1
         || idpredio.indexOf('!',0) != -1 || idpredio.indexOf('"',0) != -1
         || idpredio.indexOf(':',0) != -1 )
         { alert(cadena); return false; }
         else return true;
      }
	
/* Funcion para validar el codigo del Solicitud */
       function validarTipoSolicitud(idTipoSolicitud) 
	   {
        var cadena = "Debe seleccionar el tipo de solicitud";
         if( idTipoSolicitud=="")
         { alert(cadena); return false; }
         else return true;
       }  
	
/* Funcion para validar el dia */
       function validarDia(dia) 
	   {
        var cadena = "Debe seleccionar el dia";
         if( dia=="")
         { alert(cadena); return false; }
         else return true;
       }  
	   
/* Funcion para validar el mes */
       function validarMes(mes) 
	   {
        var cadena = "Debe seleccionar el mes";
         if( mes=="")
         { alert(cadena); return false; }
         else return true;
       }  
	   
/* Funcion para validar el año */
       function validarAno(ano) 
	   {
        var cadena = "Debe ingresar correctamente el año";
         if( ano=="" || ano.indexOf('@',0) != -1  || ano.indexOf(';',0) != -1
         || ano.indexOf('/',0) != -1  || ano.indexOf(' ',0) == 0
         || ano.indexOf(';',0) != -1 || ano.indexOf('<',0) != -1
         || ano.indexOf('>',0) != -1 || ano.indexOf('*',0) != -1
         || ano.indexOf('|',0) != -1 || ano.indexOf('¨',0) != -1
         || ano.indexOf('&',0) != -1 || ano.indexOf('$',0) != -1
         || ano.indexOf('!',0) != -1 || ano.indexOf('"',0) != -1
         || ano.indexOf(':',0) != -1 )
         { alert(cadena); return false; }
         else return true;
      }

/* Funcion para validar el motivo */
       function validarMotivo(txtMotivo) 
	   {
        var cadena = "Debe ingresar el motivo de la solicitud";
         if( txtMotivo=="")
         { alert(cadena); return false; }
         else 
         return true;
       }  	  
	  
	  
	  
  function fValidarRegistroSolicitud(Form, action, url)
  {
    if (validarIdContribuyente(Form.idContribuyente.value)
      && validarIdPredio(Form.idPredio.value)
	  && validarTipoSolicitud(Form.idTipoSolicitud.value)
 	  && validarMotivo(Form.txtMotivo.value)
      )
		{
		Form.OPTIONSERVLET.value=action;
		Form.action=url;
		Form.submit();
		}
	else
		return false;
  }


/* Función para confirmar la actualización del registro */

   function ActualizarSiNo()
 {
  var si = window.confirm("DESEA ACTUALIZAR EL REGISTRO");
  if(si)
  {
   return true;
  }
  else
  {
   return false;
  }
 }

/* Función para confirmar la adición de un nuevo registro */

   function AnadirSiNo()
 {
  var si = window.confirm("DESEA AÑADIR ESTE REGISTRO");
  if(si)
  {
   return true;
  }
  else
  {
   return false;
  }
 }


/* Función para validar los campos con el nombre y apellidos */

  function ValidarNombre(nombre1, apaterno, amaterno)  {
    var cadena = "El 1er nombre, el Apellido Paterno y el Apellido Materno son obligatorios.\n"
      + "No se puede llevar a cabo el registro, revise bien sus datos";

    if ( nombre1 == "" || apaterno == "" || amaterno == "")  {
      alert(cadena);
      return false;
      }
    else return true;
    }

/* Función para validar el anexo */

   function ValidarAnexo(anexo) {
     var cadena = "DEBE INGRESAR AL MENOS EL ANEXO PRINCIPAL";
     if ( anexo == "" || anexo.indexOf(' ',0) != -1 )
     {
      alert(cadena);
      return false;
     }
     else return true;
    }


    /* Función para validar la dirección de e-mail */

  function ValidarEmail(email){
    var cadena = "Direccion de correo no valida: " + email
      + "\nPor favor, introduce bien tu direccion";
     if(email=="")
    {
     return true;
    }
     else{
         if( email.indexOf('@',0) <= 0  || email.indexOf(';',0) != -1
         || email.indexOf('/',0) != -1  || email.indexOf(' ',0) != -1
         || email.indexOf(';',0) != -1 || email.indexOf('<',0) != -1
         || email.indexOf('>',0) != -1 || email.indexOf('*',0) != -1
         || email.indexOf('|',0) != -1 || email.indexOf('¨',0) != -1
         || email.indexOf('&',0) != -1 || email.indexOf('$',0) != -1
         || email.indexOf('!',0) != -1 || email.indexOf('"',0) != -1
         || email.indexOf(':',0) != -1 )
          { alert(cadena); return false; }
         else return true;
         }
   }

/* Función para validar el nombre de usuario y la clave */

  function ValidarFicha(ficha)  {
    var Error0 = "La Ficha del Usuario contiene algún carácter extraño, revíselo";

  /* Aquí podeis poner tantos caracteres no permitidos por
     vosotros como querais (dentro del if) */

    if(ficha == ""){
    return true;
    }else{
      if(
        ficha.indexOf(' ',0) != -1 || ficha.indexOf('ñ') >= 0
        || ficha.indexOf('?') >= 0 || ficha.indexOf('á') >= 0
        || ficha.indexOf('é') >= 0 || ficha.indexOf('í') >= 0
        || ficha.indexOf('ó') >= 0 || ficha.indexOf('ú') >= 0
        || ficha.indexOf('-') >= 0 || ficha.indexOf('*') >= 0
        || ficha.indexOf('_') >= 0 || ficha.indexOf('/') >= 0
        || ficha.indexOf('+') >= 0 || ficha.indexOf('.') >= 0
        || ficha.indexOf(';') >= 0 || ficha.indexOf(':') >= 0
        || ficha.indexOf('´') >= 0 || ficha.indexOf('¿') >= 0
        || ficha.indexOf('%') >= 0 || ficha.indexOf('|') >= 0
        || ficha.indexOf('"') >= 0 || ficha.indexOf('¡') >= 0
        || ficha.indexOf('!') >= 0 || ficha.indexOf('°') >= 0
        || ficha.indexOf('¬') >= 0 || ficha.indexOf(',') >= 0
        || ficha.indexOf('&') >= 0 || ficha.indexOf('¿') >= 0
        ){
        alert(Error0);
        return false;
        }else return true;
     }
    }




















