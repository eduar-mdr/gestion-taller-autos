/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author fuent
 */
import modelo.OrdenTrabajo;
import modelo.DetalleServicio;
import modelo.DetalleRepuesto;
import conexion.ConexionDB;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoDao {
    
    // Listar todas las órdenes con información completa
    public List<OrdenTrabajo> listarOrdenes() throws SQLException {
        String sql = "SELECT ot.*, " +
                     "c.nombre + ' ' + c.apellido as nombre_cliente, " +
                     "c.telefono as telefono_cliente, " +
                     "v.placa, v.modelo, mv.nombre_marca, " +
                     "e.nombre + ' ' + e.apellido as nombre_empleado, " +
                     "e.cargo " +
                     "FROM OrdenTrabajo ot " +
                     "INNER JOIN Cliente c ON ot.id_cliente = c.id_cliente " +
                     "INNER JOIN Vehiculo v ON ot.id_vehiculo = v.id_vehiculo " +
                     "INNER JOIN MarcaVehiculo mv ON v.id_marca = mv.id_marca " +
                     "LEFT JOIN Empleado e ON ot.id_empleado = e.id_empleado " +
                     "ORDER BY ot.fecha_ingreso DESC";
        
        List<OrdenTrabajo> ordenes = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                OrdenTrabajo orden = mapearOrden(rs);
                ordenes.add(orden);
            }
        }
        
        return ordenes;
    }
    
    // Obtener orden por ID
    public OrdenTrabajo obtenerOrdenPorId(int idOrden) throws SQLException {
        String sql = "SELECT ot.*, " +
                     "c.nombre + ' ' + c.apellido as nombre_cliente, " +
                     "c.telefono as telefono_cliente, " +
                     "v.placa, v.modelo, mv.nombre_marca, " +
                     "e.nombre + ' ' + e.apellido as nombre_empleado, " +
                     "e.cargo " +
                     "FROM OrdenTrabajo ot " +
                     "INNER JOIN Cliente c ON ot.id_cliente = c.id_cliente " +
                     "INNER JOIN Vehiculo v ON ot.id_vehiculo = v.id_vehiculo " +
                     "INNER JOIN MarcaVehiculo mv ON v.id_marca = mv.id_marca " +
                     "LEFT JOIN Empleado e ON ot.id_empleado = e.id_empleado " +
                     "WHERE ot.id_orden = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapearOrden(rs);
            }
        }
        
        return null;
    }
    
    // Crear nueva orden de trabajo
    public boolean crearOrden(OrdenTrabajo orden) throws SQLException {
        String sql = "INSERT INTO OrdenTrabajo (id_vehiculo, id_cliente, id_empleado, " +
                     "fecha_ingreso, fecha_entrega, estado, diagnostico, observaciones, " +
                     "tiempo_estimado, documentos_url) " +
                     "VALUES (?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, orden.getIdVehiculo());
            stmt.setInt(2, orden.getIdCliente());
            
            if (orden.getIdEmpleado() > 0) {
                stmt.setInt(3, orden.getIdEmpleado());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            
            if (orden.getFechaEntrega() != null) {
                stmt.setTimestamp(4, orden.getFechaEntrega());
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }
            
            stmt.setString(5, orden.getEstado());
            stmt.setString(6, orden.getDiagnostico());
            stmt.setString(7, orden.getObservaciones());
            
            if (orden.getTiempoEstimado() != null) {
                stmt.setBigDecimal(8, orden.getTiempoEstimado());
            } else {
                stmt.setNull(8, Types.DECIMAL);
            }
            
            stmt.setString(9, orden.getDocumentosUrl());
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    orden.setIdOrden(rs.getInt(1));
                }
                return true;
            }
        }
        
        return false;
    }
    
    // Actualizar orden de trabajo
    public boolean actualizarOrden(OrdenTrabajo orden) throws SQLException {
        String sql = "UPDATE OrdenTrabajo SET " +
                     "id_empleado = ?, fecha_entrega = ?, estado = ?, " +
                     "diagnostico = ?, observaciones = ?, tiempo_estimado = ?, " +
                     "documentos_url = ? " +
                     "WHERE id_orden = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            if (orden.getIdEmpleado() > 0) {
                stmt.setInt(1, orden.getIdEmpleado());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }
            
            if (orden.getFechaEntrega() != null) {
                stmt.setTimestamp(2, orden.getFechaEntrega());
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }
            
            stmt.setString(3, orden.getEstado());
            stmt.setString(4, orden.getDiagnostico());
            stmt.setString(5, orden.getObservaciones());
            
            if (orden.getTiempoEstimado() != null) {
                stmt.setBigDecimal(6, orden.getTiempoEstimado());
            } else {
                stmt.setNull(6, Types.DECIMAL);
            }
            
            stmt.setString(7, orden.getDocumentosUrl());
            stmt.setInt(8, orden.getIdOrden());
            
            return stmt.executeUpdate() > 0;
        }
    }
    
    // Agregar servicio a la orden
    public boolean agregarServicio(DetalleServicio detalle) throws SQLException {
        String sql = "INSERT INTO DetalleServicio (id_orden, id_servicio, cantidad, subtotal) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detalle.getIdOrden());
            stmt.setInt(2, detalle.getIdServicio());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setBigDecimal(4, detalle.getSubtotal());
            
            boolean resultado = stmt.executeUpdate() > 0;
            
            if (resultado) {
                actualizarTotalesOrden(detalle.getIdOrden());
            }
            
            return resultado;
        }
    }
    
    // Agregar repuesto a la orden
    public boolean agregarRepuesto(DetalleRepuesto detalle) throws SQLException {
        String sql = "INSERT INTO DetalleRepuesto (id_orden, id_repuesto, cantidad, subtotal) " +
                     "VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, detalle.getIdOrden());
            stmt.setInt(2, detalle.getIdRepuesto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setBigDecimal(4, detalle.getSubtotal());
            
            boolean resultado = stmt.executeUpdate() > 0;
            
            if (resultado) {
                actualizarTotalesOrden(detalle.getIdOrden());
            }
            
            return resultado;
        }
    }
    
    // Listar servicios de una orden
    public List<DetalleServicio> listarServiciosOrden(int idOrden) throws SQLException {
        String sql = "SELECT ds.*, s.nombre, s.descripcion, s.precio " +
                     "FROM DetalleServicio ds " +
                     "INNER JOIN Servicio s ON ds.id_servicio = s.id_servicio " +
                     "WHERE ds.id_orden = ?";
        
        List<DetalleServicio> servicios = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DetalleServicio detalle = new DetalleServicio();
                detalle.setIdDetalleServicio(rs.getInt("id_detalle_servicio"));
                detalle.setIdOrden(rs.getInt("id_orden"));
                detalle.setIdServicio(rs.getInt("id_servicio"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setSubtotal(rs.getBigDecimal("subtotal"));
                detalle.setNombreServicio(rs.getString("nombre"));
                detalle.setDescripcionServicio(rs.getString("descripcion"));
                detalle.setPrecioServicio(rs.getBigDecimal("precio"));
                
                servicios.add(detalle);
            }
        }
        
        return servicios;
    }
    
    // Listar repuestos de una orden
    public List<DetalleRepuesto> listarRepuestosOrden(int idOrden) throws SQLException {
        String sql = "SELECT dr.*, r.nombre, r.descripcion, r.precio " +
                     "FROM DetalleRepuesto dr " +
                     "INNER JOIN Repuesto r ON dr.id_repuesto = r.id_repuesto " +
                     "WHERE dr.id_orden = ?";
        
        List<DetalleRepuesto> repuestos = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DetalleRepuesto detalle = new DetalleRepuesto();
                detalle.setIdDetalleRepuesto(rs.getInt("id_detalle_repuesto"));
                detalle.setIdOrden(rs.getInt("id_orden"));
                detalle.setIdRepuesto(rs.getInt("id_repuesto"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setSubtotal(rs.getBigDecimal("subtotal"));
                detalle.setNombreRepuesto(rs.getString("nombre"));
                detalle.setDescripcionRepuesto(rs.getString("descripcion"));
                detalle.setPrecioRepuesto(rs.getBigDecimal("precio"));
                
                repuestos.add(detalle);
            }
        }
        
        return repuestos;
    }
    
    // Eliminar servicio de la orden
    public boolean eliminarServicio(int idDetalleServicio, int idOrden) throws SQLException {
        String sql = "DELETE FROM DetalleServicio WHERE id_detalle_servicio = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idDetalleServicio);
            boolean resultado = stmt.executeUpdate() > 0;
            
            if (resultado) {
                actualizarTotalesOrden(idOrden);
            }
            
            return resultado;
        }
    }
    
    // Eliminar repuesto de la orden
    public boolean eliminarRepuesto(int idDetalleRepuesto, int idOrden) throws SQLException {
        String sql = "DELETE FROM DetalleRepuesto WHERE id_detalle_repuesto = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idDetalleRepuesto);
            boolean resultado = stmt.executeUpdate() > 0;
            
            if (resultado) {
                actualizarTotalesOrden(idOrden);
            }
            
            return resultado;
        }
    }
    
    // Actualizar totales de la orden
    private void actualizarTotalesOrden(int idOrden) throws SQLException {
        String sql = "UPDATE OrdenTrabajo SET " +
                     "total_mano_obra = (SELECT ISNULL(SUM(subtotal), 0) FROM DetalleServicio WHERE id_orden = ?), " +
                     "total_repuestos = (SELECT ISNULL(SUM(subtotal), 0) FROM DetalleRepuesto WHERE id_orden = ?) " +
                     "WHERE id_orden = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            stmt.setInt(2, idOrden);
            stmt.setInt(3, idOrden);
            stmt.executeUpdate();
        }
    }
    
    // Método auxiliar para mapear ResultSet a OrdenTrabajo
    private OrdenTrabajo mapearOrden(ResultSet rs) throws SQLException {
        OrdenTrabajo orden = new OrdenTrabajo();
        orden.setIdOrden(rs.getInt("id_orden"));
        orden.setIdVehiculo(rs.getInt("id_vehiculo"));
        orden.setIdCliente(rs.getInt("id_cliente"));
        orden.setIdEmpleado(rs.getInt("id_empleado"));
        orden.setFechaIngreso(rs.getTimestamp("fecha_ingreso"));
        orden.setFechaEntrega(rs.getTimestamp("fecha_entrega"));
        orden.setEstado(rs.getString("estado"));
        orden.setDiagnostico(rs.getString("diagnostico"));
        orden.setObservaciones(rs.getString("observaciones"));
        orden.setTiempoEstimado(rs.getBigDecimal("tiempo_estimado"));
        orden.setDocumentosUrl(rs.getString("documentos_url"));
        orden.setTotalManoObra(rs.getBigDecimal("total_mano_obra"));
        orden.setTotalRepuestos(rs.getBigDecimal("total_repuestos"));
        
        // Calcular total general
        BigDecimal totalGeneral = orden.getTotalManoObra().add(orden.getTotalRepuestos());
        orden.setTotalGeneral(totalGeneral);
        
        // Información adicional
        orden.setNombreCliente(rs.getString("nombre_cliente"));
        orden.setTelefonoCliente(rs.getString("telefono_cliente"));
        orden.setPlacaVehiculo(rs.getString("placa"));
        orden.setModeloVehiculo(rs.getString("modelo"));
        orden.setMarcaVehiculo(rs.getString("nombre_marca"));
        orden.setNombreEmpleado(rs.getString("nombre_empleado"));
        orden.setCargoEmpleado(rs.getString("cargo"));
        
        return orden;
    }
}