CREATE PROCEDURE sp_VentasMensuales
AS
BEGIN
    SELECT 
        YEAR(fecha_pago) AS anio,
        MONTH(fecha_pago) AS mes,
        SUM(ISNULL(monto,0) - ISNULL(descuento,0)) AS total
    FROM Pago
    GROUP BY YEAR(fecha_pago), MONTH(fecha_pago)
    ORDER BY anio, mes;
END;
GO
