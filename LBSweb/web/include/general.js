

window.status="LBSweb";

function abrirAyudaFormato(){
    var vURL = "pages/ayudaFormato.html";
    newWin = window.open(vURL, "LBS", "toolbar=no, left=200, top=200, width=375, height=200,directories=no,status=no,scrollbars=auto,resizable=0,menubar=no");
}

//*************************************************************************************//
function calendario(nom_campo, formato) {
var matParam = new Array(3);
matParam[0] = window;
matParam[1] = nom_campo;
matParam[2] = formato;
window.showModalDialog('/LBSweb/ventanaCalendario.htm',matParam,'dialogHeight:210px; dialogWidth:280px;center:yes; help:no; resizable:no; status:no');
}
//**********************************************

function maximize(){
  if (window.screen) {
    f = document.forms[0];    
    var aw = screen.availWidth;
    var ah = screen.availHeight;
    window.moveTo(0, 0);
    window.resizeTo(aw, ah);
  }
}

//**********************************************
function ir_pagina(objeto, tipo_pagina, pagina, ruta) {
	with(objeto) {
		if (tipo_pagina=="S") 	hid_pagina.value = pagina;
		if (tipo_pagina=="A") 	hid_pagina.value = pagina;
		if (tipo_pagina=="N") 	hid_pagina.value = cmb_paginacion.value;
		action = ruta;
		submit();		
	}
}

//***********************************************
function check_all(form)  {
	for(i=0 ; i<form.elements.length; i++)	{ 
		if(form.elements[i].type == "checkbox")	
             form.elements[i].checked = (form.elements[i].checked) ? false : true;
	}
}
//***********************************************
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
//******************************************
function goFrameIzq(url){
    window.parent.frmIzq.location=url;
}

function goFrameDer(url){
    window.parent.frmDer.location=url;
}

function gotog(url){
    document.location.href = url;
}
function goMethodDemo(numform ,action,metodo){
	/*alert(action);
	alert(metodo);*/
	var f=document.forms[numform];
	//alert(f.name);
	f.action=action+".do?method="+metodo;
	f.submit();
}

function goMethod(metodo) {
	//alert(metodo);
       f = document.forms[0];
       f.method.value=metodo;
       f.submit();
}
//*****************************************
function abreBusqueda(){
    var atributos="toolbar=no,Location=no,directories=no,channelmode=no,menubar=no,status=yes,scrollbars=no,resizable=no,width=375,height=500,fullscreen=no,top=100,left=100";
    var vURL = "BusquedaNumero.do?method=inicio";
    newWin = window.open(vURL,"BusquedaNumeros",atributos);
    newWin.focus();
}

//*******scripts para display tag
function onClickCheckBox(field,id){
	var checkedAll = 0;
	var noCheckedAll = 0;
	var objts	= 0;
	var existsTableWithChecks = false;

	for(var i=0;i<document.forms[0].length;i++){
		if(document.forms[0].elements[i].type=="checkbox"){
			if(document.forms[0].elements[i].name != "_checkBox" + id && document.forms[0].elements[i].name == field ) {
				objts ++;
				if( document.forms[0].elements[i].checked )
					checkedAll ++;
				else
					noCheckedAll ++;
			}
			if(document.forms[0].elements[i].name == "_checkBox" + id)
				existsTableWithChecks = true;
		}		
	}
	
	if(existsTableWithChecks){
		if (checkedAll == objts && objts>0)
			document.all("_checkBox"+id).checked= true;
		else if (noCheckedAll <= objts || objts==0)
			document.all("_checkBox"+id).checked= false;
	}
}

function exportarXls(){
	document.forms[0].method.value = "exportarXls";
	document.forms[0].submit();
}

function exportarPdf(){
	document.forms[0].method.value = "exportarPdf";
	document.forms[0].submit();
}
function goPage(url){
	document.forms[0].method.value = "siguiente";
	document.forms[0].action = url;
	document.forms[0].submit();
}
function onClick(field){
	if(field.type=="radio" && typeof(field.fields)!="undefined"){
		document.all.item(field.fields.split(",")[0]).focus();
	}
}
function onClickField(checkFieldName,field){
	selectAllFields(checkFieldName,field.checked);
}
function selectAllFields(field,boolean)	{
	for(var i=0;i<document.forms[0].length;i++){
		if(document.forms[0].elements[i].type=="checkbox" && document.forms[0].elements[i].name==field && !document.forms[0].elements[i].disabled)
			document.forms[0].elements[i].checked = boolean;
	}
}

function selectAll(boolean)	{
	for(var i=0;i<document.forms[0].length;i++){
		if(document.forms[0].elements[i].type=="checkbox")
			document.forms[0].elements[i].checked = boolean;
	}
}
