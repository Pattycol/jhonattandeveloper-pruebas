function f_VerificarSoloNumeros()
{
	if (f_VerificarSoloNumeros.arguments.length < 1)
	{
		return true;
	}	

	var l_Permitidos = new Array('1','2','3','4','5','6','7','8','9','0');
	var l_sNumero = f_VerificarSoloNumeros.arguments[0];
	var l_sEtiqueta = f_VerificarSoloNumeros.arguments[1];
	var l_sAceptarVacio = f_VerificarSoloNumeros.arguments[2];
	var l_sObjeto = f_VerificarSoloNumeros.arguments[3];

	if (l_sNumero.length < 1)
	{
		if (l_sAceptarVacio == "si")
		{
			return true;
		}
		else
		{
			alert("El campo " + l_sEtiqueta + " debe contener un valor");
			if (l_sObjeto.length > 0)
			{
				eval("document.forms[0]." + l_sObjeto + ".focus()");
			}
			return false;
		}
	}


	for ( var i=0; i  < l_sNumero.length ; i++ )
	{
		var l_sCaracterEncontrado = "no";
		for ( var j=0; j  < l_Permitidos.length ; j++ )
		{
			if (l_sNumero.charAt(i) == l_Permitidos[j])
			{
				l_sCaracterEncontrado = "si";
				break;
			}
		}
		if (l_sCaracterEncontrado == "no")
		{

			alert("El campo " + l_sEtiqueta + " contiene caracteres que no son n?meros");
			if (l_sObjeto.length > 0)
			{
				eval("document.forms[0]." + l_sObjeto + ".focus()");
			}
			return false;
		}
	}
	return true;
}

/**** MENU ALEATORIO BOF ****/

var contents=new Array();
for(i=0;i<=9;i++){
	contents[i]=i;
}

function poblarBoton(){
	var i=0;
	var cae = "btcae";
	var aleatorio;
	while (i < contents.length) {
		aleatorio = Math.floor(Math.random()*contents.length);
		if (contents[aleatorio] != "selected") {
			var celcae=cae+i;
			var valor=contents[aleatorio];
			document.getElementById(celcae).value=valor;
			contents[aleatorio] = "selected";
			i++;
		}
	}
}

/*** MENU ALEATORIO EOF ***/

PREF="BETA";			// Preferred service ID
		MSNBETA="BETA";		// MSN Beta service ID
		MSNSTD="MSN";			// MSN standard service ID
		GEOIP=true;
	
	var curbrowse = BrowserSniff();

	document.onmousedown = function(){
//	document.getElementById("Dropdown").style.display = "none";
}

function InitLoginNav(){
if(document.getElementById("login_nav")) {
	var fnodes = document.getElementsByTagName("form");
//this already set in index.php
/*
	for(var i=0;i<fnodes.length;i++){
		fnodes[i].onsubmit = messcookie;
	}
*/ 
	var nodes = document.getElementById("login_nav").getElementsByTagName("a");
	var preSelect = doPreSelect();
//	alert(preSelect);
	for(var i=0;i<nodes.length;i++){
		if (nodes[i].getAttribute("id") == "BETA") {
			nodes[i].onclick = doMenuClickBeta;
		}else {
			nodes[i].onclick = doMenuClick;
		}
		nodes[i].nr = nodes[i].getAttribute("pid");
	}
	
	for(var i=0;i<nodes.length;i++){
		if (nodes[i].getAttribute("id") == preSelect)
		{
			var div = document.getElementById(preSelect+"_form");
			showForm([nodes[i],div]);	// show preselected form and bridge
			break;
		}
	}
//	document.getElementById(MSNBETA+"_form").onclick = doMenuClickBeta;	// checks MSNBETA compatibility on click
	
	nodes[i].parentNode.current = [nodes[i], document.getElementById(nodes[i].getAttribute("id") + "_form")];
}
}
function InitLangDropdown(){
	var o = document.getElementById("language");
	o.onmousedown = function(e){
		document.getElementById("Dropdown").style.display = "none";
		(e || event).cancelBubble = true;
	}

	var nodes = document.getElementById("Dropdown").getElementsByTagName("a");
	for(var i=0;i<nodes.length;i++){
		nodes[i].onclick = function(e){
			document.getElementById("language").getElementsByTagName("img")[0].src = this.firstChild.src;
			document.getElementById("Dropdown").style.display = "none";
			(e || event).cancelBubble = true;
		}
	}
}

