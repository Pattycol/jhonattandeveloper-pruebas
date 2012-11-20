(function($){
  $.fn.paginar = function(options) {
	
		  var settings = $.extend({
		       items: 10,
		       div_paginacion : '.page_navigation'
		    },options||{});
	
		var obj = this;
		var numero_items = this.find("tbody > tr").size(); 
		var numero_paginas = Math.ceil(numero_items/settings.items);
		var pagina_actual = 0;
		
		$('<a>',{
		    text: "Anterior",
		    href : "#",
		    'class' : "previous_link" ,
		    click:function(link_actual){
		    	
		    	previous();return false;
		    	
		    }
		}).appendTo( $(settings.div_paginacion) );
		$(settings.div_paginacion).append("&#32;");
		
		var link_actual = 0;
		
		while(numero_paginas > link_actual){
			
			$('<a>',{
			    text: link_actual + 1,
			    longdesc: "" + link_actual + "" ,
			    href : "#",
			    'class' : "page_link", 
			    click:function(){
			    	
			    	go_to_page(obj, $(this).attr("longdesc"));return false;
			    	
			    }
			}).appendTo( $(settings.div_paginacion) );
			
			$(settings.div_paginacion).append("&#32;");
			
			link_actual++;
		}
		
		
		
		$('<a>',{
		    text: "Siguiente",
		    href : "#",
		    'class' : "next_link" ,
		    click:function(link_actual){
		    	
		    	next();return false;
		    	
		    }
		}).appendTo( $(settings.div_paginacion) );
		
		
		$(settings.div_paginacion + ' .page_link:first').addClass('active_page');
		
		this.find("tbody > tr").hide();
		
		this.find("tbody > tr").slice(0, settings.items).show();
		
		function previous(){

			new_page = parseInt(pagina_actual) - 1;
			//if there is an item before the current active link run the function
			if($('.active_page').prev('.page_link').length==true){
				go_to_page(obj,new_page);
			}

		}

		function next(){
			new_page = parseInt(pagina_actual) + 1;
			//if there is an item after the current active link run the function
			if($('.active_page').next('.page_link').length==true){
				go_to_page(obj,new_page);
			}

		}
		
		function go_to_page(tabla,page_num){
			
			start_from = page_num * settings.items;
			end_on = start_from + settings.items;
			tabla.find("tbody > tr").hide().slice(start_from, end_on).show();

			$('.page_link[longdesc="' + page_num +'"]').addClass('active_page').siblings('.active_page').removeClass('active_page');
			pagina_actual = page_num;
		}
	
    return this;
  };
})(jQuery);
