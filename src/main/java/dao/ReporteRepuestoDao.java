package dao;

import conexion.ConexionDB;
import modelo.ReporteRepuesto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteRepuestoDao {

    // Repuestos más utilizados (por fecha, todos los proveedores)
    public List<ReporteRepuesto> obtenerRepuestosMasUsados(String fechaInicio, String fechaFin) {
        List<ReporteRepuesto> lista = new ArrayList<>();

        String sql =
            "SELECT " +
            "   r.nombre AS repuesto, " +
            "   ISNULL(p.nombre, 'Sin proveedor') AS proveedor, " +
            "   ISNULL(SUM(dr.cantidad), 0) AS cantidad_usada, " +
            "   ISNULL(SUM(dr.subtotal), 0) AS consumo_total " +
            "FROM DetalleRepuesto dr " +
            "INNER JOIN Repuesto r ON dr.id_repuesto = r.id_repuesto " +
            "LEFT JOIN Proveedor p ON r.id_proveedor = p.id_proveedor " +
            "INNER JOIN OrdenTrabajo ot ON dr.id_orden = ot.id_orden " +
            "WHERE ot.fecha_ingreso BETWEEN ? AND ? " +
            "GROUP BY r.nombre, p.nombre " +
            "ORDER BY cantidad_usada DESC, consumo_total DESC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fechaInicio + " 00:00:00");
            ps.setString(2, fechaFin   + " 23:59:59");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReporteRepuesto r = new ReporteRepuesto();
                    r.setRepuesto(rs.getString("repuesto"));
                    r.setProveedor(rs.getString("proveedor"));
                    r.setCantidadUsada(rs.getInt("cantidad_usada"));
                    r.setConsumoTotal(rs.getBigDecimal("consumo_total"));
                    lista.add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Repuestos más utilizados, filtrando por proveedor específico (opcional)
    public List<ReporteRepuesto> obtenerRepuestosPorProveedor(int idProveedor,
                                                              String fechaInicio,
                                                              String fechaFin) {
        List<ReporteRepuesto> lista = new ArrayList<>();

        String sql =
            "SELECT " +
            "   r.nombre AS repuesto, " +
            "   ISNULL(p.nombre, 'Sin proveedor') AS proveedor, " +
            "   ISNULL(SUM(dr.cantidad), 0) AS cantidad_usada, " +
            "   ISNULL(SUM(dr.subtotal), 0) AS consumo_total " +
            "FROM DetalleRepuesto dr " +
            "INNER JOIN Repuesto r ON dr.id_repuesto = r.id_repuesto " +
            "LEFT JOIN Proveedor p ON r.id_proveedor = p.id_proveedor " +
            "INNER JOIN OrdenTrabajo ot ON dr.id_orden = ot.id_orden " +
            "WHERE r.id_proveedor = ? " +
            "  AND ot.fecha_ingreso BETWEEN ? AND ? " +
            "GROUP BY r.nombre, p.nombre " +
            "ORDER BY cantidad_usada DESC, consumo_total DESC";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            ps.setString(2, fechaInicio + " 00:00:00");
            ps.setString(3, fechaFin   + " 23:59:59");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReporteRepuesto r = new ReporteRepuesto();
                    r.setRepuesto(rs.getString("repuesto"));
                    r.setProveedor(rs.getString("proveedor"));
                    r.setCantidadUsada(rs.getInt("cantidad_usada"));
                    r.setConsumoTotal(rs.getBigDecimal("consumo_total"));
                    lista.add(r);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
