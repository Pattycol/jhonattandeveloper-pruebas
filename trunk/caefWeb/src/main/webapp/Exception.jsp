<%@page import="pe.com.claro.caef.web.util.MENSAJES_CONFIG"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
$(function() {
	 $(document).keydown(function(e){
	  var code = (e.keyCode ? e.keyCode : e.which);
	  if(code == 116) {
	   e.preventDefault();
	   location="<%=request.getContextPath()%>/j_spring_security_logout";
	  }
	  if(code==13){
	  	return false;
	  }
	  if(code==8){
		  e.preventDefault();
		  location="<%=request.getContextPath()%>/j_spring_security_logout";		  
	  }
	  
	 });
	});
</script>
<body>

	<h3 style="color:#BAB2B2;"><%=MENSAJES_CONFIG.EXCEPCION_MENSAJE1 %><a href="mailto:atencionalcliente@claro.com.pe"><%=MENSAJES_CONFIG.EXCEPCION_MENSAJE2 %></a></h3>

</body>
</html>