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

public class ReporteIngreso {
    private String tipo; // "Servicio" o "Repuesto"
    private String nombre;
    private String categoria; // Para servicios
    private int cantidadVendida;
    private BigDecimal precioUnitario;
    private BigDecimal ingresoTotal;
    
    // Constructor vacío
    public ReporteIngreso() {
    }
    
    // Constructor completo
    public ReporteIngreso(String tipo, String nombre, String categoria, 
                         int cantidadVendida, BigDecimal precioUnitario, 
                         BigDecimal ingresoTotal) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidadVendida = cantidadVendida;
        this.precioUnitario = precioUnitario;
        this.ingresoTotal = ingresoTotal;
    }
    
    // Getters y Setters
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public int getCantidadVendida() {
        return cantidadVendida;
    }
    
    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public BigDecimal getIngresoTotal() {
        return ingresoTotal;
    }
    
    public void setIngresoTotal(BigDecimal ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }
}