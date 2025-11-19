/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author fuent
 */
import java.sql.Timestamp;
import java.math.BigDecimal;

public class OrdenTrabajo {
    private int idOrden;
    private int idVehiculo;
    private int idCliente;
    private int idEmpleado;
    private Timestamp fechaIngreso;
    private Timestamp fechaEntrega;
    private String estado;
    private String diagnostico;
    private String observaciones;
    private BigDecimal tiempoEstimado;
    private String documentosUrl;
    private BigDecimal totalManoObra;
    private BigDecimal totalRepuestos;
    private BigDecimal totalGeneral;
    
    // Campos adicionales para mostrar información relacionada
    private String nombreCliente;
    private String telefonoCliente;
    private String placaVehiculo;
    private String modeloVehiculo;
    private String marcaVehiculo;
    private String nombreEmpleado;
    private String cargoEmpleado;
    
    // Constructor vacío
    public OrdenTrabajo() {
        this.fechaIngreso = new Timestamp(System.currentTimeMillis());
        this.estado = "Pendiente";
        this.totalManoObra = BigDecimal.ZERO;
        this.totalRepuestos = BigDecimal.ZERO;
    }
    
    // Constructor con parámetros básicos
    public OrdenTrabajo(int idVehiculo, int idCliente, String diagnostico) {
        this();
        this.idVehiculo = idVehiculo;
        this.idCliente = idCliente;
        this.diagnostico = diagnostico;
    }
    
    // Getters y Setters
    public int getIdOrden() {
        return idOrden;
    }
    
    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }
    
    public int getIdVehiculo() {
        return idVehiculo;
    }
    
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }
    
    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    
    public Timestamp getFechaEntrega() {
        return fechaEntrega;
    }
    
    public void setFechaEntrega(Timestamp fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public BigDecimal getTiempoEstimado() {
        return tiempoEstimado;
    }
    
    public void setTiempoEstimado(BigDecimal tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
    
    public String getDocumentosUrl() {
        return documentosUrl;
    }
    
    public void setDocumentosUrl(String documentosUrl) {
        this.documentosUrl = documentosUrl;
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
    
    public String getNombreCliente() {
        return nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    public String getTelefonoCliente() {
        return telefonoCliente;
    }
    
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    
    public String getPlacaVehiculo() {
        return placaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    
    public String getModeloVehiculo() {
        return modeloVehiculo;
    }
    
    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }
    
    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }
    
    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
    
    public String getNombreEmpleado() {
        return nombreEmpleado;
    }
    
    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }
    
    public String getCargoEmpleado() {
        return cargoEmpleado;
    }
    
    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
    }
}