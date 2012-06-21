<%@page import="java.util.List"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%String mens=(String) request.getParameter("valor"); %>
	<table>
	<logic:iterate id="element" name="Resultado" >
	
	<bean:write name="element" />
</logic:iterate>	
    </table>
</body>
</html>