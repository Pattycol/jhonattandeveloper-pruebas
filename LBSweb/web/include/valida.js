 //Variables globales
 var dtCh= "/";			//Separador de fecha
 var minYear=1900;		//a�o minimo
 var maxYear=2100;		//a�o m�ximo
 function valida_numero(xinput,tipval)
  {
    var xkey=event.keyCode;
	if(tipval=="int")
		if ((xkey < 48) || (xkey > 57)) event.returnValue = false;
	if(tipval=="dec")
		{ if ((xkey < 46) || (xkey > 57)) event.returnValue = false;
                  }  
	if(tipval=="str")
		if (((xkey != 32) && (xkey < 65)) || ((xkey > 90) && (xkey < 97))) event.returnValue = false;
	if(tipval=="tlf")
		if (((xkey != 32) && (xkey < 45)) || (xkey > 57)) event.returnValue = false;
		
	if (tipval=="afn")	
	 { if (  ((xkey != 32) && (xkey < 45))  || (xkey > 57)   ||
	           ((xkey < 48) || (xkey > 57))   )   event.returnValue = false;
 	  }
		
  }
//********************************************************************************************//
function esTeclaCodigo(e){
var valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_%";
var key = String.fromCharCode(event.keyCode);
if (valid.indexOf("" + key) == "-1") return false;	
}
//********************************************************************************************//
function esTeclaTexto(e) {
var valid = "abcdefghijklm�nopqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ���������� ";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaTodas(e) {
var valid = "abcdefghijklm�nopqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ :.+-*/=!?$%()#&0123456789_������������@";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}
function esTeclaTodasComodin(e) {
var valid = "abcdefghijklm�nopqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ :;.,-/()#&0123456789_������������";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}

//********************************************************************************************//
function esTeclaNumero(e) {
var valid = ",0123456789";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}

//********************************************************************************************//
/*function esTeclaNumeroSinComa(e) {
	var valid = "0123456789";
	var key = String.fromCharCode(e.keyCode);
	if (valid.indexOf("" + key) == "-1") 
		return false;
	return true;
}*/
function esTeclaNumeroSinComa(e) 
{ 
	var key = window.Event ? e.which : e.keyCode; 
	return ((key >= 48 && key <= 57) || (key==8)); 
}
//********************************************************************************************//

function esNada(e) {
var valid = "";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//

function esTeclaDecimal(e) {
var valid = "0123456789,.";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaRangoNumeros(e) {
var valid = "0123456789,-";
var key = String.fromCharCode(event.keyCode);
	if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esEmail(string) //string = cadena que representa al correo electronico
{//valida si la entrada es un correo electronico si es cierto devuelve true
	  if (string.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
		  return true;
		else
		  return false;
}
//********************************************************************************************//
function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
//********************************************************************************************//
function compararFechaMayor(strFecha,strFechaMayor){
	var fecha1 = strFecha.split("/");
	var fecha2 = strFechaMayor.split("/");	

	var strFecha1 = parseFloat(fecha1[2] + fecha1[1] + fecha1[0]);
	var strFecha2 = parseFloat(fecha2[2] + fecha2[1] + fecha2[0]);
		 		 
	if (strFecha1 > strFecha2) {
		return false; }
	else { return true; }  
}
////********************************************************************************************//
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31;
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30;}
		if (i==2) {this[i] = 29;}
   } 
   return this;
}

//********************************************************************************************//
function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}
//********************************************************************************************//
//Validaci�n de n�meros
function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}
//********************************************************************************************//
function isDate(dtStr){
	var daysInMonth = DaysArray(12);
	var pos1=dtStr.indexOf(dtCh);
	var pos2=dtStr.indexOf(dtCh,pos1+1);
	var strDay=dtStr.substring(0,pos1);
	var strMonth=dtStr.substring(pos1+1,pos2);
	var strYear=dtStr.substring(pos2+1);
	strYr=strYear;
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1);
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1);
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1);
	}
	month=parseInt(strMonth);
	day=parseInt(strDay);
	year=parseInt(strYr);
	if (pos1==-1 || pos2==-1){
		alert("El formato de fecha debe ser : dd/mm/yyyy");
		return false;
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Por favor ingrese un mes válido");
		return false;
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Por favor ingrese un dia válido");
		return false;
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Por favor ingrese un año de 4 digitos entre "+minYear+" y "+maxYear);
		return false;
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Por favor ingrese una fecha válida");
		return false;
	}
	return true;
}
//********************************************************************************************//
function esFecha(day,month,year) {

  if ((day.length!=2) || (month.length!=2) || (year.length!=4)) {
     return false;
  }
  if (month>12) return false;
  if ((month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) && day>=32){
      return false;
  }
  if ((month==4 || month==6 || month==9 || month==11) && day>=31){
      alert("El mes de "+ fcnConvertirMes(month) +" s�lo tiene 30 dias!");
      return false;
  }
  if (month == 2){
      var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
      if (day>29 || (day==29 && !isleap)){
      alert("Febrero de " + year + " no tiene " + day + " dias!");
      return false;
      }
  }
  return true;
}
//*************************************************************************************//
function esVacio(cad) { //retorna true si la cadena est� vac�a 
	var es_vacio;
    var blanco = " \n\t" + String.fromCharCode(13); // blancos
	if ((cad == null) || (cad.length == 0)) { es_vacio=true; }
		else
		{
    		for(i = 0, es_vacio = true; (i < cad.length) && es_vacio; i++)
      			es_vacio = blanco.indexOf(cad.charAt(i)) != - 1;
		}
	return es_vacio;
}
//*************************************************************************************//
function esNaN(cad) { //retorna true si la cadena no es un n�mero
	var aux=cad.replace(/,/g,""); //Le quitamos todas las comas y comprobamos que sea un n�mero
	if (isNaN(aux)) return true; //Si no es un numero sin las comas, devuelve error.
	else
	{   //Validamos el formato de las comas. Primero vemos si hay comas. Si no las hay , es n�mero.
		if (cad.indexOf(',') == -1) return false;
		//Ahora si tiene punto decimal
		if (cad.indexOf('.') != -1)
		{   var arregloSplit=cad.split(".");
			//Verifica que no hayan comas en la parte decimal
			if (! ( /^\d*$/.test(arregloSplit[1]) )) return true;
			aux=arregloSplit[0];
		}
		else aux=cad;
		//var regexp=/(^-?\d{1,3}$)|(^-?\d{1,3},\d{3}$)/;//|(^-?\d+,\d{3},\d{3}$)/;
		var regexp=/^-?\d{1,3}(,\d{3})*$/;
		return !regexp.test(aux);
		
	}
}
//
function esNaI(cad)
{//Retorna true si la cadena no es un n�mero entero.
	if ( cad.indexOf('.') != -1) return true;
	return esNaN(cad);
}
//**************************************************************************************//
function enRango(cad, nroMin, nroMax)
{  //cad puede tener coma separadora de miles, pero nroMin y nroMax no
	if (esNaN(cad) || isNaN(nroMin) || isNaN(nroMax)) return false;
	var aux=cad.replace(/,/g,""); //Le quitamos todas las comas 
	return ( ( aux > nroMin ) && ( aux < nroMax ) );
}
//*************************************************************************************//
//El arreglo que necesita la funci�n esVacio, necesita objetos creados con esta funcion
function entry(key,value)
{
	this.key=key;
	this.value=value;
}
//*************************************************************************************//
function validaVacio(ArrCad,textoError) { //retorna true si alguna cadena est� vac�a o tiene espacios en blanco
  var es_vacio;
  var error=false;
  var mensajeError="";
  if(!isNaN(ArrCad.length)){
  	for (j=0;j<ArrCad.length;j++)
	{   
		cad=ArrCad[j].value;
    	es_vacio=esVacio(cad);
		if (es_vacio)
		{
		  	error=true;
			textoAux=textoError.replace(/\$0/,ArrCad[j].key);
			mensajeError+=textoAux+"\n";
		}
	}
	if(error)
		alert(mensajeError);	
  }
  return(error);
}
//*************************************************************************************//
function validaNumero(ArrCad,textoError)
{ //retorna true si alguna de las cadenas no es numero.
  var no_es_numero=true;
  var error=false;
  var mensajeError="";
  if(!isNaN(ArrCad.length)){
	for (j=0;j<ArrCad.length;j++)
	{   
		cad=ArrCad[j].value;
    	no_es_numero=esNaN(cad);
		
		if (no_es_numero)
		{
		  	error=true;
			textoAux=textoError.replace(/\$0/,ArrCad[j].key);
			mensajeError+=textoAux+"\n";
		}
	}
	if(error)
		alert(mensajeError);	
  }
  return(error);
}
//*************************************************************************************//
function validaNoNumero(ArrCad,textoError)
{ //retorna true si alguna de las cadenas no es numero.
  var es_numero=true;
  var error=false;
  var mensajeError="";
  if(!isNaN(ArrCad.length)){
	for (j=0;j<ArrCad.length;j++)
	{   
		cad=ArrCad[j].value;
    	es_numero=!isNaN(cad);
		
		if (es_numero)
		{
		  	error=true;
			textoAux=textoError.replace(/\$0/,ArrCad[j].key);
			mensajeError+=textoAux+"\n";
		}
	}
	if(error)
		alert(mensajeError);	
  }
  return(error);
}
//*************************************************************************************//
function validaEntero(ArrCad,textoError)
{
  var no_es_entero=true;
  var error=false;
  var mensajeError="";
  if(!isNaN(ArrCad.length)){
	for (j=0;j<ArrCad.length;j++)
	{   
		cad=ArrCad[j].value;
    	no_es_entero=esNaI(cad);
		
		if (no_es_entero)
		{
		  	error=true;
			textoAux=textoError.replace(/\$0/,ArrCad[j].key);
			mensajeError+=textoAux+"\n";
		}
	}
	if(error)
		alert(mensajeError);	
  }
  return(error);
}
//*************************************************************************************//
function validaPorcentaje(ArrCad,textoError)
{ //retorna true si alguna de las cadenas no es n�mero.
  var no_es_pctje=true;
  var error=false;
  var mensajeError="";
  if(!isNaN(ArrCad.length)){
	for (j=0;j<ArrCad.length;j++)
	{   
		cad=ArrCad[j].value;
    	no_es_pctje=!enRango(cad,0,100);
		if (no_es_pctje)
		{
		  	error=true;
			textoAux=textoError.replace(/\$0/,ArrCad[j].key);
			mensajeError+=textoAux+"\n";
		}
	}
	if(error)
		alert(mensajeError);	
  }
  return(error);
}
//*************************************************************************************//
function Trim(dato) { 
  return String(dato).replace(/[\s]/g,"");
}
//*************************************************************************************//
// Recibe como parametro un obj de tipo checkbox (arreglo u objeto simple) 
// y devuelve true si alguno esta seleccionado
function estaSeleccionado(obj) { 
   check = false;
   if(isNaN(obj.length)) {
       check = obj.checked;
   }
   else {
   longitud = obj.length;   		
       for(i = 0; i < longitud; i++) {
          if(obj[i].checked == true) {
              check = true;
              break;
          }
       }
   }
   return check;
}

