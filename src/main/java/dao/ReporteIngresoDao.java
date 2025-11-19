/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author fuent
 */
import modelo.ReporteIngreso;
import conexion.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteIngresoDao{
    
    // Obtener reporte de ingresos por servicios
    public List<ReporteIngreso> obtenerIngresosServicios(String fechaInicio, String fechaFin) {
        List<ReporteIngreso> reportes = new ArrayList<>();
        String sql = "SELECT " +
                    "    'Servicio' AS tipo, " +
                    "    s.nombre, " +
                    "    s.categoria, " +
                    "    ISNULL(SUM(ds.cantidad), 0) AS cantidad_vendida, " +
                    "    s.precio AS precio_unitario, " +
                    "    ISNULL(SUM(ds.subtotal), 0) AS ingreso_total " +
                    "FROM Servicio s " +
                    "LEFT JOIN DetalleServicio ds ON s.id_servicio = ds.id_servicio " +
                    "LEFT JOIN OrdenTrabajo ot ON ds.id_orden = ot.id_orden " +
                    "WHERE (ot.fecha_ingreso >= ? AND ot.fecha_ingreso <= ? OR ot.fecha_ingreso IS NULL) " +
                    "GROUP BY s.id_servicio, s.nombre, s.categoria, s.precio " +
                    "ORDER BY ingreso_total DESC";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Convertir las fechas a formato completo para comparación
            ps.setString(1, fechaInicio + " 00:00:00");
            ps.setString(2, fechaFin + " 23:59:59");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ReporteIngreso reporte = new ReporteIngreso();
                reporte.setTipo(rs.getString("tipo"));
                reporte.setNombre(rs.getString("nombre"));
                reporte.setCategoria(rs.getString("categoria"));
                reporte.setCantidadVendida(rs.getInt("cantidad_vendida"));
                reporte.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
                reporte.setIngresoTotal(rs.getBigDecimal("ingreso_total"));
                reportes.add(reporte);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return reportes;
    }
    
    // Obtener reporte de ingresos por repuestos
    public List<ReporteIngreso> obtenerIngresosRepuestos(String fechaInicio, String fechaFin) {
        List<ReporteIngreso> reportes = new ArrayList<>();
        String sql = "SELECT " +
                    "    'Repuesto' AS tipo, " +
                    "    r.nombre, " +
                    "    p.nombre AS proveedor, " +
                    "    ISNULL(SUM(dr.cantidad), 0) AS cantidad_vendida, " +
                    "    r.precio AS precio_unitario, " +
                    "    ISNULL(SUM(dr.subtotal), 0) AS ingreso_total " +
                    "FROM Repuesto r " +
                    "LEFT JOIN DetalleRepuesto dr ON r.id_repuesto = dr.id_repuesto " +
                    "LEFT JOIN OrdenTrabajo ot ON dr.id_orden = ot.id_orden " +
                    "LEFT JOIN Proveedor p ON r.id_proveedor = p.id_proveedor " +
                    "WHERE (ot.fecha_ingreso >= ? AND ot.fecha_ingreso <= ? OR ot.fecha_ingreso IS NULL) " +
                    "GROUP BY r.id_repuesto, r.nombre, p.nombre, r.precio " +
                    "ORDER BY ingreso_total DESC";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Convertir las fechas a formato completo para comparación
            ps.setString(1, fechaInicio + " 00:00:00");
            ps.setString(2, fechaFin + " 23:59:59");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ReporteIngreso reporte = new ReporteIngreso();
                reporte.setTipo(rs.getString("tipo"));
                reporte.setNombre(rs.getString("nombre"));
                reporte.setCategoria(rs.getString("proveedor"));
                reporte.setCantidadVendida(rs.getInt("cantidad_vendida"));
                reporte.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
                reporte.setIngresoTotal(rs.getBigDecimal("ingreso_total"));
                reportes.add(reporte);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return reportes;
    }
    
    // Obtener reporte combinado
    public List<ReporteIngreso> obtenerIngresosCombinado(String fechaInicio, String fechaFin) {
        List<ReporteIngreso> reportes = new ArrayList<>();
        reportes.addAll(obtenerIngresosServicios(fechaInicio, fechaFin));
        reportes.addAll(obtenerIngresosRepuestos(fechaInicio, fechaFin));
        return reportes;
    }
}