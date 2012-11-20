var browser=navigator.appName;
if (browser=="Microsoft Internet Explorer"){
         	 alert("explorer"); 
}
if (browser=="Netscape"){
	loadjscssfile("../css/ie.css","css");
         	 alert("firefox"); 
}

document.write("<br>");

if (/MSIE (\d+\.\d+);/.test(navigator.userAgent)){ //test for MSIE x.x;
 var ieversion=new Number(RegExp.$1);// capture x.x portion and store as a number
 if (ieversion==9)
  document.write('<LINK href="../css/ie.css" rel="stylesheet" type="text/css">');
 else if (ieversion==8)
  document.write('<LINK href="../css/ie.css" rel="stylesheet" type="text/css">');
 else if (ieversion==7)
  document.write('<LINK href="../css/ie.css" rel="stylesheet" type="text/css">');
 else if (ieversion==6)
  document.write("You're using IE6");
 else if (ieversion==5)
  document.write("You're using IE5");
 else
	 loadjscssfile("../css/prueba.js","js");
}  

function loadjscssfile(filename, filetype)
{
var fileref=null;
if (filetype=="js"){ //if filename is a external JavaScript file
fileref=document.createElement('script');
fileref.setAttribute("type","text/javascript");
fileref.setAttribute("src", filename);
}
else if (filetype=="css"){ //if filename is an external CSS file
fileref=document.createElement('link');
fileref.setAttribute('rel', 'stylesheet');
fileref.setAttribute('type', 'text/css');
fileref.setAttribute('href', filename);
}
alert('estoy en loadjscssfile');
if (typeof fileref!="undefined"){
alert('validando tipo de archivo');
alert('archivo '+ fileref);
document.getElementsByTagName("head")[0].appendChild(fileref);
}
}