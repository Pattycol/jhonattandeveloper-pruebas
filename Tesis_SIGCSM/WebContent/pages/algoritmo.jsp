<%@ include file="../soporte/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><bean:message key="pagina.title" /></title>
</head>
<body>
	<%@ include file="../soporte/cabecera.jsp"%>
	<table cellpadding="0" cellspacing="0"
		style="width: 769; height: 400; background-color: white; cursor: default">
		<tr>
			<td valign="middle"><html:form action="/configuracion">


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
												style="FONT-SIZE: 27px; COLOR: #ff0000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: left"><span
												class="Estilo3"><span class="iceOutTxt Estilo3"
													id="formDatos:_idJsp38"
													style="FONT-SIZE: 27px; COLOR: #ff0000; FONT-FAMILY: Arial, Helvetica, sans-serif; TEXT-ALIGN: left">Algoritmo
														Genético</span></span></td>
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
													style="width: 120px; font-weight: bold; font-size: 11;">Tamano
														Poblacion</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-0-1">
													<html:text property="t_poblacion" size="10" value="20" />
												</td>
											</tr>
											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Pacientes</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="n_pacientes" size="10" value="20" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para OMS</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasOMS" size="10" value="3" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para GESMM</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasGESMM" size="10" value="3" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para ACC</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasAAC" size="10" value="1" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para PNE</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasPNE" size="10" value="3" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para FDI</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasFDI" size="10" value="3" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para CNE</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasCNE" size="10" value="3" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Reglas para GERI</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numReglasGERI" size="10" value="3" />
												</td>
											</tr>


											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Hallazgos</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numHallazgos" size="10" value="53" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Numero
														Generaciones</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="numGeneraciones" size="10" value="20" />
												</td>
											</tr>

											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Probabilidad
														para Aplicar Croosover</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="x" size="10" value="0.28" />
												</td>
											</tr>


											<tr class="icePnlGrdRow2">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-1-0"><span
													class="iceOutTxt" id="formDatos:_idJsp42"
													style="width: 120px; font-weight: bold; font-size: 11;">Probabilidad
														para Aplicar Mutacion</span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-1-1">
													<html:text property="u" size="10" value="0.89" />
												</td>
											</tr>

											<tr class="icePnlGrdRow1">
												<td class="icePnlGrdCol1" id="formDatos:_idJsp40-2-0"><span
													class="iceOutTxt" id="formDatos:_idJsp43"></span></td>
												<td class="icePnlGrdCol2" id="formDatos:_idJsp40-2-1">
													<html:submit>
														<bean:message key="button.algoritmo" />
													</html:submit>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr class="icePnlGrdRow1">
								<td class="icePnlGrdCol1" id="formDatos:_idJsp29-6-0"><br />
								</td>
							</tr>
						</tbody>
					</table>

				</html:form></td>
		</tr>

		<%@ include file="../soporte/piepagina.jsp"%>
	</table>
</body>
</html>