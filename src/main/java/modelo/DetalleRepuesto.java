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

public class DetalleRepuesto {
    private int idDetalleRepuesto;
    private int idOrden;
    private int idRepuesto;
    private int cantidad;
    private BigDecimal subtotal;
    
    // Campos adicionales para mostrar información
    private String nombreRepuesto;
    private String descripcionRepuesto;
    private BigDecimal precioRepuesto;
    
    // Constructor vacío
    public DetalleRepuesto() {
        this.cantidad = 1;
        this.subtotal = BigDecimal.ZERO;
    }
    
    // Constructor con parámetros
    public DetalleRepuesto(int idOrden, int idRepuesto, int cantidad, BigDecimal subtotal) {
        this.idOrden = idOrden;
        this.idRepuesto = idRepuesto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    // Getters y Setters
    public int getIdDetalleRepuesto() {
        return idDetalleRepuesto;
    }
    
    public void setIdDetalleRepuesto(int idDetalleRepuesto) {
        this.idDetalleRepuesto = idDetalleRepuesto;
    }
    
    public int getIdOrden() {
        return idOrden;
    }
    
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
    
    public int getIdRepuesto() {
        return idRepuesto;
    }
    
    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
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
    
    public String getNombreRepuesto() {
        return nombreRepuesto;
    }
    
    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto;
    }
    
    public String getDescripcionRepuesto() {
        return descripcionRepuesto;
    }
    
    public void setDescripcionRepuesto(String descripcionRepuesto) {
        this.descripcionRepuesto = descripcionRepuesto;
    }
    
    public BigDecimal getPrecioRepuesto() {
        return precioRepuesto;
    }
    
    public void setPrecioRepuesto(BigDecimal precioRepuesto) {
        this.precioRepuesto = precioRepuesto;
    }
}