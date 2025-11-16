/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dao.TipoVehiculoDao;
import modelo.TipoVehiculo;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Eduar Medrano
 */
public class TipoVehiculoServicio {
     private TipoVehiculoDao tipoDao = new TipoVehiculoDao();

    // Index
    public List<TipoVehiculo> obtenerTipos() throws SQLException {
        return tipoDao.listar();
    }

    // Store
    public void registrarTipo(TipoVehiculo t) throws SQLException {
        if (t.getNombreTipo() == null || t.getNombreTipo().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de vehículo es obligatorio");
        }
        tipoDao.insertar(t);
    }

    // Show
    public TipoVehiculo obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del tipo no es válido");
        }
        return tipoDao.buscarPorId(id);
    }

    // Update
    public void actualizar(TipoVehiculo t) {
        if (t.getNombreTipo() == null || t.getNombreTipo().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo es obligatorio");
        }
        tipoDao.actualizar(t);
    }

    // Delete
    public void eliminar(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID del tipo no es válido");
        }
        tipoDao.eliminar(id);
    }
}
