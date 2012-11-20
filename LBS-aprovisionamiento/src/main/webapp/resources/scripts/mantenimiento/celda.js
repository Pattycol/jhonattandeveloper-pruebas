$(function(){
	
	$("#nueva").click(function(){
		$.get(baseURL+"celda/nueva",function(respuesta){
			crearFormulario(respuesta);
		});
	});
	
	
	
	$("#buscar").click(function(){
		buscar();
	});
	
	$("#filtro").keyup(function(event){
		if(event.which==13){
			buscar();
		}
	});
	
});

function buscar(){
	var lista=$("#lista");
	var filtro=$("#filtro").val().toLowerCase();
	lista.html("");
	if(filtro.length>2){
		lista.css("background-image","url(resources/images/loader.gif)");
		$.get(baseURL+"celda/buscar","query="+filtro,function(celdas){
			lista.css("background-image","");
			if(celdas.length>0){
				$(celdas).each(function(i,item){
					lista.append("<div class=\"fila\"><input type=\"hidden\" class=\"idCelda\" value=\""+item.id+"\" /><strong>"+item.identificador+"</strong> - <span>"+item.direccion+"</span></div>");					
				});
				$(".fila:even").css("background-color","#FFDADA");
				$(".fila").click(function(){
					var idCelda=$(this).find(".idCelda").val();
					$.get(baseURL+"celda/modificar","id="+idCelda,function(respuesta){
						crearFormulario(respuesta);
					});
				});
			}
			else{
				lista.html("No se encontró ningún resultado.");					
			}
		});
	}
}

function crearFormulario(respuesta){
	$("#formulario").html(respuesta);
	$("input[type='submit']").button();
	
	$("#celdaFormulario").validate({
		rules: {
			identificador: {
				required: true,
				digits: true,
				remote: {
					url: baseURL+"celda/existe",
					data: {
						id: function(){
							if($("#id").length>0)
								return $("#id").val();
							return -1;
						}
					}
				}
			},
			x: {
				required: true,
				number: true
			},
			y: {
				required: true,
				number: true
			},
			direccion: {
				required: true,
				maxlength: 200
			}
		},
		messages: {
			identificador: {
				required: "Ingrese un identificador",
				digits: "El identificador debe consistir sólo de dígitos",
				remote: "El identificador ingresado ya ha sido registrado"
			},
			x: {
				required: "Ingrese la coordenada X",
				number: "La coordenada debe ser un número"
			},
			y: {
				required: "Ingrese la coordenada Y",
				number: "La coordenada debe ser un número"
			},
			direccion: {
				required: "Ingrese una dirección",
				maxlength: jQuery.format("La dirección debe tener como máximo {0} caracteres")
			}
		}
	});
}