$(function(){
	
	$("#tabs").tabs();
	
	/*var idUsuario=-1;
	$("#usu").autocomplete({
		source: baseURL+"consultasEmpresa/buscarUsuario",
		minLength: 2,
		select: function(event,ui){
			idUsuario=ui.item.id;
		}
	});*/
	
	$("#fechaInicio").datepicker({
		dateFormat: "dd/mm/yy",
		altField: "#fi",
		altFormat: "mm/dd/yy",
		onSelect: function(fechaSeleccionada){
			var instance=$(this).data("datepicker");
			var fecha=$.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat,fechaSeleccionada,instance.settings);
			$("#fechaFin").datepicker("option","minDate",fecha);
		}
	});
	
	$("#fechaFin").datepicker({
		dateFormat: "dd/mm/yy",
		altField: "#ff",
		altFormat: "mm/dd/yy",
		onSelect: function(fechaSeleccionada){
			var instance=$(this).data("datepicker");
			var fecha=$.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat,fechaSeleccionada,instance.settings);
			$("#fechaInicio").datepicker("option","maxDate",fecha);
		}
	});
	
	$("#generar").click(function(){
			var fechaInicio=$("#fi").val();
			if(fechaInicio!=null && fechaInicio!=""){
				var fechaFin=$("#ff").val();
				if(fechaFin!=null && fechaFin!=""){
					$("#fecha .resultado").html("").css("background-image","url(resources/images/loader.gif)");					
					$.get(baseURL+"consultasFecha/fecha",{fechaInicio: fechaInicio,fechaFin: fechaFin},function(respuesta){
						$("#fecha .resultado").html(respuesta).css("background-image","");
						$("#reportes").tabs();
					});
				}
				else{
					$("#fecha .error").text("Seleccione una fecha de fin");
				}
			}
			else{
				$("#fecha .error").text("Seleccione una fecha de inicio");
			}
		
	});
	
});