<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<script type="text/javascript">
      function pivotear(){
            document.loginForm.submit();
      }

</script>

<head>
  <title></title>
</head>
<body>

<FORM name="loginForm" id="loginForm" action="http://172.19.52.50:7001/smsWebApp/index.htm" method="post" >
 <input type="hidden" id="fonoOrigen" name="fonoOrigen" value="${smsFrom.fonoOrigen }">
  <input type="hidden" id="dominio" name="dominio"value="${smsFrom.dominio }" >
  <input type="hidden" id="ip" name="ip"value="${smsFrom.ip }">
</FORM>
  <script type="text/javascript">
   pivotear();
  </script>
</body>
</html>
