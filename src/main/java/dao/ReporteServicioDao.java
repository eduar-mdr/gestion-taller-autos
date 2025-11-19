package dao;

import conexion.ConexionDB;
import modelo.ReporteServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteServicioDao {

    // 🔹 Reporte por fecha (todos los mecánicos)
    public List<ReporteServicio> obtenerServiciosPorFecha(String fechaInicio, String fechaFin) {
        List<ReporteServicio> lista = new ArrayList<>();

        String sql =
            "SELECT " +
            "   ot.fecha_ingreso AS fecha, " +
            "   ISNULL(e.nombre + ' ' + e.apellido, 'Sin asignar') AS mecanico, " +
            "   s.nombre AS servicio, " +
            "   ISNULL(SUM(ds.cantidad), 0) AS cantidad, " +
            "   ISNULL(SUM(ds.subtotal), 0) AS ingreso " +
            "FROM OrdenTrabajo ot " +
            "INNER JOIN DetalleServicio ds ON ot.id_orden = ds.id_orden " +
            "INNER JOIN Servicio s ON ds.id_servicio = s.id_servicio " +
            "LEFT JOIN Empleado e ON ot.id_empleado = e.id_empleado " +
            "WHERE ot.fecha_ingreso BETWEEN ? AND ? " +
            "GROUP BY ot.fecha_ingreso, e.nombre, e.apellido, s.nombre " +
            "ORDER BY ot.fecha_ingreso ASC, mecanico ASC, servicio ASC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fechaInicio + " 00:00:00");
            ps.setString(2, fechaFin   + " 23:59:59");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReporteServicio r = new ReporteServicio();
                    r.setFecha(rs.getTimestamp("fecha"));
                    r.setMecanico(rs.getString("mecanico"));
                    r.setServicio(rs.getString("servicio"));
                    r.setCantidad(rs.getInt("cantidad"));
                    r.setIngreso(rs.getBigDecimal("ingreso"));
                    lista.add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // 🔹 Reporte por mecánico + fecha
    public List<ReporteServicio> obtenerServiciosPorMecanico(int idMecanico,
                                                             String fechaInicio,
                                                             String fechaFin) {
        List<ReporteServicio> lista = new ArrayList<>();

        String sql =
            "SELECT " +
            "   ot.fecha_ingreso AS fecha, " +
            "   ISNULL(e.nombre + ' ' + e.apellido, 'Sin asignar') AS mecanico, " +
            "   s.nombre AS servicio, " +
            "   ISNULL(SUM(ds.cantidad), 0) AS cantidad, " +
            "   ISNULL(SUM(ds.subtotal), 0) AS ingreso " +
            "FROM OrdenTrabajo ot " +
            "INNER JOIN DetalleServicio ds ON ot.id_orden = ds.id_orden " +
            "INNER JOIN Servicio s ON ds.id_servicio = s.id_servicio " +
            "LEFT JOIN Empleado e ON ot.id_empleado = e.id_empleado " +
            "WHERE ot.id_empleado = ? " +
            "  AND ot.fecha_ingreso BETWEEN ? AND ? " +
            "GROUP BY ot.fecha_ingreso, e.nombre, e.apellido, s.nombre " +
            "ORDER BY ot.fecha_ingreso ASC, servicio ASC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMecanico);
            ps.setString(2, fechaInicio + " 00:00:00");
            ps.setString(3, fechaFin   + " 23:59:59");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReporteServicio r = new ReporteServicio();
                    r.setFecha(rs.getTimestamp("fecha"));
                    r.setMecanico(rs.getString("mecanico"));
                    r.setServicio(rs.getString("servicio"));
                    r.setCantidad(rs.getInt("cantidad"));
                    r.setIngreso(rs.getBigDecimal("ingreso"));
                    lista.add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
