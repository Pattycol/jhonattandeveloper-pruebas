$(function(){
	
	$(".fila:even").css("background-color","#FFDADA");
	
	$("#nueva").click(function(){
		$.get(baseURL+"blacklist/nueva",function(respuesta){
			crearFormulario(respuesta);
		});
	});
	
	$(".fila").click(function(){
		var id=$(this).find(".idBlacklist").val();
		$.get(baseURL+"blacklist/modificar","id="+id,function(respuesta){
			crearFormulario(respuesta);
		});
	});
	
	var filas=$(".fila");
	var lista=$("#lista");
	
	$("#filtro").keyup(function(event){
		var filtro=$(this).val().toLowerCase();
		lista.html("");
		if(filtro.length>0){
			filas.each(function(i,item){
				if($(item).text().toLowerCase().indexOf(filtro)>=0){
					lista.append(item);
				}
			});
		}
		else{
			filas.each(function(i,item){
				lista.append(item);
			});
		}
	});
	
});

function crearFormulario(respuesta){
	$("#formulario").html(respuesta);
	$("input[type='submit']").button();
	
	$("#fechaFin").datepicker({
		minDate: +1,
		dateFormat: "dd/mm/yy",
		altField: "#fecha",
		altFormat: "mm/dd/yy"
	});
	
	$("#blacklistFormulario").validate({
		rules: {
			numero: {
				required: true,
				digits: true,
				minlength: 9,
				maxlength: 9,
				remote: baseURL+"blacklist/existeUsuario"
			},
			fechaFin: "required"
		},
		messages: {
			numero: {
				required: "Ingrese el número telefónico",
				digits: "Número inválido",
				minlength: jQuery.format("El número debe tener {0} dígitos"),
				maxlength: jQuery.format("El número debe tener {0} dígitos"),
				remote: "El número ingresado no se encuentra registrado"
			},
			fechaFin: "Ingrese la fecha de fin de la lista"
		}
	});
}