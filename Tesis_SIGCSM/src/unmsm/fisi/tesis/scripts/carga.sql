USE SIGCSM
GO


INSERT INTO  SM_ORGANIZACION 
	(CODIGOORGANIZACION,NOMBRECORTO,NOMBRELARGO,FUENTEINFORMACION,NUMEROREGLAS,NUMEROREGLASACONSIDERAR,
	 PRIORIDAD,ESTADO)
VALUES	(1, 'OMS'	, 'Organizaci�n Mundial de la Salud'					, '', 15, 3, 1, 'S'),
		(2, 'GESMM'	, 'Grupo de Estudio del S�ndrome Metab�lico de M�xico'	, '', 15, 3, 2, 'S'),
		(3, 'AAC'	, 'Asociaci�n Americana del Coraz�n'					, '', 1 , 1, 3, 'S'),
		(4, 'PNEC-ATP III', 'Programa Nacional de Educaci�n del Coresterol-Tercer Panel de Tratamiento en Adultos','',15,3,4,'S'),
		(5, 'IDF'	, 'Federaci�n Internacional de Diabetes'				, '', 15, 3, 5, 'S'),
		(6, 'CNE'	, 'Colegio Norteamericano de Endocrinolog�a'			, '', 10, 3, 6, 'S'),
		(7, 'GEIR'	, 'Grupo Europeo para el Estudio de la Resistencia a la Insulina', '', 15, 3, 7, 'S')
		
GO	


INSERT INTO SM_OPERADORES
	(CODIGOOPERADOR, NOMBRE, ESTADO, USAR)	
VALUES (1,'RULETA'   , 'S', 'S'),
	   (2,'MUTACION' , 'S', 'S'),
	   (3,'TORNEO'   , 'S', 'N')
	   
GO
