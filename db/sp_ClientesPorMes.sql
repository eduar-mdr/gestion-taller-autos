CREATE PROCEDURE sp_ClientesPorMes
AS
BEGIN
    SELECT 
        YEAR(fecha_registro) AS anio,
        MONTH(fecha_registro) AS mes,
        COUNT(*) AS cantidad
    FROM Cliente
    GROUP BY YEAR(fecha_registro), MONTH(fecha_registro)
    ORDER BY anio, mes;
END;
GO
