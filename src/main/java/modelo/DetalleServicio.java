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

public class DetalleServicio {
    private int idDetalleServicio;
    private int idOrden;
    private int idServicio;
    private int cantidad;
    private BigDecimal subtotal;
    
    // Campos adicionales para mostrar información
    private String nombreServicio;
    private String descripcionServicio;
    private BigDecimal precioServicio;
    
    // Constructor vacío
    public DetalleServicio() {
        this.cantidad = 1;
        this.subtotal = BigDecimal.ZERO;
    }
    
    // Constructor con parámetros
    public DetalleServicio(int idOrden, int idServicio, int cantidad, BigDecimal subtotal) {
        this.idOrden = idOrden;
        this.idServicio = idServicio;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    // Getters y Setters
    public int getIdDetalleServicio() {
        return idDetalleServicio;
    }
    
    public void setIdDetalleServicio(int idDetalleServicio) {
        this.idDetalleServicio = idDetalleServicio;
    }
    
    public int getIdOrden() {
        return idOrden;
    }
    
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
    
    public int getIdServicio() {
        return idServicio;
    }
    
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public String getNombreServicio() {
        return nombreServicio;
    }
    
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }
    
    public String getDescripcionServicio() {
        return descripcionServicio;
    }
    
    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }
    
    public BigDecimal getPrecioServicio() {
        return precioServicio;
    }
    
    public void setPrecioServicio(BigDecimal precioServicio) {
        this.precioServicio = precioServicio;
    }
}
