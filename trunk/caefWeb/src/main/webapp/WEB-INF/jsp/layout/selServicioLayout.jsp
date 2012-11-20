<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<sj:head locale="es" jqueryui="true"   jquerytheme="blitzer"/>
  <head/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sistema Mi Claro</title>

<link rel="stylesheet" href="../css/text.css" />
<link rel="stylesheet" href="../css/grid.css" />
<link rel="stylesheet" href="../css/principal.css" />
<link rel="stylesheet" href="../css/facturado.css" />
<link rel="stylesheet" href="../css/menu.css" />
<link rel="stylesheet" href="../css/submenu.css" />
<link rel="stylesheet" href="../css/styleDiv.css" />
<link rel="stylesheet" href="../css/login.css" />
<link rel="stylesheet" href="../js/drop_down.js" />



<style type="text/css">
<!--
.Estilo4 {font-size: 12px}
.errors {
	background-color:#FFCCCC;
	border:1px solid #CC0000;
	width:400px;
	margin-bottom:8px;
}

.detalleCabecera
{
	background-image:url(../img/barrarojo.jpg) ;
	font-size:12px;
	color:#FFFFFF;
	margin-bottom:auto;
	text-align:left;
}

.errors li{ 
	list-style: none; 
}

.msg-ok {
    border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border: 1px solid;
	margin: 0 0 15px 0;
	padding: 8px 10px 0 10px;
	width: 400px;
	border-color: #A6D877;
	background: #D2ECBA url("../img/msg-ok.png") repeat-x;
	color: #336801;
}

.msg-ok li {
width:  400px !important;
	list-style: none;
	text-align: center;
}

.msg-ok span {
width:  400px !important;
	padding-left:15px;
	margin-left:5px;
}


.msg-error {
     border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border: 1px solid;
	margin: 0 0 15px 0;
	padding: 8px 10px 0 10px;
	width: 400px;
	border-color: #F3ABAB;
	background: #F9C9C9 url("../img/msg-error.png") repeat-x;
	color: #8D0D0D;
}

.msg-error li {
	list-style: none;
	width:  400px !important;
	text-align: center;
}

.msg-error span {
width:  400px !important;
	padding-left:15px;
	margin-left:5px;
}


.centrar{
    width:  400px !important;
	margin:0 auto !important;
	
	text-align: center !important;
}
-->
</style>
</head>
<body>

	<div class="container_12">
	
		 <tiles:insertAttribute name="header" />
	
		 <div class="grid_10 " >
			 <tiles:insertAttribute name="body" />
		 </div>
		 
		 <div class="clear"></div>
				 
		 <div class="grid_12">
			<div class="container_12">
				   <tiles:insertAttribute name="footer" />
			</div>
		</div>
		<div class="clear">
		</div>
	</div>
	
	
</body>
</html>