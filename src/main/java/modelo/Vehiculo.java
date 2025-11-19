/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Eduar Medrano
 */
public class Vehiculo {
    private int idVehiculo;
    private String modelo;
    private int anio;
    private String placa;
    private String numMotor;
    private String color;
    private int kilometraje;
    private String fechaIngreso;
    
    private String numChasis;
    private String historialServicioUrl;
    private String estadoVehiculo;
    
    private int idCliente;
    private int idTipo;
    private int idMarca;

    //Para index, no llenar
    private String nombreCliente;
    private String nombreTipo;
    private String nombreMarca;
    //Para index, no llenar
    
    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String modelo, int anio, String placa, String numMotor, String color, int kilometraje, String fechaIngreso, String numChasis, String historialServicioUrl, String estadoVehiculo, int idCliente, int idTipo, int idMarca, String nombreCliente, String nombreTipo, String nombreMarca) {
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.anio = anio;
        this.placa = placa;
        this.numMotor = numMotor;
        this.color = color;
        this.kilometraje = kilometraje;
        this.fechaIngreso = fechaIngreso;
        this.numChasis = numChasis;
        this.historialServicioUrl = historialServicioUrl;
        this.estadoVehiculo = estadoVehiculo;
        this.idCliente = idCliente;
        this.idTipo = idTipo;
        this.idMarca = idMarca;
        this.nombreCliente = nombreCliente;
        this.nombreTipo = nombreTipo;
        this.nombreMarca = nombreMarca;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNumChasis() {
        return numChasis;
    }

    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    public String getHistorialServicioUrl() {
        return historialServicioUrl;
    }

    public void setHistorialServicioUrl(String historialServicioUrl) {
        this.historialServicioUrl = historialServicioUrl;
    }

    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(String estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    
}
