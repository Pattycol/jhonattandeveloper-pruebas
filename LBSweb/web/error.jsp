<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html>
<head><title>JSP Page</title>
<script LANGUAGE="JavaScript">
function redirecciona(){
    window.parent.location="login.jsp";
    window.parent.submit();
}
</script>
</head>
<body onload='javascript:redirecciona()'>


</body>
</html>
