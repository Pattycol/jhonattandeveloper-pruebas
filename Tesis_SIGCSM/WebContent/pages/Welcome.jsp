<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>


<html:html>
<head>
<title><bean:message key="pagina.title" /></title>
<html:base />
</head>
<body bgcolor="white">

	<logic:notPresent name="org.apache.struts.action.MESSAGE"
		scope="application">
		<font color="red"> ERROR: Application resources not loaded --
			check servlet container logs for error messages. </font>
	</logic:notPresent>


	<h4 style="color: blue;" align="center">
		<bean:message key="welcome.heading" />
	</h4>
	<p>
		<bean:message key="welcome.message" />
	</p>

	<%String mens=((StringBuilder) request.getAttribute("mensaje")).toString(); %>
	<%List Resultado= (List)request.getAttribute("resultado"); %>

	<table align="center">
		<tr>
			<td>
				<% out.println(mens);%>
			</td>

		</tr>


	</table>
</body>
</html:html>