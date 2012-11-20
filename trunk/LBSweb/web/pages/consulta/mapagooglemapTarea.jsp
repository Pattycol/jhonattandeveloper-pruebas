<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.stconsulting.common.util.Constants" %>
<%@ page import="com.stconsulting.common.util.Helper" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>::</title>
<%
  String rutaMapa=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_RUTA_HOST_MAPA);
  System.out.println("Samirs probo la Ruta " + rutaMapa);
%>
<link rel="stylesheet" type="text/css" href="/include/jsGM/thickbox.css"/>

<!-- <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAAnnV32Xc4MtQYyTUhtfwkchSmgqw7Xz_HQReRzOtxMk5xOFYUChS5CMTBS-bBdKE6nZEFZ6c6sEw9nQ" type="text/javascript"></script> -->
	  <script src="http://maps.google.com/maps/api/js?client=gme-paginastelmexperu&sensor=false"></script> 
<script language="javascript" type="text/javascript" src="/include/jsGM/jquery.js"></script>
<script language="javascript" type="text/javascript" src="/include/jsGM/thickbox.js"></script>

<script type="text/javascript">
	// Iniciamos jQuery
	
	$(document).ready(function(){

		$("#direccion").keyup(function(){
			$("#form_mapa").submit();
		});
		
		load();
	});
	
	
    var map      = null;
    var geocoder = null;
    var gdir ;
    var ValorX ;
    var ValorY ;
    var pathCoordinates = []; 
    
    function load() {   
        var textHidden = document.getElementById("textHidden").value;
        var numero ;
      
        //alert("en el load "); 
     //   if (GBrowserIsCompatible()) {
        	 
            // map.addControl(new GSmallMapControl());
     	   	 //map.addControl(new GMapTypeControl());
			var YI=  document.getElementById("VALORY0").value;
        	var XI=  document.getElementById("VALORX0").value;
        	var YF=  document.getElementById("VALORY"+ parseInt(textHidden-1)).value;
        	var XF=  document.getElementById("VALORX"+ parseInt(textHidden-1)).value;
        	var FI= document.getElementById("FECHA0").value;
        	var FF= document.getElementById("FECHA"+ parseInt(textHidden-1)).value;
        
     	 //  var myLatLng = new google.maps.LatLng(-1.098565496040652,-82.8369140625);
     	   var myLatLng = new google.maps.LatLng(YI,XI);
     	   var myOptions = {
     	     zoom: 13,
     	     center: myLatLng,
     	    // mapTypeId: google.maps.MapTypeId.TERRAIN
     	    mapTypeId: google.maps.MapTypeId.ROADMAP
     	     
     	   };

     	    map = new google.maps.Map(document.getElementById("map"), myOptions);
  
          var points=[];
          for (var i = 0; i < textHidden; i++) {
  	
  	    	VALORY1= document.getElementById("VALORY"+i).value; 
  	     	VALORX1= document.getElementById("VALORX"+i).value; 
          points[i]=new google.maps.LatLng(VALORY1, VALORX1);
        
          }
          var flightPath = new google.maps.Polyline({
        	    path: points,
        	    strokeColor: "#FF0000",
        	    strokeOpacity: 1.0,
        	    strokeWeight: 2,
        	    zoomFactor :106
        	  });

        	  flightPath.setMap(map);
      
        	  pathCoordinates = new google.maps.MVCArray(); 
        	  var polyOptions = { 
        	    strokeOpacity: 0 
        	  } 
        	  poly = new google.maps.Polyline(polyOptions); 
        	  poly.setMap(map); 
        	
        	var coordenadaI = new google.maps.LatLng(YI,XI);
        	var coordenadaF = new google.maps.LatLng(YF,XF);
        	var marker = new google.maps.Marker(
                	  {map: map, 
                	 title: FI,
                      position: coordenadaI} );
        	var marker2 = new google.maps.Marker(
              	  {map: map, 
              		title: FF,
                    position: coordenadaF} );
       	
       		//gdir = new GDirections(map);
	        //GEvent.addListener(gdir, "load", onGDirectionsLoad);
  	    	//GEvent.addListener(gdir, "error", handleErrors);
  	 	//setDirections(document.getElementById("VALORY14").value,document.getElementById("VALORX14").value , document.getElementById("VALORY15").value,  document.getElementById("VALORX15").value );
   

    	  
		
      
	/*	GEvent.addListener(map, "click",
			function(marker, point) {
			 //  alert(point);
 		 		if (marker) {
               		null;
              		} else {
          			map.clearOverlays();
					var marcador = new GMarker(point);
					map.addOverlay(marcador);
					document.form_mapa.coordenadas.value = point.y+","+point.x;
					document.form_mapa.zoom.value = map.getZoom();
					}
  			}
			);*/

     //}
    } 

    function addAddressToMap(ValorX,ValorY,numero)   {  
      map.clearOverlays();  
      if (numero!='') { 

    	 // alert("ValorX "+ValorX);
        //place = response.Placemark[0];  
        point = new GLatLng(ValorY, ValorX);   
      
      //  map.setCenter(point, 15);  
        marker = new GMarker(point);  
        map.addOverlay(marker);  
        marker.openInfoWindowHtml("El Número : "+numero);  
     //   document.getElementById("punto").value = marker.getLatLng().toUrlValue();  
       // generateKML(place.address,  
       // place.Point.coordinates[0], place.Point.coordinates[1]);  
      }  
    }  

    
    function showAddress(address, zoom) {
    	
    	if (geocoder) {
        	geocoder.getLatLng(address,
          		function(point) {
          		alert(point);
            		if (!point) {
            			//alert(address + " not found");
            		} else {
            			map.setCenter(point, zoom);
            			var marker = new GMarker(point);
            			map.addOverlay(marker);
						document.form_mapa.zoom.value = map.getZoom();
       			        //document.form_mapa.coordenadas.value = point.y+","+point.x;
       			        document.form_mapa.coordenadas.value = ValorY+","+ValorX;
						
               		}
               	}
        	);
      	}}
	
    function setDirections(y1, x1 , y2, x2) {  
	    //alert("Entro en el metodo setDirections" + " y1 " +y1+ " x1 " +x1 +" y2 " +y2 +" x2 " +x2);
	    gdir.load("from: " + "@"+y1+","+ x1 +"  to: @"+y2 +", "+x2);  
    //Con la opción locale:es hace que la ruta la escriba en castellano.  
  }
