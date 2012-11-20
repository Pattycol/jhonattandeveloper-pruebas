$(function(){
	
	$("#tabs").tabs();
	
	$("#emp").autocomplete({
		source: baseURL+"consultasTrafico/buscarEmpresas",
		minLength: 2,
		select: function(event){
		}
	});
	
	var idUsuario=-1;
	$("#usu").autocomplete({
		source: baseURL+"consultasTrafico/buscarUsuario",
		minLength: 2,
		select: function(event,ui){
			idUsuario=ui.item.id;
		}
	});
	
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
		if(idUsuario!=-1){
			var fechaInicio=$("#fi").val();
			if(fechaInicio!=null && fechaInicio!=""){
				var fechaFin=$("#ff").val();
				if(fechaFin!=null && fechaFin!=""){
					$("#usuario .resultado").html("").css("background-image","url(resources/images/loader.gif)");					
					$.get(baseURL+"consultasEmpresa/usuario",{usuario: idUsuario,fechaInicio: fechaInicio,fechaFin: fechaFin},function(respuesta){
						$("#usuario .resultado").html(respuesta).css("background-image","");;
						$("#reportes").tabs();
					});
				}
				else{
					$("#usuario .error").text("Seleccione una fecha de fin");
				}
			}
			else{
				$("#usuario .error").text("Seleccione una fecha de inicio");
			}
		}
		else{
			$("#usuario .error").text("Seleccione un usuario");
		}
	});
	
});