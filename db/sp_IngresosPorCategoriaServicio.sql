CREATE PROCEDURE sp_IngresosPorCategoriaServicio
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        s.categoria      AS categoria,
        SUM(ds.subtotal) AS total
    FROM DetalleServicio ds
        INNER JOIN Servicio s 
            ON ds.id_servicio = s.id_servicio
    GROUP BY s.categoria
    ORDER BY total DESC;
END;
GO