/**

 * 
 */

function muestraDate(object){
	seleccionaFecha(object);
}

function seleccionaFecha(obj){
	var ids=obj.name.split('-');
	$("#"+obj.id).datepicker({
	      showOn: 'both',
	      buttonImage: 'images/icono_calendario.gif',
	      buttonImageOnly: true,
	      changeYear: true,
	      dateFormat: 'dd/mm/yy',
	      numberOfMonths: 1,
	      onSelect: function(textoFecha, objDatepicker){
	  		$("#"+ids[1]).html(textoFecha);
	  	      }
	   });
}