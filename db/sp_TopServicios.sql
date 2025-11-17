CREATE PROCEDURE sp_TopServicios
AS
BEGIN
    SELECT TOP 5
        s.nombre AS servicio,
        SUM(ISNULL(ds.subtotal,0)) AS total
    FROM DetalleServicio ds
        INNER JOIN Servicio s      ON ds.id_servicio = s.id_servicio
        INNER JOIN OrdenTrabajo ot ON ds.id_orden = ot.id_orden
        INNER JOIN Pago p          ON p.id_orden = ot.id_orden
    GROUP BY s.nombre
    ORDER BY total DESC;
END;
GO
