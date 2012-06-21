<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form:form modelAttribute="departamento"  action="peru.htm?accion=addDepart" >
		
		 <table style="margin: auto">
		 		<tr>
		 			<td><h1>Agregar Departamento</h1></td>
		 		</tr>
                <tr>
                    <td>Id</td>
                    <td>
                        <form:input path="id"/>
                    </td>
                </tr>
                 <tr>
                    <td>Departamento</td>
                    <td>
                        <form:input path="departamento"/>
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <input type="submit" value="Agregar"  />
                    </td>
                </tr>
		</table>
	
	</form:form>
	
</body>
</html>