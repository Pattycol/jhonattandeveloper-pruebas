var baseURL;
$(function(){
	baseURL=$("#baseURL").val();
	
	$("#menu").accordion({
		navigation: true
	});
	
	$("button,input[type='submit']").button();
});