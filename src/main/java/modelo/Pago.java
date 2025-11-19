/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author fuent
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pago {
    private int idPago;
    private int idOrden;
    private String metodoPago;
    private BigDecimal monto;
    private BigDecimal descuento;
    private String estado;
    private Timestamp fechaPago;
    private String tipoPago;
    private String numeroFactura;
    private String observaciones;
    
    // Campos adicionales para mostrar información relacionada
    private String nombreCliente;
    private String placaVehiculo;
    private BigDecimal totalManoObra;
    private BigDecimal totalRepuestos;
    private BigDecimal totalGeneral;
    private BigDecimal montoPendiente;
    
    // Constructor vacío
    public Pago() {
        this.descuento = BigDecimal.ZERO;
        this.estado = "Pendiente";
        this.fechaPago = new Timestamp(System.currentTimeMillis());
        this.tipoPago = "Total";
    }
    
    // Constructor completo
    public Pago(int idPago, int idOrden, String metodoPago, BigDecimal monto, 
                BigDecimal descuento, String estado, Timestamp fechaPago, 
                String tipoPago, String numeroFactura, String observaciones) {
        this.idPago = idPago;
        this.idOrden = idOrden;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.descuento = descuento;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.tipoPago = tipoPago;
        this.numeroFactura = numeroFactura;
        this.observaciones = observaciones;
    }
    
    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }
    
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
    
    public int getIdOrden() {
        return idOrden;
    }
    
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
    
    public String getMetodoPago() {
        return metodoPago;
    }
    
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    public BigDecimal getDescuento() {
        return descuento;
    }
    
    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Timestamp getFechaPago() {
        return fechaPago;
    }
    
    public void setFechaPago(Timestamp fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    public String getTipoPago() {
        return tipoPago;
    }
    
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    public String getNumeroFactura() {
        return numeroFactura;
    }
    
    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    
    public BigDecimal getTotalManoObra() {
        return totalManoObra;
    }
    
    public void setTotalManoObra(BigDecimal totalManoObra) {
        this.totalManoObra = totalManoObra;
    }
    
    public BigDecimal getTotalRepuestos() {
        return totalRepuestos;
    }
    
    public void setTotalRepuestos(BigDecimal totalRepuestos) {
        this.totalRepuestos = totalRepuestos;
    }
    
    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }
    
    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }
    
    public BigDecimal getMontoPendiente() {
        return montoPendiente;
    }
    
    public void setMontoPendiente(BigDecimal montoPendiente) {
        this.montoPendiente = montoPendiente;
    }
    
    // Método para calcular el monto final después del descuento
    public BigDecimal calcularMontoFinal() {
        if (monto == null) return BigDecimal.ZERO;
        if (descuento == null) return monto;
        return monto.subtract(descuento);
    }
}