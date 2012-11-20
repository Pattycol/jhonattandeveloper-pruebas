var mayorDistancia=0;
var X1=0;
var Y1=0;
var X2=0;
var Y2=0;
$(document).ready(function(){
	var opciones={
		mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var mapa=new google.maps.Map($("#mapa").get(0),opciones);
	parent.frmIzq.$("#res > tbody tr").each(function(i,item){
		var td=$(item).find("td:first");
		if(td.find("img").length<=0)
			td.prepend("<img alt=\"\" src=\"image/leyenda"+i+".png\" />");
	});
	
	$("#coordenadas p").each(function(i,item){
		item=$(item);
		var icon=new google.maps.MarkerImage("image/marker_sprite"+i+".png",
			      new google.maps.Size(20,34),
			      new google.maps.Point(0,0),
			      new google.maps.Point(10,34));
		var shadow=new google.maps.MarkerImage("image/marker_sprite"+i+".png",
			      new google.maps.Size(29,34),
			      new google.maps.Point(28,0),
			      new google.maps.Point(0,34));

		var lat=item.find("#latitud"+i).val();
		var long=item.find("#longitud"+i).val();
		if(X1==0 && Y1==0){
			X1=parseFloat(long);
			Y1=parseFloat(lat);
		}
		calcularMayorDistancia(i,long,lat);
		var latLng=new google.maps.LatLng(lat,long);
		var marca=new google.maps.Marker({
			position: latLng,
			map: mapa,
			title: item.find("#numero"+i).val(),
			shadow: shadow,
			icon: icon
		});
	});
	
	var d=mayorDistancia/2;
	var xc=(X1>X2) ? X2+d : X1+d;
	var yc=(Y1>Y2) ? Y2+d : Y1+d;
	mapa.setCenter(new google.maps.LatLng(yc,xc));
	var zoom=16;
	if(mayorDistancia>0.0032 && mayorDistancia<0.0063){
		zoom=17;
	}
	else if(mayorDistancia>0.0064 && mayorDistancia<0.0132){
		zoom=16;
	}
	else if(mayorDistancia>0.0133 && mayorDistancia<0.0262){
		zoom=15;
	}
	else if(mayorDistancia>0.0263 && mayorDistancia<0.0523){
		zoom=14;
	}
	else if(mayorDistancia>0.0524 && mayorDistancia<0.1064){
		zoom=13;
	}
	else if(mayorDistancia>0.1065 && mayorDistancia<0.2151){
		zoom=12;
	}
	else if(mayorDistancia>0.2152 && mayorDistancia<0.4398){
		zoom=11;
	}
	else if(mayorDistancia>0.4399 && mayorDistancia<0.8568){
		zoom=10;
	}
	else if(mayorDistancia>0.8569 && mayorDistancia<1.7582){
		zoom=9;
	}
	else if(mayorDistancia>1.7583 && mayorDistancia<3.5987){
		zoom=8;
	}
	else if(mayorDistancia>3.5988 && mayorDistancia<6.8245){
		zoom=7;
	}
	else if(mayorDistancia>6.8246 && mayorDistancia<12.7908){
		zoom=6;
	}
	else if(mayorDistancia>12.7909 && mayorDistancia<18.8689){
		zoom=5;
	}
	mapa.setZoom(zoom);
});

function distancia(x1,y1,x2,y2){
	return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
}

function calcularMayorDistancia(indice,x,y){
	$("#coordenadas p").each(function(i,item){
		if(i!=indice){
			var y2=$(item).find("#latitud"+i).val();
			var x2=$(item).find("#longitud"+i).val();
			x=parseFloat(x);
			y=parseFloat(y);
			x2=parseFloat(x2);
			y2=parseFloat(y2);
			var d=distancia(x,y,x2,y2);
			if(d>mayorDistancia){
				mayorDistancia=d;
				X1=x;
				Y1=y;
				X2=x2;
				Y2=y2;
			}
		}
	});
}