function doPreSelect(){
	var cookie=getcookie('Emessenger');
	
	var pXres = (document.all ? document.documentElement.offsetWidth : window.innerWidth);
	var pYres = (document.all ? document.documentElement.offsetHeight : window.innerHeight);
//alert('X: '+pXres+' Y: '+pYres);
	var urlEnc = location.href.split("#")[1];

	
	var IDs = document.getElementById("login_nav").getElementsByTagName("a");
	
  for (var i=0; i < IDs.length; i++){
  	if (urlEnc==IDs[i].getAttribute("id")) {PREF=urlEnc;return(PREF);}	// if urlencoded string is a service ID, select it
  }
//cookie = false;
	if (cookie) { PREF=cookie;return(PREF); }	// if cookie was set, retrieve and select service
	else {
		if (pXres<801 && pYres<601) {	PREF=MSNSTD;return(PREF);	}	// res < 800x600 select a certain service
		else{
			if (!GEOIP) {	PREF=MSNSTD;return(PREF);	} // GEOIP is false ? select a certain service
			else {
			 	if (BrowserSniff()=="IE_old") {	PREF=MSNSTD;return(PREF);	} // IE<5.5 select a certain service
			}
		}
	}
	return(PREF);
}

function doMenuClickBeta(){
	if (curbrowse == "IE5.5" || curbrowse == "IE6" || curbrowse == "IE7"|| curbrowse == "MOZ")
	{
		if(this.parentNode.current) hideForm(this.parentNode.current);	
		var div = document.getElementById(MSNBETA+"_form");
		this.parentNode.current = [this, div];
		
		showForm(this.parentNode.current);
		this.blur();
		PREF=MSNBETA;
	}
	else {
		alert("Upgrade to Internet Explorer 5.5 and up or Mozilla Firefox to use this service");
	} 
}

function doMenuClick(){
	if(this.parentNode.current) hideForm(this.parentNode.current);

	var div = document.getElementById(this.getAttribute("id") + "_form");
	this.parentNode.current = [this, div];

	showForm(this.parentNode.current);
	this.blur();
	PREF=(this.getAttribute("id"));
}

function showForm(nodes){
	nodes[1].className = "current";

	document.getElementById("bridge").style.marginTop = (nodes[0].nr*50) + "px";
	document.getElementById("bridge").firstChild.style.top = (nodes[0].nr*-46) + "px";
	document.getElementById("loginBgImg").style.top = (nodes[0].nr*-246) + "px";
}

function hideForm(nodes){
	if(nodes[0].className == "current")
		nodes[0].className = "";
	nodes[1].className = "";
}

function messcookie(){
	setcookie("Emessenger",PREF,new Date().getTime()+31536000000);
	return false;
}

/********* COOKIE METHODS *********
	Cookie Handling Methods
**********************************/

function setcookie(name, value, expire, path, domain, secure){
	var ck = name + "=" + escape(value) + ";";
	if(expire) ck += "expires=" + new Date(expire + new Date().getTimezoneOffset()*60).toGMTString() + ";";
	if(path) ck += "path=" + path + ";";
	if(domain) ck += "domain=" + domain;
	if(secure) ck += "secure";

	document.cookie = ck;
	return true
}

function getcookie(name){
  var aCookie = document.cookie.split("; ");
  for (var i=0; i < aCookie.length; i++){
    var aCrumb = aCookie[i].split("=");
    if (name == aCrumb[0])
      return unescape(aCrumb[1]);
  }
  return "";
}

/********* BROWSER DETECT *********
	Browser Detection Methods
**********************************/

function BrowserSniff(){
		var agt = navigator.userAgent.toLowerCase();
		if (document.layers) return "NS";
		if (document.all)
		{
			if( navigator.appVersion.indexOf("MSIE 5.5") != -1) return "IE5.5";
			if (navigator.appVersion.indexOf("MSIE 6") != -1) return "IE6";
			if (navigator.appVersion.indexOf("MSIE 7") != -1) return "IE7";
			return "IE_old";
		}
		if (document.getElementById) return "MOZ";
		if	(agt.indexOf("mac")!=-1) return "MACIE";
		return "OTHER";
	}
	
function setPassword(v,id,mensaje){
	var cantidadMaxima = document.getElementById(id).value.length;
	if(cantidadMaxima < 6){
	var a = document.getElementById(id).value;
	var b = a + v;
	document.getElementById(id).value = b;
	}else {
		alert(mensaje);
		return;
	}
}

function setPassword_p(v,w){
	var a = document.getElementById(w).value;
	var b = a + v;
	document.getElementById(w).value = b;
}


