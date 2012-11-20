<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Sistema de Aprovisionamiento del LBS</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon" />
		<link rel="icon" type="image/png" href="<c:url value="/resources/images/favicon.png" />" />		
		<link rel="stylesheet" href="<c:url value="/resources/styles/reset.css" />" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/resources/styles/blitzer/jquery-ui.css" />" type="text/css" />
		<link rel="stylesheet" href="<c:url value="/resources/styles/general.css" />" type="text/css" />
		<script type="text/javascript" src="<c:url value="/resources/scripts/jquery.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/scripts/jquery-ui.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/scripts/general.js" />"></script>
	</head>
	<body>
		<div id="main">
			<input type="hidden" id="baseURL" value="<c:url value="/" />" />
			<div id="cabecera">
				<a href="<c:url value="/" />">
					<img src="<c:url value="/resources/images/logo.png" />" alt="Claro" />
					<strong>Sistema de Aprovisionamiento del LBS</strong>
					
				</a>
			</div>
			<div id="login">
				<form action="<c:url value="/login" />" method="post">
					<fieldset>
						<p>
							<label for="usuario">Usuario:</label>
							<input name="numero" id="usuario" />
						</p>
						<p>
							<label for="clave">Contraseña:</label>
							<input name="clave" id="clave" type="password" />
						</p>
						<p>
							<input type="submit" value="Ingresar" style="float: right;" />
						</p>
					</fieldset>
				</form>
				<span class="error">${error}</span>
			</div>			
		</div>
	</body>
</html>
	