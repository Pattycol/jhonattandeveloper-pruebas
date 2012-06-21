<%@ include file="../soporte/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="pagina.title"/></title>
</head>
<body>
<%@ include file="../soporte/cabecera.jsp"%>
<table cellpadding="0" cellspacing="0"
	style="width: 769; height: 400; background-color: white; cursor: default">
	<tr>
		<td valign="middle">
		<html:form action="/login">
	
			
		<table border="0" class="icePnlGrd" id="formDatos:_idJsp29"
			style="width: 384px; border-right: dashed 1 black;">
			<tbody>
				<tr class="icePnlGrdRow1">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-0-0">
					<table border="0" cellpadding="0" cellspacing="0"
						style="cursor: default" width="370">
						<tr>
							<td
								style="FONT-SIZE: 17px; COLOR: #555555; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: right"><span
								class="Estilo2"></span></td>
						</tr>
						<tr>
							<td
								style="FONT-SIZE: 27px; COLOR: #ff0000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: right"><span
								class="Estilo3"><span class="iceOutTxt Estilo3"
								id="formDatos:_idJsp38"
								style="FONT-SIZE: 27px; COLOR: #ff0000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: right">Inicio
							de sesi&oacute;n</span></span></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="icePnlGrdRow2">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-1-0"><br />
					</td>
				</tr>
				<tr class="icePnlGrdRow1">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-2-0">
					<table border="0" class="icePnlGrd" id="formDatos:_idJsp40"
						style="cursor: default">
						<tbody>
							<tr class="icePnlGrdRow1">
								<td class="icePnlGrdCol1" id="formDatos:_idJsp40-0-0"><span
									class="iceOutTxt" id="formDatos:_idJsp41"
									style="width: 120px; font-weight: bold; font-size: 11;">Usuario</span></td>
								<td class="icePnlGrdCol2" id="formDatos:_idJsp40-0-1">
								<html:text property="usuario" size="25" maxlength="50"/></td>
							</tr>
							<tr class="icePnlGrdRow2">
								<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
									class="iceOutTxt" id="formDatos:_idJsp42"
									style="width: 120px; font-weight: bold; font-size: 11;">Contrase&ntilde;a</span></td>
								<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
								<html:text property="clave" size="25" maxlength="50"/></td>
							</tr>
							<tr class="icePnlGrdRow1">
								<td class="icePnlGrdCol1" id="formDatos:_idJsp40-2-0"><span
									class="iceOutTxt" id="formDatos:_idJsp43"></span></td>
								<td class="icePnlGrdCol2" id="formDatos:_idJsp40-2-1">
								<html:submit>
									<bean:message key="button.login" />
								</html:submit></td>
							</tr>
						</tbody>
					</table>
					</td>
				</tr>
				<tr class="icePnlGrdRow2">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-3-0">
					<table style="width: 370px">
						<tr>
							<td>
							<table id="formDatos:_idJsp48" style="display: none;">
								<td></td>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr class="icePnlGrdRow1">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-4-0"><span
						class="iceOutTxt" id="formDatos:_idJsp49"
						style="width: 370px; FONT-SIZE: 12px; COLOR: #000000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: justify">Ingrese
					su c&oacute;digo de usuario asignado y la contrase&ntilde;a para
					iniciar sesi&oacute;n en el sistema.</span></td>
				</tr>
				<tr class="icePnlGrdRow2">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-5-0"><br />
					</td>
				</tr>
				<tr class="icePnlGrdRow1">
					<td class="icePnlGrdCol1" id="formDatos:_idJsp29-6-0"><br />
					</td>
				</tr>
			</tbody>
		</table>
		<div id="formDatoshdnFldsDiv"><input name="focus_hidden_field"
			type="hidden" /></div>
		</html:form>
		</td>
	</tr>

<%@ include file="../soporte/piepagina.jsp"%>
</table>
</body>
</html>