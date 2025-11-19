/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Eduar Medrano
 */

import dao.VehiculoDao;
import modelo.Vehiculo;
import java.sql.SQLException;
import java.util.List;

public class VehiculoServicio {
    
    private VehiculoDao vehiculoDao = new VehiculoDao();

    // Index
    public List<Vehiculo> obtenerVehiculos() throws SQLException {
        return vehiculoDao.listar();
    }

    // Store
    public void registrarVehiculo(Vehiculo v) throws SQLException {

        if (v.getModelo() == null || v.getModelo().isEmpty()) {
            throw new IllegalArgumentException("El modelo es obligatorio");
        }
        if (v.getAnio() < 1900 || v.getAnio() > 2100) {
            throw new IllegalArgumentException("El año no es válido");
        }
        if (v.getPlaca() == null || v.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("La placa es obligatoria");
        }
        if (v.getIdCliente() < 1) {
            throw new IllegalArgumentException("Debe seleccionar un cliente válido");
        }
        if (v.getIdTipo() < 1) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de vehículo válido");
        }
        if (v.getIdMarca() < 1) {
            throw new IllegalArgumentException("Debe seleccionar una marca válida");
        }

        vehiculoDao.insertar(v);
    }

    // Show
    public Vehiculo obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del vehículo no es válido");
        }
        return vehiculoDao.buscarPorId(id);
    }

    // Update
    public void actualizar(Vehiculo v) {

        if (v.getModelo() == null || v.getModelo().isEmpty()) {
            throw new IllegalArgumentException("El modelo es obligatorio");
        }
        if (v.getAnio() < 1900 || v.getAnio() > 2100) {
            throw new IllegalArgumentException("El año no es válido");
        }
        if (v.getPlaca() == null || v.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("La placa es obligatoria");
        }
        if (v.getIdCliente() < 1) {
            throw new IllegalArgumentException("Debe seleccionar un cliente válido");
        }
        if (v.getIdTipo() < 1) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de vehículo válido");
        }
        if (v.getIdMarca() < 1) {
            throw new IllegalArgumentException("Debe seleccionar una marca válida");
        }

        vehiculoDao.actualizar(v);
    }

    // Delete
    public void eliminar(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del vehículo no es válido");
        }
        vehiculoDao.eliminar(id);
    }
}
