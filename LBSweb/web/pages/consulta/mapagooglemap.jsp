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
  String tiempo=Helper.getProperty(Constants.APPLICATION_BUNDLE,Constants.PROPERTY_TIEMPO_RECARGA_GRAFICO);
  String reloadGrafico=(String)session.getAttribute(Constants.REQUEST_GRAFICO_AUTOMATICO);
  System.out.println("reloadGrafico ******** " +reloadGrafico);
  if(reloadGrafico==null)
      reloadGrafico="";

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
		
		
	});
	
	
    var map      = null;
    var geocoder = null;
    var infoWindow = null;
    var ValorX ;
    var ValorY ;
    var markerArreglo= []; 
    var valorI =0;
    function load() {   
        var textHidden = document.getElementById("textHidden").value;

		var YI=  document.getElementById("VALORY0").value;
    	var XI=  document.getElementById("VALORX0").value;
        var numero ;
        for (var i = 0; i < textHidden; i++) {
        	
        	  ValorX=  document.getElementById("VALORX"+i).value; 
        	  ValorY= document.getElementById("VALORY"+i).value; 
           
        }
    	
       var myLatLng = new google.maps.LatLng(YI,XI);
  	   var myOptions = {
  	     zoom: 5,
  	     center: myLatLng,
  	    // mapTypeId: google.maps.MapTypeId.TERRAIN
  	    mapTypeId: google.maps.MapTypeId.ROADMAP
  	     
  	   };

  	    map = new google.maps.Map(document.getElementById("map"), myOptions);
  	  infoWindow = new google.maps.InfoWindow();
  	 
      google.maps.event.addListener(map, 'click', function(){
          closeInfoWindow();
      });
     
		var images = ["http://www.google.com/intl/en_us/mapfiles/ms/icons/red-dot.png",
		      		"http://www.google.com/intl/en_us/mapfiles/ms/icons/green-dot.png",
		      		"http://www.google.com/intl/en_us/mapfiles/ms/icons/blue-dot.png",
		      		"http://www.google.com/intl/en_us/mapfiles/ms/icons/yellow-dot.png",
		      		"http://www.google.com/intl/en_us/mapfiles/ms/icons/orange-dot.png"];
  	
        for (var i = 0; i < textHidden; i++) {
        numero = document.getElementById("NUMERO"+i).value;
  	 	 ValorX=  document.getElementById("VALORX"+i).value; 
	  	ValorY= document.getElementById("VALORY"+i).value; 
		var image = images[i];
		//alert(image);
    	 markerArreglo[i] = new google.maps.Marker(
            	  {map: map,
                  icon: image,	   
            	  title: numero,
                  position: new google.maps.LatLng(ValorY,ValorX)} );
     
         }

        var x=-1;
         for (var marker in markerArreglo) {
             x++;
             numero = document.getElementById("NUMERO"+x).value;
         llamarAddListener(marker, x,numero);
        }
     
   
      
    } 
   
	function llamarAddListener(marker,x,numero){
   	   google.maps.event.addListener(markerArreglo[x], 'click', function() {
           	 openInfoWindow(markerArreglo[x], numero,x);
  
      });        
  
	}



    function openInfoWindow(marker, numero,i) {
       // alert(marker.getPosition());
        var markerLatLng = markerArreglo[i].getPosition();
        infoWindow.setContent([
            '<div style="text-align:center;">',
            'El Número es :  <b>',
            numero,
            '</b> ',
            '</div>'
        ].join(''));
        infoWindow.open(map, markerArreglo[i]);
    }

    
    function closeInfoWindow() {
        infoWindow.close();
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
			<c:forEach items="${ConsultaForm.resultadoConsulta.listaResultadoDetalle}" var="detalle">
			<c:if test="${detalle.localizacion != null and detalle.localizacion != null}">
			<%System.out.println("Dentro del ifff"); %>
			<p>
				<input type="hidden" id="VALORX${i}"  name="x[${i}]" value="${detalle.localizacion.ejeX}" />
			
				<input type="hidden" id="VALORY${i}" name="y[${i}]" value="${detalle.localizacion.ejeY}" />
				<input type="hidden" id="NUMERO${i}" name="telefonos[${i}]" value="${detalle.mobileDestino}" />
				
				<input type="hidden" name="fecha[${i}]" value="${detalle.fechaConsulta}" />
			</p>
			<c:set var="i" value="${i+1}" />
			
			</c:if>
			</c:forEach> 
      	 <input type="hidden"  value="${i}"  id="textHidden"/>
      	 
      	 
      	 <div class="row">
             	
              <!--   <input type="text" name="direccion" id="direccion" class="text" value="" size="30" /> --> 
            
             </div>
             <input type="hidden" name="zoom" size="1"id="zoom" value="50" />
             <input type="hidden" name="coordenadas" id="coordenadas" value="" />
             
        </form>
        
        <div class="bg_mapa">
           <!--  <div id="map" style="width: 435px; height: 306px"></div> -->
            <div id="map" style="width: 685px; height: 450px"></div>
        </div>
       
     </div>

</body>
</html:html>