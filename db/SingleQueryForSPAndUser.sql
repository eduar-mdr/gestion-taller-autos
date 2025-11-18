 
USE Taller;


INSERT INTO Rol (nombre_rol, descripcion)
VALUES ('admin', 'admin');


INSERT INTO Usuario (nombre_usuario, email, contrasena, id_rol, estado)
VALUES ('admin', 'admin@taller.com', '123456', 1, 'Activo');




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





