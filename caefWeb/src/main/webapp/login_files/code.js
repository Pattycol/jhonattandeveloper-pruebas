
/*
Hide SELECT tag
*/

/*
function hideElms(elmTag) {
	for (i=0; i<document.all.tags(elmTag).length; i++){
		obj = document.all.tags(elmTag)[i];
		if (!obj || !obj.offsetParent) continue;
		obj.style.visibility = "hidden";
	}
}
function showElms(elmTag) {
	for (i=0; i<document.all.tags(elmTag).length; i++){
		obj = document.all.tags(elmTag)[i];
		if (!obj || !obj.offsetParent) continue;
		obj.style.visibility = "visible";
	}
}
*/
/*
function:
Open Pop up window
*/
function popUp(URL) {
day = new Date();
id = day.getTime();
eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=500,height=500,left = 390,top = 177');");
}


function toggleBox(szDivID, iState) // 1 visible, 0 hidden
{
   var obj = document.layers ? document.layers[szDivID] :
   document.getElementById ?  document.getElementById(szDivID).style :
   document.all[szDivID].style;
   obj.visibility = document.layers ? (iState ? "show" : "hide") :
   (iState ? "visible" : "hidden");
}

function auto_hide_layer() {
	window.setTimeout('show_layer()',3000);
	window.setTimeout('hide_layer()', 18000);
	}

function hide_layer() {
	if($('demodiv')) {
		$('demodiv').style.visibility = 'hidden';
	}
}
function show_layer() {
	if($('demodiv')) {
		$('demodiv').style.visibility = 'visible';
	}	
	
}
/*
function hide_layer() {
	(document.getElementById) ? dom = true : dom = false;
	if (dom) {document.getElementById("demodiv").style.visibility='hidden';}
  		if (document.layers) {
			document.layers["demodiv"].visibility='hide';
		}
  //showElms('SELECT');
}
*/
function JSFX_FloatTopLeft(div_id)
{
	var startX = 5, startY = 100;
	var ns = (navigator.appName.indexOf("Netscape") != -1);
	var d = document;
	var px = document.layers ? "" : "px";

	function ml(id)
	{
		var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
		if(d.layers)el.style=el;
		el.sP=function(x,y){this.style.left=x+px;this.style.top=y+px;};
		el.x = startX; el.y = startY;
		return el;
	}
	window.stayTopLeft=function()
	{
		var pY = ns ? pageYOffset : document.documentElement && document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
		if(pY >200) startY =10;
		if(pY <200) startY = 100;
		ftlObj.y += (pY + startY - ftlObj.y)/8;
		ftlObj.sP(ftlObj.x, ftlObj.y);
		setTimeout("stayTopLeft()", 40);
	}
	ftlObj = ml(div_id);
	stayTopLeft();
}



function alertSize() {
  var myWidth = 0, myHeight = 0;
  var winsize = new Array();
  if( typeof( window.innerWidth ) == 'number' ) {
    //Non-IE
    myWidth = window.innerWidth;
    myHeight = window.innerHeight;
  } else if( document.documentElement &&
      ( document.documentElement.clientWidth || document.documentElement.clientHeight ) ) {
    //IE 6+ in 'standards compliant mode'
    myWidth = document.documentElement.clientWidth;
    myHeight = document.documentElement.clientHeight;
  } else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
    //IE 4 compatible
    myWidth = document.body.clientWidth;
    myHeight = document.body.clientHeight;
  }
  winsize['width'] = myWidth;
  winsize['height'] = myHeight;
return winsize;
}


function over(obj){
  obj.style.color = '#99ccff';
}
function leave(obj){
  obj.style.color = '#ffffff';
}
function down(obj){
  obj.style.color = '#3c3c3c';
}

// AIM
function doAimLogin() {
  var myWin = window.open('', 'contact', "width=470, height=500, menubar=no,  toolbar = no, scrollbars=no, resizable=yes, location=no");
  var pass = document.getElementById('aim_password');
  var p = document.getElementById('aim_p');
  pass.value = p.value;
  p.value= '';
  return true;
}
// YAHOO
function doYahooLogin() {
  var myWin = window.open("", "yahoo", "width=450, height=510, menubar=no,  toolbar = no, scrollbars=no, resizable=yes, location=no");
  var pass = document.getElementById('yahoo_password');
  var p = document.getElementById('yahoo_p');
  pass.value = p.value;
  p.value= '';
  return true;
}
// MSN

function SetCookie(cookieName,cookieValue,nDays) {
 var today = new Date();
 var expire = new Date();
 if (nDays==null || nDays==0) nDays=1;
 expire.setTime(today.getTime() + 3600000*24*nDays);
 document.cookie = cookieName+"="+escape(cookieValue)
                 + ";expires="+expire.toGMTString();
}


function doMsnLogin(){
  window.open('', 'msn', 'width=480,height=500,status=yes,toolbar=no,menubar=no,location=no,resizable=yes', true);
  var pass = document.getElementById('emsn_password');
  var p = document.getElementById('emsn_p');
  pass.value = p.value;
  p.value= '';
  return true;
}

function checkMsnLogin(){
  var pass = document.getElementById('emsn_passport');
  if (pass.value.indexOf('@') < 0){
    pass.value = pass.value + "@hotmail.com";
  }
}

function clickSecure(type){
  var frm = document.getElementById('emsn_form');
  var s = document.getElementById('secure');
  if(type == 'aim'){
    frm = document.getElementById('aim_form');
    s = document.getElementById('aim_secure');
  }else if (type=='yahoo'){
    frm = document.getElementById('yahoo_form');
    s = document.getElementById('yahoo_secure');
  }
  var a = frm.action;
  if (s.checked) 
    frm.action = a.replace('http://', 'https://');
  else
    frm.action = a.replace('https://', 'http://');
}

function MM_swapImgRestore() { 
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { 
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { 
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { 
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
