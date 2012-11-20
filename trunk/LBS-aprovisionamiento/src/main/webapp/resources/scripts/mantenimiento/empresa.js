$(function(){
	
	$("#nueva").click(function(){
		$.get(baseURL+"empresa/nueva",function(respuesta){
			crearFormulario(respuesta);
		});
		$("#areas").html("");
		$("#usuarios").html("");
		$("#formularioArea").html("");
		$("#formularioUsuario").html("");
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
		$.get(baseURL+"empresa/buscar","query="+filtro,function(data){
			lista.css("background-image","");
			if(data.length>0){
				$(data).each(function(i,item){
					lista.append("<div class=\"fila\"><input type=\"hidden\" class=\"idEmpresa\" value=\""+item.id+"\" /><strong>"+item.ruc+"</strong> - <span>"+item.razonSocial+"</span></div>");					
				});
				$(".fila:even").css("background-color","#FFDADA");
				$(".fila").click(function(){
					$("#formularioArea").html("");
					$("#formularioUsuario").html("");
					var areas=$("#areas");
					var usuarios=$("#usuarios");
					usuarios.html("");
					areas.html("");
					areas.css("background-image","url(resources/images/loader.gif)");
					var idEmpresa=$(this).find(".idEmpresa").val();
					$.get(baseURL+"empresa/modificar","id="+idEmpresa,function(respuesta){
						crearFormulario(respuesta);
					});
					$.get(baseURL+"area/porEmpresa","idEmpresa="+idEmpresa,function(data){
						areas.css("background-image","");
						if(data.length>0){							
							$(data).each(function(i,item){
								areas.append("<div class=\"filaArea\"><input type=\"hidden\" class=\"idArea\" value=\""+item.id+"\" /><span>"+item.nombre+"</span></div>");
							});
							eventoFilaArea();
						}
						else{
							areas.html("La empresa no tiene ningún área registrada.");					
						}
						
					});
				});
			}
			else{
				lista.html("No se encontró ningún resultado.");					
			}
		});
	}
}

function eventoFilaArea(){
	$(".filaArea:even").css("background-color","#FFDADA");
	$(".filaArea").click(function(){
		$("#formularioUsuario").html("");
		var usuarios=$("#usuarios");
		usuarios.html("");
		usuarios.css("background-image","url(resources/images/loader.gif)");
		var idArea=$(this).find(".idArea").val();
		var idEmpresa=$("#idEmpresa").val();
		$.get(baseURL+"area/modificar","id="+idArea+"&empresa="+idEmpresa,function(respuesta){
			crearFormularioArea(respuesta);
		});
		$.get(baseURL+"usuario/porArea","idArea="+idArea,function(data){
			usuarios.css("background-image","");
			if(data.length>0){
				$(data).each(function(i,item){
					usuarios.append("<div class=\"filaUsuario\"><input type=\"hidden\" class=\"idUsuario\" value=\""+item.id+"\" /><strong>"+item.numero+"</strong> - <span>"+item.nombres+" "+item.apellidos+"</span></div>");
				});
				eventoFilaUsuario();
			}
			else{
				usuarios.html("No se ha registrado ningún usuario para el área seleccionada.");
			}
		});
	});
}

function eventoFilaUsuario(){
	$(".filaUsuario:even").css("background-color","#FFDADA");
	$(".filaUsuario").click(function(){
		var fila=$(this);
		var idUsuario=$(this).find(".idUsuario").val();
		$.get(baseURL+"usuario/modificar","id="+idUsuario+"&area="+$("#idArea").val(),function(respuesta){
			crearFormularioUsuario(respuesta,fila);
		});
	});
}

function crearFormulario(respuesta){
	$("#formulario").html(respuesta);
	$("input[type='submit']").button();
	$("#nuevaArea").button().click(function(){
		nuevaArea();
	});
	$("#formEmpresa").validate({
		rules: {
			ruc: {
				required: true,
				minlength: 11,
				maxlength: 11,
				remote: {
					url: baseURL+"empresa/existente",
					data: {
						id: function(){
							if($("#idEmpresa").length>0)
								return $("#idEmpresa").val();
							return -1;
						}
					}
				}
			},
			razonSocial: "required",
			consultasPorMes: {
				required: true,
				number: true,
				min: 0
			}
		},
		messages: {
			ruc: {
				required: "Es necesario ingresar un RUC",
				minlength: jQuery.format("El RUC debe tener {0} dígitos"),
				maxlength: jQuery.format("El RUC debe tener {0} dígitos"),
				remote: "La empresa que está intentando registrar ya existe"
			},
			razonSocial: "Ingrese una razón social",
			consultasPorMes: {
				required: "Debe ingresar el total de consultas por mes para esta empresa",
				number: "Número incorrecto",
				min: jQuery.format("El número de consultas debe ser mayor a {0}")
			}
		},
		submitHandler: function(form){
			$.post(form.action,$(form).serialize(),function(empresa){
				if(empresa){
					if($("#idEmpresa").length<=0){
						$(form).find("fieldset").append("<input type=\"button\" id=\"nuevaArea\" style=\"float:right;\" value=\"Crear Área\" />");
						$("#nuevaArea").button().click(function(){
							nuevaArea();
						});
						$(form).find("fieldset").append("<input type=\"hidden\" id=\"idEmpresa\" name=\"id\" value=\""+empresa.id+"\" />");
					}
					alert("Empresa guardada satisfactoriamente.");
				}
			});
		}
	});
}

function nuevaArea(){
	$("#usuarios").html("");
	$("#formularioUsuario").html("");
	$.get(baseURL+"area/nueva","empresa="+$("#idEmpresa").val(),function(respuesta){
		crearFormularioArea(respuesta);
	});
}

function crearFormularioArea(respuesta){
	$("#formularioArea").html(respuesta);
	$("input[type='submit']").button();
	$("#nuevoUsuario").button().click(function(){
		nuevoUsuario();
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
					if($("#idArea").length<=0){
						$(form).find("fieldset").append("<input type=\"button\" id=\"nuevoUsuario\" style=\"float:right;\" value=\"Registrar Usuario\" />");
						$("#nuevoUsuario").button().click(function(){
							nuevoUsuario();
						});
						$(form).find("fieldset").append("<input type=\"hidden\" id=\"idArea\" name=\"id\" value=\""+area.id+"\" />");
						$("#areas").append("<div class=\"filaArea\"><input type=\"hidden\" class=\"idArea\" value=\""+area.id+"\" /><span>"+area.nombre+"</span></div>");
						eventoFilaArea();
					}
					alert("Área guardada satisfactoriamente.");
				}
			});
		}
	});
}