function onGDirectionsLoad(){ 
}
function handleErrors(){  
    if (gdir.getStatus().code == G_GEO_UNKNOWN_ADDRESS)  
      alert("Direccion desconocida");  
    else if (gdir.getStatus().code == G_GEO_SERVER_ERROR)  
      alert("Error de Servidor");  
    else if (gdir.getStatus().code == G_GEO_MISSING_QUERY)  
      alert("Falta la direccion inicial");  
    else if (gdir.getStatus().code == G_GEO_BAD_KEY)  
      alert("Clave de Google Maps incorrecta");  
    else if (gdir.getStatus().code == G_GEO_BAD_REQUEST)  
      alert("No se ha encontrado la direccion de llegada");  
    else alert("Error desconocido");  
 }  
</script>
<style type="text/css">
	body{
		font:normal 12px Arial, Helvetica, sans-serif;
		color:#666
	}
	h2{ 
		margin:10px 0;
		text-align:center
	}
	#new_direccion{
		width:690px;
		height: 450px;
		margin:5px auto;
	/*	border:2px dashed #999;*/
	/*	padding:10px 30px 30px 30px;*/
		overflow:hidden;
		background:#FCFCFC
	}
	#new_direccion .bg_mapa{
		border:1px solid #CCC;
		padding:2px;
		float:left;
		width:685px;
		border: 2;
		height: 450px;
	}
	#new_direccion .new{
		overflow:hidden;
		padding:15px 0
	}
	#new_direccion .item_dir{
		-webkit-border-radius: 3px;
		-moz-border-radius: 3px;
		border-radius: 3px;
		background:#EFEFEF;
		margin-top:6px;
		padding:3px;
	}
	#new_direccion .item_dir a{
		color:#39F;
		margin-left:10px 
	}
</style>
</head>

<!--  <body onLoad="load();"  onunload="GUnload();"> -->
<body  onLoad="load();" topmargin="0" leftmargin="0" bottommargin="0" rightmargin="0">
	<div id="new_direccion">
    	<form name="form_mapa" id="form_mapa" action="#" method="post" onsubmit="showAddress('Peru '+this.direccion.value, this.zoom.value=parseFloat(this.zoom.value));return false">
      	 
      	 
      	 <c:set var="i" value="0" />
			<c:forEach items="${ConsultaTareaForm.localizaciones}" var="l">
			<c:if test="${l.ejeX != 0.0 and l.ejeY != 0.0}">
			<%System.out.println("Dentro del ifff"); %>
			
			<p>
				<input type="hidden" id="VALORX${i}" name="x[${i}]" value="${l.ejeX}" />
				<input type="hidden" id="VALORY${i}" name="y[${i}]" value="${l.ejeY}" />
				<input type="hidden" id="NUMERO${i}" name="telefonos[${i}]" value="${l.mobileDestino}" />
				<input type="hidden" id="FECHA${i}" name="fecha[${i}]" value="${l.fecha}" />
	
			</p>
			<c:set var="i" value="${i+1}" />
			</c:if>
			</c:forEach>
      	 
      	 <input type="hidden"  value="${i}"  id="textHidden"/>
      	 
      	 
      	 <div class="row">
             	
             <!--   <input type="text" name="direccion" id="direccion" class="text" value="" size="30" />  --> 
            
             </div>
             <input type="hidden" name="zoom" size="1"id="zoom" value="50" />
             <input type="hidden" name="coordenadas" id="coordenadas" value="" />
             
        </form>
        
        <br clear="all" />
        <div class="bg_mapa">
           <!--  <div id="map" style="width: 435px; height: 306px"></div> --> 
            <div id="map" style="width: 685px; height: 450px"></div>
        </div>
       
     </div>

</body>
</html:html>