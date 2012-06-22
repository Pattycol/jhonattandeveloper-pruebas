<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Nueva Persona</h1>
	<c:url var="savePersona" value="/personas/add"></c:url>
	<form:form modelAttribute="persona" method="POST" action="${savePersona}"  >
		<table>
			<tr>
				<td><form:label path="first_name">Nombre:</form:label></td>
				<td><form:input path="first_name"/></td>
			</tr>
			<tr>
				<td><form:label path="last_name">Apellido:</form:label></td>
				<td><form:input path="last_name"/></td>
			</tr>
			<tr>
				<td><form:label path="money">Money:</form:label></td>
				<td><form:input path="money"/></td>
			</tr>
		</table>
		
		<input type="submit" value="Crear" ></input>
	</form:form>
	
</body>
</html>