function nuevoUsuario(){
	$.get(baseURL+"usuario/nuevo","area="+$("#idArea").val(),function(respuesta){
		crearFormularioUsuario(respuesta,null);
	});
}

function crearFormularioUsuario(respuesta,fila){
	$("#formularioUsuario").html(respuesta);
	$("input[type='submit'],input[type='button']").button();
	
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
			var des=$("#idRol").attr("disabled");
			if(des)
				$("#idRol,#idEmpresa").removeAttr("disabled");
			$.post(form.action,$(form).serialize(),function(usuario){
				if(usuario){
					if(des)
						$("#idRol,#idEmpresa").attr("disabled","disabled");
					if($("#idUsuario").length<=0){
						$(form).find("fieldset").append("<input type=\"hidden\" id=\"idUsuario\" name=\"id\" value=\""+usuario.id+"\" />");
						$("#usuarios").append("<div class=\"filaUsuario\"><input type=\"hidden\" class=\"idUsuario\" value=\""+usuario.id+"\" /><strong>"+usuario.numero+"</strong> - <span>"+usuario.nombres+" "+usuario.apellidos+"</span></div>");
						eventoFilaUsuario();
					}
					alert("Usuario registrado satisfactoriamente.");
				}
			});
		}
	});
	
	$("#numero").blur(function(){
		rescatarUsuarioEliminado($(this).val());
	});
	
	$("#idRol").change(function(){
		var rol=$("#idRol option:selected").attr("label");
		if(rol=="jefe"){
			$("#lbsweb").removeAttr("disabled");
			$("#clave").attr("disabled","disabled");
		}
		else if(rol=="empleado"){
			$("#lbsweb").attr("disabled","disabled");
			$("#clave").attr("disabled","disabled");
		}
	}).trigger("change");

	$("#lbsweb").change(function(){
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
	}).trigger("change");
	
	$("#eliminarUsuario").click(function(){
		if(confirm("¿Seguro que desea eliminar este usuario?")){
			$.post(baseURL+"usuario/eliminar","id="+$("#idUsuario").val(),function(ok){
				if(ok){
					fila.remove();
					$("#formularioUsuario").html("");
					$(".filaUsuario:even").css("background-color","#FFDADA");
					alert("Usuario eliminado");
				}
			});
		}
	});
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
			}
		}
	});
}