$(function(){
	
	$("#nueva").click(function(){
		$.get(baseURL+"usuario/nuevo","area=-1",function(respuesta){
			crearFormulario(respuesta,null);
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
		$.get(baseURL+"usuario/buscar","query="+filtro,function(celdas){
			lista.css("background-image","");
			if(celdas.length>0){
				$(celdas).each(function(i,item){
					lista.append("<div class=\"fila\"><input type=\"hidden\" class=\"idUsuario\" value=\""+item.id+"\" /><strong>"+item.numero+"</strong> - <span>"+item.nombres+" "+item.apellidos+"</span></div>");		
				});
				$(".fila:even").css("background-color","#FFDADA");
				$(".fila").click(function(){
					var fila=$(this);
					var idUsuario=$(this).find(".idUsuario").val();
					$.get(baseURL+"usuario/modificar","id="+idUsuario+"&area=-1",function(respuesta){
						crearFormulario(respuesta,fila);
					});
				});
			}
			else{
				lista.html("No se encontró ningún resultado.");					
			}
		});
	}
}

function crearFormulario(respuesta,fila){
	$("#formulario").html(respuesta);
	$("input[type='submit'],input[type='button']").button();
	
	validaFormulario();
	
	seleccionarEmpresa();
	
	$("#numero").blur(function(){
		rescatarUsuarioEliminado($(this).val());
	});
	
	$("#idRol").change(function(){
		verificarRol();
	}).trigger("change");
	
	$("#lbsweb").change(function(){
		var rol=$("#idRol option:selected").attr("label");
		if(rol=="jefe" || rol=="empleado"){
			if(this.checked){
				$("#clave").removeAttr("disabled");
				if($("#claveOld").val()==""){
					$("#clave").parent().find("span").remove();
					$("#clave").val("12345");
					$("#clave").parent().append("<span>Clave por defecto: 12345</span>");
					$("#clave").parent().find("span").fadeOut(10000);
				}
			}
			else{
				$("#clave").val("");
				$("#claveOld").val("");
				$("#clave").attr("disabled","disabled");
			}
		}
	}).trigger("change");
	
	$("#eliminarUsuario").click(function(){
		if(confirm("¿Seguro que desea eliminar este usuario?")){
			$.post(baseURL+"usuario/eliminar","id="+$("#idUsuario").val(),function(ok){
				if(ok){
					fila.remove();
					$("#formulario").html("");
					$(".fila:even").css("background-color","#FFDADA");
					alert("Usuario eliminado");
				}
			});
		}
	});
}

function validaFormulario(){
	$("#formUsuario").validate({
		rules: {
			nombres: "required",
			apellidos: "required",
			numero: {
				required: true,
				digits: true,
				minlength: 9,
				maxlength: 9,
				remote: {
					url: baseURL+"usuario/existe",
					data: {
						id: function(){
							if($("#idUsuario").length>0)
								return $("#idUsuario").val();
							return -1;
						}
					}
				}
			},
			"rol.id": {
				required: true,
				min: 0
			},
			consultasPorMes: {
				number: true,
				min: 0,
				remote: {
					url: baseURL+"usuario/maxConsultas",
					data: {
						id: function(){
							if($("#id").length>0)
								return $("#id").val();
							return -1;
						},
						areas: function(){
							if($(".asignada").length>0){
								var areas=new Array();
								$(".asignada").each(function(i,item){
									areas[i]=$(item).find(".idArea").val();
								});
								return areas;
							}
							return -1;
						}
					}
				}
			}
		},
		messages: {
			nombres: "Ingrese los nombres del usuario",
			apellidos: "Ingrese los apellidos del usuario",
			numero: {
				required: "Ingrese el número telefónico",
				digits: "Número inválido",
				minlength: jQuery.format("El número debe tener {0} dígitos"),
				maxlength: jQuery.format("El número debe tener {0} dígitos"),
				remote: "El número ingresado ya se encuentra registrado"
			},
			"rol.id": {
				required: "Debe seleccionar un rol",
				min: "Debe seleccionar un rol"
			},
			consultasPorMes: {
				number: "Número incorrecto",
				min: jQuery.format("El número de consultas debe ser mayor a {0}"),
				remote: "El número de consultas excede el máximo permitido para las área(s) seleccionada(s)"
			}
		},
		submitHandler: function(form){
			var clave=$("#clave").val();
			if(clave!=null && clave!=""){
				$("#claveOld").val("");
			}
			var asignadas=$(".asignada");
			if(asignadas.length>0){
				asignadas.each(function(i,item){
					$(form).find("fieldset").append("<input type=\"hidden\" name=\"idsAreas\" value=\""+$(item).find(".idArea").val()+"\" />");
				});
			}
			else{
				$(form).find("fieldset").append("<input type=\"hidden\" name=\"idsAreas\" value=\"0\" />");
			}
			$("#idRol,#idEmpresa").removeAttr("disabled");
			$.post(form.action,$(form).serialize(),function(usuario){
				if(usuario){
					location.reload(true);
				}
			});
		}		
	});
}

function seleccionarEmpresa(){
	$("#idEmpresa").change(function(){
		$.get(baseURL+"area/porEmpresa","idEmpresa="+$(this).val(),function(respuesta){
			$("#disponibles").html("");
			$("#asignadas").html("");
			$(respuesta).each(function(i,item){
				$("#disponibles").append("<div class=\"disponible\" style=\"cursor:pointer;\"><input type=\"hidden\" class=\"idArea\" value=\""+item.id+"\" /><span>"+item.nombre+"</span></div>");
				iniciarDragDrop();
			});
		});
	});
	iniciarDragDrop();
}

function iniciarDragDrop(){
	var disabled=$("#idEmpresa").attr("disabled");
	if(!disabled){
		$(".disponible").draggable({
			appendTo: "body",
			helper: "clone"
		});
		$(".asignada").draggable({
			appendTo: "body",
			helper: "clone"
		});
		$("#asignadas").droppable({
			accept: ".disponible",
			drop: function(event,ui){
				$("<div class=\"asignada\" style=\"cursor:pointer;\"></div>").html(ui.draggable.html()).appendTo(this).draggable({
					appendTo: "body",
					helper: "clone"
				});
				ui.draggable.remove();
			}
		});
		$("#disponibles").droppable({
			accept: ".asignada",
			drop: function(event,ui){
				$("<div class=\"disponible\" style=\"cursor:pointer;\"></div>").html(ui.draggable.html()).appendTo(this).draggable({
					appendTo: "body",
					helper: "clone"
				});
				ui.draggable.remove();
				
			}
		});
	}
}

function verificarRol(){
	var disabled=$("#idEmpresa").attr("disabled");
	var rol=$("#idRol option:selected").attr("label");
	if(!disabled){
		if(rol=="admin" || rol=="super" || rol=="atencion"){
			$("#idEmpresa").val(-1).attr("disabled","disabled");
			$("#disponibles").html("");
			$("#asignadas").html("");
		}
	}
	if(rol=="admin" || rol=="super" || rol=="atencion"){
		$("#numero").parent().find("label").text("Usuario");
		$("#numero").rules("remove","digits minlength maxlength");
	}
	else{
		$("#idEmpresa").removeAttr("disabled");
		$("#numero").parent().find("label").text("Número");
		$("#numero").rules("add",{
			digits: true,
			minlength: 9,
			maxlength: 9,
			messages: {
				digits: "Número inválido",
				minlength: jQuery.format("El número debe tener {0} dígitos"),
				maxlength: jQuery.format("El número debe tener {0} dígitos")
			 }
		});
	}
	if(rol=="jefe"){
		$("#lbsweb").removeAttr("disabled");
		$("#clave").attr("disabled","disabled");
	}
	else if(rol=="empleado"){
		$("#lbsweb").removeAttr("checked");
		$("#lbsweb").attr("disabled","disabled");
		$("#clave").attr("disabled","disabled");
		$("#claveOld").val("");
	}
}

function rescatarUsuarioEliminado(numero){
	$.get(baseURL+"usuario/buscarUsuarioEliminado","numero="+numero,function(usuario){
		if(usuario.id!=null){
			if(confirm("El número ha sido eliminado anteriormente, desea recuperar sus datos?")){
				
				$("#formUsuario fieldset").append("<input type=\"hidden\" id=\"idUsuario\" name=\"id\" value=\""+usuario.id+"\" />");
				
				var rol="";
				$("#idRol option").each(function(i,item){
					if(item.value==usuario.rol.id){
						$(item).attr("selected","selected");
						rol=usuario.rol.codigo;
						$("#idRol").trigger("change");
					}
				});
			
				$("#nombres").val(usuario.nombres);
				$("#apellidos").val(usuario.apellidos);
				var clave=usuario.clave;
				if(clave!=null && rol=="jefe"){
					$("#claveOld").val(clave);
					$("#lbsweb").removeAttr("disabled");
					$("#lbsweb").attr("checked","checked");
					$("#lbsweb").trigger("change");
				}
				
				/*Para la empresa*/
				if(usuario.areas.length>0){
					var idEmpresa=usuario.areas[0].empresa.id;
					
					$("#idEmpresa option").each(function(i,item){
						if(item.value==idEmpresa){
							$(item).attr("selected","selected");
	
							$.get(baseURL+"area/porEmpresa","idEmpresa="+idEmpresa,function(respuesta){
								$("#disponibles").html("");
								$("#asignadas").html("");
								$(respuesta).each(function(i,item){
									if(noEnAsignadas(item.id,usuario.areas)){
										$("#disponibles").append("<div class=\"disponible\" style=\"cursor:pointer;\"><input type=\"hidden\" class=\"idArea\" value=\""+item.id+"\" /><span>"+item.nombre+"</span></div>");
									}
									else{
										$("#asignadas").append("<div class=\"asignada\" style=\"cursor:pointer;\"><input type=\"hidden\" class=\"idArea\" value=\""+item.id+"\" /><span>"+item.nombre+"</span></div>");
									}
									iniciarDragDrop();
								});
							});
							return;
						}
					});
				}
			}
		}
	});
}

function noEnAsignadas(id,areas){
	var asignada=false;
	for(var i=0;i<areas.length;i++){
		if(id==areas[i].id){
			asignada=true;
		}
	}
	return !asignada;
}