
USE SIGCSM
GO


IF EXISTS (select * from dbo.sysobjects where id = object_id(N'[dbo].[ser_fSplit]') and xtype = 'TF')
	DROP FUNCTION [dbo].[ser_fSplit]
GO


CREATE FUNCTION ser_fSplit(@CADENA VARCHAR(8000), @DELIMITADOR CHAR(1))         
RETURNS @TEMPTABLA TABLE (ITEMS VARCHAR(8000))         
AS         
BEGIN         
    DECLARE @IDX INT         
    DECLARE @SLICE VARCHAR(8000)         
        
    SELECT @IDX = 1         
        IF LEN(@CADENA)<1 OR @CADENA IS NULL  RETURN         
        
    WHILE @IDX!= 0         
    BEGIN         
        SET @IDX = CHARINDEX(@DELIMITADOR,@CADENA)         
        IF @IDX!=0         
            SET @SLICE = LEFT(@CADENA,@IDX - 1)         
        ELSE         
            SET @SLICE = @CADENA         
            
        IF(LEN(@SLICE)>0)    
            INSERT INTO @TEMPTABLA(ITEMS) VALUES(@SLICE)         
    
        SET @CADENA = RIGHT(@CADENA,LEN(@CADENA) - @IDX)         
        IF LEN(@CADENA) = 0 BREAK         
    END     
RETURN         
END 
GO

