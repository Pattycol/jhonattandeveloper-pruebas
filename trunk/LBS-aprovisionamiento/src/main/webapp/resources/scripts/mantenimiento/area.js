$(function(){
	
	$("#empresas").change(function(){
		$.get(baseURL+"area/porEmpresa","idEmpresa="+$(this).val(),function(respuesta){
			$("#lista").html("");
			$(respuesta).each(function(i,item){
				var fila="<div class=\"fila\"><input type=\"hidden\" class=\"idArea\" value=\""+item.id+"\" /><strong>"+item.nombre+"</strong></div>";
				$("#lista").append(fila);				
			});
			$(".fila:even").css("background-color","#FFDADA");
			$(".fila").click(function(){
				var idArea=$(this).find(".idArea").val();
				$.get(baseURL+"area/modificar","id="+idArea+"&empresa=-1",function(respuesta){
					crearFormulario(respuesta);
				});
			});
		});
	});
	
	$("#nueva").click(function(){
		$.get(baseURL+"area/nueva","empresa=-1",function(respuesta){
			crearFormulario(respuesta);
		});
	});
	
});

function crearFormulario(respuesta){
	$("#formulario").html(respuesta);
	$("input[type='submit']").button();
	$("#idEmpresa").change(function(){
		$.get(baseURL+"area/porEmpresa","idEmpresa="+$(this).val(),function(respuesta){
			$("#padre").html("<option value=\"-1\">Ninguna</option>");
			$(respuesta).each(function(i,item){
				$("#padre").append("<option value=\""+item.id+"\">"+item.nombre+"</option>");
			});
		});
	});
	
	$("#formArea").validate({
		rules: {
			nombre: "required",
			"empresa.id": {
				required: true,
				min: 1
			},
			consultasPorMes: {
				number: true,
				min: 0,
				remote: {
					url: baseURL+"area/maxConsultas",
					data: {
						idEmpresa: function(){
							return $("#idEmpresa").val();
						},
						id: function(){
							if($("#idArea").length>0)
								return $("#idArea").val();
							return -1;
						}
					}
				}
			}
		},
		messages: {
			nombre: "Ingrese un nombre para el Área",
			"empresa.id": {
				required: "Seleccione una empresa",
				min: "Seleccione una empresa"
			},
			consultasPorMes: {
				number: "Número incorrecto",
				min: jQuery.format("El número de consultas debe ser mayor a {0}"),
				remote: "El número de consultas excede el máximo permitido para esta empresa"
			}
		},
		submitHandler: function(form){
			$.post(form.action,$(form).serialize(),function(area){
				if(area){
					location.reload(true);
				}
			});
		}
	});
}