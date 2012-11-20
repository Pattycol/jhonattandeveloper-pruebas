<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:insert page="/baseLayout.jsp" flush="true">
    <tiles:put name="title" value="Sistema de Consulta Web LBS"  />
    <tiles:put name="header" value="/cabecera.jsp" />
    <tiles:put name="izquierda" value="/pages/consulta/nuevaConsulta.jsp" />
    <tiles:put name="derecha" value="/blank.html" />
    <tiles:put name="footer" value="/footer.jsp" />
</tiles:insert>