//*************************************************************************************//
function mensaje(){
	obj = document.getElementById("errores"); 
	if(obj.style.display=='none')
		obj.style.display='';
	else
		obj.style.display='none'; 
}
//*************************************************************************************//
function replace(linea, antiguo,nuevo){
   res = eval('linea.replace(/'+antiguo+'/g, \"'+nuevo+'\")'); 
   return res;
}
//*************************************************************************************//
function validaRangoFecha(fechaInicio,fechaFin){
   diaInicio=fechaInicio.substr(0, 2);
   mesInicio=fechaInicio.substr(3, 2);
   annoInicio=fechaInicio.substr(6, 4);
   
   diaFin=fechaFin.substr(0, 2);
   mesFin=fechaFin.substr(3, 2);
   annoFin=fechaFin.substr(6, 4);

   if(parseInt(annoFin) < parseInt(annoInicio)){                                                        
                    return false;
                }		
    if(parseInt(annoFin) == parseInt(annoInicio)){                    
        if(parseInt(mesFin) < parseInt(mesInicio)){                                                                                 
                return false;                    
        }
    }                            
    if(parseInt(annoFin) == parseInt(annoInicio)){                    
        if(parseInt(mesFin) == parseInt(mesInicio)){                                                                 
            if(parseInt(diaFin) < parseInt(diaInicio)){                                                                                 
                return false;    
            }
        }
    }
   
   return true;
}

