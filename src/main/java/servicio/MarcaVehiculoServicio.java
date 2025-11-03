/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import dao.MarcaVehiculoDao;
import modelo.MarcaVehiculo;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Eduar Medrano
 */
public class MarcaVehiculoServicio {
    private MarcaVehiculoDao marcaDao = new MarcaVehiculoDao();

    // Index
    public List<MarcaVehiculo> obtenerMarcas() throws SQLException {
        return marcaDao.listar();
    }

    // Store
    public void registrarMarca(MarcaVehiculo m) throws SQLException {
        if (m.getNombreMarca() == null || m.getNombreMarca().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca es obligatorio");
        }
        marcaDao.insertar(m);
    }

    // Show
    public MarcaVehiculo obtenerPorId(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID de la marca no es válido");
        }
        return marcaDao.buscarPorId(id);
    }

    // Update
    public void actualizar(MarcaVehiculo m) {
        if (m.getNombreMarca() == null || m.getNombreMarca().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca es obligatorio");
        }
        marcaDao.actualizar(m);
    }

    // Delete
    public void eliminar(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("El ID de la marca no es válido");
        }
        marcaDao.eliminar(id);
    }
}
