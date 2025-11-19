/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author fuent
 */

import modelo.Pago;
import conexion.ConexionDB;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoDao {
    
    // Obtener el siguiente número de factura disponible
    public String obtenerSiguienteNumeroFactura() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(numero_factura, 5, LEN(numero_factura)) AS INT)) as max_num " +
                     "FROM Pago WHERE numero_factura LIKE 'FAC-%'";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                int maxNum = rs.getInt("max_num");
                return String.format("FAC-%06d", maxNum + 1);
            }
            return "FAC-000001";
        }
    }
    
    // Calcular totales de una orden de trabajo
    public Pago calcularTotalesOrden(int idOrden) throws SQLException {
        String sql = "SELECT " +
                     "    ISNULL(SUM(dr.subtotal), 0) as total_repuestos, " +
                     "    ISNULL(SUM(ds.subtotal), 0) as total_mano_obra " +
                     "FROM OrdenTrabajo ot " +
                     "LEFT JOIN DetalleRepuesto dr ON ot.id_orden = dr.id_orden " +
                     "LEFT JOIN DetalleServicio ds ON ot.id_orden = ds.id_orden " +
                     "WHERE ot.id_orden = ? " +
                     "GROUP BY ot.id_orden";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            Pago pago = new Pago();
            if (rs.next()) {
                BigDecimal totalRepuestos = rs.getBigDecimal("total_repuestos");
                BigDecimal totalManoObra = rs.getBigDecimal("total_mano_obra");
                
                pago.setTotalRepuestos(totalRepuestos);
                pago.setTotalManoObra(totalManoObra);
                pago.setTotalGeneral(totalRepuestos.add(totalManoObra));
            }
            
            return pago;
        }
    }
    
    // Obtener información completa de la orden para el pago
    public Pago obtenerInfoOrdenParaPago(int idOrden) throws SQLException {
        String sql = "SELECT ot.id_orden, " +
                     "    c.nombre + ' ' + c.apellido as nombre_cliente, " +
                     "    v.placa, " +
                     "    ISNULL(SUM(dr.subtotal), 0) as total_repuestos, " +
                     "    ISNULL(SUM(ds.subtotal), 0) as total_mano_obra " +
                     "FROM OrdenTrabajo ot " +
                     "INNER JOIN Cliente c ON ot.id_cliente = c.id_cliente " +
                     "INNER JOIN Vehiculo v ON ot.id_vehiculo = v.id_vehiculo " +
                     "LEFT JOIN DetalleRepuesto dr ON ot.id_orden = dr.id_orden " +
                     "LEFT JOIN DetalleServicio ds ON ot.id_orden = ds.id_orden " +
                     "WHERE ot.id_orden = ? " +
                     "GROUP BY ot.id_orden, c.nombre, c.apellido, v.placa";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Pago pago = new Pago();
                pago.setIdOrden(rs.getInt("id_orden"));
                pago.setNombreCliente(rs.getString("nombre_cliente"));
                pago.setPlacaVehiculo(rs.getString("placa"));
                pago.setTotalRepuestos(rs.getBigDecimal("total_repuestos"));
                pago.setTotalManoObra(rs.getBigDecimal("total_mano_obra"));
                
                BigDecimal totalGeneral = pago.getTotalRepuestos().add(pago.getTotalManoObra());
                pago.setTotalGeneral(totalGeneral);
                
                // Calcular monto pendiente
                BigDecimal montoPagado = obtenerMontoPagado(idOrden);
                pago.setMontoPendiente(totalGeneral.subtract(montoPagado));
                
                return pago;
            }
            return null;
        }
    }
    
    // Obtener monto total pagado de una orden
    public BigDecimal obtenerMontoPagado(int idOrden) throws SQLException {
        String sql = "SELECT ISNULL(SUM(monto - descuento), 0) as total_pagado " +
                     "FROM Pago WHERE id_orden = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getBigDecimal("total_pagado");
            }
            return BigDecimal.ZERO;
        }
    }
    
    // Registrar un nuevo pago
    public boolean registrarPago(Pago pago) throws SQLException {
        String sql = "INSERT INTO Pago (id_orden, metodo_pago, monto, descuento, estado, " +
                     "fecha_pago, tipo_pago, numero_factura, observaciones) " +
                     "VALUES (?, ?, ?, ?, ?, GETDATE(), ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            // Generar número de factura automáticamente
            String numeroFactura = obtenerSiguienteNumeroFactura();
            
            stmt.setInt(1, pago.getIdOrden());
            stmt.setString(2, pago.getMetodoPago());
            stmt.setBigDecimal(3, pago.getMonto());
            stmt.setBigDecimal(4, pago.getDescuento());
            stmt.setString(5, pago.getEstado());
            stmt.setString(6, pago.getTipoPago());
            stmt.setString(7, numeroFactura);
            stmt.setString(8, pago.getObservaciones());
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    pago.setIdPago(rs.getInt(1));
                    pago.setNumeroFactura(numeroFactura);
                }
                
                // Actualizar estado de la orden si está completamente pagada
                actualizarEstadoOrden(pago.getIdOrden());
                
                return true;
            }
            return false;
        }
    }
    
    // Actualizar estado de orden según pagos
    private void actualizarEstadoOrden(int idOrden) throws SQLException {
        Pago info = obtenerInfoOrdenParaPago(idOrden);
        
        if (info != null && info.getMontoPendiente().compareTo(BigDecimal.ZERO) <= 0) {
            String sql = "UPDATE OrdenTrabajo SET estado = 'Pagado' WHERE id_orden = ?";
            try (Connection conn = ConexionDB.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idOrden);
                stmt.executeUpdate();
            }
        }
    }
    
    // Listar todos los pagos
    public List<Pago> listarPagos() throws SQLException {
        String sql = "SELECT p.*, c.nombre + ' ' + c.apellido as nombre_cliente, v.placa " +
                     "FROM Pago p " +
                     "INNER JOIN OrdenTrabajo ot ON p.id_orden = ot.id_orden " +
                     "INNER JOIN Cliente c ON ot.id_cliente = c.id_cliente " +
                     "INNER JOIN Vehiculo v ON ot.id_vehiculo = v.id_vehiculo " +
                     "ORDER BY p.fecha_pago DESC";
        
        List<Pago> pagos = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdOrden(rs.getInt("id_orden"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setDescuento(rs.getBigDecimal("descuento"));
                pago.setEstado(rs.getString("estado"));
                pago.setFechaPago(rs.getTimestamp("fecha_pago"));
                pago.setTipoPago(rs.getString("tipo_pago"));
                pago.setNumeroFactura(rs.getString("numero_factura"));
                pago.setObservaciones(rs.getString("observaciones"));
                pago.setNombreCliente(rs.getString("nombre_cliente"));
                pago.setPlacaVehiculo(rs.getString("placa"));
                
                pagos.add(pago);
            }
        }
        
        return pagos;
    }
    
    // Obtener pago por ID
    public Pago obtenerPagoPorId(int idPago) throws SQLException {
        String sql = "SELECT p.*, c.nombre + ' ' + c.apellido as nombre_cliente, v.placa " +
                     "FROM Pago p " +
                     "INNER JOIN OrdenTrabajo ot ON p.id_orden = ot.id_orden " +
                     "INNER JOIN Cliente c ON ot.id_cliente = c.id_cliente " +
                     "INNER JOIN Vehiculo v ON ot.id_vehiculo = v.id_vehiculo " +
                     "WHERE p.id_pago = ?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPago);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdOrden(rs.getInt("id_orden"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setDescuento(rs.getBigDecimal("descuento"));
                pago.setEstado(rs.getString("estado"));
                pago.setFechaPago(rs.getTimestamp("fecha_pago"));
                pago.setTipoPago(rs.getString("tipo_pago"));
                pago.setNumeroFactura(rs.getString("numero_factura"));
                pago.setObservaciones(rs.getString("observaciones"));
                pago.setNombreCliente(rs.getString("nombre_cliente"));
                pago.setPlacaVehiculo(rs.getString("placa"));
                
                return pago;
            }
        }
        
        return null;
    }
    
    // Listar pagos por orden
    public List<Pago> listarPagosPorOrden(int idOrden) throws SQLException {
        String sql = "SELECT * FROM Pago WHERE id_orden = ? ORDER BY fecha_pago DESC";
        
        List<Pago> pagos = new ArrayList<>();
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idOrden);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdOrden(rs.getInt("id_orden"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setDescuento(rs.getBigDecimal("descuento"));
                pago.setEstado(rs.getString("estado"));
                pago.setFechaPago(rs.getTimestamp("fecha_pago"));
                pago.setTipoPago(rs.getString("tipo_pago"));
                pago.setNumeroFactura(rs.getString("numero_factura"));
                pago.setObservaciones(rs.getString("observaciones"));
                
                pagos.add(pago);
            }
        }
        
        return pagos;
    }
}