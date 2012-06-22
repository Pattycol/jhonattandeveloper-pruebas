<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Personas</h1>
	<c:url var="addPersona" value="/personas/add"></c:url>
	<table style="border: 1px solid; width: 500px; text-align: center;" >
		<thead style="background:#fcf" >
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Money</th>
				<th colspan="3"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach  items="${personas}" var="persona">
				<c:url var="editPersona" value="/personas/edit?id=${persona.id}"></c:url>
				<c:url var="deletePersona" value="/personas/delete?id=${persona.id}"></c:url>
				<tr>
					<td><c:out value="${persona.first_name}"></c:out></td>
					<td><c:out value="${persona.last_name}"></c:out></td>
					<td><c:out value="${persona.money}"></c:out></td>
					<td><a href="editPersona">Editar</a></td>
					<td><a href="deletePersona">Eliminar</a></td>
					<td><a href="addPersona">Agregar</a></td>		
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${empty personas}">
		No hay registros de personas.<a href="${addPersona}">Agregar</a> una persona.
	
	</c:if>

</body>
</html>